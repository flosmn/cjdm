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


#ifndef __Included_org_ogre4j_ParameterDef__
#define __Included_org_ogre4j_ParameterDef__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     ParameterDef()
 * Type:       constructor
 * Definition: Ogre::ParameterDef::ParameterDef
 * Signature:  (std_stringstd_stringOgre_ParameterType)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ParameterDef__1_1createParameterDef_1_1StringRStringRParameterTypev (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jstring newName, 
  jstring newDescription, 
  jint newType
);

/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ParameterDef::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ParameterDef__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     getname()
 * Type:       getter for public attribute
 * Definition: String Ogre::ParameterDef::name
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ParameterDef__1getname (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     setname()
 * Type:       setter for public attribute
 * Definition: String Ogre::ParameterDef::name
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ParameterDef__1setname (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     getdescription()
 * Type:       getter for public attribute
 * Definition: String Ogre::ParameterDef::description
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ParameterDef__1getdescription (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     setdescription()
 * Type:       setter for public attribute
 * Definition: String Ogre::ParameterDef::description
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ParameterDef__1setdescription (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     getparamType()
 * Type:       getter for public attribute
 * Definition: ParameterType Ogre::ParameterDef::paramType
 * Signature:  ()Ogre_ParameterType
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_ParameterDef__1getparamType (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     setparamType()
 * Type:       setter for public attribute
 * Definition: ParameterType Ogre::ParameterDef::paramType
 * Signature:  (Ogre_ParameterType)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ParameterDef__1setparamType (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_ParameterDef__*/
