/*
 * Copyright 2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ftpserver.ftplet;

import junit.framework.TestCase;

public class DataTypeTest extends TestCase {
    
    public void testParseA() {
        assertSame(DataType.ASCII, DataType.parseArgument('A'));
        assertSame(DataType.ASCII, DataType.parseArgument('a'));
    }

    public void testParseI() {
        assertSame(DataType.BINARY, DataType.parseArgument('I'));
        assertSame(DataType.BINARY, DataType.parseArgument('i'));
    }

    public void testParseUnknown() {
        try{
            DataType.parseArgument('x');
            fail("Exception must be thrown");
        } catch(IllegalArgumentException e) {
            // ignore
        } 
    }

}
