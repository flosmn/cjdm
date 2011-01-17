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

import javax.vecmath.Color4f;
import javax.vecmath.Vector3f;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ogre4j.eclipse.ogreface.OgrePlugin;
import org.ogre4j.eclipse.ogreface.OgrePreferenceInitializer;
import org.ogre4j.eclipse.ogreface.examples.RenderLoopSometimes;
import org.ogre4j.eclipse.ogreface.examples.verysimplescene.VerySimpleCameraProvider;
import org.ogre4j.eclipse.ogreface.examples.verysimplescene.VerySimpleContentProvider;
import org.ogre4j.eclipse.ogreface.examples.verysimplescene.VerySimpleEntityProvider;
import org.ogre4j.eclipse.ogreface.examples.verysimplescene.VerySimpleLightProvider;
import org.ogre4j.eclipse.ogreface.examples.verysimplescene.VerySimpleNodeProvider;
import org.ogre4j.eclipse.ogreface.ogresys.OgreCanvas;
import org.ogre4j.eclipse.ogreface.ogresys.OgreSystem;
import org.ogre4j.eclipse.ogreface.ogresys.SceneType;
import org.ogre4j.eclipse.ogreface.ogresys.ShadowType;
import org.ogre4j.eclipse.ogreface.viewer.OgreViewer;

public class InteractiveTests {

	private Display display = null;
	private Shell shell = null;


	@BeforeClass
	public static void createAndStartPlugin() throws Exception {
		OgreSystem plugin = new OgreSystem();
		plugin.start("","",InteractiveTests.class.getSimpleName()+".log");
	}

	@AfterClass
	public static void stopPlugin() throws Exception {
		OgreSystem.getDefault().stop();
	}

	@Before
	public void setUp() {
		OgreSystem.getDefault().setRenderLoop(new RenderLoopSometimes());
		OgreSystem.getDefault().startRendering();

		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(640, 480);
	}

	@After
	public void tearDown() {
		OgreSystem.getDefault().stopRendering();
		display.dispose();
	}

	@Test
	public void renderSimpleScene() throws Exception {
		OgreCanvas ogreCanvas = new OgreCanvas(shell, "renderSimpleScene", SceneType.GENERIC, ShadowType.NONE);

		//shell.open();

		ogreCanvas.setSceneCreation(true);
		ogreCanvas.setAmbientLight(new Color4f(1, 1, 1, 1));
		String name = "DomainObject";
		String camName = "CamObject";
		Vector3f domainObjectPos = new Vector3f(0.0f, -50.0f, -100.0f);

		ogreCanvas.createNode(camName, null);
		ogreCanvas.setNodePosition(camName, new Vector3f(0, 0, 0));
		ogreCanvas.createCamera(camName);
		ogreCanvas.setCamBackgroundColour(camName, new Color4f(0.0f, 0.0f, 0.0f, 0.0f));
		ogreCanvas.setCamLookAt(camName, domainObjectPos);
		ogreCanvas.setActiveCamera(camName);

		OgreSystem.getDefault().addResourceLocation(OgrePlugin.getDefault().getPluginPreferences().getString(OgrePreferenceInitializer.OGRE_DEFAULT_RESOURCE_LOCATION_KEY), "FileSystem", "General", true);
		OgreSystem.getDefault().initialiseAllResourceGroups();

		ogreCanvas.createNode(name, null);
		ogreCanvas.createEntity(name, "ogrehead.mesh");
		ogreCanvas.setNodePosition(name, domainObjectPos);
		ogreCanvas.setSceneCreation(false);

		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
	}

	@Test
	public void verySimpleViewer() throws Exception {
	    OgreCanvas canvas = new OgreCanvas(shell, "verySimpleViewer", SceneType.GENERIC, ShadowType.NONE);
		OgreViewer viewer = new OgreViewer(canvas);

		viewer.setContentProvider(new VerySimpleContentProvider());
		viewer.setLabelProvider(new VerySimpleNodeProvider());
		viewer.setEntityProvider(new VerySimpleEntityProvider());
		viewer.setCameraProvider(new VerySimpleCameraProvider());
		viewer.setLightProvider(new VerySimpleLightProvider());

		//viewer.setInput(VerySimpleDomainObject.getInput());

		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
	}

	public static void main(String[] args) throws Exception {
		InteractiveTests.createAndStartPlugin();
		InteractiveTests tests = new InteractiveTests();

//		tests.setUp();
//		tests.renderSimpleScene();
//		tests.tearDown();

		tests.setUp();
		tests.verySimpleViewer();
		tests.tearDown();

		InteractiveTests.stopPlugin();
	}
}
