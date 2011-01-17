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
#include "class_org_ogre4j_EdgeListBuilder.h"

// import header files of original library
#include <OgreEdgeListBuilder.h>



/*
 * Class:      org.ogre4j.EdgeListBuilder
 * Method:     EdgeListBuilder()
 * Type:       constructor
 * Definition: Ogre::EdgeListBuilder::EdgeListBuilder
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_EdgeListBuilder__1_1createEdgeListBuilder (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::EdgeListBuilder 
   
   // parameter conversions 
   
   // create new instance of class Ogre::EdgeListBuilder 
   Ogre::EdgeListBuilder* _cpp_this = new Ogre::EdgeListBuilder(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::EdgeListBuilder::EdgeListBuilder */


/*
 * Class:      org.ogre4j.EdgeListBuilder
 * Method:     addVertexData()
 * Type:       non-virtual method
 * Definition: void Ogre::EdgeListBuilder::addVertexData
 * Signature:  (Ogre_VertexData)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EdgeListBuilder__1addVertexData_1_1VertexDataP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong vertexData
)
{
   // parameter conversions 
  const Ogre::VertexData* _cpp_vertexData = reinterpret_cast< const Ogre::VertexData* >(vertexData); 
   
   // cast pointer to C++ object 
   Ogre::EdgeListBuilder* _cpp_this = reinterpret_cast<Ogre::EdgeListBuilder*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->addVertexData(_cpp_vertexData);
} /* void Ogre::EdgeListBuilder::addVertexData */


/*
 * Class:      org.ogre4j.EdgeListBuilder
 * Method:     addIndexData()
 * Type:       non-virtual method
 * Definition: void Ogre::EdgeListBuilder::addIndexData
 * Signature:  (Ogre_IndexDataIOgre_RenderOperation_OperationType)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EdgeListBuilder__1addIndexData_1_1IndexDataPivRenderOperation_1OperationTypev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong indexData, 
  jint vertexSet, 
  jint opType
)
{
   // parameter conversions 
  const Ogre::IndexData* _cpp_indexData = reinterpret_cast< const Ogre::IndexData* >(indexData);
  size_t _cpp_vertexSet = vertexSet;
  Ogre::RenderOperation::OperationType _cpp_opType = (Ogre::RenderOperation::OperationType)opType; 
   
   // cast pointer to C++ object 
   Ogre::EdgeListBuilder* _cpp_this = reinterpret_cast<Ogre::EdgeListBuilder*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->addIndexData(_cpp_indexData, _cpp_vertexSet, _cpp_opType);
} /* void Ogre::EdgeListBuilder::addIndexData */


/*
 * Class:      org.ogre4j.EdgeListBuilder
 * Method:     build()
 * Type:       non-virtual method
 * Definition: EdgeData* Ogre::EdgeListBuilder::build
 * Signature:  ()Ogre_EdgeData
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_EdgeListBuilder__1build (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::EdgeListBuilder* _cpp_this = reinterpret_cast<Ogre::EdgeListBuilder*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::EdgeData* _cpp_result = _cpp_this->build() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* EdgeData* Ogre::EdgeListBuilder::build */


/*
 * Class:      org.ogre4j.EdgeListBuilder
 * Method:     log()
 * Type:       non-virtual method
 * Definition: void Ogre::EdgeListBuilder::log
 * Signature:  (Ogre_Log)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EdgeListBuilder__1log_1_1Logp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong l
)
{
   // parameter conversions 
  Ogre::Log* _cpp_l = reinterpret_cast< Ogre::Log* >(l); 
   
   // cast pointer to C++ object 
   Ogre::EdgeListBuilder* _cpp_this = reinterpret_cast<Ogre::EdgeListBuilder*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->log(_cpp_l);
} /* void Ogre::EdgeListBuilder::log */


/*
 * Class:      org.ogre4j.EdgeListBuilder
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::EdgeListBuilder::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_EdgeListBuilder__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::EdgeListBuilder 
   // cast pointer to C++ object 
   Ogre::EdgeListBuilder* _cpp_this = reinterpret_cast<Ogre::EdgeListBuilder*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::EdgeListBuilder::__delete */
