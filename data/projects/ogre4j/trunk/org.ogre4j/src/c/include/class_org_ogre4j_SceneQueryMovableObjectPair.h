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


#ifndef __Included_org_ogre4j_SceneQueryMovableObjectPair__
#define __Included_org_ogre4j_SceneQueryMovableObjectPair__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.SceneQueryMovableObjectPair
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::SceneQueryMovableObjectPair::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneQueryMovableObjectPair__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneQueryMovableObjectPair
 * Method:     getfirst()
 * Type:       getter for public attribute
 * Definition: F std::pair::first
 * Signature:  ()Ogre_MovableObject
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneQueryMovableObjectPair__1getfirst (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneQueryMovableObjectPair
 * Method:     setfirst()
 * Type:       setter for public attribute
 * Definition: F std::pair::first
 * Signature:  (Ogre_MovableObject)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneQueryMovableObjectPair__1setfirst (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.SceneQueryMovableObjectPair
 * Method:     getsecond()
 * Type:       getter for public attribute
 * Definition: S std::pair::second
 * Signature:  ()Ogre_MovableObject
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneQueryMovableObjectPair__1getsecond (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneQueryMovableObjectPair
 * Method:     setsecond()
 * Type:       setter for public attribute
 * Definition: S std::pair::second
 * Signature:  (Ogre_MovableObject)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneQueryMovableObjectPair__1setsecond (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_SceneQueryMovableObjectPair__*/
