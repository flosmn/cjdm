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
#include "class_org_ogre4j_FrameTimeControllerValue.h"

// import header files of original library
#include <OgrePredefinedControllers.h>



/*
 * Class:      org.ogre4j.FrameTimeControllerValue
 * Method:     FrameTimeControllerValue()
 * Type:       constructor
 * Definition: Ogre::FrameTimeControllerValue::FrameTimeControllerValue
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_FrameTimeControllerValue__1_1createFrameTimeControllerValue (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::FrameTimeControllerValue 
   
   // parameter conversions 
   
   // create new instance of class Ogre::FrameTimeControllerValue 
   Ogre::FrameTimeControllerValue* _cpp_this = new Ogre::FrameTimeControllerValue(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::FrameTimeControllerValue::FrameTimeControllerValue */


/*
 * Class:      org.ogre4j.FrameTimeControllerValue
 * Method:     frameEnded()
 * Type:       virtual method
 * Definition: bool Ogre::FrameTimeControllerValue::frameEnded
 * Signature:  (Ogre_FrameEvent)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_FrameTimeControllerValue__1frameEnded_1_1FrameEventR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong evt
)
{
   // parameter conversions 
  const Ogre::FrameEvent* _cpp_evt = reinterpret_cast< const Ogre::FrameEvent* >(evt); 
   
   // cast pointer to C++ object 
   Ogre::FrameTimeControllerValue* _cpp_this = reinterpret_cast<Ogre::FrameTimeControllerValue*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->frameEnded(*_cpp_evt) ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::FrameTimeControllerValue::frameEnded */


/*
 * Class:      org.ogre4j.FrameTimeControllerValue
 * Method:     frameStarted()
 * Type:       virtual method
 * Definition: bool Ogre::FrameTimeControllerValue::frameStarted
 * Signature:  (Ogre_FrameEvent)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_FrameTimeControllerValue__1frameStarted_1_1FrameEventR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong evt
)
{
   // parameter conversions 
  const Ogre::FrameEvent* _cpp_evt = reinterpret_cast< const Ogre::FrameEvent* >(evt); 
   
   // cast pointer to C++ object 
   Ogre::FrameTimeControllerValue* _cpp_this = reinterpret_cast<Ogre::FrameTimeControllerValue*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->frameStarted(*_cpp_evt) ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::FrameTimeControllerValue::frameStarted */


/*
 * Class:      org.ogre4j.FrameTimeControllerValue
 * Method:     getValue()
 * Type:       virtual method
 * Definition: Real Ogre::FrameTimeControllerValue::getValue
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_FrameTimeControllerValue__1getValue_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::FrameTimeControllerValue* _cpp_this = reinterpret_cast<const Ogre::FrameTimeControllerValue*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getValue() ; 
   return _cpp_result;
} /* Real Ogre::FrameTimeControllerValue::getValue */


/*
 * Class:      org.ogre4j.FrameTimeControllerValue
 * Method:     setValue()
 * Type:       virtual method
 * Definition: void Ogre::FrameTimeControllerValue::setValue
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_FrameTimeControllerValue__1setValue_1_1Realv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat value
)
{
   // parameter conversions 
  float _cpp_value = value; 
   
   // cast pointer to C++ object 
   Ogre::FrameTimeControllerValue* _cpp_this = reinterpret_cast<Ogre::FrameTimeControllerValue*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setValue(_cpp_value);
} /* void Ogre::FrameTimeControllerValue::setValue */


/*
 * Class:      org.ogre4j.FrameTimeControllerValue
 * Method:     getTimeFactor()
 * Type:       non-virtual method
 * Definition: Real Ogre::FrameTimeControllerValue::getTimeFactor
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_FrameTimeControllerValue__1getTimeFactor_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::FrameTimeControllerValue* _cpp_this = reinterpret_cast<const Ogre::FrameTimeControllerValue*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getTimeFactor() ; 
   return _cpp_result;
} /* Real Ogre::FrameTimeControllerValue::getTimeFactor */


