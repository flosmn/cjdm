package org.ogre4j.eclipse.opcode;

import java.io.IOException;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import org.ogre4j.eclipse.OgrePlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class OpcodePlugin extends Plugin {

    static final boolean DEBUG = false;

    // The plug-in ID
    public static final String PLUGIN_ID = "de.netallied.opcode";

    // The shared instance
    private static OpcodePlugin plugin;

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static OpcodePlugin getDefault() {
        return plugin;
    }

    /**
     * The constructor
     */
    public OpcodePlugin() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    /**
     * Copies the native libs into the working directory if they don't exist.
     * 
     * @throws IOException
     */
    private void loadLibraries() throws IOException {
        if (DEBUG) {
            System.loadLibrary("Opcode_D");
            System.loadLibrary("opcode4j_d");
        } else {
            System.loadLibrary("Opcode");
            System.loadLibrary("opcode4j");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        OgrePlugin.getDefault(); // force a start of the OGRE Plugin
        loadLibraries();
    }

    public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);		
	}
}
