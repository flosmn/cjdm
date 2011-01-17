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
#include "class_org_ogre4j_RibbonTrailFactory.h"

// import header files of original library
#include <OgreRibbonTrail.h>



/*
 * Class:      org.ogre4j.RibbonTrailFactory
 * Method:     RibbonTrailFactory()
 * Type:       constructor
 * Definition: Ogre::RibbonTrailFactory::RibbonTrailFactory
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RibbonTrailFactory__1_1createRibbonTrailFactory (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::RibbonTrailFactory 
   
   // parameter conversions 
   
   // create new instance of class Ogre::RibbonTrailFactory 
   Ogre::RibbonTrailFactory* _cpp_this = new Ogre::RibbonTrailFactory(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::RibbonTrailFactory::RibbonTrailFactory */


/*
 * Class:      org.ogre4j.RibbonTrailFactory
 * Method:     getType()
 * Type:       virtual method
 * Definition: const String& Ogre::RibbonTrailFactory::getType
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_RibbonTrailFactory__1getType_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RibbonTrailFactory* _cpp_this = reinterpret_cast<const Ogre::RibbonTrailFactory*>(_jni_pointer_); 
   
   // call library method 
   std::string _cpp_result = _cpp_this->getType() ; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* const String& Ogre::RibbonTrailFactory::getType */


/*
 * Class:      org.ogre4j.RibbonTrailFactory
 * Method:     destroyInstance()
 * Type:       virtual method
 * Definition: void Ogre::RibbonTrailFactory::destroyInstance
 * Signature:  (Ogre_MovableObject)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RibbonTrailFactory__1destroyInstance_1_1MovableObjectp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong obj
)
{
   // parameter conversions 
  Ogre::MovableObject* _cpp_obj = reinterpret_cast< Ogre::MovableObject* >(obj); 
   
   // cast pointer to C++ object 
   Ogre::RibbonTrailFactory* _cpp_this = reinterpret_cast<Ogre::RibbonTrailFactory*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->destroyInstance(_cpp_obj);
} /* void Ogre::RibbonTrailFactory::destroyInstance */


/*
 * Class:      org.ogre4j.RibbonTrailFactory
 * Method:     createInstance()
 * Type:       virtual method
 * Definition: virtual MovableObject* Ogre::MovableObjectFactory::createInstance
 * Signature:  (std_stringOgre_SceneManagerOgre_NameValuePairList)Ogre_MovableObject
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RibbonTrailFactory__1createInstance_1_1StringRSceneManagerpNameValuePairListP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jlong manager, 
  jlong params
)
{
   // parameter conversions 
  std::string _cpp_name = ""; org::xbig::jni::to_stdstring(_jni_env_, name, _cpp_name); // calls c-tor only. Not operator= .;
  Ogre::SceneManager* _cpp_manager = reinterpret_cast< Ogre::SceneManager* >(manager);
  const Ogre::NameValuePairList* _cpp_params = reinterpret_cast< const Ogre::NameValuePairList* >(params); 
   
   // cast pointer to C++ object 
   Ogre::RibbonTrailFactory* _cpp_this = reinterpret_cast<Ogre::RibbonTrailFactory*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::MovableObject* _cpp_result = _cpp_this->createInstance(_cpp_name, _cpp_manager, _cpp_params) ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* virtual MovableObject* Ogre::MovableObjectFactory::createInstance */


/*
 * Class:      org.ogre4j.RibbonTrailFactory
 * Method:     requestTypeFlags()
 * Type:       virtual method
 * Definition: virtual bool Ogre::MovableObjectFactory::requestTypeFlags
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RibbonTrailFactory__1requestTypeFlags_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RibbonTrailFactory* _cpp_this = reinterpret_cast<const Ogre::RibbonTrailFactory*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->requestTypeFlags() ; 
   return _cpp_result ? 1 : 0;
} /* virtual bool Ogre::MovableObjectFactory::requestTypeFlags */


/*
 * Class:      org.ogre4j.RibbonTrailFactory
 * Method:     _notifyTypeFlags()
 * Type:       non-virtual method
 * Definition: void Ogre::MovableObjectFactory::_notifyTypeFlags
 * Signature:  (J)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RibbonTrailFactory__1_1notifyTypeFlags_1_1Lv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong flag
)
{
   // parameter conversions 
  unsigned long _cpp_flag = flag; 
   
   // cast pointer to C++ object 
   Ogre::RibbonTrailFactory* _cpp_this = reinterpret_cast<Ogre::RibbonTrailFactory*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->_notifyTypeFlags(_cpp_flag);
} /* void Ogre::MovableObjectFactory::_notifyTypeFlags */


/*
 * Class:      org.ogre4j.RibbonTrailFactory
 * Method:     getTypeFlags()
 * Type:       non-virtual method
 * Definition: unsigned long Ogre::MovableObjectFactory::getTypeFlags
 * Signature:  ()J
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RibbonTrailFactory__1getTypeFlags_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RibbonTrailFactory* _cpp_this = reinterpret_cast<const Ogre::RibbonTrailFactory*>(_jni_pointer_); 
   
   // call library method 
   const unsigned long _cpp_result = _cpp_this->getTypeFlags() ; 
   return _cpp_result;
} /* unsigned long Ogre::MovableObjectFactory::getTypeFlags */


/*
 * Class:      org.ogre4j.RibbonTrailFactory
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::RibbonTrailFactory::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RibbonTrailFactory__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::RibbonTrailFactory 
   // cast pointer to C++ object 
   Ogre::RibbonTrailFactory* _cpp_this = reinterpret_cast<Ogre::RibbonTrailFactory*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::RibbonTrailFactory::__delete */


/*
 * Class:      org.ogre4j.RibbonTrailFactory
 * Method:     getFACTORY_TYPE_NAME()
 * Type:       getter for public attribute
 * Definition: String Ogre::RibbonTrailFactory::FACTORY_TYPE_NAME
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_RibbonTrailFactory__1getFACTORY_1TYPE_1NAME (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // parameter conversions 
   
   // call library method 
   const std::string _cpp_result = Ogre::RibbonTrailFactory::FACTORY_TYPE_NAME ; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* String Ogre::RibbonTrailFactory::FACTORY_TYPE_NAME */


/*
 * Class:      org.ogre4j.RibbonTrailFactory
 * Method:     setFACTORY_TYPE_NAME()
 * Type:       setter for public attribute
 * Definition: String Ogre::RibbonTrailFactory::FACTORY_TYPE_NAME
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RibbonTrailFactory__1setFACTORY_1TYPE_1NAME (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jstring _jni_value_
)
{
   // parameter conversions 
  std::string _cpp__jni_value_ = ""; org::xbig::jni::to_stdstring(_jni_env_, _jni_value_, _cpp__jni_value_); // calls c-tor only. Not operator= .; 
   
   // call library method 
   Ogre::RibbonTrailFactory::FACTORY_TYPE_NAME = _cpp__jni_value_;
} /* String Ogre::RibbonTrailFactory::FACTORY_TYPE_NAME */
