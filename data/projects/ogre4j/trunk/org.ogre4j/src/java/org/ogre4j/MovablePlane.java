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
public class MovablePlane extends org.xbig.base.NativeObject implements org.ogre4j.IMovablePlane {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public MovablePlane(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected MovablePlane(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public MovablePlane(org.xbig.base.WithoutNativeObject val) {
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
    public MovablePlane(String name) {
         super( new org.xbig.base.InstancePointer(__createMovablePlane__StringR( name)), false);
    }

    private native static long __createMovablePlane__StringR(String name);

    /** **/
    public MovablePlane(org.ogre4j.IPlane rhs) {
         super( new org.xbig.base.InstancePointer(__createMovablePlane__PlaneR( rhs.getInstancePointer().pointer)), false);
    }

    private native static long __createMovablePlane__PlaneR(long rhs);

    /** 
    Construct a plane through a normal, and a distance to move the plane along the normal. **/
    public MovablePlane(org.ogre4j.IVector3 rkNormal, float fConstant) {
         super( new org.xbig.base.InstancePointer(__createMovablePlane__Vector3RRealv( rkNormal.getInstancePointer().pointer,  fConstant)), false);
    }

    private native static long __createMovablePlane__Vector3RRealv(long rkNormal, float fConstant);

    /** **/
    public MovablePlane(org.ogre4j.IVector3 rkNormal, org.ogre4j.IVector3 rkPoint) {
         super( new org.xbig.base.InstancePointer(__createMovablePlane__Vector3RVector3R( rkNormal.getInstancePointer().pointer,  rkPoint.getInstancePointer().pointer)), false);
    }

    private native static long __createMovablePlane__Vector3RVector3R(long rkNormal, long rkPoint);

    /** **/
    public MovablePlane(org.ogre4j.IVector3 rkPoint0, org.ogre4j.IVector3 rkPoint1, org.ogre4j.IVector3 rkPoint2) {
         super( new org.xbig.base.InstancePointer(__createMovablePlane__Vector3RVector3RVector3R( rkPoint0.getInstancePointer().pointer,  rkPoint1.getInstancePointer().pointer,  rkPoint2.getInstancePointer().pointer)), false);
    }

    private native static long __createMovablePlane__Vector3RVector3RVector3R(long rkPoint0, long rkPoint1, long rkPoint2);

    /** **/
    public void _notifyCurrentCamera(org.ogre4j.ICamera a1) {
        __notifyCurrentCamera__Camerap(this.object.pointer, a1.getInstancePointer().pointer);
    }

    private native void __notifyCurrentCamera__Camerap(long _pointer_, long a1);

    /** **/
    public org.ogre4j.IAxisAlignedBox getBoundingBox() {
         return new org.ogre4j.AxisAlignedBox(new InstancePointer(_getBoundingBox_const(this.object.pointer)));
    }

    private native long _getBoundingBox_const(long _pointer_);

    /** **/
    public float getBoundingRadius() {
         return _getBoundingRadius_const(this.object.pointer);
    }

    private native float _getBoundingRadius_const(long _pointer_);

    /** **/
    public void _updateRenderQueue(org.ogre4j.IRenderQueue a1) {
        __updateRenderQueue__RenderQueuep(this.object.pointer, a1.getInstancePointer().pointer);
    }

    private native void __updateRenderQueue__RenderQueuep(long _pointer_, long a1);

    /** **/
    public String getMovableType() {
         return _getMovableType_const(this.object.pointer);
    }

    private native String _getMovableType_const(long _pointer_);

    /** **/
    public org.ogre4j.IPlane _getDerivedPlane() {
         return new org.ogre4j.Plane(new InstancePointer(__getDerivedPlane_const(this.object.pointer)));
    }

    private native long __getDerivedPlane_const(long _pointer_);

    /** **/
    public void visitRenderables(org.ogre4j.IRenderable.IVisitor visitor, boolean debugRenderables) {
        _visitRenderables__Renderable_Visitorpbv(this.object.pointer, visitor.getInstancePointer().pointer,  debugRenderables);
    }

    private native void _visitRenderables__Renderable_Visitorpbv(long _pointer_, long visitor, boolean debugRenderables);

    /** **/
    public org.ogre4j.Plane.Side getSide(org.ogre4j.IVector3 rkPoint) {
         return org.ogre4j.Plane.Side.toEnum(_getSide__Vector3R_const(this.object.pointer, rkPoint.getInstancePointer().pointer));
    }

    private native int _getSide__Vector3R_const(long _pointer_, long rkPoint);

    /** 
    returns the side where the aligneBox is. the flag BOTH_SIDE indicates an intersecting box. one corner ON the plane is sufficient to consider the box and the plane intersecting. **/
    public org.ogre4j.Plane.Side getSide(org.ogre4j.IAxisAlignedBox rkBox) {
         return org.ogre4j.Plane.Side.toEnum(_getSide__AxisAlignedBoxR_const(this.object.pointer, rkBox.getInstancePointer().pointer));
    }

    private native int _getSide__AxisAlignedBoxR_const(long _pointer_, long rkBox);

    /** 
    Returns which side of the plane that the given box lies on. The box is defined as centre/half-size pairs for effectively. **/
    public org.ogre4j.Plane.Side getSide(org.ogre4j.IVector3 centre, org.ogre4j.IVector3 halfSize) {
         return org.ogre4j.Plane.Side.toEnum(_getSide__Vector3RVector3R_const(this.object.pointer, centre.getInstancePointer().pointer,  halfSize.getInstancePointer().pointer));
    }

    private native int _getSide__Vector3RVector3R_const(long _pointer_, long centre, long halfSize);

    /** 
    This is a pseudodistance. The sign of the return value is positive if the point is on the positive side of the plane, negative if the point is on the negative side, and zero if the point is on the plane. **/
    public float getDistance(org.ogre4j.IVector3 rkPoint) {
         return _getDistance__Vector3R_const(this.object.pointer, rkPoint.getInstancePointer().pointer);
    }

    private native float _getDistance__Vector3R_const(long _pointer_, long rkPoint);

    /** 
    Redefine this plane based on 3 points. **/
    public void redefine(org.ogre4j.IVector3 rkPoint0, org.ogre4j.IVector3 rkPoint1, org.ogre4j.IVector3 rkPoint2) {
        _redefine__Vector3RVector3RVector3R(this.object.pointer, rkPoint0.getInstancePointer().pointer,  rkPoint1.getInstancePointer().pointer,  rkPoint2.getInstancePointer().pointer);
    }

    private native void _redefine__Vector3RVector3RVector3R(long _pointer_, long rkPoint0, long rkPoint1, long rkPoint2);

    /** 
    Redefine this plane based on a normal and a point. **/
    public void redefine(org.ogre4j.IVector3 rkNormal, org.ogre4j.IVector3 rkPoint) {
        _redefine__Vector3RVector3R(this.object.pointer, rkNormal.getInstancePointer().pointer,  rkPoint.getInstancePointer().pointer);
    }

    private native void _redefine__Vector3RVector3R(long _pointer_, long rkNormal, long rkPoint);

    /** 
    Project a vector onto the plane. **/
    public void projectVector(org.ogre4j.IVector3 returnValue, org.ogre4j.IVector3 v) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_projectVector__Vector3R_const(this.object.pointer, v.getInstancePointer().pointer)), false);
    }

