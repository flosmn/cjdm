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
public interface ISceneQuery extends INativeObject, org.ogre4j.ISceneCtlAllocatedObject {

public static interface IWorldFragment extends INativeObject {

    /** **/
    public org.ogre4j.SceneQuery.WorldFragmentType getfragmentType();

    /** **/
    public void setfragmentType(org.ogre4j.SceneQuery.WorldFragmentType _jni_value_);

    /** **/
    public void getsingleIntersection(org.ogre4j.IVector3 returnValue);

    /** **/
    public void setsingleIntersection(org.ogre4j.IVector3 _jni_value_);

    /** **/
    public void getplanes(org.std.Ilist< org.ogre4j.IPlane > returnValue);

    /** **/
    public void setplanes(org.std.Ilist< org.ogre4j.IPlane > _jni_value_);

    /** **/
    public VoidPointer getgeometry();

    /** **/
    public void setgeometry(VoidPointer _jni_value_);

    /** **/
    public org.ogre4j.IRenderOperation getrenderOp();

    /** **/
    public void setrenderOp(org.ogre4j.IRenderOperation _jni_value_);

}
    /** 
    Sets the mask for results of this query. **/
    public void setQueryMask(long mask);

    /** 
    Returns the current mask for this query. **/
    public long getQueryMask();

    /** 
    Sets the type mask for results of this query. **/
    public void setQueryTypeMask(long mask);

    /** 
    Returns the current mask for this query. **/
    public long getQueryTypeMask();

    /** 
    Tells the query what kind of world geometry to return from queries; often the full renderable geometry is not what is needed. **/
    public void setWorldFragmentType(org.ogre4j.SceneQuery.WorldFragmentType wft);

    /** 
    Gets the current world fragment types to be returned from the query. **/
    public org.ogre4j.SceneQuery.WorldFragmentType getWorldFragmentType();

    /** 
    Returns the types of world fragments this query supports. **/
    public void getSupportedWorldFragmentTypes(org.std.Iset< org.ogre4j.SceneQuery.WorldFragmentType > returnValue);

}
