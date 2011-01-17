// InvalidDAVBodyException.java
// $Id: InvalidDAVBodyException.java,v 1.1 2000/09/21 15:55:40 bmahe Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 2000.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.www.webdav;

/**
 * @version $Revision: 1.1 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public class InvalidDAVBodyException extends Exception {

    public InvalidDAVBodyException(String msg) {
	super(msg);
    }
    
}
