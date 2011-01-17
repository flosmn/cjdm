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
public enum GuiHorizontalAlignment implements INativeEnum < GuiHorizontalAlignment > {
    GHA_LEFT(GuiHorizontalAlignmentHelper.ENUM_VALUES[0]),
    GHA_CENTER(GuiHorizontalAlignmentHelper.ENUM_VALUES[1]),
    GHA_RIGHT(GuiHorizontalAlignmentHelper.ENUM_VALUES[2]);

    private int value;

    GuiHorizontalAlignment(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public GuiHorizontalAlignment getEnum(int val) {
        return toEnum(val);
    }

    public static final GuiHorizontalAlignment toEnum(int retval) {
    if (retval ==GHA_LEFT.value)
        return GuiHorizontalAlignment.GHA_LEFT;
    else if (retval ==GHA_CENTER.value)
        return GuiHorizontalAlignment.GHA_CENTER;
    else if (retval ==GHA_RIGHT.value)
        return GuiHorizontalAlignment.GHA_RIGHT;
    throw new RuntimeException("wrong number in jni call for an enum");
    }
}

class GuiHorizontalAlignmentHelper{
static { System.loadLibrary("ogre4j");}

				public static final int[] ENUM_VALUES =
				getEnumValues();

			
				private static native int[] getEnumValues();

			
};

