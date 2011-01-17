// $Id: CvsUpdateException.java,v 1.2 2000/08/16 21:37:26 ylafon Exp $
// (c) COPYRIGHT MIT and INRIA, 1998.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.cvs;

/**
 * @version $Revision: 1.2 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public class CvsUpdateException extends CvsException {

    CvsUpdateException(String filename) {
	super(filename);
    }

}
