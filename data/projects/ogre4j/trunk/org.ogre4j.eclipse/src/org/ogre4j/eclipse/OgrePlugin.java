/**
 * OgrePlugin.java
 *
 * Copyright &copy; 2008, netAllied GmbH, Tettnang, Germany. 
 * 
 * @author cnenning
 */
package org.ogre4j.eclipse;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.ogre4j.Capabilities;
import org.ogre4j.IRenderSystemCapabilities;
import org.ogre4j.IRenderSystemList;
import org.ogre4j.IRenderWindow;
import org.ogre4j.IRoot;
import org.ogre4j.LogManager;
import org.ogre4j.LogMessageLevel;
import org.ogre4j.ResourceGroupManager;
import org.ogre4j.Root;
import org.ogre4j.StringUtil;
import org.ogre4j.eclipse.preferences.PreferenceConstants;
import org.ogre4j.nativelisteners.NativeLogListener;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class OgrePlugin extends AbstractUIPlugin {

    /**
     * Receives log messages from OGRE and forwards them to this Bundle's log.
     */
    class OgreEclipseLogListener extends NativeLogListener {
        /**
         * @{inheritdoc}
         * @see org.ogre4j.ILogListener#messageLogged(java.lang.String,
         *      org.ogre4j.LogMessageLevel, boolean, java.lang.String)
         */
        public void messageLogged(String message, LogMessageLevel lml, boolean maskDebug, String logName) {
            if (LogMessageLevel.LML_CRITICAL == lml) {
                IStatus status = new Status(IStatus.ERROR, PLUGIN_ID, message);
                getLog().log(status);
            }
        }
    }

    /**
     * Key for OGRE resource location type "FileSystem".
     */
    public static final String RESOURCE_LOCATION_TYPE_FILE_SYSTEM = "FileSystem";

    /**
     * Key for OGRE resource location type "Zip".
     */
    public static final String RESOURCE_LOCATION_TYPE_ZIP = "Zip";

    /**
     * Resource group name for general resources.
     */
    public static final String RESOURCE_GROUP_GENERAL = "GENERAL";

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "org.ogre4j.eclipse";

    /**
     * Name of OpenGL Render System.
     */
    public static final String RENDERSYSTEM_NAME_OPENGL = "RenderSystem_GL";

    /**
     * Name of Direct3D9 Render System.
     */
    public static final String RENDERSYSTEM_NAME_DIRECT3D9 = "RenderSystem_Direct3D9";

    /**
     * Name to display for Render System OpenGL.
     */
    public static final String RENDERSYSTEM_DISPLAY_NAME_OPENGL = "OpenGL";

    /**
     * Name to display for Render System Direct3D9.
     */
    public static final String RENDERSYSTEM_DISPLAY_NAME_DIRECT3D9 = "Direct3D9";

    /**
     * Default Render System.
     */
    public static final String DEFAULT_RENDERSYSTEM = RENDERSYSTEM_NAME_OPENGL;

    /**
     * The shared instance.
     */
    private static OgrePlugin plugin;

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static OgrePlugin getDefault() {
        return plugin;
    }

    /**
     * Pool of created {@link IRenderWindow RenderWindows}.
     */
    private RenderWindowPool renderWindowPool;

    /**
     * OGRE Root.
     */
    private IRoot root;

    /**
     * OGRE LogManager.
     */
    private LogManager ogreLogManager;

    /**
     * Receives log messages from OGRE and forwards them to Eclipse.
     */
    private OgreEclipseLogListener ogreLogListener;

    /**
     * Render System to be used.
     */
    private String renderSystemToUse = DEFAULT_RENDERSYSTEM;

    /**
     * The constructor
     */
    public OgrePlugin() {
    }

    /**
     * Adds a new resource location to the ResourceGroupManager.
     * 
     * @param resourceLocation
     *            The full path to the resource location.
     * @param resourceLocationType
     *            The type of resource location. One of
     *            <ul>
     *            <li>{@link #RESOURCE_LOCATION_TYPE_FILE_SYSTEM}</li>
     *            <li>{@link #RESOURCE_LOCATION_TYPE_ZIP}</li>
     *            </ul>
     * @param groupName
     *            The name of the group to add the resource location.
     */
    public void addResourceLocation(String resourceLocation, String resourceLocationType, String groupName) {
        ResourceGroupManager.getSingleton().addResourceLocation(resourceLocation, resourceLocationType, groupName,
                false);
    }

    /**
     * Creates OGRE log. Disables console and file output. Registers
     * LogListener.
     */
    private void createOgreLog() {
        ogreLogManager = new LogManager();
        ogreLogManager.createLog("OgrePlugin.log", true, false, true);
        ogreLogListener = new OgreEclipseLogListener();
        ogreLogManager.getDefaultLog().addListener(ogreLogListener);
    }

    /**
     * Destroys a previously initialized resource group.
     * 
     * @param resourceGroupName
     *            The name of the resource group to destroy.
     */
    public void destroyResourceGroup(String resourceGroupName) {
        ResourceGroupManager.getSingleton().destroyResourceGroup(resourceGroupName);
    }

    /**
     * Returns OGRE Root and gives the client full access to it.
     */
    public IRoot getOgreRoot() {
        return root;
    }

    /**
     * @return The pool of render windows.
     */
    public RenderWindowPool getRenderWindowPool() {
        return renderWindowPool;
    }

    /**
     * Creates OGRE Root if not already created. Uses OpenGL by default and
     * loads the CG program manager. After plugin loading
     * {@link IRoot#initialise(boolean, String)} is called.
     */
    private void initializeOgre() {
        try {
            root = Root.getSingletonPtr();
        } catch (NullPointerException npe) {

            createOgreLog();

            try {
	            root = new Root("", "", "");

				root.loadPlugin(renderSystemToUse);

	            // set render system
	            IRenderSystemList list = root.getAvailableRenderers();
	            root.setRenderSystem(list.at(0));

	            // initialise Ogre Root
	            // its ok to get a null pointer exception here
	            // ogre doesnt create a new IRenderWindow
	            try {
	                root.initialise(false, "", StringUtil.getBLANK());
	            } catch (NullPointerException e) {
	                // OK, method returns null
	            }
            } catch (Exception e) {
            	Status status = new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage(), e);
            	getLog().log(status);
                Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
                ErrorDialog.openError(shell, "Open Error", e.getMessage(), status);
            }
        }
    }

    /**
     * Initializes a resource group.
     * 
     * @param name
     *            The name of the resource group to initialize.
     */
    public void initializeResourceGroup(String name) {
        ResourceGroupManager.getSingleton().initialiseResourceGroup(name);
    }

    /**
     * Checks if RenderSystem can handle Vertex Programs and loads the OGRE
     * Cg Plugin. Must be called after a Render Window has been created.
     */
    protected void loadCgPlugin() {
    	try {
	    	IRenderSystemCapabilities rsc = root.getRenderSystem().getCapabilities();
	        boolean canVertexProgs = rsc.hasCapability(Capabilities.RSC_VERTEX_PROGRAM);
	        if (canVertexProgs) {
	        	root.loadPlugin("Plugin_CgProgramManager");
	        }
	    } catch (Exception e) {
	    	Status status = new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage(), e);
	        getLog().log(status);
	        Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
	        ErrorDialog.openError(shell, "Open Error", e.getMessage(), status);
	    }
    }

    /**
     * Reads the plugin properties and loads all native libraries necessary for
     * this plugin to work.
     * 
     * @throws IOException
     *             If the plugin.properties are not found.
     */
    private void loadLibraries() throws IOException {
        URL propertiesURL = FileLocator.find(getBundle(), new Path("plugin.properties"), null);
        Properties props = new Properties();
        props.load(propertiesURL.openStream());
        String libprop = (String) props.get("libs");
        String[] libs = libprop.split(",");

        for (int l = 0, lcount = libs.length; l < lcount; ++l) {
            String lib = libs[l];
            try {
            	System.loadLibrary(lib);
            } catch (Throwable e) {
				getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage(), e));
			}
        }
    }

    /**
     * Removes a resource location from the ResourceGroupManager.
     * 
     * @param resourceLocation
     *            The resource location to remove.
     * @param groupName
     *            The name of the group the resource location is associated
     *            with.
     */
    public void removeResourceLocation(String resourceLocation, String groupName) {
        ResourceGroupManager.getSingleton().removeResourceLocation(resourceLocation, groupName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        loadLibraries();

        renderSystemToUse = getPreferenceStore().getString(PreferenceConstants.PREFERENCE_RENDERSYSTEM);

        initializeOgre();
        renderWindowPool = new RenderWindowPool(0, 100, renderSystemToUse == RENDERSYSTEM_NAME_OPENGL);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        renderWindowPool.destroyAllRenderWindows();
        root.delete();
        ogreLogManager.getDefaultLog().removeListener(ogreLogListener);
        ogreLogManager.delete();
        super.stop(context);
    }
}
