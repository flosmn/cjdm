/**
 * This file is part of ogre4j
 *  (The JNI bindings of OGRE - Object-Oriented Graphics Rendering Engine).
 *  
 * Copyright (c) 2005-2007 netAllied GmbH. All rights reserved.
 * Also see acknowledgements in README
 * 
 * ogre4j is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 * 
 * ogre4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with ogre4j; see the file COPYING.  If not, write to
 * the Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

package org.ogre4j.eclipse.ogreface.viewer;


public interface IOgreLightProvider {

    /**
     * 
     * @param name
     * @return String
     */
    // public String getParent (String name);

    /**
     * 
     * @param name
     * @return float
     */
    public float getAttenuationRange(Object element);

    /**
     * 
     * @param name
     * @return float
     */
    public float getAttenuationConstant(Object element);

    /**
     * 
     * @param name
     * @return float
     */
    public float getAttenuationLinear(Object element);

    /**
     * 
     * @param name
     * @return float
     */
    public float getAttenuationQuadratic(Object element);

    /**
     * 
     * @param name
     * @return javax.vecmath.Color4f
     */
    public javax.vecmath.Color4f getDiffuseColour(Object element);

    /**
     * 
     * @param name
     * @return javax.vecmath.Color4f
     */
    public javax.vecmath.Color4f getSpecularColour(Object element);

    /**
     * 
     * @param name
     * @return javax.vecmath.Vector3f
     */
    public javax.vecmath.Vector3f getDirection(Object element);

    /**
     * 
     * @param name
     * @return float
     */
    public float getSpotlightRangeInnerAngle(Object element);

    /**
     * 
     * @param name
     * @return float
     */
    public float getSpotlightRangeOuterAngle(Object element);

    /**
     * 
     * @param name
     * @return float
     */
    public float getSpotlightRangeFalloff(Object element);

    /**
     * 
     * @param name
     * @return float
     */
    public float getPowerScale(Object element);

    /**
     * 
     * @param name
     * @return ogreclipse.LightType
     */
    public org.ogre4j.eclipse.ogreface.ogresys.LightType getType(Object element);

    /**
     * 
     * @param element
     * @return long
     */
    public long getQueryFlags(java.lang.Object element);

}
