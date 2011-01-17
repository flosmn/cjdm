// $Id: MDTM.java 306721 2005-09-09 16:53:09 +0530 (Fri, 09 Sep 2005) rana_b $
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
import org.apache.ftpserver.ftplet.FileObject;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.util.DateUtils;

/**
 * <code>MDTM &lt;SP&gt; &lt;pathname&gt; &lt;CRLF&gt;</code><br>
 * 
 * Returns the date and time of when a file was modified.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class MDTM implements Command {

    /**
     * Execute command
     */
    public void execute(RequestHandler handler,
                        FtpRequestImpl request, 
                        FtpWriter out) throws IOException, FtpException {
        
        // reset state
        request.resetState();
        
        // argument check
        String fileName = request.getArgument();
        if(fileName == null) {
            out.send(501, "MDTM", null);
            return;  
        }
        
        // get file object
        FileObject file = null;
        try {
            file = request.getFileSystemView().getFileObject(fileName);
        }
        catch(Exception ex) {
        }
        if(file == null) {
            out.send(550, "MDTM", fileName);
            return;
        }
        
        // now print date
        fileName = file.getFullName();
        if(file.doesExist()) {
            String dateStr = DateUtils.getFtpDate( file.getLastModified() );
            out.send(213, "MDTM", dateStr);
        }
        else {
            out.send(550, "MDTM", fileName);
        }
    } 
}
