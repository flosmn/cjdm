/**
 * RenderWindowPool.java
 *
 * Copyright &copy; 2008, netAllied GmbH, Tettnang, Germany. 
 * 
 * @author cnenning
 */
package org.ogre4j.eclipse;

import java.util.Iterator;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.swt.SWT;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.opengl.GLData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.ogre4j.INameValuePairList;
import org.ogre4j.IRenderWindow;
import org.ogre4j.NameValuePairList;
import org.ogre4j.Root;
import org.osgi.framework.BundleActivator;
import org.xbig.base.StringPointer;

/**
 * Manages a pool of OGRE {@link IRenderWindow RenderWindows} and their
 * corresponding SWT {@link Canvas}. That serves two purposes:
 * <li>OpenGL implementations have problems with deleting and recreating
 * RenderWindows.</li>
 * <li>Save resources.</li>
 * 
 */
public class RenderWindowPool {

	/**
	 * Ties an OGRE {@link IRenderWindow RenderWindow} and a SWT {@link Canvas}
	 * together.
	 * 
	 */
	public static class RenderWindowCanvasPair {

		/**
		 * OGRE {@link IRenderWindow RenderWindow}.
		 */
		public IRenderWindow renderWindow;

		/**
		 * SWT {@link Canvas}.
		 */
		public Canvas canvas;

		/**
		 * Constructor.
		 * 
		 * @param renderWindow
		 *            RenderWindow.
		 * @param canvas
		 *            Canvas.
		 */
		public RenderWindowCanvasPair(IRenderWindow renderWindow, Canvas canvas) {
			super();
			this.renderWindow = renderWindow;
			this.canvas = canvas;
		}
	}

	private static final String NAME_PREFIX_RENDER_WINDOW = "RenderWindow_";

	private static final String KEY_EXTERNAL_WINDOW_HANDLE = "externalWindowHandle";

	/**
	 * The minimum number of render windows this pool maintains.
	 */
	private int minNumberOfRenderWindows = 0;

	/**
	 * The maximum number of render windows this pool maintains.
	 */
	private int maxNumberOfRenderWindows = 100;

	/**
	 * The map of available render windows.
	 */
	private ConcurrentHashMap<Canvas, IRenderWindow> availableRenderWindows;

	/**
	 * The map of busy render windows.
	 */
	private ConcurrentHashMap<Canvas, IRenderWindow> busyRenderWindows;

	/**
	 * A queue of created canvases and corresponding render windows.
	 */
	private ConcurrentLinkedQueue<RenderWindowCanvasPair> createdRenderWindows;

	/**
	 * Indicates if created RenderWindows shall be stored and deleted at
	 * shutdown (OpenGL) or if they shall be deleted on release (Direct3D).
	 */
	private boolean doUsePool;

	/**
	 * Indicates if the first window is created.
	 */
	private boolean isFirstWindow = true;

	/**
	 * Constructor. Creates min {@link IRenderWindow RenderWindows} if doUsePool
	 * is true.
	 * 
	 * @throws IllegalArgumentException
	 *             when min > max.
	 * @param min
	 *            Minimum number of {@link IRenderWindow RenderWindows}.
	 * @param max
	 *            Maximum number of {@link IRenderWindow RenderWindows}.
	 * @param doUsePool
	 *            Indicates if RenderWindows shall be reused or deleted.
	 */
	public RenderWindowPool(int min, int max, boolean doUsePool) {
		if (min > max) {
			throw new IllegalArgumentException("min > max");
		}
		this.doUsePool = doUsePool;
		this.minNumberOfRenderWindows = min;
		this.maxNumberOfRenderWindows = max;
		this.createdRenderWindows = new ConcurrentLinkedQueue<RenderWindowCanvasPair>();
		this.availableRenderWindows = new ConcurrentHashMap<Canvas, IRenderWindow>(
				max);
		this.busyRenderWindows = new ConcurrentHashMap<Canvas, IRenderWindow>(
				max);

		if (doUsePool) {
			for (int i = 0; i < this.minNumberOfRenderWindows; i++) {
				createRenderWindowAndCanvas();
			}
		}
	}

	/**
	 * {@link IRenderWindow RenderWindows} obtained via this method must be
	 * released with
	 * {@link #releaseRenderWindow(RenderWindowCanvasPair)}.
	 * <br>
	 * <b>Note:</b> Don't forget to set your SWT parent of the Canvas. <br>
	 * <b>Note:</b> Call renderWindow.windowMovedOrResized() after setting SWT
	 * parent. <br>
	 * <b>Note:</b> Make both visible and active.
	 * 
	 * @return A canvas and the corresponding OGRE render window or null if the
	 *         maximum number of render windows is reached.
	 */
	public RenderWindowCanvasPair accquireRenderWindow() {
		if (!doUsePool) {
			return doCreateRenderWindowAndCanvas();
		}
		if (this.availableRenderWindows.isEmpty()) {
			if (this.createdRenderWindows.size() < this.maxNumberOfRenderWindows) {
				createRenderWindowAndCanvas();
			} else {
				return null;
			}
		}

		Entry<Canvas, IRenderWindow> entry = this.availableRenderWindows
				.entrySet().iterator().next();
		RenderWindowCanvasPair renderWindowCanvasPair = new RenderWindowCanvasPair(
				entry.getValue(), entry.getKey());
		this.availableRenderWindows.remove(entry.getKey());
		this.busyRenderWindows.put(renderWindowCanvasPair.canvas,
				renderWindowCanvasPair.renderWindow);
		return renderWindowCanvasPair;
	}

