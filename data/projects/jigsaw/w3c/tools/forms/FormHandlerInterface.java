// FormHandlerInterface.java
// $Id: FormHandlerInterface.java,v 1.3 2000/08/16 21:37:49 ylafon Exp $
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.tools.forms ;

public interface FormHandlerInterface {

    /**
     * The field whose name is given has changed.
     */

    public void fieldChanged(String name) ;

    /**
     * Is the given value appropriate for the given field ?
     */

    public boolean acceptFieldValue(String name, Object value) ;

    /**
     * The whole form has been validated.
     */

    public void validate() ;

}
