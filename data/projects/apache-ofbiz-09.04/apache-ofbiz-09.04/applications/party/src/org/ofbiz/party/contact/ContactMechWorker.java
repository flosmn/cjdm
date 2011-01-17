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

package org.ofbiz.party.contact;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;

import javolution.util.FastList;
import javolution.util.FastMap;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilProperties;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.GenericDelegator;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.entity.condition.EntityCondition;
import org.ofbiz.entity.util.EntityUtil;

/**
 * Worker methods for Contact Mechanisms
 */
public class ContactMechWorker {

    public static final String module = ContactMechWorker.class.getName();

    /** @deprecated */
    public static void getPartyContactMechValueMaps(PageContext pageContext, String partyId, boolean showOld, String partyContactMechValueMapsAttr) {
        GenericDelegator delegator = (GenericDelegator) pageContext.getRequest().getAttribute("delegator");
        List<Map<String, Object>> partyContactMechValueMaps = getPartyContactMechValueMaps(delegator, partyId, showOld);
        if (partyContactMechValueMaps.size() > 0) {
            pageContext.setAttribute(partyContactMechValueMapsAttr, partyContactMechValueMaps);
        }
    }

    public static List<Map<String, Object>> getPartyContactMechValueMaps(GenericDelegator delegator, String partyId, boolean showOld) {
       return getPartyContactMechValueMaps(delegator, partyId, showOld, null);
    }

