/* This source file is part of ogre4j
 *  
 * For the latest info, see http://sourceforge.net/projects/ogre4j/
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

#ifndef __OGRE4J_AWTNATIVEHANDLE_H__
#define __OGRE4J_AWTNATIVEHANDLE_H__

#include "jni.h"

#ifdef __cplusplus
extern "C" {
#endif

/**
 * Retrieves the native window handle via the AWT native interface.
 *
 */
JNIEXPORT jlong JNICALL Java_org_ogre4j_awt_OgreAwtCanvas_createRenderWindow(
	JNIEnv * env, jclass that, jobject canvasObject, jlong root);

JNIEXPORT void JNICALL Java_org_ogre4j_awt_OgreAwtCanvas_paint
(JNIEnv* env, jobject canvas, jobject graphics);


#ifdef __cplusplus
}
#endif

#endif //__OGRE4J_AWTNATIVEHANDLE_H__