/*
 * Class:      org.ogre4j.FrameTimeControllerValue
 * Method:     setTimeFactor()
 * Type:       non-virtual method
 * Definition: void Ogre::FrameTimeControllerValue::setTimeFactor
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_FrameTimeControllerValue__1setTimeFactor_1_1Realv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat tf
)
{
   // parameter conversions 
  float _cpp_tf = tf; 
   
   // cast pointer to C++ object 
   Ogre::FrameTimeControllerValue* _cpp_this = reinterpret_cast<Ogre::FrameTimeControllerValue*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setTimeFactor(_cpp_tf);
} /* void Ogre::FrameTimeControllerValue::setTimeFactor */


/*
 * Class:      org.ogre4j.FrameTimeControllerValue
 * Method:     getFrameDelay()
 * Type:       non-virtual method
 * Definition: Real Ogre::FrameTimeControllerValue::getFrameDelay
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_FrameTimeControllerValue__1getFrameDelay_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::FrameTimeControllerValue* _cpp_this = reinterpret_cast<const Ogre::FrameTimeControllerValue*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getFrameDelay() ; 
   return _cpp_result;
} /* Real Ogre::FrameTimeControllerValue::getFrameDelay */


/*
 * Class:      org.ogre4j.FrameTimeControllerValue
 * Method:     setFrameDelay()
 * Type:       non-virtual method
 * Definition: void Ogre::FrameTimeControllerValue::setFrameDelay
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_FrameTimeControllerValue__1setFrameDelay_1_1Realv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat fd
)
{
   // parameter conversions 
  float _cpp_fd = fd; 
   
   // cast pointer to C++ object 
   Ogre::FrameTimeControllerValue* _cpp_this = reinterpret_cast<Ogre::FrameTimeControllerValue*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setFrameDelay(_cpp_fd);
} /* void Ogre::FrameTimeControllerValue::setFrameDelay */


/*
 * Class:      org.ogre4j.FrameTimeControllerValue
 * Method:     getElapsedTime()
 * Type:       non-virtual method
 * Definition: Real Ogre::FrameTimeControllerValue::getElapsedTime
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_FrameTimeControllerValue__1getElapsedTime_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::FrameTimeControllerValue* _cpp_this = reinterpret_cast<const Ogre::FrameTimeControllerValue*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getElapsedTime() ; 
   return _cpp_result;
} /* Real Ogre::FrameTimeControllerValue::getElapsedTime */


/*
 * Class:      org.ogre4j.FrameTimeControllerValue
 * Method:     setElapsedTime()
 * Type:       non-virtual method
 * Definition: void Ogre::FrameTimeControllerValue::setElapsedTime
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_FrameTimeControllerValue__1setElapsedTime_1_1Realv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat elapsedTime
)
{
   // parameter conversions 
  float _cpp_elapsedTime = elapsedTime; 
   
   // cast pointer to C++ object 
   Ogre::FrameTimeControllerValue* _cpp_this = reinterpret_cast<Ogre::FrameTimeControllerValue*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setElapsedTime(_cpp_elapsedTime);
} /* void Ogre::FrameTimeControllerValue::setElapsedTime */


/*
 * Class:      org.ogre4j.FrameTimeControllerValue
 * Method:     frameRenderingQueued()
 * Type:       virtual method
 * Definition: virtual bool Ogre::FrameListener::frameRenderingQueued
 * Signature:  (Ogre_FrameEvent)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_FrameTimeControllerValue__1frameRenderingQueued_1_1FrameEventR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong evt
)
{
   // parameter conversions 
  const Ogre::FrameEvent* _cpp_evt = reinterpret_cast< const Ogre::FrameEvent* >(evt); 
   
   // cast pointer to C++ object 
   Ogre::FrameTimeControllerValue* _cpp_this = reinterpret_cast<Ogre::FrameTimeControllerValue*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->frameRenderingQueued(*_cpp_evt) ; 
   return _cpp_result ? 1 : 0;
} /* virtual bool Ogre::FrameListener::frameRenderingQueued */


/*
 * Class:      org.ogre4j.FrameTimeControllerValue
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::FrameTimeControllerValue::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_FrameTimeControllerValue__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::FrameTimeControllerValue 
   // cast pointer to C++ object 
   Ogre::FrameTimeControllerValue* _cpp_this = reinterpret_cast<Ogre::FrameTimeControllerValue*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::FrameTimeControllerValue::__delete */
