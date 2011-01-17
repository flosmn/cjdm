package org.ogre4j.nativelisteners;

import org.ogre4j.ILogListener;
import org.ogre4j.LogMessageLevel;
import org.xbig.base.NativeObject;

/**
 * Allows receiving of native OGRE log messages in Java. Creates a C++ object
 * which forwards native callbacks to
 * {@link #messageLogged(String, int, boolean, String)}. Implementers should
 * use
 * {@link ILogListener#messageLogged(String, LogMessageLevel, boolean, String)}.
 * <p>
 * Must explicitly be deleted.
 * </p>
 * 
 */
public abstract class NativeLogListener extends NativeObject implements
		ILogListener {

	static {
		System.loadLibrary("ogre4j");
	}

	/**
	 * Constructor. Creates C++ object.
	 */
	public NativeLogListener() {
		long pInstance = __createNativeLogListener();
		setInstancePointer(pInstance, false);
	}

	private native long __createNativeLogListener();

	/**
	 * Callback method.
	 * 
	 * @see LogListener#messageLogged(String, org.ogre4j.LogMessageLevel,
	 *      boolean, String).
	 * 
	 * @param message
	 * @param logMessageLevel
	 * @param maskDebug
	 * @param logName
	 */
	public void messageLogged(String message, int logMessageLevel,
			boolean maskDebug, String logName) {
		messageLogged(message, LogMessageLevel.toEnum(logMessageLevel),
				maskDebug, logName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xbig.base.NativeObject#delete()
	 */
	public void delete() {
		if (this.remote) {
			throw new RuntimeException(
					"can't dispose object created by native library");
		}

		if (!this.deleted) {
			__deleteNativeLogListener(object.pointer);
			this.deleted = true;
			this.object.pointer = 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	public void finalize() {
		if (!this.remote && !this.deleted) {
			delete();
		}
	}

	private final native void __deleteNativeLogListener(long _pointer_);
}
