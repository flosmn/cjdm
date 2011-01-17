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
package org.ofbiz.minilang.method.eventops;

import java.util.Map;

import javolution.util.FastMap;

import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.collections.FlexibleServletAccessor;
import org.ofbiz.minilang.SimpleMethod;
import org.ofbiz.minilang.method.ContextAccessor;
import org.ofbiz.minilang.method.MethodContext;
import org.ofbiz.minilang.method.MethodOperation;
import org.w3c.dom.Element;

/**
 * Copies a Servlet session attribute to a map field
 */
public class SessionToField extends MethodOperation {
    public static final class SessionToFieldFactory implements Factory<SessionToField> {
        public SessionToField createMethodOperation(Element element, SimpleMethod simpleMethod) {
            return new SessionToField(element, simpleMethod);
        }

        public String getName() {
            return "session-to-field";
        }
    }

    public static final String module = SessionToField.class.getName();

    ContextAccessor<Map<String, Object>> mapAcsr;
    ContextAccessor<Object> fieldAcsr;
    FlexibleServletAccessor<Object> sessionAcsr;
    String defaultVal;

    public SessionToField(Element element, SimpleMethod simpleMethod) {
        super(element, simpleMethod);
        // the schema for this element now just has the "field" attribute, though the old "field-name" and "map-name" pair is still supported
        mapAcsr = new ContextAccessor<Map<String, Object>>(element.getAttribute("map-name"));
        fieldAcsr = new ContextAccessor<Object>(element.getAttribute("field"), element.getAttribute("field-name"));
        sessionAcsr = new FlexibleServletAccessor<Object>(element.getAttribute("session-name"), fieldAcsr.toString());

        defaultVal = element.getAttribute("default");
    }

    public boolean exec(MethodContext methodContext) {
        String defaultVal = methodContext.expandString(this.defaultVal);

        Object fieldVal = null;
        // only run this if it is in an EVENT context
        if (methodContext.getMethodType() == MethodContext.EVENT) {
            fieldVal = sessionAcsr.get(methodContext.getRequest().getSession(), methodContext.getEnvMap());
            if (fieldVal == null) {
                Debug.logWarning("Session attribute value not found with name " + sessionAcsr, module);
            }
        }

        // if fieldVal is null, or is a String and has zero length, use defaultVal
        if (fieldVal == null) {
            fieldVal = defaultVal;
        } else if (fieldVal instanceof String) {
            String strVal = (String) fieldVal;

            if (strVal.length() == 0) {
                fieldVal = defaultVal;
            }
        }

        if (!mapAcsr.isEmpty()) {
            Map<String, Object> fromMap = mapAcsr.get(methodContext);

            if (fromMap == null) {
                Debug.logWarning("Map not found with name " + mapAcsr + " creating a new map", module);
                fromMap = FastMap.newInstance();
                mapAcsr.put(methodContext, fromMap);
            }

            fieldAcsr.put(fromMap, fieldVal, methodContext);
        } else {
            fieldAcsr.put(methodContext, fieldVal);
        }
        return true;
    }

    public String rawString() {
        // TODO: add all attributes and other info
        return "<session-to-field session-name=\"" + this.sessionAcsr + "\" field-name=\"" + this.fieldAcsr + "\" map-name=\"" + this.mapAcsr + "\"/>";
    }
    public String expandedString(MethodContext methodContext) {
        // TODO: something more than a stub/dummy
        return this.rawString();
    }
}
