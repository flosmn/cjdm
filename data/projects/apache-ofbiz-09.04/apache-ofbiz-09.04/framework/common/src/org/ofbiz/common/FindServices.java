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
package org.ofbiz.common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javolution.util.FastMap;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.ObjectType;
import org.ofbiz.base.util.StringUtil;
import org.ofbiz.base.util.UtilDateTime;
import org.ofbiz.base.util.UtilHttp;
import static org.ofbiz.base.util.UtilGenerics.checkList;
import static org.ofbiz.base.util.UtilGenerics.checkMap;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilProperties;
import org.ofbiz.entity.GenericDelegator;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.entity.condition.EntityComparisonOperator;
import org.ofbiz.entity.condition.EntityConditionList;
import org.ofbiz.entity.condition.EntityCondition;
import org.ofbiz.entity.condition.EntityExpr;
import org.ofbiz.entity.condition.EntityOperator;
import org.ofbiz.entity.condition.EntityFunction;
import org.ofbiz.entity.condition.EntityFieldValue;
import org.ofbiz.entity.model.ModelEntity;
import org.ofbiz.entity.model.ModelField;
import org.ofbiz.entity.util.EntityFindOptions;
import org.ofbiz.entity.util.EntityListIterator;
import org.ofbiz.entity.util.EntityUtil;
import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.ServiceUtil;
import org.ofbiz.service.GenericServiceException;
import org.ofbiz.service.LocalDispatcher;

/**
 * FindServices Class
 */
public class FindServices {

    public static final String module = FindServices.class.getName();

    public static HashMap<String, EntityOperator> entityOperators;

    static {
        entityOperators = new HashMap<String, EntityOperator>();
        entityOperators.put("and", EntityOperator.AND);
        entityOperators.put("between", EntityOperator.BETWEEN);
        entityOperators.put("equals", EntityOperator.EQUALS);
        entityOperators.put("greaterThan", EntityOperator.GREATER_THAN);
        entityOperators.put("greaterThanEqualTo", EntityOperator.GREATER_THAN_EQUAL_TO);
        entityOperators.put("in", EntityOperator.IN);
        entityOperators.put("lessThan", EntityOperator.LESS_THAN);
        entityOperators.put("lessThanEqualTo", EntityOperator.LESS_THAN_EQUAL_TO);
        entityOperators.put("like", EntityOperator.LIKE);
        entityOperators.put("not", EntityOperator.NOT);
        entityOperators.put("notEqual", EntityOperator.NOT_EQUAL);
        entityOperators.put("or", EntityOperator.OR);
    }

    public FindServices() {}

