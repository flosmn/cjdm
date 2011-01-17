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
public interface ICompositorInstance extends INativeObject, org.ogre4j.IResourceAllocatedObject {

public static interface IListener extends INativeObject {

    /** 
    Notification of when a render target operation involving a material (like rendering a quad) is compiled, so that miscellaneous parameters that are different per  instance can be set up. **/
    public void notifyMaterialSetup(long pass_id, org.ogre4j.IMaterialPtr mat);

    /** 
    Notification before a render target operation involving a material (like rendering a quad), so that material parameters can be varied. **/
    public void notifyMaterialRender(long pass_id, org.ogre4j.IMaterialPtr mat);

}
public static interface IRenderSystemOperation extends INativeObject, org.ogre4j.IResourceAllocatedObject {

    /** **/
    public void execute(org.ogre4j.ISceneManager sm, org.ogre4j.IRenderSystem rs);

}
public static interface ITargetOperation extends INativeObject {

public interface IRenderQueueBitSet extends INativeObject, org.std.Ibitset {

// this type is ignored
}
    /** **/
    public org.ogre4j.IRenderTarget gettarget();

    /** **/
    public void settarget(org.ogre4j.IRenderTarget _jni_value_);

    /** **/
    public int getcurrentQueueGroupID();

    /** **/
    public void setcurrentQueueGroupID(int _jni_value_);

    /** **/
    public void getrenderSystemOperations(org.ogre4j.ICompositorInstance.IRenderSystemOpPairs returnValue);

    /** **/
    public void setrenderSystemOperations(org.ogre4j.ICompositorInstance.IRenderSystemOpPairs _jni_value_);

    /** **/
    public long getvisibilityMask();

    /** **/
    public void setvisibilityMask(long _jni_value_);

    /** **/
    public float getlodBias();

    /** **/
    public void setlodBias(float _jni_value_);

    /** **/
    public void getrenderQueues(org.ogre4j.ICompositorInstance.ITargetOperation.IRenderQueueBitSet returnValue);

    /** **/
    public void setrenderQueues(org.ogre4j.ICompositorInstance.ITargetOperation.IRenderQueueBitSet _jni_value_);

    /** **/
    public boolean getonlyInitial();

    /** **/
    public void setonlyInitial(boolean _jni_value_);

    /** **/
    public boolean gethasBeenRendered();

    /** **/
    public void sethasBeenRendered(boolean _jni_value_);

    /** **/
    public boolean getfindVisibleObjects();

    /** **/
    public void setfindVisibleObjects(boolean _jni_value_);

    /** **/
    public String getmaterialScheme();

    /** **/
    public void setmaterialScheme(String _jni_value_);

    /** **/
    public boolean getshadowsEnabled();

    /** **/
    public void setshadowsEnabled(boolean _jni_value_);

}
public interface IQuadMaterialMap extends INativeObject, org.std.Imap< Integer, org.ogre4j.IMaterialPtr > {

    /** **/
    public void clear();

    /** **/
    public int count(int key);

    /** **/
    public boolean empty();

    /** **/
    public int erase(int key);

    /** **/
    public int max_size();

    /** **/
    public int size();

    /** **/
    public org.ogre4j.IMaterialPtr get(int key);

    /** **/
    public void insert(int key, org.ogre4j.IMaterialPtr value);

}
public interface IRenderSystemOpPair extends INativeObject, org.std.Ipair< Integer, org.ogre4j.ICompositorInstance.IRenderSystemOperation > {

    /** **/
    public int getfirst();

    /** **/
    public void setfirst(int _jni_value_);

    /** **/
    public org.ogre4j.ICompositorInstance.IRenderSystemOperation getsecond();

    /** **/
    public void setsecond(org.ogre4j.ICompositorInstance.IRenderSystemOperation _jni_value_);

}
public interface IRenderSystemOpPairs extends INativeObject, org.std.Ivector< org.ogre4j.ICompositorInstance.IRenderSystemOpPair > {

    /** **/
    public void assign(int num, org.ogre4j.ICompositorInstance.IRenderSystemOpPair val);

    /** **/
    public org.ogre4j.ICompositorInstance.IRenderSystemOpPair at(int loc);

    /** **/
    public org.ogre4j.ICompositorInstance.IRenderSystemOpPair back();

    /** **/
    public int capacity();

    /** **/
    public void clear();

    /** **/
    public boolean empty();

