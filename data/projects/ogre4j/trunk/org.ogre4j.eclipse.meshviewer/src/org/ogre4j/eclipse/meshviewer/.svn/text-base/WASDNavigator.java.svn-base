/**
 * WASDNavigator.java
 *
 * Copyright &copy; 2008, netAllied GmbH, Tettnang, Germany. 
 * 
 * @author cnenning
 */
package org.ogre4j.eclipse.meshviewer;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Canvas;
import org.ogre4j.ICamera;
import org.ogre4j.IRadian;
import org.ogre4j.IRay;
import org.ogre4j.IRaySceneQuery;
import org.ogre4j.IRaySceneQueryResult;
import org.ogre4j.IRaySceneQueryResultEntry;
import org.ogre4j.ISceneManager;
import org.ogre4j.IVector3;
import org.ogre4j.Radian;
import org.ogre4j.Ray;
import org.ogre4j.Root;
import org.ogre4j.Vector3;
import org.xbig.base.WithoutNativeObject;

/**
 * A navigator to move through a 3D scene by pressing the keys W,A,S or D.
 * 
 * @author cnenning
 */
public abstract class WASDNavigator implements INavigator {

	/**
	 * Canvas to listen to input events.
	 */
	private Canvas canvas;

	/**
	 * Camera to move.
	 */
	protected ICamera camera;

	/**
	 * The OGRE SceneManager. Needed for SceneQueries.
	 */
	private ISceneManager sceneManager;

	/**
	 * Indicates if the camera should be rotated by the mouse.
	 */
	protected boolean mouseLook;

	/**
	 * Stores X coordinate of last mouse position. Used for mouse look.
	 */
	protected int lastX;

	/**
	 * Stores Y coordinate of last mouse position. Used for mouse look.
	 */
	protected int lastY;

	private boolean showBoundingBoxes;

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
	 * @{inheritdoc
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
		} else if (e.keyCode == 'b') {
			this.showBoundingBoxes = !this.showBoundingBoxes;
		}

		this.sceneManager.showBoundingBoxes(this.showBoundingBoxes);
		camera.moveRelative(moveVec);
		moveVec.delete();
		Root.getSingleton().renderOneFrame();
	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
	 */
	public void keyReleased(KeyEvent e) {
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
		if (this.canvas != canvas && this.canvas != null
				&& !this.canvas.isDisposed()) {
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
	 * @{inheritdoc
	 * @see MouseListener#mouseDoubleClick(MouseEvent)
	 */
	public void mouseDoubleClick(MouseEvent event) {
		// do nothing
	}

	/**
	 * @{inheritdoc
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
				for (int i = 0; i < rsqr.size(); i++) {
					IRaySceneQueryResultEntry rsqre = rsqr.at(i);
					System.out.println("selected: "
							+ rsqre.getmovable().getName());
				}
			} else {
				System.out.println("nothing selected");
			}
			sceneManager.destroyQuery(rsq);
		}
	}

	/**
	 * @{inheritdoc
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
	 * @{inheritdoc
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
