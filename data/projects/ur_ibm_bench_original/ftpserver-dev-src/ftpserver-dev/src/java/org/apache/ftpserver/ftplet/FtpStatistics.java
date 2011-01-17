// $Id: FtpStatistics.java 306698 2005-09-07 10:36:22 +0530 (Wed, 07 Sep 2005) rana_b $
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

import java.util.Date;

/**
 * This interface holds all the ftp server statistical information. 
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
interface FtpStatistics {

    /**
     * Get the server start time.
     */
    Date getStartTime();
    
    /**
     * Get number of files uploaded.
     */
    int getTotalUploadNumber();
    
    /**
     * Get number of files downloaded.
     */
    int getTotalDownloadNumber();
    
    /**
     * Get number of files deleted.
     */
    int getTotalDeleteNumber();
    
    /**
     * Get total number of bytes uploaded.
     */
    long getTotalUploadSize();
    
    /**
     * Get total number of bytes downloaded.
     */
    long getTotalDownloadSize();
    
    /**
     * Get total directory created.
     */
    int getTotalDirectoryCreated();
    
    /**
     * Get total directory removed.
     */
    int getTotalDirectoryRemoved();
    
    /**
     * Get total number of connections
     */
    int getTotalConnectionNumber();
    
    /**
     * Get current number of connections.
     */
    int getCurrentConnectionNumber();
    
    /**
     * Get total login number.
     */
    int getTotalLoginNumber();
    
    /**
     * Get current login number
     */
    int getCurrentLoginNumber();
    
    /**
     * Get total anonymous login number.
     */
    int getTotalAnonymousLoginNumber();
    
    /**
     * Get current anonymous login number.
     */
    int getCurrentAnonymousLoginNumber();
}
