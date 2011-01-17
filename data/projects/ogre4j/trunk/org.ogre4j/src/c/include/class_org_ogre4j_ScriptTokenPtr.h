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


#ifndef __Included_org_ogre4j_ScriptTokenPtr__
#define __Included_org_ogre4j_ScriptTokenPtr__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     ScriptTokenPtr()
 * Type:       constructor
 * Definition: Ogre::SharedPtr< T >::SharedPtr
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ScriptTokenPtr__1_1createScriptTokenPtr (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     operator=()
 * Type:       non-virtual method
 * Definition: SharedPtr& Ogre::SharedPtr< T >::operator=
 * Signature:  (Ogre_SharedPtr)Ogre_SharedPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ScriptTokenPtr__1operatorAssignment_1_1_1Ogre_1SharedPtrR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong r
);

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     operator *()
 * Type:       non-virtual method
 * Definition: T& Ogre::SharedPtr< T >::operator *
 * Signature:  ()Ogre_ScriptToken
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ScriptTokenPtr__1operatorMultiplication_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     operator->()
 * Type:       non-virtual method
 * Definition: T* Ogre::SharedPtr< T >::operator->
 * Signature:  ()Ogre_ScriptToken
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ScriptTokenPtr__1operatorMemberAccessFromAPointer_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     get()
 * Type:       non-virtual method
 * Definition: T* Ogre::SharedPtr< T >::get
 * Signature:  ()Ogre_ScriptToken
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ScriptTokenPtr__1get_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     bind()
 * Type:       non-virtual method
 * Definition: void Ogre::SharedPtr< T >::bind
 * Signature:  (Ogre_ScriptTokenOgre_SharedPtrFreeMethod)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ScriptTokenPtr__1bind_1_1Ogre_1ScriptTokenp_1Ogre_1SharedPtrFreeMethodv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rep, 
  jint freeMethod
);

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     unique()
 * Type:       non-virtual method
 * Definition: bool Ogre::SharedPtr< T >::unique
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_ScriptTokenPtr__1unique_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     useCount()
 * Type:       non-virtual method
 * Definition: unsigned int Ogre::SharedPtr< T >::useCount
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ScriptTokenPtr__1useCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     useCountPointer()
 * Type:       non-virtual method
 * Definition: unsigned int* Ogre::SharedPtr< T >::useCountPointer
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ScriptTokenPtr__1useCountPointer_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     getPointer()
 * Type:       non-virtual method
 * Definition: T* Ogre::SharedPtr< T >::getPointer
 * Signature:  ()Ogre_ScriptToken
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ScriptTokenPtr__1getPointer_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     freeMethod()
 * Type:       non-virtual method
 * Definition: SharedPtrFreeMethod Ogre::SharedPtr< T >::freeMethod
 * Signature:  ()Ogre_SharedPtrFreeMethod
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_ScriptTokenPtr__1freeMethod_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     isNull()
 * Type:       non-virtual method
 * Definition: bool Ogre::SharedPtr< T >::isNull
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_ScriptTokenPtr__1isNull_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     setNull()
 * Type:       non-virtual method
 * Definition: void Ogre::SharedPtr< T >::setNull
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ScriptTokenPtr__1setNull (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ScriptTokenPtr
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ScriptTokenPtr::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ScriptTokenPtr__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_ScriptTokenPtr__*/
