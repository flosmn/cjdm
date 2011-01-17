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
public class Overlay extends org.xbig.base.NativeObject implements org.ogre4j.IOverlay {
static { System.loadLibrary("ogre4j");}
public static class OverlayContainerList extends org.xbig.base.NativeObject implements org.ogre4j.IOverlay.IOverlayContainerList {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public OverlayContainerList(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected OverlayContainerList(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public OverlayContainerList(org.xbig.base.WithoutNativeObject val) {
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
    public OverlayContainerList() {
         super( new org.xbig.base.InstancePointer(__createOverlayContainerList()), false);
    }

    private native static long __createOverlayContainerList();

    /** **/
    public void assign(int num, org.ogre4j.IOverlayContainer val) {
        _assign__ivOgre_OverlayContainerP(this.object.pointer, num,  val.getInstancePointer().pointer);
    }

    private native void _assign__ivOgre_OverlayContainerP(long _pointer_, int num, long val);

    /** **/
    public org.ogre4j.IOverlayContainer back() {
         return new org.ogre4j.OverlayContainer(new InstancePointer(_back(this.object.pointer)));
    }

    private native long _back(long _pointer_);

    /** **/
    public void clear() {
        _clear(this.object.pointer);
    }

    private native void _clear(long _pointer_);

    /** **/
    public boolean empty() {
         return _empty_const(this.object.pointer);
    }

    private native boolean _empty_const(long _pointer_);

    /** **/
    public org.ogre4j.IOverlayContainer front() {
         return new org.ogre4j.OverlayContainer(new InstancePointer(_front(this.object.pointer)));
    }

    private native long _front(long _pointer_);

    /** **/
    public int max_size() {
         return _max_size_const(this.object.pointer);
    }

    private native int _max_size_const(long _pointer_);

    /** **/
    public void pop_back() {
        _pop_back(this.object.pointer);
    }

    private native void _pop_back(long _pointer_);

    /** **/
    public void pop_front() {
        _pop_front(this.object.pointer);
    }

    private native void _pop_front(long _pointer_);

    /** **/
    public void push_back(org.ogre4j.IOverlayContainer val) {
        _push_back__Ogre_OverlayContainerP(this.object.pointer, val.getInstancePointer().pointer);
    }

    private native void _push_back__Ogre_OverlayContainerP(long _pointer_, long val);

    /** **/
    public void push_front(org.ogre4j.IOverlayContainer val) {
        _push_front__Ogre_OverlayContainerP(this.object.pointer, val.getInstancePointer().pointer);
    }

    private native void _push_front__Ogre_OverlayContainerP(long _pointer_, long val);

    /** **/
    public void remove(org.ogre4j.IOverlayContainer val) {
        _remove__Ogre_OverlayContainerP(this.object.pointer, val.getInstancePointer().pointer);
    }

    private native void _remove__Ogre_OverlayContainerP(long _pointer_, long val);

    /** **/
    public void reverse() {
        _reverse(this.object.pointer);
    }

    private native void _reverse(long _pointer_);

    /** **/
    public int size() {
         return _size_const(this.object.pointer);
    }

    private native int _size_const(long _pointer_);

    /** **/
    public void unique() {
        _unique(this.object.pointer);
    }

    private native void _unique(long _pointer_);

}
public static class Overlay2DElementsIterator extends org.xbig.base.NativeObject implements org.ogre4j.IOverlay.IOverlay2DElementsIterator {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public Overlay2DElementsIterator(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected Overlay2DElementsIterator(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public Overlay2DElementsIterator(org.xbig.base.WithoutNativeObject val) {
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
    public Overlay2DElementsIterator(org.ogre4j.IOverlay.IOverlayContainerList c) {
         super( new org.xbig.base.InstancePointer(__createOverlay2DElementsIterator__Ogre_Overlay_OverlayContainerListr( c.getInstancePointer().pointer)), false);
    }

    private native static long __createOverlay2DElementsIterator__Ogre_Overlay_OverlayContainerListr(long c);

    /** **/
    public boolean hasMoreElements() {
         return _hasMoreElements_const(this.object.pointer);
    }

    private native boolean _hasMoreElements_const(long _pointer_);

    /** **/
    public org.ogre4j.IOverlayContainer getNext() {
         return new org.ogre4j.OverlayContainer(new InstancePointer(_getNext(this.object.pointer)));
    }

    private native long _getNext(long _pointer_);

    /** **/
    public org.ogre4j.IOverlayContainer peekNext() {
         return new org.ogre4j.OverlayContainer(new InstancePointer(_peekNext(this.object.pointer)));
    }

    private native long _peekNext(long _pointer_);

    /** **/
    public NativeObjectPointer<org.ogre4j.IOverlayContainer> peekNextPtr() {
         return new NativeObjectPointer<org.ogre4j.IOverlayContainer>(new InstancePointer(_peekNextPtr(this.object.pointer)));
    }

    private native long _peekNextPtr(long _pointer_);

    /** **/
    public void moveNext() {
        _moveNext(this.object.pointer);
    }

    private native void _moveNext(long _pointer_);

}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public Overlay(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected Overlay(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public Overlay(org.xbig.base.WithoutNativeObject val) {
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
    public Overlay(String name) {
         super( new org.xbig.base.InstancePointer(__createOverlay__StringR( name)), false);
    }

    private native static long __createOverlay__StringR(String name);

    /** **/
    public org.ogre4j.IOverlayContainer getChild(String name) {
         return new org.ogre4j.OverlayContainer(new InstancePointer(_getChild__StringR(this.object.pointer, name)));
    }

    private native long _getChild__StringR(long _pointer_, String name);

    /** 
    Gets the name of this overlay. **/
    public String getName() {
         return _getName_const(this.object.pointer);
    }

    private native String _getName_const(long _pointer_);

    /** 
    Alters the ZOrder of this overlay. **/
    public void setZOrder(int zorder) {
        _setZOrder__ushortv(this.object.pointer, zorder);
    }

    private native void _setZOrder__ushortv(long _pointer_, int zorder);

    /** 
    Gets the ZOrder of this overlay. **/
    public int getZOrder() {
         return _getZOrder_const(this.object.pointer);
    }

    private native int _getZOrder_const(long _pointer_);

    /** 
    Gets whether the overlay is displayed or not. **/
    public boolean isVisible() {
         return _isVisible_const(this.object.pointer);
    }

    private native boolean _isVisible_const(long _pointer_);

    /** 
    Gets whether the overlay is initialised or not. **/
    public boolean isInitialised() {
         return _isInitialised_const(this.object.pointer);
    }

    private native boolean _isInitialised_const(long _pointer_);

    /** 
    Shows the overlay if it was hidden. **/
    public void show() {
        _show(this.object.pointer);
    }

    private native void _show(long _pointer_);

    /** 
    Hides the overlay if it was visible. **/
    public void hide() {
        _hide(this.object.pointer);
    }

    private native void _hide(long _pointer_);

    /** 
    Adds a 2D 'container' to the overlay. **/
    public void add2D(org.ogre4j.IOverlayContainer cont) {
        _add2D__OverlayContainerp(this.object.pointer, cont.getInstancePointer().pointer);
    }

    private native void _add2D__OverlayContainerp(long _pointer_, long cont);

    /** 
    Removes a 2D container from the overlay. **/
    public void remove2D(org.ogre4j.IOverlayContainer cont) {
        _remove2D__OverlayContainerp(this.object.pointer, cont.getInstancePointer().pointer);
    }

    private native void _remove2D__OverlayContainerp(long _pointer_, long cont);

    /** 
    Adds a node capable of holding 3D objects to the overlay. **/
    public void add3D(org.ogre4j.ISceneNode node) {
        _add3D__SceneNodep(this.object.pointer, node.getInstancePointer().pointer);
    }

    private native void _add3D__SceneNodep(long _pointer_, long node);

    /** 
    Removes a 3D element from the overlay. **/
    public void remove3D(org.ogre4j.ISceneNode node) {
        _remove3D__SceneNodep(this.object.pointer, node.getInstancePointer().pointer);
    }

    private native void _remove3D__SceneNodep(long _pointer_, long node);

    /** 
    Clears the overlay of all attached items. **/
    public void clear() {
        _clear(this.object.pointer);
    }

    private native void _clear(long _pointer_);

    /** 
    Sets the scrolling factor of this overlay. **/
    public void setScroll(float x, float y) {
        _setScroll__RealvRealv(this.object.pointer, x,  y);
    }

    private native void _setScroll__RealvRealv(long _pointer_, float x, float y);

    /** 
    Gets the current X scroll value **/
    public float getScrollX() {
         return _getScrollX_const(this.object.pointer);
    }

    private native float _getScrollX_const(long _pointer_);

    /** 
    Gets the current Y scroll value **/
    public float getScrollY() {
         return _getScrollY_const(this.object.pointer);
    }

    private native float _getScrollY_const(long _pointer_);

    /** 
    Scrolls the overlay by the offsets provided. **/
    public void scroll(float xoff, float yoff) {
        _scroll__RealvRealv(this.object.pointer, xoff,  yoff);
    }

    private native void _scroll__RealvRealv(long _pointer_, float xoff, float yoff);

    /** 
    Sets the rotation applied to this overlay. **/
    public void setRotate(org.ogre4j.IRadian angle) {
        _setRotate__RadianR(this.object.pointer, angle.getInstancePointer().pointer);
    }

    private native void _setRotate__RadianR(long _pointer_, long angle);

    /** 
    Gets the rotation applied to this overlay, in degrees. **/
    public org.ogre4j.IRadian getRotate() {
         return new org.ogre4j.Radian(new InstancePointer(_getRotate_const(this.object.pointer)));
    }

    private native long _getRotate_const(long _pointer_);

    /** 
    Adds the passed in angle to the rotation applied to this overlay. **/
    public void rotate(org.ogre4j.IRadian angle) {
        _rotate__RadianR(this.object.pointer, angle.getInstancePointer().pointer);
    }

    private native void _rotate__RadianR(long _pointer_, long angle);

    /** 
    Sets the scaling factor of this overlay. **/
    public void setScale(float x, float y) {
        _setScale__RealvRealv(this.object.pointer, x,  y);
    }

    private native void _setScale__RealvRealv(long _pointer_, float x, float y);

    /** 
    Gets the current X scale value **/
    public float getScaleX() {
         return _getScaleX_const(this.object.pointer);
    }

    private native float _getScaleX_const(long _pointer_);

    /** 
    Gets the current Y scale value **/
    public float getScaleY() {
         return _getScaleY_const(this.object.pointer);
    }

    private native float _getScaleY_const(long _pointer_);

    /** 
    Used to transform the overlay when scrolling, scaling etc. **/
    public void _getWorldTransforms(org.ogre4j.IMatrix4 xform) {
        __getWorldTransforms__Matrix4p_const(this.object.pointer, xform.getInstancePointer().pointer);
    }

    private native void __getWorldTransforms__Matrix4p_const(long _pointer_, long xform);

    /** 
    Internal method to put the overlay contents onto the render queue. **/
    public void _findVisibleObjects(org.ogre4j.ICamera cam, org.ogre4j.IRenderQueue queue) {
        __findVisibleObjects__CamerapRenderQueuep(this.object.pointer, cam.getInstancePointer().pointer,  queue.getInstancePointer().pointer);
    }

    private native void __findVisibleObjects__CamerapRenderQueuep(long _pointer_, long cam, long queue);

    /** 
    This returns a  at position x,y. **/
    public org.ogre4j.IOverlayElement findElementAt(float x, float y) {
         return new org.ogre4j.OverlayElement(new InstancePointer(_findElementAt__RealvRealv(this.object.pointer, x,  y)));
    }

    private native long _findElementAt__RealvRealv(long _pointer_, float x, float y);

    /** **/
    public void get2DElementsIterator(org.ogre4j.IOverlay.IOverlay2DElementsIterator returnValue) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_get2DElementsIterator(this.object.pointer)), false);
    }

    private native long _get2DElementsIterator(long _pointer_);

    /** 
    Get the origin of this overlay, e.g. a script file name. **/
    public String getOrigin() {
         return _getOrigin_const(this.object.pointer);
    }

    private native String _getOrigin_const(long _pointer_);

    /** **/
    public void _notifyOrigin(String origin) {
        __notifyOrigin__StringR(this.object.pointer, origin);
    }

    private native void __notifyOrigin__StringR(long _pointer_, String origin);

}