    /**
     * prepareField, analyse inputFields to created normalizedFields a map with field name and operator.
     *
     * This is use to the generic method that expects entity data affixed with special suffixes
     * to indicate their purpose in formulating an SQL query statement.
     * @param inputFields     Input parameters run thru UtilHttp.getParameterMap
     * @return a map with field name and operator
     */
    public static HashMap<String, HashMap<String, HashMap<String, Object>>> prepareField(Map<String, ?> inputFields, Map<String, Object> queryStringMap, Map<String, List<Object[]>> origValueMap) {
        // Strip the "_suffix" off of the parameter name and
        // build a three-level map of values keyed by fieldRoot name,
        //    fld0 or fld1,  and, then, "op" or "value"
        // ie. id
        //  - fld0
        //      - op:like
        //      - value:abc
        //  - fld1 (if there is a range)
        //      - op:lessThan
        //      - value:55 (note: these two "flds" wouldn't really go together)
        // Also note that op/fld can be in any order. (eg. id_fld1_equals or id_equals_fld1)
        // Note that "normalizedFields" will contain values other than those
        // Contained in the associated entity.
        // Those extra fields will be ignored in the second half of this method.
        HashMap<String, HashMap<String, HashMap<String, Object>>> normalizedFields = new HashMap<String, HashMap<String, HashMap<String, Object>>>();
        //StringBuffer queryStringBuf = new StringBuffer();
        for (String fieldNameRaw: inputFields.keySet()) { // The name as it appeas in the HTML form
            String fieldNameRoot = null; // The entity field name. Everything to the left of the first "_" if
                                                                 //  it exists, or the whole word, if not.
            String fieldPair = null; // "fld0" or "fld1" - begin/end of range or just fld0 if no range.
            Object fieldValue = null; // If it is a "value" field, it will be the value to be used in the query.
                                                        // If it is an "op" field, it will be "equals", "greaterThan", etc.
            int iPos = -1;
            int iPos2 = -1;
            HashMap<String, HashMap<String, Object>> subMap = null;
            HashMap<String, Object> subMap2 = null;
            String fieldMode = null;

            fieldValue = inputFields.get(fieldNameRaw);
            if (ObjectType.isEmpty(fieldValue)) {
                continue;
            }

            //queryStringBuffer.append(fieldNameRaw + "=" + fieldValue);
            queryStringMap.put(fieldNameRaw, fieldValue);
            iPos = fieldNameRaw.indexOf("_"); // Look for suffix

            // This is a hack to skip fields from "multi" forms
            // These would have the form "fieldName_o_1"
            if (iPos >= 0) {
                String suffix = fieldNameRaw.substring(iPos + 1);
                iPos2 = suffix.indexOf("_");
                if (iPos2 == 1) {
                    continue;
                }
            }

            // If no suffix, assume no range (default to fld0) and operations of equals
            // If no field op is present, it will assume "equals".
            if (iPos < 0) {
                fieldNameRoot = fieldNameRaw;
                fieldPair = "fld0";
                fieldMode = "value";
            } else { // Must have at least "fld0/1" or "equals, greaterThan, etc."
                // Some bogus fields will slip in, like "ENTITY_NAME", but they will be ignored

                fieldNameRoot = fieldNameRaw.substring(0, iPos);
                String suffix = fieldNameRaw.substring(iPos + 1);
                iPos2 = suffix.indexOf("_");
                if (iPos2 < 0) {
                    if (suffix.startsWith("fld")) {
                        // If only one token and it starts with "fld"
                        //  assume it is a value field, not an op
                        fieldPair = suffix;
                        fieldMode = "value";
                    } else {
                        // if it does not start with fld, assume it is an op or the 'ignore case' (ic) field
                        fieldPair = "fld0";
                        fieldMode = suffix;
                    }
                } else {
                    String tkn0 = suffix.substring(0, iPos2);
                    String tkn1 = suffix.substring(iPos2 + 1);
                    // If suffix has two parts, let them be in any order
                    // One will be "fld0/1" and the other will be the op (eg. equals, greaterThan_
                    if (tkn0.startsWith("fld")) {
                        fieldPair = tkn0;
                        fieldMode = tkn1;
                    } else {
                        fieldPair = tkn1;
                        fieldMode = tkn0;
                    }
                }
            }
            subMap = normalizedFields.get(fieldNameRoot);
            if (subMap == null) {
                subMap = new HashMap<String, HashMap<String, Object>>();
                normalizedFields.put(fieldNameRoot, subMap);
            }
            subMap2 = subMap.get(fieldPair);
            if (subMap2 == null) {
                subMap2 = new HashMap<String, Object>();
                subMap.put(fieldPair, subMap2);
            }
            subMap2.put(fieldMode, fieldValue);

            List<Object[]> origList = origValueMap.get(fieldNameRoot);
            if (origList == null) {
                origList = new ArrayList<Object[]>();
                origValueMap.put(fieldNameRoot, origList);
            }
            Object [] origValues = {fieldNameRaw, fieldValue};
            origList.add(origValues);
        }
        return normalizedFields;
    }

