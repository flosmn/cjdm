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

<#if canNotView>
  <p><h3>${uiLabelMap.AccountingCardInfoNotBelongToYou}.</h3></p>
&nbsp;<a href="<@ofbizUrl>authview/${donePage}</@ofbizUrl>" class="buttontext">${uiLabelMap.CommonBack}</a>
<#else>
    <#if !creditCard?exists>
      <h1>${uiLabelMap.AccountingAddNewCreditCard}</h1>
      <form method="post" action="<@ofbizUrl>createCreditCard?DONE_PAGE=${donePage}</@ofbizUrl>" name="editcreditcardform" style="margin: 0;">
    <#else>
      <h1>${uiLabelMap.AccountingEditCreditCard}</h1>
      <form method="post" action="<@ofbizUrl>updateCreditCard?DONE_PAGE=${donePage}</@ofbizUrl>" name="editcreditcardform" style="margin: 0;">
        <input type="hidden" name="paymentMethodId" value="${paymentMethodId}">
    </#if>
      &nbsp;<a href="<@ofbizUrl>${donePage}</@ofbizUrl>" class="buttontext">${uiLabelMap.CommonCancelDone}</a>
      &nbsp;<a href="javascript:document.editcreditcardform.submit()" class="buttontext">${uiLabelMap.CommonSave}</a>

    <table width="90%" border="0" cellpadding="2" cellspacing="0">

    ${screens.render("component://accounting/widget/CommonScreens.xml#creditCardFields")}
    <tr>
      <td width="26%" align="right" valign="top"><div class="tabletext">${uiLabelMap.PartyBillingAddress}</div></td>
      <td width="5">&nbsp;</td>
      <td width="74%">
        <#-- Removed because is confusing, can add but would have to come back here with all data populated as before...
        <a href="<@ofbizUrl>editcontactmech</@ofbizUrl>" class="buttontext">
          [Create New Address]</a>&nbsp;&nbsp;
        -->
        <table width="100%" border="0" cellpadding="1">
        <#assign hasCurrent = false>
        <#if curPostalAddress?has_content>
          <#assign hasCurrent = true>
          <tr>
            <td align="right" valign="top" width="1%">
              <input type="radio" name="contactMechId" value="${curContactMechId}" checked>
            </td>
            <td valign="top" width="80%">
              <div class="tabletext"><b>${uiLabelMap.PartyUseCurrentAddress}:</b></div>
              <#list curPartyContactMechPurposes as curPartyContactMechPurpose>
                <#assign curContactMechPurposeType = curPartyContactMechPurpose.getRelatedOneCache("ContactMechPurposeType")>
                <div class="tabletext">
                  <b>${curContactMechPurposeType.get("description",locale)?if_exists}</b>
                  <#if curPartyContactMechPurpose.thruDate?exists>
                    ((${uiLabelMap.CommonExpire}:${curPartyContactMechPurpose.thruDate.toString()})
                  </#if>
                </div>
              </#list>
              <div class="tabletext">
                <#if curPostalAddress.toName?exists><b>${uiLabelMap.CommonTo}:</b> ${curPostalAddress.toName}<br/></#if>
                <#if curPostalAddress.attnName?exists><b>${uiLabelMap.PartyAddrAttnName}:</b> ${curPostalAddress.attnName}<br/></#if>
                ${curPostalAddress.address1?if_exists}<br/>
                <#if curPostalAddress.address2?exists>${curPostalAddress.address2}<br/></#if>
                ${curPostalAddress.city}<#if curPostalAddress.stateProvinceGeoId?has_content>,&nbsp;${curPostalAddress.stateProvinceGeoId}</#if>&nbsp;${curPostalAddress.postalCode}
                <#if curPostalAddress.countryGeoId?exists><br/>${curPostalAddress.countryGeoId}</#if>
              </div>
              <div class="tabletext">(${uiLabelMap.CommonUpdated}:&nbsp;${(curPartyContactMech.fromDate.toString())?if_exists})</div>
              <#if curPartyContactMech.thruDate?exists><div class="tabletext"><b>${uiLabelMap.CommonDelete}:&nbsp;${curPartyContactMech.thruDate.toString()}</b></div></#if>
            </td>
          </tr>
        <#else>
           <#-- <tr>
            <td valign="top" colspan="2">
              <div class="tabletext">${uiLabelMap.PartyBillingAddressNotSelected}</div>
            </td>
          </tr> -->
        </#if>
          <#-- is confusing
          <tr>
            <td valign="top" colspan="2">
              <div class="tabletext"><b>${uiLabelMap.EcommerceMessage3}</b></div>
            </td>
          </tr>
          -->
          <#list postalAddressInfos as postalAddressInfo>
            <#assign contactMech = postalAddressInfo.contactMech>
            <#assign partyContactMechPurposes = postalAddressInfo.partyContactMechPurposes>
            <#assign postalAddress = postalAddressInfo.postalAddress>
            <#assign partyContactMech = postalAddressInfo.partyContactMech>
            <tr>
              <td align="right" valign="top" width="1%">
                <input type="radio" name="contactMechId" value="${contactMech.contactMechId}">
              </td>
              <td valign="middle" width="80%">
                <#list partyContactMechPurposes as partyContactMechPurpose>
                    <#assign contactMechPurposeType = partyContactMechPurpose.getRelatedOneCache("ContactMechPurposeType")>
                    <div class="tabletext">
                      <b>${contactMechPurposeType.get("description",locale)?if_exists}</b>
                      <#if partyContactMechPurpose.thruDate?exists>(${uiLabelMap.CommonExpire}:${partyContactMechPurpose.thruDate})</#if>
                    </div>
                </#list>
                <div class="tabletext">
                  <#if postalAddress.toName?exists><b>${uiLabelMap.CommonTo}:</b> ${postalAddress.toName}<br/></#if>
                  <#if postalAddress.attnName?exists><b>${uiLabelMap.PartyAddrAttnName}:</b> ${postalAddress.attnName}<br/></#if>
                  ${postalAddress.address1?if_exists}<br/>
                  <#if postalAddress.address2?exists>${postalAddress.address2}<br/></#if>
                  ${postalAddress.city}<#if postalAddress.stateProvinceGeoId?has_content>,&nbsp;${postalAddress.stateProvinceGeoId}</#if>&nbsp;${postalAddress.postalCode}
                  <#if postalAddress.countryGeoId?exists><br/>${postalAddress.countryGeoId}</#if>
                </div>
                <div class="tabletext">(${uiLabelMap.CommonUpdated}:&nbsp;${(partyContactMech.fromDate.toString())?if_exists})</div>
                <#if partyContactMech.thruDate?exists><div class="tabletext"><b>${uiLabelMap.CommonDelete}:&nbsp;${partyContactMech.thruDate.toString()}</b></div></#if>
              </td>
            </tr>
          </#list>
          <#if !postalAddressInfos?has_content && !curContactMech?exists>
              <tr><td colspan="2"><div class="tabletext">${uiLabelMap.PartyNoContactInformation}.</div></td></tr>
          </#if>
          <tr>
            <td align="right" valigh="top" width="1%">
              <input type="radio" name="contactMechId" value="_NEW_" <#if !hasCurrent>checked</#if>>
            </td>
            <td valign="middle" width="80%">
              <span class="tabletext">${uiLabelMap.PartyCreateNewBillingAddress}.</span>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>

  &nbsp;<a href="<@ofbizUrl>${donePage}</@ofbizUrl>" class="buttontext">${uiLabelMap.CommonCancelDone}</a>
  &nbsp;<a href="javascript:document.editcreditcardform.submit()" class="buttontext">${uiLabelMap.CommonSave}</a>
</#if>

