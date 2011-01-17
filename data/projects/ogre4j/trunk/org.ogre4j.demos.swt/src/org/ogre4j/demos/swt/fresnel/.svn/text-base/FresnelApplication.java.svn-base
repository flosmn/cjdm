package org.ogre4j.demos.swt.fresnel;

import java.util.Vector;

import org.ogre4j.AnimationState;
import org.ogre4j.Camera;
import org.ogre4j.Capabilities;
import org.ogre4j.ColourValue;
import org.ogre4j.Entity;
import org.ogre4j.GpuProgramManager;
import org.ogre4j.HardwareBuffer;
import org.ogre4j.HardwarePixelBufferSharedPtr;
import org.ogre4j.IEntity;
import org.ogre4j.IHardwarePixelBufferSharedPtr;
import org.ogre4j.ILight;
import org.ogre4j.IManualResourceLoader;
import org.ogre4j.IMaterialPtr;
import org.ogre4j.IMeshPtr;
import org.ogre4j.IPlane;
import org.ogre4j.IRadian;
import org.ogre4j.IRenderSystemCapabilities;
import org.ogre4j.IRenderTarget;
import org.ogre4j.IResourcePtr;
import org.ogre4j.ISceneNode;
import org.ogre4j.ITexturePtr;
import org.ogre4j.IViewport;
import org.ogre4j.Light;
import org.ogre4j.ManualResourceLoader;
import org.ogre4j.MaterialManager;
import org.ogre4j.MaterialPtr;
import org.ogre4j.Math;
import org.ogre4j.MeshManager;
import org.ogre4j.MeshPtr;
import org.ogre4j.PixelFormat;
import org.ogre4j.Plane;
import org.ogre4j.Quaternion;
import org.ogre4j.Radian;
import org.ogre4j.ResourceGroupManager;
import org.ogre4j.ResourcePtr;
import org.ogre4j.Root;
import org.ogre4j.SceneNode;
import org.ogre4j.SimpleSpline;
import org.ogre4j.TextureManager;
import org.ogre4j.TexturePtr;
import org.ogre4j.TextureType;
import org.ogre4j.Vector3;
import org.ogre4j.Node.TransformSpace;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;
import org.xbig.base.WithoutNativeObject;

public class FresnelApplication extends ExampleApplication {

	public static Camera theCam;
	public static IEntity pPlaneEn;
	public static Vector<Entity> aboveWaterEnts = new Vector<Entity>();
	public static Vector<Entity> belowWaterEnts = new Vector<Entity>();
	public static IViewport view1;
	public static IViewport view2;
	public static int NUM_FISH = 30;
	public static int NUM_FISH_WAYPOINTS = 10;
	public static int FISH_PATH_LENGTH = 200;
	public static float FISH_SCALE = 1.2f;
	public static AnimationState[] fishAnimations = new AnimationState[NUM_FISH];
	public static SimpleSpline[] fishSplines = new SimpleSpline[NUM_FISH];
	public static Vector3[] fishLastPosition = new Vector3[NUM_FISH];
	public static ISceneNode[] fishNodes = new SceneNode[NUM_FISH];
	public static float animTime = 0.0f;
	public static IPlane reflectionPlane = new Plane();
	public static RefractionTextureListener mRefractionListener = new RefractionTextureListener();
	public static ReflectionTextureListener mReflectionListener = new ReflectionTextureListener();
	private ITexturePtr texture;

