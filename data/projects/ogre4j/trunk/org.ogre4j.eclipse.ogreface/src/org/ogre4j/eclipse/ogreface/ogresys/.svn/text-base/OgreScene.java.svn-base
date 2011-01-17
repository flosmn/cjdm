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

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.vecmath.Color4f;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import org.ogre4j.Camera;
import org.ogre4j.ColourValue;
import org.ogre4j.ICamera;
import org.ogre4j.IColourValue;
import org.ogre4j.IEntity;
import org.ogre4j.ILight;
import org.ogre4j.IMeshPtr;
import org.ogre4j.INode;
import org.ogre4j.IQuaternion;
import org.ogre4j.IRadian;
import org.ogre4j.IRoot;
import org.ogre4j.ISceneManager;
import org.ogre4j.ISceneNode;
import org.ogre4j.IVector3;
import org.ogre4j.Light;
import org.ogre4j.MeshPtr;
import org.ogre4j.Quaternion;
import org.ogre4j.Radian;
import org.ogre4j.Vector3;
import org.ogre4j.Node.TransformSpace;
import org.xbig.base.WithoutNativeObject;

public class OgreScene {

    private static final String ENTITY_SUFFIX = "Entity";

    private static final String CAMERA_SUFFIX = "Camera";

    private static final String LIGHT_SUFFIX = "Light";

    private static final String OGRE_ROOT_NODE_NAME = "root node";

    private Map<String, ISceneNode> nodes = new HashMap<String, ISceneNode>();

    private Map<String, IEntity> entities = new HashMap<String, IEntity>();

    private Map<String, CamData> cameras = new HashMap<String, CamData>();

    private Map<String, ILight> lights = new HashMap<String, ILight>();
    
    
    private Map<String, ICamera> ogreCams = new HashMap<String, ICamera>();

    private ISceneManager sceneManager = null;
   
    private Vector<IOgreNodeListener> nodeListeners = new Vector<IOgreNodeListener>();

    /**
     * contains the name without CAMERA_SUFFIX
     */
//    private String activeCam = null;

//    private ICamera ogreCam = null;


    boolean sceneIsInCreation = false;

   /**
     * calls shell.open() that OGRE can create it's viewport
     * 
     * @param parent
     * @param sceneType
     * @param shadowType
     */
    public OgreScene(SceneType sceneType, ShadowType shadowType) throws Exception {

        // check if Plugin is running
        if (OgreSystem.getDefault() == null) {
            throw new RuntimeException("Cannot create canvas because plugin is not running !!!");
        }

        // create render window
        IRoot root = OgreSystem.getDefault().getRoot();
        
      
        // create scene manager
        switch (sceneType) {
        case GENERIC:
            sceneManager = root.createSceneManager(org.ogre4j.SceneType.ST_GENERIC.getValue(), "Default");
            break;
        case INTERIOR:
            sceneManager = root.createSceneManager(org.ogre4j.SceneType.ST_INTERIOR.getValue(), "Interior");
            break;
        case EXTERIOR_CLOSE:
            sceneManager = root.createSceneManager(org.ogre4j.SceneType.ST_EXTERIOR_CLOSE.getValue(), "ExteriorClose");
            break;
        case EXTERIOR_FAR:
            sceneManager = root.createSceneManager(org.ogre4j.SceneType.ST_EXTERIOR_FAR.getValue(), "ExteriorFar");
            break;
        case EXTERIOR_REAL_FAR:
            sceneManager = root.createSceneManager(org.ogre4j.SceneType.ST_EXTERIOR_REAL_FAR.getValue(),
                    "ExteriorRealFar");
            break;
        }
        

    }

    ICamera getOgreCam(String name) {
    	return ogreCams.get(name);
    }
    
    /**
     * 
     * @param name
     * @param nodeName
     * @return void
     */
    public void addEntityToStaticGeometry(String name, String nodeName) {

    }

    /**
     * 
     * @param listener
     * @return void
     */
    public void addNodeListener(IOgreNodeListener listener) {
        nodeListeners.add(listener);
    }

    /**
     * 
     * @param name
     * @param nodeName
     * @return void
     */
    public void addSceneNodeToStaticGeometry(String name, String nodeName) {

    }

   
    /**
     * 
     * @param name
     * @return void
     */
    public void buildStaticGeometry(String name) {

    }

    /**
     * 
     * @return void
     */
    public void clearScene() throws Exception {
        while (!nodes.isEmpty()) {
            removeNodeWithChildrenAndObjects(nodes.keySet().iterator().next());
        }
    }

    /**
     * 
     * @param name
     * @param parent
     * @return void
     */
    public void createCamera(String name) {
        // test if name already exists
        CamData testCamera = cameras.get(name + CAMERA_SUFFIX);
        if (testCamera != null)
            throw new RuntimeException("Camera with name " + name + " already exists !!!");

        // get node from map
        ISceneNode node = nodes.get(name);
        if (node == null)
            throw new RuntimeException("Cannot create camera for non-existant node: " + name + " !!!");

        // create cam
        CamData cam = new CamData();

        // put cam into map
        cameras.put(name + CAMERA_SUFFIX, cam);  
        
        activateCam(name);
    }

