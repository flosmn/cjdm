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
public class ResourceBackgroundQueue extends org.xbig.base.NativeObject implements org.ogre4j.IResourceBackgroundQueue {
static { System.loadLibrary("ogre4j");}
public static class Listener extends org.xbig.base.NativeObject implements org.ogre4j.IResourceBackgroundQueue.IListener {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public Listener(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected Listener(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public Listener(org.xbig.base.WithoutNativeObject val) {
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
    Called when a requested operation completes, queued into main thread. **/
    public void operationCompleted(long ticket, org.ogre4j.IBackgroundProcessResult result) {
        _operationCompleted__BackgroundProcessTicketvBackgroundProcessResultR(this.object.pointer, ticket,  result.getInstancePointer().pointer);
    }

    private native void _operationCompleted__BackgroundProcessTicketvBackgroundProcessResultR(long _pointer_, long ticket, long result);

    /** 
    Called when a requested operation completes, immediate in background thread. **/
    public void operationCompletedInThread(long ticket, org.ogre4j.IBackgroundProcessResult result) {
        _operationCompletedInThread__BackgroundProcessTicketvBackgroundProcessResultR(this.object.pointer, ticket,  result.getInstancePointer().pointer);
    }

    private native void _operationCompletedInThread__BackgroundProcessTicketvBackgroundProcessResultR(long _pointer_, long ticket, long result);

}
protected static class QueuedNotification extends org.xbig.base.NativeObject implements org.ogre4j.IResourceBackgroundQueue.IQueuedNotification {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public QueuedNotification(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected QueuedNotification(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public QueuedNotification(org.xbig.base.WithoutNativeObject val) {
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
    public QueuedNotification(org.ogre4j.IResource r, boolean load) {
         super( new org.xbig.base.InstancePointer(__createQueuedNotification__Resourcepbv( r.getInstancePointer().pointer,  load)), false);
    }

    private native static long __createQueuedNotification__Resourcepbv(long r, boolean load);

    /** **/
    public boolean getload() {
         return _getload(this.object.pointer);
    }

    private native boolean _getload(long _pointer_);

    /** **/
    public void setload(boolean _jni_value_) {
        _setload(this.object.pointer, _jni_value_);
    }

    private native void _setload(long _pointer_, boolean _jni_value_);

    /** **/
    public org.ogre4j.IResource getresource() {
         return new org.ogre4j.Resource(new InstancePointer(_getresource(this.object.pointer)));
    }

    private native long _getresource(long _pointer_);

    /** **/
    public void setresource(org.ogre4j.IResource _jni_value_) {
        _setresource(this.object.pointer, _jni_value_.getInstancePointer().pointer);
    }

    private native void _setresource(long _pointer_, long _jni_value_);

    /** **/
    public void getreq(org.ogre4j.IResourceBackgroundQueue.IRequest returnValue) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getreq(this.object.pointer)), false);
    }

    private native long _getreq(long _pointer_);

    /** **/
    public void setreq(org.ogre4j.IResourceBackgroundQueue.IRequest _jni_value_) {
        _setreq(this.object.pointer, _jni_value_.getInstancePointer().pointer);
    }

    private native void _setreq(long _pointer_, long _jni_value_);

}
protected static class Request extends org.xbig.base.NativeObject implements org.ogre4j.IResourceBackgroundQueue.IRequest {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public Request(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected Request(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public Request(org.xbig.base.WithoutNativeObject val) {
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
    public Request() {
         super( new org.xbig.base.InstancePointer(__createRequest()), false);
    }

    private native static long __createRequest();

    /** **/
    public long getticketID() {
         return _getticketID(this.object.pointer);
    }

    private native long _getticketID(long _pointer_);

    /** **/
    public void setticketID(long _jni_value_) {
        _setticketID(this.object.pointer, _jni_value_);
    }

    private native void _setticketID(long _pointer_, long _jni_value_);

    /** **/
    public org.ogre4j.ResourceBackgroundQueue.RequestType gettype() {
         return org.ogre4j.ResourceBackgroundQueue.RequestType.toEnum(_gettype(this.object.pointer));
    }

    private native int _gettype(long _pointer_);

    /** **/
    public void settype(org.ogre4j.ResourceBackgroundQueue.RequestType _jni_value_) {
        _settype(this.object.pointer, _jni_value_.getValue());
    }

    private native void _settype(long _pointer_, int _jni_value_);

    /** **/
    public String getresourceName() {
         return _getresourceName(this.object.pointer);
    }

    private native String _getresourceName(long _pointer_);

    /** **/
    public void setresourceName(String _jni_value_) {
        _setresourceName(this.object.pointer, _jni_value_);
    }

    private native void _setresourceName(long _pointer_, String _jni_value_);

    /** **/
    public long getresourceHandle() {
         return _getresourceHandle(this.object.pointer);
    }

    private native long _getresourceHandle(long _pointer_);

    /** **/
    public void setresourceHandle(long _jni_value_) {
        _setresourceHandle(this.object.pointer, _jni_value_);
    }

    private native void _setresourceHandle(long _pointer_, long _jni_value_);

    /** **/
    public String getresourceType() {
         return _getresourceType(this.object.pointer);
    }

    private native String _getresourceType(long _pointer_);

    /** **/
    public void setresourceType(String _jni_value_) {
        _setresourceType(this.object.pointer, _jni_value_);
    }

    private native void _setresourceType(long _pointer_, String _jni_value_);

    /** **/
    public String getgroupName() {
         return _getgroupName(this.object.pointer);
    }

    private native String _getgroupName(long _pointer_);

    /** **/
    public void setgroupName(String _jni_value_) {
        _setgroupName(this.object.pointer, _jni_value_);
    }

    private native void _setgroupName(long _pointer_, String _jni_value_);

    /** **/
    public boolean getisManual() {
         return _getisManual(this.object.pointer);
    }

    private native boolean _getisManual(long _pointer_);

    /** **/
    public void setisManual(boolean _jni_value_) {
        _setisManual(this.object.pointer, _jni_value_);
    }

    private native void _setisManual(long _pointer_, boolean _jni_value_);

    /** **/
    public org.ogre4j.IManualResourceLoader getloader() {
         return new org.ogre4j.ManualResourceLoader(new InstancePointer(_getloader(this.object.pointer)));
    }

    private native long _getloader(long _pointer_);

    /** **/
    public void setloader(org.ogre4j.IManualResourceLoader _jni_value_) {
        _setloader(this.object.pointer, _jni_value_.getInstancePointer().pointer);
    }

    private native void _setloader(long _pointer_, long _jni_value_);

    /** **/
    public org.ogre4j.INameValuePairList getloadParams() {
         return new org.ogre4j.NameValuePairList(new InstancePointer(_getloadParams(this.object.pointer)));
    }

    private native long _getloadParams(long _pointer_);

    /** **/
    public org.ogre4j.IResourceBackgroundQueue.IListener getlistener() {
         return new org.ogre4j.ResourceBackgroundQueue.Listener(new InstancePointer(_getlistener(this.object.pointer)));
    }

    private native long _getlistener(long _pointer_);

    /** **/
    public void setlistener(org.ogre4j.IResourceBackgroundQueue.IListener _jni_value_) {
        _setlistener(this.object.pointer, _jni_value_.getInstancePointer().pointer);
    }

    private native void _setlistener(long _pointer_, long _jni_value_);

    /** **/
    public void getresult(org.ogre4j.IBackgroundProcessResult returnValue) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getresult(this.object.pointer)), false);
    }

    private native long _getresult(long _pointer_);

    /** **/
    public void setresult(org.ogre4j.IBackgroundProcessResult _jni_value_) {
        _setresult(this.object.pointer, _jni_value_.getInstancePointer().pointer);
    }

    private native void _setresult(long _pointer_, long _jni_value_);

}
protected enum RequestType implements INativeEnum < RequestType > {
    RT_INITIALISE_GROUP(RequestTypeHelper.ENUM_VALUES[0]),
    RT_INITIALISE_ALL_GROUPS(RequestTypeHelper.ENUM_VALUES[1]),
    RT_PREPARE_GROUP(RequestTypeHelper.ENUM_VALUES[2]),
    RT_PREPARE_RESOURCE(RequestTypeHelper.ENUM_VALUES[3]),
    RT_LOAD_GROUP(RequestTypeHelper.ENUM_VALUES[4]),
    RT_LOAD_RESOURCE(RequestTypeHelper.ENUM_VALUES[5]),
    RT_UNLOAD_GROUP(RequestTypeHelper.ENUM_VALUES[6]),
    RT_UNLOAD_RESOURCE(RequestTypeHelper.ENUM_VALUES[7]),
    RT_SHUTDOWN(RequestTypeHelper.ENUM_VALUES[8]);

    private int value;

    RequestType(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public RequestType getEnum(int val) {
        return toEnum(val);
    }

    public static final RequestType toEnum(int retval) {
    if (retval ==RT_INITIALISE_GROUP.value)
        return RequestType.RT_INITIALISE_GROUP;
    else if (retval ==RT_INITIALISE_ALL_GROUPS.value)
        return RequestType.RT_INITIALISE_ALL_GROUPS;
    else if (retval ==RT_PREPARE_GROUP.value)
        return RequestType.RT_PREPARE_GROUP;
    else if (retval ==RT_PREPARE_RESOURCE.value)
        return RequestType.RT_PREPARE_RESOURCE;
    else if (retval ==RT_LOAD_GROUP.value)
        return RequestType.RT_LOAD_GROUP;
    else if (retval ==RT_LOAD_RESOURCE.value)
        return RequestType.RT_LOAD_RESOURCE;
    else if (retval ==RT_UNLOAD_GROUP.value)
        return RequestType.RT_UNLOAD_GROUP;
    else if (retval ==RT_UNLOAD_RESOURCE.value)
        return RequestType.RT_UNLOAD_RESOURCE;
    else if (retval ==RT_SHUTDOWN.value)
        return RequestType.RT_SHUTDOWN;
    throw new RuntimeException("wrong number in jni call for an enum");
    }
}

static class RequestTypeHelper{

				public static final int[] ENUM_VALUES =
				getEnumValues();

			
				private static native int[] getEnumValues();

			
};

protected static class RequestQueue extends org.xbig.base.NativeObject implements org.ogre4j.IResourceBackgroundQueue.IRequestQueue {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public RequestQueue(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected RequestQueue(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public RequestQueue(org.xbig.base.WithoutNativeObject val) {
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
    public RequestQueue() {
         super( new org.xbig.base.InstancePointer(__createRequestQueue()), false);
    }

    private native static long __createRequestQueue();

    /** **/
    public void assign(int num, org.ogre4j.IResourceBackgroundQueue.IRequest val) {
        _assign__ivOgre_ResourceBackgroundQueue_RequestR(this.object.pointer, num,  val.getInstancePointer().pointer);
    }

    private native void _assign__ivOgre_ResourceBackgroundQueue_RequestR(long _pointer_, int num, long val);

    /** **/
    public org.ogre4j.IResourceBackgroundQueue.IRequest back() {
         return new org.ogre4j.ResourceBackgroundQueue.Request(new InstancePointer(_back(this.object.pointer)));
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
    public org.ogre4j.IResourceBackgroundQueue.IRequest front() {
         return new org.ogre4j.ResourceBackgroundQueue.Request(new InstancePointer(_front(this.object.pointer)));
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
    public void push_back(org.ogre4j.IResourceBackgroundQueue.IRequest val) {
        _push_back__Ogre_ResourceBackgroundQueue_RequestR(this.object.pointer, val.getInstancePointer().pointer);
    }

    private native void _push_back__Ogre_ResourceBackgroundQueue_RequestR(long _pointer_, long val);

    /** **/
    public void push_front(org.ogre4j.IResourceBackgroundQueue.IRequest val) {
        _push_front__Ogre_ResourceBackgroundQueue_RequestR(this.object.pointer, val.getInstancePointer().pointer);
    }

    private native void _push_front__Ogre_ResourceBackgroundQueue_RequestR(long _pointer_, long val);

    /** **/
    public void remove(org.ogre4j.IResourceBackgroundQueue.IRequest val) {
        _remove__Ogre_ResourceBackgroundQueue_RequestR(this.object.pointer, val.getInstancePointer().pointer);
    }

    private native void _remove__Ogre_ResourceBackgroundQueue_RequestR(long _pointer_, long val);

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
protected static class RequestTicketMap extends org.xbig.base.NativeObject implements org.ogre4j.IResourceBackgroundQueue.IRequestTicketMap {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public RequestTicketMap(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected RequestTicketMap(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public RequestTicketMap(org.xbig.base.WithoutNativeObject val) {
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
    public RequestTicketMap() {
         super( new org.xbig.base.InstancePointer(__createRequestTicketMap()), false);
    }

    private native static long __createRequestTicketMap();

    /** **/
    public void clear() {
        _clear(this.object.pointer);
    }

    private native void _clear(long _pointer_);

    /** **/
    public int count(long key) {
         return _count__LR(this.object.pointer, key);
    }

    private native int _count__LR(long _pointer_, long key);

    /** **/
    public boolean empty() {
         return _empty_const(this.object.pointer);
    }

    private native boolean _empty_const(long _pointer_);

    /** **/
    public int erase(long key) {
         return _erase__LR(this.object.pointer, key);
    }

    private native int _erase__LR(long _pointer_, long key);

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
    public org.ogre4j.IResourceBackgroundQueue.IRequest get(long key) {
         return new org.ogre4j.ResourceBackgroundQueue.Request(new InstancePointer(_get__LR(this.object.pointer, key)));
    }

    private native long _get__LR(long _pointer_, long key);

    /** **/
    public void insert(long key, org.ogre4j.IResourceBackgroundQueue.IRequest value) {
        _insert__LROgre_ResourceBackgroundQueue_Requestp(this.object.pointer, key,  value.getInstancePointer().pointer);
    }

    private native void _insert__LROgre_ResourceBackgroundQueue_Requestp(long _pointer_, long key, long value);

}
protected static class NotificationQueue extends org.xbig.base.NativeObject implements org.ogre4j.IResourceBackgroundQueue.INotificationQueue {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public NotificationQueue(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected NotificationQueue(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public NotificationQueue(org.xbig.base.WithoutNativeObject val) {
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
    public NotificationQueue() {
         super( new org.xbig.base.InstancePointer(__createNotificationQueue()), false);
    }

    private native static long __createNotificationQueue();

    /** **/
    public void assign(int num, org.ogre4j.IResourceBackgroundQueue.IQueuedNotification val) {
        _assign__ivOgre_ResourceBackgroundQueue_QueuedNotificationR(this.object.pointer, num,  val.getInstancePointer().pointer);
    }

    private native void _assign__ivOgre_ResourceBackgroundQueue_QueuedNotificationR(long _pointer_, int num, long val);

    /** **/
    public org.ogre4j.IResourceBackgroundQueue.IQueuedNotification back() {
         return new org.ogre4j.ResourceBackgroundQueue.QueuedNotification(new InstancePointer(_back(this.object.pointer)));
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
    public org.ogre4j.IResourceBackgroundQueue.IQueuedNotification front() {
         return new org.ogre4j.ResourceBackgroundQueue.QueuedNotification(new InstancePointer(_front(this.object.pointer)));
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
    public void push_back(org.ogre4j.IResourceBackgroundQueue.IQueuedNotification val) {
        _push_back__Ogre_ResourceBackgroundQueue_QueuedNotificationR(this.object.pointer, val.getInstancePointer().pointer);
    }

    private native void _push_back__Ogre_ResourceBackgroundQueue_QueuedNotificationR(long _pointer_, long val);

    /** **/
    public void push_front(org.ogre4j.IResourceBackgroundQueue.IQueuedNotification val) {
        _push_front__Ogre_ResourceBackgroundQueue_QueuedNotificationR(this.object.pointer, val.getInstancePointer().pointer);
    }

    private native void _push_front__Ogre_ResourceBackgroundQueue_QueuedNotificationR(long _pointer_, long val);

    /** **/
    public void remove(org.ogre4j.IResourceBackgroundQueue.IQueuedNotification val) {
        _remove__Ogre_ResourceBackgroundQueue_QueuedNotificationR(this.object.pointer, val.getInstancePointer().pointer);
    }

    private native void _remove__Ogre_ResourceBackgroundQueue_QueuedNotificationR(long _pointer_, long val);

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
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public ResourceBackgroundQueue(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected ResourceBackgroundQueue(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public ResourceBackgroundQueue(org.xbig.base.WithoutNativeObject val) {
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
    public ResourceBackgroundQueue() {
         super( new org.xbig.base.InstancePointer(__createResourceBackgroundQueue()), false);
    }

    private native static long __createResourceBackgroundQueue();

    /** 
    Sets whether or not a thread should be created and started to handle the background loading, or whether a user thread will call the appropriate hooks. **/
    public void setStartBackgroundThread(boolean startThread) {
        _setStartBackgroundThread__bv(this.object.pointer, startThread);
    }

    private native void _setStartBackgroundThread__bv(long _pointer_, boolean startThread);

    /** 
    Gets whether or not a thread should be created and started to handle the background loading, or whether a user thread will call the appropriate hooks. **/
    public boolean getStartBackgroundThread() {
         return _getStartBackgroundThread(this.object.pointer);
    }

    private native boolean _getStartBackgroundThread(long _pointer_);

    /** 
    Initialise the background queue system. **/
    public void initialise() {
        _initialise(this.object.pointer);
    }

    private native void _initialise(long _pointer_);

    /** 
    Shut down the background queue system. **/
    public void shutdown() {
        _shutdown(this.object.pointer);
    }

    private native void _shutdown(long _pointer_);

    /** 
    Initialise a resource group in the background. **/
    public long initialiseResourceGroup(String name, org.ogre4j.IResourceBackgroundQueue.IListener listener) {
         return _initialiseResourceGroup__StringRListenerp(this.object.pointer, name,  listener.getInstancePointer().pointer);
    }

    private native long _initialiseResourceGroup__StringRListenerp(long _pointer_, String name, long listener);

    /** 
    Initialise all resource groups which are yet to be initialised in the background. **/
    public long initialiseAllResourceGroups(org.ogre4j.IResourceBackgroundQueue.IListener listener) {
         return _initialiseAllResourceGroups__Listenerp(this.object.pointer, listener.getInstancePointer().pointer);
    }

    private native long _initialiseAllResourceGroups__Listenerp(long _pointer_, long listener);

    /** 
    Prepares a resource group in the background. **/
    public long prepareResourceGroup(String name, org.ogre4j.IResourceBackgroundQueue.IListener listener) {
         return _prepareResourceGroup__StringRListenerp(this.object.pointer, name,  listener.getInstancePointer().pointer);
    }

    private native long _prepareResourceGroup__StringRListenerp(long _pointer_, String name, long listener);

    /** 
    Loads a resource group in the background. **/
    public long loadResourceGroup(String name, org.ogre4j.IResourceBackgroundQueue.IListener listener) {
         return _loadResourceGroup__StringRListenerp(this.object.pointer, name,  listener.getInstancePointer().pointer);
    }

    private native long _loadResourceGroup__StringRListenerp(long _pointer_, String name, long listener);

    /** 
    Unload a single resource in the background. **/
    public long unload(String resType, String name, org.ogre4j.IResourceBackgroundQueue.IListener listener) {
         return _unload__StringRStringRListenerp(this.object.pointer, resType,  name,  listener.getInstancePointer().pointer);
    }

    private native long _unload__StringRStringRListenerp(long _pointer_, String resType, String name, long listener);

    /** 
    Unload a single resource in the background. **/
    public long unload(String resType, long handle, org.ogre4j.IResourceBackgroundQueue.IListener listener) {
         return _unload__StringRResourceHandlevListenerp(this.object.pointer, resType,  handle,  listener.getInstancePointer().pointer);
    }

    private native long _unload__StringRResourceHandlevListenerp(long _pointer_, String resType, long handle, long listener);

    /** 
    Unloads a resource group in the background. **/
    public long unloadResourceGroup(String name, org.ogre4j.IResourceBackgroundQueue.IListener listener) {
         return _unloadResourceGroup__StringRListenerp(this.object.pointer, name,  listener.getInstancePointer().pointer);
    }

    private native long _unloadResourceGroup__StringRListenerp(long _pointer_, String name, long listener);

    /** 
    Prepare a single resource in the background. **/
    public long prepare(String resType, String name, String group, boolean isManual, org.ogre4j.IManualResourceLoader loader, org.ogre4j.INameValuePairList loadParams, org.ogre4j.IResourceBackgroundQueue.IListener listener) {
         return _prepare__StringRStringRStringRbvManualResourceLoaderpNameValuePairListPListenerp(this.object.pointer, resType,  name,  group,  isManual,  loader.getInstancePointer().pointer,  loadParams.getInstancePointer().pointer,  listener.getInstancePointer().pointer);
    }

    private native long _prepare__StringRStringRStringRbvManualResourceLoaderpNameValuePairListPListenerp(long _pointer_, String resType, String name, String group, boolean isManual, long loader, long loadParams, long listener);

    /** 
    Load a single resource in the background. **/
    public long load(String resType, String name, String group, boolean isManual, org.ogre4j.IManualResourceLoader loader, org.ogre4j.INameValuePairList loadParams, org.ogre4j.IResourceBackgroundQueue.IListener listener) {
         return _load__StringRStringRStringRbvManualResourceLoaderpNameValuePairListPListenerp(this.object.pointer, resType,  name,  group,  isManual,  loader.getInstancePointer().pointer,  loadParams.getInstancePointer().pointer,  listener.getInstancePointer().pointer);
    }

    private native long _load__StringRStringRStringRbvManualResourceLoaderpNameValuePairListPListenerp(long _pointer_, String resType, String name, String group, boolean isManual, long loader, long loadParams, long listener);

    /** 
    Returns whether a previously queued process has completed or not. **/
    public boolean isProcessComplete(long ticket) {
         return _isProcessComplete__BackgroundProcessTicketv(this.object.pointer, ticket);
    }

    private native boolean _isProcessComplete__BackgroundProcessTicketv(long _pointer_, long ticket);

    /** 
    Process a single queued background operation. **/
    public boolean _doNextQueuedBackgroundProcess() {
         return __doNextQueuedBackgroundProcess(this.object.pointer);
    }

    private native boolean __doNextQueuedBackgroundProcess(long _pointer_);

    /** 
    Initialise processing for a background thread. **/
    public void _initThread() {
        __initThread(this.object.pointer);
    }

    private native void __initThread(long _pointer_);

    /** 
    Queue the firing of the 'background preparing complete' event to a  event. **/
    public void _queueFireBackgroundPreparingComplete(org.ogre4j.IResource res) {
        __queueFireBackgroundPreparingComplete__Resourcep(this.object.pointer, res.getInstancePointer().pointer);
    }

    private native void __queueFireBackgroundPreparingComplete__Resourcep(long _pointer_, long res);

    /** 
    Queue the firing of the 'background loading complete' event to a  event. **/
    public void _queueFireBackgroundLoadingComplete(org.ogre4j.IResource res) {
        __queueFireBackgroundLoadingComplete__Resourcep(this.object.pointer, res.getInstancePointer().pointer);
    }

    private native void __queueFireBackgroundLoadingComplete__Resourcep(long _pointer_, long res);

    /** 
    Fires all the queued events for background loaded resources. **/
    public void _fireOnFrameCallbacks() {
        __fireOnFrameCallbacks(this.object.pointer);
    }

    private native void __fireOnFrameCallbacks(long _pointer_);

    /** 
    Override standard  retrieval. **/
    public static org.ogre4j.IResourceBackgroundQueue getSingleton() {
         return new org.ogre4j.ResourceBackgroundQueue(new InstancePointer(_getSingleton()));
    }

    private native static long _getSingleton();

    /** 
    Override standard  retrieval. **/
    public static org.ogre4j.IResourceBackgroundQueue getSingletonPtr() {
         return new org.ogre4j.ResourceBackgroundQueue(new InstancePointer(_getSingletonPtr()));
    }

    private native static long _getSingletonPtr();

}
