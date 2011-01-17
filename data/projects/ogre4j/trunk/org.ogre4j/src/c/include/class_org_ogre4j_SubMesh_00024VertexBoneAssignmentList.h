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


#ifndef __Included_org_ogre4j_SubMesh_00024VertexBoneAssignmentList__
#define __Included_org_ogre4j_SubMesh_00024VertexBoneAssignmentList__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.SubMesh.00024VertexBoneAssignmentList
 * Method:     VertexBoneAssignmentList()
 * Type:       constructor
 * Definition: std::multimap::multimap
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SubMesh_00024VertexBoneAssignmentList__1_1createVertexBoneAssignmentList (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.SubMesh.00024VertexBoneAssignmentList
 * Method:     clear()
 * Type:       non-virtual method
 * Definition: std::multimap::clear
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SubMesh_00024VertexBoneAssignmentList__1clear (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SubMesh.00024VertexBoneAssignmentList
 * Method:     count()
 * Type:       non-virtual method
 * Definition: std::multimap::count
 * Signature:  (I)I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_SubMesh_00024VertexBoneAssignmentList__1count_1_1iR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint key
);

/*
 * Class:      org.ogre4j.SubMesh.00024VertexBoneAssignmentList
 * Method:     empty()
 * Type:       non-virtual method
 * Definition: std::multimap::empty
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_SubMesh_00024VertexBoneAssignmentList__1empty_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SubMesh.00024VertexBoneAssignmentList
 * Method:     erase()
 * Type:       non-virtual method
 * Definition: std::multimap::erase
 * Signature:  (I)I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_SubMesh_00024VertexBoneAssignmentList__1erase_1_1iR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint key
);

/*
 * Class:      org.ogre4j.SubMesh.00024VertexBoneAssignmentList
 * Method:     max_size()
 * Type:       non-virtual method
 * Definition: std::multimap::max_size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_SubMesh_00024VertexBoneAssignmentList__1max_1size_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SubMesh.00024VertexBoneAssignmentList
 * Method:     size()
 * Type:       non-virtual method
 * Definition: std::multimap::size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_SubMesh_00024VertexBoneAssignmentList__1size_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SubMesh.00024VertexBoneAssignmentList
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::SubMesh::VertexBoneAssignmentList::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SubMesh_00024VertexBoneAssignmentList__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_SubMesh_00024VertexBoneAssignmentList__*/
