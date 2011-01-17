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
public class CompositorChain extends org.xbig.base.NativeObject implements org.ogre4j.ICompositorChain {
static { System.loadLibrary("ogre4j");}
protected static class RQListener extends org.xbig.base.NativeObject implements org.ogre4j.ICompositorChain.IRQListener {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public RQListener(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected RQListener(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public RQListener(org.xbig.base.WithoutNativeObject val) {
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
    **/
    public void renderQueueStarted(short id, String invocation, BooleanPointer skipThisQueue) {
        _renderQueueStarted__uint8vStringRbr(this.object.pointer, id,  invocation,  skipThisQueue.object.pointer);
    }

    private native void _renderQueueStarted__uint8vStringRbr(long _pointer_, short id, String invocation, long skipThisQueue);

    /** 
    **/
    public void renderQueueEnded(short id, String invocation, BooleanPointer repeatThisQueue) {
        _renderQueueEnded__uint8vStringRbr(this.object.pointer, id,  invocation,  repeatThisQueue.object.pointer);
    }

    private native void _renderQueueEnded__uint8vStringRbr(long _pointer_, short id, String invocation, long repeatThisQueue);

    /** 
    Set current operation and target **/
    public void setOperation(org.ogre4j.ICompositorInstance.ITargetOperation op, org.ogre4j.ISceneManager sm, org.ogre4j.IRenderSystem rs) {
        _setOperation__CompositorInstance_TargetOperationpSceneManagerpRenderSystemp(this.object.pointer, op.getInstancePointer().pointer,  sm.getInstancePointer().pointer,  rs.getInstancePointer().pointer);
    }

    private native void _setOperation__CompositorInstance_TargetOperationpSceneManagerpRenderSystemp(long _pointer_, long op, long sm, long rs);

    /** 
    Notify current destination viewport **/
    public void notifyViewport(org.ogre4j.IViewport vp) {
        _notifyViewport__Viewportp(this.object.pointer, vp.getInstancePointer().pointer);
    }

    private native void _notifyViewport__Viewportp(long _pointer_, long vp);

    /** 
    Flush remaining render system operations **/
    public void flushUpTo(short id) {
        _flushUpTo__uint8v(this.object.pointer, id);
    }

    private native void _flushUpTo__uint8v(long _pointer_, short id);

    /** **/
    public RQListener() {
         super( new org.xbig.base.InstancePointer(__createRQListener()), false);
    }

    private native static long __createRQListener();

}
public static class Instances extends org.xbig.base.NativeObject implements org.ogre4j.ICompositorChain.IInstances {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public Instances(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected Instances(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public Instances(org.xbig.base.WithoutNativeObject val) {
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
    public Instances() {
         super( new org.xbig.base.InstancePointer(__createInstances()), false);
    }

    private native static long __createInstances();

    /** **/
    public void assign(int num, org.ogre4j.ICompositorInstance val) {
        _assign__ivOgre_CompositorInstanceP(this.object.pointer, num,  val.getInstancePointer().pointer);
    }

    private native void _assign__ivOgre_CompositorInstanceP(long _pointer_, int num, long val);

    /** **/
    public org.ogre4j.ICompositorInstance at(int loc) {
         return new org.ogre4j.CompositorInstance(new InstancePointer(_at__iv(this.object.pointer, loc)));
    }

    private native long _at__iv(long _pointer_, int loc);

    /** **/
    public org.ogre4j.ICompositorInstance back() {
         return new org.ogre4j.CompositorInstance(new InstancePointer(_back(this.object.pointer)));
    }

    private native long _back(long _pointer_);

    /** **/
    public int capacity() {
         return _capacity(this.object.pointer);
    }

    private native int _capacity(long _pointer_);

    /** **/
    public void clear() {
        _clear(this.object.pointer);
    }

    private native void _clear(long _pointer_);

    /** **/
    public boolean empty() {
         return _empty(this.object.pointer);
    }

    private native boolean _empty(long _pointer_);

    /** **/
    public org.ogre4j.ICompositorInstance front() {
         return new org.ogre4j.CompositorInstance(new InstancePointer(_front(this.object.pointer)));
    }

    private native long _front(long _pointer_);

    /** **/
    public int max_size() {
         return _max_size(this.object.pointer);
    }

    private native int _max_size(long _pointer_);

    /** **/
    public void pop_back() {
        _pop_back(this.object.pointer);
    }

    private native void _pop_back(long _pointer_);

    /** **/
    public void push_back(org.ogre4j.ICompositorInstance val) {
        _push_back__Ogre_CompositorInstanceP(this.object.pointer, val.getInstancePointer().pointer);
    }

    private native void _push_back__Ogre_CompositorInstanceP(long _pointer_, long val);

    /** **/
    public void reserve(int size) {
        _reserve__iV(this.object.pointer, size);
    }

    private native void _reserve__iV(long _pointer_, int size);

    /** **/
    public int size() {
         return _size(this.object.pointer);
    }

    private native int _size(long _pointer_);

}
public static class InstanceIterator extends org.xbig.base.NativeObject implements org.ogre4j.ICompositorChain.IInstanceIterator {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public InstanceIterator(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected InstanceIterator(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public InstanceIterator(org.xbig.base.WithoutNativeObject val) {
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
    public InstanceIterator(org.ogre4j.ICompositorChain.IInstances c) {
         super( new org.xbig.base.InstancePointer(__createInstanceIterator__Ogre_CompositorChain_Instancesr( c.getInstancePointer().pointer)), false);
    }

    private native static long __createInstanceIterator__Ogre_CompositorChain_Instancesr(long c);

    /** **/
    public boolean hasMoreElements() {
         return _hasMoreElements_const(this.object.pointer);
    }

    private native boolean _hasMoreElements_const(long _pointer_);

    /** **/
    public org.ogre4j.ICompositorInstance getNext() {
         return new org.ogre4j.CompositorInstance(new InstancePointer(_getNext(this.object.pointer)));
    }

    private native long _getNext(long _pointer_);

    /** **/
    public org.ogre4j.ICompositorInstance peekNext() {
         return new org.ogre4j.CompositorInstance(new InstancePointer(_peekNext(this.object.pointer)));
    }

    private native long _peekNext(long _pointer_);

    /** **/
    public NativeObjectPointer<org.ogre4j.ICompositorInstance> peekNextPtr() {
         return new NativeObjectPointer<org.ogre4j.ICompositorInstance>(new InstancePointer(_peekNextPtr(this.object.pointer)));
    }

    private native long _peekNextPtr(long _pointer_);

    /** **/
    public void moveNext() {
        _moveNext(this.object.pointer);
    }

    private native void _moveNext(long _pointer_);

}
protected static class RenderSystemOperations extends org.xbig.base.NativeObject implements org.ogre4j.ICompositorChain.IRenderSystemOperations {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public RenderSystemOperations(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected RenderSystemOperations(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public RenderSystemOperations(org.xbig.base.WithoutNativeObject val) {
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
    public RenderSystemOperations() {
         super( new org.xbig.base.InstancePointer(__createRenderSystemOperations()), false);
    }

    private native static long __createRenderSystemOperations();

    /** **/
    public void assign(int num, org.ogre4j.ICompositorInstance.IRenderSystemOperation val) {
        _assign__ivOgre_CompositorInstance_RenderSystemOperationP(this.object.pointer, num,  val.getInstancePointer().pointer);
    }

    private native void _assign__ivOgre_CompositorInstance_RenderSystemOperationP(long _pointer_, int num, long val);

    /** **/
    public org.ogre4j.ICompositorInstance.IRenderSystemOperation at(int loc) {
         return new org.ogre4j.CompositorInstance.RenderSystemOperation(new InstancePointer(_at__iv(this.object.pointer, loc)));
    }

    private native long _at__iv(long _pointer_, int loc);

    /** **/
    public org.ogre4j.ICompositorInstance.IRenderSystemOperation back() {
         return new org.ogre4j.CompositorInstance.RenderSystemOperation(new InstancePointer(_back(this.object.pointer)));
    }

    private native long _back(long _pointer_);

    /** **/
    public int capacity() {
         return _capacity(this.object.pointer);
    }

    private native int _capacity(long _pointer_);

    /** **/
    public void clear() {
        _clear(this.object.pointer);
    }

    private native void _clear(long _pointer_);

    /** **/
    public boolean empty() {
         return _empty(this.object.pointer);
    }

    private native boolean _empty(long _pointer_);

    /** **/
    public org.ogre4j.ICompositorInstance.IRenderSystemOperation front() {
         return new org.ogre4j.CompositorInstance.RenderSystemOperation(new InstancePointer(_front(this.object.pointer)));
    }

    private native long _front(long _pointer_);

    /** **/
    public int max_size() {
         return _max_size(this.object.pointer);
    }

    private native int _max_size(long _pointer_);

    /** **/
    public void pop_back() {
        _pop_back(this.object.pointer);
    }

    private native void _pop_back(long _pointer_);

    /** **/
    public void push_back(org.ogre4j.ICompositorInstance.IRenderSystemOperation val) {
        _push_back__Ogre_CompositorInstance_RenderSystemOperationP(this.object.pointer, val.getInstancePointer().pointer);
    }

    private native void _push_back__Ogre_CompositorInstance_RenderSystemOperationP(long _pointer_, long val);

    /** **/
    public void reserve(int size) {
        _reserve__iV(this.object.pointer, size);
    }

    private native void _reserve__iV(long _pointer_, int size);

    /** **/
    public int size() {
         return _size(this.object.pointer);
    }

    private native int _size(long _pointer_);

}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public CompositorChain(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected CompositorChain(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public CompositorChain(org.xbig.base.WithoutNativeObject val) {
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
    public CompositorChain(org.ogre4j.IViewport vp) {
         super( new org.xbig.base.InstancePointer(__createCompositorChain__Viewportp( vp.getInstancePointer().pointer)), false);
    }

    private native static long __createCompositorChain__Viewportp(long vp);

    /** 
    Apply a compositor. Initially, the filter is enabled. **/
    public org.ogre4j.ICompositorInstance addCompositor(org.ogre4j.ICompositorPtr filter, int addPosition, int technique) {
         return new org.ogre4j.CompositorInstance(new InstancePointer(_addCompositor__CompositorPtrviviv(this.object.pointer, filter.getInstancePointer().pointer,  addPosition,  technique)));
    }

    private native long _addCompositor__CompositorPtrviviv(long _pointer_, long filter, int addPosition, int technique);

    /** 
    Remove a compositor. **/
    public void removeCompositor(int position) {
        _removeCompositor__iv(this.object.pointer, position);
    }

    private native void _removeCompositor__iv(long _pointer_, int position);

    /** 
    Get the number of compositors. **/
    public int getNumCompositors() {
         return _getNumCompositors(this.object.pointer);
    }

    private native int _getNumCompositors(long _pointer_);

    /** 
    Remove all compositors. **/
    public void removeAllCompositors() {
        _removeAllCompositors(this.object.pointer);
    }

    private native void _removeAllCompositors(long _pointer_);

    /** 
    Get compositor instance by position. **/
    public org.ogre4j.ICompositorInstance getCompositor(int index) {
         return new org.ogre4j.CompositorInstance(new InstancePointer(_getCompositor__iv(this.object.pointer, index)));
    }

    private native long _getCompositor__iv(long _pointer_, int index);

    /** 
    Get the original scene compositor instance for this chain (internal use). **/
    public org.ogre4j.ICompositorInstance _getOriginalSceneCompositor() {
         return new org.ogre4j.CompositorInstance(new InstancePointer(__getOriginalSceneCompositor(this.object.pointer)));
    }

    private native long __getOriginalSceneCompositor(long _pointer_);

    /** 
    Get an iterator over the compositor instances. The first compositor in this list is applied first, the last one is applied last. **/
    public void getCompositors(org.ogre4j.ICompositorChain.IInstanceIterator returnValue) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getCompositors(this.object.pointer)), false);
    }

    private native long _getCompositors(long _pointer_);

    /** 
    Enable or disable a compositor, by position. Disabling a compositor stops it from rendering but does not free any resources. This can be more efficient than using removeCompositor and addCompositor in cases the filter is switched on and off a lot. **/
    public void setCompositorEnabled(int position, boolean state) {
        _setCompositorEnabled__ivbv(this.object.pointer, position,  state);
    }

    private native void _setCompositorEnabled__ivbv(long _pointer_, int position, boolean state);

    /** 
    **/
    public void preRenderTargetUpdate(org.ogre4j.IRenderTargetEvent evt) {
        _preRenderTargetUpdate__RenderTargetEventR(this.object.pointer, evt.getInstancePointer().pointer);
    }

    private native void _preRenderTargetUpdate__RenderTargetEventR(long _pointer_, long evt);

    /** 
    **/
    public void preViewportUpdate(org.ogre4j.IRenderTargetViewportEvent evt) {
        _preViewportUpdate__RenderTargetViewportEventR(this.object.pointer, evt.getInstancePointer().pointer);
    }

    private native void _preViewportUpdate__RenderTargetViewportEventR(long _pointer_, long evt);

    /** 
    **/
    public void postViewportUpdate(org.ogre4j.IRenderTargetViewportEvent evt) {
        _postViewportUpdate__RenderTargetViewportEventR(this.object.pointer, evt.getInstancePointer().pointer);
    }

    private native void _postViewportUpdate__RenderTargetViewportEventR(long _pointer_, long evt);

    /** 
    **/
    public void viewportRemoved(org.ogre4j.IRenderTargetViewportEvent evt) {
        _viewportRemoved__RenderTargetViewportEventR(this.object.pointer, evt.getInstancePointer().pointer);
    }

    private native void _viewportRemoved__RenderTargetViewportEventR(long _pointer_, long evt);

    /** 
    Mark state as dirty, and to be recompiled next frame. **/
    public void _markDirty() {
        __markDirty(this.object.pointer);
    }

    private native void __markDirty(long _pointer_);

    /** 
    Get viewport that is the target of this chain **/
    public org.ogre4j.IViewport getViewport() {
         return new org.ogre4j.Viewport(new InstancePointer(_getViewport(this.object.pointer)));
    }

    private native long _getViewport(long _pointer_);

    /** 
    Internal method for reconnecting with viewport **/
    public void _notifyViewport(org.ogre4j.IViewport vp) {
        __notifyViewport__Viewportp(this.object.pointer, vp.getInstancePointer().pointer);
    }

    private native void __notifyViewport__Viewportp(long _pointer_, long vp);

    /** 
    Remove a compositor by pointer. This is internally used by  to "weak" remove any instanced of a deleted technique. **/
    public void _removeInstance(org.ogre4j.ICompositorInstance i) {
        __removeInstance__CompositorInstancep(this.object.pointer, i.getInstancePointer().pointer);
    }

    private native void __removeInstance__CompositorInstancep(long _pointer_, long i);

    /** 
    Internal method for registering a queued operation for deletion later **/
    public void _queuedOperation(org.ogre4j.ICompositorInstance.IRenderSystemOperation op) {
        __queuedOperation__CompositorInstance_RenderSystemOperationp(this.object.pointer, op.getInstancePointer().pointer);
    }

    private native void __queuedOperation__CompositorInstance_RenderSystemOperationp(long _pointer_, long op);

    /** 
    Compile this Composition chain into a series of  operations. **/
    public void _compile() {
        __compile(this.object.pointer);
    }

    private native void __compile(long _pointer_);

    /** 
    Called just after a  has been rendered to. **/
    public void postRenderTargetUpdate(org.ogre4j.IRenderTargetEvent evt) {
        _postRenderTargetUpdate__RenderTargetEventR(this.object.pointer, evt.getInstancePointer().pointer);
    }

    private native void _postRenderTargetUpdate__RenderTargetEventR(long _pointer_, long evt);

    /** 
    Called to notify listener that a  has been added to the target in question. **/
    public void viewportAdded(org.ogre4j.IRenderTargetViewportEvent evt) {
        _viewportAdded__RenderTargetViewportEventR(this.object.pointer, evt.getInstancePointer().pointer);
    }

    private native void _viewportAdded__RenderTargetViewportEventR(long _pointer_, long evt);

    /** **/
    public static int getLAST() {
         return _getLAST();
    }

    private native static int _getLAST();

    /** **/
    public static int getBEST() {
         return _getBEST();
    }

    private native static int _getBEST();

}
