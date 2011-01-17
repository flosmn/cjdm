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
public interface IDataStream extends INativeObject, org.ogre4j.IGeneralAllocatedObject {

    /** **/
    public String getName();

    /** 
    Read the requisite number of bytes from the stream, stopping at the end of the file. **/
    public int read(VoidPointer buf, int count);

    /** 
    Get a single line from the stream. **/
    public int readLine(BytePointer buf, int maxCount, String delim);

    /** 
    Returns a String containing the next line of data, optionally trimmed for whitespace. **/
    public String getLine(boolean trimAfter);

    /** 
    Returns a String containing the entire stream. **/
    public String getAsString();

    /** 
    Skip a single line from the stream. **/
    public int skipLine(String delim);

    /** 
    Skip a defined number of bytes. This can also be a negative value, in which case the file pointer rewinds a defined number of bytes. **/
    public void skip(long count);

    /** 
    Repositions the read point to a specified byte. **/
    public void seek(int pos);

    /** 
    Returns the current byte offset from beginning **/
    public int tell();

    /** 
    Returns true if the stream has reached the end. **/
    public boolean eof();

    /** 
    Returns the total size of the data to be read from the stream, or 0 if this is indeterminate for this stream. **/
    public int size();

    /** 
    Close the stream; this makes further operations invalid. **/
    public void close();

}
