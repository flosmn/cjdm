// $Id: PROT.java 306757 2005-10-06 11:33:33 +0530 (Thu, 06 Oct 2005) rana_b $
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
import org.apache.ftpserver.FtpDataConnection;
import org.apache.ftpserver.FtpRequestImpl;
import org.apache.ftpserver.FtpWriter;
import org.apache.ftpserver.RequestHandler;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.interfaces.IFtpConfig;

/**
 * Data channel protection level.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class PROT implements Command {

    /**
     * Execute command.
     */
    public void execute(RequestHandler handler,
                        FtpRequestImpl request, 
                        FtpWriter out) throws IOException, FtpException {
    
        // reset state variables
        request.resetState();
        
        // check argument
        String arg = request.getArgument();
        if(arg == null) {
            out.send(501, "PROT", null);
            return;
        }
        
        // check argument
        arg = arg.toUpperCase();
        FtpDataConnection dcon = request.getFtpDataConnection();
        if(arg.equals("C")) {
            dcon.setSecure(false);
            out.send(200, "PROT", null);
        }
        else if(arg.equals("P")) {
            IFtpConfig fconfig = handler.getConfig();
            if(fconfig.getDataConnectionConfig().getSSL() == null) {
                out.send(431, "PROT", null);
            }
            else {
                dcon.setSecure(true);
                out.send(200, "PROT", null);
            }
        }
        else {
            out.send(504, "PROT", null);
        }
    }
    
}
