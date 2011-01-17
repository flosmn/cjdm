/*
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
 */

import java.util.*;
import org.ofbiz.entity.*;
import org.ofbiz.entity.condition.*;
import org.ofbiz.base.util.*;
import java.sql.Timestamp;
import java.text.*;

// The view mode - Day, Week, or Month
viewMode = parameters.viewMode ?: "W";
parameters.viewMode = viewMode;

// Prepare vars for mode-specific date calculations
startParam = parameters.start;
if (!startParam) {
    start = nowTimestamp.clone();
} else {
    start = new Timestamp(Long.parseLong(startParam));
}
numPeriods = 24;
periodType = Calendar.HOUR;
getFrom = null;
prev = null;
next = null;
end = null;

if ("D".equals(viewMode)) {
    // Day view
    start = UtilDateTime.getDayStart(start, timeZone, locale);
    getFrom = new Timestamp(start.getTime());
    prev = UtilDateTime.getDayStart(start, -1, timeZone, locale);
    next = UtilDateTime.getDayStart(start, 1, timeZone, locale);
} else if ("W".equals(viewMode)) {
    // Week view
    start = UtilDateTime.getWeekStart(start, timeZone, locale);
    getFrom = new Timestamp(start.getTime());
    prev = UtilDateTime.getDayStart(start, -7, timeZone, locale);
    next = UtilDateTime.getDayStart(start, 7, timeZone, locale);
    end = UtilDateTime.getDayStart(start,6, timeZone, locale);
    numPeriods = 7;
    periodType = Calendar.DATE;
} else {
    // Month view
    start = UtilDateTime.getMonthStart(start, timeZone, locale);
    tempCal = UtilDateTime.toCalendar(start, timeZone, locale);
    firstWeekNum = tempCal.get(Calendar.WEEK_OF_YEAR);
    globalContext.firstWeekNum = firstWeekNum;
    numPeriods = tempCal.getActualMaximum(Calendar.DAY_OF_MONTH);
    prev = UtilDateTime.getDayStart(start, -1, timeZone, locale);
    next = UtilDateTime.getDayStart(start, numPeriods+1, timeZone, locale);
    end = UtilDateTime.getDayStart(start, numPeriods, timeZone, locale);
    prevMonthDays = tempCal.get(Calendar.DAY_OF_WEEK) - tempCal.getFirstDayOfWeek();
    if (prevMonthDays < 0) {
        prevMonthDays = 7 + prevMonthDays;
    }
    tempCal.add(Calendar.DATE, -(prevMonthDays));
    numPeriods += prevMonthDays;
    getFrom = new Timestamp(tempCal.getTimeInMillis());
    periodType = Calendar.DATE;
    globalContext.end = end;
}

entityExprList = [EntityCondition.makeCondition("currentStatusId", EntityOperator.NOT_EQUAL, "CAL_CANCELLED")];
fixedAssetId = parameters.fixedAssetId;
if (fixedAssetId) {
    entityExprList.add(EntityCondition.makeCondition("fixedAssetId", EntityOperator.EQUALS, fixedAssetId));
    globalContext.fixedAssetId = fixedAssetId;
    globalContext.addlParam = "&fixedAssetId=" + fixedAssetId;
}
serviceCtx = [userLogin : userLogin, start : getFrom, numPeriods : numPeriods, periodType : periodType, fixedAssetId : fixedAssetId];
serviceCtx.putAll([entityExprList : entityExprList, locale : locale, timeZone : timeZone]);
result = dispatcher.runSync("getWorkEffortEventsByPeriod", serviceCtx);
globalContext.periods = result.periods;
globalContext.maxConcurrentEntries = result.maxConcurrentEntries;

globalContext.start = start;
globalContext.prev = prev;
globalContext.next = next;
