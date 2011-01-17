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

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.ogre4j.eclipse.ogreface.viewer.IOgreNodeProvider;

public class VerySimpleNodeProvider implements IOgreNodeProvider {

    public static final Vector3f SCALE = new Vector3f(1.0f, 1.0f, 1.0f);

    public String getName(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            return domObj.getName();
        }
        return null;
    }

    public Quat4f getOrientation(Object element) {
        // TODO Auto-generated method stub
        return null;
    }

    public Vector3f getPosition(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            return domObj.getPosition();
        }
        return null;
    }

    public Vector3f getScale(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            return SCALE;
        }
        return null;
    }

    public boolean isCamera(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            return domObj.getType().equals(VerySimpleDomainObject.Type.CAMERA);
        }
        return false;
    }

    public boolean isEntity(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            return domObj.getType().equals(VerySimpleDomainObject.Type.ENTITY);
        }
        return false;
    }

    public boolean isLight(Object element) {
        if (element instanceof VerySimpleDomainObject) {
            VerySimpleDomainObject domObj = (VerySimpleDomainObject) element;
            return domObj.getType().equals(VerySimpleDomainObject.Type.LIGHT);
        }
        return false;
    }

    public boolean isParticleSystem(Object element) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isShadowCaster(Object element) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isVisible(Object element) {
        // TODO Auto-generated method stub
        return false;
    }

    public void addListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub

    }

    public void dispose() {
        // TODO Auto-generated method stub

    }

    public boolean isLabelProperty(Object element, String property) {
        // TODO Auto-generated method stub
        return false;
    }

    public void removeListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub

    }
}
