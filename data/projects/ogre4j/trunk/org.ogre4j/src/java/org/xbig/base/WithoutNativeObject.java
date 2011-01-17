/* This source file is part of ogre4j
 *     (The JNI bindings for OGRE)
 * For the latest info, see http://www.ogre4j.org/
 * 
 * Copyright (c) 2005 netAllied GmbH, Tettnang
 * Also see acknowledgements in Readme.html
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA, or go to
 * http://www.gnu.org/copyleft/lesser.txt.
 */
package org.xbig.base;

/**
 * Used as parameter for special constructors of NativeObjects.
 * These constructors allow creation of Java objects without creating
 * a C++ object. This is needed when a return value must be passed 
 * as parameter. There are two cases where this happens:
 * <ul>
 * <li>objects returned by value</li>
 * <li>templates as return types</li>
 * </ul>
 * <p>
 * In the first case the returned C++ object is only valid inside the JNI function.
 * To make it available in Java it must be copied to the heap and thus deleted by 
 * the Java developer. To make sure a Java developer knows about that, he has to pass 
 * a Java object as parameter which must be deleted afterwards.
 * That object does not need to correspond to a C++ object because it will after the
 * method returns. To create the parameter object without a C++ object the constructor
 * using this enum as parameter can be used. The value <code>I_WILL_DELETE_THIS_OBJECT</code> 
 * is intended to be used in this case.
 * </p>
 * <p>
 * The second case is described in {@link INativeObject}. The value 
 * <code>I_WILL_DISCONNECT_THIS_OBJECT</code> should be used then.
 * </p>
 * <p>
 * If you think you have the second case make sure that that template is not
 * returned by value! If it is returned by value you have the first case!
 * </p>
 * 
 * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
 */
public enum WithoutNativeObject {
    I_WILL_DELETE_THIS_OBJECT,
    I_WILL_DISCONNECT_THIS_OBJECT
}
