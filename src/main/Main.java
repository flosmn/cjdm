package main;

import java.util.Collection;

import database.Database;
import database.Scope;

import utils.PathAndFileNames;
import workers.CountNestednessOfLocks;
import workers.Counter;
import workers.RecursiveNestednessCounter;

/**
 * Parse a java file or directory of java files using the generated parser
 * ANTclassOrInterfaceDeclarationLR builds from java.g
 */
class Main {
	public static void main(String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		WorkerQueue workerQueue = new WorkerQueue(database);
		
		workerQueue.addWorker(new Counter("public_methods", Scope.METHOD, ".*METHOD_DECL", "MODIFIER_LIST", "public"));
		workerQueue.addWorker(new Counter("private_methods", Scope.METHOD, ".*METHOD_DECL", "MODIFIER_LIST", "private"));
		workerQueue.addWorker(new Counter("synchronized_methods", Scope.METHOD, ".*METHOD_DECL", "MODIFIER_LIST", "synchronized"));
		workerQueue.addWorker(new Counter("method_calls", Scope.METHOD, "EXPR", "METHOD_CALL"));
		workerQueue.addWorker(new Counter("number_of_methods", Scope.CLASS, ".*METHOD_DECL"));
		workerQueue.addWorker(new Counter("synchronized_blocks", Scope.METHOD, "BLOCK_SCOPE", "synchronized"));
		workerQueue.addWorker(new Counter("lock_calls", Scope.METHOD, "METHOD_CALL", "\\.", "lock"));
		workerQueue.addWorker(new Counter("unlock_calls", Scope.METHOD, "METHOD_CALL", "\\.", "unlock"));
		workerQueue.addWorker(new Counter("wait_calls", Scope.METHOD, "METHOD_CALL", "\\.", "wait"));
		workerQueue.addWorker(new Counter("notify_calls", Scope.METHOD, "METHOD_CALL", "\\.", "notify"));
		workerQueue.addWorker(new Counter("notifyAll_calls", Scope.METHOD, "METHOD_CALL", "\\.", "notifyAll"));
		workerQueue.addWorker(new Counter("sleep_calls", Scope.METHOD, "METHOD_CALL", "\\.", "sleep"));
		workerQueue.addWorker(new Counter("yield_calls", Scope.METHOD, "METHOD_CALL", "\\.", "yield"));
		workerQueue.addWorker(new Counter("join_calls", Scope.METHOD, "METHOD_CALL", "\\.", "join"));
		workerQueue.addWorker(new Counter("volatile_variables", Scope.CLASS, "VAR_DECLARATION", "MODIFIER_LIST", "volatile"));
		workerQueue.addWorker(new Counter("public_variables", Scope.CLASS, "VAR_DECLARATION", "MODIFIER_LIST", "public"));
		workerQueue.addWorker(new Counter("private_variables", Scope.CLASS, "VAR_DECLARATION", "MODIFIER_LIST", "private"));
		workerQueue.addWorker(new Counter("lock_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Lock"));
		workerQueue.addWorker(new Counter("executor_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Executor"));
		workerQueue.addWorker(new Counter("concurrentMap_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ConcurrentMap"));
		workerQueue.addWorker(new Counter("atomicInteger_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "AtomicInteger"));
		workerQueue.addWorker(new Counter("blockingQueue_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "BlockingQueue"));
		workerQueue.addWorker(new Counter("concurrentLinkedQueue_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ConcurrentLinkedQueue"));
		workerQueue.addWorker(new Counter("copyOnWriteArrayList_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "CopyOnWriteArrayList"));
		workerQueue.addWorker(new Counter("deleyQueue_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "DeleyQueue"));
		workerQueue.addWorker(new Counter("executorService_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ExecutorService"));
		workerQueue.addWorker(new Counter("future_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Future"));
		workerQueue.addWorker(new Counter("threadPoolExecutor_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ThreadPoolExecutor"));
		workerQueue.addWorker(new Counter("countDownLatch_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "CountDownLatch"));
		workerQueue.addWorker(new Counter("cyclicBarrier_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "CyclicBarrier"));
		workerQueue.addWorker(new Counter("exchanger_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Exchanger"));
		workerQueue.addWorker(new Counter("semaphore_objects", Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Semaphore"));
		workerQueue.addWorker(new Counter("callable_interface", Scope.CLASS, "IMPLEMENTS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Callable"));
		workerQueue.addWorker(new Counter("delayed_interface", Scope.CLASS, "IMPLEMENTS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Delayed"));
		workerQueue.addWorker(new Counter("threadFactory_interface", Scope.CLASS, "IMPLEMENTS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "ThreadFactory"));
		
		workerQueue.addWorker(new CountNestednessOfLocks());
		workerQueue.addWorker(new RecursiveNestednessCounter("nestedness_synchronized", Scope.METHOD, "synchronized"));
		workerQueue.addWorker(new RecursiveNestednessCounter("nestedness_conditionals", Scope.METHOD, "if"));
		workerQueue.addWorker(new RecursiveNestednessCounter("nestedness_loops", Scope.METHOD, "for", "while", "do"));
		// TODO: add more workers here
		
		// TODO: don't drop, but append to existing tables
		workerQueue.dropViews();
		workerQueue.dropTables();
		
		workerQueue.createTables();

		Collection<TreePackage> projectPackages = (new TreePackageGenerator()).generateProjectPackages();
		for(TreePackage projectPackage: projectPackages) {
			workerQueue.doWork(projectPackage);
		}
		
		workerQueue.createViews();

		database.shutdown();
		
		System.out.println("Done!");
	}
}
