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
public class TextureManager extends org.xbig.base.NativeObject implements org.ogre4j.ITextureManager {
static { System.loadLibrary("ogre4j");}
 
        
	/**
	 * 
	 * This constructor is public for internal useage only!
	 * Do not use it!
	 * 
	 */
	public TextureManager(org.xbig.base.InstancePointer p) {
		super(p);
	}

	/**
	 * 
	 * Creates a Java wrapper object for an existing C++ object.
	 * If remote is set to 'true' this object cannot be deleted in Java.
	 * 
	 */
	protected TextureManager(org.xbig.base.InstancePointer p, boolean remote) {
		super(p, remote);
	}

    /**
     * Allows creation of Java objects without C++ objects.
     * 
     * @see org.xbig.base.WithoutNativeObject
     * @see org.xbig.base.INativeObject#disconnectFromNativeObject()
     */
	public TextureManager(org.xbig.base.WithoutNativeObject val) {
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



          /** 
    Create a new texture, or retrieve an existing one with the same name if it already exists. **/
    public void createOrRetrieve(org.ogre4j.IResourceManager.IResourceCreateOrRetrieveResult returnValue, String name, String group, boolean isManual, org.ogre4j.IManualResourceLoader loader, org.ogre4j.INameValuePairList createParams, org.ogre4j.TextureType texType, int numMipmaps, float gamma, boolean isAlpha, org.ogre4j.PixelFormat desiredFormat, boolean hwGammaCorrection) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_createOrRetrieve__StringRStringRbvManualResourceLoaderpNameValuePairListPTextureTypevivRealvbvPixelFormatvbv(this.object.pointer, name,  group,  isManual,  loader.getInstancePointer().pointer,  createParams.getInstancePointer().pointer,  texType.getValue(),  numMipmaps,  gamma,  isAlpha,  desiredFormat.getValue(),  hwGammaCorrection)), false);
    }

    private native long _createOrRetrieve__StringRStringRbvManualResourceLoaderpNameValuePairListPTextureTypevivRealvbvPixelFormatvbv(long _pointer_, String name, String group, boolean isManual, long loader, long createParams, int texType, int numMipmaps, float gamma, boolean isAlpha, int desiredFormat, boolean hwGammaCorrection);

    /** 
    Prepares to loads a texture from a file. **/
    public void prepare(org.ogre4j.ITexturePtr returnValue, String name, String group, org.ogre4j.TextureType texType, int numMipmaps, float gamma, boolean isAlpha, org.ogre4j.PixelFormat desiredFormat, boolean hwGammaCorrection) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_prepare__StringRStringRTextureTypevivRealvbvPixelFormatvbv(this.object.pointer, name,  group,  texType.getValue(),  numMipmaps,  gamma,  isAlpha,  desiredFormat.getValue(),  hwGammaCorrection)), false);
    }

    private native long _prepare__StringRStringRTextureTypevivRealvbvPixelFormatvbv(long _pointer_, String name, String group, int texType, int numMipmaps, float gamma, boolean isAlpha, int desiredFormat, boolean hwGammaCorrection);

    /** 
    Loads a texture from a file. **/
    public void load(org.ogre4j.ITexturePtr returnValue, String name, String group, org.ogre4j.TextureType texType, int numMipmaps, float gamma, boolean isAlpha, org.ogre4j.PixelFormat desiredFormat, boolean hwGammaCorrection) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_load__StringRStringRTextureTypevivRealvbvPixelFormatvbv(this.object.pointer, name,  group,  texType.getValue(),  numMipmaps,  gamma,  isAlpha,  desiredFormat.getValue(),  hwGammaCorrection)), false);
    }

    private native long _load__StringRStringRTextureTypevivRealvbvPixelFormatvbv(long _pointer_, String name, String group, int texType, int numMipmaps, float gamma, boolean isAlpha, int desiredFormat, boolean hwGammaCorrection);

    /** 
    Loads a texture from an  object. **/
    public void loadImage(org.ogre4j.ITexturePtr returnValue, String name, String group, org.ogre4j.IImage img, org.ogre4j.TextureType texType, int iNumMipmaps, float gamma, boolean isAlpha, org.ogre4j.PixelFormat desiredFormat, boolean hwGammaCorrection) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_loadImage__StringRStringRImageRTextureTypevivRealvbvPixelFormatvbv(this.object.pointer, name,  group,  img.getInstancePointer().pointer,  texType.getValue(),  iNumMipmaps,  gamma,  isAlpha,  desiredFormat.getValue(),  hwGammaCorrection)), false);
    }

    private native long _loadImage__StringRStringRImageRTextureTypevivRealvbvPixelFormatvbv(long _pointer_, String name, String group, long img, int texType, int iNumMipmaps, float gamma, boolean isAlpha, int desiredFormat, boolean hwGammaCorrection);

    /** 
    Loads a texture from a raw data stream. **/
    public void loadRawData(org.ogre4j.ITexturePtr returnValue, String name, String group, org.ogre4j.IDataStreamPtr stream, int uWidth, int uHeight, org.ogre4j.PixelFormat format, org.ogre4j.TextureType texType, int iNumMipmaps, float gamma, boolean hwGammaCorrection) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_loadRawData__StringRStringRDataStreamPtrrushortvushortvPixelFormatvTextureTypevivRealvbv(this.object.pointer, name,  group,  stream.getInstancePointer().pointer,  uWidth,  uHeight,  format.getValue(),  texType.getValue(),  iNumMipmaps,  gamma,  hwGammaCorrection)), false);
    }

    private native long _loadRawData__StringRStringRDataStreamPtrrushortvushortvPixelFormatvTextureTypevivRealvbv(long _pointer_, String name, String group, long stream, int uWidth, int uHeight, int format, int texType, int iNumMipmaps, float gamma, boolean hwGammaCorrection);

    /** 
    Create a manual texture with specified width, height and depth (not loaded from a file). **/
    public void createManual(org.ogre4j.ITexturePtr returnValue, String name, String group, org.ogre4j.TextureType texType, long width, long height, long depth, int num_mips, org.ogre4j.PixelFormat format, int usage, org.ogre4j.IManualResourceLoader loader, boolean hwGammaCorrection, long fsaa) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_createManual__StringRStringRTextureTypevuintvuintvuintvivPixelFormatvivManualResourceLoaderpbvuintv(this.object.pointer, name,  group,  texType.getValue(),  width,  height,  depth,  num_mips,  format.getValue(),  usage,  loader.getInstancePointer().pointer,  hwGammaCorrection,  fsaa)), false);
    }

    private native long _createManual__StringRStringRTextureTypevuintvuintvuintvivPixelFormatvivManualResourceLoaderpbvuintv(long _pointer_, String name, String group, int texType, long width, long height, long depth, int num_mips, int format, int usage, long loader, boolean hwGammaCorrection, long fsaa);

    /** 
    Create a manual texture with a depth of 1 (not loaded from a file). **/
    public void createManual(org.ogre4j.ITexturePtr returnValue, String name, String group, org.ogre4j.TextureType texType, long width, long height, int num_mips, org.ogre4j.PixelFormat format, int usage, org.ogre4j.IManualResourceLoader loader, boolean hwGammaCorrection, long fsaa) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_createManual__StringRStringRTextureTypevuintvuintvivPixelFormatvivManualResourceLoaderpbvuintv(this.object.pointer, name,  group,  texType.getValue(),  width,  height,  num_mips,  format.getValue(),  usage,  loader.getInstancePointer().pointer,  hwGammaCorrection,  fsaa)), false);
    }

    private native long _createManual__StringRStringRTextureTypevuintvuintvivPixelFormatvivManualResourceLoaderpbvuintv(long _pointer_, String name, String group, int texType, long width, long height, int num_mips, int format, int usage, long loader, boolean hwGammaCorrection, long fsaa);

    /** 
    Sets preferred bit depth for integer pixel format textures. **/
    public void setPreferredIntegerBitDepth(int bits, boolean reloadTextures) {
        _setPreferredIntegerBitDepth__ushortvbv(this.object.pointer, bits,  reloadTextures);
    }

    private native void _setPreferredIntegerBitDepth__ushortvbv(long _pointer_, int bits, boolean reloadTextures);

    /** 
    gets preferred bit depth for integer pixel format textures. **/
    public int getPreferredIntegerBitDepth() {
         return _getPreferredIntegerBitDepth_const(this.object.pointer);
    }

    private native int _getPreferredIntegerBitDepth_const(long _pointer_);

    /** 
    Sets preferred bit depth for float pixel format textures. **/
    public void setPreferredFloatBitDepth(int bits, boolean reloadTextures) {
        _setPreferredFloatBitDepth__ushortvbv(this.object.pointer, bits,  reloadTextures);
    }

    private native void _setPreferredFloatBitDepth__ushortvbv(long _pointer_, int bits, boolean reloadTextures);

    /** 
    gets preferred bit depth for float pixel format textures. **/
    public int getPreferredFloatBitDepth() {
         return _getPreferredFloatBitDepth_const(this.object.pointer);
    }

    private native int _getPreferredFloatBitDepth_const(long _pointer_);

    /** 
    Sets preferred bit depth for integer and float pixel format. **/
    public void setPreferredBitDepths(int integerBits, int floatBits, boolean reloadTextures) {
        _setPreferredBitDepths__ushortvushortvbv(this.object.pointer, integerBits,  floatBits,  reloadTextures);
    }

    private native void _setPreferredBitDepths__ushortvushortvbv(long _pointer_, int integerBits, int floatBits, boolean reloadTextures);

    /** 
    Returns whether this render system can natively support the precise texture format requested with the given usage options. **/
    public boolean isFormatSupported(org.ogre4j.TextureType ttype, org.ogre4j.PixelFormat format, int usage) {
         return _isFormatSupported__TextureTypevPixelFormatviv(this.object.pointer, ttype.getValue(),  format.getValue(),  usage);
    }

    private native boolean _isFormatSupported__TextureTypevPixelFormatviv(long _pointer_, int ttype, int format, int usage);

    /** 
    Returns whether this render system can support the texture format requested with the given usage options, or another format with no quality reduction. **/
    public boolean isEquivalentFormatSupported(org.ogre4j.TextureType ttype, org.ogre4j.PixelFormat format, int usage) {
         return _isEquivalentFormatSupported__TextureTypevPixelFormatviv(this.object.pointer, ttype.getValue(),  format.getValue(),  usage);
    }

    private native boolean _isEquivalentFormatSupported__TextureTypevPixelFormatviv(long _pointer_, int ttype, int format, int usage);

    /** 
    Gets the format which will be natively used for a requested format given the constraints of the current device. **/
    public org.ogre4j.PixelFormat getNativeFormat(org.ogre4j.TextureType ttype, org.ogre4j.PixelFormat format, int usage) {
         return org.ogre4j.PixelFormat.toEnum(_getNativeFormat__TextureTypevPixelFormatviv(this.object.pointer, ttype.getValue(),  format.getValue(),  usage));
    }

    private native int _getNativeFormat__TextureTypevPixelFormatviv(long _pointer_, int ttype, int format, int usage);

    /** 
    Returns whether this render system has hardware filtering supported for the texture format requested with the given usage options. **/
    public boolean isHardwareFilteringSupported(org.ogre4j.TextureType ttype, org.ogre4j.PixelFormat format, int usage, boolean preciseFormatOnly) {
         return _isHardwareFilteringSupported__TextureTypevPixelFormatvivbv(this.object.pointer, ttype.getValue(),  format.getValue(),  usage,  preciseFormatOnly);
    }

    private native boolean _isHardwareFilteringSupported__TextureTypevPixelFormatvivbv(long _pointer_, int ttype, int format, int usage, boolean preciseFormatOnly);

    /** 
    Sets the default number of mipmaps to be used for loaded textures, for when textures are loaded automatically (e.g. by  class) or when 'load' is called with the default parameters by the application. If set to MIP_UNLIMITED mipmaps will be generated until the lowest possible level, 1x1x1. **/
    public void setDefaultNumMipmaps(int num) {
        _setDefaultNumMipmaps__iv(this.object.pointer, num);
    }

    private native void _setDefaultNumMipmaps__iv(long _pointer_, int num);

    /** 
    Gets the default number of mipmaps to be used for loaded textures. **/
    public int getDefaultNumMipmaps() {
         return _getDefaultNumMipmaps(this.object.pointer);
    }

    private native int _getDefaultNumMipmaps(long _pointer_);

    /** 
    Override standard  retrieval. **/
    public static org.ogre4j.ITextureManager getSingleton() {
         return new org.ogre4j.TextureManager(new InstancePointer(_getSingleton()));
    }

    private native static long _getSingleton();

    /** 
    Override standard  retrieval. **/
    public static org.ogre4j.ITextureManager getSingletonPtr() {
         return new org.ogre4j.TextureManager(new InstancePointer(_getSingletonPtr()));
    }

    private native static long _getSingletonPtr();

    /** 
    Creates a new blank resource, but does not immediately load it. **/
    public void create(org.ogre4j.IResourcePtr returnValue, String name, String group, boolean isManual, org.ogre4j.IManualResourceLoader loader, org.ogre4j.INameValuePairList createParams) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_create__StringRStringRbvManualResourceLoaderpNameValuePairListP(this.object.pointer, name,  group,  isManual,  loader.getInstancePointer().pointer,  createParams.getInstancePointer().pointer)), false);
    }

    private native long _create__StringRStringRbvManualResourceLoaderpNameValuePairListP(long _pointer_, String name, String group, boolean isManual, long loader, long createParams);

    /** 
    Create a new resource, or retrieve an existing one with the same name if it already exists. **/
    public void createOrRetrieve(org.ogre4j.IResourceManager.IResourceCreateOrRetrieveResult returnValue, String name, String group, boolean isManual, org.ogre4j.IManualResourceLoader loader, org.ogre4j.INameValuePairList createParams) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_createOrRetrieve__StringRStringRbvManualResourceLoaderpNameValuePairListP(this.object.pointer, name,  group,  isManual,  loader.getInstancePointer().pointer,  createParams.getInstancePointer().pointer)), false);
    }

    private native long _createOrRetrieve__StringRStringRbvManualResourceLoaderpNameValuePairListP(long _pointer_, String name, String group, boolean isManual, long loader, long createParams);

    /** 
    Set a limit on the amount of memory this resource handler may use. **/
    public void setMemoryBudget(int bytes) {
        _setMemoryBudget__iv(this.object.pointer, bytes);
    }

    private native void _setMemoryBudget__iv(long _pointer_, int bytes);

    /** 
    Get the limit on the amount of memory this resource handler may use. **/
    public int getMemoryBudget() {
         return _getMemoryBudget_const(this.object.pointer);
    }

    private native int _getMemoryBudget_const(long _pointer_);

    /** 
    Gets the current memory usage, in bytes. **/
    public int getMemoryUsage() {
         return _getMemoryUsage_const(this.object.pointer);
    }

    private native int _getMemoryUsage_const(long _pointer_);

    /** 
    Unloads a single resource by name. **/
    public void unload(String name) {
        _unload__StringR(this.object.pointer, name);
    }

    private native void _unload__StringR(long _pointer_, String name);

    /** 
    Unloads a single resource by handle. **/
    public void unload(long handle) {
        _unload__ResourceHandlev(this.object.pointer, handle);
    }

    private native void _unload__ResourceHandlev(long _pointer_, long handle);

    /** 
    Unloads all resources. **/
    public void unloadAll(boolean reloadableOnly) {
        _unloadAll__bv(this.object.pointer, reloadableOnly);
    }

    private native void _unloadAll__bv(long _pointer_, boolean reloadableOnly);

    /** 
    Caused all currently loaded resources to be reloaded. **/
    public void reloadAll(boolean reloadableOnly) {
        _reloadAll__bv(this.object.pointer, reloadableOnly);
    }

    private native void _reloadAll__bv(long _pointer_, boolean reloadableOnly);

    /** 
    Unload all resources which are not referenced by any other object. **/
    public void unloadUnreferencedResources(boolean reloadableOnly) {
        _unloadUnreferencedResources__bv(this.object.pointer, reloadableOnly);
    }

    private native void _unloadUnreferencedResources__bv(long _pointer_, boolean reloadableOnly);

    /** 
    Caused all currently loaded but not referenced by any other object resources to be reloaded. **/
    public void reloadUnreferencedResources(boolean reloadableOnly) {
        _reloadUnreferencedResources__bv(this.object.pointer, reloadableOnly);
    }

    private native void _reloadUnreferencedResources__bv(long _pointer_, boolean reloadableOnly);

    /** 
    Remove a single resource. **/
    public void remove(org.ogre4j.IResourcePtr r) {
        _remove__ResourcePtrr(this.object.pointer, r.getInstancePointer().pointer);
    }

    private native void _remove__ResourcePtrr(long _pointer_, long r);

    /** 
    Remove a single resource by name. **/
    public void remove(String name) {
        _remove__StringR(this.object.pointer, name);
    }

    private native void _remove__StringR(long _pointer_, String name);

    /** 
    Remove a single resource by handle. **/
    public void remove(long handle) {
        _remove__ResourceHandlev(this.object.pointer, handle);
    }

    private native void _remove__ResourceHandlev(long _pointer_, long handle);

    /** 
    Removes all resources. **/
    public void removeAll() {
        _removeAll(this.object.pointer);
    }

    private native void _removeAll(long _pointer_);

    /** 
    Retrieves a pointer to a resource by name, or null if the resource does not exist. **/
    public void getByName(org.ogre4j.IResourcePtr returnValue, String name) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getByName__StringR(this.object.pointer, name)), false);
    }

    private native long _getByName__StringR(long _pointer_, String name);

    /** 
    Retrieves a pointer to a resource by handle, or null if the resource does not exist. **/
    public void getByHandle(org.ogre4j.IResourcePtr returnValue, long handle) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getByHandle__ResourceHandlev(this.object.pointer, handle)), false);
    }

    private native long _getByHandle__ResourceHandlev(long _pointer_, long handle);

    /** **/
    public boolean resourceExists(String name) {
         return _resourceExists__StringR(this.object.pointer, name);
    }

    private native boolean _resourceExists__StringR(long _pointer_, String name);

    /** **/
    public boolean resourceExists(long handle) {
         return _resourceExists__ResourceHandlev(this.object.pointer, handle);
    }

    private native boolean _resourceExists__ResourceHandlev(long _pointer_, long handle);

    /** 
    Notify this manager that a resource which it manages has been 'touched', i.e. used. **/
    public void _notifyResourceTouched(org.ogre4j.IResource res) {
        __notifyResourceTouched__Resourcep(this.object.pointer, res.getInstancePointer().pointer);
    }

    private native void __notifyResourceTouched__Resourcep(long _pointer_, long res);

    /** 
    Notify this manager that a resource which it manages has been loaded. **/
    public void _notifyResourceLoaded(org.ogre4j.IResource res) {
        __notifyResourceLoaded__Resourcep(this.object.pointer, res.getInstancePointer().pointer);
    }

    private native void __notifyResourceLoaded__Resourcep(long _pointer_, long res);

    /** 
    Notify this manager that a resource which it manages has been unloaded. **/
    public void _notifyResourceUnloaded(org.ogre4j.IResource res) {
        __notifyResourceUnloaded__Resourcep(this.object.pointer, res.getInstancePointer().pointer);
    }

    private native void __notifyResourceUnloaded__Resourcep(long _pointer_, long res);

    /** 
    Generic prepare method, used to create a  specific to this  without using one of the specialised 'prepare' methods (containing per-Resource-type parameters). **/
    public void prepare(org.ogre4j.IResourcePtr returnValue, String name, String group, boolean isManual, org.ogre4j.IManualResourceLoader loader, org.ogre4j.INameValuePairList loadParams) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_prepare__StringRStringRbvManualResourceLoaderpNameValuePairListP(this.object.pointer, name,  group,  isManual,  loader.getInstancePointer().pointer,  loadParams.getInstancePointer().pointer)), false);
    }

    private native long _prepare__StringRStringRbvManualResourceLoaderpNameValuePairListP(long _pointer_, String name, String group, boolean isManual, long loader, long loadParams);

    /** 
    Generic load method, used to create a  specific to this  without using one of the specialised 'load' methods (containing per-Resource-type parameters). **/
    public void load(org.ogre4j.IResourcePtr returnValue, String name, String group, boolean isManual, org.ogre4j.IManualResourceLoader loader, org.ogre4j.INameValuePairList loadParams) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_load__StringRStringRbvManualResourceLoaderpNameValuePairListP(this.object.pointer, name,  group,  isManual,  loader.getInstancePointer().pointer,  loadParams.getInstancePointer().pointer)), false);
    }

    private native long _load__StringRStringRbvManualResourceLoaderpNameValuePairListP(long _pointer_, String name, String group, boolean isManual, long loader, long loadParams);

    /** 
    Gets the file patterns which should be used to find scripts for this . **/
    public org.ogre4j.IStringVector getScriptPatterns() {
         return new org.ogre4j.StringVector(new InstancePointer(_getScriptPatterns_const(this.object.pointer)));
    }

    private native long _getScriptPatterns_const(long _pointer_);

    /** 
    Parse the definition of a set of resources from a script file. **/
    public void parseScript(org.ogre4j.IDataStreamPtr stream, String groupName) {
        _parseScript__DataStreamPtrrStringR(this.object.pointer, stream.getInstancePointer().pointer,  groupName);
    }

    private native void _parseScript__DataStreamPtrrStringR(long _pointer_, long stream, String groupName);

    /** 
    Gets the relative loading order of resources of this type. **/
    public float getLoadingOrder() {
         return _getLoadingOrder_const(this.object.pointer);
    }

    private native float _getLoadingOrder_const(long _pointer_);

    /** 
    Gets a string identifying the type of resource this manager handles. **/
    public String getResourceType() {
         return _getResourceType_const(this.object.pointer);
    }

    private native String _getResourceType_const(long _pointer_);

    /** 
    Sets whether this manager and its resources habitually produce log output **/
    public void setVerbose(boolean v) {
        _setVerbose__bv(this.object.pointer, v);
    }

    private native void _setVerbose__bv(long _pointer_, boolean v);

    /** 
    Gets whether this manager and its resources habitually produce log output **/
    public boolean getVerbose() {
         return _getVerbose(this.object.pointer);
    }

    private native boolean _getVerbose(long _pointer_);

    /** 
    Returns an iterator over all resources in this manager. **/
    public void getResourceIterator(org.ogre4j.IResourceManager.IResourceMapIterator returnValue) {
          returnValue.delete();
  returnValue.setInstancePointer(new InstancePointer(_getResourceIterator(this.object.pointer)), false);
    }

    private native long _getResourceIterator(long _pointer_);

}
