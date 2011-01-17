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
#include "class_org_ogre4j_FrameListener.h"

// import header files of original library
#include <OgreFrameListener.h>



/*
 * Class:      org.ogre4j.FrameListener
 * Method:     frameStarted()
 * Type:       virtual method
 * Definition: virtual bool Ogre::FrameListener::frameStarted
 * Signature:  (Ogre_FrameEvent)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_FrameListener__1frameStarted_1_1FrameEventR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong evt
)
{
   // parameter conversions 
  const Ogre::FrameEvent* _cpp_evt = reinterpret_cast< const Ogre::FrameEvent* >(evt); 
   
   // cast pointer to C++ object 
   Ogre::FrameListener* _cpp_this = reinterpret_cast<Ogre::FrameListener*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->frameStarted(*_cpp_evt) ; 
   return _cpp_result ? 1 : 0;
} /* virtual bool Ogre::FrameListener::frameStarted */


/*
 * Class:      org.ogre4j.FrameListener
 * Method:     frameRenderingQueued()
 * Type:       virtual method
 * Definition: virtual bool Ogre::FrameListener::frameRenderingQueued
 * Signature:  (Ogre_FrameEvent)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_FrameListener__1frameRenderingQueued_1_1FrameEventR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong evt
)
{
   // parameter conversions 
  const Ogre::FrameEvent* _cpp_evt = reinterpret_cast< const Ogre::FrameEvent* >(evt); 
   
   // cast pointer to C++ object 
   Ogre::FrameListener* _cpp_this = reinterpret_cast<Ogre::FrameListener*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->frameRenderingQueued(*_cpp_evt) ; 
   return _cpp_result ? 1 : 0;
} /* virtual bool Ogre::FrameListener::frameRenderingQueued */


/*
 * Class:      org.ogre4j.FrameListener
 * Method:     frameEnded()
 * Type:       virtual method
 * Definition: virtual bool Ogre::FrameListener::frameEnded
 * Signature:  (Ogre_FrameEvent)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_FrameListener__1frameEnded_1_1FrameEventR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong evt
)
{
   // parameter conversions 
  const Ogre::FrameEvent* _cpp_evt = reinterpret_cast< const Ogre::FrameEvent* >(evt); 
   
   // cast pointer to C++ object 
   Ogre::FrameListener* _cpp_this = reinterpret_cast<Ogre::FrameListener*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->frameEnded(*_cpp_evt) ; 
   return _cpp_result ? 1 : 0;
} /* virtual bool Ogre::FrameListener::frameEnded */


/*
 * Class:      org.ogre4j.FrameListener
 * Method:     FrameListener()
 * Type:       constructor
 * Definition: Ogre::FrameListener::FrameListener
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_FrameListener__1_1createFrameListener (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::FrameListener 
   
   // parameter conversions 
   
   // create new instance of class Ogre::FrameListener 
   Ogre::FrameListener* _cpp_this = new Ogre::FrameListener(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::FrameListener::FrameListener */


/*
 * Class:      org.ogre4j.FrameListener
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::FrameListener::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_FrameListener__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::FrameListener 
   // cast pointer to C++ object 
   Ogre::FrameListener* _cpp_this = reinterpret_cast<Ogre::FrameListener*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::FrameListener::__delete */
