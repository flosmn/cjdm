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
#include "class_org_ogre4j_PatchSurface.h"

// import header files of original library
#include <OgrePatchSurface.h>



/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     PatchSurface()
 * Type:       constructor
 * Definition: Ogre::PatchSurface::PatchSurface
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_PatchSurface__1_1createPatchSurface (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::PatchSurface 
   
   // parameter conversions 
   
   // create new instance of class Ogre::PatchSurface 
   Ogre::PatchSurface* _cpp_this = new Ogre::PatchSurface(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::PatchSurface::PatchSurface */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     defineSurface()
 * Type:       non-virtual method
 * Definition: void Ogre::PatchSurface::defineSurface
 * Signature:  (VOgre_VertexDeclarationIIOgre_PatchSurface_PatchSurfaceTypeIIOgre_PatchSurface_VisibleSide)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PatchSurface__1defineSurface_1_1vpVertexDeclarationpivivPatchSurfaceTypevivivVisibleSidev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong controlPointBuffer, 
  jlong declaration, 
  jint width, 
  jint height, 
  jint pType, 
  jint uMaxSubdivisionLevel, 
  jint vMaxSubdivisionLevel, 
  jint visibleSide
)
{
   // parameter conversions 
  void* _cpp_controlPointBuffer = reinterpret_cast<void*>(controlPointBuffer);
  Ogre::VertexDeclaration* _cpp_declaration = reinterpret_cast< Ogre::VertexDeclaration* >(declaration);
  size_t _cpp_width = width;
  size_t _cpp_height = height;
  Ogre::PatchSurface::PatchSurfaceType _cpp_pType = (Ogre::PatchSurface::PatchSurfaceType)pType;
  size_t _cpp_uMaxSubdivisionLevel = uMaxSubdivisionLevel;
  size_t _cpp_vMaxSubdivisionLevel = vMaxSubdivisionLevel;
  Ogre::PatchSurface::VisibleSide _cpp_visibleSide = (Ogre::PatchSurface::VisibleSide)visibleSide; 
   
   // cast pointer to C++ object 
   Ogre::PatchSurface* _cpp_this = reinterpret_cast<Ogre::PatchSurface*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->defineSurface(_cpp_controlPointBuffer, _cpp_declaration, _cpp_width, _cpp_height, _cpp_pType, _cpp_uMaxSubdivisionLevel, _cpp_vMaxSubdivisionLevel, _cpp_visibleSide);
} /* void Ogre::PatchSurface::defineSurface */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getRequiredVertexCount()
 * Type:       non-virtual method
 * Definition: size_t Ogre::PatchSurface::getRequiredVertexCount
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_PatchSurface__1getRequiredVertexCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::PatchSurface* _cpp_this = reinterpret_cast<const Ogre::PatchSurface*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->getRequiredVertexCount() ; 
   return _cpp_result;
} /* size_t Ogre::PatchSurface::getRequiredVertexCount */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getRequiredIndexCount()
 * Type:       non-virtual method
 * Definition: size_t Ogre::PatchSurface::getRequiredIndexCount
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_PatchSurface__1getRequiredIndexCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::PatchSurface* _cpp_this = reinterpret_cast<const Ogre::PatchSurface*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->getRequiredIndexCount() ; 
   return _cpp_result;
} /* size_t Ogre::PatchSurface::getRequiredIndexCount */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getCurrentIndexCount()
 * Type:       non-virtual method
 * Definition: size_t Ogre::PatchSurface::getCurrentIndexCount
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_PatchSurface__1getCurrentIndexCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::PatchSurface* _cpp_this = reinterpret_cast<const Ogre::PatchSurface*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->getCurrentIndexCount() ; 
   return _cpp_result;
} /* size_t Ogre::PatchSurface::getCurrentIndexCount */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getIndexOffset()
 * Type:       non-virtual method
 * Definition: size_t Ogre::PatchSurface::getIndexOffset
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_PatchSurface__1getIndexOffset_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::PatchSurface* _cpp_this = reinterpret_cast<const Ogre::PatchSurface*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->getIndexOffset() ; 
   return _cpp_result;
} /* size_t Ogre::PatchSurface::getIndexOffset */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getVertexOffset()
 * Type:       non-virtual method
 * Definition: size_t Ogre::PatchSurface::getVertexOffset
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_PatchSurface__1getVertexOffset_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::PatchSurface* _cpp_this = reinterpret_cast<const Ogre::PatchSurface*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->getVertexOffset() ; 
   return _cpp_result;
} /* size_t Ogre::PatchSurface::getVertexOffset */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getBounds()
 * Type:       non-virtual method
 * Definition: const AxisAlignedBox& Ogre::PatchSurface::getBounds
 * Signature:  ()Ogre_AxisAlignedBox
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_PatchSurface__1getBounds_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::PatchSurface* _cpp_this = reinterpret_cast<const Ogre::PatchSurface*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::AxisAlignedBox* _cpp_result = & _cpp_this->getBounds() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* const AxisAlignedBox& Ogre::PatchSurface::getBounds */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getBoundingSphereRadius()
 * Type:       non-virtual method
 * Definition: Real Ogre::PatchSurface::getBoundingSphereRadius
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_PatchSurface__1getBoundingSphereRadius_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::PatchSurface* _cpp_this = reinterpret_cast<const Ogre::PatchSurface*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getBoundingSphereRadius() ; 
   return _cpp_result;
} /* Real Ogre::PatchSurface::getBoundingSphereRadius */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     build()
 * Type:       non-virtual method
 * Definition: void Ogre::PatchSurface::build
 * Signature:  (Ogre_HardwareVertexBufferSharedPtrIOgre_HardwareIndexBufferSharedPtrI)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PatchSurface__1build_1_1HardwareVertexBufferSharedPtrvivHardwareIndexBufferSharedPtrviv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong destVertexBuffer, 
  jint vertexStart, 
  jlong destIndexBuffer, 
  jint indexStart
)
{
   // parameter conversions 
  Ogre::HardwareVertexBufferSharedPtr* _cpp_destVertexBuffer = reinterpret_cast< Ogre::HardwareVertexBufferSharedPtr* >(destVertexBuffer);
  size_t _cpp_vertexStart = vertexStart;
  Ogre::HardwareIndexBufferSharedPtr* _cpp_destIndexBuffer = reinterpret_cast< Ogre::HardwareIndexBufferSharedPtr* >(destIndexBuffer);
  size_t _cpp_indexStart = indexStart; 
   
   // cast pointer to C++ object 
   Ogre::PatchSurface* _cpp_this = reinterpret_cast<Ogre::PatchSurface*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->build(*_cpp_destVertexBuffer, _cpp_vertexStart, *_cpp_destIndexBuffer, _cpp_indexStart);
} /* void Ogre::PatchSurface::build */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     setSubdivisionFactor()
 * Type:       non-virtual method
 * Definition: void Ogre::PatchSurface::setSubdivisionFactor
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PatchSurface__1setSubdivisionFactor_1_1Realv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat factor
)
{
   // parameter conversions 
  float _cpp_factor = factor; 
   
   // cast pointer to C++ object 
   Ogre::PatchSurface* _cpp_this = reinterpret_cast<Ogre::PatchSurface*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setSubdivisionFactor(_cpp_factor);
} /* void Ogre::PatchSurface::setSubdivisionFactor */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getSubdivisionFactor()
 * Type:       non-virtual method
 * Definition: Real Ogre::PatchSurface::getSubdivisionFactor
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_PatchSurface__1getSubdivisionFactor_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::PatchSurface* _cpp_this = reinterpret_cast<const Ogre::PatchSurface*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getSubdivisionFactor() ; 
   return _cpp_result;
} /* Real Ogre::PatchSurface::getSubdivisionFactor */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     getControlPointBuffer()
 * Type:       non-virtual method
 * Definition: void* Ogre::PatchSurface::getControlPointBuffer
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_PatchSurface__1getControlPointBuffer_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::PatchSurface* _cpp_this = reinterpret_cast<const Ogre::PatchSurface*>(_jni_pointer_); 
   
   // call library method 
   void* _cpp_result = _cpp_this->getControlPointBuffer() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* void* Ogre::PatchSurface::getControlPointBuffer */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     notifyControlPointBufferDeallocated()
 * Type:       non-virtual method
 * Definition: void Ogre::PatchSurface::notifyControlPointBufferDeallocated
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PatchSurface__1notifyControlPointBufferDeallocated (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::PatchSurface* _cpp_this = reinterpret_cast<Ogre::PatchSurface*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->notifyControlPointBufferDeallocated();
} /* void Ogre::PatchSurface::notifyControlPointBufferDeallocated */


/*
 * Class:      org.ogre4j.PatchSurface
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::PatchSurface::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PatchSurface__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::PatchSurface 
   // cast pointer to C++ object 
   Ogre::PatchSurface* _cpp_this = reinterpret_cast<Ogre::PatchSurface*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::PatchSurface::__delete */
