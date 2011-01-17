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
public class MaterialScriptProgramDefinition extends org.xbig.base.NativeObject implements org.ogre4j.IMaterialScriptProgramDefinition {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public MaterialScriptProgramDefinition(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected MaterialScriptProgramDefinition(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public MaterialScriptProgramDefinition(org.xbig.base.WithoutNativeObject val) {
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
    public MaterialScriptProgramDefinition() {
         super( new org.xbig.base.InstancePointer(__createMaterialScriptProgramDefinition()), false);
    }

    private native static long __createMaterialScriptProgramDefinition();

    /** **/
    public String getname() {
         return _getname(this.object.pointer);
    }

    private native String _getname(long _pointer_);

    /** **/
    public void setname(String _jni_value_) {
        _setname(this.object.pointer, _jni_value_);
    }

    private native void _setname(long _pointer_, String _jni_value_);

    /** **/
    public org.ogre4j.GpuProgramType getprogType() {
         return org.ogre4j.GpuProgramType.toEnum(_getprogType(this.object.pointer));
    }

    private native int _getprogType(long _pointer_);

    /** **/
    public void setprogType(org.ogre4j.GpuProgramType _jni_value_) {
        _setprogType(this.object.pointer, _jni_value_.getValue());
    }

    private native void _setprogType(long _pointer_, int _jni_value_);

    /** **/
    public String getlanguage() {
         return _getlanguage(this.object.pointer);
    }

    private native String _getlanguage(long _pointer_);

    /** **/
    public void setlanguage(String _jni_value_) {
        _setlanguage(this.object.pointer, _jni_value_);
    }

    private native void _setlanguage(long _pointer_, String _jni_value_);

    /** **/
    public String getsource() {
         return _getsource(this.object.pointer);
    }

    private native String _getsource(long _pointer_);

    /** **/
    public void setsource(String _jni_value_) {
        _setsource(this.object.pointer, _jni_value_);
    }

    private native void _setsource(long _pointer_, String _jni_value_);

    /** **/
    public String getsyntax() {
         return _getsyntax(this.object.pointer);
    }

    private native String _getsyntax(long _pointer_);

    /** **/
    public void setsyntax(String _jni_value_) {
        _setsyntax(this.object.pointer, _jni_value_);
    }

    private native void _setsyntax(long _pointer_, String _jni_value_);

    /** **/
    public boolean getsupportsSkeletalAnimation() {
         return _getsupportsSkeletalAnimation(this.object.pointer);
    }

    private native boolean _getsupportsSkeletalAnimation(long _pointer_);

    /** **/
    public void setsupportsSkeletalAnimation(boolean _jni_value_) {
        _setsupportsSkeletalAnimation(this.object.pointer, _jni_value_);
    }

    private native void _setsupportsSkeletalAnimation(long _pointer_, boolean _jni_value_);

    /** **/
    public boolean getsupportsMorphAnimation() {
         return _getsupportsMorphAnimation(this.object.pointer);
    }

    private native boolean _getsupportsMorphAnimation(long _pointer_);

    /** **/
    public void setsupportsMorphAnimation(boolean _jni_value_) {
        _setsupportsMorphAnimation(this.object.pointer, _jni_value_);
    }

    private native void _setsupportsMorphAnimation(long _pointer_, boolean _jni_value_);

    /** **/
    public int getsupportsPoseAnimation() {
         return _getsupportsPoseAnimation(this.object.pointer);
    }

    private native int _getsupportsPoseAnimation(long _pointer_);

    /** **/
    public void setsupportsPoseAnimation(int _jni_value_) {
        _setsupportsPoseAnimation(this.object.pointer, _jni_value_);
    }

    private native void _setsupportsPoseAnimation(long _pointer_, int _jni_value_);

    /** **/
    public boolean getusesVertexTextureFetch() {
         return _getusesVertexTextureFetch(this.object.pointer);
    }

    private native boolean _getusesVertexTextureFetch(long _pointer_);

    /** **/
    public void setusesVertexTextureFetch(boolean _jni_value_) {
        _setusesVertexTextureFetch(this.object.pointer, _jni_value_);
    }

    private native void _setusesVertexTextureFetch(long _pointer_, boolean _jni_value_);

    /** **/
    public void getcustomParameters(org.std.Ivector< org.std.Ipair< String, String > > returnValue) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getcustomParameters(this.object.pointer)), false);
    }

    private native long _getcustomParameters(long _pointer_);

    /** **/
    public void setcustomParameters(org.std.Ivector< org.std.Ipair< String, String > > _jni_value_) {
        _setcustomParameters(this.object.pointer, _jni_value_.getInstancePointer().pointer);
    }

    private native void _setcustomParameters(long _pointer_, long _jni_value_);

}
