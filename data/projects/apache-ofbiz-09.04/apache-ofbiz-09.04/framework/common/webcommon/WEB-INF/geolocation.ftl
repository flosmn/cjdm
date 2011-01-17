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
<#if geoPoint?has_content>
  <#if geoPoint.latitude?has_content && geoPoint.longitude?has_content>
    ${uiLabelMap.CommonLatitude}${geoPoint.latitude}<br>
    ${uiLabelMap.CommonLongitude}${geoPoint.longitude}
    <#if geoPoint.elevation?has_content>
      <br>${uiLabelMap.CommonElevation}${geoPoint.elevation} ${elevationUomAbbr}
    </#if>
    <#if geoPoint.dataSourceId?has_content>
      <#if geoPoint.dataSourceId == "GEOPT_GOOGLE">
        <div id="map" style="border:1px solid #979797; background-color:#e5e3df; width:400px; height:300px; margin:2em auto;">
          <div style="padding:1em; color:gray;">${uiLabelMap.CommonLoading}</div>
        </div>
        <#assign defaultUrl = "https." + request.getServerName()>
        <#assign defaultGogleMapKey = Static["org.ofbiz.base.util.UtilProperties"].getPropertyValue("general.properties", defaultUrl)>
        <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=${defaultGogleMapKey}"
            type="text/javascript">
        </script>
        <script type="text/javascript">
          loadGoogleMap("${geoPoint.latitude}",
                        "${geoPoint.longitude}")
        </script>
      <#elseif  geoPoint.dataSourceId == "GEOPT_YAHOO">
      <#elseif  geoPoint.dataSourceId == "GEOPT_MICROSOFT">
      <#elseif  geoPoint.dataSourceId == "GEOPT_MAPTP">
      </#if>
    </#if>
  </#if>
<#else>
  <h2>${uiLabelMap.CommonNoGeolocationAvailable}</h2>
</#if>
