///////////////////////////////////////////////////////////////////////////////
/// This source file is generated by XBiG (The XSLT Bindings Generator)
/// For the latest info, see http://sourceforge.net/projects/xbig/
///
/// Copyright (c) 2004-2008 NetAllied Systems GmbH, Ravensburg. All rights reserved.
/// Also see acknowledgements in Readme.html
///
/// This program is free software; you can redistribute it and/or modify it under
/// the terms of the GNU Lesser General Public License as published by the Free Software
/// Foundation; either version 2 of the License, or (at your option) any later
/// version.
///
/// This program is distributed in the hope that it will be useful, but WITHOUT
/// ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
/// FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
///
/// You should have received a copy of the GNU Lesser General Public License along with
/// this program; if not, write to the Free Software Foundation, Inc., 59 Temple
/// Place - Suite 330, Boston, MA 02111-1307, USA, or go to
/// http://www.gnu.org/copyleft/lesser.txt.
///
/// Machine generated file
///////////////////////////////////////////////////////////////////////////////



#ifdef WIN32
	// disable warnings
	#pragma warning (disable : 4267) // conversion from 'size_t' to 'jint'
#else

#endif


#ifndef __Included_org_ogre4j_PatchSurface__
#define __Included_org_ogre4j_PatchSurface__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     PatchSurface()
 * Type:       constructor
 * Definition: Ogre::PatchSurface::PatchSurface
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_PatchSurface__1_1createPatchSurface (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     defineSurface()
 * Type:       non-virtual method
 * Definition: void Ogre::PatchSurface::defineSurface
 * Signature:  (VOgre_VertexDeclarationIIOgre_PatchSurface_PatchSurfaceTypeIIOgre_PatchSurface_VisibleSide)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PatchSurface__1defineSurface_1_1vpVertexDeclarationpivivPatchSurfaceTypevivivVisibleSidev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong controlPointBuffer, 
  jlong declaration, 
  jint width, 
  jint height, 
  jint pType, 
  jint uMaxSubdivisionLevel, 
  jint vMaxSubdivisionLevel, 
  jint visibleSide
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getRequiredVertexCount()
 * Type:       non-virtual method
 * Definition: size_t Ogre::PatchSurface::getRequiredVertexCount
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_PatchSurface__1getRequiredVertexCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getRequiredIndexCount()
 * Type:       non-virtual method
 * Definition: size_t Ogre::PatchSurface::getRequiredIndexCount
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_PatchSurface__1getRequiredIndexCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getCurrentIndexCount()
 * Type:       non-virtual method
 * Definition: size_t Ogre::PatchSurface::getCurrentIndexCount
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_PatchSurface__1getCurrentIndexCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getIndexOffset()
 * Type:       non-virtual method
 * Definition: size_t Ogre::PatchSurface::getIndexOffset
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_PatchSurface__1getIndexOffset_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getVertexOffset()
 * Type:       non-virtual method
 * Definition: size_t Ogre::PatchSurface::getVertexOffset
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_PatchSurface__1getVertexOffset_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getBounds()
 * Type:       non-virtual method
 * Definition: const AxisAlignedBox& Ogre::PatchSurface::getBounds
 * Signature:  ()Ogre_AxisAlignedBox
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_PatchSurface__1getBounds_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getBoundingSphereRadius()
 * Type:       non-virtual method
 * Definition: Real Ogre::PatchSurface::getBoundingSphereRadius
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_PatchSurface__1getBoundingSphereRadius_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     build()
 * Type:       non-virtual method
 * Definition: void Ogre::PatchSurface::build
 * Signature:  (Ogre_HardwareVertexBufferSharedPtrIOgre_HardwareIndexBufferSharedPtrI)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PatchSurface__1build_1_1HardwareVertexBufferSharedPtrvivHardwareIndexBufferSharedPtrviv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong destVertexBuffer, 
  jint vertexStart, 
  jlong destIndexBuffer, 
  jint indexStart
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     setSubdivisionFactor()
 * Type:       non-virtual method
 * Definition: void Ogre::PatchSurface::setSubdivisionFactor
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PatchSurface__1setSubdivisionFactor_1_1Realv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat factor
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getSubdivisionFactor()
 * Type:       non-virtual method
 * Definition: Real Ogre::PatchSurface::getSubdivisionFactor
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_PatchSurface__1getSubdivisionFactor_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getControlPointBuffer()
 * Type:       non-virtual method
 * Definition: void* Ogre::PatchSurface::getControlPointBuffer
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_PatchSurface__1getControlPointBuffer_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     notifyControlPointBufferDeallocated()
 * Type:       non-virtual method
 * Definition: void Ogre::PatchSurface::notifyControlPointBufferDeallocated
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PatchSurface__1notifyControlPointBufferDeallocated (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::PatchSurface::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PatchSurface__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_PatchSurface__*/
