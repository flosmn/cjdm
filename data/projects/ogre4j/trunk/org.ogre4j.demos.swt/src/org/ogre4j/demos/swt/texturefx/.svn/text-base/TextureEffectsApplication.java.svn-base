/**
 * Copyright (c) 2005-2008 NetAllied Systems GmbH, Ravensburg
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package org.ogre4j.demos.swt.texturefx;

import org.ogre4j.ResourceGroupManager;
import org.ogre4j.ColourValue;
import org.ogre4j.IColourValue;
import org.ogre4j.IEntity;
import org.ogre4j.ILight;
import org.ogre4j.Vector3;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;
import org.ogre4j.IManualResourceLoader;
import org.ogre4j.IMaterialPtr;
import org.ogre4j.INameValuePairList;
import org.ogre4j.IResourcePtr;
import org.ogre4j.ISceneNode;
import org.ogre4j.ITextureUnitState;
import org.ogre4j.IVector3;
import org.ogre4j.ManualResourceLoader;
import org.ogre4j.MaterialPtr;
import org.ogre4j.NameValuePairList;
import org.ogre4j.Quaternion;
import org.ogre4j.ResourcePtr;
import org.ogre4j.SceneManager;
import org.xbig.base.WithoutNativeObject;
import org.ogre4j.MaterialManager;

class TextureEffectsApplication extends ExampleApplication {

	protected void createScalingPlane() {
		// Set up a material for the plane

		// Create a prefab plane
		IEntity planeEnt = mSceneMgr.createEntity("Plane",
				SceneManager.PrefabType.PT_PLANE);
		// Give the plane a texture
		planeEnt.setMaterialName("Examples/TextureEffect1");

		Vector3 vec3 = new Vector3(-250, -40, -100);
		ISceneNode node = mSceneMgr.getRootSceneNode().createChildSceneNode(vec3, Quaternion.getIDENTITY());
		vec3.delete();

		node.attachObject(planeEnt);
	}

	protected void createScrollingKnot() {
		IEntity ent = mSceneMgr.createEntity("knot", "knot.mesh");

		ent.setMaterialName("Examples/TextureEffect2");
		// Add entity to the root scene node
		Vector3 vec3 = new Vector3(200, 50, 150);
		ISceneNode node = mSceneMgr.getRootSceneNode().createChildSceneNode(
				vec3, Quaternion.getIDENTITY());
		vec3.delete();

		node.attachObject(ent);
	}

	protected void createWateryPlane() {
		// Create a prefab plane
		IEntity planeEnt = mSceneMgr.createEntity("WaterPlane",
				SceneManager.PrefabType.PT_PLANE);
		// Give the plane a texture
		planeEnt.setMaterialName("Examples/TextureEffect3");

		mSceneMgr.getRootSceneNode().attachObject(planeEnt);
	}

	// Just override the mandatory create scene method
	protected void createScene() {
		// Set ambient light
		IColourValue colour = new ColourValue(0.5f, 0.5f, 0.5f, 1.0f);
		mSceneMgr.setAmbientLight(colour);
		colour.delete();

		// Create a point light
		ILight l = mSceneMgr.createLight("MainLight");
		// Accept default settings: point light, white diffuse, just set
		// position
		// NB I could attach the light to a SceneNode if I wanted it to move
		// automatically with
		// other objects, but I don't
		IVector3 vec3 = new Vector3(20, 80, 50);
		l.setPosition(vec3);
		vec3.delete();

		createScalingPlane();
		createScrollingKnot();
		createWateryPlane();

		// Set up a material for the skydome
		IManualResourceLoader mrl = new ManualResourceLoader(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		INameValuePairList nvpl = new NameValuePairList();

		IResourcePtr skyMat = new ResourcePtr(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		MaterialManager.getSingleton().create(skyMat, "SkyMat",
				ResourceGroupManager.getDEFAULT_RESOURCE_GROUP_NAME(), false,
				mrl, nvpl);
		mrl.delete();
		nvpl.delete();

		// Use a cloudy sky
		IMaterialPtr mPtr = new MaterialPtr(skyMat);
		ITextureUnitState t = ((MaterialPtr) mPtr).get().getTechnique(0)
				.getPass(0).createTextureUnitState("clouds.jpg", 0);
		// Perform no dynamic lighting on the sky
		((MaterialPtr) mPtr).get().setLightingEnabled(false);
		mPtr.delete();

		// Scroll the clouds
		t.setScrollAnimation(0.15f, 0.0f);

		// System will automatically set no depth write

		// Create a skydome
		mSceneMgr.setSkyDome(true, "SkyMat", -5, 2, 4000, true, Quaternion
				.getIDENTITY(), 16, 16, -1, ResourceGroupManager
				.getDEFAULT_RESOURCE_GROUP_NAME());
	}

	public static void main(String[] args) {
		TextureEffectsApplication app = new TextureEffectsApplication();
		try {
			app.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}