	/**
	 * Creates a new {@link IRenderWindow RenderWindow} and {@link Canvas} pair.
	 * It is pushed into {@link #availableRenderWindows} and
	 * {@link #createdRenderWindows}.
	 */
	private void createRenderWindowAndCanvas() {

		if (this.createdRenderWindows.size() >= this.maxNumberOfRenderWindows) {
			throw new RuntimeException(
					"Maximum number of RenderWindows exeeded");
		}

		RenderWindowCanvasPair pair = doCreateRenderWindowAndCanvas();

		this.availableRenderWindows.put(pair.canvas, pair.renderWindow);
		this.createdRenderWindows.add(pair);
	}

	/**
	 * Destroys all {@link IRenderWindow RenderWindows} created via
	 * {@link #accquireRenderWindow()}. To be called during
	 * {@link BundleActivator#stop(org.osgi.framework.BundleContext)}.
	 */
	public void destroyAllRenderWindows() {
		if (!this.busyRenderWindows.isEmpty()) {
			throw new IllegalStateException("Not all render windows released!");
		}
		// Workaround to delete RenderWindows in correct order (Stack like).
		Stack<RenderWindowCanvasPair> stack = new Stack<RenderWindowCanvasPair>();
		Iterator<RenderWindowCanvasPair> it = this.createdRenderWindows
				.iterator();
		while (it.hasNext()) {
			stack.push(it.next());
		}
		while (!stack.isEmpty()) {
			RenderWindowCanvasPair i = stack.pop();
			doDestroy(i);
		}
		this.availableRenderWindows.clear();
		this.busyRenderWindows.clear();
		this.createdRenderWindows.clear();
	}

	/**
	 * Creates a RenderWindow and a corresponding Canvas.
	 */
	private RenderWindowCanvasPair doCreateRenderWindowAndCanvas() {
		// if no RenderSystem has been selected, a NPE is thrown
		OgrePlugin.getDefault().getOgreRoot().getRenderSystem();

		Shell parent = Display.getDefault().getActiveShell();
		Canvas canvas;
		String operatingSystem = System.getProperty("os.name").toLowerCase();
        if (operatingSystem.equals("linux")) {
	        GLData data = new GLData ();
	        data.doubleBuffer = true;
	        canvas = new GLCanvas(parent, SWT.NONE, data); 
	        ((GLCanvas)canvas).setCurrent();
        } else {
        	canvas = new Canvas(parent, SWT.NO_BACKGROUND);
        }
		canvas.setSize(1, 1);
		long handle = canvas.handle;

		INameValuePairList params = new NameValuePairList();
		StringPointer windowHandlePointer;
        if (operatingSystem.equals("linux")) {
    		windowHandlePointer = new StringPointer("true");
    		params.insert("currentGLContext", windowHandlePointer);
        } else {
			windowHandlePointer = new StringPointer(Long
					.toString(handle));
			params.insert(KEY_EXTERNAL_WINDOW_HANDLE, windowHandlePointer);
        }
		IRenderWindow renderWindow = OgrePlugin.getDefault().getOgreRoot()
				.createRenderWindow(NAME_PREFIX_RENDER_WINDOW + handle, 1, 1,
						false, params);
		params.delete();
		windowHandlePointer.delete();
		renderWindow.setVisible(false);

		if (this.isFirstWindow) {
			OgrePlugin.getDefault().loadCgPlugin();
			this.isFirstWindow = false;
		}

		return new RenderWindowCanvasPair(renderWindow, canvas);
	}

	/**
	 * Destroys a RenderWindow and a corresponding Canvas.
	 * 
	 * @param pair
	 *            RenderWindowCanvasPair to be destroyed.
	 */
	private void doDestroy(RenderWindowCanvasPair pair) {
		Root.getSingleton().detachRenderTarget(pair.renderWindow);
		pair.canvas.dispose();
	}

	/**
	 * Returns a {@link IRenderWindow RenderWindow} which is no longer used to
	 * the pool of available {@link IRenderWindow RenderWindows}.
	 * 
	 * @param entry
	 *            The canvas and render window to be released.
	 */
	public void releaseRenderWindow(RenderWindowCanvasPair entry) {
		entry.canvas.setParent(OgrePlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell());
		
		entry.canvas.setVisible(false);
		entry.renderWindow.removeAllViewports();
		entry.renderWindow.setActive(false);
		entry.renderWindow.setVisible(false);
		if (doUsePool) {
			this.busyRenderWindows.remove(entry.canvas);
			this.availableRenderWindows.put(entry.canvas, entry.renderWindow);
		} else {
			doDestroy(entry);
		}
	}
}
