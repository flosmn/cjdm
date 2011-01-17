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


#ifndef __Included_org_ogre4j_VariableAccessAbstractNode__
#define __Included_org_ogre4j_VariableAccessAbstractNode__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     VariableAccessAbstractNode()
 * Type:       constructor
 * Definition: Ogre::VariableAccessAbstractNode::VariableAccessAbstractNode
 * Signature:  (Ogre_AbstractNode)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1_1createVariableAccessAbstractNode_1_1AbstractNodep (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong ptr
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     clone()
 * Type:       virtual method
 * Definition: AbstractNode* Ogre::VariableAccessAbstractNode::clone
 * Signature:  ()Ogre_AbstractNode
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1clone_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     getValue()
 * Type:       virtual method
 * Definition: String Ogre::VariableAccessAbstractNode::getValue
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1getValue_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::VariableAccessAbstractNode::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     getname()
 * Type:       getter for public attribute
 * Definition: String Ogre::VariableAccessAbstractNode::name
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1getname (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     setname()
 * Type:       setter for public attribute
 * Definition: String Ogre::VariableAccessAbstractNode::name
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1setname (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     getfile()
 * Type:       getter for public attribute
 * Definition: String Ogre::AbstractNode::file
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1getfile (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     setfile()
 * Type:       setter for public attribute
 * Definition: String Ogre::AbstractNode::file
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1setfile (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     getline()
 * Type:       getter for public attribute
 * Definition: unsigned int Ogre::AbstractNode::line
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1getline (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     setline()
 * Type:       setter for public attribute
 * Definition: unsigned int Ogre::AbstractNode::line
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1setline (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     gettype()
 * Type:       getter for public attribute
 * Definition: AbstractNodeType Ogre::AbstractNode::type
 * Signature:  ()Ogre_AbstractNodeType
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1gettype (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     settype()
 * Type:       setter for public attribute
 * Definition: AbstractNodeType Ogre::AbstractNode::type
 * Signature:  (Ogre_AbstractNodeType)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1settype (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     getparent()
 * Type:       getter for public attribute
 * Definition: AbstractNode* Ogre::AbstractNode::parent
 * Signature:  ()Ogre_AbstractNode
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1getparent (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     setparent()
 * Type:       setter for public attribute
 * Definition: AbstractNode* Ogre::AbstractNode::parent
 * Signature:  (Ogre_AbstractNode)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1setparent (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     getcontext()
 * Type:       getter for public attribute
 * Definition: Any Ogre::AbstractNode::context
 * Signature:  ()Ogre_Any
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1getcontext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.VariableAccessAbstractNode
 * Method:     setcontext()
 * Type:       setter for public attribute
 * Definition: Any Ogre::AbstractNode::context
 * Signature:  (Ogre_Any)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_VariableAccessAbstractNode__1setcontext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_VariableAccessAbstractNode__*/
