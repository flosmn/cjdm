// $Id: HELP.java 306732 2005-09-22 11:04:48 +0530 (Thu, 22 Sep 2005) rana_b $
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
import org.apache.ftpserver.interfaces.IMessageResource;

/**
 * <code>HELP [&lt;SP&gt; <string>] &lt;CRLF&gt;</code><br>
 *
 * This command shall cause the server to send helpful
 * information regarding its implementation status over the
 * control connection to the user.  The command may take an
 * argument (e.g., any command name) and return more specific
 * information as a response.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class HELP implements Command {
    
    /**
     * Execute command.
     */
    public void execute(RequestHandler handler,
                        FtpRequestImpl request, 
                        FtpWriter out) throws IOException {
        
        // reset state variables
        request.resetState();
        
        // print global help
        if(!request.hasArgument()) {
            out.send(214, null, null);
            return;
        }
        
        // print command specific help if available
        String ftpCmd = request.getArgument().toUpperCase();
        IMessageResource resource = handler.getConfig().getMessageResource();
        if(resource.getMessage(214, ftpCmd, request.getLanguage()) == null) {
            ftpCmd = null;
        }
        out.send(214, ftpCmd, null);
    }
}
