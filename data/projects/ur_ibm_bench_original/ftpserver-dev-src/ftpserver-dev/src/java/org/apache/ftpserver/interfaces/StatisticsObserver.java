// $Id: StatisticsObserver.java 306698 2005-09-07 10:36:22 +0530 (Wed, 07 Sep 2005) rana_b $
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

/**
 * Ftp statistics observer interface.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
interface StatisticsObserver {
    
    /**
     * User file upload notification.
     */
    void notifyUpload();
    
    /**
     * User file download notification.
     */
    void notifyDownload();
    
    /**
     * User file delete notification.
     */
    void notifyDelete();
     
    /**
     * User make directory notification.
     */
    void notifyMkdir();
    
    /**
     * User remove directory notification.
     */
    void notifyRmdir();
    
    /**
     * New user login notification.
     */
    void notifyLogin(boolean anonymous);
    
    /**
     * User logout notification.
     */
    void notifyLogout(boolean anonymous);
    
    /**
     * Connection open notification
     */
    void notifyOpenConnection(); 
    
    /**
     * Connection close notification
     */
    void notifyCloseConnection();
}    
