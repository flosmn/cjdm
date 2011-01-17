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


#ifndef __Included_org_ogre4j_MultiRenderTarget__
#define __Included_org_ogre4j_MultiRenderTarget__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     bindSurface()
 * Type:       virtual method
 * Definition: virtual void Ogre::MultiRenderTarget::bindSurface
 * Signature:  (IOgre_RenderTexture)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1bindSurface_1_1ivRenderTexturep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint attachment, 
  jlong target
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     unbindSurface()
 * Type:       virtual method
 * Definition: virtual void Ogre::MultiRenderTarget::unbindSurface
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1unbindSurface_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint attachment
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     copyContentsToMemory()
 * Type:       virtual method
 * Definition: virtual void Ogre::MultiRenderTarget::copyContentsToMemory
 * Signature:  (Ogre_PixelBoxOgre_RenderTarget_FrameBuffer)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1copyContentsToMemory_1_1PixelBoxRFrameBufferv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong dst, 
  jint buffer
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     suggestPixelFormat()
 * Type:       virtual method
 * Definition: PixelFormat Ogre::MultiRenderTarget::suggestPixelFormat
 * Signature:  ()Ogre_PixelFormat
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_MultiRenderTarget__1suggestPixelFormat_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getBoundSurfaceList()
 * Type:       non-virtual method
 * Definition: const BoundSufaceList& Ogre::MultiRenderTarget::getBoundSurfaceList
 * Signature:  ()Ogre_MultiRenderTarget_BoundSufaceList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MultiRenderTarget__1getBoundSurfaceList_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getBoundSurface()
 * Type:       non-virtual method
 * Definition: RenderTexture* Ogre::MultiRenderTarget::getBoundSurface
 * Signature:  (I)Ogre_RenderTexture
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MultiRenderTarget__1getBoundSurface_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint index
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getName()
 * Type:       virtual method
 * Definition: virtual const String& Ogre::RenderTarget::getName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_MultiRenderTarget__1getName_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getMetrics()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::getMetrics
 * Signature:  (III)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1getMetrics_1_1IrIrIr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong width, 
  jlong height, 
  jlong colourDepth
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getWidth()
 * Type:       virtual method
 * Definition: virtual unsigned int Ogre::RenderTarget::getWidth
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MultiRenderTarget__1getWidth_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getHeight()
 * Type:       virtual method
 * Definition: virtual unsigned int Ogre::RenderTarget::getHeight
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MultiRenderTarget__1getHeight_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getColourDepth()
 * Type:       virtual method
 * Definition: virtual unsigned int Ogre::RenderTarget::getColourDepth
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MultiRenderTarget__1getColourDepth_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     update()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::update
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1update_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean swapBuffers
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     swapBuffers()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::swapBuffers
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1swapBuffers_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean waitForVSync
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     addViewport()
 * Type:       virtual method
 * Definition: virtual Viewport* Ogre::RenderTarget::addViewport
 * Signature:  (Ogre_CameraIFFFF)Ogre_Viewport
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MultiRenderTarget__1addViewport_1_1CamerapivFvFvFvFv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong cam, 
  jint ZOrder, 
  jfloat left, 
  jfloat top, 
  jfloat width, 
  jfloat height
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getNumViewports()
 * Type:       virtual method
 * Definition: virtual unsigned short Ogre::RenderTarget::getNumViewports
 * Signature:  ()S
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_MultiRenderTarget__1getNumViewports_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getViewport()
 * Type:       virtual method
 * Definition: virtual Viewport* Ogre::RenderTarget::getViewport
 * Signature:  (S)Ogre_Viewport
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MultiRenderTarget__1getViewport_1_1Hv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint index
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     removeViewport()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::removeViewport
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1removeViewport_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint ZOrder
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     removeAllViewports()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::removeAllViewports
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1removeAllViewports (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getStatistics()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::getStatistics
 * Signature:  (FFFF)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1getStatistics_1_1FrFrFrFr_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong lastFPS, 
  jlong avgFPS, 
  jlong bestFPS, 
  jlong worstFPS
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getStatistics()
 * Type:       virtual method
 * Definition: virtual const FrameStats& Ogre::RenderTarget::getStatistics
 * Signature:  ()Ogre_RenderTarget_FrameStats
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MultiRenderTarget__1getStatistics_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getLastFPS()
 * Type:       virtual method
 * Definition: virtual float Ogre::RenderTarget::getLastFPS
 * Signature:  ()F
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_MultiRenderTarget__1getLastFPS_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getAverageFPS()
 * Type:       virtual method
 * Definition: virtual float Ogre::RenderTarget::getAverageFPS
 * Signature:  ()F
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_MultiRenderTarget__1getAverageFPS_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getBestFPS()
 * Type:       virtual method
 * Definition: virtual float Ogre::RenderTarget::getBestFPS
 * Signature:  ()F
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_MultiRenderTarget__1getBestFPS_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getWorstFPS()
 * Type:       virtual method
 * Definition: virtual float Ogre::RenderTarget::getWorstFPS
 * Signature:  ()F
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_MultiRenderTarget__1getWorstFPS_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getBestFrameTime()
 * Type:       virtual method
 * Definition: virtual float Ogre::RenderTarget::getBestFrameTime
 * Signature:  ()F
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_MultiRenderTarget__1getBestFrameTime_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getWorstFrameTime()
 * Type:       virtual method
 * Definition: virtual float Ogre::RenderTarget::getWorstFrameTime
 * Signature:  ()F
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_MultiRenderTarget__1getWorstFrameTime_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     resetStatistics()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::resetStatistics
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1resetStatistics (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getCustomAttribute()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::getCustomAttribute
 * Signature:  (std_stringV)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1getCustomAttribute_1_1StringRvp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jlong pData
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     addListener()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::addListener
 * Signature:  (Ogre_RenderTargetListener)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1addListener_1_1RenderTargetListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong listener
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     removeListener()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::removeListener
 * Signature:  (Ogre_RenderTargetListener)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1removeListener_1_1RenderTargetListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong listener
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     removeAllListeners()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::removeAllListeners
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1removeAllListeners (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     setPriority()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::setPriority
 * Signature:  (unsigned_char)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1setPriority_1_1ucharv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jshort priority
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getPriority()
 * Type:       virtual method
 * Definition: virtual uchar Ogre::RenderTarget::getPriority
 * Signature:  ()unsigned_char
 */

JNIEXPORT jshort JNICALL Java_org_ogre4j_MultiRenderTarget__1getPriority_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     isActive()
 * Type:       virtual method
 * Definition: virtual bool Ogre::RenderTarget::isActive
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MultiRenderTarget__1isActive_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     setActive()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::setActive
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1setActive_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean state
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     setAutoUpdated()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::setAutoUpdated
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1setAutoUpdated_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean autoupdate
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     isAutoUpdated()
 * Type:       virtual method
 * Definition: virtual bool Ogre::RenderTarget::isAutoUpdated
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MultiRenderTarget__1isAutoUpdated_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     writeContentsToFile()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderTarget::writeContentsToFile
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1writeContentsToFile_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring filename
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     writeContentsToTimestampedFile()
 * Type:       virtual method
 * Definition: virtual String Ogre::RenderTarget::writeContentsToTimestampedFile
 * Signature:  (std_stringstd_string)std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_MultiRenderTarget__1writeContentsToTimestampedFile_1_1StringRStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring filenamePrefix, 
  jstring filenameSuffix
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     requiresTextureFlipping()
 * Type:       pure virtual method
 * Definition: virtual bool Ogre::RenderTarget::requiresTextureFlipping
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MultiRenderTarget__1requiresTextureFlipping_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getTriangleCount()
 * Type:       virtual method
 * Definition: virtual size_t Ogre::RenderTarget::getTriangleCount
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_MultiRenderTarget__1getTriangleCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getBatchCount()
 * Type:       virtual method
 * Definition: virtual size_t Ogre::RenderTarget::getBatchCount
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_MultiRenderTarget__1getBatchCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     _notifyCameraRemoved()
 * Type:       virtual method
 * Definition: virtual void Ogre::RenderTarget::_notifyCameraRemoved
 * Signature:  (Ogre_Camera)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1_1notifyCameraRemoved_1_1CameraP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong cam
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     isPrimary()
 * Type:       virtual method
 * Definition: virtual bool Ogre::RenderTarget::isPrimary
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MultiRenderTarget__1isPrimary_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     isHardwareGammaEnabled()
 * Type:       virtual method
 * Definition: virtual bool Ogre::RenderTarget::isHardwareGammaEnabled
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MultiRenderTarget__1isHardwareGammaEnabled_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     getFSAA()
 * Type:       virtual method
 * Definition: virtual uint Ogre::RenderTarget::getFSAA
 * Signature:  ()unsigned_int
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MultiRenderTarget__1getFSAA_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     _getImpl()
 * Type:       virtual method
 * Definition: virtual Impl* Ogre::RenderTarget::_getImpl
 * Signature:  ()Ogre_RenderTarget_Impl
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MultiRenderTarget__1_1getImpl (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MultiRenderTarget
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::MultiRenderTarget::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MultiRenderTarget__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_MultiRenderTarget__*/
