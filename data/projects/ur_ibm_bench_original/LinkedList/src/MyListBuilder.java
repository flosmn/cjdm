//MyListBuilder.java
//This class builds a shared list from given threads .

import java.util.*;
import java.io.*;

class MyListBuilder implements Runnable
{
	/*Class Members*/
	public boolean _debug = true;
	public String _fileName = "ID_029646965.txt";
	public Object _list = null;
	public int _bound1 = 0;
	public int _bound2 = 0;

	//C'tor
	public MyListBuilder(Object lst,int bnd1,int bnd2,boolean dbg,String fName)
	{
		this._debug = dbg;

		if ( _debug == true )
			this._list = (MyLinkedList)lst;
		else
			this._list = (LinkedList)lst;

		this._fileName = fName;

		this._bound1 = bnd1;
		this._bound2 = bnd2;
	}

	/*Methods*/

	//The processor
	public void run()
	{
		for ( int i = this._bound1; i < this._bound2 ;i++ )
		{
			/*
			//just a sleep noise to the system
			try
			{
				Thread.sleep(i);
			}
			catch(InterruptedException e)
			{
				e.getMessage();
				e.printStackTrace();
			}
			/////////////////////////////////
			*/

			if ( _debug == true )
				((MyLinkedList)_list).addLast(new Integer(i));
			else
				((LinkedList)_list).addLast(new Integer(i));
		}
	}

	//Prints list elements
	public void print()
	{
		int size;

		if ( _debug == true )
			size = ((MyLinkedList)_list).size();
		else
			size = ((LinkedList)_list).size();

		try
		{
			PrintWriter of = new PrintWriter(new FileWriter(".\\" + _fileName),true);

			of.print("< " + "BugTester Program" + " , ");

			if ( _debug == true )
			{
				of.close();

				((MyLinkedList)this._list).printList((MyLinkedList)_list);

			}
			else
			{
				of.print("list : (->");

				Iterator iter = ((LinkedList)_list).iterator();

				while( iter.hasNext() )
					of.print((Integer)iter.next() + "->");

				of.print(") , ");

				if ( size == 10 )						//theoretical size of list is 10
					of.print("length : "  + size + " , No Bug >");
				else
					of.print("length : "  + size + " , Non-Atomic Bug >");

				of.close();

			}

		}
		catch(IOException e)
		{
			System.out.println("Problems with output file name : " + _fileName);
			e.getMessage();
			e.printStackTrace();
		}

	}


	//Empties list
	public void empty()
	{
		if ( _debug == true )
			((MyLinkedList)_list).clear();
		else
			((LinkedList)_list).clear();
	}

}

