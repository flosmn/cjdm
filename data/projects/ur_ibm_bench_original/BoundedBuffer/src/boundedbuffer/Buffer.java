package boundedbuffer;

/**
 *
 * <p><h1><u>Bounded Buffer</p>
 * <p>Description: the bounded buffer logic</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Haifa University</p>
 * @author
 * Tomer Sharon, Id: 038555819
 * Ofir Germansky, Id: 031594427 *
 * @version 1.0
 */
public class Buffer {

    static private boolean _active=true;


    /** capacity of the buffer*/
    private int _CAPACITY=3;
    /** buffer actual size, the extra slot needed to tell if the buffer full
     or empty*/
    private int _BUFSIZE=_CAPACITY+1;
    /** boolean field which enable/disable the output sream to the console */
    private boolean _consoleOut=true;
    /** _first is the start of the buffer*/
    private int _first;
    /** _last is the end of the buffer, the place which you enq to and deq to */
    private int _last;
    /** the buffer array*/
    private Object[] _bufArr;

    /**the class constructor    */
    public Buffer() {
        _last=_first=0;
        _bufArr=new Object[_BUFSIZE];
    }

    /*----------------------------------------------------------------*/
    /*                       Set & Helper methods                     */
    /*----------------------------------------------------------------*/

    /**
     * set the capacity of the buffer according to the input parameter
     * @param cap the capacity of the buffer
     */
    public void setCapacity(int cap){
        if (cap<0 || cap>1000) throw new
           RuntimeException(
                "illigal fifth parameter, buffer capacity between 1 to 1000");
        _CAPACITY=cap;
        _BUFSIZE=cap+1;
        _bufArr=new Object[_BUFSIZE];
        System.out.println("capacity of the buffer changed to: "+_CAPACITY);
    }

    /** print the status of the buffer **/
    public void PrintStatus() {
        System.out.println("buffer capacity: "+_CAPACITY);
    }

    /**
     * set whether to print every step of the program or just the result
     * @param co boolean indicates wheter to set output or not
     */
    public void setConsoleOut(String co) {
        if (co.equals("true")) _consoleOut=true;
        else if (co.equals("false")) _consoleOut=false;
        else throw new
            RuntimeException(
                "Illigal third paramter on command line, use: <true,false>");
    }

    /** print the buffer from place 0 to the end**/
    public synchronized void printBuffer(){
        System.out.print("_____________________BUFFER[");
        int i=_first;
        int j=0;
        int fin;
        if (_last==_CAPACITY) fin=0;
             else { fin = _last + 1;}
        //System.out.println(_first+" "+_last+" "+fin+"   "+_BUFSIZE);
        if (_first==_last)
            System.out.print("Buffer Empty");
        else do{
            if (_bufArr[i]==null) System.out.print(" N ");
            else System.out.print(" "+_bufArr[i]+" ");
            //System.out.println(i+" "+j);
            i++;
            if (i>_CAPACITY&&i!=fin) i=0;
        }
        while(i!=fin);
        System.out.println("]");
    }

    public void resetActive(){
        _active=false;
    }

    public boolean isActive(){
        return _active;
    }

    /*----------------------------------------------------------------*/
    /*                        Buffer Logic Methods                    */
    /*----------------------------------------------------------------*/

    /**
     * enq new object to the buffer
     * @param newObj the new object insert to the buffer
     * @throws InterruptedException
     */
    public synchronized void enq(Object newObj) throws InterruptedException{
        while ((_last+1)%_BUFSIZE==_first){
            if (_consoleOut) System.out.println(newObj+" waiting");
            this.wait();
        }
        if (_consoleOut) {
            System.out.print("insert "+newObj);
            printBuffer();
        }
        _bufArr[_last] = newObj;
        _active=true; // set the _active flag to true indicating the program
                      // still working
        _last=(_last+1)%_BUFSIZE;
        this.notify();
    }

    /**
     * deq an object from the buffer
     * @param conName the name of the item to be removed
     * @return the object which just had been removed from the buffer
     * @throws InterruptedException
     */
    public synchronized Object deq(String conName) throws InterruptedException{
        while (_first==_last){
            if (_consoleOut) System.out.println(conName+" waiting");
            this.wait();
        }
        Object val = null;
        val = _bufArr[_first];
        _active=true; // set the _active flag to true indicating the program
                      // still working
        if (_consoleOut) {
            System.out.print(conName+" delete "+val);
            printBuffer();
        }
        _first=(_first+1)%_BUFSIZE;
        this.notify();
        return val;
    }


}