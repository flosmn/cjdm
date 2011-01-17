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


#ifndef __Included_org_ogre4j_VertexDeclaration__
#define __Included_org_ogre4j_VertexDeclaration__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     VertexDeclaration()
 * Type:       constructor
 * Definition: Ogre::VertexDeclaration::VertexDeclaration
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexDeclaration__1_1createVertexDeclaration (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     getElementCount()
 * Type:       non-virtual method
 * Definition: size_t Ogre::VertexDeclaration::getElementCount
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_VertexDeclaration__1getElementCount (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     getElements()
 * Type:       non-virtual method
 * Definition: const VertexElementList& Ogre::VertexDeclaration::getElements
 * Signature:  ()Ogre_VertexDeclaration_VertexElementList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexDeclaration__1getElements_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     getElement()
 * Type:       non-virtual method
 * Definition: const VertexElement* Ogre::VertexDeclaration::getElement
 * Signature:  (S)Ogre_VertexElement
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexDeclaration__1getElement_1_1Hv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint index
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     sort()
 * Type:       non-virtual method
 * Definition: void Ogre::VertexDeclaration::sort
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VertexDeclaration__1sort (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     closeGapsInSource()
 * Type:       non-virtual method
 * Definition: void Ogre::VertexDeclaration::closeGapsInSource
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VertexDeclaration__1closeGapsInSource (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     getAutoOrganisedDeclaration()
 * Type:       non-virtual method
 * Definition: VertexDeclaration* Ogre::VertexDeclaration::getAutoOrganisedDeclaration
 * Signature:  (ZZ)Ogre_VertexDeclaration
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexDeclaration__1getAutoOrganisedDeclaration_1_1bvbv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jboolean skeletalAnimation, 
  jboolean vertexAnimation
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     getMaxSource()
 * Type:       non-virtual method
 * Definition: unsigned short Ogre::VertexDeclaration::getMaxSource
 * Signature:  ()S
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_VertexDeclaration__1getMaxSource_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     addElement()
 * Type:       virtual method
 * Definition: virtual const VertexElement& Ogre::VertexDeclaration::addElement
 * Signature:  (SIOgre_VertexElementTypeOgre_VertexElementSemanticS)Ogre_VertexElement
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexDeclaration__1addElement_1_1HvivVertexElementTypevVertexElementSemanticvHv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint source, 
  jint offset, 
  jint theType, 
  jint semantic, 
  jint index
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     insertElement()
 * Type:       virtual method
 * Definition: virtual const VertexElement& Ogre::VertexDeclaration::insertElement
 * Signature:  (SSIOgre_VertexElementTypeOgre_VertexElementSemanticS)Ogre_VertexElement
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexDeclaration__1insertElement_1_1HvHvivVertexElementTypevVertexElementSemanticvHv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint atPosition, 
  jint source, 
  jint offset, 
  jint theType, 
  jint semantic, 
  jint index
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     removeElement()
 * Type:       virtual method
 * Definition: virtual void Ogre::VertexDeclaration::removeElement
 * Signature:  (S)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VertexDeclaration__1removeElement_1_1Hv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint elem_index
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     removeElement()
 * Type:       virtual method
 * Definition: virtual void Ogre::VertexDeclaration::removeElement
 * Signature:  (Ogre_VertexElementSemanticS)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VertexDeclaration__1removeElement_1_1VertexElementSemanticvHv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint semantic, 
  jint index
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     removeAllElements()
 * Type:       virtual method
 * Definition: virtual void Ogre::VertexDeclaration::removeAllElements
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VertexDeclaration__1removeAllElements (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     modifyElement()
 * Type:       virtual method
 * Definition: virtual void Ogre::VertexDeclaration::modifyElement
 * Signature:  (SSIOgre_VertexElementTypeOgre_VertexElementSemanticS)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VertexDeclaration__1modifyElement_1_1HvHvivVertexElementTypevVertexElementSemanticvHv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint elem_index, 
  jint source, 
  jint offset, 
  jint theType, 
  jint semantic, 
  jint index
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     findElementBySemantic()
 * Type:       virtual method
 * Definition: virtual const VertexElement* Ogre::VertexDeclaration::findElementBySemantic
 * Signature:  (Ogre_VertexElementSemanticS)Ogre_VertexElement
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexDeclaration__1findElementBySemantic_1_1VertexElementSemanticvHv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint sem, 
  jint index
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     findElementsBySource()
 * Type:       virtual method
 * Definition: virtual VertexElementList Ogre::VertexDeclaration::findElementsBySource
 * Signature:  (S)Ogre_VertexDeclaration_VertexElementList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexDeclaration__1findElementsBySource_1_1Hv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint source
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     getVertexSize()
 * Type:       virtual method
 * Definition: virtual size_t Ogre::VertexDeclaration::getVertexSize
 * Signature:  (S)I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_VertexDeclaration__1getVertexSize_1_1Hv (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint source
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     clone()
 * Type:       virtual method
 * Definition: virtual VertexDeclaration* Ogre::VertexDeclaration::clone
 * Signature:  ()Ogre_VertexDeclaration
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VertexDeclaration__1clone (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     operator==()
 * Type:       non-virtual method
 * Definition: bool Ogre::VertexDeclaration::operator==
 * Signature:  (Ogre_VertexDeclaration)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_VertexDeclaration__1operatorEqual_1_1VertexDeclarationR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rhs
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     operator!=()
 * Type:       non-virtual method
 * Definition: bool Ogre::VertexDeclaration::operator!=
 * Signature:  (Ogre_VertexDeclaration)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_VertexDeclaration__1operatorNotEqual_1_1VertexDeclarationR_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rhs
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     vertexElementLess()
 * Type:       static method
 * Definition: static bool Ogre::VertexDeclaration::vertexElementLess
 * Signature:  (Ogre_VertexElementOgre_VertexElement)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_VertexDeclaration__1vertexElementLess_1_1VertexElementRVertexElementR (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong e1, 
  jlong e2
);

/*
 * Class:      org.ogre4j.VertexDeclaration
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::VertexDeclaration::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VertexDeclaration__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_VertexDeclaration__*/
