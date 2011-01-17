#include "Ogre4JStableHeaders.h"
#include "Ogre4JAbstractNativeListener.h"

namespace Ogre4j
{
	//--------------------------------------------------------------------
	AbstractNativeListener::AbstractNativeListener(JNIEnv* env, jobject that)
	{
		env->GetJavaVM(&mVM);
		mJavaVersion = env->GetVersion();
		mThat = env->NewGlobalRef(that);
	}

	//--------------------------------------------------------------------
	AbstractNativeListener::~AbstractNativeListener()
	{
		JNIEnv* env = getEnv();
		env->DeleteGlobalRef(mThat);
	}

	//---------------------------------------------------------------------
	JNIEnv* AbstractNativeListener::getEnv() const
	{
		JNIEnv* env = 0;
		mVM->GetEnv((void**) &env, mJavaVersion);
		return env;
	}
} // namespace Ogre4J
