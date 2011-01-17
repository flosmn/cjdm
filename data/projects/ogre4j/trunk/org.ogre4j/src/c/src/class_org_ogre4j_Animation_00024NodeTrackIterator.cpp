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
#include "class_org_ogre4j_Animation_00024NodeTrackIterator.h"

// import header files of original library
#include <OgreIteratorWrappers.h>
#include <OgreAnimation.h>



/*
 * Class:      org.ogre4j.Animation.00024NodeTrackIterator
 * Method:     NodeTrackIterator()
 * Type:       constructor
 * Definition: Ogre::ConstMapIterator< T >::ConstMapIterator
 * Signature:  (Ogre_Animation_NodeTrackList)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Animation_00024NodeTrackIterator__1_1createNodeTrackIterator_1_1Ogre_1Animation_1NodeTrackListR (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong c
)
{
   // constructor of class Ogre::Animation::NodeTrackIterator 
   
   // parameter conversions 
  const Ogre::Animation::NodeTrackList* _cpp_c = reinterpret_cast< Ogre::Animation::NodeTrackList* const >(c); 
   
   // create new instance of class Ogre::Animation::NodeTrackIterator 
   Ogre::Animation::NodeTrackIterator* _cpp_this = new Ogre::Animation::NodeTrackIterator(*_cpp_c); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::ConstMapIterator< T >::ConstMapIterator */


/*
 * Class:      org.ogre4j.Animation.00024NodeTrackIterator
 * Method:     hasMoreElements()
 * Type:       non-virtual method
 * Definition: bool Ogre::ConstMapIterator< T >::hasMoreElements
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Animation_00024NodeTrackIterator__1hasMoreElements_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Animation::NodeTrackIterator* _cpp_this = reinterpret_cast<const Ogre::Animation::NodeTrackIterator*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->hasMoreElements() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::ConstMapIterator< T >::hasMoreElements */


/*
 * Class:      org.ogre4j.Animation.00024NodeTrackIterator
 * Method:     getNext()
 * Type:       non-virtual method
 * Definition: T::mapped_type Ogre::ConstMapIterator< T >::getNext
 * Signature:  ()Ogre_NodeAnimationTrack
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Animation_00024NodeTrackIterator__1getNext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::Animation::NodeTrackIterator* _cpp_this = reinterpret_cast<Ogre::Animation::NodeTrackIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::NodeAnimationTrack* _cpp_result = _cpp_this->getNext() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::mapped_type Ogre::ConstMapIterator< T >::getNext */


/*
 * Class:      org.ogre4j.Animation.00024NodeTrackIterator
 * Method:     peekNextValue()
 * Type:       non-virtual method
 * Definition: T::mapped_type Ogre::ConstMapIterator< T >::peekNextValue
 * Signature:  ()Ogre_NodeAnimationTrack
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Animation_00024NodeTrackIterator__1peekNextValue_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Animation::NodeTrackIterator* _cpp_this = reinterpret_cast<const Ogre::Animation::NodeTrackIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::NodeAnimationTrack* _cpp_result = _cpp_this->peekNextValue() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::mapped_type Ogre::ConstMapIterator< T >::peekNextValue */


/*
 * Class:      org.ogre4j.Animation.00024NodeTrackIterator
 * Method:     peekNextKey()
 * Type:       non-virtual method
 * Definition: T::key_type Ogre::ConstMapIterator< T >::peekNextKey
 * Signature:  ()S
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Animation_00024NodeTrackIterator__1peekNextKey_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Animation::NodeTrackIterator* _cpp_this = reinterpret_cast<const Ogre::Animation::NodeTrackIterator*>(_jni_pointer_); 
   
   // call library method 
   const unsigned short _cpp_result = _cpp_this->peekNextKey() ; 
   return _cpp_result;
} /* T::key_type Ogre::ConstMapIterator< T >::peekNextKey */


/*
 * Class:      org.ogre4j.Animation.00024NodeTrackIterator
 * Method:     operator=()
 * Type:       non-virtual method
 * Definition: ConstMapIterator<T>& Ogre::ConstMapIterator< T >::operator=
 * Signature:  (Ogre_Animation_NodeTrackIterator)Ogre_Animation_NodeTrackIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Animation_00024NodeTrackIterator__1operatorAssignment_1_1_1Ogre_1Animation_1NodeTrackIteratorr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rhs
)
{
   // parameter conversions 
  Ogre::Animation::NodeTrackIterator* _cpp_rhs = reinterpret_cast< Ogre::Animation::NodeTrackIterator* >(rhs); 
   
   // cast pointer to C++ object 
   Ogre::Animation::NodeTrackIterator* _cpp_this = reinterpret_cast<Ogre::Animation::NodeTrackIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::Animation::NodeTrackIterator* _cpp_result = & _cpp_this->operator=(*_cpp_rhs) ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* ConstMapIterator<T>& Ogre::ConstMapIterator< T >::operator= */


/*
 * Class:      org.ogre4j.Animation.00024NodeTrackIterator
 * Method:     peekNextValuePtr()
 * Type:       non-virtual method
 * Definition: const T::mapped_type* Ogre::ConstMapIterator< T >::peekNextValuePtr
 * Signature:  ()Ogre_NodeAnimationTrack
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Animation_00024NodeTrackIterator__1peekNextValuePtr_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Animation::NodeTrackIterator* _cpp_this = reinterpret_cast<const Ogre::Animation::NodeTrackIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::NodeAnimationTrack*const * _cpp_result = _cpp_this->peekNextValuePtr() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* const T::mapped_type* Ogre::ConstMapIterator< T >::peekNextValuePtr */


/*
 * Class:      org.ogre4j.Animation.00024NodeTrackIterator
 * Method:     moveNext()
 * Type:       non-virtual method
 * Definition: void Ogre::ConstMapIterator< T >::moveNext
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Animation_00024NodeTrackIterator__1moveNext_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Animation::NodeTrackIterator* _cpp_this = reinterpret_cast<const Ogre::Animation::NodeTrackIterator*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->moveNext();
} /* void Ogre::ConstMapIterator< T >::moveNext */


/*
 * Class:      org.ogre4j.Animation.00024NodeTrackIterator
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::Animation::NodeTrackIterator::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Animation_00024NodeTrackIterator__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::Animation::NodeTrackIterator 
   // cast pointer to C++ object 
   Ogre::Animation::NodeTrackIterator* _cpp_this = reinterpret_cast<Ogre::Animation::NodeTrackIterator*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::Animation::NodeTrackIterator::__delete */
