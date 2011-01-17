// $Id: DbUserManager.java 306760 2005-10-06 11:42:47 +0530 (Thu, 06 Oct 2005) rana_b $
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
package org.apache.ftpserver.usermanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ftpserver.ftplet.Configuration;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.util.StringUtils;

/**
 * This is another database based user manager class. It has been
 * tested in MySQL and Oracle 8i database. The schema file is 
 * </code>res/ftp-db.sql</code>
 *
 * All the user attributes are replaced during run-time. So we can use
 * your database schema. Then you need to modify the SQLs in the configuration
 * file.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public
class DbUserManager implements UserManager {
    
    private Log m_log;
    
    private Connection m_connection;
    
    private String m_insertUserStmt;
    private String m_updateUserStmt;
    private String m_deleteUserStmt;
    private String m_selectUserStmt;
    private String m_selectAllStmt;
    private String m_isAdminStmt;
    private String m_authenticateStmt;
    
    private String m_jdbcUrl;
    private String m_dbUser;
    private String m_dbPassword;
    
    private String m_adminName;
    
    
    /**
     * Set the log factory.
     */
    public void setLogFactory(LogFactory factory) {
        m_log = factory.getInstance(getClass());
    }
    
    /**
     * Configure user manager.
     */
    public void configure(Configuration config) throws FtpException {
        
        try {
            String className = config.getString("jdbc-driver");
            Class.forName(className);
            
            m_jdbcUrl          = config.getString("jdbc-url");
            m_dbUser           = config.getString("jdbc-user", null);
            m_dbPassword       = config.getString("jdbc-password", null);
            
            m_insertUserStmt   = config.getString("sql-user-insert");
            m_deleteUserStmt   = config.getString("sql-user-delete");
            m_updateUserStmt   = config.getString("sql-user-update");
            m_selectUserStmt   = config.getString("sql-user-select");
            m_selectAllStmt    = config.getString("sql-user-select-all");
            m_authenticateStmt = config.getString("sql-user-authenticate");
            m_isAdminStmt      = config.getString("sql-user-admin");
            
            openConnection();
            
            m_adminName = config.getString("admin", "admin");
            m_log.info("Database connection opened.");
        }
        catch(Exception ex) {
            m_log.fatal("DbUserManager.configure()", ex);
            throw new FtpException("DbUserManager.configure()", ex);
        }
    }
    
    /**
     * Get the admin name.
     */
    public String getAdminName() {
        return m_adminName;
    }
    
    /**
     * @return true if user with this login is administrator
     */
    public boolean isAdmin(String login) throws FtpException {
        
        // check input
        if(login == null) {
            return false;
        }
        
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            // create the sql query
            HashMap map = new HashMap();
            map.put( BaseUser.ATTR_LOGIN, escapeString(login) );
            String sql = StringUtils.replaceString(m_isAdminStmt, map);
            m_log.info(sql);
            
            // execute query
            prepareConnection();
            stmt = m_connection.createStatement();
            rs = stmt.executeQuery(sql);
            return rs.next();
        }
        catch(SQLException ex) {
            m_log.error("DbUserManager.isAdmin()", ex);
            throw new FtpException("DbUserManager.isAdmin()", ex);
        }
        finally {
            if(rs != null) {
                try { 
                    rs.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUserManager.isAdmin()", ex);
                }
            }
            if(stmt != null) {
                try { 
                    stmt.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUserManager.isAdmin()", ex);
                }
            }
        }
    }
    
    /**
     * Open connection to database.
     */
    private void openConnection() throws SQLException {
        m_connection = DriverManager.getConnection(m_jdbcUrl, m_dbUser, m_dbPassword);
        m_connection.setAutoCommit(true);
    }
    
    /**
     * Close connection to database.
     */
    private void closeConnection() {
        if (m_connection != null) {        
            try {
                m_connection.close(); 
            } 
            catch(SQLException ex) {
                m_log.error("DbUserManager.closeConnection()", ex);
            }
            m_connection = null;
        }
        
        m_log.info("Database connection closed.");
    }
    
    /**
     * Prepare connection to database.
     */
    private void prepareConnection() throws SQLException {
        boolean isClosed = false;    
        try {
            if( (m_connection == null) || m_connection.isClosed() ) {
                isClosed = true;
            }
        }
        catch(SQLException ex) {
            m_log.error("DbUserManager.prepareConnection()", ex);
            isClosed = true;
        }
        
        if (isClosed) {
            closeConnection();
            openConnection();
        }
    }
    
    /**
     * Delete user. Delete the row from the table.
     */
    public synchronized void delete(String name) throws FtpException {
        
        // create sql query
        HashMap map = new HashMap();
        map.put( BaseUser.ATTR_LOGIN, escapeString(name) );
        String sql = StringUtils.replaceString(m_deleteUserStmt, map);
        m_log.info(sql);
        
        // execute query
        Statement stmt = null;
        try {
            prepareConnection();
            stmt = m_connection.createStatement();
            stmt.executeUpdate(sql);
        }
        catch(SQLException ex) {
            m_log.error("DbUserManager.delete()", ex);
            throw new FtpException("DbUserManager.delete()", ex);
        }
        finally {
            if(stmt != null) {
                try { 
                    stmt.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUserManager.delete()", ex);
                }
            }
        }
    }
    
    /**
     * Save user. If new insert a new row, else update the existing row.
     */
    public synchronized void save(User user) throws FtpException {
        
        // null value check
        if(user.getName() == null) {
            throw new NullPointerException("User name is null.");
        } 
        
        Statement stmt = null;
        try {
            
            // create sql query
            HashMap map = new HashMap();
            map.put( BaseUser.ATTR_LOGIN, escapeString(user.getName()) );
            map.put( BaseUser.ATTR_PASSWORD, escapeString(getPassword(user)) );
            map.put( BaseUser.ATTR_HOME, escapeString(user.getHomeDirectory()) );
            map.put( BaseUser.ATTR_ENABLE, String.valueOf(user.getEnabled()) );
            map.put( BaseUser.ATTR_WRITE_PERM, String.valueOf(user.getWritePermission()) );
            map.put( BaseUser.ATTR_MAX_IDLE_TIME, new Integer(user.getMaxIdleTime()) );
            map.put( BaseUser.ATTR_MAX_UPLOAD_RATE, new Integer(user.getMaxUploadRate()) );
            map.put( BaseUser.ATTR_MAX_DOWNLOAD_RATE, new Integer(user.getMaxDownloadRate()) ); 
            
            String sql = null;      
            if( !doesExist(user.getName()) ) {
                sql = StringUtils.replaceString(m_insertUserStmt, map);
            }
            else {
                sql = StringUtils.replaceString(m_updateUserStmt, map);
            }
            m_log.info(sql);
            
            // execute query
            prepareConnection();
            stmt = m_connection.createStatement();
            stmt.executeUpdate(sql);
        }
        catch(SQLException ex) {
            m_log.error("DbUserManager.save()", ex);
            throw new FtpException("DbUserManager.save()", ex);
        }
        finally {
            if(stmt != null) {
                try { 
                    stmt.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUsermanager.error()", ex);
                }
            }
        }
    }
    
    /**
     * Get the user object. Fetch the row from the table.
     */
    public synchronized User getUserByName(String name) throws FtpException {
        
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            // create sql query
            HashMap map = new HashMap();
            map.put( BaseUser.ATTR_LOGIN, escapeString(name) );
            String sql = StringUtils.replaceString(m_selectUserStmt, map);
            m_log.info(sql);
            
            // execute query
            prepareConnection();
            stmt = m_connection.createStatement();
            rs = stmt.executeQuery(sql);
            
            // populate user object
            BaseUser thisUser = null;
            String trueStr = Boolean.TRUE.toString();
            if(rs.next()) {
                thisUser = new BaseUser();
                thisUser.setName(rs.getString(BaseUser.ATTR_LOGIN));
                thisUser.setHomeDirectory(rs.getString(BaseUser.ATTR_HOME));
                thisUser.setEnabled(trueStr.equalsIgnoreCase(rs.getString(BaseUser.ATTR_ENABLE)));
                thisUser.setWritePermission(trueStr.equalsIgnoreCase(rs.getString(BaseUser.ATTR_WRITE_PERM)));
                thisUser.setMaxIdleTime(rs.getInt(BaseUser.ATTR_MAX_IDLE_TIME));
                thisUser.setMaxUploadRate(rs.getInt(BaseUser.ATTR_MAX_UPLOAD_RATE));
                thisUser.setMaxDownloadRate(rs.getInt(BaseUser.ATTR_MAX_DOWNLOAD_RATE));
            }
            return thisUser;
        }
        catch(SQLException ex) {
            m_log.error("DbUserManager.getUserByName()", ex);
            throw new FtpException("DbUserManager.getUserByName()", ex);
        }
        finally {
            if(rs != null) {
                try { 
                    rs.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUserManager.getUserByName()", ex);
                }
            }
            if(stmt != null) {
                try { 
                    stmt.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUserManager.getUserByName()", ex);
                }
            }
        }
    }
    
    /**
     * User existance check.
     */
    public synchronized boolean doesExist(String name) throws FtpException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            // create the sql
            HashMap map = new HashMap();
            map.put( BaseUser.ATTR_LOGIN, escapeString(name) );
            String sql = StringUtils.replaceString(m_selectUserStmt, map);
            m_log.info(sql);
            
            // execute query
            prepareConnection();
            stmt = m_connection.createStatement();
            rs = stmt.executeQuery(sql);
            return rs.next();
        }
        catch(SQLException ex) {
            m_log.error("DbUserManager.doesExist()", ex);
            throw new FtpException("DbUserManager.doesExist()", ex);
        }
        finally {
            if(rs != null) {
                try { 
                    rs.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUserManager.doesExist()", ex);
                }
            }
            if(stmt != null) {
                try { 
                    stmt.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUserManager.doesExist()", ex);
                }
            }
        }
    }
    
    /**
     * Get all user names from the database.
     */
    public synchronized Collection getAllUserNames() throws FtpException {
        
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            // create sql query
            String sql = m_selectAllStmt;
            m_log.info(sql);
            
            // execute query
            prepareConnection();
            stmt = m_connection.createStatement();
            rs = stmt.executeQuery(sql);
            
            // populate list
            ArrayList names = new ArrayList();
            while(rs.next()) {
                names.add(rs.getString(BaseUser.ATTR_LOGIN));
            }
            return names;
        }
        catch(SQLException ex) {
            m_log.error("DbUserManager.getAllUserNames()", ex);
            throw new FtpException("DbUserManager.getAllUserNames()", ex);
        }
        finally {
            if(rs != null) {
                try { 
                    rs.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUserManager.getAllUserNames()", ex);
                }
            }
            if(stmt != null) {
                try { 
                    stmt.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUserManager.getAllUserNames()", ex);
                }
            }
        }
    }
    
    /**
     * Get user password.
     * <pre>
     * If the password value is not null
     *    password = new password 
     * else 
     *   if user does exist
     *     password = old password
     *   else 
     *     password = ""
     * </pre>
     */
    private synchronized String getPassword(User user) throws SQLException {
        
        String password = user.getPassword();
        if (password != null) {
            return password;
        }

        // create sql query
        HashMap map = new HashMap();
        map.put( BaseUser.ATTR_LOGIN, escapeString(user.getName()) );
        String sql = StringUtils.replaceString(m_selectUserStmt, map);
        m_log.info(sql);
        
        // execute query
        Statement stmt = null;
        ResultSet rs = null;
        try {
            prepareConnection();
            stmt = m_connection.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                password = rs.getString(BaseUser.ATTR_PASSWORD);
            }
        }
        finally {
            if(rs != null) {
                try { 
                    rs.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUserManager.getPassword()", ex);
                }
            }
            if(stmt != null) {
                try { 
                    stmt.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUserManager.getPassword()", ex);
                }
            }
        }
        
        if (password == null) {
            password = "";
        }
        return password;
    }
    
    /**
     * User authentication.
     */
    public synchronized boolean authenticate(String user, 
                                             String password) throws FtpException {
        
        // check input
        if( (user == null) || (password == null) ) {
            return false;
        }
        
        Statement stmt = null;
        ResultSet rs = null;
        try {
            
            // create the sql query
            HashMap map = new HashMap();
            map.put( BaseUser.ATTR_LOGIN, escapeString(user) );
            map.put( BaseUser.ATTR_PASSWORD, escapeString(password) );
            String sql = StringUtils.replaceString(m_authenticateStmt, map);
            m_log.info(sql);
            
            // execute query
            prepareConnection();
            stmt = m_connection.createStatement();
            rs = stmt.executeQuery(sql);
            return rs.next();
        }
        catch(SQLException ex) {
            m_log.error("DbUserManager.authenticate()", ex);
            throw new FtpException("DbUserManager.authenticate()", ex);
        }
        finally {
            if(rs != null) {
                try { 
                    rs.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUserManager.authenticate()", ex);
                }
            }
            if(stmt != null) {
                try { 
                    stmt.close(); 
                } 
                catch(Exception ex) {
                    m_log.error("DbUserManager.authenticate()", ex);
                }
            }
        }
    }
    
    /**
     * Close this user manager. Close the database statements and connection.
     */
    public synchronized void dispose() {
        closeConnection();
    }
    
    /**
     * Escape string to be embedded in SQL statement.
     */
    private String escapeString(String input) {
        StringBuffer valBuf = new StringBuffer(input);
        for (int i=0; i<valBuf.length(); i++) {
            char ch = valBuf.charAt(i);
            if (ch == '\'' || 
                ch == '\\' || 
                ch == '$'  || 
                ch == '^'  || 
                ch == '['  || 
                ch == ']'  || 
                ch == '{'  || 
                ch == '}') {
                 
                valBuf.insert(i, '\\'); 
                i++;
             }
         }
         return valBuf.toString();
    }
}