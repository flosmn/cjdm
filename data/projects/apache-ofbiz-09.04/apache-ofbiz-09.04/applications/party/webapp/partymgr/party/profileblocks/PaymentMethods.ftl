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

  <div id="partyPaymentMethod" class="screenlet">
    <div class="screenlet-title-bar">
      <ul>
        <li class="h3">${uiLabelMap.PartyPaymentMethodInformation}</li>
        <#if security.hasEntityPermission("PAY_INFO", "_CREATE", session)>
          <li><a href="<@ofbizUrl>editeftaccount?partyId=${partyId}</@ofbizUrl>">${uiLabelMap.AccountingCreateNewEftAccount}</a></li>
          <li><a href="<@ofbizUrl>editgiftcard?partyId=${partyId}</@ofbizUrl>">${uiLabelMap.AccountingCreateNewGiftCard}</a></li>
          <li><a href="<@ofbizUrl>editcreditcard?partyId=${partyId}</@ofbizUrl>">${uiLabelMap.AccountingCreateNewCreditCard}</a></li>
        </#if>
      </ul>
      <br class="clear" />
    </div>
    <div class="screenlet-body">
      <#if paymentMethodValueMaps?has_content>
        <table class="basic-table" cellspacing="0">
          <#list paymentMethodValueMaps as paymentMethodValueMap>
            <#assign paymentMethod = paymentMethodValueMap.paymentMethod/>
            <tr>
              <#if "CREDIT_CARD" == paymentMethod.paymentMethodTypeId>
                <#assign creditCard = paymentMethodValueMap.creditCard/>
                <td class="label">
                  ${uiLabelMap.AccountingCreditCard}
                </td>
                <td>
                  <#if creditCard.companyNameOnCard?has_content>${creditCard.companyNameOnCard}&nbsp;</#if>
                  <#if creditCard.titleOnCard?has_content>${creditCard.titleOnCard}&nbsp</#if>
                  ${creditCard.firstNameOnCard}&nbsp;
                  <#if creditCard.middleNameOnCard?has_content>${creditCard.middleNameOnCard}&nbsp</#if>
                  ${creditCard.lastNameOnCard}
                  <#if creditCard.suffixOnCard?has_content>&nbsp;${creditCard.suffixOnCard}</#if>
                  &nbsp;-&nbsp;
                  <#if security.hasEntityPermission("PAY_INFO", "_VIEW", session)>
                    ${creditCard.cardType}
                    ${creditCard.cardNumber}
                    ${creditCard.expireDate}
                  <#else>
                    ${Static["org.ofbiz.party.contact.ContactHelper"].formatCreditCard(creditCard)}
                  </#if>
                  <#if paymentMethod.description?has_content>(${paymentMethod.description})</#if>
                  <#if paymentMethod.glAccountId?has_content>(for GL Account ${paymentMethod.glAccountId})</#if>
                  <#if paymentMethod.fromDate?has_content>(${uiLabelMap.CommonUpdated}:&nbsp;${paymentMethod.fromDate?if_exists})</#if>
                  <#if paymentMethod.thruDate?has_content><b>(${uiLabelMap.PartyContactEffectiveThru}:&nbsp;${paymentMethod.thruDate})</#if>
                </td>
                <td class="button-col">
                  <#if security.hasEntityPermission("MANUAL", "_PAYMENT", session)>
                    <a href="/accounting/control/manualETx?paymentMethodId=${paymentMethod.paymentMethodId}${externalKeyParam}">${uiLabelMap.PartyManualTx}</a>
                  </#if>
                  <#if security.hasEntityPermission("PAY_INFO", "_UPDATE", session)>
                    <a href="<@ofbizUrl>editcreditcard?partyId=${partyId}&paymentMethodId=${paymentMethod.paymentMethodId}</@ofbizUrl>">${uiLabelMap.CommonUpdate}</a>
                  </#if>
                <#-- </td> -->
              <#elseif "GIFT_CARD" == paymentMethod.paymentMethodTypeId>
                <#assign giftCard = paymentMethodValueMap.giftCard>
                <td class="label" valign="top">
                  ${uiLabelMap.AccountingGiftCard}
                </td>
                <td>
                  <#if security.hasEntityPermission("PAY_INFO", "_VIEW", session)>
                    ${giftCard.cardNumber?default("N/A")} [${giftCard.pinNumber?default("N/A")}]
                  <#else>
                    <#if giftCard?has_content && giftCard.cardNumber?has_content>
                      <#assign giftCardNumber = "">
                      <#assign pcardNumber = giftCard.cardNumber>
                      <#if pcardNumber?has_content>
                        <#assign psize = pcardNumber?length - 4>
                        <#if 0 < psize>
                          <#list 0 .. psize-1 as foo>
                            <#assign giftCardNumber = giftCardNumber + "*">
                          </#list>
                          <#assign giftCardNumber = giftCardNumber + pcardNumber[psize .. psize + 3]>
                        <#else>
                          <#assign giftCardNumber = pcardNumber>
                        </#if>
                      </#if>
                    </#if>
                    ${giftCardNumber?default("N/A")}
                  </#if>
                  <#if paymentMethod.description?has_content>(${paymentMethod.description})</#if>
                  <#if paymentMethod.glAccountId?has_content>(for GL Account ${paymentMethod.glAccountId})</#if>
                  <#if paymentMethod.fromDate?has_content>(${uiLabelMap.CommonUpdated}:&nbsp;${paymentMethod.fromDate?if_exists})</#if>
                  <#if paymentMethod.thruDate?has_content><b>(${uiLabelMap.PartyContactEffectiveThru}:&nbsp;${paymentMethod.thruDate.toString()}</b></#if>
                </td>
                <td class="button-col">
                  <#if security.hasEntityPermission("PAY_INFO", "_UPDATE", session)>
                    <a href="<@ofbizUrl>editgiftcard?partyId=${partyId}&paymentMethodId=${paymentMethod.paymentMethodId}</@ofbizUrl>">${uiLabelMap.CommonUpdate}</a>
                  </#if>
                <#-- </td> -->
              <#elseif "EFT_ACCOUNT" == paymentMethod.paymentMethodTypeId>
                <#assign eftAccount = paymentMethodValueMap.eftAccount>
                <td class="label" valign="top">
                    ${uiLabelMap.PartyEftAccount}
                </td>
                <td>
                  ${eftAccount.nameOnAccount} - <#if eftAccount.bankName?has_content>${uiLabelMap.PartyBank}: ${eftAccount.bankName}</#if> <#if eftAccount.accountNumber?has_content>${uiLabelMap.PartyAccount} #: ${eftAccount.accountNumber}</#if>                  <#if paymentMethod.description?has_content>(${paymentMethod.description})</#if>
                  <#if paymentMethod.glAccountId?has_content>(for GL Account ${paymentMethod.glAccountId})</#if>
                  <#if paymentMethod.fromDate?has_content>(${uiLabelMap.CommonUpdated}:&nbsp;${paymentMethod.fromDate?if_exists})</#if>
                  <#if paymentMethod.thruDate?has_content><b>(${uiLabelMap.PartyContactEffectiveThru}:&nbsp;${paymentMethod.thruDate.toString()}</#if>
                </td>
                <td class="button-col">
                  <#if security.hasEntityPermission("PAY_INFO", "_UPDATE", session)>
                    <a href="<@ofbizUrl>editeftaccount?partyId=${partyId}&paymentMethodId=${paymentMethod.paymentMethodId}</@ofbizUrl>">${uiLabelMap.CommonUpdate}</a>
                  </#if>
                <#-- </td> -->
              <#elseif "COMPANY_CHECK" == paymentMethod.paymentMethodTypeId>
                <td class="label" valign="top">
                  <#-- TODO: Convert hard-coded text to UI label properties -->
                  Company Check
                </td>
                <td>
                  <#if paymentMethod.description?has_content>(${paymentMethod.description})</#if>
                  <#if paymentMethod.glAccountId?has_content>(for GL Account ${paymentMethod.glAccountId})</#if>
                  <#if paymentMethod.fromDate?has_content>(${uiLabelMap.CommonUpdated}:&nbsp;${paymentMethod.fromDate?if_exists})</#if>
                  <#if paymentMethod.thruDate?has_content>(${uiLabelMap.PartyContactEffectiveThru}:&nbsp;${paymentMethod.thruDate.toString()}</#if>
                </td>
                <td class="button-col">
                  &nbsp;
                <#-- </td> -->
              </#if>
              <#if security.hasEntityPermission("PAY_INFO", "_DELETE", session)>
                <a href="<@ofbizUrl>deletePaymentMethod/viewprofile?partyId=${partyId}&paymentMethodId=${paymentMethod.paymentMethodId}</@ofbizUrl>">${uiLabelMap.CommonExpire}</a>
              <#else>
                &nbsp;
              </#if>
              </td> <#-- closes out orphaned <td> elements inside conditionals -->
            </tr>
          </#list>
        </table>
      <#else>
        ${uiLabelMap.PartyNoPaymentMethodInformation}
      </#if>
    </div>
  </div>
