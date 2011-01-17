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
#include "class_org_ogre4j_Pose_00024ConstVertexOffsetIterator.h"

// import header files of original library
#include <OgreIteratorWrappers.h>
#include <OgrePose.h>



/*
 * Class:      org.ogre4j.Pose.00024ConstVertexOffsetIterator
 * Method:     ConstVertexOffsetIterator()
 * Type:       constructor
 * Definition: Ogre::ConstMapIterator< T >::ConstMapIterator
 * Signature:  (Ogre_Pose_VertexOffsetMap)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Pose_00024ConstVertexOffsetIterator__1_1createConstVertexOffsetIterator_1_1Ogre_1Pose_1VertexOffsetMapR (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong c
)
{
   // constructor of class Ogre::Pose::ConstVertexOffsetIterator 
   
   // parameter conversions 
  const Ogre::Pose::VertexOffsetMap* _cpp_c = reinterpret_cast< Ogre::Pose::VertexOffsetMap* const >(c); 
   
   // create new instance of class Ogre::Pose::ConstVertexOffsetIterator 
   Ogre::Pose::ConstVertexOffsetIterator* _cpp_this = new Ogre::Pose::ConstVertexOffsetIterator(*_cpp_c); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::ConstMapIterator< T >::ConstMapIterator */


/*
 * Class:      org.ogre4j.Pose.00024ConstVertexOffsetIterator
 * Method:     hasMoreElements()
 * Type:       non-virtual method
 * Definition: bool Ogre::ConstMapIterator< T >::hasMoreElements
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Pose_00024ConstVertexOffsetIterator__1hasMoreElements_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Pose::ConstVertexOffsetIterator* _cpp_this = reinterpret_cast<const Ogre::Pose::ConstVertexOffsetIterator*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->hasMoreElements() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::ConstMapIterator< T >::hasMoreElements */


/*
 * Class:      org.ogre4j.Pose.00024ConstVertexOffsetIterator
 * Method:     getNext()
 * Type:       non-virtual method
 * Definition: T::mapped_type Ogre::ConstMapIterator< T >::getNext
 * Signature:  ()Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Pose_00024ConstVertexOffsetIterator__1getNext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::Pose::ConstVertexOffsetIterator* _cpp_this = reinterpret_cast<Ogre::Pose::ConstVertexOffsetIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::Vector3* _cpp_result = new Ogre::Vector3( _cpp_this->getNext() ); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::mapped_type Ogre::ConstMapIterator< T >::getNext */


/*
 * Class:      org.ogre4j.Pose.00024ConstVertexOffsetIterator
 * Method:     peekNextValue()
 * Type:       non-virtual method
 * Definition: T::mapped_type Ogre::ConstMapIterator< T >::peekNextValue
 * Signature:  ()Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Pose_00024ConstVertexOffsetIterator__1peekNextValue_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Pose::ConstVertexOffsetIterator* _cpp_this = reinterpret_cast<const Ogre::Pose::ConstVertexOffsetIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::Vector3* _cpp_result = new Ogre::Vector3( _cpp_this->peekNextValue() ); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::mapped_type Ogre::ConstMapIterator< T >::peekNextValue */


/*
 * Class:      org.ogre4j.Pose.00024ConstVertexOffsetIterator
 * Method:     peekNextKey()
 * Type:       non-virtual method
 * Definition: T::key_type Ogre::ConstMapIterator< T >::peekNextKey
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Pose_00024ConstVertexOffsetIterator__1peekNextKey_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Pose::ConstVertexOffsetIterator* _cpp_this = reinterpret_cast<const Ogre::Pose::ConstVertexOffsetIterator*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->peekNextKey() ; 
   return _cpp_result;
} /* T::key_type Ogre::ConstMapIterator< T >::peekNextKey */


/*
 * Class:      org.ogre4j.Pose.00024ConstVertexOffsetIterator
 * Method:     operator=()
 * Type:       non-virtual method
 * Definition: ConstMapIterator<T>& Ogre::ConstMapIterator< T >::operator=
 * Signature:  (Ogre_Pose_ConstVertexOffsetIterator)Ogre_Pose_ConstVertexOffsetIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Pose_00024ConstVertexOffsetIterator__1operatorAssignment_1_1_1Ogre_1Pose_1ConstVertexOffsetIteratorr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rhs
)
{
   // parameter conversions 
  Ogre::Pose::ConstVertexOffsetIterator* _cpp_rhs = reinterpret_cast< Ogre::Pose::ConstVertexOffsetIterator* >(rhs); 
   
   // cast pointer to C++ object 
   Ogre::Pose::ConstVertexOffsetIterator* _cpp_this = reinterpret_cast<Ogre::Pose::ConstVertexOffsetIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::Pose::ConstVertexOffsetIterator* _cpp_result = & _cpp_this->operator=(*_cpp_rhs) ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* ConstMapIterator<T>& Ogre::ConstMapIterator< T >::operator= */


/*
 * Class:      org.ogre4j.Pose.00024ConstVertexOffsetIterator
 * Method:     peekNextValuePtr()
 * Type:       non-virtual method
 * Definition: const T::mapped_type* Ogre::ConstMapIterator< T >::peekNextValuePtr
 * Signature:  ()Ogre_Vector3
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Pose_00024ConstVertexOffsetIterator__1peekNextValuePtr_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Pose::ConstVertexOffsetIterator* _cpp_this = reinterpret_cast<const Ogre::Pose::ConstVertexOffsetIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::Vector3*const _cpp_result = _cpp_this->peekNextValuePtr() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* const T::mapped_type* Ogre::ConstMapIterator< T >::peekNextValuePtr */


/*
 * Class:      org.ogre4j.Pose.00024ConstVertexOffsetIterator
 * Method:     moveNext()
 * Type:       non-virtual method
 * Definition: void Ogre::ConstMapIterator< T >::moveNext
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Pose_00024ConstVertexOffsetIterator__1moveNext_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Pose::ConstVertexOffsetIterator* _cpp_this = reinterpret_cast<const Ogre::Pose::ConstVertexOffsetIterator*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->moveNext();
} /* void Ogre::ConstMapIterator< T >::moveNext */


/*
 * Class:      org.ogre4j.Pose.00024ConstVertexOffsetIterator
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::Pose::ConstVertexOffsetIterator::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Pose_00024ConstVertexOffsetIterator__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::Pose::ConstVertexOffsetIterator 
   // cast pointer to C++ object 
   Ogre::Pose::ConstVertexOffsetIterator* _cpp_this = reinterpret_cast<Ogre::Pose::ConstVertexOffsetIterator*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::Pose::ConstVertexOffsetIterator::__delete */
