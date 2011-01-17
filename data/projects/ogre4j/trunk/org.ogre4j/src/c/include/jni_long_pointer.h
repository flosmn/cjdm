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


#include <jni_base.h>

/* Header for class base_LongPointer */

#ifndef _Included_base_LongPointer
#define _Included_base_LongPointer
#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     base_LongPointer
 * Method:    _create
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_xbig_base_LongPointer__1create
  (JNIEnv *env, jclass that, jlong value);

/*
 * Class:     base_LongPointer
 * Method:    _dispose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_xbig_base_LongPointer__1dispose
  (JNIEnv *, jobject, jlong);

/*
 * Class:     base_LongPointer
 * Method:    _get
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_xbig_base_LongPointer__1get
  (JNIEnv *, jobject, jlong);

/*
 * Class:     base_LongPointer
 * Method:    _next
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_xbig_base_LongPointer__1next
  (JNIEnv *, jobject, jlong);

/*
 * Class:     base_LongPointer
 * Method:    _set
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_org_xbig_base_LongPointer__1set
  (JNIEnv *, jobject, jlong, jlong);

#ifdef __cplusplus
}
#endif
#endif
