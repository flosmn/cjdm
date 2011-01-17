package org.ogre4j.awt;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;

/**
 * Allows integration of ogre4j and AWT.
 * 
 */
public class OgreAwtCanvas extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static {
		System.loadLibrary("ogre4j");
	}

	/**
	 * Retrieves a native window handle via the AWT native interface.
	 * 
	 * @param canvas
	 *            AWT Canvas of which the handle is required.
	 * 
	 * @return native window handle.
	 */
	public native long createRenderWindow(Canvas can, long root);
	public native void paint(Graphics g);
	
	public OgreAwtCanvas(GraphicsConfiguration gc)
	{
		super(gc);
	}
}
