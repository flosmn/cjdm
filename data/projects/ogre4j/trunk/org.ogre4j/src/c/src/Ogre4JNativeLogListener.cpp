#include <Ogre4JStableHeaders.h>
#include "Ogre4JNativeLogListener.h"


namespace Ogre4j
{
	//-----------------------------------------------------------------------------
	const std::string NativeLogListener::MESSAGELOGGED_NAME = "messageLogged";
	const std::string NativeLogListener::MESSAGELOGGED_SIGNATURE = "(Ljava/lang/String;IZLjava/lang/String;)V";

	//-----------------------------------------------------------------------------
	NativeLogListener::NativeLogListener(JNIEnv* env, jobject that)
		: AbstractNativeListener(env, that)
	{
		mMessageLoggedID = env->GetMethodID(
			env->GetObjectClass(mThat), MESSAGELOGGED_NAME.c_str(), MESSAGELOGGED_SIGNATURE.c_str());
	}

	//-----------------------------------------------------------------------------
	NativeLogListener::~NativeLogListener()
	{
	}

	//-----------------------------------------------------------------------------
	void NativeLogListener::messageLogged( const Ogre::String& message, Ogre::LogMessageLevel lml, bool maskDebug, const Ogre::String &logName )
	{
		JNIEnv* env = getEnv();
		env->CallVoidMethod(mThat, mMessageLoggedID, 
			env->NewStringUTF(message.c_str()),
			lml,
			maskDebug,
			env->NewStringUTF(logName.c_str())
		);
	}
} //namespace Ogre4j
