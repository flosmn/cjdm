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
public interface IMemoryDataStreamPtr extends INativeObject, org.ogre4j.ISharedPtr< org.ogre4j.IMemoryDataStream > {

    /** **/
    public org.ogre4j.IMemoryDataStreamPtr operatorAssignment(org.ogre4j.IMemoryDataStreamPtr r);

    /** **/
    public org.ogre4j.IMemoryDataStream operatorMultiplication();

    /** **/
    public org.ogre4j.IMemoryDataStream operatorMemberAccessFromAPointer();

    /** **/
    public org.ogre4j.IMemoryDataStream get();

    /** **/
    public void bind(org.ogre4j.IMemoryDataStream rep, org.ogre4j.SharedPtrFreeMethod freeMethod);

    /** **/
    public boolean unique();

    /** **/
    public long useCount();

    /** **/
    public LongPointer useCountPointer();

    /** **/
    public org.ogre4j.IMemoryDataStream getPointer();

    /** **/
    public org.ogre4j.SharedPtrFreeMethod freeMethod();

    /** **/
    public boolean isNull();

    /** **/
    public void setNull();

}
