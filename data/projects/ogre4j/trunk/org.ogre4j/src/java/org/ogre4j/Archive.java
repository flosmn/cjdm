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
public class Archive extends org.xbig.base.NativeObject implements org.ogre4j.IArchive {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public Archive(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected Archive(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public Archive(org.xbig.base.WithoutNativeObject val) {
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
    public String getName() {
         return _getName_const(this.object.pointer);
    }

    private native String _getName_const(long _pointer_);

    /** **/
    public boolean isCaseSensitive() {
         return _isCaseSensitive_const(this.object.pointer);
    }

    private native boolean _isCaseSensitive_const(long _pointer_);

    /** 
    Loads the archive. **/
    public void load() {
        _load(this.object.pointer);
    }

    private native void _load(long _pointer_);

    /** 
    Unloads the archive. **/
    public void unload() {
        _unload(this.object.pointer);
    }

    private native void _unload(long _pointer_);

    /** 
    Open a stream on a given file. **/
    public void open(org.ogre4j.IDataStreamPtr returnValue, String filename) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_open__StringR_const(this.object.pointer, filename)), false);
    }

    private native long _open__StringR_const(long _pointer_, String filename);

    /** 
    List all file names in the archive. **/
    public void list(org.ogre4j.IStringVectorPtr returnValue, boolean recursive, boolean dirs) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_list__bvbv(this.object.pointer, recursive,  dirs)), false);
    }

    private native long _list__bvbv(long _pointer_, boolean recursive, boolean dirs);

    /** 
    List all files in the archive with accompanying information. **/
    public void listFileInfo(org.ogre4j.IFileInfoListPtr returnValue, boolean recursive, boolean dirs) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_listFileInfo__bvbv(this.object.pointer, recursive,  dirs)), false);
    }

    private native long _listFileInfo__bvbv(long _pointer_, boolean recursive, boolean dirs);

    /** 
    Find all file or directory names matching a given pattern in this archive. **/
    public void find(org.ogre4j.IStringVectorPtr returnValue, String pattern, boolean recursive, boolean dirs) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_find__StringRbvbv(this.object.pointer, pattern,  recursive,  dirs)), false);
    }

    private native long _find__StringRbvbv(long _pointer_, String pattern, boolean recursive, boolean dirs);

    /** 
    Find out if the named file exists (note: fully qualified filename required) **/
    public boolean exists(String filename) {
         return _exists__StringR(this.object.pointer, filename);
    }

    private native boolean _exists__StringR(long _pointer_, String filename);

    /** 
    Retrieve the modification time of a given file **/
    public long getModifiedTime(String filename) {
         return _getModifiedTime__StringR(this.object.pointer, filename);
    }

    private native long _getModifiedTime__StringR(long _pointer_, String filename);

    /** 
    Find all files or directories matching a given pattern in this archive and get some detailed information about them. **/
    public void findFileInfo(org.ogre4j.IFileInfoListPtr returnValue, String pattern, boolean recursive, boolean dirs) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_findFileInfo__StringRbvbv(this.object.pointer, pattern,  recursive,  dirs)), false);
    }

    private native long _findFileInfo__StringRbvbv(long _pointer_, String pattern, boolean recursive, boolean dirs);

    /** **/
    public String getType() {
         return _getType_const(this.object.pointer);
    }

    private native String _getType_const(long _pointer_);

}
