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
public class ConcreteNode extends org.xbig.base.NativeObject implements org.ogre4j.IConcreteNode {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public ConcreteNode(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected ConcreteNode(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public ConcreteNode(org.xbig.base.WithoutNativeObject val) {
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
    public ConcreteNode() {
         super( new org.xbig.base.InstancePointer(__createConcreteNode()), false);
    }

    private native static long __createConcreteNode();

    /** **/
    public String gettoken() {
         return _gettoken(this.object.pointer);
    }

    private native String _gettoken(long _pointer_);

    /** **/
    public void settoken(String _jni_value_) {
        _settoken(this.object.pointer, _jni_value_);
    }

    private native void _settoken(long _pointer_, String _jni_value_);

    /** **/
    public String getfile() {
         return _getfile(this.object.pointer);
    }

    private native String _getfile(long _pointer_);

    /** **/
    public void setfile(String _jni_value_) {
        _setfile(this.object.pointer, _jni_value_);
    }

    private native void _setfile(long _pointer_, String _jni_value_);

    /** **/
    public long getline() {
         return _getline(this.object.pointer);
    }

    private native long _getline(long _pointer_);

    /** **/
    public void setline(long _jni_value_) {
        _setline(this.object.pointer, _jni_value_);
    }

    private native void _setline(long _pointer_, long _jni_value_);

    /** **/
    public org.ogre4j.ConcreteNodeType gettype() {
         return org.ogre4j.ConcreteNodeType.toEnum(_gettype(this.object.pointer));
    }

    private native int _gettype(long _pointer_);

    /** **/
    public void settype(org.ogre4j.ConcreteNodeType _jni_value_) {
        _settype(this.object.pointer, _jni_value_.getValue());
    }

    private native void _settype(long _pointer_, int _jni_value_);

    /** **/
    public void getchildren(org.ogre4j.IConcreteNodeList returnValue) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getchildren(this.object.pointer)), false);
    }

    private native long _getchildren(long _pointer_);

    /** **/
    public void setchildren(org.ogre4j.IConcreteNodeList _jni_value_) {
        _setchildren(this.object.pointer, _jni_value_.getInstancePointer().pointer);
    }

    private native void _setchildren(long _pointer_, long _jni_value_);

    /** **/
    public org.ogre4j.IConcreteNode getparent() {
         return new org.ogre4j.ConcreteNode(new InstancePointer(_getparent(this.object.pointer)));
    }

    private native long _getparent(long _pointer_);

    /** **/
    public void setparent(org.ogre4j.IConcreteNode _jni_value_) {
        _setparent(this.object.pointer, _jni_value_.getInstancePointer().pointer);
    }

    private native void _setparent(long _pointer_, long _jni_value_);

}
