/**
 * Copyright (c) 2005-2008 NetAllied Systems GmbH, Tettnang
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
package org.ogre4j.examples.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.opengl.GLData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.ogre4j.ColourValue;
import org.ogre4j.HardwareBuffer;
import org.ogre4j.ICamera;
import org.ogre4j.IColourValue;
import org.ogre4j.IEntity;
import org.ogre4j.ILight;
import org.ogre4j.IMeshManager;
import org.ogre4j.IMeshPtr;
import org.ogre4j.INameValuePairList;
import org.ogre4j.IRadian;
import org.ogre4j.IRay;
import org.ogre4j.IRaySceneQuery;
import org.ogre4j.IRaySceneQueryResult;
import org.ogre4j.IRaySceneQueryResultEntry;
import org.ogre4j.IRenderSystemList;
import org.ogre4j.IRenderWindow;
import org.ogre4j.IRoot;
import org.ogre4j.ISceneManager;
import org.ogre4j.ISceneNode;
import org.ogre4j.IVector3;
import org.ogre4j.IViewport;
import org.ogre4j.MeshManager;
import org.ogre4j.MeshPtr;
import org.ogre4j.NameValuePairList;
import org.ogre4j.Plane;
import org.ogre4j.Quaternion;
import org.ogre4j.Radian;
import org.ogre4j.Ray;
import org.ogre4j.ResourceGroupManager;
import org.ogre4j.Root;
import org.ogre4j.StringUtil;
import org.ogre4j.Vector3;
import org.ogre4j.Light.LightTypes;
import org.xbig.base.StringPointer;
import org.xbig.base.WithoutNativeObject;

/**
 * Simple ogre4j program. Creates plain OGRE objects to build a small scene. The
 * scene contains one light and one mesh.
 * 
 */
public final class HelloWorld {

    /**
     * Default window width.
     */
    private static final int DEFAULT_WIDTH = 640;

    /**
     * Default window height.
     */
    private static final int DEFAULT_HEIGHT = 480;

    /**
     * Main function.
     * 
     * @param args
     *            commandline arguments.
     */
    public static void main(String[] args) {

        new HelloWorld();
    }

    /**
     * The main for java webstart.
     * 
     * @param locPath
     *            The OGRE resource location path.
     * @param mediatype
     *            The OGRE resource location type.
     */
    public static void mainws(String locPath, String locType, String renderSystem) {
        // force loading of dlls to get this running as webstart.
        // cpp2j-base is now compiled into ogre4j
    	//System.loadLibrary("cpp2j-base");
        System.loadLibrary("OgreMain");
        if ("Direct3D9".equals(renderSystem)) {
            System.loadLibrary("RenderSystem_Direct3D9");
        } else {
            System.loadLibrary("RenderSystem_GL");
        }
        new HelloWorld(locPath, locType, renderSystem);
        
        // something blocks the exit of the app. force exit.
        System.exit(0);
    }

    /**
     * Ogre Root.
     */
    private IRoot root;

    /**
     * Main Camera.
     */
    private ICamera camera;

    /**
     * Ogre RenderWindow object. Uses handle of ogreCanvas.
     */
    private IRenderWindow renderWindow;

    /**
     * Set to true after exiting event loop. To avoid repaint after disposing of
     * canvas.
     */
    private boolean isDisposing = false;

    /**
     * The SWT display.
     */
    private Display display;

    /**
     * The SWT shell.
     */
    private Shell shell;

    /**
     * The SWT canvas to render.
     */
    private Canvas canvas;

    /**
     * The OGRE SceneManager.
     */
    private ISceneManager sceneManager;

    /**
     * Default constructor. Sets media to "media" directory.
     */
    public HelloWorld() {
        this("media", "FileSystem", "OpenGL");
    }

