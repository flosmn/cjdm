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
#include "class_org_ogre4j_SceneQueryListener.h"

// import header files of original library
#include <OgreSceneQuery.h>



/*
 * Class:      org.ogre4j.SceneQueryListener
 * Method:     queryResult()
 * Type:       pure virtual method
 * Definition: virtual bool Ogre::SceneQueryListener::queryResult
 * Signature:  (Ogre_MovableObject)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_SceneQueryListener__1queryResult_1_1MovableObjectp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong object
)
{
   // parameter conversions 
  Ogre::MovableObject* _cpp_object = reinterpret_cast< Ogre::MovableObject* >(object); 
   
   // cast pointer to C++ object 
   Ogre::SceneQueryListener* _cpp_this = reinterpret_cast<Ogre::SceneQueryListener*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->queryResult(_cpp_object) ; 
   return _cpp_result ? 1 : 0;
} /* virtual bool Ogre::SceneQueryListener::queryResult */


/*
 * Class:      org.ogre4j.SceneQueryListener
 * Method:     queryResult()
 * Type:       pure virtual method
 * Definition: virtual bool Ogre::SceneQueryListener::queryResult
 * Signature:  (Ogre_SceneQuery_WorldFragment)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_SceneQueryListener__1queryResult_1_1SceneQuery_1WorldFragmentp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong fragment
)
{
   // parameter conversions 
  Ogre::SceneQuery::WorldFragment* _cpp_fragment = reinterpret_cast< Ogre::SceneQuery::WorldFragment* >(fragment); 
   
   // cast pointer to C++ object 
   Ogre::SceneQueryListener* _cpp_this = reinterpret_cast<Ogre::SceneQueryListener*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->queryResult(_cpp_fragment) ; 
   return _cpp_result ? 1 : 0;
} /* virtual bool Ogre::SceneQueryListener::queryResult */


/*
 * Class:      org.ogre4j.SceneQueryListener
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::SceneQueryListener::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_SceneQueryListener__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::SceneQueryListener 
   // cast pointer to C++ object 
   Ogre::SceneQueryListener* _cpp_this = reinterpret_cast<Ogre::SceneQueryListener*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::SceneQueryListener::__delete */
