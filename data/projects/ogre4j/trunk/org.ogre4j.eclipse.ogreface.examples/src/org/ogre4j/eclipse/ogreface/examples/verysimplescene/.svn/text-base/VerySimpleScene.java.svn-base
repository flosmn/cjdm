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

import java.util.Vector;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * TODO VerySimpleScene type/class description.
 * 
 * @author kklesats
 */
public class VerySimpleScene {

    private final String cameraName = "camera";

    Vector<VerySimpleDomainObject> sceneElements = new Vector<VerySimpleDomainObject>();

    public VerySimpleScene() {
        this.addSceneElement(new VerySimpleDomainObject(getCameraName(), VerySimpleDomainObject.Type.CAMERA,
                new Vector3f(0, 0, 20), new Quat4f(0, 0, 0, 1)));
    }

    void addSceneElement(VerySimpleDomainObject sceneElement) {
        this.sceneElements.add(sceneElement);
    }

    public static VerySimpleScene getBau24Scene() {
        VerySimpleScene scene = new VerySimpleScene();

        scene.addSceneElement(new VerySimpleDomainObject("ar000005_000_prw", "ar000005_000_prw.mesh"));
        scene.addSceneElement(new VerySimpleDomainObject("ar000006_000_prw", "ar000006_000_prw.mesh"));
        scene.addSceneElement(new VerySimpleDomainObject("ar000007_000_prw", "ar000007_000_prw.mesh"));
        scene.addSceneElement(new VerySimpleDomainObject("br000004_000_prw", "br000004_000_prw.mesh"));
        scene.addSceneElement(new VerySimpleDomainObject("dl000005_000_prw", "dl000005_000_prw.mesh"));
        scene.addSceneElement(new VerySimpleDomainObject("el000002_000_prw", "el000002_000_prw.mesh"));
        scene.addSceneElement(new VerySimpleDomainObject("he000004_000_prw", "he000004_000_prw.mesh"));
        scene.addSceneElement(new VerySimpleDomainObject("la000040_000_bes", "la000040_000_bes.mesh"));
        scene.addSceneElement(new VerySimpleDomainObject("la000041_000_prw", "la000041_000_prw.mesh"));
        scene.addSceneElement(new VerySimpleDomainObject("lu000001_000_prw", "lu000001_000_prw.mesh"));
        scene.addSceneElement(new VerySimpleDomainObject("rf000001_000_prw", "rf000001_000_prw.mesh"));
        scene.addSceneElement(new VerySimpleDomainObject("sb000002_000_prw", "sb000002_000_prw.mesh"));
        scene.addSceneElement(new VerySimpleDomainObject("tf000004_000_prw", "tf000004_000_prw.mesh"));
        scene.addSceneElement(new VerySimpleDomainObject("wv000004_000_prw", "wv000004_000_prw.mesh"));

        return scene;
    }

    public static VerySimpleScene getCakeScene() {
        VerySimpleScene scene = new VerySimpleScene();

        scene.addSceneElement(new VerySimpleDomainObject("cake", "zuh.mesh"));
        return scene;
    }

    public VerySimpleDomainObject[] getElements() {
        VerySimpleDomainObject[] array = new VerySimpleDomainObject[this.sceneElements.size()];
        this.sceneElements.toArray(array);
        return array;
    }

    /**
     * @return the cameraName
     */
    public String getCameraName() {
        return cameraName;
    }
}
