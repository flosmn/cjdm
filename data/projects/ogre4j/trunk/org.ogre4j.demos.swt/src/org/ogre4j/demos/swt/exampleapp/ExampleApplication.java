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
package org.ogre4j.demos.swt.exampleapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.opengl.GLData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.ini4j.Ini;
import org.ini4j.Ini.Section;
import org.ogre4j.ColourValue;
import org.ogre4j.ICamera;
import org.ogre4j.IColourValue;
import org.ogre4j.INameValuePairList;
import org.ogre4j.IRenderSystemList;
import org.ogre4j.IRenderWindow;
import org.ogre4j.IRoot;
import org.ogre4j.ISceneManager;
import org.ogre4j.IVector3;
import org.ogre4j.IViewport;
import org.ogre4j.NameValuePairList;
import org.ogre4j.ResourceGroupManager;
import org.ogre4j.Root;
import org.ogre4j.SceneType;
import org.ogre4j.StringUtil;
import org.ogre4j.TextureManager;
import org.ogre4j.Vector3;
import org.xbig.base.StringPointer;

/**
 * Imitates the C++ Ogre ExampleApplication. Intended to be subclassed for demo
 * applications.
 * 
 * As we have no CallBack over JNI it rebuilds the RenderLoop and FrameEvents of
 * Ogre::Root.
 * 
 */
public abstract class ExampleApplication {

	/**
	 * Time of last frame. Needed to create FrameEvents.
	 */
	private long lastTime = 0;

	/**
	 * Time of last frame started event.
	 */
	private long lastTimeStarted = 0;

	/**
	 * Time of last frame ended event.
	 */
	private long lastTimeEnded = 0;

	/**
	 * Ogre root.
	 */
	protected IRoot mRoot;

	/**
	 * Default camera.
	 */
	protected ICamera mCamera;

	/**
	 * SceneManager to be used.
	 */
	protected ISceneManager mSceneMgr;

	/**
	 * RenderWindow to be used.
	 */
	protected IRenderWindow mWindow;

	/**
	 * SWT Display.
	 */
	protected Display swtDisplay;

	/**
	 * SWT Shell.
	 */
	protected Shell swtShell;

	/**
	 * SWT Canvas. It's handle is used to create an Ogre RenderWindow
	 */
	protected Canvas swtCanvas;

	/**
	 * ExampleFrameListener to be used.
	 */
	protected ExampleFrameListener mFrameListener;

	/**
	 * Contains registered FrameListeners.
	 */
	private Vector<IFrameListener> frameListeners = new Vector<IFrameListener>();

	/**
	 * Default constructor. Does nothing.
	 */
	public ExampleApplication() {

	}

	/**
	 * Start the example.
	 * 
	 * @throws Exception
	 *             On errors during setup.
	 */
	public void go() throws Exception {
		if (!setup()) {
			destroyApplication();
			return;
		}

		this.startRendering();

		// clean up
		destroyScene();
		destroyApplication();
	}

	/**
	 * Imitates Ogre::Root::startRendering.
	 * @throws InterruptedException 
	 * 
	 */
	private void startRendering() throws InterruptedException {
		// render first frame
		mRoot.renderOneFrame();

		while (!swtShell.isDisposed()) {
			if (!swtDisplay.readAndDispatch()) {
				// burn CPU
				//swtDisplay.sleep();
				// TODO render while sleeping
			}
			//avoid that timeSinceLastFrame becomes 0
			if (lastTime < System.currentTimeMillis())
			{			
				if (!fireFrameStarted()) {
					break;
				}
				
				if (!mRoot.renderOneFrame()) {
					break;
				}
	
				if (!fireFrameEnded()) {
					break;
				}
			}
		}
	}
	

	/**
	 * Used as replacement for a C++ destructor.
	 */
	protected void destroyApplication() {
		if (swtDisplay != null) {
			swtDisplay.dispose();
		}
		mRoot.delete();
	}

