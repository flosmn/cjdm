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
#include "class_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList.h"

// import header files of original library
#include <list>
#include <OgreStaticGeometry.h>



/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     OptimisedSubMeshGeometryList()
 * Type:       constructor
 * Definition: std::list::list
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1_1createOptimisedSubMeshGeometryList (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::StaticGeometry::OptimisedSubMeshGeometryList 
   
   // parameter conversions 
   
   // create new instance of class Ogre::StaticGeometry::OptimisedSubMeshGeometryList 
   Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = new Ogre::StaticGeometry::OptimisedSubMeshGeometryList(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* std::list::list */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     assign()
 * Type:       non-virtual method
 * Definition: std::list::assign
 * Signature:  (IOgre_StaticGeometry_OptimisedSubMeshGeometry)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1assign_1_1ivOgre_1StaticGeometry_1OptimisedSubMeshGeometryP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint num, 
  jlong val
)
{
   // parameter conversions 
  size_t _cpp_num = num;
  Ogre::StaticGeometry::OptimisedSubMeshGeometry*const _cpp_val = reinterpret_cast< Ogre::StaticGeometry::OptimisedSubMeshGeometry* const >(val); 
   
   // cast pointer to C++ object 
   Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->assign(_cpp_num, _cpp_val);
} /* std::list::assign */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     back()
 * Type:       non-virtual method
 * Definition: std::list::back
 * Signature:  ()Ogre_StaticGeometry_OptimisedSubMeshGeometry
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1back (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::StaticGeometry::OptimisedSubMeshGeometry* _cpp_result = _cpp_this->back() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* std::list::back */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     clear()
 * Type:       non-virtual method
 * Definition: std::list::clear
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1clear (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->clear();
} /* std::list::clear */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     empty()
 * Type:       non-virtual method
 * Definition: std::list::empty
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1empty_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<const Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->empty() ; 
   return _cpp_result ? 1 : 0;
} /* std::list::empty */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     front()
 * Type:       non-virtual method
 * Definition: std::list::front
 * Signature:  ()Ogre_StaticGeometry_OptimisedSubMeshGeometry
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1front (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::StaticGeometry::OptimisedSubMeshGeometry* _cpp_result = _cpp_this->front() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* std::list::front */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     max_size()
 * Type:       non-virtual method
 * Definition: std::list::max_size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1max_1size_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<const Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->max_size() ; 
   return _cpp_result;
} /* std::list::max_size */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     pop_back()
 * Type:       non-virtual method
 * Definition: std::list::pop_back
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1pop_1back (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->pop_back();
} /* std::list::pop_back */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     pop_front()
 * Type:       non-virtual method
 * Definition: std::list::pop_front
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1pop_1front (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->pop_front();
} /* std::list::pop_front */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     push_back()
 * Type:       non-virtual method
 * Definition: std::list::push_back
 * Signature:  (Ogre_StaticGeometry_OptimisedSubMeshGeometry)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1push_1back_1_1Ogre_1StaticGeometry_1OptimisedSubMeshGeometryP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong val
)
{
   // parameter conversions 
  Ogre::StaticGeometry::OptimisedSubMeshGeometry*const _cpp_val = reinterpret_cast< Ogre::StaticGeometry::OptimisedSubMeshGeometry* const >(val); 
   
   // cast pointer to C++ object 
   Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->push_back(_cpp_val);
} /* std::list::push_back */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     push_front()
 * Type:       non-virtual method
 * Definition: std::list::push_front
 * Signature:  (Ogre_StaticGeometry_OptimisedSubMeshGeometry)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1push_1front_1_1Ogre_1StaticGeometry_1OptimisedSubMeshGeometryP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong val
)
{
   // parameter conversions 
  Ogre::StaticGeometry::OptimisedSubMeshGeometry*const _cpp_val = reinterpret_cast< Ogre::StaticGeometry::OptimisedSubMeshGeometry* const >(val); 
   
   // cast pointer to C++ object 
   Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->push_front(_cpp_val);
} /* std::list::push_front */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     remove()
 * Type:       non-virtual method
 * Definition: std::list::remove
 * Signature:  (Ogre_StaticGeometry_OptimisedSubMeshGeometry)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1remove_1_1Ogre_1StaticGeometry_1OptimisedSubMeshGeometryP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong val
)
{
   // parameter conversions 
  Ogre::StaticGeometry::OptimisedSubMeshGeometry*const _cpp_val = reinterpret_cast< Ogre::StaticGeometry::OptimisedSubMeshGeometry* const >(val); 
   
   // cast pointer to C++ object 
   Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->remove(_cpp_val);
} /* std::list::remove */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     reverse()
 * Type:       non-virtual method
 * Definition: std::list::reverse
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1reverse (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->reverse();
} /* std::list::reverse */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     size()
 * Type:       non-virtual method
 * Definition: std::list::size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1size_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<const Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->size() ; 
   return _cpp_result;
} /* std::list::size */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     unique()
 * Type:       non-virtual method
 * Definition: std::list::unique
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1unique (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->unique();
} /* std::list::unique */


/*
 * Class:      org.ogre4j.StaticGeometry.00024OptimisedSubMeshGeometryList
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::StaticGeometry::OptimisedSubMeshGeometryList::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_StaticGeometry_00024OptimisedSubMeshGeometryList__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::StaticGeometry::OptimisedSubMeshGeometryList 
   // cast pointer to C++ object 
   Ogre::StaticGeometry::OptimisedSubMeshGeometryList* _cpp_this = reinterpret_cast<Ogre::StaticGeometry::OptimisedSubMeshGeometryList*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::StaticGeometry::OptimisedSubMeshGeometryList::__delete */