    /**
     * 
     * @param name
     * @param mesh
     * @return void
     */
    public void createEntity(String name, String mesh) throws Exception {
        // test if name already exists
        IEntity testEntity = entities.get(name + ENTITY_SUFFIX);
        if (testEntity != null) {
            throw new RuntimeException("Entity with name " + name + " already exists !!!");
        }
        // get node from map
        ISceneNode node = nodes.get(name);
        if (node == null) {
            throw new RuntimeException("Cannot create entity for non-existant node: " + name + " !!!");
        }
        // create entity
        IEntity entity = sceneManager.createEntity(name + ENTITY_SUFFIX, mesh);
        node.attachObject(entity);

        // put entity into map
        entities.put(name + ENTITY_SUFFIX, entity);

        if (!sceneIsInCreation) {
            // tell renderloop about change
            OgreSystem.getDefault().redraw();
        }
    }

    /**
     * 
     * @param name
     * @return void
     */
    public void createLight(String name) throws Exception {
        // test if name already exists
        ILight testLight = lights.get(name + LIGHT_SUFFIX);
        if (testLight != null) {
            throw new RuntimeException("Light with name " + name + " already exists !!!");
        }
        // get node from map
        ISceneNode node = nodes.get(name);
        if (node == null) {
            throw new RuntimeException("Cannot create light for non-existant node: " + name + " !!!");
        }
        // create light
        ILight light = sceneManager.createLight(name + LIGHT_SUFFIX);
        node.attachObject(light);

        // put light into map
        lights.put(name + LIGHT_SUFFIX, light);

        if (!sceneIsInCreation) {
            // tell renderloop about change
            OgreSystem.getDefault().redraw();
        }
    }

    /**
     * 
     * @param name
     * @param parent
     * @return void
     */
    public void createNode(String name, String parent) {
        // test if name already exists
        ISceneNode testNode = nodes.get(name);
        if (testNode != null) {
            throw new RuntimeException("Node with name " + name + " already exists !!!");
        }
        // get parent node
        ISceneNode parentNode = nodes.get(parent);
        if (parentNode == null) {
            parentNode = sceneManager.getRootSceneNode();
        }
        // create new node
        ISceneNode node = parentNode.createChildSceneNode(name, Vector3.getZERO(), Quaternion.getIDENTITY());

        // put new node into map
        nodes.put(name, node);
    }

    /**
     * 
     * @param name
     * @param parent
     * @param templateName
     * @return void
     */
    public void createParticleSystem(String name, String parent, String templateName) {

    }

    /**
     * 
     * @param name
     * @return void
     */
    public void createStaticGeometry(String name) {

    }

//    public void deactivateCamera() throws Exception {
//        if (activeCam != null) {
//            // set render window inactive
//            
//        	// IVICA: rethink this one...
//        	//renderWindow.setActive(false);
//        	
//        	
//            sceneManager.destroyCamera(ogreCam);
//
//            // null relevant attributes
//            activeCam = null;
//            ogreCam = null;
//        }
//    }

    /**
     * 
     * @param name
     * @return void
     */
    public void destroyOverlay(String name) {

    }

    /**
     * 
     * @return void
     */
    public void disableFog() {

    }

    /**
     * 
     * @return void
     */
    public void disableSkyBox() {

    }

    /**
     * 
     * @return void
     */
    public void disableSkyDome() {

    }

    /**
     * 
     * @return void
     */
    public void disableSkyPlane() {

    }

    /**
     * 
     * @param name
     * @param filename
     * @return void
     */
    public void dumpStaticGeometry(String name, String filename) {

    }

    // TODO delete those objects
    private ICamera findOgreCamObject(String name, INode root) {
        for (int i = 0; i < root.numChildren(); i++) {
            ICamera ogreCam;
            try {
                ogreCam = findOgreCamObject(name, root.getChild(i));
            } catch (Exception e) {
                ogreCam = null;
            }
            if (ogreCam != null) {
                return ogreCam;
            }
        }
        Camera ogreObject;
        try {
            ogreObject = (Camera) ((ISceneNode) root).getAttachedObject(name + CAMERA_SUFFIX);
        } catch (Exception e) {
            ogreObject = null;
        }
        return ogreObject;
    }

    private void fireNodeOrientationChanged(String nodeName, IQuaternion newOri) {
        Quat4f o = new Quat4f(newOri.getx(), newOri.gety(), newOri.getz(), newOri.getw());
        fireNodeOrientationChanged(nodeName, o);
    }

    private void fireNodeOrientationChanged(String nodeName, Quat4f newOri) {
        for (IOgreNodeListener listener : nodeListeners) {
            listener.orientationChanged(nodeName, newOri);
        }
    }

    private void fireNodePositionChanged(String nodeName, Vector3f newPos) {
        for (IOgreNodeListener listener : nodeListeners) {
            listener.positionChanged(nodeName, newPos);
        }
    }

    private void fireNodeScaleChanged(String nodeName, Vector3f newScale) {
        for (IOgreNodeListener listener : nodeListeners) {
            listener.scaleChanged(nodeName, newScale);
        }
    }

    private void fireNodeVisibilityChanged(String nodeName, boolean newVis) {
        for (IOgreNodeListener listener : nodeListeners) {
            listener.visibleChanged(nodeName, newVis);
        }
    }

    /**
     * 
     */
    public Color4f getActiveCamBackgroundColour() {
//        if (viewport != null) {
//            return new Color4f(viewport.getBackgroundColour().getr(), viewport.getBackgroundColour().getg(), viewport
//                    .getBackgroundColour().getb(), viewport.getBackgroundColour().geta());
//        }
        return null;
    }

//    public String getActiveCamera() {
//        return activeCam;
//    }

    /**
     * 
     */
    public long getActiveCameraQueryFlags(String camName) {
    	ICamera ogreCam = getOgreCam(camName);
        if (ogreCam != null) {
            return ogreCam.getQueryFlags();
        }
        return 0;
    }

