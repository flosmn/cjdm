Manager program
-------------------------

handed by 	Sharon Ivgi ID 029665924
		Ofer Levi	  ID 024592891

Bug description
	As explained in our mail, bug is the same but with different "cover story".
	One thread (TmemoryHandler class) is responsible for keeping track of 
	memory blocks to release. Trealse class are the threads responsible for the 
	memory release.
	Bug is in Trelease class, ReleaseMemoryBlock method  lines 48-49.
	Context switch in this lines will cause a bug and the memory will not be released.

	Technical details:
	Trealse class is using Manager.flag to signal to Tmemory Handler class that current 
	memory block is taken care of. Manager setNote method is responsible of keeping track 
	how many are trying to release same memory. Only one is allowed to release it.
	On entering ReleaseMemoryBlock method  thread signal he is in for handling the release,
	(putting a "Note" to say im handling it) then thread checks if some other thread is trying to 
	release same memory (if another "Note" was already posted but not removed), if so leaving
	 with out doing anything.

	This bug will appear only if context switch  occurs on lines 48-49. Then all treads in this code
	are assuming some other thread is already releasing that memory.

Usage:
	Manager OutputFile NumOfTreleaseThreads [NumOfMemBlockToRelease]-default value 100

Output
	Output file format is as asked, program name , bug occurs , or not
	for example:
		Program name: Manager , Bug found: true
		Program name: Manager , Bug found: false


	
Let us state that only using outside means like sleep and yield caused the bug to appear.

