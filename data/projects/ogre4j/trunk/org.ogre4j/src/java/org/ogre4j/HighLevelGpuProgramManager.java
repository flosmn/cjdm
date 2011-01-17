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
public class HighLevelGpuProgramManager extends org.xbig.base.NativeObject implements org.ogre4j.IHighLevelGpuProgramManager {
static { System.loadLibrary("ogre4j");}
public static class FactoryMap extends org.xbig.base.NativeObject implements org.ogre4j.IHighLevelGpuProgramManager.IFactoryMap {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public FactoryMap(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected FactoryMap(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public FactoryMap(org.xbig.base.WithoutNativeObject val) {
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
    public FactoryMap() {
         super( new org.xbig.base.InstancePointer(__createFactoryMap()), false);
    }

    private native static long __createFactoryMap();

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
    public org.ogre4j.IHighLevelGpuProgramFactory get(String key) {
         return new org.ogre4j.HighLevelGpuProgramFactory(new InstancePointer(_get__sR(this.object.pointer, key)));
    }

    private native long _get__sR(long _pointer_, String key);

    /** **/
    public void insert(String key, org.ogre4j.IHighLevelGpuProgramFactory value) {
        _insert__sROgre_HighLevelGpuProgramFactoryp(this.object.pointer, key,  value.getInstancePointer().pointer);
    }

    private native void _insert__sROgre_HighLevelGpuProgramFactoryp(long _pointer_, String key, long value);

}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public HighLevelGpuProgramManager(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected HighLevelGpuProgramManager(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public HighLevelGpuProgramManager(org.xbig.base.WithoutNativeObject val) {
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
    public HighLevelGpuProgramManager() {
         super( new org.xbig.base.InstancePointer(__createHighLevelGpuProgramManager()), false);
    }

    private native static long __createHighLevelGpuProgramManager();

    /** 
    Add a new factory object for high-level programs of a given language. **/
    public void addFactory(org.ogre4j.IHighLevelGpuProgramFactory factory) {
        _addFactory__HighLevelGpuProgramFactoryp(this.object.pointer, factory.getInstancePointer().pointer);
    }

    private native void _addFactory__HighLevelGpuProgramFactoryp(long _pointer_, long factory);

    /** 
    Remove a factory object for high-level programs of a given language. **/
    public void removeFactory(org.ogre4j.IHighLevelGpuProgramFactory factory) {
        _removeFactory__HighLevelGpuProgramFactoryp(this.object.pointer, factory.getInstancePointer().pointer);
    }

    private native void _removeFactory__HighLevelGpuProgramFactoryp(long _pointer_, long factory);

    /** 
    Create a new, unloaded . **/
    public void createProgram(org.ogre4j.IHighLevelGpuProgramPtr returnValue, String name, String groupName, String language, org.ogre4j.GpuProgramType gptype) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_createProgram__StringRStringRStringRGpuProgramTypev(this.object.pointer, name,  groupName,  language,  gptype.getValue())), false);
    }

    private native long _createProgram__StringRStringRStringRGpuProgramTypev(long _pointer_, String name, String groupName, String language, int gptype);

    /** 
    Override standard  retrieval. **/
    public static org.ogre4j.IHighLevelGpuProgramManager getSingleton() {
         return new org.ogre4j.HighLevelGpuProgramManager(new InstancePointer(_getSingleton()));
    }

    private native static long _getSingleton();

    /** 
    Override standard  retrieval. **/
    public static org.ogre4j.IHighLevelGpuProgramManager getSingletonPtr() {
         return new org.ogre4j.HighLevelGpuProgramManager(new InstancePointer(_getSingletonPtr()));
    }

    private native static long _getSingletonPtr();

    /** 
    Creates a new blank resource, but does not immediately load it. **/
    public void create(org.ogre4j.IResourcePtr returnValue, String name, String group, boolean isManual, org.ogre4j.IManualResourceLoader loader, org.ogre4j.INameValuePairList createParams) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_create__StringRStringRbvManualResourceLoaderpNameValuePairListP(this.object.pointer, name,  group,  isManual,  loader.getInstancePointer().pointer,  createParams.getInstancePointer().pointer)), false);
    }

    private native long _create__StringRStringRbvManualResourceLoaderpNameValuePairListP(long _pointer_, String name, String group, boolean isManual, long loader, long createParams);

