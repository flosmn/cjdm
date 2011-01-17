/* This source file is part of ogre4j
 *     (The JNI bindings for OGRE)
 * For the latest info, see http://www.ogre4j.org/
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

import java.lang.RuntimeException;
import java.lang.NumberFormatException;
import java.math.BigInteger;

/**
 * 
 * To represent the C++ data type "unsigned long long"
 */
public class ULongLong {	
	
	/**
	 * C++ type "unsigned long long" covers (-9,223,372,036,854,775,808 to 9,223,372,036,854,775,807);
	 * Java type "long" covers (0 to 18,446,744,073,709,551,615);
	 * 
	 * shiftedValue contains the real value minus 9,223,372,036,854,775,808
	 *  
	 */	
	public static BigInteger MAX_VALUE = new BigInteger("18446744073709551615");
	
	public static BigInteger MIN_VALUE = new BigInteger("0");
	
	public static BigInteger SHIFT_VALUE = new BigInteger("9223372036854775808");  
	
	private long shiftedValue = -1;
	
	private BigInteger originalValue = null;
	
	public ULongLong(String uLongLongValue) {
		BigInteger realValue = new BigInteger(uLongLongValue);
		if ((realValue.compareTo(ULongLong.MIN_VALUE) >= 0)&&(realValue.compareTo(ULongLong.MAX_VALUE) <= 0)) {			
			this.shiftedValue = realValue.subtract(ULongLong.SHIFT_VALUE).longValue();
			this.originalValue = new BigInteger(uLongLongValue);
		} else {
			throw new NumberFormatException(uLongLongValue+" is not in the range of (0 to 18,446,744,073,709,551,615).");
		}
	}
	
	public ULongLong(BigInteger uLongLongValue) {
		if ((uLongLongValue.compareTo(ULongLong.MIN_VALUE) >= 0)&&(uLongLongValue.compareTo(ULongLong.MAX_VALUE) <= 0)) {
			this.shiftedValue = uLongLongValue.subtract(ULongLong.SHIFT_VALUE).longValue();
			this.originalValue = uLongLongValue;
		} else {
			throw new NumberFormatException(uLongLongValue+" is not in the range of (0 to 18,446,744,073,709,551,615).");
		}
	}
	
	public ULongLong(long shiftedValue) {
		this.shiftedValue = shiftedValue;
		this.originalValue = new BigInteger(String.valueOf(shiftedValue)).add(ULongLong.SHIFT_VALUE);
	}
	
	public long getShiftedValue() {
		return this.shiftedValue;
	}
	
	public boolean equals(ULongLong x) {
		if (this.shiftedValue==x.getShiftedValue()) {
			return true;
		} else {
			return false;
		}
	}
	
	public ULongLong add(ULongLong x) {
		BigInteger newOriginalValue = this.originalValue.add(x.originalValue); 
		if (newOriginalValue.compareTo(ULongLong.MAX_VALUE) > 0) {
			throw new RuntimeException(newOriginalValue + " overflows for ULongLong.");
		} else {
			return new ULongLong(newOriginalValue);
		}
	}
	
	public ULongLong subtract(ULongLong x) {
		BigInteger newOriginalValue = this.originalValue.subtract(x.originalValue);
		if (newOriginalValue.compareTo(ULongLong.MIN_VALUE) < 0) {
			throw new RuntimeException(newOriginalValue + " overflows for ULongLong.");
		} else {
			return new ULongLong(newOriginalValue);
		}
	}
	
	public ULongLong divide(ULongLong x) {
		BigInteger newOriginalValue = this.originalValue.divide(x.originalValue);
		return new ULongLong(newOriginalValue);
	}
	
	public ULongLong multiply(ULongLong x) {
		BigInteger newOriginalValue = this.originalValue.multiply(x.originalValue);
		if (newOriginalValue.compareTo(ULongLong.MAX_VALUE) > 0) {
			throw new RuntimeException(newOriginalValue + " overflows for ULongLong.");
		} else {
			return new ULongLong(newOriginalValue);
		}
	}
	
	public int compareTo(ULongLong x) {
		BigInteger newOriginalValue = this.originalValue.subtract(x.originalValue);
		if (newOriginalValue.compareTo(ULongLong.MIN_VALUE) < 0) {
			return -1;
		} else if (newOriginalValue.compareTo(ULongLong.MIN_VALUE) > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * @{inheritdoc}
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.originalValue.toString();
	}
}



