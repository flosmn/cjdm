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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

/**
 * @author cnenning
 * 
 */
public class OgrePreferenceInitializer extends AbstractPreferenceInitializer {

    public static final String OGRE_PLUGIN_FILE_NAME_KEY = "OGRE_PLUGIN_FILE_NAME";

    public static final String OGRE_CONFIG_FILE_NAME_KEY = "OGRE_CONFIG_FILE_NAME";

    public static final String OGRE_LOG_FILE_NAME_KEY = "OGRE_LOG_FILE_NAME";

    public static final String OGRE_DEFAULT_RESOURCE_LOCATION_KEY = "OGRE_DEFAULT_RESOURCE_LOCATION";

    public static final String OGRE_PLUGIN_FILE_NAME_DEFAULT_VALUE = "plugins.cfg";

    public static final String OGRE_CONFIG_FILE_NAME_DEFAULT_VALUE = "ogre.cfg";

    public static final String OGRE_LOG_FILE_NAME_DEFAULT_VALUE = "Ogre.log";

    public static final String OGRE_DEFAULT_RESOURCE_LOCATION_DEFAULT_VALUE = "../media_simple";

    public OgrePreferenceInitializer() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    @Override
    public void initializeDefaultPreferences() {
        IEclipsePreferences node = new DefaultScope().getNode(OgrePlugin.ID);
        node.put(OGRE_PLUGIN_FILE_NAME_KEY, OGRE_PLUGIN_FILE_NAME_DEFAULT_VALUE);
        node.put(OGRE_CONFIG_FILE_NAME_KEY, OGRE_CONFIG_FILE_NAME_DEFAULT_VALUE);
        node.put(OGRE_LOG_FILE_NAME_KEY, OGRE_LOG_FILE_NAME_DEFAULT_VALUE);
        node.put(OGRE_DEFAULT_RESOURCE_LOCATION_KEY, OGRE_DEFAULT_RESOURCE_LOCATION_DEFAULT_VALUE);
    }
}
