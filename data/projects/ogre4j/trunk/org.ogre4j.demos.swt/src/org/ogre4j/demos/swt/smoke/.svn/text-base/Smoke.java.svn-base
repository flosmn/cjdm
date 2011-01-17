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
package org.ogre4j.demos.swt.smoke;

import org.eclipse.swt.widgets.Canvas;
import org.ogre4j.ColourValue;
import org.ogre4j.ICamera;
import org.ogre4j.IColourValue;
import org.ogre4j.IParticleSystem;
import org.ogre4j.IRenderWindow;
import org.ogre4j.ISceneNode;
import org.ogre4j.Quaternion;
import org.ogre4j.ResourceGroupManager;
import org.ogre4j.Vector3;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;
import org.ogre4j.demos.swt.exampleapp.ExampleFrameListener;

//Event handler to add ability to alter curvature
class ParticleFrameListener extends ExampleFrameListener {
	protected ISceneNode mFountainNode;

	public ParticleFrameListener(IRenderWindow win, ICamera cam,
			Canvas swtCanvas, ISceneNode fountainNode) {
		super(win, cam, swtCanvas);
		mFountainNode = fountainNode;
	}
}

public class Smoke extends ExampleApplication {

	protected ISceneNode mFountainNode;

	@Override
	protected void createScene() {
		// Set ambient light
		IColourValue ambientLight = new ColourValue(0.5f, 0.5f, 0.5f, 1.0f);
		mSceneMgr.setAmbientLight(ambientLight);
		ambientLight.delete();

		// Create a skydome
		mSceneMgr.setSkyDome(true, "Examples/CloudySky", 5, 8, 4000, true,
				Quaternion.getIDENTITY(), 16, 16, -1, ResourceGroupManager
						.getDEFAULT_RESOURCE_GROUP_NAME());

		// Create shared node for 2 fountains
		mFountainNode = mSceneMgr.getRootSceneNode().createChildSceneNode(
				Vector3.getZERO(), Quaternion.getIDENTITY());

		// smoke
		IParticleSystem pSys2 = mSceneMgr.createParticleSystem("fountain1",
				"Examples/Smoke");

		// Point the fountain at an angle
		// ISceneNode fNode =
		// static_cast<ISceneNode>(mFountainNode.createChild());
		ISceneNode fNode = mFountainNode.createChildSceneNode(
				Vector3.getZERO(), Quaternion.getIDENTITY());
		fNode.attachObject(pSys2);
	}

	// Create new frame listener
	protected void createFrameListener() {
		mFrameListener = new ParticleFrameListener(mWindow, mCamera, swtCanvas,
				mFountainNode);
		this.addFrameListener(mFrameListener);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Smoke app = new Smoke();
		try {
			app.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
