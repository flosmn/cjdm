package main;

import java.util.Collection;

import workers.Counter;
import workers.CountNestednessOfSynchronizedBlocks;

/**
 * Parse a java file or directory of java files using the generated parser
 * ANTclassOrInterfaceDeclarationLR builds from java.g
 */
class Main {
	public static void main(String[] args) {
		WorkerQueue workerQueue = new WorkerQueue();
		
		workerQueue.addWorker(new Counter(".*METHOD_DECL", "MODIFIER_LIST", "public"));
		workerQueue.addWorker(new Counter(".*METHOD_DECL", "MODIFIER_LIST", "private"));
		workerQueue.addWorker(new Counter(".*METHOD_DECL", "MODIFIER_LIST", "synchronized"));
		workerQueue.addWorker(new Counter("VAR_DECLARATION", "MODIFIER_LIST", "public"));
		workerQueue.addWorker(new Counter("VAR_DECLARATION", "MODIFIER_LIST", "private"));
		workerQueue.addWorker(new Counter("VAR_DECLARATION", "MODIFIER_LIST", "volatile"));
		workerQueue.addWorker(new Counter("BLOCK_SCOPE", "synchronized"));
		// TODO: create new method counter (don't count matching called objects)
		workerQueue.addWorker(new Counter("METHOD_CALL", "\\.", "lock"));
		workerQueue.addWorker(new Counter("METHOD_CALL", "\\.", "unlock"));
		workerQueue.addWorker(new CountNestednessOfSynchronizedBlocks());
		// TODO: add more workers here
		
		// TODO: generate trees on demand to reduce memory overhead
		Collection<CommonTreePackage> treePackages = (new GenerateTreePackages()).generate();

		for(CommonTreePackage treePackage : treePackages) {
			workerQueue.doWork(treePackage);
		}		

		// TODO: change database to export every record immediately
		workerQueue.exportResults();
		
		System.out.println("Done!");
	}
}
