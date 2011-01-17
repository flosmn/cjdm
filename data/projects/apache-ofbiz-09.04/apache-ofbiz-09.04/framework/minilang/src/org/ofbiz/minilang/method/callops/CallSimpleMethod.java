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
package org.ofbiz.minilang.method.callops;

import java.util.Map;

import org.ofbiz.base.util.Debug;
import org.ofbiz.minilang.MiniLangException;
import org.ofbiz.minilang.SimpleMethod;
import org.ofbiz.minilang.method.MethodContext;
import org.ofbiz.minilang.method.MethodOperation;
import org.w3c.dom.Element;

/**
 * An operation that calls a simple method in the same, or from another, file
 */
public class CallSimpleMethod extends MethodOperation {
    public static final class CallSimpleMethodFactory implements Factory<CallSimpleMethod> {
        public CallSimpleMethod createMethodOperation(Element element, SimpleMethod simpleMethod) {
            return new CallSimpleMethod(element, simpleMethod);
        }

        public String getName() {
            return "call-simple-method";
        }
    }

    public static final String module = CallSimpleMethod.class.getName();

    String xmlResource;
    String methodName;

    public CallSimpleMethod(Element element, SimpleMethod simpleMethod) {
        super(element, simpleMethod);
        this.methodName = element.getAttribute("method-name");
        this.xmlResource = element.getAttribute("xml-resource");
    }

    public String getXmlResource() {
        return this.xmlResource;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public boolean exec(MethodContext methodContext) {
        if (this.methodName != null && this.methodName.length() > 0) {
            String methodName = methodContext.expandString(this.methodName);
            String xmlResource = methodContext.expandString(this.xmlResource);

            SimpleMethod simpleMethodToCall = null;
            try {
                simpleMethodToCall = getSimpleMethodToCall(methodContext.getLoader());
            } catch (MiniLangException e) {
                String errMsg = "ERROR: Could not complete the " + simpleMethod.getShortDescription() + " process [error getting methods from resource: " + e.getMessage() + "]";
                Debug.logError(e, errMsg, module);
                methodContext.setErrorReturn(errMsg, simpleMethod);
                return false;
            }

            if (simpleMethodToCall == null) {
                String errMsg = "ERROR: Could not complete the " + simpleMethod.getShortDescription() + " process, could not find SimpleMethod " + methodName + " in XML document in resource: " + xmlResource;
                methodContext.setErrorReturn(errMsg, simpleMethod);
                return false;
            }

            String returnVal = simpleMethodToCall.exec(methodContext);
            if (Debug.verboseOn()) Debug.logVerbose("Called inline simple-method named [" + methodName + "] in resource [" + xmlResource + "], returnVal is [" + returnVal + "]", module);

            if (returnVal != null && returnVal.equals(simpleMethodToCall.getDefaultErrorCode())) {
                // in this case just set the error code just in case it hasn't already been set, the error messages will already be in place...
                if (methodContext.getMethodType() == MethodContext.EVENT) {
                    methodContext.putEnv(simpleMethod.getEventResponseCodeName(), simpleMethod.getDefaultErrorCode());
                } else if (methodContext.getMethodType() == MethodContext.SERVICE) {
                    methodContext.putEnv(simpleMethod.getServiceResponseMessageName(), simpleMethod.getDefaultErrorCode());
                }
                return false;
            }

            // if the response code/message is error, if so show the error and return false
            if (methodContext.getMethodType() == MethodContext.EVENT) {
                String responseCode = (String) methodContext.getEnv(simpleMethod.getEventResponseCodeName());
                if (responseCode != null && responseCode.equals(simpleMethod.getDefaultErrorCode())) {
                    Debug.logWarning("Got error [" + responseCode + "] calling inline simple-method named [" + methodName + "] in resource [" + xmlResource + "], message is " + methodContext.getEnv(simpleMethod.getEventErrorMessageName()) , module);
                    return false;
                }
            } else if (methodContext.getMethodType() == MethodContext.SERVICE) {
                String resonseMessage = (String) methodContext.getEnv(simpleMethod.getServiceResponseMessageName());
                if (resonseMessage != null && resonseMessage.equals(simpleMethod.getDefaultErrorCode())) {
                    Debug.logWarning("Got error [" + resonseMessage + "] calling inline simple-method named [" + methodName + "] in resource [" + xmlResource + "], message is " + methodContext.getEnv(simpleMethod.getServiceErrorMessageName()) + ", and the error message list is: " + methodContext.getEnv(simpleMethod.getServiceErrorMessageListName()), module);
                    return false;
                }
            }
        } else {
            String errMsg = "ERROR in call-simple-method: methodName was missing; not running simpleMethod";
            Debug.logError(errMsg, module);
            methodContext.setErrorReturn(errMsg, simpleMethod);
            return false;
        }

        return true;
    }

    public SimpleMethod getSimpleMethodToCall(ClassLoader loader) throws MiniLangException {
        SimpleMethod simpleMethodToCall = null;
        if (xmlResource == null || xmlResource.length() == 0) {
            simpleMethodToCall = this.simpleMethod.getSimpleMethodInSameFile(methodName);
        } else {
            Map<String, SimpleMethod> simpleMethods = SimpleMethod.getSimpleMethods(xmlResource, loader);
            simpleMethodToCall = simpleMethods.get(methodName);
        }
        return simpleMethodToCall;
    }

    public String rawString() {
        return "<call-simple-method xml-resource=\"" + this.xmlResource + "\" method-name=\"" + this.methodName + "\" />";
    }
    public String expandedString(MethodContext methodContext) {
        // TODO: something more than a stub/dummy
        return this.rawString();
    }
}
