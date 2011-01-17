/**
 * CADNavigator.java
 *
 * Copyright &copy; 2005-2008, netAllied GmbH, Tettnang, Germany.
 * 
 * @author kklesats
 */
package org.ogre4j.eclipse.meshviewer;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.ogre4j.IAxisAlignedBox;
import org.ogre4j.ICamera;
import org.ogre4j.IQuaternion;
import org.ogre4j.IVector3;
import org.ogre4j.Root;
import org.ogre4j.Vector3;

/**
 * InputListener to navigate like in a CAD system.
 * 
 * @author Kai Klesatschke <kai.klesatschke@netallied.de>
 */
public class CADNavigator extends WASDNavigator {
    private static final float MIN_PIVOT_DISTANCE = 1f;

    /**
     * Calculates a new absolute position from origin.
     * 
     * @param center
     *            Origin point.
     * @param distance
     *            Distance between the origin and the new point.
     * @param axisAngle
     *            Axis angle in radians to rotate.
     * @param axis
     *            Defines which axes are affected.
     * @return Returns the new absolute position.
     */
    public static Vector3f calcNewPosition(Vector3f center, float distance, double axisAngle, Vector3f axis) {
        Vector3f newPos = new Vector3f(center);
        if (axis.x > 0) {
            newPos.x += Math.tan(axisAngle) * distance;
        }
        if (axis.y > 0) {
            newPos.y += Math.sin(axisAngle) * distance;
            // newPos.y += Math.cos(axisAngle) * distance;
        }
        if (axis.z > 0) {
            newPos.z += Math.cos(axisAngle) * distance;
            // newPos.z += Math.sin(axisAngle) * distance;
        }
        return newPos;
    }

    private Vector3f lookAt;

    private boolean rotate;

    private boolean move;

    private boolean zoom;

    private IAxisAlignedBox sceneBB;

    public CADNavigator() {
        lookAt = new Vector3f(0, 0, 0);
    }

    public CADNavigator(Vector3f lookAt) {
        this.lookAt = lookAt;
    }

    private float calcMoveFactor() {
        float originDist = calcPivotDistance();
        float factor = (float) (originDist * 0.01);
        if (factor < 1)
            return 1;
        return factor;
    }

