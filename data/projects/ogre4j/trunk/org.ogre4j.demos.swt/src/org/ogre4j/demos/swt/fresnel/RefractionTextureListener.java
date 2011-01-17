package org.ogre4j.demos.swt.fresnel;

import org.ogre4j.IRenderTargetEvent;
import org.ogre4j.IRenderTargetViewportEvent;
import org.ogre4j.nativelisteners.NativeRenderTargetListener;

public class RefractionTextureListener extends NativeRenderTargetListener {

	public void preRenderTargetUpdate(IRenderTargetEvent evt) {
		// Hide plane and objects above the water
		FresnelApplication.pPlaneEn.setVisible(false);
		for (int i = 0; i < FresnelApplication.aboveWaterEnts.size(); i++)
			FresnelApplication.aboveWaterEnts.elementAt(i).setVisible(false);

	}

	public void postRenderTargetUpdate(IRenderTargetEvent evt) {
		// Show plane and objects above the water
		FresnelApplication.pPlaneEn.setVisible(true);
		for (int z = 0; z < FresnelApplication.aboveWaterEnts.size(); z++)
			FresnelApplication.aboveWaterEnts.elementAt(z).setVisible(true);

	}

	public void postViewportUpdate(IRenderTargetViewportEvent evt) {
	}

	public void preViewportUpdate(IRenderTargetViewportEvent evt) {
	}

	public void viewportAdded(IRenderTargetViewportEvent evt) {
	}

	public void viewportRemoved(IRenderTargetViewportEvent evt) {
	}
}
