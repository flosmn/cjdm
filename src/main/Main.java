package main;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import utils.PathAndFileNames;
import workers.CountNestednessOfLocks;
import workers.Counter;
import workers.RecursiveNestednessCounter;
import attributes.ClassAttributes;
import attributes.MethodAttributes;
import database.Database;
import database.Scope;

/**
 * Parse a java file or directory of java files using the generated parser
 * ANTclassOrInterfaceDeclarationLR builds from java.g
 */
class Main {
	public static void main(String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		WorkerQueue workerQueue = new WorkerQueue(database);
		
		workerQueue.addWorker(new Counter(MethodAttributes.PUBLIC_METHODS, Scope.METHOD, ".*METHOD_DECL", "MODIFIER_LIST", "public"));
		workerQueue.addWorker(new Counter(MethodAttributes.PRIVATE_METHODS, Scope.METHOD, ".*METHOD_DECL", "MODIFIER_LIST", "private"));
		workerQueue.addWorker(new Counter(MethodAttributes.SYNCHRONIZED_METHODS, Scope.METHOD, ".*METHOD_DECL", "MODIFIER_LIST", "synchronized"));
		workerQueue.addWorker(new Counter(MethodAttributes.SYNCHRONIZED_BLOCKS, Scope.METHOD, "BLOCK_SCOPE", "synchronized"));
		workerQueue.addWorker(new Counter(MethodAttributes.METHOD_CALLS, Scope.METHOD, "EXPR", "METHOD_CALL"));
		workerQueue.addWorker(new Counter(MethodAttributes.LOCK_CALLS, Scope.METHOD, "METHOD_CALL", "\\.", "lock"));
		workerQueue.addWorker(new Counter(MethodAttributes.UNLOCK_CALLS, Scope.METHOD, "METHOD_CALL", "\\.", "unlock"));
		workerQueue.addWorker(new Counter(MethodAttributes.WAIT_CALLS, Scope.METHOD, "METHOD_CALL", "\\.", "wait"));
		workerQueue.addWorker(new Counter(MethodAttributes.NOTIFY_CALLS, Scope.METHOD, "METHOD_CALL", "\\.", "notify"));
		workerQueue.addWorker(new Counter(MethodAttributes.NOTIFYALL_CALLS, Scope.METHOD, "METHOD_CALL", "\\.", "notifyAll"));
		workerQueue.addWorker(new Counter(MethodAttributes.SLEEP_CALLS, Scope.METHOD, "METHOD_CALL", "\\.", "sleep"));
		workerQueue.addWorker(new Counter(MethodAttributes.YIELD_CALLS, Scope.METHOD, "METHOD_CALL", "\\.", "yield"));
		workerQueue.addWorker(new Counter(MethodAttributes.JOIN_CALLS, Scope.METHOD, "METHOD_CALL", "\\.", "join"));
		
		workerQueue.addWorker(new Counter(MethodAttributes.LOCK_OBJECTS, Scope.METHOD, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "(Reentrant|Read|Write)?Lock(<.*>)?"));
		workerQueue.addWorker(new Counter(MethodAttributes.COUNT_DOWN_OBJECTS, Scope.METHOD, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "CountDownLatch(<.*>)?"));
		workerQueue.addWorker(new Counter(MethodAttributes.CONDITION_OBJECTS, Scope.METHOD, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Condition(<.*>)?"));
		workerQueue.addWorker(new Counter(MethodAttributes.SEMAPHORE_OBJECTS, Scope.METHOD, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Semaphore(<.*>)?"));
		workerQueue.addWorker(new Counter(MethodAttributes.CYCLICBARRIER_OBJECTS, Scope.METHOD, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "CyclicBarrier(<.*>)?"));
		
		workerQueue.addWorker(new CountNestednessOfLocks());
		workerQueue.addWorker(new RecursiveNestednessCounter(MethodAttributes.NESTEDNESS_SYNCHRONIZED, Scope.METHOD, "synchronized"));
		workerQueue.addWorker(new RecursiveNestednessCounter(MethodAttributes.NESTEDNESS_CONDITIONALS, Scope.METHOD, "if"));
		workerQueue.addWorker(new RecursiveNestednessCounter(MethodAttributes.NESTEDNESS_LOOPS, Scope.METHOD, "for", "while", "do", "FOR_EACH"));

		workerQueue.addWorker(new Counter(ClassAttributes.NUMBER_OF_METHODS, Scope.CLASS, ".*METHOD_DECL"));
		workerQueue.addWorker(new Counter(ClassAttributes.VOLATILE_VARIABLES, Scope.CLASS, "VAR_DECLARATION", "MODIFIER_LIST", "volatile"));
		workerQueue.addWorker(new Counter(ClassAttributes.PUBLIC_VARIABLES, Scope.CLASS, "VAR_DECLARATION", "MODIFIER_LIST", "public"));
		workerQueue.addWorker(new Counter(ClassAttributes.PRIVATE_VARIABLES, Scope.CLASS, "VAR_DECLARATION", "MODIFIER_LIST", "private"));
		workerQueue.addWorker(new Counter(ClassAttributes.LOCK_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "(Reentrant|Read|Write)?Lock(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.CONDITION_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Condition(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.EXECUTOR_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Executor(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.CONCURRENTMAP_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ConcurrentMap(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.ATOMICINTEGER_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "AtomicInteger(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.BLOCKINGQUEUE_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "(Array|Linked)?BlockingQueue(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.CONCURRENTLINKEDQUEUE_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ConcurrentLinkedQueue(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.COPYONWRITEARRAYLIST_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "CopyOnWriteArrayList(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.EXECUTORSERVICE_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ExecutorService(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.FUTURE_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Future(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.THREADPOOLEXECUTOR_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ThreadPoolExecutor(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.COUNTDOWNLATCH_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "CountDownLatch(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.CYCLICBARRIER_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "CyclicBarrier(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.EXCHANGER_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Exchanger(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.SEMAPHORE_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Semaphore(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.THREADFACTORY_OBJECT_FIELDS, Scope.CLASS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ThreadFactory"));
		
		workerQueue.addWorker(new Counter(ClassAttributes.CALLABLE_INTERFACE, Scope.CLASS, "IMPLEMENTS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Callable(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.DELAYED_INTERFACE, Scope.CLASS, "IMPLEMENTS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Delayed(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.THREADFACTORY_INTERFACE, Scope.CLASS, "IMPLEMENTS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "ThreadFactory(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.BLOCKINGQUEUE_INTERFACE, Scope.CLASS, "IMPLEMENTS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "BlockingQueue(<.*>)?"));
		
		workerQueue.addWorker(new Counter(ClassAttributes.CONCURRENTMAP_OBJECTS_EXTENDS, Scope.CLASS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "ConcurrentMap(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.EXECUTORSERVICE_OBJECTS_EXTENDS, Scope.CLASS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "ExecutorService(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.THREADPOOLEXECUTOR_OBJECTS_EXTENDS, Scope.CLASS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "ThreadPoolExecutor(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.CYCLICBARRIER_OBJECTS_EXTENDS, Scope.CLASS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "CyclicBarrier(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.EXCHANGER_OBJECTS_EXTENDS,	Scope.CLASS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Exchanger(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.SEMAPHORE_OBJECTS_EXTENDS, Scope.CLASS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Semaphore(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.LOCK_OBJECTS_EXTENDS, Scope.CLASS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "(Reentrant|Read|Write)?Lock(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.EXECUTOR_OBJECTS_EXTENDS, Scope.CLASS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Executor(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.ATOMICINTEGER_OBJECTS_EXTENDS, Scope.CLASS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "AtomicInteger(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.CONCURRENTLINKEDQUEUE_OBJECTS_EXTENDS, Scope.CLASS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "ConcurrentLinkedQueue(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.COPYONWRITEARRAYLIST_OBJECTS_EXTENDS, Scope.CLASS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "CopyOnWriteArrayList(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.FUTURE_OBJECTS_EXTENDS, Scope.CLASS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Future(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttributes.COUNTDOWNLATCH_OBJECTS_EXTENDS, Scope.CLASS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "CountDownLatch(<.*>)?"));
		
		workerQueue.createTables();
		
		Collection<TreePackage> projectPackages = (new TreePackageGenerator()).generateProjectPackages();
		Collections.sort((List<TreePackage>) projectPackages);
		for(TreePackage projectPackage: projectPackages) {
			workerQueue.doWork(projectPackage);
		}
		
		workerQueue.createViews();

		database.shutdown();
		
		System.out.println("Done!");
	}
}