	/**
	 * Sets up the application - returns false if the user chooses to abandon
	 * configuration.
	 * 
	 * @return false if user cancels configuration.
	 * @throws Exception
	 *             On errors during resource loading or configuration.
	 */
	protected boolean setup() throws Exception {
		String pluginsPath;
		pluginsPath = "plugins.cfg";

		mRoot = new Root(pluginsPath, "ogre.cfg", "ogre4j.log");

		setupResources();

		boolean carryOn = configure();
		if (!carryOn)
			return false;

		chooseSceneManager();
		createCamera();
		createViewports();

		// Set default mipmap level (NB some APIs ignore this)
		TextureManager.getSingleton().setDefaultNumMipmaps(5);

		// Create any resource listeners (for loading screens)
		createResourceListener();
		// Load resources
		loadResources();

		// Create the scene
		createScene();

		createFrameListener();

		return true;

	}

	/**
	 * Configures the application - returns false if the user chooses to abandon
	 * configuration.
	 * 
	 * @return false if user cancels.
	 * @throws Exception
	 *             If now RenderSystem can be loaded.
	 */
	protected boolean configure() throws Exception {
		// TODO directx dies sometimes at shutdown
		// TODO sometimes we get a weird JNI error at the very end of the app.
		// (Maybe a Java 6 issue.)

		// TODO config dialog
		final int width = 1024;
		final int height = 768;
		final int renderSystemToUse = 0;

		// SWT initialization
		swtDisplay = new Display();
		swtShell = new Shell(swtDisplay);
		swtShell.setText("ogre4j Render Window");
		swtShell.setLayout(new FillLayout());
		swtShell.setSize(width, height);

        String operatingSystem = System.getProperty("os.name").toLowerCase();
        if (operatingSystem.equals("linux")) {
	        GLData data = new GLData ();
	        data.doubleBuffer = true;
	        swtCanvas = new GLCanvas(swtShell, SWT.NONE, data); 
	        ((GLCanvas)swtCanvas).setCurrent();
        } else {
        	swtCanvas = new Canvas(swtShell, SWT.EMBEDDED);
        }

        swtShell.open();

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
        StringPointer windowHandlePointer;
        if (operatingSystem.equals("linux")) {
    		windowHandlePointer = new StringPointer("true");
    		params.insert("currentGLContext", windowHandlePointer);
        } else {
    		windowHandlePointer = new StringPointer(Integer.toString(swtCanvas.handle));
    		params.insert("externalWindowHandle", windowHandlePointer);
        }

		mWindow = mRoot.createRenderWindow("", swtCanvas.getBounds().width,
				swtCanvas.getBounds().height, false, params);
		params.delete();
		windowHandlePointer.delete();

		return true;
	}

	/**
	 * Creates a generic SceneManager. May be overridden.
	 */
	protected void chooseSceneManager() {
		// Create the SceneManager, in this case a generic one
		mSceneMgr = mRoot.createSceneManager(SceneType.ST_GENERIC.getValue(),
				"ExampleSMInstance");
	}

	/**
	 * Sets up the default camera. May be overridden.
	 */
	protected void createCamera() {
		// Create the camera
		mCamera = mSceneMgr.createCamera("PlayerCam");

		IVector3 vec = new Vector3(0, 0, 500);

		// Position it at 500 in Z direction
		mCamera.setPosition(vec);

		// Look back along -Z
		vec.setz(-300);
		mCamera.lookAt(vec);
		mCamera.setNearClipDistance(5);

		vec.delete();
	}

	/**
	 * Creates the default ExampleFrameListener and shows it's DebugOverlay. May
	 * be overridden.
	 */
	protected void createFrameListener() {
		mFrameListener = new ExampleFrameListener(mWindow, mCamera, swtCanvas);
		mFrameListener.showDebugOverlay(true);
		this.addFrameListener(mFrameListener);
	}

	/**
	 * This has to be overridden.
	 */
	abstract protected void createScene();

	/**
	 * Optional to override this.
	 */
	protected void destroyScene() {
	}

