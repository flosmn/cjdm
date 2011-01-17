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
public interface IRay extends INativeObject {

    /** 
    Sets the origin of the ray. **/
    public void setOrigin(org.ogre4j.IVector3 origin);

    /** 
    Gets the origin of the ray. **/
    public org.ogre4j.IVector3 getOrigin();

    /** 
    Sets the direction of the ray. **/
    public void setDirection(org.ogre4j.IVector3 dir);

    /** 
    Gets the direction of the ray. **/
    public org.ogre4j.IVector3 getDirection();

    /** 
    Gets the position of a point t units along the ray. **/
    public void getPoint(org.ogre4j.IVector3 returnValue, float t);

    /** 
    Gets the position of a point t units along the ray. **/
    public void operatorMultiplication(org.ogre4j.IVector3 returnValue, float t);

    /** 
    Tests whether this ray intersects the given plane. **/
    public void intersects(org.std.Ipair< Boolean, Float > returnValue, org.ogre4j.IPlane p);

    /** 
    Tests whether this ray intersects the given plane bounded volume. **/
    public void intersects(org.std.Ipair< Boolean, Float > returnValue, org.ogre4j.IPlaneBoundedVolume p);

    /** 
    Tests whether this ray intersects the given sphere. **/
    public void intersects(org.std.Ipair< Boolean, Float > returnValue, org.ogre4j.ISphere s);

    /** 
    Tests whether this ray intersects the given box. **/
    public void intersects(org.std.Ipair< Boolean, Float > returnValue, org.ogre4j.IAxisAlignedBox box);

}
