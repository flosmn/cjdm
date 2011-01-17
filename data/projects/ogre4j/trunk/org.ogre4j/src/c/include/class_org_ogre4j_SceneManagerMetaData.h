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


#ifndef __Included_org_ogre4j_SceneManagerMetaData__
#define __Included_org_ogre4j_SceneManagerMetaData__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.SceneManagerMetaData
 * Method:     SceneManagerMetaData()
 * Type:       constructor
 * Definition: Ogre::SceneManagerMetaData::SceneManagerMetaData
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneManagerMetaData__1_1createSceneManagerMetaData (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.SceneManagerMetaData
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::SceneManagerMetaData::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneManagerMetaData__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneManagerMetaData
 * Method:     gettypeName()
 * Type:       getter for public attribute
 * Definition: String Ogre::SceneManagerMetaData::typeName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_SceneManagerMetaData__1gettypeName (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneManagerMetaData
 * Method:     settypeName()
 * Type:       setter for public attribute
 * Definition: String Ogre::SceneManagerMetaData::typeName
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneManagerMetaData__1settypeName (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.SceneManagerMetaData
 * Method:     getdescription()
 * Type:       getter for public attribute
 * Definition: String Ogre::SceneManagerMetaData::description
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_SceneManagerMetaData__1getdescription (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneManagerMetaData
 * Method:     setdescription()
 * Type:       setter for public attribute
 * Definition: String Ogre::SceneManagerMetaData::description
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneManagerMetaData__1setdescription (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.SceneManagerMetaData
 * Method:     getsceneTypeMask()
 * Type:       getter for public attribute
 * Definition: SceneTypeMask Ogre::SceneManagerMetaData::sceneTypeMask
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_SceneManagerMetaData__1getsceneTypeMask (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneManagerMetaData
 * Method:     setsceneTypeMask()
 * Type:       setter for public attribute
 * Definition: SceneTypeMask Ogre::SceneManagerMetaData::sceneTypeMask
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneManagerMetaData__1setsceneTypeMask (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.SceneManagerMetaData
 * Method:     getworldGeometrySupported()
 * Type:       getter for public attribute
 * Definition: bool Ogre::SceneManagerMetaData::worldGeometrySupported
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_SceneManagerMetaData__1getworldGeometrySupported (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.SceneManagerMetaData
 * Method:     setworldGeometrySupported()
 * Type:       setter for public attribute
 * Definition: bool Ogre::SceneManagerMetaData::worldGeometrySupported
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneManagerMetaData__1setworldGeometrySupported (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_SceneManagerMetaData__*/
