// $Id: Configuration.java 306758 2005-10-06 11:35:13 +0530 (Thu, 06 Oct 2005) rana_b $
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

import java.util.Enumeration;

/**
 * Configuration interface. 
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
interface Configuration {

    /**
     * Get string - if not found throws FtpException.
     */
    String getString(String param) throws FtpException;

    /**
     * Get string - if not found returns the default value.
     */
    String getString(String param, String defaultVal);

    /**
     * Get integer - if not found throws FtpException.
     */
    int getInt(String param) throws FtpException;
        
    /**
     * Get integer - if not found returns the default value.
     */
    int getInt(String param, int defaultVal);
    
    /**
     * Get long - if not found throws FtpException.
     */
    long getLong(String param) throws FtpException;
    
    /**
     * Get long - if not found returns the default value.
     */
    long getLong(String param, long defaultVal);
    
    /**
     * Get boolean - if not found throws FtpException.
     */
    boolean getBoolean(String patram) throws FtpException;
    
    /**
     * Get boolean - if not found returns the default value.
     */
    boolean getBoolean(String param, boolean defaultVal);
    
    /**
     * Get double - if not found throws FtpException.
     */
    double getDouble(String param) throws FtpException;
    
    /**
     * Get double - if not found returns the default value.
     */
    double getDouble(String param, double defaultVal);

    /**
     * Get sub configuration - if not found throws FtpException.
     */
    Configuration getConfiguration(String param) throws FtpException;
    
    /**
     * Get sub configuration - if not found returns the default value.
     */
    Configuration getConfiguration(String param, Configuration defaultVal);
    
    /**
     * Get the configuration keys.
     */
    Enumeration getKeys();
}
