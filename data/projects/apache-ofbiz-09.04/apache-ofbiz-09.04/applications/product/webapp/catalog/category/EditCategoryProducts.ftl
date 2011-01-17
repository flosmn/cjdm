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
<#if activeOnly>
    <a href="<@ofbizUrl>EditCategoryProducts?productCategoryId=${productCategoryId?if_exists}&activeOnly=false</@ofbizUrl>" class="buttontext">${uiLabelMap.ProductActiveAndInactive}</a>
<#else>
    <a href="<@ofbizUrl>EditCategoryProducts?productCategoryId=${productCategoryId?if_exists}&activeOnly=true</@ofbizUrl>" class="buttontext">${uiLabelMap.ProductActiveOnly}</a>
</#if>
<div class="screenlet">
    <div class="screenlet-title-bar">
        <#if (listSize > 0)>
            <div class="boxhead-right">
                <#if (viewIndex > 1)>
                    <a href="<@ofbizUrl>EditCategoryProducts?productCategoryId=${productCategoryId?if_exists}&VIEW_SIZE=${viewSize}&VIEW_INDEX=${viewIndex-1}&activeOnly=${activeOnly.toString()}</@ofbizUrl>" class="submenutext">${uiLabelMap.CommonPrevious}</a> |
                </#if>
                <span class="submenutextinfo">${lowIndex} - ${highIndex} ${uiLabelMap.CommonOf} ${listSize}</span>
                <#if (listSize > highIndex)>
                    | <a class="lightbuttontext" href="<@ofbizUrl>EditCategoryProducts?productCategoryId=${productCategoryId?if_exists}&VIEW_SIZE=${viewSize}&VIEW_INDEX=${viewIndex+1}&activeOnly=${activeOnly.toString()}</@ofbizUrl>" class="submenutextright">${uiLabelMap.CommonNext}</a>
                </#if>
                &nbsp;
            </div>
            <div class="boxhead-left">
                ${uiLabelMap.PageTitleEditCategoryProducts}
            </div>
            <div class="boxhead-fill">&nbsp;</div>
        </#if>
    </div>
    <div class="screenlet-body">
        <table cellspacing="0" class="basic-table">
          <tr class="header-row">
            <td>${uiLabelMap.ProductProductNameId}</td>
            <td>${uiLabelMap.CommonFromDateTime}</td>
            <td align="center">${uiLabelMap.ProductThruDateTimeSequenceQuantity} ${uiLabelMap.CommonComments}</td>
            <td>&nbsp;</td>
          </tr>
          <#if (listSize > 0)>
            <tr><td>
              <#assign rowClass = "2">
              <#assign rowCount = 0>
              <#list productCategoryMembers as productCategoryMember>
                <#assign suffix = "_o_" + productCategoryMember_index>
                <#assign product = productCategoryMember.getRelatedOne("Product")>
                <#assign hasntStarted = false>
                <#if productCategoryMember.fromDate?exists && nowTimestamp.before(productCategoryMember.getTimestamp("fromDate"))><#assign hasntStarted = true></#if>
                <#assign hasExpired = false>
                <#if productCategoryMember.thruDate?exists && nowTimestamp.after(productCategoryMember.getTimestamp("thruDate"))><#assign hasExpired = true></#if>
                <form method="post" action="<@ofbizUrl>updateCategoryProductMember</@ofbizUrl>" name="updateCategoryProductForm">
                  <input type="hidden" name="VIEW_SIZE" value="${viewSize}"/>
                  <input type="hidden" name="VIEW_INDEX" value="${viewIndex}"/>
                  <input type="hidden" name="activeOnly" value="${activeOnly.toString()}">
                  <input type="hidden" name="productCategoryId" value="${productCategoryId?if_exists}">
                  <tr valign="middle"<#if rowClass == "1"> class="alternate-row"</#if>>
                    <td>
                      <#if (product.smallImageUrl)?exists>
                         <a href="<@ofbizUrl>EditProduct?productId=${(productCategoryMember.productId)?if_exists}</@ofbizUrl>"><img alt="Small Image" src="<@ofbizContentUrl>${product.smallImageUrl}</@ofbizContentUrl>" height="40" width="40" align="middle"></a>
                      </#if>
                      <a href="<@ofbizUrl>EditProduct?productId=${(productCategoryMember.productId)?if_exists}</@ofbizUrl>" class="buttontext"><#if product?exists>${(product.internalName)?if_exists}</#if> [${(productCategoryMember.productId)?if_exists}]</a>
                    </td>
                    <td <#if hasntStarted> style="color: red;"</#if>>${(productCategoryMember.fromDate)?if_exists}</td>
                    <td align="center">
                        <input type="hidden" name="productId${suffix}" value="${(productCategoryMember.productId)?if_exists}">
                        <input type="hidden" name="productCategoryId${suffix}" value="${(productCategoryMember.productCategoryId)?if_exists}">
                        <input type="hidden" name="fromDate${suffix}" value="${(productCategoryMember.fromDate)?if_exists}">
                        <input type="text" size="25" name="thruDate${suffix}" value="${(productCategoryMember.thruDate)?if_exists}" <#if hasExpired>style="color: red;"</#if>>
                        <a href="javascript:call_cal(document.updateCategoryProductForm.thruDate${suffix}, '${(productCategoryMember.thruDate)?default(nowTimestamp?string)}');"><img src="<@ofbizContentUrl>/images/cal.gif</@ofbizContentUrl>" width="16" height="16" border="0" alt="Calendar"></a>
                        <input type="text" size="5" name="sequenceNum${suffix}" value="${(productCategoryMember.sequenceNum)?if_exists}">
                        <input type="text" size="5" name="quantity${suffix}" value="${(productCategoryMember.quantity)?if_exists}">
                        <br/>
                        <textarea name="comments${suffix}" rows="2" cols="40">${(productCategoryMember.comments)?if_exists}</textarea>
                    </td>
                    <td align="center">
                      <a href="javascript:document.deleteProductFromCategory_o_${rowCount}.submit()" class="buttontext">${uiLabelMap.CommonDelete}</a>
                    </td>
                  </tr>
                  <#-- toggle the row color -->
                  <#if rowClass == "2">
                      <#assign rowClass = "1">
                  <#else>
                      <#assign rowClass = "2">
                  </#if>
                  <tr valign="middle">
                      <td colspan="4" align="center">
                          <input type="submit" value="${uiLabelMap.CommonUpdate}" style="font-size: x-small;">
                          <input type="hidden" value="${productCategoryMembers.size()}" name="_rowCount">
                      </td>
                  </tr>
                </form>
                <form name="deleteProductFromCategory_o_${rowCount}" method="post" action="<@ofbizUrl>removeCategoryProductMember</@ofbizUrl>">
                  <input type="hidden" name="VIEW_SIZE" value="${viewSize}"/>
                  <input type="hidden" name="VIEW_INDEX" value="${viewIndex}"/>
                  <input type="hidden" name="productId" value="${(productCategoryMember.productId)?if_exists}">
                  <input type="hidden" name="productCategoryId" value="${(productCategoryMember.productCategoryId)?if_exists}"/>
                  <input type="hidden" name="fromDate" value="${productCategoryMember.getString("fromDate")?if_exists}"/>
                  <input type="hidden" name="activeOnly" value="${activeOnly.toString()}"/>
                </form>
                <#assign rowCount = rowCount + 1>
              </#list>
          </#if>
        </table>
    </div>
    <div class="screenlet-title-bar">
        <#if (listSize > 0)>
            <div class="boxhead-right">
                <#if (viewIndex > 1)>
                    <a href="<@ofbizUrl>EditCategoryProducts?productCategoryId=${productCategoryId?if_exists}&VIEW_SIZE=${viewSize}&VIEW_INDEX=${viewIndex-1}&activeOnly=${activeOnly.toString()}</@ofbizUrl>" class="submenutext">${uiLabelMap.CommonPrevious}</a> |
                </#if>
                <span class="submenutextinfo">${lowIndex} - ${highIndex} ${uiLabelMap.CommonOf} ${listSize}</span>
                <#if (listSize > highIndex)>
                    | <a class="lightbuttontext" href="<@ofbizUrl>EditCategoryProducts?productCategoryId=${productCategoryId?if_exists}&VIEW_SIZE=${viewSize}&VIEW_INDEX=${viewIndex+1}&activeOnly=${activeOnly.toString()}</@ofbizUrl>" class="submenutextright">${uiLabelMap.CommonNext}</a>
                </#if>
                &nbsp;
            </div>
            <div class="boxhead-left">
                ${uiLabelMap.PageTitleEditCategoryProducts}
            </div>
            <div class="boxhead-fill">&nbsp;</div>
        </#if>
    </div>
