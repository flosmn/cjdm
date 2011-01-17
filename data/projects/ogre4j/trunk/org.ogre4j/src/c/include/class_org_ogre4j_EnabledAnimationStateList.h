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


#ifndef __Included_org_ogre4j_EnabledAnimationStateList__
#define __Included_org_ogre4j_EnabledAnimationStateList__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     EnabledAnimationStateList()
 * Type:       constructor
 * Definition: std::list::list
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_EnabledAnimationStateList__1_1createEnabledAnimationStateList (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     assign()
 * Type:       non-virtual method
 * Definition: std::list::assign
 * Signature:  (IOgre_AnimationState)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EnabledAnimationStateList__1assign_1_1ivOgre_1AnimationStateP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint num, 
  jlong val
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     back()
 * Type:       non-virtual method
 * Definition: std::list::back
 * Signature:  ()Ogre_AnimationState
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_EnabledAnimationStateList__1back (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     clear()
 * Type:       non-virtual method
 * Definition: std::list::clear
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EnabledAnimationStateList__1clear (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     empty()
 * Type:       non-virtual method
 * Definition: std::list::empty
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_EnabledAnimationStateList__1empty_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     front()
 * Type:       non-virtual method
 * Definition: std::list::front
 * Signature:  ()Ogre_AnimationState
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_EnabledAnimationStateList__1front (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     max_size()
 * Type:       non-virtual method
 * Definition: std::list::max_size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_EnabledAnimationStateList__1max_1size_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     pop_back()
 * Type:       non-virtual method
 * Definition: std::list::pop_back
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EnabledAnimationStateList__1pop_1back (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     pop_front()
 * Type:       non-virtual method
 * Definition: std::list::pop_front
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EnabledAnimationStateList__1pop_1front (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     push_back()
 * Type:       non-virtual method
 * Definition: std::list::push_back
 * Signature:  (Ogre_AnimationState)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EnabledAnimationStateList__1push_1back_1_1Ogre_1AnimationStateP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong val
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     push_front()
 * Type:       non-virtual method
 * Definition: std::list::push_front
 * Signature:  (Ogre_AnimationState)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EnabledAnimationStateList__1push_1front_1_1Ogre_1AnimationStateP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong val
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     remove()
 * Type:       non-virtual method
 * Definition: std::list::remove
 * Signature:  (Ogre_AnimationState)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EnabledAnimationStateList__1remove_1_1Ogre_1AnimationStateP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong val
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     reverse()
 * Type:       non-virtual method
 * Definition: std::list::reverse
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EnabledAnimationStateList__1reverse (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     size()
 * Type:       non-virtual method
 * Definition: std::list::size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_EnabledAnimationStateList__1size_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     unique()
 * Type:       non-virtual method
 * Definition: std::list::unique
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EnabledAnimationStateList__1unique (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.EnabledAnimationStateList
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::EnabledAnimationStateList::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EnabledAnimationStateList__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_EnabledAnimationStateList__*/
