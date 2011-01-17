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

package org.ofbiz.workeffort.workeffort;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javolution.util.FastList;
import javolution.util.FastMap;
import javolution.util.FastSet;

import org.ofbiz.base.util.DateRange;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.TimeDuration;
import org.ofbiz.base.util.UtilDateTime;
import org.ofbiz.base.util.UtilGenerics;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilProperties;
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
import org.ofbiz.security.Security;
import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.LocalDispatcher;
import org.ofbiz.service.ServiceUtil;
import org.ofbiz.service.calendar.TemporalExpression;
import org.ofbiz.service.calendar.TemporalExpressionWorker;

/**
 * WorkEffortServices - WorkEffort related Services
 */
public class WorkEffortServices {

    public static final String module = WorkEffortServices.class.getName();

    public static Map<String, Object> getWorkEffortAssignedEventsForRole(DispatchContext ctx, Map<String, ? extends Object> context) {
        GenericDelegator delegator = ctx.getDelegator();
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        String roleTypeId = (String) context.get("roleTypeId");

        List<GenericValue> validWorkEfforts = null;

        if (userLogin != null && userLogin.get("partyId") != null) {
            try {
                EntityConditionList<EntityExpr> ecl = EntityCondition.makeCondition(
                        EntityOperator.AND,
                        EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, userLogin.get("partyId")),
                        EntityCondition.makeCondition("roleTypeId", EntityOperator.EQUALS, roleTypeId),
                        EntityCondition.makeCondition("workEffortTypeId", EntityOperator.EQUALS, "EVENT"),
                        EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_DECLINED"),
                        EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_DELEGATED"),
                        EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_COMPLETED"),
                        EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_CANCELLED")
                );
                validWorkEfforts = EntityUtil.filterByDate(
                        delegator.findList("WorkEffortAndPartyAssign", ecl, null, UtilMisc.toList("estimatedStartDate", "priority"), null, false)
                );
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
                return ServiceUtil.returnError("Error finding desired WorkEffort records: " + e.toString());
            }
        }

