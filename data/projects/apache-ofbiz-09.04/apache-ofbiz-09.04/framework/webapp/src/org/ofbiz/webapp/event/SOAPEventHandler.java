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
package org.ofbiz.webapp.event;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.wsdl.WSDLException;
import javax.xml.soap.SOAPException;

import javolution.util.FastMap;

import org.apache.axis.AxisFault;
import org.apache.axis.Constants;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.message.RPCElement;
import org.apache.axis.message.RPCParam;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.server.AxisServer;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilXml;
import org.ofbiz.service.DispatchContext;
import org.ofbiz.service.GenericServiceException;
import org.ofbiz.service.LocalDispatcher;
import org.ofbiz.service.ModelService;
import org.ofbiz.webapp.control.RequestHandler;
import org.ofbiz.webapp.control.ConfigXMLReader.Event;
import org.ofbiz.webapp.control.ConfigXMLReader.RequestMap;
import org.w3c.dom.Document;

/**
 * SOAPEventHandler - SOAP Event Handler implementation
 */
public class SOAPEventHandler implements EventHandler {

    public static final String module = SOAPEventHandler.class.getName();

    /**
     * @see org.ofbiz.webapp.event.EventHandler#init(javax.servlet.ServletContext)
     */
    public void init(ServletContext context) throws EventHandlerException {
    }

