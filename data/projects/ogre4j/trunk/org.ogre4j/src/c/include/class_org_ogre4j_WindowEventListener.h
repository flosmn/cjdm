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


#ifndef __Included_org_ogre4j_WindowEventListener__
#define __Included_org_ogre4j_WindowEventListener__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.WindowEventListener
 * Method:     windowMoved()
 * Type:       virtual method
 * Definition: virtual void Ogre::WindowEventListener::windowMoved
 * Signature:  (Ogre_RenderWindow)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_WindowEventListener__1windowMoved_1_1RenderWindowp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rw
);

/*
 * Class:      org.ogre4j.WindowEventListener
 * Method:     windowResized()
 * Type:       virtual method
 * Definition: virtual void Ogre::WindowEventListener::windowResized
 * Signature:  (Ogre_RenderWindow)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_WindowEventListener__1windowResized_1_1RenderWindowp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rw
);

/*
 * Class:      org.ogre4j.WindowEventListener
 * Method:     windowClosing()
 * Type:       virtual method
 * Definition: virtual bool Ogre::WindowEventListener::windowClosing
 * Signature:  (Ogre_RenderWindow)Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_WindowEventListener__1windowClosing_1_1RenderWindowp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rw
);

/*
 * Class:      org.ogre4j.WindowEventListener
 * Method:     windowClosed()
 * Type:       virtual method
 * Definition: virtual void Ogre::WindowEventListener::windowClosed
 * Signature:  (Ogre_RenderWindow)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_WindowEventListener__1windowClosed_1_1RenderWindowp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rw
);

/*
 * Class:      org.ogre4j.WindowEventListener
 * Method:     windowFocusChange()
 * Type:       virtual method
 * Definition: virtual void Ogre::WindowEventListener::windowFocusChange
 * Signature:  (Ogre_RenderWindow)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_WindowEventListener__1windowFocusChange_1_1RenderWindowp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong rw
);

/*
 * Class:      org.ogre4j.WindowEventListener
 * Method:     WindowEventListener()
 * Type:       constructor
 * Definition: Ogre::WindowEventListener::WindowEventListener
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_WindowEventListener__1_1createWindowEventListener (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.WindowEventListener
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::WindowEventListener::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_WindowEventListener__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_WindowEventListener__*/
