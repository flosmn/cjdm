package attributes;

public class MethodAttributes extends Attributes {
	public static final String PUBLIC_METHODS = "PUBLIC_METHODS";
	public static final String PRIVATE_METHODS = "PRIVATE_METHODS";
	public static final String SYNCHRONIZED_METHODS = "SYNCHRONIZED_METHODS";
	public static final String METHOD_CALLS = "METHOD_CALLS";
	public static final String NUMBER_OF_METHODS = "NUMBER_OF_METHODS";
	public static final String SYNCHRONIZED_BLOCKS = "SYNCHRONIZED_BLOCKS";
	public static final String LOCK_CALLS = "LOCK_CALLS";
	public static final String UNLOCK_CALLS= "UNLOCK_CALLS";
	public static final String WAIT_CALLS = "WAIT_CALLS";
	public static final String NOTIFY_CALLS = "NOTIFY_CALLS";
	public static final String NOTIFYALL_CALLS = "NOTIFYALL_CALLS";
	public static final String SLEEP_CALLS = "SLEEP_CALLS";
	public static final String YIELD_CALLS = "YIELD_CALLS";
	public static final String JOIN_CALLS = "JOIN_CALLS";
	
	public static final String LOCK_OBJECTS = "LOCK_OBJECTS";
	public static final String COUNT_DOWN_OBJECTS = "COUNTDOWNLATCH_OBJECTS";
	public static final String CONDITION_OBJECTS = "CONDITION_OBJECTS";
	public static final String SEMAPHORE_OBJECTS = "SEMAPHORE_OBJECTS";
	public static final String CYCLICBARRIER_OBJECTS = "CYCLICBARRIER_OBJECTS";
	
	public static final String NESTEDNESS_LOCKS = "NESTEDNESS_LOCKS";
	public static final String NESTEDNESS_SYNCHRONIZED = "NESTEDNESS_SYNCHRONIZED";
	public static final String NESTEDNESS_CONDITIONALS = "NESTEDNESS_CONDITIONALS";
	public static final String NESTEDNESS_LOOPS = "NESTEDNESS_LOOPS";
}
