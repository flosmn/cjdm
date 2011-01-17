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

import java.util.Iterator;

import org.eclipse.swt.widgets.Canvas;
import org.ogre4j.IAnimationState;
import org.ogre4j.ICamera;
import org.ogre4j.IRenderWindow;
import org.ogre4j.ISceneNode;
import org.ogre4j.demos.swt.exampleapp.ExampleFrameListener;

// Listener class for frame updates
public class LightingListener extends ExampleFrameListener {
	public ISceneNode mRedYellowLightsNode;
	public ISceneNode mGreenBlueLightsNode;

	public LightingListener(IRenderWindow win, ICamera cam, Canvas swtCanvas) {
		super(win, cam, swtCanvas);
	}

	public boolean frameStarted(org.ogre4j.demos.swt.exampleapp.FrameEvent evt) {
		if (super.frameStarted(evt) == false)
			return false;
		Iterator<IAnimationState> animi;
		animi = LightingApplication.mAnimStateList.iterator();
		while (animi.hasNext()) {
			animi.next().addTime(evt.timeSinceLastEvent);
		}

		return true;
	}
}
