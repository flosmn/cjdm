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
#include <jni_enum_pointer.h>

/*
 * Class:     org_xbig_base_EnumPointer
 * Method:    _get
 * Signature: (I)J
 */
JNIEXPORT jint JNICALL Java_org_xbig_base_EnumPointer__1get
  (JNIEnv *, jobject, jlong pInstance)
{
	int* _cpp_this = reinterpret_cast<int*>(pInstance);
	return *_cpp_this;
}

/*
 * Class:     org_xbig_base_EnumPointer
 * Method:    _set
 * Signature: ()I
 */
JNIEXPORT void JNICALL Java_org_xbig_base_EnumPointer__1set
  (JNIEnv *, jobject, jlong pInstance, jint _jni_value)
{
	int* _cpp_this = reinterpret_cast<int*>(pInstance);
	*_cpp_this = _jni_value;
}

/*
 * Class:     org_xbig_base_EnumPointer
 * Method:    __createEnumPointer
 * Signature: (J)
 */
JNIEXPORT jlong JNICALL Java_org_xbig_base_EnumPointer__1_1createEnumPointer
  (JNIEnv *, jclass, jint _jni_value)
{
	int* _cpp_this = new int(_jni_value);
	return reinterpret_cast<jlong>(_cpp_this);
}

/*
 * Class:     org_xbig_base_EnumPointer
 * Method:    __delete(long)
 * Signature: ()
 */
JNIEXPORT void JNICALL Java_org_xbig_base_EnumPointer__1_1delete
  (JNIEnv *, jobject, jlong pInstance)
{
	int* _cpp_this = reinterpret_cast<int*>(pInstance);
	delete _cpp_this;
}
