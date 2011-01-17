// IntegerTextField.java
// $Id: IntegerTextField.java,v 1.4 2000/08/16 21:37:31 ylafon Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 1999.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.jigadmin.widgets;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

/**
 * A TextField that accepts only num char.
 * @version $Revision: 1.4 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public class IntegerTextField extends JTextField {

    protected void processComponentKeyEvent(KeyEvent ke) {
	if(ke.getKeyCode() == KeyEvent.VK_DELETE ||
	       ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
	       ke.getKeyCode() == KeyEvent.VK_UP ||
	       ke.getKeyCode() == KeyEvent.VK_DOWN ||
	       ke.getKeyCode() == KeyEvent.VK_LEFT ||
	       ke.getKeyCode() == KeyEvent.VK_RIGHT)
	    super.processComponentKeyEvent(ke);
	char c = ke.getKeyChar();
	if(!((c >= '0' && c <= '9') || (c == '-'))) {
	    ke.consume();
	} else {
	    super.processComponentKeyEvent(ke);
	}
    }

    public IntegerTextField() {
	super();
    }

    public IntegerTextField(int cols) {
	super(cols);
    }

}
