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
public class Log extends org.xbig.base.NativeObject implements org.ogre4j.ILog {
static { System.loadLibrary("ogre4j");}
public static class Stream extends org.xbig.base.NativeObject implements org.ogre4j.ILog.IStream {
static { System.loadLibrary("ogre4j");}
public static class Flush extends org.xbig.base.NativeObject implements org.ogre4j.ILog.IStream.IFlush {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public Flush(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected Flush(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public Flush(org.xbig.base.WithoutNativeObject val) {
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
    public Flush() {
         super( new org.xbig.base.InstancePointer(__createFlush()), false);
    }

    private native static long __createFlush();

}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public Stream(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected Stream(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public Stream(org.xbig.base.WithoutNativeObject val) {
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
    public Stream(org.ogre4j.ILog target, org.ogre4j.LogMessageLevel lml, boolean maskDebug) {
         super( new org.xbig.base.InstancePointer(__createStream__LogpLogMessageLevelvbv( target.getInstancePointer().pointer,  lml.getValue(),  maskDebug)), false);
    }

    private native static long __createStream__LogpLogMessageLevelvbv(long target, int lml, boolean maskDebug);

    /** **/
    public Stream(org.ogre4j.ILog.IStream rhs) {
         super( new org.xbig.base.InstancePointer(__createStream__StreamR( rhs.getInstancePointer().pointer)), false);
    }

    private native static long __createStream__StreamR(long rhs);

    /** **/
    public org.ogre4j.ILog.IStream operatorLeftShift(org.ogre4j.ILog.IStream.IFlush v) {
         return new org.ogre4j.Log.Stream(new InstancePointer(_operatorLeftShift__FlushR(this.object.pointer, v.getInstancePointer().pointer)));
    }

    private native long _operatorLeftShift__FlushR(long _pointer_, long v);

}
protected static class mtLogListener extends org.xbig.base.NativeObject implements org.ogre4j.ILog.ImtLogListener {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public mtLogListener(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected mtLogListener(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public mtLogListener(org.xbig.base.WithoutNativeObject val) {
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
    public mtLogListener() {
         super( new org.xbig.base.InstancePointer(__createmtLogListener()), false);
    }

    private native static long __createmtLogListener();

    /** **/
    public void assign(int num, org.ogre4j.ILogListener val) {
        _assign__ivOgre_LogListenerP(this.object.pointer, num,  val.getInstancePointer().pointer);
    }

    private native void _assign__ivOgre_LogListenerP(long _pointer_, int num, long val);

    /** **/
    public org.ogre4j.ILogListener at(int loc) {
         return new org.ogre4j.LogListener(new InstancePointer(_at__iv(this.object.pointer, loc)));
    }

    private native long _at__iv(long _pointer_, int loc);

    /** **/
    public org.ogre4j.ILogListener back() {
         return new org.ogre4j.LogListener(new InstancePointer(_back(this.object.pointer)));
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
    public org.ogre4j.ILogListener front() {
         return new org.ogre4j.LogListener(new InstancePointer(_front(this.object.pointer)));
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
    public void push_back(org.ogre4j.ILogListener val) {
        _push_back__Ogre_LogListenerP(this.object.pointer, val.getInstancePointer().pointer);
    }

    private native void _push_back__Ogre_LogListenerP(long _pointer_, long val);

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
	public Log(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected Log(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public Log(org.xbig.base.WithoutNativeObject val) {
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
    public Log(String name, boolean debugOutput, boolean suppressFileOutput) {
         super( new org.xbig.base.InstancePointer(__createLog__StringRbvbv( name,  debugOutput,  suppressFileOutput)), false);
    }

    private native static long __createLog__StringRbvbv(String name, boolean debugOutput, boolean suppressFileOutput);

    /** **/
    public String getName() {
         return _getName_const(this.object.pointer);
    }

    private native String _getName_const(long _pointer_);

    /** **/
    public boolean isDebugOutputEnabled() {
         return _isDebugOutputEnabled_const(this.object.pointer);
    }

    private native boolean _isDebugOutputEnabled_const(long _pointer_);

    /** **/
    public boolean isFileOutputSuppressed() {
         return _isFileOutputSuppressed_const(this.object.pointer);
    }

    private native boolean _isFileOutputSuppressed_const(long _pointer_);

    /** 
     a message to the debugger and to log file (the default is "<code>OGRE.log</code>"), **/
    public void logMessage(String message, org.ogre4j.LogMessageLevel lml, boolean maskDebug) {
        _logMessage__StringRLogMessageLevelvbv(this.object.pointer, message,  lml.getValue(),  maskDebug);
    }

    private native void _logMessage__StringRLogMessageLevelvbv(long _pointer_, String message, int lml, boolean maskDebug);

    /** 
    Get a stream object targetting this log. **/
    public void stream(org.ogre4j.ILog.IStream returnValue, org.ogre4j.LogMessageLevel lml, boolean maskDebug) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_stream__LogMessageLevelvbv(this.object.pointer, lml.getValue(),  maskDebug)), false);
    }

    private native long _stream__LogMessageLevelvbv(long _pointer_, int lml, boolean maskDebug);

    /** 
    **/
    public void setDebugOutputEnabled(boolean debugOutput) {
        _setDebugOutputEnabled__bv(this.object.pointer, debugOutput);
    }

    private native void _setDebugOutputEnabled__bv(long _pointer_, boolean debugOutput);

    /** 
    **/
    public void setLogDetail(org.ogre4j.LoggingLevel ll) {
        _setLogDetail__LoggingLevelv(this.object.pointer, ll.getValue());
    }

    private native void _setLogDetail__LoggingLevelv(long _pointer_, int ll);

    /** 
    Gets the level of the log detail. **/
    public org.ogre4j.LoggingLevel getLogDetail() {
         return org.ogre4j.LoggingLevel.toEnum(_getLogDetail_const(this.object.pointer));
    }

    private native int _getLogDetail_const(long _pointer_);

    /** 
    **/
    public void addListener(org.ogre4j.ILogListener listener) {
        _addListener__LogListenerp(this.object.pointer, listener.getInstancePointer().pointer);
    }

    private native void _addListener__LogListenerp(long _pointer_, long listener);

    /** 
    **/
    public void removeListener(org.ogre4j.ILogListener listener) {
        _removeListener__LogListenerp(this.object.pointer, listener.getInstancePointer().pointer);
    }

    private native void _removeListener__LogListenerp(long _pointer_, long listener);

}
