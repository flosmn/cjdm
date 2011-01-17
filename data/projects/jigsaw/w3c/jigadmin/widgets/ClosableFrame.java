// ClosableFrame.java
// $Id: ClosableFrame.java,v 1.4 2000/08/16 21:37:31 ylafon Exp $
// (c) COPYRIGHT MIT and INRIA, 1998.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.jigadmin.widgets;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * A Frame that handles windowClosing event.
 * @version $Revision: 1.4 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
abstract public class ClosableFrame extends JFrame {

    /**
     * Our internal WindowAdapter
     */
    WindowAdapter wl = new WindowAdapter() {
	public void windowClosing(WindowEvent e) {
	    if (e.getWindow() == ClosableFrame.this)
	        close();
	}
    };

    /**
     * The dialog is about to be closed
     */
    protected abstract void close();

    /**
     * Constructor
     */
    public ClosableFrame() {
	super();
	build();
    }

    /**
     * Constructor
     * @param title The frame title.
     */
    public ClosableFrame(String title) {
	super(title);
	build();
    }

    private void build() {
	addWindowListener(wl);
    }

}
