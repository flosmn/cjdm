// $Id: SITE_STAT.java 306757 2005-10-06 11:33:33 +0530 (Thu, 06 Oct 2005) rana_b $
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
import org.apache.ftpserver.ftplet.FtpStatistics;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.util.DateUtils;

/**
 * Show all statistics information.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class SITE_STAT implements Command {

    /**
     * Execute command.
     */
    public void execute(RequestHandler handler,
            FtpRequestImpl request, 
            FtpWriter out) throws IOException, FtpException {
    
        // reset state variables
        request.resetState();
        
        // only administrator can execute this
        UserManager userManager = handler.getConfig().getUserManager(); 
        boolean isAdmin = userManager.isAdmin(request.getUser().getName());
        if(!isAdmin) {
            out.send(530, "SITE", null);
            return;
        }
        
        // get statistics information
        FtpStatistics stat = handler.getConfig().getFtpStatistics();
        StringBuffer sb = new StringBuffer(256);
        sb.append('\n');
        sb.append("Start Time               : ").append( DateUtils.getISO8601Date(stat.getStartTime().getTime()) ).append('\n');
        sb.append("File Upload Number       : ").append( stat.getTotalUploadNumber() ).append('\n');
        sb.append("File Download Number     : ").append( stat.getTotalDownloadNumber() ).append('\n');
        sb.append("File Delete Number       : ").append( stat.getTotalDeleteNumber() ).append('\n');
        sb.append("File Upload Bytes        : ").append( stat.getTotalUploadSize() ).append('\n');
        sb.append("File Download Bytes      : ").append( stat.getTotalDownloadSize() ).append('\n');
        sb.append("Directory Create Number  : ").append( stat.getTotalDirectoryCreated() ).append('\n');
        sb.append("Directory Remove Number  : ").append( stat.getTotalDirectoryRemoved() ).append('\n');
        sb.append("Current Logins           : ").append( stat.getCurrentLoginNumber() ).append('\n');
        sb.append("Total Logins             : ").append( stat.getTotalLoginNumber() ).append('\n');
        sb.append("Current Anonymous Logins : ").append( stat.getCurrentAnonymousLoginNumber() ).append('\n');
        sb.append("Total Anonymous Logins   : ").append( stat.getTotalAnonymousLoginNumber() ).append('\n');
        sb.append("Current Connections      : ").append( stat.getCurrentConnectionNumber() ).append('\n');
        sb.append("Total Connections        : ").append( stat.getTotalConnectionNumber() ).append('\n');
        sb.append('\n');
        out.write(200, sb.toString());
    }
    
}