    /**
     * 
     */
    public float getActiveCamFarClipDistance(String camName) {
    	ICamera ogreCam = getOgreCam(camName);
        if (ogreCam != null) {
            return ogreCam.getFarClipDistance();
        }
        return 0;
    }

    /**
     * 
     */
    public Vector3f getActiveCamLookAt(String camName) {
    	ICamera ogreCam = getOgreCam(camName);
        if (ogreCam != null) {
            IVector3 tmp = new Vector3(WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
            ogreCam.getDirection(tmp);
            Vector3f v = new Vector3f(tmp.getx(), tmp.gety(), tmp.getz());
            tmp.delete();
            return v;
        }
        return null;
    }

   

    /**
     * 
     */
    public float getActiveCamNearClipDistance(String camName) {
    	ICamera ogreCam = getOgreCam(camName);
        if (ogreCam != null) {
            return ogreCam.getNearClipDistance();
        }
        return 0;
    }

    /**
     * 
     * @return javax.vecmath.Color4f
     */
    public javax.vecmath.Color4f getAmbientLight() {
        return null;

    }

    /**
     * 
     */
    public Color4f getCamBackgroundColour(String name) {
        // get from Map
        CamData camData = cameras.get(name + CAMERA_SUFFIX);
        if (camData == null) {
            throw new RuntimeException("Invalid camera name: " + name + " !!!");
        }
        // return
        return camData.getBgCol();
    }

    /**
     * 
     */
    public long getCameraQueryFlags(String name) {
        // get from Map
        CamData camData = cameras.get(name + CAMERA_SUFFIX);
        if (camData == null) {
            throw new RuntimeException("Invalid camera name: " + name + " !!!");
        }
        // return
        return camData.getQueryFlags();
    }

    /**
     * 
     * @return String[]
     */
    public String[] getCameras() {
        return cameras.keySet().toArray(new String[cameras.size()]);
    }

    /**
     * 
     */
    public float getCamFarClipDistance(String name) {
        // get from Map
        CamData camData = cameras.get(name + CAMERA_SUFFIX);
        if (camData == null) {
            throw new RuntimeException("Invalid camera name: " + name + " !!!");
        }
        // return
        return camData.getFarClippingDistance();
    }

    /**
     * 
     */
    public Vector3f getCamLookAt(String name) {
        // get from Map
        CamData camData = cameras.get(name + CAMERA_SUFFIX);
        if (camData == null) {
            throw new RuntimeException("Invalid camera name: " + name + " !!!");
        }
        // return
        return new Vector3f(camData.getLookAt());
    }

    /**
     * 
     */
    public CameraMode getCamMode(String name) {
        return null;
    }

    /**
     * 
     */
    public float getCamNearClipDistance(String name) {
        // get from Map
        CamData camData = cameras.get(name + CAMERA_SUFFIX);
        if (camData == null) {
            throw new RuntimeException("Invalid camera name: " + name + " !!!");
        }
        // return
        return camData.getNearClippingDistance();
    }

    /**
     * 
     * @param name
     * @return String[]
     */
    public String[] getEntityAnimations(String name) {
        // entities.get(name).getMesh().g
        return null;

    }

    /**
     * 
     */
    public String getEntityMesh(String name) {
        // get from Map
        IEntity entity = entities.get(name + ENTITY_SUFFIX);
        if (entity == null) {
            throw new RuntimeException("Invalid entity name: " + name + " !!!");
        }
        // return
        IMeshPtr meshPtr = entity.getMesh();
        String meshname = ((MeshPtr) meshPtr).get().getName();
        meshPtr.delete();
        return meshname;
    }

    /**
     * 
     */
    public long getEntityQueryFlags(String name) {
        // get from Map
        IEntity entity = entities.get(name + ENTITY_SUFFIX);
        if (entity == null) {
            throw new RuntimeException("Invalid entity name: " + name + " !!!");
        }
        // return
        return entity.getQueryFlags();
    }

    /**
     * 
     */
    public float getLightAttenuationConstant(String name) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // return
        return light.getAttenuationConstant();
    }

