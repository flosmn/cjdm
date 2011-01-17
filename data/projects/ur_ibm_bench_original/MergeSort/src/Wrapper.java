package MergeSort;
import java.io.*;

//import java.io.Writer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Wrapper {
  private static String fileName;
  private static String conCur;
  public Wrapper() {
  }
  static public void main(String[] args)
  {
    fileName=args[0];
    conCur=args[1];
    String concurrency;
    FileWriter fWriter;
    BufferedWriter bWriter;

    concurrency="4";
    if(conCur.compareTo("little")==0)
      concurrency="4";
    if(conCur.compareTo("average")==0)
      concurrency="6";
    if(conCur.compareTo("lots")==0)
      concurrency="14";

    String[] argv={concurrency,"4", "5", "6", "7", "8", "9" ,"6" ,"54" , "10" ,"54" ,"6" ,"54" ,"87", "5" ,"46" ,"81" ,"54" ,"88" ,"4" ,"5" ,"321" ,"54", "8795", "215" ,"45454","215","123","546","22","456","789","46","456","548","777","856","321","11","784","1234","4569","123","123","545454","7785","7898","77785","785","963","4521"};
    MergeSort MSort = new MergeSort(argv,fileName);
    MSort.ResetThreadCounter();
    MSort.Sorting();
    MSort.PrintResults();

    }

}
