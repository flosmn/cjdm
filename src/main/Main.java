package main;

import java.util.Collection;

import workers.CommonTreePackage;
import workers.MethodModifierCounter;

/**
 * Parse a java file or directory of java files using the generated parser
 * ANTclassOrInterfaceDeclarationLR builds from java.g
 */
class Main {
	public static void main(String[] args) {
		WorkerQueue workerQueue = new WorkerQueue();
		
		String[] modifierNames = { "public", "private", "synchronized" };
		for (String modifierName : modifierNames) {
			workerQueue.addWorker(new MethodModifierCounter(modifierName));
			// TODO: add more workers here
		}
		
		Collection<CommonTreePackage> treePackages = (new GenerateTreePackages()).generate();

		for(CommonTreePackage treePackage : treePackages){
			workerQueue.doWork(treePackage);
		}		

		workerQueue.exportResults();
	}
}