    /**
     * createCondition, comparing the normalizedFields with the list of keys, .
     *
     * This is use to the generic method that expects entity data affixed with special suffixes
     * to indicate their purpose in formulating an SQL query statement.
     * @param keys     list of field for which it's possible to make the query
     * @param normalizedFields     list of field the user have populated
     * @return a arrayList usable to create an entityCondition
     */
    public static ArrayList<EntityCondition> createCondition(ModelEntity modelEntity, HashMap<String, HashMap<String, HashMap<String, Object>>> normalizedFields, Map<String, Object> queryStringMap, Map<String, List<Object[]>> origValueMap, GenericDelegator delegator, Map<String, ?> context) {
        HashMap<String, HashMap<String, Object>> subMap = null;
        HashMap<String, Object> subMap2 = null;
        EntityOperator fieldOp = null;
        Object fieldValue = null; // If it is a "value" field, it will be the value to be used in the query.
                                  // If it is an "op" field, it will be "equals", "greaterThan", etc.
        EntityExpr cond = null;
        ArrayList<EntityCondition> tmpList = new ArrayList<EntityCondition>();
        String opString = null;
        String ignoreCase = null;
        int count = 0;
        List<ModelField> fields = modelEntity.getFieldsUnmodifiable();
        for (ModelField modelField: fields) {
            String fieldName = modelField.getName();
            subMap = normalizedFields.get(fieldName);
            if (subMap == null) {
                continue;
            }

            subMap2 = subMap.get("fld0");
            opString = (String) subMap2.get("op");
            ignoreCase = (String) subMap2.get("ic");

            if (opString != null) {
                if (opString.equals("contains")) {
                    fieldOp = EntityOperator.LIKE;
                } else if (opString.equals("empty")) {
                    fieldOp = EntityOperator.EQUALS;
                } else {
                    fieldOp = entityOperators.get(opString);
                }
            } else {
                fieldOp = EntityOperator.EQUALS;
            }

            fieldValue = subMap2.get("value");
            if (fieldValue == null) {
                continue;
            }

            if (opString != null) {
                if (opString.equals("contains")) {
                    fieldOp = EntityOperator.LIKE;
                    fieldValue = "%" + fieldValue + "%";
                } else if (opString.equals("empty")) {
                    fieldOp = EntityOperator.EQUALS;
                    fieldValue = null;
                    ignoreCase = null;
                } else if (opString.equals("like")) {
                    fieldOp = EntityOperator.LIKE;
                    fieldValue = fieldValue + "%";
                } else if (opString.equals("greaterThanFromDayStart")) {
                    fieldValue = dayStart((String) fieldValue, 0);
                    fieldOp = EntityOperator.GREATER_THAN;
                    ignoreCase = null;
                } else if (opString.equals("sameDay")) {
                    String timeStampString = (String) fieldValue;
                    fieldValue = dayStart(timeStampString, 0);
                    fieldOp = EntityOperator.GREATER_THAN_EQUAL_TO;
                    ignoreCase = null;
                    // Set up so next part finds ending conditions for same day
                    subMap2 = subMap.get("fld1");
                    if (subMap2 == null) {
                        subMap2 = new HashMap<String, Object>();
                        subMap.put("fld1", subMap2);
                    }
                    String endOfDay = dayStart(timeStampString, 1);
                    subMap2.put("value", endOfDay);
                    subMap2.put("op", "lessThan");
                } else {
                    fieldOp = entityOperators.get(opString);
                }
            } else {
                fieldOp = EntityOperator.EQUALS;
            }

            Object fieldObject = null;
            if (fieldOp != EntityOperator.IN || ! (fieldValue instanceof Collection)) {
                fieldObject = modelEntity.convertFieldValue(modelField, fieldValue, delegator, context);
            } else {
                fieldObject = fieldValue;
            }

            if (ignoreCase != null && ignoreCase.equals("Y") && "java.lang.String".equals(fieldObject.getClass().getName())) {
                cond = EntityCondition.makeCondition(EntityFunction.UPPER_FIELD(fieldName), (EntityComparisonOperator) fieldOp, EntityFunction.UPPER(((String)fieldValue).toUpperCase()));
            } else {
                cond = EntityCondition.makeCondition(fieldName, (EntityComparisonOperator) fieldOp, fieldObject);
            }
            tmpList.add(cond);
            count++;

            // Repeat above operations if there is a "range" - second value
            subMap2 = subMap.get("fld1");
            if (subMap2 == null) {
                continue;
            }
            opString = (String) subMap2.get("op");

            if (opString != null) {
                if (opString.equals("contains")) {
                    fieldOp = EntityOperator.LIKE;
                } else if (opString.equals("empty")) {
                    fieldOp = EntityOperator.EQUALS;
                } else {
                    fieldOp = entityOperators.get(opString);
                }
            } else {
                fieldOp = EntityOperator.EQUALS;
            }

            fieldValue = (String) subMap2.get("value");
            if (fieldValue == null) {
                continue;
            }
            if (opString.equals("like")) {
                fieldValue = fieldValue + "%";
            } else if (opString.equals("contains")) {
                fieldValue = fieldValue + "%" + fieldValue + "%";
            } else if (opString.equals("empty")) {
                fieldOp = EntityOperator.EQUALS;
                fieldValue = null;
            } else if (opString.equals("upToDay")) {
                fieldValue = dayStart((String) fieldValue, 0);
                fieldOp = EntityOperator.LESS_THAN;
            } else if (opString.equals("upThruDay")) {
                fieldValue = dayStart((String) fieldValue, 1);
                fieldOp = EntityOperator.LESS_THAN;
            }
            // String rhs = fieldValue.toString();
            fieldObject = modelEntity.convertFieldValue(modelField, fieldValue, delegator, context);
            cond = EntityCondition.makeCondition(fieldName, (EntityComparisonOperator) fieldOp, fieldObject);
            tmpList.add(cond);

            // add to queryStringMap
            List<Object[]> origList = origValueMap.get(fieldName);
            if (UtilValidate.isNotEmpty(origList)) {
                for (Object[] arr: origList) {
                    queryStringMap.put((String) arr[0], arr[1]);
                }
            }
        }
        return tmpList;
    }

