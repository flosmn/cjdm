// $Id: IConnectionManager.java 306698 2005-09-07 10:36:22 +0530 (Wed, 07 Sep 2005) rana_b $
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

import java.util.List;

import org.apache.ftpserver.ftplet.Component;

/**
 * It manages all the ftp connections.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public
interface IConnectionManager extends Component {

    /**
     * Get maximum number of connections.
     */
    int getMaxConnections();
     
    /**
     * Get maximum number of logins.
     */
    int getMaxLogins();
     
    
    /**
     * Is anonymous login enabled?
     */
    boolean isAnonymousLoginEnabled();
    
    
    /**
     * Get maximum anonymous logins
     */
    int getMaxAnonymousLogins();

    
    /**
     * Get all request handlers.
     */
    List getAllConnections();
    
    /**
     * Establish a new connection channel.
     */
    void newConnection(IConnection connection); 
    
    
    /**
     * Update connection.
     */
    void updateConnection(IConnection connection);
    
    
    /**
     * Close a connection.
     */
    void closeConnection(IConnection connection);
    
    
    /**
     * Close all connections.
     */
    void closeAllConnections();
    
    
    /**
     * Set connection manager observer.
     */
    void setObserver(ConnectionManagerObserver observer);
} 
