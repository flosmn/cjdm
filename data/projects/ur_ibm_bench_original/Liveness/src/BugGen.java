import java.io.*;
import java.util.*;

// The program will illustrate Liveness - bug in the form of Dormancy -
// The failure of a non-runnable thread to become runnable.

public class BugGen {

  public static void main(String[] args) {
    if (args.length < 1 || args.length > 2) {
      System.out.println("BugGen Usage:\nBugGen [output_file] [control (default 0)]");
      return;
    }

    String outputFileName = new String(args[0]);
    int control = 0; // Program might never end
    if (args.length == 2) {
      System.out.println("Control Mechanism Activated.");
      control = Integer.parseInt(args[1]);
    }

    int threadsNum = 20;

    Control c = new Control((threadsNum * 1500),outputFileName,control);
    c.start();

    HttpClient[] clients = new HttpClient[threadsNum];
    for (int i=0 ; i < threadsNum ; i++) {
      clients[i] = new HttpClient(Integer.toString(i));
      clients[i].start();
    }
  }
}