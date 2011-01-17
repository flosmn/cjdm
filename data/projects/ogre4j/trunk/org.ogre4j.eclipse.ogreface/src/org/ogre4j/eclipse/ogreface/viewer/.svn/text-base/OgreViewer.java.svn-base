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

package org.ogre4j.eclipse.ogreface.viewer;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

import javax.vecmath.Color4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.ogre4j.eclipse.ogreface.ogresys.CameraMode;
import org.ogre4j.eclipse.ogreface.ogresys.IOgreNodeListener;
import org.ogre4j.eclipse.ogreface.ogresys.LightType;
import org.ogre4j.eclipse.ogreface.ogresys.OgreCanvas;
import org.ogre4j.eclipse.ogreface.ogresys.OgreScene;

public class OgreViewer extends ContentViewer implements IOgreNodeListener, DisposeListener {

    private final java.util.HashMap<Object, String> domainObjectNameMap = new HashMap<Object, String>();

    private final java.util.Vector<INavigator> navigators = new Vector<INavigator>();

    private final java.util.Vector<IPicker> pickers = new Vector<IPicker>();

    private OgreCanvas canvas = null;
    
    private OgreScene scene = null;

    // private final ITreeContentProvider ogreContentProvider/* =
    // contentProvider*/ = null;

    // private final nodeProvider/* = labelProvider*/ = null;
    private IOgreLightProvider lightProvider = null;

    private IOgreCameraProvider cameraProvider = null;

    private IOgreEntityProvider entityProvider = null;

    private IOgreParticleSystemProvider particleSystemProvider = null;

    private IOgreNodeModifier nodeModifier = null;

    /**
     * 
     * @param canvas
     */
    public OgreViewer(final OgreCanvas canvas) {
        this.canvas = canvas;
        this.scene = canvas.getScene();
        registerAsDisposeListener();
    }

    private void registerAsDisposeListener() {
        canvas.addDisposeListener(this);
    }

    /**
     * 
     * @param navigator
     * @param camera
     * @return void
     */
    public void addNavigator(final INavigator navigator, final Object camera) {
        navigator.setCanvas(canvas);
        final String camName = domainObjectNameMap.get(camera);
        navigator.setCameraName(camName);
        navigators.add(navigator);
    }

    /**
     * 
     * @param picker
     * @return void
     */
    public void addPicker(final IPicker picker) {
        picker.setCanvas(scene);
        pickers.add(picker);
    }

