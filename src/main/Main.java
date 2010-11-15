package main;

import java.util.Collection;

import workers.AttributeModifierCounter;
import workers.CommonTreePackage;
import workers.LockBlocksCounter;
import workers.MethodModifierCounter;
import workers.SynchronizedBlocksCounter;

/**
 * Parse a java file or directory of java files using the generated parser
 * ANTclassOrInterfaceDeclarationLR builds from java.g
 */
class Main {
	public static void main(String[] args) {
		WorkerQueue workerQueue = new WorkerQueue();
		
		workerQueue.addWorker(new MethodModifierCounter("public"));
		workerQueue.addWorker(new MethodModifierCounter("private"));
		workerQueue.addWorker(new MethodModifierCounter("synchronized"));
		workerQueue.addWorker(new AttributeModifierCounter("public"));
		workerQueue.addWorker(new AttributeModifierCounter("private"));
		workerQueue.addWorker(new AttributeModifierCounter("volatile"));
		workerQueue.addWorker(new SynchronizedBlocksCounter());
		workerQueue.addWorker(new LockBlocksCounter());
		// TODO: add more workers here
		
		Collection<CommonTreePackage> treePackages = (new GenerateTreePackages()).generate();

		for(CommonTreePackage treePackage : treePackages) {
			workerQueue.doWork(treePackage);
		}		

		workerQueue.exportResults();
		
		System.out.println("Done!");
	}
}
