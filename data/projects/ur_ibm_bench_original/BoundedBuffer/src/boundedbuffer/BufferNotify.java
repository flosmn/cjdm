package boundedbuffer;

import boundedbuffer.Buffer;
import java.io.*;

/**
 * <p><h1><u>Bounded Buffer </p>
 * <p>this program run a lot of exciting bugs </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Haifa University</p>
 * @author
 * Tomer Sharon, Id: 038555819
 * Ofir Germansky, Id: 031594427
 * @version 1.0
 */
public class BufferNotify {
    /** time out in seconds    */
    static final int _timeOutSec=3;
    /** item produced by each Producer thread*/
    static int _ITEMS_PRODUCED=10;
    /** each thread that _finish raise the _finish counter*/
    static private int _finish=0;
    /** number of producer in a little concurrency run  **/
    static private int _littleP = 2;
    /** number of consumers in a little concurrency run  **/
    static private int _littleC = 1;
    /** number of producer in an average concurrency run  **/
    static private int _averageP = 16;
    /** number of consumers in an average concurrency run  **/
    static private int _averageC = 8;
    /** number of producer in a lot concurrency run  **/
    static private int _lotP = 32;
    /** number of consumers in a lot concurrency run  **/
    static private int _lotC = 16;
    /** number of producer in a default run  **/
    static private final int _defaultP = _littleP;
    /** number of consumers in a default  run  **/
    static private final int _defaultC = _littleC;
    /** the default concurrency (1=little, 2=average, 3=lot) **/
    static private final int _defaultCon = 1;
    /** the priority of the producers **/
    static private int _prodPriority = Thread.MAX_PRIORITY;
    /** the priority of the consumers **/
    static private int _consPriority = Thread.MIN_PRIORITY;
    /** the output message String **/
    static private String _tuple;
    /** creates new shared buffer **/
    static private Buffer _myBuffer= new Buffer();

    /*----------------------------------------------------------------*/
    /*                 Producer & Consumer thread class               */
    /*----------------------------------------------------------------*/
    static public class PCThreads{
        int concurrency; // level of concurrency (0=little, 1=average, 2=lot)
        Thread[] threadProducer; // the producers threads
        Thread[] threadConsumer; // the consumers threads
        int numOfProducers; // number of producers
        int numOfConsumers; // number of consumers

        /**
         * creates the array of threads according to the concurrency.
         * sets the priorities of the consumers threads and producers threads
         * finally its set the number of producers and consumers threads
         * @param concurrency the level of concurrency from 1 to 3
         * @param _myBuffer the shared buffer
         */
        PCThreads(int concurrency, Buffer _myBuffer){
            switch(concurrency){
                case 1: // little concurrency initialization
                    numOfProducers=_littleP;
                    numOfConsumers=_littleC;
                    break;
                case 2: // average concurrency initialization
                    numOfProducers=_averageP;
                    numOfConsumers=_averageC;
                    break;
                case 3: // lot concurrency initialization
                    numOfProducers=_lotP;
                    numOfConsumers=_lotC;
                    break;
            }
            threadProducer = new Thread[numOfProducers];
            threadConsumer = new Thread[numOfConsumers];
            // creates the threads and set thread priority
            for (int i = 0; i < numOfProducers; i++) {
                threadProducer[i] = new Thread(new Producer(_myBuffer,
                    "P" + String.valueOf(i)));
                threadProducer[i].setPriority(_prodPriority);
            }
            for (int i = 0; i < numOfConsumers; i++) {
                threadConsumer[i] = new Thread(new Consumer(_myBuffer,
                    "C" + String.valueOf(i)));
                threadConsumer[i].setPriority(_consPriority);
            }
        }

        /** start the running of the threads (consumer follows by producer) **/
        void startPCT(){
            int i=0;
            int j=0;
            System.out.print("Number of Producers: "+numOfProducers+
                              ", Number of Consumers "+numOfConsumers+", ");
            _myBuffer.PrintStatus();
            System.out.println("Producers priority = "+_prodPriority+
                             ", Consumers priority = "+_consPriority);
            System.out.println("Starting the program :");
            System.out.println("----------------------");
            while (i<numOfProducers || j<numOfConsumers){
                if (i<numOfProducers) {
                    System.out.println("Starting producer "+i);
                    threadProducer[i].start();
                }
                if (j<numOfConsumers) {
                    threadConsumer[j].start();
                    System.out.println("Starting consumer "+i);
                }
                i++;
                if (i<numOfProducers) {
                    threadProducer[i].start();
                    System.out.println("Starting producer " +i);
                }
                i++;
                j++;
            }
        }
    }

