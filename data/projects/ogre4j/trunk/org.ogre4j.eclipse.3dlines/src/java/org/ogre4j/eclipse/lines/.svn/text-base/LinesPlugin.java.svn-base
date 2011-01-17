package org.ogre4j.eclipse.lines;

import org.eclipse.core.runtime.Plugin;
import org.ogre4j.ColourValue;
import org.ogre4j.IColourValue;
import org.ogre4j.IManualResourceLoader;
import org.ogre4j.IMaterial;
import org.ogre4j.INameValuePairList;
import org.ogre4j.IPass;
import org.ogre4j.IResourcePtr;
import org.ogre4j.ITechnique;
import org.ogre4j.ManualResourceLoader;
import org.ogre4j.MaterialManager;
import org.ogre4j.MaterialPtr;
import org.ogre4j.NameValuePairList;
import org.ogre4j.ResourceGroupManager;
import org.ogre4j.ResourcePtr;
import org.ogre4j.eclipse.OgrePlugin;
import org.osgi.framework.BundleContext;
import org.xbig.base.WithoutNativeObject;

/**
 * The activator class controls the plug-in life cycle.
 * 
 */
public class LinesPlugin extends Plugin {

    /**
     * Name of black material to be used by lines.
     */
    public static final String LINE_MATERIAL_NAME_BLACK = "LINE_MATERIAL_BLACK";

    /**
     * Name of red material to be used by lines.
     */
    public static final String LINE_MATERIAL_NAME_RED = "LINE_MATERIAL_RED";

    /**
     * Name of resource group containing line specific resources.
     */
    public static final String LINES_RESOURCE_GROUP_NAME = "LINES_RESOURCE_GROUP";

    /**
     * The shared instance.
     */
    private static LinesPlugin instance;

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance.
     */
    public static LinesPlugin getDefault() {
        return instance;
    }

    /**
     * Creates one material with specified name and color.
     * 
     * @param name
     *            Name of new material.
     * @param colour
     *            Color of new material.
     */
    private void createMaterial(String name, IColourValue colour) {
        IManualResourceLoader manualResourceLoaderNullPtr = new ManualResourceLoader(
                WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
        INameValuePairList nameValuePairListNullPtr = new NameValuePairList(
                WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);

        IResourcePtr resourcePtr = new ResourcePtr(WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
        MaterialManager.getSingleton().create(resourcePtr, name, LINES_RESOURCE_GROUP_NAME, false,
                manualResourceLoaderNullPtr, nameValuePairListNullPtr);
        MaterialPtr matPtr = new MaterialPtr(resourcePtr.getInstancePointer());
        IMaterial material = matPtr.get();
        ITechnique tech = material.createTechnique();
        IPass pass = tech.createPass();
        pass.setAmbient(colour);
        pass.setDiffuse(colour);
        pass.setSpecular(colour);
        pass.setSelfIllumination(colour);
        pass.setDepthCheckEnabled(true);
        pass.setDepthWriteEnabled(true);
        pass.setLightingEnabled(true);
        matPtr.setNull();
    }

    /**
     * Creates simple materials used by lines.
     */
    private void createMaterials() {
        ResourceGroupManager.getSingleton().createResourceGroup(LINES_RESOURCE_GROUP_NAME);

        createMaterial(LINE_MATERIAL_NAME_BLACK, ColourValue.getBlack());
        createMaterial(LINE_MATERIAL_NAME_RED, ColourValue.getRed());
    }

    /**
     * Loads native libraries required by this plugin.
     */
    private void loadLibraries() {
        System.loadLibrary("3dlines");
    }

    /**
     * @{inheritdoc}
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        instance = this;
        OgrePlugin.getDefault(); // force a start of the OGRE Plugin
        loadLibraries();
        createMaterials();
    }

    /**
     * @{inheritdoc}
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        instance = null;
        ResourceGroupManager.getSingleton().destroyResourceGroup(LINES_RESOURCE_GROUP_NAME);
        super.stop(context);
    }
}
