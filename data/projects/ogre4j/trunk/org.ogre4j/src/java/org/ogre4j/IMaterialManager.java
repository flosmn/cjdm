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
public interface IMaterialManager extends INativeObject, org.ogre4j.IResourceManager, org.ogre4j.ISingleton< org.ogre4j.IMaterialManager > {

public static interface IListener extends INativeObject {

    /** 
    Called if a technique for a given scheme is not found within a material, allows the application to specify a  instance manually. **/
    public org.ogre4j.ITechnique handleSchemeNotFound(int schemeIndex, String schemeName, org.ogre4j.IMaterial originalMaterial, int lodIndex, org.ogre4j.IRenderable rend);

}
public interface ISchemeMap extends INativeObject, org.std.Imap< String, Integer > {

    /** **/
    public void clear();

    /** **/
    public int count(String key);

    /** **/
    public boolean empty();

    /** **/
    public int erase(String key);

    /** **/
    public int max_size();

    /** **/
    public int size();

    /** **/
    public IntegerPointer get(String key);

    /** **/
    public void insert(String key, IntegerPointer value);

}
public interface IListenerList extends INativeObject, org.std.Ilist< org.ogre4j.IMaterialManager.IListener > {

    /** **/
    public void assign(int num, org.ogre4j.IMaterialManager.IListener val);

    /** **/
    public org.ogre4j.IMaterialManager.IListener back();

    /** **/
    public void clear();

    /** **/
    public boolean empty();

    /** **/
    public org.ogre4j.IMaterialManager.IListener front();

    /** **/
    public int max_size();

    /** **/
    public void pop_back();

    /** **/
    public void pop_front();

    /** **/
    public void push_back(org.ogre4j.IMaterialManager.IListener val);

    /** **/
    public void push_front(org.ogre4j.IMaterialManager.IListener val);

    /** **/
    public void remove(org.ogre4j.IMaterialManager.IListener val);

    /** **/
    public void reverse();

    /** **/
    public int size();

    /** **/
    public void unique();

}
    /** 
    Initialises the material manager, which also triggers it to parse all available .program and .material scripts. **/
    public void initialise();

    /** 
    **/
    public void parseScript(org.ogre4j.IDataStreamPtr stream, String groupName);

    /** 
    Sets the default texture filtering to be used for loaded textures, for when textures are loaded automatically (e.g. by  class) or when 'load' is called with the default parameters by the application. **/
    public void setDefaultTextureFiltering(org.ogre4j.TextureFilterOptions fo);

    /** 
    Sets the default texture filtering to be used for loaded textures, for when textures are loaded automatically (e.g. by  class) or when 'load' is called with the default parameters by the application. **/
    public void setDefaultTextureFiltering(org.ogre4j.FilterType ftype, org.ogre4j.FilterOptions opts);

    /** 
    Sets the default texture filtering to be used for loaded textures, for when textures are loaded automatically (e.g. by  class) or when 'load' is called with the default parameters by the application. **/
    public void setDefaultTextureFiltering(org.ogre4j.FilterOptions minFilter, org.ogre4j.FilterOptions magFilter, org.ogre4j.FilterOptions mipFilter);

    /** **/
    public org.ogre4j.FilterOptions getDefaultTextureFiltering(org.ogre4j.FilterType ftype);

    /** 
    Sets the default anisotropy level to be used for loaded textures, for when textures are loaded automatically (e.g. by  class) or when 'load' is called with the default parameters by the application. **/
    public void setDefaultAnisotropy(long maxAniso);

    /** **/
    public long getDefaultAnisotropy();

    /** 
    Returns a pointer to the default  settings. **/
    public void getDefaultSettings(org.ogre4j.IMaterialPtr returnValue);

    /** 
    Internal method - returns index for a given material scheme name. **/
    public int _getSchemeIndex(String name);

    /** 
    Internal method - returns name for a given material scheme index. **/
    public String _getSchemeName(int index);

    /** 
    Internal method - returns the active scheme index. **/
    public int _getActiveSchemeIndex();

    /** 
    Returns the name of the active material scheme. **/
    public String getActiveScheme();

    /** 
    Sets the name of the active material scheme. **/
    public void setActiveScheme(String schemeName);

    /** 
    Add a listener to handle material events. **/
    public void addListener(org.ogre4j.IMaterialManager.IListener l);

    /** 
    Remove a listener handling material events. **/
    public void removeListener(org.ogre4j.IMaterialManager.IListener l);

    /** **/
    public org.ogre4j.ITechnique _arbitrateMissingTechniqueForActiveScheme(org.ogre4j.IMaterial mat, int lodIndex, org.ogre4j.IRenderable rend);

}
