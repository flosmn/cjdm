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

package org.ogre4j.eclipse.ogreface.ogresys;

import javax.vecmath.Color4f;
import javax.vecmath.Point3f;

class CamData {

    private float nearClippingDistance = 5;

    private float farClippingDistance = 5000;

    private CameraMode mode = CameraMode.SOLID;

    private Point3f lookAt = new Point3f((float) 0.0, (float) 0.0, (float) 0.0);

    private Color4f bgCol = new Color4f(0, 0, 0, 0);

    private long queryFlags = 0;

    public Color4f getBgCol() {
        return bgCol;
    }

    public void setBgCol(Color4f bgCol) {
        this.bgCol = bgCol;
    }

    public float getFarClippingDistance() {
        return farClippingDistance;
    }

    public void setFarClippingDistance(float farClippingDistance) {
        this.farClippingDistance = farClippingDistance;
    }

    public CameraMode getMode() {
        return mode;
    }

    public void setMode(CameraMode mode) {
        this.mode = mode;
    }

    public float getNearClippingDistance() {
        return nearClippingDistance;
    }

    public void setNearClippingDistance(float nearClippingDistance) {
        this.nearClippingDistance = nearClippingDistance;
    }

    public long getQueryFlags() {
        return queryFlags;
    }

    public void setQueryFlags(long queryFlags) {
        this.queryFlags = queryFlags;
    }

    public Point3f getLookAt() {
        return lookAt;
    }

    public void setLookAt(Point3f lookAt) {
        this.lookAt = lookAt;
    }
}
