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
package org.ogre4j.eclipse.ogreface.tests;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ogre4j.eclipse.ogreface.OgrePlugin;
import org.ogre4j.eclipse.ogreface.OgrePreferenceInitializer;
import org.ogre4j.eclipse.ogreface.ogresys.OgreCanvas;
import org.ogre4j.eclipse.ogreface.ogresys.OgreSystem;
import org.ogre4j.eclipse.ogreface.ogresys.SceneType;
import org.ogre4j.eclipse.ogreface.ogresys.ShadowType;
import org.ogre4j.eclipse.ogreface.viewer.OgreViewer;

public class ViewerTests {

    private Display display = null;

    private Shell shell = null;

    private OgreViewer viewer = null;

    private final static String resourceLocation = OgrePlugin.getDefault().getPluginPreferences().getString(
            OgrePreferenceInitializer.OGRE_DEFAULT_RESOURCE_LOCATION_KEY);

    private final static String MESH_NAME = "ogrehead.mesh";

    // render window (-> viewer) must be created before resources are loaded
    private void setUpOgreResources() throws Exception {
        OgreSystem.getDefault().addResourceLocation(resourceLocation, "FileSystem", "General", true);
        OgreSystem.getDefault().initialiseAllResourceGroups();
    }

    @Before
    public void createWindow() {
        display = new Display();
        shell = new Shell(display);
        shell.setLayout(new FillLayout());
        shell.setSize(640, 480);

        OgreSystem.getDefault().setRenderLoop(null);
        /*
         * positionChanged = false; orientationChanged = false; scaleChanged =
         * false; visibleChanged = false; redrawn = false;
         */
    }

    @After
    public void destroyWindow() {
        if (viewer != null)
            viewer.dispose();
        if (!display.isDisposed())
            display.dispose();
        display = null;
        shell = null;
        viewer = null;
    }

    @BeforeClass
    public static void createAndStartPlugin() throws Exception {
        OgreSystem plugin = new OgreSystem();
        plugin.start("", "", ViewerTests.class.getSimpleName() + ".log");
    }

    @AfterClass
    public static void stopPlugin() throws Exception {
        OgreSystem.getDefault().stop();
    }

    @Test
    public void createViewer() throws Exception {
        viewer = new OgreViewer(new OgreCanvas(shell, "createViewer", SceneType.GENERIC, ShadowType.NONE));
        Assert.assertNotNull(viewer.getControl());
    }

    @Test
    public void createViewer_DisposeCanvas() throws Exception {
        viewer = new OgreViewer(new OgreCanvas(shell, "createViewer_DisposeCanvas", SceneType.GENERIC, ShadowType.NONE));
        Control canvas = viewer.getControl();
        Assert.assertFalse(viewer.isDisposed());
        canvas.dispose();
        Assert.assertTrue(viewer.isDisposed());
        Assert.assertNull(viewer.getControl());
    }

    @Test
    public void createViewer_Dispose() throws Exception {
        viewer = new OgreViewer(new OgreCanvas(shell, "createViewer_Dispose", SceneType.GENERIC, ShadowType.NONE));
        Control canvas = viewer.getControl();
        viewer.dispose();
        Assert.assertTrue(canvas.isDisposed());
        Assert.assertNull(viewer.getControl());
    }
}