    /**
     *
     *  same as performFind but now returning a list instead of an iterator
     *  Extra parameters viewIndex: startPage of the partial list (0 = first page)
     *                              viewSize: the length of the page (number of records)
     *  Extra output parameter: listSize: size of the totallist
     *                                         list : the list itself.
     *
     * @param dctx
     * @param context
     * @return Map
     */
    public static Map<String, Object> performFindList(DispatchContext dctx, Map<String, ?> context) {
        Map<String, Object> result = performFind(dctx,context);

        Integer viewSize = (Integer) context.get("viewSize");
        if (viewSize == null) viewSize = Integer.valueOf(20);       // default
        Integer viewIndex = (Integer) context.get("viewIndex");
        if (viewIndex == null)  viewIndex = Integer.valueOf(0);  // default

        int start = viewIndex.intValue() * viewSize.intValue();
        List<GenericValue> list = null;
        Integer listSize = null;
        try {
            EntityListIterator it = (EntityListIterator) result.get("listIt");
            list = it.getPartialList(start+1, viewSize.intValue()); // list starts at '1'
            it.last();
            listSize = Integer.valueOf(it.currentIndex());
            it.close();
        } catch (Exception e) {
            Debug.logInfo("Problem getting partial list" + e,module);
        }

        result.put("listSize", listSize);
        result.put("list",list);
        result.remove("listIt");
        return result;
    }

