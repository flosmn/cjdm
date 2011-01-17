// DAVPropStat.java
// $Id: DAVPropStat.java,v 1.5 2000/10/12 16:19:20 bmahe Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 2000.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.www.webdav.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @version $Revision: 1.5 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public class DAVPropStat extends DAVNode {

    public String getStatus() {
	return getTextChildValue(STATUS_NODE);
    }

    public void setStatus(String line) {
	addDAVNode(STATUS_NODE, line);
    }

    public String getResponseDescription() {
	return getTextChildValue(RESPONSEDESC_NODE);
    }

    public DAVProperties getProps() {
	Node node = getDAVNode(PROP_NODE);
	if (node != null) {
	    return new DAVProperties((Element)node);
	}
	return null;
    }

    DAVPropStat(Element element) {
	super(element);
    }
    
}