    /** **/
    public org.ogre4j.ICompositorInstance.IRenderSystemOpPair front();

    /** **/
    public int max_size();

    /** **/
    public void pop_back();

    /** **/
    public void push_back(org.ogre4j.ICompositorInstance.IRenderSystemOpPair val);

    /** **/
    public void reserve(int size);

    /** **/
    public int size();

}
public interface ICompiledState extends INativeObject, org.std.Ivector< org.ogre4j.ICompositorInstance.ITargetOperation > {

    /** **/
    public void assign(int num, org.ogre4j.ICompositorInstance.ITargetOperation val);

    /** **/
    public org.ogre4j.ICompositorInstance.ITargetOperation at(int loc);

    /** **/
    public org.ogre4j.ICompositorInstance.ITargetOperation back();

    /** **/
    public int capacity();

    /** **/
    public void clear();

    /** **/
    public boolean empty();

    /** **/
    public org.ogre4j.ICompositorInstance.ITargetOperation front();

    /** **/
    public int max_size();

    /** **/
    public void pop_back();

    /** **/
    public void push_back(org.ogre4j.ICompositorInstance.ITargetOperation val);

    /** **/
    public void reserve(int size);

    /** **/
    public int size();

}
public interface ILocalTextureMap extends INativeObject, org.std.Imap< String, org.ogre4j.ITexturePtr > {

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
    public org.ogre4j.ITexturePtr get(String key);

    /** **/
    public void insert(String key, org.ogre4j.ITexturePtr value);

}
public interface ILocalMRTMap extends INativeObject, org.std.Imap< String, org.ogre4j.IMultiRenderTarget > {

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
    public org.ogre4j.IMultiRenderTarget get(String key);

    /** **/
    public void insert(String key, org.ogre4j.IMultiRenderTarget value);

}
public interface IListeners extends INativeObject, org.std.Ivector< org.ogre4j.ICompositorInstance.IListener > {

    /** **/
    public void assign(int num, org.ogre4j.ICompositorInstance.IListener val);

    /** **/
    public org.ogre4j.ICompositorInstance.IListener at(int loc);

    /** **/
    public org.ogre4j.ICompositorInstance.IListener back();

    /** **/
    public int capacity();

    /** **/
    public void clear();

    /** **/
    public boolean empty();

    /** **/
    public org.ogre4j.ICompositorInstance.IListener front();

    /** **/
    public int max_size();

    /** **/
    public void pop_back();

    /** **/
    public void push_back(org.ogre4j.ICompositorInstance.IListener val);

    /** **/
    public void reserve(int size);

    /** **/
    public int size();

}
    /** 
    Set enabled flag. The compositor instance will only render if it is enabled, otherwise it is pass-through. **/
    public void setEnabled(boolean value);

    /** 
    Get enabled flag. **/
    public boolean getEnabled();

    /** 
    Get the instance name for a local texture. **/
    public String getTextureInstanceName(String name, int mrtIndex);

    /** 
    Get the render target for a given render texture name. **/
    public org.ogre4j.IRenderTarget getRenderTarget(String name);

    /** 
    Recursively collect target states (except for final ). **/
    public void _compileTargetOperations(org.ogre4j.ICompositorInstance.ICompiledState compiledState);

    /** 
    Compile the final (output) operation. This is done separately because this is combined with the input in chained filters. **/
    public void _compileOutputOperation(org.ogre4j.ICompositorInstance.ITargetOperation finalState);

    /** 
    Get  of which this is an instance **/
    public org.ogre4j.ICompositor getCompositor();

    /** 
    Get  used by this instance **/
    public org.ogre4j.ICompositionTechnique getTechnique();

    /** 
    Get Chain that this instance is part of **/
    public org.ogre4j.ICompositorChain getChain();

    /** 
    Add a listener. Listeners provide an interface to "listen in" to to render system operations executed by this  so that materials can be programmatically set up. **/
    public void addListener(org.ogre4j.ICompositorInstance.IListener l);

    /** 
    Remove a listener. **/
    public void removeListener(org.ogre4j.ICompositorInstance.IListener l);

    /** 
    Notify listeners of a material compilation. **/
    public void _fireNotifyMaterialSetup(long pass_id, org.ogre4j.IMaterialPtr mat);

    /** 
    Notify listeners of a material render. **/
    public void _fireNotifyMaterialRender(long pass_id, org.ogre4j.IMaterialPtr mat);

}
