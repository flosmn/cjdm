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
package org.ogre4j.demos.swt.skydome;

import org.ogre4j.ColourValue;
import org.ogre4j.HardwareBuffer;
import org.ogre4j.IColourValue;
import org.ogre4j.IEntity;
import org.ogre4j.ILight;
import org.ogre4j.IMeshPtr;
import org.ogre4j.IPlane;
import org.ogre4j.MeshManager;
import org.ogre4j.MeshPtr;
import org.ogre4j.Plane;
import org.ogre4j.Quaternion;
import org.ogre4j.ResourceGroupManager;
import org.ogre4j.Vector3;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;
import org.xbig.base.WithoutNativeObject;

// TODO SkyDomeFrameListener

public class SkyDome extends ExampleApplication {

	@Override
	protected void createScene() {
		IColourValue ambientLight = new ColourValue(0.5f, 0.5f, 0.5f, 1.0f);
		mSceneMgr.setAmbientLight(ambientLight);
		ambientLight.delete();

		// Create a skydome
		mSceneMgr.setSkyDome(true, "Examples/CloudySky", 5, 8, 4000, true,
				Quaternion.getIDENTITY(), 16, 16, -1, ResourceGroupManager
						.getDEFAULT_RESOURCE_GROUP_NAME());

		// Create a light
		ILight l = mSceneMgr.createLight("MainLight");
		// Accept default settings: point light, white diffuse, just set
		// position
		// NB I could attach the light to a SceneNode if I wanted it to move
		// automatically with
		// other objects, but I don't
		l.setPosition(20, 80, 50);

		IEntity ent;

		// Define a floor plane mesh
		IPlane p = new Plane();
		p.setnormal(Vector3.getUNIT_Y());
		p.setd(200);
		IMeshPtr tmp = new MeshPtr(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		MeshManager.getSingleton().createPlane(tmp, "FloorPlane",
				ResourceGroupManager.getDEFAULT_RESOURCE_GROUP_NAME(), p, 2000,
				2000, 1, 1, true, 1, 5, 5, Vector3.getUNIT_Z(),
				HardwareBuffer.Usage.HBU_STATIC_WRITE_ONLY,
				HardwareBuffer.Usage.HBU_STATIC_WRITE_ONLY, true, true);
		tmp.delete();
		p.delete();

		// Create an entity (the floor)
		ent = mSceneMgr.createEntity("floor", "FloorPlane");
		ent.setMaterialName("Examples/RustySteel");

		mSceneMgr.getRootSceneNode().attachObject(ent);

		ent = mSceneMgr.createEntity("head", "ogrehead.mesh");
		// Attach to child of root node, better for culling (otherwise bounds
		// are the combination of the 2)
		mSceneMgr.getRootSceneNode().createChildSceneNode(Vector3.getZERO(),
				Quaternion.getIDENTITY()).attachObject(ent);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SkyDome app = new SkyDome();
		try {
			app.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
