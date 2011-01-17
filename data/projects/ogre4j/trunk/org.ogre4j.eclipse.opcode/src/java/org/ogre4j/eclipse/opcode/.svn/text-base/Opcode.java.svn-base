/**
 * Opcode.java
 *
 * Copyright &copy; 2005, netAllied GmbH, Tettnang, Germany.
 * 
 * Version Information
 * -------------------
 * $Revision: $
 * $Date: $
 * $Author: $
 */
package org.ogre4j.eclipse.opcode;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import org.ogre4j.Entity;
import org.ogre4j.IEntity;
import org.ogre4j.IRaySceneQuery;
import org.xbig.base.InstancePointer;

/**
 * TODO Opcode type/class description.
 * 
 * @author Kai Klesatschke <kai.klesatschke@netallied.de>
 */
public class Opcode {
    /**
     * 
     */
    private static Opcode singleton;

    /**
     * TODO addEntity description
     * 
     * @param pEntity
     */
    private static native void addEntity(long pEntity);

    /**
     * TODO getClosestEntity description
     * 
     * @param pRaySceneQuery
     * @return
     */
    private static native int getClosestEntity(long pRaySceneQuery);

    /**
     * TODO getClosestHit description
     * 
     * @param pRaySceneQuery
     * @return
     */
    private static native float[] getClosestHit(long pRaySceneQuery);

    /**
     * TODO getSingleton description
     * 
     * @return
     * @throws Exception
     */
    public static Opcode getSingleton() {
        if (singleton == null)
            singleton = new Opcode();
        return singleton;
    }

    /**
     * TODO init description
     */
    private static native void init();

    /**
     * TODO ndispose description
     */
    private static native void ndispose();

    /**
     * TODO removeEntity description
     * 
     * @param pEntity
     */
    private static native void removeEntity(long pEntity);

    /**
     * TODO Opcode constructor description
     * 
     * @throws RuntimeException
     */
    private Opcode() throws RuntimeException {
        if (singleton != null)
            throw new RuntimeException("OPCODE already initialized");
        init();
    }

    /**
     * TODO addEntity description
     * 
     * @param entity
     */
    public void addEntity(IEntity entity) {
        addEntity(entity.getInstancePointer().pointer);
    }

    /**
     * TODO dispose description
     */
    public void dispose() {
        ndispose();
        singleton = null;
    }

    /**
     * TODO getClosestEntity description
     * 
     * @param raySceneQuery
     * @return
     */
    public Entity getClosestEntity(IRaySceneQuery raySceneQuery) {
        int ptr = getClosestEntity(raySceneQuery.getInstancePointer().pointer);
        if (ptr == 0)
            return null;

        return new Entity(new InstancePointer(ptr));
    }

    /**
     * TODO getClosestHit description
     * 
     * @param raySceneQuery
     * @param hitCoord
     * @param normal
     * @return
     */
    public boolean getClosestHit(IRaySceneQuery raySceneQuery, Point3f hitCoord, Vector3f normal) {
        float[] values = getClosestHit(raySceneQuery.getInstancePointer().pointer);
        if (values == null)
            return false;

        if (hitCoord == null || normal == null)
            throw new IllegalArgumentException();

        hitCoord.x = values[0];
        hitCoord.y = values[1];
        hitCoord.z = values[2];
        normal.x = values[3];
        normal.y = values[4];
        normal.z = values[5];
        return true;
    }

    public IEntity executeQuery(IRaySceneQuery raySceneQuery, Point3f hitCoord, Vector3f normal) {
        float[] pointArray = new float[3];
        float[] normalArray = new float[3];
        long entityPtr = executeQuery(raySceneQuery.getInstancePointer().pointer, pointArray, normalArray);
        if (entityPtr == 0) {
            return null;
        }
        hitCoord.set(pointArray);
        normal.set(normalArray);
        return new Entity(new InstancePointer(entityPtr));
    }

    private static native long executeQuery(long pRaySceneQuery, float[] point, float[] normal);

    public void removeEntity(IEntity entity) {
        removeEntity(entity.getInstancePointer().pointer);
    }
}
