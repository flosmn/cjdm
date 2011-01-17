// $Id: IMessageResource.java 306759 2005-10-06 11:39:53 +0530 (Thu, 06 Oct 2005) rana_b $
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

import java.util.Properties;

import org.apache.ftpserver.ftplet.Component;
import org.apache.ftpserver.ftplet.FtpException;

/**
 * This is message resource interface.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
interface IMessageResource extends Component {

    /**
     * Get all the available languages.
     */
    String[] getAvailableLanguages();
    
    /**
     * Get the message for the corresponding code and sub id. 
     * If not found it will return null. 
     */
    String getMessage(int code, String subId, String language);
    
    /**
     * Save properties. This properties object contain all the
     * available messages. Old properties will not be overwritten.
     */
    void save(Properties prop, String language) throws FtpException;
    
    /**
     * Get all the messages.
     */
    Properties getMessages(String language);
}
