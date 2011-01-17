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
#include "class_org_ogre4j_SceneManager_00024lightLess.h"

// import header files of original library
#include <OgreSceneManager.h>



/*
 * Class:      org.ogre4j.SceneManager.00024lightLess
 * Method:     operator()()
 * Type:       non-virtual method
 * Definition: bool Ogre::SceneManager::lightLess::operator()
 * Signature:  (Ogre_LightOgre_Light)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_SceneManager_00024lightLess__1operatorFunctionCall_1_1LightPLightP_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong a, 
  jlong b
)
{
   // parameter conversions 
  const Ogre::Light* _cpp_a = reinterpret_cast< const Ogre::Light* >(a);
  const Ogre::Light* _cpp_b = reinterpret_cast< const Ogre::Light* >(b); 
   
   // cast pointer to C++ object 
   const Ogre::SceneManager::lightLess* _cpp_this = reinterpret_cast<const Ogre::SceneManager::lightLess*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->operator()(_cpp_a, _cpp_b) ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::SceneManager::lightLess::operator() */


/*
 * Class:      org.ogre4j.SceneManager.00024lightLess
 * Method:     lightLess()
 * Type:       constructor
 * Definition: Ogre::SceneManager::lightLess::lightLess
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_SceneManager_00024lightLess__1_1createlightLess (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::SceneManager::lightLess 
   
   // parameter conversions 
   
   // create new instance of class Ogre::SceneManager::lightLess 
   Ogre::SceneManager::lightLess* _cpp_this = new Ogre::SceneManager::lightLess(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::SceneManager::lightLess::lightLess */


/*
 * Class:      org.ogre4j.SceneManager.00024lightLess
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::SceneManager::lightLess::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneManager_00024lightLess__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::SceneManager::lightLess 
   // cast pointer to C++ object 
   Ogre::SceneManager::lightLess* _cpp_this = reinterpret_cast<Ogre::SceneManager::lightLess*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::SceneManager::lightLess::__delete */
