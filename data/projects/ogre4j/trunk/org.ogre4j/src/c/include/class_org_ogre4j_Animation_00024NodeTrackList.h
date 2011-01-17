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


#ifndef __Included_org_ogre4j_Animation_00024NodeTrackList__
#define __Included_org_ogre4j_Animation_00024NodeTrackList__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.Animation.00024NodeTrackList
 * Method:     NodeTrackList()
 * Type:       constructor
 * Definition: std::map::map
 * Signature:  ()V
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Animation_00024NodeTrackList__1_1createNodeTrackList (
  JNIEnv* _jni_env_, /* interface pointer */
  jclass _jni_class_  /* class pointer */
);

/*
 * Class:      org.ogre4j.Animation.00024NodeTrackList
 * Method:     clear()
 * Type:       non-virtual method
 * Definition: std::map::clear
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Animation_00024NodeTrackList__1clear (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Animation.00024NodeTrackList
 * Method:     count()
 * Type:       non-virtual method
 * Definition: std::map::count
 * Signature:  (S)I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Animation_00024NodeTrackList__1count_1_1HR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint key
);

/*
 * Class:      org.ogre4j.Animation.00024NodeTrackList
 * Method:     empty()
 * Type:       non-virtual method
 * Definition: std::map::empty
 * Signature:  ()Z
 */

JNIEXPORT jboolean JNICALL Java_org_ogre4j_Animation_00024NodeTrackList__1empty_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Animation.00024NodeTrackList
 * Method:     erase()
 * Type:       non-virtual method
 * Definition: std::map::erase
 * Signature:  (S)I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Animation_00024NodeTrackList__1erase_1_1HR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint key
);

/*
 * Class:      org.ogre4j.Animation.00024NodeTrackList
 * Method:     max_size()
 * Type:       non-virtual method
 * Definition: std::map::max_size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Animation_00024NodeTrackList__1max_1size_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Animation.00024NodeTrackList
 * Method:     size()
 * Type:       non-virtual method
 * Definition: std::map::size
 * Signature:  ()I
 */

JNIEXPORT jint JNICALL Java_org_ogre4j_Animation_00024NodeTrackList__1size_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.Animation.00024NodeTrackList
 * Method:     get()
 * Type:       non-virtual method
 * Definition: std::map::get
 * Signature:  (S)Ogre_NodeAnimationTrack
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_Animation_00024NodeTrackList__1get_1_1HR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint key
);

/*
 * Class:      org.ogre4j.Animation.00024NodeTrackList
 * Method:     insert()
 * Type:       non-virtual method
 * Definition: std::map::insert
 * Signature:  (SOgre_NodeAnimationTrack)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Animation_00024NodeTrackList__1insert_1_1HROgre_1NodeAnimationTrackp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jint key, 
  jlong value
);

/*
 * Class:      org.ogre4j.Animation.00024NodeTrackList
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::Animation::NodeTrackList::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_Animation_00024NodeTrackList__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_Animation_00024NodeTrackList__*/