    /** 
    Create a new resource, or retrieve an existing one with the same name if it already exists. **/
    public void createOrRetrieve(org.ogre4j.IResourceManager.IResourceCreateOrRetrieveResult returnValue, String name, String group, boolean isManual, org.ogre4j.IManualResourceLoader loader, org.ogre4j.INameValuePairList createParams) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_createOrRetrieve__StringRStringRbvManualResourceLoaderpNameValuePairListP(this.object.pointer, name,  group,  isManual,  loader.getInstancePointer().pointer,  createParams.getInstancePointer().pointer)), false);
    }

    private native long _createOrRetrieve__StringRStringRbvManualResourceLoaderpNameValuePairListP(long _pointer_, String name, String group, boolean isManual, long loader, long createParams);

    /** 
    Set a limit on the amount of memory this resource handler may use. **/
    public void setMemoryBudget(int bytes) {
        _setMemoryBudget__iv(this.object.pointer, bytes);
    }

    private native void _setMemoryBudget__iv(long _pointer_, int bytes);

    /** 
    Get the limit on the amount of memory this resource handler may use. **/
    public int getMemoryBudget() {
         return _getMemoryBudget_const(this.object.pointer);
    }

    private native int _getMemoryBudget_const(long _pointer_);

    /** 
    Gets the current memory usage, in bytes. **/
    public int getMemoryUsage() {
         return _getMemoryUsage_const(this.object.pointer);
    }

    private native int _getMemoryUsage_const(long _pointer_);

    /** 
    Unloads a single resource by name. **/
    public void unload(String name) {
        _unload__StringR(this.object.pointer, name);
    }

    private native void _unload__StringR(long _pointer_, String name);

    /** 
    Unloads a single resource by handle. **/
    public void unload(long handle) {
        _unload__ResourceHandlev(this.object.pointer, handle);
    }

    private native void _unload__ResourceHandlev(long _pointer_, long handle);

    /** 
    Unloads all resources. **/
    public void unloadAll(boolean reloadableOnly) {
        _unloadAll__bv(this.object.pointer, reloadableOnly);
    }

    private native void _unloadAll__bv(long _pointer_, boolean reloadableOnly);

    /** 
    Caused all currently loaded resources to be reloaded. **/
    public void reloadAll(boolean reloadableOnly) {
        _reloadAll__bv(this.object.pointer, reloadableOnly);
    }

    private native void _reloadAll__bv(long _pointer_, boolean reloadableOnly);

    /** 
    Unload all resources which are not referenced by any other object. **/
    public void unloadUnreferencedResources(boolean reloadableOnly) {
        _unloadUnreferencedResources__bv(this.object.pointer, reloadableOnly);
    }

    private native void _unloadUnreferencedResources__bv(long _pointer_, boolean reloadableOnly);

    /** 
    Caused all currently loaded but not referenced by any other object resources to be reloaded. **/
    public void reloadUnreferencedResources(boolean reloadableOnly) {
        _reloadUnreferencedResources__bv(this.object.pointer, reloadableOnly);
    }

    private native void _reloadUnreferencedResources__bv(long _pointer_, boolean reloadableOnly);

    /** 
    Remove a single resource. **/
    public void remove(org.ogre4j.IResourcePtr r) {
        _remove__ResourcePtrr(this.object.pointer, r.getInstancePointer().pointer);
    }

    private native void _remove__ResourcePtrr(long _pointer_, long r);

    /** 
    Remove a single resource by name. **/
    public void remove(String name) {
        _remove__StringR(this.object.pointer, name);
    }

    private native void _remove__StringR(long _pointer_, String name);

    /** 
    Remove a single resource by handle. **/
    public void remove(long handle) {
        _remove__ResourceHandlev(this.object.pointer, handle);
    }

    private native void _remove__ResourceHandlev(long _pointer_, long handle);

    /** 
    Removes all resources. **/
    public void removeAll() {
        _removeAll(this.object.pointer);
    }

    private native void _removeAll(long _pointer_);

    /** 
    Retrieves a pointer to a resource by name, or null if the resource does not exist. **/
    public void getByName(org.ogre4j.IResourcePtr returnValue, String name) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getByName__StringR(this.object.pointer, name)), false);
    }

    private native long _getByName__StringR(long _pointer_, String name);

    /** 
    Retrieves a pointer to a resource by handle, or null if the resource does not exist. **/
    public void getByHandle(org.ogre4j.IResourcePtr returnValue, long handle) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getByHandle__ResourceHandlev(this.object.pointer, handle)), false);
    }

    private native long _getByHandle__ResourceHandlev(long _pointer_, long handle);

    /** **/
    public boolean resourceExists(String name) {
         return _resourceExists__StringR(this.object.pointer, name);
    }

    private native boolean _resourceExists__StringR(long _pointer_, String name);

    /** **/
    public boolean resourceExists(long handle) {
         return _resourceExists__ResourceHandlev(this.object.pointer, handle);
    }

    private native boolean _resourceExists__ResourceHandlev(long _pointer_, long handle);

    /** 
    Notify this manager that a resource which it manages has been 'touched', i.e. used. **/
    public void _notifyResourceTouched(org.ogre4j.IResource res) {
        __notifyResourceTouched__Resourcep(this.object.pointer, res.getInstancePointer().pointer);
    }

    private native void __notifyResourceTouched__Resourcep(long _pointer_, long res);

    /** 
    Notify this manager that a resource which it manages has been loaded. **/
    public void _notifyResourceLoaded(org.ogre4j.IResource res) {
        __notifyResourceLoaded__Resourcep(this.object.pointer, res.getInstancePointer().pointer);
    }

    private native void __notifyResourceLoaded__Resourcep(long _pointer_, long res);

    /** 
    Notify this manager that a resource which it manages has been unloaded. **/
    public void _notifyResourceUnloaded(org.ogre4j.IResource res) {
        __notifyResourceUnloaded__Resourcep(this.object.pointer, res.getInstancePointer().pointer);
    }

    private native void __notifyResourceUnloaded__Resourcep(long _pointer_, long res);

    /** 
    Generic prepare method, used to create a  specific to this  without using one of the specialised 'prepare' methods (containing per-Resource-type parameters). **/
    public void prepare(org.ogre4j.IResourcePtr returnValue, String name, String group, boolean isManual, org.ogre4j.IManualResourceLoader loader, org.ogre4j.INameValuePairList loadParams) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_prepare__StringRStringRbvManualResourceLoaderpNameValuePairListP(this.object.pointer, name,  group,  isManual,  loader.getInstancePointer().pointer,  loadParams.getInstancePointer().pointer)), false);
    }

    private native long _prepare__StringRStringRbvManualResourceLoaderpNameValuePairListP(long _pointer_, String name, String group, boolean isManual, long loader, long loadParams);

    /** 
    Generic load method, used to create a  specific to this  without using one of the specialised 'load' methods (containing per-Resource-type parameters). **/
    public void load(org.ogre4j.IResourcePtr returnValue, String name, String group, boolean isManual, org.ogre4j.IManualResourceLoader loader, org.ogre4j.INameValuePairList loadParams) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_load__StringRStringRbvManualResourceLoaderpNameValuePairListP(this.object.pointer, name,  group,  isManual,  loader.getInstancePointer().pointer,  loadParams.getInstancePointer().pointer)), false);
    }

    private native long _load__StringRStringRbvManualResourceLoaderpNameValuePairListP(long _pointer_, String name, String group, boolean isManual, long loader, long loadParams);

    /** 
    Gets the file patterns which should be used to find scripts for this . **/
    public org.ogre4j.IStringVector getScriptPatterns() {
         return new org.ogre4j.StringVector(new InstancePointer(_getScriptPatterns_const(this.object.pointer)));
    }

    private native long _getScriptPatterns_const(long _pointer_);

    /** 
    Parse the definition of a set of resources from a script file. **/
    public void parseScript(org.ogre4j.IDataStreamPtr stream, String groupName) {
        _parseScript__DataStreamPtrrStringR(this.object.pointer, stream.getInstancePointer().pointer,  groupName);
    }

    private native void _parseScript__DataStreamPtrrStringR(long _pointer_, long stream, String groupName);

    /** 
    Gets the relative loading order of resources of this type. **/
    public float getLoadingOrder() {
         return _getLoadingOrder_const(this.object.pointer);
    }

    private native float _getLoadingOrder_const(long _pointer_);

    /** 
    Gets a string identifying the type of resource this manager handles. **/
    public String getResourceType() {
         return _getResourceType_const(this.object.pointer);
    }

    private native String _getResourceType_const(long _pointer_);

    /** 
    Sets whether this manager and its resources habitually produce log output **/
    public void setVerbose(boolean v) {
        _setVerbose__bv(this.object.pointer, v);
    }

    private native void _setVerbose__bv(long _pointer_, boolean v);

    /** 
    Gets whether this manager and its resources habitually produce log output **/
    public boolean getVerbose() {
         return _getVerbose(this.object.pointer);
    }

    private native boolean _getVerbose(long _pointer_);

    /** 
    Returns an iterator over all resources in this manager. **/
    public void getResourceIterator(org.ogre4j.IResourceManager.IResourceMapIterator returnValue) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getResourceIterator(this.object.pointer)), false);
    }

    private native long _getResourceIterator(long _pointer_);

}
