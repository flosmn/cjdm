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
public class ShadowRenderable extends org.xbig.base.NativeObject implements org.ogre4j.IShadowRenderable {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public ShadowRenderable(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected ShadowRenderable(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public ShadowRenderable(org.xbig.base.WithoutNativeObject val) {
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
    Set the material to be used by the shadow, should be set by the caller before adding to a render queue **/
    public void setMaterial(org.ogre4j.IMaterialPtr mat) {
        _setMaterial__MaterialPtrR(this.object.pointer, mat.getInstancePointer().pointer);
    }

    private native void _setMaterial__MaterialPtrR(long _pointer_, long mat);

    /** **/
    public org.ogre4j.IMaterialPtr getMaterial() {
         return new org.ogre4j.MaterialPtr(new InstancePointer(_getMaterial_const(this.object.pointer)));
    }

    private native long _getMaterial_const(long _pointer_);

    /** **/
    public void getRenderOperation(org.ogre4j.IRenderOperation op) {
        _getRenderOperation__RenderOperationr(this.object.pointer, op.getInstancePointer().pointer);
    }

    private native void _getRenderOperation__RenderOperationr(long _pointer_, long op);

    /** **/
    public org.ogre4j.IRenderOperation getRenderOperationForUpdate() {
         return new org.ogre4j.RenderOperation(new InstancePointer(_getRenderOperationForUpdate(this.object.pointer)));
    }

    private native long _getRenderOperationForUpdate(long _pointer_);

    /** **/
    public void getWorldTransforms(org.ogre4j.IMatrix4 xform) {
        _getWorldTransforms__Matrix4p_const(this.object.pointer, xform.getInstancePointer().pointer);
    }

    private native void _getWorldTransforms__Matrix4p_const(long _pointer_, long xform);

    /** **/
    public float getSquaredViewDepth(org.ogre4j.ICamera a1) {
         return _getSquaredViewDepth__CameraP_const(this.object.pointer, a1.getInstancePointer().pointer);
    }

    private native float _getSquaredViewDepth__CameraP_const(long _pointer_, long a1);

    /** **/
    public org.ogre4j.ILightList getLights() {
         return new org.ogre4j.LightList(new InstancePointer(_getLights_const(this.object.pointer)));
    }

    private native long _getLights_const(long _pointer_);

    /** 
    Does this renderable require a separate light cap? **/
    public boolean isLightCapSeparate() {
         return _isLightCapSeparate_const(this.object.pointer);
    }

    private native boolean _isLightCapSeparate_const(long _pointer_);

    /** **/
    public org.ogre4j.IShadowRenderable getLightCapRenderable() {
         return new org.ogre4j.ShadowRenderable(new InstancePointer(_getLightCapRenderable(this.object.pointer)));
    }

    private native long _getLightCapRenderable(long _pointer_);

    /** **/
    public boolean isVisible() {
         return _isVisible_const(this.object.pointer);
    }

    private native boolean _isVisible_const(long _pointer_);

    /** 
    Retrieves a pointer to the  this renderable object uses. **/
    public org.ogre4j.ITechnique getTechnique() {
         return new org.ogre4j.Technique(new InstancePointer(_getTechnique_const(this.object.pointer)));
    }

    private native long _getTechnique_const(long _pointer_);

    /** 
    Called just prior to the  being rendered. **/
    public boolean preRender(org.ogre4j.ISceneManager sm, org.ogre4j.IRenderSystem rsys) {
         return _preRender__SceneManagerpRenderSystemp(this.object.pointer, sm.getInstancePointer().pointer,  rsys.getInstancePointer().pointer);
    }

    private native boolean _preRender__SceneManagerpRenderSystemp(long _pointer_, long sm, long rsys);

    /** 
    Called immediately after the  has been rendered. **/
    public void postRender(org.ogre4j.ISceneManager sm, org.ogre4j.IRenderSystem rsys) {
        _postRender__SceneManagerpRenderSystemp(this.object.pointer, sm.getInstancePointer().pointer,  rsys.getInstancePointer().pointer);
    }

    private native void _postRender__SceneManagerpRenderSystemp(long _pointer_, long sm, long rsys);

    /** 
    Returns the number of world transform matrices this renderable requires. **/
    public int getNumWorldTransforms() {
         return _getNumWorldTransforms_const(this.object.pointer);
    }

    private native int _getNumWorldTransforms_const(long _pointer_);

    /** 
    Sets whether or not to use an 'identity' projection. **/
    public void setUseIdentityProjection(boolean useIdentityProjection) {
        _setUseIdentityProjection__bv(this.object.pointer, useIdentityProjection);
    }

    private native void _setUseIdentityProjection__bv(long _pointer_, boolean useIdentityProjection);

    /** 
    Returns whether or not to use an 'identity' projection. **/
    public boolean getUseIdentityProjection() {
         return _getUseIdentityProjection_const(this.object.pointer);
    }

    private native boolean _getUseIdentityProjection_const(long _pointer_);

    /** 
    Sets whether or not to use an 'identity' view. **/
    public void setUseIdentityView(boolean useIdentityView) {
        _setUseIdentityView__bv(this.object.pointer, useIdentityView);
    }

    private native void _setUseIdentityView__bv(long _pointer_, boolean useIdentityView);

    /** 
    Returns whether or not to use an 'identity' view. **/
    public boolean getUseIdentityView() {
         return _getUseIdentityView_const(this.object.pointer);
    }

    private native boolean _getUseIdentityView_const(long _pointer_);

    /** 
    Method which reports whether this renderable would normally cast a shadow. **/
    public boolean getCastsShadows() {
         return _getCastsShadows_const(this.object.pointer);
    }

    private native boolean _getCastsShadows_const(long _pointer_);

    /** 
    Sets a custom parameter for this , which may be used to drive calculations for this specific , like GPU program parameters. **/
    public void setCustomParameter(int index, org.ogre4j.IVector4 value) {
        _setCustomParameter__ivVector4R(this.object.pointer, index,  value.getInstancePointer().pointer);
    }

    private native void _setCustomParameter__ivVector4R(long _pointer_, int index, long value);

    /** 
    Gets the custom value associated with this  at the given index. **/
    public org.ogre4j.IVector4 getCustomParameter(int index) {
         return new org.ogre4j.Vector4(new InstancePointer(_getCustomParameter__iv_const(this.object.pointer, index)));
    }

    private native long _getCustomParameter__iv_const(long _pointer_, int index);

    /** 
    Update a custom  constant which is derived from information only this  knows. **/
    public void _updateCustomGpuParameter(org.ogre4j.IGpuProgramParameters.IAutoConstantEntry constantEntry, org.ogre4j.IGpuProgramParameters params) {
        __updateCustomGpuParameter__GpuProgramParameters_AutoConstantEntryRGpuProgramParametersp_const(this.object.pointer, constantEntry.getInstancePointer().pointer,  params.getInstancePointer().pointer);
    }

    private native void __updateCustomGpuParameter__GpuProgramParameters_AutoConstantEntryRGpuProgramParametersp_const(long _pointer_, long constantEntry, long params);

    /** 
    Sets whether this renderable's chosen detail level can be overridden (downgraded) by the camera setting. **/
    public void setPolygonModeOverrideable(boolean override) {
        _setPolygonModeOverrideable__bv(this.object.pointer, override);
    }

    private native void _setPolygonModeOverrideable__bv(long _pointer_, boolean override);

    /** 
    Gets whether this renderable's chosen detail level can be overridden (downgraded) by the camera setting. **/
    public boolean getPolygonModeOverrideable() {
         return _getPolygonModeOverrideable_const(this.object.pointer);
    }

    private native boolean _getPolygonModeOverrideable_const(long _pointer_);

    /** 
    Sets any kind of user value on this object. **/
    public void setUserAny(org.ogre4j.IAny anything) {
        _setUserAny__AnyR(this.object.pointer, anything.getInstancePointer().pointer);
    }

    private native void _setUserAny__AnyR(long _pointer_, long anything);

    /** 
    Retrieves the custom user value associated with this object. **/
    public org.ogre4j.IAny getUserAny() {
         return new org.ogre4j.Any(new InstancePointer(_getUserAny_const(this.object.pointer)));
    }

    private native long _getUserAny_const(long _pointer_);

    /** 
    Sets render system private data **/
    public org.ogre4j.IRenderable.IRenderSystemData getRenderSystemData() {
         return new org.ogre4j.Renderable.RenderSystemData(new InstancePointer(_getRenderSystemData_const(this.object.pointer)));
    }

    private native long _getRenderSystemData_const(long _pointer_);

    /** 
    gets render system private data **/
    public void setRenderSystemData(org.ogre4j.IRenderable.IRenderSystemData val) {
        _setRenderSystemData__RenderSystemDatap_const(this.object.pointer, val.getInstancePointer().pointer);
    }

    private native void _setRenderSystemData__RenderSystemDatap_const(long _pointer_, long val);

}
