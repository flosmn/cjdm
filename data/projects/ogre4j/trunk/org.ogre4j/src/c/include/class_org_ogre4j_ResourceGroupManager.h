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


#ifndef __Included_org_ogre4j_ResourceGroupManager__
#define __Included_org_ogre4j_ResourceGroupManager__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     ResourceGroupManager()
 * Type:       constructor
 * Definition: Ogre::ResourceGroupManager::ResourceGroupManager
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1_1createResourceGroupManager (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     createResourceGroup()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::createResourceGroup
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1createResourceGroup_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     initialiseResourceGroup()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::initialiseResourceGroup
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1initialiseResourceGroup_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     initialiseAllResourceGroups()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::initialiseAllResourceGroups
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1initialiseAllResourceGroups (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     prepareResourceGroup()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::prepareResourceGroup
 * Signature:  (std_stringZZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1prepareResourceGroup_1_1StringRbvbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jboolean prepareMainResources, 
  jboolean prepareWorldGeom
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     loadResourceGroup()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::loadResourceGroup
 * Signature:  (std_stringZZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1loadResourceGroup_1_1StringRbvbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jboolean loadMainResources, 
  jboolean loadWorldGeom
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     unloadResourceGroup()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::unloadResourceGroup
 * Signature:  (std_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1unloadResourceGroup_1_1StringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jboolean reloadableOnly
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     unloadUnreferencedResourcesInGroup()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::unloadUnreferencedResourcesInGroup
 * Signature:  (std_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1unloadUnreferencedResourcesInGroup_1_1StringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jboolean reloadableOnly
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     clearResourceGroup()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::clearResourceGroup
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1clearResourceGroup_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     destroyResourceGroup()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::destroyResourceGroup
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1destroyResourceGroup_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     isResourceGroupInitialised()
 * Type:       non-virtual method
 * Definition: bool Ogre::ResourceGroupManager::isResourceGroupInitialised
 * Signature:  (std_string)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_ResourceGroupManager__1isResourceGroupInitialised_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     isResourceGroupLoaded()
 * Type:       non-virtual method
 * Definition: bool Ogre::ResourceGroupManager::isResourceGroupLoaded
 * Signature:  (std_string)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_ResourceGroupManager__1isResourceGroupLoaded_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     addResourceLocation()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::addResourceLocation
 * Signature:  (std_stringstd_stringstd_stringZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1addResourceLocation_1_1StringRStringRStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jstring locType, 
  jstring resGroup, 
  jboolean recursive
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     removeResourceLocation()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::removeResourceLocation
 * Signature:  (std_stringstd_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1removeResourceLocation_1_1StringRStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jstring resGroup
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     declareResource()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::declareResource
 * Signature:  (std_stringstd_stringstd_stringOgre_NameValuePairList)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1declareResource_1_1StringRStringRStringRNameValuePairListR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jstring resourceType, 
  jstring groupName, 
  jlong loadParameters
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     declareResource()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::declareResource
 * Signature:  (std_stringstd_stringstd_stringOgre_ManualResourceLoaderOgre_NameValuePairList)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1declareResource_1_1StringRStringRStringRManualResourceLoaderpNameValuePairListR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jstring resourceType, 
  jstring groupName, 
  jlong loader, 
  jlong loadParameters
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     undeclareResource()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::undeclareResource
 * Signature:  (std_stringstd_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1undeclareResource_1_1StringRStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name, 
  jstring groupName
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     openResource()
 * Type:       non-virtual method
 * Definition: DataStreamPtr Ogre::ResourceGroupManager::openResource
 * Signature:  (std_stringstd_stringZOgre_Resource)Ogre_DataStreamPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1openResource_1_1StringRStringRbvResourcep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring resourceName, 
  jstring groupName, 
  jboolean searchGroupsIfNotFound, 
  jlong resourceBeingLoaded
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     openResources()
 * Type:       non-virtual method
 * Definition: DataStreamListPtr Ogre::ResourceGroupManager::openResources
 * Signature:  (std_stringstd_string)Ogre_DataStreamListPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1openResources_1_1StringRStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring pattern, 
  jstring groupName
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     listResourceNames()
 * Type:       non-virtual method
 * Definition: StringVectorPtr Ogre::ResourceGroupManager::listResourceNames
 * Signature:  (std_stringZ)Ogre_StringVectorPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1listResourceNames_1_1StringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring groupName, 
  jboolean dirs
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     listResourceFileInfo()
 * Type:       non-virtual method
 * Definition: FileInfoListPtr Ogre::ResourceGroupManager::listResourceFileInfo
 * Signature:  (std_stringZ)Ogre_FileInfoListPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1listResourceFileInfo_1_1StringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring groupName, 
  jboolean dirs
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     findResourceNames()
 * Type:       non-virtual method
 * Definition: StringVectorPtr Ogre::ResourceGroupManager::findResourceNames
 * Signature:  (std_stringstd_stringZ)Ogre_StringVectorPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1findResourceNames_1_1StringRStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring groupName, 
  jstring pattern, 
  jboolean dirs
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     resourceExists()
 * Type:       non-virtual method
 * Definition: bool Ogre::ResourceGroupManager::resourceExists
 * Signature:  (std_stringstd_string)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_ResourceGroupManager__1resourceExists_1_1StringRStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring group, 
  jstring filename
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     resourceExists()
 * Type:       non-virtual method
 * Definition: bool Ogre::ResourceGroupManager::resourceExists
 * Signature:  (Ogre_ResourceGroupManager_ResourceGroupstd_string)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_ResourceGroupManager__1resourceExists_1_1ResourceGrouppStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong group, 
  jstring filename
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     findGroupContainingResource()
 * Type:       non-virtual method
 * Definition: const String& Ogre::ResourceGroupManager::findGroupContainingResource
 * Signature:  (std_string)std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ResourceGroupManager__1findGroupContainingResource_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring filename
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     findResourceFileInfo()
 * Type:       non-virtual method
 * Definition: FileInfoListPtr Ogre::ResourceGroupManager::findResourceFileInfo
 * Signature:  (std_stringstd_stringZ)Ogre_FileInfoListPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1findResourceFileInfo_1_1StringRStringRbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring group, 
  jstring pattern, 
  jboolean dirs
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     resourceModifiedTime()
 * Type:       non-virtual method
 * Definition: time_t Ogre::ResourceGroupManager::resourceModifiedTime
 * Signature:  (std_stringstd_string)J
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1resourceModifiedTime_1_1StringRStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring group, 
  jstring filename
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     resourceModifiedTime()
 * Type:       non-virtual method
 * Definition: time_t Ogre::ResourceGroupManager::resourceModifiedTime
 * Signature:  (Ogre_ResourceGroupManager_ResourceGroupstd_string)J
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1resourceModifiedTime_1_1ResourceGrouppStringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong group, 
  jstring filename
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     addResourceGroupListener()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::addResourceGroupListener
 * Signature:  (Ogre_ResourceGroupListener)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1addResourceGroupListener_1_1ResourceGroupListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong l
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     removeResourceGroupListener()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::removeResourceGroupListener
 * Signature:  (Ogre_ResourceGroupListener)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1removeResourceGroupListener_1_1ResourceGroupListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong l
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     setWorldResourceGroupName()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::setWorldResourceGroupName
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1setWorldResourceGroupName_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring groupName
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     getWorldResourceGroupName()
 * Type:       non-virtual method
 * Definition: const String& Ogre::ResourceGroupManager::getWorldResourceGroupName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ResourceGroupManager__1getWorldResourceGroupName_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     linkWorldGeometryToResourceGroup()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::linkWorldGeometryToResourceGroup
 * Signature:  (std_stringstd_stringOgre_SceneManager)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1linkWorldGeometryToResourceGroup_1_1StringRStringRSceneManagerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring group, 
  jstring worldGeometry, 
  jlong sceneManager
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     unlinkWorldGeometryFromResourceGroup()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::unlinkWorldGeometryFromResourceGroup
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1unlinkWorldGeometryFromResourceGroup_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring group
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     shutdownAll()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::shutdownAll
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1shutdownAll (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     _registerResourceManager()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::_registerResourceManager
 * Signature:  (std_stringOgre_ResourceManager)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1_1registerResourceManager_1_1StringRResourceManagerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring resourceType, 
  jlong rm
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     _unregisterResourceManager()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::_unregisterResourceManager
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1_1unregisterResourceManager_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring resourceType
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     getResourceManagerIterator()
 * Type:       non-virtual method
 * Definition: ResourceManagerIterator Ogre::ResourceGroupManager::getResourceManagerIterator
 * Signature:  ()Ogre_ResourceGroupManager_ResourceManagerIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1getResourceManagerIterator (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     _registerScriptLoader()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::_registerScriptLoader
 * Signature:  (Ogre_ScriptLoader)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1_1registerScriptLoader_1_1ScriptLoaderp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong su
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     _unregisterScriptLoader()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::_unregisterScriptLoader
 * Signature:  (Ogre_ScriptLoader)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1_1unregisterScriptLoader_1_1ScriptLoaderp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong su
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     _getResourceManager()
 * Type:       non-virtual method
 * Definition: ResourceManager* Ogre::ResourceGroupManager::_getResourceManager
 * Signature:  (std_string)Ogre_ResourceManager
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1_1getResourceManager_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring resourceType
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     _notifyResourceCreated()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::_notifyResourceCreated
 * Signature:  (Ogre_ResourcePtr)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1_1notifyResourceCreated_1_1ResourcePtrr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong res
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     _notifyResourceRemoved()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::_notifyResourceRemoved
 * Signature:  (Ogre_ResourcePtr)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1_1notifyResourceRemoved_1_1ResourcePtrr (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong res
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     _notifyResourceGroupChanged()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::_notifyResourceGroupChanged
 * Signature:  (std_stringOgre_Resource)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1_1notifyResourceGroupChanged_1_1StringRResourcep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring oldGroup, 
  jlong res
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     _notifyAllResourcesRemoved()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::_notifyAllResourcesRemoved
 * Signature:  (Ogre_ResourceManager)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1_1notifyAllResourcesRemoved_1_1ResourceManagerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong manager
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     _notifyWorldGeometryPrepareStageStarted()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::_notifyWorldGeometryPrepareStageStarted
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1_1notifyWorldGeometryPrepareStageStarted_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring description
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     _notifyWorldGeometryPrepareStageEnded()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::_notifyWorldGeometryPrepareStageEnded
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1_1notifyWorldGeometryPrepareStageEnded (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     _notifyWorldGeometryStageStarted()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::_notifyWorldGeometryStageStarted
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1_1notifyWorldGeometryStageStarted_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring description
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     _notifyWorldGeometryStageEnded()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::_notifyWorldGeometryStageEnded
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1_1notifyWorldGeometryStageEnded (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     getResourceGroups()
 * Type:       non-virtual method
 * Definition: StringVector Ogre::ResourceGroupManager::getResourceGroups
 * Signature:  ()Ogre_StringVector
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1getResourceGroups (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     getResourceDeclarationList()
 * Type:       non-virtual method
 * Definition: ResourceDeclarationList Ogre::ResourceGroupManager::getResourceDeclarationList
 * Signature:  (std_string)Ogre_ResourceGroupManager_ResourceDeclarationList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1getResourceDeclarationList_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring groupName
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     setLoadingListener()
 * Type:       non-virtual method
 * Definition: void Ogre::ResourceGroupManager::setLoadingListener
 * Signature:  (Ogre_ResourceLoadingListener)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1setLoadingListener_1_1ResourceLoadingListenerp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong listener
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     getLoadingListener()
 * Type:       non-virtual method
 * Definition: ResourceLoadingListener* Ogre::ResourceGroupManager::getLoadingListener
 * Signature:  ()Ogre_ResourceLoadingListener
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1getLoadingListener (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     getSingleton()
 * Type:       static method
 * Definition: static ResourceGroupManager& Ogre::ResourceGroupManager::getSingleton
 * Signature:  ()Ogre_ResourceGroupManager
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1getSingleton (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     getSingletonPtr()
 * Type:       static method
 * Definition: static ResourceGroupManager* Ogre::ResourceGroupManager::getSingletonPtr
 * Signature:  ()Ogre_ResourceGroupManager
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ResourceGroupManager__1getSingletonPtr (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ResourceGroupManager::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     getDEFAULT_RESOURCE_GROUP_NAME()
 * Type:       getter for public attribute
 * Definition: String Ogre::ResourceGroupManager::DEFAULT_RESOURCE_GROUP_NAME
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ResourceGroupManager__1getDEFAULT_1RESOURCE_1GROUP_1NAME (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     setDEFAULT_RESOURCE_GROUP_NAME()
 * Type:       setter for public attribute
 * Definition: String Ogre::ResourceGroupManager::DEFAULT_RESOURCE_GROUP_NAME
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1setDEFAULT_1RESOURCE_1GROUP_1NAME (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     getINTERNAL_RESOURCE_GROUP_NAME()
 * Type:       getter for public attribute
 * Definition: String Ogre::ResourceGroupManager::INTERNAL_RESOURCE_GROUP_NAME
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ResourceGroupManager__1getINTERNAL_1RESOURCE_1GROUP_1NAME (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     setINTERNAL_RESOURCE_GROUP_NAME()
 * Type:       setter for public attribute
 * Definition: String Ogre::ResourceGroupManager::INTERNAL_RESOURCE_GROUP_NAME
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1setINTERNAL_1RESOURCE_1GROUP_1NAME (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     getBOOTSTRAP_RESOURCE_GROUP_NAME()
 * Type:       getter for public attribute
 * Definition: String Ogre::ResourceGroupManager::BOOTSTRAP_RESOURCE_GROUP_NAME
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ResourceGroupManager__1getBOOTSTRAP_1RESOURCE_1GROUP_1NAME (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     setBOOTSTRAP_RESOURCE_GROUP_NAME()
 * Type:       setter for public attribute
 * Definition: String Ogre::ResourceGroupManager::BOOTSTRAP_RESOURCE_GROUP_NAME
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1setBOOTSTRAP_1RESOURCE_1GROUP_1NAME (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     getAUTODETECT_RESOURCE_GROUP_NAME()
 * Type:       getter for public attribute
 * Definition: String Ogre::ResourceGroupManager::AUTODETECT_RESOURCE_GROUP_NAME
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ResourceGroupManager__1getAUTODETECT_1RESOURCE_1GROUP_1NAME (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     setAUTODETECT_RESOURCE_GROUP_NAME()
 * Type:       setter for public attribute
 * Definition: String Ogre::ResourceGroupManager::AUTODETECT_RESOURCE_GROUP_NAME
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1setAUTODETECT_1RESOURCE_1GROUP_1NAME (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     getRESOURCE_SYSTEM_NUM_REFERENCE_COUNTS()
 * Type:       getter for public attribute
 * Definition: size_t Ogre::ResourceGroupManager::RESOURCE_SYSTEM_NUM_REFERENCE_COUNTS
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_ResourceGroupManager__1getRESOURCE_1SYSTEM_1NUM_1REFERENCE_1COUNTS (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.ResourceGroupManager
 * Method:     setRESOURCE_SYSTEM_NUM_REFERENCE_COUNTS()
 * Type:       setter for public attribute
 * Definition: size_t Ogre::ResourceGroupManager::RESOURCE_SYSTEM_NUM_REFERENCE_COUNTS
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ResourceGroupManager__1setRESOURCE_1SYSTEM_1NUM_1REFERENCE_1COUNTS (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jint _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_ResourceGroupManager__*/
