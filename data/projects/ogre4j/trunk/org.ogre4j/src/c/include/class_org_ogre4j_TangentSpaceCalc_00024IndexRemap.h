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


#ifndef __Included_org_ogre4j_TangentSpaceCalc_00024IndexRemap__
#define __Included_org_ogre4j_TangentSpaceCalc_00024IndexRemap__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.TangentSpaceCalc.00024IndexRemap
 * Method:     IndexRemap()
 * Type:       constructor
 * Definition: Ogre::TangentSpaceCalc::IndexRemap::IndexRemap
 * Signature:  (IIOgre_TangentSpaceCalc_VertexSplit)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_TangentSpaceCalc_00024IndexRemap__1_1createIndexRemap_1_1ivivVertexSplitR (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jint i, 
  jint f, 
  jlong s
);

/*
 * Class:      org.ogre4j.TangentSpaceCalc.00024IndexRemap
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::TangentSpaceCalc::IndexRemap::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_TangentSpaceCalc_00024IndexRemap__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.TangentSpaceCalc.00024IndexRemap
 * Method:     getindexSet()
 * Type:       getter for public attribute
 * Definition: size_t Ogre::TangentSpaceCalc::IndexRemap::indexSet
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_TangentSpaceCalc_00024IndexRemap__1getindexSet (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.TangentSpaceCalc.00024IndexRemap
 * Method:     setindexSet()
 * Type:       setter for public attribute
 * Definition: size_t Ogre::TangentSpaceCalc::IndexRemap::indexSet
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_TangentSpaceCalc_00024IndexRemap__1setindexSet (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.TangentSpaceCalc.00024IndexRemap
 * Method:     getfaceIndex()
 * Type:       getter for public attribute
 * Definition: size_t Ogre::TangentSpaceCalc::IndexRemap::faceIndex
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_TangentSpaceCalc_00024IndexRemap__1getfaceIndex (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.TangentSpaceCalc.00024IndexRemap
 * Method:     setfaceIndex()
 * Type:       setter for public attribute
 * Definition: size_t Ogre::TangentSpaceCalc::IndexRemap::faceIndex
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_TangentSpaceCalc_00024IndexRemap__1setfaceIndex (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.TangentSpaceCalc.00024IndexRemap
 * Method:     getsplitVertex()
 * Type:       getter for public attribute
 * Definition: VertexSplit Ogre::TangentSpaceCalc::IndexRemap::splitVertex
 * Signature:  ()Ogre_TangentSpaceCalc_VertexSplit
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_TangentSpaceCalc_00024IndexRemap__1getsplitVertex (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.TangentSpaceCalc.00024IndexRemap
 * Method:     setsplitVertex()
 * Type:       setter for public attribute
 * Definition: VertexSplit Ogre::TangentSpaceCalc::IndexRemap::splitVertex
 * Signature:  (Ogre_TangentSpaceCalc_VertexSplit)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_TangentSpaceCalc_00024IndexRemap__1setsplitVertex (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_TangentSpaceCalc_00024IndexRemap__*/