        Map<String, Object> result = FastMap.newInstance();
        if (validWorkEfforts == null) {
            validWorkEfforts = FastList.newInstance();
        }
        result.put("events", validWorkEfforts);
        return result;
    }

    public static Map<String, Object> getWorkEffortAssignedEventsForRoleOfAllParties(DispatchContext ctx, Map<String, ? extends Object> context) {
        GenericDelegator delegator = ctx.getDelegator();
        String roleTypeId = (String) context.get("roleTypeId");

        List validWorkEfforts = null;

        try {
            List<EntityExpr> conditionList = FastList.newInstance();
            conditionList.add(EntityCondition.makeCondition("roleTypeId", EntityOperator.EQUALS, roleTypeId));
            conditionList.add(EntityCondition.makeCondition("workEffortTypeId", EntityOperator.EQUALS, "EVENT"));
            conditionList.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_DECLINED"));
            conditionList.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_DELEGATED"));
            conditionList.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_COMPLETED"));
            conditionList.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_CANCELLED"));

            EntityConditionList<EntityExpr> ecl = EntityCondition.makeCondition(conditionList, EntityOperator.AND);
            validWorkEfforts = EntityUtil.filterByDate(
                    delegator.findList("WorkEffortAndPartyAssign", ecl, null, UtilMisc.toList("estimatedStartDate", "priority"), null, false)
            );
        } catch (GenericEntityException e) {
            Debug.logWarning(e, module);
            return ServiceUtil.returnError("Error finding desired WorkEffort records: " + e.toString());
        }

        Map<String, Object> result = FastMap.newInstance();
        if (validWorkEfforts == null) {
            validWorkEfforts = FastList.newInstance();
        }
        result.put("events", validWorkEfforts);
        return result;
    }

    public static Map<String, Object> getWorkEffortAssignedTasks(DispatchContext ctx, Map<String, ? extends Object> context) {
        GenericDelegator delegator = ctx.getDelegator();
        GenericValue userLogin = (GenericValue) context.get("userLogin");

        List<GenericValue> validWorkEfforts = null;

        if (userLogin != null && userLogin.get("partyId") != null) {
            try {
                EntityConditionList<EntityExpr> ecl = EntityCondition.makeCondition(
                        EntityOperator.AND,
                        EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, userLogin.get("partyId")),
                        EntityCondition.makeCondition("workEffortTypeId", EntityOperator.EQUALS, "TASK"),
                        EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_DECLINED"),
                        EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_DELEGATED"),
                        EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_COMPLETED"),
                        EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_CANCELLED"),
                        EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "PRTYASGN_UNASSIGNED"));
                validWorkEfforts = EntityUtil.filterByDate(delegator.findList("WorkEffortAndPartyAssign", ecl, null, UtilMisc.toList("priority"), null, false));
                ecl = EntityCondition.makeCondition(
                        EntityOperator.AND,
                        EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, userLogin.get("partyId")),
                        EntityCondition.makeCondition("workEffortTypeId", EntityOperator.EQUALS, "PROD_ORDER_TASK"),
                        EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "PRUN_CANCELLED "),
                        EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "PRUN_COMPLETED"),
                        EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "PRUN_CLOSED"));
                validWorkEfforts.addAll(EntityUtil.filterByDate(delegator.findList("WorkEffortAndPartyAssign", ecl, null, UtilMisc.toList("createdDate DESC"), null, false)));
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
                return ServiceUtil.returnError("Error finding desired WorkEffort records: " + e.toString());
            }
        }

        Map<String, Object> result = FastMap.newInstance();
        if (validWorkEfforts == null) validWorkEfforts = FastList.newInstance();
        result.put("tasks", validWorkEfforts);
        return result;
    }

    public static Map<String, Object> getWorkEffortAssignedActivities(DispatchContext ctx, Map<String, ? extends Object> context) {
        GenericDelegator delegator = ctx.getDelegator();
        GenericValue userLogin = (GenericValue) context.get("userLogin");

        List<GenericValue> validWorkEfforts = null;

        if (userLogin != null && userLogin.get("partyId") != null) {
            try {
                List<EntityExpr> constraints = FastList.newInstance();

                constraints.add(EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, userLogin.get("partyId")));
                constraints.add(EntityCondition.makeCondition("workEffortTypeId", EntityOperator.EQUALS, "ACTIVITY"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "CAL_DECLINED"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "CAL_DELEGATED"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "CAL_COMPLETED"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "CAL_CANCELLED"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "PRTYASGN_UNASSIGNED"));
                constraints.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "WF_COMPLETED"));
                constraints.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "WF_TERMINATED"));
                constraints.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "WF_ABORTED"));

                EntityConditionList<EntityExpr> ecl = EntityCondition.makeCondition(constraints, EntityOperator.AND);
                validWorkEfforts = EntityUtil.filterByDate(delegator.findList("WorkEffortAndPartyAssign", ecl, null, UtilMisc.toList("priority"), null, false));
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
                return ServiceUtil.returnError("Error finding desired WorkEffort records: " + e.toString());
            }
        }

        Map<String, Object> result = FastMap.newInstance();
        if (validWorkEfforts == null) validWorkEfforts = FastList.newInstance();
        result.put("activities", validWorkEfforts);
        return result;
    }

    public static Map<String, Object> getWorkEffortAssignedActivitiesByRole(DispatchContext ctx, Map<String, ? extends Object> context) {
        GenericDelegator delegator = ctx.getDelegator();
        GenericValue userLogin = (GenericValue) context.get("userLogin");

        List<GenericValue> roleWorkEfforts = null;

        if (userLogin != null && userLogin.get("partyId") != null) {
            try {
                List<EntityExpr> constraints = FastList.newInstance();

                constraints.add(EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, userLogin.get("partyId")));
                constraints.add(EntityCondition.makeCondition("workEffortTypeId", EntityOperator.EQUALS, "ACTIVITY"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "CAL_DECLINED"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "CAL_DELEGATED"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "CAL_COMPLETED"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "CAL_CANCELLED"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "PRTYASGN_UNASSIGNED"));
                constraints.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "WF_COMPLETED"));
                constraints.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "WF_TERMINATED"));
                constraints.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "WF_ABORTED"));

                EntityConditionList<EntityExpr> ecl = EntityCondition.makeCondition(constraints);
                roleWorkEfforts = EntityUtil.filterByDate(
                        delegator.findList("WorkEffortPartyAssignByRole", ecl, null, UtilMisc.toList("priority"), null, false)
                );
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
                return ServiceUtil.returnError("Error finding desired WorkEffort records: " + e.toString());
            }
        }

        Map<String, Object> result = FastMap.newInstance();
        if (roleWorkEfforts == null) roleWorkEfforts = FastList.newInstance();
        result.put("roleActivities", roleWorkEfforts);
        return result;
    }

    public static Map<String, Object> getWorkEffortAssignedActivitiesByGroup(DispatchContext ctx, Map<String, ? extends Object> context) {
        GenericDelegator delegator = ctx.getDelegator();
        GenericValue userLogin = (GenericValue) context.get("userLogin");

        List<GenericValue> groupWorkEfforts = null;

        if (userLogin != null && userLogin.get("partyId") != null) {
            try {
                List<EntityExpr> constraints = FastList.newInstance();

                constraints.add(EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, userLogin.get("partyId")));
                constraints.add(EntityCondition.makeCondition("workEffortTypeId", EntityOperator.EQUALS, "ACTIVITY"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "CAL_DECLINED"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "CAL_DELEGATED"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "CAL_COMPLETED"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "CAL_CANCELLED"));
                constraints.add(EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "PRTYASGN_UNASSIGNED"));
                constraints.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "WF_COMPLETED"));
                constraints.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "WF_TERMINATED"));
                constraints.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "WF_ABORTED"));

                EntityConditionList<EntityExpr> ecl = EntityCondition.makeCondition(constraints);
                groupWorkEfforts = EntityUtil.filterByDate(
                        delegator.findList("WorkEffortPartyAssignByGroup", ecl, null, UtilMisc.toList("priority"), null, false)
                );
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
                return ServiceUtil.returnError("Error finding desired WorkEffort records: " + e.toString());
            }
        }

        Map<String, Object> result = FastMap.newInstance();
        if (groupWorkEfforts == null) groupWorkEfforts = FastList.newInstance();
        result.put("groupActivities", groupWorkEfforts);
        return result;
    }

    public static Map<String, Object> getWorkEffort(DispatchContext ctx, Map<String, ? extends Object> context) {
        GenericDelegator delegator = ctx.getDelegator();
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        Security security = ctx.getSecurity();
        Map<String, Object> resultMap = FastMap.newInstance();

        String workEffortId = (String) context.get("workEffortId");
        GenericValue workEffort = null;

        try {
            workEffort = delegator.findOne("WorkEffort", false, "workEffortId", workEffortId);
        } catch (GenericEntityException e) {
            Debug.logWarning(e, module);
        }

        Boolean canView = null;
        Collection workEffortPartyAssignments = null;
        Boolean tryEntity = null;
        GenericValue currentStatus = null;

        if (workEffort == null) {
            tryEntity = Boolean.FALSE;
            canView = Boolean.TRUE;

            String statusId = (String) context.get("currentStatusId");

            if (statusId != null && statusId.length() > 0) {
                try {
                    currentStatus = delegator.findByPrimaryKeyCache("StatusItem", UtilMisc.toMap("statusId", statusId));
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
            }
        } else {
            // get a collection of workEffortPartyAssignments, if empty then this user CANNOT view the event, unless they have permission to view all
            if (userLogin != null && userLogin.get("partyId") != null && workEffortId != null) {
                try {
                    workEffortPartyAssignments = delegator.findByAnd("WorkEffortPartyAssignment", UtilMisc.toMap("workEffortId", workEffortId, "partyId", userLogin.get("partyId")));
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
            }
            canView = (workEffortPartyAssignments != null && workEffortPartyAssignments.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
            if (!canView.booleanValue() && security.hasEntityPermission("WORKEFFORTMGR", "_VIEW", userLogin)) {
                canView = Boolean.TRUE;
            }

            tryEntity = Boolean.TRUE;

            if (workEffort.get("currentStatusId") != null) {
                try {
                    currentStatus = delegator.findByPrimaryKeyCache("StatusItem", UtilMisc.toMap("statusId", workEffort.get("currentStatusId")));
                } catch (GenericEntityException e) {
                    Debug.logWarning(e, module);
                }
            }
        }

        if (workEffortId != null) resultMap.put("workEffortId", workEffortId);
        if (workEffort != null) resultMap.put("workEffort", workEffort);
        if (canView != null) resultMap.put("canView", canView);
        if (workEffortPartyAssignments != null) resultMap.put("partyAssigns", workEffortPartyAssignments);
        if (tryEntity != null) resultMap.put("tryEntity", tryEntity);
        if (currentStatus != null) resultMap.put("currentStatusItem", currentStatus);
        return resultMap;
    }

    private static List<EntityCondition> getDefaultWorkEffortExprList(Collection<String> partyIds, String facilityId, String fixedAssetId, String workEffortTypeId) {
        List<EntityCondition> entityExprList = UtilMisc.<EntityCondition>toList(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_CANCELLED"), EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "PRUN_CANCELLED"));
        List<EntityExpr> typesList = FastList.newInstance();
        if (UtilValidate.isNotEmpty(workEffortTypeId)) {
            typesList.add(EntityCondition.makeCondition("workEffortTypeId", EntityOperator.EQUALS, workEffortTypeId));
        }
        if (UtilValidate.isNotEmpty(partyIds)) {
            entityExprList.add(EntityCondition.makeCondition("partyId", EntityOperator.IN, partyIds));
        }
        if (UtilValidate.isNotEmpty(facilityId)) {
            entityExprList.add(EntityCondition.makeCondition("facilityId", EntityOperator.EQUALS, facilityId));
            typesList.add(EntityCondition.makeCondition("workEffortTypeId", EntityOperator.EQUALS, "PROD_ORDER_HEADER"));
            entityExprList.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "PRUN_CREATED"));
            entityExprList.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "PRUN_COMPLETED"));
            entityExprList.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "PRUN_CLOSED"));
        }
        if (UtilValidate.isNotEmpty(fixedAssetId)) {
            entityExprList.add(EntityCondition.makeCondition("fixedAssetId", EntityOperator.EQUALS, fixedAssetId));
//            typesList.add(EntityCondition.makeCondition("workEffortTypeId", EntityOperator.EQUALS, "PROD_ORDER_TASK"));
            entityExprList.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "PRUN_CREATED"));
            entityExprList.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "PRUN_COMPLETED"));
            entityExprList.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "PRUN_CLOSED"));
        }
        EntityCondition typesCondition = null;
        if (typesList.size() == 0) {
            return entityExprList;
        } else if (typesList.size() == 1) {
            typesCondition = typesList.get(0);
        } else {
            typesCondition = EntityCondition.makeCondition(typesList, EntityJoinOperator.OR);
        }
        entityExprList.add(typesCondition);
        return entityExprList;
    }

    /**
     * Get Work Efforts by period.
     * <p>
     * This method takes the following parameters:
     * </p>
     * <ul>
     * <li>start - TimeStamp (Period start date/time)</li>
     * <li>numPeriods - Integer</li>
     * <li>periodType - Integer (see java.util.Calendar)</li>
     * <li>eventStatus - String</li>
     * <li>partyId - String</li>
     * <li>partyIds - List</li>
     * <li>facilityId - String</li>
     * <li>fixedAssetId - String</li>
     * <li>filterOutCanceledEvents - Boolean</li>
     * <li>entityExprList - List</li>
     * </ul>
     * <p>
     * The method will find all matching Work Effort events and return them as a List called
     * <b>periods</b> - one List element per period. It also returns a
     * <b>maxConcurrentEntries</b> Integer - which indicates the maximum number of
     * Work Efforts found in one period.
     * </p>
     * <p>
     * Each <b>periods</b> list element is a Map containing the following
     * key/value pairs:
     * </p>
     * <ul>
     * <li>start - TimeStamp (Period start date/time)</li>
     * <li>end - TimeStamp (Period end date/time)</li>
     * <li>calendarEntries - List of Maps. Each Map contains the following
     * key/value pairs:</li>
     * <ul>
     * <li>workEffort - GenericValue</li>
     * <li>periodSpan - Integer (Number of periods this Work Effort spans)</li>
     * <li>startOfPeriod - Boolean (true if this is the first occurrence in the period range)</li>
     * </ul>
     * </ul>
     */
    public static Map<String, Object> getWorkEffortEventsByPeriod(DispatchContext ctx, Map<String, ? extends Object> context) {
        GenericDelegator delegator = ctx.getDelegator();
        Security security = ctx.getSecurity();
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        Locale locale = (Locale) context.get("locale");
        TimeZone timeZone = (TimeZone) context.get("timeZone");

        Timestamp startDay = (Timestamp) context.get("start");
        Integer numPeriodsInteger = (Integer) context.get("numPeriods");

        String partyId = (String) context.get("partyId");
        Collection<String> partyIds = UtilGenerics.checkCollection(context.get("partyIds"));
        String facilityId = (String) context.get("facilityId");
        String fixedAssetId = (String) context.get("fixedAssetId");
        String workEffortTypeId = (String) context.get("workEffortTypeId");
        Boolean filterOutCanceledEvents = (Boolean) context.get("filterOutCanceledEvents");
        if (filterOutCanceledEvents == null) {
            filterOutCanceledEvents = Boolean.FALSE;
        }

        // To be returned, the max concurrent entries for a single period
        int maxConcurrentEntries = 0;

        Integer periodTypeObject = (Integer) context.get("periodType");
        int periodType = 0;
        if (periodTypeObject != null) {
            periodType = periodTypeObject.intValue();
        }

        int numPeriods = 0;
        if (numPeriodsInteger != null) {
            numPeriods = numPeriodsInteger.intValue();
        }

        // get a timestamp (date) for the beginning of today and for beginning of numDays+1 days from now
        Timestamp startStamp = UtilDateTime.getDayStart(startDay, timeZone, locale);
        Timestamp endStamp = UtilDateTime.adjustTimestamp(startStamp, periodType, 1, timeZone, locale);
        long periodLen = endStamp.getTime() - startStamp.getTime();
        endStamp = UtilDateTime.adjustTimestamp(startStamp, periodType, numPeriods, timeZone, locale);

        // Get the WorkEfforts
        List<GenericValue> validWorkEfforts = null;
        Collection<String> partyIdsToUse = partyIds;
        if (partyIdsToUse == null) {
            partyIdsToUse = FastSet.newInstance();
        }
        if (UtilValidate.isNotEmpty(partyId)) {
            if (partyId.equals(userLogin.getString("partyId")) || security.hasEntityPermission("WORKEFFORTMGR", "_VIEW", userLogin)) {
                partyIdsToUse.add(partyId);
            } else {
                return ServiceUtil.returnError("You do not have permission to view information for party with ID [" + partyId + "], you must be logged in as a user associated with this party, or have the WORKEFFORTMGR_VIEW or WORKEFFORTMGR_ADMIN permissions.");
            }
        } else {
            // if a facilityId or a fixedAssetId are not specified, don't set a default partyId...
            if (UtilValidate.isEmpty(facilityId) && UtilValidate.isEmpty(fixedAssetId) && UtilValidate.isNotEmpty(userLogin.getString("partyId"))) {
                partyIdsToUse.add(userLogin.getString("partyId"));
            }
        }
        List<EntityCondition> entityExprList = UtilGenerics.checkList(context.get("entityExprList"));
        if (entityExprList == null) {
            entityExprList = getDefaultWorkEffortExprList(partyIdsToUse, facilityId, fixedAssetId, workEffortTypeId);
        }
        entityExprList.add(EntityCondition.makeCondition("estimatedStartDate", EntityOperator.LESS_THAN, endStamp));
        List<EntityCondition> completionExprList = UtilMisc.<EntityCondition>toList(EntityCondition.makeCondition("estimatedCompletionDate", EntityOperator.GREATER_THAN_EQUAL_TO, startStamp), EntityCondition.makeCondition("estimatedCompletionDate", EntityOperator.EQUALS, null));
        entityExprList.add(EntityCondition.makeCondition(completionExprList, EntityJoinOperator.OR));
        if (filterOutCanceledEvents.booleanValue()) {
            entityExprList.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "EVENT_CANCELLED"));
        }
        EntityConditionList<EntityCondition> ecl = EntityCondition.makeCondition(entityExprList);
        List<String> orderByList = UtilMisc.toList("estimatedStartDate");
        if (partyIdsToUse.size() > 0 || UtilValidate.isNotEmpty(facilityId) || UtilValidate.isNotEmpty(fixedAssetId)) {
            try {
                List<GenericValue> tempWorkEfforts = null;
                if (UtilValidate.isNotEmpty(partyIdsToUse)) {
                    tempWorkEfforts = EntityUtil.filterByDate(delegator.findList("WorkEffortAndPartyAssign", ecl, null, orderByList, null, false));
                } else if (UtilValidate.isNotEmpty(fixedAssetId)) {
                    // Get "old style" work efforts and "new style" work efforts
                    tempWorkEfforts = delegator.findList("WorkEffort", ecl, null, orderByList, null, false);
                    tempWorkEfforts.addAll(EntityUtil.filterByDate(delegator.findList("WorkEffortAndFixedAssetAssign", ecl, null, orderByList, null, false)));
                } else {
                    tempWorkEfforts = delegator.findList("WorkEffort", ecl, null, UtilMisc.toList("estimatedStartDate"), null, false);
                }
                validWorkEfforts = WorkEffortWorker.removeDuplicateWorkEfforts(tempWorkEfforts);
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }
        }

        // Split the WorkEffort list into a map with entries for each period, period start is the key
        List<Map<String, Object>> periods = FastList.newInstance();
        if (validWorkEfforts != null) {
            List<DateRange> periodRanges = FastList.newInstance();
            for (int i = 0; i < numPeriods; i++) {
                Timestamp curPeriodStart = UtilDateTime.adjustTimestamp(startStamp, periodType, i, timeZone, locale);
                Timestamp curPeriodEnd = UtilDateTime.adjustTimestamp(curPeriodStart, periodType, 1, timeZone, locale);
                curPeriodEnd = new Timestamp(curPeriodEnd.getTime() - 1);
                periodRanges.add(new DateRange(curPeriodStart, curPeriodEnd));
            }
            try {
                // Process recurring work efforts
                Set<GenericValue> exclusions = FastSet.newInstance();
                Set<GenericValue> inclusions = FastSet.newInstance();
                DateRange range = new DateRange(startStamp, endStamp);
                Calendar cal = UtilDateTime.toCalendar(startStamp, timeZone, locale);
                for (GenericValue workEffort : validWorkEfforts) {
                    if (UtilValidate.isNotEmpty(workEffort.getString("tempExprId"))) {
                        TemporalExpression tempExpr = TemporalExpressionWorker.getTemporalExpression(delegator, workEffort.getString("tempExprId"));
                        Set<Date> occurrences = tempExpr.getRange(range, cal);
                        for (Date occurrence : occurrences) {
                            for (DateRange periodRange : periodRanges) {
                                if (periodRange.includesDate(occurrence)) {
                                    GenericValue cloneWorkEffort = (GenericValue) workEffort.clone();
                                    Double durationMillis = workEffort.getDouble("estimatedMilliSeconds");
                                    if (durationMillis != null) {
                                        TimeDuration duration = TimeDuration.fromLong(durationMillis.longValue());
                                        Calendar endCal = UtilDateTime.toCalendar(occurrence, timeZone, locale);
                                        Date endDate = duration.addToCalendar(endCal).getTime();
                                        cloneWorkEffort.set("estimatedStartDate", new Timestamp(occurrence.getTime()));
                                        cloneWorkEffort.set("estimatedCompletionDate", new Timestamp(endDate.getTime()));
                                    } else {
                                        cloneWorkEffort.set("estimatedStartDate", periodRange.startStamp());
                                        cloneWorkEffort.set("estimatedCompletionDate", periodRange.endStamp());
                                    }
                                    inclusions.add(cloneWorkEffort);
                                }
                            }
                        }
                        exclusions.add(workEffort);
                    }
                }
                validWorkEfforts.removeAll(exclusions);
                validWorkEfforts.addAll(inclusions);
            } catch (GenericEntityException e) {
                Debug.logWarning(e, module);
            }

            // For each period in the set we check all work efforts to see if they fall within range
            boolean firstEntry = true;
            for (DateRange periodRange : periodRanges) {
                List<Map<String, Object>> curWorkEfforts = FastList.newInstance();
                Map<String, Object> entry = FastMap.newInstance();
                for (GenericValue workEffort : validWorkEfforts) {
                    DateRange weRange = new DateRange(workEffort.getTimestamp("estimatedStartDate"), workEffort.getTimestamp("estimatedCompletionDate"));
                    if (periodRange.intersectsRange(weRange)) {
                        Map<String, Object> calEntry = FastMap.newInstance();
                        calEntry.put("workEffort", workEffort);
                        long length = ((weRange.end().after(endStamp) ? endStamp.getTime() : weRange.end().getTime()) - (weRange.start().before(startStamp) ? startStamp.getTime() : weRange.start().getTime()));
                        int periodSpan = (int) Math.ceil((double) length / periodLen);
                        calEntry.put("periodSpan", Integer.valueOf(periodSpan));
                        if (firstEntry) {
                            // If this is the first period any valid entry is starting here
                            calEntry.put("startOfPeriod", Boolean.TRUE);
                            firstEntry = false;
                        } else {
                            boolean startOfPeriod = ((weRange.start().getTime() - periodRange.start().getTime()) >= 0);
                            calEntry.put("startOfPeriod", Boolean.valueOf(startOfPeriod));
                        }
                        curWorkEfforts.add(calEntry);
                    }
                }
                int numEntries = curWorkEfforts.size();
                if (numEntries > maxConcurrentEntries) {
                    maxConcurrentEntries = numEntries;
                }
                entry.put("start", periodRange.startStamp());
                entry.put("end", periodRange.endStamp());
                entry.put("calendarEntries", curWorkEfforts);
                periods.add(entry);
            }
        }
        Map<String, Object> result = FastMap.newInstance();
        result.put("periods", periods);
        result.put("maxConcurrentEntries", Integer.valueOf(maxConcurrentEntries));
        return result;
    }

    public static Map<String, Object> getProductManufacturingSummaryByFacility(DispatchContext ctx, Map<String, ? extends Object> context) {
        GenericDelegator delegator = ctx.getDelegator();
        String productId = (String) context.get("productId");
        String facilityId = (String) context.get("facilityId"); // optional

        Map<String, Map<String, Object>> summaryInByFacility = FastMap.newInstance();
        Map<String, Map<String, Object>> summaryOutByFacility = FastMap.newInstance();
        try {
            //
            // Information about the running production runs that are going
            // to produce units of productId by facility.
            //
            List<EntityCondition> findIncomingProductionRunsConds = FastList.newInstance();

            findIncomingProductionRunsConds.add(EntityCondition.makeCondition("productId", EntityOperator.EQUALS, productId));
            findIncomingProductionRunsConds.add(EntityCondition.makeCondition("statusId", EntityOperator.EQUALS, "WEGS_CREATED"));
            findIncomingProductionRunsConds.add(EntityCondition.makeCondition("workEffortGoodStdTypeId", EntityOperator.EQUALS, "PRUN_PROD_DELIV"));
            if (facilityId != null) {
                findIncomingProductionRunsConds.add(EntityCondition.makeCondition("facilityId", EntityOperator.EQUALS, facilityId));
            }

            List<EntityCondition> findIncomingProductionRunsStatusConds = FastList.newInstance();
            findIncomingProductionRunsStatusConds.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.EQUALS, "PRUN_CREATED"));
            findIncomingProductionRunsStatusConds.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.EQUALS, "PRUN_SCHEDULED"));
            findIncomingProductionRunsStatusConds.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.EQUALS, "PRUN_DOC_PRINTED"));
            findIncomingProductionRunsStatusConds.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.EQUALS, "PRUN_RUNNING"));
            findIncomingProductionRunsStatusConds.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.EQUALS, "PRUN_COMPLETED"));
            findIncomingProductionRunsConds.add(EntityCondition.makeCondition(findIncomingProductionRunsStatusConds, EntityOperator.OR));

            EntityConditionList findIncomingProductionRunsCondition = EntityCondition.makeCondition(findIncomingProductionRunsConds, EntityOperator.AND);

            List<GenericValue> incomingProductionRuns = delegator.findList("WorkEffortAndGoods", findIncomingProductionRunsCondition, null, UtilMisc.toList("-estimatedCompletionDate"), null, false);
            for (GenericValue incomingProductionRun: incomingProductionRuns) {
                double producedQtyTot = 0.0;
                if (incomingProductionRun.getString("currentStatusId").equals("PRUN_COMPLETED")) {
                    List<GenericValue> inventoryItems = delegator.findByAnd("WorkEffortAndInventoryProduced", UtilMisc.toMap("productId", productId, "workEffortId", incomingProductionRun.getString("workEffortId")));
                    for (GenericValue inventoryItem: inventoryItems) {
                        GenericValue inventoryItemDetail = EntityUtil.getFirst(delegator.findByAnd("InventoryItemDetail", UtilMisc.toMap("inventoryItemId", inventoryItem.getString("inventoryItemId")), UtilMisc.toList("inventoryItemDetailSeqId")));
                        if (inventoryItemDetail != null && inventoryItemDetail.get("quantityOnHandDiff") != null) {
                            Double inventoryItemQty = inventoryItemDetail.getDouble("quantityOnHandDiff");
                            producedQtyTot = producedQtyTot + inventoryItemQty.doubleValue();
                        }
                    }
                }
                double estimatedQuantity = 0.0;
                if (incomingProductionRun.get("estimatedQuantity") != null) {
                    estimatedQuantity = incomingProductionRun.getDouble("estimatedQuantity").doubleValue();
                }
                double remainingQuantity = estimatedQuantity - producedQtyTot; // the qty that still needs to be produced
                if (remainingQuantity > 0) {
                    incomingProductionRun.set("estimatedQuantity", Double.valueOf(remainingQuantity));
                } else {
                    continue;
                }
                String weFacilityId = incomingProductionRun.getString("facilityId");

                Map<String, Object> quantitySummary = UtilGenerics.checkMap(summaryInByFacility.get(weFacilityId));
                if (quantitySummary == null) {
                    quantitySummary = FastMap.newInstance();
                    quantitySummary.put("facilityId", weFacilityId);
                    summaryInByFacility.put(weFacilityId, quantitySummary);
                }
                Double remainingQuantityTot = (Double)quantitySummary.get("estimatedQuantityTotal");
                if (remainingQuantityTot == null) {
                    quantitySummary.put("estimatedQuantityTotal", Double.valueOf(remainingQuantity));
                } else {
                    quantitySummary.put("estimatedQuantityTotal", Double.valueOf(remainingQuantity + remainingQuantityTot.doubleValue()));
                }

                List<GenericValue> incomingProductionRunList = UtilGenerics.checkList(quantitySummary.get("incomingProductionRunList"));
                if (incomingProductionRunList == null) {
                    incomingProductionRunList = FastList.newInstance();
                    quantitySummary.put("incomingProductionRunList", incomingProductionRunList);
                }
                incomingProductionRunList.add(incomingProductionRun);
            }
            //
            // Information about the running production runs that are going
            // to consume units of productId by facility.
            //
            List<EntityCondition> findOutgoingProductionRunsConds = FastList.newInstance();

            findOutgoingProductionRunsConds.add(EntityCondition.makeCondition("productId", EntityOperator.EQUALS, productId));
            findOutgoingProductionRunsConds.add(EntityCondition.makeCondition("statusId", EntityOperator.EQUALS, "WEGS_CREATED"));
            findOutgoingProductionRunsConds.add(EntityCondition.makeCondition("workEffortGoodStdTypeId", EntityOperator.EQUALS, "PRUNT_PROD_NEEDED"));
            if (facilityId != null) {
                findOutgoingProductionRunsConds.add(EntityCondition.makeCondition("facilityId", EntityOperator.EQUALS, facilityId));
            }

            List<EntityCondition> findOutgoingProductionRunsStatusConds = FastList.newInstance();
            findOutgoingProductionRunsStatusConds.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.EQUALS, "PRUN_CREATED"));
            findOutgoingProductionRunsStatusConds.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.EQUALS, "PRUN_SCHEDULED"));
            findOutgoingProductionRunsStatusConds.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.EQUALS, "PRUN_DOC_PRINTED"));
            findOutgoingProductionRunsStatusConds.add(EntityCondition.makeCondition("currentStatusId", EntityOperator.EQUALS, "PRUN_RUNNING"));
            findOutgoingProductionRunsConds.add(EntityCondition.makeCondition(findOutgoingProductionRunsStatusConds, EntityOperator.OR));

            EntityConditionList findOutgoingProductionRunsCondition = EntityCondition.makeCondition(findOutgoingProductionRunsConds, EntityOperator.AND);
            List<GenericValue> outgoingProductionRuns = delegator.findList("WorkEffortAndGoods", findOutgoingProductionRunsCondition, null, UtilMisc.toList("-estimatedStartDate"), null, false);
            for (GenericValue outgoingProductionRun: outgoingProductionRuns) {
                String weFacilityId = outgoingProductionRun.getString("facilityId");
                Double neededQuantity = outgoingProductionRun.getDouble("estimatedQuantity");
                if (neededQuantity == null) {
                    neededQuantity = Double.valueOf(0);
                }

                Map<String, Object> quantitySummary = UtilGenerics.checkMap(summaryOutByFacility.get(weFacilityId));
                if (quantitySummary == null) {
                    quantitySummary = FastMap.newInstance();
                    quantitySummary.put("facilityId", weFacilityId);
                    summaryOutByFacility.put(weFacilityId, quantitySummary);
                }
                Double remainingQuantityTot = (Double)quantitySummary.get("estimatedQuantityTotal");
                if (remainingQuantityTot == null) {
                    quantitySummary.put("estimatedQuantityTotal", neededQuantity);
                } else {
                    quantitySummary.put("estimatedQuantityTotal", Double.valueOf(neededQuantity.doubleValue() + remainingQuantityTot.doubleValue()));
                }

                List<GenericValue> outgoingProductionRunList = UtilGenerics.checkList(quantitySummary.get("outgoingProductionRunList"));
                if (outgoingProductionRunList == null) {
                    outgoingProductionRunList = FastList.newInstance();
                    quantitySummary.put("outgoingProductionRunList", outgoingProductionRunList);
                }
                outgoingProductionRunList.add(outgoingProductionRun);
            }

        } catch (GenericEntityException gee) {
            return ServiceUtil.returnError("Error retrieving manufacturing data for productId [" + productId + "]: " + gee.getMessage());
        }
        Map<String, Object> resultMap = ServiceUtil.returnSuccess();
        resultMap.put("summaryInByFacility", summaryInByFacility);
        resultMap.put("summaryOutByFacility", summaryOutByFacility);
        return resultMap;
    }

    /** Process work effort event reminders. This service is used by the job scheduler.
     * @param ctx
     * @param context
     * @return
     */
    public static Map<String, Object> processWorkEffortEventReminders(DispatchContext ctx, Map<String, ? extends Object> context) {
        GenericDelegator delegator = ctx.getDelegator();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<GenericValue> eventReminders = null;
        try {
            eventReminders = delegator.findList("WorkEffortEventReminder", EntityCondition.makeCondition(UtilMisc.<EntityCondition>toList(EntityCondition.makeCondition("reminderDateTime", EntityOperator.EQUALS, null), EntityCondition.makeCondition("reminderDateTime", EntityOperator.LESS_THAN_EQUAL_TO, now)), EntityOperator.OR), null, null, null, false);
        } catch (GenericEntityException e) {
            return ServiceUtil.returnError("Error while retrieving work effort event reminders: " + e);
        }
        for (GenericValue reminder : eventReminders) {
            int repeatCount = reminder.get("repeatCount") == null ? 0 : reminder.getLong("repeatCount").intValue();
            int currentCount = reminder.get("currentCount") == null ? 0 : reminder.getLong("currentCount").intValue();
            String isPopup = reminder.getString("isPopup");
            if ("Y".equals(isPopup)) {
                if (repeatCount != 0 && repeatCount == currentCount) {
                    try {
                        reminder.remove();
                    } catch (GenericEntityException e) {
                        Debug.logWarning("Error while removing work effort event reminder: " + e, module);
                    }
                }
                continue;
            }
            GenericValue workEffort = null;
            try {
                workEffort = reminder.getRelatedOne("WorkEffort");
            } catch (GenericEntityException e) {
                Debug.logWarning("Error while getting work effort: " + e, module);
            }
            if (workEffort == null) {
                try {
                    reminder.remove();
                } catch (GenericEntityException e) {
                    Debug.logWarning("Error while removing work effort event reminder: " + e, module);
                }
                continue;
            }
            Locale locale = reminder.getString("localeId") == null ? Locale.getDefault() : new Locale(reminder.getString("localeId"));
            TimeZone timeZone = reminder.getString("timeZoneId") == null ? TimeZone.getDefault() : TimeZone.getTimeZone(reminder.getString("timeZoneId"));
            Map<String, Object> parameters = UtilMisc.toMap("locale", locale, "timeZone", timeZone, "workEffortId", reminder.get("workEffortId"));
            Calendar cal = UtilDateTime.toCalendar(now, timeZone, locale);
            Timestamp reminderStamp = reminder.getTimestamp("reminderDateTime");
            Date eventDateTime = workEffort.getTimestamp("estimatedStartDate");
            String tempExprId = workEffort.getString("tempExprId");
            if (UtilValidate.isNotEmpty(tempExprId)) {
                TemporalExpression temporalExpression = null;
                try {
                    temporalExpression = TemporalExpressionWorker.getTemporalExpression(delegator, tempExprId);
                } catch (GenericEntityException e) {
                    Debug.logWarning("Error while getting temporal expression, id = " + tempExprId + ": " + e, module);
                }
                if (temporalExpression != null) {
                    eventDateTime = temporalExpression.first(cal).getTime();
                    Date reminderDateTime = null;
                    long recurrenceOffset = reminder.get("recurrenceOffset") == null ? 0 : reminder.getLong("recurrenceOffset").longValue();
                    if (reminderStamp == null) {
                        if (recurrenceOffset != 0) {
                            cal.setTime(eventDateTime);
                            TimeDuration duration = TimeDuration.fromLong(recurrenceOffset);
                            duration.addToCalendar(cal);
                            reminderDateTime = cal.getTime();
                        } else {
                            reminderDateTime = eventDateTime;
                        }
                    } else {
                        reminderDateTime = new Date(reminderStamp.getTime());
                    }
                    if (reminderDateTime.before(now) && reminderStamp != null) {
                        try {
                            parameters.put("eventDateTime", new Timestamp(eventDateTime.getTime()));
                            processEventReminder(ctx, reminder, parameters);
                            if (repeatCount != 0 && currentCount + 1 >= repeatCount) {
                                reminder.remove();
                            } else {
                                cal.setTime(reminderDateTime);
                                Date newReminderDateTime = null;
                                if (recurrenceOffset != 0) {
                                    TimeDuration duration = TimeDuration.fromLong(-recurrenceOffset);
                                    duration.addToCalendar(cal);
                                    cal.setTime(temporalExpression.next(cal).getTime());
                                    duration = TimeDuration.fromLong(recurrenceOffset);
                                    duration.addToCalendar(cal);
                                    newReminderDateTime = cal.getTime();
                                } else {
                                    newReminderDateTime = temporalExpression.next(cal).getTime();
                                }
                                reminder.set("currentCount", new Long(currentCount + 1));
                                reminder.set("reminderDateTime", new Timestamp(newReminderDateTime.getTime()));
                                reminder.store();
                            }
                        } catch (GenericEntityException e) {
                            Debug.logWarning("Error while processing temporal expression reminder, id = " + tempExprId + ": " + e, module);
                        }
                    } else if (reminderStamp == null) {
                        try {
                            reminder.set("reminderDateTime", new Timestamp(reminderDateTime.getTime()));
                            reminder.store();
                        } catch (GenericEntityException e) {
                            Debug.logWarning("Error while processing temporal expression reminder, id = " + tempExprId + ": " + e, module);
                        }
                    }
                }
                continue;
            }
            if (reminderStamp != null) {
                Date reminderDateTime = new Date(reminderStamp.getTime());
                if (reminderDateTime.before(now)) {
                    try {
                        parameters.put("eventDateTime", eventDateTime);
                        processEventReminder(ctx, reminder, parameters);
                        long repeatInterval = reminder.get("repeatInterval") == null ? 0 : reminder.getLong("repeatInterval").longValue();
                        if ((repeatCount != 0 && currentCount + 1 >= repeatCount) || repeatInterval == 0) {
                            reminder.remove();
                        } else {
                            cal.setTime(now);
                            TimeDuration duration = TimeDuration.fromLong(repeatInterval);
                            duration.addToCalendar(cal);
                            reminderDateTime = cal.getTime();
                            reminder.set("currentCount", new Long(currentCount + 1));
                            reminder.set("reminderDateTime", new Timestamp(reminderDateTime.getTime()));
                            reminder.store();
                        }
                    } catch (GenericEntityException e) {
                        Debug.logWarning("Error while processing event reminder: " + e, module);
                    }
                }
            }
        }
        return ServiceUtil.returnSuccess();
    }

    protected static void processEventReminder(DispatchContext ctx, GenericValue reminder, Map<String, Object> parameters) throws GenericEntityException {
        LocalDispatcher dispatcher = ctx.getDispatcher();
        GenericValue contactMech = reminder.getRelatedOne("ContactMech");
        if (contactMech != null && "EMAIL_ADDRESS".equals(contactMech.get("contactMechTypeId"))) {
            String screenLocation = UtilProperties.getPropertyValue("EventReminders", "eventReminders.emailScreenWidgetLocation");
            String fromAddress = UtilProperties.getPropertyValue("EventReminders", "eventReminders.emailFromAddress");
            String toAddress = contactMech.getString("infoString");
            String subject = UtilProperties.getMessage("WorkEffortUiLabels", "WorkEffortEventReminder", (Locale) parameters.get("locale"));
            Map<String, Object> emailCtx = UtilMisc.toMap("sendFrom", fromAddress, "sendTo", toAddress, "subject", subject, "bodyParameters", parameters, "bodyScreenUri", screenLocation);
            try {
                dispatcher.runAsync("sendMailFromScreen", emailCtx);
            } catch (Exception e) {
                Debug.logWarning("Error while emailing event reminder - workEffortId = " + reminder.get("workEffortId") + ", contactMechId = " + reminder.get("contactMechId") + ": " + e, module);
            }
            return;
        }
        // TODO: Other contact mechanism types
        Debug.logWarning("Invalid event reminder contact mech, workEffortId = " + reminder.get("workEffortId") + ", contactMechId = " + reminder.get("contactMechId"), module);
    }
}
