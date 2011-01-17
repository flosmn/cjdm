Golan Lavi 25571449
Barak Horovitz 31908288

This program is built from two programs:

1. BuggedProgram - this program has a bug.
   This program receives two parameters:
       a. Reference to the output stream, where the program writes the number of times the bug           happened.
       b. The number of threads to run in this program.

   The program has function which called "pingPong":
   This function has a bug, that will cause a NullPointerException each time a thread comes       before a previous thread finish his "work".
   This bug could have been prevented if the the function was declared as "Synchronized", that       will not give any thread the option to enter the function before the area is clear, and in       that way no thread can find the reference to the variable null.



2. ProgramRunner:
   Runns the BuggedProgram, each time with a different number of threads.
