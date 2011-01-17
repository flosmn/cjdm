/*******************************************************************************
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
 *******************************************************************************/
package org.ofbiz.common.email;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Security;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;

import javolution.util.FastList;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.MimeConstants;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.GeneralException;
import org.ofbiz.base.util.HttpClient;
import org.ofbiz.base.util.HttpClientException;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilProperties;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.base.util.collections.MapStack;
import org.ofbiz.base.util.string.FlexibleStringExpander;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.LocalDispatcher;
import org.ofbiz.service.ServiceUtil;
import org.ofbiz.webapp.view.ApacheFopWorker;
import org.ofbiz.widget.fo.FoScreenRenderer;
import org.ofbiz.widget.html.HtmlScreenRenderer;
import org.ofbiz.widget.screen.ScreenRenderer;
import org.xml.sax.SAXException;

/**
 * Email Services
 */
public class EmailServices {

    public final static String module = EmailServices.class.getName();

    protected static final HtmlScreenRenderer htmlScreenRenderer = new HtmlScreenRenderer();
    protected static final FoScreenRenderer foScreenRenderer = new FoScreenRenderer();

    /**
     * Basic JavaMail Service
     *@param ctx The DispatchContext that this service is operating in
     *@param context Map containing the input parameters
     *@return Map with the result of the service, the output parameters
     */
    public static Map<String, Object> sendMail(DispatchContext ctx, Map<String, ? extends Object> context) {
        Map results = ServiceUtil.returnSuccess();
        String subject = (String) context.get("subject");
        String partyId = (String) context.get("partyId");
        String body = (String) context.get("body");
        List bodyParts = (List) context.get("bodyParts");
        GenericValue userLogin = (GenericValue) context.get("userLogin");

        results.put("partyId", partyId);
        results.put("subject", subject);
        if (UtilValidate.isNotEmpty(body)) {
            results.put("body", body);
        }
        if (UtilValidate.isNotEmpty(bodyParts)) {
            results.put("bodyParts", bodyParts);
        }
        results.put("userLogin", userLogin);

        // first check to see if sending mail is enabled
        String mailEnabled = UtilProperties.getPropertyValue("general.properties", "mail.notifications.enabled", "N");
        if (!"Y".equalsIgnoreCase(mailEnabled)) {
            // no error; just return as if we already processed
            Debug.logImportant("Mail notifications disabled in general.properties; here is the context with info that would have been sent: " + context, module);
            return results;
        }
        String sendTo = (String) context.get("sendTo");
        String sendCc = (String) context.get("sendCc");
        String sendBcc = (String) context.get("sendBcc");

        // check to see if we should redirect all mail for testing
        String redirectAddress = UtilProperties.getPropertyValue("general.properties", "mail.notifications.redirectTo");
        if (UtilValidate.isNotEmpty(redirectAddress)) {
            String originalRecipients = " [To: " + sendTo + ", Cc: " + sendCc + ", Bcc: " + sendBcc + "]";
            subject += originalRecipients;
            sendTo = redirectAddress;
            sendCc = null;
            sendBcc = null;
        }

        String sendFrom = (String) context.get("sendFrom");
        String sendType = (String) context.get("sendType");
        String port = (String) context.get("port");
        String socketFactoryClass = (String) context.get("socketFactoryClass");
        String socketFactoryPort  = (String) context.get("socketFactoryPort");
        String socketFactoryFallback  = (String) context.get("socketFactoryFallback");
        String sendVia = (String) context.get("sendVia");
        String authUser = (String) context.get("authUser");
        String authPass = (String) context.get("authPass");
        String messageId = (String) context.get("messageId");
        String contentType = (String) context.get("contentType");

        boolean useSmtpAuth = false;

        // define some default
        if (sendType == null || sendType.equals("mail.smtp.host")) {
            sendType = "mail.smtp.host";
            if (UtilValidate.isEmpty(sendVia)) {
                sendVia = UtilProperties.getPropertyValue("general.properties", "mail.smtp.relay.host", "localhost");
            }
            if (UtilValidate.isEmpty(authUser)) {
                authUser = UtilProperties.getPropertyValue("general.properties", "mail.smtp.auth.user");
            }
            if (UtilValidate.isEmpty(authPass)) {
                authPass = UtilProperties.getPropertyValue("general.properties", "mail.smtp.auth.password");
            }
            if (UtilValidate.isNotEmpty(authUser)) {
                useSmtpAuth = true;
            }
            if (UtilValidate.isEmpty(port)) {
                port = UtilProperties.getPropertyValue("general.properties", "mail.smtp.port");
            }
            if (UtilValidate.isEmpty(socketFactoryPort)) {
                socketFactoryPort = UtilProperties.getPropertyValue("general.properties", "mail.smtp.socketFactory.port");
            }
            if (UtilValidate.isEmpty(socketFactoryClass)) {
                socketFactoryClass = UtilProperties.getPropertyValue("general.properties", "mail.smtp.socketFactory.class");
            }
            if (UtilValidate.isEmpty(socketFactoryFallback)) {
                socketFactoryFallback = UtilProperties.getPropertyValue("general.properties", "mail.smtp.socketFactory.fallback", "false");
            }
        } else if (sendVia == null) {
            return ServiceUtil.returnError("Parameter sendVia is required when sendType is not mail.smtp.host");
        }

        if (contentType == null) {
            contentType = "text/html";
        }

        if (UtilValidate.isNotEmpty(bodyParts)) {
            contentType = "multipart/mixed";
        }
        results.put("contentType", contentType);

        Session session;
        MimeMessage mail;
        try {
            Properties props = System.getProperties();
            props.put(sendType, sendVia);
            if (UtilValidate.isNotEmpty(port)) {
                props.put("mail.smtp.port", port);
            }
            if (UtilValidate.isNotEmpty(socketFactoryPort)) {
                props.put("mail.smtp.socketFactory.port", socketFactoryPort);
            }
            if (UtilValidate.isNotEmpty(socketFactoryClass)) {
                props.put("mail.smtp.socketFactory.class", socketFactoryClass);
                Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            }
            if (UtilValidate.isNotEmpty(socketFactoryFallback)) {
                props.put("mail.smtp.socketFactory.fallback", socketFactoryFallback);
            }
            if (useSmtpAuth) {
                props.put("mail.smtp.auth", "true");
            }

            session = Session.getInstance(props);
            boolean debug = UtilProperties.propertyValueEqualsIgnoreCase("general.properties", "mail.debug.on", "Y");
            session.setDebug(debug);

            mail = new MimeMessage(session);
            if (messageId != null) {
                mail.setHeader("In-Reply-To", messageId);
                mail.setHeader("References", messageId);
            }
            mail.setFrom(new InternetAddress(sendFrom));
            mail.setSubject(subject, "UTF-8");
            mail.setHeader("X-Mailer", "Apache OFBiz, The Apache Open For Business Project");
            mail.setSentDate(new Date());
            mail.addRecipients(Message.RecipientType.TO, sendTo);

            if (UtilValidate.isNotEmpty(sendCc)) {
                mail.addRecipients(Message.RecipientType.CC, sendCc);
            }
            if (UtilValidate.isNotEmpty(sendBcc)) {
                mail.addRecipients(Message.RecipientType.BCC, sendBcc);
            }

            if (UtilValidate.isNotEmpty(bodyParts)) {
                // check for multipart message (with attachments)
                // BodyParts contain a list of Maps items containing content(String) and type(String) of the attachement
                MimeMultipart mp = new MimeMultipart();
                Debug.logInfo(bodyParts.size() + " multiparts found",module);
                Iterator bodyPartIter = bodyParts.iterator();
                while (bodyPartIter.hasNext()) {
                    Map bodyPart = (Map) bodyPartIter.next();
                    Object bodyPartContent = bodyPart.get("content");
                    MimeBodyPart mbp = new MimeBodyPart();

                    if (bodyPartContent instanceof String) {
                        Debug.logInfo("part of type: " + bodyPart.get("type") + " and size: " + bodyPart.get("content").toString().length() , module);
                        mbp.setText((String) bodyPartContent, "UTF-8", ((String) bodyPart.get("type")).substring(5));
                    } else if (bodyPartContent instanceof byte[]) {
                        ByteArrayDataSource bads = new ByteArrayDataSource((byte[]) bodyPartContent, (String) bodyPart.get("type"));
                        Debug.logInfo("part of type: " + bodyPart.get("type") + " and size: " + ((byte[]) bodyPartContent).length , module);
                        mbp.setDataHandler(new DataHandler(bads));
                    } else {
                        mbp.setDataHandler(new DataHandler(bodyPartContent, (String) bodyPart.get("type")));
                    }

                    String fileName = (String) bodyPart.get("filename");
                    if (fileName != null) {
                        mbp.setFileName(fileName);
                    }
                    mp.addBodyPart(mbp);
                }
                mail.setContent(mp);
                mail.saveChanges();
            } else {
                // create the singelpart message
                if (contentType.startsWith("text")) {
                    mail.setText(body, "UTF-8", contentType.substring(5));
                } else {
                    mail.setContent(body, contentType);
                }
                mail.saveChanges();
            }
        } catch (MessagingException e) {
            String errMsg = "MessagingException when creating message to [" + sendTo + "] from [" + sendFrom + "] cc [" + sendCc + "] bcc [" + sendBcc + "] subject [" + subject + "]";
            Debug.logError(e, errMsg, module);
            Debug.logError("Email message that could not be created to [" + sendTo + "] had context: " + context, module);
            return ServiceUtil.returnError(errMsg);
        } catch (IOException e) {
            String errMsg = "IOExcepton when creating message to [" + sendTo + "] from [" + sendFrom + "] cc [" + sendCc + "] bcc [" + sendBcc + "] subject [" + subject + "]";
            Debug.logError(e, errMsg, module);
            Debug.logError("Email message that could not be created to [" + sendTo + "] had context: " + context, module);
            return ServiceUtil.returnError(errMsg);
        }

        try {
            Transport trans = session.getTransport("smtp");
            if (!useSmtpAuth) {
                trans.connect();
            } else {
                trans.connect(sendVia, authUser, authPass);
            }
            trans.sendMessage(mail, mail.getAllRecipients());
            results.put("messageId", mail.getMessageID());
            trans.close();
        } catch (SendFailedException e) {
            // message code prefix may be used by calling services to determine the cause of the failure
            String errMsg = "[ADDRERR] Address error when sending message to [" + sendTo + "] from [" + sendFrom + "] cc [" + sendCc + "] bcc [" + sendBcc + "] subject [" + subject + "]";
            Debug.logError(e, errMsg, module);
            Debug.logError("Email message that could not be sent to [" + sendTo + "] had context: " + context, module);
            return ServiceUtil.returnError(errMsg);
        } catch (MessagingException e) {
            // message code prefix may be used by calling services to determine the cause of the failure
            String errMsg = "[CON] Connection error when sending message to [" + sendTo + "] from [" + sendFrom + "] cc [" + sendCc + "] bcc [" + sendBcc + "] subject [" + subject + "]";
            Debug.logError(e, errMsg, module);
            Debug.logError("Email message that could not be sent to [" + sendTo + "] had context: " + context, module);
            return ServiceUtil.returnError(errMsg);
        }
        return results;
    }

