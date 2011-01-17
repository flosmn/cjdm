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
public interface IVertexBufferBinding extends INativeObject, org.ogre4j.IGeometryAllocatedObject {

public interface IVertexBufferBindingMap extends INativeObject, org.std.Imap< Integer, org.ogre4j.IHardwareVertexBufferSharedPtr > {

    /** **/
    public void clear();

    /** **/
    public int count(int key);

    /** **/
    public boolean empty();

    /** **/
    public int erase(int key);

    /** **/
    public int max_size();

    /** **/
    public int size();

    /** **/
    public org.ogre4j.IHardwareVertexBufferSharedPtr get(int key);

    /** **/
    public void insert(int key, org.ogre4j.IHardwareVertexBufferSharedPtr value);

}
public interface IBindingIndexMap extends INativeObject, org.std.Imap< Integer, Integer > {

    /** **/
    public void clear();

    /** **/
    public int count(int key);

    /** **/
    public boolean empty();

    /** **/
    public int erase(int key);

    /** **/
    public int max_size();

    /** **/
    public int size();

    /** **/
    public IntegerPointer get(int key);

    /** **/
    public void insert(int key, IntegerPointer value);

}
    /** 
    Set a binding, associating a vertex buffer with a given index. **/
    public void setBinding(int index, org.ogre4j.IHardwareVertexBufferSharedPtr buffer);

    /** 
    Removes an existing binding. **/
    public void unsetBinding(int index);

    /** 
    Removes all the bindings. **/
    public void unsetAllBindings();

    /** **/
    public org.ogre4j.IVertexBufferBinding.IVertexBufferBindingMap getBindings();

    /** **/
    public org.ogre4j.IHardwareVertexBufferSharedPtr getBuffer(int index);

    /** **/
    public boolean isBufferBound(int index);

    /** **/
    public int getBufferCount();

    /** 
    Gets the highest index which has already been set, plus 1. **/
    public int getNextIndex();

    /** 
    Gets the last bound index. **/
    public int getLastBoundIndex();

    /** 
    Check whether any gaps in the bindings. **/
    public boolean hasGaps();

    /** 
    Remove any gaps in the bindings. **/
    public void closeGaps(org.ogre4j.IVertexBufferBinding.IBindingIndexMap bindingIndexMap);

}
