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
		
		String[] methodModifierNames = { "public", "private", "synchronized" };
		for (String modifierName : methodModifierNames) {
			workerQueue.addWorker(new MethodModifierCounter(modifierName));
		}

		String[] attributeModifierNames = { "public", "private", "volatile" };
		for (String modifierName : attributeModifierNames) {
			workerQueue.addWorker(new AttributeModifierCounter(modifierName));
		}
		workerQueue.addWorker(new SynchronizedBlocksCounter());
		workerQueue.addWorker(new LockBlocksCounter());
		// TODO: add more workers here
		
		Collection<CommonTreePackage> treePackages = (new GenerateTreePackages()).generate();

		for(CommonTreePackage treePackage : treePackages) {
			workerQueue.doWork(treePackage);
		}		

		workerQueue.exportResults();
	}
}
