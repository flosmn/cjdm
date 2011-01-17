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
 *
 * @see NativeObject
 */
public interface INativeObject {

	/**
	 * Needed for Interfaces which are needed for multiple inheritance
	 * 
	 * @return the object
	 */
	InstancePointer getInstancePointer();

	/**
	 * Deletes the corresponding C++ object.
	 * <p>
	 * Subclasses must implement this but if the native instance was created by
	 * the underlying library the native call to dispose must <b>not</b>
	 * happen!
	 * </p>
	 * 
	 * @see #remote
	 */
	void delete();

	/**
	 * Sets the instance pointer for the native object that is managed by this
	 * java object.
	 * <p>
	 * <b>Note:</b> This is an internal method. Do not use it to change the
	 * instance pointer!
	 * </p>
	 * 
	 * @param pInstance
	 *            The instance pointer to set.
	 * @param argRemote
	 *            If the native object is created by the underlaying library.
	 * @throws NullPointerException if the instance pointer is null.          
	 */
	void setInstancePointer(InstancePointer pInstance, boolean argRemote);

	/**
	 * Convenience method to set the instance pointer. Sets the createdByLibrary
	 * flag to true.
	 * <p>
	 * <b>Note:</b> This is an internal method. Do not use it to change the
	 * instance pointer!
	 * </p>
	 * 
	 * @see #setInstancePointer(long, boolean)
	 */
	void setInstancePointer(long pInstance);

	/**
	 * Convenience method to set the instance pointer. Creates the
	 * InstancePointer object.
	 * <p>
	 * <b>Note:</b> This is an internal method. Do not use it to change the
	 * instance pointer!
	 * </p>
	 * 
	 * @see #setInstancePointer(InstancePointer, boolean)
	 */
	void setInstancePointer(long pInstance, boolean createdByLibray);

    /**
     * Separates this Java object from it's corresponding C++ object.<br/>
     * Allows deletion of this Java object without deleting the C++ object.
     * <p>
     * Needed for templates as return values.<br/>
     * Consider following situation in the original library:
     * <pre>
     *  template &lt;class T&gt; class A;
     *  class B;
     *  class C {
     *  public:
     *      A&lt;B&gt;* a();
     *  };
     * </pre>
     * The method <code>C::a()</code> must return a Java object. But there is no Java
     * class we could instantiate because only Java interfaces can be generated for 
     * templates. So this return value must be passed as parameter which has the generated 
     * interface as type (with default configuration):<br/>
     * <code>public void a(org.xbig.IA&lt; org.xbig.IB &gt; returnValue)</code>.<br/>
     * This way you have to decide which Java class shall be instantiated and do so by your
     * own. Possible classes are typedefs for this template which use same type parameters
     * or you could implement an own subclass of NativeObject. 
     * But as this object is returned as a pointer, it is not allowed to be deleted in Java.
     * Furthermore a method could return a pointer or reference to itself which would result
     * in one C++ object corresponding to two Java objects.
     * </p>
     * <p>
     * With this method an unneeded Java object can be deleted without deleting a still needed
     * C++ object. Do not forget to set the unneeded Java reference to <code>null</code>!
     * </p>
     * 
     * @see org.xbig.base.WithoutNativeObject
     */
    void disconnectFromNativeObject();
}
