// LanguageAttribute.java
// $Id: LanguageAttribute.java,v 1.4 2002/06/09 11:27:48 ylafon Exp $
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.jigsaw.frames ;

import org.w3c.tools.resources.Attribute;
import org.w3c.tools.resources.StringAttribute;

public class LanguageAttribute extends StringAttribute {

    public LanguageAttribute(String name, String def, int flags) {
	super(name, def, flags) ;
	this.type = "java.lang.String".intern();
    }

    public LanguageAttribute() {
	super() ;
    }

}
