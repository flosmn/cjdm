#include <Ogre4JStableHeaders.h>
#include "Ogre4JNativeRenderTargetListener.h"

namespace Ogre4j
{
	//preRenderTargetUpdate
	const std::string NativeRenderTargetListener::PRE_RENDERTARGETUPDATE_NAME = "preRenderTargetUpdate";
	const std::string NativeRenderTargetListener::PRE_RENDERTARGETUPDATE_SIGNATURE = "(J)V";

	//postRenderTargetUpdate
	const std::string NativeRenderTargetListener::POST_RENDERTARGETUPDATE_NAME = "postRenderTargetUpdate";
	const std::string NativeRenderTargetListener::POST_RENDERTARGETUPDATE_SIGNATURE = "(J)V";

	//preViewportUpdateUpdate
	const std::string NativeRenderTargetListener::PRE_VIEWPORTUPDATE_NAME = "preViewportUpdate";
	const std::string NativeRenderTargetListener::PRE_VIEWPORTUPDATE_SIGNATURE = "(J)V";

	//postViewportUpdateUpdate
	const std::string NativeRenderTargetListener::POST_VIEWPORTUPDATE_NAME = "postViewportUpdate";
	const std::string NativeRenderTargetListener::POST_VIEWPORTUPDATE_SIGNATURE = "(J)V";

	//viewportAdded
	const std::string NativeRenderTargetListener::VIEWPORT_ADDED_NAME = "viewportAdded";
	const std::string NativeRenderTargetListener::VIEWPORT_ADDED_SIGNATURE = "(J)V";

	//viewportRemoved
	const std::string NativeRenderTargetListener::VIEWPORT_REMOVED_NAME = "viewportAdded";
	const std::string NativeRenderTargetListener::VIEWPORT_REMOVED_SIGNATURE = "(J)V";

	//--------------------------------------------------------------------
	NativeRenderTargetListener::NativeRenderTargetListener(JNIEnv* env, jobject that)
		: AbstractNativeListener(env, that)
	{
		//preRenderTargetUpdate
		mPreRenderTargetUpdateID = env->GetMethodID(
			env->GetObjectClass(mThat), PRE_RENDERTARGETUPDATE_NAME.c_str(), PRE_RENDERTARGETUPDATE_SIGNATURE.c_str());

		//postRenderTargetUpdate
		mPostRenderTargetUpdateID = env->GetMethodID(
			env->GetObjectClass(mThat), POST_RENDERTARGETUPDATE_NAME.c_str(), POST_RENDERTARGETUPDATE_SIGNATURE.c_str());

		//preViewportUpdate
		mPreViewportUpdateID = env->GetMethodID(
			env->GetObjectClass(mThat), PRE_VIEWPORTUPDATE_NAME.c_str(), PRE_VIEWPORTUPDATE_SIGNATURE.c_str());

		//postViewportUpdate
		mPostViewportUpdateID = env->GetMethodID(
			env->GetObjectClass(mThat), POST_VIEWPORTUPDATE_NAME.c_str(), POST_VIEWPORTUPDATE_SIGNATURE.c_str());

		//viewportAdded
		mViewportAddedID = env->GetMethodID(
			env->GetObjectClass(mThat), VIEWPORT_ADDED_NAME.c_str(), VIEWPORT_ADDED_SIGNATURE.c_str());

		//viewportRemoved
		mViewportRemovedID = env->GetMethodID(
			env->GetObjectClass(mThat), VIEWPORT_REMOVED_NAME.c_str(), VIEWPORT_REMOVED_SIGNATURE.c_str());
	}
	
	//--------------------------------------------------------------------
	NativeRenderTargetListener::~NativeRenderTargetListener()
	{
	}

	//----------------------------------------------------------------------------
	void NativeRenderTargetListener::preRenderTargetUpdate(const Ogre::RenderTargetEvent &evt)
	{
		JNIEnv* env = getEnv();
		env->CallVoidMethod(mThat, mPreRenderTargetUpdateID, reinterpret_cast<jlong>(&evt));
	}

	//----------------------------------------------------------------------------
	void NativeRenderTargetListener::postRenderTargetUpdate(const Ogre::RenderTargetEvent &evt)
	{
		JNIEnv* env = getEnv();
		env->CallVoidMethod(mThat, mPostRenderTargetUpdateID, reinterpret_cast<jlong>(&evt));
	}

	//----------------------------------------------------------------------------
	void NativeRenderTargetListener::postViewportUpdate(const Ogre::RenderTargetViewportEvent& evt)
	{
		JNIEnv* env = getEnv();
		env->CallVoidMethod(mThat, mPostViewportUpdateID, reinterpret_cast<jlong>(&evt));
	}

	//----------------------------------------------------------------------------
	void NativeRenderTargetListener::preViewportUpdate(const Ogre::RenderTargetViewportEvent& evt)
	{
		JNIEnv* env = getEnv();
		env->CallVoidMethod(mThat, mPreViewportUpdateID, reinterpret_cast<jlong>(&evt));
	}
	
	//----------------------------------------------------------------------------
	void NativeRenderTargetListener::viewportAdded(const Ogre::RenderTargetViewportEvent& evt)
	{
		JNIEnv* env = getEnv();
		env->CallVoidMethod(mThat, mViewportAddedID, reinterpret_cast<jlong>(&evt));
	}

	//----------------------------------------------------------------------------
	void NativeRenderTargetListener::viewportRemoved(const Ogre::RenderTargetViewportEvent& evt)
	{
		JNIEnv* env = getEnv();
		env->CallVoidMethod(mThat, mViewportRemovedID, reinterpret_cast<jlong>(&evt));
	}


} // namespace Ogre4j
