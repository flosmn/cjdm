__________________________________________________________________________________________

    BuggyProgram

      Submitted by Raz Ohad, 038249785
__________________________________________________________________________________________


Hope I understood the demands of this exercise correctly.
If not, please return and clear things up for me a little.

This program generates and assigns a random number to a specific user, 
and keeps track of all numbers generated. 

In "real world", the application can be used for issuing lottery
numbers, identification numbers, passwords, and the like.

It is a multi-user, in the sense that two or more people can request
numbers at the same time, where each request is serviced by a thread.
A practical, real world example of this would be a web site.

Two possibilities could happen:

1) Two people get the same number.

2) The number generated for a particular person may not be the same when 
   the time comes to record the number.


The bug may happen because the Generate-Present-Record are not atomic, 
in regards the common variable, randomNumber.

The bug is in the generate method (lines 308-213), in the present method 
(lines 324-332), and in the record method (lines 344-351).

bug patterns: 
1. Weak-reality - Not-Atomic
2. Weak-reality - Wrong-Lock or No-Lock
3. Blocking bug - Blocking-Critical-Section.
   This demands for a little bit more:
   If one alters the program so that, for example, lot is 3333, the program 
   will halt.
   This because one did not alter the number of digits (originaly, 3), and
   the generate method will not be able to come with a new, non registered
   number.

As mentioned before, the shared resource (variable) is the randomNumber.

As for the dictionary asked, with all the possible tuples, it is practically 
impossible, since generating random numbers, which could vary according to the
number of digits one specifies (MAX_NUM_OF_DIGITS).
But I think it is well understood...

P.S.
Would like to try the bonus.

Ohad Raz, 038249785,
OhadRaz@hotmail.com