    private float calcPivotDistance() {
        IVector3 nativePos = camera.getPosition();
        Vector3f pos = toJavaObject(nativePos);
        Vector3f pivot = new Vector3f(lookAt);
        pivot.sub(pos);
        float originDist = pivot.length();
        return originDist;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.keyCode == SWT.HOME) {
            if (this.sceneBB != null)
                setInitialCameraPosition(this.sceneBB);
        }
        super.keyPressed(e);
    }

    public void mouseDown(MouseEvent e) {
        if ((e.stateMask & SWT.CONTROL) == 0 && (e.stateMask & SWT.ALT) == 0) {
            if (e.button == 1)
                rotate = true;
            else if (e.button == 2)
                move = true;
            else if (e.button == 3)
                zoom = true;
        }

        if (rotate || move || zoom) {
            lastX = e.x;
            lastY = e.y;
            Root.getSingleton().renderOneFrame();
        }
    }

    public void mouseMove(MouseEvent event) {
        if (rotate || move || zoom) {
            // calc delta to last position
            int deltaX = event.x - lastX;
            int deltaY = event.y - lastY;
            // update position
            lastX = event.x;
            lastY = event.y;

            if (rotate) {
                rotate(deltaX, deltaY);
            } else if (move) {
                move(deltaX, deltaY);
            } else if (zoom)
                zoom(deltaX, deltaY);

            Root.getSingleton().renderOneFrame();
        }
    }

    @Override
    public void mouseUp(MouseEvent e) {
        if (rotate || move || zoom) {
            if (e.button == 1)
                rotate = false;
            else if (e.button == 2)
                move = false;
            else if (e.button == 3)
                zoom = false;
        }
    }

    public void move(float delta_x, float delta_y) {
        float factor = calcMoveFactor();

        Vector3f moveVec = new Vector3f();
        moveVec.x = -delta_x * factor;
        moveVec.y = delta_y * factor;
        moveVec.z = 0;

        IVector3 nativeMoveVec = toNativeObject(moveVec);
        camera.moveRelative(nativeMoveVec);
        nativeMoveVec.delete();

        IQuaternion orient = camera.getOrientation();
        translateRelative(lookAt, moveVec, toJavaObject(orient));
    }

    /**
     * TODO moveRelative description
     * 
     * @param camera
     * @param moveVec
     */
    private void moveRelative(ICamera camera, Vector3f moveVec) {
        IVector3 nativeMoveVec = toNativeObject(moveVec);
        camera.moveRelative(nativeMoveVec);
        nativeMoveVec.delete();
    }

    /**
     * Pushes the lookAt, along the camera movement direction, if the camera
     * comes to close.
     * 
     * @param direction
     *            The direction to push the lookAt along.
     * @param length
     *            The distance of camPos to lookAt.
     */
    private void pushLookAt(ICamera cam, float length) {
        Vector3f camPosition = toJavaObject(cam.getPosition());
        Vector3f pivot = new Vector3f(lookAt);
        Vector3f direction = new Vector3f();
        direction.sub(pivot, camPosition);
        direction.normalize();
        direction.scale(length);
        lookAt.add(camPosition, direction);
    }

    /**
     * TODO quatMulVec description
     * 
     * @param q
     * @param v
     * @return
     */
    public Vector3f quatMulVec(Quat4f q, Vector3f v) {
        // nVidia SDK implementation
        Vector3f uv = new Vector3f();
        Vector3f uuv = new Vector3f();
        Vector3f qvec = new Vector3f(q.x, q.y, q.z);
        uv.cross(qvec, v);
        uuv.cross(qvec, uv);
        uv.scale(2.0f * q.w);
        uuv.scale(2.0f);

        Vector3f result = new Vector3f();
        result.add(v);
        result.add(uv);
        result.add(uuv);
        return result;
    }

    /**
     * TODO rotate description
     * 
     * @param delta_x
     * @param delta_y
     */
    public void rotate(float delta_x, float delta_y) {
        IVector3 camPos = camera.getPosition();
        Vector3f pos = null;
        if (delta_x != 0) {
            pos = toJavaObject(camPos);
            Vector3f rotAxis = new Vector3f(0, 1, 0);
            float angle = (float) (-delta_x * Math.PI / 180.0);
            rotateRelative(pos, this.lookAt, rotAxis, angle, toJavaObject(this.camera.getOrientation()));
            setCameraPosition(pos);
            setCameraLookAt(this.lookAt);
        }
        if (delta_y != 0) {
            pos = toJavaObject(camPos);
            Vector3f rotAxis = new Vector3f(1, 0, 0);
            float angle = (float) (-delta_y * Math.PI / 180.0);
            rotateRelative(pos, this.lookAt, rotAxis, angle, toJavaObject(this.camera.getOrientation()));
            setCameraPosition(pos);
            setCameraLookAt(this.lookAt);
        }
    }

    /**
     * TODO rotateRelative description
     * 
     * @param point
     * @param pivot
     * @param axis
     * @param angle
     * @param orientation
     */
    public void rotateRelative(Vector3f point, Vector3f pivot, Vector3f axis, float angle, Quat4f orientation) {
        // translation into origin
        point.sub(pivot);

        // apply rotation
        axis = quatMulVec(orientation, axis);
        AxisAngle4f axisAngle = new AxisAngle4f(axis, angle);
        Matrix3f m = new Matrix3f();
        m.set(axisAngle);
        m.transform(point);

        // reverse translation
        point.add(pivot);
    }

    @Override
    public void setCamera(ICamera camera) {
        super.setCamera(camera);
        if (camera != null)
            camera.setFixedYawAxis(false, Vector3.getUNIT_Y());
    }

    /**
     * TODO setLookAt description
     * 
     * @param lookAt
     */
    public void setCameraLookAt(Vector3f lookAt) {
        this.lookAt.set(lookAt);
        this.camera.lookAt(lookAt.getX(), lookAt.getY(), lookAt.getZ());
    }

    /**
     * TODO setCameraPosition description
     * 
     * @param pos
     */
    void setCameraPosition(Vector3f pos) {
        IVector3 nativePos = toNativeObject(pos);
        camera.setPosition(nativePos);
        nativePos.delete();
    }

    /**
     * TODO setInitialCameraPosition description
     * 
     * @param bb
     */
    public void setInitialCameraPosition(IAxisAlignedBox bb) {
        this.sceneBB = bb;
        // get length of diagonal vector
        Vector3f diagonal = toJavaObject(bb.getMaximum());
        diagonal.sub(toJavaObject(bb.getMinimum()));
        float length = diagonal.length();

        // calc center
        IVector3 nativeCenter = new Vector3();
        bb.getCenter(nativeCenter);
        Vector3f center = new Vector3f(nativeCenter.getx(), nativeCenter.gety(), nativeCenter.getz());
        nativeCenter.delete();

        // new cam position 45 degrees with length of the diagonal from center.
        // only on y and z axis.
        Vector3f axis = new Vector3f(0, 1, 1);

        Vector3f camPos = null;
        camPos = calcNewPosition(center, length * 1.2f, 0, axis);
        // Matrix4f jtCorrection = new Matrix4f();
        // jtCorrection.set(new AxisAngle4f(1,0,0,(float)Math.toRadians(90)));
        // jtCorrection.transform(camPos);

        this.setCameraPosition(camPos);
        this.setCameraLookAt(center);
    }

    /**
     * TODO toJavaObject description
     * 
     * @param orient
     * @return
     */
    private Quat4f toJavaObject(IQuaternion orient) {
        return new Quat4f(orient.getx(), orient.gety(), orient.getz(), orient.getw());
    }

    /**
     * TODO toJavaObject description
     * 
     * @param nativePos
     * @return
     */
    private Vector3f toJavaObject(IVector3 nativePos) {
        return new Vector3f(nativePos.getx(), nativePos.gety(), nativePos.getz());
    }

    /**
     * TODO newNativeObject description
     * 
     * @param moveVec
     * @return
     */
    private IVector3 toNativeObject(Vector3f moveVec) {
        return new Vector3(moveVec.getX(), moveVec.getY(), moveVec.getZ());
    }

    /**
     * TODO translateRelative description
     * 
     * @param point
     * @param vec
     * @param orientation
     */
    public void translateRelative(Vector3f point, Vector3f vec, Quat4f orientation) {
        Vector3f trans = quatMulVec(orientation, vec);
        point.add(trans);
    }

    /**
     * TODO zoom description
     * 
     * @param delta_x
     * @param delta_y
     */
    public void zoom(float delta_x, float delta_y) {
        float factor = calcMoveFactor();

        Vector3f moveVec = new Vector3f();
        moveVec.x = 0;
        moveVec.y = 0;
        moveVec.z = delta_y * factor;        
        moveRelative(camera, moveVec);
        float originDist = calcPivotDistance();
        if (originDist < MIN_PIVOT_DISTANCE) {
            pushLookAt(camera, MIN_PIVOT_DISTANCE);
        }
    }
}
