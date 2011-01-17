package org.ogre4j.nativelisteners;

import org.ogre4j.IRenderTargetListener;
import org.ogre4j.RenderTargetEvent;
import org.ogre4j.RenderTargetViewportEvent;
import org.xbig.base.InstancePointer;
import org.xbig.base.NativeObject;
import org.xbig.base.WithoutNativeObject;

/**
 * Allows receiving native OGRE RenderTarget- and RenderTargetViewportEvents in
 * Java. Creates a C++ Object which forwards the Events. Implementers should use
 * methods of {@link IRenderTargetListener}.
 * <p>
 * Must explicitly be deleted.
 * </p>
 * 
 */
public abstract class NativeRenderTargetListener extends NativeObject implements
		IRenderTargetListener {

	static {
		System.loadLibrary("ogre4j");
	}

	/**
	 * Constructor. Creates C++ object.
	 */
	public NativeRenderTargetListener() {
		long pInstance = __createNativeRenderTargetListener();
		setInstancePointer(pInstance, false);
	}

	private native long __createNativeRenderTargetListener();

	/**
	 * @param p
	 *            Address of native RenderTargetEvent.
	 */
	public void preRenderTargetUpdate(long p) {
		InstancePointer ptr = new InstancePointer(p);
		RenderTargetEvent evt = new RenderTargetEvent(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		evt.setInstancePointer(ptr, true);
		preRenderTargetUpdate(evt);
	}

	/**
	 * @param p
	 *            Address of native RenderTargetEvent.
	 */
	public void postRenderTargetUpdate(long p) {
		InstancePointer ptr = new InstancePointer(p);
		RenderTargetEvent evt = new RenderTargetEvent(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		evt.setInstancePointer(ptr, true);
		postRenderTargetUpdate(evt);
	}

	/**
	 * @param p
	 *            Address of native RenderTargetEvent.
	 */
	public void preViewportUpdate(long p) {
		InstancePointer ptr = new InstancePointer(p);
		RenderTargetViewportEvent evt = new RenderTargetViewportEvent(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		evt.setInstancePointer(ptr, true);
		preViewportUpdate(evt);
	}

	/**
	 * @param p
	 *            Address of native RenderTargetEvent.
	 */
	public void postViewportUpdate(long p) {
		InstancePointer ptr = new InstancePointer(p);
		RenderTargetViewportEvent evt = new RenderTargetViewportEvent(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		evt.setInstancePointer(ptr, true);
		postViewportUpdate(evt);
	}

	/**
	 * @param p
	 *            Address of native RenderTargetEvent.
	 */
	public void viewportAdded(long p) {
		InstancePointer ptr = new InstancePointer(p);
		RenderTargetViewportEvent evt = new RenderTargetViewportEvent(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		evt.setInstancePointer(ptr, true);
		viewportAdded(evt);
	}

	/**
	 * @param p
	 *            Address of native RenderTargetEvent.
	 */
	public void viewportRemoved(long p) {
		InstancePointer ptr = new InstancePointer(p);
		RenderTargetViewportEvent evt = new RenderTargetViewportEvent(
				WithoutNativeObject.I_WILL_DELETE_THIS_OBJECT);
		evt.setInstancePointer(ptr, true);
		viewportRemoved(evt);
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
			__deleteNativeRenderTargetListener(object.pointer);
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

	private final native void __deleteNativeRenderTargetListener(long _pointer_);
}
