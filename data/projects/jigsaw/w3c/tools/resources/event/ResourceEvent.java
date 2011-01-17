// ResourceEvent.java
// $Id: ResourceEvent.java,v 1.1 1998/01/22 12:59:52 bmahe Exp $  
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.tools.resources.event;

import java.util.EventObject;

public class ResourceEvent extends EventObject {

  protected int id = -1;

  public int getID() {
    return id;
  }

  public ResourceEvent (Object source, int id) {
    super(source);
    this.id = id;
  }

}
