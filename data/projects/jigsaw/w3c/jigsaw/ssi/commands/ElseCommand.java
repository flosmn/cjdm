// DirectoryResource.java
// $Id: ElseCommand.java,v 1.4 2000/08/16 21:37:47 ylafon Exp $
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.jigsaw.ssi.commands;

import java.util.Dictionary;
import java.util.Hashtable;

import org.w3c.www.http.HTTP;

import org.w3c.jigsaw.http.Reply;
import org.w3c.jigsaw.http.Request;

import org.w3c.tools.resources.Resource;
import org.w3c.tools.resources.ResourceFrame;

import org.w3c.util.ArrayDictionary;

import org.w3c.jigsaw.ssi.SSIFrame;

/**
 * Implementation of the SSI <code>else</code> command.  
 * @author Benoit Mahe <bmahe@sophia.inria.fr>
 */

public class ElseCommand implements ControlCommand {
    private final static String  NAME  = "else";
    private final static boolean debug = true;

    private static final String keys[] = { "name" };

    protected static Hashtable elsestore = null;

    static {
	elsestore = new Hashtable(23);
    }

    protected static int getPosition(String name) 
	throws ControlCommandException    
    {
	Integer pos = (Integer)elsestore.get(name);
	if (pos == null)
	    throw new ControlCommandException(NAME,"Position unknown.");
	else return pos.intValue();
    }

    public void setPosition(SSIFrame ssiframe,
			    Request request,
			    CommandRegistry registry,
			    ArrayDictionary parameters,
			    Dictionary variables,
			    int position) 
    {
	Object values[] = parameters.getMany(keys);
	String name     = (String) values[0];
	if (name != null)
	    elsestore.put(ssiframe.getURLPath()+":"+name, 
			  new Integer(position));
    }

    public String getValue(Dictionary variables, String var, Request request) {
	return null;
    }

    public Reply execute(SSIFrame ssiframe
			 , Request request
			 , ArrayDictionary parameters
			 , Dictionary variables) 
    {
	return ssiframe.createCommandReply(request, HTTP.OK);
    }

    public int jumpTo(SSIFrame ssiframe,
		      Request request,
		      CommandRegistry registry,
		      ArrayDictionary parameters,
		      Dictionary variables)
	throws ControlCommandException
    {
	Object values[] = parameters.getMany(keys);
	String name     = (String) values[0];
	if (name != null)
	    return 
		(EndifCommand.getPosition(ssiframe.getResource().getURLPath()+
					  ":"+name)+1);
	throw new ControlCommandException(NAME,"name not initialized.");
    }

    public String getName() {
	return NAME;
    }

    /**
     * return true if reply can be cached.
     * @return a boolean.
     */
    public boolean acceptCaching() {
	return false;
    }

}


