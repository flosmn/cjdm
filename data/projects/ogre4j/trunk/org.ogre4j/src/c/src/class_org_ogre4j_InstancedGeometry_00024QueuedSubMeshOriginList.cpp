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
#include "class_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList.h"

// import header files of original library
#include <vector>
#include <OgreInstancedGeometry.h>



/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     QueuedSubMeshOriginList()
 * Type:       constructor
 * Definition: std::vector::vector
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1_1createQueuedSubMeshOriginList (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::InstancedGeometry::QueuedSubMeshOriginList 
   
   // parameter conversions 
   
   // create new instance of class Ogre::InstancedGeometry::QueuedSubMeshOriginList 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = new Ogre::InstancedGeometry::QueuedSubMeshOriginList(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* std::vector::vector */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     assign()
 * Type:       non-virtual method
 * Definition: void std::vector::assign
 * Signature:  (ILjava/lang/String;)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1assign_1_1ivsR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint num, 
  jstring val
)
{
   // parameter conversions 
  size_t _cpp_num = num;
  std::string _cpp_val = ""; org::xbig::jni::to_stdstring(_jni_env_, val, _cpp_val); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::QueuedSubMeshOriginList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->assign(_cpp_num, _cpp_val);
} /* void std::vector::assign */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     at()
 * Type:       non-virtual method
 * Definition: T& std::vector::at
 * Signature:  (I)Ljava/lang/String;
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1at_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint loc
)
{
   // parameter conversions 
  size_t _cpp_loc = loc; 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::QueuedSubMeshOriginList*>(_jni_pointer_); 
   
   // call library method 
   std::string& _cpp_result = _cpp_this->at(_cpp_loc) ; 
   return reinterpret_cast<jlong>(&_cpp_result);
} /* T& std::vector::at */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     back()
 * Type:       non-virtual method
 * Definition: T std::vector::back
 * Signature:  ()Ljava/lang/String;
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1back (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::QueuedSubMeshOriginList*>(_jni_pointer_); 
   
   // call library method 
   std::string& _cpp_result = _cpp_this->back() ; 
   return reinterpret_cast<jlong>(&_cpp_result);
} /* T std::vector::back */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     capacity()
 * Type:       non-virtual method
 * Definition: size_t std::vector::capacity
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1capacity (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::QueuedSubMeshOriginList*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->capacity() ; 
   return _cpp_result;
} /* size_t std::vector::capacity */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     clear()
 * Type:       non-virtual method
 * Definition: void std::vector::clear
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1clear (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::QueuedSubMeshOriginList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->clear();
} /* void std::vector::clear */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     empty()
 * Type:       non-virtual method
 * Definition: bool std::vector::empty
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1empty (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::QueuedSubMeshOriginList*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->empty() ; 
   return _cpp_result ? 1 : 0;
} /* bool std::vector::empty */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     front()
 * Type:       non-virtual method
 * Definition: T std::vector::front
 * Signature:  ()Ljava/lang/String;
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1front (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::QueuedSubMeshOriginList*>(_jni_pointer_); 
   
   // call library method 
   std::string& _cpp_result = _cpp_this->front() ; 
   return reinterpret_cast<jlong>(&_cpp_result);
} /* T std::vector::front */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     max_size()
 * Type:       non-virtual method
 * Definition: size_t std::vector::max_size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1max_1size (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::QueuedSubMeshOriginList*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->max_size() ; 
   return _cpp_result;
} /* size_t std::vector::max_size */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     pop_back()
 * Type:       non-virtual method
 * Definition: void std::vector::pop_back
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1pop_1back (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::QueuedSubMeshOriginList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->pop_back();
} /* void std::vector::pop_back */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     push_back()
 * Type:       non-virtual method
 * Definition: void std::vector::push_back
 * Signature:  (Ljava/lang/String;)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1push_1back_1_1sR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring val
)
{
   // parameter conversions 
  std::string _cpp_val = ""; org::xbig::jni::to_stdstring(_jni_env_, val, _cpp_val); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::QueuedSubMeshOriginList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->push_back(_cpp_val);
} /* void std::vector::push_back */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     reserve()
 * Type:       non-virtual method
 * Definition: void std::vector::reserve
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1reserve_1_1iV (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint size
)
{
   // parameter conversions 
  const size_t _cpp_size = size; 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::QueuedSubMeshOriginList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->reserve(_cpp_size);
} /* void std::vector::reserve */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     size()
 * Type:       non-virtual method
 * Definition: size_t std::vector::size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1size (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::QueuedSubMeshOriginList*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->size() ; 
   return _cpp_result;
} /* size_t std::vector::size */


/*
 * Class:      org.ogre4j.InstancedGeometry.00024QueuedSubMeshOriginList
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::InstancedGeometry::QueuedSubMeshOriginList::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_InstancedGeometry_00024QueuedSubMeshOriginList__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::InstancedGeometry::QueuedSubMeshOriginList 
   // cast pointer to C++ object 
   Ogre::InstancedGeometry::QueuedSubMeshOriginList* _cpp_this = reinterpret_cast<Ogre::InstancedGeometry::QueuedSubMeshOriginList*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::InstancedGeometry::QueuedSubMeshOriginList::__delete */
