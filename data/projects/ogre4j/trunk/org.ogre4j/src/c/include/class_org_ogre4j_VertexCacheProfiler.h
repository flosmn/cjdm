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


#ifndef __Included_org_ogre4j_VertexCacheProfiler__
#define __Included_org_ogre4j_VertexCacheProfiler__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.VertexCacheProfiler
 * Method:     VertexCacheProfiler()
 * Type:       constructor
 * Definition: Ogre::VertexCacheProfiler::VertexCacheProfiler
 * Signature:  (IOgre_VertexCacheProfiler_CacheType)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexCacheProfiler__1_1createVertexCacheProfiler_1_1IvCacheTypev (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong cachesize, 
  jint cachetype
);

/*
 * Class:      org.ogre4j.VertexCacheProfiler
 * Method:     profile()
 * Type:       non-virtual method
 * Definition: void Ogre::VertexCacheProfiler::profile
 * Signature:  (Ogre_HardwareIndexBufferSharedPtr)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VertexCacheProfiler__1profile_1_1HardwareIndexBufferSharedPtrR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong indexBuffer
);

/*
 * Class:      org.ogre4j.VertexCacheProfiler
 * Method:     reset()
 * Type:       non-virtual method
 * Definition: void Ogre::VertexCacheProfiler::reset
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VertexCacheProfiler__1reset (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VertexCacheProfiler
 * Method:     flush()
 * Type:       non-virtual method
 * Definition: void Ogre::VertexCacheProfiler::flush
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VertexCacheProfiler__1flush (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VertexCacheProfiler
 * Method:     getHits()
 * Type:       non-virtual method
 * Definition: unsigned int Ogre::VertexCacheProfiler::getHits
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexCacheProfiler__1getHits (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VertexCacheProfiler
 * Method:     getMisses()
 * Type:       non-virtual method
 * Definition: unsigned int Ogre::VertexCacheProfiler::getMisses
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexCacheProfiler__1getMisses (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VertexCacheProfiler
 * Method:     getSize()
 * Type:       non-virtual method
 * Definition: unsigned int Ogre::VertexCacheProfiler::getSize
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexCacheProfiler__1getSize (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VertexCacheProfiler
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::VertexCacheProfiler::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VertexCacheProfiler__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_VertexCacheProfiler__*/
