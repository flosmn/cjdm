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
package org.ogre4j.demos.swt;

import org.ogre4j.ColourValue;
import org.ogre4j.IColourValue;
import org.ogre4j.IEntity;
import org.ogre4j.ISceneNode;
import org.ogre4j.Quaternion;
import org.ogre4j.Vector3;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;

public class EmptyScene {

	public static void main(String[] args) throws Exception {
		EmptyApplication app = new EmptyApplication();
		app.go();
	}
}

class EmptyApplication extends ExampleApplication {

	@Override
	protected void createScene() {
		IColourValue ambientColour = new ColourValue(1.0f, 1.0f, 1.0f, 1.0f);
		mSceneMgr.setAmbientLight(ambientColour);
		ambientColour.delete();
		ISceneNode ogreNode = mSceneMgr.getRootSceneNode()
				.createChildSceneNode("ogreNode", Vector3.getZERO(),
						Quaternion.getIDENTITY());
		IEntity ogreHead = mSceneMgr.createEntity("ogreHead", "ogrehead.mesh");
		ogreNode.attachObject(ogreHead);
	}
}
