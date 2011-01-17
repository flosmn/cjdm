/**
 * RedrawListener.java
 *
 * Copyright &copy; 2008, netAllied GmbH, Tettnang, Germany. 
 * 
 * @author cnenning
 */
package org.ogre4j.eclipse;

import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.ogre4j.ICamera;
import org.ogre4j.IRenderWindow;
import org.ogre4j.IRoot;

/**
 * A listener to SWT paint and control events.
 * 
 * @author cnenning
 */
public class RedrawListener implements PaintListener, ControlListener {

    private Canvas canvas;

    private IRenderWindow renderWindow;

    private ICamera camera;

    /**
     * @{inheritdoc}
     * @see org.eclipse.swt.events.ControlListener#controlMoved(org.eclipse.swt.events.ControlEvent)
     */
    public void controlMoved(ControlEvent e) {
        redraw();
    }

    /**
     * @{inheritdoc}
     * @see org.eclipse.swt.events.ControlListener#controlResized(org.eclipse.swt.events.ControlEvent)
     */
    public void controlResized(ControlEvent e) {
        redraw();
    }

    /**
     * @return The camera this listener updates the aspect ratio in case of
     *         paint, resize or moved events.
     */
    public ICamera getCamera() {
        return camera;
    }

    /**
     * @return The canvas this listener is listens to.
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * @return The render window this listener should update in case of paint,
     *         resize or moved events.
     */
    public IRenderWindow getRenderWindow() {
        return renderWindow;
    }

    /**
     * @{inheritdoc}
     * @see org.eclipse.swt.events.PaintListener#paintControl(org.eclipse.swt.events.PaintEvent)
     */
    public void paintControl(PaintEvent e) {
        redraw();
    }

    private void redraw() {
        int height = canvas.getBounds().height;
        int width = canvas.getBounds().width;

        // circumvent a directx bug. directx crashed
        if (height <= 0) {
            height = 1;
        }
        if (width <= 0) {
            width = 1;
        }

        if (camera == null || renderWindow == null) {
            return;
        }

        camera.setAspectRatio((float) width / (float) height);
        renderWindow.windowMovedOrResized();

        IRoot root = OgrePlugin.getDefault().getOgreRoot();
        root.renderOneFrame();
    }

    /**
     * Sets the camera this listener updates the aspect ratio in case of paint,
     * resize or moved events.
     * 
     * @param camera
     *            The camera to set.
     */
    public void setCamera(ICamera camera) {
        this.camera = camera;
    }

    /**
     * Sets the canvas this listener should listen to. If this listener already
     * listens to another canvas it will be unregistered first. If the old and
     * new canvas are equal this has no effect.
     * 
     * @param swtCanvas
     *            the canvas this listener should listens to.
     */
    private void setCanvas(Canvas swtCanvas) {
        if (this.canvas == swtCanvas) {
            return;
        }

        if (this.canvas != null) {
            this.canvas.removeControlListener(this);
            this.canvas.removePaintListener(this);
        }

        this.canvas = swtCanvas;
        
        if(this.canvas != null){
            this.canvas.addControlListener(this);
            this.canvas.addPaintListener(this);
        }
    }

    /**
     * Sets the canvas this listener should listen to and the render window that
     * should be should updated in case of paint, resize or moved events.
     * 
     * @param canvas
     *            The canvas to set.
     * @param renderWindow
     *            The render window to set.
     */
    public void setCanvasAndRenderWindow(Canvas canvas, IRenderWindow renderWindow) {
        setRenderWindow(renderWindow);
        setCanvas(canvas);

    }

    /**
     * Sets the canvas this listener should listen to and the render window that
     * should be should updated in case of paint, resize or moved events.
     * 
     * @param pair
     *            The canvas, render window pair.
     */
    public void setCanvasAndRenderWindow(RenderWindowPool.RenderWindowCanvasPair pair) {
        if (pair == null) {
            setCanvasAndRenderWindow(null, null);
        } else {
            setCanvasAndRenderWindow(pair.canvas, pair.renderWindow);
        }
    }

    /**
     * Sets the render window this listener is in charge of.
     * 
     * @param renderWindow
     */
    private void setRenderWindow(IRenderWindow renderWindow) {
        this.renderWindow = renderWindow;
    }
}
