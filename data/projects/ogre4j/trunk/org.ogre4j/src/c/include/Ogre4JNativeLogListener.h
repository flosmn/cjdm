#ifndef __OGRE4J_NATIVELOGLISTENER_H__
#define __OGRE4J_NATIVELOGLISTENER_H__

#include "jni.h"
#include "OgreLog.h"
#include "Ogre4JAbstractNativeListener.h"

namespace Ogre4j
{
	/**
	* OGRE LogListener which forwards log messages to a Java object.
	*/
	class JNIEXPORT NativeLogListener : public Ogre::LogListener, public AbstractNativeListener
	{
	public:

		/**
		* Name of Java method to be invoked via JNI.
		*/
		const static std::string MESSAGELOGGED_NAME;

		/**
		* Signature of Java method to be invoked via JNI.
		*/
		const static std::string MESSAGELOGGED_SIGNATURE;

		/**
		* Constructor. To be called by a native Java method during
		* construction of a Java object.
		*/
		NativeLogListener(JNIEnv* env, jobject that);

		/**
		* Destructor. Must explicitly be called from Java.
		*/
		virtual ~NativeLogListener();

		/**
		* Callback method which is forwarded to Java.
		*/
		virtual void messageLogged( const Ogre::String& message, Ogre::LogMessageLevel lml, bool maskDebug, const Ogre::String &logName );

	private:
		/**
		* Disable C-Ctor and assignment operator.
		*/
		NativeLogListener(const NativeLogListener& rhs);
		NativeLogListener& operator=(const NativeLogListener& rhs);

		/**
		* ID of Java method to be invoked during callback.
		*/
		jmethodID mMessageLoggedID;
	};
}

#endif //__OGRE4J_NATIVELOGLISTENER_H__
