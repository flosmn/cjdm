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
#include "class_org_ogre4j_AnimationControllerFunction.h"

// import header files of original library
#include <OgrePredefinedControllers.h>



/*
 * Class:      org.ogre4j.AnimationControllerFunction
 * Method:     AnimationControllerFunction()
 * Type:       constructor
 * Definition: Ogre::AnimationControllerFunction::AnimationControllerFunction
 * Signature:  (floatfloat)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_AnimationControllerFunction__1_1createAnimationControllerFunction_1_1RealvRealv (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jfloat sequenceTime, 
  jfloat timeOffset
)
{
   // constructor of class Ogre::AnimationControllerFunction 
   
   // parameter conversions 
  float _cpp_sequenceTime = sequenceTime;
  float _cpp_timeOffset = timeOffset; 
   
   // create new instance of class Ogre::AnimationControllerFunction 
   Ogre::AnimationControllerFunction* _cpp_this = new Ogre::AnimationControllerFunction(_cpp_sequenceTime, _cpp_timeOffset); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::AnimationControllerFunction::AnimationControllerFunction */


/*
 * Class:      org.ogre4j.AnimationControllerFunction
 * Method:     calculate()
 * Type:       virtual method
 * Definition: Real Ogre::AnimationControllerFunction::calculate
 * Signature:  (float)float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_AnimationControllerFunction__1calculate_1_1Realv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat source
)
{
   // parameter conversions 
  float _cpp_source = source; 
   
   // cast pointer to C++ object 
   Ogre::AnimationControllerFunction* _cpp_this = reinterpret_cast<Ogre::AnimationControllerFunction*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->calculate(_cpp_source) ; 
   return _cpp_result;
} /* Real Ogre::AnimationControllerFunction::calculate */


/*
 * Class:      org.ogre4j.AnimationControllerFunction
 * Method:     setTime()
 * Type:       non-virtual method
 * Definition: void Ogre::AnimationControllerFunction::setTime
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_AnimationControllerFunction__1setTime_1_1Realv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat timeVal
)
{
   // parameter conversions 
  float _cpp_timeVal = timeVal; 
   
   // cast pointer to C++ object 
   Ogre::AnimationControllerFunction* _cpp_this = reinterpret_cast<Ogre::AnimationControllerFunction*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setTime(_cpp_timeVal);
} /* void Ogre::AnimationControllerFunction::setTime */


/*
 * Class:      org.ogre4j.AnimationControllerFunction
 * Method:     setSequenceTime()
 * Type:       non-virtual method
 * Definition: void Ogre::AnimationControllerFunction::setSequenceTime
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_AnimationControllerFunction__1setSequenceTime_1_1Realv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat seqVal
)
{
   // parameter conversions 
  float _cpp_seqVal = seqVal; 
   
   // cast pointer to C++ object 
   Ogre::AnimationControllerFunction* _cpp_this = reinterpret_cast<Ogre::AnimationControllerFunction*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setSequenceTime(_cpp_seqVal);
} /* void Ogre::AnimationControllerFunction::setSequenceTime */


/*
 * Class:      org.ogre4j.AnimationControllerFunction
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::AnimationControllerFunction::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_AnimationControllerFunction__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::AnimationControllerFunction 
   // cast pointer to C++ object 
   Ogre::AnimationControllerFunction* _cpp_this = reinterpret_cast<Ogre::AnimationControllerFunction*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::AnimationControllerFunction::__delete */
