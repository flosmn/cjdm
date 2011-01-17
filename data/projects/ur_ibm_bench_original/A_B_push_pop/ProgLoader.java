/*************************************************************/
/* (C) IBM Corporation (2007), ALL RIGHTS RESERVED 	         */
/*                                                           */
/*	Benny Godlin	30/1/2007	Class created                */
/*************************************************************/
// Note: ConTest only version - not part of CMGTT

import java.util.*;

public class ProgLoader {
    
    class TestedClass {
        String name;
        Class  cls;
        int    howMany;
    }

    protected Vector<TestedClass> testProgConfig;

    protected Vector<Thread> testProg;
    protected Vector<Object> testObjs;

    protected boolean shouldStop = false; // successors may force stop.
    
    // prevent situation when(shouldStop) but join() prevents stopping 
    protected int joinTimeout = 5000;

    protected ProgLoader() {
        /* empty constructor */
    }

    // Does all at once: collects classes, loads objects and runs them
    protected ProgLoader(String args[]) throws AnyException {        
        collectTestProgThreads(args);
        createRunnable();
        startTestProg();
    }   

    protected static String[] splitCommandLine(String command) {
        return command.split("\\s+", 0);
    }

    protected static void printArgs(String args[]) {
        for(int i = 0; i<args.length; ++i)
            System.out.println("args["+i+"] = "+args[i]);
    }

    protected void collectTestProgThreads(String args[]) throws AnyException {
        // Succesors may replace this function to generate test program his way.

        TestedClass tstCls;
        Class cls;
        String fileName;
        int num;

        Names.setBaseDir(System.getProperty("user.dir"));
        
        testProgConfig = new Vector<TestedClass>(args.length);

        for (int i=0; i<args.length; ++i) {
            try {
                num = Integer.parseInt(args[i]);
                i++;
            } catch (NumberFormatException ex) {
                num = 1;
            }

            try {
                cls = Class.forName(args[i]);
            } catch (Throwable ex) {
                String msg = "ProgLoader.collectTestProgThreads: unable to load tested thread class \""+
                    args[i]+"\": "+ex.getMessage();
                System.err.println(msg);
                throw new AnyException(msg, ex);
            }
            
            tstCls = new TestedClass();
            tstCls.name = args[i];
            tstCls.cls = cls;
            tstCls.howMany = num;
            
            testProgConfig.add(tstCls);
        } /* for */

        if (testProgConfig.size() == 0) {
            String msg = "ProgLoader: No test threads where specified !";
            System.err.println(msg);
            throw new AnyException(msg);
        }
    }

    protected void createRunnable() throws AnyException {
        int i,j;
        TestedClass tstCls;
        Object obj;
        Thread trd;
        String threadName;

        int num = testProgConfig.size();

        if (testObjs == null)
            testObjs = new Vector<Object>(num);
        else
            testObjs.clear();

        if (testProg == null)
            testProg = new Vector<Thread>(num);
        else
            testProg.clear();

        for (i=0; i<num; ++i) {
            tstCls = testProgConfig.get(i);

            for(j=0; j<tstCls.howMany; ++j) {

                try {
                    obj = tstCls.cls.newInstance();
                } catch(Exception ex) {
                    String msg = "ProgLoader.createRunnable: unable to create instance of: "+
                        tstCls.name;
                    System.out.println(msg);
                    throw new AnyException(msg, ex);
                }

                testObjs.add(obj);

                // may create objects of the tested program which are not threads:
                if (obj instanceof Thread)
                    trd = (Thread) obj;
                else
                    continue;

                setThreadName(trd, j, tstCls.howMany);

                testProg.add(trd);
            }
        }
    }

	/**Give a name to the newly created thread. 
	 * Successors may replace to give other names to threads. 
     * @param it the thread we want to name
	 * @param index the index of the thread among the threads in its class
     * @param numOfSameClass the total number of threads of the same class as this thread
     * @return the new name
	 */
    protected String setThreadName(Thread it, int index, int numOfSameClass) {
        String threadName;

        if (numOfSameClass > 1)
            threadName = Names.threadName(it, index);
        else
            threadName = Names.threadName(it);

        it.setName(threadName);
        return threadName;
    }
        
    protected void startTestProg() {
        int num = testProg.size();
        for (int i=0; i<num; ++i)
            testProg.get(i).start();
    }

    protected void waitTestProgThreads() {
        // wait to all treads in testProg to finish:
        int i, num = testProg.size();
        int numRunning = num; // at the beginning
        while (numRunning > 0) {
            numRunning = 0;
            for (i=0; i<num; ++i) {
                if (shouldStop)
                    return;

                if (testProg.get(i).isAlive()) {
                    numRunning++;
                    try {
                        testProg.get(i).join(joinTimeout);
                    } 
                    catch (InterruptedException ex) { /* continue */ }
                }
            }
        }
    }
}        