    /**
     * 
     */
    public float getLightAttenuationLinear(String name) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // return
        return light.getAttenuationLinear();
    }

    /**
     * 
     */
    public float getLightAttenuationQuadric(String name) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // return
        return light.getAttenuationQuadric();
    }

    /**
     * 
     */
    public float getLightAttenuationRange(String name) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // return
        return light.getAttenuationRange();
    }

    /**
     * 
     */
    public Color4f getLightDiffuseColour(String name) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // return
        IColourValue tmp = light.getDiffuseColour();
        return new Color4f(tmp.getr(), tmp.getg(), tmp.getb(), tmp.geta());
    }

    /**
     * 
     */
    public Vector3f getLightDirection(String name) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // return
        IVector3 tmp = light.getDirection();
        return new Vector3f(tmp.getx(), tmp.gety(), tmp.getz());
    }

    /**
     * 
     */
    public float getLightPowerScale(String name) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // return
        return 0;
    }

    /**
     * 
     */
    public long getLightQueryFlags(String name) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // return
        return light.getQueryFlags();
    }

    /**
     * 
     */
    public Color4f getLightSpecularColour(String name) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // return
        IColourValue tmp = light.getSpecularColour();
        return new Color4f(tmp.getr(), tmp.getg(), tmp.getb(), tmp.geta());
    }

    /**
     * 
     */
    public LightType getLightType(String name) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // get
        LightType type = null;
        switch (light.getType()) {
        case LT_SPOTLIGHT:
            type = LightType.SPOT;
            break;
        case LT_POINT:
            type = LightType.POINT;
            break;
        case LT_DIRECTIONAL:
            type = LightType.DIRECTIONAL;
            break;
        default:
            // nothing
        }

        // return
        return type;
    }

    /**
     * 
     */
    public Quat4f getNodeOrientation(String name) {
        // get from Map
        ISceneNode node = nodes.get(name);
        if (node == null) {
            throw new RuntimeException("Invalid node name: " + name + " !!!");
        }
        // return
        IQuaternion tmp = node.getOrientation();
        return new Quat4f(tmp.getx(), tmp.gety(), tmp.getz(), tmp.getw());
    }

    /**
     * 
     * @param name
     * @return javax.vecmath.Vector3f
     */
    public Vector3f getNodePosition(String name) {
        // get node from map
        ISceneNode node = nodes.get(name);
        if (node == null) {
            throw new RuntimeException("Invalid node name: " + name + " !!!");
        }
        // get position
        IVector3 tmp = node.getPosition();
        return new Vector3f(tmp.getx(), tmp.gety(), tmp.getz());
    }

    /**
     * 
     */
    public Vector3f getNodeScale(String name) {
        // get from Map
        ISceneNode node = nodes.get(name);
        if (node == null) {
            throw new RuntimeException("Invalid node name: " + name + " !!!");
        }
        // return
        IVector3 tmp = node.getScale();
        return new Vector3f(tmp.getx(), tmp.gety(), tmp.getz());
    }

    /**
     * 
     * @param name
     * @return float
     */
    public float getOverlayRotate(String name) {
        return 0;

    }

    /**
     * 
     * @param name
     * @return float
     */
    public float getOverlayScaleX(String name) {
        return 0;

    }

    /**
     * 
     * @param name
     * @return float
     */
    public float getOverlayScaleY(String name) {
        return 0;

    }

    /**
     * 
     * @param name
     * @return boolean
     */
    public boolean getOverlayScrollX(String name) {
        return false;

    }

    /**
     * 
     * @param name
     * @return float
     */
    public float getOverlayScrollY(String name) {
        return 0;

    }

    /**
     * 
     * @param name
     * @return javax.vecmath.Quat4f
     */
    public javax.vecmath.Quat4f getOverlayWorldOrientation(String name) {
        return null;

    }

    /**
     * 
     * @param name
     * @return javax.vecmath.Vector3f
     */
    public javax.vecmath.Vector3f getOverlayWorldPosition(String name) {
        return null;

    }

    /**
     * 
     * @param name
     * @return int
     */
    public int getOverlayZOrder(String name) {
        return 0;

    }

    

    /**
     * 
     * @param name
     * @return boolean
     */
    public boolean hasCamera(String name) {
        return cameras.containsKey(name + CAMERA_SUFFIX);
    }

    /**
     * 
     * @param name
     * @return boolean
     */
    public boolean hasEntity(String name) {
        return entities.containsKey(name + ENTITY_SUFFIX);
    }

    /**
     * 
     * @param name
     * @return boolean
     */
    public boolean hasLight(String name) {
        return lights.containsKey(name + LIGHT_SUFFIX);
    }

    /**
     * 
     * @param name
     * @return boolean
     */
    public boolean hasNode(String name) {
        return nodes.containsKey(name);
    }

    /**
     * 
     * @param name
     * @return boolean
     */
    public boolean hasParticleSystem(String name) {
        return false;

    }

    /**
     * 
     * @param name
     * @return void
     */
    public void hideOverlay(String name) {

    }

    /**
     * 
     * @return boolean
     */
    public boolean isSkyBoxEnabled() {
        return false;

    }

    /**
     * 
     * @return boolean
     */
    public boolean isSkyDomeEnabled() {
        return false;

    }

    /**
     * 
     * @return boolean
     */
    public boolean isSkyPlaneEnabled() {
        return false;

    }

    /**
     * 
     * @param name
     * @return void
     */
    public void loadOverlay(String name) {

    }

    /**
     * 
     * @param name
     * @return boolean
     */
    public boolean nodeHasChild(String parent, String child) {
        if (parent == null || child == null) {
            return false;
        }
        try {
            nodes.get(parent).getChild(child);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void nodeTranslate(String name, javax.vecmath.Vector3f value) {
        // get node from map
        ISceneNode node = nodes.get(name);
        if (node == null) {
            throw new RuntimeException("Invalid node name: " + name + " !!!");
        }
        // set new value
        node.translate(value.x, value.y, value.z, TransformSpace.TS_PARENT);

        if (!sceneIsInCreation) {
            // tell renderloop about change
            OgreSystem.getDefault().redraw();

            // tell listeners
            fireNodePositionChanged(name, value);
        }
    }

    /**
     * Rotate the node around the X-axis.
     * 
     * @param nodeName
     * @param angle
     */
    public void pitchNode(String nodeName, float angle) {
        // get node from map
        ISceneNode node = nodes.get(nodeName);
        if (node == null) {
            throw new RuntimeException("Invalid node name: " + nodeName + " !!!");
        }
        // set new value
        IRadian rad = new Radian(angle);
        node.pitch(rad, TransformSpace.TS_LOCAL);

        if (!sceneIsInCreation) {
            // tell renderloop about change
            OgreSystem.getDefault().redraw();

            // tell listeners
            fireNodeOrientationChanged(nodeName, node.getOrientation());
        }

    }

    /**
     * 
     * @param cameraName
     * @param queryTypeMask
     * @param sortByDistance
     * @return void
     */
    public String[] raySceneQuery(String cameraName, long queryTypeMask, boolean sortByDistance) throws Exception {
//        if (cameraName.equals(activeCam)) {
//            IRay ray = new Ray(WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
//            ogreCam.getCameraToViewportRay(ray, viewport.getWidth(), viewport.getHeight());
//            IRaySceneQuery query = sceneManager.createRayQuery(ray, queryTypeMask);
//            // RaySceneQueryResult result = query.execute();
//            ray.delete();
//            return null;
//        }
    	// IVICA
        return null;
    }

    /**
     * 
     * @param startPoint
     * @param direction
     * @param queryTypeMask
     * @param sortByDistance
     * @return void
     */
    public String[] raySceneQuery(Vector3f startPoint, Vector3f direction, long queryTypeMask, boolean sortByDistance) {
        return null;
    }

    /**
     * Detatching from and deletion of Node is not done here
     * 
     * @param name
     * @return void
     */
    public void removeCamera(String name) throws Exception {
        CamData camera = cameras.get(name + CAMERA_SUFFIX);
        if (camera != null) {
//            if (getActiveCamera() != null && getActiveCamera().equals(name)) {
//                deactivateCamera();
//            }

            // TODO sceneManager.destroyCamera(name + CAMERA_SUFFIX);
            cameras.remove(name + CAMERA_SUFFIX);
        }
    }

    /**
     * Detatching from and deletion of Node is not done here
     * 
     * @param name
     * @return void
     */
    public void removeEntity(String name) {
        IEntity entity = entities.get(name + ENTITY_SUFFIX);
        if (entity != null) {
            try {
                sceneManager.destroyEntity(name + ENTITY_SUFFIX);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            entities.remove(name + ENTITY_SUFFIX);
        }
        // TODO stop animation and remove animation state
    }

    /**
     * Detatching from and deletion of Node is not done here
     * 
     * @param name
     * @return void
     */
    public void removeLight(String name) {
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light != null) {
            try {
                sceneManager.destroyLight(name + LIGHT_SUFFIX);
                light.delete();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            lights.remove(name + LIGHT_SUFFIX);
        }
    }

    /**
     * Detatching of Objects and deletion of Children is not done here
     * 
     * @param name
     * @return void
     */
    private void removeNode(String name) {
        ISceneNode node = nodes.get(name);
        if (!node.getParent().getName().equals(OGRE_ROOT_NODE_NAME)) {
            node.getParent().removeChild(name);
        }
        if (node != null) {
            // sceneManager.destroyNode(name);
            try {
                sceneManager.destroySceneNode(name);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            nodes.remove(name);
        }
    }

    /**
     * 
     * @param listener
     * @return void
     */
    public void removeNodeListener(IOgreNodeListener listener) {
        nodeListeners.remove(listener);
    }

    public void removeNodeWithChildrenAndObjects(String name) throws Exception {
        // remove children first
        ISceneNode node = nodes.get(name);
        int numChildren = node.numChildren();
        if (numChildren > 0) {
            for (int i = 0; i < numChildren; ++i) {
                String childName = node.getChild(i).getName();
                removeNodeWithChildrenAndObjects(childName);
            }
        }
        // remove attached objects
        IEntity entity = entities.get(name + ENTITY_SUFFIX);
        if (entity != null) {
            node.detachObject(name + ENTITY_SUFFIX);
            removeEntity(name);
        }
        CamData camera = cameras.get(name + CAMERA_SUFFIX);
        if (camera != null) {
            // node.detachObject(name + CAMERA_SUFFIX);
//            if (name.equals(activeCam)) {
//                deactivateCamera();
//            }
            removeCamera(name);
        }
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light != null) {
            node.detachObject(name + LIGHT_SUFFIX);
            removeLight(name);
        }

        // TODO handle StaticGeometries

        // remove Node
        removeNode(name);
    }

    /**
     * 
     * @param name
     * @return void
     */
    public void removeParticleSystem(String name) {

    }

    
    /**
     * 
     * @param name
     * @return void
     */
    public void removeStaticGeometry(String name) {

    }

    /**
     * Rotate the node around the Z-axis.
     * 
     * @param nodeName
     * @param angle
     */
    public void rollNode(String nodeName, float angle) {
        // get node from map
        ISceneNode node = nodes.get(nodeName);
        if (node == null) {
            throw new RuntimeException("Invalid node name: " + nodeName + " !!!");
        }
        // set new value
        Radian rad = new Radian(angle);
        node.roll(rad, TransformSpace.TS_LOCAL);
        rad.delete();

        if (!sceneIsInCreation) {
            // tell renderloop about change
            OgreSystem.getDefault().redraw();

            // tell listeners
            fireNodeOrientationChanged(nodeName, node.getOrientation());
        }

    }

    /**
     * 
     * @param name
     * @param angle
     * @return void
     */
    public void rotateOverlay(String name, float angle) {

    }

    /**
     * 
     * @param name
     * @param xoff
     * @param yoff
     * @return void
     */
    public void scrollOverlay(String name, float xoff, float yoff) {

    }

    /**
     * 
     * @param value
     * @return void
     */
    public void setAmbientLight(Color4f value) {
        IColourValue ambient = new ColourValue(value.x, value.y, value.z, value.w);
        sceneManager.setAmbientLight(ambient);
        ambient.delete();
        if (!sceneIsInCreation) {
            // tell renderloop
            OgreSystem.getDefault().redraw();
        }
    }

    /**
     * Sets the aspect ratio for the frustum viewport. The ratio between the x
     * and y dimensions of the rectangular area visible through the frustum is
     * known as aspect ratio: aspect = width / height.
     */
    protected void setAspectRatio(String camName, float aspectRatio) {
        ICamera camera = getOgreCam(camName);
        if (camera == null) {
            return;
        }
        camera.setAspectRatio(aspectRatio);

        if (!sceneIsInCreation) {
            // tell renderloop
            OgreSystem.getDefault().redraw();
        }
    }

    

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setCamBackgroundColour(String name, javax.vecmath.Color4f value) {
        // get from Map
        CamData camData = cameras.get(name + CAMERA_SUFFIX);
        if (camData == null) {
            throw new RuntimeException("Invalid camera name: " + name + " !!!");
        }
        // set
        camData.setBgCol(value);

        // check for active cam
//        if (name.equals(getActiveCamera())) {
//            IColourValue bg = new ColourValue(value.x, value.y, value.z, value.w);
//            
//            //viewport.setBackgroundColour(bg);
//            // IVICA
//            
//            bg.delete();
//
//            if (!sceneIsInCreation) {
//                // tell renderloop
//                OgreSystem.getDefault().redraw();
//            }
//        }
    }

    /**
     * Convenience method to set the look at for the parent scene node of a
     * camera.
     * 
     * @param camNodeName
     *            The node name.
     * @param targetPiont
     *            The target point.
     */
    private void setCameraNodeLookAt(String camNodeName, Vector3f targetPiont) {
        ISceneNode node = nodes.get(camNodeName);
        Vector3f direction = new Vector3f(targetPiont);
        IVector3 tmp = node.getPosition();
        Vector3f position = new Vector3f(tmp.getx(), tmp.gety(), tmp.gety());
        direction.sub(position);
        node.setDirection(direction.x, direction.y, direction.z, TransformSpace.TS_LOCAL, Vector3.getNEGATIVE_UNIT_Z());
    }

    /**
     * 
     * @param name
     * @param flags
     * @return void
     */
    public void setCameraQueryFlags(String name, long flags) {
        // get from Map
        CamData camData = cameras.get(name + CAMERA_SUFFIX);
        if (camData == null) {
            throw new RuntimeException("Invalid camera name: " + name + " !!!");
        }
        // set
        camData.setQueryFlags(flags);

        // check for active cam
//        if (name.equals(getActiveCamera())) {
//            ogreCam.setQueryFlags(flags);
//        }
    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setCamFarClipDistance(String name, float value) {
        // get from Map
        CamData camData = cameras.get(name + CAMERA_SUFFIX);
        if (camData == null) {
            throw new RuntimeException("Invalid camera name: " + name + " !!!");
        }
        // set
        camData.setFarClippingDistance(value);

        // check for active cam
//        if (name.equals(getActiveCamera())) {
//            ogreCam.setFarClipDistance(value);
//
//            if (!sceneIsInCreation) {
//                // tell renderloop
//                OgreSystem.getDefault().redraw();
//            }
//        }
    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setCamLookAt(String name, javax.vecmath.Vector3f value) {
        // get from Map
        CamData camData = cameras.get(name + CAMERA_SUFFIX);
        if (camData == null) {
            throw new RuntimeException("Invalid camera name: " + name + " !!!");
        }
        // set
        camData.setLookAt(new Point3f(value.x, value.y, value.z));

        // check for active cam
//        if (name.equals(getActiveCamera())) {
//            // ogreCam.lookAt(new Point3f(value.x, value.y, value.z));
//            setCameraNodeLookAt(name, value);
//
//            if (!sceneIsInCreation) {
//                // tell renderloop
//                OgreSystem.getDefault().redraw();
//            }
//        }
    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setCamMode(String name, CameraMode value) {

    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setCamNearClipDistance(String name, float value) {
        // get from Map
        CamData camData = cameras.get(name + CAMERA_SUFFIX);
        if (camData == null) {
            throw new RuntimeException("Invalid camera name: " + name + " !!!");
        }
        // set
        camData.setNearClippingDistance(value);

        // check for active cam
//        if (name.equals(getActiveCamera())) {
//            ogreCam.setNearClipDistance(value);
//
//            if (!sceneIsInCreation) {
//                // tell renderloop
//                OgreSystem.getDefault().redraw();
//            }
//        }
    }

    /**
     * 
     * @param name
     * @param animationName
     * @param loop
     * @param autoUpdate
     * @return void
     */
    public void setEntityAnimation(String name, String animationName, boolean loop, boolean autoUpdate) {

    }

    /**
     * 
     * 
     */
    public void setEntityMesh(String name, String meshName) {
        // get from Map
        IEntity entity = entities.get(name + ENTITY_SUFFIX);
        if (entity == null) {
            throw new RuntimeException("Invalid entity name: " + name + " !!!");
        }
        // set
        // entity.setm

        if (!sceneIsInCreation) {
            // tell renderloop
            OgreSystem.getDefault().redraw();
        }
    }

    /**
     * 
     * @param name
     * @param flags
     * @return void
     */
    public void setEntityQueryFlags(String name, long flags) {
        // get from Map
        IEntity entity = entities.get(name + ENTITY_SUFFIX);
        if (entity == null) {
            throw new RuntimeException("Invalid entity name: " + name + " !!!");
        }
        // set
        entity.setQueryFlags(flags);
    }

    /**
     * 
     * @param mode
     * @param colour
     * @param expDensity
     * @param linearStart
     * @param linearEnd
     * @return void
     */
    public void setFog(FogMode mode, javax.vecmath.Color4f colour, float expDensity, float linearStart, float linearEnd) {

    }

    /**
     * 
     * @param name
     * @param range
     * @param constant
     * @param linear
     * @param quadratic
     * @return void
     */
    public void setLightAttenuation(String name, float range, float constant, float linear, float quadratic) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // set
        light.setAttenuation(range, constant, linear, quadratic);

        if (!sceneIsInCreation) {
            // tell renderloop
            OgreSystem.getDefault().redraw();
        }
    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setLightDiffuseColour(String name, javax.vecmath.Color4f value) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // set
        light.setDiffuseColour(value.x, value.y, value.z);

        if (!sceneIsInCreation) {
            // tell renderloop
            OgreSystem.getDefault().redraw();
        }
    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setLightDirection(String name, javax.vecmath.Vector3f value) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // set
        light.setDirection(value.x, value.y, value.z);

        if (!sceneIsInCreation) {
            // tell renderloop
            OgreSystem.getDefault().redraw();
        }
    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setLightPowerScale(String name, float value) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // set
        // light.setPowerScale(value);

        if (!sceneIsInCreation) {
            // tell renderloop
            OgreSystem.getDefault().redraw();
        }
    }

    /**
     * 
     * @param name
     * @param flags
     * @return void
     */
    public void setLightQueryFlags(String name, long flags) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // set
        light.setQueryFlags(flags);
    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setLightSpecularColour(String name, javax.vecmath.Color4f value) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // set
        light.setSpecularColour(value.x, value.y, value.z);

        if (!sceneIsInCreation) {
            // tell renderloop
            OgreSystem.getDefault().redraw();
        }
    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setLightType(String name, LightType value) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // set
        switch (value) {
        case SPOT:
            light.setType(Light.LightTypes.LT_SPOTLIGHT);
            break;
        case POINT:
            light.setType(Light.LightTypes.LT_POINT);
            break;
        case DIRECTIONAL:
            light.setType(Light.LightTypes.LT_DIRECTIONAL);
            break;
        default:
            throw new RuntimeException("Invalid light type !!!");
        }

        if (!sceneIsInCreation) {
            // tell renderloop
            OgreSystem.getDefault().redraw();
        }
    }

    /**
     * 
     */
    public void setNodeIsShadowCaster(String name, boolean value) {
        /*
         * // get node from map ISceneNode node = nodes.get(name); if (node ==
         * null) throw new RuntimeException("Invalid node name: " + name + "
         * !!!"); // set new value node.scale(value);
         * 
         * if (!sceneIsInCreation) { // tell renderloop about change
         * OgrePlugin.getDefault().redraw(); }
         */
    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setNodeOrientation(String name, javax.vecmath.Quat4f value) {
        // get node from map
        ISceneNode node = nodes.get(name);
        if (node == null) {
            throw new RuntimeException("Invalid node name: " + name + " !!!");
        }
        // set new value
        node.setOrientation(value.x, value.y, value.z, value.w);

        if (!sceneIsInCreation) {
            // tell renderloop about change
            OgreSystem.getDefault().redraw();

            // tell listeners
            fireNodeOrientationChanged(name, value);
        }
    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setNodePosition(String name, javax.vecmath.Vector3f value) {
        // get node from map
        ISceneNode node = nodes.get(name);
        if (node == null) {
            throw new RuntimeException("Invalid node name: " + name + " !!!");
        }

        // set new value
        node.setPosition(value.x, value.y, value.z);

        if (!sceneIsInCreation) {
            // tell renderloop about change
            OgreSystem.getDefault().redraw();

            // tell listeners
            fireNodePositionChanged(name, value);
        }
    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setNodeScale(String name, javax.vecmath.Vector3f value) {
        // get node from map
        ISceneNode node = nodes.get(name);
        if (node == null) {
            throw new RuntimeException("Invalid node name: " + name + " !!!");
        }
        // set new value
        node.scale(value.x, value.y, value.z);

        if (!sceneIsInCreation) {
            // tell renderloop about change
            OgreSystem.getDefault().redraw();

            // tell listeners
            fireNodeScaleChanged(name, value);
        }
    }

    /**
     * 
     * @param name
     * @param value
     * @return void
     */
    public void setNodeVisible(String name, boolean value) {
        /*
         * // get node from map ISceneNode node = nodes.get(name); if (node ==
         * null) throw new RuntimeException("Invalid node name: " + name + "
         * !!!"); // set new value node.scale(value);
         * 
         * if (!sceneIsInCreation) { // tell renderloop about change
         * OgrePlugin.getDefault().redraw(); // tell listeners
         * fireNodeScaleChanged(name, value); }
         */
    }

    /**
     * 
     * @param name
     * @param angle
     * @return void
     */
    public void setOverlayRotate(String name, float angle) {

    }

    /**
     * 
     * @param name
     * @param x
     * @param y
     * @return void
     */
    public void setOverlayScale(String name, float x, float y) {

    }

    /**
     * 
     * @param name
     * @param x
     * @param y
     * @return void
     */
    public void setOverlayScroll(String name, float x, float y) {

    }

    /**
     * 
     * @param name
     * @param zorder
     * @return void
     */
    public void setOverlayZOrder(String name, int zorder) {

    }

    /**
     * 
     * @param name
     * @param autoUpdateBounds
     * @return void
     */
    public void setParticleSystemAutoUpdateBounds(String name, boolean autoUpdateBounds) {

    }

    /**
     * 
     * @param name
     * @param height
     * @return void
     */
    public void setParticleSystemDefaultHeight(String name, float height) {

    }

    /**
     * 
     * @param name
     * @param width
     * @return void
     */
    public void setParticleSystemDefaultWidth(String name, float width) {

    }

    /**
     * 
     * @param name
     * @param nonVisibleTimeout
     * @return void
     */
    public void setParticleSystemNonVisibleTimeOut(String name, float nonVisibleTimeout) {

    }

    /**
     * 
     * @param name
     * @param flags
     * @return void
     */
    public void setParticleSystemQueryFlags(String name, long flags) {

    }

    /**
     * 
     * @param name
     * @param sortingEnabled
     * @return void
     */
    public void setParticleSystemSortingEnabled(String name, boolean sortingEnabled) {

    }

    /**
     * 
     * @param name
     * @param speedFactor
     * @return void
     */
    public void setParticleSystemSpeedFactor(String name, float speedFactor) {

    }

    /**
     * 
     * @param val
     * @return void
     */
    public void setSceneCreation(boolean val) {
        sceneIsInCreation = val;
        if (!sceneIsInCreation) {
            OgreSystem.getDefault().redraw();
        }
    }

    /**
     * 
     * @param distance
     * @return void
     */
    public void setShadowFarDistance(float distance) {

    }

    
    /**
     * 
     * @param materialName
     * @param distance
     * @param drawFirst
     * @param orientation
     * @param groupName
     * @return void
     */
    public void setSkyBox(String materialName, float distance, boolean drawFirst, javax.vecmath.Quat4f orientation,
            String groupName) {

    }

    /**
     * 
     * @param materialName
     * @param curvature
     * @param tiling
     * @param distance
     * @param drawFirst
     * @param orientation
     * @param xsegments
     * @param ysegments
     * @param ysegments_keep
     * @param groupName
     * @return void
     */
    public void setSkyDome(String materialName, float curvature, float tiling, float distance, boolean drawFirst,
            javax.vecmath.Quat4f orientation, int xsegments, int ysegments, int ysegments_keep, String groupName) {

    }

    /**
     * 
     * @param materialName
     * @param scale
     * @param tiling
     * @param drawFirst
     * @param bow
     * @param xsegments
     * @param ysegments
     * @param groupName
     * @param planeNormal
     * @param planeDistance
     * @return void
     */
    public void setSkyPlane(String materialName, float scale, float tiling, boolean drawFirst, float bow,
            int xsegments, int ysegments, String groupName, javax.vecmath.Vector3f planeNormal,
            javax.vecmath.Vector3f planeDistance) {

    }

    /**
     * 
     * @param name
     * @param innerAngle
     * @param outerAngle
     * @param falloff
     * @return void
     */
    public void setSpotlightRange(String name, float innerAngle, float outerAngle, float falloff) {
        // get from Map
        ILight light = lights.get(name + LIGHT_SUFFIX);
        if (light == null) {
            throw new RuntimeException("Invalid light name: " + name + " !!!");
        }
        // set
        IRadian tmp = new Radian(innerAngle);
        light.setSpotlightInnerAngle(tmp);
        tmp.delete();
        tmp = new Radian(outerAngle);
        light.setSpotlightOuterAngle(tmp);
        tmp.delete();
        light.setSpotlightFalloff(falloff);

        if (!sceneIsInCreation) {
            // tell renderloop
            OgreSystem.getDefault().redraw();
        }
    }

    /**
     * 
     * @param filename
     * @return void
     */
    public void setWorldGeometry(String filename) {

    }

    /**
     * 
     * @param name
     * @return void
     */
    public void showOverlay(String name) {

    }

    /**
     * 
     * @param name
     * @param timeSinceLastFrame
     * @return void
     */
    public void updateEntityAnimation(String name, float timeSinceLastFrame) {

    }

    /**
     * Rotate the node around the Y-axis.
     * 
     * @param camera
     * @param rot
     */
    public void yawNode(String nodeName, float angle) {
        // get node from map
        ISceneNode node = nodes.get(nodeName);
        if (node == null) {
            throw new RuntimeException("Invalid node name: " + nodeName + " !!!");
        }

        // set new value
        IRadian rad = new Radian(angle);
        node.yaw(rad, TransformSpace.TS_LOCAL);
        rad.delete();

        if (!sceneIsInCreation) {
            // tell renderloop about change
            OgreSystem.getDefault().redraw();

            // tell listeners
            fireNodeOrientationChanged(nodeName, node.getOrientation());
        }

    }
    
    
    private ICamera activateCam(String name) {
        // TODO delete Ogre Objects
        
//        if (name.equals(activeCam)) {
//            oldOgreCam = ogreCam;
//        }
        // deactivate cam
//        deactivateCamera();

        // get CamData from Map
        CamData camData = cameras.get(name + CAMERA_SUFFIX);
        if (camData == null) {
            throw new RuntimeException("Invalid camera name: " + name + " !!!");
        }
        // get node from map
        ISceneNode node = nodes.get(name);
        if (node == null) {
            throw new RuntimeException("Cannot create camera for non-existant node: " + name + " !!!");
        }
        // set active cam
        //activeCam = name;

        // create new camera
        // TODO delete OGRE Cam object
        
        ICamera ogreCam = sceneManager.createCamera(name + CAMERA_SUFFIX);
        ogreCams.put(name, ogreCam);
        

        // TODO set Cam Mode
        ogreCam.setNearClipDistance(camData.getNearClippingDistance());
        ogreCam.setFarClipDistance(camData.getFarClippingDistance());
        // ogreCam.lookAt(camData.getLookAt());
        setCameraNodeLookAt(name, new Vector3f(camData.getLookAt()));
        ogreCam.setQueryFlags(camData.getQueryFlags());
        // ogreCam.setAspectRatio(this.getSize().x / this.getSize().y);

        
        // attach cam to node        
        node.attachObject(ogreCam);
        
        return ogreCam;
    }
}
