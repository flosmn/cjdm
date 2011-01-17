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
#include "class_org_ogre4j_InstancedGeometry_00024LODBucket_00024MaterialIterator.h"

// import header files of original library
#include <OgreIteratorWrappers.h>
#include <OgreInstancedGeometry.h>



/*
 * Class:      org.ogre4j.InstancedGeometry.00024LODBucket.00024MaterialIterator
 * Method:     MaterialIterator()
 * Type:       constructor
 * Definition: Ogre::MapIterator< T >::MapIterator
 * Signature:  (Ogre_InstancedGeometry_LODBucket_MaterialBucketMap)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024LODBucket_00024MaterialIterator__1_1createMaterialIterator_1_1Ogre_1InstancedGeometry_1LODBucket_1MaterialBucketMapr (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong c
)
{
   // constructor of class Ogre::InstancedGeometry::LODBucket::MaterialIterator 
   
   // parameter conversions 
  Ogre::InstancedGeometry::LODBucket::MaterialBucketMap* _cpp_c = reinterpret_cast< Ogre::InstancedGeometry::LODBucket::MaterialBucketMap* >(c); 
   
   // create new instance of class Ogre::InstancedGeometry::LODBucket::MaterialIterator 
   Ogre::InstancedGeometry::LODBucket::MaterialIterator* _cpp_this = new Ogre::InstancedGeometry::LODBucket::MaterialIterator(*_cpp_c); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::MapIterator< T >::MapIterator */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024LODBucket.00024MaterialIterator
 * Method:     hasMoreElements()
 * Type:       non-virtual method
 * Definition: bool Ogre::MapIterator< T >::hasMoreElements
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_InstancedGeometry_00024LODBucket_00024MaterialIterator__1hasMoreElements_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::InstancedGeometry::LODBucket::MaterialIterator* _cpp_this = reinterpret_cast<const Ogre::InstancedGeometry::LODBucket::MaterialIterator*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->hasMoreElements() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::MapIterator< T >::hasMoreElements */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024LODBucket.00024MaterialIterator
 * Method:     getNext()
 * Type:       non-virtual method
 * Definition: T::mapped_type Ogre::MapIterator< T >::getNext
 * Signature:  ()Ogre_InstancedGeometry_MaterialBucket
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024LODBucket_00024MaterialIterator__1getNext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::LODBucket::MaterialIterator* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::LODBucket::MaterialIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::InstancedGeometry::MaterialBucket* _cpp_result = _cpp_this->getNext() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::mapped_type Ogre::MapIterator< T >::getNext */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024LODBucket.00024MaterialIterator
 * Method:     peekNextValue()
 * Type:       non-virtual method
 * Definition: T::mapped_type Ogre::MapIterator< T >::peekNextValue
 * Signature:  ()Ogre_InstancedGeometry_MaterialBucket
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024LODBucket_00024MaterialIterator__1peekNextValue (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::LODBucket::MaterialIterator* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::LODBucket::MaterialIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::InstancedGeometry::MaterialBucket* _cpp_result = _cpp_this->peekNextValue() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::mapped_type Ogre::MapIterator< T >::peekNextValue */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024LODBucket.00024MaterialIterator
 * Method:     peekNextKey()
 * Type:       non-virtual method
 * Definition: T::key_type Ogre::MapIterator< T >::peekNextKey
 * Signature:  ()Ljava/lang/String;
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_InstancedGeometry_00024LODBucket_00024MaterialIterator__1peekNextKey (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::LODBucket::MaterialIterator* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::LODBucket::MaterialIterator*>(_jni_pointer_); 
   
   // call library method 
   const std::string _cpp_result = _cpp_this->peekNextKey() ; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* T::key_type Ogre::MapIterator< T >::peekNextKey */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024LODBucket.00024MaterialIterator
 * Method:     operator=()
 * Type:       non-virtual method
 * Definition: MapIterator<T>& Ogre::MapIterator< T >::operator=
 * Signature:  (Ogre_InstancedGeometry_LODBucket_MaterialIterator)Ogre_InstancedGeometry_LODBucket_MaterialIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024LODBucket_00024MaterialIterator__1operatorAssignment_1_1_1Ogre_1InstancedGeometry_1LODBucket_1MaterialIteratorr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rhs
)
{
   // parameter conversions 
  Ogre::InstancedGeometry::LODBucket::MaterialIterator* _cpp_rhs = reinterpret_cast< Ogre::InstancedGeometry::LODBucket::MaterialIterator* >(rhs); 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::LODBucket::MaterialIterator* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::LODBucket::MaterialIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::InstancedGeometry::LODBucket::MaterialIterator* _cpp_result = & _cpp_this->operator=(*_cpp_rhs) ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* MapIterator<T>& Ogre::MapIterator< T >::operator= */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024LODBucket.00024MaterialIterator
 * Method:     peekNextValuePtr()
 * Type:       non-virtual method
 * Definition: T::mapped_type* Ogre::MapIterator< T >::peekNextValuePtr
 * Signature:  ()Ogre_InstancedGeometry_MaterialBucket
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024LODBucket_00024MaterialIterator__1peekNextValuePtr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::LODBucket::MaterialIterator* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::LODBucket::MaterialIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::InstancedGeometry::MaterialBucket* const * _cpp_result = _cpp_this->peekNextValuePtr() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::mapped_type* Ogre::MapIterator< T >::peekNextValuePtr */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024LODBucket.00024MaterialIterator
 * Method:     moveNext()
 * Type:       non-virtual method
 * Definition: void Ogre::MapIterator< T >::moveNext
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024LODBucket_00024MaterialIterator__1moveNext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::LODBucket::MaterialIterator* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::LODBucket::MaterialIterator*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->moveNext();
} /* void Ogre::MapIterator< T >::moveNext */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024LODBucket.00024MaterialIterator
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::InstancedGeometry::LODBucket::MaterialIterator::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024LODBucket_00024MaterialIterator__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::InstancedGeometry::LODBucket::MaterialIterator 
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::LODBucket::MaterialIterator* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::LODBucket::MaterialIterator*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::InstancedGeometry::LODBucket::MaterialIterator::__delete */
