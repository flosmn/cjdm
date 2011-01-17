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
package org.ogre4j.examples.awt;

import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.ogre4j.ColourValue;
import org.ogre4j.HardwareBuffer;
import org.ogre4j.ICamera;
import org.ogre4j.IColourValue;
import org.ogre4j.IEntity;
import org.ogre4j.ILight;
import org.ogre4j.IMeshManager;
import org.ogre4j.IMeshPtr;
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
import org.ogre4j.Plane;
import org.ogre4j.Quaternion;
import org.ogre4j.Radian;
import org.ogre4j.Ray;
import org.ogre4j.RenderWindow;
import org.ogre4j.ResourceGroupManager;
import org.ogre4j.Root;
import org.ogre4j.StringUtil;
import org.ogre4j.Vector3;
import org.ogre4j.Light.LightTypes;
import org.ogre4j.awt.OgreAwtCanvas;
import org.xbig.base.InstancePointer;
import org.xbig.base.WithoutNativeObject;

/**
 * Simple ogre4j program. Creates plain OGRE objects to build a small scene. The
 * scene contains one light and one mesh.
 * 
 */
public final class HelloWorld extends Frame {

	public class Listener implements MouseListener, MouseMotionListener,
			KeyListener, ComponentListener {

		private int lastX = 0;
		private int lastY = 0;
		private boolean mouseLook = true;

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				mouseLook = true;
				lastX = e.getXOnScreen();
				lastY = e.getYOnScreen();

			} else if (e.getButton() == MouseEvent.BUTTON3) {
				float x = 1.0f / canvas.getWidth() * e.getXOnScreen();
				float y = 1.0f / canvas.getHeight() * e.getYOnScreen();

				IRay ray = new Ray(
						WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
				camera.getCameraToViewportRay(ray, x, y);
				IRaySceneQuery rsq = sceneManager.createRayQuery(ray,
						0xFFFFFFFF);
				ray.delete();
				rsq.setSortByDistance(true, 10);
				IRaySceneQueryResult rsqr = rsq.execute();
				if (rsqr.size() > 0) {
					for (int i = 0; i < rsqr.size(); i++) {
						IRaySceneQueryResultEntry rsqre = rsqr.at(i);
						System.out.println("selected: "
								+ rsqre.getmovable().getName());
					}
				} else {
					System.out.println("nothing selected");
				}
				sceneManager.destroyQuery(rsq);
				canvas.paint(getGraphics());
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// 1 is left button
			if (e.getButton() == 1) {
				mouseLook = false;
				lastX = 0;
				lastY = 0;
			}

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (mouseLook) {
				int deltaX = lastX - e.getXOnScreen();
				int deltaY = lastY - e.getYOnScreen();
				lastX = e.getXOnScreen();
				lastY = e.getYOnScreen();

				final float rotate = 0.005f;
				IRadian yaw = new Radian(deltaX * rotate);
				camera.yaw(yaw);
				yaw.delete();
				IRadian pitch = new Radian(deltaY * rotate);
				camera.pitch(pitch);
				pitch.delete();
				canvas.paint(getGraphics());
			}

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (mouseLook) {

			}
		}

		@Override
		public void keyPressed(KeyEvent e) {

			IVector3 moveVec = new Vector3(0.0f, 0.0f, 0.0f);

			if (e.getKeyChar() == 'w') {
				moveVec.setz(-1);
			} else if (e.getKeyChar() == 'a') {
				moveVec.setx(-1);
			} else if (e.getKeyChar() == 's') {
				moveVec.setz(+1);
			} else if (e.getKeyChar() == 'd') {
				moveVec.setx(+1);
			} else if (e.getKeyChar() == 'c') {
				moveVec.sety(-1);
			} else if (e.getKeyChar() == 0x20) {
				moveVec.sety(+1);
			}

			camera.moveRelative(moveVec);
			moveVec.delete();
			canvas.paint(getGraphics());
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void componentHidden(ComponentEvent e) {
			canvas.paint(getGraphics());
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			canvas.paint(getGraphics());
		}

		@Override
		public void componentResized(ComponentEvent e) {
			int height = canvas.getHeight();
            int width = canvas.getWidth();

    		// circumvent a directx bug. directx crashed
            if (height == 0) {
                height = 1;
            }
            if (width == 0) {
                width = 1;
            }

            // redraw the ogre window
            if (camera == null || renderWindow == null) {
                return;
            }

            camera.setAspectRatio((float) width / (float) height);
			renderWindow.windowMovedOrResized();
			canvas.paint(getGraphics());

		}

		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub

		}
	}

	
	/**
	 * Generated value to avoid compiler warning.
	 */
	private static final long serialVersionUID = 5085994290309572835L;

