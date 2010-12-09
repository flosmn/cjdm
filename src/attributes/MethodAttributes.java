package attributes;

public class MethodAttributes extends Attributes {
	public static final String PUBLIC_METHODS = "public_methods";
	public static final String PRIVATE_METHODS = "private_methods";
	public static final String SYNCHRONIZED_METHODS = "synchronized_methods";
	public static final String METHOD_CALLS = "method_calls";
	public static final String NUMBER_OF_METHODS = "number_of_methods";
	public static final String SYNCHRONIZED_BLOCKS = "synchronized_blocks";
	public static final String LOCK_CALLS = "lock_calls";
	public static final String UNLOCK_CALLS= "unlock_calls";
	public static final String WAIT_CALLS = "wait_calls";
	public static final String NOTIFY_CALLS = "notify_calls";
	public static final String NOTIFYALL_CALLS = "notifyAll_calls";
	public static final String SLEEP_CALLS = "sleep_calls";
	public static final String YIELD_CALLS = "yield_calls";
	public static final String JOIN_CALLS = "join_calls";
	
	public static final String LOCK_OBJECTS = "lock_objects";
	public static final String COUNT_DOWN_OBJECTS = "countDownLatch_objects";
	public static final String CONDITION_OBJECTS = "condition_objects";
	public static final String SEMAPHORE_OBJECTS = "semaphore_objects";
	public static final String CYCLICBARRIER_OBJECTS = "cyclicBarrier_objects";
	
	public static final String NESTEDNESS_LOCKS = "nestedness_locks";
	public static final String NESTEDNESS_SYNCHRONIZED = "nestedness_synchronized";
	public static final String NESTEDNESS_CONDITIONALS = "nestedness_conditionals";
	public static final String NESTEDNESS_LOOPS = "nestedness_loops";
}
