// $Id: ServerFrame.java 306698 2005-09-07 10:36:22 +0530 (Wed, 07 Sep 2005) rana_b $
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
package org.apache.ftpserver.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * This is the FTP server UI starting point.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class ServerFrame extends JFrame {
    
    private static final long serialVersionUID = 8399655106217258507L;
    
    private PluginPanelContainer m_pluginContainer;
    
    /**
     * Constructor - create tree and show the root panel.
     */
    public ServerFrame() {
        JTabbedPane tabPane = new JTabbedPane(); 
        getContentPane().add(tabPane);
        
        // add all plugin panels
        m_pluginContainer = new TreePluginPanelContainer();
        m_pluginContainer.add(new RootPanel(m_pluginContainer));
        m_pluginContainer.add(new UserManagerPanel(m_pluginContainer));
        m_pluginContainer.add(new IPRestrictorPanel(m_pluginContainer));
        m_pluginContainer.add(new MessagePanel(m_pluginContainer));
        m_pluginContainer.add(new ConnectionPanel(m_pluginContainer));
        m_pluginContainer.add(new SpyPanelContainer(m_pluginContainer));
        m_pluginContainer.add(new FilePanel(m_pluginContainer));
        m_pluginContainer.add(new DirectoryPanel(m_pluginContainer));
        m_pluginContainer.add(new LoggerPanel(m_pluginContainer));
        m_pluginContainer.add(new StatisticsPanel(m_pluginContainer));
        
        tabPane.addTab("FTP", m_pluginContainer.getComponent());
        m_pluginContainer.setSelectedIndex(PluginPanelContainer.ROOT_INDEX);
        
        // show frame
        setTitle("Apache FTP Server");
        ImageIcon icon = GuiUtils.createImageIcon("org/apache/ftpserver/gui/logo.gif");
        if (icon != null) {
            setIconImage(icon.getImage());
        }
        setSize(new Dimension(620, 420));
        GuiUtils.setLocation(this);
        setVisible(true);
        toFront();
    }
    
    /*
     * Handle window closing event.
     */ 
    protected void processWindowEvent(WindowEvent e) {
        int id = e.getID();
        if (id == WindowEvent.WINDOW_CLOSING) {
            if ( !GuiUtils.getConfirmation(this, "Do you really want to exit?") ) {
                return;
            }
            super.processWindowEvent(e);
            RootPanel root = (RootPanel)m_pluginContainer.getPluginPanel(0);
            root.stopServer();
            dispose();
            System.exit(0);
        } 
        else {
            super.processWindowEvent(e);
        }    
    }

    /**
     * Server GUI starting point.
     */
     public static void main (String args[]) {
        System.out.println("Opening UI window, please wait...");            
        new ServerFrame();
     }
}
