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
#include "class_org_ogre4j_RenderTargetMap.h"

// import header files of original library
#include <map>
#include "OgreRenderSystem.h"



/*
 * Class:      org.ogre4j.RenderTargetMap
 * Method:     RenderTargetMap()
 * Type:       constructor
 * Definition: std::map::map
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderTargetMap__1_1createRenderTargetMap (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::RenderTargetMap 
   
   // parameter conversions 
   
   // create new instance of class Ogre::RenderTargetMap 
   Ogre::RenderTargetMap* _cpp_this = new Ogre::RenderTargetMap(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* std::map::map */


/*
 * Class:      org.ogre4j.RenderTargetMap
 * Method:     clear()
 * Type:       non-virtual method
 * Definition: std::map::clear
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTargetMap__1clear (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderTargetMap* _cpp_this = reinterpret_cast<Ogre::RenderTargetMap*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->clear();
} /* std::map::clear */


/*
 * Class:      org.ogre4j.RenderTargetMap
 * Method:     count()
 * Type:       non-virtual method
 * Definition: std::map::count
 * Signature:  (Ljava/lang/String;)I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderTargetMap__1count_1_1sR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring key
)
{
   // parameter conversions 
  std::string _cpp_key = ""; org::xbig::jni::to_stdstring(_jni_env_, key, _cpp_key); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::RenderTargetMap* _cpp_this = reinterpret_cast<Ogre::RenderTargetMap*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->count(_cpp_key) ; 
   return _cpp_result;
} /* std::map::count */


/*
 * Class:      org.ogre4j.RenderTargetMap
 * Method:     empty()
 * Type:       non-virtual method
 * Definition: std::map::empty
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderTargetMap__1empty_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTargetMap* _cpp_this = reinterpret_cast<const Ogre::RenderTargetMap*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->empty() ; 
   return _cpp_result ? 1 : 0;
} /* std::map::empty */


/*
 * Class:      org.ogre4j.RenderTargetMap
 * Method:     erase()
 * Type:       non-virtual method
 * Definition: std::map::erase
 * Signature:  (Ljava/lang/String;)I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderTargetMap__1erase_1_1sR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring key
)
{
   // parameter conversions 
  std::string _cpp_key = ""; org::xbig::jni::to_stdstring(_jni_env_, key, _cpp_key); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::RenderTargetMap* _cpp_this = reinterpret_cast<Ogre::RenderTargetMap*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->erase(_cpp_key) ; 
   return _cpp_result;
} /* std::map::erase */


/*
 * Class:      org.ogre4j.RenderTargetMap
 * Method:     max_size()
 * Type:       non-virtual method
 * Definition: std::map::max_size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderTargetMap__1max_1size_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTargetMap* _cpp_this = reinterpret_cast<const Ogre::RenderTargetMap*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->max_size() ; 
   return _cpp_result;
} /* std::map::max_size */


/*
 * Class:      org.ogre4j.RenderTargetMap
 * Method:     size()
 * Type:       non-virtual method
 * Definition: std::map::size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderTargetMap__1size_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTargetMap* _cpp_this = reinterpret_cast<const Ogre::RenderTargetMap*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->size() ; 
   return _cpp_result;
} /* std::map::size */


/*
 * Class:      org.ogre4j.RenderTargetMap
 * Method:     get()
 * Type:       non-virtual method
 * Definition: std::map::get
 * Signature:  (Ljava/lang/String;)Ogre_RenderTarget
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderTargetMap__1get_1_1sR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring key
)
{
  // parameter conversions 
  std::string _cpp_key = ""; org::xbig::jni::to_stdstring(_jni_env_, key, _cpp_key); // calls c-tor only. Not operator= .; 
   
  // cast pointer to C++ object 
  Ogre::RenderTargetMap* _cpp_this = reinterpret_cast<Ogre::RenderTargetMap*>(_jni_pointer_); 
   
  // call library method 
  const Ogre::RenderTarget* _cpp_result = (*_cpp_this)[_cpp_key]; 
  return reinterpret_cast<jlong>(_cpp_result);
} /* std::map::get */


/*
 * Class:      org.ogre4j.RenderTargetMap
 * Method:     insert()
 * Type:       non-virtual method
 * Definition: std::map::insert
 * Signature:  (Ljava/lang/String;Ogre_RenderTarget)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTargetMap__1insert_1_1sROgre_1RenderTargetp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring key, 
  jlong value
)
{
  // parameter conversions 
  std::string _cpp_key = ""; org::xbig::jni::to_stdstring(_jni_env_, key, _cpp_key); // calls c-tor only. Not operator= .;
  Ogre::RenderTarget* _cpp_value = reinterpret_cast< Ogre::RenderTarget* >(value); 
   
  // cast pointer to C++ object 
  Ogre::RenderTargetMap* _cpp_this = reinterpret_cast<Ogre::RenderTargetMap*>(_jni_pointer_); 
   
  // call library method 
  _cpp_this->insert(std::make_pair(_cpp_key, _cpp_value));
} /* std::map::insert */


/*
 * Class:      org.ogre4j.RenderTargetMap
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::RenderTargetMap::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTargetMap__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::RenderTargetMap 
   // cast pointer to C++ object 
   Ogre::RenderTargetMap* _cpp_this = reinterpret_cast<Ogre::RenderTargetMap*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::RenderTargetMap::__delete */
