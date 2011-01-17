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
#include "class_org_ogre4j_Viewport.h"

// import header files of original library
#include <OgreViewport.h>



/*
 * Class:      org.ogre4j.Viewport
 * Method:     Viewport()
 * Type:       constructor
 * Definition: Ogre::Viewport::Viewport
 * Signature:  (Ogre_CameraOgre_RenderTargetfloatfloatfloatfloatI)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Viewport__1_1createViewport_1_1CamerapRenderTargetpRealvRealvRealvRealviv (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong camera, 
  jlong target, 
  jfloat left, 
  jfloat top, 
  jfloat width, 
  jfloat height, 
  jint ZOrder
)
{
   // constructor of class Ogre::Viewport 
   
   // parameter conversions 
  Ogre::Camera* _cpp_camera = reinterpret_cast< Ogre::Camera* >(camera);
  Ogre::RenderTarget* _cpp_target = reinterpret_cast< Ogre::RenderTarget* >(target);
  float _cpp_left = left;
  float _cpp_top = top;
  float _cpp_width = width;
  float _cpp_height = height;
  int _cpp_ZOrder = ZOrder; 
   
   // create new instance of class Ogre::Viewport 
   Ogre::Viewport* _cpp_this = new Ogre::Viewport(_cpp_camera, _cpp_target, _cpp_left, _cpp_top, _cpp_width, _cpp_height, _cpp_ZOrder); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::Viewport::Viewport */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     _updateDimensions()
 * Type:       non-virtual method
 * Definition: void Ogre::Viewport::_updateDimensions
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1_1updateDimensions (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->_updateDimensions();
} /* void Ogre::Viewport::_updateDimensions */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     update()
 * Type:       non-virtual method
 * Definition: void Ogre::Viewport::update
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1update (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->update();
} /* void Ogre::Viewport::update */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getTarget()
 * Type:       non-virtual method
 * Definition: RenderTarget* Ogre::Viewport::getTarget
 * Signature:  ()Ogre_RenderTarget
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Viewport__1getTarget_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::RenderTarget* _cpp_result = _cpp_this->getTarget() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* RenderTarget* Ogre::Viewport::getTarget */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getCamera()
 * Type:       non-virtual method
 * Definition: Camera* Ogre::Viewport::getCamera
 * Signature:  ()Ogre_Camera
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Viewport__1getCamera_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::Camera* _cpp_result = _cpp_this->getCamera() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* Camera* Ogre::Viewport::getCamera */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     setCamera()
 * Type:       non-virtual method
 * Definition: void Ogre::Viewport::setCamera
 * Signature:  (Ogre_Camera)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1setCamera_1_1Camerap (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong cam
)
{
   // parameter conversions 
  Ogre::Camera* _cpp_cam = reinterpret_cast< Ogre::Camera* >(cam); 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setCamera(_cpp_cam);
} /* void Ogre::Viewport::setCamera */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getZOrder()
 * Type:       non-virtual method
 * Definition: int Ogre::Viewport::getZOrder
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Viewport__1getZOrder_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const int _cpp_result = _cpp_this->getZOrder() ; 
   return _cpp_result;
} /* int Ogre::Viewport::getZOrder */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getLeft()
 * Type:       non-virtual method
 * Definition: Real Ogre::Viewport::getLeft
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_Viewport__1getLeft_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getLeft() ; 
   return _cpp_result;
} /* Real Ogre::Viewport::getLeft */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getTop()
 * Type:       non-virtual method
 * Definition: Real Ogre::Viewport::getTop
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_Viewport__1getTop_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getTop() ; 
   return _cpp_result;
} /* Real Ogre::Viewport::getTop */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getWidth()
 * Type:       non-virtual method
 * Definition: Real Ogre::Viewport::getWidth
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_Viewport__1getWidth_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getWidth() ; 
   return _cpp_result;
} /* Real Ogre::Viewport::getWidth */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getHeight()
 * Type:       non-virtual method
 * Definition: Real Ogre::Viewport::getHeight
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_Viewport__1getHeight_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getHeight() ; 
   return _cpp_result;
} /* Real Ogre::Viewport::getHeight */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getActualLeft()
 * Type:       non-virtual method
 * Definition: int Ogre::Viewport::getActualLeft
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Viewport__1getActualLeft_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const int _cpp_result = _cpp_this->getActualLeft() ; 
   return _cpp_result;
} /* int Ogre::Viewport::getActualLeft */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getActualTop()
 * Type:       non-virtual method
 * Definition: int Ogre::Viewport::getActualTop
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Viewport__1getActualTop_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const int _cpp_result = _cpp_this->getActualTop() ; 
   return _cpp_result;
} /* int Ogre::Viewport::getActualTop */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getActualWidth()
 * Type:       non-virtual method
 * Definition: int Ogre::Viewport::getActualWidth
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Viewport__1getActualWidth_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const int _cpp_result = _cpp_this->getActualWidth() ; 
   return _cpp_result;
} /* int Ogre::Viewport::getActualWidth */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getActualHeight()
 * Type:       non-virtual method
 * Definition: int Ogre::Viewport::getActualHeight
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Viewport__1getActualHeight_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const int _cpp_result = _cpp_this->getActualHeight() ; 
   return _cpp_result;
} /* int Ogre::Viewport::getActualHeight */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     setDimensions()
 * Type:       non-virtual method
 * Definition: void Ogre::Viewport::setDimensions
 * Signature:  (floatfloatfloatfloat)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1setDimensions_1_1RealvRealvRealvRealv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat left, 
  jfloat top, 
  jfloat width, 
  jfloat height
)
{
   // parameter conversions 
  float _cpp_left = left;
  float _cpp_top = top;
  float _cpp_width = width;
  float _cpp_height = height; 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setDimensions(_cpp_left, _cpp_top, _cpp_width, _cpp_height);
} /* void Ogre::Viewport::setDimensions */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     setBackgroundColour()
 * Type:       non-virtual method
 * Definition: void Ogre::Viewport::setBackgroundColour
 * Signature:  (Ogre_ColourValue)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1setBackgroundColour_1_1ColourValueR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong colour
)
{
   // parameter conversions 
  const Ogre::ColourValue* _cpp_colour = reinterpret_cast< const Ogre::ColourValue* >(colour); 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setBackgroundColour(*_cpp_colour);
} /* void Ogre::Viewport::setBackgroundColour */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getBackgroundColour()
 * Type:       non-virtual method
 * Definition: const ColourValue& Ogre::Viewport::getBackgroundColour
 * Signature:  ()Ogre_ColourValue
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Viewport__1getBackgroundColour_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::ColourValue* _cpp_result = & _cpp_this->getBackgroundColour() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* const ColourValue& Ogre::Viewport::getBackgroundColour */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     setClearEveryFrame()
 * Type:       non-virtual method
 * Definition: void Ogre::Viewport::setClearEveryFrame
 * Signature:  (ZI)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1setClearEveryFrame_1_1bvIv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean clear, 
  jlong buffers
)
{
   // parameter conversions 
  bool _cpp_clear = clear ? true : false;
  unsigned int _cpp_buffers = buffers; 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setClearEveryFrame(_cpp_clear, _cpp_buffers);
} /* void Ogre::Viewport::setClearEveryFrame */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getClearEveryFrame()
 * Type:       non-virtual method
 * Definition: bool Ogre::Viewport::getClearEveryFrame
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Viewport__1getClearEveryFrame_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->getClearEveryFrame() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::Viewport::getClearEveryFrame */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getClearBuffers()
 * Type:       non-virtual method
 * Definition: unsigned int Ogre::Viewport::getClearBuffers
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Viewport__1getClearBuffers_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const unsigned int _cpp_result = _cpp_this->getClearBuffers() ; 
   return _cpp_result;
} /* unsigned int Ogre::Viewport::getClearBuffers */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     setMaterialScheme()
 * Type:       non-virtual method
 * Definition: void Ogre::Viewport::setMaterialScheme
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1setMaterialScheme_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring schemeName
)
{
   // parameter conversions 
  std::string _cpp_schemeName = ""; org::xbig::jni::to_stdstring(_jni_env_, schemeName, _cpp_schemeName); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setMaterialScheme(_cpp_schemeName);
} /* void Ogre::Viewport::setMaterialScheme */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getMaterialScheme()
 * Type:       non-virtual method
 * Definition: const String& Ogre::Viewport::getMaterialScheme
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_Viewport__1getMaterialScheme_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   std::string _cpp_result = _cpp_this->getMaterialScheme() ; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* const String& Ogre::Viewport::getMaterialScheme */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getActualDimensions()
 * Type:       non-virtual method
 * Definition: void Ogre::Viewport::getActualDimensions
 * Signature:  (IIII)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1getActualDimensions_1_1iriririr_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong left, 
  jlong top, 
  jlong width, 
  jlong height
)
{
   // parameter conversions 
  int* _cpp_left = reinterpret_cast<int*>(left);
  int* _cpp_top = reinterpret_cast<int*>(top);
  int* _cpp_width = reinterpret_cast<int*>(width);
  int* _cpp_height = reinterpret_cast<int*>(height); 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->getActualDimensions(*_cpp_left, *_cpp_top, *_cpp_width, *_cpp_height);
} /* void Ogre::Viewport::getActualDimensions */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     _isUpdated()
 * Type:       non-virtual method
 * Definition: bool Ogre::Viewport::_isUpdated
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Viewport__1_1isUpdated_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->_isUpdated() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::Viewport::_isUpdated */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     _clearUpdatedFlag()
 * Type:       non-virtual method
 * Definition: void Ogre::Viewport::_clearUpdatedFlag
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1_1clearUpdatedFlag (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->_clearUpdatedFlag();
} /* void Ogre::Viewport::_clearUpdatedFlag */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     _getNumRenderedFaces()
 * Type:       non-virtual method
 * Definition: unsigned int Ogre::Viewport::_getNumRenderedFaces
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Viewport__1_1getNumRenderedFaces_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const unsigned int _cpp_result = _cpp_this->_getNumRenderedFaces() ; 
   return _cpp_result;
} /* unsigned int Ogre::Viewport::_getNumRenderedFaces */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     _getNumRenderedBatches()
 * Type:       non-virtual method
 * Definition: unsigned int Ogre::Viewport::_getNumRenderedBatches
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Viewport__1_1getNumRenderedBatches_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const unsigned int _cpp_result = _cpp_this->_getNumRenderedBatches() ; 
   return _cpp_result;
} /* unsigned int Ogre::Viewport::_getNumRenderedBatches */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     setOverlaysEnabled()
 * Type:       non-virtual method
 * Definition: void Ogre::Viewport::setOverlaysEnabled
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1setOverlaysEnabled_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean enabled
)
{
   // parameter conversions 
  bool _cpp_enabled = enabled ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setOverlaysEnabled(_cpp_enabled);
} /* void Ogre::Viewport::setOverlaysEnabled */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getOverlaysEnabled()
 * Type:       non-virtual method
 * Definition: bool Ogre::Viewport::getOverlaysEnabled
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Viewport__1getOverlaysEnabled_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->getOverlaysEnabled() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::Viewport::getOverlaysEnabled */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     setSkiesEnabled()
 * Type:       non-virtual method
 * Definition: void Ogre::Viewport::setSkiesEnabled
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1setSkiesEnabled_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean enabled
)
{
   // parameter conversions 
  bool _cpp_enabled = enabled ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setSkiesEnabled(_cpp_enabled);
} /* void Ogre::Viewport::setSkiesEnabled */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getSkiesEnabled()
 * Type:       non-virtual method
 * Definition: bool Ogre::Viewport::getSkiesEnabled
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Viewport__1getSkiesEnabled_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->getSkiesEnabled() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::Viewport::getSkiesEnabled */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     setShadowsEnabled()
 * Type:       non-virtual method
 * Definition: void Ogre::Viewport::setShadowsEnabled
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1setShadowsEnabled_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean enabled
)
{
   // parameter conversions 
  bool _cpp_enabled = enabled ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setShadowsEnabled(_cpp_enabled);
} /* void Ogre::Viewport::setShadowsEnabled */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getShadowsEnabled()
 * Type:       non-virtual method
 * Definition: bool Ogre::Viewport::getShadowsEnabled
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Viewport__1getShadowsEnabled_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->getShadowsEnabled() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::Viewport::getShadowsEnabled */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     setVisibilityMask()
 * Type:       non-virtual method
 * Definition: void Ogre::Viewport::setVisibilityMask
 * Signature:  (unsigned_int)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1setVisibilityMask_1_1uint32v (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong mask
)
{
   // parameter conversions 
  unsigned int _cpp_mask = mask; 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setVisibilityMask(_cpp_mask);
} /* void Ogre::Viewport::setVisibilityMask */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getVisibilityMask()
 * Type:       non-virtual method
 * Definition: uint Ogre::Viewport::getVisibilityMask
 * Signature:  ()unsigned_int
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Viewport__1getVisibilityMask_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const unsigned int _cpp_result = _cpp_this->getVisibilityMask() ; 
   return _cpp_result;
} /* uint Ogre::Viewport::getVisibilityMask */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     setRenderQueueInvocationSequenceName()
 * Type:       virtual method
 * Definition: virtual void Ogre::Viewport::setRenderQueueInvocationSequenceName
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1setRenderQueueInvocationSequenceName_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring sequenceName
)
{
   // parameter conversions 
  std::string _cpp_sequenceName = ""; org::xbig::jni::to_stdstring(_jni_env_, sequenceName, _cpp_sequenceName); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setRenderQueueInvocationSequenceName(_cpp_sequenceName);
} /* virtual void Ogre::Viewport::setRenderQueueInvocationSequenceName */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     getRenderQueueInvocationSequenceName()
 * Type:       virtual method
 * Definition: virtual const String& Ogre::Viewport::getRenderQueueInvocationSequenceName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_Viewport__1getRenderQueueInvocationSequenceName_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::Viewport* _cpp_this = reinterpret_cast<const Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   std::string _cpp_result = _cpp_this->getRenderQueueInvocationSequenceName() ; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* virtual const String& Ogre::Viewport::getRenderQueueInvocationSequenceName */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     _getRenderQueueInvocationSequence()
 * Type:       non-virtual method
 * Definition: RenderQueueInvocationSequence* Ogre::Viewport::_getRenderQueueInvocationSequence
 * Signature:  ()Ogre_RenderQueueInvocationSequence
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Viewport__1_1getRenderQueueInvocationSequence (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::RenderQueueInvocationSequence* _cpp_result = _cpp_this->_getRenderQueueInvocationSequence() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* RenderQueueInvocationSequence* Ogre::Viewport::_getRenderQueueInvocationSequence */


/*
 * Class:      org.ogre4j.Viewport
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::Viewport::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Viewport__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::Viewport 
   // cast pointer to C++ object 
   Ogre::Viewport* _cpp_this = reinterpret_cast<Ogre::Viewport*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::Viewport::__delete */
