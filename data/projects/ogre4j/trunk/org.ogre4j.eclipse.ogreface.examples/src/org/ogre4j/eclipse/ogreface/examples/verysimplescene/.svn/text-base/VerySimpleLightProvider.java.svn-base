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
package org.ogre4j.eclipse.ogreface.examples.verysimplescene;

import javax.vecmath.Color4f;
import javax.vecmath.Vector3f;

import org.ogre4j.eclipse.ogreface.ogresys.LightType;
import org.ogre4j.eclipse.ogreface.viewer.IOgreLightProvider;

public class VerySimpleLightProvider implements IOgreLightProvider {

    public static final float attenuationConstant = 1;

    public static final float attenuationLinear = 1;

    public static final float attenuationQuadratic = 1;

    public static final float attenuationRange = 1;

    public static final Color4f diffuseColour = new Color4f(0.7f, 0.7f, 0.7f, 0);

    public static final Vector3f direction = new Vector3f(0, -0.5f, -1);

    public static final float powerScale = 1;

    public static final long queryFlags = 0;

    public static final Color4f specularColour = new Color4f(0.7f, 0.7f, 0.7f, 0);

    public static final float spotlightRangeFalloff = 5;

    public static final float spotlightRangeInnerAngle = 5;

    public static final float spotlightRangeOuterAngle = 5;

    public static final LightType type = LightType.DIRECTIONAL;

    public float getAttenuationConstant(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            if (domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT))
                return attenuationConstant;
        }
        return 0;
    }

    public float getAttenuationLinear(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            if (domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT))
                return attenuationLinear;
        }
        return 0;
    }

    public float getAttenuationQuadratic(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            if (domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT))
                return attenuationQuadratic;
        }
        return 0;
    }

    public float getAttenuationRange(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            if (domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT))
                return attenuationRange;
        }
        return 0;
    }

    public Color4f getDiffuseColour(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            if (domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT))
                return diffuseColour;
        }
        return null;
    }

    public Vector3f getDirection(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            if (domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT))
                return direction;
        }
        return null;
    }

    public float getPowerScale(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            if (domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT))
                return powerScale;
        }
        return 0;
    }

    public long getQueryFlags(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            if (domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT))
                return queryFlags;
        }
        return 0;
    }

    public Color4f getSpecularColour(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            if (domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT))
                return specularColour;
        }
        return null;
    }

    public float getSpotlightRangeFalloff(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            if (domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT))
                return spotlightRangeFalloff;
        }
        return 0;
    }

    public float getSpotlightRangeInnerAngle(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            if (domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT))
                return spotlightRangeInnerAngle;
        }
        return 0;
    }

    public float getSpotlightRangeOuterAngle(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            if (domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT))
                return spotlightRangeOuterAngle;
        }
        return 0;
    }

    public LightType getType(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            if (domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT))
                return type;
        }
        return null;
    }
}
