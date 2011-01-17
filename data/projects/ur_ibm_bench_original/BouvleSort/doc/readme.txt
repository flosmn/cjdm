Our program sorts an array of integers using bubble sort.
Every iteration in the sort process implements in separate thread.
Program work normally with not busy system but if there are another tasks 
then will possible bug`s appearance (wrong numbers order in the final array)
This is an "initialization-sleep" bugpattern 
A problem is in the following code:

  try {
       this.sleep(0,1);
      }
   catch (InterruptedException e){}

If there is not enough processor time for this thread then function sleep() will
not guarantee a correct interleaving.

A program`s output dictionary:

 1) "SortProgram finished with no bugs." There was no bugs into sorting process.
 2) "SortProgram finished with N bug(s) of Initialization-Sleep Pattern". There was a N bugs
  into a final integer array.
 3)"wrong input". Input of the program is not correct.

 A program starts with run.bat (parameters - outfile, concurrency)  


Our ID:
  309522399
  318108701 