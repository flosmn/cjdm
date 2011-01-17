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
#include "class_org_ogre4j_TangentSpaceCalc_00024VertexSplit.h"

// import header files of original library
#include <map>
#include <OgreTangentSpaceCalc.h>



/*
 * Class:      org.ogre4j.TangentSpaceCalc.00024VertexSplit
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::TangentSpaceCalc::VertexSplit::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_TangentSpaceCalc_00024VertexSplit__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::TangentSpaceCalc::VertexSplit 
   // cast pointer to C++ object 
   Ogre::TangentSpaceCalc::VertexSplit* _cpp_this = reinterpret_cast<Ogre::TangentSpaceCalc::VertexSplit*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::TangentSpaceCalc::VertexSplit::__delete */


/*
 * Class:      org.ogre4j.TangentSpaceCalc.00024VertexSplit
 * Method:     getfirst()
 * Type:       getter for public attribute
 * Definition: F std::pair::first
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_TangentSpaceCalc_00024VertexSplit__1getfirst (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::TangentSpaceCalc::VertexSplit* _cpp_this = reinterpret_cast<Ogre::TangentSpaceCalc::VertexSplit*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->first; 
   return _cpp_result;
} /* F std::pair::first */


/*
 * Class:      org.ogre4j.TangentSpaceCalc.00024VertexSplit
 * Method:     setfirst()
 * Type:       setter for public attribute
 * Definition: F std::pair::first
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_TangentSpaceCalc_00024VertexSplit__1setfirst (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
)
{
   // parameter conversions 
  size_t _cpp__jni_value_ = _jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::TangentSpaceCalc::VertexSplit* _cpp_this =reinterpret_cast<Ogre::TangentSpaceCalc::VertexSplit*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->first = _cpp__jni_value_;
} /* F std::pair::first */


/*
 * Class:      org.ogre4j.TangentSpaceCalc.00024VertexSplit
 * Method:     getsecond()
 * Type:       getter for public attribute
 * Definition: S std::pair::second
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_TangentSpaceCalc_00024VertexSplit__1getsecond (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::TangentSpaceCalc::VertexSplit* _cpp_this = reinterpret_cast<Ogre::TangentSpaceCalc::VertexSplit*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->second; 
   return _cpp_result;
} /* S std::pair::second */


/*
 * Class:      org.ogre4j.TangentSpaceCalc.00024VertexSplit
 * Method:     setsecond()
 * Type:       setter for public attribute
 * Definition: S std::pair::second
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_TangentSpaceCalc_00024VertexSplit__1setsecond (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
)
{
   // parameter conversions 
  size_t _cpp__jni_value_ = _jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::TangentSpaceCalc::VertexSplit* _cpp_this =reinterpret_cast<Ogre::TangentSpaceCalc::VertexSplit*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->second = _cpp__jni_value_;
} /* S std::pair::second */