</div>
<div class="screenlet">
    <div class="screenlet-title-bar">
        <h3>${uiLabelMap.ProductAddProductCategoryMember}:</h3>
    </div>
    <div class="screenlet-body">
        <table cellspacing="0" class="basic-table">
            <tr><td>
                <form method="post" action="<@ofbizUrl>addCategoryProductMember</@ofbizUrl>" style="margin: 0;" name="addProductCategoryMemberForm">
                    <input type="hidden" name="productCategoryId" value="${productCategoryId?if_exists}">
                    <input type="hidden" name="activeOnly" value="${activeOnly.toString()}">
                    <div>
                        <span class="label">${uiLabelMap.ProductProductId}</span> <input type="text" size="20" name="productId">
                        <a href="javascript:call_fieldlookup2(document.addProductCategoryMemberForm.productId, 'LookupProduct');"><img src="<@ofbizContentUrl>/images/fieldlookup.gif</@ofbizContentUrl>" width="16" height="16" border="0" alt="${uiLabelMap.CommonClickHereForFieldLookup}"></a>
                        <span class="label">${uiLabelMap.CommonFromDate}</span> <input type="text" size="22" name="fromDate">
                        <a href="javascript:call_cal(document.addProductCategoryMemberForm.fromDate, '${nowTimestamp?string}');"><img src="<@ofbizContentUrl>/images/cal.gif</@ofbizContentUrl>" width="16" height="16" border="0" alt="Calendar"></a>
                          <br/>
                          <span class="label">${uiLabelMap.CommonComments}</span> <textarea name="comments" rows="2" cols="40"></textarea>
                          <input type="submit" value="${uiLabelMap.CommonAdd}">
                    </div>
                </form>
            </td></tr>
        </table>
    </div>
