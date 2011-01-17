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
#include "class_org_ogre4j_GpuProgramUsage.h"

// import header files of original library
#include <OgreGpuProgramUsage.h>



/*
 * Class:      org.ogre4j.GpuProgramUsage
 * Method:     GpuProgramUsage()
 * Type:       constructor
 * Definition: Ogre::GpuProgramUsage::GpuProgramUsage
 * Signature:  (Ogre_GpuProgramType)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_GpuProgramUsage__1_1createGpuProgramUsage_1_1GpuProgramTypev (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jint gptype
)
{
   // constructor of class Ogre::GpuProgramUsage 
   
   // parameter conversions 
  Ogre::GpuProgramType _cpp_gptype = (Ogre::GpuProgramType)gptype; 
   
   // create new instance of class Ogre::GpuProgramUsage 
   Ogre::GpuProgramUsage* _cpp_this = new Ogre::GpuProgramUsage(_cpp_gptype); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::GpuProgramUsage::GpuProgramUsage */


/*
 * Class:      org.ogre4j.GpuProgramUsage
 * Method:     GpuProgramUsage()
 * Type:       constructor
 * Definition: Ogre::GpuProgramUsage::GpuProgramUsage
 * Signature:  (Ogre_GpuProgramUsage)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_GpuProgramUsage__1_1createGpuProgramUsage_1_1GpuProgramUsageR (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong rhs
)
{
   // constructor of class Ogre::GpuProgramUsage 
   
   // parameter conversions 
  const Ogre::GpuProgramUsage* _cpp_rhs = reinterpret_cast< const Ogre::GpuProgramUsage* >(rhs); 
   
   // create new instance of class Ogre::GpuProgramUsage 
   Ogre::GpuProgramUsage* _cpp_this = new Ogre::GpuProgramUsage(*_cpp_rhs); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::GpuProgramUsage::GpuProgramUsage */


/*
 * Class:      org.ogre4j.GpuProgramUsage
 * Method:     getType()
 * Type:       non-virtual method
 * Definition: GpuProgramType Ogre::GpuProgramUsage::getType
 * Signature:  ()Ogre_GpuProgramType
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_GpuProgramUsage__1getType_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::GpuProgramUsage* _cpp_this = reinterpret_cast<const Ogre::GpuProgramUsage*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::GpuProgramType _cpp_result = _cpp_this->getType() ; 
   return _cpp_result;
} /* GpuProgramType Ogre::GpuProgramUsage::getType */


/*
 * Class:      org.ogre4j.GpuProgramUsage
 * Method:     setProgramName()
 * Type:       non-virtual method
 * Definition: void Ogre::GpuProgramUsage::setProgramName
 * Signature:  (std_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_GpuProgramUsage__1setProgramName_1_1StringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jboolean resetParams
)
{
   // parameter conversions 
  std::string _cpp_name = ""; org::xbig::jni::to_stdstring(_jni_env_, name, _cpp_name); // calls c-tor only. Not operator= .;
  bool _cpp_resetParams = resetParams ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::GpuProgramUsage* _cpp_this = reinterpret_cast<Ogre::GpuProgramUsage*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setProgramName(_cpp_name, _cpp_resetParams);
} /* void Ogre::GpuProgramUsage::setProgramName */


/*
 * Class:      org.ogre4j.GpuProgramUsage
 * Method:     setProgram()
 * Type:       non-virtual method
 * Definition: void Ogre::GpuProgramUsage::setProgram
 * Signature:  (Ogre_GpuProgramPtr)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_GpuProgramUsage__1setProgram_1_1GpuProgramPtrr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong prog
)
{
   // parameter conversions 
  Ogre::GpuProgramPtr* _cpp_prog = reinterpret_cast< Ogre::GpuProgramPtr* >(prog); 
   
   // cast pointer to C++ object 
   Ogre::GpuProgramUsage* _cpp_this = reinterpret_cast<Ogre::GpuProgramUsage*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setProgram(*_cpp_prog);
} /* void Ogre::GpuProgramUsage::setProgram */


