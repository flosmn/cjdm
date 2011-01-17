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


#ifndef __Included_org_ogre4j_FileHandleDataStream__
#define __Included_org_ogre4j_FileHandleDataStream__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.FileHandleDataStream
 * Method:     read()
 * Type:       virtual method
 * Definition: size_t Ogre::FileHandleDataStream::read
 * Signature:  (VI)I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_FileHandleDataStream__1read_1_1vpiv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong buf, 
  jint count
);

/*
 * Class:      org.ogre4j.FileHandleDataStream
 * Method:     skip()
 * Type:       virtual method
 * Definition: void Ogre::FileHandleDataStream::skip
 * Signature:  (J)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_FileHandleDataStream__1skip_1_1lv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong count
);

/*
 * Class:      org.ogre4j.FileHandleDataStream
 * Method:     seek()
 * Type:       virtual method
 * Definition: void Ogre::FileHandleDataStream::seek
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_FileHandleDataStream__1seek_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint pos
);

/*
 * Class:      org.ogre4j.FileHandleDataStream
 * Method:     tell()
 * Type:       virtual method
 * Definition: size_t Ogre::FileHandleDataStream::tell
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_FileHandleDataStream__1tell_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.FileHandleDataStream
 * Method:     eof()
 * Type:       virtual method
 * Definition: bool Ogre::FileHandleDataStream::eof
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_FileHandleDataStream__1eof_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.FileHandleDataStream
 * Method:     close()
 * Type:       virtual method
 * Definition: void Ogre::FileHandleDataStream::close
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_FileHandleDataStream__1close (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.FileHandleDataStream
 * Method:     getName()
 * Type:       non-virtual method
 * Definition: const String& Ogre::DataStream::getName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_FileHandleDataStream__1getName (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.FileHandleDataStream
 * Method:     readLine()
 * Type:       virtual method
 * Definition: virtual size_t Ogre::DataStream::readLine
 * Signature:  (CIstd_string)I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_FileHandleDataStream__1readLine_1_1cpivStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong buf, 
  jint maxCount, 
  jstring delim
);

/*
 * Class:      org.ogre4j.FileHandleDataStream
 * Method:     getLine()
 * Type:       virtual method
 * Definition: virtual String Ogre::DataStream::getLine
 * Signature:  (Z)std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_FileHandleDataStream__1getLine_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean trimAfter
);

/*
 * Class:      org.ogre4j.FileHandleDataStream
 * Method:     getAsString()
 * Type:       virtual method
 * Definition: virtual String Ogre::DataStream::getAsString
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_FileHandleDataStream__1getAsString (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.FileHandleDataStream
 * Method:     skipLine()
 * Type:       virtual method
 * Definition: virtual size_t Ogre::DataStream::skipLine
 * Signature:  (std_string)I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_FileHandleDataStream__1skipLine_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring delim
);

/*
 * Class:      org.ogre4j.FileHandleDataStream
 * Method:     size()
 * Type:       non-virtual method
 * Definition: size_t Ogre::DataStream::size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_FileHandleDataStream__1size_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.FileHandleDataStream
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::FileHandleDataStream::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_FileHandleDataStream__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_FileHandleDataStream__*/