</div>
<div class="screenlet">
    <div class="screenlet-title-bar">
        <h3>${uiLabelMap.ProductCopyProductCategoryMembersToAnotherCategory}:</h3>
    </div>
    <div class="screenlet-body">
        <table cellspacing="0" class="basic-table">
            <tr><td>
                <form method="post" action="<@ofbizUrl>copyCategoryProductMembers</@ofbizUrl>" style="margin: 0;" name="copyCategoryProductMembersForm">
                    <input type="hidden" name="productCategoryId" value="${productCategoryId?if_exists}">
                    <input type="hidden" name="activeOnly" value="${activeOnly.toString()}">
                    <div>
                        <span class="label">${uiLabelMap.ProductTargetProductCategory}</span>
                        <input type="text" name="productCategoryIdTo" size="20" maxlength="20"/>
                        <a href="javascript:call_fieldlookup2(document.copyCategoryProductMembersForm.productCategoryIdTo,'LookupProductCategory');"><img src='/images/fieldlookup.gif' width='15' height='14' border='0' alt="${uiLabelMap.CommonClickHereForFieldLookup}"/></a>
                        <br/>
                        <span class="label">${uiLabelMap.ProductOptionalFilterWithDate}</span> <input type="text" size="20" name="validDate">
                        <a href="javascript:call_cal(document.copyCategoryProductMembersForm.validDate, '${nowTimestamp?string}');"><img src="<@ofbizContentUrl>/images/cal.gif</@ofbizContentUrl>" width="16" height="16" border="0" alt="Calendar"></a>
                        <br/>
                        <span class="label">${uiLabelMap.ProductIncludeSubCategories}?</span>
                        <select name="recurse">
                            <option value="N">${uiLabelMap.CommonN}</option>
                            <option value="Y">${uiLabelMap.CommonY}</option>
                        </select>
                        <input type="submit" value="${uiLabelMap.CommonCopy}">
                    </div>
                </form>
            </td></tr>
        </table>
    </div>
</div>
<div class="screenlet">
    <div class="screenlet-title-bar">
        <h3>${uiLabelMap.ProductExpireAllProductMembers}:</h3>
    </div>
    <div class="screenlet-body">
        <table cellspacing="0" class="basic-table">
            <tr><td>
                <form method="post" action="<@ofbizUrl>expireAllCategoryProductMembers</@ofbizUrl>" style="margin: 0;" name="expireAllCategoryProductMembersForm">
                    <input type="hidden" name="productCategoryId" value="${productCategoryId?if_exists}">
                    <input type="hidden" name="activeOnly" value="${activeOnly.toString()}">
                    <div>
                        <span class="label">${uiLabelMap.ProductOptionalExpirationDate}</span> <input type="text" size="20" name="thruDate">
                        <a href="javascript:call_cal(document.expireAllCategoryProductMembersForm.thruDate, '${nowTimestamp?string}');"><img src="<@ofbizContentUrl>/images/cal.gif</@ofbizContentUrl>" width="16" height="16" border="0" alt="Calendar"></a>
                        &nbsp;&nbsp;<input type="submit" value="${uiLabelMap.CommonExpireAll}">
                    </div>
                </form>
            </td></tr>
        </table>
    </div>
</div>
<div class="screenlet">
    <div class="screenlet-title-bar">
        <h3>${uiLabelMap.ProductRemoveExpiredProductMembers}:</h3>
    </div>
    <div class="screenlet-body">
        <table cellspacing="0" class="basic-table">
            <tr><td>
                <form method="post" action="<@ofbizUrl>removeExpiredCategoryProductMembers</@ofbizUrl>" style="margin: 0;" name="removeExpiredCategoryProductMembersForm">
                    <input type="hidden" name="productCategoryId" value="${productCategoryId?if_exists}">
                    <input type="hidden" name="activeOnly" value="${activeOnly.toString()}">
                    <div>
                        <span class="label">${uiLabelMap.ProductOptionalExpiredBeforeDate}</span> <input type="text" size="20" name="validDate">
                        <a href="javascript:call_cal(document.removeExpiredCategoryProductMembersForm.validDate, '${nowTimestamp?string}');"><img src="<@ofbizContentUrl>/images/cal.gif</@ofbizContentUrl>" width="16" height="16" border="0" alt="Calendar"></a>
                        &nbsp;&nbsp;<input type="submit" value="${uiLabelMap.CommonRemoveExpired}">
                    </div>
                </form>
            </td></tr>
        </table>
    </div>
</div>
