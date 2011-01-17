<#--
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
-->

<#--
To use these macros in your template, insert the following line in
your template file:
<#include "component://common/webcommon/includes/commonMacros.ftl"/>
-->

<#assign
  dayValueList = Static["org.ofbiz.service.calendar.ExpressionUiHelper"].getDayValueList(locale)
  monthValueList = Static["org.ofbiz.service.calendar.ExpressionUiHelper"].getMonthValueList(locale)
/>

<#macro NullMacro></#macro>

<#macro DateField formName="" fieldName="" fieldValue="" fieldClass="">
  <input type="text" name="${fieldName}"<#if fieldValue?has_content> value="${fieldValue}"</#if><#if fieldClass?has_content> class="${fieldClass}"</#if> maxlength="25" size="25"/>
  <#if javaScriptEnabled>
    <a href="javascript:call_cal(document.${formName}.${fieldName},'${fieldValue}');">
    <img src="/images/cal.gif" width="16" height="16" border="0" alt="${uiLabelMap.CommonViewCalendar}" title="${uiLabelMap.CommonViewCalendar}"/></a>
  </#if>
  <span class="tooltip">${uiLabelMap.CommonFormatDateTime}</span>
  <#if fieldClass == "required">
    <span class="tooltip">${uiLabelMap.CommonRequired}</span>
  </#if>
</#macro>

<#macro MonthField fieldName="" fieldValue=-1 fieldClass="">
  <select name="${fieldName}"<#if fieldClass?has_content> class="${fieldClass}"</#if>>
    <#list monthValueList as monthValue>
      <option value="${monthValue.value}"<#if monthValue.value == fieldValue> selected="selected"</#if>>${monthValue.description}</option>
    </#list>
  </select>
  <#if fieldClass == "required">
    <span class="tooltip">${uiLabelMap.CommonRequired}</span>
  </#if>
</#macro>

<#macro HourOfDayField fieldName="" fieldValue=-1 fieldClass="">
  <select name="${fieldName}"<#if fieldClass?has_content> class="${fieldClass}"</#if>>
    <#list 0..23 as i>
      <option value="${i}"<#if i == fieldValue> selected="selected"</#if>>${i}</option>
    </#list>
  </select>
  <#if fieldClass == "required">
    <span class="tooltip">${uiLabelMap.CommonRequired}</span>
  </#if>
</#macro>

<#macro MinuteField fieldName="" fieldValue=-1 fieldClass="">
  <select name="${fieldName}"<#if fieldClass?has_content> class="${fieldClass}"</#if>>
    <#list 0..59 as i>
      <option value="${i}"<#if i == fieldValue> selected="selected"</#if>>${i}</option>
    </#list>
  </select>
  <#if fieldClass == "required">
    <span class="tooltip">${uiLabelMap.CommonRequired}</span>
  </#if>
</#macro>

<#macro DayOfWeekField fieldName="" fieldValue=-1 fieldClass="">
  <select name="${fieldName}"<#if fieldClass?has_content> class="${fieldClass}"</#if>>
    <#list dayValueList as dayValue>
      <option value="${dayValue.value}"<#if dayValue.value == fieldValue> selected="selected"</#if>>${dayValue.description}</option>
    </#list>
  </select>
  <#if fieldClass == "required">
    <span class="tooltip">${uiLabelMap.CommonRequired}</span>
  </#if>
</#macro>

<#macro DayOfMonthField fieldName="" fieldValue=-1 fieldClass="">
  <select name="${fieldName}"<#if fieldClass?has_content> class="${fieldClass}"</#if>>
    <#list 1..31 as i>
      <option value="${i}"<#if i == fieldValue> selected="selected"</#if>>${i}</option>
    </#list>
  </select>
  <#if fieldClass == "required">
    <span class="tooltip">${uiLabelMap.CommonRequired}</span>
  </#if>
</#macro>
