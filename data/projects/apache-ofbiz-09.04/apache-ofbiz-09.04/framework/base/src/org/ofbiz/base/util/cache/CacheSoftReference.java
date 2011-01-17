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
package org.ofbiz.base.util.cache;

import java.lang.ref.SoftReference;
import java.lang.ref.ReferenceQueue;
import java.io.Serializable;

import org.ofbiz.base.util.Debug;

public class CacheSoftReference<V> extends SoftReference<V> implements Serializable {

    public static final String module = CacheSoftReference.class.getName();

    public CacheSoftReference(V o) {
        super(o);
    }

    public CacheSoftReference(V o, ReferenceQueue<? super V> referenceQueue) {
        super(o, referenceQueue);
    }

    public void clear() {
        if (Debug.verboseOn()) {
            Debug.logVerbose(new Exception("UtilCache.CacheSoftRef.clear()"), "Clearing UtilCache SoftReference - " + get(), module);
        }
        super.clear();
    }

    public void finalize() throws Throwable {
        if (Debug.verboseOn()) {
            Debug.logVerbose(new Exception("UtilCache.CacheSoftRef.finalize()"), "Finalize UtilCache SoftReference - " + get(), module);
        }
        super.finalize();
    }
}
