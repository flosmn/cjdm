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
 * Represents a pointer or reference to a native short value.
 * @see NumberPointer
 */
public class ShortPointer extends NumberPointer<Short> {
	
    /*
     * (non-Javadoc)
     * 
     * @see org.xbig.base.NativeObject#NativeObject(InstancePointer)
     */
	public ShortPointer(InstancePointer pInstance) {
		super(pInstance);
	}

    /*
     * (non-Javadoc)
     * 
     * @see org.xbig.base.NativeObject#NativeObject(InstancePointer, boolean)
     */
    public ShortPointer(InstancePointer pointer, boolean b) {
        super(pointer,b);
    }

    /**
     * Create native variable and initialise it.
     * 
     * @param value Value to initialise native value.
     */
	public ShortPointer(short value) {
		super(new InstancePointer(_create(value)),false);
	}

	private static native long _create(short value);

	private native void _dispose(long pInstance);

	private native short _get(long pInstance);

	private native long _next(long pInstance);

	private native void _set(long pInstance, short value);

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
	 * @{inheritdoc}
	 * @see org.xbig.base.NumberPointer#doubleValue()
	 */
	@Override
	public double doubleValue() {
		return _get(object.pointer);
	}
	/**
	 * @{inheritdoc}
	 * @see org.xbig.base.NativeObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ShortPointer) {
			return this._get(object.pointer) == ((ShortPointer) obj)._get(object.pointer);			
		}
		return super.equals(obj);
	}
	/**
	 * @{inheritdoc}
	 * @see org.xbig.base.NumberPointer#floatValue()
	 */
	@Override
	public float floatValue() {
		return (float) _get(object.pointer);
	}
	/**
	 * @{inheritdoc}
	 * @see org.xbig.base.NumberPointer#get()
	 */
	public Short get()
	{
		return _get(object.pointer);
	}
	/**
	 * @{inheritdoc}
	 * @see org.xbig.base.NativeObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return intValue();
	}

	/**
	 * @{inheritdoc}
	 * @see org.xbig.base.NumberPointer#intValue()
	 */
	@Override
	public int intValue() {
		return (int) _get(object.pointer);
	}

	/**
	 * @{inheritdoc}
	 * @see org.xbig.base.NumberPointer#longValue()
	 */
	@Override
	public long longValue() {
		return (long) _get(object.pointer);
	}

	/**
	 * @{inheritdoc}
	 * @see org.xbig.base.NumberPointer#next()
	 */
	@Override
	public ShortPointer next() {
		long ptr = _next(object.pointer);
		if(ptr==0)return null;		
		return new ShortPointer(new InstancePointer(ptr));
	}
	
	/**
	 * @{inheritdoc}
	 * @see org.xbig.base.NumberPointer#set(java.lang.Object)
	 */
	public void set(Short value)
	{
		_set(object.pointer, value);
	}

	/**
	 * @{inheritdoc}
	 * @see org.xbig.base.NativeObject#toString()
	 */
	@Override
	public String toString() {
		return Short.toString(get());
	}

	public void finalize() {
		if(!this.remote && !this.deleted) {
			delete();
		}
	}
}
