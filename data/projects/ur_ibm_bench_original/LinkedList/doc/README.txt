
/***************************************************/
/* Prepared By  	: S.C.                      /
/* Prepared for 	: Software Testing Course   /
/* Classification       : Public                    / 
/* Date Created         : 05/2003                   / 
/***************************************************/


Non-Atomic Bug In A Linked List's Operation
---------------------------------------------

Introduction
---------------

The motivation for this bug comes from the java's collections , which not all of them 

are synchronized , as noted in java's documentation .

In this program i implemented a linked list of mine , in order to emulate java's linked

list , which is one of the non-synchronized collections .

My goal is to show that operation like adding element to list , which is not synchronized ,

may cause a multi-threaded bug .


Where is the bug ?
--------------------

The bug appears when two threads , t1 and t2 in BugTester.java , starts and tries to add

new elements to the same list , mlst in BugTester.java .

While one thread is creating its new node , the other can access the addLast function in

MyLinkedList.java , and find the last element in the current list , the while loop in addLast 

function , in order to connect his new element to it .

What we have now is one thread that is creating an element to connect to the element that the

other thread is going to connect his . So we get an override of the new element of the first 

thread and its new element disappears . At last , if that interleaving happens , we lose elements 

from list and we get a smaller list than the expected one .


Which variables are involved ?
--------------------------------

The _next field in MyListNode class .


Classification
----------------

Non-Atomic Bug - addLast function in MyLinkedList class is not atomic operation .  


Expected Result
-----------------

A full linked list composed of all the elements inserted . It's theoretical length should

be 10 .


How to avoid this bug ?
-------------------------

The simple way to correct this bug is to make addLast function a synchronized one .


Inputs and Results
--------------------

From the command line type :

>c:\java BugTester <outputfile.txt> [1 num1 num2]

- 1 for running my list while 0 for runnint java's collection list .

- num1 and num2 for the sleep time (in msec) - unactive .

You can try any positive combination of this two nums to explore the bug results .

In your outputfile.txt you will find a tuple like this :

< program name , list , list size , bug indication >


Dictionary
------------

list - The final list in memory .

list size - The list length .

bug indication - No Bug - no bug found .

	       - Non-Atomic Bug - the add operation was not atomic (the list lacks some elements) .	

