package mergesortbug;

import java.io.*;

public class MergeSortBug extends Thread {

  /**
   * The Integers array to sort
   */
  private static int[] intArray;

  /**
   * A temporary array to use while merging. This array will serve all threads.
   * That way we save unneeded allocations.
   */
  private static int[] tempArray;

  /**
   * Holds the number of threads that can run at that moment.
   * This member is updated each time a thread dies and starts.
   */
  private static int maxThreads;
  private static int lowestNumThreads;

  /**
   * These two variables hold the indexes in the array the current thread
   * should sort;
   */
  private int startIndex;
  private int endIndex;

  /**
   * Sorts the section in the array between two sections
   * The method uses the current instance's indexes and calls a static
   * MergeSections method
   */
  public static void sortArray(int sIndex, int eIndex) throws
      InterruptedException {

    // If we're dealing with one element, return to caller
    if (eIndex - sIndex == 0) {
      return;
    }

    // Sort recursively
    int median = (eIndex + sIndex) / 2;

    MergeSortBug mergeA = new MergeSortBug(sIndex, median);
    if (maxThreads > 0) {
      mergeA.start();
    }
    else {
      sortArray(sIndex, median);
    }

    // Current thread continues with "right" part of the array.
    sortArray(median + 1, eIndex);

    mergeA.join();
    try {
      mergeSections(sIndex, median, median + 1, eIndex);
    }
    catch (InvalidInput e) {
      System.out.println("Fatal Error - invalid indexes");
    }
  }

  /**
   * Merge two sections of the array in a sorted fashion.
   * This method is synchornized, so any access to intArray is monitored.
   * @param sInd1 The first start index.
   * @param eInd1 The first end index.
   * @param sInd2 The second start index.
   * @param eInd2 The second end index.
   */
  public static void mergeSections(int sInd1, int eInd1,
                                   int sInd2, int eInd2) throws InvalidInput {

    // Check if the indexes are valid
    if ( (sInd1 > eInd1) || (sInd2 > eInd2) ||
        (sInd1 < 0) || (eInd1 < 0) || (sInd2 < 0) || (eInd2 < 0)) {
      throw new InvalidInput("Invalid indexes supplied");
    }

    /***********************************************/
    /*                 Merge the sections          */
    /***********************************************/

    // Temporary variables to use later when coping the
    // elements from tempArray to intArray
    int tmpSInd = sInd1;
    int tmpEInd = eInd2;
    int i = sInd1;

    // Merge starting from the first index
    while (sInd1 <= eInd1 && sInd2 <= eInd2) {
      if (intArray[sInd1] < intArray[sInd2]) {
        tempArray[i++] = intArray[sInd1++];
      }
      else {
        tempArray[i++] = intArray[sInd2++];
      }
    }

    // Finish the first section
    while (sInd1 <= eInd1) {
      tempArray[i++] = intArray[sInd1++];
    }

    // Finish the second section
    while (sInd2 <= eInd2) {
      tempArray[i++] = intArray[sInd2++];
    }

    // Copy the data to intArray
    while (tmpSInd <= tmpEInd) {
      intArray[tmpSInd] = tempArray[tmpSInd++];
    }
  }

  /**
   * Updates maxThreads and runs sortArray with the proper indexes
   */
  public void run() {
    setMaxThreads(-1);
    try {
      sortArray(startIndex, endIndex);
    }
    catch (InterruptedException e) {
    }
    setMaxThreads(1);
  }

  /**
   * Updates current running threads
   * @param modifier Can be +/- 1.
   */
  public static void setMaxThreads(int modifier) {

    maxThreads += modifier;
    if (maxThreads < lowestNumThreads) {
      lowestNumThreads = maxThreads;
    }
  }

  /**
   * Contructor. Initialize variables for merge sort.
   * @param sIndex The start index this instance begins with.
   * @param eIndex The end index this instance ends in.
   */
  public MergeSortBug(int sIndex, int eIndex) {
    startIndex = sIndex;
    endIndex = eIndex;
  }

  /**
   * Create a String representation of intArray
   * @return The string representing intArray
   */
  public String toString() {
    String array = "";

    for (int i = 0; i < intArray.length; i++) {
      array += intArray[i] + " ";
    }

    return array;
  }

  public MergeSortBug(String[] args) throws InvalidInput {

    // Check if the input is valid
    try {
      if (args.length < 3) {
        // Too few parameters
        throw new InvalidInput("Too few parameters supplied");
      }

      intArray = new int[args.length - 2];
      tempArray = new int[args.length - 2];

      // Deduct 1 from maxThread since there's already the MainThread running
      maxThreads = Integer.parseInt(args[1]) - 1;
      if (maxThreads < 0) {
        throw new InvalidInput("Can't run less then 1 thread");
      }
      for (int i = 2; i < args.length; i++) {
        intArray[i - 2] = Integer.parseInt(args[i]);
      }
    }
    catch (NumberFormatException e) {
      throw new InvalidInput("Invalid input supplied");
    }

    // Check if the number of threads is valid
    if (maxThreads > (intArray.length / 2)) {
      throw new InvalidInput("Invalid # of threads");
    }

    // Set initial indexes
    startIndex = 0;
    endIndex = intArray.length - 1;
  }

  public static void main(String[] args) {

    try {
      int test = Integer.parseInt(args[0]);
      // If here, the first argument is a number and can't be treated
      // as a filename
      System.out.println("First argument must specify file name !!!");
    }
    catch (NumberFormatException ex) {
      // If here then the first argument is a string and thus can be
      // treated as a filename
      try {
        MergeSortBug sort = new MergeSortBug(args);
        // If we reached here, then the input is valid
        try {
          sort.sortArray(0, args.length - 3);
        }
        catch (InterruptedException e) {
          // Do Nothing
        }
        System.out.println(sort);
      }
      catch (InvalidInput e) {
        System.out.println(e);
        return;
      }

      FileWriter output;

      try {
        output = new FileWriter(args[0]);
        output.write("MergeSort Bug, " +
                     "Lowest number of threads available is: " +
                     lowestNumThreads + ", Not-Atomic");
        output.close();
      }
      catch (IOException ex1) {
      }
    }
  }
}
