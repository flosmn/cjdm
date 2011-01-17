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


#ifndef _Included_jni_base_
#define _Included_jni_base_

#include <jni.h>
#include <string>


#ifdef WIN32
	#define EXPORT __declspec(dllexport)
#else
	#define EXPORT
#endif


namespace org { namespace xbig { namespace jni {

EXPORT std::string& to_stdstring(JNIEnv* env, jstring jString, std::string& outString);

EXPORT std::string& to_stdstringUTF8(JNIEnv* env, jstring jString, std::string& outString);

EXPORT std::wstring& to_stdwstring(JNIEnv* env, jstring jString, std::wstring& outString);

EXPORT char* to_cstring(JNIEnv* env, jstring jString, char* outString);

EXPORT jstring to_jstring(JNIEnv* env, const std::string& str);

EXPORT jstring to_jstring(JNIEnv* env, const char*);

EXPORT jstring to_jstring(JNIEnv* env, const std::wstring&);

EXPORT jsize getStringLength(JNIEnv* env, const jstring& jString);

}}}

#ifdef __cplusplus
extern "C" {
#endif




#ifdef __cplusplus
}
#endif
#endif