    private native long _projectVector__Vector3R_const(long _pointer_, long v);

    /** 
    Normalises the plane. **/
    public float normalise() {
         return _normalise(this.object.pointer);
    }

    private native float _normalise(long _pointer_);

    /** **/
    public boolean operatorEqual(org.ogre4j.IPlane rhs) {
         return _operatorEqual__PlaneR_const(this.object.pointer, rhs.getInstancePointer().pointer);
    }

    private native boolean _operatorEqual__PlaneR_const(long _pointer_, long rhs);

    /** **/
    public boolean operatorNotEqual(org.ogre4j.IPlane rhs) {
         return _operatorNotEqual__PlaneR_const(this.object.pointer, rhs.getInstancePointer().pointer);
    }

    private native boolean _operatorNotEqual__PlaneR_const(long _pointer_, long rhs);

    /** 
    Notify the object of it's creator (internal use only) **/
    public void _notifyCreator(org.ogre4j.IMovableObjectFactory fact) {
        __notifyCreator__MovableObjectFactoryp(this.object.pointer, fact.getInstancePointer().pointer);
    }

    private native void __notifyCreator__MovableObjectFactoryp(long _pointer_, long fact);

    /** 
    Get the creator of this object, if any (internal use only) **/
    public org.ogre4j.IMovableObjectFactory _getCreator() {
         return new org.ogre4j.MovableObjectFactory(new InstancePointer(__getCreator_const(this.object.pointer)));
    }

