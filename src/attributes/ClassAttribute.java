/**
Ê* @author Christian Wellenbrock
 * @author Florian Simon
Ê* @author JŸrgen Walter
 * @author Stefan Kober
Ê* Teams 09, 10
Ê*
Ê* This code has been developed during the winter term 2010-2011 at the
Ê* Karlsruhe Institute of Technology (KIT), Germany.
Ê* It is part of a project assignment in the course
Ê* "Multicore Programming in Practice: Tools, Models, and Languages".
Ê* Project director/instructor:
Ê* Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package attributes;

import database.Scope;

/**
 * This class stores all class {@code Scope} attribute names that can be used for mining.
 */
public class ClassAttribute {
	public static final Attribute COMBINED_CLASS_NAME = new Attribute(Scope.CLASS,
			"CONCAT(PROJECT_NAME,'->',CLASS_NAME," +
			"'(',CAST(RAND()*1000000 AS INT),')') AS NAME", false);
	
	/** name of project */
	public static final Attribute PROJECT_NAME = Attribute.PROJECT_NAME;
	/** name of class */
	public static final Attribute CLASS_NAME = Attribute.CLASS_NAME;
	
	// method attributes
	/** number of {@code public} methods */
	public static final Attribute PUBLIC_METHODS = Attribute.PUBLIC_METHODS;
	/** number of {@code private} methods */
	public static final Attribute PRIVATE_METHODS = Attribute.PRIVATE_METHODS;
	/** number of {@code synchronized} methods */
	public static final Attribute SYNCHRONIZED_METHODS = Attribute.SYNCHRONIZED_METHODS;
	/** number of method calls in a method*/
	public static final Attribute METHOD_CALLS = Attribute.METHOD_CALLS;
	/** number of {@code synchronized} calls in a method*/
	public static final Attribute SYNCHRONIZED_BLOCKS = Attribute.SYNCHRONIZED_BLOCKS;
	/** number of {@code Lock.lock()} calls */
	public static final Attribute LOCK_CALLS = Attribute.LOCK_CALLS;
	/** number of {@code Lock.unlock()} calls */
	public static final Attribute UNLOCK_CALLS= Attribute.UNLOCK_CALLS;
	/** number of {@code Thread.wait()} calls */
	public static final Attribute WAIT_CALLS = Attribute.WAIT_CALLS;
	/** number of {@code Thread.notify()} calls */
	public static final Attribute NOTIFY_CALLS = Attribute.NOTIFY_CALLS;
	/** number of {@code Thread.notifyAll()} calls */
	public static final Attribute NOTIFYALL_CALLS = Attribute.NOTIFYALL_CALLS;
	/** number of {@code Thread.sleep(long)} calls */
	public static final Attribute SLEEP_CALLS = Attribute.SLEEP_CALLS;
	/** number of {@code Thread.yield()} calls */
	public static final Attribute YIELD_CALLS = Attribute.YIELD_CALLS;
	/** number of {@code Thread.join()} calls */
	public static final Attribute JOIN_CALLS = Attribute.JOIN_CALLS;
	
	/** number of ({@link java.util.concurrent.locks.ReentrantLock | ReadLock | WriteLock | Lock}) objects in method*/
	public static final Attribute LOCK_OBJECTS = Attribute.LOCK_OBJECTS;
	/** number of {@link java.util.concurrent.locks CountDownLatch} objects in method */	
	public static final Attribute COUNT_DOWN_OBJECTS = Attribute.COUNT_DOWN_OBJECTS;
	/** number of {@link java.util.concurrent.locks.Condition} objects in method */		
	public static final Attribute CONDITION_OBJECTS = Attribute.CONDITION_OBJECTS;
	/** number of {@link java.util.concurrent.locks.Semaphore} objects in method */		
	public static final Attribute SEMAPHORE_OBJECTS = Attribute.SEMAPHORE_OBJECTS;
	/** number of {@link java.util.concurrent.locks.CyclicBarrier} objects in method */
	public static final Attribute CYCLICBARRIER_OBJECTS = Attribute.CYCLICBARRIER_OBJECTS;
	
