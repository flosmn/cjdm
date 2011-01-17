// MimeParserFactory.java
// $Id: MimeParserFactory.java,v 1.5 1998/01/22 14:30:32 bmahe Exp $
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.www.mime;

/**
 * This class is used by the MimeParser, to create new MIME message holders.
 * Each MIME parse instances is custmozied wit hits own factory, which it
 * will use to create MIME header holders.
 */

public interface MimeParserFactory {

    /**
     * Create a new header holder to hold the parser's result.
     * @param parser The parser that has something to parse.
     * @return A MimeParserHandler compliant object.
     */

    abstract public MimeHeaderHolder createHeaderHolder(MimeParser parser);

}
