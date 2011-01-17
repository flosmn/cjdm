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
package org.ofbiz.minilang.method.ifops;

import java.util.List;
import java.util.Map;

import javolution.util.FastList;

import org.ofbiz.minilang.method.MethodOperation;
import org.ofbiz.minilang.method.MethodContext;
import org.ofbiz.minilang.method.ContextAccessor;
import org.ofbiz.minilang.SimpleMethod;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.ObjectType;
import org.ofbiz.base.util.UtilXml;

import org.w3c.dom.Element;

public class IfInstanceOf extends MethodOperation {
    public static final class IfInstanceOfFactory implements Factory<IfInstanceOf> {
        public IfInstanceOf createMethodOperation(Element element, SimpleMethod simpleMethod) {
            return new IfInstanceOf(element, simpleMethod);
        }

        public String getName() {
            return "if-instance-of";
        }
    }

    public static final String module = IfInstanceOf.class.getName();

    protected List<MethodOperation> subOps = FastList.newInstance();
    protected List<MethodOperation> elseSubOps = null;

    protected ContextAccessor<Map<String, ? extends Object>> mapAcsr = null;
    protected ContextAccessor<Object> fieldAcsr = null;
    protected String className = null;

    public IfInstanceOf(Element element, SimpleMethod simpleMethod) {
        super(element, simpleMethod);
        // the schema for this element now just has the "field" attribute, though the old "field-name" and "map-name" pair is still supported
        this.fieldAcsr = new ContextAccessor<Object>(element.getAttribute("field"), element.getAttribute("field-name"));
        this.mapAcsr = new ContextAccessor<Map<String, ? extends Object>>(element.getAttribute("map-name"));
        this.className = element.getAttribute("class");

        SimpleMethod.readOperations(element, subOps, simpleMethod);

        Element elseElement = UtilXml.firstChildElement(element, "else");
        if (elseElement != null) {
            elseSubOps = FastList.newInstance();
            SimpleMethod.readOperations(elseElement, elseSubOps, simpleMethod);
        }
    }

    public boolean exec(MethodContext methodContext) {
        // only run subOps if element is instanceOf
        boolean runSubOps = false;
        Object fieldVal = null;

        if (!mapAcsr.isEmpty()) {
            Map<String, ? extends Object> fromMap = mapAcsr.get(methodContext);
            if (fromMap == null) {
                if (Debug.infoOn()) Debug.logInfo("Map not found with name " + mapAcsr + ", running operations", module);
            } else {
                fieldVal = fieldAcsr.get(fromMap, methodContext);
            }
        } else {
            // no map name, try the env
            fieldVal = fieldAcsr.get(methodContext);
        }

        runSubOps = ObjectType.instanceOf(fieldVal, className);

        if (runSubOps) {
            return SimpleMethod.runSubOps(subOps, methodContext);
        } else {
            if (elseSubOps != null) {
                return SimpleMethod.runSubOps(elseSubOps, methodContext);
            } else {
                return true;
            }
        }
    }

    public List<MethodOperation> getAllSubOps() {
        List<MethodOperation> allSubOps = FastList.newInstance();
        allSubOps.addAll(this.subOps);
        if (this.elseSubOps != null) allSubOps.addAll(this.elseSubOps);
        return allSubOps;
    }

    public String rawString() {
        // TODO: add all attributes and other info
        return "<if-instance-of field-name=\"" + this.fieldAcsr + "\" map-name=\"" + this.mapAcsr + "\"/>";
    }
    public String expandedString(MethodContext methodContext) {
        // TODO: something more than a stub/dummy
        return this.rawString();
    }
}
