

import java.io.*;

class Signal{
int i;
public Signal(){i=0;}
public void set(int n){i=n;}
public int get(){return i;}   
}

public class shop{
	
	 //Data Members
     static Signal S=new Signal();
 	 int costumers_amount=0;
	 private int[] storage=new int[1000] ;
	 private int costumersAmount;
	 int items=-1;
	
	 //Synchronized methods- access shared variables of shop,
	 
	 public synchronized void  getItem(){
	 
	 		storage[items]=0;
	 		items--;	 			  		 			 	
	 	}
	 	
	 public synchronized void  putItem(){
	 	items++;
	 	storage[items]=1;
	 
	 	}
	 public synchronized boolean isEmpty(){
	
	 	boolean empty;
	 	if(items==-1)
	 		empty=true;
	 	else
	 		empty=false;
	 	
	 	return empty;
	 	}
	 	
	public  static void main(String[] args){

		
		
		try{
			
		String file;
		if(args.length>0)
			 file=args[0];
		else
			file="output.txt";
					
		OutputStream oStream = new FileOutputStream (file) ;
 		DataOutputStream out = new DataOutputStream (oStream) ;
		
					
		shop sp=new shop();
		if(args.length>1){
		if(args[1].compareTo("little")==0){
			sp.costumers_amount=2;
			} 
		else if(args[1].compareTo("average")==0){
			sp.costumers_amount=3;
			} 	
		else if(args[1].compareTo("lot")==0){
			sp.costumers_amount=6;
			} }
		else{
				System.out.println("Specify concurrency level:little,average,lot");
				return;
			
			}
		
		/* create supplier (yields after invocation)
	 	 */
	    	
		out.writeBytes("< shop, ");
		supplier s=new supplier(sp,sp.costumers_amount);
		s.start();
		
		
		/*	
		 *	a bug could happen if the sleep 
		 *	terminates before the supplier finishes.
		 *	(see ReadMe file)
		 *
		 */
		try{
			System.out.println("shop sleep:"); 
			Thread.sleep(10);		
						
			}
			
		catch(InterruptedException ie){
			System.out.println("Exception: "+ ie.toString());
			}
			
		System.out.println("shop:"+sp.items); 	
		costumer[] costumers=new costumer[sp.costumers_amount];		
		int i=sp.costumers_amount-1;
	
		/*
		 *	creating costumers
		 */
		
		while(i>=0){			
			costumers[i]=new costumer(10,sp,(i+1),out,S);
			System.out.println("invoking customer "+i);
			costumers[i].start();
			i--;
			}
		System.out.println("items left:  "+sp.items);
		
		int num=0;
		while(num<costumers.length){
			while(costumers[num].isAlive()){}
			num++;
			}
		out.writeBytes(", ");			
		writeBug(S,out);
		out.close();
		
	
		}
		
		
	catch(IOException e)
		{
			System.out.print(e.toString());
			}		
		
	
	}
		
		
	static void  writeBug(Signal S,DataOutputStream out) throws IOException
	{
		String BugOneStr="denail ( init sleep)";
		String BugTwoStr="denail ( init sleep) + weak reality (lock unlock lock)";  
		int i=S.get();
		if(i==2)out.writeBytes(BugTwoStr);
		else if (i==1) out.writeBytes(BugOneStr);
	    else out.writeBytes("no bug");
	    out.writeBytes(" >"); 
	return;
	}	
		
   public synchronized void printCustomer(String S,DataOutputStream out)throws IOException
   {
   	out.writeBytes(S);
   	return;
   }
}
	