    private native long __getCreator_const(long _pointer_);

    /** 
    Notify the object of it's manager (internal use only) **/
    public void _notifyManager(org.ogre4j.ISceneManager man) {
        __notifyManager__SceneManagerp(this.object.pointer, man.getInstancePointer().pointer);
    }

    private native void __notifyManager__SceneManagerp(long _pointer_, long man);

    /** 
    Get the manager of this object, if any (internal use only) **/
    public org.ogre4j.ISceneManager _getManager() {
         return new org.ogre4j.SceneManager(new InstancePointer(__getManager_const(this.object.pointer)));
    }

    private native long __getManager_const(long _pointer_);

    /** 
    Returns the name of this object. **/
    public String getName() {
         return _getName_const(this.object.pointer);
    }

    private native String _getName_const(long _pointer_);

    /** 
    Returns the node to which this object is attached. **/
    public org.ogre4j.INode getParentNode() {
         return new org.ogre4j.Node(new InstancePointer(_getParentNode_const(this.object.pointer)));
    }

    private native long _getParentNode_const(long _pointer_);

    /** 
    Returns the scene node to which this object is attached. **/
    public org.ogre4j.ISceneNode getParentSceneNode() {
         return new org.ogre4j.SceneNode(new InstancePointer(_getParentSceneNode_const(this.object.pointer)));
    }

    private native long _getParentSceneNode_const(long _pointer_);

    /** 
    Internal method called to notify the object that it has been attached to a node. **/
    public void _notifyAttached(org.ogre4j.INode parent, boolean isTagPoint) {
        __notifyAttached__Nodepbv(this.object.pointer, parent.getInstancePointer().pointer,  isTagPoint);
    }

    private native void __notifyAttached__Nodepbv(long _pointer_, long parent, boolean isTagPoint);

    /** 
    Returns true if this object is attached to a  or . **/
    public boolean isAttached() {
         return _isAttached_const(this.object.pointer);
    }

    private native boolean _isAttached_const(long _pointer_);

    /** 
    Detaches an object from a parent  or , if attached. **/
    public void detatchFromParent() {
        _detatchFromParent(this.object.pointer);
    }

    private native void _detatchFromParent(long _pointer_);

    /** 
    Returns true if this object is attached to a  or , and this  /  is currently in an active part of the scene graph. **/
    public boolean isInScene() {
         return _isInScene_const(this.object.pointer);
    }

    private native boolean _isInScene_const(long _pointer_);

    /** 
    Internal method called to notify the object that it has been moved. **/
    public void _notifyMoved() {
        __notifyMoved(this.object.pointer);
    }

    private native void __notifyMoved(long _pointer_);

    /** 
    Retrieves the axis-aligned bounding box for this object in world coordinates. **/
    public org.ogre4j.IAxisAlignedBox getWorldBoundingBox(boolean derive) {
         return new org.ogre4j.AxisAlignedBox(new InstancePointer(_getWorldBoundingBox__bv_const(this.object.pointer, derive)));
    }

    private native long _getWorldBoundingBox__bv_const(long _pointer_, boolean derive);

    /** 
    Retrieves the worldspace bounding sphere for this object. **/
    public org.ogre4j.ISphere getWorldBoundingSphere(boolean derive) {
         return new org.ogre4j.Sphere(new InstancePointer(_getWorldBoundingSphere__bv_const(this.object.pointer, derive)));
    }

    private native long _getWorldBoundingSphere__bv_const(long _pointer_, boolean derive);

    /** 
    Tells this object whether to be visible or not, if it has a renderable component. **/
    public void setVisible(boolean visible) {
        _setVisible__bv(this.object.pointer, visible);
    }

    private native void _setVisible__bv(long _pointer_, boolean visible);

