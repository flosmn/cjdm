// $Id: LANG.java 306732 2005-09-22 11:04:48 +0530 (Thu, 22 Sep 2005) rana_b $
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
package org.apache.ftpserver.command;

import java.io.IOException;

import org.apache.ftpserver.Command;
import org.apache.ftpserver.FtpRequestImpl;
import org.apache.ftpserver.FtpWriter;
import org.apache.ftpserver.RequestHandler;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.interfaces.IMessageResource;

/**
 * A new command "LANG" is added to the FTP command set to allow
 * server-FTP process to determine in which language to present server
 * greetings and the textual part of command responses.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class LANG implements Command {

    /**
     * Execute command.
     */
    public void execute(RequestHandler handler, 
                        FtpRequestImpl request, 
                        FtpWriter out) throws IOException, FtpException {
        
        // reset state
        request.resetState();
        
        // default language
        String language = request.getArgument();
        if(language == null) {
            request.setLanguage(null);
            out.send(200, "LANG", null);
            return;
        }
        
        // check and set language
        language = language.toLowerCase();
        IMessageResource msgResource = handler.getConfig().getMessageResource();
        String[] availableLanguages = msgResource.getAvailableLanguages();
        if(availableLanguages != null) {
            for(int i=0; i<availableLanguages.length; ++i) {
                if(availableLanguages[i].equals(language)) {
                    request.setLanguage(language);
                    out.send(200, "LANG", null);
                    return;
                }
            }
        }
        
        // not found - send error message
        out.send(504, "LANG", null);
    }
}
