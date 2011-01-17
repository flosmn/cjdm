// $Id: ABOR.java 306757 2005-10-06 11:33:33 +0530 (Thu, 06 Oct 2005) rana_b $
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

/**
 * <code>ABOR &lt;CRLF&gt;</code><br>
 *
 * This command tells the server to abort the previous FTP
 * service command and any associated transfer of data.
 * No action is to be taken if the previous command
 * has been completed (including data transfer).  The control
 * connection is not to be closed by the server, but the data
 * connection must be closed.  
 * Current implementation does not do anything. As here data 
 * transfers are not multi-threaded. 
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class ABOR implements Command {

    /**
     * Execute command
     */
    public void execute(RequestHandler handler,
                        FtpRequestImpl request, 
                        FtpWriter out) throws IOException {
        
        // reset state variables
        request.resetState();
        
        // and abort any data connection
        request.getFtpDataConnection().closeDataSocket();
        out.send(226, "ABOR", null);
    }   
}
