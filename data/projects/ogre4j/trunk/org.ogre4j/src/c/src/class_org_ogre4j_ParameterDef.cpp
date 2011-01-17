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
#include "class_org_ogre4j_ParameterDef.h"

// import header files of original library
#include <OgreStringInterface.h>



/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     ParameterDef()
 * Type:       constructor
 * Definition: Ogre::ParameterDef::ParameterDef
 * Signature:  (std_stringstd_stringOgre_ParameterType)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ParameterDef__1_1createParameterDef_1_1StringRStringRParameterTypev (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jstring newName, 
  jstring newDescription, 
  jint newType
)
{
   // constructor of class Ogre::ParameterDef 
   
   // parameter conversions 
  std::string _cpp_newName = ""; org::xbig::jni::to_stdstring(_jni_env_, newName, _cpp_newName); // calls c-tor only. Not operator= .;
  std::string _cpp_newDescription = ""; org::xbig::jni::to_stdstring(_jni_env_, newDescription, _cpp_newDescription); // calls c-tor only. Not operator= .;
  Ogre::ParameterType _cpp_newType = (Ogre::ParameterType)newType; 
   
   // create new instance of class Ogre::ParameterDef 
   Ogre::ParameterDef* _cpp_this = new Ogre::ParameterDef(_cpp_newName, _cpp_newDescription, _cpp_newType); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::ParameterDef::ParameterDef */


/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ParameterDef::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ParameterDef__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::ParameterDef 
   // cast pointer to C++ object 
   Ogre::ParameterDef* _cpp_this = reinterpret_cast<Ogre::ParameterDef*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::ParameterDef::__delete */


/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     getname()
 * Type:       getter for public attribute
 * Definition: String Ogre::ParameterDef::name
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ParameterDef__1getname (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ParameterDef* _cpp_this = reinterpret_cast<Ogre::ParameterDef*>(_jni_pointer_); 
   
   // call library method 
   const std::string _cpp_result = _cpp_this->name; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* String Ogre::ParameterDef::name */


/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     setname()
 * Type:       setter for public attribute
 * Definition: String Ogre::ParameterDef::name
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ParameterDef__1setname (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
)
{
   // parameter conversions 
  std::string _cpp__jni_value_ = ""; org::xbig::jni::to_stdstring(_jni_env_, _jni_value_, _cpp__jni_value_); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::ParameterDef* _cpp_this =reinterpret_cast<Ogre::ParameterDef*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->name = _cpp__jni_value_;
} /* String Ogre::ParameterDef::name */


/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     getdescription()
 * Type:       getter for public attribute
 * Definition: String Ogre::ParameterDef::description
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ParameterDef__1getdescription (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ParameterDef* _cpp_this = reinterpret_cast<Ogre::ParameterDef*>(_jni_pointer_); 
   
   // call library method 
   const std::string _cpp_result = _cpp_this->description; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* String Ogre::ParameterDef::description */


/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     setdescription()
 * Type:       setter for public attribute
 * Definition: String Ogre::ParameterDef::description
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ParameterDef__1setdescription (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
)
{
   // parameter conversions 
  std::string _cpp__jni_value_ = ""; org::xbig::jni::to_stdstring(_jni_env_, _jni_value_, _cpp__jni_value_); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::ParameterDef* _cpp_this =reinterpret_cast<Ogre::ParameterDef*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->description = _cpp__jni_value_;
} /* String Ogre::ParameterDef::description */


/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     getparamType()
 * Type:       getter for public attribute
 * Definition: ParameterType Ogre::ParameterDef::paramType
 * Signature:  ()Ogre_ParameterType
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_ParameterDef__1getparamType (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ParameterDef* _cpp_this = reinterpret_cast<Ogre::ParameterDef*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::ParameterType _cpp_result = _cpp_this->paramType; 
   return _cpp_result;
} /* ParameterType Ogre::ParameterDef::paramType */


/*
 * Class:      org.ogre4j.ParameterDef
 * Method:     setparamType()
 * Type:       setter for public attribute
 * Definition: ParameterType Ogre::ParameterDef::paramType
 * Signature:  (Ogre_ParameterType)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ParameterDef__1setparamType (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
)
{
   // parameter conversions 
  Ogre::ParameterType _cpp__jni_value_ = (Ogre::ParameterType)_jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::ParameterDef* _cpp_this =reinterpret_cast<Ogre::ParameterDef*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->paramType = _cpp__jni_value_;
} /* ParameterType Ogre::ParameterDef::paramType */
