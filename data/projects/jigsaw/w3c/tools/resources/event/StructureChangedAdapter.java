// StructureChangedAdapter.java
// $Id: StructureChangedAdapter.java,v 1.2 2000/08/16 21:37:53 ylafon Exp $
// (c) COPYRIGHT MIT and INRIA, 1998.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.tools.resources.event;

/**
 * @version $Revision: 1.2 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public class StructureChangedAdapter implements StructureChangedListener {

    /**
     * This handles the <code>RESOURCE_MODIFIED</code> kind of events.
     * @param evt The StructureChangeEvent.
     */

    public void resourceModified(StructureChangedEvent evt){ }

    /**
     * A new resource has been created in some space.
     * This handles the <code>RESOURCE_CREATED</code> kind of events.
     * @param evt The event describing the change.
     */

    public void resourceCreated(StructureChangedEvent evt){ }

    /**
     * A resource is about to be removed
     * This handles the <code>RESOURCE_REMOVED</code> kind of events.
     * @param evt The event describing the change.
     */

    public void resourceRemoved(StructureChangedEvent evt){ }

    /**
     * A resource is about to be unloaded
     * This handles the <code>RESOURCE_UNLOADED</code> kind of events.
     * @param evt The event describing the change.
     */

    public void resourceUnloaded(StructureChangedEvent evt){ }

   
}
