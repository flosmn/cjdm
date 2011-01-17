/**
 *  This source file is generated by XBiG (The XSLT Bindings Generator)
 *  For the latest info, see http://sourceforge.net/projects/xbig/
 * 
 *  Copyright (c) 2004-2008 NetAllied Systems GmbH, Ravensburg. All rights reserved.
 *  Also see acknowledgements in Readme.html
 * 
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU Lesser General Public License as published by the Free Software
 *  Foundation; either version 2 of the License, or (at your option) any later
 *  version.
 * 
 *  This program is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 *  FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public License along with
 *  this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 *  Place - Suite 330, Boston, MA 02111-1307, USA, or go to
 *  http://www.gnu.org/copyleft/lesser.txt.
 * 
 *  Machine generated file
 */

        

package org.ogre4j;


import org.xbig.base.*;
public enum AbstractNodeType implements INativeEnum < AbstractNodeType > {
    ANT_UNKNOWN(AbstractNodeTypeHelper.ENUM_VALUES[0]),
    ANT_ATOM(AbstractNodeTypeHelper.ENUM_VALUES[1]),
    ANT_OBJECT(AbstractNodeTypeHelper.ENUM_VALUES[2]),
    ANT_PROPERTY(AbstractNodeTypeHelper.ENUM_VALUES[3]),
    ANT_IMPORT(AbstractNodeTypeHelper.ENUM_VALUES[4]),
    ANT_VARIABLE_SET(AbstractNodeTypeHelper.ENUM_VALUES[5]),
    ANT_VARIABLE_ACCESS(AbstractNodeTypeHelper.ENUM_VALUES[6]);

    private int value;

    AbstractNodeType(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public AbstractNodeType getEnum(int val) {
        return toEnum(val);
    }

    public static final AbstractNodeType toEnum(int retval) {
    if (retval ==ANT_UNKNOWN.value)
        return AbstractNodeType.ANT_UNKNOWN;
    else if (retval ==ANT_ATOM.value)
        return AbstractNodeType.ANT_ATOM;
    else if (retval ==ANT_OBJECT.value)
        return AbstractNodeType.ANT_OBJECT;
    else if (retval ==ANT_PROPERTY.value)
        return AbstractNodeType.ANT_PROPERTY;
    else if (retval ==ANT_IMPORT.value)
        return AbstractNodeType.ANT_IMPORT;
    else if (retval ==ANT_VARIABLE_SET.value)
        return AbstractNodeType.ANT_VARIABLE_SET;
    else if (retval ==ANT_VARIABLE_ACCESS.value)
        return AbstractNodeType.ANT_VARIABLE_ACCESS;
    throw new RuntimeException("wrong number in jni call for an enum");
    }
}

class AbstractNodeTypeHelper{
static { System.loadLibrary("ogre4j");}

				public static final int[] ENUM_VALUES =
				getEnumValues();

			
				private static native int[] getEnumValues();

			
};

