// NoSuchPropertyException.java
// $Id: NoSuchPropertyException.java,v 1.1 2000/09/22 14:20:02 bmahe Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 2000.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.www.webdav.xml;

/**
 * @version $Revision: 1.1 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public class NoSuchPropertyException extends Exception {

    public NoSuchPropertyException(String property) {
	super(property);
    }
    
}
