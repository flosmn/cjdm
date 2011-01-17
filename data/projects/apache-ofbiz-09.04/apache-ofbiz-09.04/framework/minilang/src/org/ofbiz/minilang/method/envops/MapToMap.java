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

import javolution.util.FastMap;

import org.w3c.dom.*;
import org.ofbiz.base.util.*;
import org.ofbiz.minilang.*;
import org.ofbiz.minilang.method.*;

/**
 * Copies a map field to a map field
 */
public class MapToMap extends MethodOperation {
    public static final class MapToMapFactory implements Factory<MapToMap> {
        public MapToMap createMethodOperation(Element element, SimpleMethod simpleMethod) {
            return new MapToMap(element, simpleMethod);
        }

        public String getName() {
            return "map-to-map";
        }
    }

    public static final String module = MapToMap.class.getName();

    ContextAccessor<Map<String, Object>> mapAcsr;
    ContextAccessor<Map<String, Object>> toMapAcsr;

    public MapToMap(Element element, SimpleMethod simpleMethod) {
        super(element, simpleMethod);
        mapAcsr = new ContextAccessor<Map<String, Object>>(element.getAttribute("map"), element.getAttribute("map-name"));
        toMapAcsr = new ContextAccessor<Map<String, Object>>(element.getAttribute("to-map"), element.getAttribute("to-map-name"));
    }

    public boolean exec(MethodContext methodContext) {
        Map<String, Object> fromMap = null;
        if (!mapAcsr.isEmpty()) {
            fromMap = mapAcsr.get(methodContext);

            if (fromMap == null) {
                if (Debug.infoOn()) Debug.logInfo("Map not found with name " + mapAcsr + ", not copying from this map", module);
                fromMap = FastMap.newInstance();
                mapAcsr.put(methodContext, fromMap);
            }
        }

        if (!toMapAcsr.isEmpty()) {
            Map<String, Object> toMap = toMapAcsr.get(methodContext);
            if (toMap == null) {
                if (Debug.verboseOn()) Debug.logVerbose("Map not found with name " + toMapAcsr + ", creating new map", module);
                toMap = FastMap.newInstance();
                toMapAcsr.put(methodContext, toMap);
            }

            toMap.putAll(fromMap);
        } else {
            methodContext.putAllEnv(fromMap);
        }
        return true;
    }

    public String rawString() {
        // TODO: something more than the empty tag
        return "<map-to-map/>";
    }
    public String expandedString(MethodContext methodContext) {
        // TODO: something more than a stub/dummy
        return this.rawString();
    }
}
