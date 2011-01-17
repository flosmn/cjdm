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
#include "class_org_ogre4j_FocusedShadowCameraSetup.h"

// import header files of original library
#include <OgreShadowCameraSetupFocused.h>



/*
 * Class:      org.ogre4j.FocusedShadowCameraSetup
 * Method:     FocusedShadowCameraSetup()
 * Type:       constructor
 * Definition: Ogre::FocusedShadowCameraSetup::FocusedShadowCameraSetup
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_FocusedShadowCameraSetup__1_1createFocusedShadowCameraSetup (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::FocusedShadowCameraSetup 
   
   // parameter conversions 
   
   // create new instance of class Ogre::FocusedShadowCameraSetup 
   Ogre::FocusedShadowCameraSetup* _cpp_this = new Ogre::FocusedShadowCameraSetup(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::FocusedShadowCameraSetup::FocusedShadowCameraSetup */


/*
 * Class:      org.ogre4j.FocusedShadowCameraSetup
 * Method:     getShadowCamera()
 * Type:       virtual method
 * Definition: virtual void Ogre::FocusedShadowCameraSetup::getShadowCamera
 * Signature:  (Ogre_SceneManagerOgre_CameraOgre_ViewportOgre_LightOgre_CameraI)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_FocusedShadowCameraSetup__1getShadowCamera_1_1SceneManagerPCameraPViewportPLightPCamerapiv_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong sm, 
  jlong cam, 
  jlong vp, 
  jlong light, 
  jlong texCam, 
  jint iteration
)
{
   // parameter conversions 
  const Ogre::SceneManager* _cpp_sm = reinterpret_cast< const Ogre::SceneManager* >(sm);
  const Ogre::Camera* _cpp_cam = reinterpret_cast< const Ogre::Camera* >(cam);
  const Ogre::Viewport* _cpp_vp = reinterpret_cast< const Ogre::Viewport* >(vp);
  const Ogre::Light* _cpp_light = reinterpret_cast< const Ogre::Light* >(light);
  Ogre::Camera* _cpp_texCam = reinterpret_cast< Ogre::Camera* >(texCam);
  size_t _cpp_iteration = iteration; 
   
   // cast pointer to C++ object 
   const Ogre::FocusedShadowCameraSetup* _cpp_this = reinterpret_cast<const Ogre::FocusedShadowCameraSetup*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->getShadowCamera(_cpp_sm, _cpp_cam, _cpp_vp, _cpp_light, _cpp_texCam, _cpp_iteration);
} /* virtual void Ogre::FocusedShadowCameraSetup::getShadowCamera */


/*
 * Class:      org.ogre4j.FocusedShadowCameraSetup
 * Method:     setUseAggressiveFocusRegion()
 * Type:       non-virtual method
 * Definition: void Ogre::FocusedShadowCameraSetup::setUseAggressiveFocusRegion
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_FocusedShadowCameraSetup__1setUseAggressiveFocusRegion_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean aggressive
)
{
   // parameter conversions 
  bool _cpp_aggressive = aggressive ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::FocusedShadowCameraSetup* _cpp_this = reinterpret_cast<Ogre::FocusedShadowCameraSetup*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setUseAggressiveFocusRegion(_cpp_aggressive);
} /* void Ogre::FocusedShadowCameraSetup::setUseAggressiveFocusRegion */


/*
 * Class:      org.ogre4j.FocusedShadowCameraSetup
 * Method:     getUseAggressiveFocusRegion()
 * Type:       non-virtual method
 * Definition: bool Ogre::FocusedShadowCameraSetup::getUseAggressiveFocusRegion
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_FocusedShadowCameraSetup__1getUseAggressiveFocusRegion_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::FocusedShadowCameraSetup* _cpp_this = reinterpret_cast<const Ogre::FocusedShadowCameraSetup*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->getUseAggressiveFocusRegion() ; 
   return _cpp_result ? 1 : 0;
} /* bool Ogre::FocusedShadowCameraSetup::getUseAggressiveFocusRegion */


/*
 * Class:      org.ogre4j.FocusedShadowCameraSetup
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::FocusedShadowCameraSetup::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_FocusedShadowCameraSetup__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::FocusedShadowCameraSetup 
   // cast pointer to C++ object 
   Ogre::FocusedShadowCameraSetup* _cpp_this = reinterpret_cast<Ogre::FocusedShadowCameraSetup*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::FocusedShadowCameraSetup::__delete */
