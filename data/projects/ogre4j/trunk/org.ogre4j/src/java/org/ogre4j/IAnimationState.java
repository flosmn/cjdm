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
public interface IAnimationState extends INativeObject, org.ogre4j.IAnimationAllocatedObject {

public interface IBoneBlendMask extends INativeObject, org.std.Ivector< Float > {

    /** **/
    public void assign(int num, float val);

    /** **/
    public FloatPointer at(int loc);

    /** **/
    public FloatPointer back();

    /** **/
    public int capacity();

    /** **/
    public void clear();

    /** **/
    public boolean empty();

    /** **/
    public FloatPointer front();

    /** **/
    public int max_size();

    /** **/
    public void pop_back();

    /** **/
    public void push_back(float val);

    /** **/
    public void reserve(int size);

    /** **/
    public int size();

}
    /** **/
    public String getAnimationName();

    /** **/
    public float getTimePosition();

    /** **/
    public void setTimePosition(float timePos);

    /** **/
    public float getLength();

    /** **/
    public void setLength(float len);

    /** **/
    public float getWeight();

    /** **/
    public void setWeight(float weight);

    /** 
    Modifies the time position, adjusting for animation length **/
    public void addTime(float offset);

    /** **/
    public boolean hasEnded();

    /** **/
    public boolean getEnabled();

    /** **/
    public void setEnabled(boolean enabled);

    /** **/
    public boolean operatorEqual(org.ogre4j.IAnimationState rhs);

    /** **/
    public boolean operatorNotEqual(org.ogre4j.IAnimationState rhs);

    /** 
    Sets whether or not an animation loops at the start and end of the animation if the time continues to be altered. **/
    public void setLoop(boolean loop);

    /** **/
    public boolean getLoop();

    /** 
    Copies the states from another animation state, preserving the animation name (unlike operator=) but copying everything else. **/
    public void copyStateFrom(org.ogre4j.IAnimationState animState);

    /** **/
    public org.ogre4j.IAnimationStateSet getParent();

    /** 
    In addition to assigning a single weight value to a skeletal animation, it may be desirable to assign animation weights per bone using a 'blend mask'.
    **/
    public void createBlendMask(int blendMaskSizeHint, float initialWeight);

    /** **/
    public void destroyBlendMask();

    /** 
    **/
    public void _setBlendMaskData(FloatPointer blendMaskData);

    /** 
    **/
    public void _setBlendMask(org.ogre4j.IAnimationState.IBoneBlendMask blendMask);

    /** **/
    public org.ogre4j.IAnimationState.IBoneBlendMask getBlendMask();

    /** **/
    public boolean hasBlendMask();

    /** **/
    public void setBlendMaskEntry(int boneHandle, float weight);

    /** **/
    public float getBlendMaskEntry(int boneHandle);

}
