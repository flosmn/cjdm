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

import javax.swing.JOptionPane;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.ogre4j.ICamera;
import org.ogre4j.IOverlay;
import org.ogre4j.IOverlayElement;
import org.ogre4j.IQuaternion;
import org.ogre4j.IRadian;
import org.ogre4j.IRenderTarget;
import org.ogre4j.IRenderWindow;
import org.ogre4j.IUTFString;
import org.ogre4j.IVector3;
import org.ogre4j.MaterialManager;
import org.ogre4j.OverlayManager;
import org.ogre4j.PolygonMode;
import org.ogre4j.Radian;
import org.ogre4j.Root;
import org.ogre4j.TextureFilterOptions;
import org.ogre4j.UTFString;
import org.ogre4j.Vector3;
import org.xbig.base.WithoutNativeObject;

/**
 * Defines an example frame listener which responds to frame events. This frame
 * listener just moves a specified camera around based on keyboard and mouse
 * movements.
 * 
 * <pre>
 * Mouse: Freelook
 * W or Up: Forward
 * S or Down: Backward
 * A: Step left
 * D: Step right
 * PgUp: Move upwards
 * PgDown: Move downwards
 * F: Toggle frame rate stats on/off
 * R: Render mode
 * T: Cycle texture filtering Bilinear, Trilinear, Anisotropic(8)
 * P: Toggle on/off display of camera position / orientation
 * </pre>
 */
