// $Id: LIST.java 306757 2005-10-06 11:33:33 +0530 (Thu, 06 Oct 2005) rana_b $
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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.SocketException;

import org.apache.ftpserver.Command;
import org.apache.ftpserver.DirectoryLister;
import org.apache.ftpserver.FtpRequestImpl;
import org.apache.ftpserver.FtpWriter;
import org.apache.ftpserver.RequestHandler;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.util.IoUtils;

/**
 * <code>LIST [&lt;SP&gt; &lt;pathname&gt;] &lt;CRLF&gt;</code><br>
 *
 * This command causes a list to be sent from the server to the
 * passive DTP.  If the pathname specifies a directory or other
 * group of files, the server should transfer a list of files
 * in the specified directory.  If the pathname specifies a
 * file then the server should send current information on the
 * file.  A null argument implies the user's current working or
 * default directory.  The data transfer is over the data
 * connection.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class LIST implements Command {
    
    /**
     * Execute command.
     */
    public void execute(RequestHandler handler,
                        FtpRequestImpl request, 
                        FtpWriter out) throws IOException, FtpException {
        
        try {
        
            // reset state variables
            request.resetState();
            
            // get data connection
            out.send(150, "LIST", null);
            OutputStream os = null;
            try {
                os = request.getDataOutputStream();
            }
            catch(IOException ex) {
                out.send(425, "LIST", null);
                return;
            }
            
            // transfer listing data
            boolean failure = false;
            boolean syntaxError = false;
            Writer writer = null;
            try {
            
                // open stream
                writer = new OutputStreamWriter(os, "UTF-8");
                
                // transfer data
                DirectoryLister dirLister = handler.getDirectoryLister();
                syntaxError = !dirLister.doLIST(request.getArgument(), writer);
            }
            catch(SocketException ex) {
                ex.printStackTrace();
                failure = true;
                out.send(426, "LIST", null);
            }
            catch(IOException ex) {
                ex.printStackTrace();
                failure = true;
                out.send(551, "LIST", null);
            }
            finally {
                IoUtils.close(writer);
            }
            
            // if listing syntax error - send message
            if(syntaxError) {
                out.send(501, "LIST", null);
            }
            
            // if data transfer ok - send transfer complete message
            if(!failure) {
                out.send(226, "LIST", null);
            }
        }
        finally {
            request.getFtpDataConnection().closeDataSocket();
        }
    }

}
