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
public interface IScriptCompilerListener extends INativeObject {

    /** **/
    public void importFile(org.ogre4j.IConcreteNodeListPtr returnValue, org.ogre4j.IScriptCompiler compiler, String name);

    /** **/
    public void preConversion(org.ogre4j.IScriptCompiler compiler, org.ogre4j.IConcreteNodeListPtr nodes);

    /** 
    **/
    public boolean postConversion(org.ogre4j.IScriptCompiler compiler, org.ogre4j.IAbstractNodeListPtr a2);

    /** **/
    public void handleError(org.ogre4j.IScriptCompiler compiler, long code, String file, int line, String msg);

    /** 
    **/
    public boolean handleEvent(org.ogre4j.IScriptCompiler compiler, String name, org.std.Ivector< org.ogre4j.IAny > args, org.ogre4j.IAny retval);

    /** 
    **/
    public void createObject(org.ogre4j.IAny returnValue, org.ogre4j.IScriptCompiler compiler, String type, org.std.Ivector< org.ogre4j.IAny > args);

}