    /**
     * performFind
     *
     * This is a generic method that expects entity data affixed with special suffixes
     * to indicate their purpose in formulating an SQL query statement.
     */
    public static Map<String, Object> performFind(DispatchContext dctx, Map<String, ?> context) {
        String entityName = (String) context.get("entityName");
        String orderBy = (String) context.get("orderBy");
        Map<String, ?> inputFields = checkMap(context.get("inputFields"), String.class, Object.class); // Input
        String noConditionFind = (String) context.get("noConditionFind");
        String distinct = (String) context.get("distinct");
        List fieldList =  (List) context.get("fieldList");
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        if (UtilValidate.isEmpty(noConditionFind)) {
            // try finding in inputFields Map
            noConditionFind = (String) inputFields.get("noConditionFind");
        }
        if (UtilValidate.isEmpty(noConditionFind)) {
            // Use configured default
            noConditionFind = UtilProperties.getPropertyValue("widget", "widget.defaultNoConditionFind");
        }
        String filterByDate = (String) context.get("filterByDate");
        if (UtilValidate.isEmpty(filterByDate)) {
            // try finding in inputFields Map
            filterByDate = (String) inputFields.get("filterByDate");
        }
        Timestamp filterByDateValue = (Timestamp) context.get("filterByDateValue");

        LocalDispatcher dispatcher = dctx.getDispatcher();

        Map<String, Object> prepareResult = null;
        try {
            prepareResult = dispatcher.runSync("prepareFind", UtilMisc.toMap("entityName", entityName, "orderBy", orderBy, "inputFields", inputFields, "filterByDate", filterByDate,"filterByDateValue", filterByDateValue, "userLogin", userLogin, "locale", context.get("locale"), "timeZone", context.get("timeZone")));
        } catch (GenericServiceException gse) {
            return ServiceUtil.returnError("Error preparing conditions: " + gse.getMessage());
        }
        EntityConditionList exprList = (EntityConditionList)prepareResult.get("entityConditionList");
        List<String> orderByList = checkList(prepareResult.get("orderByList"), String.class);

        Map<String, Object> executeResult = null;
        try {
            executeResult = dispatcher.runSync("executeFind", UtilMisc.toMap("entityName", entityName, "orderByList", orderByList, "fieldList", fieldList, "entityConditionList", exprList, "noConditionFind", noConditionFind, "distinct", distinct, "locale", context.get("locale"), "timeZone", context.get("timeZone")));
        } catch (GenericServiceException gse) {
            return ServiceUtil.returnError("Error finding iterator: " + gse.getMessage());
        }

        if (executeResult.get("listIt") == null) {
            if (Debug.verboseOn()) Debug.logVerbose("No list iterator found for query string + [" + prepareResult.get("queryString") + "]", module);
        }

        Map<String, Object> results = ServiceUtil.returnSuccess();
        results.put("listIt", executeResult.get("listIt"));
        results.put("queryString", prepareResult.get("queryString"));
        results.put("queryStringMap", prepareResult.get("queryStringMap"));
        return results;
    }

