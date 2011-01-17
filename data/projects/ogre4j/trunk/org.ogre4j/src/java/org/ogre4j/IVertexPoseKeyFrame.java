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
public interface IVertexPoseKeyFrame extends INativeObject, org.ogre4j.IKeyFrame {

public static interface IPoseRef extends INativeObject {

    /** **/
    public int getposeIndex();

    /** **/
    public void setposeIndex(int _jni_value_);

    /** **/
    public float getinfluence();

    /** **/
    public void setinfluence(float _jni_value_);

}
public interface IPoseRefList extends INativeObject, org.std.Ivector< org.ogre4j.IVertexPoseKeyFrame.IPoseRef > {

    /** **/
    public void assign(int num, org.ogre4j.IVertexPoseKeyFrame.IPoseRef val);

    /** **/
    public org.ogre4j.IVertexPoseKeyFrame.IPoseRef at(int loc);

    /** **/
    public org.ogre4j.IVertexPoseKeyFrame.IPoseRef back();

    /** **/
    public int capacity();

    /** **/
    public void clear();

    /** **/
    public boolean empty();

    /** **/
    public org.ogre4j.IVertexPoseKeyFrame.IPoseRef front();

    /** **/
    public int max_size();

    /** **/
    public void pop_back();

    /** **/
    public void push_back(org.ogre4j.IVertexPoseKeyFrame.IPoseRef val);

    /** **/
    public void reserve(int size);

    /** **/
    public int size();

}
public interface IPoseRefIterator extends INativeObject, org.ogre4j.IVectorIterator< org.ogre4j.IVertexPoseKeyFrame.IPoseRefList > {

    /** **/
    public boolean hasMoreElements();

    /** **/
    public void getNext(org.ogre4j.IVertexPoseKeyFrame.IPoseRef returnValue);

    /** **/
    public void peekNext(org.ogre4j.IVertexPoseKeyFrame.IPoseRef returnValue);

    /** **/
    public org.ogre4j.IVertexPoseKeyFrame.IPoseRef peekNextPtr();

    /** **/
    public void moveNext();

}
public interface IConstPoseRefIterator extends INativeObject, org.ogre4j.IConstVectorIterator< org.ogre4j.IVertexPoseKeyFrame.IPoseRefList > {

    /** **/
    public boolean hasMoreElements();

    /** **/
    public void getNext(org.ogre4j.IVertexPoseKeyFrame.IPoseRef returnValue);

    /** **/
    public void peekNext(org.ogre4j.IVertexPoseKeyFrame.IPoseRef returnValue);

    /** **/
    public org.ogre4j.IVertexPoseKeyFrame.IPoseRef peekNextPtr();

    /** **/
    public void moveNext();

}
    /** 
    Add a new pose reference. **/
    public void addPoseReference(int poseIndex, float influence);

    /** 
    Update the influence of a pose reference. **/
    public void updatePoseReference(int poseIndex, float influence);

    /** 
    Remove reference to a given pose. **/
    public void removePoseReference(int poseIndex);

    /** 
    Remove all pose references. **/
    public void removeAllPoseReferences();

    /** 
    Get a const reference to the list of pose references. **/
    public org.ogre4j.IVertexPoseKeyFrame.IPoseRefList getPoseReferences();

    /** 
    Get an iterator over the pose references. **/
    public void getPoseReferenceIterator(org.ogre4j.IVertexPoseKeyFrame.IPoseRefIterator returnValue);

    /** **/
    public void getPoseReferenceIterator_const(org.ogre4j.IVertexPoseKeyFrame.IConstPoseRefIterator returnValue);

    /** 
    Clone a keyframe (internal use only) **/
    public org.ogre4j.IKeyFrame _clone(org.ogre4j.IAnimationTrack newParent);

}
