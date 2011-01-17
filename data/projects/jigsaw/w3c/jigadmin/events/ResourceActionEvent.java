// ResourceActionEvent.java
// $Id: ResourceActionEvent.java,v 1.7 2000/08/16 21:37:31 ylafon Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 1999.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.jigadmin.events; 

import java.util.EventObject;

/**
 * ActionEvent dedicated to Resource.
 * @version $Revision: 1.7 $
 * @author Beno�t Mah� (bmahe@w3.org)
 */
public class ResourceActionEvent extends EventObject {

    public final static int DELETE_EVENT    = 10;
    public final static int ADD_EVENT       = 20;
    public final static int REINDEX_EVENT   = 30;
    public final static int SAVE_EVENT      = 40;
    public final static int STOP_EVENT      = 50;
    public final static int REFERENCE_EVENT = 60;
    public final static int EDIT_EVENT      = 70;

    private int command = -1;

    public ResourceActionEvent(Object source, int command) {
	super(source);
	this.command = command;
    }

    public int getResourceActionCommand() {
	return command;
    }

    public String toString() {
	switch (command) {
	case DELETE_EVENT:
	    return "DELETE RESOURCES";
	case REFERENCE_EVENT:
	    return "SHOW REFERENCE DOCUMENTATION";
	case REINDEX_EVENT:
	    return "REINDEX RESOURCES";
	case SAVE_EVENT:
	    return "SAVE RESOURCES";
	case STOP_EVENT:
	    return "STOP SERVER";
	case ADD_EVENT:
	    return "ADD RESOURCE";
	case EDIT_EVENT:
	    return "EDIT RESOURCE";
	default:
	    return "UKNOWN";
	}
    }

}
