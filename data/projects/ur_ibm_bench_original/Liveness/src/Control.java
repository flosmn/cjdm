import java.io.*;
import java.lang.*;
import java.util.*;

public class Control extends Thread {
  private int sleep_time;
  private String outputFile;
  private int active;

  public Control(int time,String filename,int activate) {
    sleep_time = time;
    outputFile = new String(filename);
    active = activate;
  }

  public void run() {
    try {
      DataOutputStream oFile = new DataOutputStream(new BufferedOutputStream(new
          FileOutputStream(outputFile)));
      while (true) {
        try { this.sleep(sleep_time); } catch (InterruptedException e) {}
        if (active == 0) { break; } // bypassing bug-stopping mechanism
        if (HttpClient.runningClientsExists() == false) {
          if (HttpClient.suspendedClientsExists() == false) {
            break;
          } else {
            oFile.writeUTF("BugGen,1,Liveness - Bug (Dormancy)");
            System.out.println("BugGen,1,Liveness - Bug (Dormancy)");
            oFile.close();
            System.exit(1);
          }
        }
      }
      oFile.writeUTF("BugGen,0,none");
      System.out.println("BugGen,0,none");
      oFile.close();
    } catch (IOException e) {
      System.out.println("-E- " + e.toString());
      System.exit(1);
    }
  }
}