	/**
	 * Creates the default Viewport. May be overridden.
	 */
	protected void createViewports() {
		// Create one viewport, entire window
		IViewport vp = mWindow.addViewport(mCamera, 0, 0.0f, 0.0f, 1.0f, 1.0f);

		IColourValue bgCol = new ColourValue(0.0f, 0.0f, 0.0f, 1.0f);
		vp.setBackgroundColour(bgCol);
		bgCol.delete();

		// Alter the camera aspect ratio to match the viewport
		mCamera.setAspectRatio((float) vp.getActualWidth()
				/ (float) vp.getActualHeight());
	}

	/**
	 * Method which will define the source of resources (other than current
	 * folder).
	 * 
	 * @throws IOException
	 *             If file "resources.cfg" cannot be loaded.
	 */
	protected void setupResources() throws IOException {
		Ini resourcesIni = new Ini(new FileInputStream(
				new File("resources.cfg")));
		Set<Entry<String, Section>> sections = resourcesIni.entrySet();
		for (Entry<String, Section> section : sections) {
			Set<Entry<String, String>> entries = section.getValue().entrySet();
			for (Entry<String, String> i : entries) {
				// in resources.cfg are values unique, not keys
				// this is why we have changed ini4j
				ResourceGroupManager.getSingleton().addResourceLocation(
						i.getKey(), i.getValue(), section.getKey(), false);
			}
		}
	}

	/**
	 * Optional override method where you can create resource listeners (e.g.
	 * for loading screens).
	 */
	protected void createResourceListener() {
	}

	/**
	 * Optional override method where you can perform resource group loading.
	 * Must at least do <code>
	 * ResourceGroupManager.getSingleton().initialiseAllResourceGroups();
	 * </code>
	 */
	protected void loadResources() {
		// Initialize, parse scripts etc
		ResourceGroupManager.getSingleton().initialiseAllResourceGroups();
	}

	/**
	 * Tell FrameListeners that a frame has started. Calculates time since last
	 * frame and time since last event.
	 * 
	 * @return if rendering shall continue.
	 */
	private boolean fireFrameStarted() {
		// TODO check time calculation
		long time = System.currentTimeMillis();
		FrameEvent evt = new FrameEvent();
		if (lastTime != 0) {
			evt.timeSinceLastEvent = (time - lastTime) / (float) 1000;
		}
		if (lastTimeStarted != 0) {
			evt.timeSinceLastFrame = (time - lastTimeStarted) / (float) 1000;
		}
		lastTime = time;
		lastTimeStarted = time;

		boolean retVal = true;
		for (IFrameListener frameListener : frameListeners) {
			if (!frameListener.frameStarted(evt)) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}

	/**
	 * Tell FrameListeners that a frame has ended. Calculates time since last
	 * frame and time since last event.
	 * 
	 * @return if rendering shall continue.
	 */
	private boolean fireFrameEnded() {
		// TODO check time calculation
		long time = System.currentTimeMillis();
		FrameEvent evt = new FrameEvent();
		if (lastTime != 0) {
			evt.timeSinceLastEvent = (time - lastTime) / 1000;
		}
		if (lastTimeEnded != 0) {
			evt.timeSinceLastFrame = (time - lastTimeEnded) / 1000;
		}
		lastTime = time;
		lastTimeEnded = time;

		boolean retVal = true;
		for (IFrameListener frameListener : frameListeners) {
			if (!frameListener.frameEnded(evt)) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}

	/**
	 * Puts another FrameListener in the list of FrameListeners to be notified
	 * when a frame starts or ends.
	 * 
	 * @param fl
	 *            The FrameListener to add.
	 */
	public void addFrameListener(IFrameListener fl) {
		if (!frameListeners.contains(fl)) {
			frameListeners.add(fl);
		}
	}

	/**
	 * Removes a FrameListner from the list of FrameListeners to be notified
	 * when a frame starts or ends.
	 * 
	 * @param fl
	 *            The FrameListener to remove.
	 */
	public void removeFrameListener(IFrameListener fl) {
		if (frameListeners.contains(fl)) {
			frameListeners.remove(fl);
		}
	}
}
