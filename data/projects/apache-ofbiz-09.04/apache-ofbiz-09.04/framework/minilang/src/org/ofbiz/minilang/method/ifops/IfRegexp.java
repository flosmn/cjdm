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

import java.util.*;

import javolution.util.FastList;

import org.apache.oro.text.regex.*;
import org.w3c.dom.*;

import org.ofbiz.base.util.*;
import org.ofbiz.base.util.string.FlexibleStringExpander;
import org.ofbiz.minilang.*;
import org.ofbiz.minilang.method.*;

/**
 * Iff the specified field complies with the pattern specified by the regular expression, process sub-operations
 */
public class IfRegexp extends MethodOperation {
    public static final class IfRegexpFactory implements Factory<IfRegexp> {
        public IfRegexp createMethodOperation(Element element, SimpleMethod simpleMethod) {
            return new IfRegexp(element, simpleMethod);
        }

        public String getName() {
            return "if-regexp";
        }
    }

    public static final String module = IfRegexp.class.getName();

    static PatternMatcher matcher = new Perl5Matcher();
    static PatternCompiler compiler = new Perl5Compiler();

    List<MethodOperation> subOps = FastList.newInstance();
    List<MethodOperation> elseSubOps = null;

    ContextAccessor<Map<String, ? extends Object>> mapAcsr;
    ContextAccessor<Object> fieldAcsr;

    FlexibleStringExpander exprExdr;

    public IfRegexp(Element element, SimpleMethod simpleMethod) {
        super(element, simpleMethod);
        // the schema for this element now just has the "field" attribute, though the old "field-name" and "map-name" pair is still supported
        this.fieldAcsr = new ContextAccessor<Object>(element.getAttribute("field"), element.getAttribute("field-name"));
        this.mapAcsr = new ContextAccessor<Map<String, ? extends Object>>(element.getAttribute("map-name"));

        this.exprExdr = FlexibleStringExpander.getInstance(element.getAttribute("expr"));

        SimpleMethod.readOperations(element, subOps, simpleMethod);

        Element elseElement = UtilXml.firstChildElement(element, "else");

        if (elseElement != null) {
            elseSubOps = FastList.newInstance();
            SimpleMethod.readOperations(elseElement, elseSubOps, simpleMethod);
        }
    }

    public boolean exec(MethodContext methodContext) {
        // if conditions fails, always return true; if a sub-op returns false
        // return false and stop, otherwise return true

        String fieldString = null;
        Object fieldVal = null;

        if (!mapAcsr.isEmpty()) {
            Map<String, ? extends Object> fromMap = mapAcsr.get(methodContext);
            if (fromMap == null) {
                if (Debug.infoOn()) Debug.logInfo("Map not found with name " + mapAcsr + ", using empty string for comparison", module);
            } else {
                fieldVal = fieldAcsr.get(fromMap, methodContext);
            }
        } else {
            // no map name, try the env
            fieldVal = fieldAcsr.get(methodContext);
        }

        if (fieldVal != null) {
            try {
                fieldString = (String) ObjectType.simpleTypeConvert(fieldVal, "String", null, methodContext.getTimeZone(), methodContext.getLocale(), true);
            } catch (GeneralException e) {
                Debug.logError(e, "Could not convert object to String, using empty String", module);
            }
        }
        // always use an empty string by default
        if (fieldString == null) fieldString = "";

        Pattern pattern = null;
        try {
            pattern = compiler.compile(methodContext.expandString(this.exprExdr));
        } catch (MalformedPatternException e) {
            Debug.logError(e, "Regular Expression [" + this.exprExdr + "] is mal-formed: " + e.toString(), module);
        }

        if (matcher.matches(fieldString, pattern)) {
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
        return "<if-regexp field-name=\"" + this.fieldAcsr + "\" map-name=\"" + this.mapAcsr + "\"/>";
    }
    public String expandedString(MethodContext methodContext) {
        // TODO: something more than a stub/dummy
        return this.rawString();
    }
}
