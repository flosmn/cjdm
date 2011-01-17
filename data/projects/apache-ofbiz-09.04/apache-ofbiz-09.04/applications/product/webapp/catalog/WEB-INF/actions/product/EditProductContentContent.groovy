/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import org.ofbiz.entity.*;
import org.ofbiz.entity.util.*;
import org.ofbiz.base.util.*;
import java.sql.Timestamp;
import org.ofbiz.base.util.ObjectType

contentId = request.getParameter("contentId");
if ("".equals(contentId)) {
    contentId = null;
}

productContentTypeId = request.getParameter("productContentTypeId");

fromDate = request.getParameter("fromDate");
if ("".equals(fromDate)) {
    fromDate = null;
} else {
    fromDate = ObjectType.simpleTypeConvert(fromDate, "Timestamp", null, null, false)
}


description = request.getParameter("description");
if ("".equals(description)) {
    description = null;
}

productContent = delegator.findOne("ProductContent", [contentId : contentId, productId : productId, productContentTypeId : productContentTypeId, fromDate : fromDate], false);
if (!productContent) {
    productContent = [:];
    productContent.productId = productId;
    productContent.contentId = contentId;
    productContent.productContentTypeId = productContentTypeId;
    productContent.fromDate = fromDate;
    productContent.thruDate = request.getParameter("thruDate");
    productContent.purchaseFromDate = request.getParameter("purchaseFromDate");
    productContent.purchaseThruDate = request.getParameter("purchaseThruDate");
    productContent.useCountLimit = request.getParameter("useCountLimit");
    productContent.useTime = request.getParameter("useTime");
    productContent.useTimeUomId = request.getParameter("useTimeUomId");
    productContent.useRoleTypeId = request.getParameter("useRoleTypeId");
}
context.productContent = productContent;

productContentData = [:];
productContentData.putAll(productContent);

content = [:];
context.contentId = contentId;
if (contentId) {
    content = delegator.findOne("Content", [contentId : contentId], false);
    context.content = content;
} else {
    content = [:];
    if (description) {
        content.description = description;
    }
}

//Email
if ("FULFILLMENT_EMAIL".equals(productContentTypeId)) {
    emailData = [:];
    if (contentId && content) {
        subjectDr = content.getRelatedOne("DataResource");
        if (subjectDr) {
            subject = subjectDr.getRelatedOne("ElectronicText");
            emailData.subject = subject.textData;
            emailData.subjectDataResourceId = subject.dataResourceId;
        }
        serviceCtx = [userLogin : userLogin, contentId : contentId, mapKeys : ['plainBody', 'htmlBody']];
        result = dispatcher.runSync("findAssocContent", serviceCtx);
        contentAssocs = result.get("contentAssocs");
        if (contentAssocs) {
            for (contentAssocIter = contentAssocs.iterator(); contentAssocIter;) {
                contentAssoc  = contentAssocIter.next();
                bodyContent = contentAssoc.getRelatedOne("ToContent");
                bodyDr = bodyContent.getRelatedOne("DataResource");
                body = bodyDr.getRelatedOne("ElectronicText");
                emailData.put(contentAssoc.mapKey, body.textData);
                emailData.put(contentAssoc.get("mapKey")+"DataResourceId", body.dataResourceId);
            }
        }
    }

    context.contentFormName = "EditProductContentEmail";
    context.emailData = emailData;
} else if ("DIGITAL_DOWNLOAD".equals(productContentTypeId)) {
    downloadData = [:];
    if (contentId && content) {
        downloadDr = content.getRelatedOne("DataResource");
        if (downloadDr) {
            download = downloadDr.getRelatedOne("OtherDataResource");
            if (download) {
                downloadData.file = download.dataResourceContent;
                downloadData.fileDataResourceId = download.dataResourceId;
            }
        }
    }
    context.contentFormName = "EditProductContentDownload";
    context.downloadData = downloadData;
} else if ("FULFILLMENT_EXTERNAL".equals(productContentTypeId)) {
    context.contentFormName = "EditProductContentExternal";
} else if (productContentTypeId && productContentTypeId.indexOf("ADDITIONAL_IMAGE") > -1) {
    context.contentFormName = "EditProductAdditionalImageContent";
} else {
    //Assume it is a generic simple text content
    textData = [:];
    if (contentId && content) {
        textDr = content.getRelatedOne("DataResource");
        if (textDr) {
            text = textDr.getRelatedOne("ElectronicText");
            if (text) {
                textData.text = text.textData;
                textData.textDataResourceId = text.dataResourceId;
            }
        }
    }
    context.contentFormName = "EditProductContentSimpleText";
    context.textData = textData;
}

context.productContentData = productContentData;
context.content = content;
context.contentId = contentId;
