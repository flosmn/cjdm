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


#ifndef __Included_org_ogre4j_CompositorManager__
#define __Included_org_ogre4j_CompositorManager__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     CompositorManager()
 * Type:       constructor
 * Definition: Ogre::CompositorManager::CompositorManager
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1_1createCompositorManager (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     createImpl()
 * Type:       virtual method
 * Definition: Resource* Ogre::CompositorManager::createImpl
 * Signature:  (std_stringunsigned_longstd_stringZOgre_ManualResourceLoaderOgre_NameValuePairList)Ogre_Resource
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1createImpl_1_1StringRResourceHandlevStringRbvManualResourceLoaderpNameValuePairListP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jlong handle, 
  jstring group, 
  jboolean isManual, 
  jlong loader, 
  jlong params
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     initialise()
 * Type:       non-virtual method
 * Definition: void Ogre::CompositorManager::initialise
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1initialise (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     parseScript()
 * Type:       virtual method
 * Definition: void Ogre::CompositorManager::parseScript
 * Signature:  (Ogre_DataStreamPtrstd_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1parseScript_1_1DataStreamPtrrStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong stream, 
  jstring groupName
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     getCompositorChain()
 * Type:       non-virtual method
 * Definition: CompositorChain* Ogre::CompositorManager::getCompositorChain
 * Signature:  (Ogre_Viewport)Ogre_CompositorChain
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1getCompositorChain_1_1Viewportp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong vp
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     hasCompositorChain()
 * Type:       non-virtual method
 * Definition: bool Ogre::CompositorManager::hasCompositorChain
 * Signature:  (Ogre_Viewport)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_CompositorManager__1hasCompositorChain_1_1Viewportp_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong vp
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     removeCompositorChain()
 * Type:       non-virtual method
 * Definition: void Ogre::CompositorManager::removeCompositorChain
 * Signature:  (Ogre_Viewport)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1removeCompositorChain_1_1Viewportp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong vp
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     addCompositor()
 * Type:       non-virtual method
 * Definition: CompositorInstance* Ogre::CompositorManager::addCompositor
 * Signature:  (Ogre_Viewportstd_stringI)Ogre_CompositorInstance
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1addCompositor_1_1ViewportpStringRiv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong vp, 
  jstring compositor, 
  jint addPosition
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     removeCompositor()
 * Type:       non-virtual method
 * Definition: void Ogre::CompositorManager::removeCompositor
 * Signature:  (Ogre_Viewportstd_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1removeCompositor_1_1ViewportpStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong vp, 
  jstring compositor
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     setCompositorEnabled()
 * Type:       non-virtual method
 * Definition: void Ogre::CompositorManager::setCompositorEnabled
 * Signature:  (Ogre_Viewportstd_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1setCompositorEnabled_1_1ViewportpStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong vp, 
  jstring compositor, 
  jboolean value
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     _getTexturedRectangle2D()
 * Type:       non-virtual method
 * Definition: Renderable* Ogre::CompositorManager::_getTexturedRectangle2D
 * Signature:  ()Ogre_Renderable
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1_1getTexturedRectangle2D (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     removeAll()
 * Type:       virtual method
 * Definition: void Ogre::CompositorManager::removeAll
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1removeAll (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     _reconstructAllCompositorResources()
 * Type:       non-virtual method
 * Definition: void Ogre::CompositorManager::_reconstructAllCompositorResources
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1_1reconstructAllCompositorResources (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     getSingleton()
 * Type:       static method
 * Definition: static CompositorManager& Ogre::CompositorManager::getSingleton
 * Signature:  ()Ogre_CompositorManager
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1getSingleton (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     getSingletonPtr()
 * Type:       static method
 * Definition: static CompositorManager* Ogre::CompositorManager::getSingletonPtr
 * Signature:  ()Ogre_CompositorManager
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1getSingletonPtr (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     create()
 * Type:       virtual method
 * Definition: virtual ResourcePtr Ogre::ResourceManager::create
 * Signature:  (std_stringstd_stringZOgre_ManualResourceLoaderOgre_NameValuePairList)Ogre_ResourcePtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1create_1_1StringRStringRbvManualResourceLoaderpNameValuePairListP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jstring group, 
  jboolean isManual, 
  jlong loader, 
  jlong createParams
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     createOrRetrieve()
 * Type:       virtual method
 * Definition: virtual ResourceCreateOrRetrieveResult Ogre::ResourceManager::createOrRetrieve
 * Signature:  (std_stringstd_stringZOgre_ManualResourceLoaderOgre_NameValuePairList)Ogre_ResourceManager_ResourceCreateOrRetrieveResult
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1createOrRetrieve_1_1StringRStringRbvManualResourceLoaderpNameValuePairListP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jstring group, 
  jboolean isManual, 
  jlong loader, 
  jlong createParams
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     setMemoryBudget()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::setMemoryBudget
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1setMemoryBudget_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint bytes
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     getMemoryBudget()
 * Type:       virtual method
 * Definition: virtual size_t Ogre::ResourceManager::getMemoryBudget
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_CompositorManager__1getMemoryBudget_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     getMemoryUsage()
 * Type:       virtual method
 * Definition: virtual size_t Ogre::ResourceManager::getMemoryUsage
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_CompositorManager__1getMemoryUsage_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     unload()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::unload
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1unload_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     unload()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::unload
 * Signature:  (unsigned_long)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1unload_1_1ResourceHandlev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong handle
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     unloadAll()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::unloadAll
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1unloadAll_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean reloadableOnly
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     reloadAll()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::reloadAll
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1reloadAll_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean reloadableOnly
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     unloadUnreferencedResources()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::unloadUnreferencedResources
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1unloadUnreferencedResources_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean reloadableOnly
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     reloadUnreferencedResources()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::reloadUnreferencedResources
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1reloadUnreferencedResources_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean reloadableOnly
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     remove()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::remove
 * Signature:  (Ogre_ResourcePtr)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1remove_1_1ResourcePtrr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong r
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     remove()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::remove
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1remove_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     remove()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::remove
 * Signature:  (unsigned_long)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1remove_1_1ResourceHandlev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong handle
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     getByName()
 * Type:       virtual method
 * Definition: virtual ResourcePtr Ogre::ResourceManager::getByName
 * Signature:  (std_string)Ogre_ResourcePtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1getByName_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     getByHandle()
 * Type:       virtual method
 * Definition: virtual ResourcePtr Ogre::ResourceManager::getByHandle
 * Signature:  (unsigned_long)Ogre_ResourcePtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1getByHandle_1_1ResourceHandlev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong handle
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     resourceExists()
 * Type:       virtual method
 * Definition: virtual bool Ogre::ResourceManager::resourceExists
 * Signature:  (std_string)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_CompositorManager__1resourceExists_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     resourceExists()
 * Type:       virtual method
 * Definition: virtual bool Ogre::ResourceManager::resourceExists
 * Signature:  (unsigned_long)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_CompositorManager__1resourceExists_1_1ResourceHandlev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong handle
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     _notifyResourceTouched()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::_notifyResourceTouched
 * Signature:  (Ogre_Resource)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1_1notifyResourceTouched_1_1Resourcep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong res
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     _notifyResourceLoaded()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::_notifyResourceLoaded
 * Signature:  (Ogre_Resource)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1_1notifyResourceLoaded_1_1Resourcep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong res
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     _notifyResourceUnloaded()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::_notifyResourceUnloaded
 * Signature:  (Ogre_Resource)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1_1notifyResourceUnloaded_1_1Resourcep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong res
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     prepare()
 * Type:       virtual method
 * Definition: virtual ResourcePtr Ogre::ResourceManager::prepare
 * Signature:  (std_stringstd_stringZOgre_ManualResourceLoaderOgre_NameValuePairList)Ogre_ResourcePtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1prepare_1_1StringRStringRbvManualResourceLoaderpNameValuePairListP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jstring group, 
  jboolean isManual, 
  jlong loader, 
  jlong loadParams
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     load()
 * Type:       virtual method
 * Definition: virtual ResourcePtr Ogre::ResourceManager::load
 * Signature:  (std_stringstd_stringZOgre_ManualResourceLoaderOgre_NameValuePairList)Ogre_ResourcePtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1load_1_1StringRStringRbvManualResourceLoaderpNameValuePairListP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jstring group, 
  jboolean isManual, 
  jlong loader, 
  jlong loadParams
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     getScriptPatterns()
 * Type:       virtual method
 * Definition: virtual const StringVector& Ogre::ResourceManager::getScriptPatterns
 * Signature:  ()Ogre_StringVector
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1getScriptPatterns_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     getLoadingOrder()
 * Type:       virtual method
 * Definition: virtual Real Ogre::ResourceManager::getLoadingOrder
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_CompositorManager__1getLoadingOrder_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     getResourceType()
 * Type:       non-virtual method
 * Definition: const String& Ogre::ResourceManager::getResourceType
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_CompositorManager__1getResourceType_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     setVerbose()
 * Type:       virtual method
 * Definition: virtual void Ogre::ResourceManager::setVerbose
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1setVerbose_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean v
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     getVerbose()
 * Type:       virtual method
 * Definition: virtual bool Ogre::ResourceManager::getVerbose
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_CompositorManager__1getVerbose (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     getResourceIterator()
 * Type:       non-virtual method
 * Definition: ResourceMapIterator Ogre::ResourceManager::getResourceIterator
 * Signature:  ()Ogre_ResourceManager_ResourceMapIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositorManager__1getResourceIterator (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositorManager
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::CompositorManager::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositorManager__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_CompositorManager__*/
