package org.ogre4j.demos.swt.Bezier;

import org.ogre4j.ColourValue;
import org.ogre4j.HardwareBuffer;
import org.ogre4j.HardwareBufferManager;
import org.ogre4j.IColourValue;
import org.ogre4j.IEntity;
import org.ogre4j.ILight;
import org.ogre4j.IPass;
import org.ogre4j.IResourcePtr;
import org.ogre4j.IVertexDeclaration;
import org.ogre4j.ManualResourceLoader;
import org.ogre4j.Material;
import org.ogre4j.MaterialManager;
import org.ogre4j.MeshManager;
import org.ogre4j.NameValuePairList;
import org.ogre4j.PatchMeshPtr;
import org.ogre4j.PatchSurface;
import org.ogre4j.ResourceGroupManager;
import org.ogre4j.ResourcePtr;
import org.ogre4j.VertexElementSemantic;
import org.ogre4j.VertexElementType;
import org.ogre4j.Light.LightTypes;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;
import org.xbig.base.NativeFloatBuffer;
import org.xbig.base.WithoutNativeObject;

public class BezierApplication extends ExampleApplication {

	protected IVertexDeclaration patchDecl;
	protected PatchVertex[] patchCtlPoints;
	// buffer in which the Vertex data is hacked
	protected NativeFloatBuffer buf = new NativeFloatBuffer(72);

	PatchMeshPtr patch = new PatchMeshPtr();
	IPass patchPass;

	public BezierApplication() {
		super();
		this.patchDecl = null;
		this.patchCtlPoints = null;
	}

	@Override
	protected void destroyApplication() {
//		if (patchDecl != null) {
//			patchDecl.delete();
//		}
		patch.delete();
		super.destroyApplication();
	}

