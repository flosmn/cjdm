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

/**
 * Type safe enum for describing the structure
 *
 * @author <a href="mailto:niklas@protocol7.com">Niklas Gustavsson</a>
 */
public class Structure {

    /**
     * File structure
     */
    public static final Structure FILE = new Structure("F");
    
    /**
     * Parses the argument value from the STRU command into
     * the type safe class
     * 
     * @param argument The argument value from the STRU command.
     *     Not case sensitive
     * @return The appropriate structure
     * @throws IllegalArgumentException If the structure is unknown
     */

    public static Structure parseArgument(char argument) {
        switch(argument) {
        case 'F':
        case 'f':
            return FILE;
        default:
            throw new IllegalArgumentException("Unknown structure: " + argument);
        }
    }
    
    private String structure;

    /**
     * Private constructor.
     */
    private Structure(String structure) {
        this.structure = structure;
    }

    /**
     * String representation of the structure.
     */
    public String toString() {
        return structure;
    }
}
