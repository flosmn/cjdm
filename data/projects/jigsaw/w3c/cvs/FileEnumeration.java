// FileEnumeration.java
// $Id: FileEnumeration.java,v 1.6 2007/02/09 13:45:14 ylafon Exp $  
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.cvs;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

class FileEnumeration implements Enumeration {
    CvsEntry    next = null;
    Enumeration cvsenum = null;

    private CvsEntry computeNextElement() {
	if ( cvsenum == null ) 
	    return (next = null);
	while ((next == null) && cvsenum.hasMoreElements()) {
	    CvsEntry entry = (CvsEntry) cvsenum.nextElement();
	    if ( ! entry.isdir )
		next = entry;
	}
	return next;
    }

    public synchronized boolean hasMoreElements() {
	return ((next != null) || ((next = computeNextElement()) != null));
    }

    public synchronized Object nextElement() {
	if ( next == null ) {
	    if ((next = computeNextElement()) == null)
		throw new NoSuchElementException("invalid enum");
	}
	CvsEntry item = next;
	next = null;
	return item.name;
    }

    FileEnumeration(Hashtable entries) {
	this.cvsenum = (entries != null) ? entries.elements() : null;
    }

}
