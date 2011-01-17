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
#include "class_org_ogre4j_RenderTexture.h"

// import header files of original library
#include <OgreRenderTexture.h>



/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     copyContentsToMemory()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTexture::copyContentsToMemory
 * Signature:  (Ogre_PixelBoxOgre_RenderTarget_FrameBuffer)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1copyContentsToMemory_1_1PixelBoxRFrameBufferv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong dst, 
  jint buffer
)
{
   // parameter conversions 
  const Ogre::PixelBox* _cpp_dst = reinterpret_cast< const Ogre::PixelBox* >(dst);
  Ogre::RenderTarget::FrameBuffer _cpp_buffer = (Ogre::RenderTarget::FrameBuffer)buffer; 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->copyContentsToMemory(*_cpp_dst, _cpp_buffer);
} /* virtual void Ogre::RenderTexture::copyContentsToMemory */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     suggestPixelFormat()
 * Type:       virtual method
 * Definition: PixelFormat Ogre::RenderTexture::suggestPixelFormat
 * Signature:  ()Ogre_PixelFormat
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderTexture__1suggestPixelFormat_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::PixelFormat _cpp_result = _cpp_this->suggestPixelFormat() ; 
   return _cpp_result;
} /* PixelFormat Ogre::RenderTexture::suggestPixelFormat */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getName()
 * Type:       virtual method
 * Definition: virtual const String& Ogre::RenderTarget::getName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_RenderTexture__1getName_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   std::string _cpp_result = _cpp_this->getName() ; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* virtual const String& Ogre::RenderTarget::getName */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getMetrics()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::getMetrics
 * Signature:  (III)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1getMetrics_1_1IrIrIr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong width, 
  jlong height, 
  jlong colourDepth
)
{
   // parameter conversions 
  unsigned int* _cpp_width = reinterpret_cast<unsigned int*>(width);
  unsigned int* _cpp_height = reinterpret_cast<unsigned int*>(height);
  unsigned int* _cpp_colourDepth = reinterpret_cast<unsigned int*>(colourDepth); 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->getMetrics(*_cpp_width, *_cpp_height, *_cpp_colourDepth);
} /* virtual void Ogre::RenderTarget::getMetrics */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getWidth()
 * Type:       virtual method
 * Definition: virtual unsigned int Ogre::RenderTarget::getWidth
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderTexture__1getWidth_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const unsigned int _cpp_result = _cpp_this->getWidth() ; 
   return _cpp_result;
} /* virtual unsigned int Ogre::RenderTarget::getWidth */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getHeight()
 * Type:       virtual method
 * Definition: virtual unsigned int Ogre::RenderTarget::getHeight
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderTexture__1getHeight_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const unsigned int _cpp_result = _cpp_this->getHeight() ; 
   return _cpp_result;
} /* virtual unsigned int Ogre::RenderTarget::getHeight */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getColourDepth()
 * Type:       virtual method
 * Definition: virtual unsigned int Ogre::RenderTarget::getColourDepth
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderTexture__1getColourDepth_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const unsigned int _cpp_result = _cpp_this->getColourDepth() ; 
   return _cpp_result;
} /* virtual unsigned int Ogre::RenderTarget::getColourDepth */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     update()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::update
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1update_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean swapBuffers
)
{
   // parameter conversions 
  bool _cpp_swapBuffers = swapBuffers ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->update(_cpp_swapBuffers);
} /* virtual void Ogre::RenderTarget::update */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     swapBuffers()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::swapBuffers
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1swapBuffers_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean waitForVSync
)
{
   // parameter conversions 
  bool _cpp_waitForVSync = waitForVSync ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->swapBuffers(_cpp_waitForVSync);
} /* virtual void Ogre::RenderTarget::swapBuffers */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     addViewport()
 * Type:       virtual method
 * Definition: virtual Viewport* Ogre::RenderTarget::addViewport
 * Signature:  (Ogre_CameraIFFFF)Ogre_Viewport
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderTexture__1addViewport_1_1CamerapivFvFvFvFv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong cam, 
  jint ZOrder, 
  jfloat left, 
  jfloat top, 
  jfloat width, 
  jfloat height
)
{
   // parameter conversions 
  Ogre::Camera* _cpp_cam = reinterpret_cast< Ogre::Camera* >(cam);
  int _cpp_ZOrder = ZOrder;
  float _cpp_left = left;
  float _cpp_top = top;
  float _cpp_width = width;
  float _cpp_height = height; 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::Viewport* _cpp_result = _cpp_this->addViewport(_cpp_cam, _cpp_ZOrder, _cpp_left, _cpp_top, _cpp_width, _cpp_height) ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* virtual Viewport* Ogre::RenderTarget::addViewport */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getNumViewports()
 * Type:       virtual method
 * Definition: virtual unsigned short Ogre::RenderTarget::getNumViewports
 * Signature:  ()S
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderTexture__1getNumViewports_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const unsigned short _cpp_result = _cpp_this->getNumViewports() ; 
   return _cpp_result;
} /* virtual unsigned short Ogre::RenderTarget::getNumViewports */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getViewport()
 * Type:       virtual method
 * Definition: virtual Viewport* Ogre::RenderTarget::getViewport
 * Signature:  (S)Ogre_Viewport
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderTexture__1getViewport_1_1Hv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint index
)
{
   // parameter conversions 
  unsigned short _cpp_index = index; 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::Viewport* _cpp_result = _cpp_this->getViewport(_cpp_index) ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* virtual Viewport* Ogre::RenderTarget::getViewport */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     removeViewport()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::removeViewport
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1removeViewport_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint ZOrder
)
{
   // parameter conversions 
  int _cpp_ZOrder = ZOrder; 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->removeViewport(_cpp_ZOrder);
} /* virtual void Ogre::RenderTarget::removeViewport */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     removeAllViewports()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::removeAllViewports
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1removeAllViewports (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->removeAllViewports();
} /* virtual void Ogre::RenderTarget::removeAllViewports */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getStatistics()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::getStatistics
 * Signature:  (FFFF)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1getStatistics_1_1FrFrFrFr_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong lastFPS, 
  jlong avgFPS, 
  jlong bestFPS, 
  jlong worstFPS
)
{
   // parameter conversions 
  float* _cpp_lastFPS = reinterpret_cast<float*>(lastFPS);
  float* _cpp_avgFPS = reinterpret_cast<float*>(avgFPS);
  float* _cpp_bestFPS = reinterpret_cast<float*>(bestFPS);
  float* _cpp_worstFPS = reinterpret_cast<float*>(worstFPS); 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->Ogre::RenderTarget::getStatistics(*_cpp_lastFPS, *_cpp_avgFPS, *_cpp_bestFPS, *_cpp_worstFPS);
} /* virtual void Ogre::RenderTarget::getStatistics */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getStatistics()
 * Type:       virtual method
 * Definition: virtual const FrameStats& Ogre::RenderTarget::getStatistics
 * Signature:  ()Ogre_RenderTarget_FrameStats
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderTexture__1getStatistics_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::RenderTarget::FrameStats* _cpp_result = & _cpp_this->Ogre::RenderTarget::getStatistics() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* virtual const FrameStats& Ogre::RenderTarget::getStatistics */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getLastFPS()
 * Type:       virtual method
 * Definition: virtual float Ogre::RenderTarget::getLastFPS
 * Signature:  ()F
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_RenderTexture__1getLastFPS_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getLastFPS() ; 
   return _cpp_result;
} /* virtual float Ogre::RenderTarget::getLastFPS */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getAverageFPS()
 * Type:       virtual method
 * Definition: virtual float Ogre::RenderTarget::getAverageFPS
 * Signature:  ()F
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_RenderTexture__1getAverageFPS_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getAverageFPS() ; 
   return _cpp_result;
} /* virtual float Ogre::RenderTarget::getAverageFPS */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getBestFPS()
 * Type:       virtual method
 * Definition: virtual float Ogre::RenderTarget::getBestFPS
 * Signature:  ()F
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_RenderTexture__1getBestFPS_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getBestFPS() ; 
   return _cpp_result;
} /* virtual float Ogre::RenderTarget::getBestFPS */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getWorstFPS()
 * Type:       virtual method
 * Definition: virtual float Ogre::RenderTarget::getWorstFPS
 * Signature:  ()F
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_RenderTexture__1getWorstFPS_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getWorstFPS() ; 
   return _cpp_result;
} /* virtual float Ogre::RenderTarget::getWorstFPS */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getBestFrameTime()
 * Type:       virtual method
 * Definition: virtual float Ogre::RenderTarget::getBestFrameTime
 * Signature:  ()F
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_RenderTexture__1getBestFrameTime_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getBestFrameTime() ; 
   return _cpp_result;
} /* virtual float Ogre::RenderTarget::getBestFrameTime */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getWorstFrameTime()
 * Type:       virtual method
 * Definition: virtual float Ogre::RenderTarget::getWorstFrameTime
 * Signature:  ()F
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_RenderTexture__1getWorstFrameTime_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const float _cpp_result = _cpp_this->getWorstFrameTime() ; 
   return _cpp_result;
} /* virtual float Ogre::RenderTarget::getWorstFrameTime */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     resetStatistics()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::resetStatistics
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1resetStatistics (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->resetStatistics();
} /* virtual void Ogre::RenderTarget::resetStatistics */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getCustomAttribute()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::getCustomAttribute
 * Signature:  (std_stringV)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1getCustomAttribute_1_1StringRvp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jlong pData
)
{
   // parameter conversions 
  std::string _cpp_name = ""; org::xbig::jni::to_stdstring(_jni_env_, name, _cpp_name); // calls c-tor only. Not operator= .;
  void* _cpp_pData = reinterpret_cast<void*>(pData); 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->getCustomAttribute(_cpp_name, _cpp_pData);
} /* virtual void Ogre::RenderTarget::getCustomAttribute */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     addListener()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::addListener
 * Signature:  (Ogre_RenderTargetListener)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1addListener_1_1RenderTargetListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong listener
)
{
   // parameter conversions 
  Ogre::RenderTargetListener* _cpp_listener = reinterpret_cast< Ogre::RenderTargetListener* >(listener); 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->addListener(_cpp_listener);
} /* virtual void Ogre::RenderTarget::addListener */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     removeListener()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::removeListener
 * Signature:  (Ogre_RenderTargetListener)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1removeListener_1_1RenderTargetListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong listener
)
{
   // parameter conversions 
  Ogre::RenderTargetListener* _cpp_listener = reinterpret_cast< Ogre::RenderTargetListener* >(listener); 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->removeListener(_cpp_listener);
} /* virtual void Ogre::RenderTarget::removeListener */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     removeAllListeners()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::removeAllListeners
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1removeAllListeners (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->removeAllListeners();
} /* virtual void Ogre::RenderTarget::removeAllListeners */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     setPriority()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::setPriority
 * Signature:  (unsigned_char)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1setPriority_1_1ucharv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jshort priority
)
{
   // parameter conversions 
  unsigned char _cpp_priority = priority; 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setPriority(_cpp_priority);
} /* virtual void Ogre::RenderTarget::setPriority */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getPriority()
 * Type:       virtual method
 * Definition: virtual uchar Ogre::RenderTarget::getPriority
 * Signature:  ()unsigned_char
 */

