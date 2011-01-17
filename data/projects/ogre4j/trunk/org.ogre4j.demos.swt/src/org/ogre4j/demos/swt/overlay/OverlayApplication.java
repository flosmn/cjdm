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
package org.ogre4j.demos.swt.overlay;

import org.ogre4j.ColourValue;
import org.ogre4j.GuiMetricsMode;
import org.ogre4j.IColourValue;
import org.ogre4j.IEntity;
import org.ogre4j.IOverlay;
import org.ogre4j.IOverlayContainer;
import org.ogre4j.IOverlayElement;
import org.ogre4j.IOverlayManager;
import org.ogre4j.ISceneNode;
import org.ogre4j.ITextAreaOverlayElement;
import org.ogre4j.OverlayContainer;
import org.ogre4j.OverlayManager;
import org.ogre4j.Quaternion;
import org.ogre4j.TextAreaOverlayElement;
import org.ogre4j.UTFString;
import org.ogre4j.Vector3;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;
import org.xbig.base.InstancePointer;

public class OverlayApplication extends ExampleApplication {

	@Override
	protected void createScene() {
		// set ambient light
		IColourValue ambientColour = new ColourValue(1.0f, 1.0f, 1.0f, 1.0f);
		mSceneMgr.setAmbientLight(ambientColour);
		ambientColour.delete();

		// create an ogre head
		ISceneNode ogreNode = mSceneMgr.getRootSceneNode()
				.createChildSceneNode("ogreNode", Vector3.getZERO(),
						Quaternion.getIDENTITY());
		IEntity ogreHead = mSceneMgr.createEntity("ogreHead", "ogrehead.mesh");
		ogreNode.attachObject(ogreHead);

		// get OverlayManager
		IOverlayManager overlayManager = OverlayManager.getSingleton();

		// create container for strings
		IOverlayElement overlayElement = overlayManager
				.createOverlayElementFromFactory("Panel", "GUI");
		IOverlayContainer panel = new OverlayContainer(new InstancePointer(
				overlayElement.getInstancePointer().pointer));
		overlayElement.disconnectFromNativeObject();
		overlayElement = null;

		// configure string container
		panel.setMetricsMode(GuiMetricsMode.GMM_PIXELS);
		panel.setPosition(0.0f, 0.0f);
		panel.setDimensions(1.0f, 1.0f);

		// create the overlay which contains the panel
		IOverlay overlay = overlayManager.create("GUI_OVERLAY");
		overlay.add2D(panel);

		// create first string
		overlayElement = overlayManager.createOverlayElementFromFactory(
				"TextArea", "helloWorld");
		ITextAreaOverlayElement textArea = new TextAreaOverlayElement(
				new InstancePointer(overlayElement.getInstancePointer().pointer));
		overlayElement.disconnectFromNativeObject();
		overlayElement = null;

		// create second string
		overlayElement = overlayManager.createOverlayElementFromFactory(
				"TextArea", "ogre4j.org");
		ITextAreaOverlayElement textAreaWeb = new TextAreaOverlayElement(
				new InstancePointer(overlayElement.getInstancePointer().pointer));
		overlayElement.disconnectFromNativeObject();
		overlayElement = null;

		// create third string
		overlayElement = overlayManager.createOverlayElementFromFactory(
				"TextArea", "rule");
		ITextAreaOverlayElement textAreaRule = new TextAreaOverlayElement(
				new InstancePointer(overlayElement.getInstancePointer().pointer));
		overlayElement.disconnectFromNativeObject();
		overlayElement = null;

		// add strings to container
		panel.addChild(textArea);
		panel.addChild(textAreaWeb);
		panel.addChild(textAreaRule);
		overlay.show();

		// set fist string
		UTFString utfStringHello = new UTFString("HELLO ogre4j WORLD");
		textArea.setCaption(utfStringHello);
		utfStringHello.delete();
		textArea.setDimensions(1.0f, 1.0f);
		textArea.setMetricsMode(GuiMetricsMode.GMM_PIXELS);
		textArea.setFontName("StarWars");
		textArea.setCharHeight(24);
		textArea.setPosition(325f, 200f);
		textArea.setColour(ColourValue.getBlue());

		// set second string
		UTFString utfStringWeb = new UTFString("http://ogre4j.org");
		textAreaWeb.setCaption(utfStringWeb);
		utfStringWeb.delete();
		textAreaWeb.setDimensions(1.0f, 1.0f);
		textAreaWeb.setMetricsMode(GuiMetricsMode.GMM_PIXELS);
		textAreaWeb.setFontName("BlueHighway");
		textAreaWeb.setCharHeight(20);
		textAreaWeb.setPosition(70f, 50f);
		textAreaWeb.setColour(ColourValue.getRed());

		// set third string
		UTFString utfStringRule = new UTFString("overlays rule !!!");
		textAreaRule.setCaption(utfStringRule);
		utfStringRule.delete();
		textAreaRule.setDimensions(1.0f, 1.0f);
		textAreaRule.setMetricsMode(GuiMetricsMode.GMM_PIXELS);
		textAreaRule.setFontName("BlueHighway");
		textAreaRule.setCharHeight(32);
		textAreaRule.setPosition(600f, 500f);
		textAreaRule.setColour(ColourValue.getGreen());
	}

	protected void destroyScene() {
		IOverlayManager overlayManager = OverlayManager.getSingleton();
		overlayManager.destroyAllOverlayElements(false);
		overlayManager.destroy("GUI_OVERLAY");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		OverlayApplication app = new OverlayApplication();
		app.go();
	}

}
