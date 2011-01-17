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
package org.ofbiz.minilang.method;

import java.util.*;

import org.w3c.dom.*;
import org.ofbiz.base.util.*;
import org.ofbiz.minilang.*;

/**
 * A type of MethodObject that represents an Object value in a certain location
 */
public class FieldObject<T> extends MethodObject<T> {

    public static final String module = FieldObject.class.getName();

    ContextAccessor<T> fieldAcsr;
    ContextAccessor<Map<String, ? extends Object>> mapAcsr;
    String type;

    public FieldObject(Element element, SimpleMethod simpleMethod) {
        super(element, simpleMethod);
        // the schema for this element now just has the "field" attribute, though the old "field-name" and "map-name" pair is still supported
        fieldAcsr = new ContextAccessor<T>(element.getAttribute("field"), element.getAttribute("field-name"));
        mapAcsr = new ContextAccessor<Map<String, ? extends Object>>(element.getAttribute("map-name"));

        type = element.getAttribute("type");
        if (UtilValidate.isEmpty(type)) {
            type = "String";
        }
    }

    /** Get the name for the type of the object */
    public String getTypeName() {
        return type;
    }

    public Class<T> getTypeClass(ClassLoader loader) {
        try {
            return UtilGenerics.cast(ObjectType.loadClass(type, loader));
        } catch (ClassNotFoundException e) {
            Debug.logError(e, "Could not find class for type: " + type, module);
            return null;
        }
    }

    public T getObject(MethodContext methodContext) {
        T fieldVal = null;

        if (!mapAcsr.isEmpty()) {
            Map<String, ? extends Object> fromMap = mapAcsr.get(methodContext);
            if (fromMap == null) {
                Debug.logWarning("Map not found with name " + mapAcsr + ", not getting Object value, returning null.", module);
                return null;
            }
            fieldVal = fieldAcsr.get(fromMap, methodContext);
        } else {
            // no map name, try the env
            fieldVal = fieldAcsr.get(methodContext);
        }

        if (fieldVal == null) {
            if (Debug.infoOn()) Debug.logInfo("Field value not found with name " + fieldAcsr + " in Map with name " + mapAcsr + ", not getting Object value, returning null.", module);
            return null;
        }

        return fieldVal;
    }
}
