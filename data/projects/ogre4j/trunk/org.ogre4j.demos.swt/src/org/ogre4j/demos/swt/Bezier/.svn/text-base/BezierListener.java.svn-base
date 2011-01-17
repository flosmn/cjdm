package org.ogre4j.demos.swt.Bezier;

import org.eclipse.swt.widgets.Canvas;
import org.ogre4j.ICamera;
import org.ogre4j.IPass;
import org.ogre4j.IRenderWindow;
import org.ogre4j.PatchMeshPtr;
import org.ogre4j.PolygonMode;
import org.ogre4j.demos.swt.exampleapp.ExampleFrameListener;
import org.ogre4j.demos.swt.exampleapp.FrameEvent;

public class BezierListener extends ExampleFrameListener {

	private float timeLapse = 0.0f;
	private float factor = 0.0f;
	private boolean wireframe = false;
	private PatchMeshPtr patch;
	private IPass patchPass;

	public BezierListener(IRenderWindow rw, ICamera cam, Canvas swtCanvas) {
		super(rw, cam, swtCanvas);
	}

	// give the Variables wich are global in the c demo to the Listener
	public void setPatch(PatchMeshPtr p) {
		patch = p;
	}

	public void setPatchPass(IPass pP) {
		patchPass = pP;
	}

	@Override
	public boolean frameStarted(FrameEvent evt) {
		if (!super.frameStarted(evt)) {
			return false;
		}

		timeLapse += evt.timeSinceLastFrame;

		// Progressively grow the patch
		if (timeLapse > 1.0f) {
			factor += 0.2;

			// TODO patchPass and patch are global in the original
			if (factor > 1.0f) {
				wireframe = !wireframe;
				patchPass.setPolygonMode(wireframe ? PolygonMode.PM_WIREFRAME
						: PolygonMode.PM_SOLID);
				factor = 0.0f;

			}

			patch.get().setSubdivision(factor);
			mDebugText = "Bezier subdivision factor: " + factor;
			timeLapse = 0.0f;

		}

		// Call default
		return true;
	}
}