	// Just override the mandatory create scene method
	protected void createScene() {
		aboveWaterEnts.clear();
		belowWaterEnts.clear();

		// Check prerequisites first
		IRenderSystemCapabilities caps = Root.getSingleton().getRenderSystem()
				.getCapabilities();
		if (!caps.hasCapability(Capabilities.RSC_VERTEX_PROGRAM)
				|| !(caps.hasCapability(Capabilities.RSC_FRAGMENT_PROGRAM))) {
			throw new RuntimeException(
					"Your card does not support vertex and fragment programs, so cannot run this demo. Sorry!");
		} else {
			if (!GpuProgramManager.getSingleton().isSyntaxSupported("arbfp1")
					&& !GpuProgramManager.getSingleton().isSyntaxSupported(
							"ps_2_0")
					&& !GpuProgramManager.getSingleton().isSyntaxSupported(
							"ps_1_4")) {
				throw new RuntimeException(
						"Your card does not support advanced fragment programs, so cannot run this demo. Sorry!");
			}
		}

		theCam = (Camera) mCamera;
		theCam.setPosition(-50, 125, 760);
		theCam.setDirection(0, 0, -1);
		// Set ambient light
		mSceneMgr.setAmbientLight(new ColourValue(0.5f, 0.5f, 0.5f, 1f));

		// Create a point light
		ILight l = mSceneMgr.createLight("MainLight");
		l.setType(Light.LightTypes.LT_DIRECTIONAL);
		l.setDirection(Vector3.getNEGATIVE_UNIT_Y());

		Entity pEnt;
		IManualResourceLoader mrl = new ManualResourceLoader(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		texture = new TexturePtr();
		TextureManager.getSingleton().createManual(texture, "Refraction",
				ResourceGroupManager.getDEFAULT_RESOURCE_GROUP_NAME(),
				TextureType.TEX_TYPE_2D, (long) 512, (long) 512, 0,
				PixelFormat.PF_R8G8B8, 0x200, mrl, false, 0);
		// RenderTexture* rttTex =
		// mRoot->getRenderSystem()->createRenderTexture( "Refraction", 512, 512
		// );
		IHardwarePixelBufferSharedPtr hPBPtr = new HardwarePixelBufferSharedPtr();
		((TexturePtr) texture).get().getBuffer(hPBPtr, 0, 0);
		IRenderTarget rttTex = ((HardwarePixelBufferSharedPtr) hPBPtr).get()
				.getRenderTarget(0);

		{
			view1 = rttTex.addViewport(mCamera, 0, 0f, 0f, 1f, 1.0f);
			IResourcePtr ptr = new ResourcePtr();
			MaterialManager.getSingleton().getByName(ptr,
					"Examples/FresnelReflectionRefraction");
			IMaterialPtr mat = new MaterialPtr(ptr);
			((MaterialPtr) mat).get().getTechnique(0).getPass(0)
					.getTextureUnitState(2).setTextureName("Refraction",
							TextureType.TEX_TYPE_2D);
			view1.setOverlaysEnabled(false);
			rttTex.addListener(mRefractionListener);
			// v.delete();
			// ptr.delete();
			mat.delete();

		}

		TextureManager.getSingleton().createManual(texture, "Reflection",
				ResourceGroupManager.getDEFAULT_RESOURCE_GROUP_NAME(),
				TextureType.TEX_TYPE_2D, 512, 512, 0, PixelFormat.PF_R8G8B8,
				0x200, mrl, false, 0);
		mrl.delete();
		((TexturePtr) texture).get().getBuffer(hPBPtr, 0, 0);
		rttTex = ((HardwarePixelBufferSharedPtr) hPBPtr).get().getRenderTarget(
				0);
		{
			view2 = rttTex.addViewport(mCamera, 0, 0f, 0f, 1f, 1f);
			IResourcePtr ptr = new ResourcePtr();
			MaterialManager.getSingleton().getByName(ptr,
					"Examples/FresnelReflectionRefraction");
			IMaterialPtr mat = new MaterialPtr(ptr);
			((MaterialPtr) mat).get().getTechnique(0).getPass(0)
					.getTextureUnitState(1).setTextureName("Reflection",
							TextureType.TEX_TYPE_2D);
			view2.setOverlaysEnabled(false);
			rttTex.addListener(mReflectionListener);
			ptr.delete();
			mat.delete();
		}

		// Define a floor plane mesh
		reflectionPlane.setnormal(Vector3.getUNIT_Y());
		reflectionPlane.setd(0);
		IMeshPtr tmp = new MeshPtr();
		MeshManager.getSingleton().createPlane(tmp, "ReflectPlane",
				ResourceGroupManager.getDEFAULT_RESOURCE_GROUP_NAME(),
				reflectionPlane, 700, 1300, 10, 10, true, 2, 1, 1,
				Vector3.getNEGATIVE_UNIT_Z(),
				HardwareBuffer.Usage.HBU_STATIC_WRITE_ONLY,
				HardwareBuffer.Usage.HBU_STATIC_WRITE_ONLY, true, true);

		pPlaneEn = (Entity) mSceneMgr.createEntity("plane", "ReflectPlane");
		pPlaneEn.setMaterialName("Examples/FresnelReflectionRefraction");
		mSceneMgr.getRootSceneNode().createChildSceneNode(Vector3.getZERO(),
				Quaternion.getIDENTITY()).attachObject(pPlaneEn);

		mSceneMgr.setSkyBox(true, "Examples/CloudyNoonSkyBox", 5000, true,
				Quaternion.getIDENTITY(), ResourceGroupManager
						.getDEFAULT_RESOURCE_GROUP_NAME());

		// My node to which all objects will be attached
		SceneNode myRootNode = (SceneNode) mSceneMgr.getRootSceneNode()
				.createChildSceneNode(Vector3.getZERO(),
						Quaternion.getIDENTITY());

		// above water entities
		pEnt = (Entity) mSceneMgr.createEntity("RomanBathUpper",
				"RomanBathUpper.mesh");
		myRootNode.attachObject(pEnt);
		aboveWaterEnts.addElement(pEnt);

		pEnt = (Entity) mSceneMgr.createEntity("Columns", "Columns.mesh");
		myRootNode.attachObject(pEnt);
		aboveWaterEnts.addElement(pEnt);

		SceneNode headNode = (SceneNode) myRootNode.createChildSceneNode(
				Vector3.getZERO(), Quaternion.getIDENTITY());
		pEnt = (Entity) mSceneMgr.createEntity("OgreHead", "ogrehead.mesh");
		pEnt.setMaterialName("RomanBath/OgreStone");
		headNode.attachObject(pEnt);
		headNode.setPosition(-350, 55, 130);
		IRadian rad = new Radian(90);
		headNode.rotate(Vector3.getUNIT_Y(), rad, TransformSpace.TS_LOCAL);
		rad.delete();
		aboveWaterEnts.addElement(pEnt);

		pEnt = (Entity) mSceneMgr.createEntity("RomanBathLower",
				"RomanBathLower.mesh");
		belowWaterEnts.addElement(pEnt);
		myRootNode.attachObject(pEnt);

		int fishNo;
		for (fishNo = 0; fishNo < NUM_FISH; ++fishNo) {
			fishSplines[fishNo] = new SimpleSpline();
			fishLastPosition[fishNo] = new Vector3(0, 0, 0);
			pEnt = (Entity) mSceneMgr.createEntity("fish"
					+ String.valueOf(fishNo), "fish.mesh");
			fishNodes[fishNo] = (SceneNode) myRootNode.createChildSceneNode(
					Vector3.getZERO(), Quaternion.getIDENTITY());
			fishNodes[fishNo].setScale(FISH_SCALE, FISH_SCALE, FISH_SCALE);
			fishAnimations[fishNo] = (AnimationState) pEnt
					.getAnimationState("swim");
			fishAnimations[fishNo].setEnabled(true);
			fishNodes[fishNo].attachObject(pEnt);
			belowWaterEnts.addElement(pEnt);

			// Generate a random selection of points for the fish to swim to
			fishSplines[fishNo].setAutoCalculate(false);
			Vector3 lastPos = new Vector3(0, 0, 0);
			for (int waypoint = 0; waypoint < NUM_FISH_WAYPOINTS; ++waypoint) {
				Vector3 pos = new Vector3(
						((float) Math.SymmetricRandom() * 270), -10f,
						((float) Math.SymmetricRandom() * 700f));
				if (waypoint > 0) {
					// check this waypoint isn't too far, we don't want
					// turbo-fish ;)
					// since the waypoints are achieved every 5 seconds, half
					// the length
					// of the pond is ok#
					Vector3 dif = new Vector3();
					lastPos.operatorSubtraction(dif, pos);
					while (dif.length() > 750) {
						pos = new Vector3(
								((float) Math.SymmetricRandom() * 270), -10f,
								((float) Math.SymmetricRandom() * 700f));
						lastPos.operatorSubtraction(dif, pos);
					}
				}
				lastPos.delete();
				fishSplines[fishNo].addPoint(pos);
				lastPos = pos;
			}
			// close the spline
			fishSplines[fishNo].addPoint(fishSplines[fishNo].getPoint(0));
			// recalc
			fishSplines[fishNo].recalcTangents();

		}

	}

	protected void createFrameListener() {
		mFrameListener = new FresnelFrameListener(mWindow, mCamera, swtCanvas);
		mFrameListener.showDebugOverlay(true);
		this.addFrameListener(mFrameListener);
	}

	@Override
	protected void destroyScene() {
		IHardwarePixelBufferSharedPtr hPBPtr = new HardwarePixelBufferSharedPtr();
		((TexturePtr) texture).get().getBuffer(hPBPtr, 0, 0);
		IRenderTarget rttTex = ((HardwarePixelBufferSharedPtr) hPBPtr).get()
				.getRenderTarget(0);
		rttTex.removeAllListeners();

		mReflectionListener.delete();
		mRefractionListener.delete();

		super.destroyScene();
	}

	public static void main(String[] args) {
		FresnelApplication app = new FresnelApplication();
		try {
			app.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
