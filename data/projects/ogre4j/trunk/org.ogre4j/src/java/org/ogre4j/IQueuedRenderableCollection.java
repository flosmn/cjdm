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
public interface IQueuedRenderableCollection extends INativeObject, org.ogre4j.ISceneCtlAllocatedObject {

public static interface IDepthSortDescendingLess extends INativeObject {

    /** **/
    public boolean operatorFunctionCall(org.ogre4j.IRenderablePass a, org.ogre4j.IRenderablePass b);

    /** **/
    public org.ogre4j.ICamera getcamera();

}
public static interface IPassGroupLess extends INativeObject {

    /** **/
    public boolean operatorFunctionCall(org.ogre4j.IPass a, org.ogre4j.IPass b);

}
public static interface IRadixSortFunctorDistance extends INativeObject {

    /** **/
    public float operatorFunctionCall(org.ogre4j.IRenderablePass p);

    /** **/
    public org.ogre4j.ICamera getcamera();

}
public static interface IRadixSortFunctorPass extends INativeObject {

    /** **/
    public long operatorFunctionCall(org.ogre4j.IRenderablePass p);

}
public interface IRenderablePassList extends INativeObject, org.std.Ivector< org.ogre4j.IRenderablePass > {

    /** **/
    public void assign(int num, org.ogre4j.IRenderablePass val);

    /** **/
    public org.ogre4j.IRenderablePass at(int loc);

    /** **/
    public org.ogre4j.IRenderablePass back();

    /** **/
    public int capacity();

    /** **/
    public void clear();

    /** **/
    public boolean empty();

    /** **/
    public org.ogre4j.IRenderablePass front();

    /** **/
    public int max_size();

    /** **/
    public void pop_back();

    /** **/
    public void push_back(org.ogre4j.IRenderablePass val);

    /** **/
    public void reserve(int size);

    /** **/
    public int size();

}
public interface IRenderableList extends INativeObject, org.std.Ivector< org.ogre4j.IRenderable > {

    /** **/
    public void assign(int num, org.ogre4j.IRenderable val);

    /** **/
    public org.ogre4j.IRenderable at(int loc);

    /** **/
    public org.ogre4j.IRenderable back();

    /** **/
    public int capacity();

    /** **/
    public void clear();

    /** **/
    public boolean empty();

    /** **/
    public org.ogre4j.IRenderable front();

    /** **/
    public int max_size();

    /** **/
    public void pop_back();

    /** **/
    public void push_back(org.ogre4j.IRenderable val);

    /** **/
    public void reserve(int size);

    /** **/
    public int size();

}
public interface IPassGroupRenderableMap extends INativeObject, org.std.Imap< org.ogre4j.IPass, org.ogre4j.IQueuedRenderableCollection.IRenderableList > {

    /** **/
    public void clear();

    /** **/
    public int count(org.ogre4j.IPass key);

    /** **/
    public boolean empty();

    /** **/
    public int erase(org.ogre4j.IPass key);

    /** **/
    public int max_size();

    /** **/
    public int size();

    /** **/
    public org.ogre4j.IQueuedRenderableCollection.IRenderableList get(org.ogre4j.IPass key);

    /** **/
    public void insert(org.ogre4j.IPass key, org.ogre4j.IQueuedRenderableCollection.IRenderableList value);

}
    /** **/
    public void clear();

    /** 
    Remove the group entry (if any) for a given . **/
    public void removePassGroup(org.ogre4j.IPass p);

    /** 
    Reset the organisation modes required for this collection. **/
    public void resetOrganisationModes();

    /** 
    Add a required sorting / grouping mode to this collection when next used. **/
    public void addOrganisationMode(org.ogre4j.QueuedRenderableCollection.OrganisationMode om);

    /** **/
    public void addRenderable(org.ogre4j.IPass pass, org.ogre4j.IRenderable rend);

    /** 
    Perform any sorting that is required on this collection. **/
    public void sort(org.ogre4j.ICamera cam);

    /** 
    Accept a visitor over the collection contents. **/
    public void acceptVisitor(org.ogre4j.IQueuedRenderableVisitor visitor, org.ogre4j.QueuedRenderableCollection.OrganisationMode om);

}
