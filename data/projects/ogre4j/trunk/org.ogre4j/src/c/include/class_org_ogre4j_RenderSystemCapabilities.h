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


#ifndef __Included_org_ogre4j_RenderSystemCapabilities__
#define __Included_org_ogre4j_RenderSystemCapabilities__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     RenderSystemCapabilities()
 * Type:       constructor
 * Definition: Ogre::RenderSystemCapabilities::RenderSystemCapabilities
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderSystemCapabilities__1_1createRenderSystemCapabilities (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     calculateSize()
 * Type:       virtual method
 * Definition: virtual size_t Ogre::RenderSystemCapabilities::calculateSize
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1calculateSize_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setDriverVersion()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setDriverVersion
 * Signature:  (Ogre_DriverVersion)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setDriverVersion_1_1DriverVersionR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong version
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     parseDriverVersionFromString()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::parseDriverVersionFromString
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1parseDriverVersionFromString_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring versionString
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getDriverVersion()
 * Type:       non-virtual method
 * Definition: DriverVersion Ogre::RenderSystemCapabilities::getDriverVersion
 * Signature:  ()Ogre_DriverVersion
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getDriverVersion_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getVendor()
 * Type:       non-virtual method
 * Definition: GPUVendor Ogre::RenderSystemCapabilities::getVendor
 * Signature:  ()Ogre_GPUVendor
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getVendor_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setVendor()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setVendor
 * Signature:  (Ogre_GPUVendor)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setVendor_1_1GPUVendorv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint v
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     parseVendorFromString()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::parseVendorFromString
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1parseVendorFromString_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring vendorString
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     isDriverOlderThanVersion()
 * Type:       non-virtual method
 * Definition: bool Ogre::RenderSystemCapabilities::isDriverOlderThanVersion
 * Signature:  (Ogre_DriverVersion)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderSystemCapabilities__1isDriverOlderThanVersion_1_1DriverVersionv_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong v
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setNumWorldMatrices()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setNumWorldMatrices
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setNumWorldMatrices_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint num
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setNumTextureUnits()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setNumTextureUnits
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setNumTextureUnits_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint num
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setStencilBufferBitDepth()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setStencilBufferBitDepth
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setStencilBufferBitDepth_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint num
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setNumVertexBlendMatrices()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setNumVertexBlendMatrices
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setNumVertexBlendMatrices_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint num
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setNumMultiRenderTargets()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setNumMultiRenderTargets
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setNumMultiRenderTargets_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint num
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getNumWorldMatrices()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getNumWorldMatrices
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getNumWorldMatrices_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getNumTextureUnits()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getNumTextureUnits
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getNumTextureUnits_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getStencilBufferBitDepth()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getStencilBufferBitDepth
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getStencilBufferBitDepth_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getNumVertexBlendMatrices()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getNumVertexBlendMatrices
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getNumVertexBlendMatrices_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getNumMultiRenderTargets()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getNumMultiRenderTargets
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getNumMultiRenderTargets_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     isCapabilityRenderSystemSpecific()
 * Type:       non-virtual method
 * Definition: bool Ogre::RenderSystemCapabilities::isCapabilityRenderSystemSpecific
 * Signature:  (Ogre_Capabilities)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderSystemCapabilities__1isCapabilityRenderSystemSpecific_1_1CapabilitiesV (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint c
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setCapability()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setCapability
 * Signature:  (Ogre_Capabilities)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setCapability_1_1CapabilitiesV (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint c
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     unsetCapability()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::unsetCapability
 * Signature:  (Ogre_Capabilities)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1unsetCapability_1_1CapabilitiesV (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint c
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     hasCapability()
 * Type:       non-virtual method
 * Definition: bool Ogre::RenderSystemCapabilities::hasCapability
 * Signature:  (Ogre_Capabilities)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderSystemCapabilities__1hasCapability_1_1CapabilitiesV_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint c
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     addShaderProfile()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::addShaderProfile
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1addShaderProfile_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring profile
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     removeShaderProfile()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::removeShaderProfile
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1removeShaderProfile_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring profile
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     isShaderProfileSupported()
 * Type:       non-virtual method
 * Definition: bool Ogre::RenderSystemCapabilities::isShaderProfileSupported
 * Signature:  (std_string)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderSystemCapabilities__1isShaderProfileSupported_1_1StringR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring profile
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getSupportedShaderProfiles()
 * Type:       non-virtual method
 * Definition: const ShaderProfiles& Ogre::RenderSystemCapabilities::getSupportedShaderProfiles
 * Signature:  ()Ogre_RenderSystemCapabilities_ShaderProfiles
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getSupportedShaderProfiles_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getVertexProgramConstantFloatCount()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getVertexProgramConstantFloatCount
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getVertexProgramConstantFloatCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getVertexProgramConstantIntCount()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getVertexProgramConstantIntCount
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getVertexProgramConstantIntCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getVertexProgramConstantBoolCount()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getVertexProgramConstantBoolCount
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getVertexProgramConstantBoolCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getGeometryProgramConstantFloatCount()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getGeometryProgramConstantFloatCount
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getGeometryProgramConstantFloatCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getGeometryProgramConstantIntCount()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getGeometryProgramConstantIntCount
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getGeometryProgramConstantIntCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getGeometryProgramConstantBoolCount()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getGeometryProgramConstantBoolCount
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getGeometryProgramConstantBoolCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getFragmentProgramConstantFloatCount()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getFragmentProgramConstantFloatCount
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getFragmentProgramConstantFloatCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getFragmentProgramConstantIntCount()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getFragmentProgramConstantIntCount
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getFragmentProgramConstantIntCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getFragmentProgramConstantBoolCount()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getFragmentProgramConstantBoolCount
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getFragmentProgramConstantBoolCount_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setDeviceName()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setDeviceName
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setDeviceName_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getDeviceName()
 * Type:       non-virtual method
 * Definition: String Ogre::RenderSystemCapabilities::getDeviceName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getDeviceName_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setVertexProgramConstantFloatCount()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setVertexProgramConstantFloatCount
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setVertexProgramConstantFloatCount_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint c
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setVertexProgramConstantIntCount()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setVertexProgramConstantIntCount
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setVertexProgramConstantIntCount_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint c
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setVertexProgramConstantBoolCount()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setVertexProgramConstantBoolCount
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setVertexProgramConstantBoolCount_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint c
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setGeometryProgramConstantFloatCount()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setGeometryProgramConstantFloatCount
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setGeometryProgramConstantFloatCount_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint c
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setGeometryProgramConstantIntCount()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setGeometryProgramConstantIntCount
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setGeometryProgramConstantIntCount_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint c
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setGeometryProgramConstantBoolCount()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setGeometryProgramConstantBoolCount
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setGeometryProgramConstantBoolCount_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint c
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setFragmentProgramConstantFloatCount()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setFragmentProgramConstantFloatCount
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setFragmentProgramConstantFloatCount_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint c
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setFragmentProgramConstantIntCount()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setFragmentProgramConstantIntCount
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setFragmentProgramConstantIntCount_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint c
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setFragmentProgramConstantBoolCount()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setFragmentProgramConstantBoolCount
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setFragmentProgramConstantBoolCount_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint c
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setMaxPointSize()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setMaxPointSize
 * Signature:  (float)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setMaxPointSize_1_1Realv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jfloat s
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getMaxPointSize()
 * Type:       non-virtual method
 * Definition: Real Ogre::RenderSystemCapabilities::getMaxPointSize
 * Signature:  ()float
 */

JNIEXPORT jfloat JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getMaxPointSize_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setNonPOW2TexturesLimited()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setNonPOW2TexturesLimited
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setNonPOW2TexturesLimited_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean l
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getNonPOW2TexturesLimited()
 * Type:       non-virtual method
 * Definition: bool Ogre::RenderSystemCapabilities::getNonPOW2TexturesLimited
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getNonPOW2TexturesLimited_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setNumVertexTextureUnits()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setNumVertexTextureUnits
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setNumVertexTextureUnits_1_1ushortv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint n
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getNumVertexTextureUnits()
 * Type:       non-virtual method
 * Definition: ushort Ogre::RenderSystemCapabilities::getNumVertexTextureUnits
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getNumVertexTextureUnits_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setVertexTextureUnitsShared()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setVertexTextureUnitsShared
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setVertexTextureUnitsShared_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean shared
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getVertexTextureUnitsShared()
 * Type:       non-virtual method
 * Definition: bool Ogre::RenderSystemCapabilities::getVertexTextureUnitsShared
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getVertexTextureUnitsShared_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setGeometryProgramNumOutputVertices()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setGeometryProgramNumOutputVertices
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setGeometryProgramNumOutputVertices_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint numOutputVertices
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getGeometryProgramNumOutputVertices()
 * Type:       non-virtual method
 * Definition: int Ogre::RenderSystemCapabilities::getGeometryProgramNumOutputVertices
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getGeometryProgramNumOutputVertices_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     getRenderSystemName()
 * Type:       non-virtual method
 * Definition: String Ogre::RenderSystemCapabilities::getRenderSystemName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_RenderSystemCapabilities__1getRenderSystemName_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setRenderSystemName()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setRenderSystemName
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setRenderSystemName_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring rs
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     setCategoryRelevant()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::setCategoryRelevant
 * Signature:  (Ogre_CapabilitiesCategoryZ)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1setCategoryRelevant_1_1CapabilitiesCategoryvbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint cat, 
  jboolean relevant
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     isCategoryRelevant()
 * Type:       non-virtual method
 * Definition: bool Ogre::RenderSystemCapabilities::isCategoryRelevant
 * Signature:  (Ogre_CapabilitiesCategory)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_RenderSystemCapabilities__1isCategoryRelevant_1_1CapabilitiesCategoryv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint cat
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     log()
 * Type:       non-virtual method
 * Definition: void Ogre::RenderSystemCapabilities::log
 * Signature:  (Ogre_Log)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1log_1_1Logp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong pLog
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     vendorFromString()
 * Type:       static method
 * Definition: static GPUVendor Ogre::RenderSystemCapabilities::vendorFromString
 * Signature:  (std_string)Ogre_GPUVendor
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_RenderSystemCapabilities__1vendorFromString_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jstring vendorString
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     vendorToString()
 * Type:       static method
 * Definition: static String Ogre::RenderSystemCapabilities::vendorToString
 * Signature:  (Ogre_GPUVendor)std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_RenderSystemCapabilities__1vendorToString_1_1GPUVendorv (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jint v
);

/*
 * Class:      org.ogre4j.RenderSystemCapabilities
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::RenderSystemCapabilities::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_RenderSystemCapabilities__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_RenderSystemCapabilities__*/
