// $Id: FtpConfig.java 306760 2005-10-06 11:42:47 +0530 (Thu, 06 Oct 2005) rana_b $
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
package org.apache.ftpserver.ftplet;

import java.net.InetAddress;

import org.apache.commons.logging.LogFactory;

/**
 * A ftplet configuration object used by a ftplet container used to pass 
 * information to a ftplet during initialization. The configuration information 
 * contains initialization parameters.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
interface FtpConfig {

    /**
     * Get the log factory.
     */
    public LogFactory getLogFactory();
    
    /**
     * Get the user manager.
     */
    UserManager getUserManager();
     
    /**
     * Get file system manager
     */
    FileSystemManager getFileSystemManager();
     
    /**
     * Get ftp statistics.
     */
    FtpStatistics getFtpStatistics();
    
    /**
     * Get Ftplet.
     */
    Ftplet getFtplet(String name);
    
    /**
     * Get server address.
     */
    InetAddress getServerAddress();
        
    /**
     * Get server port.
     */ 
    int getServerPort();
}
