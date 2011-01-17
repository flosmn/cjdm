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
package org.ogre4j.demos.swt.log;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.ogre4j.ColourValue;
import org.ogre4j.IColourValue;
import org.ogre4j.IEntity;
import org.ogre4j.INameValuePairList;
import org.ogre4j.IRenderSystemList;
import org.ogre4j.ISceneNode;
import org.ogre4j.LogManager;
import org.ogre4j.LogMessageLevel;
import org.ogre4j.NameValuePairList;
import org.ogre4j.Quaternion;
import org.ogre4j.StringUtil;
import org.ogre4j.Vector3;
import org.ogre4j.demos.swt.exampleapp.ExampleApplication;
import org.ogre4j.nativelisteners.NativeLogListener;
import org.xbig.base.StringPointer;

public class LogApplication extends ExampleApplication {

	class JavaLogListener extends NativeLogListener {
		public void messageLogged(String message, LogMessageLevel lml,
				boolean maskDebug, String logName) {
			if (loggingTextBox != null && !loggingTextBox.isDisposed()) {
				loggingTextBox.append(message);
				loggingTextBox.append("\n");
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		LogApplication app = new LogApplication();
		app.go();
	}

	private Text loggingTextBox = null;

	private JavaLogListener logListener = null;

	private LogManager logManager;

	@Override
	protected boolean configure() throws Exception {
		final int renderSystemToUse = 0;

		// XXX SWT initialization is done in setup()

		// set render system
		IRenderSystemList list = mRoot.getAvailableRenderers();
		if (list == null || list.size() == 0) {
			throw new Exception("No RenderSystem loaded!");
		}
		mRoot.setRenderSystem(list.at(renderSystemToUse));

		// initialise Ogre Root
		// its ok to get a null pointer exception here
		// ogre doesnt create a new IRenderWindow
		try {
			mRoot.initialise(false, "", StringUtil.getBLANK());
		} catch (NullPointerException e) {
			// OK, method returns null
		}

		// create RenderWindow with SWT handle
		INameValuePairList params = new NameValuePairList();
		StringPointer windowHandlePointer = new StringPointer(Integer
				.toString(swtCanvas.handle));
		params.insert("externalWindowHandle", windowHandlePointer);
		mWindow = mRoot.createRenderWindow("", swtCanvas.getBounds().width,
				swtCanvas.getBounds().height, false, params);
		params.delete();
		windowHandlePointer.delete();

		return true;
	}

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
	}

	@Override
	protected void destroyApplication() {
		logManager.getDefaultLog().removeListener(logListener);
		logListener.delete();
		super.destroyApplication();
	}

	@Override
	protected boolean setup() throws Exception {
		final int width = 1024;
		final int height = 768;

		// SWT initialization
		swtDisplay = new Display();
		swtShell = new Shell(swtDisplay);
		swtShell.setText("ogre4j Render Window");
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		swtShell.setLayout(fillLayout);
		swtShell.setSize(width, height);
		swtCanvas = new Canvas(swtShell, SWT.EMBEDDED);

		// XXX create additional box for logging
		loggingTextBox = new Text(swtShell, SWT.MULTI | SWT.READ_ONLY
				| SWT.V_SCROLL | SWT.H_SCROLL);
		swtShell.open();

		logManager = new LogManager();
		logManager.createLog("JavaLog", true, false, true);
		logListener = new JavaLogListener();
		logManager.getDefaultLog().addListener(logListener);

		return super.setup();
	}

}
