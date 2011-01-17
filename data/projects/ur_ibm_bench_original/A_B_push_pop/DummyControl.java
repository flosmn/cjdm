/*************************************************************/
/* (C) IBM Corporation (2007), ALL RIGHTS RESERVED 	         */
/*                                                           */
/*	Benny Godlin	30/1/2007	Class created                */
/*************************************************************/
// Note: ConTest only version - not part of CMGTT

/*
  DummyControl 
    use this class to run directly the tested program without the Stopper
    and all other CMGTT components. It is used f.e., to run test programs under ConTest.
*/

public class DummyControl extends ProgLoader {

    public DummyControl() {
        /* empty constructor - for successors */
    }

    public DummyControl(String args[]) throws AnyException {
        super(args);            
    }

    public static void main(String args[]) throws AnyException {       
        new DummyControl(args);
    }
}
