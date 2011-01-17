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


#ifndef __Included_org_ogre4j_HardwarePixelBuffer__
#define __Included_org_ogre4j_HardwarePixelBuffer__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     lock()
 * Type:       virtual method
 * Definition: virtual const PixelBox& Ogre::HardwarePixelBuffer::lock
 * Signature:  (Ogre_BoxOgre_HardwareBuffer_LockOptions)Ogre_PixelBox
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_HardwarePixelBuffer__1lock_1_1Image_1BoxRLockOptionsv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong lockBox, 
  jint options
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     lock()
 * Type:       virtual method
 * Definition: virtual void* Ogre::HardwarePixelBuffer::lock
 * Signature:  (IIOgre_HardwareBuffer_LockOptions)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_HardwarePixelBuffer__1lock_1_1ivivLockOptionsv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint offset, 
  jint length, 
  jint options
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     getCurrentLock()
 * Type:       non-virtual method
 * Definition: const PixelBox& Ogre::HardwarePixelBuffer::getCurrentLock
 * Signature:  ()Ogre_PixelBox
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_HardwarePixelBuffer__1getCurrentLock (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     readData()
 * Type:       virtual method
 * Definition: virtual void Ogre::HardwarePixelBuffer::readData
 * Signature:  (IIV)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_HardwarePixelBuffer__1readData_1_1ivivvp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint offset, 
  jint length, 
  jlong pDest
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     writeData()
 * Type:       virtual method
 * Definition: virtual void Ogre::HardwarePixelBuffer::writeData
 * Signature:  (IIVZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_HardwarePixelBuffer__1writeData_1_1ivivvPbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint offset, 
  jint length, 
  jlong pSource, 
  jboolean discardWholeBuffer
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     blit()
 * Type:       virtual method
 * Definition: virtual void Ogre::HardwarePixelBuffer::blit
 * Signature:  (Ogre_HardwarePixelBufferSharedPtrOgre_BoxOgre_Box)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_HardwarePixelBuffer__1blit_1_1HardwarePixelBufferSharedPtrRImage_1BoxRImage_1BoxR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong src, 
  jlong srcBox, 
  jlong dstBox
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     blit()
 * Type:       non-virtual method
 * Definition: void Ogre::HardwarePixelBuffer::blit
 * Signature:  (Ogre_HardwarePixelBufferSharedPtr)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_HardwarePixelBuffer__1blit_1_1HardwarePixelBufferSharedPtrR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong src
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     blitFromMemory()
 * Type:       pure virtual method
 * Definition: virtual void Ogre::HardwarePixelBuffer::blitFromMemory
 * Signature:  (Ogre_PixelBoxOgre_Box)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_HardwarePixelBuffer__1blitFromMemory_1_1PixelBoxRImage_1BoxR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong src, 
  jlong dstBox
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     blitFromMemory()
 * Type:       non-virtual method
 * Definition: void Ogre::HardwarePixelBuffer::blitFromMemory
 * Signature:  (Ogre_PixelBox)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_HardwarePixelBuffer__1blitFromMemory_1_1PixelBoxR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong src
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     blitToMemory()
 * Type:       pure virtual method
 * Definition: virtual void Ogre::HardwarePixelBuffer::blitToMemory
 * Signature:  (Ogre_BoxOgre_PixelBox)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_HardwarePixelBuffer__1blitToMemory_1_1Image_1BoxRPixelBoxR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong srcBox, 
  jlong dst
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     blitToMemory()
 * Type:       non-virtual method
 * Definition: void Ogre::HardwarePixelBuffer::blitToMemory
 * Signature:  (Ogre_PixelBox)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_HardwarePixelBuffer__1blitToMemory_1_1PixelBoxR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong dst
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     getRenderTarget()
 * Type:       virtual method
 * Definition: virtual RenderTexture* Ogre::HardwarePixelBuffer::getRenderTarget
 * Signature:  (I)Ogre_RenderTexture
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_HardwarePixelBuffer__1getRenderTarget_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint slice
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     getWidth()
 * Type:       non-virtual method
 * Definition: size_t Ogre::HardwarePixelBuffer::getWidth
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_HardwarePixelBuffer__1getWidth_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     getHeight()
 * Type:       non-virtual method
 * Definition: size_t Ogre::HardwarePixelBuffer::getHeight
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_HardwarePixelBuffer__1getHeight_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     getDepth()
 * Type:       non-virtual method
 * Definition: size_t Ogre::HardwarePixelBuffer::getDepth
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_HardwarePixelBuffer__1getDepth_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     getFormat()
 * Type:       non-virtual method
 * Definition: PixelFormat Ogre::HardwarePixelBuffer::getFormat
 * Signature:  ()Ogre_PixelFormat
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_HardwarePixelBuffer__1getFormat_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     lock()
 * Type:       non-virtual method
 * Definition: void* Ogre::HardwareBuffer::lock
 * Signature:  (Ogre_HardwareBuffer_LockOptions)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_HardwarePixelBuffer__1lock_1_1LockOptionsv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint options
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     unlock()
 * Type:       virtual method
 * Definition: virtual void Ogre::HardwareBuffer::unlock
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_HardwarePixelBuffer__1unlock (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     copyData()
 * Type:       virtual method
 * Definition: virtual void Ogre::HardwareBuffer::copyData
 * Signature:  (Ogre_HardwareBufferIIIZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_HardwarePixelBuffer__1copyData_1_1HardwareBufferrivivivbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong srcBuffer, 
  jint srcOffset, 
  jint dstOffset, 
  jint length, 
  jboolean discardWholeBuffer
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     _updateFromShadow()
 * Type:       virtual method
 * Definition: virtual void Ogre::HardwareBuffer::_updateFromShadow
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_HardwarePixelBuffer__1_1updateFromShadow (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     getSizeInBytes()
 * Type:       non-virtual method
 * Definition: size_t Ogre::HardwareBuffer::getSizeInBytes
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_HardwarePixelBuffer__1getSizeInBytes_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     getUsage()
 * Type:       non-virtual method
 * Definition: Usage Ogre::HardwareBuffer::getUsage
 * Signature:  ()Ogre_HardwareBuffer_Usage
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_HardwarePixelBuffer__1getUsage_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     isSystemMemory()
 * Type:       non-virtual method
 * Definition: bool Ogre::HardwareBuffer::isSystemMemory
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_HardwarePixelBuffer__1isSystemMemory_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     hasShadowBuffer()
 * Type:       non-virtual method
 * Definition: bool Ogre::HardwareBuffer::hasShadowBuffer
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_HardwarePixelBuffer__1hasShadowBuffer_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     isLocked()
 * Type:       non-virtual method
 * Definition: bool Ogre::HardwareBuffer::isLocked
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_HardwarePixelBuffer__1isLocked_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     suppressHardwareUpdate()
 * Type:       non-virtual method
 * Definition: void Ogre::HardwareBuffer::suppressHardwareUpdate
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_HardwarePixelBuffer__1suppressHardwareUpdate_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean suppress
);

/*
 * Class:      org.ogre4j.HardwarePixelBuffer
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::HardwarePixelBuffer::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_HardwarePixelBuffer__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_HardwarePixelBuffer__*/
