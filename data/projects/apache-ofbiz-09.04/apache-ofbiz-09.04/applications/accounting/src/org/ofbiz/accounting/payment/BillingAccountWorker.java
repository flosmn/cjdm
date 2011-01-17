/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package org.ofbiz.accounting.payment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;
import java.util.Comparator;
import java.math.BigDecimal;

import javolution.util.FastList;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.GeneralException;
import org.ofbiz.base.util.UtilDateTime;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilNumber;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.GenericDelegator;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.entity.condition.EntityCondition;
import org.ofbiz.entity.condition.EntityConditionList;
import org.ofbiz.entity.condition.EntityExpr;
import org.ofbiz.entity.condition.EntityJoinOperator;
import org.ofbiz.entity.condition.EntityOperator;
import org.ofbiz.entity.util.EntityUtil;
import org.ofbiz.order.order.OrderReadHelper;
import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.LocalDispatcher;
import org.ofbiz.service.ServiceUtil;

/**
 * Worker methods for BillingAccounts
 */
public class BillingAccountWorker {

    public static final String module = BillingAccountWorker.class.getName();
    private static BigDecimal ZERO = BigDecimal.ZERO;
    private static int decimals = -1;
    private static int rounding = -1;
    static {
        decimals = UtilNumber.getBigDecimalScale("order.decimals");
        rounding = UtilNumber.getBigDecimalRoundingMode("order.rounding");

        // set zero to the proper scale
        if (decimals != -1) ZERO = ZERO.setScale(decimals);
    }

    public static List makePartyBillingAccountList(GenericValue userLogin, String currencyUomId, String partyId, GenericDelegator delegator, LocalDispatcher dispatcher) throws GeneralException {
        List billingAccountList = FastList.newInstance();

        Map agentResult = dispatcher.runSync("getRelatedParties", UtilMisc.<String, Object>toMap("userLogin", userLogin, "partyIdFrom", partyId,
                "roleTypeIdFrom", "AGENT", "roleTypeIdTo", "CUSTOMER", "partyRelationshipTypeId", "AGENT", "includeFromToSwitched", "Y"));
        if (ServiceUtil.isError(agentResult)) {
            throw new GeneralException("Error while finding party BillingAccounts when getting Customers that this party is an agent of: " + ServiceUtil.getErrorMessage(agentResult));
        }
        List relatedPartyIdList = (List) agentResult.get("relatedPartyIdList");

        EntityCondition barFindCond = EntityCondition.makeCondition(UtilMisc.toList(
                EntityCondition.makeCondition("partyId", EntityOperator.IN, relatedPartyIdList),
                EntityCondition.makeCondition("roleTypeId", EntityOperator.EQUALS, "BILL_TO_CUSTOMER")), EntityOperator.AND);
        List<GenericValue> billingAccountRoleList = delegator.findList("BillingAccountRole", barFindCond, null, null, null, false);
        billingAccountRoleList = EntityUtil.filterByDate(billingAccountRoleList);

        if (billingAccountRoleList.size() > 0) {
            BigDecimal totalAvailable = BigDecimal.ZERO;
            Iterator billingAcctIter = billingAccountRoleList.iterator();
            while (billingAcctIter.hasNext()) {
                GenericValue billingAccountRole = (GenericValue) billingAcctIter.next();
                GenericValue billingAccountVO = billingAccountRole.getRelatedOne("BillingAccount");

                // skip accounts that have thruDate < nowTimestamp
                java.sql.Timestamp thruDate = billingAccountVO.getTimestamp("thruDate");
                if ((thruDate != null) && UtilDateTime.nowTimestamp().after(thruDate)) continue;

                if (currencyUomId.equals(billingAccountVO.getString("accountCurrencyUomId"))) {
                    BigDecimal accountBalance = BillingAccountWorker.getBillingAccountBalance(billingAccountVO);

                    Map billingAccount = new HashMap(billingAccountVO);
                    BigDecimal accountLimit = getAccountLimit(billingAccountVO);

                    billingAccount.put("accountBalance", accountBalance);
                    BigDecimal accountAvailable = accountLimit.subtract(accountBalance);
                    totalAvailable = totalAvailable.add(accountAvailable);
                    billingAccountList.add(billingAccount);
                }
            }
            Collections.sort(billingAccountList, new BillingAccountComparator());
        }
        return billingAccountList;
    }

    /**
     * Returns the accountLimit of the BillingAccount or BigDecimal ZERO if it is null
     * @param billingAccount
     * @throws GenericEntityException
     */
    public static BigDecimal getAccountLimit(GenericValue billingAccount) throws GenericEntityException {
        if (billingAccount.getBigDecimal("accountLimit") != null) {
            return billingAccount.getBigDecimal("accountLimit");
        } else {
            Debug.logWarning("Billing Account [" + billingAccount.getString("billingAccountId") + "] does not have an account limit defined, assuming zero.", module);
            return ZERO;
        }
    }

