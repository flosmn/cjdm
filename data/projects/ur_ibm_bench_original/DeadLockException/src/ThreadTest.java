/**
 * @author Asaf Betman ID 034087122 & Dror Helper ID 034354712
 *
 * This program demonstrates a multi-thread bug:
 * Each thread is doing some work using thread->run
 * Only MAXTHREADS number of threads is allowed to run in the same time
 *
 * Before starting to work the thread:
 * 1) Check if number of running threads in lower then max working threads allowed
 * 2) Increase the "working threads" counter
 *
 * After finishing work the thread decrease the counter
 *
 *
 * BUG: In case of exiting by exception the thread doesn't decrease the counter
 * and less thread are able to run
 * this may even cause a deadlock
 * in case MAXWORKERS threads exited by exception.
 *
 *
 * to change the probability of an exception use FACTOR value
 * the higher the factor the less probability that we'll receive '0' for the division
 * and get and exception.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/** Main class */
public class ThreadTest {
    // Global Vriables
    private final static String DEFAULT_FILE_NAME = "result.txt";
    private final static int MAX_THREADS = 50;    // max number of threads
    private final static int WORKERS_ALLOWED = 10;     // number of threeads that can run in the same time
    private final static int LOW_FACTOR = 1000000;
    private final static int MED_FACTOR = 350;                // multiply factor
    private final static int HIGH_FACTOR = 20;
    private final static int ITERATE = 10;          // number of "work" iterations

    // number of threads currently running
    private Counter numWorkers;

    private Counter deadThreads; //number of threads that exited by exception (Error)


    private int factor;
    private String filename;

    public ThreadTest(String[] args) {
        filename = (args.length == 0 ? DEFAULT_FILE_NAME : args[0]);
        factor = (args.length < 2 ? MED_FACTOR : parseFactor(args[1]));
        System.out.println(factor);
    }

    private static int parseFactor(String strFactor) {
        if (strFactor.equals("little")) {
            return LOW_FACTOR;
        }

        if (strFactor.equals("lot")) {
            return HIGH_FACTOR;
        }

        return MED_FACTOR;
    }

    /**  return number of running workers */
    private int getWorkers() {
        synchronized (numWorkers) {
            return numWorkers.getCount();
        }
    }

    /** start threads "work" cycle*/
    private int work() {
        numWorkers = new Counter();
        deadThreads = new Counter();
        workerThread[] threadArray = new workerThread[MAX_THREADS];

        // creating threads
        // MAX_THREADS should be much more bigger than MAXWORKERS
        for (int i = 0; i < MAX_THREADS; ++i) {
            threadArray[i] = new workerThread(i);
            threadArray[i].start();
        }

        // wait for all the threads to finish working
        for (int i = 0; i < MAX_THREADS; ++i) {

            try {
                threadArray[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

// return the number of threads which didn't exit properly

        try {
            writeResults();
        } catch (IOException e) {
            System.out.println("Error writing file <" + filename + ">\n e.getMessage()");
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
        return getWorkers();
    }

    /**
     * svae results to file
     */
    private void writeResults() throws IOException {
        FileWriter writer = new FileWriter(filename);
        PrintWriter printer = new PrintWriter(writer);

        int results = getWorkers();
        printer.print("ThreadTest, ");

        if (results >= WORKERS_ALLOWED) {
            printer.print("DEADLOCK");
        } else {
            printer.print(results);
        }

        if (results > 0) {
            printer.println(", BLOCKING_CRITICAL_SECTION");
        } else {
            printer.println(", none");
        }

        printer.close();
    }

    /** class to keep track of running instances */
    class Counter {
        private int count = 0;

        public void inc() {
            ++count;
        }

        public void dec() {
            --count;
        }

        public int getCount() {
            return count;
        }
    }


    /** the thread class
     * should only use "run" */
    class workerThread extends Thread {
        private int id;

        /** initilze the thread id for debbuging purposes */
        public workerThread(int _id) {
            id = _id;
        }

        /** init before start of work */
        private void initWork() throws DeadLockException {
            boolean mine = false;
            while (!mine) {
                synchronized (deadThreads) {
                    if (deadThreads.getCount() >= WORKERS_ALLOWED) {
                        throw new DeadLockException();
                    }
                }

                synchronized (numWorkers) {
                    if (numWorkers.getCount() < WORKERS_ALLOWED) {
                        mine = true;
                        numWorkers.inc();
                    }
                }

                if (!mine)
                    try {
                        sleep(1500);
                    } catch (InterruptedException e) {// no harm done continue as usual
                        System.out.println("Erorr: can't sleep " + e.getMessage());
                    }
            }
        }

        /** finilize actions in the end of work */
        private void finalizeWork() {
            synchronized (numWorkers) {
                numWorkers.dec();
            }
        }

        /** the thread main function */
        public void run() {
            System.out.println("-- thread #" + id + " started --");

            try {

                initWork();

                System.out.println("thread #" + id + " working");
                int result;// simulates a needed calculated value

                for (int i = 0; i < ITERATE; ++i) {
                    System.out.print(". ");

                    result = i / (int) (Math.random() * factor);  //might throw exception
                }

                finalizeWork();

                System.out.println("thread #" + id + " exiting . . .");
            } catch (java.lang.ArithmeticException e) {
                synchronized (deadThreads) {
                    deadThreads.inc();
                }
                System.out.println("thread #" + id + " exception exiting . . .");
            } catch (DeadLockException de) {
//nothing to do -- just exit
            }
        }
    }

    public static void main(String[] args) {
        ThreadTest test = new ThreadTest(args);

        /* we know the bug happend
         * if after finish of work the return value is not '0'
         * (number of running threads) */
        System.out.println("Worker threads left: " + test.work());

    }
}
