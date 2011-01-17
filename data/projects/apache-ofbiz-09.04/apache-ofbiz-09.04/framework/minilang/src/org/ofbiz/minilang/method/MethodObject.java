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

import org.w3c.dom.*;
import org.ofbiz.minilang.*;

/**
 * A single Object value to be used as a parameter or whatever
 */
public abstract class MethodObject<T> {

    protected SimpleMethod simpleMethod;

    public MethodObject(Element element, SimpleMethod simpleMethod) {
        this.simpleMethod = simpleMethod;
    }

    /** Get the name for the type of the object */
    public abstract String getTypeName();

    /** Get the Class for the type of the object */
    public abstract Class<T> getTypeClass(ClassLoader loader);

    /** Get the Object value */
    public abstract T getObject(MethodContext methodContext);
}
