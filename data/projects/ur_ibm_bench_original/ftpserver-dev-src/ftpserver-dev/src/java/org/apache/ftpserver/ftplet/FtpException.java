// $Id: FtpException.java 306698 2005-09-07 10:36:22 +0530 (Wed, 07 Sep 2005) rana_b $
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

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Ftplet exception class.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class FtpException extends Exception {

    private static final long serialVersionUID = -1328383839915898987L;
    
    private Throwable m_throwable = null;

    /**
     * Default constructor.
     */
    public FtpException() {
        super();
    }

    /**
     * Constructs a <code>FtpException</code> object with a message.
     * 
     * @param msg a description of the exception 
     */
    public FtpException(String msg) {
        super(msg);
    }

    /**
     * Constructs a <code>FtpException</code> object with a 
     * <code>Throwable</code> cause.
     * 
     * @param th the original cause
     */
    public FtpException(Throwable th) {
        super(th.getMessage());
        m_throwable = th;
    }

    /**
     * Constructs a <code>BaseException</code> object with a 
     * <code>Throwable</code> cause.
     * 
     * @param th the original cause
     */
    public FtpException(String msg, Throwable th) {
        super(msg);
        m_throwable = th;
    }
    
    /**
     * Get the root cause.
     */
    public Throwable getRootCause() {
        return m_throwable;
    }
    
    /**
     * Print stack trace.
     */
    public void printStackTrace(PrintWriter pw) {
        if(m_throwable == null) {
            super.printStackTrace(pw);
        }
        else {
            m_throwable.printStackTrace(pw);
        }
    }
    
    /**
     * Print stack trace.
     */
    public void printStackTrace(PrintStream ps) {
        if(m_throwable == null) {
            super.printStackTrace(ps);
        }
        else {
            m_throwable.printStackTrace(ps);
        }
    }
    
    /**
     * Print stack trace.
     */
    public void printStackTrace() {
        if(m_throwable == null) {
            super.printStackTrace();
        }
        else {
            m_throwable.printStackTrace();
        }
    }
}
