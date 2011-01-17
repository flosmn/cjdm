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
public class BillboardSetFactory extends org.xbig.base.NativeObject implements org.ogre4j.IBillboardSetFactory {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public BillboardSetFactory(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected BillboardSetFactory(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public BillboardSetFactory(org.xbig.base.WithoutNativeObject val) {
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
    public BillboardSetFactory() {
         super( new org.xbig.base.InstancePointer(__createBillboardSetFactory()), false);
    }

    private native static long __createBillboardSetFactory();

    /** **/
    public String getType() {
         return _getType_const(this.object.pointer);
    }

    private native String _getType_const(long _pointer_);

    /** **/
    public void destroyInstance(org.ogre4j.IMovableObject obj) {
        _destroyInstance__MovableObjectp(this.object.pointer, obj.getInstancePointer().pointer);
    }

    private native void _destroyInstance__MovableObjectp(long _pointer_, long obj);

    /** 
    Create a new instance of the object. **/
    public org.ogre4j.IMovableObject createInstance(String name, org.ogre4j.ISceneManager manager, org.ogre4j.INameValuePairList params) {
         return new org.ogre4j.MovableObject(new InstancePointer(_createInstance__StringRSceneManagerpNameValuePairListP(this.object.pointer, name,  manager.getInstancePointer().pointer,  params.getInstancePointer().pointer)));
    }

    private native long _createInstance__StringRSceneManagerpNameValuePairListP(long _pointer_, String name, long manager, long params);

    /** 
    Does this factory require the allocation of a 'type flag', used to selectively include / exclude this type from scene queries? **/
    public boolean requestTypeFlags() {
         return _requestTypeFlags_const(this.object.pointer);
    }

    private native boolean _requestTypeFlags_const(long _pointer_);

    /** 
    Notify this factory of the type mask to apply. **/
    public void _notifyTypeFlags(long flag) {
        __notifyTypeFlags__Lv(this.object.pointer, flag);
    }

    private native void __notifyTypeFlags__Lv(long _pointer_, long flag);

    /** 
    Gets the type flag for this factory. **/
    public long getTypeFlags() {
         return _getTypeFlags_const(this.object.pointer);
    }

    private native long _getTypeFlags_const(long _pointer_);

    /** **/
    public static String getFACTORY_TYPE_NAME() {
         return _getFACTORY_TYPE_NAME();
    }

    private native static String _getFACTORY_TYPE_NAME();

    /** **/
    public static void setFACTORY_TYPE_NAME(String _jni_value_) {
        _setFACTORY_TYPE_NAME( _jni_value_);
    }

    private native static void _setFACTORY_TYPE_NAME(String _jni_value_);

}
