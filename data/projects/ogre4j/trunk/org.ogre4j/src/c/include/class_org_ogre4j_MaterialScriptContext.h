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


#ifndef __Included_org_ogre4j_MaterialScriptContext__
#define __Included_org_ogre4j_MaterialScriptContext__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     MaterialScriptContext()
 * Type:       constructor
 * Definition: Ogre::MaterialScriptContext::MaterialScriptContext
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MaterialScriptContext__1_1createMaterialScriptContext (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::MaterialScriptContext::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getsection()
 * Type:       getter for public attribute
 * Definition: MaterialScriptSection Ogre::MaterialScriptContext::section
 * Signature:  ()Ogre_MaterialScriptSection
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_MaterialScriptContext__1getsection (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setsection()
 * Type:       setter for public attribute
 * Definition: MaterialScriptSection Ogre::MaterialScriptContext::section
 * Signature:  (Ogre_MaterialScriptSection)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setsection (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getgroupName()
 * Type:       getter for public attribute
 * Definition: String Ogre::MaterialScriptContext::groupName
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_MaterialScriptContext__1getgroupName (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setgroupName()
 * Type:       setter for public attribute
 * Definition: String Ogre::MaterialScriptContext::groupName
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setgroupName (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getmaterial()
 * Type:       getter for public attribute
 * Definition: MaterialPtr Ogre::MaterialScriptContext::material
 * Signature:  ()Ogre_MaterialPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MaterialScriptContext__1getmaterial (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setmaterial()
 * Type:       setter for public attribute
 * Definition: MaterialPtr Ogre::MaterialScriptContext::material
 * Signature:  (Ogre_MaterialPtr)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setmaterial (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     gettechnique()
 * Type:       getter for public attribute
 * Definition: Technique* Ogre::MaterialScriptContext::technique
 * Signature:  ()Ogre_Technique
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MaterialScriptContext__1gettechnique (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     settechnique()
 * Type:       setter for public attribute
 * Definition: Technique* Ogre::MaterialScriptContext::technique
 * Signature:  (Ogre_Technique)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1settechnique (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getpass()
 * Type:       getter for public attribute
 * Definition: Pass* Ogre::MaterialScriptContext::pass
 * Signature:  ()Ogre_Pass
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MaterialScriptContext__1getpass (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setpass()
 * Type:       setter for public attribute
 * Definition: Pass* Ogre::MaterialScriptContext::pass
 * Signature:  (Ogre_Pass)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setpass (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     gettextureUnit()
 * Type:       getter for public attribute
 * Definition: TextureUnitState* Ogre::MaterialScriptContext::textureUnit
 * Signature:  ()Ogre_TextureUnitState
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MaterialScriptContext__1gettextureUnit (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     settextureUnit()
 * Type:       setter for public attribute
 * Definition: TextureUnitState* Ogre::MaterialScriptContext::textureUnit
 * Signature:  (Ogre_TextureUnitState)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1settextureUnit (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getprogram()
 * Type:       getter for public attribute
 * Definition: GpuProgramPtr Ogre::MaterialScriptContext::program
 * Signature:  ()Ogre_GpuProgramPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MaterialScriptContext__1getprogram (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setprogram()
 * Type:       setter for public attribute
 * Definition: GpuProgramPtr Ogre::MaterialScriptContext::program
 * Signature:  (Ogre_GpuProgramPtr)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setprogram (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getisProgramShadowCaster()
 * Type:       getter for public attribute
 * Definition: bool Ogre::MaterialScriptContext::isProgramShadowCaster
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MaterialScriptContext__1getisProgramShadowCaster (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setisProgramShadowCaster()
 * Type:       setter for public attribute
 * Definition: bool Ogre::MaterialScriptContext::isProgramShadowCaster
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setisProgramShadowCaster (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getisVertexProgramShadowReceiver()
 * Type:       getter for public attribute
 * Definition: bool Ogre::MaterialScriptContext::isVertexProgramShadowReceiver
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MaterialScriptContext__1getisVertexProgramShadowReceiver (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setisVertexProgramShadowReceiver()
 * Type:       setter for public attribute
 * Definition: bool Ogre::MaterialScriptContext::isVertexProgramShadowReceiver
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setisVertexProgramShadowReceiver (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getisFragmentProgramShadowReceiver()
 * Type:       getter for public attribute
 * Definition: bool Ogre::MaterialScriptContext::isFragmentProgramShadowReceiver
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_MaterialScriptContext__1getisFragmentProgramShadowReceiver (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setisFragmentProgramShadowReceiver()
 * Type:       setter for public attribute
 * Definition: bool Ogre::MaterialScriptContext::isFragmentProgramShadowReceiver
 * Signature:  (Z)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setisFragmentProgramShadowReceiver (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getprogramParams()
 * Type:       getter for public attribute
 * Definition: GpuProgramParametersSharedPtr Ogre::MaterialScriptContext::programParams
 * Signature:  ()Ogre_GpuProgramParametersSharedPtr
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MaterialScriptContext__1getprogramParams (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setprogramParams()
 * Type:       setter for public attribute
 * Definition: GpuProgramParametersSharedPtr Ogre::MaterialScriptContext::programParams
 * Signature:  (Ogre_GpuProgramParametersSharedPtr)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setprogramParams (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getnumAnimationParametrics()
 * Type:       getter for public attribute
 * Definition: ushort Ogre::MaterialScriptContext::numAnimationParametrics
 * Signature:  ()unsigned_short
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_MaterialScriptContext__1getnumAnimationParametrics (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setnumAnimationParametrics()
 * Type:       setter for public attribute
 * Definition: ushort Ogre::MaterialScriptContext::numAnimationParametrics
 * Signature:  (unsigned_short)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setnumAnimationParametrics (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getprogramDef()
 * Type:       getter for public attribute
 * Definition: MaterialScriptProgramDefinition* Ogre::MaterialScriptContext::programDef
 * Signature:  ()Ogre_MaterialScriptProgramDefinition
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MaterialScriptContext__1getprogramDef (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setprogramDef()
 * Type:       setter for public attribute
 * Definition: MaterialScriptProgramDefinition* Ogre::MaterialScriptContext::programDef
 * Signature:  (Ogre_MaterialScriptProgramDefinition)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setprogramDef (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     gettechLev()
 * Type:       getter for public attribute
 * Definition: int Ogre::MaterialScriptContext::techLev
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_MaterialScriptContext__1gettechLev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     settechLev()
 * Type:       setter for public attribute
 * Definition: int Ogre::MaterialScriptContext::techLev
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1settechLev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getpassLev()
 * Type:       getter for public attribute
 * Definition: int Ogre::MaterialScriptContext::passLev
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_MaterialScriptContext__1getpassLev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setpassLev()
 * Type:       setter for public attribute
 * Definition: int Ogre::MaterialScriptContext::passLev
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setpassLev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getstateLev()
 * Type:       getter for public attribute
 * Definition: int Ogre::MaterialScriptContext::stateLev
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_MaterialScriptContext__1getstateLev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setstateLev()
 * Type:       setter for public attribute
 * Definition: int Ogre::MaterialScriptContext::stateLev
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setstateLev (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getdefaultParamLines()
 * Type:       getter for public attribute
 * Definition: StringVector Ogre::MaterialScriptContext::defaultParamLines
 * Signature:  ()Ogre_StringVector
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MaterialScriptContext__1getdefaultParamLines (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setdefaultParamLines()
 * Type:       setter for public attribute
 * Definition: StringVector Ogre::MaterialScriptContext::defaultParamLines
 * Signature:  (Ogre_StringVector)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setdefaultParamLines (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getlineNo()
 * Type:       getter for public attribute
 * Definition: size_t Ogre::MaterialScriptContext::lineNo
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_MaterialScriptContext__1getlineNo (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setlineNo()
 * Type:       setter for public attribute
 * Definition: size_t Ogre::MaterialScriptContext::lineNo
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setlineNo (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     getfilename()
 * Type:       getter for public attribute
 * Definition: String Ogre::MaterialScriptContext::filename
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_MaterialScriptContext__1getfilename (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     setfilename()
 * Type:       setter for public attribute
 * Definition: String Ogre::MaterialScriptContext::filename
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1setfilename (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     gettextureAliases()
 * Type:       getter for public attribute
 * Definition: AliasTextureNamePairList Ogre::MaterialScriptContext::textureAliases
 * Signature:  ()Ogre_AliasTextureNamePairList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_MaterialScriptContext__1gettextureAliases (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.MaterialScriptContext
 * Method:     settextureAliases()
 * Type:       setter for public attribute
 * Definition: AliasTextureNamePairList Ogre::MaterialScriptContext::textureAliases
 * Signature:  (Ogre_AliasTextureNamePairList)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_MaterialScriptContext__1settextureAliases (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_MaterialScriptContext__*/
