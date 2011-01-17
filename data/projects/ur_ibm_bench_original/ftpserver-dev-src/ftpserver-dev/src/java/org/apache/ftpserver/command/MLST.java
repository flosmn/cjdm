// $Id: MLST.java 306717 2005-09-09 16:49:47 +0530 (Fri, 09 Sep 2005) rana_b $
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
import java.io.StringWriter;

import org.apache.ftpserver.Command;
import org.apache.ftpserver.DirectoryLister;
import org.apache.ftpserver.FtpRequestImpl;
import org.apache.ftpserver.FtpWriter;
import org.apache.ftpserver.RequestHandler;

/**
 * <code>MLST &lt;SP&gt; &lt;pathname&gt; &lt;CRLF&gt;</code><br>
 *
 * Returns info on the file over the control connection.
 * 
 * @author Birkir A. Barkarson
 */
public 
class MLST implements Command {

    /**
     * Execute command.
     */
    public void execute(RequestHandler handler,
                        FtpRequestImpl request, 
                        FtpWriter out) throws IOException {
        
        // reset state variables
        request.resetState();
        
        // print file information
        DirectoryLister dirLister = handler.getDirectoryLister();
        StringWriter sw = new StringWriter();
        if(!dirLister.doMLST(request.getArgument(), sw)) {
            out.send(501, "MLST", null);
        }
        else {
            String output = sw.toString();
            out.send(250, "MLST", output);
        }
        sw.close();
    }   
}
