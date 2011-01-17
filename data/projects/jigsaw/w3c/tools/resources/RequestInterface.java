// RequestInterface.java
// $Id: RequestInterface.java,v 1.2 2000/08/16 21:37:53 ylafon Exp $
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.tools.resources ;

public interface RequestInterface {

  /**
   * FIXME doc
   */
  public void setState(String name, String state);

  /**
   * Get the URL path of the target resource.
   */
  public String getURLPath();

  /**
   * Return true is the request is internal.
   * @return a boolean.
   */
  public boolean isInternal();

  /**
   * Get a "Bad request" reply.
   * @return a ReplyInterface instance.
   */
  public ReplyInterface makeBadRequestReply();

}


