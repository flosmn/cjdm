// Main.java
// $Id: Main.java,v 1.2 1998/01/22 13:22:54 bmahe Exp $
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.jigadm;

/**
 * a place holder for running the administration tool
 */

public class Main {

    public static void main(String[] args) {
	if(args.length == 0) {
	    String[] arg = {"http://localhost:8009/"};
	    org.w3c.jigadm.gui.ServerBrowser.main(arg);
	} else {
	    org.w3c.jigadm.gui.ServerBrowser.main(args);
	}
    }
}
