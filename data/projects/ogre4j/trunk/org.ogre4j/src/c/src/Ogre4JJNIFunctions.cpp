#include <Ogre4JStableHeaders.h>
#include "Ogre4JNativeLogListener.h"
#include "Ogre4JNativeRenderTargetListener.h"
#include "Ogre4JJNIFunctions.h"

//-----------------------------------------------------------------------------
JNIEXPORT jlong JNICALL Java_org_ogre4j_nativelisteners_NativeLogListener__1_1createNativeLogListener(
	JNIEnv * env, jobject that)
{
	Ogre4j::NativeLogListener * cppThis = new Ogre4j::NativeLogListener(env, that);
	return reinterpret_cast<jlong>(cppThis);
}

//-----------------------------------------------------------------------------
JNIEXPORT void JNICALL Java_org_ogre4j_nativelisteners_NativeLogListener__1_1deleteNativeLogListener(
	JNIEnv * env, jobject that, jlong pInstance)
{
	Ogre4j::NativeLogListener * cppThis = reinterpret_cast<Ogre4j::NativeLogListener*>(pInstance);
	delete cppThis;
}

//-----------------------------------------------------------------------------
JNIEXPORT jlong JNICALL Java_org_ogre4j_nativelisteners_NativeRenderTargetListener__1_1createNativeRenderTargetListener(
	JNIEnv * env, jobject that)
{
	Ogre4j::NativeRenderTargetListener * cppThis = new Ogre4j::NativeRenderTargetListener(env, that);
	return reinterpret_cast<jlong>(cppThis);
}

//-----------------------------------------------------------------------------
JNIEXPORT void JNICALL Java_org_ogre4j_nativelisteners_NativeRenderTargetListener__1_1deleteNativeRenderTargetListener(
	JNIEnv * env, jobject that, jlong pInstance)
{
	Ogre4j::NativeRenderTargetListener * cppThis = reinterpret_cast<Ogre4j::NativeRenderTargetListener*>(pInstance);
	delete cppThis;
}

//-----------------------------------------------------------------------------
void Ogre4j::handleOgreException(JNIEnv* _jni_env_, const Ogre::Exception& e)
{
    std::string msg = e.getFullDescription();
    jclass ogre4jException = _jni_env_->FindClass("org/ogre4j/exceptions/Ogre4jException");
    if (ogre4jException == NULL)
    {
        std::cerr << msg.c_str() << std::endl;
        return;
    }
    _jni_env_->ThrowNew(ogre4jException, msg.c_str());
}

//-----------------------------------------------------------------------------
void Ogre4j::handleUnknownException(JNIEnv* _jni_env_, const std::string& methodName)
{
    std::string msg = "Unknown Exception in " + methodName;
    jclass unknownException = _jni_env_->FindClass("org/ogre4j/exceptions/UnknownException");
    if (unknownException == NULL)
    {
        std::cerr << msg.c_str() << std::endl;
        return;
    }
    _jni_env_->ThrowNew(unknownException, msg.c_str());
}