    public static List<Map<String, Object>> getPartyContactMechValueMaps(GenericDelegator delegator, String partyId, boolean showOld, String contactMechTypeId) {
        List<Map<String, Object>> partyContactMechValueMaps = FastList.newInstance();

        List<GenericValue> allPartyContactMechs = null;

        try {
            List<GenericValue> tempCol = delegator.findByAnd("PartyContactMech", UtilMisc.toMap("partyId", partyId));
            if (contactMechTypeId != null) {
                List<GenericValue> tempColTemp = FastList.newInstance();
                for (GenericValue partyContactMech: tempCol) {
                    GenericValue contactMech = delegator.getRelatedOne("ContactMech", partyContactMech);
                    if (contactMech != null && contactMechTypeId.equals(contactMech.getString("contactMechTypeId"))) {
                        tempColTemp.add(partyContactMech);
                    }

                }
                tempCol = tempColTemp;
            }
            if (!showOld) tempCol = EntityUtil.filterByDate(tempCol, true);
            allPartyContactMechs = tempCol;
        } catch (GenericEntityException e) {
            Debug.logWarning(e, module);
        }

        if (allPartyContactMechs == null) return partyContactMechValueMaps;

        for (GenericValue partyContactMech: allPartyContactMechs) {
            GenericValue contactMech = null;

            try {
                contactMech = partyContactMech.getRelatedOne("ContactMech");
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
            if (contactMech != null) {
                Map<String, Object> partyContactMechValueMap = FastMap.newInstance();

                partyContactMechValueMaps.add(partyContactMechValueMap);
                partyContactMechValueMap.put("contactMech", contactMech);
                partyContactMechValueMap.put("partyContactMech", partyContactMech);

                try {
                    partyContactMechValueMap.put("contactMechType", contactMech.getRelatedOneCache("ContactMechType"));
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }

                try {
                    List<GenericValue> partyContactMechPurposes = partyContactMech.getRelated("PartyContactMechPurpose");

                    if (!showOld) partyContactMechPurposes = EntityUtil.filterByDate(partyContactMechPurposes, true);
                    partyContactMechValueMap.put("partyContactMechPurposes", partyContactMechPurposes);
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }

                try {
                    if ("POSTAL_ADDRESS".equals(contactMech.getString("contactMechTypeId"))) {
                        partyContactMechValueMap.put("postalAddress", contactMech.getRelatedOne("PostalAddress"));
                    } else if ("TELECOM_NUMBER".equals(contactMech.getString("contactMechTypeId"))) {
                        partyContactMechValueMap.put("telecomNumber", contactMech.getRelatedOne("TelecomNumber"));
                    }
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
            }
        }

        return partyContactMechValueMaps;
    }

    public static List<Map<String, Object>> getFacilityContactMechValueMaps(GenericDelegator delegator, String facilityId, boolean showOld, String contactMechTypeId) {
        List<Map<String, Object>> facilityContactMechValueMaps = FastList.newInstance();

        List<GenericValue> allFacilityContactMechs = null;

        try {
            List<GenericValue> tempCol = delegator.findByAnd("FacilityContactMech", UtilMisc.toMap("facilityId", facilityId));
            if (contactMechTypeId != null) {
                List<GenericValue> tempColTemp = FastList.newInstance();
                for (GenericValue partyContactMech: tempCol) {
                    GenericValue contactMech = delegator.getRelatedOne("ContactMech", partyContactMech);
                    if (contactMech != null && contactMechTypeId.equals(contactMech.getString("contactMechTypeId"))) {
                        tempColTemp.add(partyContactMech);
                    }

                }
                tempCol = tempColTemp;
            }
            if (!showOld) tempCol = EntityUtil.filterByDate(tempCol, true);
            allFacilityContactMechs = tempCol;
        } catch (GenericEntityException e) {
            Debug.logWarning(e, module);
        }

        if (allFacilityContactMechs == null) return facilityContactMechValueMaps;

        for (GenericValue facilityContactMech: allFacilityContactMechs) {
            GenericValue contactMech = null;

            try {
                contactMech = facilityContactMech.getRelatedOne("ContactMech");
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
            if (contactMech != null) {
                Map<String, Object> facilityContactMechValueMap = FastMap.newInstance();

                facilityContactMechValueMaps.add(facilityContactMechValueMap);
                facilityContactMechValueMap.put("contactMech", contactMech);
                facilityContactMechValueMap.put("facilityContactMech", facilityContactMech);

                try {
                    facilityContactMechValueMap.put("contactMechType", contactMech.getRelatedOneCache("ContactMechType"));
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }

                try {
                    List<GenericValue> facilityContactMechPurposes = facilityContactMech.getRelated("FacilityContactMechPurpose");

                    if (!showOld) facilityContactMechPurposes = EntityUtil.filterByDate(facilityContactMechPurposes, true);
                    facilityContactMechValueMap.put("facilityContactMechPurposes", facilityContactMechPurposes);
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }

                try {
                    if ("POSTAL_ADDRESS".equals(contactMech.getString("contactMechTypeId"))) {
                        facilityContactMechValueMap.put("postalAddress", contactMech.getRelatedOne("PostalAddress"));
                    } else if ("TELECOM_NUMBER".equals(contactMech.getString("contactMechTypeId"))) {
                        facilityContactMechValueMap.put("telecomNumber", contactMech.getRelatedOne("TelecomNumber"));
                    }
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
            }
        }

        return facilityContactMechValueMaps;
    }


    /** @deprecated */
    public static void getOrderContactMechValueMaps(PageContext pageContext, String orderId, String orderContactMechValueMapsAttr) {
        GenericDelegator delegator = (GenericDelegator) pageContext.getRequest().getAttribute("delegator");
        List<Map<String, GenericValue>> maps = getOrderContactMechValueMaps(delegator, orderId);
        if (UtilValidate.isNotEmpty(maps)) {
            pageContext.setAttribute(orderContactMechValueMapsAttr, maps);
        }
    }
    public static List<Map<String, GenericValue>> getOrderContactMechValueMaps(GenericDelegator delegator, String orderId) {
        List<Map<String, GenericValue>> orderContactMechValueMaps = FastList.newInstance();

        List<GenericValue> allOrderContactMechs = null;

        try {
            allOrderContactMechs = delegator.findByAnd("OrderContactMech", UtilMisc.toMap("orderId", orderId), UtilMisc.toList("contactMechPurposeTypeId"));
        } catch (GenericEntityException e) {
            Debug.logWarning(e, module);
        }

        if (allOrderContactMechs == null) return orderContactMechValueMaps;

        for (GenericValue orderContactMech: allOrderContactMechs) {
            GenericValue contactMech = null;

            try {
                contactMech = orderContactMech.getRelatedOne("ContactMech");
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
            if (contactMech != null) {
                Map<String, GenericValue> orderContactMechValueMap = FastMap.newInstance();

                orderContactMechValueMaps.add(orderContactMechValueMap);
                orderContactMechValueMap.put("contactMech", contactMech);
                orderContactMechValueMap.put("orderContactMech", orderContactMech);

                try {
                    orderContactMechValueMap.put("contactMechType", contactMech.getRelatedOneCache("ContactMechType"));
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }

                try {
                    GenericValue contactMechPurposeType = orderContactMech.getRelatedOne("ContactMechPurposeType");

                    orderContactMechValueMap.put("contactMechPurposeType", contactMechPurposeType);
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }

                try {
                    if ("POSTAL_ADDRESS".equals(contactMech.getString("contactMechTypeId"))) {
                        orderContactMechValueMap.put("postalAddress", contactMech.getRelatedOne("PostalAddress"));
                    } else if ("TELECOM_NUMBER".equals(contactMech.getString("contactMechTypeId"))) {
                        orderContactMechValueMap.put("telecomNumber", contactMech.getRelatedOne("TelecomNumber"));
                    }
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
            }
        }

        return orderContactMechValueMaps;
    }

    public static Collection<Map<String, GenericValue>> getWorkEffortContactMechValueMaps(GenericDelegator delegator, String workEffortId) {
        Collection<Map<String, GenericValue>> workEffortContactMechValueMaps = FastList.newInstance();

        List<GenericValue> allWorkEffortContactMechs = null;

        try {
            allWorkEffortContactMechs = delegator.findByAnd("WorkEffortContactMech", UtilMisc.toMap("workEffortId", workEffortId));
        } catch (GenericEntityException e) {
            Debug.logWarning(e, module);
        }

        if (allWorkEffortContactMechs == null) return null;

        for (GenericValue workEffortContactMech: allWorkEffortContactMechs) {
            GenericValue contactMech = null;

            try {
                contactMech = workEffortContactMech.getRelatedOne("ContactMech");
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
            if (contactMech != null) {
                Map<String, GenericValue> workEffortContactMechValueMap = FastMap.newInstance();

                workEffortContactMechValueMaps.add(workEffortContactMechValueMap);
                workEffortContactMechValueMap.put("contactMech", contactMech);
                workEffortContactMechValueMap.put("workEffortContactMech", workEffortContactMech);

                try {
                    workEffortContactMechValueMap.put("contactMechType", contactMech.getRelatedOneCache("ContactMechType"));
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }

                try {
                    if ("POSTAL_ADDRESS".equals(contactMech.getString("contactMechTypeId"))) {
                        workEffortContactMechValueMap.put("postalAddress", contactMech.getRelatedOne("PostalAddress"));
                    } else if ("TELECOM_NUMBER".equals(contactMech.getString("contactMechTypeId"))) {
                        workEffortContactMechValueMap.put("telecomNumber", contactMech.getRelatedOne("TelecomNumber"));
                    }
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
            }
        }

        return workEffortContactMechValueMaps.size() > 0 ? workEffortContactMechValueMaps : null;
    }

    public static void getContactMechAndRelated(ServletRequest request, String partyId, Map<String, Object> target) {
        GenericDelegator delegator = (GenericDelegator) request.getAttribute("delegator");

        boolean tryEntity = true;
        if (request.getAttribute("_ERROR_MESSAGE_") != null) tryEntity = false;
        if ("true".equals(request.getParameter("tryEntity"))) tryEntity = true;

        String donePage = request.getParameter("DONE_PAGE");
        if (donePage == null) donePage = (String) request.getAttribute("DONE_PAGE");
        if (donePage == null || donePage.length() <= 0) donePage = "viewprofile";
        target.put("donePage", donePage);

        String contactMechTypeId = request.getParameter("preContactMechTypeId");

        if (contactMechTypeId == null) contactMechTypeId = (String) request.getAttribute("preContactMechTypeId");
        if (contactMechTypeId != null)
            tryEntity = false;

        String contactMechId = request.getParameter("contactMechId");

        if (request.getAttribute("contactMechId") != null)
            contactMechId = (String) request.getAttribute("contactMechId");

        GenericValue contactMech = null;

        if (contactMechId != null) {
            target.put("contactMechId", contactMechId);

            // try to find a PartyContactMech with a valid date range
            List<GenericValue> partyContactMechs = null;

            try {
                partyContactMechs = EntityUtil.filterByDate(delegator.findByAnd("PartyContactMech", UtilMisc.toMap("partyId", partyId, "contactMechId", contactMechId)), true);
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }

            GenericValue partyContactMech = EntityUtil.getFirst(partyContactMechs);

            if (partyContactMech != null) {
                target.put("partyContactMech", partyContactMech);

                Collection<GenericValue> partyContactMechPurposes = null;

                try {
                    partyContactMechPurposes = EntityUtil.filterByDate(partyContactMech.getRelated("PartyContactMechPurpose"), true);
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
                if (partyContactMechPurposes != null && partyContactMechPurposes.size() > 0)
                    target.put("partyContactMechPurposes", partyContactMechPurposes);
            }

            try {
                contactMech = delegator.findByPrimaryKey("ContactMech", UtilMisc.toMap("contactMechId", contactMechId));
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }

            if (contactMech != null) {
                target.put("contactMech", contactMech);
                contactMechTypeId = contactMech.getString("contactMechTypeId");
            }
        }

        if (contactMechTypeId != null) {
            target.put("contactMechTypeId", contactMechTypeId);

            try {
                GenericValue contactMechType = delegator.findByPrimaryKey("ContactMechType", UtilMisc.toMap("contactMechTypeId", contactMechTypeId));

                if (contactMechType != null)
                    target.put("contactMechType", contactMechType);
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }

            Collection<GenericValue> purposeTypes = FastList.newInstance();
            Iterator<GenericValue> typePurposes = null;

            try {
                typePurposes = UtilMisc.toIterator(delegator.findByAnd("ContactMechTypePurpose", UtilMisc.toMap("contactMechTypeId", contactMechTypeId)));
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
            while (typePurposes != null && typePurposes.hasNext()) {
                GenericValue contactMechTypePurpose = typePurposes.next();
                GenericValue contactMechPurposeType = null;

                try {
                    contactMechPurposeType = contactMechTypePurpose.getRelatedOne("ContactMechPurposeType");
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
                if (contactMechPurposeType != null) {
                    purposeTypes.add(contactMechPurposeType);
                }
            }
            if (purposeTypes.size() > 0)
                target.put("purposeTypes", purposeTypes);
        }

        String requestName;

        if (contactMech == null) {
            // create
            if ("POSTAL_ADDRESS".equals(contactMechTypeId)) {
                if (request.getParameter("contactMechPurposeTypeId") != null || request.getAttribute("contactMechPurposeTypeId") != null) {
                    requestName = "createPostalAddressAndPurpose";
                } else {
                    requestName = "createPostalAddress";
                }
            } else if ("TELECOM_NUMBER".equals(contactMechTypeId)) {
                requestName = "createTelecomNumber";
            } else if ("EMAIL_ADDRESS".equals(contactMechTypeId)) {
                requestName = "createEmailAddress";
            } else {
                requestName = "createContactMech";
            }
        } else {
            // update
            if ("POSTAL_ADDRESS".equals(contactMechTypeId)) {
                requestName = "updatePostalAddress";
            } else if ("TELECOM_NUMBER".equals(contactMechTypeId)) {
                requestName = "updateTelecomNumber";
            } else if ("EMAIL_ADDRESS".equals(contactMechTypeId)) {
                requestName = "updateEmailAddress";
            } else {
                requestName = "updateContactMech";
            }
        }
        target.put("requestName", requestName);

        if ("POSTAL_ADDRESS".equals(contactMechTypeId)) {
            GenericValue postalAddress = null;

            try {
                if (contactMech != null) postalAddress = contactMech.getRelatedOne("PostalAddress");
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
            if (postalAddress != null) target.put("postalAddress", postalAddress);
        } else if ("TELECOM_NUMBER".equals(contactMechTypeId)) {
            GenericValue telecomNumber = null;

            try {
                if (contactMech != null) telecomNumber = contactMech.getRelatedOne("TelecomNumber");
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
            if (telecomNumber != null) target.put("telecomNumber", telecomNumber);
        }

        if ("true".equals(request.getParameter("useValues"))) tryEntity = true;
        target.put("tryEntity", Boolean.valueOf(tryEntity));

        try {
            Collection<GenericValue> contactMechTypes = delegator.findList("ContactMechType", null, null, null, null, true);

            if (contactMechTypes != null) {
                target.put("contactMechTypes", contactMechTypes);
            }
        } catch (GenericEntityException e) {
            Debug.logWarning(e, module);
        }
    }

    /** Returns the first valid FacilityContactMech found based on the given facilityId and a prioritized list of purposes
     * @param delegator
     * @param facilityId
     * @param purposeTypes A List of ContactMechPurposeType ids which will be checked one at a time until a valid contact mech is found
     * @return
     */
    public static GenericValue getFacilityContactMechByPurpose(GenericDelegator delegator, String facilityId, List<String> purposeTypes) {
        if (UtilValidate.isEmpty(facilityId)) return null;
        if (UtilValidate.isEmpty(purposeTypes)) return null;

        for (String purposeType: purposeTypes) {
            List<GenericValue> facilityContactMechPurposes = null;
            List<EntityCondition> conditionList = FastList.newInstance();
            conditionList.add(EntityCondition.makeCondition("facilityId", facilityId));
            conditionList.add(EntityCondition.makeCondition("contactMechPurposeTypeId", purposeType));
            conditionList.add(EntityCondition.makeConditionDate("fromDate", "thruDate"));
            EntityCondition entityCondition = EntityCondition.makeCondition(conditionList);
            try {
                facilityContactMechPurposes = delegator.findList("FacilityContactMechPurpose", entityCondition, null, UtilMisc.toList("-fromDate"), null, true);
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
            for (GenericValue facilityContactMechPurpose: facilityContactMechPurposes) {
                String contactMechId = facilityContactMechPurpose.getString("contactMechId");
                List<GenericValue> facilityContactMechs = null;
                conditionList = FastList.newInstance();
                conditionList.add(EntityCondition.makeCondition("facilityId", facilityId));
                conditionList.add(EntityCondition.makeCondition("contactMechId", contactMechId));
                conditionList.add(EntityCondition.makeConditionDate("fromDate", "thruDate"));
                entityCondition = EntityCondition.makeCondition(conditionList);
                try {
                    facilityContactMechs = delegator.findList("FacilityContactMech", entityCondition, null, UtilMisc.toList("-fromDate"), null, true);
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
                if (UtilValidate.isNotEmpty(facilityContactMechs)) {
                    return EntityUtil.getFirst(facilityContactMechs);
                }
            }

        }
        return null;
    }

    public static void getFacilityContactMechAndRelated(ServletRequest request, String facilityId, Map<String, Object> target) {
        GenericDelegator delegator = (GenericDelegator) request.getAttribute("delegator");

        boolean tryEntity = true;
        if (request.getAttribute("_ERROR_MESSAGE") != null) tryEntity = false;
        if ("true".equals(request.getParameter("tryEntity"))) tryEntity = true;

        String donePage = request.getParameter("DONE_PAGE");
        if (donePage == null) donePage = (String) request.getAttribute("DONE_PAGE");
        if (donePage == null || donePage.length() <= 0) donePage = "viewprofile";
        target.put("donePage", donePage);

        String contactMechTypeId = request.getParameter("preContactMechTypeId");

        if (contactMechTypeId == null) contactMechTypeId = (String) request.getAttribute("preContactMechTypeId");
        if (contactMechTypeId != null)
            tryEntity = false;

        String contactMechId = request.getParameter("contactMechId");

        if (request.getAttribute("contactMechId") != null)
            contactMechId = (String) request.getAttribute("contactMechId");

        GenericValue contactMech = null;

        if (contactMechId != null) {
            target.put("contactMechId", contactMechId);

            // try to find a PartyContactMech with a valid date range
            List<GenericValue> facilityContactMechs = null;

            try {
                facilityContactMechs = EntityUtil.filterByDate(delegator.findByAnd("FacilityContactMech", UtilMisc.toMap("facilityId", facilityId, "contactMechId", contactMechId)), true);
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }

            GenericValue facilityContactMech = EntityUtil.getFirst(facilityContactMechs);

            if (facilityContactMech != null) {
                target.put("facilityContactMech", facilityContactMech);

                Collection<GenericValue> facilityContactMechPurposes = null;

                try {
                    facilityContactMechPurposes = EntityUtil.filterByDate(facilityContactMech.getRelated("FacilityContactMechPurpose"), true);
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
                if (facilityContactMechPurposes != null && facilityContactMechPurposes.size() > 0)
                    target.put("facilityContactMechPurposes", facilityContactMechPurposes);
            }

            try {
                contactMech = delegator.findByPrimaryKey("ContactMech", UtilMisc.toMap("contactMechId", contactMechId));
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }

            if (contactMech != null) {
                target.put("contactMech", contactMech);
                contactMechTypeId = contactMech.getString("contactMechTypeId");
            }
        }

        if (contactMechTypeId != null) {
            target.put("contactMechTypeId", contactMechTypeId);

            try {
                GenericValue contactMechType = delegator.findByPrimaryKey("ContactMechType", UtilMisc.toMap("contactMechTypeId", contactMechTypeId));

                if (contactMechType != null)
                    target.put("contactMechType", contactMechType);
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }

            Collection<GenericValue> purposeTypes = FastList.newInstance();
            Iterator<GenericValue> typePurposes = null;

            try {
                typePurposes = UtilMisc.toIterator(delegator.findByAnd("ContactMechTypePurpose", UtilMisc.toMap("contactMechTypeId", contactMechTypeId)));
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
            while (typePurposes != null && typePurposes.hasNext()) {
                GenericValue contactMechTypePurpose = typePurposes.next();
                GenericValue contactMechPurposeType = null;

                try {
                    contactMechPurposeType = contactMechTypePurpose.getRelatedOne("ContactMechPurposeType");
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
                if (contactMechPurposeType != null) {
                    purposeTypes.add(contactMechPurposeType);
                }
            }
            if (purposeTypes.size() > 0)
                target.put("purposeTypes", purposeTypes);
        }

        String requestName;

        if (contactMech == null) {
            // create
            if ("POSTAL_ADDRESS".equals(contactMechTypeId)) {
                if (request.getParameter("contactMechPurposeTypeId") != null || request.getAttribute("contactMechPurposeTypeId") != null) {
                    requestName = "createPostalAddressAndPurpose";
                } else {
                    requestName = "createPostalAddress";
                }
            } else if ("TELECOM_NUMBER".equals(contactMechTypeId)) {
                requestName = "createTelecomNumber";
            } else if ("EMAIL_ADDRESS".equals(contactMechTypeId)) {
                requestName = "createEmailAddress";
            } else {
                requestName = "createContactMech";
            }
        } else {
            // update
            if ("POSTAL_ADDRESS".equals(contactMechTypeId)) {
                requestName = "updatePostalAddress";
            } else if ("TELECOM_NUMBER".equals(contactMechTypeId)) {
                requestName = "updateTelecomNumber";
            } else if ("EMAIL_ADDRESS".equals(contactMechTypeId)) {
                requestName = "updateEmailAddress";
            } else {
                requestName = "updateContactMech";
            }
        }
        target.put("requestName", requestName);

        if ("POSTAL_ADDRESS".equals(contactMechTypeId)) {
            GenericValue postalAddress = null;

            try {
                if (contactMech != null) postalAddress = contactMech.getRelatedOne("PostalAddress");
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
            if (postalAddress != null) target.put("postalAddress", postalAddress);
        } else if ("TELECOM_NUMBER".equals(contactMechTypeId)) {
            GenericValue telecomNumber = null;

            try {
                if (contactMech != null) telecomNumber = contactMech.getRelatedOne("TelecomNumber");
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
            if (telecomNumber != null) target.put("telecomNumber", telecomNumber);
        }

        if ("true".equals(request.getParameter("useValues"))) tryEntity = true;
        target.put("tryEntity", Boolean.valueOf(tryEntity));

        try {
            Collection contactMechTypes = delegator.findList("ContactMechType", null, null, null, null, true);

            if (contactMechTypes != null) {
                target.put("contactMechTypes", contactMechTypes);
            }
        } catch (GenericEntityException e) {
            Debug.logWarning(e, module);
        }
    }

    public static List<Map<String, Object>> getPartyPostalAddresses(ServletRequest request, String partyId, String curContactMechId) {
        GenericDelegator delegator = (GenericDelegator) request.getAttribute("delegator");
        List<Map<String, Object>> postalAddressInfos = FastList.newInstance();

        List<GenericValue> allPartyContactMechs = null;

        try {
            allPartyContactMechs = EntityUtil.filterByDate(delegator.findByAnd("PartyContactMech", UtilMisc.toMap("partyId", partyId)), true);
        } catch (GenericEntityException e) {
            Debug.logWarning(e, module);
        }

        if (allPartyContactMechs == null) return postalAddressInfos;

        for (GenericValue partyContactMech: allPartyContactMechs) {
            GenericValue contactMech = null;

            try {
                contactMech = partyContactMech.getRelatedOne("ContactMech");
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
            if (contactMech != null && "POSTAL_ADDRESS".equals(contactMech.getString("contactMechTypeId")) && !contactMech.getString("contactMechId").equals(curContactMechId)) {
                Map<String, Object> postalAddressInfo = FastMap.newInstance();

                postalAddressInfos.add(postalAddressInfo);
                postalAddressInfo.put("contactMech", contactMech);
                postalAddressInfo.put("partyContactMech", partyContactMech);

                try {
                    GenericValue postalAddress = contactMech.getRelatedOne("PostalAddress");
                    postalAddressInfo.put("postalAddress", postalAddress);
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }

                try {
                    List<GenericValue> partyContactMechPurposes = EntityUtil.filterByDate(partyContactMech.getRelated("PartyContactMechPurpose"), true);
                    postalAddressInfo.put("partyContactMechPurposes", partyContactMechPurposes);
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
            }
        }

        return postalAddressInfos;
    }

    public static Map<String, Object> getCurrentPostalAddress(ServletRequest request, String partyId, String curContactMechId) {
        GenericDelegator delegator = (GenericDelegator) request.getAttribute("delegator");
        Map<String, Object> results = FastMap.newInstance();

        if (curContactMechId != null) {
            List<GenericValue> partyContactMechs = null;

            try {
                partyContactMechs = EntityUtil.filterByDate(delegator.findByAnd("PartyContactMech", UtilMisc.toMap("partyId", partyId, "contactMechId", curContactMechId)), true);
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
            GenericValue curPartyContactMech = EntityUtil.getFirst(partyContactMechs);
            results.put("curPartyContactMech", curPartyContactMech);

            GenericValue curContactMech = null;
            if (curPartyContactMech != null) {
                try {
                    curContactMech = curPartyContactMech.getRelatedOne("ContactMech");
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }

                Collection<GenericValue> curPartyContactMechPurposes = null;
                try {
                    curPartyContactMechPurposes = EntityUtil.filterByDate(curPartyContactMech.getRelated("PartyContactMechPurpose"), true);
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
                results.put("curPartyContactMechPurposes", curPartyContactMechPurposes);
            }
            results.put("curContactMech", curContactMech);

            GenericValue curPostalAddress = null;
            if (curContactMech != null) {
                try {
                    curPostalAddress = curContactMech.getRelatedOne("PostalAddress");
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
            }

            results.put("curPostalAddress", curPostalAddress);
        }
        return results;
    }

    public static boolean isUspsAddress(GenericValue postalAddress) {
        if (postalAddress == null) {
            // null postal address is not a USPS address
            return false;
        }
        if (!"PostalAddress".equals(postalAddress.getEntityName())) {
            // not a postal address not a USPS address
            return false;
        }

        // get and clean the address strings
        String addr1 = postalAddress.getString("address1");
        String addr2 = postalAddress.getString("address2");

        // get the matching string from general.properties
        String matcher = UtilProperties.getPropertyValue("general.properties", "usps.address.match");
        if (UtilValidate.isNotEmpty(matcher)) {
            if (addr1 != null && addr1.toLowerCase().matches(matcher)) {
                return true;
            }
            if (addr2 != null && addr2.toLowerCase().matches(matcher)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isCompanyAddress(GenericValue postalAddress, String companyPartyId) {
        if (postalAddress == null) {
            // null postal address is not an internal address
            return false;
        }
        if (!"PostalAddress".equals(postalAddress.getEntityName())) {
            // not a postal address not an internal address
            return false;
        }
        if (companyPartyId == null) {
            // no partyId not an internal address
            return false;
        }

        String state = postalAddress.getString("stateProvinceGeoId");
        String addr1 = postalAddress.getString("address1");
        String addr2 = postalAddress.getString("address2");
        if (state != null) {
            state = state.replaceAll("\\W", "").toLowerCase();
        } else {
            state = "";
        }
        if (addr1 != null) {
            addr1 = addr1.replaceAll("\\W", "").toLowerCase();
        } else {
            addr1 = "";
        }
        if (addr2 != null) {
            addr2 = addr2.replaceAll("\\W", "").toLowerCase();
        } else {
            addr2 = "";
        }

        // get all company addresses
        GenericDelegator delegator = postalAddress.getDelegator();
        List<GenericValue> postalAddresses = FastList.newInstance();
        try {
            List<GenericValue> partyContactMechs = delegator.findByAnd("PartyContactMech", UtilMisc.toMap("partyId", companyPartyId));
            partyContactMechs = EntityUtil.filterByDate(partyContactMechs);
            if (partyContactMechs != null) {
                for (GenericValue pcm: partyContactMechs) {
                    GenericValue addr = pcm.getRelatedOne("PostalAddress");
                    if (addr != null) {
                        postalAddresses.add(addr);
                    }
                }
            }
        } catch (GenericEntityException e) {
            Debug.logError(e, "Unable to get party postal addresses", module);
        }

        if (postalAddresses != null) {
            for (GenericValue addr: postalAddresses) {
                String thisAddr1 = addr.getString("address1");
                String thisAddr2 = addr.getString("address2");
                String thisState = addr.getString("stateProvinceGeoId");
                if (thisState != null) {
                    thisState = thisState.replaceAll("\\W", "").toLowerCase();
                } else {
                    thisState = "";
                }
                if (thisAddr1 != null) {
                    thisAddr1 = thisAddr1.replaceAll("\\W", "").toLowerCase();
                } else {
                    thisAddr1 = "";
                }
                if (thisAddr2 != null) {
                    thisAddr2 = thisAddr2.replaceAll("\\W", "").toLowerCase();
                } else {
                    thisAddr2 = "";
                }
                if (thisAddr1.equals(addr1) && thisAddr2.equals(addr2) && thisState.equals(state)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String getContactMechAttribute(GenericDelegator delegator, String contactMechId, String attrName) {
        GenericValue attr = null;
        try {
            attr = delegator.findByPrimaryKey("ContactMechAttribute", UtilMisc.toMap("contactMechId", contactMechId, "attrName", attrName));
        } catch (GenericEntityException e) {
            Debug.logError(e, module);
        }
        if (attr == null) {
            return null;
        } else {
            return attr.getString("attrValue");
        }
    }

    public static String getPostalAddressPostalCodeGeoId(GenericValue postalAddress, GenericDelegator delegator) throws GenericEntityException {
        // if postalCodeGeoId not empty use that
        if (UtilValidate.isNotEmpty(postalAddress.getString("postalCodeGeoId"))) {
            return postalAddress.getString("postalCodeGeoId");
        }

        // no postalCodeGeoId, see if there is a Geo record matching the countryGeoId and postalCode fields
        if (UtilValidate.isNotEmpty(postalAddress.getString("countryGeoId")) && UtilValidate.isNotEmpty(postalAddress.getString("postalCode"))) {
            // first try the shortcut with the geoId convention for "{countryGeoId}-{postalCode}"
            GenericValue geo = delegator.findByPrimaryKeyCache("Geo", UtilMisc.toMap("geoId", postalAddress.getString("countryGeoId") + "-" + postalAddress.getString("postalCode")));
            if (geo != null) {
                // save the value to the database for quicker future reference
                postalAddress.set("postalCodeGeoId", geo.getString("geoId"));
                postalAddress.store();

                return geo.getString("geoId");
            }

            // no shortcut, try the longcut to see if there is something with a geoCode associated to the countryGeoId
            List<GenericValue> geoAssocAndGeoToList = delegator.findByAndCache("GeoAssocAndGeoTo",
                    UtilMisc.toMap("geoIdFrom", postalAddress.getString("countryGeoId"), "geoCode", postalAddress.getString("postalCode"), "geoAssocTypeId", "REGIONS"));
            GenericValue geoAssocAndGeoTo = EntityUtil.getFirst(geoAssocAndGeoToList);
            if (geoAssocAndGeoTo != null) {
                // save the value to the database for quicker future reference
                postalAddress.set("postalCodeGeoId", geoAssocAndGeoTo.getString("geoId"));
                postalAddress.store();

                return geoAssocAndGeoTo.getString("geoId");
            }
        }

        // nothing found, return null
        return null;
    }
}