/*
 * Class:      org.ogre4j.GpuProgramUsage
 * Method:     getProgram()
 * Type:       non-virtual method
 * Definition: const GpuProgramPtr& Ogre::GpuProgramUsage::getProgram
 * Signature:  ()Ogre_GpuProgramPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_GpuProgramUsage__1getProgram_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::GpuProgramUsage* _cpp_this = reinterpret_cast<const Ogre::GpuProgramUsage*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::GpuProgramPtr* _cpp_result = & _cpp_this->getProgram() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* const GpuProgramPtr& Ogre::GpuProgramUsage::getProgram */


/*
 * Class:      org.ogre4j.GpuProgramUsage
 * Method:     getProgramName()
 * Type:       non-virtual method
 * Definition: const String& Ogre::GpuProgramUsage::getProgramName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_GpuProgramUsage__1getProgramName_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::GpuProgramUsage* _cpp_this = reinterpret_cast<const Ogre::GpuProgramUsage*>(_jni_pointer_); 
   
   // call library method 
   std::string _cpp_result = _cpp_this->getProgramName() ; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* const String& Ogre::GpuProgramUsage::getProgramName */


/*
 * Class:      org.ogre4j.GpuProgramUsage
 * Method:     setParameters()
 * Type:       non-virtual method
 * Definition: void Ogre::GpuProgramUsage::setParameters
 * Signature:  (Ogre_GpuProgramParametersSharedPtr)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_GpuProgramUsage__1setParameters_1_1GpuProgramParametersSharedPtrv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong params
)
{
   // parameter conversions 
  Ogre::GpuProgramParametersSharedPtr* _cpp_params = reinterpret_cast< Ogre::GpuProgramParametersSharedPtr* >(params); 
   
   // cast pointer to C++ object 
   Ogre::GpuProgramUsage* _cpp_this = reinterpret_cast<Ogre::GpuProgramUsage*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setParameters(*_cpp_params);
} /* void Ogre::GpuProgramUsage::setParameters */


/*
 * Class:      org.ogre4j.GpuProgramUsage
 * Method:     getParameters()
 * Type:       non-virtual method
 * Definition: GpuProgramParametersSharedPtr Ogre::GpuProgramUsage::getParameters
 * Signature:  ()Ogre_GpuProgramParametersSharedPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_GpuProgramUsage__1getParameters (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::GpuProgramUsage* _cpp_this = reinterpret_cast<Ogre::GpuProgramUsage*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::GpuProgramParametersSharedPtr* _cpp_result = new Ogre::GpuProgramParametersSharedPtr( _cpp_this->getParameters() ); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* GpuProgramParametersSharedPtr Ogre::GpuProgramUsage::getParameters */


/*
 * Class:      org.ogre4j.GpuProgramUsage
 * Method:     _load()
 * Type:       non-virtual method
 * Definition: void Ogre::GpuProgramUsage::_load
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_GpuProgramUsage__1_1load (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::GpuProgramUsage* _cpp_this = reinterpret_cast<Ogre::GpuProgramUsage*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->_load();
} /* void Ogre::GpuProgramUsage::_load */


/*
 * Class:      org.ogre4j.GpuProgramUsage
 * Method:     _unload()
 * Type:       non-virtual method
 * Definition: void Ogre::GpuProgramUsage::_unload
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_GpuProgramUsage__1_1unload (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::GpuProgramUsage* _cpp_this = reinterpret_cast<Ogre::GpuProgramUsage*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->_unload();
} /* void Ogre::GpuProgramUsage::_unload */


/*
 * Class:      org.ogre4j.GpuProgramUsage
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::GpuProgramUsage::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_GpuProgramUsage__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::GpuProgramUsage 
   // cast pointer to C++ object 
   Ogre::GpuProgramUsage* _cpp_this = reinterpret_cast<Ogre::GpuProgramUsage*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::GpuProgramUsage::__delete */
