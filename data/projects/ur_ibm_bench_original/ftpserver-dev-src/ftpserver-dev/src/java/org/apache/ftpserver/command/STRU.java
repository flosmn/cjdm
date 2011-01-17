// $Id: STRU.java 328636 2005-10-26 17:38:10 +0530 (Wed, 26 Oct 2005) rana_b $
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
import org.apache.ftpserver.ftplet.Structure;

/**
 * <code>STRU &lt;SP&gt; &lt;structure-code&gt; &lt;CRLF&gt;</code><br>
 *
 * The argument is a single Telnet character code specifying
 * file structure.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class STRU implements Command {
    
    /**
     * Execute command
     */
    public void execute(RequestHandler handler, 
                        FtpRequestImpl request, 
                        FtpWriter out) throws IOException {
        
        // reset state variables
        request.resetState();
        
        // argument check
        if(!request.hasArgument()) {
            out.send(501, "STRU", null);
            return;  
        }
        
        // set structure
        char stru = request.getArgument().charAt(0);
        try  {
            handler.setStructure(Structure.parseArgument(stru));
            out.send(200, "STRU", null);
        } 
        catch(IllegalArgumentException e) {
            out.send(504, "STRU", null);
        }
    }

}
