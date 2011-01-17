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
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import org.eclipse.swt.layout.FillLayout;
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
import org.ogre4j.eclipse.ogreface.ogresys.IFrameListener;
import org.ogre4j.eclipse.ogreface.ogresys.IOgreNodeListener;
import org.ogre4j.eclipse.ogreface.ogresys.IRenderLoop;
import org.ogre4j.eclipse.ogreface.ogresys.LightType;
import org.ogre4j.eclipse.ogreface.ogresys.OgreCanvas;
import org.ogre4j.eclipse.ogreface.ogresys.OgreSystem;
import org.ogre4j.eclipse.ogreface.ogresys.SceneType;
import org.ogre4j.eclipse.ogreface.ogresys.ShadowType;

public class CanvasTests {

	private Display display = null;
	private Shell shell = null;
	private OgreCanvas ogreCanvas = null;


	private final static String resourceLocation = OgrePlugin.getDefault().getPluginPreferences().getString(OgrePreferenceInitializer.OGRE_DEFAULT_RESOURCE_LOCATION_KEY);
	private final static String MESH_NAME = "ogrehead.mesh";


	// render window (-> canvas) must be created before resources are loaded
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

		positionChanged = false;
		orientationChanged = false;
		scaleChanged = false;
		visibleChanged = false;
		redrawn = false;
	}

	@After
	public void destroyWindow() {
		if (ogreCanvas != null) ogreCanvas.dispose();
		display.dispose();
		display = null;
		shell = null;
		ogreCanvas = null;
	}

	@BeforeClass
	public static void createAndStartPlugin() throws Exception {
		OgreSystem plugin = new OgreSystem();
		plugin.start("", "", CanvasTests.class.getSimpleName()+".log");
	}

	@AfterClass
	public static void stopPlugin() throws Exception {
		OgreSystem.getDefault().stop();
	}

	@Test
	public void createCanvas() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createCanvasTest", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
	}

	@Test
	public void createNode() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createNodeTest", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("createNodeTest", null);
		Assert.assertTrue(ogreCanvas.hasNode("createNodeTest"));
	}

	@Test(expected= RuntimeException.class)
	public void createNode_NameExists() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createNodeTest_NameExists", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("createNodeTest_NameExists", null);
		ogreCanvas.createNode("createNodeTest_NameExists", null);
	}

	@Test
	public void createNodeWithParent() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createNodeWithParent", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("Parent", null);
		ogreCanvas.createNode("Child", "Parent");
		Assert.assertTrue(ogreCanvas.nodeHasChild("Parent", "Child"));
	}

	@Test
	public void removeNode() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "removeNodeTest", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("removeNodeTest", null);
		Assert.assertTrue(ogreCanvas.hasNode("removeNodeTest"));
		ogreCanvas.removeNodeWithChildrenAndObjects("removeNodeTest");
		Assert.assertFalse(ogreCanvas.hasNode("removeNodeTest"));
	}

	@Test
	public void removeNode_Child() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "removeNode_Child", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("removeNode_ChildParent", null);
		ogreCanvas.createNode("removeNode_ChildChild", "removeNode_ChildParent");
		Assert.assertTrue(ogreCanvas.nodeHasChild("removeNode_ChildParent", "removeNode_ChildChild"));
		ogreCanvas.removeNodeWithChildrenAndObjects("removeNode_ChildChild");
		Assert.assertTrue(ogreCanvas.hasNode("removeNode_ChildParent"));
		Assert.assertFalse(ogreCanvas.hasNode("removeNode_ChildChild"));
		Assert.assertFalse(ogreCanvas.nodeHasChild("removeNode_ChildParent", "removeNode_ChildChild"));
	}

	@Test
	public void removeNode_Parent() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "removeNode_Parent", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("removeNode_ParentParent", null);
		ogreCanvas.createNode("removeNode_ParentChild", "removeNode_ParentParent");
		Assert.assertTrue(ogreCanvas.nodeHasChild("removeNode_ParentParent", "removeNode_ParentChild"));
		ogreCanvas.removeNodeWithChildrenAndObjects("removeNode_ParentParent");
		Assert.assertFalse(ogreCanvas.hasNode("removeNode_ParentParent"));
		Assert.assertFalse(ogreCanvas.hasNode("removeNode_ParentChild"));
	}

	// TODO get this test working and for entities, lights and cams, too
	/*@Test
	public void removeNode_CreateItAgain() throws OgreException {
		ogreCanvas = new OgreCanvas(shell, "removeNode_CreateItAgainTest", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("removeNode_CreateItAgain", null);
		Assert.assertTrue(ogreCanvas.hasNode("removeNode_CreateItAgain"));
		ogreCanvas.removeNodeWithChildrenAndObjects("removeNode_CreateItAgain");
		Assert.assertFalse(ogreCanvas.hasNode("removeNode_CreateItAgain"));
		ogreCanvas.createNode("removeNode_CreateItAgain", null);
		Assert.assertTrue(ogreCanvas.hasNode("removeNode_CreateItAgain"));
	}*/

	private boolean positionChanged = false;
	private boolean orientationChanged = false;
	private boolean scaleChanged = false;
	private boolean visibleChanged = false;

	private class ExpectEventNodeListener implements IOgreNodeListener {
		public void orientationChanged(String name, Quat4f orientation) {
			orientationChanged = true;
		}
		public void positionChanged(String name, Vector3f position) {
			positionChanged = true;
		}
		public void scaleChanged(String name, Vector3f scale) {
			scaleChanged = true;
		}
		public void visibleChanged(String name, boolean visible) {
			visibleChanged = true;
		}
	}
	private class NotExpectEventNodeListener implements IOgreNodeListener {
		public void orientationChanged(String name, Quat4f orientation) {
			Assert.fail();
		}
		public void positionChanged(String name, Vector3f position) {
			Assert.fail();
		}
		public void scaleChanged(String name, Vector3f scale) {
			Assert.fail();
		}
		public void visibleChanged(String name, boolean visible) {
			Assert.fail();
		}
	}

	private boolean redrawn = false;
	private class ExpectRedrawRenderLoop implements IRenderLoop {
		public void addFrameListener(IFrameListener listener) {
		}
		public void redraw() {
			redrawn = true;
		}
		public void removeFrameListener(IFrameListener listener) {
		}
		public void start() {
		}
		public void stop() {
		}
	}
	private class NotExpectRedrawRenderLoop implements IRenderLoop {
		public void addFrameListener(IFrameListener listener) {
		}
		public void redraw() {
			Assert.fail();
		}
		public void removeFrameListener(IFrameListener listener) {
		}
		public void start() {
		}
		public void stop() {
		}
	}

	@Test
	public void setAndGetNodePosition() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setAndGetNodePosition", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setAndGetNodePosition", null);
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodePosition("setAndGetNodePosition", pos);
		Assert.assertTrue(pos.equals(ogreCanvas.getNodePosition("setAndGetNodePosition")));
	}

	@Test(expected= RuntimeException.class)
	public void setNodePosition_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setGetNodePosition_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodePosition("invalid", pos);
	}

	@Test
	public void setNodePositionExpectEvent() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodePositionExpectEvent", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		ogreCanvas.addNodeListener(new ExpectEventNodeListener());
		ogreCanvas.createNode("setNodePositionExpectEvent", null);
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodePosition("setNodePositionExpectEvent", pos);
		Assert.assertTrue(positionChanged);
	}

	@Test
	public void setNodePositionExpectEventRemoveListener() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodePositionExpectEventRemoveListener", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		ExpectEventNodeListener listener = new ExpectEventNodeListener();
		ogreCanvas.addNodeListener(listener);
		ogreCanvas.createNode("setNodePositionExpectEventRemoveListener", null);
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodePosition("setNodePositionExpectEventRemoveListener", pos);
		Assert.assertTrue(positionChanged);
		positionChanged = false;
		ogreCanvas.removeNodeListener(listener);
		ogreCanvas.setNodePosition("setNodePositionExpectEventRemoveListener", new Vector3f(0, 0, 0));
		Assert.assertFalse(positionChanged);
	}

	@Test
	public void setNodePositionNotExpectEvent() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodePositionNotExpectEvent", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		ogreCanvas.addNodeListener(new NotExpectEventNodeListener());
		ogreCanvas.createNode("setNodePositionNotExpectEvent", null);
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodePosition("setNodePositionNotExpectEvent", pos);
		Assert.assertFalse(positionChanged);
	}

	@Test
	public void setNodePositionExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodePositionExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("setNodePositionExpectRedraw", null);
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodePosition("setNodePositionExpectRedraw", pos);
		Assert.assertTrue(redrawn);
	}

	@Test
	public void setNodePositionNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodePositionNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("setNodePositionNotExpectRedraw", null);
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodePosition("setNodePositionNotExpectRedraw", pos);
		Assert.assertFalse(redrawn);
	}

	@Test(expected= RuntimeException.class)
	public void getNodePosition_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getNodePosition_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getNodePosition("invalid");
	}

	@Test
	public void setAndGetNodeOrientation() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setAndGetNodeOrientation", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setAndGetNodeOrientation", null);
		Quat4f ori = new Quat4f(50, 50, 50, 50);
		ogreCanvas.setNodeOrientation("setAndGetNodeOrientation", ori);
		Assert.assertTrue(ori.equals(ogreCanvas.getNodeOrientation("setAndGetNodeOrientation")));
	}

	@Test(expected= RuntimeException.class)
	public void setNodeOrientation_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setGetNodeOrientation_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		Quat4f ori = new Quat4f(50, 50, 50, 50);
		ogreCanvas.setNodeOrientation("invalid", ori);
	}

	@Test
	public void setNodeOrientationExpectEvent() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodeOrientationExpectEvent", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		ogreCanvas.addNodeListener(new ExpectEventNodeListener());
		ogreCanvas.createNode("setNodeOrientationExpectEvent", null);
		Quat4f ori = new Quat4f(50, 50, 50, 50);
		ogreCanvas.setNodeOrientation("setNodeOrientationExpectEvent", ori);
		Assert.assertTrue(orientationChanged);
	}

	@Test
	public void setNodeOrientationExpectEventRemoveListener() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodeOrientationExpectEventRemoveListener", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		ExpectEventNodeListener listener = new ExpectEventNodeListener();
		ogreCanvas.addNodeListener(listener);
		ogreCanvas.createNode("setNodeOrientationExpectEventRemoveListener", null);
		Quat4f ori = new Quat4f(50, 50, 50, 50);
		ogreCanvas.setNodeOrientation("setNodeOrientationExpectEventRemoveListener", ori);
		Assert.assertTrue(orientationChanged);
		orientationChanged = false;
		ogreCanvas.removeNodeListener(listener);
		ogreCanvas.setNodeOrientation("setNodeOrientationExpectEventRemoveListener", new Quat4f(0, 0, 0, 0));
		Assert.assertFalse(orientationChanged);
	}

	@Test
	public void setNodeOrientationNotExpectEvent() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodeOrientationNotExpectEvent", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		ogreCanvas.addNodeListener(new NotExpectEventNodeListener());
		ogreCanvas.createNode("setNodeOrientationNotExpectEvent", null);
		Quat4f ori = new Quat4f(50, 50, 50, 50);
		ogreCanvas.setNodeOrientation("setNodeOrientationNotExpectEvent", ori);
		Assert.assertFalse(orientationChanged);
	}

	@Test
	public void setNodeOrientationExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodeOrientationExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("setNodeOrientationExpectRedraw", null);
		Quat4f ori = new Quat4f(50, 50, 50, 50);
		ogreCanvas.setNodeOrientation("setNodeOrientationExpectRedraw", ori);
		Assert.assertTrue(redrawn);
	}

	@Test
	public void setNodeOrientationNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodeOrientationNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("setNodeOrientationNotExpectRedraw", null);
		Quat4f ori = new Quat4f(50, 50, 50, 50);
		ogreCanvas.setNodeOrientation("setNodeOrientationNotExpectRedraw", ori);
		Assert.assertFalse(redrawn);
	}

	@Test(expected= RuntimeException.class)
	public void getNodeOrientation_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getNodeOrientation_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getNodeOrientation("invalid");
	}

	@Test
	public void setAndGetNodeScale() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setAndGetNodeScale", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setAndGetNodeScale", null);
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodeScale("setAndGetNodeScale", pos);
		Assert.assertTrue(pos.equals(ogreCanvas.getNodeScale("setAndGetNodeScale")));
	}

	@Test(expected= RuntimeException.class)
	public void setNodeScale_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setGetNodeScale_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodeScale("invalid", pos);
	}

	@Test
	public void setNodeScaleExpectEvent() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodeScaleExpectEvent", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		ogreCanvas.addNodeListener(new ExpectEventNodeListener());
		ogreCanvas.createNode("setNodeScaleExpectEvent", null);
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodeScale("setNodeScaleExpectEvent", pos);
		Assert.assertTrue(scaleChanged);
	}

	@Test
	public void setNodeScaleExpectEventRemoveListener() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodeScaleExpectEventRemoveListener", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		ExpectEventNodeListener listener = new ExpectEventNodeListener();
		ogreCanvas.addNodeListener(listener);
		ogreCanvas.createNode("setNodeScaleExpectEventRemoveListener", null);
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodeScale("setNodeScaleExpectEventRemoveListener", pos);
		Assert.assertTrue(scaleChanged);
		scaleChanged = false;
		ogreCanvas.removeNodeListener(listener);
		ogreCanvas.setNodeScale("setNodeScaleExpectEventRemoveListener", new Vector3f(0, 0, 0));
		Assert.assertFalse(scaleChanged);
	}

	@Test
	public void setNodeScaleNotExpectEvent() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodeScaleNotExpectEvent", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		ogreCanvas.addNodeListener(new NotExpectEventNodeListener());
		ogreCanvas.createNode("setNodeScaleNotExpectEvent", null);
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodeScale("setNodeScaleNotExpectEvent", pos);
		Assert.assertFalse(scaleChanged);
	}

	@Test
	public void setNodeScaleExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodeScaleExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("setNodeScaleExpectRedraw", null);
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodeScale("setNodeScaleExpectRedraw", pos);
		Assert.assertTrue(redrawn);
	}

	@Test
	public void setNodeScaleNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setNodeScaleNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("setNodeScaleNotExpectRedraw", null);
		Vector3f pos = new Vector3f(50, 50, 50);
		ogreCanvas.setNodeScale("setNodeScaleNotExpectRedraw", pos);
		Assert.assertFalse(redrawn);
	}

	@Test(expected= RuntimeException.class)
	public void getNodeScale_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getNodeScale_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getNodeScale("invalid");
	}

	@Test
	public void createEntity() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createEntity", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		setUpOgreResources();
		ogreCanvas.createNode("createEntity", null);
		ogreCanvas.createEntity("createEntity", MESH_NAME);
		Assert.assertTrue(ogreCanvas.hasEntity("createEntity"));
	}

	@Test(expected= RuntimeException.class)
	public void createEntity_NameExists() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createEntity_NameExists", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		setUpOgreResources();
		ogreCanvas.createNode("createEntity_NameExists", null);
		ogreCanvas.createEntity("createEntity_NameExists", MESH_NAME);
		ogreCanvas.createEntity("createEntity_NameExists", MESH_NAME);
		Assert.assertFalse(ogreCanvas.hasEntity("createEntity_NameExists"));
	}

	@Test(expected= RuntimeException.class)
	public void createEntity_NameDoesNotExist() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createEntity_NameDoesNotExist", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		setUpOgreResources();
		ogreCanvas.createEntity("createEntity_NameDoesNotExist", MESH_NAME);
		Assert.assertFalse(ogreCanvas.hasEntity("createEntity_NameDoesNotExist"));
	}

	@Test
	public void createEntityExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createEntityExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		setUpOgreResources();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("createEntityExpectRedraw", null);
		ogreCanvas.createEntity("createEntityExpectRedraw", MESH_NAME);
		Assert.assertTrue(redrawn);
	}

	@Test
	public void createEntityNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createEntityNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("createEntityNotExpectRedraw", null);
		ogreCanvas.createEntity("createEntityNotExpectRedraw", MESH_NAME);
		Assert.assertFalse(redrawn);
	}

	@Test
	public void removeEntity() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "removeEntity", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		setUpOgreResources();
		ogreCanvas.createNode("removeEntity", null);
		ogreCanvas.createEntity("removeEntity", MESH_NAME);
		Assert.assertTrue(ogreCanvas.hasEntity("removeEntity"));
		ogreCanvas.removeEntity("removeEntity");
		Assert.assertFalse(ogreCanvas.hasEntity("removeEntity"));
		Assert.assertTrue(ogreCanvas.hasNode("removeEntity"));
	}

	@Test
	public void removeEntity_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "removeEntity_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		setUpOgreResources();
		ogreCanvas.createNode("removeEntity_InvalidName", null);
		ogreCanvas.removeEntity("removeEntity_InvalidName");
		ogreCanvas.removeEntity("invalid");
		Assert.assertTrue(ogreCanvas.hasNode("removeEntity_InvalidName"));
	}

	@Test
	public void removeEntityWithNode() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "removeEntityWithNode", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		setUpOgreResources();
		ogreCanvas.createNode("removeEntityWithNode", null);
		ogreCanvas.createEntity("removeEntityWithNode", MESH_NAME);
		Assert.assertTrue(ogreCanvas.hasEntity("removeEntityWithNode"));
		ogreCanvas.removeNodeWithChildrenAndObjects("removeEntityWithNode");
		Assert.assertFalse(ogreCanvas.hasEntity("removeEntityWithNode"));
		Assert.assertFalse(ogreCanvas.hasNode("removeEntityWithNode"));
	}

	@Test
	public void getEntityMesh() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getEntityMesh", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		setUpOgreResources();
		ogreCanvas.createNode("getEntityMesh", null);
		ogreCanvas.createEntity("getEntityMesh", MESH_NAME);
		Assert.assertTrue(MESH_NAME.equalsIgnoreCase(ogreCanvas.getEntityMesh("getEntityMesh")));
	}

	@Test(expected= RuntimeException.class)
	public void getEntityMesh_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getEntityMesh_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getEntityMesh("invalid");
	}

	@Test
	public void getAndSetEntityQueryFlags() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getAndSetEntityQueryFlags", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		setUpOgreResources();
		ogreCanvas.createNode("getAndSetEntityQueryFlags", null);
		ogreCanvas.createEntity("getAndSetEntityQueryFlags", MESH_NAME);
		long flags = 42;
		ogreCanvas.setEntityQueryFlags("getAndSetEntityQueryFlags", flags);
		Assert.assertEquals(flags, ogreCanvas.getEntityQueryFlags("getAndSetEntityQueryFlags"));
	}

	@Test(expected= RuntimeException.class)
	public void getEntityQueryFlags_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getEntityQueryFlags_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getEntityQueryFlags("invalid");
	}

	@Test(expected= RuntimeException.class)
	public void setEntityQueryFlags_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setEntityQueryFlags_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setEntityQueryFlags("invalid", 42);
	}

	@Test
	public void createLight() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createLight", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("createLight", null);
		ogreCanvas.createLight("createLight");
		Assert.assertTrue(ogreCanvas.hasLight("createLight"));
	}

	@Test(expected= RuntimeException.class)
	public void createLight_NameExists() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createLight_NameExists", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("createLight_NameExists", null);
		ogreCanvas.createLight("createLight_NameExists");
		ogreCanvas.createLight("createLight_NameExists");
		Assert.assertFalse(ogreCanvas.hasLight("createLight_NameExists"));
	}

	@Test(expected= RuntimeException.class)
	public void createLight_NameDoesNotExist() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createLight_NameDoesNotExist", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createLight("createLight_NameDoesNotExist");
		Assert.assertFalse(ogreCanvas.hasLight("createLight_NameDoesNotExist"));
	}

	@Test
	public void createLightExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createLightExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("createLightExpectRedraw", null);
		ogreCanvas.createLight("createLightExpectRedraw");
		Assert.assertTrue(redrawn);
	}

	@Test
	public void createLightNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createLightNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("createLightNotExpectRedraw", null);
		ogreCanvas.createLight("createLightNotExpectRedraw");
		Assert.assertFalse(redrawn);
	}

	@Test
	public void removeLight() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "removeLight", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("removeLight", null);
		ogreCanvas.createLight("removeLight");
		Assert.assertTrue(ogreCanvas.hasLight("removeLight"));
		ogreCanvas.removeLight("removeLight");
		Assert.assertFalse(ogreCanvas.hasLight("removeLight"));
		Assert.assertTrue(ogreCanvas.hasNode("removeLight"));
	}

	@Test
	public void removeLight_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "removeLight_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("removeLight_InvalidName", null);
		ogreCanvas.removeLight("removeLight_InvalidName");
		ogreCanvas.removeLight("invalid");
		Assert.assertTrue(ogreCanvas.hasNode("removeLight_InvalidName"));
	}

	@Test
	public void removeLightWithNode() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "removeLightWithNode", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("removeLightWithNode", null);
		ogreCanvas.createLight("removeLightWithNode");
		Assert.assertTrue(ogreCanvas.hasLight("removeLightWithNode"));
		ogreCanvas.removeNodeWithChildrenAndObjects("removeLightWithNode");
		Assert.assertFalse(ogreCanvas.hasLight("removeLightWithNode"));
		Assert.assertFalse(ogreCanvas.hasNode("removeLightWithNode"));
	}

	@Test
	public void getAndSetLightAttenuation() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getAndSetLightAttenuation", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("getAndSetLightAttenuation", null);
		ogreCanvas.createLight("getAndSetLightAttenuation");
		float range = 10;
		float constant = 20;
		float linear = 30;
		float quadratic = 40;
		ogreCanvas.setLightAttenuation("getAndSetLightAttenuation", range, constant, linear, quadratic);
		Assert.assertEquals(range, ogreCanvas.getLightAttenuationRange("getAndSetLightAttenuation"));
		Assert.assertEquals(constant, ogreCanvas.getLightAttenuationConstant("getAndSetLightAttenuation"));
		Assert.assertEquals(linear, ogreCanvas.getLightAttenuationLinear("getAndSetLightAttenuation"));
		Assert.assertEquals(quadratic, ogreCanvas.getLightAttenuationQuadric("getAndSetLightAttenuation"));
	}

	@Test(expected= RuntimeException.class)
	public void setLightAttenuation_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightAttenuation_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		float range = 10;
		float constant = 20;
		float linear = 30;
		float quadratic = 40;
		ogreCanvas.setLightAttenuation("invalid", range, constant, linear, quadratic);
	}

	@Test(expected= RuntimeException.class)
	public void getLightAttenuationRange_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getLightAttenuationRange_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getLightAttenuationRange("invalid");
	}

	@Test(expected= RuntimeException.class)
	public void getLightAttenuationConstant_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getLightAttenuationConstant_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getLightAttenuationConstant("invalid");
	}

	@Test(expected= RuntimeException.class)
	public void getLightAttenuationLinear_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getLightAttenuationLinear_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getLightAttenuationLinear("invalid");
	}

	@Test(expected= RuntimeException.class)
	public void getLightAttenuationQuadric_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getLightAttenuationQuadric_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getLightAttenuationQuadric("invalid");
	}

	@Test
	public void setLightAttenuationExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightAttenuationExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("setLightAttenuationExpectRedraw", null);
		ogreCanvas.createLight("setLightAttenuationExpectRedraw");
		float range = 10;
		float constant = 20;
		float linear = 30;
		float quadratic = 40;
		ogreCanvas.setLightAttenuation("setLightAttenuationExpectRedraw", range, constant, linear, quadratic);
		Assert.assertTrue(redrawn);
	}

	@Test
	public void setLightAttenuationNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightAttenuationNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("setLightAttenuationNotExpectRedraw", null);
		ogreCanvas.createLight("setLightAttenuationNotExpectRedraw");
		float range = 10;
		float constant = 20;
		float linear = 30;
		float quadratic = 40;
		ogreCanvas.setLightAttenuation("setLightAttenuationNotExpectRedraw", range, constant, linear, quadratic);
		Assert.assertFalse(redrawn);
	}

	@Test
	public void getAndSetLightDiffuseColour() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getAndSetLightDiffuseColour", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("getAndSetLightDiffuseColour", null);
		ogreCanvas.createLight("getAndSetLightDiffuseColour");
		Color4f colour = new Color4f(0.4f,0.3f,0.2f,1.0f);
		ogreCanvas.setLightDiffuseColour("getAndSetLightDiffuseColour", colour);
		Assert.assertTrue(colour.equals(ogreCanvas.getLightDiffuseColour("getAndSetLightDiffuseColour")));
	}

	@Test(expected= RuntimeException.class)
	public void setLightDiffuseColour_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightDiffuseColour_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		Color4f colour = new Color4f(1,0,1,0);
		ogreCanvas.setLightDiffuseColour("invalid", colour);
	}

	@Test(expected= RuntimeException.class)
	public void getLightDiffuseColour_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getLightDiffuseColour_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getLightDiffuseColour("invalid");
	}

	@Test
	public void setLightDiffuseColourExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightDiffuseColourExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("setLightDiffuseColourExpectRedraw", null);
		ogreCanvas.createLight("setLightDiffuseColourExpectRedraw");
		Color4f colour = new Color4f(1,0,1,0);
		ogreCanvas.setLightDiffuseColour("setLightDiffuseColourExpectRedraw", colour);
		Assert.assertTrue(redrawn);
	}

	@Test
	public void setLightDiffuseColourNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightDiffuseColourNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("setLightDiffuseColourNotExpectRedraw", null);
		ogreCanvas.createLight("setLightDiffuseColourNotExpectRedraw");
		Color4f colour = new Color4f(1,0,1,0);
		ogreCanvas.setLightDiffuseColour("setLightDiffuseColourNotExpectRedraw", colour);
		Assert.assertFalse(redrawn);
	}

	@Test
	public void getAndSetLightDirection() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getAndSetLightDirection", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("getAndSetLightDirection", null);
		ogreCanvas.createLight("getAndSetLightDirection");
		Vector3f dir = new Vector3f(10, 20, 30);
		ogreCanvas.setLightDirection("getAndSetLightDirection", dir);
		Assert.assertTrue(dir.equals(ogreCanvas.getLightDirection("getAndSetLightDirection")));
	}

	@Test(expected= RuntimeException.class)
	public void setLightDirection_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightDirection_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		Vector3f dir = new Vector3f(10, 20, 30);
		ogreCanvas.setLightDirection("invalid", dir);
	}

	@Test(expected= RuntimeException.class)
	public void getLightDirection_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getLightDirection_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getLightDirection("invalid");
	}

	@Test
	public void setLightDirectionExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightDirectionExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("setLightDirectionExpectRedraw", null);
		ogreCanvas.createLight("setLightDirectionExpectRedraw");
		Vector3f dir = new Vector3f(10, 20, 30);
		ogreCanvas.setLightDirection("setLightDirectionExpectRedraw", dir);
		Assert.assertTrue(redrawn);
	}

	@Test
	public void setLightDirectionNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightDirectionNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("setLightDirectionNotExpectRedraw", null);
		ogreCanvas.createLight("setLightDirectionNotExpectRedraw");
		Vector3f dir = new Vector3f(10, 20, 30);
		ogreCanvas.setLightDirection("setLightDirectionNotExpectRedraw", dir);
		Assert.assertFalse(redrawn);
	}

	@Test
	public void getAndSetLightSpecularColour() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getAndSetLightSpecularColour", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("getAndSetLightSpecularColour", null);
		ogreCanvas.createLight("getAndSetLightSpecularColour");
		Color4f colour = new Color4f(0.4f,0.3f,0.2f,1.0f);
		ogreCanvas.setLightSpecularColour("getAndSetLightSpecularColour", colour);
		Assert.assertTrue(colour.equals(ogreCanvas.getLightSpecularColour("getAndSetLightSpecularColour")));
	}

	@Test(expected= RuntimeException.class)
	public void setLightSpecularColour_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightSpecularColour_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		Color4f colour = new Color4f(1,0,1,0);
		ogreCanvas.setLightSpecularColour("invalid", colour);
	}

	@Test(expected= RuntimeException.class)
	public void getLightSpecularColour_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getLightSpecularColour_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getLightSpecularColour("invalid");
	}

	@Test
	public void setLightSpecularColourExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightSpecularColourExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("setLightSpecularColourExpectRedraw", null);
		ogreCanvas.createLight("setLightSpecularColourExpectRedraw");
		Color4f colour = new Color4f(1,0,1,0);
		ogreCanvas.setLightSpecularColour("setLightSpecularColourExpectRedraw", colour);
		Assert.assertTrue(redrawn);
	}

	@Test
	public void setLightSpecularColourNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightSpecularColourNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("setLightSpecularColourNotExpectRedraw", null);
		ogreCanvas.createLight("setLightSpecularColourNotExpectRedraw");
		Color4f colour = new Color4f(1,0,1,0);
		ogreCanvas.setLightSpecularColour("setLightSpecularColourNotExpectRedraw", colour);
		Assert.assertFalse(redrawn);
	}

	@Test
	public void getAndSetLightQueryFlags() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getAndSetLightQueryFlags", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("getAndSetLightQueryFlags", null);
		ogreCanvas.createLight("getAndSetLightQueryFlags");
		long flags = 42;
		ogreCanvas.setLightQueryFlags("getAndSetLightQueryFlags", flags);
		Assert.assertEquals(flags, ogreCanvas.getLightQueryFlags("getAndSetLightQueryFlags"));
	}

	@Test(expected= RuntimeException.class)
	public void getLightQueryFlags_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getLightQueryFlags_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getLightQueryFlags("invalid");
	}

	@Test(expected= RuntimeException.class)
	public void setLightQueryFlags_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightQueryFlags_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setLightQueryFlags("invalid", 42);
	}

	@Test
	public void getAndSetLightType() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getAndSetLightType", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("getAndSetLightType", null);
		ogreCanvas.createLight("getAndSetLightType");
		LightType type = LightType.POINT;
		ogreCanvas.setLightType("getAndSetLightType", type);
		Assert.assertTrue(type.equals(ogreCanvas.getLightType("getAndSetLightType")));
	}

	@Test(expected= RuntimeException.class)
	public void setLightType_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightType_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		LightType type = LightType.POINT;
		ogreCanvas.setLightType("invalid", type);
	}

	@Test(expected= RuntimeException.class)
	public void getLightType_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getLightType_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getLightType("invalid");
	}

	@Test
	public void setLightTypeExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightTypeExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("setLightTypeExpectRedraw", null);
		ogreCanvas.createLight("setLightTypeExpectRedraw");
		LightType type = LightType.POINT;
		ogreCanvas.setLightType("setLightTypeExpectRedraw", type);
		Assert.assertTrue(redrawn);
	}

	@Test
	public void setLightTypeNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setLightTypeNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("setLightTypeNotExpectRedraw", null);
		ogreCanvas.createLight("setLightTypeNotExpectRedraw");
		LightType type = LightType.POINT;
		ogreCanvas.setLightType("setLightTypeNotExpectRedraw", type);
		Assert.assertFalse(redrawn);
	}

	@Test
	public void createCamera() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createCamera", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("createCamera", null);
		ogreCanvas.createCamera("createCamera");
		Assert.assertTrue(ogreCanvas.hasCamera("createCamera"));
	}

	@Test(expected= RuntimeException.class)
	public void createCamera_NameExists() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createCamera_NameExists", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("createCamera_NameExists", null);
		ogreCanvas.createCamera("createCamera_NameExists");
		ogreCanvas.createCamera("createCamera_NameExists");
		Assert.assertFalse(ogreCanvas.hasCamera("createCamera_NameExists"));
	}

	@Test(expected= RuntimeException.class)
	public void createCamera_NameDoesNotExist() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createCamera_NameDoesNotExist", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createCamera("createCamera_NameDoesNotExist");
		Assert.assertFalse(ogreCanvas.hasCamera("createCamera_NameDoesNotExist"));
	}

	@Test
	public void createCameraExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createCameraExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("createCameraExpectRedraw", null);
		ogreCanvas.createCamera("createCameraExpectRedraw");
		Assert.assertFalse(redrawn); // it should NOT be redrawn
	}

	@Test
	public void createCameraNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "createCameraNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("createCameraNotExpectRedraw", null);
		ogreCanvas.createCamera("createCameraNotExpectRedraw");
		Assert.assertFalse(redrawn);
	}

	@Test
	public void removeCamera() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "removeCamera", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("removeCamera", null);
		ogreCanvas.createCamera("removeCamera");
		Assert.assertTrue(ogreCanvas.hasCamera("removeCamera"));
		ogreCanvas.removeCamera("removeCamera");
		Assert.assertFalse(ogreCanvas.hasCamera("removeCamera"));
		Assert.assertTrue(ogreCanvas.hasNode("removeCamera"));
	}

	@Test
	public void removeCamera_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "removeCamera_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("removeCamera_InvalidName", null);
		ogreCanvas.removeCamera("removeCamera_InvalidName");
		ogreCanvas.removeCamera("invalid");
		Assert.assertTrue(ogreCanvas.hasNode("removeCamera_InvalidName"));
	}

	@Test
	public void removeCameraWithNode() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "removeCameraWithNode", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("removeCameraWithNode", null);
		ogreCanvas.createCamera("removeCameraWithNode");
		Assert.assertTrue(ogreCanvas.hasCamera("removeCameraWithNode"));
		ogreCanvas.removeNodeWithChildrenAndObjects("removeCameraWithNode");
		Assert.assertFalse(ogreCanvas.hasCamera("removeCameraWithNode"));
		Assert.assertFalse(ogreCanvas.hasNode("removeCameraWithNode"));
	}

	@Test
	public void setAndGetCameraBackgroundColour() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setAndGetCameraBackgroundColour", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setAndGetCameraBackgroundColour", null);
		ogreCanvas.createCamera("setAndGetCameraBackgroundColour");
		Color4f col = new Color4f(1,0,0,0);
		ogreCanvas.setCamBackgroundColour("setAndGetCameraBackgroundColour", col);
		Assert.assertTrue(col.equals(ogreCanvas.getCamBackgroundColour("setAndGetCameraBackgroundColour")));
	}

	@Test(expected= RuntimeException.class)
	public void setCameraBackgroundColour_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setCameraBackgroundColour_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		Color4f colour = new Color4f(1,0,1,0);
		ogreCanvas.setCamBackgroundColour("invalid", colour);
	}

	@Test
	public void setCameraBackgroundColourNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setCameraBackgroundColourNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("setCameraBackgroundColourNotExpectRedraw", null);
		ogreCanvas.createCamera("setCameraBackgroundColourNotExpectRedraw");
		Color4f colour = new Color4f(1,0,1,0);
		ogreCanvas.setCamBackgroundColour("setCameraBackgroundColourNotExpectRedraw", colour);
		Assert.assertFalse(redrawn);
	}

	@Test(expected= RuntimeException.class)
	public void getCameraBackgroundColour_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getCameraBackgroundColour_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getCamBackgroundColour("invalid");
	}

	@Test
	public void setAndGetCameraQueryFlags() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setAndGetCameraQueryFlags", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setAndGetCameraQueryFlags", null);
		ogreCanvas.createCamera("setAndGetCameraQueryFlags");
		long flags = 42;
		ogreCanvas.setCameraQueryFlags("setAndGetCameraQueryFlags", flags);
		Assert.assertEquals(flags, ogreCanvas.getCameraQueryFlags("setAndGetCameraQueryFlags"));
	}

	@Test(expected= RuntimeException.class)
	public void setCameraQueryFlags_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setCameraQueryFlags_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		long flags = 42;
		ogreCanvas.setCameraQueryFlags("invalid", flags);
	}

	@Test
	public void setCameraQueryFlagsNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setCameraQueryFlagsNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("setCameraQueryFlagsNotExpectRedraw", null);
		ogreCanvas.createCamera("setCameraQueryFlagsNotExpectRedraw");
		long flags = 42;
		ogreCanvas.setCameraQueryFlags("setCameraQueryFlagsNotExpectRedraw", flags);
		Assert.assertFalse(redrawn);
	}

	@Test(expected= RuntimeException.class)
	public void getCameraQueryFlags_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getCameraQueryFlags_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getCameraQueryFlags("invalid");
	}

	@Test
	public void setAndGetCameraFarClipDistance() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setAndGetCameraFarClipDistance", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setAndGetCameraFarClipDistance", null);
		ogreCanvas.createCamera("setAndGetCameraFarClipDistance");
		float far = 4629;
		ogreCanvas.setCamFarClipDistance("setAndGetCameraFarClipDistance", far);
		Assert.assertEquals(far, ogreCanvas.getCamFarClipDistance("setAndGetCameraFarClipDistance"));
	}

	@Test(expected= RuntimeException.class)
	public void setCameraFarClipDistance_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setCameraFarClipDistance_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		float far = 4629;
		ogreCanvas.setCamFarClipDistance("invalid", far);
	}

	@Test
	public void setCameraFarClipDistanceNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setCameraFarClipDistanceNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("setCameraFarClipDistanceNotExpectRedraw", null);
		ogreCanvas.createCamera("setCameraFarClipDistanceNotExpectRedraw");
		float far = 4629;
		ogreCanvas.setCamFarClipDistance("setCameraFarClipDistanceNotExpectRedraw", far);
		Assert.assertFalse(redrawn);
	}

	@Test(expected= RuntimeException.class)
	public void getCameraFarClipDistance_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getCameraFarClipDistance_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getCamFarClipDistance("invalid");
	}

	@Test
	public void setAndGetCameraNearClipDistance() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setAndGetCameraNearClipDistance", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setAndGetCameraNearClipDistance", null);
		ogreCanvas.createCamera("setAndGetCameraNearClipDistance");
		float near = 4.629f;
		ogreCanvas.setCamNearClipDistance("setAndGetCameraNearClipDistance", near);
		Assert.assertEquals(near, ogreCanvas.getCamNearClipDistance("setAndGetCameraNearClipDistance"));
	}

	@Test(expected= RuntimeException.class)
	public void setCameraNearClipDistance_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setCameraNearClipDistance_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		float near = 4.629f;
		ogreCanvas.setCamNearClipDistance("invalid", near);
	}

	@Test
	public void setCameraNearClipDistanceNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setCameraNearClipDistanceNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("setCameraNearClipDistanceNotExpectRedraw", null);
		ogreCanvas.createCamera("setCameraNearClipDistanceNotExpectRedraw");
		float near = 4.629f;
		ogreCanvas.setCamNearClipDistance("setCameraNearClipDistanceNotExpectRedraw", near);
		Assert.assertFalse(redrawn);
	}

	@Test(expected= RuntimeException.class)
	public void getCameraNearClipDistance_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getCameraNearClipDistance_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getCamNearClipDistance("invalid");
	}

	@Test
	public void setAndGetCameraLookAt() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setAndGetCameraLookAt", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setAndGetCameraLookAt", null);
		ogreCanvas.createCamera("setAndGetCameraLookAt");
		Vector3f dir = new Vector3f(1,0,0);
		ogreCanvas.setCamLookAt("setAndGetCameraLookAt", dir);
		Assert.assertTrue(dir.equals(ogreCanvas.getCamLookAt("setAndGetCameraLookAt")));
	}

	@Test(expected= RuntimeException.class)
	public void setCameraLookAt_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setCameraLookAt_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		Vector3f dir = new Vector3f(1,0,0);
		ogreCanvas.setCamLookAt("invalid", dir);
	}

	@Test
	public void setCameraLookAtNotExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setCameraLookAtNotExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(true);
		OgreSystem.getDefault().setRenderLoop(new NotExpectRedrawRenderLoop());
		ogreCanvas.createNode("setCameraLookAtNotExpectRedraw", null);
		ogreCanvas.createCamera("setCameraLookAtNotExpectRedraw");
		Vector3f dir = new Vector3f(1,0,0);
		ogreCanvas.setCamLookAt("setCameraLookAtNotExpectRedraw", dir);
		Assert.assertFalse(redrawn);
	}

	@Test(expected= RuntimeException.class)
	public void getCameraLookAt_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getCameraLookAt_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.getCamLookAt("invalid");
	}

	@Test
	public void setActiveCamera() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setActiveCamera", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setActiveCamera", null);
		ogreCanvas.createCamera("setActiveCamera");
		ogreCanvas.setActiveCamera("setActiveCamera");
		Assert.assertEquals("setActiveCamera", ogreCanvas.getActiveCamera());
	}

	@Test(expected= RuntimeException.class)
	public void setActiveCamera_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setActiveCamera_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setActiveCamera("invalid");
	}

	@Test
	public void deactiveCamera() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "deactiveCamera", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("deactiveCamera", null);
		ogreCanvas.createCamera("deactiveCamera");
		ogreCanvas.setActiveCamera("deactiveCamera");
		ogreCanvas.deactivateCamera();
		Assert.assertNull(ogreCanvas.getActiveCamera());
	}

	@Test
	public void deactiveCamera_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "deactiveCamera_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.deactivateCamera();
		Assert.assertNull(ogreCanvas.getActiveCamera());
	}

	@Test
	public void setAndGetActiveCameraBackgroundColour() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setAndGetActiveCameraBackgroundColour", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setAndGetActiveCameraBackgroundColour", null);
		ogreCanvas.createCamera("setAndGetActiveCameraBackgroundColour");
		Color4f col = new Color4f(1,0,0,0);
		ogreCanvas.setCamBackgroundColour("setAndGetActiveCameraBackgroundColour", col);
		ogreCanvas.setActiveCamera("setAndGetActiveCameraBackgroundColour");
		Assert.assertTrue(col.equals(ogreCanvas.getActiveCamBackgroundColour()));
		Color4f col2 = new Color4f(0,0,1,0);
		ogreCanvas.setCamBackgroundColour("setAndGetActiveCameraBackgroundColour", col2);
		Assert.assertTrue(col2.equals(ogreCanvas.getActiveCamBackgroundColour()));
		Assert.assertTrue(col2.equals(ogreCanvas.getCamBackgroundColour("setAndGetActiveCameraBackgroundColour")));
		ogreCanvas.deactivateCamera();
		Assert.assertNull(ogreCanvas.getActiveCamBackgroundColour());
	}

	@Test
	public void setActiveCameraBackgroundColourExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setActiveCameraBackgroundColourExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("setActiveCameraBackgroundColourExpectRedraw", null);
		ogreCanvas.createCamera("setActiveCameraBackgroundColourExpectRedraw");
		ogreCanvas.setActiveCamera("setActiveCameraBackgroundColourExpectRedraw");
		Color4f colour = new Color4f(1,0,1,0);
		ogreCanvas.setCamBackgroundColour("setActiveCameraBackgroundColourExpectRedraw", colour);
		Assert.assertTrue(redrawn);
	}

	@Test
	public void getActiveCameraBackgroundColour_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getActiveCameraBackgroundColour_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		Assert.assertNull(ogreCanvas.getActiveCamBackgroundColour());
	}

	@Test
	public void setAndGetActiveCameraQueryFlags() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setAndGetActiveCameraQueryFlags", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setAndGetActiveCameraQueryFlags", null);
		ogreCanvas.createCamera("setAndGetActiveCameraQueryFlags");
		long flags = 42;
		ogreCanvas.setCameraQueryFlags("setAndGetActiveCameraQueryFlags", flags);
		ogreCanvas.setActiveCamera("setAndGetActiveCameraQueryFlags");
		Assert.assertEquals(flags, ogreCanvas.getActiveCameraQueryFlags());
		long flags2 = 5;
		ogreCanvas.setCameraQueryFlags("setAndGetActiveCameraQueryFlags", flags2);
		Assert.assertEquals(flags2, ogreCanvas.getActiveCameraQueryFlags());
		Assert.assertEquals(flags2, ogreCanvas.getCameraQueryFlags("setAndGetActiveCameraQueryFlags"));
		ogreCanvas.deactivateCamera();
		Assert.assertEquals(0L, ogreCanvas.getActiveCameraQueryFlags());
	}

	@Test
	public void getActiveCameraQueryFlags_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getActiveCameraQueryFlags_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		Assert.assertEquals(0L, ogreCanvas.getActiveCameraQueryFlags());
	}

	@Test
	public void setAndGetActiveCameraFarClipDistance() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setAndGetActiveCameraFarClipDistance", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setAndGetActiveCameraFarClipDistance", null);
		ogreCanvas.createCamera("setAndGetActiveCameraFarClipDistance");
		float dist = 4321;
		ogreCanvas.setCamFarClipDistance("setAndGetActiveCameraFarClipDistance", dist);
		ogreCanvas.setActiveCamera("setAndGetActiveCameraFarClipDistance");
		Assert.assertEquals(dist, ogreCanvas.getActiveCamFarClipDistance());
		float dist2 = 4567;
		ogreCanvas.setCamFarClipDistance("setAndGetActiveCameraFarClipDistance", dist2);
		Assert.assertEquals(dist2, ogreCanvas.getActiveCamFarClipDistance());
		Assert.assertEquals(dist2, ogreCanvas.getCamFarClipDistance("setAndGetActiveCameraFarClipDistance"));
		ogreCanvas.deactivateCamera();
		Assert.assertEquals(0.0f, ogreCanvas.getActiveCamFarClipDistance());
	}

	@Test
	public void setActiveCameraFarClipDistanceExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setActiveCameraFarClipDistanceExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("setActiveCameraFarClipDistanceExpectRedraw", null);
		ogreCanvas.createCamera("setActiveCameraFarClipDistanceExpectRedraw");
		ogreCanvas.setActiveCamera("setActiveCameraFarClipDistanceExpectRedraw");
		float dist = 4321;
		ogreCanvas.setCamFarClipDistance("setActiveCameraFarClipDistanceExpectRedraw", dist);
		Assert.assertTrue(redrawn);
	}

	@Test
	public void getActiveCameraFarClipDistance_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getActiveCameraFarClipDistance_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		Assert.assertEquals(0.0f, ogreCanvas.getActiveCamFarClipDistance());
	}

	@Test
	public void setAndGetActiveCameraNearClipDistance() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setAndGetActiveCameraNearClipDistance", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setAndGetActiveCameraNearClipDistance", null);
		ogreCanvas.createCamera("setAndGetActiveCameraNearClipDistance");
		float dist = 4.321f;
		ogreCanvas.setCamNearClipDistance("setAndGetActiveCameraNearClipDistance", dist);
		ogreCanvas.setActiveCamera("setAndGetActiveCameraNearClipDistance");
		Assert.assertEquals(dist, ogreCanvas.getActiveCamNearClipDistance());
		float dist2 = 4.567f;
		ogreCanvas.setCamNearClipDistance("setAndGetActiveCameraNearClipDistance", dist2);
		Assert.assertEquals(dist2, ogreCanvas.getActiveCamNearClipDistance());
		Assert.assertEquals(dist2, ogreCanvas.getCamNearClipDistance("setAndGetActiveCameraNearClipDistance"));
		ogreCanvas.deactivateCamera();
		Assert.assertEquals(0.0f, ogreCanvas.getActiveCamNearClipDistance());
	}

	@Test
	public void setActiveCameraNearClipDistanceExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setActiveCameraNearClipDistanceExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("setActiveCameraNearClipDistanceExpectRedraw", null);
		ogreCanvas.createCamera("setActiveCameraNearClipDistanceExpectRedraw");
		ogreCanvas.setActiveCamera("setActiveCameraNearClipDistanceExpectRedraw");
		float dist = 4.321f;
		ogreCanvas.setCamNearClipDistance("setActiveCameraNearClipDistanceExpectRedraw", dist);
		Assert.assertTrue(redrawn);
	}

	@Test
	public void getActiveCameraNearClipDistance_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getActiveCameraNearClipDistance_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		Assert.assertEquals(0.0f, ogreCanvas.getActiveCamNearClipDistance());
	}

	@Test
	public void setAndGetActiveCameraLookAt() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setAndGetActiveCameraLookAt", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.createNode("setAndGetActiveCameraLookAt", null);
		ogreCanvas.createCamera("setAndGetActiveCameraLookAt");
		Vector3f dir = new Vector3f(1,0,0);
		ogreCanvas.setCamLookAt("setAndGetActiveCameraLookAt", dir);
		ogreCanvas.setActiveCamera("setAndGetActiveCameraLookAt");
		Assert.assertEquals(dir.x, (float)Math.round(ogreCanvas.getActiveCamLookAt().x));
		Assert.assertEquals(dir.y, (float)Math.round(ogreCanvas.getActiveCamLookAt().y));
		Assert.assertEquals(dir.z, (float)Math.round(ogreCanvas.getActiveCamLookAt().z));
		Vector3f dir2 = new Vector3f(0,0,1);
		ogreCanvas.setCamLookAt("setAndGetActiveCameraLookAt", dir2);
		Assert.assertEquals(dir2.x, (float)Math.round(ogreCanvas.getActiveCamLookAt().x));
		Assert.assertEquals(dir2.y, (float)Math.round(ogreCanvas.getActiveCamLookAt().y));
		Assert.assertEquals(dir2.z, (float)Math.round(ogreCanvas.getActiveCamLookAt().z));
		Assert.assertEquals(dir2.x, (float)Math.round(ogreCanvas.getCamLookAt("setAndGetActiveCameraLookAt").x));
		Assert.assertEquals(dir2.y, (float)Math.round(ogreCanvas.getCamLookAt("setAndGetActiveCameraLookAt").y));
		Assert.assertEquals(dir2.z, (float)Math.round(ogreCanvas.getCamLookAt("setAndGetActiveCameraLookAt").z));
		ogreCanvas.deactivateCamera();
		Assert.assertNull(ogreCanvas.getActiveCamLookAt());
	}

	@Test
	public void setActiveCameraLookAtExpectRedraw() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "setActiveCameraLookAtExpectRedraw", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		ogreCanvas.setSceneCreation(false);
		OgreSystem.getDefault().setRenderLoop(new ExpectRedrawRenderLoop());
		ogreCanvas.createNode("setActiveCameraLookAtExpectRedraw", null);
		ogreCanvas.createCamera("setActiveCameraLookAtExpectRedraw");
		ogreCanvas.setActiveCamera("setActiveCameraLookAtExpectRedraw");
		Vector3f dir = new Vector3f(1,0,0);
		ogreCanvas.setCamLookAt("setActiveCameraLookAtExpectRedraw", dir);
		Assert.assertTrue(redrawn);
	}

	@Test
	public void getActiveCameraLookAt_InvalidName() throws Exception {
		ogreCanvas = new OgreCanvas(shell, "getActiveCameraLookAt_InvalidName", SceneType.GENERIC, ShadowType.NONE);
		shell.open();
		Assert.assertNull(ogreCanvas.getActiveCamLookAt());
	}
}
