/*
 * XtangoAnimator.java                       version 1.00   4 September 1996
 *    synchronized on icon.position                  1.01  28 September 1996
 *    removed bounded buffer                         1.02   7 October   1996
 *    took out sleep(frameDelay) in doCommand()      1.03  16 October   1996
 *    single stepping button                         1.04  20 December  1996
 *    JDK 1.1 event delegation model                 1.050  7 May       1997
 *    both abstract, synchronized illegal on draw()  1.051  8 December  1997
 *
 *  This is an implementation of the Xtango animator interpreter command
 * set in Java.  See the main() method below for example use.  For more
 * information about the Xtango algorithm animation package, see
 * John T. Stasko and Doug Hayes, "XTANGO Algorithm Animation Designer's
 * Package," October 1992, available by anonymous ftp from machine
 * ftp.cc.gatech.edu (from directory pub/people/stasko, retrieve file
 * xtango.tar.Z, then uncompress and extract file xtangodoc.ps from
 * directory ./xtango/doc in the archive file xtango.tar).
 *
 * (C) 1996, 1997 Stephen J. Hartley.  All rights reserved.
 * Permission to use, copy, modify, and distribute this software for
 * non-commercial uses is hereby granted provided this notice is kept
 * intact within the source file.
 *
 * mailto:shartley@mcs.drexel.edu http://www.mcs.drexel.edu/~shartley
 * Drexel University, Math and Computer Science Department
 * Philadelphia, PA 19104 USA  telephone: +1-215-895-2678
 */

//package XtangoAnimation;

import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;




