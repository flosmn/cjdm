
/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 11/06/2003
 * Time: 13:40:01
 * To change this template use Options | File Templates.
 */

/**
 * This Class is responsible on one 'bubble' sort, meaning one run through an array
 * in order to bubble up the highest number in the array
 */
public class OneBubble extends Thread
{
    private int st; // holds the sleeping time of this thread in mimseconds
    private int [] arr; // pointer to the array
    private int size; // size of the given array
    private static Object _threadCounterLock = new Object();// lock object

    /**
     * Constructs a thread by pointing to an array of integers and gettin the array's size
     * @param array  The array to be sorted
     * @param sleepingTime determins how many miliseconds this thread should sleep
     */
    public OneBubble(int [] array, int sleepingTime)
    {
        st = sleepingTime;
        arr = array;
        size = arr.length;
    }

    /**
     * The function that does the thread's work,
     * running on the array once and bubbling up the highest number
     */
    public void run()
    {
        if(st != 0) // in this case level of concurency is not maximal
        {
            try
            {
                sleep(st);
            }
            catch (InterruptedException e)
            {}
        }
        // running on the whole array once
        for(int i = 0;i < size - 1;++i)
        {
            if(arr[i] > arr[i + 1])  // in case higher number is benieth lower number
                SwapConsecutives(i);// bubbling up the higher number
        }
    }

    // swapping the i-th number with the i + 1 number in this instance array
    private void SwapConsecutives(int i)
    {
        // the approach to the data is synchronized
        synchronized(_threadCounterLock)
        {
            int temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
        }
    }
}
