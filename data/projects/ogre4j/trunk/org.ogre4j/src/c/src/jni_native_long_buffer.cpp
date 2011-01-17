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
#include <jni_native_long_buffer.h>

/*
 * Class:     base_NativeLongBuffer
 * Method:    _create
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_xbig_base_NativeLongBuffer__1create
  (JNIEnv *env, jclass that, jint size)
{
	long* buffer = new long[ size ];
	return reinterpret_cast<jlong>(buffer);
}

/*
 * Class:     base_NativeLongBuffer
 * Method:    _dispose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_xbig_base_NativeLongBuffer__1dispose
  (JNIEnv *, jobject, jlong pInstance)
{
	long* buffer = reinterpret_cast<long*>(pInstance);
	delete[] buffer;
}

/*
 * Class:     base_NativeLongBuffer
 * Method:    _get
 * Signature: (J)C
 */
JNIEXPORT jlong JNICALL Java_org_xbig_base_NativeLongBuffer__1getIndex
  (JNIEnv *, jobject, jlong pInstance, jint index)
{
	long* buffer = reinterpret_cast<long*>(pInstance);
	return buffer[index];
}

/*
 * Class:     base_NativeLongBuffer
 * Method:    _set
 * Signature: (J)C
 */
JNIEXPORT void JNICALL Java_org_xbig_base_NativeLongBuffer__1setIndex
  (JNIEnv *, jobject, jlong pInstance, jint index, jlong value)
{
	long* buffer = reinterpret_cast<long*>(pInstance);
	buffer[index] = value;
}
