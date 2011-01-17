package org.ogre4j.eclipse.lines;

import org.ogre4j.ICamera;
import org.ogre4j.IVector3;
import org.ogre4j.SimpleRenderable;
import org.ogre4j.Vector3;
import org.ogre4j.RenderOperation.OperationType;
import org.xbig.base.InstancePointer;

/**
 * A 3D Line. See http://www.ogre3d.org/wiki/index.php/DynamicLineDrawing.
 *
 */
public class DynamicLine extends SimpleRenderable implements IDynamicLine {

    private native static long __createDynamicLine();

    /**
     * Default constructor.
     */
    public DynamicLine() {
        super(new org.xbig.base.InstancePointer(__createDynamicLine()), false);
    }

    /**
     * 
     * This constructor is public for internal useage only! Do not use it!
     * 
     */
    public DynamicLine(org.xbig.base.InstancePointer p) {
        super(p);
    }

    /**
     * 
     * Creates a Java wrapper object for an existing C++ object. If remote is
     * set to 'true' this object cannot be deleted in Java.
     * 
     */
    protected DynamicLine(org.xbig.base.InstancePointer p, boolean remote) {
        super(p, remote);
    }

    /**
     * {@inheritDoc}
     */
    public DynamicLine(org.xbig.base.WithoutNativeObject val) {
        super(val);
    }

    private final native void __delete(long _pointer_);

    private native void _addPoint__fff(long _pointer_, float x, float y, float z);

    private native void _addPoint__vector(long _pointer_, long p);

    private native void _clear(long _pointer_);

    private native int _getNumPoints(long _pointer_);

    private native int _getOperationType(long _pointer_);

    private native long _getPoint(long _pointer_, int index);

    private native float _getSquaredViewDepth(long _pointer_, long cam);

    private native void _initialize(long _pointer_, int operationType, boolean useIndices);

    private native void _setPoint(long _pointer_, int index, long value);

    private native void _update(long _pointer_);

    /**
     * @{inheritdoc}
     * @see org.ogre4j.eclipse.lines.IDynamicLine#addPoint(float, float, float)
     */
    public void addPoint(float x, float y, float z) {
        _addPoint__fff(this.object.pointer, x, y, z);
    }

    /**
     * @{inheritdoc}
     * @see org.ogre4j.eclipse.lines.IDynamicLine#addPoint(org.ogre4j.Vector3)
     */
    public void addPoint(IVector3 p) {
        _addPoint__vector(this.object.pointer, p.getInstancePointer().pointer);
    }

    /**
     * @{inheritdoc}
     * @see org.ogre4j.eclipse.lines.IDynamicLine#clear()
     */
    public void clear() {
        _clear(this.object.pointer);
    }

    /**
     * @{inheritdoc}
     * @see org.ogre4j.SimpleRenderable#delete()
     */
    @Override
    public void delete() {
        if (this.remote) {
            throw new RuntimeException("can't dispose object created by native library");
        }

        if (!this.deleted) {
            __delete(object.pointer);
            this.deleted = true;
            this.object.pointer = 0;
        }
    }

    /**
     * @{inheritdoc}
     * @see org.ogre4j.SimpleRenderable#finalize()
     */
    @Override
    public void finalize() {
        if (!this.remote && !this.deleted) {
            delete();
        }
    }

    /**
     * @{inheritdoc}
     * @see org.ogre4j.eclipse.lines.IDynamicLine#getNumPoints()
     */
    public int getNumPoints() {
        return _getNumPoints(this.object.pointer);
    }

    /**
     * @{inheritdoc}
     * @see org.ogre4j.eclipse.lines.IDynamicLine#getOperationType()
     */
    public OperationType getOperationType() {
        return OperationType.toEnum(_getOperationType(this.object.pointer));
    }

    /**
     * @{inheritdoc}
     * @see org.ogre4j.eclipse.lines.IDynamicLine#getPoint(int)
     */
    public IVector3 getPoint(int index) {
        return new Vector3(new InstancePointer(_getPoint(this.object.pointer, index)));
    }

    /**
     * @{inheritdoc}
     * @see org.ogre4j.eclipse.lines.IDynamicLine#getSquaredViewDepth(org.ogre4j.Camera)
     */
    @Override
    public float getSquaredViewDepth(ICamera cam) {
        return _getSquaredViewDepth(this.object.pointer, cam.getInstancePointer().pointer);
    }

    /**
     * @{inheritdoc}
     * @see org.ogre4j.eclipse.lines.IDynamicLine#initialize(org.ogre4j.RenderOperation.OperationType,
     *      boolean)
     */
    public void initialize(OperationType operationType, boolean useIndices) {
        _initialize(this.object.pointer, operationType.getValue(), useIndices);
    }

    /**
     * @{inheritdoc}
     * @see org.ogre4j.eclipse.lines.IDynamicLine#setPoint(int,
     *      org.ogre4j.Vector3)
     */
    public void setPoint(int index, IVector3 value) {
        _setPoint(this.object.pointer, index, value.getInstancePointer().pointer);
    }

    /**
     * @{inheritdoc}
     * @see org.ogre4j.eclipse.lines.IDynamicLine#update()
     */
    public void update() {
        _update(this.object.pointer);
    }
}
