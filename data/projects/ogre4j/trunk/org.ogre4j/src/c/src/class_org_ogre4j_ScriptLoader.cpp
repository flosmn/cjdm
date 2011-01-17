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
#include "class_org_ogre4j_ScriptLoader.h"

// import header files of original library
#include <OgreScriptLoader.h>



/*
 * Class:      org.ogre4j.ScriptLoader
 * Method:     getScriptPatterns()
 * Type:       pure virtual method
 * Definition: virtual const StringVector& Ogre::ScriptLoader::getScriptPatterns
 * Signature:  ()Ogre_StringVector
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ScriptLoader__1getScriptPatterns_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::ScriptLoader* _cpp_this = reinterpret_cast<const Ogre::ScriptLoader*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::StringVector* _cpp_result = & _cpp_this->getScriptPatterns() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* virtual const StringVector& Ogre::ScriptLoader::getScriptPatterns */


/*
 * Class:      org.ogre4j.ScriptLoader
 * Method:     parseScript()
 * Type:       pure virtual method
 * Definition: virtual void Ogre::ScriptLoader::parseScript
 * Signature:  (Ogre_DataStreamPtrstd_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ScriptLoader__1parseScript_1_1DataStreamPtrrStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong stream, 
  jstring groupName
)
{
   // parameter conversions 
  Ogre::DataStreamPtr* _cpp_stream = reinterpret_cast< Ogre::DataStreamPtr* >(stream);
  std::string _cpp_groupName = ""; org::xbig::jni::to_stdstring(_jni_env_, groupName, _cpp_groupName); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::ScriptLoader* _cpp_this = reinterpret_cast<Ogre::ScriptLoader*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->parseScript(*_cpp_stream, _cpp_groupName);
} /* virtual void Ogre::ScriptLoader::parseScript */


/*
 * Class:      org.ogre4j.ScriptLoader
 * Method:     getLoadingOrder()
 * Type:       pure virtual method
 * Definition: virtual Real Ogre::ScriptLoader::getLoadingOrder
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_ScriptLoader__1getLoadingOrder_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::ScriptLoader* _cpp_this = reinterpret_cast<const Ogre::ScriptLoader*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getLoadingOrder() ; 
   return _cpp_result;
} /* virtual Real Ogre::ScriptLoader::getLoadingOrder */


/*
 * Class:      org.ogre4j.ScriptLoader
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ScriptLoader::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ScriptLoader__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::ScriptLoader 
   // cast pointer to C++ object 
   Ogre::ScriptLoader* _cpp_this = reinterpret_cast<Ogre::ScriptLoader*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::ScriptLoader::__delete */
