/***************************/
/** Guy David            ***/
/** I.D : 034529602      ***/
/***************************/


(For how to activate the program - see in the end)

This program simulates travel agents booking flight tickets on a plane.
The number of travel agents and tickets can be controled by the user.
Each agents works in a different thread and they all tring to book the tickets simultaneously.
Needless to say that every ticket can be bought only once.
The bug I made in this program is the Double checked Locking bug.
This pattern of double checked locking was invented to avoid a situation of taking a lock
when there is no need, but the pattern doesnt work so well (as you will see).
The thing that can happen, when using this pattern, is that an object is used without its 
full initialization, and that is what I checked and I will show why it can happen
( in this program using the object means checking it with the fuction
check_ticket_details(), this way I could keep track if there was a bug).

I would like to say first, that, this bug is very hard to find, and in most JVM its really hard to make it happen
I would also like to say that I coudnt make it happen on my computer, but I know the bug is there,
I read a lot about it, and it is a known fact that in some JVM it is impossible to make it happen.

The Bug :
The bug is in the double checked locking pattern that I did in the run() function in class
TravelAgent.

A scenario for the bug:

	 public void run(){
	     for( int i = 0; i < seats_num; i++) {
		 if (seats[i].ticket==null) {
		     synchronized(seats[i]){ ---> line 1
			 if (seats[i].ticket==null) { ---> line 2
			     seats[i].ticket=new Ticket(this.name); ---> line 3
			 }
		     }
		 }
		 else {
		    synchronized(seats[i]){
		     check_ticket_details(i);
		    }

		 }
	     }
	 }
Assume 2 threads are tring to create a ticket:

1. Thread 1 enters the run() method.

2. Thread 1 enters the synchronized block at line 1 because instance is null.

3. Thread 1 checks again  at line 2 if the instance is null (it still is) and enters the if block.

4. Thread 1 proceeds to line 3 and makes instance non-null, but before the constructor executes. 

5 .Thread 1 is preempted by thread 2.

6. Thread 2 checks to see if instance is null. Because it is not, 
   thread 2 goes to the else part at line 4 and there uses fully constructed, but partially initialized, object. 

7. Thread 2 is preempted by thread 1.

8. Thread 1 completes the initialization of the ticket object by running its constructor . 

The result is, using an object without fully initializing it.


How to activate?
java TicketsOrderSim <output file full name> <concurrency> [<agents number> <tickets number>]
    output file - in this file will be printed the result ( the tuples ).
    concurrency - little - travel agents - 3 , tickets - 5
                  average - travel agents - 20 , tickets - 30
                  lot - travel agents - 1000 , tickets - 1000
                  user - if you put this option YOU can determine the number of agents and tickets.
                         number of agents first and then number of tickets. 


Dictionary - Possible tuples:

<TicketsOrderSim, All Tickets were sold properly, No Bug Happened> - all Tickets were sold,
 the bug didnt happen.

<TicketsOrderSim, X Tickets were used without being initialized, Double Checked Locking - Partial initialization>
 - The bug happened, X is the number of tickets (objects) were uses without been fully initialized.





    