    private Object findDomainObjectForString(final String name) {
        if (domainObjectNameMap.containsValue(name)) {
            final Set<Entry<Object, String>> set = domainObjectNameMap.entrySet();
            for (final Entry<Object, String> entry : set) {
                if (entry.getValue().equals(name))
                    return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 
     * @return ogreclipse.IOgreCameraProvider
     */
    public org.ogre4j.eclipse.ogreface.viewer.IOgreCameraProvider getCameraProvider() {
        return cameraProvider;

    }

    /**
     * 
     * @return org.eclipse.swt.widgets.Control
     */
    @Override
    public org.eclipse.swt.widgets.Control getControl() {
        return canvas;
    }

    /**
     * 
     * @param name
     * @return java.lang.Object
     */
    public java.lang.Object getDomainObject(final String name) {
        return findDomainObjectForString(name);

    }

    /**
     * 
     * @return ogreclipse.IOgreEntityProvider
     */
    public org.ogre4j.eclipse.ogreface.viewer.IOgreEntityProvider getEntityProvider() {
        return entityProvider;

    }

    /**
     * 
     * @return ogreclipse.IOgreLightProvider
     */
    public org.ogre4j.eclipse.ogreface.viewer.IOgreLightProvider getLightProvider() {
        return lightProvider;

    }

    /**
     * 
     * @return ogreclipse.IOgreNodeModifier
     */
    public org.ogre4j.eclipse.ogreface.viewer.IOgreNodeModifier getNodeModifier() {
        return nodeModifier;

    }

    /**
     * 
     * @return ogreclipse.IOgreParticleSystemProvider
     */
    public org.ogre4j.eclipse.ogreface.viewer.IOgreParticleSystemProvider getParticleSystemProvider() {
        return particleSystemProvider;

    }

    public final INavigator[] getNavigators() {
        return navigators.toArray(new INavigator[navigators.size()]);
    }

    /**
     * 
     * @return org.eclipse.jface.viewers.ISelection
     */
    @Override
    public org.eclipse.jface.viewers.ISelection getSelection() {
        final String name = canvas.getSelection();
        final StructuredSelection selection = new StructuredSelection(findDomainObjectForString(name));
        return selection;
    }

    /**
     * 
     * @param input
     * @param oldInput
     * @return void
     */
    @Override
    public void inputChanged(final java.lang.Object input, final java.lang.Object oldInput) {
        super.inputChanged(input, oldInput);

        // remove old ogre objects
        try {
        	scene.clearScene();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // create ogre objects
        IOgreContentProvider ogreContentProvider = (IOgreContentProvider) getContentProvider();
        final Object[] elements = ogreContentProvider.getElements(input);
        if (elements != null) {
            for (final Object e : elements) {
                try {
                    refresh(e);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }

        // set a camera active
        String activeCam = ogreContentProvider.getActiveCamera(input);
        if (activeCam != null) {
            try {
            	canvas.setActiveCamera(activeCam);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // TODO tell navigators about new input
    }

    public void orientationChanged(final String name, final Quat4f orientation) {
        final Object element = findDomainObjectForString(name);
        nodeModifier.modifyOrientation(element, orientation);
    }

    public void positionChanged(final String name, final Vector3f position) {
        final Object element = findDomainObjectForString(name);
        nodeModifier.modifyPosition(element, position);
    }

    /**
     * 
     * @return void
     */
    @Override
    public void refresh() {
        try {
            refresh(getInput());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param element
     * @return void
     * @throws Exception
     */
    public void refresh(final java.lang.Object element) throws Exception {
        if (element == null)
            return;

        // TODO handle removal of elements

        IOgreContentProvider ogreContentProvider = (IOgreContentProvider) getContentProvider();

        scene.setSceneCreation(true);

        final Object parent = ogreContentProvider.getParent(element);
        update(element, parent);
        if (ogreContentProvider.hasChildren(element)) {
            final Object[] children = ogreContentProvider.getChildren(element);
            for (final Object child : children) {
                refresh(child);
            }
        }
        scene.setSceneCreation(false);
    }

    /**
     * 
     * @param navigator
     * @return void
     */
    public void removeNavigator(final org.ogre4j.eclipse.ogreface.viewer.INavigator navigator) {
        navigators.remove(navigator);
    }

    /**
     * 
     * @param picker
     * @return void
     */
    public void removePicker(final IPicker picker) {
        pickers.remove(picker);
    }

    public void scaleChanged(final String name, final Vector3f scale) {
        final Object element = findDomainObjectForString(name);
        nodeModifier.modifyScale(element, scale);
    }

    /**
     * 
     * @param cameraProvider
     * @return void
     */
    public void setCameraProvider(final org.ogre4j.eclipse.ogreface.viewer.IOgreCameraProvider cameraProvider) {
        this.cameraProvider = cameraProvider;
    }

    /**
     * 
     * @param entityProvider
     * @return void
     */
    public void setEntityProvider(final org.ogre4j.eclipse.ogreface.viewer.IOgreEntityProvider entityProvider) {
        this.entityProvider = entityProvider;
    }

    /**
     * 
     * @param lightProvider
     * @return void
     */
    public void setLightProvider(final org.ogre4j.eclipse.ogreface.viewer.IOgreLightProvider lightProvider) {
        this.lightProvider = lightProvider;
    }

    /**
     * 
     * @param nodeModifier
     * @return void
     */
    public void setNodeModifier(final org.ogre4j.eclipse.ogreface.viewer.IOgreNodeModifier nodeModifier) {
        this.nodeModifier = nodeModifier;
    }

    /**
     * 
     * @param particleSystemProvider
     * @return void
     */
    public void setParticleSystemProvider(
            final org.ogre4j.eclipse.ogreface.viewer.IOgreParticleSystemProvider particleSystemProvider) {
        this.particleSystemProvider = particleSystemProvider;
    }

    /**
     * 
     * @param selection
     * @param reveal
     * @return void
     */
    @Override
    public void setSelection(final org.eclipse.jface.viewers.ISelection selection, final boolean reveal) {
        if (!selection.isEmpty()) {
            if (selection instanceof IStructuredSelection) {
                final IStructuredSelection strucSel = (IStructuredSelection) selection;
                final Object selectedElement = strucSel.getFirstElement();
                final String name = domainObjectNameMap.get(selectedElement);
                canvas.setSelection(name);
            }
        }
    }

    /**
     * Sets the properties for a single Domain Object. Uses the providers to get
     * the values.
     * 
     * @param elements
     * @return void
     * @throws Exception
     */
    public void update(final Object element, final Object parent) throws Exception {
        IOgreNodeProvider nodeProvider = (IOgreNodeProvider) getLabelProvider();
        if (nodeProvider == null)
            return;

        final String parentName = domainObjectNameMap.get(parent);
        final String name = nodeProvider.getName(element);

        updateNode(element, name, parentName, nodeProvider);

        if (nodeProvider.isEntity(element) && entityProvider != null) {
            updateEntity(element, name);
        }
        if (nodeProvider.isLight(element) && lightProvider != null) {
            updateLight(element, name);
        }
        if (nodeProvider.isCamera(element) && cameraProvider != null) {
            updateCamera(element, name);
        }
        if (nodeProvider.isParticleSystem(element) && particleSystemProvider != null) {
            updateParticleSystem(element, name, parentName);
        }
    }

    private void updateCamera(final Object element, final String name) {
        final float near = cameraProvider.getNearClippingDistance(element);
        final float far = cameraProvider.getFarClippingDistance(element);
        final Vector3f lookAt = cameraProvider.getLookAt(element);
        final CameraMode mode = cameraProvider.getMode(element);
        final Color4f bg = cameraProvider.getBackgroundColour(element);
        final long flags = cameraProvider.getQueryFlags(element);

        if (!scene.hasCamera(name))
        	scene.createCamera(name);
        scene.setCamNearClipDistance(name, near);
        scene.setCamFarClipDistance(name, far);
        if (lookAt != null)
        	scene.setCamLookAt(name, lookAt);
        if (mode != null)
        	scene.setCamMode(name, mode);
        if (bg != null)
        	scene.setCamBackgroundColour(name, bg);
        scene.setCameraQueryFlags(name, flags);
    }

    private void updateEntity(final Object element, final String name) throws Exception {
        final String mesh = entityProvider.getMesh(element);
        final String animation = entityProvider.getPlayAnimation(element);
        final boolean loop = entityProvider.getLoopAnimation(element);
        final boolean update = entityProvider.getAutoUpdateAnimation(element);
        final long queryFlags = entityProvider.getQueryFlags(element);

        if (!scene.hasEntity(name))
        	scene.createEntity(name, mesh);
        else
        	scene.setEntityMesh(name, mesh);
        scene.setEntityAnimation(name, animation, loop, update);
        scene.setEntityQueryFlags(name, queryFlags);
    }

    private void updateLight(final Object element, final String name) throws Exception {
        final LightType type = lightProvider.getType(element);
//        final float range = lightProvider.getAttenuationRange(element);
//        final float constant = lightProvider.getAttenuationConstant(element);
//        final float linear = lightProvider.getAttenuationLinear(element);
//        final float quadratic = lightProvider.getAttenuationQuadratic(element);
        final Color4f dCol = lightProvider.getDiffuseColour(element);
        final Color4f sCol = lightProvider.getSpecularColour(element);
        final Vector3f dir = lightProvider.getDirection(element);
//        final float innerAngle = lightProvider.getSpotlightRangeInnerAngle(element);
//        final float outerAngle = lightProvider.getSpotlightRangeOuterAngle(element);
//        final float falloff = lightProvider.getSpotlightRangeFalloff(element);
//        final float ps = lightProvider.getPowerScale(element);
        final long flags = lightProvider.getQueryFlags(element);

        if (!scene.hasLight(name))
        	scene.createLight(name);
        if (type != null)
        	scene.setLightType(name, type);
        //canvas.setLightAttenuation(name, range, constant, linear, quadratic);
        if (dCol != null)
        	scene.setLightDiffuseColour(name, dCol);
        if (sCol != null)
        	scene.setLightSpecularColour(name, sCol);
        if (dir != null)
        	scene.setLightDirection(name, dir);
        //canvas.setSpotlightRange(name, innerAngle, outerAngle, falloff);
        //canvas.setLightPowerScale(name, ps);
        scene.setLightQueryFlags(name, flags);
    }

    private void updateNode(final Object element, final String name, final String parent,
            final IOgreNodeProvider nodeProvider) {
        final Vector3f position = nodeProvider.getPosition(element);
        final Quat4f orientation = nodeProvider.getOrientation(element);
        final Vector3f scale = nodeProvider.getScale(element);
        final boolean visible = nodeProvider.isVisible(element);
        final boolean shadowCaster = nodeProvider.isShadowCaster(element);

        if (!scene.hasNode(name))
        	scene.createNode(name, parent);
        if (position != null)
        	scene.setNodePosition(name, position);
        if (orientation != null)
        	scene.setNodeOrientation(name, orientation);
        if (scale != null)
        	scene.setNodeScale(name, scale);
        scene.setNodeVisible(name, visible);
        scene.setNodeIsShadowCaster(name, shadowCaster);

    }

    private void updateParticleSystem(final Object element, final String name, final String parent) {
        final String templateName = particleSystemProvider.getTemplateName(element);
        final float speedFactor = particleSystemProvider.getSpeedFactor(element);
        final float nonVisibleTimeout = particleSystemProvider.getNonVisibleTimeout(element);
        final float width = particleSystemProvider.getDefaultWidth(element);
        final float height = particleSystemProvider.getDefaultHeight(element);
        final boolean sortingEnabled = particleSystemProvider.isSortingEnabled(element);
        final boolean autoUpdateBounds = particleSystemProvider.areBoundsAutoUpdated(element);
        final long flags = particleSystemProvider.getQueryFlags(element);

        if (!scene.hasParticleSystem(name))
        	scene.createParticleSystem(name, parent, templateName);
        scene.setParticleSystemSpeedFactor(name, speedFactor);
        scene.setParticleSystemNonVisibleTimeOut(name, nonVisibleTimeout);
        scene.setParticleSystemDefaultWidth(name, width);
        scene.setParticleSystemDefaultHeight(name, height);
        scene.setParticleSystemSortingEnabled(name, sortingEnabled);
        scene.setParticleSystemAutoUpdateBounds(name, autoUpdateBounds);
        scene.setParticleSystemQueryFlags(name, flags);
    }

    public void visibleChanged(final String name, final boolean visible) {
        final Object element = findDomainObjectForString(name);
        nodeModifier.modifyVisible(element, visible);
    }

    public boolean isDisposed() {
        if (canvas != null)
            return canvas.isDisposed();
        return true;
    }

    public void dispose() {
        if (canvas != null && !canvas.isDisposed()) {
            canvas.dispose();
            canvas = null;
        }
    }

    public void widgetDisposed(DisposeEvent e) {
        // TODO Tell Navis, Pickers
        canvas = null;
    }
}
