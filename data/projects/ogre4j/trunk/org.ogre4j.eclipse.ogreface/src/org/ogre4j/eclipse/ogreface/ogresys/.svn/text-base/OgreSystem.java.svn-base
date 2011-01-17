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

package org.ogre4j.eclipse.ogreface.ogresys;

import org.ogre4j.IRenderSystemList;
import org.ogre4j.IResourceGroupManager;
import org.ogre4j.IRoot;
import org.ogre4j.ResourceGroupManager;
import org.ogre4j.Root;
import org.ogre4j.StringUtil;

public class OgreSystem {

    static private OgreSystem instance = null;

    // 
    static private IRoot root = null;

    // 
    static private IResourceGroupManager resourceGroupManager = null;

    // TODO migrate to OGRE 1.2
    // private org.ogre4j.OverlayManager overlayManager = null;
    // 
    private IRenderLoop renderLoop = null;

    // 
    /**
     * contains a list of OgreViewers that have been registered through their
     * c-tor
     */
    // private java.util.Vector<OgreViewer> viewerList = new
    // Vector<OgreViewer>();
    // 
    // private java.util.Vector<OgreCanvas> canvasList = new
    // Vector<OgreCanvas>();
    public void start(String pluginFileName, String configFileName, String logFileName) {
        // remember singleton
        instance = this;

        // create and save OGRE singletons
        root = new Root(pluginFileName, configFileName, logFileName);
        resourceGroupManager = ResourceGroupManager.getSingleton();
        if (resourceGroupManager == null) {
            throw new RuntimeException("OGRE was not correctly initialised !!!");
        }
        // initialise OGRE
        // TODO should be done with config files
        root.loadPlugin("RenderSystem_GL");
        root.loadPlugin("Plugin_CgProgramManager");

        IRenderSystemList list = root.getAvailableRenderers();
        if (list == null || list.size() == 0) {
            throw new RuntimeException("No RenderSystem loaded!");
        }
        root.setRenderSystem(list.at(0));
        try {
            // init wihtout creation of a renderwindow.
            root.initialise(false, "", StringUtil.getBLANK());
        } catch (NullPointerException e) {
            // we didn't want a renderwindow created automatically. therefor
            // Ogre::Root returns a NullPointer. This is okay
        }
    }

    public void stop() {

        // stop rendering
        if (renderLoop != null) {
            renderLoop.stop();
        }
        /*
         * // dispose viewers for (OgreViewer viewer : viewerList) {
         * viewer.dispose(); } // dispose remaining canvas for (OgreCanvas
         * canvas : this.canvasList) { canvas.dispose(); }
         */
        // clean up OGRE resources
        if (resourceGroupManager != null) {
            resourceGroupManager.shutdownAll();
        }

        // destroy OGRE root
        root.delete();
    }

    /**
     * 
     * @return ogreclipse.OgrePlugin
     */
    public static OgreSystem getDefault() {
        return instance;

    }

    /**
     * needed by OgreCanvas
     */
    IRoot getRoot() {
        return root;
    }

    /**
     * 
     * @return String[]
     */
    public String[] getAvailableRenderLoops() {
        return null;

    }

    /**
     * 
     * @return String[]
     */
    public String[] getAvailableNavigators() {
        return null;

    }

    /**
     * 
     * @return String[]
     */
    public String[] getAvailablePickers() {
        return null;

    }

    /**
     * 
     * @param renderLoop
     * @return void
     */
    public void setRenderLoop(org.ogre4j.eclipse.ogreface.ogresys.IRenderLoop renderLoop) {
        if (this.renderLoop != null)
            this.renderLoop.stop();
        this.renderLoop = renderLoop;
    }

    /**
     * 
     * @return void
     */
    public void startRendering() {
        if (renderLoop == null)
            throw new RuntimeException("RenderLoop is not set !!!");
        renderLoop.start();
    }

    /**
     * 
     * @return void
     */
    public void stopRendering() {
        if (renderLoop == null)
            throw new RuntimeException("RenderLoop is not set !!!");
        renderLoop.stop();
    }

    /**
     * To be used by IRenderLoop
     * 
     * @return void
     * @throws OgreException
     */
    public synchronized void renderOneFrame() throws Exception {
        root.renderOneFrame();
    }

    /**
     * To be used by OgreCanvas
     * 
     * @return void
     */
    public void redraw() {
        if (renderLoop != null)
            renderLoop.redraw();
    }

    /**
     * 
     * @param name
     * @param locType
     * @param resGroup
     * @param recursive
     * @return void
     * @throws Exception
     */
    public void addResourceLocation(String name, String locType, String resGroup, boolean recursive) throws Exception {
        resourceGroupManager.addResourceLocation(name, locType, resGroup, recursive);
    }

    /**
     * 
     * @param fileName
     * @return void
     */
    public void addResourceConfigFile(String fileName) {

    }

    /**
     * 
     * @param name
     * @return void
     */
    public void initialiseResourceGroup(String name) throws Exception {
        resourceGroupManager.initialiseResourceGroup(name);
    }

    /**
     * 
     * @return void
     */
    public void initialiseAllResourceGroups() throws Exception {
        resourceGroupManager.initialiseAllResourceGroups();
    }

    /**
     * 
     * @param name
     * @param loadMainResources
     * @param loadWorldGeom
     * @return void
     */
    public void loadResourceGroup(String name, boolean loadMainResources, boolean loadWorldGeom) throws Exception {
        resourceGroupManager.loadResourceGroup(name, loadMainResources, loadWorldGeom);
    }

    /**
     * 
     * @param name
     * @param reloadableOnly
     * @return void
     */
    public void unloadResourceGroup(String name, boolean reloadableOnly) throws Exception {
        resourceGroupManager.unloadResourceGroup(name, reloadableOnly);
    }

    /**
     * 
     * @param name
     * @return void
     */
    public void clearResourceGroup(String name) throws Exception {
        resourceGroupManager.clearResourceGroup(name);
    }

    /**
     * 
     * @param viewer
     * @return void
     */
    // public void registerViewer (ogreclipse.OgreViewer viewer) {
    // viewerList.add(viewer);
    // }
    /**
     * 
     * @param viewer
     * @return void
     */
    // public void deregisterViewer (ogreclipse.OgreViewer viewer) {
    // viewerList.remove(viewer);
    // }
    /**
     * 
     * @param canvas
     * @return void
     */
    // public void registerCanvas (ogreclipse.OgreCanvas canvas) {
    // this.canvasList.add(canvas);
    // }
    /**
     * 
     * @param canvas
     * @return void
     */
    // public void deregisterCanvas (ogreclipse.OgreCanvas canvas) {
    // this.canvasList.remove(canvas);
    // }
    /**
     * TODO migrate to OGRE 1.2
     * 
     * @param name
     * @return org.ogre4j.Overlay
     */
    /*
     * org.ogre4j.Overlay getOverlayByName (String name) { return null; }
     */
    /**
     * 
     * @param name
     * @return void
     */
    void destroyOverlay(String name) {

    }
}
