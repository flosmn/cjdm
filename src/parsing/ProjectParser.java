/**
 * @author Christian Wellenbrock
 * @author Florian Simon
 * @author JŸrgen Walter
 * @author Stefan Kober
 * Teams 09, 10
 *
 * This code has been developed during the winter term 2010-2011 at the
 * Karlsruhe Institute of Technology (KIT), Germany.
 * It is part of a project assignment in the course
 * "Multicore Programming in Practice: Tools, Models, and Languages".
 * Project director/instructor:
 * Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package parsing;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import utils.PathAndFileNames;
import workers.CountNestednessOfLocks;
import workers.Counter;
import workers.PatternCounter;
import workers.RecursiveNestednessCounter;
import attributes.ClassAttribute;
import attributes.MethodAttribute;
import database.Database;

/**
 * Parse a java file or directory of java files using the generated parser
 * ANTclassOrInterfaceDeclarationLR builds from java.g
 */
public class ProjectParser {
	
	/**
	 * calls {@link #parseProjects(String)} with projectSourcesPath as param
	 * @param args projectSourcesPath
	 */
	public static void main(String[] args) {
		String projectSourcesPath = args[0];
		new ProjectParser().parseProjects(projectSourcesPath);
	}
	
	/**
	 * parses all files in projects/ or in a passed projects path
	 * and writes into a database
	 * @param projectSourcesPath path to the projects to be parsed 
	 */
	public void parseProjects(String projectSourcesPath) {
		if(projectSourcesPath == null){
				projectSourcesPath = PathAndFileNames.PROJECT_SOURCES_PATH;
		}
		
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		WorkerQueue workerQueue = new WorkerQueue(database);

		workerQueue.addWorker(new PatternCounter(MethodAttribute.WHILE_WAIT, 
				"BLOCK_SCOPE", "synchronized", "BLOCK_SCOPE", "(while|for)", "BLOCK_SCOPE", "METHOD_CALL", "\\.", "wait"));
		workerQueue.addWorker(new PatternCounter(MethodAttribute.CONDITIONAL_WAIT, 
				"BLOCK_SCOPE", "synchronized", "BLOCK_SCOPE", "if", "BLOCK_SCOPE", "METHOD_CALL", "\\.", "wait"));
		workerQueue.addWorker(new PatternCounter(MethodAttribute.DOUBLE_CHECKED_LOCK, 
				"if", "synchronized", "if"));
		
		workerQueue.addWorker(new Counter(MethodAttribute.PUBLIC_METHODS, ".*METHOD_DECL", "MODIFIER_LIST", "public"));
		workerQueue.addWorker(new Counter(MethodAttribute.PRIVATE_METHODS, ".*METHOD_DECL", "MODIFIER_LIST", "private"));
		workerQueue.addWorker(new Counter(MethodAttribute.SYNCHRONIZED_METHODS, ".*METHOD_DECL", "MODIFIER_LIST", "synchronized"));
		workerQueue.addWorker(new Counter(MethodAttribute.SYNCHRONIZED_BLOCKS, "BLOCK_SCOPE", "synchronized"));
		workerQueue.addWorker(new Counter(MethodAttribute.METHOD_CALLS, "EXPR", "METHOD_CALL"));
		workerQueue.addWorker(new Counter(MethodAttribute.LOCK_CALLS, "METHOD_CALL", "\\.", "lock"));
		workerQueue.addWorker(new Counter(MethodAttribute.UNLOCK_CALLS, "METHOD_CALL", "\\.", "unlock"));
		workerQueue.addWorker(new Counter(MethodAttribute.WAIT_CALLS, "METHOD_CALL", "\\.", "wait"));
		workerQueue.addWorker(new Counter(MethodAttribute.NOTIFY_CALLS, "METHOD_CALL", "\\.", "notify"));
		workerQueue.addWorker(new Counter(MethodAttribute.NOTIFYALL_CALLS, "METHOD_CALL", "\\.", "notifyAll"));
		workerQueue.addWorker(new Counter(MethodAttribute.SLEEP_CALLS, "METHOD_CALL", "\\.", "sleep"));
		workerQueue.addWorker(new Counter(MethodAttribute.YIELD_CALLS, "METHOD_CALL", "\\.", "yield"));
		workerQueue.addWorker(new Counter(MethodAttribute.JOIN_CALLS, "METHOD_CALL", "\\.", "join"));
		workerQueue.addWorker(new Counter(MethodAttribute.START_CALLS, "METHOD_CALL", "\\.", "start"));
		workerQueue.addWorker(new Counter(MethodAttribute.RUN_CALLS, "METHOD_CALL", "\\.", "run"));
		
		workerQueue.addWorker(new Counter(MethodAttribute.LOCK_OBJECTS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "(Reentrant|Read|Write)?Lock(<.*>)?"));
		workerQueue.addWorker(new Counter(MethodAttribute.COUNT_DOWN_OBJECTS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "CountDownLatch(<.*>)?"));
		workerQueue.addWorker(new Counter(MethodAttribute.CONDITION_OBJECTS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Condition(<.*>)?"));
		workerQueue.addWorker(new Counter(MethodAttribute.SEMAPHORE_OBJECTS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Semaphore(<.*>)?"));
		workerQueue.addWorker(new Counter(MethodAttribute.CYCLICBARRIER_OBJECTS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "CyclicBarrier(<.*>)?"));
		
		workerQueue.addWorker(new CountNestednessOfLocks());
		workerQueue.addWorker(new RecursiveNestednessCounter(MethodAttribute.NESTEDNESS_SYNCHRONIZED, "synchronized"));
		workerQueue.addWorker(new RecursiveNestednessCounter(MethodAttribute.NESTEDNESS_CONDITIONALS, "if"));
		workerQueue.addWorker(new RecursiveNestednessCounter(MethodAttribute.NESTEDNESS_LOOPS, "for", "while", "do", "FOR_EACH"));

		workerQueue.addWorker(new Counter(ClassAttribute.NUMBER_OF_METHODS, ".*METHOD_DECL"));
		workerQueue.addWorker(new Counter(ClassAttribute.VOLATILE_VARIABLES, "VAR_DECLARATION", "MODIFIER_LIST", "volatile"));
		workerQueue.addWorker(new Counter(ClassAttribute.PUBLIC_VARIABLES, "VAR_DECLARATION", "MODIFIER_LIST", "public"));
		workerQueue.addWorker(new Counter(ClassAttribute.PRIVATE_VARIABLES, "VAR_DECLARATION", "MODIFIER_LIST", "private"));
		workerQueue.addWorker(new Counter(ClassAttribute.LOCK_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "(Reentrant|Read|Write)?Lock(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.CONDITION_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Condition(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.EXECUTOR_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Executor(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.CONCURRENTMAP_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ConcurrentMap(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.ATOMICINTEGER_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "AtomicInteger(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.BLOCKINGQUEUE_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "(Array|Linked)?BlockingQueue(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.CONCURRENTLINKEDQUEUE_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ConcurrentLinkedQueue(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.COPYONWRITEARRAYLIST_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "CopyOnWriteArrayList(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.EXECUTORSERVICE_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ExecutorService(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.FUTURE_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Future(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.THREADPOOLEXECUTOR_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ThreadPoolExecutor(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.COUNTDOWNLATCH_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "CountDownLatch(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.CYCLICBARRIER_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "CyclicBarrier(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.EXCHANGER_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Exchanger(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.SEMAPHORE_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "Semaphore(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.THREADFACTORY_OBJECT_FIELDS, "VAR_DECLARATION", "TYPE", "QUALIFIED_TYPE_IDENT", "ThreadFactory"));
		
		workerQueue.addWorker(new Counter(ClassAttribute.CALLABLE_INTERFACE, "IMPLEMENTS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Callable(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.DELAYED_INTERFACE, "IMPLEMENTS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Delayed(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.THREADFACTORY_INTERFACE, "IMPLEMENTS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "ThreadFactory(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.BLOCKINGQUEUE_INTERFACE, "IMPLEMENTS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "BlockingQueue(<.*>)?"));
		
		workerQueue.addWorker(new Counter(ClassAttribute.CONCURRENTMAP_OBJECTS_EXTENDS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "ConcurrentMap(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.EXECUTORSERVICE_OBJECTS_EXTENDS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "ExecutorService(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.THREADPOOLEXECUTOR_OBJECTS_EXTENDS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "ThreadPoolExecutor(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.CYCLICBARRIER_OBJECTS_EXTENDS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "CyclicBarrier(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.EXCHANGER_OBJECTS_EXTENDS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Exchanger(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.SEMAPHORE_OBJECTS_EXTENDS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Semaphore(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.LOCK_OBJECTS_EXTENDS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "(Reentrant|Read|Write)?Lock(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.EXECUTOR_OBJECTS_EXTENDS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Executor(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.ATOMICINTEGER_OBJECTS_EXTENDS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "AtomicInteger(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.CONCURRENTLINKEDQUEUE_OBJECTS_EXTENDS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "ConcurrentLinkedQueue(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.COPYONWRITEARRAYLIST_OBJECTS_EXTENDS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "CopyOnWriteArrayList(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.FUTURE_OBJECTS_EXTENDS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "Future(<.*>)?"));
		workerQueue.addWorker(new Counter(ClassAttribute.COUNTDOWNLATCH_OBJECTS_EXTENDS, "EXTENDS_CLAUSE", "TYPE", "QUALIFIED_TYPE_IDENT", "CountDownLatch(<.*>)?"));

		workerQueue.createTables();
		
		Collection<TreePackage> projectPackages = (new TreePackageGenerator()).generateProjectPackages(projectSourcesPath);
		Collections.sort((List<TreePackage>) projectPackages);
		for(TreePackage projectPackage: projectPackages) {
			workerQueue.doWork(projectPackage);
		}
		
		workerQueue.createViews();

		database.shutdown();
	}
}