    /**
     * Calculates the "available" balance of a billing account, which is the
     * net balance minus amount of pending (not cancelled, rejected, or received) order payments.
     * When looking at using a billing account for a new order, you should use this method.
     * @param billingAccountId
     * @param delegator
     * @return
     * @throws GenericEntityException
     */
    public static BigDecimal getBillingAccountBalance(GenericDelegator delegator, String billingAccountId) throws GenericEntityException {
        GenericValue billingAccount = delegator.findByPrimaryKey("BillingAccount", UtilMisc.toMap("billingAccountId", billingAccountId));
        return getBillingAccountBalance(billingAccount);
    }

    public static BigDecimal getBillingAccountBalance(GenericValue billingAccount) throws GenericEntityException {

        GenericDelegator delegator = billingAccount.getDelegator();
        String billingAccountId = billingAccount.getString("billingAccountId");

        BigDecimal balance = ZERO;
        BigDecimal accountLimit = getAccountLimit(billingAccount);
        balance = balance.add(accountLimit);
        // pending (not cancelled, rejected, or received) order payments
        EntityConditionList whereConditions = EntityCondition.makeCondition(UtilMisc.toList(
                EntityCondition.makeCondition("billingAccountId", EntityOperator.EQUALS, billingAccountId),
                EntityCondition.makeCondition("paymentMethodTypeId", EntityOperator.EQUALS, "EXT_BILLACT"),
                EntityCondition.makeCondition("statusId", EntityOperator.NOT_IN, UtilMisc.toList("ORDER_CANCELLED", "ORDER_REJECTED")),
                EntityCondition.makeCondition("preferenceStatusId", EntityOperator.NOT_IN, UtilMisc.toList("PAYMENT_SETTLED", "PAYMENT_RECEIVED", "PAYMENT_DECLINED", "PAYMENT_CANCELLED")) // PAYMENT_NOT_AUTH
            ), EntityOperator.AND);

        List orderPaymentPreferenceSums = delegator.findList("OrderPurchasePaymentSummary", whereConditions, UtilMisc.toSet("maxAmount"), null, null, false);
        for (Iterator oppsi = orderPaymentPreferenceSums.iterator(); oppsi.hasNext(); ) {
            GenericValue orderPaymentPreferenceSum = (GenericValue) oppsi.next();
            BigDecimal maxAmount = orderPaymentPreferenceSum.getBigDecimal("maxAmount");
            balance = maxAmount != null ? balance.subtract(maxAmount) : balance;
        }

        List paymentAppls = delegator.findByAnd("PaymentApplication", UtilMisc.toMap("billingAccountId", billingAccountId));
        // TODO: cancelled payments?
        for (Iterator pAi = paymentAppls.iterator(); pAi.hasNext(); ) {
            GenericValue paymentAppl = (GenericValue) pAi.next();
            if (paymentAppl.getString("invoiceId") == null) {
                BigDecimal amountApplied = paymentAppl.getBigDecimal("amountApplied");
                balance = balance.add(amountApplied);
            }
        }

        balance = balance.setScale(decimals, rounding);
        return balance;
        /*
        GenericDelegator delegator = billingAccount.getDelegator();
        String billingAccountId = billingAccount.getString("billingAccountId");

        // first get the net balance of invoices - payments
        BigDecimal balance = getBillingAccountNetBalance(delegator, billingAccountId);

        // now the amounts of all the pending orders (not cancelled, rejected or completed)
        List orderHeaders = getBillingAccountOpenOrders(delegator, billingAccountId);

        if (orderHeaders != null) {
            Iterator ohi = orderHeaders.iterator();
            while (ohi.hasNext()) {
                GenericValue orderHeader = (GenericValue) ohi.next();
                OrderReadHelper orh = new OrderReadHelper(orderHeader);
                balance = balance.add(orh.getOrderGrandTotal());
            }
        }

        // set the balance to BillingAccount.accountLimit if it is greater.  This is necessary because nowhere do we track the amount of BillingAccount
        // to be charged to an order, such as FinAccountAuth entity does for FinAccount.  As a result, we must assume that the system is doing things correctly
        // and use the accountLimit
        BigDecimal accountLimit = billingAccount.getBigDecimal("accountLimit");
        if (balance.compareTo(accountLimit) > 0) {
            balance = accountLimit;
        } else {
            balance = balance.setScale(decimals, rounding);
        }
        return balance;
         */
    }

