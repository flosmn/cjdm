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


#ifndef __Included_org_ogre4j_GpuConstantDefinition__
#define __Included_org_ogre4j_GpuConstantDefinition__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     isFloat()
 * Type:       non-virtual method
 * Definition: bool Ogre::GpuConstantDefinition::isFloat
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_GpuConstantDefinition__1isFloat_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     isSampler()
 * Type:       non-virtual method
 * Definition: bool Ogre::GpuConstantDefinition::isSampler
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_GpuConstantDefinition__1isSampler_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     GpuConstantDefinition()
 * Type:       constructor
 * Definition: Ogre::GpuConstantDefinition::GpuConstantDefinition
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_GpuConstantDefinition__1_1createGpuConstantDefinition (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::GpuConstantDefinition::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_GpuConstantDefinition__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     getconstType()
 * Type:       getter for public attribute
 * Definition: GpuConstantType Ogre::GpuConstantDefinition::constType
 * Signature:  ()Ogre_GpuConstantType
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_GpuConstantDefinition__1getconstType (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     setconstType()
 * Type:       setter for public attribute
 * Definition: GpuConstantType Ogre::GpuConstantDefinition::constType
 * Signature:  (Ogre_GpuConstantType)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_GpuConstantDefinition__1setconstType (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     getphysicalIndex()
 * Type:       getter for public attribute
 * Definition: size_t Ogre::GpuConstantDefinition::physicalIndex
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_GpuConstantDefinition__1getphysicalIndex (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     setphysicalIndex()
 * Type:       setter for public attribute
 * Definition: size_t Ogre::GpuConstantDefinition::physicalIndex
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_GpuConstantDefinition__1setphysicalIndex (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     getlogicalIndex()
 * Type:       getter for public attribute
 * Definition: size_t Ogre::GpuConstantDefinition::logicalIndex
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_GpuConstantDefinition__1getlogicalIndex (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     setlogicalIndex()
 * Type:       setter for public attribute
 * Definition: size_t Ogre::GpuConstantDefinition::logicalIndex
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_GpuConstantDefinition__1setlogicalIndex (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     getelementSize()
 * Type:       getter for public attribute
 * Definition: size_t Ogre::GpuConstantDefinition::elementSize
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_GpuConstantDefinition__1getelementSize (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     setelementSize()
 * Type:       setter for public attribute
 * Definition: size_t Ogre::GpuConstantDefinition::elementSize
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_GpuConstantDefinition__1setelementSize (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     getarraySize()
 * Type:       getter for public attribute
 * Definition: size_t Ogre::GpuConstantDefinition::arraySize
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_GpuConstantDefinition__1getarraySize (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.GpuConstantDefinition
 * Method:     setarraySize()
 * Type:       setter for public attribute
 * Definition: size_t Ogre::GpuConstantDefinition::arraySize
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_GpuConstantDefinition__1setarraySize (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_GpuConstantDefinition__*/
