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
public interface IPlaneBoundedVolume extends INativeObject {

public interface IPlaneList extends INativeObject, org.std.Ivector< org.ogre4j.IPlane > {

    /** **/
    public void assign(int num, org.ogre4j.IPlane val);

    /** **/
    public org.ogre4j.IPlane at(int loc);

    /** **/
    public org.ogre4j.IPlane back();

    /** **/
    public int capacity();

    /** **/
    public void clear();

    /** **/
    public boolean empty();

    /** **/
    public org.ogre4j.IPlane front();

    /** **/
    public int max_size();

    /** **/
    public void pop_back();

    /** **/
    public void push_back(org.ogre4j.IPlane val);

    /** **/
    public void reserve(int size);

    /** **/
    public int size();

}
    /** 
    Intersection test with AABB **/
    public boolean intersects(org.ogre4j.IAxisAlignedBox box);

    /** 
    Intersection test with **/
    public boolean intersects(org.ogre4j.ISphere sphere);

    /** 
    Intersection test with a **/
    public void intersects(org.std.Ipair< Boolean, Float > returnValue, org.ogre4j.IRay ray);

    /** **/
    public void getplanes(org.ogre4j.IPlaneBoundedVolume.IPlaneList returnValue);

    /** **/
    public void setplanes(org.ogre4j.IPlaneBoundedVolume.IPlaneList _jni_value_);

    /** **/
    public org.ogre4j.Plane.Side getoutside();

    /** **/
    public void setoutside(org.ogre4j.Plane.Side _jni_value_);

}
