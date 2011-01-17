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
#include "class_org_ogre4j_ConfigFile.h"

// import header files of original library
#include <OgreConfigFile.h>



/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     ConfigFile()
 * Type:       constructor
 * Definition: Ogre::ConfigFile::ConfigFile
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ConfigFile__1_1createConfigFile (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::ConfigFile 
   
   // parameter conversions 
   
   // create new instance of class Ogre::ConfigFile 
   Ogre::ConfigFile* _cpp_this = new Ogre::ConfigFile(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::ConfigFile::ConfigFile */


/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     load()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigFile::load
 * Signature:  (std_stringstd_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1load_1_1StringRStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring filename, 
  jstring separators, 
  jboolean trimWhitespace
)
{
   // parameter conversions 
  std::string _cpp_filename = ""; org::xbig::jni::to_stdstring(_jni_env_, filename, _cpp_filename); // calls c-tor only. Not operator= .;
  std::string _cpp_separators = ""; org::xbig::jni::to_stdstring(_jni_env_, separators, _cpp_separators); // calls c-tor only. Not operator= .;
  bool _cpp_trimWhitespace = trimWhitespace ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::ConfigFile* _cpp_this = reinterpret_cast<Ogre::ConfigFile*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->load(_cpp_filename, _cpp_separators, _cpp_trimWhitespace);
} /* void Ogre::ConfigFile::load */


/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     load()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigFile::load
 * Signature:  (std_stringstd_stringstd_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1load_1_1StringRStringRStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring filename, 
  jstring resourceGroup, 
  jstring separators, 
  jboolean trimWhitespace
)
{
   // parameter conversions 
  std::string _cpp_filename = ""; org::xbig::jni::to_stdstring(_jni_env_, filename, _cpp_filename); // calls c-tor only. Not operator= .;
  std::string _cpp_resourceGroup = ""; org::xbig::jni::to_stdstring(_jni_env_, resourceGroup, _cpp_resourceGroup); // calls c-tor only. Not operator= .;
  std::string _cpp_separators = ""; org::xbig::jni::to_stdstring(_jni_env_, separators, _cpp_separators); // calls c-tor only. Not operator= .;
  bool _cpp_trimWhitespace = trimWhitespace ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::ConfigFile* _cpp_this = reinterpret_cast<Ogre::ConfigFile*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->load(_cpp_filename, _cpp_resourceGroup, _cpp_separators, _cpp_trimWhitespace);
} /* void Ogre::ConfigFile::load */


/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     load()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigFile::load
 * Signature:  (Ogre_DataStreamPtrstd_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1load_1_1DataStreamPtrRStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong stream, 
  jstring separators, 
  jboolean trimWhitespace
)
{
   // parameter conversions 
  const Ogre::DataStreamPtr* _cpp_stream = reinterpret_cast< const Ogre::DataStreamPtr* >(stream);
  std::string _cpp_separators = ""; org::xbig::jni::to_stdstring(_jni_env_, separators, _cpp_separators); // calls c-tor only. Not operator= .;
  bool _cpp_trimWhitespace = trimWhitespace ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::ConfigFile* _cpp_this = reinterpret_cast<Ogre::ConfigFile*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->load(*_cpp_stream, _cpp_separators, _cpp_trimWhitespace);
} /* void Ogre::ConfigFile::load */


/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     loadDirect()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigFile::loadDirect
 * Signature:  (std_stringstd_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1loadDirect_1_1StringRStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring filename, 
  jstring separators, 
  jboolean trimWhitespace
)
{
   // parameter conversions 
  std::string _cpp_filename = ""; org::xbig::jni::to_stdstring(_jni_env_, filename, _cpp_filename); // calls c-tor only. Not operator= .;
  std::string _cpp_separators = ""; org::xbig::jni::to_stdstring(_jni_env_, separators, _cpp_separators); // calls c-tor only. Not operator= .;
  bool _cpp_trimWhitespace = trimWhitespace ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::ConfigFile* _cpp_this = reinterpret_cast<Ogre::ConfigFile*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->loadDirect(_cpp_filename, _cpp_separators, _cpp_trimWhitespace);
} /* void Ogre::ConfigFile::loadDirect */


/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     loadFromResourceSystem()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigFile::loadFromResourceSystem
 * Signature:  (std_stringstd_stringstd_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1loadFromResourceSystem_1_1StringRStringRStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring filename, 
  jstring resourceGroup, 
  jstring separators, 
  jboolean trimWhitespace
)
{
   // parameter conversions 
  std::string _cpp_filename = ""; org::xbig::jni::to_stdstring(_jni_env_, filename, _cpp_filename); // calls c-tor only. Not operator= .;
  std::string _cpp_resourceGroup = ""; org::xbig::jni::to_stdstring(_jni_env_, resourceGroup, _cpp_resourceGroup); // calls c-tor only. Not operator= .;
  std::string _cpp_separators = ""; org::xbig::jni::to_stdstring(_jni_env_, separators, _cpp_separators); // calls c-tor only. Not operator= .;
  bool _cpp_trimWhitespace = trimWhitespace ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::ConfigFile* _cpp_this = reinterpret_cast<Ogre::ConfigFile*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->loadFromResourceSystem(_cpp_filename, _cpp_resourceGroup, _cpp_separators, _cpp_trimWhitespace);
} /* void Ogre::ConfigFile::loadFromResourceSystem */


/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     getSetting()
 * Type:       non-virtual method
 * Definition: String Ogre::ConfigFile::getSetting
 * Signature:  (std_stringstd_stringstd_string)std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ConfigFile__1getSetting_1_1StringRStringRStringR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring key, 
  jstring section, 
  jstring defaultValue
)
{
   // parameter conversions 
  std::string _cpp_key = ""; org::xbig::jni::to_stdstring(_jni_env_, key, _cpp_key); // calls c-tor only. Not operator= .;
  std::string _cpp_section = ""; org::xbig::jni::to_stdstring(_jni_env_, section, _cpp_section); // calls c-tor only. Not operator= .;
  std::string _cpp_defaultValue = ""; org::xbig::jni::to_stdstring(_jni_env_, defaultValue, _cpp_defaultValue); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   const Ogre::ConfigFile* _cpp_this = reinterpret_cast<const Ogre::ConfigFile*>(_jni_pointer_); 
   
   // call library method 
   const std::string _cpp_result = _cpp_this->getSetting(_cpp_key, _cpp_section, _cpp_defaultValue) ; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* String Ogre::ConfigFile::getSetting */


/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     getMultiSetting()
 * Type:       non-virtual method
 * Definition: StringVector Ogre::ConfigFile::getMultiSetting
 * Signature:  (std_stringstd_string)Ogre_StringVector
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ConfigFile__1getMultiSetting_1_1StringRStringR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring key, 
  jstring section
)
{
   // parameter conversions 
  std::string _cpp_key = ""; org::xbig::jni::to_stdstring(_jni_env_, key, _cpp_key); // calls c-tor only. Not operator= .;
  std::string _cpp_section = ""; org::xbig::jni::to_stdstring(_jni_env_, section, _cpp_section); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   const Ogre::ConfigFile* _cpp_this = reinterpret_cast<const Ogre::ConfigFile*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::StringVector* _cpp_result = new Ogre::StringVector( _cpp_this->getMultiSetting(_cpp_key, _cpp_section) ); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* StringVector Ogre::ConfigFile::getMultiSetting */


/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     getSectionIterator()
 * Type:       non-virtual method
 * Definition: SectionIterator Ogre::ConfigFile::getSectionIterator
 * Signature:  ()Ogre_ConfigFile_SectionIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ConfigFile__1getSectionIterator (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ConfigFile* _cpp_this = reinterpret_cast<Ogre::ConfigFile*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::ConfigFile::SectionIterator* _cpp_result = new Ogre::ConfigFile::SectionIterator( _cpp_this->getSectionIterator() ); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* SectionIterator Ogre::ConfigFile::getSectionIterator */


/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     getSettingsIterator()
 * Type:       non-virtual method
 * Definition: SettingsIterator Ogre::ConfigFile::getSettingsIterator
 * Signature:  (std_string)Ogre_ConfigFile_SettingsIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ConfigFile__1getSettingsIterator_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring section
)
{
   // parameter conversions 
  std::string _cpp_section = ""; org::xbig::jni::to_stdstring(_jni_env_, section, _cpp_section); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::ConfigFile* _cpp_this = reinterpret_cast<Ogre::ConfigFile*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::ConfigFile::SettingsIterator* _cpp_result = new Ogre::ConfigFile::SettingsIterator( _cpp_this->getSettingsIterator(_cpp_section) ); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* SettingsIterator Ogre::ConfigFile::getSettingsIterator */


/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     clear()
 * Type:       non-virtual method
 * Definition: void Ogre::ConfigFile::clear
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1clear (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ConfigFile* _cpp_this = reinterpret_cast<Ogre::ConfigFile*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->clear();
} /* void Ogre::ConfigFile::clear */


/*
 * Class:      org.ogre4j.ConfigFile
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ConfigFile::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ConfigFile__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::ConfigFile 
   // cast pointer to C++ object 
   Ogre::ConfigFile* _cpp_this = reinterpret_cast<Ogre::ConfigFile*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::ConfigFile::__delete */
