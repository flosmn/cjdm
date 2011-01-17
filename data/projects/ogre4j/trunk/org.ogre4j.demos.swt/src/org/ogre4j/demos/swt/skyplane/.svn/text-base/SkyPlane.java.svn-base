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
package org.ogre4j.demos.swt.skyplane;

import org.ogre4j.ColourValue;
import org.ogre4j.IColourValue;
import org.ogre4j.IEntity;
import org.ogre4j.ILight;
import org.ogre4j.IPlane;
import org.ogre4j.Plane;
import org.ogre4j.Vector3;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;

class SkyPlane extends ExampleApplication {

	// Just override the mandatory create scene method
	protected void createScene() {
		// Set ambient light
		IColourValue colour = new ColourValue(0.5f, 0.5f, 0.5f, 1.0f);
		mSceneMgr.setAmbientLight(colour);
		colour.delete();

		// Define the required skyplane
		IPlane plane = new Plane();
		// 5000 world units from the camera
		plane.setd(5000);
		// Above the camera, facing down
		plane.setnormal(Vector3.getNEGATIVE_UNIT_Y());
		// Create the plane 10000 units wide, tile the texture 3 times
		mSceneMgr.setSkyPlane(true, plane, "Examples/SpaceSkyPlane", 10000, 10,
				true, 0, 1, 1, "");
		plane.delete();

		// Create a light
		ILight l = mSceneMgr.createLight("MainLight");
		// Accept default settings: point light, white diffuse, just set
		// position
		// NB I could attach the light to a SceneNode if I wanted it to move
		// automatically with other objects, but I don't
		l.setPosition(20, 80, 50);

		// Also add a nice dragon in
		IEntity ent = mSceneMgr.createEntity("dragon", "dragon.mesh");
		mSceneMgr.getRootSceneNode().attachObject(ent);
	}

	public static void main(String[] args) {
		SkyPlane app = new SkyPlane();
		try {
			app.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
