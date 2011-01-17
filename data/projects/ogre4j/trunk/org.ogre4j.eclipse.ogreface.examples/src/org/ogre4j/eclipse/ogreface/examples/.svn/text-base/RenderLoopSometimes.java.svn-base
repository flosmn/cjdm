/**
 * This file is part of ogre4j
 *  (The JNI bindings of OGRE - Object-Oriented Graphics Rendering Engine).
 *  
 * Copyright (c) 2005-2007 netAllied GmbH. All rights reserved.
 * Also see acknowledgements in README
 * 
 * ogre4j is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 * 
 * ogre4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with ogre4j; see the file COPYING.  If not, write to
 * the Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */
package org.ogre4j.eclipse.ogreface.examples;

import java.util.Vector;

import org.ogre4j.eclipse.ogreface.ogresys.FrameEvent;
import org.ogre4j.eclipse.ogreface.ogresys.IFrameListener;
import org.ogre4j.eclipse.ogreface.ogresys.IRenderLoop;
import org.ogre4j.eclipse.ogreface.ogresys.OgreSystem;

public class RenderLoopSometimes implements IRenderLoop {

    private Vector<IFrameListener> frameListeners = new Vector<IFrameListener>();

    private boolean active = false;

    public void addFrameListener(IFrameListener listener) {
        frameListeners.add(listener);
    }

    public void redraw() {
        if (active) {
            fireFrameStarted();
            try {
                renderOneFrame();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            fireFrameEnded();
        }
    }

    public void removeFrameListener(IFrameListener listener) {
        frameListeners.remove(listener);
    }

    public void start() {
        active = true;
    }

    public void stop() {
        active = false;
    }

    private synchronized void renderOneFrame() throws Exception {
        fireFrameStarted();
        OgreSystem.getDefault().renderOneFrame();
        fireFrameEnded();
    }

    private void fireFrameStarted() {
        // TODO calc time
        float timeSinceLastEvent = (float) (0.0);
        float timeSinceLastFrame = timeSinceLastEvent;

        for (IFrameListener listener : frameListeners) {
            listener.frameStarted(new FrameEvent(this, timeSinceLastEvent, timeSinceLastFrame));
        }
    }

    private void fireFrameEnded() {
        // TODO calc time
        float timeSinceLastEvent = (float) (0.0);
        float timeSinceLastFrame = timeSinceLastEvent;

        for (IFrameListener listener : frameListeners) {
            listener.frameEnded(new FrameEvent(this, timeSinceLastEvent, timeSinceLastFrame));
        }
    }
}
