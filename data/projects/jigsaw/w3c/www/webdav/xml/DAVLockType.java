// DAVLockType.java
// $Id: DAVLockType.java,v 1.3 2000/10/12 16:19:20 bmahe Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 2000.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.www.webdav.xml;

import org.w3c.dom.Element;

/**
 * @version $Revision: 1.3 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public class DAVLockType extends DAVNode {
    
    public final static short UNDEFINED = 0;
    public final static short WRITE     = 1;

    public short getType() {
	if (getDAVNode(WRITE_NODE) != null) {
	    return WRITE;
	} else {
	    return UNDEFINED;
	}
    }

    DAVLockType(Element element) {
	super(element);
    }
}
