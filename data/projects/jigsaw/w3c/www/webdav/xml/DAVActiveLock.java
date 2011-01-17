// DAVActiveLock.java
// $Id: DAVActiveLock.java,v 1.6 2000/10/20 16:12:46 bmahe Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 2000.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.www.webdav.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @version $Revision: 1.6 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public class DAVActiveLock extends DAVLockEntry {

    public String getDepth() {
	return getTextChildValue(DEPTH_NODE);
    }

    public Node getOwner() {
	return getDAVNode(OWNER_NODE);
    }

    public String getTimeout() {
	return getTextChildValue(TIMEOUT_NODE);
    }

    /**
     * Get the locktoken (A array of href)
     * @return a String array
     */
    public String[] getLockToken() {
	Node n = getDAVNode(LOCKTOKEN_NODE);
	if (n != null) {
	    return getMultipleTextChildValue(n, HREF_NODE);
	}
	return null;
    }

    DAVActiveLock(Element element) {
	super(element);
    }
    
}
