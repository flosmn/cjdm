    //MyListNode.java
    //This class implements basic node stored in a linked list .

    class MyListNode
    {
		/*Class Members*/
		public Object _element;			//Node's data
        public MyListNode _next;		//Pointer to next node
        public int _nTime = 0;			//The  time to sleep

		//C'tor - 1
        MyListNode( Object theElement,int nT ){ this( theElement, null , nT ); }

		//C'tor - 2
        MyListNode( Object theElement, MyListNode n , int nT )
        {
			this._nTime = nT;

			synchronized ( this )
			{
				this._element = theElement;
				this._next = n;
            }

			/*
			//a sleep before the last element can be added to list .
			//it conflicts with the while looop in addLast func in MyLinkedList.java file .
			//the if condition is in order to show the case that no noise is added
			//to this c'tor in which case it is hard to acheive the bug
			if ( this._nTime > 0 )
			{
				try
				{
					Thread.sleep(this._nTime);
				}
				catch(InterruptedException e)
				{
					e.getMessage();
					e.printStackTrace();
				}
			}
			//no else
			////////////////////////////////////////////////
			*/
        }

    }