    /*----------------------------------------------------------------*/
    /*                                Main                            */
    /*----------------------------------------------------------------*/

    /**
     * creats the Producer and Consumers threads, gives them their priorities
     * based on the args[1] input paramater. start each thread and update the
     * output file
     * @param args
     * args[0] = the name of the output file <br>
     * args[1] = level of bugs appereance (1-low, 2-medium, 3-high 4-manual)
     * args[2] = (Optional) set whether to print every step of the program
     *           (true) or just the result (false)
     * args[3] = (Optional) set the number of items each Producer produces
     * args[4] = (Optional) set the capacity of the buffer
     * args[5] = (Optional) if manual in used set the number of Producers
     * args[6] = (Optional) producer priority
     * args[7] = (Optional) consumer priority
     */
    public static void main(String[] args) {
        // Sets the parameters
        if (args.length<1) throw new RuntimeException
            ("not enough parameters, need a text file to save results");
        int concurrency = 0; //level of concurrency (0=little, 1=average, 2=lot)
        // set the concurrency occording to the input args[1]
        if (args.length>1) concurrency=setCon(args[1]);
        else concurrency=_defaultCon;
        // Optional parameters:
        if (args.length>2) _myBuffer.setConsoleOut(args[2]);
        if (args.length>3) setItemProduce(args[3]);
        if (args.length>4) _myBuffer.setCapacity(Integer.parseInt(args[4]));
        if (args.length>5) _prodPriority=setPriority(Integer.parseInt(args[5]));
        if (args.length>6) _consPriority=setPriority(Integer.parseInt(args[6]));
        if (concurrency==4){
            if (args.length!=8) throw new RuntimeException
             ("parameters six missing, number of producers expected <2..1000>");
            concurrency=setProducersNum(args[7]);
        }
        else printConMessage(concurrency);

        // creates the consumer and producer threads
        PCThreads PCT = new PCThreads (concurrency,_myBuffer);
        // the total number of threads
        int numOfThreads=PCT.numOfConsumers+PCT.numOfProducers;
        // timeout loop method do detect the bug, result is written to the
        // _tuple String which is the output message to the file
        // start the threads work
        PCT.startPCT();
        boolean quitMe= WaitForBug(numOfThreads);
        // the results
        System.out.println(_tuple);
        Output(args[0],_tuple);
        if (quitMe) { // need to exit the program due to the bug
            System.out.println("program found a bug, exiting");
            System.exit(1);
        }
        System.exit(1);
    }

    /*-----------------------------------------------------------------*/
    /*                        Class Set Methods                        */
    /*-----------------------------------------------------------------*/

    /**
     * set the number of Producers (and Consumers = producers/2)
     *  override the default values
     * @param producersNum  the number of producers
     * @return value between 1 and 3 represents the default overided value
     */
    static int setProducersNum(String producersNum){
        int num = Integer.parseInt(producersNum);
        if ((num % 2) !=0) throw new RuntimeException
           ("Number of Producers must be even, beacuse the #Consumers is half");
        if ( num<2 || num >1000) throw new RuntimeException
            ("Invalid number of Producers, must be between 2 to 1000");
        if (_defaultCon==1){
            _littleP=num;
            _littleC=num/2;
        }
        else if (_defaultCon==2){
            _averageP=num;
            _averageC=num/2;
        }
        else if (_defaultCon==3){
            _lotP=num;
            _lotC=num/2;
        }
        System.out.println("Using manual number of threads configuration.");
        return _defaultCon;
    }

    /**
     * set the number of items each Producer produces according to the input
     * @param itemsNum the number of items each Producer produce
     */
    static void setItemProduce(String itemsNum) {
        int items=Integer.parseInt(itemsNum);
        if (items<0 || items>1000) throw new
            RuntimeException(
                "Illigal forth paramter on command line, use <1..1000>");
        _ITEMS_PRODUCED=items;
        System.out.println("number of item each Producers produced change to: "
                           +_ITEMS_PRODUCED);
    }

