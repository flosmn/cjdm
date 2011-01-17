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


#ifndef __Included_org_ogre4j_StringInterface__
#define __Included_org_ogre4j_StringInterface__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.StringInterface
 * Method:     getParamDictionary()
 * Type:       non-virtual method
 * Definition: ParamDictionary* Ogre::StringInterface::getParamDictionary
 * Signature:  ()Ogre_ParamDictionary
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_StringInterface__1getParamDictionary (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.StringInterface
 * Method:     getParamDictionary_const()
 * Type:       non-virtual method
 * Definition: const ParamDictionary* Ogre::StringInterface::getParamDictionary
 * Signature:  ()Ogre_ParamDictionary
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_StringInterface__1getParamDictionary_1const_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.StringInterface
 * Method:     getParameters()
 * Type:       non-virtual method
 * Definition: const ParameterList& Ogre::StringInterface::getParameters
 * Signature:  ()Ogre_ParameterList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_StringInterface__1getParameters_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.StringInterface
 * Method:     setParameter()
 * Type:       virtual method
 * Definition: virtual bool Ogre::StringInterface::setParameter
 * Signature:  (std_stringstd_string)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_StringInterface__1setParameter_1_1StringRStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jstring value
);

/*
 * Class:      org.ogre4j.StringInterface
 * Method:     setParameterList()
 * Type:       virtual method
 * Definition: virtual void Ogre::StringInterface::setParameterList
 * Signature:  (Ogre_NameValuePairList)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StringInterface__1setParameterList_1_1NameValuePairListR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong paramList
);

/*
 * Class:      org.ogre4j.StringInterface
 * Method:     getParameter()
 * Type:       virtual method
 * Definition: virtual String Ogre::StringInterface::getParameter
 * Signature:  (std_string)std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_StringInterface__1getParameter_1_1StringR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.StringInterface
 * Method:     copyParametersTo()
 * Type:       virtual method
 * Definition: virtual void Ogre::StringInterface::copyParametersTo
 * Signature:  (Ogre_StringInterface)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StringInterface__1copyParametersTo_1_1StringInterfacep_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong dest
);

/*
 * Class:      org.ogre4j.StringInterface
 * Method:     cleanupDictionary()
 * Type:       static method
 * Definition: static void Ogre::StringInterface::cleanupDictionary
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StringInterface__1cleanupDictionary (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.StringInterface
 * Method:     StringInterface()
 * Type:       constructor
 * Definition: Ogre::StringInterface::StringInterface
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_StringInterface__1_1createStringInterface (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.StringInterface
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::StringInterface::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StringInterface__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_StringInterface__*/
