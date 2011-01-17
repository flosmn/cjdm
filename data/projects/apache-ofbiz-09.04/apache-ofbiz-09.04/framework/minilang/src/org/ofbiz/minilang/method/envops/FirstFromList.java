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
package org.ofbiz.minilang.method.envops;

import java.util.*;

import org.w3c.dom.*;
import org.ofbiz.base.util.*;
import org.ofbiz.minilang.*;
import org.ofbiz.minilang.method.*;

/**
 * Get the first entry from the list
 */
public class FirstFromList extends MethodOperation {
    public static final class FirstFromListFactory implements Factory<FirstFromList> {
        public FirstFromList createMethodOperation(Element element, SimpleMethod simpleMethod) {
            return new FirstFromList(element, simpleMethod);
        }

        public String getName() {
            return "first-from-list";
        }
    }

    public static final String module = FirstFromList.class.getName();

    ContextAccessor<Object> entryAcsr;
    ContextAccessor<List<? extends Object>> listAcsr;

    public FirstFromList(Element element, SimpleMethod simpleMethod) {
        super(element, simpleMethod);
        this.entryAcsr = new ContextAccessor<Object>(element.getAttribute("entry"), element.getAttribute("entry-name"));
        this.listAcsr = new ContextAccessor<List<? extends Object>>(element.getAttribute("list"), element.getAttribute("list-name"));
    }

    public boolean exec(MethodContext methodContext) {
        if (listAcsr.isEmpty()) {
            Debug.logWarning("No list-name specified in iterate tag, doing nothing", module);
            return true;
        }

        List<? extends Object> theList = listAcsr.get(methodContext);

        if (UtilValidate.isEmpty(theList)) {
            entryAcsr.put(methodContext, null);
            return true;
        }

        entryAcsr.put(methodContext, theList.get(0));
        return true;
    }

    public String rawString() {
        return "<first-from-list list-name=\"" + this.listAcsr + "\" entry-name=\"" + this.entryAcsr + "\"/>";
    }
    public String expandedString(MethodContext methodContext) {
        // TODO: something more than a stub/dummy
        return this.rawString();
    }
}
