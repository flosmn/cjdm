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
package org.ogre4j.demos.swt.lighting;

import java.util.Vector;

import org.ogre4j.Animation;
import org.ogre4j.Billboard;
import org.ogre4j.ColourValue;
import org.ogre4j.ControllerFunctionRealPtr;
import org.ogre4j.IAnimation;
import org.ogre4j.IAnimationState;
import org.ogre4j.IBillboardSet;
import org.ogre4j.IColourValue;
import org.ogre4j.IControllerValueRealPtr;
import org.ogre4j.IEntity;
import org.ogre4j.ILight;
import org.ogre4j.INameValuePairList;
import org.ogre4j.INodeAnimationTrack;
import org.ogre4j.IRibbonTrail;
import org.ogre4j.ISceneNode;
import org.ogre4j.ITransformKeyFrame;
import org.ogre4j.Light;
import org.ogre4j.NameValuePairList;
import org.ogre4j.Quaternion;
import org.ogre4j.ResourceGroupManager;
import org.ogre4j.RibbonTrail;
import org.ogre4j.Vector3;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;
import org.xbig.base.InstancePointer;
import org.xbig.base.StringPointer;

/** Application class */
public class LightingApplication extends ExampleApplication {

	// The set of all the billboards used for the lights
	// 2 sets because we'll rotate them differently
	protected IBillboardSet mRedYellowLights;
	protected IBillboardSet mGreenBlueLights;

	// Billboards
	protected Billboard mRedLightBoard;
	protected Billboard mBlueLightBoard;
	protected Billboard mYellowLightBoard;
	protected Billboard mGreenLightBoard;

	// Lights
	protected Light mRedLight;
	protected Light mBlueLight;
	protected Light mYellowLight;
	protected Light mGreenLight;

	// Light flashers
	protected IControllerValueRealPtr mRedLightFlasher;
	protected IControllerValueRealPtr mBlueLightFlasher;
	protected IControllerValueRealPtr mYellowLightFlasher;
	protected IControllerValueRealPtr mGreenLightFlasher;

	// Light controller functions
	protected ControllerFunctionRealPtr mRedLightControllerFunc;
	protected ControllerFunctionRealPtr mBlueLightControllerFunc;
	protected ControllerFunctionRealPtr mYellowLightControllerFunc;
	protected ControllerFunctionRealPtr mGreenLightControllerFunc;

	// Light controllers

	public static Vector<IAnimationState> mAnimStateList = new Vector<IAnimationState>();

	protected void createScene() {
		// Set a very low level of ambient lighting
		IColourValue color = new ColourValue(0.1f, 0.1f, 0.1f, 1.0f);
		mSceneMgr.setAmbientLight(color);
		color.delete();

		// Use the "Space" skybox
		mSceneMgr.setSkyBox(true, "Examples/SpaceSkyBox", 4000f, true,
				Quaternion.getIDENTITY(), ResourceGroupManager
						.getDEFAULT_RESOURCE_GROUP_NAME());

		// Load ogre head
		IEntity head = mSceneMgr.createEntity("head", "ogrehead.mesh");

		// Attach the head to the scene
		mSceneMgr.getRootSceneNode().attachObject(head);

		setupTrailLights();
	}

