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
package org.ogre4j.demos.swt.particlefx;

import org.eclipse.swt.widgets.Canvas;
import org.ogre4j.ColourValue;
import org.ogre4j.Degree;
import org.ogre4j.ICamera;
import org.ogre4j.IColourValue;
import org.ogre4j.IDegree;
import org.ogre4j.IEntity;
import org.ogre4j.IParticleSystem;
import org.ogre4j.IRadian;
import org.ogre4j.IRenderWindow;
import org.ogre4j.ISceneNode;
import org.ogre4j.ParticleSystem;
import org.ogre4j.Quaternion;
import org.ogre4j.Radian;
import org.ogre4j.Vector3;
import org.ogre4j.Node.TransformSpace;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;
import org.ogre4j.demos.swt.exampleapp.ExampleFrameListener;
import org.ogre4j.demos.swt.exampleapp.FrameEvent;

//Event handler to add ability to alter curvature
class ParticleFrameListener extends ExampleFrameListener {
	protected ISceneNode mFountainNode;

	public ParticleFrameListener(IRenderWindow win, ICamera cam,
			Canvas swtCanvas, ISceneNode fountainNode) {
		super(win, cam, swtCanvas);
		mFountainNode = fountainNode;
	}

	public boolean frameStarted(FrameEvent evt) {
		if (super.frameStarted(evt) == false)
			return false;

		// Rotate fountains
		IDegree degree = new Degree(evt.timeSinceLastFrame * 30);
		IRadian rad = new Radian(degree);
		mFountainNode.yaw(rad, TransformSpace.TS_LOCAL);
		rad.delete();
		degree.delete();

		// Call default
		return true;

	}
}

public class ParticleFX extends ExampleApplication {

	protected ISceneNode mFountainNode;

	@Override
	protected void createScene() {
		// Set ambient light
		IColourValue ambientLight = new ColourValue(0.5f, 0.5f, 0.5f, 1.0f);
		mSceneMgr.setAmbientLight(ambientLight);
		ambientLight.delete();

		IEntity ent = mSceneMgr.createEntity("head", "ogrehead.mesh");

		// Add entity to the root scene node
		mSceneMgr.getRootSceneNode().createChildSceneNode(Vector3.getZERO(),
				Quaternion.getIDENTITY()).attachObject(ent);

		// Green nimbus around Ogre
		mSceneMgr.getRootSceneNode().createChildSceneNode(Vector3.getZERO(),
				Quaternion.getIDENTITY()).attachObject(
				mSceneMgr.createParticleSystem("Nimbus",
						"Examples/GreenyNimbus"));

		// Create some nice fireworks
		mSceneMgr.getRootSceneNode().createChildSceneNode(Vector3.getZERO(),
				Quaternion.getIDENTITY()).attachObject(
				mSceneMgr.createParticleSystem("Fireworks",
						"Examples/Fireworks"));

		// Create shared node for 2 fountains
		mFountainNode = mSceneMgr.getRootSceneNode().createChildSceneNode(
				Vector3.getZERO(), Quaternion.getIDENTITY());

		// fountain 1
		IParticleSystem pSys2 = mSceneMgr.createParticleSystem("fountain1",
				"Examples/PurpleFountain");
		// Point the fountain at an angle
		ISceneNode fNode = mFountainNode.createChildSceneNode(
				Vector3.getZERO(), Quaternion.getIDENTITY());
		fNode.translate(200f, -100f, 0f, TransformSpace.TS_PARENT);
		IRadian degree = new Radian(20);
		fNode.rotate(Vector3.getUNIT_Z(), degree, TransformSpace.TS_LOCAL);
		degree.delete();
		fNode.attachObject(pSys2);

		// fountain 2
		IParticleSystem pSys3 = mSceneMgr.createParticleSystem("fountain2",
				"Examples/PurpleFountain");
		// Point the fountain at an angle
		fNode = mFountainNode.createChildSceneNode(Vector3.getZERO(),
				Quaternion.getIDENTITY());
		fNode.translate(-200f, -100f, 0f, TransformSpace.TS_PARENT);
		degree = new Radian(20);
		fNode.rotate(Vector3.getUNIT_Z(), degree, TransformSpace.TS_LOCAL);
		degree.delete();
		fNode.attachObject(pSys3);

		// Create a rainstorm
		IParticleSystem pSys4 = mSceneMgr.createParticleSystem("rain",
				"Examples/Rain");
		ISceneNode rNode = mSceneMgr.getRootSceneNode().createChildSceneNode(
				Vector3.getZERO(), Quaternion.getIDENTITY());
		rNode.translate(0f, 1000f, 0f, TransformSpace.TS_PARENT);
		rNode.attachObject(pSys4);
		// Fast-forward the rain so it looks more natural
		pSys4.fastForward(5.0f, 0.1f);

		// Aureola around Ogre perpendicular to the ground
		IParticleSystem pSys5 = mSceneMgr.createParticleSystem("Aureola",
				"Examples/Aureola");
		mSceneMgr.getRootSceneNode().createChildSceneNode(Vector3.getZERO(),
				Quaternion.getIDENTITY()).attachObject(pSys5);

		// Set nonvisible timeout
		ParticleSystem.setDefaultNonVisibleUpdateTimeout(5);
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
		ParticleFX app = new ParticleFX();
		try {
			app.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
