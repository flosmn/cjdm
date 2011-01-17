// HolderInitException.java
// $Id: HolderInitException.java,v 1.1 1998/01/22 12:55:55 bmahe Exp $
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.tools.resources ;

/**
 * Exception throw when unable to restore an attribute holder.
 */

public class HolderInitException extends RuntimeException {

    public HolderInitException(String msg) {
	super(msg) ;
    }

}
