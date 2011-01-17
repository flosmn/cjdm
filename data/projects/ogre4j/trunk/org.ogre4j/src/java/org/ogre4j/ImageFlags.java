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
public enum ImageFlags implements INativeEnum < ImageFlags > {
    IF_COMPRESSED(ImageFlagsHelper.ENUM_VALUES[0]),
    IF_CUBEMAP(ImageFlagsHelper.ENUM_VALUES[1]),
    IF_3D_TEXTURE(ImageFlagsHelper.ENUM_VALUES[2]);

    private int value;

    ImageFlags(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public ImageFlags getEnum(int val) {
        return toEnum(val);
    }

    public static final ImageFlags toEnum(int retval) {
    if (retval ==IF_COMPRESSED.value)
        return ImageFlags.IF_COMPRESSED;
    else if (retval ==IF_CUBEMAP.value)
        return ImageFlags.IF_CUBEMAP;
    else if (retval ==IF_3D_TEXTURE.value)
        return ImageFlags.IF_3D_TEXTURE;
    throw new RuntimeException("wrong number in jni call for an enum");
    }
}

class ImageFlagsHelper{
static { System.loadLibrary("ogre4j");}

				public static final int[] ENUM_VALUES =
				getEnumValues();

			
				private static native int[] getEnumValues();

			
};