	/**
	 * Default window width.
	 */
	private static final int DEFAULT_WIDTH = 640;

	/**
	 * Default window height.
	 */
	private static final int DEFAULT_HEIGHT = 480;

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
	 * The OGRE SceneManager.
	 */
	private ISceneManager sceneManager;

	/**
	 * The AWT canvas to render.
	 */
	private OgreAwtCanvas canvas;

	/**
	 * Creates a plane.
	 */
	private void createPlane() {
		Plane plane = new Plane(Vector3.getUNIT_Y(), 0);
		IMeshPtr meshPtr = new MeshPtr(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		IMeshManager meshMgr = MeshManager.getSingleton();
		meshMgr.createPlane(meshPtr, "ground", ResourceGroupManager
				.getDEFAULT_RESOURCE_GROUP_NAME(), plane, 15f, 15f, 20, 20,
				true, 1, 5, 5, Vector3.getUNIT_Z(),
				HardwareBuffer.Usage.HBU_STATIC_WRITE_ONLY,
				HardwareBuffer.Usage.HBU_STATIC_WRITE_ONLY, true, true);
		plane.delete();
		meshPtr.delete();

		IEntity entity = sceneManager.createEntity("GroundEntity", "ground");

		ISceneNode node = sceneManager.getRootSceneNode()
				.createChildSceneNode("GroundEntityNode", Vector3.getZERO(),
						Quaternion.getIDENTITY());
		node.attachObject(entity);
	}

	/**
	 * Default constructor. Sets media to "media" directory.
	 */
	public void setup() throws Exception {
		setup("media", "FileSystem", "OpenGL");
		// setup("media", "FileSystem", "Direct3D9");
	}

	public void setup(String media, String locType, String renderSystem)
			throws Exception {
		// create Ogre Root
		root = new Root("", "", this.getClass().getName() + ".log");
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

		// create RenderWindow with AWT handle and if necessary GL context
		InstancePointer ptr = new InstancePointer(canvas.createRenderWindow(
				canvas, root.getInstancePointer().pointer));
		renderWindow = new RenderWindow(ptr);

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
		IViewport viewport = renderWindow.addViewport(camera, 0, 0.0f, 0.0f,
				1.0f, 1.0f);
		IColourValue backgroundColour = new ColourValue(0.3f, 0.3f, 0.3f, 1.0f);
		viewport.setBackgroundColour(backgroundColour);
		backgroundColour.delete();
		viewport.setOverlaysEnabled(false);
		viewport.setCamera(camera);
		camera.setAspectRatio((float) this.getWidth()
				/ (float) this.getHeight());

		// connect camera with render window
		renderWindow.setActive(true);

		// load resources
		ResourceGroupManager.getSingleton().addResourceLocation(media, locType,
				"General", false);
		ResourceGroupManager.getSingleton().initialiseAllResourceGroups();

		// create entity
		IEntity entity = sceneManager.createEntity("cake", "zuh.mesh");
		ISceneNode cakeNode = rootSceneNode.createChildSceneNode(Vector3
				.getZERO(), Quaternion.getIDENTITY());
		cakeNode.attachObject(entity);
		cakeNode.setPosition(-3.5f, 0, 0);
		// create a plane as ground
		createPlane();
		Listener lis = new Listener();
		canvas.addMouseListener(lis);
		canvas.addKeyListener(lis);
		canvas.addMouseMotionListener(lis);
		canvas.addComponentListener(lis);
		canvas.paint(getGraphics());
		//allow repainting again
		canvas.setIgnoreRepaint(false);

	}

	public HelloWorld() throws Exception {

		super(GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDefaultConfiguration());
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("Hello World");

		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				root.delete();
				System.exit(0);
			}
		});
	}

	public void run() throws Exception {
		canvas = new OgreAwtCanvas(this.getGraphicsConfiguration());
		add(canvas);
		//Ignore repaint to avoid canvas from rendering before renderwindow ist created
		canvas.setIgnoreRepaint(true);
		setup();
	}

	/**
	 * Main function.
	 * 
	 * @param args
	 *            commandline arguments.
	 */
	public static void main(String[] args) throws Exception {
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.run();
	}

}