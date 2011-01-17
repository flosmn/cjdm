// $Id: IFtpStatistics.java 306698 2005-09-07 10:36:22 +0530 (Wed, 07 Sep 2005) rana_b $
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

import org.apache.ftpserver.ftplet.Component;
import org.apache.ftpserver.ftplet.FileObject;
import org.apache.ftpserver.ftplet.FtpStatistics;

/**
 * This is same as <code>org.apache.ftpserver.ftplet.FtpStatistics</code>
 * with added observer and setting values functionalities.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
interface IFtpStatistics extends FtpStatistics, Component {

    /**
     * Set statistics observer.
     */
    void setObserver(StatisticsObserver observer);
    
    /**
     * Set file observer.
     */
    void setFileObserver(FileObserver observer);
    
    /**
     * Increment upload count.
     */
    void setUpload(IConnection connection, FileObject file, long size);
    
    /**
     * Increment download count.
     */
    void setDownload(IConnection connection, FileObject file, long size);
    
    /**
     * Increment make directory count.
     */
    void setMkdir(IConnection connection, FileObject dir);
    
    /**
     * Decrement remove directory count.
     */
    void setRmdir(IConnection connection, FileObject dir) ;
    
    /**
     * Increment delete count.
     */
    void setDelete(IConnection connection, FileObject file);
    
    /**
     * Increment current connection count.
     */
    void setOpenConnection(IConnection connection);
    
    /**
     * Decrement close connection count.
     */
    void setCloseConnection(IConnection connection);
    
    /**
     * Increment current login count.
     */
    void setLogin(IConnection connection);
    
    /**
     * Decrement current login count.
     */
    void setLogout(IConnection connection);
}
