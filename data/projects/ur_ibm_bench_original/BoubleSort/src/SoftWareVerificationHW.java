// Submitted By Alex Balan : 015328479
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 11/06/2003
 * Time: 13:02:56
 * To change this template use Options | File Templates.
 */
public class SoftWareVerificationHW
{
    // static public int [] array = {3,5,9,2,0,4,-6,-3,13,10,-1,1,6,7,-5,17,15,-12,-9,11,8,-8,18,-18,-15}; // the array to be sorted
      static public int [] array = {3,5,9,2,0,4,-6,-3}; // the array to be sorted
    // do not put the same number in the array more then once- it may cause a bug in the report.
    static public String bsOutput = "SortedArray.txt"; // output file name of the BubbleSort
    static public String rOutput = "Output.txt"; //Report output path, initiated with default value
    static final String  hi_concurency = "lot";// maximal concurency
    static final String mid_concurency = "average";// mediuml concurency
    static final String low_concurency = "little";// small concurency
    static public String concurency; // concurency of threads in bubble sort
    static final int hi_sync = 2;// hi synchronization level
    static final int mid_sync = 1; // medium synchronization level
    static final int low_sync = 0; // low level of sync
    static public int sync; // holds the synchronization level which is oppositte to concurency

    // Sorting the array and printing the result to 'bsOutput'
    static public void main(String args[])
    {
        // getting concurency
        if(args.length > 1) // 2nd argument to main should be the concurency
        {
            concurency = args[1]; // getting the concurency - 2nd parameter
            if(concurency.equals(low_concurency))
                    sync = hi_sync;
            else if(concurency.equals(mid_concurency))
                sync = mid_sync;
            else // in case of hi concurency or need for default value
            {
                sync = low_sync; // setting default value
                if(!concurency.equals(low_concurency)) // in case no legal value was entered
                    System.out.println("Concurency in 2nd parameter must be one of the following: \n"
                            + "Low concurency: '" + low_concurency + "' , Mid concurency: '" + mid_concurency +
                            "' , or High concurency, which is also the default value: '" + hi_concurency + "'. \n" +
                            "The program runs with the default value");
            }
        }
        else // in case 2nd parameter was not set
           sync = low_sync;// setting default value
        // Running the Bubble Sort Program
        try
        {
            // creating a BubbleSort object with level of synchronization, oposite to concurency
            BubbleSort bs = new BubbleSort(array, new File(bsOutput),sync);
            bs.Sort();// sorting the array with Bubble sort Algorithem
            bs.PrintArray();// printing the sorted array
        }
        catch(IOException e)
        {
            return;
        }

        // Checking and reporting results
        try
        {
            if(args.length > 0) // in case the program recieves 1st parameter
                rOutput = args[0];// this parameter exchanges the default output path
            Reporter.GetInstance(bsOutput, array.length).Report(rOutput, array);// creatinr the report
        }
        catch( IOException e)
        { }
    }
}
