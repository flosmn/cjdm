/**
 *  This source file is generated by XBiG (The XSLT Bindings Generator)
 *  For the latest info, see http://sourceforge.net/projects/xbig/
 * 
 *  Copyright (c) 2004-2008 NetAllied Systems GmbH, Ravensburg. All rights reserved.
 *  Also see acknowledgements in Readme.html
 * 
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU Lesser General Public License as published by the Free Software
 *  Foundation; either version 2 of the License, or (at your option) any later
 *  version.
 * 
 *  This program is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 *  FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public License along with
 *  this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 *  Place - Suite 330, Boston, MA 02111-1307, USA, or go to
 *  http://www.gnu.org/copyleft/lesser.txt.
 * 
 *  Machine generated file
 */

        

package org.ogre4j;


import org.xbig.base.*;
public interface IOptimisedUtil extends INativeObject {

    /** 
    Performs software vertex skinning. **/
    public void softwareVertexSkinning(FloatPointer srcPosPtr, FloatPointer destPosPtr, FloatPointer srcNormPtr, FloatPointer destNormPtr, FloatPointer blendWeightPtr, ShortPointer blendIndexPtr, NativeObjectPointer<org.ogre4j.IMatrix4> blendMatrices, int srcPosStride, int destPosStride, int srcNormStride, int destNormStride, int blendWeightStride, int blendIndexStride, int numWeightsPerVertex, int numVertices);

    /** 
    Performs a software vertex morph, of the kind used for morph animation although it can be used for other purposes. **/
    public void softwareVertexMorph(float t, FloatPointer srcPos1, FloatPointer srcPos2, FloatPointer dstPos, int numVertices);

    /** 
    Concatenate an affine matrix to an array of affine matrices. **/
    public void concatenateAffineMatrices(org.ogre4j.IMatrix4 baseMatrix, org.ogre4j.IMatrix4 srcMatrices, org.ogre4j.IMatrix4 dstMatrices, int numMatrices);

    /** 
    Calculate the face normals for the triangles based on position information. **/
    public void calculateFaceNormals(FloatPointer positions, org.ogre4j.IEdgeData.ITriangle triangles, org.ogre4j.IVector4 faceNormals, int numTriangles);

    /** 
    Calculate the light facing state of the triangle's face normals **/
    public void calculateLightFacing(org.ogre4j.IVector4 lightPos, org.ogre4j.IVector4 faceNormals, BytePointer lightFacings, int numFaces);

    /** 
    Extruding vertices by a fixed distance based on light position. **/
    public void extrudeVertices(org.ogre4j.IVector4 lightPos, float extrudeDist, FloatPointer srcPositions, FloatPointer destPositions, int numVertices);

}
