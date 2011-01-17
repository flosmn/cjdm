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
#include "class_org_ogre4j_ParamCommand.h"

// import header files of original library
#include <OgreStringInterface.h>



/*
 * Class:      org.ogre4j.ParamCommand
 * Method:     doGet()
 * Type:       pure virtual method
 * Definition: virtual String Ogre::ParamCommand::doGet
 * Signature:  (V)std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ParamCommand__1doGet_1_1vP_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong target
)
{
   // parameter conversions 
  const void* _cpp_target = reinterpret_cast<void*>(target); 
   
   // cast pointer to C++ object 
   const Ogre::ParamCommand* _cpp_this = reinterpret_cast<const Ogre::ParamCommand*>(_jni_pointer_); 
   
   // call library method 
   const std::string _cpp_result = _cpp_this->doGet(_cpp_target) ; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* virtual String Ogre::ParamCommand::doGet */


/*
 * Class:      org.ogre4j.ParamCommand
 * Method:     doSet()
 * Type:       pure virtual method
 * Definition: virtual void Ogre::ParamCommand::doSet
 * Signature:  (Vstd_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ParamCommand__1doSet_1_1vpStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong target, 
  jstring val
)
{
   // parameter conversions 
  void* _cpp_target = reinterpret_cast<void*>(target);
  std::string _cpp_val = ""; org::xbig::jni::to_stdstring(_jni_env_, val, _cpp_val); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::ParamCommand* _cpp_this = reinterpret_cast<Ogre::ParamCommand*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->doSet(_cpp_target, _cpp_val);
} /* virtual void Ogre::ParamCommand::doSet */


/*
 * Class:      org.ogre4j.ParamCommand
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ParamCommand::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ParamCommand__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::ParamCommand 
   // cast pointer to C++ object 
   Ogre::ParamCommand* _cpp_this = reinterpret_cast<Ogre::ParamCommand*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::ParamCommand::__delete */
