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
#include "class_org_ogre4j_Plane_00024SideHelper.h"

// import header files of original library
#include <OgrePlane.h>

/*
 * Class:org_ogre4j_Plane_00024SideHelper
 * Method:getEnumValues
 * Signature:()[I
 */
JNIEXPORT jintArray JNICALL Java_org_ogre4j_Plane_00024SideHelper_getEnumValues
(JNIEnv * env, jclass that)
{
    jint enum_values[] = {
        Ogre::Plane::NO_SIDE,
        Ogre::Plane::POSITIVE_SIDE,
        Ogre::Plane::NEGATIVE_SIDE,
        Ogre::Plane::BOTH_SIDE
    };
    jintArray array = env->NewIntArray(4);
    env->SetIntArrayRegion(array, 0,4, enum_values);
    return array;
}