	void setupTrailLights() {
		ColourValue color = new ColourValue(0.5f, 0.5f, 0.5f, 1.0f);
		mSceneMgr.setAmbientLight(color);
		color.delete();

		Vector3 dir = new Vector3(-1f, -1f, 0.5f);

		dir.normalise();
		ILight l = mSceneMgr.createLight("light1");
		l.setType(Light.LightTypes.LT_DIRECTIONAL);
		l.setDirection(dir);
		dir.delete();

		INameValuePairList pairList = new NameValuePairList();
		StringPointer ptr = new StringPointer("2");
		pairList.insert("numberOfChains", ptr);
		ptr.delete();
		ptr = new StringPointer("80");
		pairList.insert("maxElements", ptr);
		ptr.delete();
		IRibbonTrail trail = new RibbonTrail(new InstancePointer(mSceneMgr
				.createMovableObject("1", "RibbonTrail", pairList)
				.getInstancePointer().pointer));
		pairList.delete();
		trail.setMaterialName("Examples/LightRibbonTrail");
		trail.setTrailLength(400);

		mSceneMgr.getRootSceneNode().createChildSceneNode(Vector3.getZERO(),
				Quaternion.getIDENTITY()).attachObject(trail);

		// Create 3 nodes for trail to follow
		ISceneNode animNode = mSceneMgr.getRootSceneNode()
				.createChildSceneNode(Vector3.getZERO(),
						Quaternion.getIDENTITY());
		animNode.setPosition(50, 30, 0);
		IAnimation anim = mSceneMgr.createAnimation("an1", 14);
		anim.setInterpolationMode(Animation.InterpolationMode.IM_SPLINE);
		INodeAnimationTrack track = anim.createNodeTrack(1, animNode);
		ITransformKeyFrame kf = track.createNodeKeyFrame(0);
		kf.setTranslate(new Vector3(50, 30, 0));
		kf = track.createNodeKeyFrame(2);
		kf.setTranslate(new Vector3(100, -30, 0));
		kf = track.createNodeKeyFrame(4);
		kf.setTranslate(new Vector3(120, -100, 150));
		kf = track.createNodeKeyFrame(6);
		kf.setTranslate(new Vector3(30, -100, 50));
		kf = track.createNodeKeyFrame(8);
		kf.setTranslate(new Vector3(-50, 30, -50));
		kf = track.createNodeKeyFrame(10);
		kf.setTranslate(new Vector3(-150, -20, -100));
		kf = track.createNodeKeyFrame(12);
		kf.setTranslate(new Vector3(-50, -30, 0));
		kf = track.createNodeKeyFrame(14);
		kf.setTranslate(new Vector3(50, 30, 0));

		IAnimationState animState = mSceneMgr.createAnimationState("an1");
		animState.setEnabled(true);
		mAnimStateList.addElement(animState);

		trail.setInitialColour(0, 1.0f, 0.8f, 0f, 0f);
		trail.setColourChange(0, 0.5f, 0.5f, 0.5f, 0.5f);
		trail.setInitialWidth(0, 5);
		trail.addNode(animNode);

		// Add light
		ILight l2 = mSceneMgr.createLight("l2");
		l2.setDiffuseColour(trail.getInitialColour(0));
		animNode.attachObject(l2);

		// Add billboard
		IBillboardSet bbs = mSceneMgr.createBillboardSet("bb", 1);
		bbs.createBillboard(Vector3.getZERO(), trail.getInitialColour(0));
		bbs.setMaterialName("Examples/Flare");
		animNode.attachObject(bbs);

		animNode = mSceneMgr.getRootSceneNode().createChildSceneNode(
				Vector3.getZERO(), Quaternion.getIDENTITY());
		animNode.setPosition(-50, 100, 0);
		anim = mSceneMgr.createAnimation("an2", 10);
		anim.setInterpolationMode(Animation.InterpolationMode.IM_SPLINE);
		track = anim.createNodeTrack(1, animNode);
		kf = track.createNodeKeyFrame(0);
		kf.setTranslate(new Vector3(-50, 100, 0));
		kf = track.createNodeKeyFrame(2);
		kf.setTranslate(new Vector3(-100, 150, -30));
		kf = track.createNodeKeyFrame(4);
		kf.setTranslate(new Vector3(-200, 0, 40));
		kf = track.createNodeKeyFrame(6);
		kf.setTranslate(new Vector3(0, -150, 70));
		kf = track.createNodeKeyFrame(8);
		kf.setTranslate(new Vector3(50, 0, 30));
		kf = track.createNodeKeyFrame(10);
		kf.setTranslate(new Vector3(-50, 100, 0));

		// mAnimStateList = new
		animState = mSceneMgr.createAnimationState("an2");
		animState.setEnabled(true);
		mAnimStateList.addElement(animState);

		trail.setInitialColour(1, 0.0f, 1.0f, 0.4f, 0.0f);
		trail.setColourChange(1, 0.5f, 0.5f, 0.5f, 0.5f);
		trail.setInitialWidth(1, 5);
		trail.addNode(animNode);

		// Add light
		l2 = mSceneMgr.createLight("l3");
		l2.setDiffuseColour(trail.getInitialColour(1));
		animNode.attachObject(l2);

		// Add billboard
		bbs = mSceneMgr.createBillboardSet("bb2", 1);
		bbs.createBillboard(Vector3.getZERO(), trail.getInitialColour(1));
		bbs.setMaterialName("Examples/Flare");
		animNode.attachObject(bbs);
	}

	protected void createFrameListener() {
		// This is where we instantiate our own frame listener
		mFrameListener = new LightingListener(mWindow, mCamera, swtCanvas);
		this.addFrameListener(mFrameListener);
	}

	public static void main(String[] args) {
		LightingApplication app = new LightingApplication();
		try {
			app.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
