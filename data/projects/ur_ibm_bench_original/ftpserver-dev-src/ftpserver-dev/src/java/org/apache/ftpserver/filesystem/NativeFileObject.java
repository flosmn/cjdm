// $Id: NativeFileObject.java 306716 2005-09-09 16:48:17 +0530 (Fri, 09 Sep 2005) rana_b $
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
package org.apache.ftpserver.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

import org.apache.ftpserver.ftplet.FileObject;

/**
 * This class wraps native file object. 
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class NativeFileObject implements FileObject {

    // the file name with respect to the user root.
    // The path separator character will be '/' and
    // it will always begin with '/'.
    private String m_fileName;
    
    private File m_file;
    private boolean m_writePermission;
    
    
    /**
     * Constructor.
     */
    protected NativeFileObject(String fileName, File file, boolean writePerm) {
        m_fileName = fileName;
        m_file = file;
        m_writePermission = writePerm;
    }
    
    /**
     * Get full name.
     */
    public String getFullName() {
        
        // strip the last '/' if necessary
        String fullName = m_fileName;
        int filelen = fullName.length();
        if( (filelen != 1)&& (fullName.charAt(filelen - 1) == '/') ) {
            fullName = fullName.substring(0, filelen - 1);
        }
        
        return fullName;
    }
    
    /**
     * Get short name.
     */
    public String getShortName() {
        
        // root - the short name will be '/'
        if(m_fileName.equals("/")) {
            return "/";
        }
        
        // strip the last '/'
        String shortName = m_fileName;
        int filelen = m_fileName.length();
        if(m_fileName.charAt(filelen - 1) == '/') {
            shortName = shortName.substring(0, filelen - 1);
        }
        
        // return from the last '/'
        int slashIndex = m_fileName.lastIndexOf('/');
        if(slashIndex != -1) {
            shortName = shortName.substring(slashIndex + 1);
        }
        return shortName;
    }
    
    /**
     * Is a hidden file?
     */
    public boolean isHidden() {
        return m_file.isHidden();
    }
     
    /**
     * Is it a directory?
     */
    public boolean isDirectory() {
        return m_file.isDirectory();
    }
    
    /**
     * Is it a file?
     */
    public boolean isFile() {
        return m_file.isFile();
    }
    
    /**
     * Does this file exists?
     */
    public boolean doesExist() {
        return m_file.exists();
    }
    
    /**
     * Get file size.
     */
    public long getSize() {
        return m_file.length();
    }
    
    /**
     * Get file owner.
     */
    public String getOwnerName() {
        return "user";
    }
    
    /**
     * Get group name
     */
    public String getGroupName() {
        return "group";
    }
    
    /**
     * Get link count
     */
    public int getLinkCount() {
        return m_file.isDirectory() ? 3 : 1;
    }
    
    /**
     * Get last modified time.
     */ 
    public long getLastModified() {
        return m_file.lastModified();
    }
    
    /**
     * Check read permission.
     */
    public boolean hasReadPermission() {
        return m_file.canRead();
    }
    
    /**
     * Chech file write permission.
     */
    public boolean hasWritePermission() {
        if(!m_writePermission) {
            return false;
        }
        
        if(m_file.exists()) {
            return m_file.canWrite();
        }
        return true;
    }
    
    /**
     * Has delete permission.
     */
    public boolean hasDeletePermission() {
        
        // root cannot be deleted
        if( "/".equals(m_fileName) ) {
            return false;
        }
        
        return hasWritePermission();
    }
    
    /**
     * Delete file.
     */
    public boolean delete() {
        boolean retVal = false;
        if( hasDeletePermission() ) {
            retVal = m_file.delete();
        }
        return retVal;
    }
    
    /**
     * Move file object.
     */
    public boolean move(FileObject dest) {
        boolean retVal = false;
        if(dest.hasWritePermission() && hasReadPermission()) {
            File destFile = ((NativeFileObject)dest).m_file;
            retVal = m_file.renameTo(destFile);
        }
        return retVal;
    }
    
    /**
     * Create directory.
     */
    public boolean mkdir() {
        boolean retVal = false;
        if(hasWritePermission()) {
            retVal = m_file.mkdirs();
        }
        return retVal;
    }
    
    /**
     * Get the physical file object.
     */
    public File getPhysicalFile() {
        return m_file;
    }
    
    /**
     * Create output stream for writing.
     */
    public OutputStream createOutputStream(boolean append) throws IOException {
        
        // permission check
        if(!hasWritePermission()) {
            throw new IOException("No write permission : " + m_file.getName());
        }
        
        // create output stream
        OutputStream out = null;
        if(append && m_file.exists()) {
            RandomAccessFile raf = new RandomAccessFile(m_file, "rw");
            raf.seek(raf.length());
            out = new FileOutputStream(raf.getFD());
        }
        else {
            out = new FileOutputStream(m_file);
        }
        
        return out;
    }
    
    /**
     * Create output stream for writing.
     */
    public OutputStream createOutputStream(long offset) throws IOException {
        
        // permission check
        if(!hasWritePermission()) {
            throw new IOException("No write permission : " + m_file.getName());
        }
        
        // create output stream
        RandomAccessFile raf = new RandomAccessFile(m_file, "rw");
        raf.setLength(offset);
        raf.seek(offset);
        return new FileOutputStream(raf.getFD());
    }
    
    /**
     * Create input stream for reading.
     */
    public InputStream createInputStream(long offset) throws IOException {
        
        // permission check
        if(!hasReadPermission()) {
            throw new IOException("No read permission : " + m_file.getName());
        }
        
        // move to the appropriate offset and create input stream
        RandomAccessFile raf = new RandomAccessFile(m_file, "r");
        raf.seek(offset);
        return new FileInputStream(raf.getFD());
    }
    
    /**
     * Normalize separate characher. Separate character should be '/' always.
     */
    public final static String normalizeSeparateChar(String pathName) {
       pathName = pathName.replace(File.separatorChar, '/');
       pathName = pathName.replace('\\', '/');
       return pathName;
    }
    
    /**
     * Get the physical canonical file name. It works like 
     * File.getCanonicalPath().
     * 
     * @param rootDir The root directory. The path separator will always 
     * be '/'. It will always end with '/'.
     * 
     * @param currDir The current directory. It will always be with
     * respect to the root directory. The first and last characters 
     * will always be '/'.
     * 
     * @param fileName The input file name.
     * 
     * @return The return string will always begin with the root directory.
     * It will never be null.
     */
    public final static String getPhysicalName(String rootDir, 
                                               String currDir, 
                                               String fileName) {
        
        // get the starting directory
        fileName = normalizeSeparateChar(fileName);
        String resArg;
        if(fileName.charAt(0) != '/') {
            resArg = rootDir + currDir.substring(1);
        }
        else {
            resArg = rootDir;
        }
        
        // strip last '/'
        if(resArg.charAt(resArg.length() -1) == '/') {
            resArg = resArg.substring(0, resArg.length()-1);
        }
        
        // replace ., ~ and ..
        // in this loop resArg will never end with '/'
        StringTokenizer st = new StringTokenizer(fileName, "/");
        while(st.hasMoreTokens()) {
            String tok = st.nextToken().trim();
            
            // . => current directory
            if(tok.equals(".")) {
                continue;
            }
            
            // .. => parent directory (if not root)
            if(tok.equals("..")) {
                if(resArg.startsWith(rootDir)) {
                    int slashIndex = resArg.lastIndexOf('/');
                    if(slashIndex != -1) {
                        resArg = resArg.substring(0, slashIndex);
                    }
                }
                continue;
            }
            
            // ~ => home directory (in this case the root directory)
            if (tok.equals("~")) {
                resArg = rootDir.substring(0, rootDir.length()-1);
                continue;
            }
            
            resArg = resArg + '/' + tok;
        }
        
        // add last slash if necessary
        if( (resArg.length()) + 1 == rootDir.length() ) {
            resArg += '/';
        }
        
        // final check
        if ( !resArg.regionMatches(0, rootDir, 0, rootDir.length()) ) {
            resArg = rootDir;
        }
        
        return resArg;
    }
}
