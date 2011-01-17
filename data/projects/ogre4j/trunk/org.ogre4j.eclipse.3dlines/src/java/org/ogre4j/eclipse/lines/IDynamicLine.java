package org.ogre4j.eclipse.lines;

import org.ogre4j.ICamera;
import org.ogre4j.ISimpleRenderable;
import org.ogre4j.IVector3;
import org.ogre4j.RenderOperation.OperationType;

/**
 * A 3D Line. See http://www.ogre3d.org/wiki/index.php/DynamicLineDrawing.
 *
 */
public interface IDynamicLine extends ISimpleRenderable {

    /**
     * Add a point to the point list.
     */
    void addPoint(float x, float y, float z);

    /**
     * Add a point to the point list.
     */
    void addPoint(IVector3 p);

    /**
     * Remove all points from the point list.
     */
    void clear();

    /**
     * Implementation of Ogre::SimpleRenderable,
     */
    float getBoundingRadius();

    /**
     * Return the total number of points in the point list.
     */
    int getNumPoints();

    OperationType getOperationType();

    /**
     * Return the location of an existing point in the point list.
     */
    IVector3 getPoint(int index);

    /**
     * Implementation of Ogre::SimpleRenderable.
     */
    float getSquaredViewDepth(ICamera cam);

    /**
     * Initializes the dynamic renderable.
     * 
     * @remarks This function should only be called once. It initializes the
     *          render operation, and calls the abstract function
     *          createVertexDeclaration().
     * @param operationType
     *            The type of render operation to perform.
     * @param useIndices
     *            Specifies whether to use indices to determine the vertices to
     *            use as input.
     */
    void initialize(OperationType operationType, boolean useIndices);

    /**
     * Change the location of an existing point in the point list.
     */
    void setPoint(int index, IVector3 value);

    /**
     * Call this to update the hardware buffer after making changes.
     */
    void update();
}
