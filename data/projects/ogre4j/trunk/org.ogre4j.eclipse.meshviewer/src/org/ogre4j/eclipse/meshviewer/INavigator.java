package org.ogre4j.eclipse.meshviewer;

import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Canvas;
import org.ogre4j.ICamera;
import org.ogre4j.ISceneManager;

public interface INavigator extends KeyListener, MouseListener, MouseMoveListener {

    public void setCamera(ICamera camera);

    public void setSceneManager(ISceneManager sceneManager);

    public void setCanvas(Canvas canvas);

    public ICamera getCamera();

    public ISceneManager getSceneManager();

    public Canvas getCanvas();
}
