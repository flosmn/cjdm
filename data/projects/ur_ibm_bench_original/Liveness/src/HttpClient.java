import java.io.*;
import java.lang.*;
import java.util.*;
public class HttpClient extends Thread{

  private final  int maxAllowedUsers = 3;
  private static Counter actualUsers = new Counter(0);
  private String clientName;
  private static Vector suspendedClients = new Vector();

  public HttpClient(String name) {
    clientName = new String(name);
  }

  public static boolean suspendedClientsExists() {
    return (!suspendedClients.isEmpty());
  }

  public static boolean runningClientsExists() {
    if (actualUsers.get() > 0) { return true; }
    return false;
  }


  public void run() {
    boolean accessGranted = false;
    System.out.println("Client " + clientName + " trying to access server for the 1st time");
    while (!accessGranted) {
      synchronized (actualUsers) {
        if (actualUsers.get() < maxAllowedUsers) {
          actualUsers.inc();
          System.out.println("Granting access to " + clientName + ", Currently " + actualUsers.get() + " clients are being served");
          accessGranted = true;
        }
      }
      if (accessGranted) {
        // =====================================
        // Work with Server - Code goes here ...
        // =====================================
        synchronized (actualUsers) {
          actualUsers.dec();
          System.out.println("Work of client " + clientName + " with server done, " + actualUsers.get() + " clients are still using the server");
        }

        synchronized (suspendedClients) {
          if (!suspendedClients.isEmpty()) {
            System.out.println("On termination of Client " + clientName +
                               ", Resuming waiting client");
            ( (HttpClient) suspendedClients.remove(0)).resume();
          }
          else {
            System.out.println("On termination of Client " + clientName +
                               ", no clients are waiting for Resume ");

          }
        }
      } else {
        System.out.println("Client " + clientName + " needs to wait for server...");
          try  {   if (Integer.parseInt(clientName) > 18) { this.sleep (3000) ; } } catch (InterruptedException e) { }
        System.out.println("Client " + clientName + " moving to Suspend mode...");
        synchronized (suspendedClients) {
          suspendedClients.add(this);
        }
        this.suspend();
      }
    }
  }
}