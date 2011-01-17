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
public enum TextureFilterOptions implements INativeEnum < TextureFilterOptions > {
    TFO_NONE(TextureFilterOptionsHelper.ENUM_VALUES[0]),
    TFO_BILINEAR(TextureFilterOptionsHelper.ENUM_VALUES[1]),
    TFO_TRILINEAR(TextureFilterOptionsHelper.ENUM_VALUES[2]),
    TFO_ANISOTROPIC(TextureFilterOptionsHelper.ENUM_VALUES[3]);

    private int value;

    TextureFilterOptions(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public TextureFilterOptions getEnum(int val) {
        return toEnum(val);
    }

    public static final TextureFilterOptions toEnum(int retval) {
    if (retval ==TFO_NONE.value)
        return TextureFilterOptions.TFO_NONE;
    else if (retval ==TFO_BILINEAR.value)
        return TextureFilterOptions.TFO_BILINEAR;
    else if (retval ==TFO_TRILINEAR.value)
        return TextureFilterOptions.TFO_TRILINEAR;
    else if (retval ==TFO_ANISOTROPIC.value)
        return TextureFilterOptions.TFO_ANISOTROPIC;
    throw new RuntimeException("wrong number in jni call for an enum");
    }
}

class TextureFilterOptionsHelper{
static { System.loadLibrary("ogre4j");}

				public static final int[] ENUM_VALUES =
				getEnumValues();

			
				private static native int[] getEnumValues();

			
};

