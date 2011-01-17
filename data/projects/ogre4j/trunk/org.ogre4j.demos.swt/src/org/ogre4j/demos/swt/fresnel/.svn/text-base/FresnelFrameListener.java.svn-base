package org.ogre4j.demos.swt.fresnel;

import org.eclipse.swt.widgets.Canvas;
import org.ogre4j.ICamera;
import org.ogre4j.IQuaternion;
import org.ogre4j.IRenderWindow;
import org.ogre4j.Math;
import org.ogre4j.Quaternion;
import org.ogre4j.Vector3;
import org.ogre4j.demos.swt.exampleapp.ExampleFrameListener;

public class FresnelFrameListener extends ExampleFrameListener {
	public FresnelFrameListener(IRenderWindow win, ICamera cam, Canvas swtCanvas) {
		super(win, cam, swtCanvas);
	}

	public boolean frameStarted(org.ogre4j.demos.swt.exampleapp.FrameEvent evt) {
		FresnelApplication.animTime += evt.timeSinceLastEvent;
		
		while (FresnelApplication.animTime > FresnelApplication.FISH_PATH_LENGTH) {
			FresnelApplication.animTime -= FresnelApplication.FISH_PATH_LENGTH;
		}

		for (int fish = 0; fish < FresnelApplication.NUM_FISH; ++fish) {
			Vector3 newPos = new Vector3();

			// Animate the fish
			FresnelApplication.fishAnimations[fish]
					.addTime(evt.timeSinceLastFrame * 2);
			
			FresnelApplication.fishSplines[fish].interpolate(newPos,
					FresnelApplication.animTime
							/ FresnelApplication.FISH_PATH_LENGTH);
			FresnelApplication.fishNodes[fish].setPosition(newPos);
			// Work out the direction
			Vector3 direction = new Vector3(
					FresnelApplication.fishLastPosition[fish].getx()
							- newPos.getx(),
					FresnelApplication.fishLastPosition[fish].gety()
							- newPos.gety(),
					FresnelApplication.fishLastPosition[fish].getz()
							- newPos.getz());
			direction.normalise();
			// Test for opposite vectors
			float d = 1.0f + Vector3.getUNIT_X().dotProduct(direction);
			if (Math.Abs(d) < 0.00001)

			{
				// Diametrically opposed vectors
				Quaternion orientation = new Quaternion(Vector3
						.getNEGATIVE_UNIT_X(), Vector3.getUNIT_Y(), Vector3
						.getNEGATIVE_UNIT_Z());
				FresnelApplication.fishNodes[fish].setOrientation(orientation);
				orientation.delete();
			} else {
				IQuaternion tmp = new Quaternion(Vector3.getUNIT_X());
				Vector3.getUNIT_X().getRotationTo(tmp, direction,
						Vector3.getUNIT_X());
				FresnelApplication.fishNodes[fish].setOrientation(tmp);
				tmp.delete();
			}
			FresnelApplication.fishLastPosition[fish].delete();
			FresnelApplication.fishLastPosition[fish] = newPos;
			direction.delete();
				
		}
		return true;
	}

}
