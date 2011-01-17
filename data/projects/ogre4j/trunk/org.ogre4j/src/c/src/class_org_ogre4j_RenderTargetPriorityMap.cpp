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
#include "class_org_ogre4j_RenderTargetPriorityMap.h"

// import header files of original library
#include <map>
#include "OgreRenderSystem.h"



/*
 * Class:      org.ogre4j.RenderTargetPriorityMap
 * Method:     RenderTargetPriorityMap()
 * Type:       constructor
 * Definition: std::multimap::multimap
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderTargetPriorityMap__1_1createRenderTargetPriorityMap (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::RenderTargetPriorityMap 
   
   // parameter conversions 
   
   // create new instance of class Ogre::RenderTargetPriorityMap 
   Ogre::RenderTargetPriorityMap* _cpp_this = new Ogre::RenderTargetPriorityMap(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* std::multimap::multimap */


/*
 * Class:      org.ogre4j.RenderTargetPriorityMap
 * Method:     clear()
 * Type:       non-virtual method
 * Definition: std::multimap::clear
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTargetPriorityMap__1clear (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderTargetPriorityMap* _cpp_this = reinterpret_cast<Ogre::RenderTargetPriorityMap*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->clear();
} /* std::multimap::clear */


/*
 * Class:      org.ogre4j.RenderTargetPriorityMap
 * Method:     count()
 * Type:       non-virtual method
 * Definition: std::multimap::count
 * Signature:  (C)I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderTargetPriorityMap__1count_1_1BR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong key
)
{
   // parameter conversions 
  const unsigned char _cpp_key = key; 
   
   // cast pointer to C++ object 
   Ogre::RenderTargetPriorityMap* _cpp_this = reinterpret_cast<Ogre::RenderTargetPriorityMap*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->count(_cpp_key) ; 
   return _cpp_result;
} /* std::multimap::count */


/*
 * Class:      org.ogre4j.RenderTargetPriorityMap
 * Method:     empty()
 * Type:       non-virtual method
 * Definition: std::multimap::empty
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderTargetPriorityMap__1empty_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTargetPriorityMap* _cpp_this = reinterpret_cast<const Ogre::RenderTargetPriorityMap*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->empty() ; 
   return _cpp_result ? 1 : 0;
} /* std::multimap::empty */


/*
 * Class:      org.ogre4j.RenderTargetPriorityMap
 * Method:     erase()
 * Type:       non-virtual method
 * Definition: std::multimap::erase
 * Signature:  (C)I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderTargetPriorityMap__1erase_1_1BR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong key
)
{
   // parameter conversions 
  const unsigned char _cpp_key = key; 
   
   // cast pointer to C++ object 
   Ogre::RenderTargetPriorityMap* _cpp_this = reinterpret_cast<Ogre::RenderTargetPriorityMap*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->erase(_cpp_key) ; 
   return _cpp_result;
} /* std::multimap::erase */


/*
 * Class:      org.ogre4j.RenderTargetPriorityMap
 * Method:     max_size()
 * Type:       non-virtual method
 * Definition: std::multimap::max_size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderTargetPriorityMap__1max_1size_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTargetPriorityMap* _cpp_this = reinterpret_cast<const Ogre::RenderTargetPriorityMap*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->max_size() ; 
   return _cpp_result;
} /* std::multimap::max_size */


/*
 * Class:      org.ogre4j.RenderTargetPriorityMap
 * Method:     size()
 * Type:       non-virtual method
 * Definition: std::multimap::size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderTargetPriorityMap__1size_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTargetPriorityMap* _cpp_this = reinterpret_cast<const Ogre::RenderTargetPriorityMap*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->size() ; 
   return _cpp_result;
} /* std::multimap::size */


/*
 * Class:      org.ogre4j.RenderTargetPriorityMap
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::RenderTargetPriorityMap::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTargetPriorityMap__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::RenderTargetPriorityMap 
   // cast pointer to C++ object 
   Ogre::RenderTargetPriorityMap* _cpp_this = reinterpret_cast<Ogre::RenderTargetPriorityMap*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::RenderTargetPriorityMap::__delete */
