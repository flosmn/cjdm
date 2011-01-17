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
package org.ogre4j.demos.swt.skeletalanimation;

import org.eclipse.swt.widgets.Canvas;
import org.ogre4j.Animation;
import org.ogre4j.AnimationTrack;
import org.ogre4j.ColourValue;
import org.ogre4j.Degree;
import org.ogre4j.GpuProgramPtr;
import org.ogre4j.HardwareBuffer;
import org.ogre4j.IAnimation;
import org.ogre4j.IAnimationState;
import org.ogre4j.IAnimationTrack;
import org.ogre4j.IBone;
import org.ogre4j.ICamera;
import org.ogre4j.IColourValue;
import org.ogre4j.IDegree;
import org.ogre4j.IEntity;
import org.ogre4j.ILight;
import org.ogre4j.IMaterialPtr;
import org.ogre4j.IMeshPtr;
import org.ogre4j.INameValuePairList;
import org.ogre4j.INodeAnimationTrack;
import org.ogre4j.IPass;
import org.ogre4j.IPlane;
import org.ogre4j.IQuaternion;
import org.ogre4j.IRadian;
import org.ogre4j.IRenderWindow;
import org.ogre4j.IRenderable;
import org.ogre4j.IResourcePtr;
import org.ogre4j.ISceneNode;
import org.ogre4j.ISkeletonPtr;
import org.ogre4j.ITechnique;
import org.ogre4j.ITimeIndex;
import org.ogre4j.ITransformKeyFrame;
import org.ogre4j.IVector3;
import org.ogre4j.Light;
import org.ogre4j.MaterialPtr;
import org.ogre4j.Math;
import org.ogre4j.MeshManager;
import org.ogre4j.MeshPtr;
import org.ogre4j.NameValuePairList;
import org.ogre4j.Plane;
import org.ogre4j.Quaternion;
import org.ogre4j.Radian;
import org.ogre4j.Renderable;
import org.ogre4j.ResourceGroupManager;
import org.ogre4j.ResourcePtr;
import org.ogre4j.ShadowTechnique;
import org.ogre4j.SkeletonManager;
import org.ogre4j.SkeletonPtr;
import org.ogre4j.TimeIndex;
import org.ogre4j.TransformKeyFrame;
import org.ogre4j.Vector3;
import org.ogre4j.Animation.InterpolationMode;
import org.ogre4j.Animation.RotationInterpolationMode;
import org.ogre4j.Node.TransformSpace;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;
import org.ogre4j.demos.swt.exampleapp.ExampleFrameListener;
import org.ogre4j.demos.swt.exampleapp.FrameEvent;
import org.xbig.base.WithoutNativeObject;

public class SkeletalAnimation extends ExampleApplication {

	public class SkeletalAnimationFrameListener extends ExampleFrameListener {

		public SkeletalAnimationFrameListener(IRenderWindow rw, ICamera cam,
				Canvas swtCanvas, String debugText) {
			super(rw, cam, swtCanvas);
			mDebugText = debugText;
		}

		public boolean frameStarted(FrameEvent evt) {
			if (!super.frameStarted(evt)) {
				return false;
			}
			for (int i = 0; i < NUM_JAIQUAS; ++i) {
				float inc = evt.timeSinceLastFrame * mAnimationSpeed[i];

				if ((mAnimState[i].getTimePosition() + inc) >= mAnimChop) {

					// Quaternion rot(mAnimationRotation, Vector3::UNIT_Y);
					IRadian rad = new Radian(mAnimationRotation);
					IQuaternion rot = new Quaternion(rad, Vector3.getUNIT_Y());
					rad.delete();

					// Vector3 startoffset = mSceneNode[i]->getOrientation() *
					// -mSneakStartOffset;
					IVector3 startoffset = new Vector3();
					IVector3 tmp2 = new Vector3();
					mSneakStartOffset.operatorSubtraction(tmp2);
					mSceneNode[i].getOrientation().operatorMultiplication(
							startoffset, tmp2);
					tmp2.delete();
					tmp2 = null;

					// Vector3 endoffset = mSneakEndOffset;
					IVector3 endoffset = mSneakEndOffset;

					// Vector3 offset = rot * startoffset;
					Vector3 offset = new Vector3();
					rot.operatorMultiplication(offset, startoffset);

					// Vector3 currEnd = mSceneNode[i]->getOrientation() *
					// endoffset + mSceneNode[i]->getPosition();
					Vector3 currEnd = new Vector3();
					IVector3 tmp3 = new Vector3();
					mSceneNode[i].getOrientation().operatorMultiplication(tmp3,
							endoffset);
					tmp3.operatorAddition(currEnd, mSceneNode[i].getPosition());
					tmp3.delete();
					tmp3 = null;

					// mSceneNode[i]->setPosition(currEnd + offset);
					Vector3 tmp = new Vector3();
					currEnd.operatorAddition(tmp, offset);
					mSceneNode[i].setPosition(tmp);
					tmp.delete();
					tmp = null;

					// mSceneNode[i]->rotate(rot);
					mSceneNode[i].rotate(rot, TransformSpace.TS_LOCAL);

					// mAnimState[i]->setTimePosition((mAnimState[i]->getTimePosition()
					// + inc) - mAnimChop);
					mAnimState[i].setTimePosition((mAnimState[i]
							.getTimePosition() + inc)
							- mAnimChop);

					startoffset.delete();
					offset.delete();
					currEnd.delete();
					rot.delete();
				} else {
					mAnimState[i].addTime(inc);
				}

			}
			// Call default
			return true;
		}

	}

