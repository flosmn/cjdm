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

import org.ogre4j.eclipse.ogreface.ogresys.CameraMode;
import org.ogre4j.eclipse.ogreface.viewer.IOgreCameraProvider;


public class VerySimpleCameraProvider implements IOgreCameraProvider {

	public static final Color4f backgroundColour = new Color4f(0.2f, 0.2f, 0.2f, 0.0f);
	public static final float farClippingDistance = 7531;
	public static final Vector3f lookAt = new Vector3f(0, 0, 0);
	public static final CameraMode mode = CameraMode.SOLID;
	public static final float nearClippingDistance = 2.46f;
	public static final long queryFlags = 0;


	public Color4f getBackgroundColour(Object element) {
		if (element instanceof VerySimpleDomainObject) {
			VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
			if (domObj.getType().equals(VerySimpleDomainObject.Type.CAMERA))
				return backgroundColour;
		}
		return null;
	}

	public float getFarClippingDistance(Object element) {
		if (element instanceof VerySimpleDomainObject) {
			VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
			if (domObj.getType().equals(VerySimpleDomainObject.Type.CAMERA))
				return farClippingDistance;
		}
		return 0;
	}

	public Vector3f getLookAt(Object element) {
		if (element instanceof VerySimpleDomainObject) {
			VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
			if (domObj.getType().equals(VerySimpleDomainObject.Type.CAMERA))
				return lookAt;
		}
		return null;
	}

	public CameraMode getMode(Object element) {
		if (element instanceof VerySimpleDomainObject) {
			VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
			if (domObj.getType().equals(VerySimpleDomainObject.Type.CAMERA))
				return mode;
		}
		return null;
	}

	public float getNearClippingDistance(Object element) {
		if (element instanceof VerySimpleDomainObject) {
			VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
			if (domObj.getType().equals(VerySimpleDomainObject.Type.CAMERA))
				return nearClippingDistance;
		}
		return 0;
	}

	public long getQueryFlags(Object element) {
		if (element instanceof VerySimpleDomainObject) {
			VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
			if (domObj.getType().equals(VerySimpleDomainObject.Type.CAMERA))
				return queryFlags;
		}
		return 0;
	}
}
