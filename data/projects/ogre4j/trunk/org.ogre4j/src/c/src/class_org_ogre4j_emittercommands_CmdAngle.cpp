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
#include "class_org_ogre4j_emittercommands_CmdAngle.h"

// import header files of original library
#include <OgreParticleEmitterCommands.h>



/*
 * Class:      org.ogre4j.emittercommands.CmdAngle
 * Method:     doGet()
 * Type:       virtual method
 * Definition: String Ogre::EmitterCommands::CmdAngle::doGet
 * Signature:  (V)std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_emittercommands_CmdAngle__1doGet_1_1vP_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong target
)
{
   // parameter conversions 
  const void* _cpp_target = reinterpret_cast<void*>(target); 
   
   // cast pointer to C++ object 
   const Ogre::EmitterCommands::CmdAngle* _cpp_this = reinterpret_cast<const Ogre::EmitterCommands::CmdAngle*>(_jni_pointer_); 
   
   // call library method 
   const std::string _cpp_result = _cpp_this->doGet(_cpp_target) ; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* String Ogre::EmitterCommands::CmdAngle::doGet */


/*
 * Class:      org.ogre4j.emittercommands.CmdAngle
 * Method:     doSet()
 * Type:       virtual method
 * Definition: void Ogre::EmitterCommands::CmdAngle::doSet
 * Signature:  (Vstd_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_emittercommands_CmdAngle__1doSet_1_1vpStringR (
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
   Ogre::EmitterCommands::CmdAngle* _cpp_this = reinterpret_cast<Ogre::EmitterCommands::CmdAngle*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->doSet(_cpp_target, _cpp_val);
} /* void Ogre::EmitterCommands::CmdAngle::doSet */


/*
 * Class:      org.ogre4j.emittercommands.CmdAngle
 * Method:     CmdAngle()
 * Type:       constructor
 * Definition: Ogre::EmitterCommands::CmdAngle::CmdAngle
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_emittercommands_CmdAngle__1_1createCmdAngle (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::EmitterCommands::CmdAngle 
   
   // parameter conversions 
   
   // create new instance of class Ogre::EmitterCommands::CmdAngle 
   Ogre::EmitterCommands::CmdAngle* _cpp_this = new Ogre::EmitterCommands::CmdAngle(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::EmitterCommands::CmdAngle::CmdAngle */


/*
 * Class:      org.ogre4j.emittercommands.CmdAngle
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::EmitterCommands::CmdAngle::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_emittercommands_CmdAngle__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::EmitterCommands::CmdAngle 
   // cast pointer to C++ object 
   Ogre::EmitterCommands::CmdAngle* _cpp_this = reinterpret_cast<Ogre::EmitterCommands::CmdAngle*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::EmitterCommands::CmdAngle::__delete */