public final class XtangoAnimator

    implements WindowListener {

  public static Position i = null;
  public static String filename="o";

  public static final int OUTLINE = 0; // for fillval in rectangle, circle,
  public static final int HALF = 1; // triangle, fill
  public static final int SOLID = 2;

  public static final int THIN = 3; // for widthval in line, pointLine
  public static final int MEDTHICK = 4;
  public static final int THICK = 5;

  // SYNC: the movement is completed before the method returns
  static final int SYNC = 6;

  // ASYNC: the method returns after the command is started
  static final int ASYNC = 7; // for moveAsync, etc.

  static boolean debug = true;

  private static final int frameWidth = 640;
  private static final int frameHeight = 700;

  private AnimatorFrame af = null;

  public XtangoAnimator(int frameWidth, int frameHeight, boolean dbg) {
    debug = dbg;
    af = new AnimatorFrame(frameWidth, frameHeight, this);
    af.addWindowListener(this);
  }

  public XtangoAnimator() {
    this(frameWidth, frameHeight, false);
  }

  public XtangoAnimator(boolean dbg) {
    this(frameWidth, frameHeight, dbg);
  }

  public XtangoAnimator(int frameWidth, int frameHeight) {
    this(frameWidth, frameHeight, false);
  }

  public void windowClosed(WindowEvent evt) {
    if (debug) {
      System.out.println("XtangoAnimator: window closed");
    }
  }

  public void windowDeiconified(WindowEvent evt) {
    if (debug) {
      System.out.println("XtangoAnimator: window deiconified");
    }
  }

  public void windowIconified(WindowEvent evt) {
    if (debug) {
      System.out.println("XtangoAnimator: window iconified");
    }
  }

  public void windowActivated(WindowEvent evt) {
    if (debug) {
      System.out.println("XtangoAnimator: window activated");
    }
  }

  public void windowDeactivated(WindowEvent evt) {
    if (debug) {
      System.out.println("XtangoAnimator: window deactivated");
    }
  }

  public void windowOpened(WindowEvent evt) {
    if (debug) {
      System.out.println("XtangoAnimator: window opened");
    }
  }

  // user used window manager, not Java, to destroy the window
  public void windowClosing(WindowEvent evt) {
    if (debug) {
      System.out.println("XtangoAnimator: window destroy");
    }
    af.setVisible(false);
    af.dispose();
    System.exit(0);
  }

  synchronized void startPushed() {
    notify();
  }

  synchronized void quitPushed() {
    notify();
  }

// Wait to start the animation until the Start button is clicked.
  public synchronized void begin() {
    System.out.println("XtangoAnimator: Push the Start button");
    // modified so user does not need to push the start button. --SDS
    Button b = new Button("Start");
    ActionEvent ae = new ActionEvent(b, 127, null);
    af.actionPerformed(ae);
    //try {
    //   wait();
    //} catch (InterruptedException e) {}
  }

// Wait to terminate the animation until the Close or Quit button is clicked.
  public synchronized void end() {
    if (debug) {
      System.out.println("XtangoAnimator end");
    }
    af.doCommand(new Cend());
    // modified so user does not need to push the close button. --SDS
    System.exit(0);
    //try {
    //   wait();
    //} catch (InterruptedException e) {}
  }

// Change the background to the given color.
// The default starter is white.
  public void bg(Color colorval) {
    if (debug) {
      System.out.println("XtangoAnimator bg: " + colorval);
    }
    af.doCommand(new Cbg(colorval));
  }

// Change the displayed coordinates to the given values.
// The bottom left and top right corners of the animation
// window are set to coordinates (lx,by) and (rx,ty).
  public void coords(float lx, float by, float rx, float ty) {
    if (rx - lx == 0 || ty - by == 0) {
      System.err.println
          ("XtangoAnimator coords: rx - lx == 0 || ty - by == 0");
      return;
    }
    /*
     * widthCanvas and heightCanvas determine what (x,y) coordinates actually
     * show up in the AnimatorFrame window; (0,0) is by default in the lower
     * left corner; whatever is in the Canvas bounds will show up when drawn;
     * the coords command really just moves (0,0) i.e. really just sets what
     * the lower left corner is; only two numbers are needed but the Xtango
     * command has four; for simplicity rx-lx==ty-by is required; oops, the
     * coord command also sets the linear scale so just three numbers are
     * actually needed.
     */
    if (rx - lx != ty - by) {
      System.err.println
          ("XtangoAnimator coords: rx - lx != ty - by");
      return;
    }
    Ccoords c = new Ccoords(lx, by, rx, ty);
    af.doCommand(c);
    if (debug) {
      System.out.println("XtangoAnimator coords: " + c);
    }
  }

// Generate the given number of animation frames with no changes in them.
  public void delay(int steps) {
    if (debug) {
      System.out.println("XtangoAnimator delay: " + steps);
    }
    af.doCommand(new Cdelay(steps));
  }

// Create a line with one endpoint at the given position
// and of the given size.
  public void line(String id, float xpos, float ypos,
                   float xsize, float ysize, Color colorval, int widthval) {
    Iline ell = new Iline(id, xpos, ypos, xsize, ysize, colorval, widthval);
    af.doCommand(ell);
    if (debug) {
      System.out.println("XtangoAnimator line: " + ell);
    }
  }

// Create a line with its two endpoints at the given positions
  public void pointLine(String id, float xpos1, float ypos1,
                        float xpos2, float ypos2, Color colorval, int widthval) {
    Iline ell = new Iline(id, xpos1, ypos1, xpos2 - xpos1, ypos2 - ypos1,
                          colorval, widthval);
    af.doCommand(ell);
    if (debug) {
      System.out.println("XtangoAnimator pointLine: " + ell);
    }
  }

// Create a rectangle with lower left corner at the given position
// and of the given size (the size must be positive).
  public void rectangle(String id, float xpos, float ypos,
                        float xsize, float ysize, Color colorval, int fillval) {
    Irectangle r = new Irectangle(id, xpos, ypos, xsize, ysize,
                                  colorval, fillval);
    af.doCommand(r);
    if (debug) {
      System.out.println("XtangoAnimator rectangle: " + r);
    }
  }

// Create a circle with the given radius centered at the given position.
  public void circle(String id, float xpos, float ypos,
                     float radius, Color colorval, int fillval) {
    Icircle c = new Icircle(id, xpos, ypos, radius, colorval, fillval);
    af.doCommand(c);
    if (debug) {
      System.out.println("XtangoAnimator circle: " + c);
    }
  }

// Create a triangle whose three vertices are located
// at the given three coordinates.
  public void triangle(String id, float v1x, float v1y, float v2x,
                       float v2y, float v3x, float v3y, Color colorval,
                       int fillval) {
    Itriangle t = new Itriangle(id, v1x, v1y, v2x, v2y, v3x, v3y,
                                colorval, fillval);
    af.doCommand(t);
    if (debug) {
      System.out.println("XtangoAnimator triangle: " + t);
    }
  }

// Create text with lower left corner at the given position
// if centered is false.  If centered is true, the position
// arguments denote the place where the center of the text is put.
  public void text(String id, float xpos, float ypos,
                   boolean centered, Color colorval, String string) {
    Itext t = new Itext(id, xpos, ypos, centered, colorval, string,
                        Itext.NORMAL);
    af.doCommand(t);
    if (debug) {
      System.out.println("XtangoAnimator text: " + t);
    }
  }

// This works just like the text command except that
// this text is in a much larger font.
  public void bigText(String id, float xpos, float ypos,
                      boolean centered, Color colorval, String string) {
    Itext t = new Itext(id, xpos, ypos, centered, colorval, string,
                        Itext.BIG);
    af.doCommand(t);
    if (debug) {
      System.out.println("XtangoAnimator bigText: " + t);
    }
  }

// This works just like the text command except that
// this text is in a much smaller font.
  public void smallText(String id, float xpos, float ypos,
                        boolean centered, Color colorval, String string) {
    Itext t = new Itext(id, xpos, ypos, centered, colorval, string,
                        Itext.SMALL);
    af.doCommand(t);
    if (debug) {
      System.out.println("XtangoAnimator smallText: " + t);
    }
  }

// Smoothly move, via a sequence of intermediate steps,
// the object with the given id to the specified position.
  public void move(String id, float xpos, float ypos) {
    if (debug) {
      System.out.println("XtangoAnimator move: " + id
                         + ", xpos=" + xpos + ", ypos=" + ypos);
    }
    af.doCommand(new Cmove(id, xpos, ypos, SYNC));
  }

// Smoothly move, via a sequence of intermediate steps,
// the object with the given identifier by the given relative distance.
  public void moveRelative(String id, float xdelta, float ydelta) {
    if (debug) {
      System.out.println("XtangoAnimator moveRelative: " + id
                         + ", xdelta=" + xdelta + ", ydelta=" + ydelta);
    }
    af.doCommand(new CmoveRelative(id, xdelta, ydelta, SYNC));
  }

// Smoothly move, via a sequence of intermediate steps, the object with
// the first id to the current position of the object with the second id.
  public void moveTo(String id1, String id2) {
    if (debug) {
      System.out.println("XtangoAnimator moveTo: " + id1 + " to " + id2);
    }
    af.doCommand(new CmoveTo(id1, id2, SYNC));
  }

// Move the object with the given identifier
// to the designated position in a one frame jump.
  public void jump(String id, float xpos, float ypos) {
    if (debug) {
      System.out.println("XtangoAnimator jump: " + id
                         + ", xpos=" + xpos + ", ypos=" + ypos);
    }
    af.doCommand(new Cjump(id, xpos, ypos));
  }

// Move the object with the given identifier
// by the provided relative distance in one jump.
  public void jumpRelative(String id, float xdelta, float ydelta) {
    if (debug) {
      System.out.println("XtangoAnimator jumpRelative: " + id
                         + ", xdelta=" + xdelta + ", ydelta=" + ydelta);
    }
    af.doCommand(new CjumpRelative(id, xdelta, ydelta));
  }

// Move the object with the given identifier to the current position
// of the object with the second identifier in a one frame jump.
  public void jumpTo(String id1, String id2) {
    if (debug) {
      System.out.println("XtangoAnimator jumpTo: " + id1 + " to " + id2);
    }
    af.doCommand(new CjumpTo(id1, id2));
  }

// Change the color of the object with the given identifier
// to the specified color value.
  public void color(String id, Color colorval) {
    if (debug) {
      System.out.println("XtangoAnimator color: " + id + ", " + colorval);
    }
    af.doCommand(new Ccolor(id, colorval));
  }

// Permanently remove the object with the given identifier from the display,
// and remove any association of this identifier string with the object.
  public void delete(String id) {
    if (debug) {
      System.out.println("XtangoAnimator delete: " + id);
    }
    af.doCommand(new Cdelete(id));
  }

// Change the object with the given identifier to the designated fill value.
  public void fill(String id, int fillval) {
    if (debug) {
      System.out.println("XtangoAnimator fill: id=" + id
                         + ", fillval=" + fillval);
    }
    af.doCommand(new Cfill(id, fillval));
  }

// Toggle the visibility of the object with the given identifier.
  public void vis(String id) {
    if (debug) {
      System.out.println("XtangoAnimator vis: " + id);
    }
    af.doCommand(new Cvis(id, Cvis.VIS));
  }

// Push the object with the given identifier backward to the viewing
// plane farthest from the viewer.
  public void lower(String id) {
    if (debug) {
      System.out.println("XtangoAnimator lower: " + id);
    }
    af.doCommand(new Cvis(id, Cvis.LOWER));
  }

// Pop the object with the given identifier forward to the viewing
// plane closest to the viewer.
  public void raise(String id) {
    if (debug) {
      System.out.println("XtangoAnimator raise: " + id);
    }
    af.doCommand(new Cvis(id, Cvis.RAISE));
  }

// Make the two objects specified by the given identifiers
// smoothly exchange positions.
  public void exchangePos(String id1, String id2) {
    if (debug) {
      System.out.println("XtangoAnimator exchangePos: " + id1 + ", " + id2);
    }
    af.doCommand(new CexchangePos(id1, id2, SYNC));
  }

// Make the two objects specified by the given identifiers
// exchange positions in one instantaneous jump.
  public void switchPos(String id1, String id2) {
    if (debug) {
      System.out.println("XtangoAnimator switchPos: " + id1 + ", " + id2);
    }
    af.doCommand(new CswitchPos(id1, id2));
  }

// Exchange the identifiers used to designate the two given objects.
  public void swapIds(String id1, String id2) {
    if (debug) {
      System.out.println("XtangoAnimator swapIds: " + id1 + ", " + id2);
    }
    af.doCommand(new CswapIds(id1, id2));
  }

// Smoothly move asynchronously, via a sequence of intermediate steps,
// the object with the given id to the specified position.
  public void moveAsync(String id, float xpos, float ypos) {
    if (debug) {
      System.out.println("XtangoAnimator moveAsync: " + id
                         + ", xpos=" + xpos + ", ypos=" + ypos);
    }
    af.doCommand(new Cmove(id, xpos, ypos, ASYNC));
  }

// Smoothly move asynchronously, via a sequence of intermediate steps,
// the object with the given identifier by the given relative distance.
  public void moveRelativeAsync(String id, float xdelta, float ydelta) {
    if (debug) {
      System.out.println("XtangoAnimator moveRelativeAsync: "
                         + id + ", xdelta=" + xdelta + ", ydelta=" + ydelta);
    }
    af.doCommand(new CmoveRelative(id, xdelta, ydelta, ASYNC));
  }

// Smoothly move asynchronously, via a sequence of intermediate steps,
// the object with the first id to the current position of the object
// with the second id.
  public void moveToAsync(String id1, String id2) {
    if (debug) {
      System.out.println("XtangoAnimator moveToAsync: "
                         + id1 + " to " + id2);
    }
    af.doCommand(new CmoveTo(id1, id2, ASYNC));
  }

// Make the two objects specified by the given identifiers
// smoothly exchange positions asynchronously.
  public void exchangePosAsync(String id1, String id2) {
    if (debug) {
      System.out.println("XtangoAnimator exchangePosAsync: "
                         + id1 + ", " + id2);
    }
    af.doCommand(new CexchangePos(id1, id2, ASYNC));
  }

  public static void main(String[] args) { // for testing
if (args.length!=1){
  System.out.println("no file name argument");
  System.exit(1);
}

filename= args[0];

    if (debug) {
      System.out.println("XtangoAnimator: main");
    }
    XtangoAnimator xa = new XtangoAnimator();
    xa.begin();
//       xa.delay(10);
//       xa.pointLine("Lthin", 0.3f, 0.2f, 0.8f, 0.7f, Color.black,
//          XtangoAnimator.THIN);
//       xa.delay(10);
//       xa.pointLine("Lmedium", 0.4f, 0.2f, 0.9f, 0.7f, Color.black,
//          XtangoAnimator.MEDTHICK);
//       xa.delay(10);
//       xa.pointLine("Lthick", 0.5f, 0.2f, 1.0f, 0.7f, Color.black,
//          XtangoAnimator.THICK);
//       xa.delay(10);
//       xa.triangle("Tri1", 0.1f, 0.1f, 0.5f, 0.9f, 0.9f, 0.2f, Color.magenta,
//          XtangoAnimator.OUTLINE);
//       xa.delay(10);
//       xa.triangle("Tri2", 0.1f, 0.3f, 0.7f, 0.9f, 0.8f, 0.3f, Color.green,
//          XtangoAnimator.SOLID);
//       xa.delay(10);
//       xa.bg(Color.yellow);
//       xa.delay(10);
//       xa.text("t00", 0.0f, 0.0f, false, Color.gray, "text 0");
//       xa.delay(10);
//       xa.text("t01", 0.1f, 0.1f, false, Color.blue, "text 1");
//       xa.delay(10);
//       xa.circle("c0", 0.8f, 0.2f, 0.1f, Color.red, XtangoAnimator.SOLID);
//       xa.delay(10);
//       xa.bg(Color.cyan);
//       xa.delay(10);
//       xa.text("t02", 0.2f, 0.2f, false, Color.gray, "text 2");
//       xa.delay(10);
//       xa.text("t03", 0.3f, 0.3f, false, Color.gray, "text 3");
//       xa.delay(10);
//       xa.text("t04", 0.4f, 0.4f, false, Color.gray, "text 4");
//       xa.delay(10);
//       xa.text("t05", 0.5f, 0.5f, false, Color.gray, "text 5");
//       xa.delay(10);
//       xa.text("t06", 0.6f, 0.6f, false, Color.gray, "text 6");
//       xa.delay(10);
//       xa.text("t06", 0.6f, 0.6f, false, Color.gray, "TEXT 6");
//       xa.delay(10);
//       xa.text("t07", 0.7f, 0.7f, false, Color.gray, "text 7");
//       xa.delay(10);
//       xa.text("t08", 0.8f, 0.8f, false, Color.gray, "text 8");
//       xa.delay(10);
//       xa.text("t09", 0.9f, 0.9f, false, Color.gray, "text 9");
//       xa.delay(10);
//       xa.text("t10", 1.0f, 1.0f, false, Color.gray, "text10");
//       xa.delay(10);
//       xa.circle("c1", 0.7f, 0.7f, 0.05f, Color.black, XtangoAnimator.OUTLINE);
//       xa.delay(10);
//       xa.fill("c1", XtangoAnimator.SOLID);
//       xa.delay(10);
//       xa.fill("c1", XtangoAnimator.HALF);
//       xa.delay(10);
//       xa.color("t07", Color.white);
//       xa.delay(10);
//       xa.raise("t07");
//       xa.delay(10);
//       xa.swapIds("t04", "t05");
//       xa.jump("t04", 0.9f, 0.1f);
//       xa.delay(10);
//       xa.delete("t04"); xa.delete("t05"); xa.delete("t06");
//       xa.delete("t04");
//       xa.delay(10);
//       xa.jumpTo("c0", "t03");
//       xa.delay(10);
//       xa.vis("t03");    // should lower
//       xa.delay(10);
//       xa.vis("t03");    // should raise
//       xa.delay(10);
//       xa.rectangle("R", 0.5f, 0.5f, 0.1f, 0.2f, Color.black,
//          XtangoAnimator.SOLID);
//       xa.delay(10);
//       xa.rectangle("Rhalf", 0.6f, 0.6f, 0.2f, 0.1f, Color.black,
//          XtangoAnimator.HALF);
//       xa.delay(10);
//       xa.line("L", 0.1f, 0.1f, 0.8f, 0.8f, Color.black, XtangoAnimator.THIN);
//       xa.delay(10);
    xa.circle("moveTest1", 0.1f, 0.9f, 0.05f, Color.magenta,
              XtangoAnimator.SOLID);
//       xa.move("moveTest1", 0.9f, 0.1f);
    xa.circle("moveTest2", 0.9f, 0.9f, 0.05f, Color.blue,
              XtangoAnimator.OUTLINE);
//       xa.move("moveTest2", 0.1f, 0.1f);
//       for (int i = 0; i < 5; i++) {
//          xa.rectangle("R"+i, (float)Math.random(), (float)Math.random(),
//             (float)Math.random()/4,
//             (float)Math.random()/4, Color.magenta, XtangoAnimator.SOLID);
//          xa.delay(2);
//          xa.color("R"+i, Color.orange);
//          xa.delay(2);
//          xa.delete("R"+i);
//       }
//       for (int i = 0; i < 5; i++) {
//          xa.delay(2);
//          float x = (float)Math.random();
//          float y = (float)Math.random();
//          System.out.println("jump x=" + x + ", y=" + y);
//          xa.jump("c1", x, y);
//       }
    xa.exchangePosAsync("moveTest1", "moveTest2");
//       xa.delay(1);
//       xa.bigText("ha!", 0.5f, 0.1f, true, Color.black, "ha!");
    xa.exchangePos("moveTest2", "moveTest1"); // swapped args
//       xa.delay(1);
//       xa.bigText("Ha!", 0.6f, 0.1f, true, Color.black, "Ha!");
    xa.exchangePosAsync("moveTest1", "moveTest2");
//       xa.delay(5);
//       xa.bigText("HA!", 0.7f, 0.1f, true, Color.black, "HA!");
//       xa.delay(10);
//       xa.jumpRelative("t00", 0.05f, 0.05f);
//       xa.delay(10);
//       xa.jumpRelative("t01", -0.05f, 0.07f);
//       xa.delay(10);
//       xa.jumpRelative("t02", 0.02f, -0.15f);
//       xa.delay(10);
//       xa.jumpRelative("t03", -0.3f, -0.3f);
//       xa.delay(10);
//       xa.moveRelative("moveTest1", 0.3f, 0.3f);
//       xa.moveTo("moveTest2", "t00");
//       xa.delay(10);
//       xa.smallText("Tsmall", 0.2f, 0.9f, true, Color.magenta, "Going...");
//       xa.line("L1", 0.2f, 0.85f, 0.0f, 0.1f, Color.black, XtangoAnimator.THIN);
//       xa.line("L2", 0.15f, 0.9f, 0.1f, 0.0f, Color.black, XtangoAnimator.THIN);
//       xa.delay(10);
//       xa.text("Tnormal", 0.15f, 0.8f, true, Color.blue, "Yup, Going...");
//       xa.line("L3", 0.15f, 0.75f, 0.0f, 0.1f, Color.black, XtangoAnimator.THIN);
//       xa.line("L4", 0.1f, 0.8f, 0.1f, 0.0f, Color.black, XtangoAnimator.THIN);
//       xa.delay(10);
//       xa.bigText("Tbig", 0.1f, 0.7f, true, Color.darkGray, "Surely, GONE!");
//       xa.line("L5", 0.1f, 0.65f, 0.0f, 0.1f, Color.black, XtangoAnimator.THIN);
//       xa.line("L6", 0.05f, 0.7f, 0.1f, 0.0f, Color.black, XtangoAnimator.THIN);
//       xa.delay(10);
//       xa.coords(0.0f, 0.0f, 0.5f, 0.5f);
//       xa.delay(10);
//       xa.coords(0.0f, 0.5f, 0.5f, 1.0f);
//       xa.delay(10);
//       xa.coords(0.0f, 0.0f, 1.0f, 1.0f);
//       xa.delay(10);
//       xa.coords(0.0f, 0.0f, 1.0f, 0.5f);
//       xa.delay(10);
//       xa.coords(0.0f, 0.0f, 0.5f, 1.0f);
//       xa.delay(10);
//       xa.switchPos("Tbig", "Tsmall");
//       xa.delay(10);
//       xa.moveToAsync("Tnormal", "Tsmall");
//       xa.delay(1);
//       xa.moveRelativeAsync("Tbig", 0.3f, 0.0f);
//       xa.moveAsync("Tsmall", 0.2f, 0.9f);
    System.out.println("DONE!");


    try {
     // System.out.println("inside!");
      File output = new File(filename);
      FileWriter out = new FileWriter(output);

      out.write("< XtangoAnimator , finished , None >" + "\r\n");
      out.close();

    }

    catch (Exception e) { // IOException  or  ClassNotFoundException

      System.out.println(e);
    }
    xa.end();
   // System.exit(0);
  }


}

