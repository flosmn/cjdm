// $Id: OSVirualFileSystemManager.java 306760 2005-10-06 11:42:47 +0530 (Thu, 06 Oct 2005) rana_b $
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
package org.apache.ftpserver.filesystem;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ftpserver.ftplet.Configuration;
import org.apache.ftpserver.ftplet.FileSystemManager;
import org.apache.ftpserver.ftplet.FileSystemView;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;

/**
 * This is a operating system based virtual root file system manager. 
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class OSVirualFileSystemManager implements FileSystemManager {  
    
    private Log m_log;
    private LogFactory m_logFactory;
    private boolean m_createHome;
    
    
    /**
     * Set the log factory.
     */
    public void setLogFactory(LogFactory factory) {
        m_logFactory = factory;
        m_log = m_logFactory.getInstance(getClass());
    }
    
    /**
     * Configure the file system manager - does nothing.
     */
    public void configure(Configuration conf) throws FtpException {
        m_createHome = conf.getBoolean("create-home", false);
    }
    
    /**
     * Dispose the file system manager.
     */
    public void dispose() {
    }
    
    /**
     * Create the appropriate user file system view.
     */
    public FileSystemView createFileSystemView(User user) throws FtpException {
        
        // create home if does not exist
        if(m_createHome) {
            String homeDirStr = user.getHomeDirectory();
            File homeDir = new File(homeDirStr);
            if(homeDir.isFile()) {
                m_log.warn("Not a directory :: " + homeDirStr);
                throw new FtpException("Not a directory :: " + homeDirStr);
            }
            if( (!homeDir.exists()) && (!homeDir.mkdirs()) ) {
                m_log.warn("Cannot create user home :: " + homeDirStr);
                throw new FtpException("Cannot create user home :: " + homeDirStr);
            }
        }
        
        OSVirualFileSystemView fsView = new OSVirualFileSystemView(user, m_logFactory);
        return fsView;
    }   
}
