#include <Ogre.h>
#include "jni.h"

#ifndef __OGRE4J_JNIFUNCTIONS_H__
#define __OGRE4J_JNIFUNCTIONS_H__

#ifdef __cplusplus
extern "C" {
#endif

	/**
	* Creates C++ NativeLogListener. Called during Java constructor.
	*/
	JNIEXPORT jlong JNICALL Java_org_ogre4j_nativelisteners_NativeLogListener__1_1createNativeLogListener(
		JNIEnv * env, jobject that);

	/**
	* Deletes C++ NativeLogListener.
	*/
	JNIEXPORT void JNICALL Java_org_ogre4j_nativelisteners_NativeLogListener__1_1deleteNativeLogListener(
		JNIEnv * env, jobject that, jlong pInstance);

	/**
	* Creates C++ NativeRenderTargetListener. Called during Java constructor.
	*/
	JNIEXPORT jlong JNICALL Java_org_ogre4j_nativelisteners_NativeRenderTargetListener__1_1createNativeRenderTargetListener(
		JNIEnv * env, jobject that);

	/**
	* Deletes C++ NativeRendertargetListener.
	*/
	JNIEXPORT void JNICALL Java_org_ogre4j_nativelisteners_NativeRenderTargetListener__1_1deleteNativeRenderTargetListener(
		JNIEnv * env, jobject that, jlong pInstance);

#ifdef __cplusplus
}
#endif



namespace Ogre4j {

    /**
    * Handles native OGRE exceptions via forwarding them to Java.
    */
    void handleOgreException(JNIEnv* _jni_env_, const Ogre::Exception& e);

    /**
    * To be used in a catch(...) block to throw a Java exception.
    *
    * @param methodName Method where exception occurred.
    */
    void handleUnknownException(JNIEnv* _jni_env_, const std::string& methodName);

}


#endif//__OGRE4J_JNIFUNCTIONS_H__