final class AnimatorFrame
    extends Frame
    implements ActionListener {

  private int frameWidth = 0;
  private int frameHeight = 0;
  private XtangoAnimator xa = null;
  private Panel p = null;
  private AnimatorCanvas ac = null;
  private Thread at = null; // in AnimatorCanvas
  private Vector icons = null;
  private Hashtable ht = null;
  private TextField tf = null;
  private boolean singleStep = false;
  private Button singleStepButton = null;

  AnimatorFrame(int frameWidth, int frameHeight, XtangoAnimator xa) {
    super("AnimatorFrame");
    setLayout(new BorderLayout());
    p = new Panel();
    p.setLayout(new FlowLayout());
    Button b;
    p.add(b = new Button("Start"));
    b.addActionListener(this);
    p.add(b = new Button("Faster"));
    b.addActionListener(this);
    tf = new TextField(5);
    tf.setEditable(false);
    p.add(tf);
    p.add(b = new Button("Slower"));
    b.addActionListener(this);
    p.add(singleStepButton = new Button("Single Step Off"));
    singleStepButton.addActionListener(this);
    p.add(b = new Button("Step"));
    b.addActionListener(this);
    p.add(b = new Button("Stop"));
    b.addActionListener(this);
    p.add(b = new Button("Close"));
    b.addActionListener(this);
    p.add(b = new Button("Quit"));
    b.addActionListener(this);
    add("North", p);
    icons = new Vector();
    ac = new AnimatorCanvas(icons);
    add("Center", ac);
    ht = new Hashtable();
    this.frameWidth = frameWidth;
    this.frameHeight = frameHeight;
    this.xa = xa;
    setSize(frameWidth, frameHeight);
    setBackground(Color.white);
    setVisible(true);
    ac.resetBounds();
    if (XtangoAnimator.debug) {
      System.out.println("AnimatorFrame: constructor done");
    }
  }

  public void actionPerformed(ActionEvent evt) {
    Object o = evt.getSource();
    if (o instanceof Button) {
      String label = ( (Button) o).getLabel();
      if (label.equals("Start")) {
        tf.setText("" + ac.frameDelay);
        if (at == null) {
          at = new Thread(ac);
          at.setPriority(Thread.MAX_PRIORITY);
          at.setDaemon(true);
          at.start();
          if (XtangoAnimator.debug) {
            System.out.println("AnimatorFrame: animator thread started");
          }
        }
        xa.startPushed();
      }
      else if (label.equals("Faster")) { // divide frameDelay by 2
        int newDelay = ac.frameDelay;
        newDelay /= 2;
        newDelay = newDelay < 16 ? 16 : newDelay;
        ac.frameDelay = newDelay;
        tf.setText("" + newDelay);
        if (XtangoAnimator.debug) {
          System.out.println("AnimatorFrame: new frameDelay="
                             + newDelay);
        }
      }
      else if (label.equals("Slower")) { // multiply frameDelay by 2
        int newDelay = ac.frameDelay;
        newDelay *= 2;
        ac.frameDelay = newDelay;
        tf.setText("" + newDelay);
        if (XtangoAnimator.debug) {
          System.out.println("AnimatorFrame: new frameDelay="
                             + newDelay);
        }
      }
      else if (label.equals("Single Step Off")) {
        synchronized (this) {
          singleStep = true;
        }
        singleStepButton.setLabel("Single Step On");
      }
      else if (label.equals("Single Step On")) {
        synchronized (this) {
          singleStep = false; // now that single stepping is off,
          this.notifyAll(); // clear out any waiting threads
        }
        singleStepButton.setLabel("Single Step Off");
      }
      else if (label.equals("Step")) {
        synchronized (this) {
          if (singleStep) {
            this.notifyAll();
          }
        }
      }
      else if (label.equals("Stop")) {
        if (at != null) {
          at.stop();
        }
      }
      else if (label.equals("Close")) {
        if (at != null) {
          at.stop();
        }
        this.setVisible(false);
        this.dispose();
        xa.quitPushed();
      }
      else if (label.equals("Quit")) {
        System.exit(0);
      }
      else if (XtangoAnimator.debug) {
        System.out.println("unknown Button label: " + label);
      }
    }
    else if (XtangoAnimator.debug) {
      System.out.println("ActionEvent is not a Button");
    }
  }

  void doCommand(AnimatorCommand command) {
    // do not make this synchronized so that commands can be processed
    // in parallel, e.g. two threads both moving icons at the same time
    if (XtangoAnimator.debug) {
      System.out.println("doCommand: command " + command);
    }
    if (command instanceof AnimatorAction) {
      ( (AnimatorAction) command).perform(ac, ht, icons);
    }
    else if (command instanceof AnimatorIcon) {
      ( (AnimatorIcon) command).add(ht, icons);
    }
    else {
      System.err.println("doCommand: illegal command");
    }
// Make it the programmer's responsibility to call xa.delay(frames) if
// a delay is needed.  This will allow a group of commmands to be done
// nearly instantaneously.
//      try {
//         int frameDelay = ac.frameDelay;
//         Thread.sleep(frameDelay);
//      } catch (InterruptedException e) {}
    synchronized (this) {
      if (singleStep) {
        try {
          wait();
        }
        catch (InterruptedException e) {}
      }
    }
  }
}

