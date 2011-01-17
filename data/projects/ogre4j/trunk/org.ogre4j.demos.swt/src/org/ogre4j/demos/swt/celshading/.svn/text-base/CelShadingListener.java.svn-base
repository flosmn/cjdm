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

import org.eclipse.swt.widgets.Canvas;
import org.ogre4j.Degree;
import org.ogre4j.ICamera;
import org.ogre4j.IDegree;
import org.ogre4j.IRadian;
import org.ogre4j.IRenderWindow;
import org.ogre4j.Radian;
import org.ogre4j.Node.TransformSpace;
import org.ogre4j.demos.swt.exampleapp.ExampleFrameListener;
import org.ogre4j.demos.swt.exampleapp.FrameEvent;

public class CelShadingListener extends ExampleFrameListener {

	public CelShadingListener(IRenderWindow win, ICamera cam, Canvas swtCanvas) {
		super(win, cam, swtCanvas);
	}

	public boolean frameStarted(FrameEvent evt) {
		if (super.frameStarted(evt) == false)
			return false;

		IDegree deg = new Degree(evt.timeSinceLastEvent * 30);
		IRadian rad = new Radian(deg);
		CelShadingApplication.rotNode.yaw(rad, TransformSpace.TS_LOCAL);
		rad.delete();
		deg.delete();
		return true;
	}

}
