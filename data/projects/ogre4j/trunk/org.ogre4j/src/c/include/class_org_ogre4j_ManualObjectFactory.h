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


#ifndef __Included_org_ogre4j_ManualObjectFactory__
#define __Included_org_ogre4j_ManualObjectFactory__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.ManualObjectFactory
 * Method:     ManualObjectFactory()
 * Type:       constructor
 * Definition: Ogre::ManualObjectFactory::ManualObjectFactory
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ManualObjectFactory__1_1createManualObjectFactory (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ManualObjectFactory
 * Method:     getType()
 * Type:       virtual method
 * Definition: const String& Ogre::ManualObjectFactory::getType
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ManualObjectFactory__1getType_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ManualObjectFactory
 * Method:     destroyInstance()
 * Type:       virtual method
 * Definition: void Ogre::ManualObjectFactory::destroyInstance
 * Signature:  (Ogre_MovableObject)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ManualObjectFactory__1destroyInstance_1_1MovableObjectp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong obj
);

/*
 * Class:      org.ogre4j.ManualObjectFactory
 * Method:     createInstance()
 * Type:       virtual method
 * Definition: virtual MovableObject* Ogre::MovableObjectFactory::createInstance
 * Signature:  (std_stringOgre_SceneManagerOgre_NameValuePairList)Ogre_MovableObject
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ManualObjectFactory__1createInstance_1_1StringRSceneManagerpNameValuePairListP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jlong manager, 
  jlong params
);

/*
 * Class:      org.ogre4j.ManualObjectFactory
 * Method:     requestTypeFlags()
 * Type:       virtual method
 * Definition: virtual bool Ogre::MovableObjectFactory::requestTypeFlags
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_ManualObjectFactory__1requestTypeFlags_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ManualObjectFactory
 * Method:     _notifyTypeFlags()
 * Type:       non-virtual method
 * Definition: void Ogre::MovableObjectFactory::_notifyTypeFlags
 * Signature:  (J)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ManualObjectFactory__1_1notifyTypeFlags_1_1Lv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong flag
);

/*
 * Class:      org.ogre4j.ManualObjectFactory
 * Method:     getTypeFlags()
 * Type:       non-virtual method
 * Definition: unsigned long Ogre::MovableObjectFactory::getTypeFlags
 * Signature:  ()J
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ManualObjectFactory__1getTypeFlags_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ManualObjectFactory
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ManualObjectFactory::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ManualObjectFactory__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ManualObjectFactory
 * Method:     getFACTORY_TYPE_NAME()
 * Type:       getter for public attribute
 * Definition: String Ogre::ManualObjectFactory::FACTORY_TYPE_NAME
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ManualObjectFactory__1getFACTORY_1TYPE_1NAME (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ManualObjectFactory
 * Method:     setFACTORY_TYPE_NAME()
 * Type:       setter for public attribute
 * Definition: String Ogre::ManualObjectFactory::FACTORY_TYPE_NAME
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ManualObjectFactory__1setFACTORY_1TYPE_1NAME (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jstring _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_ManualObjectFactory__*/
