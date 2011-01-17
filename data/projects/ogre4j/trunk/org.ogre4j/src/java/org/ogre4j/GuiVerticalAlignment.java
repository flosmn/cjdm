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
public enum GuiVerticalAlignment implements INativeEnum < GuiVerticalAlignment > {
    GVA_TOP(GuiVerticalAlignmentHelper.ENUM_VALUES[0]),
    GVA_CENTER(GuiVerticalAlignmentHelper.ENUM_VALUES[1]),
    GVA_BOTTOM(GuiVerticalAlignmentHelper.ENUM_VALUES[2]);

    private int value;

    GuiVerticalAlignment(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public GuiVerticalAlignment getEnum(int val) {
        return toEnum(val);
    }

    public static final GuiVerticalAlignment toEnum(int retval) {
    if (retval ==GVA_TOP.value)
        return GuiVerticalAlignment.GVA_TOP;
    else if (retval ==GVA_CENTER.value)
        return GuiVerticalAlignment.GVA_CENTER;
    else if (retval ==GVA_BOTTOM.value)
        return GuiVerticalAlignment.GVA_BOTTOM;
    throw new RuntimeException("wrong number in jni call for an enum");
    }
}

class GuiVerticalAlignmentHelper{
static { System.loadLibrary("ogre4j");}

				public static final int[] ENUM_VALUES =
				getEnumValues();

			
				private static native int[] getEnumValues();

			
};

