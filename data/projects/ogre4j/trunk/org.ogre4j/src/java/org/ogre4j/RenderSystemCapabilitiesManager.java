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
public class RenderSystemCapabilitiesManager extends org.xbig.base.NativeObject implements org.ogre4j.IRenderSystemCapabilitiesManager {
static { System.loadLibrary("ogre4j");}
protected static class CapabilitiesMap extends org.xbig.base.NativeObject implements org.ogre4j.IRenderSystemCapabilitiesManager.ICapabilitiesMap {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public CapabilitiesMap(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected CapabilitiesMap(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public CapabilitiesMap(org.xbig.base.WithoutNativeObject val) {
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
    public CapabilitiesMap() {
         super( new org.xbig.base.InstancePointer(__createCapabilitiesMap()), false);
    }

    private native static long __createCapabilitiesMap();

    /** **/
    public void clear() {
        _clear(this.object.pointer);
    }

    private native void _clear(long _pointer_);

    /** **/
    public int count(String key) {
         return _count__sR(this.object.pointer, key);
    }

    private native int _count__sR(long _pointer_, String key);

    /** **/
    public boolean empty() {
         return _empty_const(this.object.pointer);
    }

    private native boolean _empty_const(long _pointer_);

    /** **/
    public int erase(String key) {
         return _erase__sR(this.object.pointer, key);
    }

    private native int _erase__sR(long _pointer_, String key);

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
    public org.ogre4j.IRenderSystemCapabilities get(String key) {
         return new org.ogre4j.RenderSystemCapabilities(new InstancePointer(_get__sR(this.object.pointer, key)));
    }

    private native long _get__sR(long _pointer_, String key);

    /** **/
    public void insert(String key, org.ogre4j.IRenderSystemCapabilities value) {
        _insert__sROgre_RenderSystemCapabilitiesp(this.object.pointer, key,  value.getInstancePointer().pointer);
    }

    private native void _insert__sROgre_RenderSystemCapabilitiesp(long _pointer_, String key, long value);

}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public RenderSystemCapabilitiesManager(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected RenderSystemCapabilitiesManager(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public RenderSystemCapabilitiesManager(org.xbig.base.WithoutNativeObject val) {
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
    Default constructor. **/
    public RenderSystemCapabilitiesManager() {
         super( new org.xbig.base.InstancePointer(__createRenderSystemCapabilitiesManager()), false);
    }

    private native static long __createRenderSystemCapabilitiesManager();

    /** 
    **/
    public void parseCapabilitiesFromArchive(String filename, String archiveType, boolean recursive) {
        _parseCapabilitiesFromArchive__StringRStringRbv(this.object.pointer, filename,  archiveType,  recursive);
    }

    private native void _parseCapabilitiesFromArchive__StringRStringRbv(long _pointer_, String filename, String archiveType, boolean recursive);

    /** 
    Returns a capability loaded with  method **/
    public org.ogre4j.IRenderSystemCapabilities loadParsedCapabilities(String name) {
         return new org.ogre4j.RenderSystemCapabilities(new InstancePointer(_loadParsedCapabilities__StringR(this.object.pointer, name)));
    }

    private native long _loadParsedCapabilities__StringR(long _pointer_, String name);

    /** 
    Method used by **/
    public void _addRenderSystemCapabilities(String name, org.ogre4j.IRenderSystemCapabilities caps) {
        __addRenderSystemCapabilities__StringRRenderSystemCapabilitiesp(this.object.pointer, name,  caps.getInstancePointer().pointer);
    }

    private native void __addRenderSystemCapabilities__StringRRenderSystemCapabilitiesp(long _pointer_, String name, long caps);

    /** 
    Override standard  retrieval. **/
    public static org.ogre4j.IRenderSystemCapabilitiesManager getSingleton() {
         return new org.ogre4j.RenderSystemCapabilitiesManager(new InstancePointer(_getSingleton()));
    }

    private native static long _getSingleton();

    /** 
    Override standard  retrieval. **/
    public static org.ogre4j.IRenderSystemCapabilitiesManager getSingletonPtr() {
         return new org.ogre4j.RenderSystemCapabilitiesManager(new InstancePointer(_getSingletonPtr()));
    }

    private native static long _getSingletonPtr();

}
