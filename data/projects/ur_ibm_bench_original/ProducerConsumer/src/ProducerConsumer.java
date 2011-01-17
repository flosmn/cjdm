// Eddie Nevoani Orphaned Bug Example - December 2003
//This file is ProducerConsumer.java

package ProducerConsumer;

import java.io.*;
import java.lang.String;
import java.util.Vector;

class Server extends ProducerConsumer {
	Client     client;
	int        counter       = 0;
    static int countSERVER   = 0;
	String     msg1          = "\r\n Bug Number";
	String     msg2          = " <ProducerConsumer.java , counter > 30 so Exception is thrown , Orphaned_Bug>";

    private final long start = System.currentTimeMillis();
    private final String EddieSERVERTime(){
        return "" + (System.currentTimeMillis() - start);
    }

	public Server(Client _client) {
		this.client          = _client;
		this.counter         = 0;
	}


	public void run() {
		try {
            this.counter = getCurrCount();
			while (this.counter < 30) {
                if (! (this.client.queue.size() == 30)) {
	                this.client.buffer[countSERVER] = EddieSERVERTime();
	            }
                long currTime = System.currentTimeMillis() - start;
                System.out.println("\r\n PRODUCED["+(countSERVER)+"] :" + this.client.buffer[countSERVER]);
		        System.out.println("At time :" + currTime);
                OutputFile.println("\r\n PRODUCED["+(countSERVER)+"] :" + this.client.buffer[countSERVER]);
                OutputFile.println("At time :" + currTime);
				this.client.queue.addElement(new Integer((int)currTime));
                countSERVER++;
                setCountSERVER();                          // Raise the Server counter by 1 in main function
                setCurrCount(1);                           // Add 1 to current counter
			}
			throw new RuntimeException("Counter >= 30");  // This is guarantied to accur since the counter is increasing always
		}catch (Exception EddieException) {
			this.client.interruptFlag = true;
            try {
                cnt++;
			    OutputFile.println(msg1 + cnt);
                System.out.println(msg1 + cnt);
                OutputFile.println(msg2);
                System.out.println(msg2);
        	    throw new IOException("File Output Error");
		    }catch (IOException e1) {}
		}
	}
}


// ***** END OF Server class *****
//*******************************************************************************************************

class Client extends ProducerConsumer {
	Vector queue;
	boolean interruptFlag;
    String [] buffer       = new String [30];
    static int countCLIENT = 0;
    String    msg3         = "\r\n Bug Number";
    String    msg4         = "\r\n <ProducerConsumer.java , Client thread prints element CONSUMED before SERVER ";
    String    msg5         = " thread prints PRODUCED on that element , RaceFound Exception>";




    private final long start = System.currentTimeMillis();
    private final String EddieCLIENTTime(){
        return "" + (int) (System.currentTimeMillis() - start);
    }

	public Client() {
		this.queue           = new Vector();
		this.interruptFlag   = false;
	}

	public void run() {
		while (! interruptFlag) {
				 buffer[countCLIENT] = EddieCLIENTTime();
                try {
                    processNextElement();
                    if (countCLIENT > getCountSERVER()) {
                        throw new Exception("RaceFound Exception");
                    }
			    }catch (Exception RaceFound){
                    try {
                        try {
                            sleep((int)(Math.random()*100));
                        }catch (InterruptedException eC) {}

                        cnt++;
                        System.out.println(msg3 + cnt);
                        System.out.println(msg4);
                        System.out.println(msg5);
                        OutputFile.println(msg3 + cnt);
                        OutputFile.println(msg4);
                        OutputFile.println(msg5);
                        throw new IOException("File Output Error");
                    }catch (IOException e3) {}
			    }
		}
		// Processes whatever elements remain on the queue before exiting.
		while (! (queue.size() == 0)) {
            try{
                processNextElement();
                if (countCLIENT > getCountSERVER()) {
                    throw new Exception("RaceFound Exception");
                }
            }catch (Exception RaceFound){
                try {
                    cnt++;
                    System.out.println(msg3 + cnt);
                    System.out.println(msg4);
                    System.out.println(msg5);
                    OutputFile.println(msg3 + cnt);
                    OutputFile.println(msg4);
                    OutputFile.println(msg5);
                    throw new IOException("File Output Error");
                }catch (IOException e3) {}
			}
		}
		System.out.flush();
	}

