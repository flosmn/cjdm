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


#ifndef __Included_org_ogre4j_ExternalTextureSourceManager__
#define __Included_org_ogre4j_ExternalTextureSourceManager__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.ExternalTextureSourceManager
 * Method:     ExternalTextureSourceManager()
 * Type:       constructor
 * Definition: Ogre::ExternalTextureSourceManager::ExternalTextureSourceManager
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ExternalTextureSourceManager__1_1createExternalTextureSourceManager (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ExternalTextureSourceManager
 * Method:     setCurrentPlugIn()
 * Type:       non-virtual method
 * Definition: void Ogre::ExternalTextureSourceManager::setCurrentPlugIn
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ExternalTextureSourceManager__1setCurrentPlugIn_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring sTexturePlugInType
);

/*
 * Class:      org.ogre4j.ExternalTextureSourceManager
 * Method:     getCurrentPlugIn()
 * Type:       non-virtual method
 * Definition: ExternalTextureSource* Ogre::ExternalTextureSourceManager::getCurrentPlugIn
 * Signature:  ()Ogre_ExternalTextureSource
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ExternalTextureSourceManager__1getCurrentPlugIn_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ExternalTextureSourceManager
 * Method:     destroyAdvancedTexture()
 * Type:       non-virtual method
 * Definition: void Ogre::ExternalTextureSourceManager::destroyAdvancedTexture
 * Signature:  (std_stringstd_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ExternalTextureSourceManager__1destroyAdvancedTexture_1_1StringRStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring sTextureName, 
  jstring groupName
);

/*
 * Class:      org.ogre4j.ExternalTextureSourceManager
 * Method:     getExternalTextureSource()
 * Type:       non-virtual method
 * Definition: ExternalTextureSource* Ogre::ExternalTextureSourceManager::getExternalTextureSource
 * Signature:  (std_string)Ogre_ExternalTextureSource
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ExternalTextureSourceManager__1getExternalTextureSource_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring sTexturePlugInType
);

/*
 * Class:      org.ogre4j.ExternalTextureSourceManager
 * Method:     setExternalTextureSource()
 * Type:       non-virtual method
 * Definition: void Ogre::ExternalTextureSourceManager::setExternalTextureSource
 * Signature:  (std_stringOgre_ExternalTextureSource)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ExternalTextureSourceManager__1setExternalTextureSource_1_1StringRExternalTextureSourcep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring sTexturePlugInType, 
  jlong pTextureSystem
);

/*
 * Class:      org.ogre4j.ExternalTextureSourceManager
 * Method:     getSingleton()
 * Type:       static method
 * Definition: static ExternalTextureSourceManager& Ogre::ExternalTextureSourceManager::getSingleton
 * Signature:  ()Ogre_ExternalTextureSourceManager
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ExternalTextureSourceManager__1getSingleton (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ExternalTextureSourceManager
 * Method:     getSingletonPtr()
 * Type:       static method
 * Definition: static ExternalTextureSourceManager* Ogre::ExternalTextureSourceManager::getSingletonPtr
 * Signature:  ()Ogre_ExternalTextureSourceManager
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ExternalTextureSourceManager__1getSingletonPtr (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ExternalTextureSourceManager
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ExternalTextureSourceManager::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ExternalTextureSourceManager__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_ExternalTextureSourceManager__*/
