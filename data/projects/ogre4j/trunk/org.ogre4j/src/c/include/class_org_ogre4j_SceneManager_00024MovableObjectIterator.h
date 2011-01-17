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


#ifndef __Included_org_ogre4j_SceneManager_00024MovableObjectIterator__
#define __Included_org_ogre4j_SceneManager_00024MovableObjectIterator__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.SceneManager.00024MovableObjectIterator
 * Method:     hasMoreElements()
 * Type:       non-virtual method
 * Definition: bool Ogre::MapIterator< T >::hasMoreElements
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_SceneManager_00024MovableObjectIterator__1hasMoreElements_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneManager.00024MovableObjectIterator
 * Method:     getNext()
 * Type:       non-virtual method
 * Definition: T::mapped_type Ogre::MapIterator< T >::getNext
 * Signature:  ()Ogre_MovableObject
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneManager_00024MovableObjectIterator__1getNext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneManager.00024MovableObjectIterator
 * Method:     peekNextValue()
 * Type:       non-virtual method
 * Definition: T::mapped_type Ogre::MapIterator< T >::peekNextValue
 * Signature:  ()Ogre_MovableObject
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneManager_00024MovableObjectIterator__1peekNextValue (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneManager.00024MovableObjectIterator
 * Method:     peekNextKey()
 * Type:       non-virtual method
 * Definition: T::key_type Ogre::MapIterator< T >::peekNextKey
 * Signature:  ()Ljava/lang/String;
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_SceneManager_00024MovableObjectIterator__1peekNextKey (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneManager.00024MovableObjectIterator
 * Method:     operator=()
 * Type:       non-virtual method
 * Definition: MapIterator<T>& Ogre::MapIterator< T >::operator=
 * Signature:  (Ogre_SceneManager_MovableObjectIterator)Ogre_SceneManager_MovableObjectIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneManager_00024MovableObjectIterator__1operatorAssignment_1_1_1Ogre_1SceneManager_1MovableObjectIteratorr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rhs
);

/*
 * Class:      org.ogre4j.SceneManager.00024MovableObjectIterator
 * Method:     peekNextValuePtr()
 * Type:       non-virtual method
 * Definition: T::mapped_type* Ogre::MapIterator< T >::peekNextValuePtr
 * Signature:  ()Ogre_MovableObject
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneManager_00024MovableObjectIterator__1peekNextValuePtr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneManager.00024MovableObjectIterator
 * Method:     moveNext()
 * Type:       non-virtual method
 * Definition: void Ogre::MapIterator< T >::moveNext
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneManager_00024MovableObjectIterator__1moveNext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneManager.00024MovableObjectIterator
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::SceneManager::MovableObjectIterator::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneManager_00024MovableObjectIterator__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_SceneManager_00024MovableObjectIterator__*/
