package main;

import java.io.File;

import database.Database;
import utils.PathAndFileNames;
import workers.Counter;
import workers.CountNestednessOfSynchronizedBlocks;

/**
 * Parse a java file or directory of java files using the generated parser
 * ANTclassOrInterfaceDeclarationLR builds from java.g
 */
class Main {
	public static void main(String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		WorkerQueue workerQueue = new WorkerQueue(database);
		
		workerQueue.addWorker(new Counter("public_methods", ".*METHOD_DECL", "MODIFIER_LIST", "public"));
		workerQueue.addWorker(new Counter("private_methods", ".*METHOD_DECL", "MODIFIER_LIST", "private"));
		workerQueue.addWorker(new Counter("synchronized_methods", ".*METHOD_DECL", "MODIFIER_LIST", "synchronized"));
		workerQueue.addWorker(new Counter("public_variables", "VAR_DECLARATION", "MODIFIER_LIST", "public"));
		workerQueue.addWorker(new Counter("private_variables", "VAR_DECLARATION", "MODIFIER_LIST", "private"));
		workerQueue.addWorker(new Counter("volatile_variables", "VAR_DECLARATION", "MODIFIER_LIST", "volatile"));
		workerQueue.addWorker(new Counter("synchronized_blocks", "BLOCK_SCOPE", "synchronized"));
		workerQueue.addWorker(new Counter("lock_calls", "METHOD_CALL", "\\.", "lock"));
		workerQueue.addWorker(new Counter("unlock_calls", "METHOD_CALL", "\\.", "unlock"));
		workerQueue.addWorker(new Counter("wait_calls", "METHOD_CALL", "\\.", "wait"));
		workerQueue.addWorker(new Counter("notify_calls", "METHOD_CALL", "\\.", "notify"));
		workerQueue.addWorker(new Counter("notifyAll_calls", "METHOD_CALL", "\\.", "notifyAll"));
		workerQueue.addWorker(new Counter("sleep_calls", "METHOD_CALL", "\\.", "sleep"));
		workerQueue.addWorker(new Counter("yield_calls", "METHOD_CALL", "\\.", "yield"));
		workerQueue.addWorker(new Counter("join_calls", "METHOD_CALL", "\\.", "join"));
		workerQueue.addWorker(new CountNestednessOfSynchronizedBlocks());
		// TODO: add more workers here
		
		workerQueue.createTables();
		
		File[] projects = (new File(PathAndFileNames.PROJECT_SOURCES_PATH)).listFiles();
		for(File project : projects) {
			workerQueue.doWork(project);
		}
		
		workerQueue.createViews();

		database.shutdown();
		
		System.out.println("Done!");
	}
}
