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
public interface IAnimationStateSet extends INativeObject, org.ogre4j.IAnimationAllocatedObject {

    /** 
    Create a new  instance. **/
    public org.ogre4j.IAnimationState createAnimationState(String animName, float timePos, float length, float weight, boolean enabled);

    /** **/
    public org.ogre4j.IAnimationState getAnimationState(String name);

    /** **/
    public boolean hasAnimationState(String name);

    /** **/
    public void removeAnimationState(String name);

    /** **/
    public void removeAllAnimationStates();

    /** 
    Get an iterator over all the animation states in this set. **/
    public void getAnimationStateIterator(org.ogre4j.IAnimationStateIterator returnValue);

    /** **/
    public void getAnimationStateIterator_const(org.ogre4j.IConstAnimationStateIterator returnValue);

    /** **/
    public void copyMatchingState(org.ogre4j.IAnimationStateSet target);

    /** **/
    public void _notifyDirty();

    /** **/
    public long getDirtyFrameNumber();

    /** **/
    public void _notifyAnimationStateEnabled(org.ogre4j.IAnimationState target, boolean enabled);

    /** **/
    public boolean hasEnabledAnimationState();

    /** 
    Get an iterator over all the enabled animation states in this set **/
    public void getEnabledAnimationStateIterator(org.ogre4j.IConstEnabledAnimationStateIterator returnValue);

}
