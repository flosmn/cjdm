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


#ifndef __Included_org_ogre4j_SceneQuery_00024WorldFragment__
#define __Included_org_ogre4j_SceneQuery_00024WorldFragment__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.SceneQuery.00024WorldFragment
 * Method:     WorldFragment()
 * Type:       constructor
 * Definition: Ogre::SceneQuery::WorldFragment::WorldFragment
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneQuery_00024WorldFragment__1_1createWorldFragment (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.SceneQuery.00024WorldFragment
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::SceneQuery::WorldFragment::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneQuery_00024WorldFragment__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneQuery.00024WorldFragment
 * Method:     getfragmentType()
 * Type:       getter for public attribute
 * Definition: WorldFragmentType Ogre::SceneQuery::WorldFragment::fragmentType
 * Signature:  ()Ogre_SceneQuery_WorldFragmentType
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_SceneQuery_00024WorldFragment__1getfragmentType (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneQuery.00024WorldFragment
 * Method:     setfragmentType()
 * Type:       setter for public attribute
 * Definition: WorldFragmentType Ogre::SceneQuery::WorldFragment::fragmentType
 * Signature:  (Ogre_SceneQuery_WorldFragmentType)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneQuery_00024WorldFragment__1setfragmentType (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.SceneQuery.00024WorldFragment
 * Method:     getsingleIntersection()
 * Type:       getter for public attribute
 * Definition: Vector3 Ogre::SceneQuery::WorldFragment::singleIntersection
 * Signature:  ()Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneQuery_00024WorldFragment__1getsingleIntersection (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneQuery.00024WorldFragment
 * Method:     setsingleIntersection()
 * Type:       setter for public attribute
 * Definition: Vector3 Ogre::SceneQuery::WorldFragment::singleIntersection
 * Signature:  (Ogre_Vector3)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneQuery_00024WorldFragment__1setsingleIntersection (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.SceneQuery.00024WorldFragment
 * Method:     getplanes()
 * Type:       getter for public attribute
 * Definition: std::list<Plane>* Ogre::SceneQuery::WorldFragment::planes
 * Signature:  ()std_list__Plane__
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneQuery_00024WorldFragment__1getplanes (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneQuery.00024WorldFragment
 * Method:     setplanes()
 * Type:       setter for public attribute
 * Definition: std::list<Plane>* Ogre::SceneQuery::WorldFragment::planes
 * Signature:  (std_list__Plane__)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneQuery_00024WorldFragment__1setplanes (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.SceneQuery.00024WorldFragment
 * Method:     getgeometry()
 * Type:       getter for public attribute
 * Definition: void* Ogre::SceneQuery::WorldFragment::geometry
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneQuery_00024WorldFragment__1getgeometry (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneQuery.00024WorldFragment
 * Method:     setgeometry()
 * Type:       setter for public attribute
 * Definition: void* Ogre::SceneQuery::WorldFragment::geometry
 * Signature:  (V)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneQuery_00024WorldFragment__1setgeometry (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.SceneQuery.00024WorldFragment
 * Method:     getrenderOp()
 * Type:       getter for public attribute
 * Definition: RenderOperation* Ogre::SceneQuery::WorldFragment::renderOp
 * Signature:  ()Ogre_RenderOperation
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneQuery_00024WorldFragment__1getrenderOp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneQuery.00024WorldFragment
 * Method:     setrenderOp()
 * Type:       setter for public attribute
 * Definition: RenderOperation* Ogre::SceneQuery::WorldFragment::renderOp
 * Signature:  (Ogre_RenderOperation)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneQuery_00024WorldFragment__1setrenderOp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_SceneQuery_00024WorldFragment__*/
