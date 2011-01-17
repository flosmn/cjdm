// RemoteNode.java
// $Id: RemoteNode.java,v 1.5 2000/08/16 21:37:30 ylafon Exp $
// (c) COPYRIGHT MIT and INRIA, 1998.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.jigadmin.editors;

/**
 * The interface for remote nodes.
 * @version $Revision: 1.5 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public interface RemoteNode {

    /**
     * Invoked whenever this node is about to be expanded.
     */  
    public void nodeWillExpand();

    /**
     * Invoked whenever this node is about to be collapsed.
     */
    public void nodeWillCollapse();

}
