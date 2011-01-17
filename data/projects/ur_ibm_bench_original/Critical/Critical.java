public class Critical {
   public int turn;

   public static void main(String[] args){
    Thread t1, t2;

    Critical c = new Critical();
    Section s1 = new Section(c, 0);
    Section s2 = new Section(c, 1);

    t1 = new Thread(s1);
    t1.start();

    t2 = new Thread(s2);
    t2.start();

    try {
      t1.join();
 	 }
 	 catch ( InterruptedException e ) {}

    try {
       t2.join();
 	 }
 	 catch ( InterruptedException e ) {}

   }
}

class Section implements Runnable {

   Critical t;
   int threadNumber;

   public Section(Critical t, int threadNumber)
   {
      this.t = t;
      this.threadNumber = threadNumber;
   }

   public void run() {
     
      if(threadNumber == 0)
      {
       
         t.turn = 0;
         System.out.println("In critical section, thread number = " + threadNumber);
         while(t.turn != 0);
         System.out.println("Out critical section, thread number = " + threadNumber);
         t.turn = 1;
       }
      else
      {
         if(threadNumber == 1)
         {
            t.turn = 1;
            System.out.println("In critical section, thread number = " + threadNumber);
            while(t.turn != 1);
            System.out.println("Out critical section, thread number = " + threadNumber);
            t.turn = 0;          }
         else
         {
            System.err.println("This algorithm only supports two threads");
         }
      }
   }
}






























