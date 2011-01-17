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
package org.ofbiz.minilang.method.entityops;

import org.ofbiz.base.util.Debug;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.minilang.SimpleMethod;
import org.ofbiz.minilang.method.ContextAccessor;
import org.ofbiz.minilang.method.MethodContext;
import org.ofbiz.minilang.method.MethodOperation;
import org.w3c.dom.Element;

/**
 * Uses the delegator to create the specified value object entity in the datasource
 */
public class SetCurrentUserLogin extends MethodOperation {
    public static final class SetCurrentUserLoginFactory implements Factory<SetCurrentUserLogin> {
        public SetCurrentUserLogin createMethodOperation(Element element, SimpleMethod simpleMethod) {
            return new SetCurrentUserLogin(element, simpleMethod);
        }

        public String getName() {
            return "set-current-user-login";
        }
    }

    public static final String module = SetCurrentUserLogin.class.getName();

    ContextAccessor<GenericValue> valueAcsr;

    public SetCurrentUserLogin(Element element, SimpleMethod simpleMethod) {
        super(element, simpleMethod);
        valueAcsr = new ContextAccessor<GenericValue>(element.getAttribute("value-field"), element.getAttribute("value-name"));
    }

    public boolean exec(MethodContext methodContext) {
        GenericValue userLogin = valueAcsr.get(methodContext);
        if (userLogin == null) {
            Debug.logWarning("In SetCurrentUserLogin a value was not found with the specified valueName: " + valueAcsr + ", not setting", module);
            return true;
        }

        methodContext.setUserLogin(userLogin, this.simpleMethod.getUserLoginEnvName());
        return true;
    }

    public String rawString() {
        // TODO: something more than the empty tag
        return "<set-current-user-login/>";
    }
    public String expandedString(MethodContext methodContext) {
        // TODO: something more than a stub/dummy
        return this.rawString();
    }
}
