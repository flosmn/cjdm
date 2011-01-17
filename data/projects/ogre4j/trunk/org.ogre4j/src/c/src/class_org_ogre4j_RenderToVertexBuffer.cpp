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
#include "class_org_ogre4j_RenderToVertexBuffer.h"

// import header files of original library
#include <OgreRenderToVertexBuffer.h>



/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     getVertexDeclaration()
 * Type:       non-virtual method
 * Definition: VertexDeclaration* Ogre::RenderToVertexBuffer::getVertexDeclaration
 * Signature:  ()Ogre_VertexDeclaration
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderToVertexBuffer__1getVertexDeclaration (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::VertexDeclaration* _cpp_result = _cpp_this->getVertexDeclaration() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* VertexDeclaration* Ogre::RenderToVertexBuffer::getVertexDeclaration */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     getMaxVertexCount()
 * Type:       non-virtual method
 * Definition: unsigned int Ogre::RenderToVertexBuffer::getMaxVertexCount
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderToVertexBuffer__1getMaxVertexCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<const Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   const unsigned int _cpp_result = _cpp_this->getMaxVertexCount() ; 
   return _cpp_result;
} /* unsigned int Ogre::RenderToVertexBuffer::getMaxVertexCount */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     setMaxVertexCount()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderToVertexBuffer::setMaxVertexCount
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderToVertexBuffer__1setMaxVertexCount_1_1Iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong maxVertexCount
)
{
   // parameter conversions 
  unsigned int _cpp_maxVertexCount = maxVertexCount; 
   
   // cast pointer to C++ object 
   Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setMaxVertexCount(_cpp_maxVertexCount);
} /* void Ogre::RenderToVertexBuffer::setMaxVertexCount */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     getOperationType()
 * Type:       non-virtual method
 * Definition: RenderOperation::OperationType Ogre::RenderToVertexBuffer::getOperationType
 * Signature:  ()Ogre_RenderOperation_OperationType
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderToVertexBuffer__1getOperationType_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<const Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::RenderOperation::OperationType _cpp_result = _cpp_this->getOperationType() ; 
   return _cpp_result;
} /* RenderOperation::OperationType Ogre::RenderToVertexBuffer::getOperationType */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     setOperationType()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderToVertexBuffer::setOperationType
 * Signature:  (Ogre_RenderOperation_OperationType)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderToVertexBuffer__1setOperationType_1_1RenderOperation_1OperationTypev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint operationType
)
{
   // parameter conversions 
  Ogre::RenderOperation::OperationType _cpp_operationType = (Ogre::RenderOperation::OperationType)operationType; 
   
   // cast pointer to C++ object 
   Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setOperationType(_cpp_operationType);
} /* void Ogre::RenderToVertexBuffer::setOperationType */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     setResetsEveryUpdate()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderToVertexBuffer::setResetsEveryUpdate
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderToVertexBuffer__1setResetsEveryUpdate_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean resetsEveryUpdate
)
{
   // parameter conversions 
  bool _cpp_resetsEveryUpdate = resetsEveryUpdate ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setResetsEveryUpdate(_cpp_resetsEveryUpdate);
} /* void Ogre::RenderToVertexBuffer::setResetsEveryUpdate */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     getResetsEveryUpdate()
 * Type:       non-virtual method
 * Definition: bool Ogre::RenderToVertexBuffer::getResetsEveryUpdate
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderToVertexBuffer__1getResetsEveryUpdate_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<const Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->getResetsEveryUpdate() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::RenderToVertexBuffer::getResetsEveryUpdate */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     getRenderOperation()
 * Type:       pure virtual method
 * Definition: virtual void Ogre::RenderToVertexBuffer::getRenderOperation
 * Signature:  (Ogre_RenderOperation)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderToVertexBuffer__1getRenderOperation_1_1RenderOperationr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong op
)
{
   // parameter conversions 
  Ogre::RenderOperation* _cpp_op = reinterpret_cast< Ogre::RenderOperation* >(op); 
   
   // cast pointer to C++ object 
   Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->getRenderOperation(*_cpp_op);
} /* virtual void Ogre::RenderToVertexBuffer::getRenderOperation */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     update()
 * Type:       pure virtual method
 * Definition: virtual void Ogre::RenderToVertexBuffer::update
 * Signature:  (Ogre_SceneManager)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderToVertexBuffer__1update_1_1SceneManagerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong sceneMgr
)
{
   // parameter conversions 
  Ogre::SceneManager* _cpp_sceneMgr = reinterpret_cast< Ogre::SceneManager* >(sceneMgr); 
   
   // cast pointer to C++ object 
   Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->update(_cpp_sceneMgr);
} /* virtual void Ogre::RenderToVertexBuffer::update */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     reset()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderToVertexBuffer::reset
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderToVertexBuffer__1reset (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->reset();
} /* virtual void Ogre::RenderToVertexBuffer::reset */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     setSourceRenderable()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderToVertexBuffer::setSourceRenderable
 * Signature:  (Ogre_Renderable)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderToVertexBuffer__1setSourceRenderable_1_1Renderablep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong source
)
{
   // parameter conversions 
  Ogre::Renderable* _cpp_source = reinterpret_cast< Ogre::Renderable* >(source); 
   
   // cast pointer to C++ object 
   Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setSourceRenderable(_cpp_source);
} /* void Ogre::RenderToVertexBuffer::setSourceRenderable */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     getSourceRenderable()
 * Type:       non-virtual method
 * Definition: const Renderable* Ogre::RenderToVertexBuffer::getSourceRenderable
 * Signature:  ()Ogre_Renderable
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderToVertexBuffer__1getSourceRenderable_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<const Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::Renderable* _cpp_result = _cpp_this->getSourceRenderable() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* const Renderable* Ogre::RenderToVertexBuffer::getSourceRenderable */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     getRenderToBufferMaterial()
 * Type:       non-virtual method
 * Definition: const MaterialPtr& Ogre::RenderToVertexBuffer::getRenderToBufferMaterial
 * Signature:  ()Ogre_MaterialPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderToVertexBuffer__1getRenderToBufferMaterial (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::MaterialPtr* _cpp_result = & _cpp_this->getRenderToBufferMaterial() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* const MaterialPtr& Ogre::RenderToVertexBuffer::getRenderToBufferMaterial */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     setRenderToBufferMaterialName()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderToVertexBuffer::setRenderToBufferMaterialName
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderToVertexBuffer__1setRenderToBufferMaterialName_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring materialName
)
{
   // parameter conversions 
  std::string _cpp_materialName = ""; org::xbig::jni::to_stdstring(_jni_env_, materialName, _cpp_materialName); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setRenderToBufferMaterialName(_cpp_materialName);
} /* void Ogre::RenderToVertexBuffer::setRenderToBufferMaterialName */


/*
 * Class:      org.ogre4j.RenderToVertexBuffer
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::RenderToVertexBuffer::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderToVertexBuffer__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::RenderToVertexBuffer 
   // cast pointer to C++ object 
   Ogre::RenderToVertexBuffer* _cpp_this = reinterpret_cast<Ogre::RenderToVertexBuffer*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::RenderToVertexBuffer::__delete */
