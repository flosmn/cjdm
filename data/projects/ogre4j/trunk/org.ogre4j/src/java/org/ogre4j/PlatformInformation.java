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
public class PlatformInformation extends org.xbig.base.NativeObject implements org.ogre4j.IPlatformInformation {
static { System.loadLibrary("ogre4j");}
public enum CpuFeatures implements INativeEnum < CpuFeatures > {
    CPU_FEATURE_NONE(CpuFeaturesHelper.ENUM_VALUES[0]);

    private int value;

    CpuFeatures(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public CpuFeatures getEnum(int val) {
        return toEnum(val);
    }

    public static final CpuFeatures toEnum(int retval) {
    if (retval ==CPU_FEATURE_NONE.value)
        return CpuFeatures.CPU_FEATURE_NONE;
    throw new RuntimeException("wrong number in jni call for an enum");
    }
}

static class CpuFeaturesHelper{

				public static final int[] ENUM_VALUES =
				getEnumValues();

			
				private static native int[] getEnumValues();

			
};

 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public PlatformInformation(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected PlatformInformation(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public PlatformInformation(org.xbig.base.WithoutNativeObject val) {
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
    Gets a string of the CPU identifier. **/
    public static String getCpuIdentifier() {
         return _getCpuIdentifier();
    }

    private native static String _getCpuIdentifier();

    /** 
    Gets a or-masked of enum CpuFeatures that are supported by the CPU. **/
    public static long getCpuFeatures() {
         return _getCpuFeatures();
    }

    private native static long _getCpuFeatures();

    /** 
    Gets whether a specific feature is supported by the CPU. **/
    public static boolean hasCpuFeature(org.ogre4j.PlatformInformation.CpuFeatures feature) {
         return _hasCpuFeature__CpuFeaturesv( feature.getValue());
    }

    private native static boolean _hasCpuFeature__CpuFeaturesv(int feature);

    /** 
    Write the CPU information to the passed in **/
    public static void log(org.ogre4j.ILog pLog) {
        _log__Logp( pLog.getInstancePointer().pointer);
    }

    private native static void _log__Logp(long pLog);

    /** **/
    public PlatformInformation() {
         super( new org.xbig.base.InstancePointer(__createPlatformInformation()), false);
    }

    private native static long __createPlatformInformation();

}
