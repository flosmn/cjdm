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


#ifndef __Included_org_ogre4j_InvalidStateException__
#define __Included_org_ogre4j_InvalidStateException__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.InvalidStateException
 * Method:     InvalidStateException()
 * Type:       constructor
 * Definition: Ogre::InvalidStateException::InvalidStateException
 * Signature:  (Istd_stringstd_stringCJ)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InvalidStateException__1_1createInvalidStateException_1_1ivStringRStringRcPlv (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jint number, 
  jstring description, 
  jstring source, 
  jstring file, 
  jlong line
);

/*
 * Class:      org.ogre4j.InvalidStateException
 * Method:     operator=()
 * Type:       non-virtual method
 * Definition: void Ogre::Exception::operator=
 * Signature:  (Ogre_Exception)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InvalidStateException__1operatorAssignment_1_1ExceptionR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rhs
);

/*
 * Class:      org.ogre4j.InvalidStateException
 * Method:     getFullDescription()
 * Type:       virtual method
 * Definition: virtual const String& Ogre::Exception::getFullDescription
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_InvalidStateException__1getFullDescription_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InvalidStateException
 * Method:     getNumber()
 * Type:       virtual method
 * Definition: virtual int Ogre::Exception::getNumber
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_InvalidStateException__1getNumber_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InvalidStateException
 * Method:     getSource()
 * Type:       virtual method
 * Definition: virtual const String& Ogre::Exception::getSource
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_InvalidStateException__1getSource_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InvalidStateException
 * Method:     getFile()
 * Type:       virtual method
 * Definition: virtual const String& Ogre::Exception::getFile
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_InvalidStateException__1getFile_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InvalidStateException
 * Method:     getLine()
 * Type:       virtual method
 * Definition: virtual long Ogre::Exception::getLine
 * Signature:  ()J
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InvalidStateException__1getLine_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InvalidStateException
 * Method:     getDescription()
 * Type:       virtual method
 * Definition: virtual const String& Ogre::Exception::getDescription
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_InvalidStateException__1getDescription_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InvalidStateException
 * Method:     what()
 * Type:       non-virtual method
 * Definition: const char* Ogre::Exception::what
 * Signature:  ()C
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_InvalidStateException__1what_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.InvalidStateException
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::InvalidStateException::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InvalidStateException__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_InvalidStateException__*/