final class AnimatorCanvas
    extends Canvas
    implements Runnable {

  int frameDelay = 16; // was 128
  int numMoveSteps = 10;

  private int widthCanvas = 0, heightCanvas = 0;
  private int cornerX = 0, cornerY = 0;
  private int squareCanvas = 0;
  private int excessY = 0;
  private Vector icons = null;
  private Image offscreenImage = null;
  private Graphics offscreenGraphics = null;
  private float lx = 0, by = 0, rx = 1, ty = 1; // default (0,0)..(1,1)

  AnimatorCanvas(Vector icons) {
    super();
    this.icons = icons;
  }

  synchronized void changeCoordinates
      (float lx, float by, float rx, float ty) {
    this.lx = lx;
    this.by = by;
    this.rx = rx;
    this.ty = ty;
  }

  void resetBounds() {
    Rectangle boundingBox = getBounds();
    widthCanvas = boundingBox.width;
    heightCanvas = boundingBox.height;
    squareCanvas = Math.min(widthCanvas, heightCanvas);
    excessY = heightCanvas - squareCanvas;
// take out the following line so the Canvas can utilize all
//    setSize(squareCanvas, squareCanvas);
    cornerX = boundingBox.x;
    cornerY = boundingBox.y;
    offscreenImage = createImage(widthCanvas, heightCanvas);
    offscreenGraphics = offscreenImage.getGraphics();
    if (XtangoAnimator.debug) {
      System.out.println("Canvas: cornerX=" + cornerX
                         + ", cornerY=" + cornerY);
      System.out.println("Canvas: widthCanvas=" + widthCanvas
                         + ", heightCanvas=" + heightCanvas + ", squareCanvas="
                         + squareCanvas + ", excessY=" + excessY);
    }
  }

  public void update(Graphics g) {
    paint(g);
  }

  public synchronized void paint(Graphics g) {
    // for each icon in the icon Vector
    // icon.draw(g);
    if (XtangoAnimator.debug) {
      System.out.println("AnimatorCanvas: paint");
    }
    offscreenGraphics.setColor(getBackground());
    offscreenGraphics.fillRect(0, 0, widthCanvas, heightCanvas);
    synchronized (icons) {
      int howMany = icons.size();
      for (int i = 0; i < howMany; i++) {
        if (XtangoAnimator.debug) {
          System.out.println("painting " + icons.elementAt(i));
        }
        ( (AnimatorIcon) icons.elementAt(i)).draw(this, offscreenGraphics);
      }
    }
    g.drawImage(offscreenImage, 0, 0, this);
  }

  int scaleX(float xpos) {
    return ( (int) ( (xpos - lx) / (rx - lx) * squareCanvas));
  }

  int scaleY(float ypos) { // excessY: make sure y=0 is along bottom
    return ( (int) ( (ty - ypos) / (ty - by) * squareCanvas)) + excessY;
  }

  int scaleR(float r) { // linear scaling for radius, rectangle size,...
    return (int) (r / (rx - lx) * squareCanvas); // assumes rx-lx==ty-by
  }

  public void run() {
    while (true) {
      try {
        Thread.sleep(frameDelay);
      }
      catch (InterruptedException e) {}
      repaint();
    }
  }
}

