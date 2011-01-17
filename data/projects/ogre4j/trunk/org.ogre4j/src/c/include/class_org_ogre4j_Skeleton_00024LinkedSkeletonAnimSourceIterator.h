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


#ifndef __Included_org_ogre4j_Skeleton_00024LinkedSkeletonAnimSourceIterator__
#define __Included_org_ogre4j_Skeleton_00024LinkedSkeletonAnimSourceIterator__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.Skeleton.00024LinkedSkeletonAnimSourceIterator
 * Method:     LinkedSkeletonAnimSourceIterator()
 * Type:       constructor
 * Definition: Ogre::ConstVectorIterator< T >::ConstVectorIterator
 * Signature:  (Ogre_Skeleton_LinkedSkeletonAnimSourceList)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Skeleton_00024LinkedSkeletonAnimSourceIterator__1_1createLinkedSkeletonAnimSourceIterator_1_1Ogre_1Skeleton_1LinkedSkeletonAnimSourceListR (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong c
);

/*
 * Class:      org.ogre4j.Skeleton.00024LinkedSkeletonAnimSourceIterator
 * Method:     hasMoreElements()
 * Type:       non-virtual method
 * Definition: bool Ogre::ConstVectorIterator< T >::hasMoreElements
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Skeleton_00024LinkedSkeletonAnimSourceIterator__1hasMoreElements_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Skeleton.00024LinkedSkeletonAnimSourceIterator
 * Method:     getNext()
 * Type:       non-virtual method
 * Definition: T::value_type Ogre::ConstVectorIterator< T >::getNext
 * Signature:  ()Ogre_LinkedSkeletonAnimationSource
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Skeleton_00024LinkedSkeletonAnimSourceIterator__1getNext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Skeleton.00024LinkedSkeletonAnimSourceIterator
 * Method:     peekNext()
 * Type:       non-virtual method
 * Definition: T::value_type Ogre::ConstVectorIterator< T >::peekNext
 * Signature:  ()Ogre_LinkedSkeletonAnimationSource
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Skeleton_00024LinkedSkeletonAnimSourceIterator__1peekNext_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Skeleton.00024LinkedSkeletonAnimSourceIterator
 * Method:     peekNextPtr()
 * Type:       non-virtual method
 * Definition: T::const_pointer Ogre::ConstVectorIterator< T >::peekNextPtr
 * Signature:  ()Ogre_LinkedSkeletonAnimationSource
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Skeleton_00024LinkedSkeletonAnimSourceIterator__1peekNextPtr_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Skeleton.00024LinkedSkeletonAnimSourceIterator
 * Method:     moveNext()
 * Type:       non-virtual method
 * Definition: void Ogre::ConstVectorIterator< T >::moveNext
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Skeleton_00024LinkedSkeletonAnimSourceIterator__1moveNext_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Skeleton.00024LinkedSkeletonAnimSourceIterator
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::Skeleton::LinkedSkeletonAnimSourceIterator::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Skeleton_00024LinkedSkeletonAnimSourceIterator__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_Skeleton_00024LinkedSkeletonAnimSourceIterator__*/
