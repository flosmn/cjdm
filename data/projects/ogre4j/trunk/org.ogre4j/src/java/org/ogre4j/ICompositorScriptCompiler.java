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
public interface ICompositorScriptCompiler extends INativeObject, org.ogre4j.ICompiler2Pass {

public static interface ICompositorScriptContext extends INativeObject {

    /** **/
    public org.ogre4j.CompositorScriptCompiler.CompositorScriptSection getsection();

    /** **/
    public void setsection(org.ogre4j.CompositorScriptCompiler.CompositorScriptSection _jni_value_);

    /** **/
    public String getgroupName();

    /** **/
    public void setgroupName(String _jni_value_);

    /** **/
    public void getcompositor(org.ogre4j.ICompositorPtr returnValue);

    /** **/
    public void setcompositor(org.ogre4j.ICompositorPtr _jni_value_);

    /** **/
    public org.ogre4j.ICompositionTechnique gettechnique();

    /** **/
    public void settechnique(org.ogre4j.ICompositionTechnique _jni_value_);

    /** **/
    public org.ogre4j.ICompositionTargetPass gettarget();

    /** **/
    public void settarget(org.ogre4j.ICompositionTargetPass _jni_value_);

    /** **/
    public org.ogre4j.ICompositionPass getpass();

    /** **/
    public void setpass(org.ogre4j.ICompositionPass _jni_value_);

}
public interface ITokenActionMap extends INativeObject, org.std.Imap {

// this type is ignored
}
    /** 
    gets BNF Grammar for  script. **/
    public String getClientBNFGrammer();

    /** 
    get the name of the  script BNF grammar. **/
    public String getClientGrammerName();

    /** 
    Compile a compositor script from a data stream using a specific resource group name. **/
    public void parseScript(org.ogre4j.IDataStreamPtr stream, String groupName);

}
