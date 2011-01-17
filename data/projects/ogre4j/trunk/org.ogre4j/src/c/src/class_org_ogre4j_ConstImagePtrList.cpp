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
#include "class_org_ogre4j_ConstImagePtrList.h"

// import header files of original library
#include <vector>
#include "OgreImage.h"



/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     ConstImagePtrList()
 * Type:       constructor
 * Definition: std::vector::vector
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ConstImagePtrList__1_1createConstImagePtrList (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::ConstImagePtrList 
   
   // parameter conversions 
   
   // create new instance of class Ogre::ConstImagePtrList 
   Ogre::ConstImagePtrList* _cpp_this = new Ogre::ConstImagePtrList(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* std::vector::vector */


/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     assign()
 * Type:       non-virtual method
 * Definition: void std::vector::assign
 * Signature:  (IOgre_Image)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConstImagePtrList__1assign_1_1ivOgre_1ImageP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint num, 
  jlong val
)
{
   // parameter conversions 
  size_t _cpp_num = num;
  Ogre::Image*const _cpp_val = reinterpret_cast< Ogre::Image* const >(val); 
   
   // cast pointer to C++ object 
   Ogre::ConstImagePtrList* _cpp_this = reinterpret_cast<Ogre::ConstImagePtrList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->assign(_cpp_num, _cpp_val);
} /* void std::vector::assign */


/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     at()
 * Type:       non-virtual method
 * Definition: T& std::vector::at
 * Signature:  (I)Ogre_Image
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ConstImagePtrList__1at_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint loc
)
{
   // parameter conversions 
  size_t _cpp_loc = loc; 
   
   // cast pointer to C++ object 
   Ogre::ConstImagePtrList* _cpp_this = reinterpret_cast<Ogre::ConstImagePtrList*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::Image* _cpp_result = _cpp_this->at(_cpp_loc) ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T& std::vector::at */


/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     back()
 * Type:       non-virtual method
 * Definition: T std::vector::back
 * Signature:  ()Ogre_Image
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ConstImagePtrList__1back (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ConstImagePtrList* _cpp_this = reinterpret_cast<Ogre::ConstImagePtrList*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::Image* _cpp_result = _cpp_this->back() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T std::vector::back */


/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     capacity()
 * Type:       non-virtual method
 * Definition: size_t std::vector::capacity
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_ConstImagePtrList__1capacity (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ConstImagePtrList* _cpp_this = reinterpret_cast<Ogre::ConstImagePtrList*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->capacity() ; 
   return _cpp_result;
} /* size_t std::vector::capacity */


/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     clear()
 * Type:       non-virtual method
 * Definition: void std::vector::clear
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConstImagePtrList__1clear (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ConstImagePtrList* _cpp_this = reinterpret_cast<Ogre::ConstImagePtrList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->clear();
} /* void std::vector::clear */


/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     empty()
 * Type:       non-virtual method
 * Definition: bool std::vector::empty
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_ConstImagePtrList__1empty (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ConstImagePtrList* _cpp_this = reinterpret_cast<Ogre::ConstImagePtrList*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->empty() ; 
   return _cpp_result ? 1 : 0;
} /* bool std::vector::empty */


/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     front()
 * Type:       non-virtual method
 * Definition: T std::vector::front
 * Signature:  ()Ogre_Image
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ConstImagePtrList__1front (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ConstImagePtrList* _cpp_this = reinterpret_cast<Ogre::ConstImagePtrList*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::Image* _cpp_result = _cpp_this->front() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* T std::vector::front */


/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     max_size()
 * Type:       non-virtual method
 * Definition: size_t std::vector::max_size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_ConstImagePtrList__1max_1size (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ConstImagePtrList* _cpp_this = reinterpret_cast<Ogre::ConstImagePtrList*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->max_size() ; 
   return _cpp_result;
} /* size_t std::vector::max_size */


/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     pop_back()
 * Type:       non-virtual method
 * Definition: void std::vector::pop_back
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConstImagePtrList__1pop_1back (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ConstImagePtrList* _cpp_this = reinterpret_cast<Ogre::ConstImagePtrList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->pop_back();
} /* void std::vector::pop_back */


/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     push_back()
 * Type:       non-virtual method
 * Definition: void std::vector::push_back
 * Signature:  (Ogre_Image)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConstImagePtrList__1push_1back_1_1Ogre_1ImageP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong val
)
{
   // parameter conversions 
  Ogre::Image*const _cpp_val = reinterpret_cast< Ogre::Image* const >(val); 
   
   // cast pointer to C++ object 
   Ogre::ConstImagePtrList* _cpp_this = reinterpret_cast<Ogre::ConstImagePtrList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->push_back(_cpp_val);
} /* void std::vector::push_back */


/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     reserve()
 * Type:       non-virtual method
 * Definition: void std::vector::reserve
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConstImagePtrList__1reserve_1_1iV (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint size
)
{
   // parameter conversions 
  const size_t _cpp_size = size; 
   
   // cast pointer to C++ object 
   Ogre::ConstImagePtrList* _cpp_this = reinterpret_cast<Ogre::ConstImagePtrList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->reserve(_cpp_size);
} /* void std::vector::reserve */


/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     size()
 * Type:       non-virtual method
 * Definition: size_t std::vector::size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_ConstImagePtrList__1size (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ConstImagePtrList* _cpp_this = reinterpret_cast<Ogre::ConstImagePtrList*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->size() ; 
   return _cpp_result;
} /* size_t std::vector::size */


/*
 * Class:      org.ogre4j.ConstImagePtrList
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ConstImagePtrList::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConstImagePtrList__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::ConstImagePtrList 
   // cast pointer to C++ object 
   Ogre::ConstImagePtrList* _cpp_this = reinterpret_cast<Ogre::ConstImagePtrList*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::ConstImagePtrList::__delete */
