// $Id: ISocketFactory.java 306759 2005-10-06 11:39:53 +0530 (Thu, 06 Oct 2005) rana_b $
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
import java.net.ServerSocket;

import org.apache.ftpserver.ftplet.Component;


/**
 * This interface is responsible to create appropriate server socket.
 *  
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public
interface ISocketFactory extends Component {
    
    /**
     * Create the server socket. 
     */
    ServerSocket createServerSocket() throws Exception;
    
    /**
     * Get server address.
     */
    InetAddress getServerAddress();
    
    /**
     * Get server port.
     */
    int getPort();
    
    /**
     * Get SSL component.
     */
    ISsl getSSL();
}
