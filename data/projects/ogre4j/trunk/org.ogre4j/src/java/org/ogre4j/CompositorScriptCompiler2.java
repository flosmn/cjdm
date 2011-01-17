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


import org.std.Ivector;
import org.xbig.base.*;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
public class CompositorScriptCompiler2 extends org.xbig.base.NativeObject implements org.ogre4j.ICompositorScriptCompiler2 {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public CompositorScriptCompiler2(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected CompositorScriptCompiler2(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public CompositorScriptCompiler2(org.xbig.base.WithoutNativeObject val) {
		super(val);
	}

	public void delete() {
		if (this.remote) {
	       throw new RuntimeException("can't dispose object created by native library");
	    }

		if(!this.deleted) {
		    __delete(object.pointer);
		    this.deleted = true;
		   	this.object.pointer = 0;
		}
	}

	public void finalize() {
		if(!this.remote && !this.deleted) {
			delete();
		}
	}
	
			
	private final native void __delete(long _pointer_);

	public boolean _compile(IAbstractNodeListPtr nodes, String group) {
		throw new NotImplementedException();
	}

	public void _fireCreateObject(IAny returnValue, String type,
			Ivector<IAny> args) {
		throw new NotImplementedException();
	}

	public boolean _fireEvent(String name, Ivector<IAny> args, IAny retval) {
		throw new NotImplementedException();
	}

	public void addError(long code, String file, int line, String msg) {
		throw new NotImplementedException();
	}

	public void addNameExclusion(String type) {
		throw new NotImplementedException();
	}

	public boolean compile(String str, String source, String group) {
		throw new NotImplementedException();
	}

	public boolean compile(IConcreteNodeListPtr nodes, String group) {
		throw new NotImplementedException();
	}

	public IScriptCompilerListener getListener() {
		throw new NotImplementedException();
	}

	public String getResourceGroup() {
		throw new NotImplementedException();
	}

	public void removeNameExclusion(String type) {
		throw new NotImplementedException();
	}

	public void setListener(IScriptCompilerListener listener) {
		throw new NotImplementedException();
	}	



      }
