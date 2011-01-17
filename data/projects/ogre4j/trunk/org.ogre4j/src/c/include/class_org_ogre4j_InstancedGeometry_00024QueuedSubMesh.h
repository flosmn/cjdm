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


#ifndef __Included_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__
#define __Included_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     QueuedSubMesh()
 * Type:       constructor
 * Definition: Ogre::InstancedGeometry::QueuedSubMesh::QueuedSubMesh
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1_1createQueuedSubMesh (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::InstancedGeometry::QueuedSubMesh::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     getsubmesh()
 * Type:       getter for public attribute
 * Definition: SubMesh* Ogre::InstancedGeometry::QueuedSubMesh::submesh
 * Signature:  ()Ogre_SubMesh
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1getsubmesh (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     setsubmesh()
 * Type:       setter for public attribute
 * Definition: SubMesh* Ogre::InstancedGeometry::QueuedSubMesh::submesh
 * Signature:  (Ogre_SubMesh)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1setsubmesh (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     getgeometryLodList()
 * Type:       getter for public attribute
 * Definition: SubMeshLodGeometryLinkList* Ogre::InstancedGeometry::QueuedSubMesh::geometryLodList
 * Signature:  ()Ogre_InstancedGeometry_SubMeshLodGeometryLinkList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1getgeometryLodList (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     setgeometryLodList()
 * Type:       setter for public attribute
 * Definition: SubMeshLodGeometryLinkList* Ogre::InstancedGeometry::QueuedSubMesh::geometryLodList
 * Signature:  (Ogre_InstancedGeometry_SubMeshLodGeometryLinkList)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1setgeometryLodList (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     getmaterialName()
 * Type:       getter for public attribute
 * Definition: String Ogre::InstancedGeometry::QueuedSubMesh::materialName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1getmaterialName (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     setmaterialName()
 * Type:       setter for public attribute
 * Definition: String Ogre::InstancedGeometry::QueuedSubMesh::materialName
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1setmaterialName (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     getposition()
 * Type:       getter for public attribute
 * Definition: Vector3 Ogre::InstancedGeometry::QueuedSubMesh::position
 * Signature:  ()Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1getposition (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     setposition()
 * Type:       setter for public attribute
 * Definition: Vector3 Ogre::InstancedGeometry::QueuedSubMesh::position
 * Signature:  (Ogre_Vector3)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1setposition (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     getorientation()
 * Type:       getter for public attribute
 * Definition: Quaternion Ogre::InstancedGeometry::QueuedSubMesh::orientation
 * Signature:  ()Ogre_Quaternion
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1getorientation (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     setorientation()
 * Type:       setter for public attribute
 * Definition: Quaternion Ogre::InstancedGeometry::QueuedSubMesh::orientation
 * Signature:  (Ogre_Quaternion)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1setorientation (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     getscale()
 * Type:       getter for public attribute
 * Definition: Vector3 Ogre::InstancedGeometry::QueuedSubMesh::scale
 * Signature:  ()Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1getscale (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     setscale()
 * Type:       setter for public attribute
 * Definition: Vector3 Ogre::InstancedGeometry::QueuedSubMesh::scale
 * Signature:  (Ogre_Vector3)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1setscale (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     getworldBounds()
 * Type:       getter for public attribute
 * Definition: AxisAlignedBox Ogre::InstancedGeometry::QueuedSubMesh::worldBounds
 * Signature:  ()Ogre_AxisAlignedBox
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1getworldBounds (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     setworldBounds()
 * Type:       setter for public attribute
 * Definition: AxisAlignedBox Ogre::InstancedGeometry::QueuedSubMesh::worldBounds
 * Signature:  (Ogre_AxisAlignedBox)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1setworldBounds (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     getID()
 * Type:       getter for public attribute
 * Definition: unsigned int Ogre::InstancedGeometry::QueuedSubMesh::ID
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1getID (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMesh
 * Method:     setID()
 * Type:       setter for public attribute
 * Definition: unsigned int Ogre::InstancedGeometry::QueuedSubMesh::ID
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__1setID (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_InstancedGeometry_00024QueuedSubMesh__*/
