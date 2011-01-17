    //MyLinkedList.java
    //This class implements a linked list class .

    import java.io.*;

    class MyLinkedList
    {
		/*Class Member*/
		public String _fileName = "ID_029646965.txt";
		private MyListNode _header;	//The list head pointer
		private int _lTime = 0;		//The time to sleep

		//C'tor
        public MyLinkedList(int lT,int nT,String fName)
        {
			this._fileName = fName;
			this._lTime = lT;
			this._header = new MyListNode( null,nT );
		}

		/*Methods*/

		//Checks if list is empty
        public boolean isEmpty( ){ return this._header._next == null; }

		//Empties list
        public void clear( ){ this._header._next = null; }

		//Returns first element in list
        public MyLinkedListItr first( )
        {
            return new MyLinkedListItr( this._header._next );
        }

		//Inserts element anywhere in list just after current
        public void insert( Object x, MyLinkedListItr p )
        {
			if( p != null && p._current != null )
				p._current._next = new MyListNode( x, p._current._next , p._current._nTime );
        }

		//Inserts element to the end of list .
		//If this func is synchronized the bug will not apear
        public void addLast( Object x )
		{
			MyListNode itr = this._header;

			/*
			//just a sleep noise to the system
			try
			{
				Thread.sleep(this._lTime);
			}
			catch(InterruptedException e)
			{
				e.getMessage();
				e.printStackTrace();
			}
			/////////////////////////////////
			*/

			while( itr._next != null )
 	        	itr = itr._next;

 	        insert(x,new MyLinkedListItr(itr));
		}

		//Retrieves list size
		public int size()
		{
			MyListNode itr = this._header;
			int i = 0;

			while( itr._next != null )
			{
				i++;
				itr = itr._next;
			}

			return i;
		}

		//Finds 'x' element in list
        public MyLinkedListItr find( Object x )
        {
      		MyListNode itr = this._header._next;

       		while( itr != null && !itr._element.equals( x ) )
	          	itr = itr._next;

      		return new MyLinkedListItr( itr );
        }

		//Finds 'x' previous element in list
        public MyLinkedListItr findPrevious( Object x )
        {
			  MyListNode itr = this._header;

			  while( itr._next != null && !itr._next._element.equals( x ) )
				  itr = itr._next;

			  return new MyLinkedListItr( itr );
        }

		//Removes 'x' element from list
        public void remove( Object x )
        {
            MyLinkedListItr p = findPrevious( x );

            if( p._current._next != null )
                p._current._next = p._current._next._next;  // Bypass deleted node
        }

		//Prints list
        public void printList( MyLinkedList theList ) throws IOException
        {
			PrintWriter of = new PrintWriter(new FileWriter(".\\" + _fileName,true),true);

            if( theList.isEmpty( ) )
                of.println( "Empty list" );
            else
            {
				of.print("list : (->");

                MyLinkedListItr itr = theList.first( );
                for( ; !itr.isPastEnd( ); itr.advance( ) )
                    of.print( (Integer)itr.retrieve( ) + "->" );

                of.print(") , ");
            }

			if ( this.size() == 10 )						//theoretical size of list is 10
				of.print("length : " + this.size() +  " , No Bug >");
			else
				of.print("length : " + this.size() +  " , Non-Atomic Bug >");

			of.close();


        }

    }
