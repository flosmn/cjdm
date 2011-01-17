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

<#if webSiteId?exists && requestAttributes._CURRENT_VIEW_?exists>
  <#assign helpTopic = webSiteId + "_" + requestAttributes._CURRENT_VIEW_ />
  <#assign helpUrlTopic = helpUrlsMap["HelpNotFound"]/>
  <#assign helpUrlPrefix = "" />
  <#assign helpUrlSuffix = "" />

<#-- uncomment this to show the current screen help topic key (this is usefull to cut and paste in the help link resources files
${helpTopic}
-->

  <#if helpUrlsMap["Prefix"] != "Prefix">
    <#assign helpUrlPrefix = helpUrlsMap["Prefix"] />
  </#if>
  <#if helpUrlsMap["Suffix"] != "Suffix">
    <#assign helpUrlSuffix = helpUrlsMap["Suffix"] />
  </#if>
  <#if helpUrlsMap[helpTopic] != helpTopic >
    <#assign helpUrlTopic = helpUrlsMap[helpTopic] />
  </#if>
  <li><a href="${helpUrlPrefix}${helpUrlTopic}${helpUrlSuffix}" target="_blank">${uiLabelMap.CommonHelp}</a></li>
</#if>
