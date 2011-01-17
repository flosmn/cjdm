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
<div class="screenlet">
    <div class="screenlet-title-bar">
        <ul>
            <li class="h3">${uiLabelMap.PageTitleRequestItems}</li>
        </ul>
        <br class="clear"/>
    </div>
	<table class="basic-table hover-bar" cellspacing="0" >
	<tr class="header-row">
           <td>
              ${uiLabelMap.CommonNbr}
           </td>
           <td colspan="2">
              ${uiLabelMap.CommonDescription}
           </td>
           <td>
           </td>
        </tr>
	<#list custRequestItems as custRequestItemList>
	<#if custRequestItemList.custRequestItemSeqId?has_content>
	   <tr class="header-row">
           <td>
           </td>
           <td colspan="2">
           </td>
           <td>
           </td>
        </tr>
		<tr>
            <td>
              <a href="<@ofbizUrl>requestitem?custRequestId=${custRequestItemList.custRequestId}&custRequestItemSeqId=${custRequestItemList.custRequestItemSeqId}</@ofbizUrl>" class="linktext">${custRequestItemList.custRequestItemSeqId}</a>
            </td>
            <td colspan="2">
              <#if custRequestItemList.story?has_content>
              ${custRequestItemList.story}
              </#if>
            </td>
            <td>
                <a href="<@ofbizUrl>requestitemnotes?custRequestId=${custRequestItemList.custRequestId}&custRequestItemSeqId=${custRequestItemList.custRequestItemSeqId}</@ofbizUrl>" class="linktext">${uiLabelMap.OrderAddNote}</a>
            </td>
        </tr>
        <tr class="header-row">
            <td>
            </td>
            <td>
                ${uiLabelMap.CommonNbr}
            </td>
            <td>
                ${uiLabelMap.CommonNote}
            </td>
            <td>
                ${uiLabelMap.CommonDate}
            </td>
        </tr>
		<#list custRequestItemNoteViews as custRequestItemNoteViewList>
			<#if custRequestItemNoteViewList.custRequestItemSeqId == custRequestItemList.custRequestItemSeqId>
			<#if row?has_content>
			     <#assign row="">
			     <#else>
			         <#assign row="alternate-row">
			</#if>
			<tr class="${row}">
	            <td>
	            </td>
	            <td>
	               ${custRequestItemNoteViewList.noteId}
	            </td>
	            <td >
	               ${custRequestItemNoteViewList.noteInfo}
	            </td>
	            <td>
	               ${custRequestItemNoteViewList.noteDateTime.toString().substring(0,10)}
	            </td>
			</tr>
			</#if>
		</#list>
	</#if>
	</#list>
	</table>
</div>