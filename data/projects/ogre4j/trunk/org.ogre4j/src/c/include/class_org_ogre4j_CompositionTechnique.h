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


#ifndef __Included_org_ogre4j_CompositionTechnique__
#define __Included_org_ogre4j_CompositionTechnique__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     CompositionTechnique()
 * Type:       constructor
 * Definition: Ogre::CompositionTechnique::CompositionTechnique
 * Signature:  (Ogre_Compositor)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositionTechnique__1_1createCompositionTechnique_1_1Compositorp (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong parent
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     createTextureDefinition()
 * Type:       non-virtual method
 * Definition: TextureDefinition* Ogre::CompositionTechnique::createTextureDefinition
 * Signature:  (std_string)Ogre_CompositionTechnique_TextureDefinition
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositionTechnique__1createTextureDefinition_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     removeTextureDefinition()
 * Type:       non-virtual method
 * Definition: void Ogre::CompositionTechnique::removeTextureDefinition
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositionTechnique__1removeTextureDefinition_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint idx
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     getTextureDefinition()
 * Type:       non-virtual method
 * Definition: TextureDefinition* Ogre::CompositionTechnique::getTextureDefinition
 * Signature:  (I)Ogre_CompositionTechnique_TextureDefinition
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositionTechnique__1getTextureDefinition_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint idx
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     getNumTextureDefinitions()
 * Type:       non-virtual method
 * Definition: size_t Ogre::CompositionTechnique::getNumTextureDefinitions
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_CompositionTechnique__1getNumTextureDefinitions (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     removeAllTextureDefinitions()
 * Type:       non-virtual method
 * Definition: void Ogre::CompositionTechnique::removeAllTextureDefinitions
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositionTechnique__1removeAllTextureDefinitions (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     getTextureDefinitionIterator()
 * Type:       non-virtual method
 * Definition: TextureDefinitionIterator Ogre::CompositionTechnique::getTextureDefinitionIterator
 * Signature:  ()Ogre_CompositionTechnique_TextureDefinitionIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositionTechnique__1getTextureDefinitionIterator (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     createTargetPass()
 * Type:       non-virtual method
 * Definition: CompositionTargetPass* Ogre::CompositionTechnique::createTargetPass
 * Signature:  ()Ogre_CompositionTargetPass
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositionTechnique__1createTargetPass (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     removeTargetPass()
 * Type:       non-virtual method
 * Definition: void Ogre::CompositionTechnique::removeTargetPass
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositionTechnique__1removeTargetPass_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint idx
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     getTargetPass()
 * Type:       non-virtual method
 * Definition: CompositionTargetPass* Ogre::CompositionTechnique::getTargetPass
 * Signature:  (I)Ogre_CompositionTargetPass
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositionTechnique__1getTargetPass_1_1iv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint idx
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     getNumTargetPasses()
 * Type:       non-virtual method
 * Definition: size_t Ogre::CompositionTechnique::getNumTargetPasses
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_CompositionTechnique__1getNumTargetPasses (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     removeAllTargetPasses()
 * Type:       non-virtual method
 * Definition: void Ogre::CompositionTechnique::removeAllTargetPasses
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositionTechnique__1removeAllTargetPasses (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     getTargetPassIterator()
 * Type:       non-virtual method
 * Definition: TargetPassIterator Ogre::CompositionTechnique::getTargetPassIterator
 * Signature:  ()Ogre_CompositionTechnique_TargetPassIterator
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositionTechnique__1getTargetPassIterator (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     getOutputTargetPass()
 * Type:       non-virtual method
 * Definition: CompositionTargetPass* Ogre::CompositionTechnique::getOutputTargetPass
 * Signature:  ()Ogre_CompositionTargetPass
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositionTechnique__1getOutputTargetPass (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     isSupported()
 * Type:       virtual method
 * Definition: virtual bool Ogre::CompositionTechnique::isSupported
 * Signature:  (Z)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_CompositionTechnique__1isSupported_1_1bv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean allowTextureDegradation
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     createInstance()
 * Type:       virtual method
 * Definition: virtual CompositorInstance* Ogre::CompositionTechnique::createInstance
 * Signature:  (Ogre_CompositorChain)Ogre_CompositorInstance
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositionTechnique__1createInstance_1_1CompositorChainp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong chain
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     destroyInstance()
 * Type:       virtual method
 * Definition: virtual void Ogre::CompositionTechnique::destroyInstance
 * Signature:  (Ogre_CompositorInstance)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositionTechnique__1destroyInstance_1_1CompositorInstancep (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong instance
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     getParent()
 * Type:       non-virtual method
 * Definition: Compositor* Ogre::CompositionTechnique::getParent
 * Signature:  ()Ogre_Compositor
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_CompositionTechnique__1getParent (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.CompositionTechnique
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::CompositionTechnique::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_CompositionTechnique__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_CompositionTechnique__*/
