package database;

import workers.CountNestednessOfLocks;
import workers.Counter;
import workers.RecursiveNestednessCounter;

public class Attributes {

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
	
	public static final String VOLATILE_VARIABLES = "volatile_variables";
	public static final String PUBLIC_VARIABLES = "public_variables";
	public static final String PRIVATE_VARIABLES = "private_variables";
	public static final String LOCK_OBJECT_FIELDS = "lock_object_fields";
	public static final String CONDITION_OBJECT_FIELDS = "condition_object_fields";
	public static final String EXECUTOR_OBJECT_FIELDS = "executor_object_fields";
	public static final String CONCURRENTMAP_OBJECT_FIELDS = "concurrentMap_object_fields";
	public static final String ATOMICINTEGER_OBJECT_FIELDS = "atomicInteger_object_fields";
	public static final String BLOCKINGQUEUE_OBJECT_FIELDS = "blockingQueue_object_fields";
	public static final String CONCURRENTLINKEDQUEUE_OBJECT_FIELDS = "concurrentLinkedQueue_object_fields";
	public static final String COPYONWRITEARRAYLIST_OBJECT_FIELDS = "copyOnWriteArrayList_object_fields";
	public static final String EXECUTORSERVICE_OBJECT_FIELDS = "executorService_object_fields";
	public static final String FUTURE_OBJECT_FIELDS = "future_object_fields";
	public static final String THREADPOOLEXECUTOR_OBJECT_FIELDS = "threadPoolExecutor_object_fields";
	public static final String COUNTDOWNLATCH_OBJECT_FIELDS = "countDownLatch_object_fields";
	public static final String CYCLICBARRIER_OBJECT_FIELDS = "cyclicBarrier_object_fields";
	public static final String EXCHANGER_OBJECT_FIELDS = "exchanger_object_fields";
	public static final String SEMAPHORE_OBJECT_FIELDS = "semaphore_object_fields";
	public static final String THREADFACTORY_OBJECT_FIELDS = "threadFactory_object_fields";
	
	public static final String CALLABLE_INTERFACE = "callable_interface";
	public static final String DELAYED_INTERFACE = "delayed_interface";
	public static final String THREADFACTORY_INTERFACE = "threadFactory_interface";
	public static final String BLOCKINGQUEUE_INTERFACE = "blockingQueue_interface";
	
	public static final String NESTEDNESS_LOCKS = "nestedness_locks";
	public static final String NESTEDNESS_SYNCHRONIZED = "nestedness_synchronized";
	public static final String NESTEDNESS_CONDITIONALS = "nestedness_conditionals";
	public static final String NESTEDNESS_LOOPS = "nestedness_loops";

	public static final String CONCURRENTMAP_OBJECTS_EXTENDS = "concurrentMap_objects_extends";
	public static final String EXECUTORSERVICE_OBJECTS_EXTENDS = "executorService_objects_extends";
	public static final String THREADPOOLEXECUTOR_OBJECTS_EXTENDS = "threadPoolExecutor_objects_extends";
	public static final String CYCLICBARRIER_OBJECTS_EXTENDS = "cyclicBarrier_objects_extends";
	public static final String EXCHANGER_OBJECTS_EXTENDS = "exchanger_objects_extends";
	public static final String SEMAPHORE_OBJECTS_EXTENDS = "semaphore_objects_extends";
	public static final String LOCK_OBJECTS_EXTENDS = "lock_objects_extends";
	public static final String EXECUTOR_OBJECTS_EXTENDS = "executor_objects_extends";
	public static final String ATOMICINTEGER_OBJECTS_EXTENDS = "atomicInteger_objects_extends";
	public static final String CONCURRENTLINKEDQUEUE_OBJECTS_EXTENDS = "concurrentLinkedQueue_objects_extends";
	public static final String COPYONWRITEARRAYLIST_OBJECTS_EXTENDS = "copyOnWriteArrayList_objects_extends";
	public static final String FUTURE_OBJECTS_EXTENDS = "future_objects_extends";
	public static final String COUNTDOWNLATCH_OBJECTS_EXTENDS = "countDownLatch_objects_extends";	
}
