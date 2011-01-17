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
package org.ogre4j.demos.swt.manualobject;

import org.ogre4j.ColourValue;
import org.ogre4j.IColourValue;
import org.ogre4j.IEntity;
import org.ogre4j.ISceneNode;
import org.ogre4j.IVector3;
import org.ogre4j.ManualObject;
import org.ogre4j.MeshPtr;
import org.ogre4j.Quaternion;
import org.ogre4j.Vector3;
import org.ogre4j.RenderOperation.OperationType;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;

/**
 * A simple demo showing how to use ManualObject.
 * 
 */
public class ManualObjectDemo extends ExampleApplication {

	/**
	 * ManualObject to be rendered in this simple demo. It is a member to
	 * prevent garbage collection.
	 */
	protected ManualObject triangle;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ogre4j.demos.swt.exampleapp.ExampleApplication#createScene()
	 */
	@Override
	protected void createScene() {
		triangle = new ManualObject("triangle");
		triangle.begin("BaseWhiteNoLighting", OperationType.OT_TRIANGLE_LIST);
		triangle.position(-50, 0, 0);
		triangle.normal(1, 0, 0);
		triangle.position(50, 0, 0);
		triangle.normal(1, 0, 0);
		triangle.position(0, 50, 0);
		triangle.normal(1, 0, 0);
		triangle.triangle(0, 1, 2);
		triangle.end();
		String meshName = "triangle.mesh";
		MeshPtr triangleMesh = new MeshPtr();
		triangle.convertToMesh(triangleMesh, meshName, "manual");

		// OGRE API doc says you have to set bounds. I don't do it here for
		// simplicity's sake.
		// triangleMesh.get()._setBounds();

		// setup scene

		// set ambient light
		IColourValue ambientColour = new ColourValue(1.0f, 1.0f, 1.0f, 1.0f);
		mSceneMgr.setAmbientLight(ambientColour);
		ambientColour.delete();

		// put manual mesh into scene
		IEntity meshEntity = mSceneMgr.createEntity("mesh_triangle", meshName);
		ISceneNode meshNode = mSceneMgr.getRootSceneNode()
				.createChildSceneNode("meshNode", Vector3.getZERO(),
						Quaternion.getIDENTITY());
		meshNode.attachObject(meshEntity);

		// put manual object into scene
		IVector3 pos = new Vector3(100, 0, 0);
		ISceneNode manualNode = mSceneMgr.getRootSceneNode()
				.createChildSceneNode("manualNode", pos,
						Quaternion.getIDENTITY());
		pos.delete();
		manualNode.attachObject(triangle);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ogre4j.demos.swt.exampleapp.ExampleApplication#destroyScene()
	 */
	@Override
	protected void destroyScene() {
		triangle.delete();
		super.destroyScene();
	}

	/**
	 * Main.
	 */
	public static void main(String[] args) {
		ManualObjectDemo app = new ManualObjectDemo();
		try {
			app.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
