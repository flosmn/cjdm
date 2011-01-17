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
public enum MaterialScriptSection implements INativeEnum < MaterialScriptSection > {
    MSS_NONE(MaterialScriptSectionHelper.ENUM_VALUES[0]),
    MSS_MATERIAL(MaterialScriptSectionHelper.ENUM_VALUES[1]),
    MSS_TECHNIQUE(MaterialScriptSectionHelper.ENUM_VALUES[2]),
    MSS_PASS(MaterialScriptSectionHelper.ENUM_VALUES[3]),
    MSS_TEXTUREUNIT(MaterialScriptSectionHelper.ENUM_VALUES[4]),
    MSS_PROGRAM_REF(MaterialScriptSectionHelper.ENUM_VALUES[5]),
    MSS_PROGRAM(MaterialScriptSectionHelper.ENUM_VALUES[6]),
    MSS_DEFAULT_PARAMETERS(MaterialScriptSectionHelper.ENUM_VALUES[7]),
    MSS_TEXTURESOURCE(MaterialScriptSectionHelper.ENUM_VALUES[8]);

    private int value;

    MaterialScriptSection(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public MaterialScriptSection getEnum(int val) {
        return toEnum(val);
    }

    public static final MaterialScriptSection toEnum(int retval) {
    if (retval ==MSS_NONE.value)
        return MaterialScriptSection.MSS_NONE;
    else if (retval ==MSS_MATERIAL.value)
        return MaterialScriptSection.MSS_MATERIAL;
    else if (retval ==MSS_TECHNIQUE.value)
        return MaterialScriptSection.MSS_TECHNIQUE;
    else if (retval ==MSS_PASS.value)
        return MaterialScriptSection.MSS_PASS;
    else if (retval ==MSS_TEXTUREUNIT.value)
        return MaterialScriptSection.MSS_TEXTUREUNIT;
    else if (retval ==MSS_PROGRAM_REF.value)
        return MaterialScriptSection.MSS_PROGRAM_REF;
    else if (retval ==MSS_PROGRAM.value)
        return MaterialScriptSection.MSS_PROGRAM;
    else if (retval ==MSS_DEFAULT_PARAMETERS.value)
        return MaterialScriptSection.MSS_DEFAULT_PARAMETERS;
    else if (retval ==MSS_TEXTURESOURCE.value)
        return MaterialScriptSection.MSS_TEXTURESOURCE;
    throw new RuntimeException("wrong number in jni call for an enum");
    }
}

class MaterialScriptSectionHelper{
static { System.loadLibrary("ogre4j");}

				public static final int[] ENUM_VALUES =
				getEnumValues();

			
				private static native int[] getEnumValues();

			
};