abstract class AnimatorCommand {

  public abstract String toString();
}

abstract class AnimatorAction
    extends AnimatorCommand {

  abstract void perform(AnimatorCanvas ac, Hashtable ht, Vector icons);

  public abstract String toString();
}

class Cbg
    extends AnimatorAction {

  private Color colorval = null;

  Cbg(Color colorval) {
    this.colorval = colorval;
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    ac.setBackground(colorval);
  }

  public String toString() {
    return "Cbg: colorval=" + colorval;
  }
}

class Ccoords
    extends AnimatorAction {

  private float lx = 0, by = 0, rx = 1, ty = 1;

  Ccoords(float lx, float by, float rx, float ty) {
    this.lx = lx;
    this.by = by;
    this.rx = rx;
    this.ty = ty;
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    ac.changeCoordinates(lx, by, rx, ty);
  }

  public String toString() {
    return "Ccoords: (lx,by,rx,ty)=" + lx + "," + by + "," + rx + "," + ty;
  }
}

class Cdelay
    extends AnimatorAction {

  private int steps = 0;

  Cdelay(int steps) {
    this.steps = steps;
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    if (XtangoAnimator.debug) {
      System.out.println("Cdelay: napping " + steps + " steps");
    }
    try {
      Thread.sleep(steps * ac.frameDelay);
    }
    catch (InterruptedException e) {}
  }

  public String toString() {
    return "Cdelay: steps=" + steps;
  }
}

class Ccolor
    extends AnimatorAction {

  private String id = null;
  private Color colorval = null;

  Ccolor(String id, Color colorval) {
    this.id = id;
    this.colorval = colorval;
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    Object icon = null;
    synchronized (ht) {
      if ( (icon = ht.get(id)) == null) {
        System.err.println("Ccolor hashtable: no such id=" + id);
        return;
      }
    }
    ( (AnimatorIcon) icon).colorval(colorval);
  }

  public String toString() {
    return "Ccolor: colorval=" + colorval;
  }
}

class Cfill
    extends AnimatorAction {

  private String id = null;
  private int fillval = XtangoAnimator.OUTLINE;

  Cfill(String id, int fillval) {
    this.id = id;
    this.fillval = fillval;
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    Object icon = null;
    synchronized (ht) {
      if ( (icon = ht.get(id)) == null) {
        System.err.println("Cfill hashtable: no such id=" + id);
        return;
      }
    }
    if (icon instanceof AnimatorShape) {
      if (fillval == XtangoAnimator.SOLID
          || fillval == XtangoAnimator.HALF
          || fillval == XtangoAnimator.OUTLINE) {
        ( (AnimatorShape) icon).fillval(fillval);
      }
      else {
        System.err.println
            ("Cfill: fillval=" + fillval + " not implemented");
      }
    }
    else {
      System.err.println("Cfill: icon is not fillable, icon=" + icon);
    }
  }

  public String toString() {
    return "Cfill: fillval=" + fillval;
  }
}

class Cdelete
    extends AnimatorAction {

  private String id = null;

  Cdelete(String id) {
    this.id = id;
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    Object icon = null;
    synchronized (ht) {
      if ( (icon = ht.remove(id)) == null) {
        System.err.println("Cdelete hashtable: no such id=" + id);
        return;
      }
    }
    synchronized (icons) {
      if (!icons.removeElement(icon)) {
        System.err.println("Cdelete vector: no such icon=" + icon);
        return;
      }
    }
  }

  public String toString() {
    return "Cdelete: id=" + id;
  }
}

class Cmove
    extends AnimatorAction
    implements Runnable {

  protected final static int ABSOLUTE = 1, RELATIVE = 2, ICON = 3;

  protected String id = null, id2 = null;
  protected float xpos = 0, ypos = 0;
  protected float xdelta = 0, ydelta = 0;
  protected AnimatorIcon icon = null, icon2 = null;
  protected int syncMode = 0;
  protected int moveMode = 0;
  protected int numMoveSteps = 0, frameDelay = 0;

  Cmove(String id, float xpos, float ypos, int syncMode,
        String id2, float xdelta, float ydelta, int moveMode) {
    this.id = id;
    this.id2 = id2;
    this.xpos = xpos;
    this.ypos = ypos;
    this.xdelta = xdelta;
    this.ydelta = ydelta;
    this.syncMode = syncMode;
    this.moveMode = moveMode;
  }

  Cmove(String id, float xpos, float ypos, int syncMode) {
    this(id, xpos, ypos, syncMode, null, 0.0f, 0.0f, ABSOLUTE);
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    numMoveSteps = ac.numMoveSteps;
    frameDelay = ac.frameDelay;
    synchronized (ht) {
      if ( (icon = (AnimatorIcon) ht.get(id)) == null) {
        System.err.println("Cjump hashtable: no such id=" + id);
        return;
      }
      if (id2 != null) {
        if ( (icon2 = (AnimatorIcon) ht.get(id2)) == null) {
          System.err.println("CmoveTo hashtable: no such id2=" + id2);
          return;
        }
      }
    }
    if (syncMode == XtangoAnimator.SYNC) {
      run();
    }
    else if (syncMode == XtangoAnimator.ASYNC) {
      (new Thread(this)).start();
    }
    else {
      System.err.println("Cmove: illegal syncMode=" + syncMode);
    }
  }

  public void run() {
    /*
     * When moving an icon, we need to synchronize on the icon's position so
     * that an asynchronous move followed closely by a move of the same icon
     * does not interleave.
     */
    synchronized (icon.position) { // for ASYNC move, there may be
      Position p = icon.position(); // a race condition locking
      if (moveMode == RELATIVE) { // the position with another
        xpos = (xdelta + p.x); // move of same icon next
        ypos = (ydelta + p.y);
      }
      else if (moveMode == ICON) {
        Position p2 = null;
        synchronized (icon2.position) {
          p2 = icon2.position();
        }
        xpos = p2.x;
        ypos = p2.y;
      }
      else if (moveMode == ABSOLUTE) {
      }
      else {
        System.err.println("Cmove: illegal moveMode=" + moveMode);
      }
      float xchange = (xpos - p.x) / numMoveSteps;
      float ychange = (ypos - p.y) / numMoveSteps;
      for (int i = 1; i < numMoveSteps; i++) {
        icon.positionRel(xchange, ychange);
        try {
          Thread.sleep(frameDelay);
        }
        catch (InterruptedException e) {}
      }
      icon.position(xpos, ypos); // avoid summing round-off errors
    }
  }

  public String toString() {
    return "Cmove: id=" + id + ", xpos=" + xpos + ", ypos=" + ypos
        + ", syncMode=" + syncMode;
  }
}

class CmoveRelative
    extends Cmove {

  CmoveRelative(String id, float xdelta, float ydelta, int syncMode) {
    super(id, 0.0f, 0.0f, syncMode, null, xdelta, ydelta, RELATIVE);
  }

  public String toString() {
    return "CmoveRelative: id=" + id + ", xdelta=" + xdelta + ", ydelta=" +
        ydelta
        + ", syncMode=" + syncMode;
  }
}

class CmoveTo
    extends Cmove {

  CmoveTo(String id, String id2, int syncMode) {
    super(id, 0.0f, 0.0f, syncMode, id2, 0.0f, 0.0f, ICON);
  }

  public String toString() {
    return "CmoveTo: id=" + id + ", id2=" + id2 + ", syncMode=" + syncMode;
  }
}