    /**
     * This constructor is the main program.
     * 
     * @param media
     *            The OGRE resource location.
     * @param locType
     *            The OGRE resource location type.
     * @param renderSystem
     */
    private HelloWorld(String media, String locType, String renderSystem) {

        try {
            setup(media, locType, renderSystem);

            // enter event loop
            while (!shell.isDisposed()) {
                if (!display.readAndDispatch()) {
                    display.sleep();
                }
            }

            cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    /**
     * Shuts down the app.
     */
    private void cleanup() {
        // clean up
        isDisposing = true;

        display.dispose();
        root.delete();
    }

    /**
     * Creates a plane.
     */
    private void createPlane() {
        Plane plane = new Plane(Vector3.getUNIT_Y(), 0);
    	IMeshPtr meshPtr = new MeshPtr(WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
        IMeshManager meshMgr = MeshManager.getSingleton();
        meshMgr.createPlane(meshPtr, "ground", ResourceGroupManager.getDEFAULT_RESOURCE_GROUP_NAME(), plane,
                15f, 15f, 20, 20, true, 1, 5, 5, Vector3.getUNIT_Z(), HardwareBuffer.Usage.HBU_STATIC_WRITE_ONLY,
                HardwareBuffer.Usage.HBU_STATIC_WRITE_ONLY, true, true);
        plane.delete();
        meshPtr.delete();

        IEntity entity = sceneManager.createEntity("GroundEntity", "ground");

        ISceneNode node = sceneManager.getRootSceneNode().createChildSceneNode("GroundEntityNode",
        		Vector3.getZERO(), Quaternion.getIDENTITY());
        node.attachObject(entity);
    }

    /**
     * Sets up the app.
     * 
     * @param media
     *            The path to the media (resourcelocation).
     * @param locType
     *            The OGRE type of the resource location.
     * @param renderSystem
     * 
     * @throws Exception
     *             if an error occurs while initilaising OGRE.
     */
    private void setup(String media, String locType, String renderSystem) throws Exception {
        // create Ogre Root
       	root = new Root("", "", this.getClass().getName() + ".log");

        // SWT initialization
        display = new Display();
        shell = new Shell(display);
        shell.setText("Hello World");
        shell.setLayout(new FillLayout());
        shell.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        String operatingSystem = System.getProperty("os.name").toLowerCase();
        if (operatingSystem.equals("linux")) {
	        GLData data = new GLData ();
	        data.doubleBuffer = true;
	        canvas = new GLCanvas(shell, SWT.NONE, data);
	        ((GLCanvas)canvas).setCurrent();
        } else {
        	canvas = new Canvas(shell, SWT.EMBEDDED);
        }

        shell.open();

        // load render system
        if ("Direct3D9".equals(renderSystem)) {
            root.loadPlugin("RenderSystem_Direct3D9");
        } else {
            root.loadPlugin("RenderSystem_GL");
        }

        // set render system
        IRenderSystemList list = root.getAvailableRenderers();
        if (list == null || list.size() == 0) {
            throw new Exception("No RenderSystem loaded!");
        }
        root.setRenderSystem(list.at(0));

        // initialise Ogre Root
        // its ok to get a null pointer exception here
        // ogre doesnt create a new IRenderWindow
        try {
            root.initialise(false, "", StringUtil.getBLANK());
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
    		windowHandlePointer = new StringPointer(Integer.toString(canvas.handle));
    		params.insert("externalWindowHandle", windowHandlePointer);
        }
        renderWindow = root.createRenderWindow("", canvas.getBounds().width, canvas.getBounds().height, false, params);
        params.delete();
        windowHandlePointer.delete();

        // create scene manager
        sceneManager = root.createSceneManager(0, "Default");
        IColourValue ambientColour = new ColourValue(0.1f, 0.1f, 0.1f, 0);
        sceneManager.setAmbientLight(ambientColour);
        ambientColour.delete();

        // create light
        IColourValue lightColour = new ColourValue(0.7f, 0.7f, 0.7f, 0);
        ILight light = sceneManager.createLight("lightName");
        light.setDiffuseColour(lightColour);
        light.setSpecularColour(lightColour);
        lightColour.delete();
        light.setType(LightTypes.LT_DIRECTIONAL);
        light.setDirection(0, -0.5f, -1);
        light.setPosition(0, 50, 100);
        light.setVisible(true);

        // create camera
        ISceneNode rootSceneNode = sceneManager.getRootSceneNode();
        camera = sceneManager.createCamera("Main Camera");
        camera.setPosition(0, 10, 20);
        camera.lookAt(0, 0, 0);
        camera.setNearClipDistance(0.1f);

        // create viewport
        IViewport viewport = renderWindow.addViewport(camera, 0, 0.0f, 0.0f, 1.0f, 1.0f);
        IColourValue backgroundColour = new ColourValue(0.3f, 0.3f, 0.3f, 1.0f);
        viewport.setBackgroundColour(backgroundColour);
        backgroundColour.delete();
        viewport.setOverlaysEnabled(false);
        viewport.setCamera(camera);
        camera.setAspectRatio((float) canvas.getBounds().width / (float) canvas.getBounds().height);

        // connect camera with render window
        renderWindow.setActive(true);

        // load resources
        ResourceGroupManager.getSingleton().addResourceLocation(media, locType, "General", false);
        ResourceGroupManager.getSingleton().initialiseAllResourceGroups();

        // create entity
        IEntity entity = sceneManager.createEntity("cake", "zuh.mesh");
        ISceneNode cakeNode = rootSceneNode.createChildSceneNode(Vector3.getZERO(), Quaternion.getIDENTITY());
        cakeNode.attachObject(entity);
        cakeNode.setPosition(-3.5f, 0, 0);

        // create a plane as ground
        createPlane();

        // enable keyboard
        Navigator navigator = new Navigator();
        navigator.setCamera(camera);
        navigator.setCanvas(canvas);
        navigator.setSceneManager(sceneManager);

        // react to paint events
        canvas.addPaintListener(new PaintListener() {

            public void paintControl(PaintEvent e) {
                int height = canvas.getBounds().height;
                int width = canvas.getBounds().width;

        		// circumvent a directx bug. directx crashed
                if (height == 0) {
                    height = 1;
                }
                if (width == 0) {
                    width = 1;
                }

                // redraw the ogre window
                if (isDisposing == true || camera == null || renderWindow == null) {
                    return;
                }

                camera.setAspectRatio((float) width / (float) height);
                renderWindow.windowMovedOrResized();
                root.renderOneFrame();
            }

        });

        // last but not least render first frame
        root.renderOneFrame();
    }
}

/**
 * This class allows navigation through a 3D scene. It needs a canvas to get
 * input events and a camera to move. Keys for movement are 'A', 'S', 'D', 'W',
 * 'C' and 'Space', the mouse is used to rotate the camera when the left 
 * button is pressed.
 * 
 */
class Navigator implements KeyListener, MouseListener, MouseMoveListener {

    /**
     * Canvas to listen to input events.
     */
    private Canvas canvas;

    /**
     * Camera to move.
     */
    private ICamera camera;

    /**
     * The OGRE SceneManager. Needed for SceneQueries.
     */
    private ISceneManager sceneManager;

    /**
     * Indicates if the camera should be rotated by the mouse.
     */
	private boolean mouseLook;

	/**
	 * Stores X coordinate of last mouse position.
	 * Used for mouse look.
	 */
	private int lastX;

	/**
	 * Stores Y coordinate of last mouse position.
	 * Used for mouse look.
	 */
	private int lastY;

	/**
     * Returns the camera that is moved by this Navigator.
     * 
     * @return Camera to move.
     */
    public ICamera getCamera() {
        return camera;
    }

    /**
     * Returns the Canvas that is lisented to.
     * 
     * @return Canvas to listen to input events.
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Returns the SceneManager that is used for scene queries.
     * 
     * @return SceneManager used for scene queries.
     */
    public ISceneManager getSceneManager() {
        return sceneManager;
    }

    /**
     * @{inheritdoc}
     * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
     */
    public void keyPressed(KeyEvent e) {
        IVector3 moveVec = new Vector3(0.0f, 0.0f, 0.0f);

        if (e.keyCode == 'w') {
            moveVec.setz(-1);
        } else if (e.keyCode == 'a') {
            moveVec.setx(-1);
        } else if (e.keyCode == 's') {
            moveVec.setz(+1);
        } else if (e.keyCode == 'd') {
            moveVec.setx(+1);
        } else if (e.keyCode == 'c') {
            moveVec.sety(-1);
        } else if (e.keyCode == 0x20) {
            moveVec.sety(+1);
        }

        camera.moveRelative(moveVec);
        moveVec.delete();
        Root.getSingleton().renderOneFrame();
    }

    /**
     * @{inheritdoc}
     * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
     */
    public void keyReleased(KeyEvent e) {
        // do nothing
    }

    /**
     * Set camera to move.
     * 
     * @param camera
     *            to move.
     */
    public void setCamera(ICamera camera) {
        this.camera = camera;
    }

    /**
     * Set canvas to listen to input events.
     * 
     * @param canvas
     *            to listen to input events.
     */
    public void setCanvas(Canvas canvas) {
        if (this.canvas != canvas && this.canvas != null) {
            this.canvas.removeKeyListener(this);
            this.canvas.removeMouseListener(this);
            this.canvas.removeMouseMoveListener(this);
        }

        this.canvas = canvas;

        if (canvas != null) {
            canvas.addKeyListener(this);
            canvas.addMouseListener(this);
            canvas.addMouseMoveListener(this);
        }
    }

    /**
     * Set sceneManager for scene queries.
     * 
     * @param sceneManager
     *            for scene queries.
     */
    public void setSceneManager(ISceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

	/**
	 * @{inheritdoc}
	 * @see MouseListener#mouseDoubleClick(MouseEvent)
	 */
	public void mouseDoubleClick(MouseEvent event) {
		// do nothing
	}

	/**
	 * @{inheritdoc}
	 * @see MouseListener#mouseDown(MouseEvent)
	 */
	public void mouseDown(MouseEvent event) {
		// 1 is left button
		if (event.button == 1) {
			mouseLook = true;
			lastX = event.x;
			lastY = event.y;

		} else if (event.button == 3) {
			float x = 1.0f / canvas.getClientArea().width * event.x;
	        float y = 1.0f / canvas.getClientArea().height * event.y;

	        IRay ray = new Ray(WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
			camera.getCameraToViewportRay(ray, x, y);
			IRaySceneQuery rsq = sceneManager.createRayQuery(ray, 0xFFFFFFFF);
			ray.delete();
			rsq.setSortByDistance(true, 10);
			IRaySceneQueryResult rsqr = rsq.execute();
			if (rsqr.size() > 0) {
				for (int i=0; i<rsqr.size(); i++) {
					IRaySceneQueryResultEntry rsqre = rsqr.at(i);
					System.out.println("selected: " + rsqre.getmovable().getName());
				}
			} else {
				System.out.println("nothing selected");
			}
			sceneManager.destroyQuery(rsq);
		}
	}

	/**
	 * @{inheritdoc}
	 * @see MouseListener#mouseUp(MouseEvent)
	 */
	public void mouseUp(MouseEvent event) {
		// 1 is left button
		if (event.button == 1) {
			mouseLook = false;
			lastX = 0;
			lastY = 0;
		}
	}

	/**
	 * @{inheritdoc}
	 * @see MouseMoveListener#mouseMove(MouseEvent)
	 */
	public void mouseMove(MouseEvent event) {
		if (mouseLook) {
			int deltaX = lastX - event.x;
			int deltaY = lastY - event.y;
			lastX = event.x;
			lastY = event.y;

			final float rotate = 0.05f;
			IRadian yaw = new Radian(deltaX * rotate);
			camera.yaw(yaw);
			yaw.delete();
			IRadian pitch = new Radian(deltaY * rotate);
			camera.pitch(pitch);
			pitch.delete();
			Root.getSingleton().renderOneFrame();
		}
	}
}