    /**
     * @see org.ofbiz.webapp.event.EventHandler#invoke(Event, org.ofbiz.webapp.control.ConfigXMLReader.RequestMap, HttpServletRequest, HttpServletResponse)
     */
    public String invoke(Event event, RequestMap requestMap, HttpServletRequest request, HttpServletResponse response) throws EventHandlerException {
        LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");
        AxisServer axisServer;

        // first check for WSDL request
        String wsdlReq = request.getParameter("wsdl");
        if (wsdlReq == null) {
            wsdlReq = request.getParameter("WSDL");
        }
        if (wsdlReq != null) {
            String serviceName = RequestHandler.getOverrideViewUri(request.getPathInfo());
            DispatchContext dctx = dispatcher.getDispatchContext();
            String locationUri = this.getLocationURI(request);

            if (serviceName != null) {
                Document wsdl = null;
                try {
                    wsdl = dctx.getWSDL(serviceName, locationUri);
                } catch (GenericServiceException e) {
                    serviceName = null;
                } catch (WSDLException e) {
                    sendError(response, "Unable to obtain WSDL");
                    throw new EventHandlerException("Unable to obtain WSDL", e);
                }

                if (wsdl != null) {
                    try {
                        OutputStream os = response.getOutputStream();
                        response.setContentType("text/xml");
                        UtilXml.writeXmlDocument(os, wsdl);
                        response.flushBuffer();
                    } catch (IOException e) {
                        throw new EventHandlerException(e);
                    }
                    return null;
                } else {
                    sendError(response, "Unable to obtain WSDL");
                    throw new EventHandlerException("Unable to obtain WSDL");
                }
            }

            if (serviceName == null) {
                try {
                    Writer writer = response.getWriter();
                    StringBuilder sb = new StringBuilder();
                    sb.append("<html><head><title>OFBiz SOAP/1.1 Services</title></head>");
                    sb.append("<body>No such service.").append("<p>Services:<ul>");

                    for (String scvName: dctx.getAllServiceNames()) {
                        ModelService model = dctx.getModelService(scvName);
                        if (model.export) {
                            sb.append("<li><a href=\"").append(locationUri).append("/").append(model.name).append("?wsdl\">");
                            sb.append(model.name).append("</a></li>");
                        }
                    }
                    sb.append("</ul></p></body></html>");

                    writer.write(sb.toString());
                    writer.flush();
                    return null;
                } catch (Exception e) {
                    sendError(response, "Unable to obtain WSDL");
                    throw new EventHandlerException("Unable to obtain WSDL");
                }
            }
        }

        // not a wsdl request; invoke the service
        try {
            axisServer = AxisServer.getServer(UtilMisc.toMap("name", "OFBiz/Axis Server", "provider", null));
        } catch (AxisFault e) {
            sendError(response, e);
            throw new EventHandlerException("Problems with the AXIS server", e);
        }
        MessageContext mctx = new MessageContext(axisServer);
        mctx.setEncodingStyle(Constants.URI_LITERAL_ENC); // sets the response encoding

        // get the SOAP message
        Message msg = null;

        try {
            msg = new Message(request.getInputStream(), false,
                        request.getHeader("Content-Type"), request.getHeader("Content-Location"));
        } catch (IOException ioe) {
            sendError(response, "Problem processing the service");
            throw new EventHandlerException("Cannot read the input stream", ioe);
        }

        if (msg == null) {
            sendError(response, "No message");
            throw new EventHandlerException("SOAP Message is null");
        }

        // log the request message
        if (Debug.verboseOn()) {
            try {
                Debug.logInfo("Request Message:\n" + messageToString(msg) + "\n", module);
            } catch (Throwable t) {
            }
        }

        mctx.setRequestMessage(msg);

        // new envelopes
        SOAPEnvelope resEnv = new SOAPEnvelope();
        SOAPEnvelope reqEnv = null;

        // get the service name and parameters
        try {
            reqEnv = (SOAPEnvelope) msg.getSOAPPart().getEnvelope();
        } catch (SOAPException e) {
            sendError(response, "Problem processing the service");
            throw new EventHandlerException("Cannot get the envelope", e);
        }

        List<Object> bodies = null;

        try {
            bodies = reqEnv.getBodyElements();
        } catch (AxisFault e) {
            sendError(response, e);
            throw new EventHandlerException(e.getMessage(), e);
        }

        Debug.logVerbose("[Processing]: SOAP Event", module);

        // each is a different service call
        for (Object o: bodies) {

            if (o instanceof RPCElement) {
                RPCElement body = (RPCElement) o;
                String serviceName = body.getMethodName();
                List<RPCParam> params = null;
                try {
                    params = body.getParams();
                } catch (Exception e) {
                    sendError(response, e);
                    throw new EventHandlerException(e.getMessage(), e);
                }
                Map<String, Object> serviceContext = FastMap.newInstance();
                for (RPCParam param: params) {
                    if (Debug.verboseOn()) Debug.logVerbose("[Reading Param]: " + param.getName(), module);
                    serviceContext.put(param.getName(), param.getObjectValue());
                }
                try {
                    // verify the service is exported for remote execution and invoke it
                    ModelService model = dispatcher.getDispatchContext().getModelService(serviceName);

                    if (model != null && model.export) {
                        Map<String, Object> result = dispatcher.runSync(serviceName, serviceContext);

                        Debug.logVerbose("[EventHandler] : Service invoked", module);
                        RPCElement resBody = new RPCElement(serviceName + "Response");

                        resBody.setPrefix(body.getPrefix());
                        resBody.setNamespaceURI(body.getNamespaceURI());

                        for (Map.Entry<String, Object> entry: result.entrySet()) {
                            RPCParam par = new RPCParam(entry.getKey(), entry.getValue());

                            resBody.addParam(par);
                        }
                        resEnv.addBodyElement(resBody);
                        resEnv.setEncodingStyle(Constants.URI_LITERAL_ENC);
                    } else {
                        sendError(response, "Requested service not available");
                        throw new EventHandlerException("Service is not exported");
                    }
                } catch (GenericServiceException e) {
                    sendError(response, "Problem processing the service");
                    throw new EventHandlerException(e.getMessage(), e);
                } catch (javax.xml.soap.SOAPException e) {
                    sendError(response, "Problem processing the service");
                    throw new EventHandlerException(e.getMessage(), e);
                }
            }
        }

        // setup the response
        Debug.logVerbose("[EventHandler] : Setting up response message", module);
        msg = new Message(resEnv);
        mctx.setResponseMessage(msg);
        if (msg == null) {
            sendError(response, "No response message available");
            throw new EventHandlerException("No response message available");
        }

        // log the response message
        if (Debug.verboseOn()) {
            try {
                Debug.log("Response Message:\n" + messageToString(msg) + "\n", module);
            } catch (Throwable t) {
            }
        }

        try {
            response.setContentType(msg.getContentType(Constants.DEFAULT_SOAP_VERSION));
            response.setContentLength(Integer.parseInt(Long.toString(msg.getContentLength())));
        } catch (AxisFault e) {
            sendError(response, e);
            throw new EventHandlerException(e.getMessage(), e);
        }

        try {
            msg.writeTo(response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new EventHandlerException("Cannot write to the output stream");
        } catch (SOAPException e) {
            throw new EventHandlerException("Cannot write message to the output stream");
        }

        Debug.logVerbose("[EventHandler] : Message sent to requester", module);

        return null;
    }

    private void sendError(HttpServletResponse res, Object obj) throws EventHandlerException {
        Message msg = new Message(obj);

        try {
            res.setContentType(msg.getContentType(Constants.DEFAULT_SOAP_VERSION));
            res.setContentLength(Integer.parseInt(Long.toString(msg.getContentLength())));
            msg.writeTo(res.getOutputStream());
            res.flushBuffer();
        } catch (Exception e) {
            throw new EventHandlerException(e.getMessage(), e);
        }
    }

    private String getLocationURI(HttpServletRequest request) {
        StringBuilder uri = new StringBuilder();
        uri.append(request.getScheme());
        uri.append("://");
        uri.append(request.getServerName());
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            uri.append(":");
            uri.append(request.getServerPort());
        }
        uri.append(request.getContextPath());
        uri.append(request.getServletPath());

        String reqInfo = RequestHandler.getRequestUri(request.getPathInfo());
        if (!reqInfo.startsWith("/")) {
            reqInfo = "/" + reqInfo;
        }

        uri.append(reqInfo);
        return uri.toString();
    }

    public static String messageToString(Message msg) throws SOAPException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        msg.writeTo(out);
        return out.toString();
    }
}
