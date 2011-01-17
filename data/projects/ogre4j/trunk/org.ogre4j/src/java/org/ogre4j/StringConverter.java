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
public class StringConverter extends org.xbig.base.NativeObject implements org.ogre4j.IStringConverter {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public StringConverter(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected StringConverter(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public StringConverter(org.xbig.base.WithoutNativeObject val) {
		super(val);
	}

	public void delete() {
		if (this.remote) {
	       throw new RuntimeException("can't dispose object created by native library");
	    }

		if(!this.deleted) {
		    __delete(object.pointer);
		    this.deleted = true;
		   	this.object.pointer = 0;
		}
	}

	public void finalize() {
		if(!this.remote && !this.deleted) {
			delete();
		}
	}
	
			
	private final native void __delete(long _pointer_);	



          /** 
    Converts a Real to a String. **/
    public static String toString(float val, int precision, int width, short fill, org.std.ios.fmtflags flags) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a  to a String. **/
    public static String toString(org.ogre4j.IRadian val, int precision, int width, short fill, org.std.ios.fmtflags flags) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a  to a String. **/
    public static String toString(org.ogre4j.IDegree val, int precision, int width, short fill, org.std.ios.fmtflags flags) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts an int to a String. **/
    public static String toString(int val, int width, short fill, org.std.ios.fmtflags flags) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** **/
    public static String toString__ivHvcvstd_ios_fmtflagsv(int val, int width, short fill, org.std.ios.fmtflags flags) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts an unsigned long to a String. **/
    public static String toString(long val, int width, short fill, org.std.ios.fmtflags flags) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** **/
    public static String toString__lvHvcvstd_ios_fmtflagsv(long val, int width, short fill, org.std.ios.fmtflags flags) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a boolean to a String. **/
    public static String toString(boolean val, boolean yesNo) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a  to a String. **/
    public static String toString(org.ogre4j.IVector2 val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a  to a String. **/
    public static String toString(org.ogre4j.IVector3 val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a  to a String. **/
    public static String toString(org.ogre4j.IVector4 val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a  to a String. **/
    public static String toString(org.ogre4j.IMatrix3 val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a  to a String. **/
    public static String toString(org.ogre4j.IMatrix4 val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a  to a String. **/
    public static String toString(org.ogre4j.IQuaternion val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a  to a String. **/
    public static String toString(org.ogre4j.IColourValue val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a StringVector to a string. **/
    public static String toString(org.ogre4j.IStringVector val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a String to a Real. **/
    public static float parseReal(String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a String to a . **/
    public static void parseAngle(org.ogre4j.IRadian returnValue, String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a String to a whole number. **/
    public static int parseInt(String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a String to a whole number. **/
    public static long parseUnsignedInt(String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a String to a whole number. **/
    public static long parseLong(String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a String to a whole number. **/
    public static long parseUnsignedLong(String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Converts a String to a boolean. **/
    public static boolean parseBool(String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Parses a  out of a String. **/
    public static void parseVector2(org.ogre4j.IVector2 returnValue, String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Parses a  out of a String. **/
    public static void parseVector3(org.ogre4j.IVector3 returnValue, String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Parses a  out of a String. **/
    public static void parseVector4(org.ogre4j.IVector4 returnValue, String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Parses a  out of a String. **/
    public static void parseMatrix3(org.ogre4j.IMatrix3 returnValue, String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Parses a  out of a String. **/
    public static void parseMatrix4(org.ogre4j.IMatrix4 returnValue, String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Parses a  out of a String. **/
    public static void parseQuaternion(org.ogre4j.IQuaternion returnValue, String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Parses a  out of a String. **/
    public static void parseColourValue(org.ogre4j.IColourValue returnValue, String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Pareses a StringVector from a string. **/
    public static void parseStringVector(org.ogre4j.IStringVector returnValue, String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** 
    Checks the String is a valid number value. **/
    public static boolean isNumber(String val) {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



    /** **/
    public StringConverter() {
            throw new UnsupportedOperationException("This type is on ignore list!");
    }



}
