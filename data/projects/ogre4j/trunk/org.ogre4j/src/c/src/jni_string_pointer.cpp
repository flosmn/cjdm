/* This source file is part of XBiG
 *     (XSLT Bindings Generator)
 * For the latest info, see http://sourceforge.net/projects/xbig/
 *
 * Copyright (c) 2005 netAllied GmbH, Tettnang
 * Also see acknowledgements in Readme.html
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA, or go to
 * http://www.gnu.org/copyleft/lesser.txt.
 */
#include <jni_string_pointer.h>

/*
 * Class:     base_StringPointer
 * Method:    _create
 * Signature: (Ljava/lang/String)J
 */
JNIEXPORT jlong JNICALL Java_org_xbig_base_StringPointer__1create
  (JNIEnv *env, jclass that, jstring value)
{
	std::string * ptr = new std::string;
	org::xbig::jni::to_stdstring(env, value, *ptr);
	return reinterpret_cast<jlong>(ptr);
}


/*
 * Class:     base_StringPointer
 * Method:    _dispose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_xbig_base_StringPointer__1dispose
  (JNIEnv *, jobject that, jlong pInstance)
{
	std::string * ptr = reinterpret_cast<std::string*>(pInstance);
	delete ptr;
}

/*
 * Class:     base_StringPointer
 * Method:    _get
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_xbig_base_StringPointer__1get
  (JNIEnv * env, jobject that, jlong pInstance)
{
	std::string * ptr = reinterpret_cast<std::string*>(pInstance);
	return org::xbig::jni::to_jstring(env, *ptr);
}

/*
 * Class:     base_StringPointer
 * Method:    _next
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_xbig_base_StringPointer__1next
  (JNIEnv * env, jobject that, jlong pInstance)
{
	std::string * ptr = reinterpret_cast<std::string*>(pInstance);
	ptr++;
	return reinterpret_cast<jlong>(ptr);
}

/*
 * Class:     base_StringPointer
 * Method:    _set
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_xbig_base_StringPointer__1set
  (JNIEnv * env, jobject that, jlong pInstance, jstring value)
{
	std::string * ptr = reinterpret_cast<std::string*>(pInstance);
	org::xbig::jni::to_stdstring(env, value, *ptr);
}
