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
#include "class_org_ogre4j_CompositorInstance_00024TargetOperation.h"

// import header files of original library
#include <OgreCompositorInstance.h>



/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     TargetOperation()
 * Type:       constructor
 * Definition: Ogre::CompositorInstance::TargetOperation::TargetOperation
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1_1createTargetOperation (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::CompositorInstance::TargetOperation 
   
   // parameter conversions 
   
   // create new instance of class Ogre::CompositorInstance::TargetOperation 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = new Ogre::CompositorInstance::TargetOperation(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::CompositorInstance::TargetOperation::TargetOperation */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     TargetOperation()
 * Type:       constructor
 * Definition: Ogre::CompositorInstance::TargetOperation::TargetOperation
 * Signature:  (Ogre_RenderTarget)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1_1createTargetOperation_1_1RenderTargetp (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong target
)
{
   // constructor of class Ogre::CompositorInstance::TargetOperation 
   
   // parameter conversions 
  Ogre::RenderTarget* _cpp_target = reinterpret_cast< Ogre::RenderTarget* >(target); 
   
   // create new instance of class Ogre::CompositorInstance::TargetOperation 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = new Ogre::CompositorInstance::TargetOperation(_cpp_target); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::CompositorInstance::TargetOperation::TargetOperation */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::CompositorInstance::TargetOperation::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::CompositorInstance::TargetOperation 
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::CompositorInstance::TargetOperation::__delete */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     gettarget()
 * Type:       getter for public attribute
 * Definition: RenderTarget* Ogre::CompositorInstance::TargetOperation::target
 * Signature:  ()Ogre_RenderTarget
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1gettarget (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::RenderTarget* _cpp_result = _cpp_this->target; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* RenderTarget* Ogre::CompositorInstance::TargetOperation::target */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     settarget()
 * Type:       setter for public attribute
 * Definition: RenderTarget* Ogre::CompositorInstance::TargetOperation::target
 * Signature:  (Ogre_RenderTarget)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1settarget (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
)
{
   // parameter conversions 
  Ogre::RenderTarget* _cpp__jni_value_ = reinterpret_cast< Ogre::RenderTarget* >(_jni_value_); 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this =reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->target = _cpp__jni_value_;
} /* RenderTarget* Ogre::CompositorInstance::TargetOperation::target */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     getcurrentQueueGroupID()
 * Type:       getter for public attribute
 * Definition: int Ogre::CompositorInstance::TargetOperation::currentQueueGroupID
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1getcurrentQueueGroupID (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   const int _cpp_result = _cpp_this->currentQueueGroupID; 
   return _cpp_result;
} /* int Ogre::CompositorInstance::TargetOperation::currentQueueGroupID */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     setcurrentQueueGroupID()
 * Type:       setter for public attribute
 * Definition: int Ogre::CompositorInstance::TargetOperation::currentQueueGroupID
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1setcurrentQueueGroupID (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
)
{
   // parameter conversions 
  int _cpp__jni_value_ = _jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this =reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->currentQueueGroupID = _cpp__jni_value_;
} /* int Ogre::CompositorInstance::TargetOperation::currentQueueGroupID */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     getrenderSystemOperations()
 * Type:       getter for public attribute
 * Definition: RenderSystemOpPairs Ogre::CompositorInstance::TargetOperation::renderSystemOperations
 * Signature:  ()Ogre_CompositorInstance_RenderSystemOpPairs
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1getrenderSystemOperations (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::CompositorInstance::RenderSystemOpPairs* _cpp_result = new Ogre::CompositorInstance::RenderSystemOpPairs( _cpp_this->renderSystemOperations); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* RenderSystemOpPairs Ogre::CompositorInstance::TargetOperation::renderSystemOperations */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     setrenderSystemOperations()
 * Type:       setter for public attribute
 * Definition: RenderSystemOpPairs Ogre::CompositorInstance::TargetOperation::renderSystemOperations
 * Signature:  (Ogre_CompositorInstance_RenderSystemOpPairs)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1setrenderSystemOperations (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
)
{
   // parameter conversions 
  Ogre::CompositorInstance::RenderSystemOpPairs* _cpp__jni_value_ = reinterpret_cast< Ogre::CompositorInstance::RenderSystemOpPairs* >(_jni_value_); 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this =reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->renderSystemOperations = *_cpp__jni_value_;
} /* RenderSystemOpPairs Ogre::CompositorInstance::TargetOperation::renderSystemOperations */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     getvisibilityMask()
 * Type:       getter for public attribute
 * Definition: uint32 Ogre::CompositorInstance::TargetOperation::visibilityMask
 * Signature:  ()unsigned_int
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1getvisibilityMask (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   const unsigned int _cpp_result = _cpp_this->visibilityMask; 
   return _cpp_result;
} /* uint32 Ogre::CompositorInstance::TargetOperation::visibilityMask */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     setvisibilityMask()
 * Type:       setter for public attribute
 * Definition: uint32 Ogre::CompositorInstance::TargetOperation::visibilityMask
 * Signature:  (unsigned_int)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1setvisibilityMask (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
)
{
   // parameter conversions 
  unsigned int _cpp__jni_value_ = _jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this =reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->visibilityMask = _cpp__jni_value_;
} /* uint32 Ogre::CompositorInstance::TargetOperation::visibilityMask */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     getlodBias()
 * Type:       getter for public attribute
 * Definition: float Ogre::CompositorInstance::TargetOperation::lodBias
 * Signature:  ()F
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1getlodBias (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->lodBias; 
   return _cpp_result;
} /* float Ogre::CompositorInstance::TargetOperation::lodBias */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     setlodBias()
 * Type:       setter for public attribute
 * Definition: float Ogre::CompositorInstance::TargetOperation::lodBias
 * Signature:  (F)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1setlodBias (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat _jni_value_
)
{
   // parameter conversions 
  float _cpp__jni_value_ = _jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this =reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->lodBias = _cpp__jni_value_;
} /* float Ogre::CompositorInstance::TargetOperation::lodBias */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     getrenderQueues()
 * Type:       getter for public attribute
 * Definition: RenderQueueBitSet Ogre::CompositorInstance::TargetOperation::renderQueues
 * Signature:  ()Ogre_CompositorInstance_TargetOperation_RenderQueueBitSet
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1getrenderQueues (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::CompositorInstance::TargetOperation::RenderQueueBitSet* _cpp_result = new Ogre::CompositorInstance::TargetOperation::RenderQueueBitSet( _cpp_this->renderQueues); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* RenderQueueBitSet Ogre::CompositorInstance::TargetOperation::renderQueues */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     setrenderQueues()
 * Type:       setter for public attribute
 * Definition: RenderQueueBitSet Ogre::CompositorInstance::TargetOperation::renderQueues
 * Signature:  (Ogre_CompositorInstance_TargetOperation_RenderQueueBitSet)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1setrenderQueues (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
)
{
   // parameter conversions 
  Ogre::CompositorInstance::TargetOperation::RenderQueueBitSet* _cpp__jni_value_ = reinterpret_cast< Ogre::CompositorInstance::TargetOperation::RenderQueueBitSet* >(_jni_value_); 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this =reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->renderQueues = *_cpp__jni_value_;
} /* RenderQueueBitSet Ogre::CompositorInstance::TargetOperation::renderQueues */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     getonlyInitial()
 * Type:       getter for public attribute
 * Definition: bool Ogre::CompositorInstance::TargetOperation::onlyInitial
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1getonlyInitial (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->onlyInitial; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::CompositorInstance::TargetOperation::onlyInitial */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     setonlyInitial()
 * Type:       setter for public attribute
 * Definition: bool Ogre::CompositorInstance::TargetOperation::onlyInitial
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1setonlyInitial (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean _jni_value_
)
{
   // parameter conversions 
  bool _cpp__jni_value_ = _jni_value_ ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this =reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->onlyInitial = _cpp__jni_value_;
} /* bool Ogre::CompositorInstance::TargetOperation::onlyInitial */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     gethasBeenRendered()
 * Type:       getter for public attribute
 * Definition: bool Ogre::CompositorInstance::TargetOperation::hasBeenRendered
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1gethasBeenRendered (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->hasBeenRendered; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::CompositorInstance::TargetOperation::hasBeenRendered */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     sethasBeenRendered()
 * Type:       setter for public attribute
 * Definition: bool Ogre::CompositorInstance::TargetOperation::hasBeenRendered
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1sethasBeenRendered (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean _jni_value_
)
{
   // parameter conversions 
  bool _cpp__jni_value_ = _jni_value_ ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this =reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->hasBeenRendered = _cpp__jni_value_;
} /* bool Ogre::CompositorInstance::TargetOperation::hasBeenRendered */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     getfindVisibleObjects()
 * Type:       getter for public attribute
 * Definition: bool Ogre::CompositorInstance::TargetOperation::findVisibleObjects
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1getfindVisibleObjects (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->findVisibleObjects; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::CompositorInstance::TargetOperation::findVisibleObjects */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     setfindVisibleObjects()
 * Type:       setter for public attribute
 * Definition: bool Ogre::CompositorInstance::TargetOperation::findVisibleObjects
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1setfindVisibleObjects (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean _jni_value_
)
{
   // parameter conversions 
  bool _cpp__jni_value_ = _jni_value_ ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this =reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->findVisibleObjects = _cpp__jni_value_;
} /* bool Ogre::CompositorInstance::TargetOperation::findVisibleObjects */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     getmaterialScheme()
 * Type:       getter for public attribute
 * Definition: String Ogre::CompositorInstance::TargetOperation::materialScheme
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1getmaterialScheme (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   const std::string _cpp_result = _cpp_this->materialScheme; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* String Ogre::CompositorInstance::TargetOperation::materialScheme */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     setmaterialScheme()
 * Type:       setter for public attribute
 * Definition: String Ogre::CompositorInstance::TargetOperation::materialScheme
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1setmaterialScheme (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
)
{
   // parameter conversions 
  std::string _cpp__jni_value_ = ""; org::xbig::jni::to_stdstring(_jni_env_, _jni_value_, _cpp__jni_value_); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this =reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->materialScheme = _cpp__jni_value_;
} /* String Ogre::CompositorInstance::TargetOperation::materialScheme */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     getshadowsEnabled()
 * Type:       getter for public attribute
 * Definition: bool Ogre::CompositorInstance::TargetOperation::shadowsEnabled
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1getshadowsEnabled (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this = reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->shadowsEnabled; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::CompositorInstance::TargetOperation::shadowsEnabled */


/*
 * Class:      org.ogre4j.CompositorInstance.00024TargetOperation
 * Method:     setshadowsEnabled()
 * Type:       setter for public attribute
 * Definition: bool Ogre::CompositorInstance::TargetOperation::shadowsEnabled
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorInstance_00024TargetOperation__1setshadowsEnabled (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean _jni_value_
)
{
   // parameter conversions 
  bool _cpp__jni_value_ = _jni_value_ ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::CompositorInstance::TargetOperation* _cpp_this =reinterpret_cast<Ogre::CompositorInstance::TargetOperation*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->shadowsEnabled = _cpp__jni_value_;
} /* bool Ogre::CompositorInstance::TargetOperation::shadowsEnabled */
