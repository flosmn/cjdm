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
public enum LayerBlendOperationEx implements INativeEnum < LayerBlendOperationEx > {
    LBX_SOURCE1(LayerBlendOperationExHelper.ENUM_VALUES[0]),
    LBX_SOURCE2(LayerBlendOperationExHelper.ENUM_VALUES[1]),
    LBX_MODULATE(LayerBlendOperationExHelper.ENUM_VALUES[2]),
    LBX_MODULATE_X2(LayerBlendOperationExHelper.ENUM_VALUES[3]),
    LBX_MODULATE_X4(LayerBlendOperationExHelper.ENUM_VALUES[4]),
    LBX_ADD(LayerBlendOperationExHelper.ENUM_VALUES[5]),
    LBX_ADD_SIGNED(LayerBlendOperationExHelper.ENUM_VALUES[6]),
    LBX_ADD_SMOOTH(LayerBlendOperationExHelper.ENUM_VALUES[7]),
    LBX_SUBTRACT(LayerBlendOperationExHelper.ENUM_VALUES[8]),
    LBX_BLEND_DIFFUSE_ALPHA(LayerBlendOperationExHelper.ENUM_VALUES[9]),
    LBX_BLEND_TEXTURE_ALPHA(LayerBlendOperationExHelper.ENUM_VALUES[10]),
    LBX_BLEND_CURRENT_ALPHA(LayerBlendOperationExHelper.ENUM_VALUES[11]),
    LBX_BLEND_MANUAL(LayerBlendOperationExHelper.ENUM_VALUES[12]),
    LBX_DOTPRODUCT(LayerBlendOperationExHelper.ENUM_VALUES[13]),
    LBX_BLEND_DIFFUSE_COLOUR(LayerBlendOperationExHelper.ENUM_VALUES[14]);

    private int value;

    LayerBlendOperationEx(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public LayerBlendOperationEx getEnum(int val) {
        return toEnum(val);
    }

    public static final LayerBlendOperationEx toEnum(int retval) {
    if (retval ==LBX_SOURCE1.value)
        return LayerBlendOperationEx.LBX_SOURCE1;
    else if (retval ==LBX_SOURCE2.value)
        return LayerBlendOperationEx.LBX_SOURCE2;
    else if (retval ==LBX_MODULATE.value)
        return LayerBlendOperationEx.LBX_MODULATE;
    else if (retval ==LBX_MODULATE_X2.value)
        return LayerBlendOperationEx.LBX_MODULATE_X2;
    else if (retval ==LBX_MODULATE_X4.value)
        return LayerBlendOperationEx.LBX_MODULATE_X4;
    else if (retval ==LBX_ADD.value)
        return LayerBlendOperationEx.LBX_ADD;
    else if (retval ==LBX_ADD_SIGNED.value)
        return LayerBlendOperationEx.LBX_ADD_SIGNED;
    else if (retval ==LBX_ADD_SMOOTH.value)
        return LayerBlendOperationEx.LBX_ADD_SMOOTH;
    else if (retval ==LBX_SUBTRACT.value)
        return LayerBlendOperationEx.LBX_SUBTRACT;
    else if (retval ==LBX_BLEND_DIFFUSE_ALPHA.value)
        return LayerBlendOperationEx.LBX_BLEND_DIFFUSE_ALPHA;
    else if (retval ==LBX_BLEND_TEXTURE_ALPHA.value)
        return LayerBlendOperationEx.LBX_BLEND_TEXTURE_ALPHA;
    else if (retval ==LBX_BLEND_CURRENT_ALPHA.value)
        return LayerBlendOperationEx.LBX_BLEND_CURRENT_ALPHA;
    else if (retval ==LBX_BLEND_MANUAL.value)
        return LayerBlendOperationEx.LBX_BLEND_MANUAL;
    else if (retval ==LBX_DOTPRODUCT.value)
        return LayerBlendOperationEx.LBX_DOTPRODUCT;
    else if (retval ==LBX_BLEND_DIFFUSE_COLOUR.value)
        return LayerBlendOperationEx.LBX_BLEND_DIFFUSE_COLOUR;
    throw new RuntimeException("wrong number in jni call for an enum");
    }
}

class LayerBlendOperationExHelper{
static { System.loadLibrary("ogre4j");}

				public static final int[] ENUM_VALUES =
				getEnumValues();

			
				private static native int[] getEnumValues();

			
};

