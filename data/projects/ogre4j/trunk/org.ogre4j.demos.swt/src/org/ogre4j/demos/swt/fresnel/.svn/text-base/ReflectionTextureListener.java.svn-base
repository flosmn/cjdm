package org.ogre4j.demos.swt.fresnel;

import org.ogre4j.IRenderTargetEvent;
import org.ogre4j.IRenderTargetViewportEvent;
import org.ogre4j.nativelisteners.NativeRenderTargetListener;

public class ReflectionTextureListener extends NativeRenderTargetListener {

	public void preRenderTargetUpdate(IRenderTargetEvent evt) {
		// Hide plane and objects below the water
		FresnelApplication.pPlaneEn.setVisible(false);
		for (int i = 0; i < FresnelApplication.belowWaterEnts.size(); i++)
			FresnelApplication.belowWaterEnts.elementAt(i).setVisible(false);

		FresnelApplication.theCam
				.enableReflection(FresnelApplication.reflectionPlane);

	}

	public void postRenderTargetUpdate(IRenderTargetEvent evt) {
		// Show plane and objects below the water
		FresnelApplication.pPlaneEn.setVisible(true);

		for (int i = 0; i < FresnelApplication.belowWaterEnts.size(); i++)
			FresnelApplication.belowWaterEnts.elementAt(i).setVisible(true);

		FresnelApplication.theCam.disableReflection();
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
