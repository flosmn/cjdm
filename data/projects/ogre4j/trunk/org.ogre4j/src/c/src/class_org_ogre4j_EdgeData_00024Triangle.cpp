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
#include "class_org_ogre4j_EdgeData_00024Triangle.h"

// import header files of original library
#include <OgreEdgeListBuilder.h>



/*
 * Class:      org.ogre4j.EdgeData.00024Triangle
 * Method:     Triangle()
 * Type:       constructor
 * Definition: Ogre::EdgeData::Triangle::Triangle
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_EdgeData_00024Triangle__1_1createTriangle (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::EdgeData::Triangle 
   
   // parameter conversions 
   
   // create new instance of class Ogre::EdgeData::Triangle 
   Ogre::EdgeData::Triangle* _cpp_this = new Ogre::EdgeData::Triangle(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::EdgeData::Triangle::Triangle */


/*
 * Class:      org.ogre4j.EdgeData.00024Triangle
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::EdgeData::Triangle::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EdgeData_00024Triangle__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::EdgeData::Triangle 
   // cast pointer to C++ object 
   Ogre::EdgeData::Triangle* _cpp_this = reinterpret_cast<Ogre::EdgeData::Triangle*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::EdgeData::Triangle::__delete */


/*
 * Class:      org.ogre4j.EdgeData.00024Triangle
 * Method:     getindexSet()
 * Type:       getter for public attribute
 * Definition: size_t Ogre::EdgeData::Triangle::indexSet
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_EdgeData_00024Triangle__1getindexSet (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::EdgeData::Triangle* _cpp_this = reinterpret_cast<Ogre::EdgeData::Triangle*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->indexSet; 
   return _cpp_result;
} /* size_t Ogre::EdgeData::Triangle::indexSet */


/*
 * Class:      org.ogre4j.EdgeData.00024Triangle
 * Method:     setindexSet()
 * Type:       setter for public attribute
 * Definition: size_t Ogre::EdgeData::Triangle::indexSet
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EdgeData_00024Triangle__1setindexSet (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
)
{
   // parameter conversions 
  size_t _cpp__jni_value_ = _jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::EdgeData::Triangle* _cpp_this =reinterpret_cast<Ogre::EdgeData::Triangle*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->indexSet = _cpp__jni_value_;
} /* size_t Ogre::EdgeData::Triangle::indexSet */


/*
 * Class:      org.ogre4j.EdgeData.00024Triangle
 * Method:     getvertexSet()
 * Type:       getter for public attribute
 * Definition: size_t Ogre::EdgeData::Triangle::vertexSet
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_EdgeData_00024Triangle__1getvertexSet (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::EdgeData::Triangle* _cpp_this = reinterpret_cast<Ogre::EdgeData::Triangle*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->vertexSet; 
   return _cpp_result;
} /* size_t Ogre::EdgeData::Triangle::vertexSet */


/*
 * Class:      org.ogre4j.EdgeData.00024Triangle
 * Method:     setvertexSet()
 * Type:       setter for public attribute
 * Definition: size_t Ogre::EdgeData::Triangle::vertexSet
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EdgeData_00024Triangle__1setvertexSet (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
)
{
   // parameter conversions 
  size_t _cpp__jni_value_ = _jni_value_; 
   
   // cast pointer to C++ object 
   Ogre::EdgeData::Triangle* _cpp_this =reinterpret_cast<Ogre::EdgeData::Triangle*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->vertexSet = _cpp__jni_value_;
} /* size_t Ogre::EdgeData::Triangle::vertexSet */