    /**
     * prepareFind
     *
     * This is a generic method that expects entity data affixed with special suffixes
     * to indicate their purpose in formulating an SQL query statement.
     */
    public static Map prepareFind(DispatchContext dctx, Map<String, ?> context) {
        String entityName = (String) context.get("entityName");
        String orderBy = (String) context.get("orderBy");
        Map<String, ?> inputFields = checkMap(context.get("inputFields"), String.class, Object.class); // Input
        String noConditionFind = (String) context.get("noConditionFind");
        if (UtilValidate.isEmpty(noConditionFind)) {
            // try finding in inputFields Map
            noConditionFind = (String) inputFields.get("noConditionFind");
        }
        if (UtilValidate.isEmpty(noConditionFind)) {
            // Use configured default
            noConditionFind = UtilProperties.getPropertyValue("widget", "widget.defaultNoConditionFind");
        }
        String filterByDate = (String) context.get("filterByDate");
        if (UtilValidate.isEmpty(filterByDate)) {
            // try finding in inputFields Map
            filterByDate = (String) inputFields.get("filterByDate");
        }
        Timestamp filterByDateValue = (Timestamp) context.get("filterByDateValue");

        // parameters run thru UtilHttp.getParameterMap
        Map<String, Object> queryStringMap = FastMap.newInstance();
        Map<String, List<Object[]>> origValueMap = FastMap.newInstance();
        HashMap<String, HashMap<String, HashMap<String, Object>>> normalizedFields = prepareField(inputFields, queryStringMap, origValueMap);

        // Now use only the values that correspond to entity fields to build
        //   an EntityConditionList
        GenericDelegator delegator = dctx.getDelegator();

        GenericValue entityValue = delegator.makeValue(entityName, FastMap.newInstance());

        ModelEntity modelEntity = entityValue.getModelEntity();
        ArrayList<EntityCondition> tmpList = createCondition(modelEntity, normalizedFields, queryStringMap, origValueMap, delegator, context);

        /* the filter by date condition should only be added when there are other conditions or when
         * the user has specified a noConditionFind.  Otherwise, specifying filterByDate will become
         * its own condition.
         */
        if ((tmpList.size() > 0) || ((noConditionFind != null) && (noConditionFind.equals("Y")))) {
            if (!UtilValidate.isEmpty(filterByDate) && "Y".equals(filterByDate)) {
                if (UtilValidate.isEmpty(filterByDateValue)) {
                    EntityCondition filterByDateCondition = EntityUtil.getFilterByDateExpr();
                    tmpList.add(filterByDateCondition);
                } else {
                    EntityCondition filterByDateCondition = EntityUtil.getFilterByDateExpr(filterByDateValue);
                    tmpList.add(filterByDateCondition);
                }
            }
        }

        EntityConditionList exprList = null;
        if (tmpList.size() > 0) {
            exprList = EntityCondition.makeCondition(tmpList);
        }

        List<String> orderByList = null;
        if (UtilValidate.isNotEmpty(orderBy)) {
            orderByList = StringUtil.split(orderBy,"|");
        }

        Map<String, Object> results = ServiceUtil.returnSuccess();
        Map<String, Object> reducedQueryStringMap = buildReducedQueryString(inputFields, entityName, delegator);
        reducedQueryStringMap.put("noConditionFind", noConditionFind);
        reducedQueryStringMap.put("filterByDate", filterByDate);
        reducedQueryStringMap.put("filterByDateValue", filterByDateValue);
        String queryString = UtilHttp.urlEncodeArgs(reducedQueryStringMap);
        results.put("queryString", queryString);
        results.put("queryStringMap", reducedQueryStringMap);

        results.put("orderByList", orderByList);
        results.put("entityConditionList", exprList);
        return results;
    }

    /**
     * executeFind
     *
     * This is a generic method that returns an EntityListIterator.
     */
    public static Map<String, Object> executeFind(DispatchContext dctx, Map<String, ?> context) {
        String entityName = (String) context.get("entityName");
        EntityConditionList entityConditionList = (EntityConditionList) context.get("entityConditionList");
        List<String> orderByList = checkList(context.get("orderByList"), String.class);
        boolean noConditionFind = "Y".equals((String) context.get("noConditionFind"));
        boolean distinct = "Y".equals((String) context.get("distinct"));
        List fieldList =  (List) context.get("fieldList");
        Set fieldSet = null;
        if (fieldList != null) {
            fieldSet = new HashSet(fieldList);
        }
        GenericDelegator delegator = dctx.getDelegator();
        // Retrieve entities  - an iterator over all the values
        EntityListIterator listIt = null;
        try {
            if (noConditionFind || (entityConditionList != null && entityConditionList.getConditionListSize() > 0)) {
                listIt = delegator.find(entityName, entityConditionList, null, fieldSet, orderByList,
                        new EntityFindOptions(true, EntityFindOptions.TYPE_SCROLL_INSENSITIVE, EntityFindOptions.CONCUR_READ_ONLY, distinct));
            }
        } catch (GenericEntityException e) {
            return ServiceUtil.returnError("Error running Find on the [" + entityName + "] entity: " + e.getMessage());
        }

        Map<String, Object> results = ServiceUtil.returnSuccess();
        results.put("listIt", listIt);
        return results;
    }

    private static String dayStart(String timeStampString, int daysLater) {
        String retValue = null;
        Timestamp ts = null;
        Timestamp startTs = null;
        try {
            ts = Timestamp.valueOf(timeStampString);
        } catch (IllegalArgumentException e) {
            timeStampString += " 00:00:00.000";
            try {
                ts = Timestamp.valueOf(timeStampString);
            } catch (IllegalArgumentException e2) {
                return retValue;
            }
        }
        startTs = UtilDateTime.getDayStart(ts, daysLater);
        retValue = startTs.toString();
        return retValue;
    }

