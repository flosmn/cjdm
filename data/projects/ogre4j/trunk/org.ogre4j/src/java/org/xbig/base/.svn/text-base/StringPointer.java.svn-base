/* This source file is part of XBiG
 *     (XSLT Bindings Generator)
 * For the latest info, see http://sourceforge.net/projects/xbig/
 * 
 * Copyright (c) 2005 netAllied GmbH, Tettnang
 * Also see acknowledgements in Readme.html
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA, or go to
 * http://www.gnu.org/copyleft/lesser.txt.
 */
package org.xbig.base;

/**
 * Represents a pointer or reference to a native string.
 * @see NumberPointer
 */
public class StringPointer extends NativeObject {

    /*
     * (non-Javadoc)
     * 
     * @see org.xbig.base.NativeObject#NativeObject(InstancePointer)
     */
	public StringPointer(InstancePointer pInstance) {
		super(pInstance);
	}

    /*
     * (non-Javadoc)
     * 
     * @see org.xbig.base.NativeObject#NativeObject(InstancePointer, boolean)
     */
    public StringPointer(InstancePointer pointer, boolean b) {
        super(pointer,b);
    }

    /**
     * Create native variable and initialise it.
     * 
     * @param value Value to initialise native value.
     */
	public StringPointer(String value) {
		super(new InstancePointer(_create(value)),false);
	}

	private static native long _create(String value);

	private native void _dispose(long pInstance);

	private native String _get(long pInstance);	
	
	private native long _next(long pInstance);

	private native void _set(long pInstance, String value);

	/**
	 * @{inheritdoc}
	 * @see org.xbig.base.NativeObject#delete()
	 */
	@Override
	public void delete() {
		if(this.remote) {
			throw new RuntimeException("Instance created by the library! It's not allowed to dispose it.");
		}

		if(!this.deleted) {
			_dispose(object.pointer);
		    this.deleted = true;
		   	this.object.pointer = 0;
		}
	}

	/**
	 * If a java.lang.String is passed, it is compared to the string
	 * this StringPointer points to.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StringPointer) {
			return this._get(object.pointer) == ((StringPointer) obj)._get(object.pointer);			
		}
		if (obj instanceof String) {
			return obj.equals(_get(object.pointer));
		}
		return super.equals(obj);
	}

    /*
     * (non-Javadoc)
     * 
     * @see org.xbig.base.NumberPointer#get()
     */
	public String get()
	{
		return _get(object.pointer);
	}

    /*
     * (non-Javadoc)
     * 
     * @see org.xbig.base.NumberPointer#next()
     */
	public StringPointer next() {
		long ptr = _next(object.pointer);
		if(ptr==0)return null;		
		return new StringPointer(new InstancePointer(ptr));
	}
	
    /*
     * (non-Javadoc)
     * 
     * @see org.xbig.base.NumberPointer#set()
     */
	public void set(String value) {
		_set(object.pointer, value);
	}

	/**
	 * @{inheritdoc}
	 * @see org.xbig.base.NativeObject#toString()
	 */
	@Override
	public String toString() {
		return _get(object.pointer);
	}

	public void finalize() {
		if(!this.remote && !this.deleted) {
			delete();
		}
	}
}
