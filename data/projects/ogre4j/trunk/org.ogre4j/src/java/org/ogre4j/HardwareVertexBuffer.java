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
public class HardwareVertexBuffer extends org.xbig.base.NativeObject implements org.ogre4j.IHardwareVertexBuffer {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public HardwareVertexBuffer(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected HardwareVertexBuffer(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public HardwareVertexBuffer(org.xbig.base.WithoutNativeObject val) {
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
    public int getVertexSize() {
         return _getVertexSize_const(this.object.pointer);
    }

    private native int _getVertexSize_const(long _pointer_);

    /** **/
    public int getNumVertices() {
         return _getNumVertices_const(this.object.pointer);
    }

    private native int _getNumVertices_const(long _pointer_);

    /** 
    Lock the buffer for (potentially) reading / writing. **/
    public VoidPointer lock(int offset, int length, org.ogre4j.HardwareBuffer.LockOptions options) {
         return new VoidPointer(new InstancePointer(_lock__ivivLockOptionsv(this.object.pointer, offset,  length,  options.getValue())));
    }

    private native long _lock__ivivLockOptionsv(long _pointer_, int offset, int length, int options);

    /** 
    Lock the entire buffer for (potentially) reading / writing. **/
    public VoidPointer lock(org.ogre4j.HardwareBuffer.LockOptions options) {
         return new VoidPointer(new InstancePointer(_lock__LockOptionsv(this.object.pointer, options.getValue())));
    }

    private native long _lock__LockOptionsv(long _pointer_, int options);

    /** 
    Releases the lock on this buffer. **/
    public void unlock() {
        _unlock(this.object.pointer);
    }

    private native void _unlock(long _pointer_);

    /** 
    Reads data from the buffer and places it in the memory pointed to by pDest. **/
    public void readData(int offset, int length, VoidPointer pDest) {
        _readData__ivivvp(this.object.pointer, offset,  length,  pDest.object.pointer);
    }

    private native void _readData__ivivvp(long _pointer_, int offset, int length, long pDest);

    /** 
    Writes data to the buffer from an area of system memory; note that you must ensure that your buffer is big enough. **/
    public void writeData(int offset, int length, VoidPointer pSource, boolean discardWholeBuffer) {
        _writeData__ivivvPbv(this.object.pointer, offset,  length,  pSource.object.pointer,  discardWholeBuffer);
    }

    private native void _writeData__ivivvPbv(long _pointer_, int offset, int length, long pSource, boolean discardWholeBuffer);

    /** 
    Copy data from another buffer into this one. **/
    public void copyData(org.ogre4j.IHardwareBuffer srcBuffer, int srcOffset, int dstOffset, int length, boolean discardWholeBuffer) {
        _copyData__HardwareBufferrivivivbv(this.object.pointer, srcBuffer.getInstancePointer().pointer,  srcOffset,  dstOffset,  length,  discardWholeBuffer);
    }

    private native void _copyData__HardwareBufferrivivivbv(long _pointer_, long srcBuffer, int srcOffset, int dstOffset, int length, boolean discardWholeBuffer);

    /** **/
    public void _updateFromShadow() {
        __updateFromShadow(this.object.pointer);
    }

    private native void __updateFromShadow(long _pointer_);

    /** **/
    public int getSizeInBytes() {
         return _getSizeInBytes_const(this.object.pointer);
    }

    private native int _getSizeInBytes_const(long _pointer_);

    /** **/
    public org.ogre4j.HardwareBuffer.Usage getUsage() {
         return org.ogre4j.HardwareBuffer.Usage.toEnum(_getUsage_const(this.object.pointer));
    }

    private native int _getUsage_const(long _pointer_);

    /** **/
    public boolean isSystemMemory() {
         return _isSystemMemory_const(this.object.pointer);
    }

    private native boolean _isSystemMemory_const(long _pointer_);

    /** **/
    public boolean hasShadowBuffer() {
         return _hasShadowBuffer_const(this.object.pointer);
    }

    private native boolean _hasShadowBuffer_const(long _pointer_);

    /** **/
    public boolean isLocked() {
         return _isLocked_const(this.object.pointer);
    }

    private native boolean _isLocked_const(long _pointer_);

    /** **/
    public void suppressHardwareUpdate(boolean suppress) {
        _suppressHardwareUpdate__bv(this.object.pointer, suppress);
    }

    private native void _suppressHardwareUpdate__bv(long _pointer_, boolean suppress);

}
