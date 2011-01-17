// AclPrincipal.java
// $Id: AclPrincipal.java,v 1.5 2000/08/16 21:37:33 ylafon Exp $
// (c) COPYRIGHT MIT, INRIA and Keio, 1999.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.jigsaw.acl;

import java.security.Principal;
import java.net.InetAddress;

/**
 * @version $Revision: 1.5 $
 * @author  Beno�t Mah� (bmahe@w3.org)
 */
public interface AclPrincipal extends Principal {

    /**
     * Get the realm associated to this principal.(could be his group)
     * @return the realm name.
     */
    public String getRealm();

    /**
     * Get the password associated to this principal.
     * @return the password
     */
    public String getPassword();

    /**
     * Set a parameter.
     * @param name the parameter name
     * @param value the parameter value
     */
    public void setValue(String name, Object value);

    /**
     * Get a parameter value.
     * @param name the parameter name.
     * @return the parameter value
     */
    public Object getValue(String name);

    /**
     * Return true if the IPadress associated to this principal match
     * the given one.
     * @param adr an IP adress
     * @return true if the ip adress match.
     */
    public boolean matchIP(InetAddress adr);

}


