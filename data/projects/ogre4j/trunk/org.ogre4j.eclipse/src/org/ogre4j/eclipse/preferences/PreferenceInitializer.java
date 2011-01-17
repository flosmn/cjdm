package org.ogre4j.eclipse.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.ogre4j.eclipse.OgrePlugin;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = OgrePlugin.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.PREFERENCE_RENDERSYSTEM, OgrePlugin.DEFAULT_RENDERSYSTEM);
	}
}