	/** nestedness of {@code lock} */			
	public static final Attribute NESTEDNESS_LOCKS = Attribute.NESTEDNESS_LOCKS;
	/** nestedness of {@code synchronized}*/
	public static final Attribute NESTEDNESS_SYNCHRONIZED = Attribute.NESTEDNESS_SYNCHRONIZED;
	/** 
	 * nestedness of {@code if}, example:
	 * {@code 
	 *   if (a) {
	 *     if (b){
	 *     }
	 *   }
	 * }
	 * has nestedness of 2
	 **/
	public static final Attribute NESTEDNESS_CONDITIONALS = Attribute.NESTEDNESS_CONDITIONALS;
	/** nestedness of {@code for| while | do while} */			
	public static final Attribute NESTEDNESS_LOOPS = Attribute.NESTEDNESS_LOOPS;
	
	/** number of while wait pattern */
	public static final Attribute WHILE_WAIT = Attribute.WHILE_WAIT;
	/** number of conditional wait pattern */
	public static final Attribute CONDITIONAL_WAIT = Attribute.CONDITIONAL_WAIT;
	/** number of double checked lock pattern */
	public static final Attribute DOUBLE_CHECKED_LOCK = Attribute.DOUBLE_CHECKED_LOCK;

	// class attributes
	/** number of methods used in class */
	public static final Attribute NUMBER_OF_METHODS = Attribute.NUMBER_OF_METHODS;
	/** number of {@code volatile} variables used in class */
	public static final Attribute VOLATILE_VARIABLES = Attribute.VOLATILE_VARIABLES;
	/** number of {@code public} variables used in class */
	public static final Attribute PUBLIC_VARIABLES = Attribute.PUBLIC_VARIABLES;
	/** number of {@code private} variables used in class */
	public static final Attribute PRIVATE_VARIABLES = Attribute.PRIVATE_VARIABLES;
	/** number of {@code ReentrantLock, ReadLock, WriteLock or Lock} objects in class*/
	public static final Attribute LOCK_OBJECT_FIELDS = Attribute.LOCK_OBJECT_FIELDS;
	/** number of {@code Condition} objects in class */
	public static final Attribute CONDITION_OBJECT_FIELDS = Attribute.CONDITION_OBJECT_FIELDS;
	/** number of {@code Executor} objects in class */
	public static final Attribute EXECUTOR_OBJECT_FIELDS = Attribute.EXECUTOR_OBJECT_FIELDS;
	/** number of {@code ConcurrentMap} objects in class */
	public static final Attribute CONCURRENTMAP_OBJECT_FIELDS = Attribute.CONCURRENTMAP_OBJECT_FIELDS;
	/** number of {@code AtomicInteger} objects in class */
	public static final Attribute ATOMICINTEGER_OBJECT_FIELDS = Attribute.ATOMICINTEGER_OBJECT_FIELDS;
	/** number of {@code BlockingQueue} objects in class */
	public static final Attribute BLOCKINGQUEUE_OBJECT_FIELDS = Attribute.BLOCKINGQUEUE_OBJECT_FIELDS;
	/** number of {@code ConcurrentLinkedQueue} objects in class */
	public static final Attribute CONCURRENTLINKEDQUEUE_OBJECT_FIELDS = Attribute.CONCURRENTLINKEDQUEUE_OBJECT_FIELDS;
	/** number of {@code CopyOnWriteArrayList} objects in class */
	public static final Attribute COPYONWRITEARRAYLIST_OBJECT_FIELDS = Attribute.COPYONWRITEARRAYLIST_OBJECT_FIELDS;
	/** number of {@code ExecutorService} objects in class */
	public static final Attribute EXECUTORSERVICE_OBJECT_FIELDS = Attribute.EXECUTORSERVICE_OBJECT_FIELDS;
	/** number of {@code Future} objects in class */
	public static final Attribute FUTURE_OBJECT_FIELDS = Attribute.FUTURE_OBJECT_FIELDS;
	/** number of {@code ThreadPoolExecutor} objects in class */
	public static final Attribute THREADPOOLEXECUTOR_OBJECT_FIELDS = Attribute.THREADPOOLEXECUTOR_OBJECT_FIELDS;
	/** number of {@code CountDownLatch} objects in class */
	public static final Attribute COUNTDOWNLATCH_OBJECT_FIELDS = Attribute.COUNTDOWNLATCH_OBJECT_FIELDS;
	/** number of {@code CyclicBarrier} objects in class */		
	public static final Attribute CYCLICBARRIER_OBJECT_FIELDS = Attribute.CYCLICBARRIER_OBJECT_FIELDS;
	/** number of {@code Exchanger} objects in class */		
	public static final Attribute EXCHANGER_OBJECT_FIELDS = Attribute.EXCHANGER_OBJECT_FIELDS;
	/** number of {@code Semaphore} objects in class */		
	public static final Attribute SEMAPHORE_OBJECT_FIELDS = Attribute.SEMAPHORE_OBJECT_FIELDS;
	/** number of {@code ThreadFactory} objects in class */		
	public static final Attribute THREADFACTORY_OBJECT_FIELDS = Attribute.THREADFACTORY_OBJECT_FIELDS;
	
