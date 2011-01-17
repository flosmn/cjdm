/**
 *  This source file is generated by XBiG (The XSLT Bindings Generator)
 *  For the latest info, see http://sourceforge.net/projects/xbig/
 * 
 *  Copyright (c) 2004-2008 NetAllied Systems GmbH, Ravensburg. All rights reserved.
 *  Also see acknowledgements in Readme.html
 * 
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU Lesser General Public License as published by the Free Software
 *  Foundation; either version 2 of the License, or (at your option) any later
 *  version.
 * 
 *  This program is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 *  FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public License along with
 *  this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 *  Place - Suite 330, Boston, MA 02111-1307, USA, or go to
 *  http://www.gnu.org/copyleft/lesser.txt.
 * 
 *  Machine generated file
 */

        

package org.ogre4j;


import org.xbig.base.*;
public interface IHardwareBuffer extends INativeObject, org.ogre4j.IRenderSysAllocatedObject {

    /** 
    Lock the buffer for (potentially) reading / writing. **/
    public VoidPointer lock(int offset, int length, org.ogre4j.HardwareBuffer.LockOptions options);

    /** 
    Lock the entire buffer for (potentially) reading / writing. **/
    public VoidPointer lock(org.ogre4j.HardwareBuffer.LockOptions options);

    /** 
    Releases the lock on this buffer. **/
    public void unlock();

    /** 
    Reads data from the buffer and places it in the memory pointed to by pDest. **/
    public void readData(int offset, int length, VoidPointer pDest);

    /** 
    Writes data to the buffer from an area of system memory; note that you must ensure that your buffer is big enough. **/
    public void writeData(int offset, int length, VoidPointer pSource, boolean discardWholeBuffer);

    /** 
    Copy data from another buffer into this one. **/
    public void copyData(org.ogre4j.IHardwareBuffer srcBuffer, int srcOffset, int dstOffset, int length, boolean discardWholeBuffer);

    /** **/
    public void _updateFromShadow();

    /** **/
    public int getSizeInBytes();

    /** **/
    public org.ogre4j.HardwareBuffer.Usage getUsage();

    /** **/
    public boolean isSystemMemory();

    /** **/
    public boolean hasShadowBuffer();

    /** **/
    public boolean isLocked();

    /** **/
    public void suppressHardwareUpdate(boolean suppress);

}
