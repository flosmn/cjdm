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

import org.eclipse.jface.viewers.Viewer;
import org.ogre4j.eclipse.ogreface.ogresys.OgreCanvas;
import org.ogre4j.eclipse.ogreface.ogresys.OgreScene;
import org.ogre4j.eclipse.ogreface.ogresys.OgreSystem;
import org.ogre4j.eclipse.ogreface.viewer.INavigator;
import org.ogre4j.eclipse.ogreface.viewer.IOgreContentProvider;
import org.ogre4j.eclipse.ogreface.viewer.OgreViewer;

public class VerySimpleContentProvider implements IOgreContentProvider {

	/**
     * return children for any element in the tree
     */
    public Object[] getChildren(Object parentElement) {
        return null;
    }

    public Object getParent(Object element) {
        return null;
    }

    public boolean hasChildren(Object element) {
        return false;
    }

    /**
     * return root elements for input
     */
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof VerySimpleScene) {
            VerySimpleScene scene = (VerySimpleScene) inputElement;
            return scene.getElements();
        }
        return null;
    }

    public void dispose() {
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        try {
            // set up ogre resources for this scene
            OgreSystem.getDefault().addResourceLocation("media_simple", "FileSystem", "General", true);
            // OgrePlugin.getDefault().addResourceLocation(OgrePlugin.getDefault().getPluginPreferences().getString(OgrePreferenceInitializer.OGRE_DEFAULT_RESOURCE_LOCATION_KEY),
            // "FileSystem", "General", true);
            OgreSystem.getDefault().initialiseAllResourceGroups();

            // set ambient light
            ((OgreCanvas) viewer.getControl()).getScene().
            	setAmbientLight(new Color4f(0.7f, 0.7f, 0.7f, 0));

            // set cameras for navigators
            INavigator[] navis = ((OgreViewer) viewer).getNavigators();
            for (int i = 0; i < navis.length; i++) {
                navis[i].setCameraName(((VerySimpleScene) newInput).getCameraName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getActiveCamera(Object input) {
        if (input instanceof VerySimpleScene) {
            VerySimpleScene scene = (VerySimpleScene) input;
            return scene.getCameraName();
        }
        return null;
    }
}
