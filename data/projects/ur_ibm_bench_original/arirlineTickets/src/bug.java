
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: amit rotstein I.D: 037698867
 * Date: Oct 17, 2003
 * Time: 1:02:13 PM
 * To change this template use Options | File Templates.
 */
public  class bug implements Runnable{

    static int  Num_Of_Seats_Sold =0;
    int         Maximum_Capacity, Num_of_tickets_issued;
    boolean     StopSales = false;
    Thread      threadArr[] ;
    FileOutputStream output;

    private String fileName;

    public bug (String fileName, String Concurency){
        this.fileName = fileName;
        try {
            output = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
          if(Concurency.equals("little")) Num_of_tickets_issued = 10;
          if(Concurency.equals("average")) Num_of_tickets_issued = 100;
          if(Concurency.equals( "lot")) Num_of_tickets_issued = 5000;
         Maximum_Capacity = Num_of_tickets_issued - (Num_of_tickets_issued)/10 ; // issuing 10% more tickets for sale
         threadArr = new Thread[Num_of_tickets_issued];

         System.out.println( "The airline issued "+ Num_of_tickets_issued +" tickets for "+Maximum_Capacity+" seats to be sold.");
/**
 * starting the selling of the tickets:
 * "StopSales" indicates to the airline that the max capacity was sold & that they should stop issuing tickets
 */
        for( int i=0; i < Num_of_tickets_issued; i++) {

           threadArr[i] = new Thread (this) ;
/**
 * first the airline is checking to see if it's agents had sold all the seats:
 */
             if( StopSales ){
                 Num_Of_Seats_Sold--;
                 break;
             }
 /**
 * THE BUG : StopSales is updated by the selling posts ( public void run() ), and by the time it is updated
 *           more tickets then are alowed to be are sold by other threads that are still running
 */
            threadArr[i].start();  // "make the sale !!!"
         }
            System.out.println("SOLD "+ Num_Of_Seats_Sold + " Seats !!!");

         try {
             output = new FileOutputStream(fileName);
         } catch (FileNotFoundException e) {
             e.printStackTrace();  //To change body of catch statement use Options | File Templates.
         }
        String str1="< "+fileName+" , Concurency="+Concurency+" , "+"No Bug"+" >";
        String str2="< "+fileName+" , Concurency="+Concurency+" , "+"Interleaving"+" >";

         if (Num_Of_Seats_Sold > Maximum_Capacity)
             try {
                 output.write(str2.getBytes());
             } catch (IOException e) {
                 e.printStackTrace();  //To change body of catch statement use Options | File Templates.
             }
         else
             try {
                 output.write(str1.getBytes());
             } catch (IOException e) {
                 e.printStackTrace();  //To change body of catch statement use Options | File Templates.
             }
     }
/**
 * the selling post:
 * making the sale & checking if limit was reached ( and updating "StopSales" ),
 */
    public void run() {

    Num_Of_Seats_Sold++;                          // making the sale

    if (Num_Of_Seats_Sold > Maximum_Capacity)     // checking
              StopSales = true;                   // updating
    }

    public static void main(String args[]) {
           new bug(args[0],args[1]);
    }
}



