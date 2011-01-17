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


#ifndef __Included_org_ogre4j_Technique_00024GPUVendorRule__
#define __Included_org_ogre4j_Technique_00024GPUVendorRule__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.Technique.00024GPUVendorRule
 * Method:     GPUVendorRule()
 * Type:       constructor
 * Definition: Ogre::Technique::GPUVendorRule::GPUVendorRule
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Technique_00024GPUVendorRule__1_1createGPUVendorRule (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.Technique.00024GPUVendorRule
 * Method:     GPUVendorRule()
 * Type:       constructor
 * Definition: Ogre::Technique::GPUVendorRule::GPUVendorRule
 * Signature:  (Ogre_GPUVendorOgre_Technique_IncludeOrExclude)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Technique_00024GPUVendorRule__1_1createGPUVendorRule_1_1GPUVendorvIncludeOrExcludev (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jint v, 
  jint ie
);

/*
 * Class:      org.ogre4j.Technique.00024GPUVendorRule
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::Technique::GPUVendorRule::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Technique_00024GPUVendorRule__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Technique.00024GPUVendorRule
 * Method:     getvendor()
 * Type:       getter for public attribute
 * Definition: GPUVendor Ogre::Technique::GPUVendorRule::vendor
 * Signature:  ()Ogre_GPUVendor
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Technique_00024GPUVendorRule__1getvendor (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Technique.00024GPUVendorRule
 * Method:     setvendor()
 * Type:       setter for public attribute
 * Definition: GPUVendor Ogre::Technique::GPUVendorRule::vendor
 * Signature:  (Ogre_GPUVendor)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Technique_00024GPUVendorRule__1setvendor (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.Technique.00024GPUVendorRule
 * Method:     getincludeOrExclude()
 * Type:       getter for public attribute
 * Definition: IncludeOrExclude Ogre::Technique::GPUVendorRule::includeOrExclude
 * Signature:  ()Ogre_Technique_IncludeOrExclude
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Technique_00024GPUVendorRule__1getincludeOrExclude (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Technique.00024GPUVendorRule
 * Method:     setincludeOrExclude()
 * Type:       setter for public attribute
 * Definition: IncludeOrExclude Ogre::Technique::GPUVendorRule::includeOrExclude
 * Signature:  (Ogre_Technique_IncludeOrExclude)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Technique_00024GPUVendorRule__1setincludeOrExclude (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_Technique_00024GPUVendorRule__*/
