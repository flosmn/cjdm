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

<div class="button-bar tab-bar">
  <ul>
    <li><a href="<@ofbizUrl>emptycart</@ofbizUrl>">${uiLabelMap.OrderClearOrder}</a></li>
    <#if (shoppingCart.size() > 0)>
      <li><a href="javascript:document.cartform.submit()">${uiLabelMap.OrderRecalculateOrder}</a></li>
      <li><a href="javascript:removeSelected();">${uiLabelMap.OrderRemoveSelected}</a></li>
    <#else>
      <li class="disabled">${uiLabelMap.OrderRecalculateOrder}</li>
      <li class="disabled">${uiLabelMap.OrderRemoveSelected}</li>
    </#if>
    <#if shoppingCart.getOrderType() == "PURCHASE_ORDER">
      <#if shoppingCart.getOrderPartyId() == "_NA_" || (shoppingCart.size() = 0)>
        <li class="disabled">${uiLabelMap.OrderFinalizeOrder}</li>
      <#else>
        <li><a href="<@ofbizUrl>finalizeOrder?finalizeMode=purchase&finalizeReqCustInfo=false&finalizeReqShipInfo=false&finalizeReqOptions=false&finalizeReqPayInfo=false</@ofbizUrl>">${uiLabelMap.OrderFinalizeOrder}</a></li>
      </#if>
    <#else>
      <#if shoppingCart.size() = 0>
        <li class="disabled">${uiLabelMap.OrderQuickFinalizeOrder}</li>
        <li class="disabled">${uiLabelMap.OrderFinalizeOrderDefault}</li>
        <li class="disabled">${uiLabelMap.OrderFinalizeOrder}</li>
      <#else>
        <li><a href="<@ofbizUrl>quickcheckout</@ofbizUrl>">${uiLabelMap.OrderQuickFinalizeOrder}</a></li>
        <li><a href="<@ofbizUrl>finalizeOrder?finalizeMode=default</@ofbizUrl>">${uiLabelMap.OrderFinalizeOrderDefault}</a></li>
        <li><a href="<@ofbizUrl>finalizeOrder?finalizeMode=init</@ofbizUrl>">${uiLabelMap.OrderFinalizeOrder}</a></li>
      </#if>
    </#if>
  </ul>
</div>
<br/>
<div class="screenlet-title-bar">
    <h3>
        ${uiLabelMap.CommonCreate}
        <#if shoppingCart.getOrderType() == "PURCHASE_ORDER">
            ${uiLabelMap.OrderPurchaseOrder}
        <#else>
            ${uiLabelMap.OrderSalesOrder}
        </#if>
    </h3>
</div>

