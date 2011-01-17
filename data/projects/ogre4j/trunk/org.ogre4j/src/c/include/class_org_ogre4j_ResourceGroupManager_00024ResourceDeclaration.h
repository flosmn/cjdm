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


#ifndef __Included_org_ogre4j_ResourceGroupManager_00024ResourceDeclaration__
#define __Included_org_ogre4j_ResourceGroupManager_00024ResourceDeclaration__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.ResourceGroupManager.00024ResourceDeclaration
 * Method:     ResourceDeclaration()
 * Type:       constructor
 * Definition: Ogre::ResourceGroupManager::ResourceDeclaration::ResourceDeclaration
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager_00024ResourceDeclaration__1_1createResourceDeclaration (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager.00024ResourceDeclaration
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ResourceGroupManager::ResourceDeclaration::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager_00024ResourceDeclaration__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager.00024ResourceDeclaration
 * Method:     getresourceName()
 * Type:       getter for public attribute
 * Definition: String Ogre::ResourceGroupManager::ResourceDeclaration::resourceName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ResourceGroupManager_00024ResourceDeclaration__1getresourceName (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager.00024ResourceDeclaration
 * Method:     setresourceName()
 * Type:       setter for public attribute
 * Definition: String Ogre::ResourceGroupManager::ResourceDeclaration::resourceName
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager_00024ResourceDeclaration__1setresourceName (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.ResourceGroupManager.00024ResourceDeclaration
 * Method:     getresourceType()
 * Type:       getter for public attribute
 * Definition: String Ogre::ResourceGroupManager::ResourceDeclaration::resourceType
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ResourceGroupManager_00024ResourceDeclaration__1getresourceType (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager.00024ResourceDeclaration
 * Method:     setresourceType()
 * Type:       setter for public attribute
 * Definition: String Ogre::ResourceGroupManager::ResourceDeclaration::resourceType
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager_00024ResourceDeclaration__1setresourceType (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.ResourceGroupManager.00024ResourceDeclaration
 * Method:     getloader()
 * Type:       getter for public attribute
 * Definition: ManualResourceLoader* Ogre::ResourceGroupManager::ResourceDeclaration::loader
 * Signature:  ()Ogre_ManualResourceLoader
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager_00024ResourceDeclaration__1getloader (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager.00024ResourceDeclaration
 * Method:     setloader()
 * Type:       setter for public attribute
 * Definition: ManualResourceLoader* Ogre::ResourceGroupManager::ResourceDeclaration::loader
 * Signature:  (Ogre_ManualResourceLoader)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager_00024ResourceDeclaration__1setloader (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.ResourceGroupManager.00024ResourceDeclaration
 * Method:     getparameters()
 * Type:       getter for public attribute
 * Definition: NameValuePairList Ogre::ResourceGroupManager::ResourceDeclaration::parameters
 * Signature:  ()Ogre_NameValuePairList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager_00024ResourceDeclaration__1getparameters (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager.00024ResourceDeclaration
 * Method:     setparameters()
 * Type:       setter for public attribute
 * Definition: NameValuePairList Ogre::ResourceGroupManager::ResourceDeclaration::parameters
 * Signature:  (Ogre_NameValuePairList)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager_00024ResourceDeclaration__1setparameters (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_ResourceGroupManager_00024ResourceDeclaration__*/
