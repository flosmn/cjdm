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
#include "class_org_ogre4j_LayerBlendModeEx.h"

// import header files of original library
#include <OgreBlendMode.h>



/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     operator==()
 * Type:       non-virtual method
 * Definition: bool Ogre::LayerBlendModeEx::operator==
 * Signature:  (Ogre_LayerBlendModeEx)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_LayerBlendModeEx__1operatorEqual_1_1LayerBlendModeExR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rhs
)
{
   // parameter conversions 
  const Ogre::LayerBlendModeEx* _cpp_rhs = reinterpret_cast< const Ogre::LayerBlendModeEx* >(rhs); 
   
   // cast pointer to C++ object 
   const Ogre::LayerBlendModeEx* _cpp_this = reinterpret_cast<const Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->operator==(*_cpp_rhs) ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::LayerBlendModeEx::operator== */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     operator!=()
 * Type:       non-virtual method
 * Definition: bool Ogre::LayerBlendModeEx::operator!=
 * Signature:  (Ogre_LayerBlendModeEx)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_LayerBlendModeEx__1operatorNotEqual_1_1LayerBlendModeExR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rhs
)
{
   // parameter conversions 
  const Ogre::LayerBlendModeEx* _cpp_rhs = reinterpret_cast< const Ogre::LayerBlendModeEx* >(rhs); 
   
   // cast pointer to C++ object 
   const Ogre::LayerBlendModeEx* _cpp_this = reinterpret_cast<const Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->operator!=(*_cpp_rhs) ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::LayerBlendModeEx::operator!= */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     LayerBlendModeEx()
 * Type:       constructor
 * Definition: Ogre::LayerBlendModeEx::LayerBlendModeEx
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_LayerBlendModeEx__1_1createLayerBlendModeEx (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::LayerBlendModeEx 
   
   // parameter conversions 
   
   // create new instance of class Ogre::LayerBlendModeEx 
   Ogre::LayerBlendModeEx* _cpp_this = new Ogre::LayerBlendModeEx(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::LayerBlendModeEx::LayerBlendModeEx */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::LayerBlendModeEx::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_LayerBlendModeEx__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::LayerBlendModeEx 
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this = reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::LayerBlendModeEx::__delete */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     getblendType()
 * Type:       getter for public attribute
 * Definition: LayerBlendType Ogre::LayerBlendModeEx::blendType
 * Signature:  ()Ogre_LayerBlendType
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_LayerBlendModeEx__1getblendType (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this = reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::LayerBlendType _cpp_result = _cpp_this->blendType; 
   return _cpp_result;
} /* LayerBlendType Ogre::LayerBlendModeEx::blendType */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     setblendType()
 * Type:       setter for public attribute
 * Definition: LayerBlendType Ogre::LayerBlendModeEx::blendType
 * Signature:  (Ogre_LayerBlendType)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_LayerBlendModeEx__1setblendType (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
)
{
   // parameter conversions 
  Ogre::LayerBlendType _cpp__jni_value_ = (Ogre::LayerBlendType)_jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this =reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->blendType = _cpp__jni_value_;
} /* LayerBlendType Ogre::LayerBlendModeEx::blendType */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     getoperation()
 * Type:       getter for public attribute
 * Definition: LayerBlendOperationEx Ogre::LayerBlendModeEx::operation
 * Signature:  ()Ogre_LayerBlendOperationEx
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_LayerBlendModeEx__1getoperation (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this = reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::LayerBlendOperationEx _cpp_result = _cpp_this->operation; 
   return _cpp_result;
} /* LayerBlendOperationEx Ogre::LayerBlendModeEx::operation */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     setoperation()
 * Type:       setter for public attribute
 * Definition: LayerBlendOperationEx Ogre::LayerBlendModeEx::operation
 * Signature:  (Ogre_LayerBlendOperationEx)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_LayerBlendModeEx__1setoperation (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
)
{
   // parameter conversions 
  Ogre::LayerBlendOperationEx _cpp__jni_value_ = (Ogre::LayerBlendOperationEx)_jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this =reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->operation = _cpp__jni_value_;
} /* LayerBlendOperationEx Ogre::LayerBlendModeEx::operation */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     getsource1()
 * Type:       getter for public attribute
 * Definition: LayerBlendSource Ogre::LayerBlendModeEx::source1
 * Signature:  ()Ogre_LayerBlendSource
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_LayerBlendModeEx__1getsource1 (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this = reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::LayerBlendSource _cpp_result = _cpp_this->source1; 
   return _cpp_result;
} /* LayerBlendSource Ogre::LayerBlendModeEx::source1 */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     setsource1()
 * Type:       setter for public attribute
 * Definition: LayerBlendSource Ogre::LayerBlendModeEx::source1
 * Signature:  (Ogre_LayerBlendSource)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_LayerBlendModeEx__1setsource1 (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
)
{
   // parameter conversions 
  Ogre::LayerBlendSource _cpp__jni_value_ = (Ogre::LayerBlendSource)_jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this =reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->source1 = _cpp__jni_value_;
} /* LayerBlendSource Ogre::LayerBlendModeEx::source1 */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     getsource2()
 * Type:       getter for public attribute
 * Definition: LayerBlendSource Ogre::LayerBlendModeEx::source2
 * Signature:  ()Ogre_LayerBlendSource
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_LayerBlendModeEx__1getsource2 (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this = reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::LayerBlendSource _cpp_result = _cpp_this->source2; 
   return _cpp_result;
} /* LayerBlendSource Ogre::LayerBlendModeEx::source2 */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     setsource2()
 * Type:       setter for public attribute
 * Definition: LayerBlendSource Ogre::LayerBlendModeEx::source2
 * Signature:  (Ogre_LayerBlendSource)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_LayerBlendModeEx__1setsource2 (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
)
{
   // parameter conversions 
  Ogre::LayerBlendSource _cpp__jni_value_ = (Ogre::LayerBlendSource)_jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this =reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->source2 = _cpp__jni_value_;
} /* LayerBlendSource Ogre::LayerBlendModeEx::source2 */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     getcolourArg1()
 * Type:       getter for public attribute
 * Definition: ColourValue Ogre::LayerBlendModeEx::colourArg1
 * Signature:  ()Ogre_ColourValue
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_LayerBlendModeEx__1getcolourArg1 (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this = reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::ColourValue* _cpp_result = new Ogre::ColourValue( _cpp_this->colourArg1); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* ColourValue Ogre::LayerBlendModeEx::colourArg1 */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     setcolourArg1()
 * Type:       setter for public attribute
 * Definition: ColourValue Ogre::LayerBlendModeEx::colourArg1
 * Signature:  (Ogre_ColourValue)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_LayerBlendModeEx__1setcolourArg1 (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
)
{
   // parameter conversions 
  Ogre::ColourValue* _cpp__jni_value_ = reinterpret_cast< Ogre::ColourValue* >(_jni_value_); 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this =reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->colourArg1 = *_cpp__jni_value_;
} /* ColourValue Ogre::LayerBlendModeEx::colourArg1 */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     getcolourArg2()
 * Type:       getter for public attribute
 * Definition: ColourValue Ogre::LayerBlendModeEx::colourArg2
 * Signature:  ()Ogre_ColourValue
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_LayerBlendModeEx__1getcolourArg2 (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this = reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::ColourValue* _cpp_result = new Ogre::ColourValue( _cpp_this->colourArg2); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* ColourValue Ogre::LayerBlendModeEx::colourArg2 */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     setcolourArg2()
 * Type:       setter for public attribute
 * Definition: ColourValue Ogre::LayerBlendModeEx::colourArg2
 * Signature:  (Ogre_ColourValue)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_LayerBlendModeEx__1setcolourArg2 (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
)
{
   // parameter conversions 
  Ogre::ColourValue* _cpp__jni_value_ = reinterpret_cast< Ogre::ColourValue* >(_jni_value_); 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this =reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->colourArg2 = *_cpp__jni_value_;
} /* ColourValue Ogre::LayerBlendModeEx::colourArg2 */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     getalphaArg1()
 * Type:       getter for public attribute
 * Definition: Real Ogre::LayerBlendModeEx::alphaArg1
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_LayerBlendModeEx__1getalphaArg1 (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this = reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->alphaArg1; 
   return _cpp_result;
} /* Real Ogre::LayerBlendModeEx::alphaArg1 */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     setalphaArg1()
 * Type:       setter for public attribute
 * Definition: Real Ogre::LayerBlendModeEx::alphaArg1
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_LayerBlendModeEx__1setalphaArg1 (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat _jni_value_
)
{
   // parameter conversions 
  float _cpp__jni_value_ = _jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this =reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->alphaArg1 = _cpp__jni_value_;
} /* Real Ogre::LayerBlendModeEx::alphaArg1 */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     getalphaArg2()
 * Type:       getter for public attribute
 * Definition: Real Ogre::LayerBlendModeEx::alphaArg2
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_LayerBlendModeEx__1getalphaArg2 (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this = reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->alphaArg2; 
   return _cpp_result;
} /* Real Ogre::LayerBlendModeEx::alphaArg2 */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     setalphaArg2()
 * Type:       setter for public attribute
 * Definition: Real Ogre::LayerBlendModeEx::alphaArg2
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_LayerBlendModeEx__1setalphaArg2 (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat _jni_value_
)
{
   // parameter conversions 
  float _cpp__jni_value_ = _jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this =reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->alphaArg2 = _cpp__jni_value_;
} /* Real Ogre::LayerBlendModeEx::alphaArg2 */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     getfactor()
 * Type:       getter for public attribute
 * Definition: Real Ogre::LayerBlendModeEx::factor
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_LayerBlendModeEx__1getfactor (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this = reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->factor; 
   return _cpp_result;
} /* Real Ogre::LayerBlendModeEx::factor */


/*
 * Class:      org.ogre4j.LayerBlendModeEx
 * Method:     setfactor()
 * Type:       setter for public attribute
 * Definition: Real Ogre::LayerBlendModeEx::factor
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_LayerBlendModeEx__1setfactor (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat _jni_value_
)
{
   // parameter conversions 
  float _cpp__jni_value_ = _jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::LayerBlendModeEx* _cpp_this =reinterpret_cast<Ogre::LayerBlendModeEx*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->factor = _cpp__jni_value_;
} /* Real Ogre::LayerBlendModeEx::factor */
