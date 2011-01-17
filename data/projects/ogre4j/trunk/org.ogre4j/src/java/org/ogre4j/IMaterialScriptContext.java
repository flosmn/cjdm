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
public interface IMaterialScriptContext extends INativeObject {

    /** **/
    public org.ogre4j.MaterialScriptSection getsection();

    /** **/
    public void setsection(org.ogre4j.MaterialScriptSection _jni_value_);

    /** **/
    public String getgroupName();

    /** **/
    public void setgroupName(String _jni_value_);

    /** **/
    public void getmaterial(org.ogre4j.IMaterialPtr returnValue);

    /** **/
    public void setmaterial(org.ogre4j.IMaterialPtr _jni_value_);

    /** **/
    public org.ogre4j.ITechnique gettechnique();

    /** **/
    public void settechnique(org.ogre4j.ITechnique _jni_value_);

    /** **/
    public org.ogre4j.IPass getpass();

    /** **/
    public void setpass(org.ogre4j.IPass _jni_value_);

    /** **/
    public org.ogre4j.ITextureUnitState gettextureUnit();

    /** **/
    public void settextureUnit(org.ogre4j.ITextureUnitState _jni_value_);

    /** **/
    public void getprogram(org.ogre4j.IGpuProgramPtr returnValue);

    /** **/
    public void setprogram(org.ogre4j.IGpuProgramPtr _jni_value_);

    /** **/
    public boolean getisProgramShadowCaster();

    /** **/
    public void setisProgramShadowCaster(boolean _jni_value_);

    /** **/
    public boolean getisVertexProgramShadowReceiver();

    /** **/
    public void setisVertexProgramShadowReceiver(boolean _jni_value_);

    /** **/
    public boolean getisFragmentProgramShadowReceiver();

    /** **/
    public void setisFragmentProgramShadowReceiver(boolean _jni_value_);

    /** **/
    public void getprogramParams(org.ogre4j.IGpuProgramParametersSharedPtr returnValue);

    /** **/
    public void setprogramParams(org.ogre4j.IGpuProgramParametersSharedPtr _jni_value_);

    /** **/
    public int getnumAnimationParametrics();

    /** **/
    public void setnumAnimationParametrics(int _jni_value_);

    /** **/
    public org.ogre4j.IMaterialScriptProgramDefinition getprogramDef();

    /** **/
    public void setprogramDef(org.ogre4j.IMaterialScriptProgramDefinition _jni_value_);

    /** **/
    public int gettechLev();

    /** **/
    public void settechLev(int _jni_value_);

    /** **/
    public int getpassLev();

    /** **/
    public void setpassLev(int _jni_value_);

    /** **/
    public int getstateLev();

    /** **/
    public void setstateLev(int _jni_value_);

    /** **/
    public void getdefaultParamLines(org.ogre4j.IStringVector returnValue);

    /** **/
    public void setdefaultParamLines(org.ogre4j.IStringVector _jni_value_);

    /** **/
    public int getlineNo();

    /** **/
    public void setlineNo(int _jni_value_);

    /** **/
    public String getfilename();

    /** **/
    public void setfilename(String _jni_value_);

    /** **/
    public void gettextureAliases(org.ogre4j.IAliasTextureNamePairList returnValue);

    /** **/
    public void settextureAliases(org.ogre4j.IAliasTextureNamePairList _jni_value_);

}
