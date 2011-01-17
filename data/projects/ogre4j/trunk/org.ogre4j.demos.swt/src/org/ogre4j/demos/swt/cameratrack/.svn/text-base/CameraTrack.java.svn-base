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
package org.ogre4j.demos.swt.cameratrack;

import org.ogre4j.Animation;
import org.ogre4j.AnimationState;
import org.ogre4j.ColourValue;
import org.ogre4j.Entity;
import org.ogre4j.FogMode;
import org.ogre4j.ILight;
import org.ogre4j.IMeshPtr;
import org.ogre4j.IPlane;
import org.ogre4j.MeshManager;
import org.ogre4j.MeshPtr;
import org.ogre4j.NodeAnimationTrack;
import org.ogre4j.Plane;
import org.ogre4j.Quaternion;
import org.ogre4j.ResourceGroupManager;
import org.ogre4j.SceneNode;
import org.ogre4j.TransformKeyFrame;
import org.ogre4j.Vector3;
import org.ogre4j.HardwareBuffer.Usage;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;

public class CameraTrack extends ExampleApplication {

	protected SceneNode mFountainNode;
	public static AnimationState mAnimState;

	// Just override the mandatory create scene method
	public void createScene() {

		// Set ambient light
		ColourValue color = new ColourValue(0.2f, 0.2f, 0.2f, 1f);
		mSceneMgr.setAmbientLight(color);
		color.delete();

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

		Entity ent;

		// Define a floor plane mesh
		IPlane p = new Plane();
		p.setnormal(Vector3.getUNIT_Y());
		p.setd(200);
		IMeshPtr ptr = new MeshPtr();
		MeshManager.getSingleton().createPlane(ptr, "FloorPlane",
				ResourceGroupManager.getDEFAULT_RESOURCE_GROUP_NAME(), p,
				200000, 200000, 20, 20, true, 1, 50, 50, Vector3.getUNIT_Z(),
				Usage.HBU_STATIC_WRITE_ONLY, Usage.HBU_STATIC_WRITE_ONLY,
				false, false);
		ptr.delete();
		p.delete();

		// Create an entity (the floor)
		ent = (Entity) mSceneMgr.createEntity("floor", "FloorPlane");
		ent.setMaterialName("Examples/RustySteel");
		// Attach to child of root node, better for culling (otherwise bounds
		// are the combination of the 2)
		mSceneMgr.getRootSceneNode().createChildSceneNode(Vector3.getZERO(),
				Quaternion.getIDENTITY()).attachObject(ent);

		// Add a head, give it it's own node
		SceneNode headNode = (SceneNode) mSceneMgr.getRootSceneNode()
				.createChildSceneNode(Vector3.getZERO(),
						Quaternion.getIDENTITY());
		ent = (Entity) mSceneMgr.createEntity("head", "ogrehead.mesh");
		headNode.attachObject(ent);

		// Make sure the camera track this node
		mCamera.setAutoTracking(true, headNode, Vector3.getZERO());

		// Create the camera node & attach camera
		SceneNode camNode = (SceneNode) mSceneMgr.getRootSceneNode()
				.createChildSceneNode(Vector3.getZERO(),
						Quaternion.getIDENTITY());
		camNode.attachObject(mCamera);

		// set up spline animation of node
		Animation anim = (Animation) mSceneMgr.createAnimation("CameraTrack",
				10);
		// Spline it for nice curves
		anim.setInterpolationMode(Animation.InterpolationMode.IM_SPLINE);
		// Create a track to animate the camera's node
		NodeAnimationTrack track = (NodeAnimationTrack) anim.createNodeTrack(0,
				camNode);
		// Setup keyframes
		TransformKeyFrame key = (TransformKeyFrame) track.createNodeKeyFrame(0); // startposition
		key = (TransformKeyFrame) track.createNodeKeyFrame(2.5f);

		Vector3 vec3 = new Vector3(500f, 500f, -1000f);
		key.setTranslate(vec3);
		vec3.delete();

		vec3 = new Vector3(-1500f, 1000f, -600f);
		key = (TransformKeyFrame) track.createNodeKeyFrame(5f);
		key.setTranslate(vec3);
		vec3.delete();

		vec3 = new Vector3(0f, -100f, 0f);
		key = (TransformKeyFrame) track.createNodeKeyFrame(7.5f);
		key.setTranslate(vec3);
		vec3.delete();

		vec3 = new Vector3(0f, 0f, 0f);
		key = (TransformKeyFrame) track.createNodeKeyFrame(10f);
		key.setTranslate(vec3);
		vec3.delete();

		// Create a new animation state to track this
		mAnimState = (AnimationState) mSceneMgr
				.createAnimationState("CameraTrack");
		mAnimState.setEnabled(true);

		// Put in a bit of fog for the hell of it
		mSceneMgr.setFog(FogMode.FOG_EXP, ColourValue.getWhite(), 0.0002f,
				0.0f, 1f);

	}

	// Create new frame listener
	protected void createFrameListener() {
		mFrameListener = new CameraTrackListener(mWindow, mCamera, swtCanvas);
		this.addFrameListener(mFrameListener);

	}

	public static void main(String[] args) {
		CameraTrack app = new CameraTrack();
		try {
			app.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
