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
public class RenderQueueGroup extends org.xbig.base.NativeObject implements org.ogre4j.IRenderQueueGroup {
static { System.loadLibrary("ogre4j");}
public static class PriorityMap extends org.xbig.base.NativeObject implements org.ogre4j.IRenderQueueGroup.IPriorityMap {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public PriorityMap(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected PriorityMap(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public PriorityMap(org.xbig.base.WithoutNativeObject val) {
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
    public PriorityMap() {
         super( new org.xbig.base.InstancePointer(__createPriorityMap()), false);
    }

    private native static long __createPriorityMap();

    /** **/
    public void clear() {
        _clear(this.object.pointer);
    }

    private native void _clear(long _pointer_);

    /** **/
    public int count(int key) {
         return _count__HR(this.object.pointer, key);
    }

    private native int _count__HR(long _pointer_, int key);

    /** **/
    public boolean empty() {
         return _empty_const(this.object.pointer);
    }

    private native boolean _empty_const(long _pointer_);

    /** **/
    public int erase(int key) {
         return _erase__HR(this.object.pointer, key);
    }

    private native int _erase__HR(long _pointer_, int key);

    /** **/
    public int max_size() {
         return _max_size_const(this.object.pointer);
    }

    private native int _max_size_const(long _pointer_);

    /** **/
    public int size() {
         return _size_const(this.object.pointer);
    }

    private native int _size_const(long _pointer_);

    /** **/
    public org.ogre4j.IRenderPriorityGroup get(int key) {
         return new org.ogre4j.RenderPriorityGroup(new InstancePointer(_get__HR(this.object.pointer, key)));
    }

    private native long _get__HR(long _pointer_, int key);

    /** **/
    public void insert(int key, org.ogre4j.IRenderPriorityGroup value) {
        _insert__HROgre_RenderPriorityGroupp(this.object.pointer, key,  value.getInstancePointer().pointer);
    }

    private native void _insert__HROgre_RenderPriorityGroupp(long _pointer_, int key, long value);

}
public static class PriorityMapIterator extends org.xbig.base.NativeObject implements org.ogre4j.IRenderQueueGroup.IPriorityMapIterator {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public PriorityMapIterator(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected PriorityMapIterator(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public PriorityMapIterator(org.xbig.base.WithoutNativeObject val) {
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
    public PriorityMapIterator(org.ogre4j.IRenderQueueGroup.IPriorityMap c) {
         super( new org.xbig.base.InstancePointer(__createPriorityMapIterator__Ogre_RenderQueueGroup_PriorityMapr( c.getInstancePointer().pointer)), false);
    }

    private native static long __createPriorityMapIterator__Ogre_RenderQueueGroup_PriorityMapr(long c);

    /** **/
    public boolean hasMoreElements() {
         return _hasMoreElements_const(this.object.pointer);
    }

    private native boolean _hasMoreElements_const(long _pointer_);

    /** **/
    public org.ogre4j.IRenderPriorityGroup getNext() {
         return new org.ogre4j.RenderPriorityGroup(new InstancePointer(_getNext(this.object.pointer)));
    }

    private native long _getNext(long _pointer_);

    /** **/
    public org.ogre4j.IRenderPriorityGroup peekNextValue() {
         return new org.ogre4j.RenderPriorityGroup(new InstancePointer(_peekNextValue(this.object.pointer)));
    }

    private native long _peekNextValue(long _pointer_);

    /** **/
    public int peekNextKey() {
         return _peekNextKey(this.object.pointer);
    }

    private native int _peekNextKey(long _pointer_);

    /** **/
    public org.ogre4j.IRenderQueueGroup.IPriorityMapIterator operatorAssignment(org.ogre4j.IRenderQueueGroup.IPriorityMapIterator rhs) {
         return new org.ogre4j.RenderQueueGroup.PriorityMapIterator(new InstancePointer(_operatorAssignment___Ogre_RenderQueueGroup_PriorityMapIteratorr(this.object.pointer, rhs.getInstancePointer().pointer)));
    }

    private native long _operatorAssignment___Ogre_RenderQueueGroup_PriorityMapIteratorr(long _pointer_, long rhs);

    /** **/
    public NativeObjectPointer<org.ogre4j.IRenderPriorityGroup> peekNextValuePtr() {
         return new NativeObjectPointer<org.ogre4j.IRenderPriorityGroup>(new InstancePointer(_peekNextValuePtr(this.object.pointer)));
    }

    private native long _peekNextValuePtr(long _pointer_);

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
	public RenderQueueGroup(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected RenderQueueGroup(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public RenderQueueGroup(org.xbig.base.WithoutNativeObject val) {
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
    public RenderQueueGroup(org.ogre4j.IRenderQueue parent, boolean splitPassesByLightingType, boolean splitNoShadowPasses, boolean shadowCastersNotReceivers) {
         super( new org.xbig.base.InstancePointer(__createRenderQueueGroup__RenderQueuepbvbvbv( parent.getInstancePointer().pointer,  splitPassesByLightingType,  splitNoShadowPasses,  shadowCastersNotReceivers)), false);
    }

    private native static long __createRenderQueueGroup__RenderQueuepbvbvbv(long parent, boolean splitPassesByLightingType, boolean splitNoShadowPasses, boolean shadowCastersNotReceivers);

    /** 
    Get an iterator for browsing through child contents. **/
    public void getIterator(org.ogre4j.IRenderQueueGroup.IPriorityMapIterator returnValue) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getIterator(this.object.pointer)), false);
    }

    private native long _getIterator(long _pointer_);

    /** 
    Add a renderable to this group, with the given priority. **/
    public void addRenderable(org.ogre4j.IRenderable pRend, org.ogre4j.ITechnique pTech, int priority) {
        _addRenderable__RenderablepTechniquepushortv(this.object.pointer, pRend.getInstancePointer().pointer,  pTech.getInstancePointer().pointer,  priority);
    }

    private native void _addRenderable__RenderablepTechniquepushortv(long _pointer_, long pRend, long pTech, int priority);

    /** 
    Clears this group of renderables. **/
    public void clear(boolean destroy) {
        _clear__bv(this.object.pointer, destroy);
    }

    private native void _clear__bv(long _pointer_, boolean destroy);

    /** 
    Indicate whether a given queue group will be doing any shadow setup. **/
    public void setShadowsEnabled(boolean enabled) {
        _setShadowsEnabled__bv(this.object.pointer, enabled);
    }

    private native void _setShadowsEnabled__bv(long _pointer_, boolean enabled);

    /** 
    Are shadows enabled for this queue? **/
    public boolean getShadowsEnabled() {
         return _getShadowsEnabled_const(this.object.pointer);
    }

    private native boolean _getShadowsEnabled_const(long _pointer_);

    /** 
    Sets whether or not the queue will split passes by their lighting type, ie ambient, per-light and decal. **/
    public void setSplitPassesByLightingType(boolean split) {
        _setSplitPassesByLightingType__bv(this.object.pointer, split);
    }

    private native void _setSplitPassesByLightingType__bv(long _pointer_, boolean split);

    /** 
    Sets whether or not the queue will split passes which have shadow receive turned off (in their parent material), which is needed when certain shadow techniques are used. **/
    public void setSplitNoShadowPasses(boolean split) {
        _setSplitNoShadowPasses__bv(this.object.pointer, split);
    }

    private native void _setSplitNoShadowPasses__bv(long _pointer_, boolean split);

    /** 
    Sets whether or not objects which cast shadows should be treated as never receiving shadows. **/
    public void setShadowCastersCannotBeReceivers(boolean ind) {
        _setShadowCastersCannotBeReceivers__bv(this.object.pointer, ind);
    }

    private native void _setShadowCastersCannotBeReceivers__bv(long _pointer_, boolean ind);

    /** 
    Reset the organisation modes required for the solids in this group. **/
    public void resetOrganisationModes() {
        _resetOrganisationModes(this.object.pointer);
    }

    private native void _resetOrganisationModes(long _pointer_);

    /** 
    Add a required sorting / grouping mode for the solids in this group. **/
    public void addOrganisationMode(org.ogre4j.QueuedRenderableCollection.OrganisationMode om) {
        _addOrganisationMode__QueuedRenderableCollection_OrganisationModev(this.object.pointer, om.getValue());
    }

    private native void _addOrganisationMode__QueuedRenderableCollection_OrganisationModev(long _pointer_, int om);

    /** 
    Setthe sorting / grouping mode for the solids in this group to the default. **/
    public void defaultOrganisationMode() {
        _defaultOrganisationMode(this.object.pointer);
    }

    private native void _defaultOrganisationMode(long _pointer_);

}
