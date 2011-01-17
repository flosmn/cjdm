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

<#if security.hasEntityPermission("FACILITY", "_VIEW", session)>
<#if facilityId?has_content>
  <h1>${uiLabelMap.ProductEditFacility} ${facility.facilityName?if_exists} [${facilityId?if_exists}]</h1>
  <div class="button-bar">
    <a href="<@ofbizUrl>EditFacility</@ofbizUrl>" name="EditFacilityForm" class="buttontext">${uiLabelMap.ProductNewFacility}</a>
      <a href="/workeffort/control/month?facilityId=${facilityId}&externalLoginKey=${requestAttributes.externalLoginKey?if_exists}" class="buttontext">${uiLabelMap.CommonViewCalendar}</a>
  </div>
<#else>
  <h1>${uiLabelMap.ProductNewFacility}</h1>
</#if>

<#if facility?exists && facilityId?has_content>
  <form action="<@ofbizUrl>UpdateFacility</@ofbizUrl>" name="EditFacilityForm" method="post">
  <input type="hidden" name="facilityId" value="${facilityId?if_exists}">
  <table class="basic-table" cellspacing='0'>
  <tr>
    <td class="label">${uiLabelMap.ProductFacilityId}</td>
    <td>
      ${facilityId?if_exists} <span class="tooltip">${uiLabelMap.ProductNotModificationRecrationFacility}</span>
    </td>
  </tr>
<#else>
  <form action="<@ofbizUrl>CreateFacility</@ofbizUrl>" name="EditFacilityForm" method="post" style='margin: 0;'>
  <#if facilityId?exists>
    <h3>${uiLabelMap.ProductCouldNotFindFacilityWithId} "${facilityId?if_exists}".</h3>
  </#if>
  <table class="basic-table" cellspacing='0'>
</#if>
  <tr>
    <td class="label">${uiLabelMap.ProductFacilityTypeId}</td>
    <td>
      <select name="facilityTypeId">
        <option selected value='${facilityType.facilityTypeId?if_exists}'>${facilityType.get("description",locale)?if_exists}</option>
        <option value='${facilityType.facilityTypeId?if_exists}'>----</option>
        <#list facilityTypes as nextFacilityType>
          <option value='${nextFacilityType.facilityTypeId?if_exists}'>${nextFacilityType.get("description",locale)?if_exists}</option>
        </#list>
      </select>
    </td>
  </tr>
  <tr>
    <td class="label">${uiLabelMap.FormFieldTitle_parentFacilityId}</td>
    <td>
      <input type="text" name="parentFacilityId" value="${facility.parentFacilityId?if_exists}"/>
      <a href="javascript:call_fieldlookup2(document.EditFacilityForm.parentFacilityId,'LookupFacility');" title="${uiLabelMap.CommonFieldLookup}">
        <img src="<@ofbizContentUrl>/images/fieldlookup.gif</@ofbizContentUrl>" width="15" height="14" border="0" alt="${uiLabelMap.CommonFieldLookup}"/>
      </a>
    </td>
  </tr>
  <tr>
    <td class="label">${uiLabelMap.ProductFacilityOwner}</td>
    <td>
      <input type="text" class="required" name="ownerPartyId" value="${facility.ownerPartyId?if_exists}"/>
      <a href="javascript:call_fieldlookup2(document.EditFacilityForm.ownerPartyId,'LookupPartyName');" title="${uiLabelMap.CommonFieldLookup}">
        <img src="<@ofbizContentUrl>/images/fieldlookup.gif</@ofbizContentUrl>" width="15" height="14" border="0" alt="${uiLabelMap.CommonFieldLookup}"/>
      </a>
      <span class="tooltip">${uiLabelMap.CommonRequired}</span>
    </td>
  </tr>
  <tr>
    <td class="label">${uiLabelMap.ProductFacilityDefaultWeightUnit}</td>
    <td>
      <select name="defaultWeightUomId">
          <option value=''>${uiLabelMap.CommonNone}</option>
          <#list weightUomList as uom>
            <option value='${uom.uomId}'
               <#if (facility.defaultWeightUomId?has_content) && (uom.uomId == facility.defaultWeightUomId)>
               SELECTED
               </#if>
             >${uom.get("description",locale)?default(uom.uomId)}</option>
          </#list>
      </select>
    </td>
  </tr>
  <tr>
    <td class="label">${uiLabelMap.ProductFacilityDefaultInventoryItemType}</td>
    <td>
      <select name="defaultInventoryItemTypeId">
          <#list inventoryItemTypes as nextInventoryItemType>
            <option value='${nextInventoryItemType.inventoryItemTypeId}'
               <#if (facility.defaultInventoryItemTypeId?has_content) && (nextInventoryItemType.inventoryItemTypeId == facility.defaultInventoryItemTypeId)>
               SELECTED
               </#if>
             >${nextInventoryItemType.get("description",locale)?default(nextInventoryItemType.inventoryItemTypeId)}</option>
          </#list>
      </select>
    </td>
  </tr>

  <tr>
    <td class="label">${uiLabelMap.ProductName}</td>
    <td><input type="text" name="facilityName" value="${facility.facilityName?if_exists}" size="30" maxlength="60"></td>
  </tr>
  <tr>
    <td class="label">${uiLabelMap.ProductSquareFootage}</td>
    <td><input type="text" name="squareFootage" value="${facility.squareFootage?if_exists}" size="10" maxlength="20"></td>
  </tr>
  <tr>
    <td class="label">${uiLabelMap.ProductProductDescription}</td>
    <td ><input type="text" name="description" value="${facility.description?if_exists}" size="60" maxlength="250"></td>
  </tr>
  <tr>
    <td class="label">${uiLabelMap.ProductDefaultDaysToShip}</td>
    <td><input type="text" name="defaultDaysToShip" value="${facility.defaultDaysToShip?if_exists}" size="10" maxlength="20"></td>
  </tr>

  <tr>
    <td>&nbsp;</td>
    <#if facilityId?has_content>
      <td><input type="submit" name="Update" value="${uiLabelMap.CommonUpdate}"></td>
    <#else>
      <td><input type="submit" name="Update" value="${uiLabelMap.CommonSave}"></td>
    </#if>
  </tr>
</table>
</form>

<#else>
  <h3>${uiLabelMap.ProductFacilityViewPermissionError}</h3>
</#if>