class Cjump
    extends AnimatorAction {

  private String id = null;
  private float xpos = 0, ypos = 0;

  Cjump(String id, float xpos, float ypos) {
    this.id = id;
    this.xpos = xpos;
    this.ypos = ypos;
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    AnimatorIcon icon = null;
    synchronized (ht) {
      if ( (icon = (AnimatorIcon) ht.get(id)) == null) {
        System.err.println("Cjump hashtable: no such id=" + id);
        return;
      }
    }
    synchronized (icon.position) {
      icon.position(xpos, ypos);
    }
  }

  public String toString() {
    return "Cjump: id=" + id + ", xpos=" + xpos + ", ypos=" + ypos;
  }
}

class CjumpRelative
    extends AnimatorAction {

  private String id = null;
  private float xdelta = 0, ydelta = 0;

  CjumpRelative(String id, float xdelta, float ydelta) {
    this.id = id;
    this.xdelta = xdelta;
    this.ydelta = ydelta;
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    AnimatorIcon icon = null;
    synchronized (ht) {
      if ( (icon = (AnimatorIcon) ht.get(id)) == null) {
        System.err.println("CjumpRelative hashtable: no such id=" + id);
        return;
      }
    }
    synchronized (icon.position) {
      icon.positionRel(xdelta, ydelta);
    }
  }

  public String toString() {
    return "CjumpRelative: id=" + id + ", xdelta=" + xdelta + ", ydelta=" +
        ydelta;
  }
}

class CjumpTo
    extends AnimatorAction {

  private String id1 = null;
  private String id2 = null;

  CjumpTo(String id1, String id2) {
    this.id1 = id1;
    this.id2 = id2;
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    AnimatorIcon icon1 = null, icon2 = null;
    Position p = null;
    synchronized (ht) {
      if ( (icon1 = (AnimatorIcon) ht.get(id1)) == null) {
        System.err.println("CjumpTo hashtable: no such id1=" + id1);
        return;
      }
      if ( (icon2 = (AnimatorIcon) ht.get(id2)) == null) {
        System.err.println("CjumpTo hashtable: no such id2=" + id2);
        return;
      }
    }
    synchronized (icon2.position) {
      p = icon2.position();
    }
    synchronized (icon1.position) {
      icon1.position(p.x, p.y);
    }
  }

  public String toString() {
    return "CjumpTo: id1=" + id1 + ", id2=" + id2;
  }
}

class CexchangePos
    extends AnimatorAction
    implements Runnable {

  private String id1 = null;
  private String id2 = null;
  private int syncMode = 0;
  private int numMoveSteps = 0, frameDelay = 0;
  private AnimatorIcon icon1 = null, icon2 = null;

  CexchangePos(String id1, String id2, int syncMode) {
    this.id1 = id1;
    this.id2 = id2;
    this.syncMode = syncMode;
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    numMoveSteps = ac.numMoveSteps;
    frameDelay = ac.frameDelay;
    synchronized (ht) {
      if ( (icon1 = (AnimatorIcon) ht.get(id1)) == null) {
        System.err.println("CexchangePos hashtable: no such id1=" + id1);
        return;
      }
      if ( (icon2 = (AnimatorIcon) ht.get(id2)) == null) {
        System.err.println("CexchangePos hashtable: no such id2=" + id2);
        return;
      }
    }
    if (syncMode == XtangoAnimator.SYNC) {
      run();
    }
    else if (syncMode == XtangoAnimator.ASYNC) {
      (new Thread(this)).start();
    }
    else {
      System.err.println("Cmove: illegal syncMode=" + syncMode);
    }
  }

  public void run() {
  // FileOutputStream output=null;
    XtangoAnimator.i=null;
    // while (XtangoAnimator.i!=icon1.position){
          XtangoAnimator.i = icon1.position;
   //    }

    synchronized (icon1.position) { // deadlock possible!

    // Thread.yield();


      if (XtangoAnimator.i==icon2.position){
        try {
    // System.out.println("inside!");
     File output = new File(XtangoAnimator.filename);
     FileWriter out = new FileWriter(output);

     out.write("< XtangoAnimator , deadlock occured , deadlock >" + "\r\n");
     out.close();

   }


    catch (Exception e) {
       e.printStackTrace();
   }

           System.exit(1);

      }
    //Thread.yield();
      synchronized (icon2.position) {
        Position p1 = icon1.position();
        Position p2 = icon2.position();
        float xchange = (p2.x - p1.x) / numMoveSteps;
        float ychange = (p2.y - p1.y) / numMoveSteps;
        for (int i = 1; i < numMoveSteps; i++) {
          icon1.positionRel(xchange, ychange);
          icon2.positionRel( -xchange, -ychange);
          try {
            Thread.sleep(frameDelay);
          }
          catch (InterruptedException e) {}
        }
        icon1.position(p2.x, p2.y); // avoid summing round-off errors
        icon2.position(p1.x, p1.y);
      }
    }
  }

  public String toString() {
    return "CexchangePos: id1=" + id1 + ",id2=" + id2 + ", syncMode=" +
        syncMode;
  }
}

class Cvis
    extends AnimatorAction {

  static final int VIS = 0;
  static final int RAISE = 1;
  static final int LOWER = 2;

  private String id = null;
  private int action = Cvis.VIS;

  Cvis(String id, int action) {
    this.id = id;
    this.action = action;
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    Object icon = null;
    int newVisibility = Cvis.VIS;
    synchronized (ht) {
      if ( (icon = ht.get(id)) == null) {
        System.err.println("Cvis hashtable: no such id=" + id);
        return;
      }
    }
    if (action == Cvis.VIS) { // move to end opposite of current visibility
      int oldVisibility = ( (AnimatorIcon) icon).visibility();
      if (oldVisibility == Cvis.RAISE) {
        newVisibility = Cvis.LOWER;
      }
      else if (oldVisibility == Cvis.LOWER) {
        newVisibility = Cvis.RAISE;
      }
      else {
        System.err.println("Cvis: oldVisibility=" + oldVisibility
                           + " is incorrect");
      }
    }
    else if (action == Cvis.RAISE) { // move to end of icons
      newVisibility = Cvis.RAISE;
    }
    else if (action == Cvis.LOWER) { // move to beginning of icons
      newVisibility = Cvis.LOWER;
    }
    else {
      System.err.println("Cvis: action=" + action + " not implemented");
    }
    ( (AnimatorIcon) icon).visibility(newVisibility);
    synchronized (icons) {
      if (newVisibility == Cvis.RAISE) {
        if (icons.removeElement(icon)) {
          icons.addElement(icon);
        }
        else {
          System.err.println("Cvis: missing icon=" + icon);
        }
      }
      else if (newVisibility == Cvis.LOWER) {
        if (icons.removeElement(icon)) {
          icons.insertElementAt(icon, 0);
        }
        else {
          System.err.println("Cvis: missing icon=" + icon);
        }
      }
      else {
        System.err.println("Cvis: newVisibility=" + newVisibility
                           + " is incorrect (should not happen)");
      }
    }
  }

  public String toString() {
    return "Cvis: action=" + action;
  }
}

class CswitchPos
    extends AnimatorAction {

  private String id1 = null, id2 = null;

  CswitchPos(String id1, String id2) {
    this.id1 = id1;
    this.id2 = id2;
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    AnimatorIcon icon1 = null, icon2 = null;
    Position p1 = null, p2 = null;
    synchronized (ht) {
      if ( (icon1 = (AnimatorIcon) ht.get(id1)) == null) {
        System.err.println("CswitchPos hashtable: no such id1=" + id1);
        return;
      }
      if ( (icon2 = (AnimatorIcon) ht.get(id2)) == null) {
        System.err.println("CswitchPos hashtable: no such id2=" + id2);
        return;
      }
    }
    synchronized (icons) { // sync on icons since we want both positions
      // changed before allowing drawing again
      synchronized (icon1.position) {
        p1 = icon1.position();
      }
      synchronized (icon2.position) {
        p2 = icon2.position();
      }
      icon1.position(p2.x, p2.y);
      icon2.position(p1.x, p1.y);
    }
  }

  public String toString() {
    return "CswitchPos: id1=" + id1 + ",id2=" + id2;
  }
}