public class ExampleFrameListener implements IFrameListener, KeyListener,
		MouseListener, MouseMoveListener, PaintListener, DisposeListener {

	/**
	 * Camera to be moved.
	 */
	protected ICamera mCamera;

	/**
	 * Window to resize and create screen shots.
	 */
	protected IRenderWindow mWindow;

	/**
	 * Indicates if DebugOverlay shall be displayed.
	 */
	protected boolean mStatsOn;

	/**
	 * The Ogre DebugOverlay.
	 */
	protected IOverlay mDebugOverlay;

	/**
	 * String to be displayed in the middle of the DebugOverlay.
	 */
	protected String mDebugText;

	/**
	 * Number of screen shots. Used for filename.
	 */
	protected int mNumScreenShots;

	/**
	 * Current TextureFilterOption.
	 */
	protected TextureFilterOptions mFiltering;

	/**
	 * Anisotropic filtering.
	 */
	protected int mAniso;

	/**
	 * Render mode to use.
	 */
	protected int mSceneDetailIndex;

	/**
	 * Movement speed.
	 */
	protected float mMoveScale;

	/**
	 * Rotation speed.
	 */
	protected float mRotScale;

	/**
	 * Indicates if mouse movement shall rotate the camera.
	 */
	protected boolean mouseLook;

	/**
	 * Indicates if mouse movement shall move the camera.
	 */
	protected boolean mouseMove;

	/**
	 * Last clicked x coordinate.
	 */
	protected int lastX;

	/**
	 * Last clicked y coordinate.
	 */
	protected int lastY;

	/**
	 * Indicates if rendering shall be continued.
	 */
	protected boolean mContinue = true;

	/**
	 * SWT Canvas to listen to input events.
	 */
	protected Canvas swtCanvas;

	/**
	 * Indicates if current camera position and orientation shall be displayed
	 * in DebugOverlay.
	 */
	protected boolean displayCameraDetails = false;

	/**
	 * Constructor to be used by
	 * {@link ExampleApplication#createFrameListener()}. Takes a RenderWindow
	 * for resize events and screen shots. The camera is used for movement and
	 * the canvas for input events.
	 * 
	 * @param rw
	 * @param cam
	 * @param swtCanvas
	 */
	public ExampleFrameListener(IRenderWindow rw, ICamera cam, Canvas swtCanvas) {
		mCamera = cam;
		mWindow = rw;
		mStatsOn = true;
		mDebugText = "";

		mNumScreenShots = 0;
		mFiltering = TextureFilterOptions.TFO_BILINEAR;
		mAniso = 1;
		mSceneDetailIndex = 0;

		mMoveScale = 1;
		mRotScale = 0.001f;

		mDebugOverlay = OverlayManager.getSingleton().getByName(
				"Core/DebugOverlay");

		showDebugOverlay(true);

		this.swtCanvas = swtCanvas;
		swtCanvas.addKeyListener(this);
		swtCanvas.addMouseListener(this);
		swtCanvas.addMouseMoveListener(this);
		swtCanvas.addPaintListener(this);
		swtCanvas.addDisposeListener(this);
	}

	/**
	 * Updates DebugOverlay. Called every frame.
	 */
	protected void updateStats() {
		String currFps = "Current FPS: ";
		String avgFps = "Average FPS: ";
		String bestFps = "Best FPS: ";
		String worstFps = "Worst FPS: ";
		String tris = "Triangle Count: ";
		String batches = "Batch Count: ";

		// update stats when necessary
		IOverlayElement guiAvg = OverlayManager.getSingleton()
				.getOverlayElement("Core/AverageFps", false);
		IOverlayElement guiCurr = OverlayManager.getSingleton()
				.getOverlayElement("Core/CurrFps", false);
		IOverlayElement guiBest = OverlayManager.getSingleton()
				.getOverlayElement("Core/BestFps", false);
		IOverlayElement guiWorst = OverlayManager.getSingleton()
				.getOverlayElement("Core/WorstFps", false);

		IRenderTarget.IFrameStats stats = mWindow.getStatistics();

		IUTFString utfString = new UTFString(avgFps + stats.getavgFPS());
		guiAvg.setCaption(utfString);
		guiCurr.setCaption(utfString.assign(currFps + stats.getlastFPS()));
		guiBest.setCaption(utfString.assign(bestFps + stats.getbestFPS() + " "
				+ stats.getbestFrameTime() + " ms"));
		guiWorst.setCaption(utfString.assign(worstFps + stats.getworstFPS()
				+ " " + stats.getworstFrameTime() + " ms"));

		IOverlayElement guiTris = OverlayManager.getSingleton()
				.getOverlayElement("Core/NumTris", false);
		guiTris.setCaption(utfString.assign(tris + stats.gettriangleCount()));

		IOverlayElement guiBatches = OverlayManager.getSingleton()
				.getOverlayElement("Core/NumBatches", false);
		guiBatches
				.setCaption(utfString.assign(batches + stats.getbatchCount()));

		IOverlayElement guiDbg = OverlayManager.getSingleton()
				.getOverlayElement("Core/DebugText", false);
		guiDbg.setCaption(utfString.assign(mDebugText));

		utfString.delete();
	}

	/**
	 * Override frameEnded event to process that (just update stats).
	 */
	public boolean frameEnded(FrameEvent evt) {
		updateStats();
		return mContinue;
	}

	/**
	 * Override frameStarted event to process that (don't care much about
	 * frameEnded).
	 */
	public boolean frameStarted(FrameEvent evt) {
		// TODO movement & rotation time dependent
		// That means make a move vector as attribute and manipulate it
		// during keyPressed and keyReleased. Camera movement should then
		// be made here with taking care of the passed event. This is not
		// done right now because keyReleased events are missing when
		// more than one key is pressed.

		return mContinue;
	}

	/**
	 * Enables / Disables DebugOverlay.
	 * 
	 * @param show
	 *            Show or hide.
	 */
	public void showDebugOverlay(boolean show) {
		if (mDebugOverlay != null) {
			if (show)
				mDebugOverlay.show();
			else
				mDebugOverlay.hide();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.
	 * KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		IVector3 moveVec = new Vector3(0.0f, 0.0f, 0.0f);

		if (e.keyCode == 'w' || e.keyCode == 0x1000001) {
			// move forward on 'w' and 'up'
			moveVec.setz(-1);

		} else if (e.keyCode == 'a') {
			// move left on 'a'
			moveVec.setx(-1);

		} else if (e.keyCode == 's' || e.keyCode == 0x1000002) {
			// move backward on 's' and 'down'
			moveVec.setz(+1);

		} else if (e.keyCode == 'd') {
			// move right on 'd'
			moveVec.setx(+1);

		} else if (e.keyCode == 'c' || e.keyCode == 0x1000006) {
			// move down on 'c' and 'page up'
			moveVec.sety(-1);

		} else if (e.keyCode == 0x20 || e.keyCode == 0x1000005) {
			// move up on 'space' and 'page down'
			moveVec.sety(+1);

		} else if (e.keyCode == 0x1000003) {
			// rotate left on 'left'
			IRadian rad = new Radian(mRotScale);
			mCamera.yaw(rad);
			rad.delete();

		} else if (e.keyCode == 0x1000004) {
			// rotate right on 'right'
			IRadian rad = new Radian(-mRotScale);
			mCamera.yaw(rad);
			rad.delete();

		} else if (e.keyCode == 0x1B) {
			// quit on 'Esc'
			mContinue = false;

		} else if (e.keyCode == 'f') {
			// toggle stats
			mStatsOn = !mStatsOn;
			showDebugOverlay(mStatsOn);

		} else if (e.keyCode == 't') {
			// change filtering
			switch (mFiltering) {
			case TFO_BILINEAR:
				mFiltering = TextureFilterOptions.TFO_TRILINEAR;
				mAniso = 1;
				break;
			case TFO_TRILINEAR:
				mFiltering = TextureFilterOptions.TFO_ANISOTROPIC;
				mAniso = 8;
				break;
			case TFO_ANISOTROPIC:
				mFiltering = TextureFilterOptions.TFO_BILINEAR;
				mAniso = 1;
				break;
			default:
				break;
			}
			MaterialManager.getSingleton().setDefaultTextureFiltering(
					mFiltering);
			MaterialManager.getSingleton().setDefaultAnisotropy(mAniso);

			showDebugOverlay(mStatsOn);

		} else if (e.keyCode == 0x00) {
			// TODO change key for screenshot (currently '^')
			String ss = "screenshot_" + (++mNumScreenShots) + ".png";
			mWindow.writeContentsToFile(ss);
			mDebugText = "Saved: " + ss;

		} else if (e.keyCode == 'r') {
			// change render mode
			mSceneDetailIndex = (mSceneDetailIndex + 1) % 3;
			switch (mSceneDetailIndex) {
			case 0:
				mCamera.setPolygonMode(PolygonMode.PM_SOLID);
				break;
			case 1:
				mCamera.setPolygonMode(PolygonMode.PM_WIREFRAME);
				break;
			case 2:
				mCamera.setPolygonMode(PolygonMode.PM_POINTS);
				break;
			}

		} else if (e.keyCode == 'p') {
			// toggle display of camera stats
			displayCameraDetails = !displayCameraDetails;
			if (!displayCameraDetails)
				mDebugText = "";
		}

		// Print camera details
		if (displayCameraDetails) {
			IVector3 pos = mCamera.getDerivedPosition();
			String camPos = pos.getx() + " " + pos.gety() + " " + pos.getz();
			IQuaternion ori = mCamera.getDerivedOrientation();
			String camOri = ori.getx() + " " + ori.gety() + " " + ori.getz()
					+ " " + ori.getw();
			mDebugText = "P: " + camPos + " " + "O: " + camOri;
		}

		IVector3 tmp = new Vector3(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		moveVec.operatorMultiplication(tmp, mMoveScale);
		mCamera.moveRelative(tmp);
		tmp.delete();
		moveVec.delete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events
	 * .KeyEvent)
	 */
	public void keyReleased(KeyEvent arg0) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt
	 * .events.MouseEvent)
	 */
	public void mouseDoubleClick(MouseEvent arg0) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events
	 * .MouseEvent)
	 */
	public void mouseDown(MouseEvent event) {
		// 1 is left button
		if (event.button == 1) {
			mouseLook = true;

		} else if (event.button == 3) {
			// 3 is right button
			// mouseMove is disabled to show a dialog at right click
			// mouseMove = true;

			// show dialog in a new thread that rendering is not blocked
			// TODO avoid start of more than one thread
			Thread dialogThread = new Thread() {
				public void run() {
					JOptionPane.showMessageDialog(null,
							"This is a Java Application");
				}
			};
			dialogThread.start();
		}

		lastX = event.x;
		lastY = event.y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.
	 * MouseEvent)
	 */
	public void mouseUp(MouseEvent event) {
		// 1 is left button
		if (event.button == 1) {
			mouseLook = false;

		} else if (event.button == 3) {
			// 3 is right button
			mouseMove = false;
		}

		lastX = 0;
		lastY = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.events.MouseMoveListener#mouseMove(org.eclipse.swt.events
	 * .MouseEvent)
	 */
	public void mouseMove(MouseEvent event) {
		if (mouseLook) {
			int deltaX = lastX - event.x;
			int deltaY = lastY - event.y;
			lastX = event.x;
			lastY = event.y;

			IRadian yaw = new Radian(deltaX * mRotScale);
			mCamera.yaw(yaw);
			yaw.delete();
			IRadian pitch = new Radian(deltaY * mRotScale);
			mCamera.pitch(pitch);
			pitch.delete();
			Root.getSingleton().renderOneFrame();

		} else if (mouseMove) {
			int deltaX = lastX - event.x;
			int deltaY = lastY - event.y;
			lastX = event.x;
			lastY = event.y;

			IVector3 moveVec = new Vector3(0.0f, 0.0f, 0.0f);
			moveVec.setx(deltaX * 0.13f);
			moveVec.sety(deltaY * 0.13f);
			IVector3 tmp = new Vector3(
					WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
			moveVec.operatorMultiplication(tmp, mMoveScale);
			mCamera.moveRelative(tmp);
			tmp.delete();
			moveVec.delete();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.events.PaintListener#paintControl(org.eclipse.swt.events
	 * .PaintEvent)
	 */
	public void paintControl(PaintEvent e) {
		int height = swtCanvas.getBounds().height;
		int width = swtCanvas.getBounds().width;

		// circumvent a directx bug. directx crashed
		if (height == 0) {
			height = 1;
		}
		if (width == 0) {
			width = 1;
		}

		// redraw the ogre window
		if (mCamera == null || mWindow == null) {
			return;
		}

		mCamera.setAspectRatio((float) width / (float) height);
		mWindow.resize(width, height);
		mWindow.windowMovedOrResized();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt
	 * .events.DisposeEvent)
	 */
	public void widgetDisposed(DisposeEvent e) {
		swtCanvas.removeKeyListener(this);
		swtCanvas.removeMouseListener(this);
		swtCanvas.removeMouseMoveListener(this);
		swtCanvas.removePaintListener(this);
		swtCanvas.removeDisposeListener(this);
	}
}
