// ResourceTreeUI.java
// $Id: ResourceTreeUI.java,v 1.3 2000/08/16 21:37:31 ylafon Exp $
// (c) COPYRIGHT MIT and INRIA, 1998.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.jigadmin.editors;

import javax.swing.plaf.basic.BasicTreeUI;

import java.awt.event.MouseEvent;

/**
 * The UI of the ResourceTreeBrowser
 * @version $Revision: 1.3 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public class ResourceTreeUI extends BasicTreeUI {

    /**
     * return always false to disable expands on double click.
     */
    protected boolean isToggleEvent(MouseEvent event) {
	return false;
    }

}
