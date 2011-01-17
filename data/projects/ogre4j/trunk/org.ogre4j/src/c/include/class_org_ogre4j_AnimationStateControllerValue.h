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


#ifndef __Included_org_ogre4j_AnimationStateControllerValue__
#define __Included_org_ogre4j_AnimationStateControllerValue__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.AnimationStateControllerValue
 * Method:     AnimationStateControllerValue()
 * Type:       constructor
 * Definition: Ogre::AnimationStateControllerValue::AnimationStateControllerValue
 * Signature:  (Ogre_AnimationState)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_AnimationStateControllerValue__1_1createAnimationStateControllerValue_1_1AnimationStatep (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong targetAnimationState
);

/*
 * Class:      org.ogre4j.AnimationStateControllerValue
 * Method:     getValue()
 * Type:       virtual method
 * Definition: Real Ogre::AnimationStateControllerValue::getValue
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_AnimationStateControllerValue__1getValue_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.AnimationStateControllerValue
 * Method:     setValue()
 * Type:       virtual method
 * Definition: void Ogre::AnimationStateControllerValue::setValue
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_AnimationStateControllerValue__1setValue_1_1Realv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat value
);

/*
 * Class:      org.ogre4j.AnimationStateControllerValue
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::AnimationStateControllerValue::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_AnimationStateControllerValue__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_AnimationStateControllerValue__*/
