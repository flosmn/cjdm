#ifndef __OGRE4J_NATIVERENDERTARGETLISTENER_H__
#define __OGRE4J_NATIVERENDERTARGETLISTENER_H__

#include "OgreRenderTargetListener.h"
#include "Ogre4JAbstractNativeListener.h"

namespace Ogre4j
{
	/**
	* Listener wich forwards OGRE RenderTarget- and RenderTargetViewportEvents to Java.
	*/
	class JNIEXPORT NativeRenderTargetListener : public Ogre::RenderTargetListener, public AbstractNativeListener
	{
	public:

		/**
		* Constructor. To be called by a native Java method during 
		* construction of a Java object.
		*/
		NativeRenderTargetListener(JNIEnv* env, jobject that);

		/**
		* Destructor. Must explicitly be called from Java.
		*/
		virtual ~NativeRenderTargetListener();

		/**
		* Called before RenderTarget is updated.
		*/
		virtual void preRenderTargetUpdate(const Ogre::RenderTargetEvent& evt);
		
		/**
		* Called after RenderTarget has been updated.
		*/
		virtual void postRenderTargetUpdate(const Ogre::RenderTargetEvent& evt);
		
        /**
        * Called just before a Viewport on a RenderTarget is to be updated.
        * @remarks
        * This method is called before each viewport on the RenderTarget is
        * rendered to. You can use this to perform per-viewport settings changes,
        * such as showing / hiding particular overlays.
        */
		virtual void preViewportUpdate(const Ogre::RenderTargetViewportEvent& evt);

		/**
		* Called just after a Viewport on a RenderTarget is to be updated.
        * @remarks
		* This method is called after each viewport on the RenderTarget is
        * rendered to. 
		*/
		virtual void postViewportUpdate(const Ogre::RenderTargetViewportEvent& evt);

		/**
		* Called if a viewport is added.
		*/
		virtual void viewportAdded(const Ogre::RenderTargetViewportEvent& evt);

		/**
		* Called if a viewport is removed.
		*/
		virtual void viewportRemoved(const Ogre::RenderTargetViewportEvent& evt);
		
	private:
		/** Disable default copy ctor. */
		NativeRenderTargetListener( const NativeRenderTargetListener& pre );
		/** Disable default assignment operator. */
		const NativeRenderTargetListener& operator= ( const NativeRenderTargetListener& pre );
		
		/** ID of preRenderTargetUpdate method. */
		jmethodID mPreRenderTargetUpdateID;

		/** ID of postRenderTargetUpdate method. */
		jmethodID mPostRenderTargetUpdateID;

		/** ID of preViewportUpdate method. */
		jmethodID mPreViewportUpdateID;

		/** ID of postViewportUpdate method. */
		jmethodID mPostViewportUpdateID;

		/** ID of viewportAdded method. */
		jmethodID mViewportAddedID;

		/** ID of viewportRemoved method. */
		jmethodID mViewportRemovedID;

		/** Name of Java method to be invoked via JNI. */
		const static std::string PRE_RENDERTARGETUPDATE_NAME;

		/** Signature of Java method to be invoked via JNI. */
		const static std::string PRE_RENDERTARGETUPDATE_SIGNATURE;

		/** Name of Java method to be invoked via JNI. */
		const static std::string POST_RENDERTARGETUPDATE_NAME;

		/** Signature of Java method to be invoked via JNI. */
		const static std::string POST_RENDERTARGETUPDATE_SIGNATURE;

		/** Name of Java method to be invoked via JNI. */
		const static std::string PRE_VIEWPORTUPDATE_NAME;

		/** Signature of Java method to be invoked via JNI. */
		const static std::string PRE_VIEWPORTUPDATE_SIGNATURE;

		/** Name of Java method to be invoked via JNI. */
		const static std::string POST_VIEWPORTUPDATE_NAME;

		/** Signature of Java method to be invoked via JNI. */
		const static std::string POST_VIEWPORTUPDATE_SIGNATURE;

		/** Name of Java method to be invoked via JNI. */
		const static std::string VIEWPORT_ADDED_NAME;

		/** Signature of Java method to be invoked via JNI. */
		const static std::string VIEWPORT_ADDED_SIGNATURE;

		/** Name of Java method to be invoked via JNI. */
		const static std::string VIEWPORT_REMOVED_NAME;

		/** Signature of Java method to be invoked via JNI. */
		const static std::string VIEWPORT_REMOVED_SIGNATURE;

	};
} // namespace OGRE4J

#endif // __OGRE4J_NATIVERENDERTARGETLISTENER_H__
