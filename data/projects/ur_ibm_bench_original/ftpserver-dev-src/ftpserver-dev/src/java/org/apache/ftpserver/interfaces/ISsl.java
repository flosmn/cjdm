// $Id: ISsl.java 306744 2005-10-04 12:58:02 +0530 (Tue, 04 Oct 2005) rana_b $
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
import java.net.Socket;

import org.apache.ftpserver.ftplet.Component;

/**
 * SSL interface.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
interface ISsl extends Component {

    /**
     * Create secure server socket.
     */
    ServerSocket createServerSocket(String protocol, 
                                    InetAddress addr, 
                                    int port) throws Exception;
    
    /**
     * Returns a socket layered over an existing socket.
     */
    Socket createSocket(String protocol,
                        Socket soc, 
                        boolean clientMode) throws Exception;
    
    /**
     * Create a secure socket.
     */
    Socket createSocket(String protocol,
                        InetAddress addr, 
                        int port,
                        boolean clientMode) throws Exception;
}
