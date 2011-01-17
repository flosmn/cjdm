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
public class TempBlendedBufferInfo extends org.xbig.base.NativeObject implements org.ogre4j.ITempBlendedBufferInfo {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public TempBlendedBufferInfo(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected TempBlendedBufferInfo(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public TempBlendedBufferInfo(org.xbig.base.WithoutNativeObject val) {
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



          /** **/
    public void extractFrom(org.ogre4j.IVertexData sourceData) {
        _extractFrom__VertexDataP(this.object.pointer, sourceData.getInstancePointer().pointer);
    }

    private native void _extractFrom__VertexDataP(long _pointer_, long sourceData);

    /** **/
    public void checkoutTempCopies(boolean positions, boolean normals) {
        _checkoutTempCopies__bvbv(this.object.pointer, positions,  normals);
    }

    private native void _checkoutTempCopies__bvbv(long _pointer_, boolean positions, boolean normals);

    /** **/
    public void bindTempCopies(org.ogre4j.IVertexData targetData, boolean suppressHardwareUpload) {
        _bindTempCopies__VertexDatapbv(this.object.pointer, targetData.getInstancePointer().pointer,  suppressHardwareUpload);
    }

    private native void _bindTempCopies__VertexDatapbv(long _pointer_, long targetData, boolean suppressHardwareUpload);

    /** 
    Overridden member from . **/
    public void licenseExpired(org.ogre4j.IHardwareBuffer buffer) {
        _licenseExpired__HardwareBufferp(this.object.pointer, buffer.getInstancePointer().pointer);
    }

    private native void _licenseExpired__HardwareBufferp(long _pointer_, long buffer);

    /** 
    Detect currently have buffer copies checked out and touch it **/
    public boolean buffersCheckedOut(boolean positions, boolean normals) {
         return _buffersCheckedOut__bvbv_const(this.object.pointer, positions,  normals);
    }

    private native boolean _buffersCheckedOut__bvbv_const(long _pointer_, boolean positions, boolean normals);

    /** **/
    public TempBlendedBufferInfo() {
         super( new org.xbig.base.InstancePointer(__createTempBlendedBufferInfo()), false);
    }

    private native static long __createTempBlendedBufferInfo();

}
