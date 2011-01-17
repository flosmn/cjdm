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

#include <iostream>
#include <string.h>


#ifdef WIN32
#include <windows.h>

namespace org { namespace xbig { namespace jni {

void windowsStringConverter(JNIEnv* env, jstring jString, std::string& outString, int codePage)
{
	// note: jchar and wchar_t are both 2 bytes with msvc
	const wchar_t* c_str = (const wchar_t*)env->GetStringChars(jString, 0);

    int requiredSize = WideCharToMultiByte( codePage, 0, c_str, -1,
        0, 0, NULL, NULL );

    char * dest = new char[ requiredSize ];
    WideCharToMultiByte( codePage, 0, c_str, -1,
                         dest, requiredSize, NULL, NULL );
    outString = dest;
    delete[] dest;
    env->ReleaseStringChars(jString, (const jchar*)c_str);
}

}}}
#endif

std::string& org::xbig::jni::to_stdstring(JNIEnv* env, jstring jString, std::string& outString) {
#ifdef WIN32
	windowsStringConverter(env, jString, outString, CP_ACP);
    return outString;
#else
    const char* c_str = env->GetStringUTFChars(jString, 0);
    outString = (c_str);
    env->ReleaseStringUTFChars(jString, c_str);
    return outString;
#endif
}

std::string& org::xbig::jni::to_stdstringUTF8(JNIEnv* env, jstring jString, std::string& outString) {
#ifdef WIN32
	windowsStringConverter(env, jString, outString, CP_UTF8);
    return outString;
#else
    return to_stdstring(env, jString, outString);
#endif
}

std::wstring& org::xbig::jni::to_stdwstring(JNIEnv* env, jstring jString, std::wstring& outString) {
    const jchar* j_str = env->GetStringChars(jString, 0);
    if (sizeof(jchar) == sizeof(wchar_t))
    {
    	outString = (const wchar_t*)j_str;
    }
    else
    {
    	for (int i=0; j_str[i]!='\0'; ++i)
    	{
    		outString.append(1, j_str[i]);
    	}
    }
    env->ReleaseStringChars(jString, j_str);
    return outString;
}

char* org::xbig::jni::to_cstring(JNIEnv* env, jstring jString, char* outString) {
	// TODO platform specific conversion
	char* c_str = (char*)env->GetStringUTFChars(jString, 0);
	strcpy (outString, c_str);
	env->ReleaseStringUTFChars(jString, c_str);
	return outString;
}


jstring org::xbig::jni::to_jstring(JNIEnv* env, const std::string& str) {
	// TODO platform specific conversion
	return env->NewStringUTF(str.c_str());
}


jstring org::xbig::jni::to_jstring(JNIEnv* env, const char* str) {
	// TODO platform specific conversion
	return env->NewStringUTF(str);
}

jstring org::xbig::jni::to_jstring(JNIEnv* env, const std::wstring& str) {
	if (sizeof(jchar) == sizeof(wchar_t))
	{
		return env->NewString((const jchar*)str.c_str(), str.length());
	}
	else
	{
		jchar* j_str = new jchar[ str.length() ];
		for (size_t i=0; i<str.length(); ++i)
		{
			j_str[i] = str.at(i);
		}
		jstring jStr = env->NewString(j_str, str.length());
		delete[] j_str;
		return jStr;
	}
}

jsize org::xbig::jni::getStringLength(JNIEnv* env, const jstring& jString) {
	return env->GetStringUTFLength(jString);
}
