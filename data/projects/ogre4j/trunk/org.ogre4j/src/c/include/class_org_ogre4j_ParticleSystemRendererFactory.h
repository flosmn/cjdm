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


#ifndef __Included_org_ogre4j_ParticleSystemRendererFactory__
#define __Included_org_ogre4j_ParticleSystemRendererFactory__


#include "jni_base.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * Class:      org.ogre4j.ParticleSystemRendererFactory
 * Method:     getType()
 * Type:       pure virtual method
 * Definition: virtual const String& Ogre::FactoryObj< T >::getType
 * Signature:  ()std_string
 */

JNIEXPORT jstring JNICALL Java_org_ogre4j_ParticleSystemRendererFactory__1getType_1const (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

/*
 * Class:      org.ogre4j.ParticleSystemRendererFactory
 * Method:     createInstance()
 * Type:       pure virtual method
 * Definition: virtual T* Ogre::FactoryObj< T >::createInstance
 * Signature:  (std_string)Ogre_ParticleSystemRenderer
 */

JNIEXPORT jlong JNICALL Java_org_ogre4j_ParticleSystemRendererFactory__1createInstance_1_1StringR (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jstring name
);

/*
 * Class:      org.ogre4j.ParticleSystemRendererFactory
 * Method:     destroyInstance()
 * Type:       pure virtual method
 * Definition: virtual void Ogre::FactoryObj< T >::destroyInstance
 * Signature:  (Ogre_ParticleSystemRenderer)V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ParticleSystemRendererFactory__1destroyInstance_1_1ParticleSystemRendererp (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_, /* C++ pointer */
  jlong a1
);

/*
 * Class:      org.ogre4j.ParticleSystemRendererFactory
 * Method:     __delete()
 * Type:       destructor
 * Definition: Ogre::ParticleSystemRendererFactory::__delete
 * Signature:  ()V
 */

JNIEXPORT void JNICALL Java_org_ogre4j_ParticleSystemRendererFactory__1_1delete (
  JNIEnv* _jni_env_, /* interface pointer */
  jobject _jni_this_, /* Java object */
  jlong _jni_pointer_ /* C++ pointer */
);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /*__Included_org_ogre4j_ParticleSystemRendererFactory__*/
