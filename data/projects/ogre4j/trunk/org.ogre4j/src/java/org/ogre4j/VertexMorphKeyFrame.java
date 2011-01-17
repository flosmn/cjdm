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
public class VertexMorphKeyFrame extends org.xbig.base.NativeObject implements org.ogre4j.IVertexMorphKeyFrame {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public VertexMorphKeyFrame(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected VertexMorphKeyFrame(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public VertexMorphKeyFrame(org.xbig.base.WithoutNativeObject val) {
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
    Default constructor, you should not call this but use  instead. **/
    public VertexMorphKeyFrame(org.ogre4j.IAnimationTrack parent, float time) {
         super( new org.xbig.base.InstancePointer(__createVertexMorphKeyFrame__AnimationTrackPRealv( parent.getInstancePointer().pointer,  time)), false);
    }

    private native static long __createVertexMorphKeyFrame__AnimationTrackPRealv(long parent, float time);

    /** 
    Sets the vertex buffer containing the source positions for this keyframe. **/
    public void setVertexBuffer(org.ogre4j.IHardwareVertexBufferSharedPtr buf) {
        _setVertexBuffer__HardwareVertexBufferSharedPtrR(this.object.pointer, buf.getInstancePointer().pointer);
    }

    private native void _setVertexBuffer__HardwareVertexBufferSharedPtrR(long _pointer_, long buf);

    /** 
    Gets the vertex buffer containing positions for this keyframe. **/
    public org.ogre4j.IHardwareVertexBufferSharedPtr getVertexBuffer() {
         return new org.ogre4j.HardwareVertexBufferSharedPtr(new InstancePointer(_getVertexBuffer_const(this.object.pointer)));
    }

    private native long _getVertexBuffer_const(long _pointer_);

    /** 
    Clone a keyframe (internal use only) **/
    public org.ogre4j.IKeyFrame _clone(org.ogre4j.IAnimationTrack newParent) {
         return new org.ogre4j.KeyFrame(new InstancePointer(__clone__AnimationTrackp_const(this.object.pointer, newParent.getInstancePointer().pointer)));
    }

    private native long __clone__AnimationTrackp_const(long _pointer_, long newParent);

    /** 
    Gets the time of this keyframe in the animation sequence. **/
    public float getTime() {
         return _getTime_const(this.object.pointer);
    }

    private native float _getTime_const(long _pointer_);

}
