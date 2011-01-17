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


<form method="post" name="agreementForm" action="<@ofbizUrl>setOrderCurrencyAgreementShipDates</@ofbizUrl>">
<div class="screenlet">
  <div class="screenlet-title-bar">
      <ul>
        <li class="h3">${uiLabelMap.OrderOrderEntryCurrencyAgreementShipDates}</li>
        <li><a href="javascript:document.agreementForm.submit()">${uiLabelMap.CommonContinue}</a></li>
      </ul>
      <br class="clear" />
  </div>
  <div class="screenlet-body">
    <table>
      <tr><td colspan="4">&nbsp;</td></tr>

      <#if agreements?exists>
      <input type='hidden' name='hasAgreements' value='Y'/>
      <tr>
        <td>&nbsp;</td>
        <td align='right' valign='top' nowrap>
          <div class='tableheadtext'>
            ${uiLabelMap.OrderSelectAgreement}
          </div>
        </td>
        <td>&nbsp;</td>
        <td valign='middle'>
          <div class='tabletext' valign='top'>
            <select name="agreementId">
            <option value="">${uiLabelMap.CommonNone}</option>
            <#list agreements as agreement>
            <option value='${agreement.agreementId}' >${agreement.agreementId} - ${agreement.description?if_exists}</option>
            </#list>
            </select>
          </div>
        </td>
      </tr>
      <#else><input type='hidden' name='hasAgreements' value='N'/>
      </#if>
      <#if agreementRoles?exists>
        <tr>
          <td>&nbsp;</td>
          <td align='right' valign='top' nowrap>
            <div class='tableheadtext'>
              ${uiLabelMap.OrderSelectAgreementRoles}
            </div>
          </td>
          <td>&nbsp;</td>
          <td valign='middle'>
            <div class='tabletext' valign='top'>
              <select name="agreementId">
              <option value="">${uiLabelMap.CommonNone}</option>
              <#list agreementRoles as agreementRole>
                  <option value='${agreementRole.agreementId?if_exists}' >${agreementRole.agreementId?if_exists} - ${agreementRole.roleTypeId?if_exists}</option>
              </#list>
              </select>
            </div>
          </td>
        </tr>
      </#if>

      <#if "PURCHASE_ORDER" == cart.getOrderType()>
        <tr>
          <td>&nbsp;</td>
          <td align='right' valign='middle' class='tableheadtext'>
            ${uiLabelMap.OrderOrderId}
          </td>
          <td>&nbsp;</td>
          <td align='left'>
            <input type='text' size='15' maxlength='100' name='orderId' value=""/>
          </td>
        </tr>
      </#if>

      <tr>
        <td>&nbsp;</td>
        <td align='right' valign='middle' class='tableheadtext' nowrap>
           ${uiLabelMap.OrderOrderName}
        </td>
        <td>&nbsp;</td>
        <td align='left'>
          <input type='text' size='60' maxlength='100' name='orderName'/>
        </td>
      </tr>

      <#if cart.getOrderType() != "PURCHASE_ORDER">
      <tr>
        <td>&nbsp;</td>
        <td align='right' valign='middle' class='tableheadtext' nowrap>
          ${uiLabelMap.OrderPONumber}
        </td>
        <td>&nbsp;</td>
        <td align='left'>
          <input type="text" class='inputBox' name="correspondingPoId" size="15">
        </td>
      </tr>
      </#if>

      <tr>
        <td>&nbsp;</td>
        <td align='right' valign='middle' nowrap>
          <div class='tableheadtext'>
            <#if agreements?exists>${uiLabelMap.OrderSelectCurrencyOr}
            <#else>${uiLabelMap.OrderSelectCurrency}
            </#if>
          </div>
        </td>
        <td>&nbsp;</td>
        <td valign='middle'>
          <div class='tabletext' valign='top'>
            <select name="currencyUomId">
              <option value=""></option>
              <#list currencies as currency>
              <option value="${currency.uomId}" <#if (defaultCurrencyUomId?has_content) && (currency.uomId == defaultCurrencyUomId)>selected</#if>>${currency.uomId}</option>
              </#list>
            </select>
          </div>
        </td>
      </tr>

      <tr>
        <td>&nbsp;</td>
        <td align="right">
          ${uiLabelMap.ProductChooseCatalog}
        </td>
        <td>&nbsp;</td>
        <td>
           <select name='CURRENT_CATALOG_ID'>
            <option value='${currentCatalogId}'>${currentCatalogName}</option>
            <option value='${currentCatalogId}'></option>
            <#list catalogCol as catalogId>
              <#assign thisCatalogName = Static["org.ofbiz.product.catalog.CatalogWorker"].getCatalogName(request, catalogId)>
              <option value='${catalogId}'>${thisCatalogName}</option>
            </#list>
          </select>
        </td>
      </tr>

      <tr>
        <td>&nbsp;</td>
        <td align="right">
          ${uiLabelMap.WorkEffortWorkEffortId}
        </td>
        <td>&nbsp;</td>
        <td>
          <input type="text" name="workEffortId" size="15"/>
          <a href="javascript:call_fieldlookup2(document.agreementForm.workEffortId,'LookupWorkEffort');"><img src='/images/fieldlookup.gif' width='15' height='14' border='0' alt="${uiLabelMap.CommonClickHereForFieldLookup}"/></a>
        </td>
      </tr>

      <tr>
        <td>&nbsp;</td>
        <td align='right' valign='top' nowrap>
          <div class='tableheadtext'>
            ${uiLabelMap.OrderShipAfterDateDefault}
          </div>
        </td>
        <td>&nbsp;</td>
        <td><input type="text" name="shipAfterDate" size="20" maxlength="30"/>
          <a href="javascript:call_cal(document.agreementForm.shipAfterDate,'');">
            <img src='/images/cal.gif' width='16' height='16' border='0' alt='Calendar'/>
          </a>
        </td>
      </tr>

      <tr>
        <td>&nbsp;</td>
        <td align='right' valign='top' nowrap>
          <div class='tableheadtext'>
            ${uiLabelMap.OrderShipBeforeDateDefault}
          </div>
        </td>
        <td>&nbsp;</td>
        <td><input type="text" name="shipBeforeDate" size="20" maxlength="30"/>
          <a href="javascript:call_cal(document.agreementForm.shipBeforeDate,'');">
            <img src='/images/cal.gif' width='16' height='16' border='0' alt='Calendar'/>
          </a>
        </td>
      </tr>

      <#if cart.getOrderType() == "PURCHASE_ORDER">
        <tr>
          <td>&nbsp;</td>
          <td align='right' valign='top'>
            <div class='tableheadtext'>
              ${uiLabelMap.FormFieldTitle_cancelBackOrderDate}
            </div>
          </td>
          <td>&nbsp;</td>
          <td><input type="text" name="cancelBackOrderDate" size="20" maxlength="30"/>
            <a href="javascript:call_cal(document.agreementForm.cancelBackOrderDate,'');">
              <img src='/images/cal.gif' width='16' height='16' border='0' alt='Calendar'/>
            </a>
          </td>
        </tr>
      </#if>

    </table>
  </div>
</div>
</form>
