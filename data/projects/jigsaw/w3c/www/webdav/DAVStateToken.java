// DAVStateToken.java
// $Id: DAVStateToken.java,v 1.2 2000/10/20 16:12:46 bmahe Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 2000.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.www.webdav;

/**
 * @version $Revision: 1.2 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public class DAVStateToken {

    boolean isnot = false;
    String  token = null;

    public boolean isNot() {
	return isnot;
    }

    public String getStateToken() {
	return token;
    }

    public String toString() {
	StringBuffer buffer = new StringBuffer();
	if (isnot) {
	    buffer.append("Not ");
	}
	buffer.append("<").append(token).append(">");
	return buffer.toString();
    }

    public DAVStateToken(String token, boolean isnot) {
	this.token = token;
	this.isnot = isnot;
    }
    
    
}
