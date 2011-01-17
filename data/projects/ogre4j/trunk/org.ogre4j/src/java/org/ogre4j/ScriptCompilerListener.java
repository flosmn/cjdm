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
public class ScriptCompilerListener extends org.xbig.base.NativeObject implements org.ogre4j.IScriptCompilerListener {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public ScriptCompilerListener(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected ScriptCompilerListener(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public ScriptCompilerListener(org.xbig.base.WithoutNativeObject val) {
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



          /** **/
    public ScriptCompilerListener() {
         super( new org.xbig.base.InstancePointer(__createScriptCompilerListener()), false);
    }

    private native static long __createScriptCompilerListener();

    /** **/
    public void importFile(org.ogre4j.IConcreteNodeListPtr returnValue, org.ogre4j.IScriptCompiler compiler, String name) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_importFile__ScriptCompilerpStringR(this.object.pointer, compiler.getInstancePointer().pointer,  name)), false);
    }

    private native long _importFile__ScriptCompilerpStringR(long _pointer_, long compiler, String name);

    /** **/
    public void preConversion(org.ogre4j.IScriptCompiler compiler, org.ogre4j.IConcreteNodeListPtr nodes) {
        _preConversion__ScriptCompilerpConcreteNodeListPtrv(this.object.pointer, compiler.getInstancePointer().pointer,  nodes.getInstancePointer().pointer);
    }

    private native void _preConversion__ScriptCompilerpConcreteNodeListPtrv(long _pointer_, long compiler, long nodes);

    /** 
    **/
    public boolean postConversion(org.ogre4j.IScriptCompiler compiler, org.ogre4j.IAbstractNodeListPtr a2) {
         return _postConversion__ScriptCompilerpAbstractNodeListPtrR(this.object.pointer, compiler.getInstancePointer().pointer,  a2.getInstancePointer().pointer);
    }

    private native boolean _postConversion__ScriptCompilerpAbstractNodeListPtrR(long _pointer_, long compiler, long a2);

    /** **/
    public void handleError(org.ogre4j.IScriptCompiler compiler, long code, String file, int line, String msg) {
        _handleError__ScriptCompilerpuint32vStringRivStringR(this.object.pointer, compiler.getInstancePointer().pointer,  code,  file,  line,  msg);
    }

    private native void _handleError__ScriptCompilerpuint32vStringRivStringR(long _pointer_, long compiler, long code, String file, int line, String msg);

    /** 
    **/
    public boolean handleEvent(org.ogre4j.IScriptCompiler compiler, String name, org.std.Ivector< org.ogre4j.IAny > args, org.ogre4j.IAny retval) {
         return _handleEvent__ScriptCompilerpStringRstd_vector__Ogre_Any__rOgre_Anyp(this.object.pointer, compiler.getInstancePointer().pointer,  name,  args.getInstancePointer().pointer,  retval.getInstancePointer().pointer);
    }

    private native boolean _handleEvent__ScriptCompilerpStringRstd_vector__Ogre_Any__rOgre_Anyp(long _pointer_, long compiler, String name, long args, long retval);

    /** 
    **/
    public void createObject(org.ogre4j.IAny returnValue, org.ogre4j.IScriptCompiler compiler, String type, org.std.Ivector< org.ogre4j.IAny > args) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_createObject__ScriptCompilerpStringRstd_vector__Ogre_Any__r(this.object.pointer, compiler.getInstancePointer().pointer,  type,  args.getInstancePointer().pointer)), false);
    }

    private native long _createObject__ScriptCompilerpStringRstd_vector__Ogre_Any__r(long _pointer_, long compiler, String type, long args);

}
