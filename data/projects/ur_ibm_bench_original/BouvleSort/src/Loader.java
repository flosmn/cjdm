//
//  -Class Loader-
//  Create array,start sort process,verify final array
//


package tr;

import tr.NewThread;
import java.lang.Integer;
import java.io.RandomAccessFile;
public class Loader {

   public static void main(String[] args){
     if(args.length<2) {
          System.out.println("wrong input");
          System.exit(1);
     }
     String outputFile=args[0];//output file name
     String conc=args[1]; //concurrency
     int len=10;    //lenght of array
     int prior=3;   //priority of sorting process
     int array[];   //array of integers

     if(conc.equals("low"))
       len=200;
     if(conc.equals("medium"))
       len=1000;
     if(conc.equals("high"))
       len=20000;



     array=new int[len];
     Thread curTh=Thread.currentThread();
     curTh.setPriority(1);
     NewThread.priority=prior;
     int i;

        for(i=0;i<len;i++){
           array[i]=len-i;
        }


     NewThread.array=array;
     NewThread ntr=new NewThread(len-1);
     ntr.start();

    try {
      while(!NewThread.endd) {
         curTh.sleep(2000);
      }
    }
      catch (InterruptedException e){}

    int n_bugs=0;
     for(i=0;i<len-1;i++){
      if(array[i]>array[i+1]) {
           n_bugs++;
        }
     }
     String outString="";
     if(n_bugs==0) {
          outString+="finished with No Bug";
     }
     else{
      outString+="finished with "+n_bugs+" bugs <Initialization-Sleep Pattern>";
     }

     try{
       RandomAccessFile outFile=new RandomAccessFile(outputFile,"rw");//create new file
       outFile.writeBytes("SortProgram "+outString);
     }
     catch (Exception e){
       System.out.println(""+e);
     }

 }
}