JNIEXPORT jshort JNICALL Java_org_ogre4j_RenderTexture__1getPriority_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const unsigned char _cpp_result = _cpp_this->getPriority() ; 
   return _cpp_result;
} /* virtual uchar Ogre::RenderTarget::getPriority */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     isActive()
 * Type:       virtual method
 * Definition: virtual bool Ogre::RenderTarget::isActive
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderTexture__1isActive_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->isActive() ; 
   return _cpp_result ? 1 : 0;
} /* virtual bool Ogre::RenderTarget::isActive */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     setActive()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::setActive
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1setActive_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean state
)
{
   // parameter conversions 
  bool _cpp_state = state ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setActive(_cpp_state);
} /* virtual void Ogre::RenderTarget::setActive */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     setAutoUpdated()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::setAutoUpdated
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1setAutoUpdated_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean autoupdate
)
{
   // parameter conversions 
  bool _cpp_autoupdate = autoupdate ? true : false; 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->setAutoUpdated(_cpp_autoupdate);
} /* virtual void Ogre::RenderTarget::setAutoUpdated */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     isAutoUpdated()
 * Type:       virtual method
 * Definition: virtual bool Ogre::RenderTarget::isAutoUpdated
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderTexture__1isAutoUpdated_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->isAutoUpdated() ; 
   return _cpp_result ? 1 : 0;
} /* virtual bool Ogre::RenderTarget::isAutoUpdated */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     writeContentsToFile()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderTarget::writeContentsToFile
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1writeContentsToFile_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring filename
)
{
   // parameter conversions 
  std::string _cpp_filename = ""; org::xbig::jni::to_stdstring(_jni_env_, filename, _cpp_filename); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->writeContentsToFile(_cpp_filename);
} /* void Ogre::RenderTarget::writeContentsToFile */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     writeContentsToTimestampedFile()
 * Type:       virtual method
 * Definition: virtual String Ogre::RenderTarget::writeContentsToTimestampedFile
 * Signature:  (std_stringstd_string)std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_RenderTexture__1writeContentsToTimestampedFile_1_1StringRStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring filenamePrefix, 
  jstring filenameSuffix
)
{
   // parameter conversions 
  std::string _cpp_filenamePrefix = ""; org::xbig::jni::to_stdstring(_jni_env_, filenamePrefix, _cpp_filenamePrefix); // calls c-tor only. Not operator= .;
  std::string _cpp_filenameSuffix = ""; org::xbig::jni::to_stdstring(_jni_env_, filenameSuffix, _cpp_filenameSuffix); // calls c-tor only. Not operator= .; 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const std::string _cpp_result = _cpp_this->writeContentsToTimestampedFile(_cpp_filenamePrefix, _cpp_filenameSuffix) ; 
   return org::xbig::jni::to_jstring(_jni_env_, _cpp_result);
} /* virtual String Ogre::RenderTarget::writeContentsToTimestampedFile */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     requiresTextureFlipping()
 * Type:       pure virtual method
 * Definition: virtual bool Ogre::RenderTarget::requiresTextureFlipping
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderTexture__1requiresTextureFlipping_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->requiresTextureFlipping() ; 
   return _cpp_result ? 1 : 0;
} /* virtual bool Ogre::RenderTarget::requiresTextureFlipping */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getTriangleCount()
 * Type:       virtual method
 * Definition: virtual size_t Ogre::RenderTarget::getTriangleCount
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderTexture__1getTriangleCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->getTriangleCount() ; 
   return _cpp_result;
} /* virtual size_t Ogre::RenderTarget::getTriangleCount */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getBatchCount()
 * Type:       virtual method
 * Definition: virtual size_t Ogre::RenderTarget::getBatchCount
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderTexture__1getBatchCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const size_t _cpp_result = _cpp_this->getBatchCount() ; 
   return _cpp_result;
} /* virtual size_t Ogre::RenderTarget::getBatchCount */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     _notifyCameraRemoved()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::_notifyCameraRemoved
 * Signature:  (Ogre_Camera)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1_1notifyCameraRemoved_1_1CameraP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong cam
)
{
   // parameter conversions 
  const Ogre::Camera* _cpp_cam = reinterpret_cast< const Ogre::Camera* >(cam); 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   _cpp_this->_notifyCameraRemoved(_cpp_cam);
} /* virtual void Ogre::RenderTarget::_notifyCameraRemoved */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     isPrimary()
 * Type:       virtual method
 * Definition: virtual bool Ogre::RenderTarget::isPrimary
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderTexture__1isPrimary_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->isPrimary() ; 
   return _cpp_result ? 1 : 0;
} /* virtual bool Ogre::RenderTarget::isPrimary */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     isHardwareGammaEnabled()
 * Type:       virtual method
 * Definition: virtual bool Ogre::RenderTarget::isHardwareGammaEnabled
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderTexture__1isHardwareGammaEnabled_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const bool _cpp_result = _cpp_this->isHardwareGammaEnabled() ; 
   return _cpp_result ? 1 : 0;
} /* virtual bool Ogre::RenderTarget::isHardwareGammaEnabled */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     getFSAA()
 * Type:       virtual method
 * Definition: virtual uint Ogre::RenderTarget::getFSAA
 * Signature:  ()unsigned_int
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderTexture__1getFSAA_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   const Ogre::RenderTexture* _cpp_this = reinterpret_cast<const Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const unsigned int _cpp_result = _cpp_this->getFSAA() ; 
   return _cpp_result;
} /* virtual uint Ogre::RenderTarget::getFSAA */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     _getImpl()
 * Type:       virtual method
 * Definition: virtual Impl* Ogre::RenderTarget::_getImpl
 * Signature:  ()Ogre_RenderTarget_Impl
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderTexture__1_1getImpl (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // parameter conversions 
   
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   
   // call library method 
   const Ogre::RenderTarget::Impl* _cpp_result = _cpp_this->_getImpl() ; 
   return reinterpret_cast<jlong>(_cpp_result);
} /* virtual Impl* Ogre::RenderTarget::_getImpl */


/*
 * Class:      org.ogre4j.RenderTexture
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::RenderTexture::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderTexture__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
)
{
   // destructor of class Ogre::RenderTexture 
   // cast pointer to C++ object 
   Ogre::RenderTexture* _cpp_this = reinterpret_cast<Ogre::RenderTexture*>(_jni_pointer_); 
   // delete object if it exists 
   if(_cpp_this != NULL) delete _cpp_this;
} /* Ogre::RenderTexture::__delete */
