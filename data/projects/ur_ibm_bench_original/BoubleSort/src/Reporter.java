
/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 12/06/2003
 * Time: 12:44:08
 * To change this template use Options | File Templates.
 */

import java.io.*;

/**
 * Checks results and prints report of them
 */
public class Reporter
{
    private static Reporter RInstance = null; // initiating a null instance (singelton)
    private File rFile; // the report file
    private Writer out; //writer to the report output file
    private int [] printedArray; // holds the printed array by the BubbleSort program
    private int arrSize; // holds the size of the sorted array

    // private creator (singelton method), creation is in GEtInstance
    // only one input path - where BubbleSort writes it's output, there for initialized on construction
    private Reporter(File input, int arraySize) throws IOException
    {
        try
        {
            input = input.getAbsoluteFile();
            Reader in = new InputStreamReader(new FileInputStream(input));
            StreamTokenizer st = new StreamTokenizer(in);
            st.parseNumbers();
            arrSize = arraySize;
            printedArray = new int[arrSize];

            // filling in the array
            for(int i = 0;i < arrSize;++i)
            {
                st.nextToken();
                //int temp = (int)st.nval;
                printedArray[i] = (int)st.nval;
            }
        }
        catch(IOException e)
        {
            System.out.println("Error in opening input file: " + input);
            throw e;
        }
    }

    /**
     * Creates Class Reporter once, multiple outputs can be created from 'Roport' method
     * @param input The file containing the input for this class which is the output of the bubble sorting
     * @param arraySize The size of the array sorted
     * @return an instance of this class, happands one in this program
     * @throws IOException
     */
    public static Reporter GetInstance(String input, int arraySize) throws IOException
    {
        if(RInstance == null)
            RInstance = new Reporter(new File(input), arraySize);
        return RInstance;
    }

    /**
     * Checks the legality of output and reports the outcome of the check
     * @param output The file the report is written to
     * @throws IOException
     */
    public void Report(String output, int [] originalArray) throws IOException
    {
        try
        {
            // Creating the output file
            rFile = new File(output);
            rFile = rFile.getAbsoluteFile();
            out = new OutputStreamWriter (new FileOutputStream(rFile));

            // checking and printing the result
            out.write("<BubbleSort Program,");// Printing the name of the program
            for(int i = 0;i < arrSize;++i) // printing the result array
                out.write(printedArray[i] + " ");
            out.write(IndicateBug(originalArray));// writing the bug to the output file
            out.close();
        }
        catch(IOException e)
        {
            System.out.println("IOException in creating Bubble Sort output file");
            throw e;
        }
   }

    private String IndicateBug(int [] originalArray)
    {
        // Checking for numbers appearance, (bugs might appear in this funciton if 'originalArray' contains
        // the same number more then once
        for(int i = 0;i < arrSize;++i)
        {
            int j;
            for(j = 0;j < arrSize;++j)
            {
                if(originalArray[i] == printedArray[j])// in case the number appers
                    break;
            }

            if(j == arrSize) // in case the number in the original array does not exist in the printed array
                return (",(Bug Found: the number " + originalArray[i] + ", exists in the original array," +
                        " but does not exist in the printed array)>");
        }

        for(int i = 0;i < arrSize - 1;++i)
        {
            if(printedArray[i] > printedArray[i + 1])
                return (",(Bug Found: The number at place " + (i + 1) + '(' + printedArray[i] + ')'
            + ", is bigger then the number at place " + (i + 2) + '(' + printedArray[i + 1] + ").)>");
        }
        return ",No bugs found>";
    }
 }
