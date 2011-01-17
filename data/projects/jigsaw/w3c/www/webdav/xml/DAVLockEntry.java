// DAVLockEntry.java
// $Id: DAVLockEntry.java,v 1.4 2000/10/12 16:19:20 bmahe Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 2000.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.www.webdav.xml;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * @version $Revision: 1.4 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public class DAVLockEntry extends DAVNode {

    public DAVLockType getLockType() {
	Node n = getDAVNode(LOCKTYPE_NODE);
	if (n != null) {
	    return new DAVLockType((Element)n);
	}
	return null;
    }

    public DAVLockScope getLockScope() {
	Node n = getDAVNode(LOCKSCOPE_NODE);
	if (n != null) {
	    return new DAVLockScope((Element)n);
	}
	return null;
    }

    DAVLockEntry (Element element) {
	super(element);
    }

}
