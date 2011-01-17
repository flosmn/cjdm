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
package org.ogre4j.eclipse.ogreface.examples.navigators;

import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Point;
import org.ogre4j.eclipse.ogreface.ogresys.OgreCanvas;
import org.ogre4j.eclipse.ogreface.ogresys.OgreScene;
import org.ogre4j.eclipse.ogreface.viewer.INavigator;

/**
 * First Person Navigator.
 * 
 * @author kklesats
 */
public class FirstPersonNavigator implements INavigator, KeyListener, MouseListener, MouseMoveListener {

    /**
     * The parent canvas of the node to control.
     */
    private OgreCanvas canvas = null;
    
    private OgreScene scene = null;

    /**
     * The node to control.
     */
    private String camera = null;

    /**
     * The last screen position of the mouse cursor.
     */
    private Point screenPosition = null;

    /**
     * If head rotation is enabled.
     */
    private boolean look = false;

    /**
     * Default ctor.
     */
    public FirstPersonNavigator() {
    }

    /**
     * Convenience method.
     * 
     * @see OgreCanvas#getNodeOrientation(String);
     */
    private Quat4f getCameraOrientation() {
        return this.scene.getNodeOrientation(this.camera);
    }

    /**
     * Convenience method.
     * 
     * @see OgreCanvas#getNodePosition(String);
     */
    private Vector3f getCameraPosition() {
        return this.scene.getNodePosition(this.camera);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
     */
    public void keyPressed(KeyEvent e) {
        Vector3f moveVec = new Vector3f();

        if (e.keyCode == 'w')
            moveVec.z = -1;

        else if (e.keyCode == 'a')
            moveVec.x = -1;

        else if (e.keyCode == 's')
            moveVec.z = +1;

        else if (e.keyCode == 'd')
            moveVec.x = +1;

        else if (e.keyCode == 'c')
            moveVec.y = -1;

        else if (e.keyCode == 0x20)
            moveVec.y = +1;

        moveVec.scale(10);
        moveRelativeToOrientation(moveVec);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
     */
    public void keyReleased(KeyEvent e) {
        // do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt.events.MouseEvent)
     */
    public void mouseDoubleClick(MouseEvent e) {
        // do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events.MouseEvent)
     */
    public void mouseDown(MouseEvent e) {
        if (e.button == 1) {
            this.look = true;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.MouseMoveListener#mouseMove(org.eclipse.swt.events.MouseEvent)
     */
    public void mouseMove(MouseEvent e) {
        if (this.screenPosition == null) {
            this.screenPosition = new Point(e.x, e.y);
            return;
        }

        int delta_x = e.x - screenPosition.x;
        int delta_y = e.y - screenPosition.y;

        screenPosition.x = e.x;
        screenPosition.y = e.y;

        if (delta_x == 0 && delta_y == 0)
            return;

        if (this.look) {
            // Rotates the camera anticlockwise around it's local y axis.
            double angle = (-delta_x * 2 * ((Math.toRadians(Math.PI / 4.0f)) / 1.333f / 320.0f)) * 100;
            yawCamera(angle);

            // Pitches the camera up/down anticlockwise around it's local z
            // axis.
            angle = (-delta_y * ((Math.toRadians(Math.PI / 4.0f) / 240.0f))) * 100;
            pitchCamera(angle);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.MouseEvent)
     */
    public void mouseUp(MouseEvent e) {
        if (e.button == 1) {
            this.look = false;
        }
    }

    /**
     * Moves the camera with respect to its current position and orientation.
     * 
     * @param vector
     *            The vector to move relative from the current position and
     *            orientation.
     */
    private void moveRelativeToOrientation(Vector3f vector) {
        Quat4f orient = getCameraOrientation();
        Vector3f pos = getCameraPosition();

        Matrix3f m = new Matrix3f();
        m.set(orient);
        m.transform(vector);

        pos.add(vector);
        setCameraPosition(pos);
    }

    /**
     * Convenience method.
     * 
     * @see OgreScene#pitchNode(String, float)
     */
    private void pitchCamera(double angle) {
        this.scene.pitchNode(this.camera, (float) angle);
    }

    /**
     * @{inheritdoc}
     * @see org.ogre4j.eclipse.ogreface.viewer.INavigator#setCameraName(java.lang.String)
     */
    public void setCameraName(String camera) {
        this.camera = camera;
    }

    /**
     * Convenience method.
     * 
     * @see OgreScene#setNodePosition(String, Vector3f)
     */
    private void setCameraPosition(Vector3f pos) {
        this.scene.setNodePosition(this.camera, pos);
    }

    /**
     * @{inheritdoc}
     * @see org.ogre4j.eclipse.ogreface.viewer.INavigator#setCanvas(org.ogre4j.eclipse.ogreface.ogresys.OgreScene)
     */
    public void setCanvas(OgreCanvas canvas) {
        this.canvas = canvas;
        this.scene = canvas.getScene();
        this.canvas.addKeyListener(this);
        this.canvas.addMouseListener(this);
        this.canvas.addMouseMoveListener(this);
    }

    /**
     * Convenience method.
     * 
     * @see OgreScene#yawNode(String, float)
     */
    private void yawCamera(double angle) {
        this.scene.yawNode(this.camera, (float) angle);
    }

}
