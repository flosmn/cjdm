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


#ifndef __Included_org_ogre4j_RaySceneQuery__
#define __Included_org_ogre4j_RaySceneQuery__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     setRay()
 * Type:       virtual method
 * Definition: virtual void Ogre::RaySceneQuery::setRay
 * Signature:  (Ogre_Ray)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RaySceneQuery__1setRay_1_1RayR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong ray
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     getRay()
 * Type:       virtual method
 * Definition: virtual const Ray& Ogre::RaySceneQuery::getRay
 * Signature:  ()Ogre_Ray
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RaySceneQuery__1getRay_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     setSortByDistance()
 * Type:       virtual method
 * Definition: virtual void Ogre::RaySceneQuery::setSortByDistance
 * Signature:  (Zunsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RaySceneQuery__1setSortByDistance_1_1bvushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean sort, 
  jint maxresults
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     getSortByDistance()
 * Type:       virtual method
 * Definition: virtual bool Ogre::RaySceneQuery::getSortByDistance
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RaySceneQuery__1getSortByDistance_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     getMaxResults()
 * Type:       virtual method
 * Definition: virtual ushort Ogre::RaySceneQuery::getMaxResults
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RaySceneQuery__1getMaxResults_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     execute()
 * Type:       virtual method
 * Definition: virtual RaySceneQueryResult& Ogre::RaySceneQuery::execute
 * Signature:  ()Ogre_RaySceneQueryResult
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RaySceneQuery__1execute (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     execute()
 * Type:       pure virtual method
 * Definition: virtual void Ogre::RaySceneQuery::execute
 * Signature:  (Ogre_RaySceneQueryListener)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RaySceneQuery__1execute_1_1RaySceneQueryListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong listener
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     getLastResults()
 * Type:       virtual method
 * Definition: virtual RaySceneQueryResult& Ogre::RaySceneQuery::getLastResults
 * Signature:  ()Ogre_RaySceneQueryResult
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RaySceneQuery__1getLastResults (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     clearResults()
 * Type:       virtual method
 * Definition: virtual void Ogre::RaySceneQuery::clearResults
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RaySceneQuery__1clearResults (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     queryResult()
 * Type:       virtual method
 * Definition: bool Ogre::RaySceneQuery::queryResult
 * Signature:  (Ogre_MovableObjectfloat)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RaySceneQuery__1queryResult_1_1MovableObjectpRealv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong obj, 
  jfloat distance
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     queryResult()
 * Type:       virtual method
 * Definition: bool Ogre::RaySceneQuery::queryResult
 * Signature:  (Ogre_SceneQuery_WorldFragmentfloat)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RaySceneQuery__1queryResult_1_1SceneQuery_1WorldFragmentpRealv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong fragment, 
  jfloat distance
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     setQueryMask()
 * Type:       virtual method
 * Definition: virtual void Ogre::SceneQuery::setQueryMask
 * Signature:  (unsigned_int)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RaySceneQuery__1setQueryMask_1_1uint32v (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong mask
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     getQueryMask()
 * Type:       virtual method
 * Definition: virtual uint32 Ogre::SceneQuery::getQueryMask
 * Signature:  ()unsigned_int
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RaySceneQuery__1getQueryMask_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     setQueryTypeMask()
 * Type:       virtual method
 * Definition: virtual void Ogre::SceneQuery::setQueryTypeMask
 * Signature:  (unsigned_int)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RaySceneQuery__1setQueryTypeMask_1_1uint32v (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong mask
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     getQueryTypeMask()
 * Type:       virtual method
 * Definition: virtual uint32 Ogre::SceneQuery::getQueryTypeMask
 * Signature:  ()unsigned_int
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RaySceneQuery__1getQueryTypeMask_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     setWorldFragmentType()
 * Type:       virtual method
 * Definition: virtual void Ogre::SceneQuery::setWorldFragmentType
 * Signature:  (Ogre_SceneQuery_WorldFragmentType)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RaySceneQuery__1setWorldFragmentType_1_1WorldFragmentTypev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint wft
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     getWorldFragmentType()
 * Type:       virtual method
 * Definition: virtual WorldFragmentType Ogre::SceneQuery::getWorldFragmentType
 * Signature:  ()Ogre_SceneQuery_WorldFragmentType
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RaySceneQuery__1getWorldFragmentType_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     getSupportedWorldFragmentTypes()
 * Type:       virtual method
 * Definition: virtual const std::set<WorldFragmentType>* Ogre::SceneQuery::getSupportedWorldFragmentTypes
 * Signature:  ()std_set__WorldFragmentType__
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RaySceneQuery__1getSupportedWorldFragmentTypes_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RaySceneQuery
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::RaySceneQuery::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RaySceneQuery__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_RaySceneQuery__*/
