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


#ifndef __Included_org_ogre4j_Node_00024Listener__
#define __Included_org_ogre4j_Node_00024Listener__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.Node.00024Listener
 * Method:     Listener()
 * Type:       constructor
 * Definition: Ogre::Node::Listener::Listener
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Node_00024Listener__1_1createListener (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.Node.00024Listener
 * Method:     nodeUpdated()
 * Type:       virtual method
 * Definition: virtual void Ogre::Node::Listener::nodeUpdated
 * Signature:  (Ogre_Node)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Node_00024Listener__1nodeUpdated_1_1NodeP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong a1
);

/*
 * Class:      org.ogre4j.Node.00024Listener
 * Method:     nodeDestroyed()
 * Type:       virtual method
 * Definition: virtual void Ogre::Node::Listener::nodeDestroyed
 * Signature:  (Ogre_Node)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Node_00024Listener__1nodeDestroyed_1_1NodeP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong a1
);

/*
 * Class:      org.ogre4j.Node.00024Listener
 * Method:     nodeAttached()
 * Type:       virtual method
 * Definition: virtual void Ogre::Node::Listener::nodeAttached
 * Signature:  (Ogre_Node)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Node_00024Listener__1nodeAttached_1_1NodeP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong a1
);

/*
 * Class:      org.ogre4j.Node.00024Listener
 * Method:     nodeDetached()
 * Type:       virtual method
 * Definition: virtual void Ogre::Node::Listener::nodeDetached
 * Signature:  (Ogre_Node)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Node_00024Listener__1nodeDetached_1_1NodeP (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong a1
);

/*
 * Class:      org.ogre4j.Node.00024Listener
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::Node::Listener::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Node_00024Listener__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_Node_00024Listener__*/
