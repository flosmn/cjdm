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
#include "class_org_ogre4j_UTFString_00024invalid_1data.h"

// import header files of original library
#include <OgreUTFString.h>



/*
 * Class:      org.ogre4j.UTFString.00024invalid.1data
 * Method:     invalid_data()
 * Type:       constructor
 * Definition: Ogre::UTFString::invalid_data::invalid_data
 * Signature:  (Ljava/lang/String;)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_UTFString_00024invalid_1data__1_1createinvalid_1data_1_1sR (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jstring _Message
)
{
   // constructor of class Ogre::UTFString::invalid_data 
   
   // parameter conversions 
  std::string _cpp__Message = ""; org::xbig::jni::to_stdstring(_jni_env_, _Message, _cpp__Message); // calls c-tor only. Not operator= .; 
   
   // create new instance of class Ogre::UTFString::invalid_data 
   Ogre::UTFString::invalid_data* _cpp_this = new Ogre::UTFString::invalid_data(_cpp__Message); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::UTFString::invalid_data::invalid_data */


/*
 * Class:      org.ogre4j.UTFString.00024invalid.1data
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::UTFString::invalid_data::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_UTFString_00024invalid_1data__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::UTFString::invalid_data 
   // cast pointer to C++ object 
   Ogre::UTFString::invalid_data* _cpp_this = reinterpret_cast<Ogre::UTFString::invalid_data*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::UTFString::invalid_data::__delete */
