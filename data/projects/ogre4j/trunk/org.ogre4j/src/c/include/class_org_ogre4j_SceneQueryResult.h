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


#ifndef __Included_org_ogre4j_SceneQueryResult__
#define __Included_org_ogre4j_SceneQueryResult__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.SceneQueryResult
 * Method:     SceneQueryResult()
 * Type:       constructor
 * Definition: Ogre::SceneQueryResult::SceneQueryResult
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneQueryResult__1_1createSceneQueryResult (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.SceneQueryResult
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::SceneQueryResult::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneQueryResult__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneQueryResult
 * Method:     getmovables()
 * Type:       getter for public attribute
 * Definition: SceneQueryResultMovableList Ogre::SceneQueryResult::movables
 * Signature:  ()Ogre_SceneQueryResultMovableList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneQueryResult__1getmovables (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneQueryResult
 * Method:     setmovables()
 * Type:       setter for public attribute
 * Definition: SceneQueryResultMovableList Ogre::SceneQueryResult::movables
 * Signature:  (Ogre_SceneQueryResultMovableList)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneQueryResult__1setmovables (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.SceneQueryResult
 * Method:     getworldFragments()
 * Type:       getter for public attribute
 * Definition: SceneQueryResultWorldFragmentList Ogre::SceneQueryResult::worldFragments
 * Signature:  ()Ogre_SceneQueryResultWorldFragmentList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneQueryResult__1getworldFragments (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneQueryResult
 * Method:     setworldFragments()
 * Type:       setter for public attribute
 * Definition: SceneQueryResultWorldFragmentList Ogre::SceneQueryResult::worldFragments
 * Signature:  (Ogre_SceneQueryResultWorldFragmentList)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneQueryResult__1setworldFragments (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_SceneQueryResult__*/
