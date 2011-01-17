// $Id: IoUtils.java 306756 2005-10-06 11:30:03 +0530 (Thu, 06 Oct 2005) rana_b $
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
package org.apache.ftpserver.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Random;

/**
 * IO utility methods.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public
class IoUtils {
    
    /**
     * Random number generator to make unique file name
     */
    private final static Random RANDOM_GEN = new Random(System.currentTimeMillis());
     
    
    /**
     * Get a <code>BufferedInputStream</code>. 
     */
    public final static BufferedInputStream getBufferedInputStream(InputStream in) {
        BufferedInputStream bin = null;
        if(in instanceof java.io.BufferedInputStream) {
            bin = (BufferedInputStream)in;
        }
        else {
            bin = new BufferedInputStream(in);
        }
        return bin;
    }
    
    /**
     * Get a <code>BufferedOutputStream</code>. 
     */
    public final static BufferedOutputStream getBufferedOutputStream(OutputStream out) {
        BufferedOutputStream bout = null;
        if(out instanceof java.io.BufferedOutputStream) {
            bout = (BufferedOutputStream)out;
        }
        else {
            bout = new BufferedOutputStream(out);
        }
        return bout;
    }
    
    /**
     * Get <code>BufferedReader</code>.
     */
    public final static BufferedReader getBufferedReader(Reader reader) {
        BufferedReader buffered = null;
        if (reader instanceof java.io.BufferedReader) {
            buffered = (BufferedReader) reader;
        } 
        else {
            buffered = new BufferedReader(reader);
        }
        return buffered;
    }
    
    
    /**
     * Get <code>BufferedWriter</code>.
     */
    public final static BufferedWriter getBufferedWriter(Writer wr) {
        BufferedWriter bw = null;
        if(wr instanceof java.io.BufferedWriter) {
            bw = (BufferedWriter)wr;
        }
        else {
            bw = new BufferedWriter(wr);
        }
        return bw;
    }

    /**
     * Get unique file object.
     */
    public final static File getUniqueFile(File oldFile) {
        File newFile = oldFile;
        while (true) {
            if (!newFile.exists()) {
                break;
            }
            newFile = new File(oldFile.getAbsolutePath() + '.' + Math.abs(RANDOM_GEN.nextLong()));
        }
        return newFile;
    }
   
    /**
     * No exception <code>InputStream</code> close method.
     */
    public final static void close(InputStream is) {
        if(is != null) {
            try { is.close(); } catch(Exception ex) {}
        }
    } 
   
    /**
     * No exception <code>OutputStream</code> close method.
     */
    public final static void close(OutputStream os) {
        if(os != null) {
            try { os.close(); } catch(Exception ex) {}
        }
    }
   
    /**
     * No exception <code>java.io.Reader</code> close method.
     */
    public final static void close(Reader rd) {
        if(rd != null) {
            try { rd.close(); } catch(Exception ex) {}
        }
    }
   
   
    /**
     * No exception <code>java.io.Writer</code> close method.
     */
    public final static void close(Writer wr) {
        if(wr != null) {
            try { wr.close(); } catch(Exception ex) {}
        }
    }
   
   
    /**
     * Get exception stack trace.
     */
    public final static String getStackTrace(Throwable ex) {
        String result = "";
        if(ex != null) {
            try  {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                pw.close();
                sw.close();
                result = sw.toString();
            }
            catch(Exception e)  {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * Copy chars from a <code>Reader</code> to a <code>Writer</code>.
     * @param bufferSize Size of internal buffer to use.
     */
    public final static void copy(Reader input, Writer output, int bufferSize ) throws IOException {
        char buffer[] = new char[bufferSize];
        int n = 0;
        while( (n=input.read(buffer)) != -1) {
            output.write(buffer, 0, n);
        }
    } 
    
    /**
     * Copy chars from a <code>InputStream</code> to a <code>OutputStream</code>.
     * @param bufferSize Size of internal buffer to use.
     */
    public final static void copy(InputStream input, OutputStream output, int bufferSize ) throws IOException {
        byte buffer[] = new byte[bufferSize];
        int n = 0;
        while( (n=input.read(buffer)) != -1) {
            output.write(buffer, 0, n);
        }
    }
    
    /**
     * Read fully from reader
     */
    public final static String readFully(Reader reader) throws IOException {
        StringWriter writer = new StringWriter();
        copy(reader, writer, 1024);
        return writer.toString();
    }
    
    /**
     * Read fully from stream
     */ 
    public final static String readFully(InputStream input) throws IOException {
        StringWriter writer = new StringWriter();
        InputStreamReader reader = new InputStreamReader(input);
        copy(reader, writer, 1024);
        return writer.toString();
    } 
}    