    /** 
    Gets this object whether to be visible or not, if it has a renderable component. **/
    public boolean getVisible() {
         return _getVisible_const(this.object.pointer);
    }

    private native boolean _getVisible_const(long _pointer_);

    /** 
    Returns whether or not this object is supposed to be visible or not. **/
    public boolean isVisible() {
         return _isVisible_const(this.object.pointer);
    }

    private native boolean _isVisible_const(long _pointer_);

    /** 
    Sets the distance at which the object is no longer rendered. **/
    public void setRenderingDistance(float dist) {
        _setRenderingDistance__Realv(this.object.pointer, dist);
    }

    private native void _setRenderingDistance__Realv(long _pointer_, float dist);

    /** 
    Gets the distance at which batches are no longer rendered. **/
    public float getRenderingDistance() {
         return _getRenderingDistance_const(this.object.pointer);
    }

    private native float _getRenderingDistance_const(long _pointer_);

    /** 
    Call this to associate your own custom user object instance with this . **/
    public void setUserObject(org.ogre4j.IUserDefinedObject obj) {
        _setUserObject__UserDefinedObjectp(this.object.pointer, obj.getInstancePointer().pointer);
    }

    private native void _setUserObject__UserDefinedObjectp(long _pointer_, long obj);

    /** 
    Retrieves a pointer to a custom application object associated with this movable by an earlier call to setUserObject. **/
    public org.ogre4j.IUserDefinedObject getUserObject() {
         return new org.ogre4j.UserDefinedObject(new InstancePointer(_getUserObject(this.object.pointer)));
    }

    private native long _getUserObject(long _pointer_);

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
    Sets the render queue group this entity will be rendered through. **/
    public void setRenderQueueGroup(short queueID) {
        _setRenderQueueGroup__uint8v(this.object.pointer, queueID);
    }

    private native void _setRenderQueueGroup__uint8v(long _pointer_, short queueID);

    /** 
    Gets the queue group for this entity, see setRenderQueueGroup for full details. **/
    public short getRenderQueueGroup() {
         return _getRenderQueueGroup_const(this.object.pointer);
    }

    private native short _getRenderQueueGroup_const(long _pointer_);

    /** **/
    public org.ogre4j.IMatrix4 _getParentNodeFullTransform() {
         return new org.ogre4j.Matrix4(new InstancePointer(__getParentNodeFullTransform_const(this.object.pointer)));
    }

    private native long __getParentNodeFullTransform_const(long _pointer_);

    /** 
    Sets the query flags for this object. **/
    public void setQueryFlags(long flags) {
        _setQueryFlags__uint32v(this.object.pointer, flags);
    }

    private native void _setQueryFlags__uint32v(long _pointer_, long flags);

    /** 
    As setQueryFlags, except the flags passed as parameters are appended to the existing flags on this object. **/
    public void addQueryFlags(long flags) {
        _addQueryFlags__uint32v(this.object.pointer, flags);
    }

    private native void _addQueryFlags__uint32v(long _pointer_, long flags);

    /** 
    As setQueryFlags, except the flags passed as parameters are removed from the existing flags on this object. **/
    public void removeQueryFlags(long flags) {
        _removeQueryFlags__Lv(this.object.pointer, flags);
    }

    private native void _removeQueryFlags__Lv(long _pointer_, long flags);

    /** **/
    public long getQueryFlags() {
         return _getQueryFlags_const(this.object.pointer);
    }

    private native long _getQueryFlags_const(long _pointer_);

    /** 
    Sets the visiblity flags for this object. **/
    public void setVisibilityFlags(long flags) {
        _setVisibilityFlags__uint32v(this.object.pointer, flags);
    }

    private native void _setVisibilityFlags__uint32v(long _pointer_, long flags);

    /** 
    As setVisibilityFlags, except the flags passed as parameters are appended to the existing flags on this object. **/
    public void addVisibilityFlags(long flags) {
        _addVisibilityFlags__uint32v(this.object.pointer, flags);
    }

    private native void _addVisibilityFlags__uint32v(long _pointer_, long flags);

