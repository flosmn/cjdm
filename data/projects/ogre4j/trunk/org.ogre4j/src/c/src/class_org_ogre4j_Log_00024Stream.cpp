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
#include "class_org_ogre4j_Log_00024Stream.h"

// import header files of original library
#include <OgreLog.h>



/*
 * Class:      org.ogre4j.Log.00024Stream
 * Method:     Stream()
 * Type:       constructor
 * Definition: Ogre::Log::Stream::Stream
 * Signature:  (Ogre_LogOgre_LogMessageLevelZ)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Log_00024Stream__1_1createStream_1_1LogpLogMessageLevelvbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong target, 
  jint lml, 
  jboolean maskDebug
)
{
   // constructor of class Ogre::Log::Stream 
   
   // parameter conversions 
  Ogre::Log* _cpp_target = reinterpret_cast< Ogre::Log* >(target);
  Ogre::LogMessageLevel _cpp_lml = (Ogre::LogMessageLevel)lml;
  bool _cpp_maskDebug = maskDebug ? true : false; 
   
   // create new instance of class Ogre::Log::Stream 
   Ogre::Log::Stream* _cpp_this = new Ogre::Log::Stream(_cpp_target, _cpp_lml, _cpp_maskDebug); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::Log::Stream::Stream */


/*
 * Class:      org.ogre4j.Log.00024Stream
 * Method:     Stream()
 * Type:       constructor
 * Definition: Ogre::Log::Stream::Stream
 * Signature:  (Ogre_Log_Stream)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Log_00024Stream__1_1createStream_1_1StreamR (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong rhs
)
{
   // constructor of class Ogre::Log::Stream 
   
   // parameter conversions 
  const Ogre::Log::Stream* _cpp_rhs = reinterpret_cast< const Ogre::Log::Stream* >(rhs); 
   
   // create new instance of class Ogre::Log::Stream 
   Ogre::Log::Stream* _cpp_this = new Ogre::Log::Stream(*_cpp_rhs); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::Log::Stream::Stream */


/*
 * Class:      org.ogre4j.Log.00024Stream
 * Method:     operator<<()
 * Type:       non-virtual method
 * Definition: Stream& Ogre::Log::Stream::operator<<
 * Signature:  (Ogre_Log_Stream_Flush)Ogre_Log_Stream
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Log_00024Stream__1operatorLeftShift_1_1FlushR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong v
)
{
   // parameter conversions 
  const Ogre::Log::Stream::Flush* _cpp_v = reinterpret_cast< const Ogre::Log::Stream::Flush* >(v); 
   
   // cast pointer to C++ object 
   Ogre::Log::Stream* _cpp_this = reinterpret_cast<Ogre::Log::Stream*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::Log::Stream* _cpp_result = & _cpp_this->operator<<(*_cpp_v) ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* Stream& Ogre::Log::Stream::operator<< */


/*
 * Class:      org.ogre4j.Log.00024Stream
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::Log::Stream::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Log_00024Stream__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::Log::Stream 
   // cast pointer to C++ object 
   Ogre::Log::Stream* _cpp_this = reinterpret_cast<Ogre::Log::Stream*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::Log::Stream::__delete */
