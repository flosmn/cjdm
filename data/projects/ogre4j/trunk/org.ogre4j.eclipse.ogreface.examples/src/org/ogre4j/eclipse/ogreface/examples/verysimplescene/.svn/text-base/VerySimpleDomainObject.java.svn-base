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
package org.ogre4j.eclipse.ogreface.examples.verysimplescene;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class VerySimpleDomainObject {
    enum Type {
        ENTITY, LIGHT, CAMERA
    }

    private String name = null;

    private String mesh;

    private Vector3f position = new Vector3f(0, 0, 0);

    private Quat4f orientation = new Quat4f(0, 0, 0, 1);

    private Type type;

    public VerySimpleDomainObject(String name, String meshName) {
        this.name = name;
        this.mesh = meshName;
        this.type = Type.ENTITY;
    }

    public VerySimpleDomainObject(String name, String meshName, Vector3f position, Quat4f orientation) {
        this(name, meshName);
        this.position = position;
        this.orientation = orientation;
    }

    public VerySimpleDomainObject(String name, Type type) {
        this.name = name;
        this.mesh = null;
        this.type = type;
    }

    public VerySimpleDomainObject(String name, Type type, Vector3f position, Quat4f orientation) {
        this(name, type);
        this.position = position;
        this.orientation = orientation;

    }

    public String getName() {
        return name;
    }

    /**
     * @return the mesh
     */
    public String getMesh() {
        return mesh;
    }

    /**
     * @param mesh
     *            the mesh to set
     */
    public void setMesh(String mesh) {
        this.mesh = mesh;
    }

    /**
     * @return the orientation
     */
    public Quat4f getOrientation() {
        return orientation;
    }

    /**
     * @param orientation
     *            the orientation to set
     */
    public void setOrientation(Quat4f orientation) {
        this.orientation = orientation;
    }

    /**
     * @return the position
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * @param position
     *            the position to set
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
