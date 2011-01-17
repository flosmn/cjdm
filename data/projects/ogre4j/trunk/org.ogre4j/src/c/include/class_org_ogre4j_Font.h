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


#ifndef __Included_org_ogre4j_Font__
#define __Included_org_ogre4j_Font__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.Font
 * Method:     Font()
 * Type:       constructor
 * Definition: Ogre::Font::Font
 * Signature:  (Ogre_ResourceManagerstd_stringunsigned_longstd_stringZOgre_ManualResourceLoader)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Font__1_1createFont_1_1ResourceManagerpStringRResourceHandlevStringRbvManualResourceLoaderp (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong creator, 
  jstring name, 
  jlong handle, 
  jstring group, 
  jboolean isManual, 
  jlong loader
);

/*
 * Class:      org.ogre4j.Font
 * Method:     setType()
 * Type:       non-virtual method
 * Definition: void Ogre::Font::setType
 * Signature:  (Ogre_FontType)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1setType_1_1FontTypev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint ftype
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getType()
 * Type:       non-virtual method
 * Definition: FontType Ogre::Font::getType
 * Signature:  ()Ogre_FontType
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Font__1getType_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     setSource()
 * Type:       non-virtual method
 * Definition: void Ogre::Font::setSource
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1setSource_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring source
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getSource()
 * Type:       non-virtual method
 * Definition: const String& Ogre::Font::getSource
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_Font__1getSource_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     setTrueTypeSize()
 * Type:       non-virtual method
 * Definition: void Ogre::Font::setTrueTypeSize
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1setTrueTypeSize_1_1Realv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat ttfSize
);

/*
 * Class:      org.ogre4j.Font
 * Method:     setTrueTypeResolution()
 * Type:       non-virtual method
 * Definition: void Ogre::Font::setTrueTypeResolution
 * Signature:  (unsigned_int)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1setTrueTypeResolution_1_1uintv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong ttfResolution
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getTrueTypeSize()
 * Type:       non-virtual method
 * Definition: Real Ogre::Font::getTrueTypeSize
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_Font__1getTrueTypeSize_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getTrueTypeResolution()
 * Type:       non-virtual method
 * Definition: uint Ogre::Font::getTrueTypeResolution
 * Signature:  ()unsigned_int
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Font__1getTrueTypeResolution_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getTrueTypeMaxBearingY()
 * Type:       non-virtual method
 * Definition: int Ogre::Font::getTrueTypeMaxBearingY
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Font__1getTrueTypeMaxBearingY_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getGlyphTexCoords()
 * Type:       non-virtual method
 * Definition: const UVRect& Ogre::Font::getGlyphTexCoords
 * Signature:  (unsigned_int)Ogre_FloatRect
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Font__1getGlyphTexCoords_1_1CodePointv_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong id
);

/*
 * Class:      org.ogre4j.Font
 * Method:     setGlyphTexCoords()
 * Type:       non-virtual method
 * Definition: void Ogre::Font::setGlyphTexCoords
 * Signature:  (unsigned_intfloatfloatfloatfloatfloat)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1setGlyphTexCoords_1_1CodePointvRealvRealvRealvRealvRealv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong id, 
  jfloat u1, 
  jfloat v1, 
  jfloat u2, 
  jfloat v2, 
  jfloat textureAspect
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getGlyphAspectRatio()
 * Type:       non-virtual method
 * Definition: Real Ogre::Font::getGlyphAspectRatio
 * Signature:  (unsigned_int)float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_Font__1getGlyphAspectRatio_1_1CodePointv_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong id
);

/*
 * Class:      org.ogre4j.Font
 * Method:     setGlyphAspectRatio()
 * Type:       non-virtual method
 * Definition: void Ogre::Font::setGlyphAspectRatio
 * Signature:  (unsigned_intfloat)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1setGlyphAspectRatio_1_1CodePointvRealv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong id, 
  jfloat ratio
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getGlyphInfo()
 * Type:       non-virtual method
 * Definition: const GlyphInfo& Ogre::Font::getGlyphInfo
 * Signature:  (unsigned_int)Ogre_Font_GlyphInfo
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Font__1getGlyphInfo_1_1CodePointv_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong id
);

/*
 * Class:      org.ogre4j.Font
 * Method:     addCodePointRange()
 * Type:       non-virtual method
 * Definition: void Ogre::Font::addCodePointRange
 * Signature:  (Ogre_Font_CodePointRange)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1addCodePointRange_1_1CodePointRangeR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong range
);

/*
 * Class:      org.ogre4j.Font
 * Method:     clearCodePointRanges()
 * Type:       non-virtual method
 * Definition: void Ogre::Font::clearCodePointRanges
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1clearCodePointRanges (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getCodePointRangeList()
 * Type:       non-virtual method
 * Definition: const CodePointRangeList& Ogre::Font::getCodePointRangeList
 * Signature:  ()Ogre_Font_CodePointRangeList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Font__1getCodePointRangeList_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getMaterial_const()
 * Type:       non-virtual method
 * Definition: const MaterialPtr& Ogre::Font::getMaterial
 * Signature:  ()Ogre_MaterialPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Font__1getMaterial_1const_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getMaterial()
 * Type:       non-virtual method
 * Definition: const MaterialPtr& Ogre::Font::getMaterial
 * Signature:  ()Ogre_MaterialPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Font__1getMaterial (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     setAntialiasColour()
 * Type:       non-virtual method
 * Definition: void Ogre::Font::setAntialiasColour
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1setAntialiasColour_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean enabled
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getAntialiasColour()
 * Type:       non-virtual method
 * Definition: bool Ogre::Font::getAntialiasColour
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Font__1getAntialiasColour_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     loadResource()
 * Type:       virtual method
 * Definition: void Ogre::Font::loadResource
 * Signature:  (Ogre_Resource)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1loadResource_1_1Resourcep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong resource
);

/*
 * Class:      org.ogre4j.Font
 * Method:     prepare()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::prepare
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1prepare (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     load()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::load
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1load_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean backgroundThread
);

/*
 * Class:      org.ogre4j.Font
 * Method:     reload()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::reload
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1reload (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     isReloadable()
 * Type:       virtual method
 * Definition: virtual bool Ogre::Resource::isReloadable
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Font__1isReloadable_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     isManuallyLoaded()
 * Type:       virtual method
 * Definition: virtual bool Ogre::Resource::isManuallyLoaded
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Font__1isManuallyLoaded_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     unload()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::unload
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1unload (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getSize()
 * Type:       virtual method
 * Definition: virtual size_t Ogre::Resource::getSize
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Font__1getSize_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     touch()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::touch
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1touch (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getName()
 * Type:       virtual method
 * Definition: virtual const String& Ogre::Resource::getName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_Font__1getName_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getHandle()
 * Type:       virtual method
 * Definition: virtual ResourceHandle Ogre::Resource::getHandle
 * Signature:  ()unsigned_long
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Font__1getHandle_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     isPrepared()
 * Type:       virtual method
 * Definition: virtual bool Ogre::Resource::isPrepared
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Font__1isPrepared_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     isLoaded()
 * Type:       virtual method
 * Definition: virtual bool Ogre::Resource::isLoaded
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Font__1isLoaded_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     isLoading()
 * Type:       virtual method
 * Definition: virtual bool Ogre::Resource::isLoading
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Font__1isLoading_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getLoadingState()
 * Type:       virtual method
 * Definition: virtual LoadingState Ogre::Resource::getLoadingState
 * Signature:  ()Ogre_Resource_LoadingState
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Font__1getLoadingState_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     isBackgroundLoaded()
 * Type:       virtual method
 * Definition: virtual bool Ogre::Resource::isBackgroundLoaded
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Font__1isBackgroundLoaded_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     setBackgroundLoaded()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::setBackgroundLoaded
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1setBackgroundLoaded_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean bl
);

/*
 * Class:      org.ogre4j.Font
 * Method:     escalateLoading()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::escalateLoading
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1escalateLoading (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     addListener()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::addListener
 * Signature:  (Ogre_Resource_Listener)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1addListener_1_1Listenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong lis
);

/*
 * Class:      org.ogre4j.Font
 * Method:     removeListener()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::removeListener
 * Signature:  (Ogre_Resource_Listener)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1removeListener_1_1Listenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong lis
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getGroup()
 * Type:       virtual method
 * Definition: virtual const String& Ogre::Resource::getGroup
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_Font__1getGroup (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     changeGroupOwnership()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::changeGroupOwnership
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1changeGroupOwnership_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring newGroup
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getCreator()
 * Type:       virtual method
 * Definition: virtual ResourceManager* Ogre::Resource::getCreator
 * Signature:  ()Ogre_ResourceManager
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Font__1getCreator (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getOrigin()
 * Type:       virtual method
 * Definition: virtual const String& Ogre::Resource::getOrigin
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_Font__1getOrigin_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     _notifyOrigin()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::_notifyOrigin
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1_1notifyOrigin_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring origin
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getStateCount()
 * Type:       virtual method
 * Definition: virtual size_t Ogre::Resource::getStateCount
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Font__1getStateCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     _dirtyState()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::_dirtyState
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1_1dirtyState (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     _fireBackgroundLoadingComplete()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::_fireBackgroundLoadingComplete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1_1fireBackgroundLoadingComplete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     _fireBackgroundPreparingComplete()
 * Type:       virtual method
 * Definition: virtual void Ogre::Resource::_fireBackgroundPreparingComplete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1_1fireBackgroundPreparingComplete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getParamDictionary()
 * Type:       non-virtual method
 * Definition: ParamDictionary* Ogre::StringInterface::getParamDictionary
 * Signature:  ()Ogre_ParamDictionary
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Font__1getParamDictionary (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getParamDictionary_const()
 * Type:       non-virtual method
 * Definition: const ParamDictionary* Ogre::StringInterface::getParamDictionary
 * Signature:  ()Ogre_ParamDictionary
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Font__1getParamDictionary_1const_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getParameters()
 * Type:       non-virtual method
 * Definition: const ParameterList& Ogre::StringInterface::getParameters
 * Signature:  ()Ogre_ParameterList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Font__1getParameters_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     setParameter()
 * Type:       virtual method
 * Definition: virtual bool Ogre::StringInterface::setParameter
 * Signature:  (std_stringstd_string)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Font__1setParameter_1_1StringRStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jstring value
);

/*
 * Class:      org.ogre4j.Font
 * Method:     setParameterList()
 * Type:       virtual method
 * Definition: virtual void Ogre::StringInterface::setParameterList
 * Signature:  (Ogre_NameValuePairList)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1setParameterList_1_1NameValuePairListR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong paramList
);

/*
 * Class:      org.ogre4j.Font
 * Method:     getParameter()
 * Type:       virtual method
 * Definition: virtual String Ogre::StringInterface::getParameter
 * Signature:  (std_string)std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_Font__1getParameter_1_1StringR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.Font
 * Method:     copyParametersTo()
 * Type:       virtual method
 * Definition: virtual void Ogre::StringInterface::copyParametersTo
 * Signature:  (Ogre_StringInterface)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1copyParametersTo_1_1StringInterfacep_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong dest
);

/*
 * Class:      org.ogre4j.Font
 * Method:     cleanupDictionary()
 * Type:       static method
 * Definition: static void Ogre::StringInterface::cleanupDictionary
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1cleanupDictionary (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.Font
 * Method:     prepareResource()
 * Type:       virtual method
 * Definition: virtual void Ogre::ManualResourceLoader::prepareResource
 * Signature:  (Ogre_Resource)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1prepareResource_1_1Resourcep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong resource
);

/*
 * Class:      org.ogre4j.Font
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::Font::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Font__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_Font__*/
