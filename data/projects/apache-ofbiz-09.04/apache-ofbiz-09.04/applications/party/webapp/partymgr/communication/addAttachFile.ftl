      <form id="uploadPartyContent" method="post" enctype="multipart/form-data" action="<@ofbizUrl>uploadAttachFiletoEmail</@ofbizUrl>">
        <input type="hidden" name="dataCategoryId" value="PERSONAL"/>
        <input type="hidden" name="contentTypeId" value="DOCUMENT"/>
        <input type="hidden" name="statusId" value="CTNT_PUBLISHED"/>
        <input type="hidden" name="partyId" value="${partyId}" id="contentPartyId"/>
        <input type="hidden" name="partyContentTypeId" value="USERDEF"/>
        <input type="hidden" name="roleTypeId" value="CONTENT"/>
        <input type="hidden" name="communicationEventId" value="${communicationEventId}"/>
        <input type="hidden" name="communicationEventTypeId" value="${communicationEventTypeId}"/>
        <input type="hidden" name="parentCommEventId" value="${parameters.parentCommEventId?if_exists}"/>
        <input type="hidden" name="originalCommEventId" value="${parameters.originalCommEventId?if_exists}"/>

        <input type="file" name="uploadedFile" size="25" id="uploadedFile"/><br/>
        <input type="submit" value="${uiLabelMap.CommonUpload}"/><br><br>
      </form>
      <div id='progress_bar'><div></div></div>