	@Override
	protected void createScene() {
		// Set ambient light
		IColourValue ambientLight = new ColourValue(0.2f, 0.2f, 0.2f, 1.0f);
		mSceneMgr.setAmbientLight(ambientLight);
		ambientLight.delete();

		// Create a point light
		ILight l = mSceneMgr.createLight("MainLight");
		// Accept default settings: point light, white diffuse, just set
		// position
		// NB I could attach the light to a SceneNode if I wanted it to move
		// automatically with
		// other objects, but I don't
		l.setType(LightTypes.LT_DIRECTIONAL);
		l.setDirection(-0.5f, -0.5f, 0.0f);

		// Create patch
		final int sizeof_float = 4;
		patchDecl = HardwareBufferManager.getSingleton()
				.createVertexDeclaration();
		patchDecl.addElement(0, 0, VertexElementType.VET_FLOAT3,
				VertexElementSemantic.VES_POSITION, 0);
		patchDecl.addElement(0, sizeof_float * 3, VertexElementType.VET_FLOAT3,
				VertexElementSemantic.VES_NORMAL, 0);
		patchDecl.addElement(0, sizeof_float * 6, VertexElementType.VET_FLOAT2,
				VertexElementSemantic.VES_TEXTURE_COORDINATES, 0);

		// Make a 3x3 patch for test
		patchCtlPoints = new PatchVertex[9];

		patchCtlPoints[0] = new PatchVertex();
		// Patch data
		PatchVertex pVert = patchCtlPoints[0];

		pVert.x = -500.0f;
		pVert.y = 200.0f;
		pVert.z = -500.0f;
		pVert.nx = -0.5f;
		pVert.ny = 0.5f;
		pVert.nz = 0.0f;
		pVert.u = 0.0f;
		pVert.v = 0.0f;
		patchCtlPoints[1] = new PatchVertex();
		pVert = patchCtlPoints[1];
		pVert.x = 0.0f;
		pVert.y = 500.0f;
		pVert.z = -750.0f;
		pVert.nx = 0.0f;
		pVert.ny = 0.5f;
		pVert.nz = 0.0f;
		pVert.u = 0.5f;
		pVert.v = 0.0f;
		patchCtlPoints[2] = new PatchVertex();
		pVert = patchCtlPoints[2];
		pVert.x = 500.0f;
		pVert.y = 1000.0f;
		pVert.z = -500.0f;
		pVert.nx = 0.5f;
		pVert.ny = 0.5f;
		pVert.nz = 0.0f;
		pVert.u = 1.0f;
		pVert.v = 0.0f;
		patchCtlPoints[3] = new PatchVertex();
		pVert = patchCtlPoints[3];

		pVert.x = -500.0f;
		pVert.y = 0.0f;
		pVert.z = 0.0f;
		pVert.nx = -0.5f;
		pVert.ny = 0.5f;
		pVert.nz = 0.0f;
		pVert.u = 0.0f;
		pVert.v = 0.5f;
		patchCtlPoints[4] = new PatchVertex();
		pVert = patchCtlPoints[4];
		pVert.x = 0.0f;
		pVert.y = 500.0f;
		pVert.z = 0.0f;
		pVert.nx = 0.0f;
		pVert.ny = 0.5f;
		pVert.nz = 0.0f;
		pVert.u = 0.5f;
		pVert.v = 0.5f;
		patchCtlPoints[5] = new PatchVertex();
		pVert = patchCtlPoints[5];
		pVert.x = 500.0f;
		pVert.y = -50.0f;
		pVert.z = 0.0f;
		pVert.nx = 0.5f;
		pVert.ny = 0.5f;
		pVert.nz = 0.0f;
		pVert.u = 1.0f;
		pVert.v = 0.5f;
		patchCtlPoints[6] = new PatchVertex();
		pVert = patchCtlPoints[6];

		pVert.x = -500.0f;
		pVert.y = 0.0f;
		pVert.z = 500.0f;
		pVert.nx = -0.5f;
		pVert.ny = 0.5f;
		pVert.nz = 0.0f;
		pVert.u = 0.0f;
		pVert.v = 1.0f;
		patchCtlPoints[7] = new PatchVertex();
		pVert = patchCtlPoints[7];
		pVert.x = 0.0f;
		pVert.y = 500.0f;
		pVert.z = 500.0f;
		pVert.nx = 0.0f;
		pVert.ny = 0.5f;
		pVert.nz = 0.0f;
		pVert.u = 0.5f;
		pVert.v = 1.0f;
		patchCtlPoints[8] = new PatchVertex();
		pVert = patchCtlPoints[8];
		pVert.x = 500.0f;
		pVert.y = 200.0f;
		pVert.z = 800.0f;
		pVert.nx = 0.5f;
		pVert.ny = 0.5f;
		pVert.nz = 0.0f;
		pVert.u = 1.0f;
		pVert.v = 1.0f;
		pVert = null;

		// Hack the PatchVertex into a native Buffer
		for (int i = 0; i < 9; i++) {
			buf.setIndex(i * 8, patchCtlPoints[i].x);
			buf.setIndex(i * 8 + 1, patchCtlPoints[i].y);
			buf.setIndex(i * 8 + 2, patchCtlPoints[i].z);
			buf.setIndex(i * 8 + 3, patchCtlPoints[i].nx);
			buf.setIndex(i * 8 + 4, patchCtlPoints[i].ny);
			buf.setIndex(i * 8 + 5, patchCtlPoints[i].nz);
			buf.setIndex(i * 8 + 6, patchCtlPoints[i].u);
			buf.setIndex(i * 8 + 7, patchCtlPoints[i].v);
		}

		MeshManager.getSingleton().createBezierPatch(patch, "Bezier1",
				ResourceGroupManager.getDEFAULT_RESOURCE_GROUP_NAME(),
				buf.getVoidPointer(), patchDecl, 3, 3, 5, 5,
				PatchSurface.VisibleSide.VS_BOTH,
				HardwareBuffer.Usage.HBU_STATIC_WRITE_ONLY,
				HardwareBuffer.Usage.HBU_DYNAMIC_WRITE_ONLY, true, true);

		// Start patch at 0 detail
		patch.get().setSubdivision(0.0f);
		// Create entity based on patch
		IEntity patchEntity = mSceneMgr.createEntity("Entity1", "Bezier1");

		// These are NULL a default
		ManualResourceLoader mRL = new ManualResourceLoader(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		NameValuePairList nVPL = new NameValuePairList(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);

		IResourcePtr pMat = new ResourcePtr(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);

		MaterialManager.getSingleton().create(pMat, "TextMat",
				ResourceGroupManager.getDEFAULT_RESOURCE_GROUP_NAME(), false,
				mRL, nVPL);
		// mRL.delete();
		// I know that this IResourcePtr is a Material
		Material mat = new Material(pMat.get().getInstancePointer());
		mat.getTechnique(0).getPass(0).createTextureUnitState("BumpyMetal.jpg",
				0);
		patchEntity.setMaterialName("TextMat");
		patchPass = mat.getTechnique(0).getPass(0);

		// delete Null objects
		mRL.delete();
		nVPL.delete();

		pMat.delete();

		// Attach the entity to the root of the scene
		mSceneMgr.getRootSceneNode().attachObject(patchEntity);

		mCamera.setPosition(500, 500, 1500);
		mCamera.lookAt(0, 200, -300);
	}

	@Override
	protected void destroyScene() {
		patch.setNull();
		buf.delete();
	}

	@Override
	protected void createFrameListener() {
		mFrameListener = new BezierListener(mWindow, mCamera, swtCanvas);
		((BezierListener) mFrameListener).setPatchPass(patchPass);
		((BezierListener) mFrameListener).setPatch(patch);
		this.addFrameListener(mFrameListener);
	}

	/**
	 * Run this demo.
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String[] args) {
		System.loadLibrary("ogre4j");
		BezierApplication bezierApplication = new BezierApplication();
		try {
			bezierApplication.go();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class PatchVertex {
	float x, y, z;
	float nx, ny, nz;
	float u, v;
};
