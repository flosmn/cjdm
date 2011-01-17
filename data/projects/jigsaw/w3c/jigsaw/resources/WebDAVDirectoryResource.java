// HttpDirectoryResource.java
// $Id: WebDAVDirectoryResource.java,v 1.1 2005/06/08 12:41:35 ylafon Exp $
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.jigsaw.resources ;

public class WebDAVDirectoryResource extends DirectoryResource {

  public void initialize(Object values[]) {
    super.initialize(values);
    try {
      registerFrameIfNone("org.w3c.jigsaw.webdav.DAVFrame",
			  "webdav-frame");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
