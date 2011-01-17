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

package org.ogre4j.eclipse.ogreface.ogresys;

import java.util.EventObject;

public class FrameEvent extends EventObject {
    /**
     * 
     */
    private static final long serialVersionUID = -9180692619915623812L;

    private float timeSinceLastEvent;

    private float timeSinceLastFrame;

    /**
     * 
     * @param timeSinceLastEvent
     * @param timeSinceLastFrame
     */
    public FrameEvent(Object source, float timeSinceLastEvent, float timeSinceLastFrame) {
        super(source);
        this.timeSinceLastEvent = timeSinceLastEvent;
        this.timeSinceLastFrame = timeSinceLastFrame;
    }

    /**
     * 
     * @return float
     */
    public float getTimeSinceLastEvent() {
        return timeSinceLastEvent;
    }

    /**
     * 
     * @return float
     */
    public float getTimeSinceLastFrame() {
        return timeSinceLastFrame;
    }
}
