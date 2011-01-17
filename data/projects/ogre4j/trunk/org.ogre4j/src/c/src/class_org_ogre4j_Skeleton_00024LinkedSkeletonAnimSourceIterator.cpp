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


// includes from config
#include <Ogre4JStableHeaders.h>

// use base library for cpp2j
#include "jni_base_all.h"

// import declaration of all functions
#include "class_org_ogre4j_Skeleton_00024LinkedSkeletonAnimSourceIterator.h"

// import header files of original library
#include <OgreIteratorWrappers.h>
#include <OgreSkeleton.h>



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
)
{
   // constructor of class Ogre::Skeleton::LinkedSkeletonAnimSourceIterator 
   
   // parameter conversions 
  const Ogre::Skeleton::LinkedSkeletonAnimSourceList* _cpp_c = reinterpret_cast< Ogre::Skeleton::LinkedSkeletonAnimSourceList* const >(c); 
   
   // create new instance of class Ogre::Skeleton::LinkedSkeletonAnimSourceIterator 
   Ogre::Skeleton::LinkedSkeletonAnimSourceIterator* _cpp_this = new Ogre::Skeleton::LinkedSkeletonAnimSourceIterator(*_cpp_c); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::ConstVectorIterator< T >::ConstVectorIterator */


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
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Skeleton::LinkedSkeletonAnimSourceIterator* _cpp_this = reinterpret_cast<const Ogre::Skeleton::LinkedSkeletonAnimSourceIterator*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->hasMoreElements() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::ConstVectorIterator< T >::hasMoreElements */


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
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::Skeleton::LinkedSkeletonAnimSourceIterator* _cpp_this = reinterpret_cast<Ogre::Skeleton::LinkedSkeletonAnimSourceIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::LinkedSkeletonAnimationSource* _cpp_result = new Ogre::LinkedSkeletonAnimationSource( _cpp_this->getNext() ); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::value_type Ogre::ConstVectorIterator< T >::getNext */


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
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Skeleton::LinkedSkeletonAnimSourceIterator* _cpp_this = reinterpret_cast<const Ogre::Skeleton::LinkedSkeletonAnimSourceIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::LinkedSkeletonAnimationSource* _cpp_result = new Ogre::LinkedSkeletonAnimationSource( _cpp_this->peekNext() ); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::value_type Ogre::ConstVectorIterator< T >::peekNext */


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
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Skeleton::LinkedSkeletonAnimSourceIterator* _cpp_this = reinterpret_cast<const Ogre::Skeleton::LinkedSkeletonAnimSourceIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::LinkedSkeletonAnimationSource* _cpp_result = _cpp_this->peekNextPtr() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::const_pointer Ogre::ConstVectorIterator< T >::peekNextPtr */


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
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Skeleton::LinkedSkeletonAnimSourceIterator* _cpp_this = reinterpret_cast<const Ogre::Skeleton::LinkedSkeletonAnimSourceIterator*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->moveNext();
} /* void Ogre::ConstVectorIterator< T >::moveNext */


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
)
{
   // destructor of class Ogre::Skeleton::LinkedSkeletonAnimSourceIterator 
   // cast pointer to C++ object 
   Ogre::Skeleton::LinkedSkeletonAnimSourceIterator* _cpp_this = reinterpret_cast<Ogre::Skeleton::LinkedSkeletonAnimSourceIterator*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::Skeleton::LinkedSkeletonAnimSourceIterator::__delete */
