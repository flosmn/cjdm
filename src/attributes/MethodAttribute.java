package attributes;

import database.Scope;

/**
 * This class stores all method {@code Scope} attribute names that can be used for mining.
 */
public class MethodAttribute {
	public static final Attribute COMBINED_METHOD_NAME = new Attribute(Scope.METHOD,
			"CONCAT(PROJECT_NAME,'->',CLASS_NAME,'->',METHOD_NAME," +
			"'(',CAST(RAND()*1000000 AS INT),')') AS NAME", false);
	
	/** name of project */
	public static final Attribute PROJECT_NAME = Attribute.PROJECT_NAME;
	/** name of class */
	public static final Attribute CLASS_NAME = Attribute.CLASS_NAME;
	/** name of method */
	public static final Attribute METHOD_NAME = Attribute.METHOD_NAME;
	
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
	public static final Attribute UNLOCK_CALLS = Attribute.UNLOCK_CALLS;
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
	/** number of {@code Thread.start()} calls */
	public static final Attribute START_CALLS = Attribute.START_CALLS;
	/** number of {@code Thread.run()} calls */
	public static final Attribute RUN_CALLS = Attribute.RUN_CALLS;
	
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

}