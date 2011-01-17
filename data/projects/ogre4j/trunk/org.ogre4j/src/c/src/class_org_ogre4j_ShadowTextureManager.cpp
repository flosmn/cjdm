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
#include "class_org_ogre4j_ShadowTextureManager.h"

// import header files of original library
#include <OgreShadowTextureManager.h>



/*
 * Class:      org.ogre4j.ShadowTextureManager
 * Method:     ShadowTextureManager()
 * Type:       constructor
 * Definition: Ogre::ShadowTextureManager::ShadowTextureManager
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ShadowTextureManager__1_1createShadowTextureManager (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // constructor of class Ogre::ShadowTextureManager 
   
   // parameter conversions 
   
   // create new instance of class Ogre::ShadowTextureManager 
   Ogre::ShadowTextureManager* _cpp_this = new Ogre::ShadowTextureManager(); 
   
   // return casted pointer 
   jlong _jni_pointer_ = reinterpret_cast<jlong>(_cpp_this); 
   return _jni_pointer_;
} /* Ogre::ShadowTextureManager::ShadowTextureManager */


/*
 * Class:      org.ogre4j.ShadowTextureManager
 * Method:     getShadowTextures()
 * Type:       virtual method
 * Definition: virtual void Ogre::ShadowTextureManager::getShadowTextures
 * Signature:  (Ogre_ShadowTextureConfigListOgre_ShadowTextureList)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ShadowTextureManager__1getShadowTextures_1_1ShadowTextureConfigListRShadowTextureListr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong config, 
  jlong listToPopulate
)
{
   // parameter conversions 
  const Ogre::ShadowTextureConfigList* _cpp_config = reinterpret_cast< const Ogre::ShadowTextureConfigList* >(config);
  Ogre::ShadowTextureList* _cpp_listToPopulate = reinterpret_cast< Ogre::ShadowTextureList* >(listToPopulate); 
   
   // cast pointer to C++ object 
   Ogre::ShadowTextureManager* _cpp_this = reinterpret_cast<Ogre::ShadowTextureManager*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->getShadowTextures(*_cpp_config, *_cpp_listToPopulate);
} /* virtual void Ogre::ShadowTextureManager::getShadowTextures */


/*
 * Class:      org.ogre4j.ShadowTextureManager
 * Method:     getNullShadowTexture()
 * Type:       virtual method
 * Definition: virtual TexturePtr Ogre::ShadowTextureManager::getNullShadowTexture
 * Signature:  (Ogre_PixelFormat)Ogre_TexturePtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ShadowTextureManager__1getNullShadowTexture_1_1PixelFormatv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint format
)
{
   // parameter conversions 
  Ogre::PixelFormat _cpp_format = (Ogre::PixelFormat)format; 
   
   // cast pointer to C++ object 
   Ogre::ShadowTextureManager* _cpp_this = reinterpret_cast<Ogre::ShadowTextureManager*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::TexturePtr* _cpp_result = new Ogre::TexturePtr( _cpp_this->getNullShadowTexture(_cpp_format) ); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* virtual TexturePtr Ogre::ShadowTextureManager::getNullShadowTexture */


/*
 * Class:      org.ogre4j.ShadowTextureManager
 * Method:     clearUnused()
 * Type:       virtual method
 * Definition: virtual void Ogre::ShadowTextureManager::clearUnused
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ShadowTextureManager__1clearUnused (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ShadowTextureManager* _cpp_this = reinterpret_cast<Ogre::ShadowTextureManager*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->clearUnused();
} /* virtual void Ogre::ShadowTextureManager::clearUnused */


/*
 * Class:      org.ogre4j.ShadowTextureManager
 * Method:     clear()
 * Type:       virtual method
 * Definition: virtual void Ogre::ShadowTextureManager::clear
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ShadowTextureManager__1clear (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::ShadowTextureManager* _cpp_this = reinterpret_cast<Ogre::ShadowTextureManager*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->clear();
} /* virtual void Ogre::ShadowTextureManager::clear */


/*
 * Class:      org.ogre4j.ShadowTextureManager
 * Method:     getSingleton()
 * Type:       static method
 * Definition: static ShadowTextureManager& Ogre::ShadowTextureManager::getSingleton
 * Signature:  ()Ogre_ShadowTextureManager
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ShadowTextureManager__1getSingleton (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // static method of class Ogre::ShadowTextureManager with return value 
   // parameter conversions 
   
   // call library method 
   const Ogre::ShadowTextureManager* _cpp_result = & Ogre::ShadowTextureManager::getSingleton(); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* static ShadowTextureManager& Ogre::ShadowTextureManager::getSingleton */


/*
 * Class:      org.ogre4j.ShadowTextureManager
 * Method:     getSingletonPtr()
 * Type:       static method
 * Definition: static ShadowTextureManager* Ogre::ShadowTextureManager::getSingletonPtr
 * Signature:  ()Ogre_ShadowTextureManager
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ShadowTextureManager__1getSingletonPtr (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
)
{
   // static method of class Ogre::ShadowTextureManager with return value 
   // parameter conversions 
   
   // call library method 
   const Ogre::ShadowTextureManager* _cpp_result = Ogre::ShadowTextureManager::getSingletonPtr(); 
   return reinterpret_cast<jlong>(_cpp_result);
} /* static ShadowTextureManager* Ogre::ShadowTextureManager::getSingletonPtr */


/*
 * Class:      org.ogre4j.ShadowTextureManager
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ShadowTextureManager::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ShadowTextureManager__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::ShadowTextureManager 
   // cast pointer to C++ object 
   Ogre::ShadowTextureManager* _cpp_this = reinterpret_cast<Ogre::ShadowTextureManager*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::ShadowTextureManager::__delete */
