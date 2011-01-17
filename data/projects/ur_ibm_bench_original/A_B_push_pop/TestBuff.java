/*************************************************************/
/* (C) IBM Corporation (2007), ALL RIGHTS RESERVED 	         */
/*                                                           */
/*	Benny Godlin	 6/3/2007	Class created                */
/*************************************************************/


import java.io.*;

public class TestBuff {
    static TestBuff singleton;

    String data[];
    int next, maxFill;
    int underflows, overflows;

    public int bufSize = 36;
    public int numIterations = 10;

    public boolean verbose = false;
    PrintStream out;

    public TestBuff() {
        singleton = this;
        out = System.out;

        data = new String[bufSize];
        init();
    }

    void init() {
        maxFill = next = 0;
        underflows = overflows = 0;
    }

    boolean push(String item) {  // caller must lock !
        if (next >= bufSize) {
            overflows++;
            
            // print extraordinary number of overflows:
            if (verbose && overflows > 0)
                out.println("TestBuff: #overflows reached "+overflows);
            
            return false;
        }
        else {
            data[next] = item;
            next++;
            if (maxFill < next)
                maxFill = next;
            return true;
        }
    }

    String pop() {  // caller must lock !
        String ret = null;

        if (next <= 0) {
            underflows++;
            
            // print extraordinary number of underflows:
            if (verbose && underflows > 0)
                out.println("Error: had "+underflows+" underflows !");
        }
        else {
            next--;
            ret = data[next];
        }
        
        return ret;
    }

    boolean action(String myName) {
        // returns true when managed to push
        boolean ret = false;

        synchronized(this){
        {
            // check top of stack - if it is not myName then push:
            if (next == 0 || !data[next-1].equals(myName))
                ret = push(myName);
            else
                pop();
        }
        }

        return ret;
    }
}
       
    