class CswapIds
    extends AnimatorAction {

  private String id1 = null;
  private String id2 = null;

  CswapIds(String id1, String id2) {
    this.id1 = id1;
    this.id2 = id2;
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    AnimatorIcon icon1 = null, icon2 = null;
    synchronized (ht) {
      if ( (icon1 = (AnimatorIcon) ht.get(id1)) == null) {
        System.err.println("CswapIds hashtable: no such id1=" + id1);
        return;
      }
      if ( (icon2 = (AnimatorIcon) ht.get(id2)) == null) {
        System.err.println("CswapIds hashtable: no such id2=" + id2);
        return;
      }
      if (id1.equals(id2)) {
        return;
      }
      if (ht.remove(id1) == null) {
        System.err.println("CswapIds: internal remove error, id1=" + id1);
      }
      if (ht.remove(id2) == null) {
        System.err.println("CswapIds: internal remove error, id2=" + id2);
      }
      if (ht.put(id1, icon2) != null) {
        System.err.println("CswapIds: internal put error, id1=" + id1);
      }
      if (ht.put(id2, icon1) != null) {
        System.err.println("CswapIds: internal put error, id2=" + id2);
      }
    }
  }

  public String toString() {
    return "CswapIds: id1=" + id1 + ",id2=" + id2;
  }
}

class Cend
    extends AnimatorAction {

  Cend() {
    super();
  }

  void perform(AnimatorCanvas ac, Hashtable ht, Vector icons) {
    System.out.println("XtangoAnimator: Push the Close or Quit button");
  }

  public String toString() {
    return "Cend";
  }
}

class Position { // point on canvas (0,0)..(1,1)

  float x = 0;
  float y = 0;

  Position() {
    this(0, 0);
  }

  Position(float x, float y) {
    super();
    this.x = x;
    this.y = y;
  }

  public synchronized String toString() {
    return "xpos=" + x + ", ypos " + y;
  }
}

class Size {

  float w = 0;
  float h = 0;

  Size() {
    this(0, 0);
  }

  Size(float w, float h) {
    super();
    this.w = w;
    this.h = h;
  }

  public synchronized String toString() {
    return ", xsize=" + w + ", ysize=" + h;
  }
}

abstract class AnimatorIcon
    extends AnimatorCommand {
  // an icon can be a command to draw the icon
  protected String id = null;
  final Position position = new Position(); // need to synchronize on this
  protected Color colorval = Color.black;
  protected Color colorvalHALF = Color.gray; // only used in triangles now
  protected int visibility = Cvis.RAISE;

  protected AnimatorIcon(String id, float xpos, float ypos,
                         Color colorval) {
    this.id = id;
    this.position.x = xpos;
    this.position.y = ypos;
    this.colorval = colorval;
    int R = colorval.getRed();
    int G = colorval.getGreen();
    int B = colorval.getBlue();
    float[] HSB = Color.RGBtoHSB(R, G, B, new float[3]);
    this.colorvalHALF =
        /*
         * Half the saturation with same hue (of course) and brightness works fine
         * for red, green, blue, yellow, cyan, magenta, but not so well with pink
         * and not at all for black on Windows 95.  Use only for HALF fillval in
         * triangles for now.
         */
        new Color(Color.HSBtoRGB(HSB[0], 0.5f * HSB[1], HSB[2]));
  }

  synchronized void colorval(Color colorval) {
    this.colorval = colorval;
  }

  synchronized int visibility() {
    return visibility;
  }

  synchronized void visibility(int visibility) {
    this.visibility = visibility;
  }

  synchronized Color colorval() {
    return colorval;
  }

  synchronized Position position() { // return consistent snapshot of
    return new Position(position.x, position.y);
  } // current position

  synchronized void position(float xpos, float ypos) {
    position.x = xpos;
    position.y = ypos;
  }

  synchronized void positionRel(float xdelta, float ydelta) {
    position.x += xdelta;
    position.y += ydelta;
  }

  void add(Hashtable ht, Vector icons) {
    Object old = null;
    synchronized (ht) {
      if (ht.containsKey(id)) {
        System.err.println
            ("CommandThread add: ignoring duplicate id=" + id);
        return;
      }
      old = ht.put(id, this);
    }
    if (old == null) {
      synchronized (icons) {
        icons.addElement(this);
      }
    }
    else {
      System.err.println
          ("CommandThread add: non-null old should not have happened!!");
    }
  }

  // JLS says cannot have both abstract and synchronized (page 157).
  // Funny: Microsoft JVM catches this but Sun's JDK <=1.1.4 does not.
  abstract
  /* synchronized */ void draw(AnimatorCanvas ac, Graphics g);

  public synchronized String toString() {
    return "id=" + id + ", " + position + ", colorval=" + colorval;
  }
}

abstract class AnimatorShape
    extends AnimatorIcon {

  protected static int numHalf = 10;

  protected int fillval = XtangoAnimator.SOLID;

  protected AnimatorShape(String id, float xpos, float ypos,
                          Color colorval, int fillval) {
    super(id, xpos, ypos, colorval);
    this.fillval = fillval;
  }

  synchronized void fillval(int fillval) {
    this.fillval = fillval;
  }

  synchronized int fillval() {
    return fillval;
  }

  abstract
  /* synchronized */ void draw(AnimatorCanvas ac, Graphics g);

  public synchronized String toString() {
    return super.toString() + ", fillval=" + fillval;
  }
}

class Icircle
    extends AnimatorShape {

  protected float radius = 0;

  Icircle(String id, float xpos, float ypos, float radius,
          Color colorval, int fillval) {
    super(id, xpos, ypos, colorval, fillval);
    this.radius = radius;
  }

  synchronized void draw(AnimatorCanvas ac, Graphics g) {
    g.setColor(colorval);
    if (fillval == XtangoAnimator.SOLID) { // translate from center
      g.fillOval( // to upper-left
          ac.scaleX(position.x - radius), ac.scaleY(position.y + radius),
          ac.scaleR(2 * radius), ac.scaleR(2 * radius));
    }
    else if (fillval == XtangoAnimator.OUTLINE) {
      g.drawOval(
          ac.scaleX(position.x - radius), ac.scaleY(position.y + radius),
          ac.scaleR(2 * radius), ac.scaleR(2 * radius));
    }
    else if (fillval == XtangoAnimator.HALF) {
      float inc = radius / (float) numHalf;
      for (int i = 1; i < numHalf; i++) {
        float rad = inc * i;
        g.drawOval(
            ac.scaleX(position.x - rad), ac.scaleY(position.y + rad),
            ac.scaleR(2 * rad), ac.scaleR(2 * rad));
      }
      g.drawOval(
          ac.scaleX(position.x - radius), ac.scaleY(position.y + radius),
          ac.scaleR(2 * radius), ac.scaleR(2 * radius));
    }
    else {
      System.err.println("Icircle.draw(): fillval=" + fillval
                         + " not yet implemented");
    }
  }

  public synchronized String toString() {
    return super.toString() + ", radius=" + radius;
  }
}

