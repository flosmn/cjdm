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


#ifndef __Included_org_ogre4j_StaticGeometry_00024MaterialBucket__
#define __Included_org_ogre4j_StaticGeometry_00024MaterialBucket__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.StaticGeometry.00024MaterialBucket
 * Method:     MaterialBucket()
 * Type:       constructor
 * Definition: Ogre::StaticGeometry::MaterialBucket::MaterialBucket
 * Signature:  (Ogre_StaticGeometry_LODBucketstd_string)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_StaticGeometry_00024MaterialBucket__1_1createMaterialBucket_1_1LODBucketpStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong parent, 
  jstring materialName
);

/*
 * Class:      org.ogre4j.StaticGeometry.00024MaterialBucket
 * Method:     getParent()
 * Type:       non-virtual method
 * Definition: LODBucket* Ogre::StaticGeometry::MaterialBucket::getParent
 * Signature:  ()Ogre_StaticGeometry_LODBucket
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_StaticGeometry_00024MaterialBucket__1getParent (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.StaticGeometry.00024MaterialBucket
 * Method:     getMaterialName()
 * Type:       non-virtual method
 * Definition: const String& Ogre::StaticGeometry::MaterialBucket::getMaterialName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_StaticGeometry_00024MaterialBucket__1getMaterialName_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.StaticGeometry.00024MaterialBucket
 * Method:     assign()
 * Type:       non-virtual method
 * Definition: void Ogre::StaticGeometry::MaterialBucket::assign
 * Signature:  (Ogre_StaticGeometry_QueuedGeometry)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024MaterialBucket__1assign_1_1QueuedGeometryp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong qsm
);

/*
 * Class:      org.ogre4j.StaticGeometry.00024MaterialBucket
 * Method:     build()
 * Type:       non-virtual method
 * Definition: void Ogre::StaticGeometry::MaterialBucket::build
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024MaterialBucket__1build_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean stencilShadows
);

/*
 * Class:      org.ogre4j.StaticGeometry.00024MaterialBucket
 * Method:     addRenderables()
 * Type:       non-virtual method
 * Definition: void Ogre::StaticGeometry::MaterialBucket::addRenderables
 * Signature:  (Ogre_RenderQueueunsigned_charfloat)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024MaterialBucket__1addRenderables_1_1RenderQueuepuint8vRealv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong queue, 
  jshort group, 
  jfloat camSquaredDist
);

/*
 * Class:      org.ogre4j.StaticGeometry.00024MaterialBucket
 * Method:     getMaterial()
 * Type:       non-virtual method
 * Definition: const MaterialPtr& Ogre::StaticGeometry::MaterialBucket::getMaterial
 * Signature:  ()Ogre_MaterialPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_StaticGeometry_00024MaterialBucket__1getMaterial_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.StaticGeometry.00024MaterialBucket
 * Method:     getGeometryIterator()
 * Type:       non-virtual method
 * Definition: GeometryIterator Ogre::StaticGeometry::MaterialBucket::getGeometryIterator
 * Signature:  ()Ogre_StaticGeometry_MaterialBucket_GeometryIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_StaticGeometry_00024MaterialBucket__1getGeometryIterator (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.StaticGeometry.00024MaterialBucket
 * Method:     getCurrentTechnique()
 * Type:       non-virtual method
 * Definition: Technique* Ogre::StaticGeometry::MaterialBucket::getCurrentTechnique
 * Signature:  ()Ogre_Technique
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_StaticGeometry_00024MaterialBucket__1getCurrentTechnique_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.StaticGeometry.00024MaterialBucket
 * Method:     dump()
 * Type:       non-virtual method
 * Definition: void Ogre::StaticGeometry::MaterialBucket::dump
 * Signature:  (std_ofstream)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024MaterialBucket__1dump_1_1std_1ofstreamr_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong of
);

/*
 * Class:      org.ogre4j.StaticGeometry.00024MaterialBucket
 * Method:     visitRenderables()
 * Type:       non-virtual method
 * Definition: void Ogre::StaticGeometry::MaterialBucket::visitRenderables
 * Signature:  (Ogre_Renderable_VisitorZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024MaterialBucket__1visitRenderables_1_1Renderable_1Visitorpbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong visitor, 
  jboolean debugRenderables
);

/*
 * Class:      org.ogre4j.StaticGeometry.00024MaterialBucket
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::StaticGeometry::MaterialBucket::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024MaterialBucket__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_StaticGeometry_00024MaterialBucket__*/