	private void processNextElement() throws Exception{
        long currentTime = System.currentTimeMillis() - start;
        if (! (queue.size() == 0)) {
		    queue.removeElementAt(0);
            System.out.println("\r\n CONSUMED["+(countCLIENT)+"] :" + buffer[countCLIENT]);
		    System.out.println("At time :" + currentTime);
            OutputFile.println("\r\n CONSUMED["+(countCLIENT)+"] :" + buffer[countCLIENT]);
            OutputFile.println("At time :" + currentTime);
        }
        else if (queue.size() == 0) {
            try {
                if (countCLIENT > getCountSERVER()) {
                    throw new Exception("RaceFound Exception");
                }
            }catch (Exception RaceFound){
                try {
                        cnt++;
                        System.out.println(msg3 + cnt);
                        System.out.println(msg4);
                        System.out.println(msg5);
                        OutputFile.println(msg3 + cnt);
                        OutputFile.println(msg4);
                        OutputFile.println(msg5);
                        throw new IOException("File Output Error");
                }catch (IOException e3) {}
			}
        }

        setCurrCount(-1);                               // Subtract 1 from current counter
        countCLIENT++;
	}
}

// ***** END OF Client class *****
//*******************************************************************************************************


public class ProducerConsumer extends Thread {

	public Client clients;
	public Server servers;
	String OutputFileName = "DefaultOutputFile.txt";
    static int CurrCount  = 0;
    static int countSER   = 0;
    static int cnt        = 0;

    int getCurrCount() {
        return CurrCount;
    }

    void setCurrCount(int count) {
        CurrCount = CurrCount + count;
    }

    void setCountSERVER() {
        countSER++;
    }

    int getCountSERVER() {
        return countSER;
    }


   public static PrintWriter OutputFile = null;

	public static void main(String[] args) throws Throwable {
	
		int IterNum;
        IterNum = 3;

        if(args.length > 0) {
			try{
				OutputFile = new PrintWriter(new BufferedWriter(new FileWriter(args[0].toString())));
			}catch (IOException e4){
				System.out.println(e4);
			}
		}
		else if(OutputFile == null) {
			System.out.println("Default: DefaultOutputFile.txt");
			try{
				OutputFile = new PrintWriter(new BufferedWriter(new FileWriter("DefaultOutputFile.txt")));
				throw new IOException("Error While Creating File");
			}catch (IOException e5){
				System.out.println("Error While Creating File");
			}
		}

        if(args.length > 1){
		    if (args[1] != null){
			    if ( ((args[1]).equals("little")) || ((args[1]).equals("LITTLE")) ){
				    IterNum = 3;
                    System.out.println("Concurrency Level is LOW");
                    OutputFile.println("Concurrency Level is LOW");
			    }
			    else if ( ((args[1]).equals("average")) || ((args[1]).equals("AVERAGE")) ){
				    IterNum = 5;
                    System.out.println("Concurrency Level is MEDIUM");
                    OutputFile.println("Concurrency Level is MEDIUM");
			    }
			    else if ( ((args[1]).equals("lot")) || ((args[1]).equals("LOT")) ){
				    IterNum = 20;
                    System.out.println("Concurrency Level is HIGH");
                    OutputFile.println("Concurrency Level is HIGH");
			    }
                else {
	   	            IterNum = 3;
                    System.out.println("Concurrency Level is LOW By Default");
                    OutputFile.println("Concurrency Level is LOW By Default");
                }
            }
        }

        Client [] clnt = new Client[IterNum];
        Server [] serv = new Server[IterNum];

		for(int i = 0; i < IterNum; ++i) { //Loop to fill the array up
            clnt[i]    = new Client();
            serv[i]    = new Server(clnt[i]);
		}

		for(int j = 0; j < IterNum; ++j){
            clnt[j].start();
            serv[j].start();
		}
//        if(OutputFile != null)
//        {
//           try{
				sleep(1000);
	//   }catch (IOException e5){}
           OutputFile.close();
//        }
	}

// ***** END OF main class ***** 
//*******************************************************************************************************

	public ProducerConsumer() {
	}
		public void run() {
			this.servers.start();
		}

}
// ***** END OF ProducerConsumer class *****
//*******************************************************************************************************
