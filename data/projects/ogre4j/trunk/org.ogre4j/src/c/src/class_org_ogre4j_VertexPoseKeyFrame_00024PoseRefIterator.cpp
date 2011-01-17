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
#include "class_org_ogre4j_VertexPoseKeyFrame_00024PoseRefIterator.h"

// import header files of original library
#include <OgreIteratorWrappers.h>
#include <OgreKeyFrame.h>



/*
 * Class:      org.ogre4j.VertexPoseKeyFrame.00024PoseRefIterator
 * Method:     PoseRefIterator()
 * Type:       constructor
 * Definition: Ogre::VectorIterator< T >::VectorIterator
 * Signature:  (Ogre_VertexPoseKeyFrame_PoseRefList)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexPoseKeyFrame_00024PoseRefIterator__1_1createPoseRefIterator_1_1Ogre_1VertexPoseKeyFrame_1PoseRefListr (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong c
)
{
   // constructor of class Ogre::VertexPoseKeyFrame::PoseRefIterator 
   
   // parameter conversions 
  Ogre::VertexPoseKeyFrame::PoseRefList* _cpp_c = reinterpret_cast< Ogre::VertexPoseKeyFrame::PoseRefList* >(c); 
   
   // create new instance of class Ogre::VertexPoseKeyFrame::PoseRefIterator 
   Ogre::VertexPoseKeyFrame::PoseRefIterator* _cpp_this = new Ogre::VertexPoseKeyFrame::PoseRefIterator(*_cpp_c); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::VectorIterator< T >::VectorIterator */


/*
 * Class:      org.ogre4j.VertexPoseKeyFrame.00024PoseRefIterator
 * Method:     hasMoreElements()
 * Type:       non-virtual method
 * Definition: bool Ogre::VectorIterator< T >::hasMoreElements
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_VertexPoseKeyFrame_00024PoseRefIterator__1hasMoreElements_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::VertexPoseKeyFrame::PoseRefIterator* _cpp_this = reinterpret_cast<const Ogre::VertexPoseKeyFrame::PoseRefIterator*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->hasMoreElements() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::VectorIterator< T >::hasMoreElements */


/*
 * Class:      org.ogre4j.VertexPoseKeyFrame.00024PoseRefIterator
 * Method:     getNext()
 * Type:       non-virtual method
 * Definition: T::value_type Ogre::VectorIterator< T >::getNext
 * Signature:  ()Ogre_VertexPoseKeyFrame_PoseRef
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexPoseKeyFrame_00024PoseRefIterator__1getNext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::VertexPoseKeyFrame::PoseRefIterator* _cpp_this = reinterpret_cast<Ogre::VertexPoseKeyFrame::PoseRefIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::VertexPoseKeyFrame::PoseRef* _cpp_result = new Ogre::VertexPoseKeyFrame::PoseRef( _cpp_this->getNext() ); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::value_type Ogre::VectorIterator< T >::getNext */


/*
 * Class:      org.ogre4j.VertexPoseKeyFrame.00024PoseRefIterator
 * Method:     peekNext()
 * Type:       non-virtual method
 * Definition: T::value_type Ogre::VectorIterator< T >::peekNext
 * Signature:  ()Ogre_VertexPoseKeyFrame_PoseRef
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexPoseKeyFrame_00024PoseRefIterator__1peekNext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::VertexPoseKeyFrame::PoseRefIterator* _cpp_this = reinterpret_cast<Ogre::VertexPoseKeyFrame::PoseRefIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::VertexPoseKeyFrame::PoseRef* _cpp_result = new Ogre::VertexPoseKeyFrame::PoseRef( _cpp_this->peekNext() ); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::value_type Ogre::VectorIterator< T >::peekNext */


/*
 * Class:      org.ogre4j.VertexPoseKeyFrame.00024PoseRefIterator
 * Method:     peekNextPtr()
 * Type:       non-virtual method
 * Definition: T::pointer Ogre::VectorIterator< T >::peekNextPtr
 * Signature:  ()Ogre_VertexPoseKeyFrame_PoseRef
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexPoseKeyFrame_00024PoseRefIterator__1peekNextPtr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::VertexPoseKeyFrame::PoseRefIterator* _cpp_this = reinterpret_cast<Ogre::VertexPoseKeyFrame::PoseRefIterator*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::VertexPoseKeyFrame::PoseRef* _cpp_result = _cpp_this->peekNextPtr() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T::pointer Ogre::VectorIterator< T >::peekNextPtr */


/*
 * Class:      org.ogre4j.VertexPoseKeyFrame.00024PoseRefIterator
 * Method:     moveNext()
 * Type:       non-virtual method
 * Definition: void Ogre::VectorIterator< T >::moveNext
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VertexPoseKeyFrame_00024PoseRefIterator__1moveNext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::VertexPoseKeyFrame::PoseRefIterator* _cpp_this = reinterpret_cast<Ogre::VertexPoseKeyFrame::PoseRefIterator*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->moveNext();
} /* void Ogre::VectorIterator< T >::moveNext */


/*
 * Class:      org.ogre4j.VertexPoseKeyFrame.00024PoseRefIterator
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::VertexPoseKeyFrame::PoseRefIterator::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VertexPoseKeyFrame_00024PoseRefIterator__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::VertexPoseKeyFrame::PoseRefIterator 
   // cast pointer to C++ object 
   Ogre::VertexPoseKeyFrame::PoseRefIterator* _cpp_this = reinterpret_cast<Ogre::VertexPoseKeyFrame::PoseRefIterator*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::VertexPoseKeyFrame::PoseRefIterator::__delete */
