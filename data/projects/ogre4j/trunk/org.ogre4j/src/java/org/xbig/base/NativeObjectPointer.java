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
 * The native object pointer is a generic to handle pointer pointers in C. E.g.
 * float ** a.
 * 
 */
public class NativeObjectPointer<T extends INativeObject> extends NativeObject
{
    /**
     * Creates a new java object that points to a pointer-pointer created
     * remote.
     * 
     * <p>
     * <b>Note:</b> This is not public API. Constructor is public for
     * internal use only.
     * </p>
     * 
     * @param pInstance
     *            The instance pointer for this NativeObjectPointer.
     */
    public NativeObjectPointer(InstancePointer pInstance)
    {
        super(pInstance);
    }

    /**
     * Native function to retreive the pointer pointer.
     * @param pInstance The this pointer.
     * @return The pointer pointer.
     */
    private native long _getObject(long pInstance);

    /* (non-Javadoc)
     * @see org.xbig.base.NativeObject#delete()
     */
    @Override
    public void delete()
    {
        //do nothing. user should never call this function.        
    }

    /**
     * Retreives the native object this NativeObjectPointer points to.
     * <p>
     * <b>Note:</b> For the given native object this function will free
     * possible alocated memory by calling {@link INativeObject#delete()}.
     * </p>
     * 
     * @param nativeObject
     *            The native object to set the instance pointer.
     * @throws NullPointerException
     *             if the native object is null.
     */
    public void getObject(T nativeObject)
    {
        nativeObject.delete();
        long ptr = _getObject(object.pointer);
        if (ptr == 0)
            throw new NullPointerException();
        nativeObject.setInstancePointer(ptr);
    }
}
