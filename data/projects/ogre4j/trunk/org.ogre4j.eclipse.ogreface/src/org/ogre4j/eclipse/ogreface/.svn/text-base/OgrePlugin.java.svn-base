/**
 * This file is part of ogre4j
 *  (The JNI bindings of OGRE - Object-Oriented Graphics Rendering Engine).
 *  
 * Copyright (c) 2005-2007 netAllied GmbH. All rights reserved.
 * Also see acknowledgements in README
 * 
 * ogre4j is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 * 
 * ogre4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with ogre4j; see the file COPYING.  If not, write to
 * the Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

package org.ogre4j.eclipse.ogreface;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Preferences;
import org.ogre4j.eclipse.ogreface.ogresys.OgreSystem;
import org.osgi.framework.BundleContext;

/**
 * Initialises the OgreSystem and loads the preferences.
 * 
 * @author ivica
 */
public class OgrePlugin extends Plugin {

    public static final String ID = "org.ogre4j.eclipse";

    static private OgrePlugin instance = null;

    private OgreSystem ogreSys = new OgreSystem();
    
    /**
     * contains a list of OgreViewers that have been registered through their
     * c-tor
     */
    // private java.util.Vector<OgreViewer> viewerList = new
    // Vector<OgreViewer>();
    // 
    // private java.util.Vector<OgreCanvas> canvasList = new
    // Vector<OgreCanvas>();
    @Override
    public void start(BundleContext context) throws Exception {
        // if run as stand alone application
        if (context != null) {
            super.start(context);
        }
        // remember singleton
        instance = this;

        // create and save OGRE singletons
        Preferences prefs = getPluginPreferences();
        String pluginFileName = prefs.getString(OgrePreferenceInitializer.OGRE_PLUGIN_FILE_NAME_KEY);
        String configFileName = prefs.getString(OgrePreferenceInitializer.OGRE_CONFIG_FILE_NAME_KEY);
        String logFileName = "ogreface.log";//prefs.getString(OgrePreferenceInitializer.OGRE_LOG_FILE_NAME_KEY);
        
        ogreSys.start(pluginFileName, configFileName, logFileName);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // save preferences
        savePluginPreferences();

        ogreSys.stop();

        // if run as stand alone application
        if (context != null) {
            super.stop(context);
        }
    }

    
    
    public OgreSystem getOgreSys() {
		return ogreSys;
	}

	/**
     * 
     * @return ogreclipse.OgrePlugin
     */
    public static OgrePlugin getDefault() {
        return instance;

    }

    public static OgreSystem getDefaultOgreSys() {
    	return instance.getOgreSys();
    }
}
