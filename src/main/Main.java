package main;

import java.util.Collection;

import workers.ModifierCounter;
import workers.LockBlocksCounter;
import workers.SynchronizedBlocksCounter;

/**
 * Parse a java file or directory of java files using the generated parser
 * ANTclassOrInterfaceDeclarationLR builds from java.g
 */
class Main {
	public static void main(String[] args) {
		WorkerQueue workerQueue = new WorkerQueue();
		
		workerQueue.addWorker(new ModifierCounter("METHOD_DECL", "public"));
		workerQueue.addWorker(new ModifierCounter("METHOD_DECL", "private"));
		workerQueue.addWorker(new ModifierCounter("METHOD_DECL", "synchronized"));
		workerQueue.addWorker(new ModifierCounter("VAR_DECLARATION", "public"));
		workerQueue.addWorker(new ModifierCounter("VAR_DECLARATION", "private"));
		workerQueue.addWorker(new ModifierCounter("VAR_DECLARATION", "volatile"));
		workerQueue.addWorker(new SynchronizedBlocksCounter());
		workerQueue.addWorker(new LockBlocksCounter());
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
