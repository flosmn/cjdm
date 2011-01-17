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
 * Allows allocation of a memory region and accessing it char wise.
 * 
 */
public class NativeCharBuffer extends NativeObject {

    /**
     * Size of this buffer.
     */
    private int size = 0;

    /*
     * (non-Javadoc)
     * 
     * @see org.xbig.base.NativeObject#NativeObject(InstancePointer)
     */
    public NativeCharBuffer(InstancePointer pInstance) {
        super(pInstance);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xbig.base.NativeObject#NativeObject(InstancePointer, boolean)
     */
    public NativeCharBuffer(InstancePointer pointer, boolean b) {
        super(pointer,b);
    }

    /**
     * Allocate memory of passed size.
     * 
     * @param size Size of memory to allocate.
     */
    public NativeCharBuffer(int size) {
        super(new InstancePointer(_create(size)),false);
        this.size = size;
    }

    private static native long _create(int size);

    private native void _dispose(long pInstance);

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
     * Get value from buffer at a given index.
     * 
     * @param index Index to get value from.
     */
    public char getIndex(int index) {
        if (this.deleted) {
            throw new IllegalStateException();
        }
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException();
        }
        return _getIndex(object.pointer, index);
    }
    private native char _getIndex(long pInstance, int index);

    /**
     * Set value at a given index.
     * 
     * @param index Index to set.
     * @param value Value to set.
     */
    public void setIndex(int index, char value) {
        if (this.deleted) {
            throw new IllegalStateException();
        }
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException();
        }
        _setIndex(object.pointer, index, value);
    }
    private native void _setIndex(long pInstance, int index, char value);

    /**
     * Get size of this buffer.
     * 
     * @return Size of this buffer.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Get a VoidPointer for this buffer.
     * 
     * @return VoidPointer pointing at this buffer.
     */
    public VoidPointer getVoidPointer() {
        return new VoidPointer(new InstancePointer(object.pointer), true);
    }
    
    public void finalize() {
		if(!this.remote && !this.deleted) {
			delete();
		}
	}
}
