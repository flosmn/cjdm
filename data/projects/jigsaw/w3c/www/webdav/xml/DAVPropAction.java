// DAVPropAction.java
// $Id: DAVPropAction.java,v 1.2 2000/10/12 16:19:20 bmahe Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 2000.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.www.webdav.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @version $Revision: 1.2 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public class DAVPropAction extends DAVNode {
    public final static int UNDEFINED = -1;
    public final static int SET       = 1;
    public final static int REMOVE    = 2;

    int action = UNDEFINED;

    public int getAction() {
	if (action == UNDEFINED) {
	    if (element.getLocalName().equals(SET_NODE)) {
		action = SET;
	    } else if (element.getLocalName().equals(REMOVE_NODE)) {
		action = REMOVE;
	    } 
	}
	return action;
    }

    public DAVProperties getProperties() {
	Element n = (Element)getDAVNode(PROP_NODE);
	if (n != null) {
	    return new DAVProperties(n);
	}
	return null;
    }

    DAVPropAction(Element element) {
	super(element);
    }

}
