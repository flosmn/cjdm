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
package org.ogre4j.demos.swt.transparency;

import org.ogre4j.ColourValue;
import org.ogre4j.IColourValue;
import org.ogre4j.IEntity;
import org.ogre4j.ISceneNode;
import org.ogre4j.SceneManager;
import org.ogre4j.Vector3;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;
import org.ogre4j.ILight;
import org.ogre4j.Math;

public class Transparency extends ExampleApplication {

	@Override
	protected void createScene() {
		// Set ambient light
		IColourValue ambientLight = new ColourValue(0.5f, 0.5f, 0.5f, 1.0f);
		mSceneMgr.setAmbientLight(ambientLight);
		ambientLight.delete();

		// Create a light
		ILight l = mSceneMgr.createLight("MainLight");
		l.setPosition(20, 80, 50);

		// Create a prefab plane Vector3.getUNIT_Y()
		IEntity planeEnt = mSceneMgr.createEntity("Plane",
				SceneManager.PrefabType.PT_PLANE);
		// Give the plane a texture
		planeEnt.setMaterialName("Examples/BumpyMetal");

		// Create an entity from a model (will be loaded automatically)
		IEntity knotEnt = mSceneMgr.createEntity("Knot", "knot.mesh");
		knotEnt.setMaterialName("Examples/TransparentTest");

		// Attach the 2 new entities to the root of the scene
		ISceneNode rootNode = mSceneMgr.getRootSceneNode();
		rootNode.attachObject(planeEnt);
		rootNode.attachObject(knotEnt);

		// Add a whole bunch of extra transparent entities
		IEntity cloneEnt;
		for (int n = 0; n < 10; ++n) {
			// Create a new node under the root
			ISceneNode node = mSceneMgr.createSceneNode();
			// Random translate
			Vector3 nodePos = new Vector3();

			Double tmpDouble = Math.SymmetricRandom() * 500.0;
			nodePos.setx(tmpDouble.floatValue());
			tmpDouble = Math.SymmetricRandom() * 500.0;
			nodePos.sety(tmpDouble.floatValue());
			tmpDouble = Math.SymmetricRandom() * 500.0;
			nodePos.setz(tmpDouble.floatValue());

			node.setPosition(nodePos);
			rootNode.addChild(node);

			nodePos.delete();
			// Clone knot
			cloneEnt = knotEnt.clone("Knot" + String.valueOf(n));
			// Attach to new node
			node.attachObject(cloneEnt);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Transparency app = new Transparency();
		try {
			app.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