    /**
     * Returns list of orders which are currently open against a billing account
     */
    public static List getBillingAccountOpenOrders(GenericDelegator delegator, String billingAccountId) throws GenericEntityException {
        EntityConditionList<EntityExpr> ecl = EntityCondition.makeCondition(UtilMisc.toList(
                EntityCondition.makeCondition("billingAccountId", EntityOperator.EQUALS, billingAccountId),
                EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "ORDER_REJECTED"),
                EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "ORDER_CANCELLED"),
                EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "ORDER_COMPLETED")),
                EntityJoinOperator.AND);
        return delegator.findList("OrderHeader", ecl, null, null, null, false);
    }

    /**
     * Returns the amount which could be charged to a billing account, which is defined as the accountLimit minus account balance and minus the balance of outstanding orders
     * When trying to figure out how much of a billing account can be used to pay for an outstanding order, use this method
     * @param billingAccount
     * @return
     * @throws GenericEntityException
     */
    public static BigDecimal getBillingAccountAvailableBalance(GenericValue billingAccount) throws GenericEntityException {
        if ((billingAccount != null) && (billingAccount.get("accountLimit") != null)) {
            BigDecimal accountLimit = billingAccount.getBigDecimal("accountLimit");
            BigDecimal availableBalance = accountLimit.subtract(getBillingAccountBalance(billingAccount)).setScale(decimals, rounding);
            return availableBalance;
        } else {
            Debug.logWarning("Available balance requested for null billing account, returning zero", module);
            return ZERO;
        }
    }

    public static BigDecimal getBillingAccountAvailableBalance(GenericDelegator delegator, String billingAccountId) throws GenericEntityException {
        GenericValue billingAccount = delegator.findByPrimaryKey("BillingAccount", UtilMisc.toMap("billingAccountId", billingAccountId));
        return getBillingAccountAvailableBalance(billingAccount);
    }

    /**
     * Calculates the net balance of a billing account, which is sum of all amounts applied to invoices minus sum of all amounts applied from payments.
     * When charging or capturing an invoice to a billing account, use this method
     * @param delegator
     * @param billingAccountId
     * @return
     * @throws GenericEntityException
     */
    public static BigDecimal getBillingAccountNetBalance(GenericDelegator delegator, String billingAccountId) throws GenericEntityException {
        BigDecimal balance = ZERO;

        // search through all PaymentApplications and add the amount that was applied to invoice and subtract the amount applied from payments
        List paymentAppls = delegator.findByAnd("PaymentApplication", UtilMisc.toMap("billingAccountId", billingAccountId));
        for (Iterator pAi = paymentAppls.iterator(); pAi.hasNext(); ) {
            GenericValue paymentAppl = (GenericValue) pAi.next();
            BigDecimal amountApplied = paymentAppl.getBigDecimal("amountApplied");
            GenericValue invoice = paymentAppl.getRelatedOne("Invoice");
            if (invoice != null) {
                // make sure the invoice has not been canceled and it is not a "Customer return invoice"
                if (!"CUST_RTN_INVOICE".equals(invoice.getString("invoiceTypeId")) && !"INVOICE_CANCELLED".equals(invoice.getString("statusId"))) {
                    balance = balance.add(amountApplied);
                }
            } else {
                balance = balance.subtract(amountApplied);
            }
        }

        balance = balance.setScale(decimals, rounding);
        return balance;
    }

    /**
     * Returns the amount of the billing account which could be captured, which is BillingAccount.accountLimit - net balance
     * @param billingAccount
     * @return
     * @throws GenericEntityException
     */
    public static BigDecimal availableToCapture(GenericValue billingAccount) throws GenericEntityException {
        BigDecimal netBalance = getBillingAccountNetBalance(billingAccount.getDelegator(), billingAccount.getString("billingAccountId"));
        BigDecimal accountLimit = billingAccount.getBigDecimal("accountLimit");

        return accountLimit.subtract(netBalance).setScale(decimals, rounding);
    }

    public static Map calcBillingAccountBalance(DispatchContext dctx, Map context) {
        GenericDelegator delegator = dctx.getDelegator();
        String billingAccountId = (String) context.get("billingAccountId");
        Map result = ServiceUtil.returnSuccess();

        try {
            GenericValue billingAccount = delegator.findByPrimaryKey("BillingAccount", UtilMisc.toMap("billingAccountId", billingAccountId));
            if (billingAccount == null) {
                return ServiceUtil.returnError("Unable to locate billing account #" + billingAccountId);
            }

            result.put("billingAccount", billingAccount);
            result.put("accountBalance",  getBillingAccountBalance(delegator, billingAccountId));
            result.put("netAccountBalance", getBillingAccountNetBalance(delegator, billingAccountId));
            result.put("availableBalance", getBillingAccountAvailableBalance(billingAccount));
            result.put("availableToCapture", availableToCapture(billingAccount));

            return result;
        } catch (GenericEntityException e) {
            Debug.logError(e, module);
            return ServiceUtil.returnError("Error getting billing account or calculating balance for billing account #" + billingAccountId);
        }
    }

    private static class BillingAccountComparator implements Comparator {
        public int compare(Object billingAccount1, Object billingAccount2) {
            return ((BigDecimal)((Map)billingAccount1).get("accountBalance")).compareTo((BigDecimal)((Map)billingAccount2).get("accountBalance"));
        }
    }
}