    /**
     * JavaMail Service that gets body content from a URL
     *@param ctx The DispatchContext that this service is operating in
     *@param rcontext Map containing the input parameters
     *@return Map with the result of the service, the output parameters
     */
    public static Map<String, Object> sendMailFromUrl(DispatchContext ctx, Map<String, ? extends Object> rcontext) {
        Map<String, Object> context = UtilMisc.makeMapWritable(rcontext);
        // pretty simple, get the content and then call the sendMail method below
        String bodyUrl = (String) context.remove("bodyUrl");
        Map bodyUrlParameters = (Map) context.remove("bodyUrlParameters");

        URL url = null;

        try {
            url = new URL(bodyUrl);
        } catch (MalformedURLException e) {
            Debug.logWarning(e, module);
            return ServiceUtil.returnError("Malformed URL: " + bodyUrl + "; error was: " + e.toString());
        }

        HttpClient httpClient = new HttpClient(url, bodyUrlParameters);
        String body = null;

        try {
            body = httpClient.post();
        } catch (HttpClientException e) {
            Debug.logWarning(e, module);
            return ServiceUtil.returnError("Error getting content: " + e.toString());
        }

        context.put("body", body);
        Map result = sendMail(ctx, context);

        result.put("body", body);
        return result;
    }

    /**
     * JavaMail Service that gets body content from a Screen Widget
     * defined in the product store record and if available as attachment also.
     *@param dctx The DispatchContext that this service is operating in
     *@param rServiceContext Map containing the input parameters
     *@return Map with the result of the service, the output parameters
     */
    public static Map<String, Object> sendMailFromScreen(DispatchContext dctx, Map<String, ? extends Object> rServiceContext) {
        Map<String, Object> serviceContext = UtilMisc.makeMapWritable(rServiceContext);
        LocalDispatcher dispatcher = dctx.getDispatcher();
        String webSiteId = (String) serviceContext.remove("webSiteId");
        String bodyText = (String) serviceContext.remove("bodyText");
        String bodyScreenUri = (String) serviceContext.remove("bodyScreenUri");
        String xslfoAttachScreenLocation = (String) serviceContext.remove("xslfoAttachScreenLocation");
        String attachmentName = (String) serviceContext.remove("attachmentName");
        Locale locale = (Locale) serviceContext.get("locale");
        Map bodyParameters = (Map) serviceContext.remove("bodyParameters");
        if (bodyParameters == null) {
            bodyParameters = MapStack.create();
        }
        if (!bodyParameters.containsKey("locale")) {
            bodyParameters.put("locale", locale);
        } else {
            locale = (Locale) bodyParameters.get("locale");
        }
        String partyId = (String) bodyParameters.get("partyId");
        if (UtilValidate.isNotEmpty(webSiteId)) {
            NotificationServices.setBaseUrl(dctx.getDelegator(), webSiteId, bodyParameters);
        }
        String contentType = (String) serviceContext.remove("contentType");

        if (UtilValidate.isEmpty(attachmentName)) {
            attachmentName = "Details.pdf";
        }
        StringWriter bodyWriter = new StringWriter();

        MapStack screenContext = MapStack.create();
        screenContext.put("locale", locale);
        ScreenRenderer screens = new ScreenRenderer(bodyWriter, screenContext, htmlScreenRenderer);
        screens.populateContextForService(dctx, bodyParameters);
        screenContext.putAll(bodyParameters);

        if (bodyScreenUri != null) {
            try {
                screens.render(bodyScreenUri);
            } catch (GeneralException e) {
                String errMsg = "Error rendering screen for email: " + e.toString();
                Debug.logError(e, errMsg, module);
                return ServiceUtil.returnError(errMsg);
            } catch (IOException e) {
                String errMsg = "Error rendering screen for email: " + e.toString();
                Debug.logError(e, errMsg, module);
                return ServiceUtil.returnError(errMsg);
            } catch (SAXException e) {
                String errMsg = "Error rendering screen for email: " + e.toString();
                Debug.logError(e, errMsg, module);
                return ServiceUtil.returnError(errMsg);
            } catch (ParserConfigurationException e) {
                String errMsg = "Error rendering screen for email: " + e.toString();
                Debug.logError(e, errMsg, module);
                return ServiceUtil.returnError(errMsg);
            }
        }

        boolean isMultiPart = false;

        // check if attachment screen location passed in
        if (UtilValidate.isNotEmpty(xslfoAttachScreenLocation)) {
            isMultiPart = true;
            // start processing fo pdf attachment
            try {
                Writer writer = new StringWriter();
                MapStack screenContextAtt = MapStack.create();
                // substitute the freemarker variables...
                ScreenRenderer screensAtt = new ScreenRenderer(writer, screenContext, foScreenRenderer);
                screensAtt.populateContextForService(dctx, bodyParameters);
                screenContextAtt.putAll(bodyParameters);
                screensAtt.render(xslfoAttachScreenLocation);

                /*
                try { // save generated fo file for debugging
                    String buf = writer.toString();
                    java.io.FileWriter fw = new java.io.FileWriter(new java.io.File("/tmp/file1.xml"));
                    fw.write(buf.toString());
                    fw.close();
                } catch (IOException e) {
                    Debug.logError(e, "Couldn't save xsl-fo xml debug file: " + e.toString(), module);
                }
                */

                // create the input stream for the generation
                StreamSource src = new StreamSource(new StringReader(writer.toString()));

                // create the output stream for the generation
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                Fop fop = ApacheFopWorker.createFopInstance(baos, MimeConstants.MIME_PDF);
                ApacheFopWorker.transform(src, null, fop);

                // and generate the PDF
                baos.flush();
                baos.close();

                // store in the list of maps for sendmail....
                List bodyParts = FastList.newInstance();
                if (bodyText != null) {
                    bodyText = FlexibleStringExpander.expandString(bodyText, screenContext,  locale);
                    bodyParts.add(UtilMisc.toMap("content", bodyText, "type", "text/html"));
                } else {
                    bodyParts.add(UtilMisc.toMap("content", bodyWriter.toString(), "type", "text/html"));
                }
                bodyParts.add(UtilMisc.toMap("content", baos.toByteArray(), "type", "application/pdf", "filename", attachmentName));
                serviceContext.put("bodyParts", bodyParts);
            } catch (GeneralException ge) {
                String errMsg = "Error rendering PDF attachment for email: " + ge.toString();
                Debug.logError(ge, errMsg, module);
                return ServiceUtil.returnError(errMsg);
            } catch (IOException ie) {
                String errMsg = "Error rendering PDF attachment for email: " + ie.toString();
                Debug.logError(ie, errMsg, module);
                return ServiceUtil.returnError(errMsg);
            } catch (FOPException fe) {
                String errMsg = "Error rendering PDF attachment for email: " + fe.toString();
                Debug.logError(fe, errMsg, module);
                return ServiceUtil.returnError(errMsg);
            } catch (SAXException se) {
                String errMsg = "Error rendering PDF attachment for email: " + se.toString();
                Debug.logError(se, errMsg, module);
                return ServiceUtil.returnError(errMsg);
            } catch (ParserConfigurationException pe) {
                String errMsg = "Error rendering PDF attachment for email: " + pe.toString();
                Debug.logError(pe, errMsg, module);
                return ServiceUtil.returnError(errMsg);
            }
        } else {
            isMultiPart = false;
            // store body and type for single part message in the context.
            if (bodyText != null) {
                bodyText = FlexibleStringExpander.expandString(bodyText, screenContext,  locale);
                serviceContext.put("body", bodyText);
            } else {
                serviceContext.put("body", bodyWriter.toString());
            }

            // Only override the default contentType in case of plaintext, since other contentTypes may be multipart
            //    and would require specific handling.
            if (contentType != null && contentType.equalsIgnoreCase("text/plain")) {
                serviceContext.put("contentType", "text/plain");
            } else {
                serviceContext.put("contentType", "text/html");
            }
        }

        // also expand the subject at this point, just in case it has the FlexibleStringExpander syntax in it...
        String subject = (String) serviceContext.remove("subject");
        subject = FlexibleStringExpander.expandString(subject, screenContext, locale);
        serviceContext.put("subject", subject);
        serviceContext.put("partyId", partyId);

        if (Debug.verboseOn()) Debug.logVerbose("sendMailFromScreen sendMail context: " + serviceContext, module);

        Map result = ServiceUtil.returnSuccess();
        try {
            if (isMultiPart) {
                dispatcher.runSync("sendMailMultiPart", serviceContext);
            } else {
                dispatcher.runSync("sendMail", serviceContext);
            }
        } catch (Exception e) {
            String errMsg = "Error send email :" + e.toString();
            Debug.logError(e, errMsg, module);
            return ServiceUtil.returnError(errMsg);
        }
        result.put("body", bodyWriter.toString());
        return result;
    }

    /** class to create a file in memory required for sending as an attachment */
    public static class StringDataSource implements DataSource {
        private String contentType;
        private ByteArrayOutputStream contentArray;

        public StringDataSource(String content, String contentType) throws IOException {
            this.contentType = contentType;
            contentArray = new ByteArrayOutputStream();
            contentArray.write(content.getBytes("iso-8859-1"));
            contentArray.flush();
            contentArray.close();
        }

        public String getContentType() {
            return contentType == null ? "application/octet-stream" : contentType;
        }

        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(contentArray.toByteArray());
        }

        public String getName() {
            return "stringDatasource";
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Cannot write to this read-only resource");
        }
    }

    /** class to create a file in memory required for sending as an attachment */
    public static class ByteArrayDataSource implements DataSource {
        private String contentType;
        private byte[] contentArray;

        public ByteArrayDataSource(byte[] content, String contentType) throws IOException {
            this.contentType = contentType;
            this.contentArray = content;
        }

        public String getContentType() {
            return contentType == null ? "application/octet-stream" : contentType;
        }

        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(contentArray);
        }

        public String getName() {
            return "ByteArrayDataSource";
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Cannot write to this read-only resource");
        }
    }
}