	/** number of {@code Callable} interface use */
	public static final Attribute CALLABLE_INTERFACE = Attribute.CALLABLE_INTERFACE;
	/** number of {@code Delayed} interface use */
	public static final Attribute DELAYED_INTERFACE = Attribute.DELAYED_INTERFACE;
	/** number of {@code ThreadFactory} interface use */
	public static final Attribute THREADFACTORY_INTERFACE = Attribute.THREADFACTORY_INTERFACE;
	/** number of {@code BlockingQueue} interface use */
	public static final Attribute BLOCKINGQUEUE_INTERFACE = Attribute.BLOCKINGQUEUE_INTERFACE;
	
	/** class {@code extends ConcurrentMap}*/
	public static final Attribute CONCURRENTMAP_OBJECTS_EXTENDS = Attribute.CONCURRENTMAP_OBJECTS_EXTENDS;
	/** class {@code extends ExecutorService}*/
	public static final Attribute EXECUTORSERVICE_OBJECTS_EXTENDS = Attribute.EXECUTORSERVICE_OBJECTS_EXTENDS;
	/** class {@code extends ThreadPoolExecutor}*/
	public static final Attribute THREADPOOLEXECUTOR_OBJECTS_EXTENDS = Attribute.THREADPOOLEXECUTOR_OBJECTS_EXTENDS;
	/** class {@code extends CyclicBarrier}*/
	public static final Attribute CYCLICBARRIER_OBJECTS_EXTENDS = Attribute.CYCLICBARRIER_OBJECTS_EXTENDS;
	/** class {@code extends Exchanger}*/
	public static final Attribute EXCHANGER_OBJECTS_EXTENDS = Attribute.EXCHANGER_OBJECTS_EXTENDS;
	/** class {@code extends Semaphore}*/
	public static final Attribute SEMAPHORE_OBJECTS_EXTENDS = Attribute.SEMAPHORE_OBJECTS_EXTENDS;
	/** class {@code extends Lock}*/
	public static final Attribute LOCK_OBJECTS_EXTENDS = Attribute.LOCK_OBJECTS_EXTENDS;
	/** class {@code extends Executor}*/
	public static final Attribute EXECUTOR_OBJECTS_EXTENDS = Attribute.EXECUTOR_OBJECTS_EXTENDS;
	/** class {@code extends AtomicInteger}*/
	public static final Attribute ATOMICINTEGER_OBJECTS_EXTENDS = Attribute.ATOMICINTEGER_OBJECTS_EXTENDS;
	/** class {@code extends ConcurrentLinkedQueue}*/
	public static final Attribute CONCURRENTLINKEDQUEUE_OBJECTS_EXTENDS = Attribute.CONCURRENTLINKEDQUEUE_OBJECTS_EXTENDS;
	/** class {@code extends CopyOnWriteArrayList}*/
	public static final Attribute COPYONWRITEARRAYLIST_OBJECTS_EXTENDS = Attribute.COPYONWRITEARRAYLIST_OBJECTS_EXTENDS;
	/** class {@code extends Future}*/
	public static final Attribute FUTURE_OBJECTS_EXTENDS = Attribute.FUTURE_OBJECTS_EXTENDS;
	/** class {@code extends CountDownLatch}*/
	public static final Attribute COUNTDOWNLATCH_OBJECTS_EXTENDS = Attribute.COUNTDOWNLATCH_OBJECTS_EXTENDS;
}
