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


#ifndef __Included_org_ogre4j_PropertyAbstractNode__
#define __Included_org_ogre4j_PropertyAbstractNode__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     PropertyAbstractNode()
 * Type:       constructor
 * Definition: Ogre::PropertyAbstractNode::PropertyAbstractNode
 * Signature:  (Ogre_AbstractNode)V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_PropertyAbstractNode__1_1createPropertyAbstractNode_1_1AbstractNodep (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_,  /* class pointer */
  jlong ptr
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     clone()
 * Type:       virtual method
 * Definition: AbstractNode* Ogre::PropertyAbstractNode::clone
 * Signature:  ()Ogre_AbstractNode
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_PropertyAbstractNode__1clone_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     getValue()
 * Type:       virtual method
 * Definition: String Ogre::PropertyAbstractNode::getValue
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_PropertyAbstractNode__1getValue_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::PropertyAbstractNode::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PropertyAbstractNode__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     getname()
 * Type:       getter for public attribute
 * Definition: String Ogre::PropertyAbstractNode::name
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_PropertyAbstractNode__1getname (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     setname()
 * Type:       setter for public attribute
 * Definition: String Ogre::PropertyAbstractNode::name
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PropertyAbstractNode__1setname (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     getid()
 * Type:       getter for public attribute
 * Definition: uint32 Ogre::PropertyAbstractNode::id
 * Signature:  ()unsigned_int
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_PropertyAbstractNode__1getid (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     setid()
 * Type:       setter for public attribute
 * Definition: uint32 Ogre::PropertyAbstractNode::id
 * Signature:  (unsigned_int)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PropertyAbstractNode__1setid (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     getvalues()
 * Type:       getter for public attribute
 * Definition: AbstractNodeList Ogre::PropertyAbstractNode::values
 * Signature:  ()Ogre_AbstractNodeList
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_PropertyAbstractNode__1getvalues (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     setvalues()
 * Type:       setter for public attribute
 * Definition: AbstractNodeList Ogre::PropertyAbstractNode::values
 * Signature:  (Ogre_AbstractNodeList)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PropertyAbstractNode__1setvalues (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     getfile()
 * Type:       getter for public attribute
 * Definition: String Ogre::AbstractNode::file
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_PropertyAbstractNode__1getfile (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     setfile()
 * Type:       setter for public attribute
 * Definition: String Ogre::AbstractNode::file
 * Signature:  (std_string)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PropertyAbstractNode__1setfile (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring _jni_value_
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     getline()
 * Type:       getter for public attribute
 * Definition: unsigned int Ogre::AbstractNode::line
 * Signature:  ()I
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_PropertyAbstractNode__1getline (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     setline()
 * Type:       setter for public attribute
 * Definition: unsigned int Ogre::AbstractNode::line
 * Signature:  (I)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PropertyAbstractNode__1setline (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     gettype()
 * Type:       getter for public attribute
 * Definition: AbstractNodeType Ogre::AbstractNode::type
 * Signature:  ()Ogre_AbstractNodeType
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_PropertyAbstractNode__1gettype (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     settype()
 * Type:       setter for public attribute
 * Definition: AbstractNodeType Ogre::AbstractNode::type
 * Signature:  (Ogre_AbstractNodeType)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PropertyAbstractNode__1settype (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint _jni_value_
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     getparent()
 * Type:       getter for public attribute
 * Definition: AbstractNode* Ogre::AbstractNode::parent
 * Signature:  ()Ogre_AbstractNode
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_PropertyAbstractNode__1getparent (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     setparent()
 * Type:       setter for public attribute
 * Definition: AbstractNode* Ogre::AbstractNode::parent
 * Signature:  (Ogre_AbstractNode)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PropertyAbstractNode__1setparent (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     getcontext()
 * Type:       getter for public attribute
 * Definition: Any Ogre::AbstractNode::context
 * Signature:  ()Ogre_Any
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_PropertyAbstractNode__1getcontext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.PropertyAbstractNode
 * Method:     setcontext()
 * Type:       setter for public attribute
 * Definition: Any Ogre::AbstractNode::context
 * Signature:  (Ogre_Any)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_PropertyAbstractNode__1setcontext (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong _jni_value_
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_PropertyAbstractNode__*/
