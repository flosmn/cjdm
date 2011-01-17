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
package org.ogre4j.demos.swt.celshading;

import org.ogre4j.Capabilities;
import org.ogre4j.ColourValue;
import org.ogre4j.Entity;
import org.ogre4j.Light;
import org.ogre4j.Quaternion;
import org.ogre4j.RenderSystemCapabilities;
import org.ogre4j.Root;
import org.ogre4j.SceneNode;
import org.ogre4j.SubEntity;
import org.ogre4j.Vector3;
import org.ogre4j.Vector4;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;

public class CelShadingApplication extends ExampleApplication {

	public static SceneNode rotNode;

	public static int CUSTOM_SHININESS = 1;
	public static int CUSTOM_DIFFUSE = 2;
	public static int CUSTOM_SPECULAR = 3;

	protected void createFrameListener() {
		// This is where we instantiate our own frame listener
		mFrameListener = new CelShadingListener(mWindow, mCamera, swtCanvas);
		this.addFrameListener(mFrameListener);

	}

	// Just override the mandatory create scene method
	protected void createScene() {
		// Check capabilities
		RenderSystemCapabilities caps = (RenderSystemCapabilities) Root
				.getSingleton().getRenderSystem().getCapabilities();
		if (!caps.hasCapability(Capabilities.RSC_VERTEX_PROGRAM)
				|| !(caps.hasCapability(Capabilities.RSC_FRAGMENT_PROGRAM))) {

		}

		// Create a point light
		Light l = (Light) mSceneMgr.createLight("MainLight");
		// Accept default settings: point light, white diffuse, just set
		// position
		// Add light to the scene node
		rotNode = (SceneNode) mSceneMgr.getRootSceneNode()
				.createChildSceneNode(Vector3.getZERO(),
						Quaternion.getIDENTITY());

		Vector3 vec3 = new Vector3(20, 40, 50);
		rotNode.createChildSceneNode(vec3, Quaternion.getIDENTITY())
				.attachObject(l);
		vec3.delete();

		Entity ent = (Entity) mSceneMgr.createEntity("head", "ogrehead.mesh");

		mCamera.setPosition(20, 0, 100);
		mCamera.lookAt(0, 0, 0);

		// Set common material, but define custom parameters to change colours
		// See Example-Advanced.material for how these are finally bound to GPU
		// parameters
		SubEntity sub;
		// eyes
		sub = (SubEntity) ent.getSubEntity(0);
		sub.setMaterialName("Examples/CelShading");

		Vector4 vec4 = new Vector4(35f, 0f, 0f, 0f);
		sub.setCustomParameter(CUSTOM_SHININESS, vec4);
		vec4.delete();

		vec4 = new Vector4(1f, .3f, .3f, 1f);
		sub.setCustomParameter(CUSTOM_DIFFUSE, vec4);
		vec4.delete();

		vec4 = new Vector4(1.0f, 0.6f, 0.6f, 1.0f);
		sub.setCustomParameter(CUSTOM_SPECULAR, vec4);
		vec4.delete();

		// skin
		sub = (SubEntity) ent.getSubEntity(1);
		sub.setMaterialName("Examples/CelShading");

		vec4 = new Vector4(10.0f, 0.0f, 0.0f, 0.0f);
		sub.setCustomParameter(CUSTOM_SHININESS, vec4);
		vec4.delete();

		vec4 = new Vector4(0.0f, 0.5f, 0.0f, 1.0f);
		sub.setCustomParameter(CUSTOM_DIFFUSE, vec4);
		vec4.delete();

		vec4 = new Vector4(0.3f, 0.5f, 0.3f, 1.0f);
		sub.setCustomParameter(CUSTOM_SPECULAR, vec4);
		vec4.delete();

		// earring
		sub = (SubEntity) ent.getSubEntity(2);
		sub.setMaterialName("Examples/CelShading");

		vec4 = new Vector4(25.0f, 0.0f, 0.0f, 0.0f);
		sub.setCustomParameter(CUSTOM_SHININESS, vec4);
		vec4.delete();

		vec4 = new Vector4(1.0f, 1.0f, 0.0f, 1.0f);
		sub.setCustomParameter(CUSTOM_DIFFUSE, vec4);
		vec4.delete();

		vec4 = new Vector4(1.0f, 1.0f, 0.7f, 1.0f);
		sub.setCustomParameter(CUSTOM_SPECULAR, vec4);
		vec4.delete();

		// teeth
		sub = (SubEntity) ent.getSubEntity(3);
		sub.setMaterialName("Examples/CelShading");

		vec4 = new Vector4(20.0f, 0.0f, 0.0f, 0.0f);
		sub.setCustomParameter(CUSTOM_SHININESS, vec4);
		vec4.delete();

		vec4 = new Vector4(1.0f, 1.0f, 0.7f, 1.0f);
		sub.setCustomParameter(CUSTOM_DIFFUSE, vec4);
		vec4.delete();

		vec4 = new Vector4(1.0f, 1.0f, 1.0f, 1.0f);
		sub.setCustomParameter(CUSTOM_SPECULAR, vec4);
		vec4.delete();

		// Add entity to the root scene node
		mSceneMgr.getRootSceneNode().createChildSceneNode(Vector3.getZERO(),
				Quaternion.getIDENTITY()).attachObject(ent);

		mWindow.getViewport(0).setBackgroundColour(ColourValue.getWhite());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CelShadingApplication app = new CelShadingApplication();
		try {
			app.go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
