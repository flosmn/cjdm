//BugTester.java
//implements two threads that are building the same list
//and are conflicting each other next pointer in the latency between
//fetch and write back

import java.util.*;

public class BugTester
{
	public static void main(String[] args)
	{
		try
		{
			MyListBuilder mlist1;
			MyListBuilder mlist2;

			int lT = 0;			//times to sleep
			int nT = 0;
			if ( args.length >= 1 )
			{
				if ( args.length > 1 && args[1].equals("1") )
				{
					if ( args.length >= 3 )
					{
						lT = Integer.parseInt(args[2]);
						nT = Integer.parseInt(args[3]);
					}
					//no else

					MyLinkedList mlst = new MyLinkedList(lT,nT,args[0]);

					mlist1 = new MyListBuilder(mlst,0,5,true,args[0]);
					mlist2 = new MyListBuilder(mlst,5,10,true,args[0]);
				}
				else		//showing the case in the linked list of java's collection
				{
					LinkedList lst = new LinkedList();

					mlist1 = new MyListBuilder(lst,0,5,false,args[0]);
					mlist2 = new MyListBuilder(lst,5,10,false,args[0]);
				}

				Thread t1 = new Thread(mlist1);
				Thread t2 = new Thread(mlist2);

				t1.start();				//starting the two threads
				t2.start();

				t1.join();				//waiting for all threads to finish
				t2.join();

				mlist1.print();			//prints results to output file

				mlist1.empty();			//empties list
			}
			else
				System.out.println("Name of output file is required as argument!!!");
		}

		catch(InterruptedException e)
		{
			e.getMessage();
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.getMessage();
			e.printStackTrace();
		}

	}


}

