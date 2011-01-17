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
 * Native number represents pointers of native primitive numeric values. E.g
 * int. This allows the user to work with primitive values returned in a pointer
 * or by reference.
 * 
 */
public abstract class NumberPointer<T> extends NativeObject {

    /*
     * (non-Javadoc)
     * 
     * @see org.xbig.base.NativeObject#NativeObject(InstancePointer)
     */
	public NumberPointer(InstancePointer pInstance) {
		super(pInstance);
	}

    /*
     * (non-Javadoc)
     * 
     * @see org.xbig.base.NativeObject#NativeObject(InstancePointer, boolean)
     */
	public NumberPointer(InstancePointer pointer, boolean b) {
		super(pointer,b);
	}

	/**
	 * Returns the value of the specified number as a <code>byte</code>. This
	 * may involve rounding or truncation.
	 * 
	 * @return the numeric value represented by this object after conversion to
	 *         type <code>byte</code>.
	 */
	public byte byteValue() {
		return (byte) intValue();
	}

	/**
	 * Returns the value of the specified number as a <code>double</code>.
	 * This may involve rounding.
	 * 
	 * @return the numeric value represented by this object after conversion to
	 *         type <code>double</code>.
	 */
	public abstract double doubleValue();

	/**
	 * Returns the value of the specified number as a <code>float</code>.
	 * This may involve rounding.
	 * 
	 * @return the numeric value represented by this object after conversion to
	 *         type <code>float</code>.
	 */
	public abstract float floatValue();

	/**
	 * @return The value of the native number.
	 */
	public abstract T get();

	/**
	 * Returns the value of the specified number as an <code>int</code>. This
	 * may involve rounding or truncation.
	 * 
	 * @return the numeric value represented by this object after conversion to
	 *         type <code>int</code>.
	 */
	public abstract int intValue();

	/**
	 * Returns the value of the specified number as a <code>long</code>. This
	 * may involve rounding or truncation.
	 * 
	 * @return the numeric value represented by this object after conversion to
	 *         type <code>long</code>.
	 */
	public abstract long longValue();

	/**
	 * Sets the value of the native number
	 * 
	 * @param value
	 *            the value to set.
	 */
	public abstract void set(T value);

	/**
	 * Returns the value of the specified number as a <code>short</code>.
	 * This may involve rounding or truncation.
	 * 
	 * @return the numeric value represented by this object after conversion to
	 *         type <code>short</code>.
	 */
	public short shortValue() {
		return (short) intValue();
	}
	
	/**
	 * @return The pointer to the next number in memory. 
	 */
	public abstract NumberPointer< T > next();
}