    /**
     * return the integer number of cuncurrency from the input
     * @param input gets the input concurrency level from the command line
     * @return the value represent the concurrency  from 1 to 3)
     */
    static int setCon(String input){
        if (input.equals("little")||input.equals("1")) return 1;
        if (input.equals("average")||input.equals("2"))return 2;
        if (input.equals("lot") || input.equals("3"))  return 3;
        if (input.equals("manual") || input.equals("4")) return 4;
        throw new RuntimeException(
         "Illigal second paramter on command line, use : <little,average,lot>");
    }

    static int setPriority(int prior) {
        if (prior==1) return Thread.MIN_PRIORITY;
        if (prior==2) return Thread.NORM_PRIORITY;
        if (prior==3) return Thread.MAX_PRIORITY;
        throw new RuntimeException
            ("illigal sixth or seventh paramater: "+prior+", use : 1, 2 or 3");
    }


    /*-----------------------------------------------------------------*/
    /*                              CP Threads                         */
    /*-----------------------------------------------------------------*/

    /** The Producer thread  */
    static class Producer implements Runnable{
        private Buffer buffer;
        private String name;

        public Producer(Buffer _myBuffer,String n){ buffer=_myBuffer; name=n;}

        public void run(){
            try {
                for (int i=0;i<_ITEMS_PRODUCED;i++){
                    buffer.enq(name+"["+String.valueOf(i)+"]");
                }
            }catch (InterruptedException ex) {
            }finally{
                synchronized(this) { _finish++;} // _finish is a common resource
            }
        }
    }

    /** The Consumer thread  */
    static class Consumer implements Runnable{
        private Buffer buffer;
        private String name;
        public Consumer(Buffer _myBuffer, String n) {buffer=_myBuffer; name=n;}

        public void run(){
            try {
                for (int i=0;i<_ITEMS_PRODUCED*2;i++){
                    buffer.deq(name);
                }
            }catch (InterruptedException ex) {
                System.err.println(ex);
            }finally{
                synchronized(this) { _finish++;} // _finish is a common resource
            }
        }
    }

    /*-----------------------------------------------------------------*/
    /*                           Helper Methods                        */
    /*-----------------------------------------------------------------*/

    /**
     * every msec*100 milli second the active is reset (false), if in the next
     * msec*100 milli seconds, 'active' wouldn't set to true and the number of
     * threads finish not equal to the total number of threads then the program
     * is stack. ('active' is set (true) every time an access to the buffer
     * happends.)
     * @param numOfThreads overall number of threads
     * @return
     *    true - if bug happend then we need to explicitly end the program
     *    false - no bug had happend the program will implicitly ends
     */
    static boolean WaitForBug(int numOfThreads){
        boolean quitMe=false;
        int msec=30;
        while (_myBuffer.isActive()){ // 'active' is set if recently changed
            try {
                _myBuffer.resetActive();
                int count=0;
                // wait for program to wait while in the loop of msec times.
                while (_finish!=numOfThreads&&count++!=msec){
                    Thread.sleep(100);
                }
                if (_finish==numOfThreads) break; // if program finish
            }
            catch (InterruptedException ex) {
            }
        }
        // we need to check if the program finish (_finish==numOfThreads) or
        // not. if not then set quitMe to true so the program will explicity
        // quit itself
        if (_finish!=numOfThreads) {
            _tuple="<BoundedBuffer,Threads in infinite loop: "+
                  (numOfThreads-_finish)+" out of: "+numOfThreads+
                  ", notify instead notifyAll>";
            quitMe=true;
        }
        else{
            _tuple="<BoundedBuffer,Programs succesfuly excecuted,none>";
        }
        return quitMe;
  }

    /**
     * print the preview message indicates the level of concurrency
     * @param concurrency the level of concurrency represent as String
     */
    static void printConMessage(int concurrency){
        switch (concurrency){
            case 1 : System.out.println("LOW RISK LEVEL (little concurrency)");
                break;
            case 2 : System.out.println("MEDIUM RISK LEVEL (avg. concurrency)");
                break;
            case 3 : System.out.println("HIGH RISK LEVEL (lot concurrency)");
                break;
        }
    }

    /**
     * create the output file and write the message to it
     * @param filename the name of the output file
     * @param message the message output to the file
     */
    static void Output(String filename, String message){
        FileOutputStream out;
        DataOutputStream data;
        try {
            out = new FileOutputStream(filename);
            data = new DataOutputStream(out);
            data.writeBytes(message);
            data.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("File to open file for writing");
        }
        catch (IOException ex) {
            System.out.println("RuntimeException on writing/closing file");
        }
    }


}