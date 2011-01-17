// $Id: IIpRestrictor.java 306759 2005-10-06 11:39:53 +0530 (Thu, 06 Oct 2005) rana_b $
/*
 * Copyright 2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ftpserver.interfaces;

import java.net.InetAddress;

import org.apache.ftpserver.ftplet.Component;
import org.apache.ftpserver.ftplet.FtpException;


/**
 * Ip restrictor interface.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public
interface IIpRestrictor extends Component {
    
    /**
     * Check IP permission.
     */
    boolean hasPermission(InetAddress address) throws FtpException;
    
    
    /**
     * Get IP and permission {String, Boolean} array.
     */
    Object[][] getPermissions() throws FtpException;
    
    
    /**
     * Set IP and permission {String, Boolean} array.
     */
    void setPermissions(Object[][] permissions) throws FtpException;
}
