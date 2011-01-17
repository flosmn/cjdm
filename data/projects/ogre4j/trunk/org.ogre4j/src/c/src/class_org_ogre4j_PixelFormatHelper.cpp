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



// use base library for cpp2j
#include "jni_base_all.h"

// includes from config
#include <Ogre4JStableHeaders.h>

// import declaration of all functions
#include "class_org_ogre4j_PixelFormatHelper.h"

// import header files of original library
#include <OgrePixelFormat.h>

/*
 * Class:org_ogre4j_PixelFormatHelper
 * Method:getEnumValues
 * Signature:()[I
 */
JNIEXPORT jintArray JNICALL Java_org_ogre4j_PixelFormatHelper_getEnumValues
(JNIEnv * env, jclass that)
{
    jint enum_values[] = {
        Ogre::PF_UNKNOWN,
        Ogre::PF_L8,
        Ogre::PF_BYTE_L,
        Ogre::PF_L16,
        Ogre::PF_SHORT_L,
        Ogre::PF_A8,
        Ogre::PF_BYTE_A,
        Ogre::PF_A4L4,
        Ogre::PF_BYTE_LA,
        Ogre::PF_R5G6B5,
        Ogre::PF_B5G6R5,
        Ogre::PF_R3G3B2,
        Ogre::PF_A4R4G4B4,
        Ogre::PF_A1R5G5B5,
        Ogre::PF_R8G8B8,
        Ogre::PF_B8G8R8,
        Ogre::PF_A8R8G8B8,
        Ogre::PF_A8B8G8R8,
        Ogre::PF_B8G8R8A8,
        Ogre::PF_R8G8B8A8,
        Ogre::PF_X8R8G8B8,
        Ogre::PF_X8B8G8R8,
        Ogre::PF_BYTE_RGB,
        Ogre::PF_BYTE_BGR,
        Ogre::PF_BYTE_BGRA,
        Ogre::PF_BYTE_RGBA,
        Ogre::PF_A2R10G10B10,
        Ogre::PF_A2B10G10R10,
        Ogre::PF_DXT1,
        Ogre::PF_DXT2,
        Ogre::PF_DXT3,
        Ogre::PF_DXT4,
        Ogre::PF_DXT5,
        Ogre::PF_FLOAT16_R,
        Ogre::PF_FLOAT16_RGB,
        Ogre::PF_FLOAT16_RGBA,
        Ogre::PF_FLOAT32_R,
        Ogre::PF_FLOAT32_RGB,
        Ogre::PF_FLOAT32_RGBA,
        Ogre::PF_FLOAT16_GR,
        Ogre::PF_FLOAT32_GR,
        Ogre::PF_DEPTH,
        Ogre::PF_SHORT_RGBA,
        Ogre::PF_SHORT_GR,
        Ogre::PF_SHORT_RGB,
        Ogre::PF_COUNT
    };
    jintArray array = env->NewIntArray(46);
    env->SetIntArrayRegion(array, 0,46, enum_values);
    return array;
}