class Iline
    extends AnimatorIcon {

  protected Size size = null;
  protected int widthval = XtangoAnimator.THIN;

  Iline(String id, float xpos, float ypos, float xsize, float ysize,
        Color colorval, int widthval) {
    super(id, xpos, ypos, colorval);
    this.size = new Size(xsize, ysize);
    this.widthval = widthval;
  }

  synchronized void draw(AnimatorCanvas ac, Graphics g) {
    g.setColor(colorval);
    if (widthval == XtangoAnimator.THIN) {
      g.drawLine(
          ac.scaleX(position.x), ac.scaleY(position.y),
          ac.scaleX(position.x + size.w), ac.scaleY(position.y + size.h));
    }
    else if (widthval == XtangoAnimator.MEDTHICK) {
      g.drawLine(
          ac.scaleX(position.x), ac.scaleY(position.y),
          ac.scaleX(position.x + size.w), ac.scaleY(position.y + size.h));
      g.drawLine(
          ac.scaleX(position.x) + 1, ac.scaleY(position.y) + 1,
          ac.scaleX(position.x + size.w) + 1,
          ac.scaleY(position.y + size.h) + 1);
      g.drawLine(
          ac.scaleX(position.x) + 1, ac.scaleY(position.y) - 1,
          ac.scaleX(position.x + size.w) + 1,
          ac.scaleY(position.y + size.h) - 1);
      g.drawLine(
          ac.scaleX(position.x) - 1, ac.scaleY(position.y) - 1,
          ac.scaleX(position.x + size.w) - 1,
          ac.scaleY(position.y + size.h) - 1);
      g.drawLine(
          ac.scaleX(position.x) - 1, ac.scaleY(position.y) + 1,
          ac.scaleX(position.x + size.w) - 1,
          ac.scaleY(position.y + size.h) + 1);
    }
    else if (widthval == XtangoAnimator.THICK) {
      g.drawLine(
          ac.scaleX(position.x), ac.scaleY(position.y),
          ac.scaleX(position.x + size.w), ac.scaleY(position.y + size.h));
      g.drawLine(
          ac.scaleX(position.x) + 1, ac.scaleY(position.y) + 1,
          ac.scaleX(position.x + size.w) + 1,
          ac.scaleY(position.y + size.h) + 1);
      g.drawLine(
          ac.scaleX(position.x) + 1, ac.scaleY(position.y) - 1,
          ac.scaleX(position.x + size.w) + 1,
          ac.scaleY(position.y + size.h) - 1);
      g.drawLine(
          ac.scaleX(position.x) - 1, ac.scaleY(position.y) - 1,
          ac.scaleX(position.x + size.w) - 1,
          ac.scaleY(position.y + size.h) - 1);
      g.drawLine(
          ac.scaleX(position.x) - 1, ac.scaleY(position.y) + 1,
          ac.scaleX(position.x + size.w) - 1,
          ac.scaleY(position.y + size.h) + 1);
      g.drawLine(
          ac.scaleX(position.x) + 2, ac.scaleY(position.y) + 2,
          ac.scaleX(position.x + size.w) + 2,
          ac.scaleY(position.y + size.h) + 2);
      g.drawLine(
          ac.scaleX(position.x) + 2, ac.scaleY(position.y) - 2,
          ac.scaleX(position.x + size.w) + 2,
          ac.scaleY(position.y + size.h) - 2);
      g.drawLine(
          ac.scaleX(position.x) - 2, ac.scaleY(position.y) - 2,
          ac.scaleX(position.x + size.w) - 2,
          ac.scaleY(position.y + size.h) - 2);
      g.drawLine(
          ac.scaleX(position.x) - 2, ac.scaleY(position.y) + 2,
          ac.scaleX(position.x + size.w) - 2,
          ac.scaleY(position.y + size.h) + 2);
    }
    else {
      System.err.println("Iline.draw(): widthval=" + widthval
                         + " not yet implemented");
    }
  }

  public synchronized String toString() {
    return super.toString() + size + ", widthval=" + widthval;
  }
}

class Irectangle
    extends AnimatorShape {

  protected Size size = null;

  Irectangle(String id, float xpos, float ypos, float xsize, float ysize,
             Color colorval, int fillval) {
    super(id, xpos, ypos, colorval, fillval);
    this.size = new Size(xsize, ysize);
  }

  synchronized void draw(AnimatorCanvas ac, Graphics g) {
    g.setColor(colorval);
    if (fillval == XtangoAnimator.SOLID) { // translate from lower-left
      g.fillRect( // corner to upper-left
          ac.scaleX(position.x), ac.scaleY(position.y + size.h),
          ac.scaleR(size.w), ac.scaleR(size.h));
    }
    else if (fillval == XtangoAnimator.HALF) {
      float incw = size.w / (float) numHalf;
      float inch = size.h / (float) numHalf;
      for (int i = 1; i < numHalf; i++) {
        float sizew = incw * i;
        float sizeh = inch * i;
        g.drawRect(
            ac.scaleX(position.x), ac.scaleY(position.y + sizeh),
            ac.scaleR(sizew), ac.scaleR(sizeh));
      }
      g.drawRect(
          ac.scaleX(position.x), ac.scaleY(position.y + size.h),
          ac.scaleR(size.w), ac.scaleR(size.h));
    }
    else if (fillval == XtangoAnimator.OUTLINE) {
      g.drawRect(
          ac.scaleX(position.x), ac.scaleY(position.y + size.h),
          ac.scaleR(size.w), ac.scaleR(size.h));
    }
    else {
      System.err.println("Irectangle.draw(): fillval=" + fillval
                         + " not yet implemented");
    }
  }

  public synchronized String toString() {
    return super.toString() + size;
  }
}

class Itriangle
    extends AnimatorShape {

  private Position p2 = null, p3 = null;

  Itriangle(String id, float v1x, float v1y, float v2x, float v2y,
            float v3x, float v3y, Color colorval, int fillval) {
    super(id, v1x, v1y, colorval, fillval);
    this.p2 = new Position(v2x, v2y);
    this.p3 = new Position(v3x, v3y);
  }

  synchronized void position(float xpos, float ypos) { // override
    // update p2, p3
    float xdelta = xpos - position.x;
    float ydelta = ypos - position.y;
    p2.x += xdelta;
    p2.y += ydelta;
    p3.x += xdelta;
    p3.y += ydelta;
    position.x = xpos;
    position.y = ypos;
  }

  synchronized void positionRel(float xdelta, float ydelta) { // override
    position.x += xdelta;
    position.y += ydelta;
    p2.x += xdelta;
    p2.y += ydelta;
    p3.x += xdelta;
    p3.y += ydelta;
  }

  synchronized void draw(AnimatorCanvas ac, Graphics g) {
    /*
     * For triangles, in contrast to circles and rectangles where concentric
     * outlines are done for HALF, we will try half the saturation.
     */
    if (fillval == XtangoAnimator.HALF) {
      g.setColor(colorvalHALF);
    }
    else {
      g.setColor(colorval);
    }
    int[] X = {
        ac.scaleX(position.x), ac.scaleX(p2.x), ac.scaleX(p3.x),
        ac.scaleX(position.x)};
    int[] Y = {
        ac.scaleY(position.y), ac.scaleY(p2.y), ac.scaleY(p3.y),
        ac.scaleY(position.y)};
    if (fillval == XtangoAnimator.SOLID) {
      g.fillPolygon(X, Y, 4);
    }
    else if (fillval == XtangoAnimator.HALF) {
      g.fillPolygon(X, Y, 4);
    }
    else if (fillval == XtangoAnimator.OUTLINE) {
      g.drawPolygon(X, Y, 4);
    }
    else {
      System.err.println("Irectangle.draw(): fillval=" + fillval
                         + " not yet implemented");
    }
  }

  public synchronized String toString() {
    return super.toString() + p2 + p3;
  }
}

class Itext
    extends AnimatorIcon {

  public static final int SMALL = 0;
  public static final int NORMAL = 1;
  public static final int BIG = 2;

  protected static final Font fSMALL =
      new Font("TimesRoman", Font.PLAIN, 10);
  protected static final Font fNORMAL =
      new Font("TimesRoman", Font.PLAIN, 16);
  protected static final Font fBIG =
      new Font("TimesRoman", Font.PLAIN, 22);

  protected boolean centered = false;
  protected String string = null;
  protected int size = Itext.NORMAL;

  Itext(String id, float xpos, float ypos, boolean centered,
        Color colorval, String string, int size) {
    super(id, xpos, ypos, colorval);
    this.centered = centered;
    this.string = string;
    this.size = size;
  }

  synchronized void string(String string) {
    this.string = string;
  }

  synchronized String string() {
    return string;
  }

  synchronized void draw(AnimatorCanvas ac, Graphics g) {
    g.setColor(colorval);
    if (size == Itext.SMALL) {
      g.setFont(fSMALL);
    }
    else if (size == Itext.NORMAL) {
      g.setFont(fNORMAL);
    }
    else if (size == Itext.BIG) {
      g.setFont(fBIG);
    }
    else {
      System.err.println("Itext draw(): no size=" + size);
    }
    if (centered) {
      FontMetrics fm = g.getFontMetrics();
      int length = fm.stringWidth(string);
      int height = fm.getAscent() - fm.getDescent();
      g.drawString(string, ac.scaleX(position.x) - length / 2,
                   ac.scaleY(position.y) + height / 2);
    }
    else {
      g.drawString(string, ac.scaleX(position.x), ac.scaleY(position.y));
    }
  }

  public synchronized String toString() {
    return super.toString() + ", centered=" + centered + ", string=" + string
        + ", size=" + size;
  }
}