    public static HashMap<String, Object> buildReducedQueryString(Map<String, ?> inputFields, String entityName, GenericDelegator delegator) {
        // Strip the "_suffix" off of the parameter name and
        // build a three-level map of values keyed by fieldRoot name,
        //    fld0 or fld1,  and, then, "op" or "value"
        // ie. id
        //  - fld0
        //      - op:like
        //      - value:abc
        //  - fld1 (if there is a range)
        //      - op:lessThan
        //      - value:55 (note: these two "flds" wouldn't really go together)
        // Also note that op/fld can be in any order. (eg. id_fld1_equals or id_equals_fld1)
        // Note that "normalizedFields" will contain values other than those
        // Contained in the associated entity.
        // Those extra fields will be ignored in the second half of this method.
        ModelEntity modelEntity = delegator.getModelEntity(entityName);
        HashMap<String, Object> normalizedFields = new HashMap<String, Object>();
        //StringBuffer queryStringBuf = new StringBuffer();
        for (String fieldNameRaw: inputFields.keySet()) { // The name as it appeas in the HTML form
            String fieldNameRoot = null; // The entity field name. Everything to the left of the first "_" if
                                                                 //  it exists, or the whole word, if not.
            String fieldPair = null; // "fld0" or "fld1" - begin/end of range or just fld0 if no range.
            Object fieldValue = null; // If it is a "value" field, it will be the value to be used in the query.
                                                        // If it is an "op" field, it will be "equals", "greaterThan", etc.
            int iPos = -1;
            int iPos2 = -1;
            String fieldMode = null;

            fieldValue = inputFields.get(fieldNameRaw);
            if (ObjectType.isEmpty(fieldValue)) {
                continue;
            }

            //queryStringBuffer.append(fieldNameRaw + "=" + fieldValue);
            iPos = fieldNameRaw.indexOf("_"); // Look for suffix

            // This is a hack to skip fields from "multi" forms
            // These would have the form "fieldName_o_1"
            if (iPos >= 0) {
                String suffix = fieldNameRaw.substring(iPos + 1);
                iPos2 = suffix.indexOf("_");
                if (iPos2 == 1) {
                    continue;
                }
            }

            // If no suffix, assume no range (default to fld0) and operations of equals
            // If no field op is present, it will assume "equals".
            if (iPos < 0) {
                fieldNameRoot = fieldNameRaw;
                fieldPair = "fld0";
                fieldMode = "value";
            } else { // Must have at least "fld0/1" or "equals, greaterThan, etc."
                // Some bogus fields will slip in, like "ENTITY_NAME", but they will be ignored

                fieldNameRoot = fieldNameRaw.substring(0, iPos);
            }
            if (modelEntity.isField(fieldNameRoot)) {
                normalizedFields.put(fieldNameRaw, fieldValue);
            }
        }
        return normalizedFields;
    }
    /**
     * Returns the first generic item of the service 'performFind'
     * Same parameters as performFind service but returns a single GenericValue
     *
     * @param dctx
     * @param context
     * @return
     */
    public static Map<String, Object> performFindItem(DispatchContext dctx, Map<String, ?> context) {
        Map<String, Object> result = org.ofbiz.common.FindServices.performFind(dctx,context);

        List<GenericValue> list = null;
        GenericValue item= null;
        try {
            EntityListIterator it = (EntityListIterator) result.get("listIt");
            list = it.getPartialList(1, 1); // list starts at '1'
            if (list != null && list.size() > 0 ) {
                item = list.get(0);
            }
            it.close();
        } catch (Exception e) {
            Debug.logInfo("Problem getting list Item" + e,module);
        }

        if (!UtilValidate.isEmpty(item)) {
            result.put("item",item);
        }
        result.remove("listIt");
        return result;
    }
}
