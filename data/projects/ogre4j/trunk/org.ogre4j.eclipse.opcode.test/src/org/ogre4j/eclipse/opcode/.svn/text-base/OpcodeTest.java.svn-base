/**
 * OpcodeTest.java
 *
 * Copyright &copy; 2008, netAllied GmbH, Tettnang, Germany. 
 * 
 * @author kklesats
 */
package org.ogre4j.eclipse.opcode;

import java.net.URL;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import junit.framework.Assert;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.junit.Test;
import org.ogre4j.Entity;
import org.ogre4j.ICamera;
import org.ogre4j.IEntity;
import org.ogre4j.IRay;
import org.ogre4j.IRaySceneQuery;
import org.ogre4j.IRoot;
import org.ogre4j.ISceneManager;
import org.ogre4j.ISceneNode;
import org.ogre4j.IViewport;
import org.ogre4j.Quaternion;
import org.ogre4j.Ray;
import org.ogre4j.Vector3;
import org.ogre4j.eclipse.OgrePlugin;
import org.ogre4j.eclipse.RenderWindowPool.RenderWindowCanvasPair;
import org.xbig.base.WithoutNativeObject;

public class OpcodeTest {

    static final String MESHES[] = { "athene.mesh", "Barrel.mesh", "column.mesh", "cube.mesh", "facial.mesh",
            "geosphere4500.mesh", "geosphere8000.mesh", "knot.mesh", "ogrehead.mesh", "razor.mesh",
            "sphere.mesh", "tudorhouse.mesh", "WoodPallet.mesh" };
    
    @Test
    public void test() throws Exception {
        IRoot root = OgrePlugin.getDefault().getOgreRoot();
        Assert.assertNotNull(root);

        // create RenderWindow with SWT handle
        RenderWindowCanvasPair windowCanvasPair = OgrePlugin.getDefault().getRenderWindowPool().accquireRenderWindow();
        Assert.assertNotNull(windowCanvasPair);

        windowCanvasPair.canvas.setEnabled(false);
        windowCanvasPair.canvas.setVisible(true);
        windowCanvasPair.renderWindow.windowMovedOrResized();

        // render window is created, resources can be loaded
        URL url = FileLocator.find(OpcodeTestPlugin.getInstance().getBundle(), new Path("resources"), null);
        url = FileLocator.toFileURL(url);
        IPath path = new Path(url.getPath());

        OgrePlugin.getDefault().addResourceLocation(path.toOSString(), OgrePlugin.RESOURCE_LOCATION_TYPE_FILE_SYSTEM,
                OpcodeTest.class.getName());
        OgrePlugin.getDefault().initializeResourceGroup(OpcodeTest.class.getName());

        // create scene manager
        ISceneManager sceneManager = root.createSceneManager(0, "FigureScene" + "_" + windowCanvasPair.canvas.handle);
        Assert.assertNotNull(sceneManager);

        // create camera
        ICamera camera = sceneManager.createCamera("Main Camera");
        Assert.assertNotNull(camera);
        camera.setPosition(100, 100, 100);
        camera.lookAt(0, 0, 0);
        camera.setNearClipDistance(0.1f);
        // camera.setAspectRatio(1);

        // create viewport
        IViewport viewport = windowCanvasPair.renderWindow.addViewport(camera, 0, 0.0f, 0.0f, 1.0f, 1.0f);
        Assert.assertNotNull(viewport);
        viewport.setOverlaysEnabled(false);
        viewport.setCamera(camera);

        // connect camera with render window
        windowCanvasPair.renderWindow.setActive(true);

        ISceneNode rootSceneNode = sceneManager.getRootSceneNode();
        Assert.assertNotNull(rootSceneNode);

        ISceneNode sceneNode = rootSceneNode.createChildSceneNode(Vector3.getZERO(), Quaternion.getIDENTITY());
        Assert.assertNotNull(sceneNode);
        sceneNode.setPosition(0, 0, 0);

        // create entity
        IEntity[] entities = new IEntity[MESHES.length];
        for (int m = 0, mcount = MESHES.length; m < mcount; ++m) {
            entities[m] = sceneManager.createEntity(MESHES[m], MESHES[m]);
            Assert.assertNotNull(entities[m]);
            sceneNode.attachObject(entities[m]);
        }

        root.renderOneFrame();

        Opcode opcode = Opcode.getSingleton();
        Assert.assertNotNull(opcode);

        for (int e = 0, ecount = entities.length; e < ecount; ++e) {
            opcode.addEntity(entities[e]);
        }

        IRay ray = new Ray(WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
        camera.getCameraToViewportRay(ray, 1, 1);
        IRaySceneQuery rsq = sceneManager.createRayQuery(ray, 0xFFFFFFFF);
        ray.delete();

        Point3f hitPoint = new Point3f();
        Vector3f hitNormal = new Vector3f();        
        IEntity entity = opcode.executeQuery(rsq, hitPoint, hitNormal);
        Assert.assertNotNull(entity);
        boolean hit = opcode.getClosestHit(rsq, hitPoint, hitNormal);
        Assert.assertTrue(hit);
        IEntity hitEntity = opcode.getClosestEntity(rsq);
        Assert.assertEquals(entity, hitEntity);
        if (hit && hitEntity != null) {
            System.err.println("---------------------------------------------------)");
            System.err.println("Closest Entity:\t"+hitEntity.getName());
            System.err.println("Hit Coords:\t"+hitPoint);
            System.err.println("Hit Normal:\t"+hitNormal);
            System.err.println("---------------------------------------------------)");
        }
        sceneManager.destroyQuery(rsq);

        for (int e = 0, ecount = entities.length; e < ecount; ++e) {
            opcode.removeEntity(entities[e]);
        }

        opcode.dispose();

        root.destroySceneManager(sceneManager);
        OgrePlugin.getDefault().getRenderWindowPool().releaseRenderWindow(windowCanvasPair);
    }
    
    @Test
    public void loop() throws Exception{
        for(int i=0; i<100; ++i){
            test();
        }
    }
}
