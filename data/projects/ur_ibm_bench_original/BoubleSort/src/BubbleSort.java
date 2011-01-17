
/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 11/06/2003
 * Time: 13:29:18
 * To change this template use Options | File Templates.
 */

import java.io.*;

/**
 * BubbleSort sorts an array by the Bubble sort algorithem
 */
public class BubbleSort
{
    private int [] arr; // holds the array to be sorted
    private int size; // holds the size of the array above
    private Writer out;// output file of printed array
    private int st; // holds the sleeping time before printing according to level of sync
    private int tst = 0; // threads sleeping time, initiated to 0
    private static Object sort_lock = new Object();// lock object for sorting
    // these synchronisation values represent the inverse values of the concurency values
    // is concurency values in class 'SoftWareVerificationHW' change, these values should change accordingly
    public static final int hi_sync = 2;
    public static final int mid_sync = 1;
    public static final int low_sync = 0;

    /**
     * getting the array to be sorted and it's size
     * @param array  The array to be sorted
     * @param outputFile the output file holding the outcome of the printing method
     * @param sync determines the level of synchronization between the threads,
     * meaning, the bigger sync is, the less concurency is between the threads - lesser chance for bugs
     */
    public BubbleSort(int [] array, File outputFile, int sync) throws IOException
    {
        // filling this instance fields
        size = array.length;
        arr =  array;

        // fields of synchronization level
        st = sync * 10 ; // initializing the local variable holding the synchronization time
        if(sync == hi_sync) // in this case we start synchronising the threads as well as the printing function
        {
            tst = st;// Threads get a value different then 0
            st = st + tst*size; // the printing sleeps as long as all the threads sleep and more
        }

        // getting the output file path and opening the File
        File fullPath = outputFile.getAbsoluteFile();
        try
        {
            out = new OutputStreamWriter (new FileOutputStream(fullPath.toString()));
        }
        catch(IOException e)
        {
            System.out.println("IOException in creating Bubble Sort output file");
            throw e;
        }
    }

    /**
     * Sorting the array by the bubble sort algorithem:
     * Running 'size' times on the array, each time a thread is created that's supposed to bubble up
     * the next biggest number not sorted yet.
     * Since it is unknown which thread may finish 1st, and cause a bug,
     * every thread runs from the biginning to the end of the the array,
     * to correct possible mistakes
     */
    public void Sort()
    {
        synchronized(sort_lock)
        {
            for(int i =0;i < size;++i) // running 'size' times
            {
                OneBubble ob = new OneBubble(arr, tst); // creating a One bubble thread
                ob.start();// bubbling up the next highest number
            }
        }
    }

    // printing the array
    public void PrintArray() throws IOException
    {
        synchronized(sort_lock)
        {
            if(st !=  0) // in case there is some degree of synchronization
            {
                try
                {
                    Thread.sleep(st);
                }
                catch(InterruptedException e)
                {}
            }
            for(int i = 0;i < size;++i)
                out.write(arr[i] +" ");
            out.close();
        }
    }
}