    /** 
    As setVisibilityFlags, except the flags passed as parameters are removed from the existing flags on this object. **/
    public void removeVisibilityFlags(long flags) {
        _removeVisibilityFlags__uint32v(this.object.pointer, flags);
    }

    private native void _removeVisibilityFlags__uint32v(long _pointer_, long flags);

    /** **/
    public long getVisibilityFlags() {
         return _getVisibilityFlags_const(this.object.pointer);
    }

    private native long _getVisibilityFlags_const(long _pointer_);

    /** 
    Sets a listener for this object. **/
    public void setListener(org.ogre4j.IMovableObject.IListener listener) {
        _setListener__Listenerp(this.object.pointer, listener.getInstancePointer().pointer);
    }

    private native void _setListener__Listenerp(long _pointer_, long listener);

    /** 
    Gets the current listener for this object. **/
    public org.ogre4j.IMovableObject.IListener getListener() {
         return new org.ogre4j.MovableObject.Listener(new InstancePointer(_getListener_const(this.object.pointer)));
    }

    private native long _getListener_const(long _pointer_);

    /** 
    Gets a list of lights, ordered relative to how close they are to this movable object. **/
    public org.ogre4j.ILightList queryLights() {
         return new org.ogre4j.LightList(new InstancePointer(_queryLights_const(this.object.pointer)));
    }

    private native long _queryLights_const(long _pointer_);

    /** 
    Returns a pointer to the current list of lights for this object. **/
    public org.ogre4j.ILightList _getLightList() {
         return new org.ogre4j.LightList(new InstancePointer(__getLightList(this.object.pointer)));
    }

    private native long __getLightList(long _pointer_);

    /** **/
    public org.ogre4j.IEdgeData getEdgeList() {
         return new org.ogre4j.EdgeData(new InstancePointer(_getEdgeList(this.object.pointer)));
    }

    private native long _getEdgeList(long _pointer_);

    /** **/
    public boolean hasEdgeList() {
         return _hasEdgeList(this.object.pointer);
    }

    private native boolean _hasEdgeList(long _pointer_);

