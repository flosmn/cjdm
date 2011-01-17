#ifndef __OGRE4J_ABSTRACTNATIVELISTENER_H__
#define __OGRE4J_ABSTRACTNATIVELISTENER_H__

#include <jni.h>



namespace Ogre4j
{
	/**
	* Base class for native listeners. Not intended to be instantiated directly.
	*/
	class JNIEXPORT AbstractNativeListener
	{
	public:

		/**
		* Destructor. Must explicitly be called from Java.
		*/
		virtual ~AbstractNativeListener();

	protected:

		/**
		* Constructor. To be called by a native Java method during
		* construction of a Java object.
		*/
		AbstractNativeListener(JNIEnv* env, jobject that);

		/**
		* Internal method to get JNI environment pointer.
		*/
		JNIEnv* getEnv() const;

		/**
		* Corresponding Java object which will receive callbacks.
		*/
		jobject mThat;

	private:
        /** Disable default copy ctor. */
		AbstractNativeListener( const AbstractNativeListener& pre );
        /** Disable default assignment operator. */
		const AbstractNativeListener& operator= ( const AbstractNativeListener& pre );

		/**
		* Java VM. Used to obtain valid JNIEnv*.
		*/
		JavaVM* mVM;

		/**
		* Needed to obtain valid JNIEnv*.
		*/
		jint mJavaVersion;

	};
} // namespace OGRE4J

#endif // __OGRE4J_ABSTRACTNATIVELISTENER_H__
