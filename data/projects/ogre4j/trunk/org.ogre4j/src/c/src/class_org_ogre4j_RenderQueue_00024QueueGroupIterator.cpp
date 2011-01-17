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
#include "class_org_ogre4j_RenderQueue_00024QueueGroupIterator.h"

// import header files of original library
#include <OgreIteratorWrappers.h>
#include <OgreRenderQueue.h>



/*
 * Class:      org.ogre4j.RenderQueue.00024QueueGroupIterator
 * Method:     QueueGroupIterator()
 * Type:       constructor
 * Definition: Ogre::MapIterator< T >::MapIterator
 * Signature:  (Ogre_RenderQueue_RenderQueueGroupMap)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderQueue_00024QueueGroupIterator__1_1createQueueGroupIterator_1_1Ogre_1RenderQueue_1RenderQueueGroupMapr (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong c
)
{
   // constructor of class Ogre::RenderQueue::QueueGroupIterator 
   
   // parameter conversions 
  Ogre::RenderQueue::RenderQueueGroupMap* _cpp_c = reinterpret_cast< Ogre::RenderQueue::RenderQueueGroupMap* >(c); 
   
   // create new instance of class Ogre::RenderQueue::QueueGroupIterator 
   Ogre::RenderQueue::QueueGroupIterator* _cpp_this = new Ogre::RenderQueue::QueueGroupIterator(*_cpp_c); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::MapIterator< T >::MapIterator */


/*
 * Class:      org.ogre4j.RenderQueue.00024QueueGroupIterator
 * Method:     hasMoreElements()
 * Type:       non-virtual method
 * Definition: bool Ogre::MapIterator< T >::hasMoreElements
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderQueue_00024QueueGroupIterator__1hasMoreElements_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderQueue::QueueGroupIterator* _cpp_this = reinterpret_cast<const Ogre::RenderQueue::QueueGroupIterator*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->hasMoreElements() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::MapIterator< T >::hasMoreElements */


/*
 * Class:      org.ogre4j.RenderQueue.00024QueueGroupIterator
 * Method:     getNext()
 * Type:       non-virtual method
 * Definition: T::mapped_type Ogre::MapIterator< T >::getNext
 * Signature:  ()Ogre_RenderQueueGroup
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderQueue_00024QueueGroupIterator__1getNext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderQueue::QueueGroupIterator* _cpp_this = reinterpret_cast<Ogre::RenderQueue::QueueGroupIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::RenderQueueGroup* _cpp_result = _cpp_this->getNext() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::mapped_type Ogre::MapIterator< T >::getNext */


/*
 * Class:      org.ogre4j.RenderQueue.00024QueueGroupIterator
 * Method:     peekNextValue()
 * Type:       non-virtual method
 * Definition: T::mapped_type Ogre::MapIterator< T >::peekNextValue
 * Signature:  ()Ogre_RenderQueueGroup
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderQueue_00024QueueGroupIterator__1peekNextValue (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderQueue::QueueGroupIterator* _cpp_this = reinterpret_cast<Ogre::RenderQueue::QueueGroupIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::RenderQueueGroup* _cpp_result = _cpp_this->peekNextValue() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::mapped_type Ogre::MapIterator< T >::peekNextValue */


/*
 * Class:      org.ogre4j.RenderQueue.00024QueueGroupIterator
 * Method:     peekNextKey()
 * Type:       non-virtual method
 * Definition: T::key_type Ogre::MapIterator< T >::peekNextKey
 * Signature:  ()C
 */

JNIEXPORT jshort JNICALL Java_org_ogre4j_RenderQueue_00024QueueGroupIterator__1peekNextKey (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderQueue::QueueGroupIterator* _cpp_this = reinterpret_cast<Ogre::RenderQueue::QueueGroupIterator*>(_jni_pointer_); 
   
   // call library method 
   const unsigned char _cpp_result = _cpp_this->peekNextKey() ; 
   return _cpp_result;
} /* T::key_type Ogre::MapIterator< T >::peekNextKey */


/*
 * Class:      org.ogre4j.RenderQueue.00024QueueGroupIterator
 * Method:     operator=()
 * Type:       non-virtual method
 * Definition: MapIterator<T>& Ogre::MapIterator< T >::operator=
 * Signature:  (Ogre_RenderQueue_QueueGroupIterator)Ogre_RenderQueue_QueueGroupIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderQueue_00024QueueGroupIterator__1operatorAssignment_1_1_1Ogre_1RenderQueue_1QueueGroupIteratorr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rhs
)
{
   // parameter conversions 
  Ogre::RenderQueue::QueueGroupIterator* _cpp_rhs = reinterpret_cast< Ogre::RenderQueue::QueueGroupIterator* >(rhs); 
   
   // cast pointer to C++ object 
   Ogre::RenderQueue::QueueGroupIterator* _cpp_this = reinterpret_cast<Ogre::RenderQueue::QueueGroupIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::RenderQueue::QueueGroupIterator* _cpp_result = & _cpp_this->operator=(*_cpp_rhs) ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* MapIterator<T>& Ogre::MapIterator< T >::operator= */


/*
 * Class:      org.ogre4j.RenderQueue.00024QueueGroupIterator
 * Method:     peekNextValuePtr()
 * Type:       non-virtual method
 * Definition: T::mapped_type* Ogre::MapIterator< T >::peekNextValuePtr
 * Signature:  ()Ogre_RenderQueueGroup
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderQueue_00024QueueGroupIterator__1peekNextValuePtr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderQueue::QueueGroupIterator* _cpp_this = reinterpret_cast<Ogre::RenderQueue::QueueGroupIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::RenderQueueGroup* const * _cpp_result = _cpp_this->peekNextValuePtr() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::mapped_type* Ogre::MapIterator< T >::peekNextValuePtr */


/*
 * Class:      org.ogre4j.RenderQueue.00024QueueGroupIterator
 * Method:     moveNext()
 * Type:       non-virtual method
 * Definition: void Ogre::MapIterator< T >::moveNext
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderQueue_00024QueueGroupIterator__1moveNext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderQueue::QueueGroupIterator* _cpp_this = reinterpret_cast<Ogre::RenderQueue::QueueGroupIterator*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->moveNext();
} /* void Ogre::MapIterator< T >::moveNext */


/*
 * Class:      org.ogre4j.RenderQueue.00024QueueGroupIterator
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::RenderQueue::QueueGroupIterator::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderQueue_00024QueueGroupIterator__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::RenderQueue::QueueGroupIterator 
   // cast pointer to C++ object 
   Ogre::RenderQueue::QueueGroupIterator* _cpp_this = reinterpret_cast<Ogre::RenderQueue::QueueGroupIterator*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::RenderQueue::QueueGroupIterator::__delete */