    /** **/
    public void getShadowVolumeRenderableIterator(org.ogre4j.IShadowCaster.IShadowRenderableListIterator returnValue, org.ogre4j.ShadowTechnique shadowTechnique, org.ogre4j.ILight light, org.ogre4j.IHardwareIndexBufferSharedPtr indexBuffer, boolean extrudeVertices, float extrusionDist, long flags) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getShadowVolumeRenderableIterator__ShadowTechniquevLightPHardwareIndexBufferSharedPtrpbvRealvLv(this.object.pointer, shadowTechnique.getValue(),  light.getInstancePointer().pointer,  indexBuffer.getInstancePointer().pointer,  extrudeVertices,  extrusionDist,  flags)), false);
    }

    private native long _getShadowVolumeRenderableIterator__ShadowTechniquevLightPHardwareIndexBufferSharedPtrpbvRealvLv(long _pointer_, int shadowTechnique, long light, long indexBuffer, boolean extrudeVertices, float extrusionDist, long flags);

    /** 
    Overridden member from . **/
    public org.ogre4j.IAxisAlignedBox getLightCapBounds() {
         return new org.ogre4j.AxisAlignedBox(new InstancePointer(_getLightCapBounds_const(this.object.pointer)));
    }

    private native long _getLightCapBounds_const(long _pointer_);

    /** 
    Overridden member from . **/
    public org.ogre4j.IAxisAlignedBox getDarkCapBounds(org.ogre4j.ILight light, float dirLightExtrusionDist) {
         return new org.ogre4j.AxisAlignedBox(new InstancePointer(_getDarkCapBounds__LightRRealv_const(this.object.pointer, light.getInstancePointer().pointer,  dirLightExtrusionDist)));
    }

    private native long _getDarkCapBounds__LightRRealv_const(long _pointer_, long light, float dirLightExtrusionDist);

    /** 
    Sets whether or not this object will cast shadows. **/
    public void setCastShadows(boolean enabled) {
        _setCastShadows__bv(this.object.pointer, enabled);
    }

    private native void _setCastShadows__bv(long _pointer_, boolean enabled);

    /** 
    Returns whether shadow casting is enabled for this object. **/
    public boolean getCastShadows() {
         return _getCastShadows_const(this.object.pointer);
    }

    private native boolean _getCastShadows_const(long _pointer_);

    /** 
    Get the distance to extrude for a point/spot light **/
    public float getPointExtrusionDistance(org.ogre4j.ILight l) {
         return _getPointExtrusionDistance__LightP_const(this.object.pointer, l.getInstancePointer().pointer);
    }

    private native float _getPointExtrusionDistance__LightP_const(long _pointer_, long l);

    /** 
    Get the 'type flags' for this . **/
    public long getTypeFlags() {
         return _getTypeFlags_const(this.object.pointer);
    }

    private native long _getTypeFlags_const(long _pointer_);

    /** 
    Sets whether or not the debug display of this object is enabled. **/
    public void setDebugDisplayEnabled(boolean enabled) {
        _setDebugDisplayEnabled__bv(this.object.pointer, enabled);
    }

    private native void _setDebugDisplayEnabled__bv(long _pointer_, boolean enabled);

    /** **/
    public boolean isDebugDisplayEnabled() {
         return _isDebugDisplayEnabled_const(this.object.pointer);
    }

    private native boolean _isDebugDisplayEnabled_const(long _pointer_);

    /** 
    Set the default query flags for all future  instances. **/
    public static void setDefaultQueryFlags(long flags) {
        _setDefaultQueryFlags__uint32v( flags);
    }

    private native static void _setDefaultQueryFlags__uint32v(long flags);

    /** 
    Get the default query flags for all future  instances. **/
    public static long getDefaultQueryFlags() {
         return _getDefaultQueryFlags();
    }

    private native static long _getDefaultQueryFlags();

    /** 
    Set the default visibility flags for all future  instances. **/
    public static void setDefaultVisibilityFlags(long flags) {
        _setDefaultVisibilityFlags__uint32v( flags);
    }

    private native static void _setDefaultVisibilityFlags__uint32v(long flags);

    /** 
    Get the default visibility flags for all future  instances. **/
    public static long getDefaultVisibilityFlags() {
         return _getDefaultVisibilityFlags();
    }

    private native static long _getDefaultVisibilityFlags();

    /** 
    Utility method for extruding vertices based on a light. **/
    public static void extrudeVertices(org.ogre4j.IHardwareVertexBufferSharedPtr vertexBuffer, int originalVertexCount, org.ogre4j.IVector4 lightPos, float extrudeDist) {
        _extrudeVertices__HardwareVertexBufferSharedPtrRivVector4RRealv( vertexBuffer.getInstancePointer().pointer,  originalVertexCount,  lightPos.getInstancePointer().pointer,  extrudeDist);
    }

    private native static void _extrudeVertices__HardwareVertexBufferSharedPtrRivVector4RRealv(long vertexBuffer, int originalVertexCount, long lightPos, float extrudeDist);

    /** 
    Gets a list of animable value names for this object. **/
    public org.ogre4j.IStringVector getAnimableValueNames() {
         return new org.ogre4j.StringVector(new InstancePointer(_getAnimableValueNames_const(this.object.pointer)));
    }

    private native long _getAnimableValueNames_const(long _pointer_);

    /** 
    Create a reference-counted AnimableValuePtr for the named value. **/
    public void createAnimableValue(org.ogre4j.IAnimableValuePtr returnValue, String valueName) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_createAnimableValue__StringR(this.object.pointer, valueName)), false);
    }

    private native long _createAnimableValue__StringR(long _pointer_, String valueName);

    /** **/
    public void getnormal(org.ogre4j.IVector3 returnValue) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getnormal(this.object.pointer)), false);
    }

    private native long _getnormal(long _pointer_);

    /** **/
    public void setnormal(org.ogre4j.IVector3 _jni_value_) {
        _setnormal(this.object.pointer, _jni_value_.getInstancePointer().pointer);
    }

    private native void _setnormal(long _pointer_, long _jni_value_);

    /** **/
    public float getd() {
         return _getd(this.object.pointer);
    }

    private native float _getd(long _pointer_);

    /** **/
    public void setd(float _jni_value_) {
        _setd(this.object.pointer, _jni_value_);
    }

    private native void _setd(long _pointer_, float _jni_value_);

}
