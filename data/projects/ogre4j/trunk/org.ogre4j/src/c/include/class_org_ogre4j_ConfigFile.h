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


#ifndef __Included_org_ogre4j_ConfigFile__
#define __Included_org_ogre4j_ConfigFile__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     ConfigFile()
 * Type:       constructor
 * Definition: Ogre::ConfigFile::ConfigFile
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ConfigFile__1_1createConfigFile (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     load()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigFile::load
 * Signature:  (std_stringstd_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1load_1_1StringRStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring filename, 
  jstring separators, 
  jboolean trimWhitespace
);

/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     load()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigFile::load
 * Signature:  (std_stringstd_stringstd_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1load_1_1StringRStringRStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring filename, 
  jstring resourceGroup, 
  jstring separators, 
  jboolean trimWhitespace
);

/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     load()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigFile::load
 * Signature:  (Ogre_DataStreamPtrstd_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1load_1_1DataStreamPtrRStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong stream, 
  jstring separators, 
  jboolean trimWhitespace
);

/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     loadDirect()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigFile::loadDirect
 * Signature:  (std_stringstd_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1loadDirect_1_1StringRStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring filename, 
  jstring separators, 
  jboolean trimWhitespace
);

/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     loadFromResourceSystem()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigFile::loadFromResourceSystem
 * Signature:  (std_stringstd_stringstd_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1loadFromResourceSystem_1_1StringRStringRStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring filename, 
  jstring resourceGroup, 
  jstring separators, 
  jboolean trimWhitespace
);

/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     getSetting()
 * Type:       non-virtual method
 * Definition: String Ogre::ConfigFile::getSetting
 * Signature:  (std_stringstd_stringstd_string)std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ConfigFile__1getSetting_1_1StringRStringRStringR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring key, 
  jstring section, 
  jstring defaultValue
);

/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     getMultiSetting()
 * Type:       non-virtual method
 * Definition: StringVector Ogre::ConfigFile::getMultiSetting
 * Signature:  (std_stringstd_string)Ogre_StringVector
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ConfigFile__1getMultiSetting_1_1StringRStringR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring key, 
  jstring section
);

/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     getSectionIterator()
 * Type:       non-virtual method
 * Definition: SectionIterator Ogre::ConfigFile::getSectionIterator
 * Signature:  ()Ogre_ConfigFile_SectionIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ConfigFile__1getSectionIterator (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     getSettingsIterator()
 * Type:       non-virtual method
 * Definition: SettingsIterator Ogre::ConfigFile::getSettingsIterator
 * Signature:  (std_string)Ogre_ConfigFile_SettingsIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ConfigFile__1getSettingsIterator_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring section
);

/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     clear()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigFile::clear
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1clear (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ConfigFile::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_ConfigFile__*/
