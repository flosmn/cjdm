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
public interface IPSSMShadowCameraSetup extends INativeObject, org.ogre4j.ILiSPSMShadowCameraSetup {

public interface ISplitPointList extends INativeObject, org.std.Ivector< Float > {

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
public interface IOptimalAdjustFactorList extends INativeObject, org.std.Ivector< Float > {

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
    /** 
    Calculate a new splitting scheme. **/
    public void calculateSplitPoints(int splitCount, float nearDist, float farDist, float lambda);

    /** 
    Manually configure a new splitting scheme. **/
    public void setSplitPoints(org.ogre4j.IPSSMShadowCameraSetup.ISplitPointList newSplitPoints);

    /** 
    Set the LiSPSM optimal adjust factor for a given split (call after configuring splits). **/
    public void setOptimalAdjustFactor(int splitIndex, float factor);

    /** 
    Set the padding factor to apply to the near & far distances when matching up splits to one another, to avoid 'cracks'. **/
    public void setSplitPadding(float pad);

    /** 
    Get the padding factor to apply to the near & far distances when matching up splits to one another, to avoid 'cracks'. **/
    public float getSplitPadding();

    /** **/
    public int getSplitCount();

    /** **/
    public void getShadowCamera(org.ogre4j.ISceneManager sm, org.ogre4j.ICamera cam, org.ogre4j.IViewport vp, org.ogre4j.ILight light, org.ogre4j.ICamera texCam, int iteration);

    /** **/
    public org.ogre4j.IPSSMShadowCameraSetup.ISplitPointList getSplitPoints();

    /** **/
    public float getOptimalAdjustFactor(int splitIndex);

    /** **/
    public float getOptimalAdjustFactor();

}
