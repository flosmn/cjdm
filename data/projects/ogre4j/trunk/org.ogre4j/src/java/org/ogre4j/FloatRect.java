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
public class FloatRect extends org.xbig.base.NativeObject implements org.ogre4j.IFloatRect {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public FloatRect(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected FloatRect(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public FloatRect(org.xbig.base.WithoutNativeObject val) {
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
    public FloatRect() {
         super( new org.xbig.base.InstancePointer(__createFloatRect()), false);
    }

    private native static long __createFloatRect();

    /** **/
    public FloatRect(FloatPointer l, FloatPointer t, FloatPointer r, FloatPointer b) {
         super( new org.xbig.base.InstancePointer(__createFloatRect__FrFrFrFr( l.object.pointer,  t.object.pointer,  r.object.pointer,  b.object.pointer)), false);
    }

    private native static long __createFloatRect__FrFrFrFr(long l, long t, long r, long b);

    /** **/
    public org.ogre4j.IFloatRect operatorAssignment(org.ogre4j.IFloatRect o) {
         return new org.ogre4j.FloatRect(new InstancePointer(_operatorAssignment___Ogre_TRectr(this.object.pointer, o.getInstancePointer().pointer)));
    }

    private native long _operatorAssignment___Ogre_TRectr(long _pointer_, long o);

    /** **/
    public float width() {
         return _width_const(this.object.pointer);
    }

    private native float _width_const(long _pointer_);

    /** **/
    public float height() {
         return _height_const(this.object.pointer);
    }

    private native float _height_const(long _pointer_);

    /** **/
    public float getleft() {
         return _getleft(this.object.pointer);
    }

    private native float _getleft(long _pointer_);

    /** **/
    public void setleft(float _jni_value_) {
        _setleft(this.object.pointer, _jni_value_);
    }

    private native void _setleft(long _pointer_, float _jni_value_);

    /** **/
    public float gettop() {
         return _gettop(this.object.pointer);
    }

    private native float _gettop(long _pointer_);

    /** **/
    public void settop(float _jni_value_) {
        _settop(this.object.pointer, _jni_value_);
    }

    private native void _settop(long _pointer_, float _jni_value_);

    /** **/
    public float getright() {
         return _getright(this.object.pointer);
    }

    private native float _getright(long _pointer_);

    /** **/
    public void setright(float _jni_value_) {
        _setright(this.object.pointer, _jni_value_);
    }

    private native void _setright(long _pointer_, float _jni_value_);

    /** **/
    public float getbottom() {
         return _getbottom(this.object.pointer);
    }

    private native float _getbottom(long _pointer_);

    /** **/
    public void setbottom(float _jni_value_) {
        _setbottom(this.object.pointer, _jni_value_);
    }

    private native void _setbottom(long _pointer_, float _jni_value_);

}
