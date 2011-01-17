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
package org.ogre4j.eclipse.ogreface.examples;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.ogre4j.eclipse.ogreface.examples.navigators.FirstPersonNavigator;
import org.ogre4j.eclipse.ogreface.examples.verysimplescene.VerySimpleCameraProvider;
import org.ogre4j.eclipse.ogreface.examples.verysimplescene.VerySimpleContentProvider;
import org.ogre4j.eclipse.ogreface.examples.verysimplescene.VerySimpleEntityProvider;
import org.ogre4j.eclipse.ogreface.examples.verysimplescene.VerySimpleLightProvider;
import org.ogre4j.eclipse.ogreface.examples.verysimplescene.VerySimpleNodeProvider;
import org.ogre4j.eclipse.ogreface.examples.verysimplescene.VerySimpleScene;
import org.ogre4j.eclipse.ogreface.ogresys.OgreCanvas;
import org.ogre4j.eclipse.ogreface.ogresys.OgreScene;
import org.ogre4j.eclipse.ogreface.ogresys.OgreSystem;
import org.ogre4j.eclipse.ogreface.ogresys.SceneType;
import org.ogre4j.eclipse.ogreface.ogresys.ShadowType;
import org.ogre4j.eclipse.ogreface.viewer.OgreViewer;

public class SimpleOgreView extends ViewPart {

    private VerySimpleContentProvider contentProvider;

    @Override
    public void createPartControl(Composite parent) {
    	OgreSystem.getDefault().setRenderLoop(new RenderLoopSometimes());
    	OgreSystem.getDefault().startRendering();

        parent.setLayout(new FillLayout());
        try {
            /*
             * use a plain canvas OgreCanvas ogreCanvas = new OgreCanvas(parent,
             * "Ogre View", SceneType.GENERIC, ShadowType.NONE);
             * 
             * ogreCanvas.setSceneCreation(true); ogreCanvas.setAmbientLight(new
             * Color4f(new float[]{1,1,1,1})); String name = "DomainObject";
             * String camName = "CamObject"; Vector3f domainObjectPos = new
             * Vector3f(0.0f, -50.0f, -100.0f);
             * 
             * ogreCanvas.createNode(camName, null);
             * ogreCanvas.setNodePosition(camName, new Vector3f(0, 0, 0));
             * ogreCanvas.createCamera(camName);
             * ogreCanvas.setCamBackgroundColour(camName, new Color4f(new
             * float[]{0.0f,0.0f,0.0f,0.0f})); ogreCanvas.setCamLookAt(camName,
             * domainObjectPos); ogreCanvas.setActiveCamera(camName);
             * 
             * "FileSystem", "General", true);
             * OgrePlugin.getDefault().addResourceLocation(OgrePlugin.getDefault().getPluginPreferences().getString(OgrePreferenceInitializer.OGRE_DEFAULT_RESOURCE_LOCATION_KEY),
             * "FileSystem", "General", true);
             * OgrePlugin.getDefault().initialiseAllResourceGroups();
             * 
             * ogreCanvas.createNode(name, null); ogreCanvas.createEntity(name,
             * "ogrehead.mesh"); ogreCanvas.setNodePosition(name,
             * domainObjectPos); ogreCanvas.setSceneCreation(false);
             */

            /*
             * use a viewer
             */
            VerySimpleScene scene = VerySimpleScene.getCakeScene();

            OgreScene ogreScene = new OgreScene(SceneType.GENERIC, ShadowType.NONE);
            OgreCanvas canvas = new OgreCanvas(parent, "Ogre View", ogreScene);
            
            OgreViewer viewer = new OgreViewer(canvas);

            this.contentProvider = new VerySimpleContentProvider();
            viewer.setContentProvider(this.contentProvider);
            viewer.setLabelProvider(new VerySimpleNodeProvider());
            viewer.setEntityProvider(new VerySimpleEntityProvider());
            viewer.setCameraProvider(new VerySimpleCameraProvider());
            viewer.setLightProvider(new VerySimpleLightProvider());
            viewer.addNavigator(new FirstPersonNavigator(), this.contentProvider.getActiveCamera(scene));
            viewer.setInput(scene);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void setFocus() {
    }

}
