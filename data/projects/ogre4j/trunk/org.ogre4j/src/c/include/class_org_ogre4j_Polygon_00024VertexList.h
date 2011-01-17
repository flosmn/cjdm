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


#ifndef __Included_org_ogre4j_Polygon_00024VertexList__
#define __Included_org_ogre4j_Polygon_00024VertexList__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     VertexList()
 * Type:       constructor
 * Definition: std::vector::vector
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Polygon_00024VertexList__1_1createVertexList (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     assign()
 * Type:       non-virtual method
 * Definition: void std::vector::assign
 * Signature:  (IOgre_Vector3)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Polygon_00024VertexList__1assign_1_1ivOgre_1Vector3R (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint num, 
  jlong val
);

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     at()
 * Type:       non-virtual method
 * Definition: T& std::vector::at
 * Signature:  (I)Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Polygon_00024VertexList__1at_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint loc
);

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     back()
 * Type:       non-virtual method
 * Definition: T std::vector::back
 * Signature:  ()Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Polygon_00024VertexList__1back (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     capacity()
 * Type:       non-virtual method
 * Definition: size_t std::vector::capacity
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Polygon_00024VertexList__1capacity (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     clear()
 * Type:       non-virtual method
 * Definition: void std::vector::clear
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Polygon_00024VertexList__1clear (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     empty()
 * Type:       non-virtual method
 * Definition: bool std::vector::empty
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Polygon_00024VertexList__1empty (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     front()
 * Type:       non-virtual method
 * Definition: T std::vector::front
 * Signature:  ()Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Polygon_00024VertexList__1front (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     max_size()
 * Type:       non-virtual method
 * Definition: size_t std::vector::max_size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Polygon_00024VertexList__1max_1size (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     pop_back()
 * Type:       non-virtual method
 * Definition: void std::vector::pop_back
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Polygon_00024VertexList__1pop_1back (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     push_back()
 * Type:       non-virtual method
 * Definition: void std::vector::push_back
 * Signature:  (Ogre_Vector3)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Polygon_00024VertexList__1push_1back_1_1Ogre_1Vector3R (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong val
);

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     reserve()
 * Type:       non-virtual method
 * Definition: void std::vector::reserve
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Polygon_00024VertexList__1reserve_1_1iV (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint size
);

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     size()
 * Type:       non-virtual method
 * Definition: size_t std::vector::size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Polygon_00024VertexList__1size (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Polygon.00024VertexList
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::Polygon::VertexList::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Polygon_00024VertexList__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_Polygon_00024VertexList__*/
