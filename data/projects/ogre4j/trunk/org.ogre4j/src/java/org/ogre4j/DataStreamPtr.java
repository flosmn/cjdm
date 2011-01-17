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
public class DataStreamPtr extends org.xbig.base.NativeObject implements org.ogre4j.IDataStreamPtr {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public DataStreamPtr(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected DataStreamPtr(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public DataStreamPtr(org.xbig.base.WithoutNativeObject val) {
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
    public DataStreamPtr() {
         super( new org.xbig.base.InstancePointer(__createDataStreamPtr()), false);
    }

    private native static long __createDataStreamPtr();

    /** **/
    public org.ogre4j.IDataStreamPtr operatorAssignment(org.ogre4j.IDataStreamPtr r) {
         return new org.ogre4j.DataStreamPtr(new InstancePointer(_operatorAssignment___Ogre_SharedPtrR(this.object.pointer, r.getInstancePointer().pointer)));
    }

    private native long _operatorAssignment___Ogre_SharedPtrR(long _pointer_, long r);

    /** **/
    public org.ogre4j.IDataStream operatorMultiplication() {
         return new org.ogre4j.DataStream(new InstancePointer(_operatorMultiplication_const(this.object.pointer)));
    }

    private native long _operatorMultiplication_const(long _pointer_);

    /** **/
    public org.ogre4j.IDataStream operatorMemberAccessFromAPointer() {
         return new org.ogre4j.DataStream(new InstancePointer(_operatorMemberAccessFromAPointer_const(this.object.pointer)));
    }

    private native long _operatorMemberAccessFromAPointer_const(long _pointer_);

    /** **/
    public org.ogre4j.IDataStream get() {
         return new org.ogre4j.DataStream(new InstancePointer(_get_const(this.object.pointer)));
    }

    private native long _get_const(long _pointer_);

    /** **/
    public void bind(org.ogre4j.IDataStream rep, org.ogre4j.SharedPtrFreeMethod freeMethod) {
        _bind__Ogre_DataStreamp_Ogre_SharedPtrFreeMethodv(this.object.pointer, rep.getInstancePointer().pointer,  freeMethod.getValue());
    }

    private native void _bind__Ogre_DataStreamp_Ogre_SharedPtrFreeMethodv(long _pointer_, long rep, int freeMethod);

    /** **/
    public boolean unique() {
         return _unique_const(this.object.pointer);
    }

    private native boolean _unique_const(long _pointer_);

    /** **/
    public long useCount() {
         return _useCount_const(this.object.pointer);
    }

    private native long _useCount_const(long _pointer_);

    /** **/
    public LongPointer useCountPointer() {
         return new LongPointer(new InstancePointer(_useCountPointer_const(this.object.pointer)));
    }

    private native long _useCountPointer_const(long _pointer_);

    /** **/
    public org.ogre4j.IDataStream getPointer() {
         return new org.ogre4j.DataStream(new InstancePointer(_getPointer_const(this.object.pointer)));
    }

    private native long _getPointer_const(long _pointer_);

    /** **/
    public org.ogre4j.SharedPtrFreeMethod freeMethod() {
         return org.ogre4j.SharedPtrFreeMethod.toEnum(_freeMethod_const(this.object.pointer));
    }

    private native int _freeMethod_const(long _pointer_);

    /** **/
    public boolean isNull() {
         return _isNull_const(this.object.pointer);
    }

    private native boolean _isNull_const(long _pointer_);

    /** **/
    public void setNull() {
        _setNull(this.object.pointer);
    }

    private native void _setNull(long _pointer_);

}