	public static final int NUM_JAIQUAS = 6;
	protected IAnimationState[] mAnimState = new IAnimationState[NUM_JAIQUAS];
	protected float[] mAnimationSpeed = new float[NUM_JAIQUAS];
	protected IVector3 mSneakStartOffset = new Vector3(0, 0, 0);
	protected IVector3 mSneakEndOffset = new Vector3(0, 0, 0);

	protected IQuaternion[] mOrientations = new IQuaternion[NUM_JAIQUAS];
	protected IVector3[] mBasePositions = new IVector3[NUM_JAIQUAS];
	protected ISceneNode[] mSceneNode = new ISceneNode[NUM_JAIQUAS];
	protected IDegree mAnimationRotation = new Degree(-60);
	protected float mAnimChop = 7.96666f;
	protected float mAnimChopBlend = 0.3f;

	protected String mDebugText;

	@Override
	protected void createScene() {
		mSceneMgr
				.setShadowTechnique(ShadowTechnique.SHADOWTYPE_TEXTURE_MODULATIVE);
		mSceneMgr.setShadowTextureSize(512);
		IColourValue shadowColour = new ColourValue(0.6f, 0.6f, 0.6f, 1.0f);
		mSceneMgr.setShadowColour(shadowColour);
		shadowColour.delete();

		// Setup animation default
		Animation.setDefaultInterpolationMode(InterpolationMode.IM_LINEAR);
		Animation
				.setDefaultRotationInterpolationMode(RotationInterpolationMode.RIM_LINEAR);

		// Set ambient light
		IColourValue ambientLight = new ColourValue(0.5f, 0.5f, 0.5f, 1.0f);
		mSceneMgr.setAmbientLight(ambientLight);
		ambientLight.delete();

		// The jaiqua sneak animation doesn't loop properly, so lets hack it so
		// it does
		// We want to copy the initial keyframes of all bones, but alter the
		// Spineroot
		// to give it an offset of where the animation ends
		IResourcePtr resPtr = new ResourcePtr(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		INameValuePairList nvPairList = new NameValuePairList();
		SkeletonManager.getSingleton().load(resPtr, "jaiqua.skeleton",
				ResourceGroupManager.getDEFAULT_RESOURCE_GROUP_NAME(), false,
				MeshManager.getSingleton(), nvPairList);
		nvPairList.delete();

		ISkeletonPtr skel = new SkeletonPtr(resPtr.getInstancePointer());
		IAnimation anim = ((SkeletonPtr) skel).get().getAnimation(1);

		Animation.NodeTrackIterator trackIter = new Animation.NodeTrackIterator(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		anim.getNodeTrackIterator(trackIter);
		while (trackIter.hasMoreElements()) {
			INodeAnimationTrack track = trackIter.getNext();

			IAnimationTrack nullAnimationTrack = new AnimationTrack(
					WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
			// note: nullAnimationTrack is a null pointer, deleting it is not
			// necessary
			ITransformKeyFrame oldKf = new TransformKeyFrame(
					nullAnimationTrack, 0.0f);
			ITimeIndex tmpTimeIndex = new TimeIndex(mAnimChop);
			track.getInterpolatedKeyFrame(tmpTimeIndex, oldKf);
			tmpTimeIndex.delete();

			// Drop all keyframes after the chop
			while (track.getKeyFrame(track.getNumKeyFrames() - 1).getTime() >= mAnimChop
					- mAnimChopBlend)
				track.removeKeyFrame(track.getNumKeyFrames() - 1);

			ITransformKeyFrame newKf = track.createNodeKeyFrame(mAnimChop);
			ITransformKeyFrame startKf = track.getNodeKeyFrame(0);

			IBone bone = ((SkeletonPtr) skel).get().getBone(track.getHandle());

			if (bone.getName().equals("Spineroot")) {
				startKf.getTranslate().operatorAddition(mSneakStartOffset,
						bone.getInitialPosition());
				oldKf.getTranslate().operatorAddition(mSneakEndOffset,
						bone.getInitialPosition());
				mSneakStartOffset.sety(mSneakEndOffset.gety());
				// Adjust spine root relative to new location
				newKf.setRotation(oldKf.getRotation());
				newKf.setTranslate(oldKf.getTranslate());
				newKf.setScale(oldKf.getScale());

			} else {
				newKf.setRotation(startKf.getRotation());
				newKf.setTranslate(startKf.getTranslate());
				newKf.setScale(startKf.getScale());
			}

		}

		trackIter.delete();
		resPtr.delete();

		// IVector3 pos = mCamera.getDerivedPosition();
		float rotInc = Math.getTWO_PI() / (float) NUM_JAIQUAS;
		float rot = 0.0f;
		for (int i = 0; i < NUM_JAIQUAS; ++i) {
			IQuaternion q = new Quaternion(1, 0, 0, 0);
			IRadian rad = new Radian(rot);
			q.FromAngleAxis(rad, Vector3.getUNIT_Y());
			rad.delete();
			mOrientations[i] = q;
			IVector3 tmp = new Vector3();
			IVector3 tmpVec = new Vector3(0, 0, -20);
			q.operatorMultiplication(tmp, tmpVec);
			mBasePositions[i] = tmp;
			tmpVec.delete();

			IEntity jaiqua = mSceneMgr
					.createEntity("jaiqua" + i, "jaiqua.mesh");

			mSceneNode[i] = mSceneMgr.getRootSceneNode().createChildSceneNode(
					Vector3.getZERO(), Quaternion.getIDENTITY());
			mSceneNode[i].attachObject(jaiqua);
			mSceneNode[i].rotate(q, TransformSpace.TS_LOCAL);
			mSceneNode[i].translate(mBasePositions[i], TransformSpace.TS_LOCAL);

			mAnimState[i] = jaiqua.getAnimationState("Sneak");
			mAnimState[i].setEnabled(true);
			mAnimState[i].setLoop(false);
			mAnimationSpeed[i] = Math.RangeRandom((float) 0.5, (float) 1.5);
			rot += rotInc;
		}

		// Give it a little ambience with lights
		ILight l;
		l = mSceneMgr.createLight("BlueLight");
		l.setType(Light.LightTypes.LT_SPOTLIGHT);
		l.setPosition(-200, 150, -100);
		IVector3 dir = l.getPosition();
		IVector3 inverseDir = new Vector3(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		dir.operatorSubtraction(inverseDir);
		inverseDir.normalise();
		l.setDirection(inverseDir);
		inverseDir.delete();
		l.setDiffuseColour(0.5f, 0.5f, 1.0f);

		l = mSceneMgr.createLight("GreenLight");
		l.setType(Light.LightTypes.LT_SPOTLIGHT);
		l.setPosition(0, 150, -100);
		dir = l.getPosition();
		inverseDir = new Vector3(WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		dir.operatorSubtraction(inverseDir);
		inverseDir.normalise();
		l.setDirection(inverseDir);
		inverseDir.delete();
		l.setDiffuseColour(0.5f, 1.0f, 0.5f);

		// Position the camera
		mCamera.setPosition(100, 20, 0);
		mCamera.lookAt(0, 10, 0);

		// Report whether hardware skinning is enabled or not
		IEntity ent = mSceneMgr.getEntity("jaiqua0");
		IMaterialPtr matPtr = ent.getSubEntity(0).getMaterial();
		IRenderable nullPtr = new Renderable(WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		ITechnique t = ((MaterialPtr) matPtr).get().getBestTechnique(0, nullPtr);
		IPass p = t.getPass(0);
		if (p.hasVertexProgram()
				&& ((GpuProgramPtr) p.getVertexProgram()).get()
						.isSkeletalAnimationIncluded())
			mDebugText = "Hardware skinning is enabled";
		else
			mDebugText = "Software skinning is enabled";

		IPlane plane = new Plane();
		plane.setnormal(Vector3.getUNIT_Y());
		plane.setd(100);
		IMeshPtr meshPtr = new MeshPtr();
		MeshManager.getSingleton().createPlane(meshPtr, "Myplane",
				ResourceGroupManager.getDEFAULT_RESOURCE_GROUP_NAME(), plane,
				1500, 1500, 20, 20, true, 1, 60, 60, Vector3.getUNIT_Z(),
				HardwareBuffer.Usage.HBU_STATIC_WRITE_ONLY,
				HardwareBuffer.Usage.HBU_STATIC_WRITE_ONLY, true, true);
		meshPtr.delete();
		IEntity pPlaneEnt = mSceneMgr.createEntity("plane", "Myplane");
		pPlaneEnt.setMaterialName("Examples/Rockwall");
		pPlaneEnt.setCastShadows(false);
		IVector3 translate = new Vector3(0, 99, 0);
		mSceneMgr.getRootSceneNode().createChildSceneNode(translate,
				Quaternion.getIDENTITY()).attachObject(pPlaneEnt);
		translate.delete();
		plane.delete();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SkeletalAnimation app = new SkeletalAnimation();
		try {
			app.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void createFrameListener() {
		SkeletalAnimationFrameListener mSkeletalFrameListener = new SkeletalAnimationFrameListener(
				mWindow, mCamera, swtCanvas, mDebugText);
		this.addFrameListener(mSkeletalFrameListener);
	}

	@Override
	protected void destroyScene() {
		mAnimationRotation.delete();
		for (IQuaternion q : mOrientations) {
			q.delete();
		}
		for (IVector3 vec : mBasePositions) {
			vec.delete();
		}
	}
}
