/*
 *  SExpr.java
 *
 *  Copyright 1997 Massachusetts Institute of Technology.
 *  All Rights Reserved.
 *
 *  Author: Ora Lassila
 *
 *  $Id: SExpr.java,v 1.2 1998/01/22 13:09:00 bmahe Exp $
 */

package org.w3c.tools.sexpr;

import java.io.PrintStream;

/**
 * Interface for all new s-expression subtypes.
 */
public interface SExpr {

  /**
   * Print a representation of the s-expression into the output stream.
   */
  public void printExpr(PrintStream out);

}
