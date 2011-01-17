// AttributeEditorFactory.java
// $Id: AttributeEditorFactory.java,v 1.9 2000/08/16 21:37:26 ylafon Exp $
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.jigadm.editors ;

import java.util.Properties;
import org.w3c.jigsaw.admin.RemoteResource;
import org.w3c.tools.resources.Attribute;
import org.w3c.jigadm.PropertyManager;
import org.w3c.jigadm.RemoteResourceWrapper;

public class AttributeEditorFactory {

    /**
     * Get an editor for the Attribute element of the Resource. If the editor
     * for the specified attribute in the specified resource is not found,
     * we try to find an editor for the same attribute in the superclasses
     * of the resource. If it is still not found, we try to find an editor
     * for the superclass of the attribute in the specified resource,
     * if still not found, we iterate the process.
     * @param resource the RemoteResource which contains informations about
     * the server resource.
     * @param attribute the attribute to be edited
     * @return an AttributeEditor, or <strong>null</strong> if none found
     */

    public static synchronized
    AttributeEditor getEditor(RemoteResourceWrapper rrw, Attribute attribute) {
	RemoteResource resource = rrw.getResource();

	PropertyManager pm = PropertyManager.getPropertyManager();
	Properties props = pm.getAttributeProperties(rrw, attribute);
	String className = (String) props.get("class");
	if ( className == null ) {
	    System.out.println("can't edit "+attribute.getName()+" !");
	    return null;
	}
	try {
	    Class cls = Class.forName(className);
	    return (AttributeEditor) cls.newInstance();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return null;
    }
}
