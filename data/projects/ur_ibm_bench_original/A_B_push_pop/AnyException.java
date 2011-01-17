/*************************************************************/
/* (C) IBM Corporation (2007), ALL RIGHTS RESERVED 	         */
/*                                                           */
/*	Benny Godlin	30/1/2007	Class created                */
/*************************************************************/
// Note: ConTest only version - not part of CMGTT

public class AnyException extends Exception {

    public AnyException(String message) {
        super(message);
    }

    public AnyException(String message, Throwable cause) {
        super(message, cause);
    }

    public void addExceptionToLog() {
        /* maybe later <<==== FIN */
    }
}
