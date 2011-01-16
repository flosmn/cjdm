package attributes;

import java.util.HashMap;

import database.Scope;

/**
 * This class stores all attribute names that can be used for mining.
 */
public class Attribute {
	private Scope scope;
	private String name;
	private boolean parallel;
	
	private static HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
	
	public static String combine(Object ... objects) {
		String result = "";
		for (Object object: objects) {
			String attributeName = object.toString();
			
			if (!result.equals("") && !attributeName.equals("")) {
				result += ", ";
			}
			
			result += attributeName;
		}
		
		return result;
	}
	
	public Attribute(Scope scope, String name, boolean parallel) {
		this.scope = scope;
		this.name = name;
		this.parallel = parallel;
		
		attributes.put(name, this);
	}
	
	public static Attribute getAttribute(String name) {
		return attributes.get(name);
	}

	public Scope getScope() {
		return scope;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return getName();
	}
	
	public boolean isParallel() {
		return parallel;
	}

	public static final Attribute PROJECT_NAME = new Attribute(Scope.PROJECT, "PROJECT_NAME", false);
	public static final Attribute CLASS_NAME = new Attribute(Scope.METHOD, "CLASS_NAME", false);
	public static final Attribute METHOD_NAME = new Attribute(Scope.CLASS, "METHOD_NAME", false);
	
	public static final Attribute PUBLIC_METHODS = new Attribute(Scope.METHOD, "PUBLIC_METHODS", false);
	public static final Attribute PRIVATE_METHODS = new Attribute(Scope.METHOD, "PRIVATE_METHODS", false);
	public static final Attribute SYNCHRONIZED_METHODS = new Attribute(Scope.METHOD, "SYNCHRONIZED_METHODS", true);
	
	public static final Attribute METHOD_CALLS = new Attribute(Scope.METHOD, "METHOD_CALLS", false);
	public static final Attribute SYNCHRONIZED_BLOCKS = new Attribute(Scope.METHOD, "SYNCHRONIZED_BLOCKS", true);
	
	public static final Attribute WHILE_WAIT = new Attribute(Scope.METHOD, "WHILE_WAIT", true);
	public static final Attribute CONDITIONAL_WAIT = new Attribute(Scope.METHOD, "CONDITIONAL_WAIT", true);
	public static final Attribute DOUBLE_CHECKED_LOCK = new Attribute(Scope.METHOD, "DOUBLE_CHECKED_LOCK", true);;

	public static final Attribute LOCK_CALLS = new Attribute(Scope.METHOD, "LOCK_CALLS", true);
	public static final Attribute UNLOCK_CALLS= new Attribute(Scope.METHOD, "UNLOCK_CALLS", true);
	public static final Attribute WAIT_CALLS = new Attribute(Scope.METHOD, "WAIT_CALLS", true);
	public static final Attribute NOTIFY_CALLS = new Attribute(Scope.METHOD, "NOTIFY_CALLS", true);
	public static final Attribute NOTIFYALL_CALLS = new Attribute(Scope.METHOD, "NOTIFYALL_CALLS", true);
	public static final Attribute SLEEP_CALLS = new Attribute(Scope.METHOD, "SLEEP_CALLS", true);
	public static final Attribute YIELD_CALLS = new Attribute(Scope.METHOD, "YIELD_CALLS", true);
	public static final Attribute JOIN_CALLS = new Attribute(Scope.METHOD, "JOIN_CALLS", true);
	public static final Attribute START_CALLS = new Attribute(Scope.METHOD, "START_CALLS", true);
	public static final Attribute RUN_CALLS = new Attribute(Scope.METHOD, "RUN_CALLS", true);
	
	public static final Attribute LOCK_OBJECTS = new Attribute(Scope.METHOD, "LOCK_OBJECTS", true);
	public static final Attribute COUNT_DOWN_OBJECTS = new Attribute(Scope.METHOD, "COUNTDOWNLATCH_OBJECTS", true);
	public static final Attribute CONDITION_OBJECTS = new Attribute(Scope.METHOD, "CONDITION_OBJECTS", true);
	public static final Attribute SEMAPHORE_OBJECTS = new Attribute(Scope.METHOD, "SEMAPHORE_OBJECTS", true);
	public static final Attribute CYCLICBARRIER_OBJECTS = new Attribute(Scope.METHOD, "CYCLICBARRIER_OBJECTS", true);
	
	public static final Attribute NESTEDNESS_LOCKS = new Attribute(Scope.METHOD, "NESTEDNESS_LOCKS", true);
	public static final Attribute NESTEDNESS_SYNCHRONIZED = new Attribute(Scope.METHOD, "NESTEDNESS_SYNCHRONIZED", true);
	public static final Attribute NESTEDNESS_CONDITIONALS = new Attribute(Scope.METHOD, "NESTEDNESS_CONDITIONALS", false);
	public static final Attribute NESTEDNESS_LOOPS = new Attribute(Scope.METHOD, "NESTEDNESS_LOOPS", false);
	
	public static final Attribute NUMBER_OF_METHODS = new Attribute(Scope.CLASS, "NUMBER_OF_METHODS", true);
	public static final Attribute VOLATILE_VARIABLES = new Attribute(Scope.CLASS, "VOLATILE_VARIABLES", true);
	public static final Attribute PUBLIC_VARIABLES = new Attribute(Scope.CLASS, "PUBLIC_VARIABLES", true);
	public static final Attribute PRIVATE_VARIABLES = new Attribute(Scope.CLASS, "PRIVATE_VARIABLES", true);
	public static final Attribute LOCK_OBJECT_FIELDS = new Attribute(Scope.CLASS, "LOCK_OBJECT_FIELDS", true);
	public static final Attribute CONDITION_OBJECT_FIELDS = new Attribute(Scope.CLASS, "CONDITION_OBJECT_FIELDS", true);
	public static final Attribute EXECUTOR_OBJECT_FIELDS = new Attribute(Scope.CLASS, "EXECUTOR_OBJECT_FIELDS", true);
	public static final Attribute CONCURRENTMAP_OBJECT_FIELDS = new Attribute(Scope.CLASS, "CONCURRENTMAP_OBJECT_FIELDS", true);
	public static final Attribute ATOMICINTEGER_OBJECT_FIELDS = new Attribute(Scope.CLASS, "ATOMICINTEGER_OBJECT_FIELDS", true);
	public static final Attribute BLOCKINGQUEUE_OBJECT_FIELDS = new Attribute(Scope.CLASS, "BLOCKINGQUEUE_OBJECT_FIELDS", true);
	public static final Attribute CONCURRENTLINKEDQUEUE_OBJECT_FIELDS = new Attribute(Scope.CLASS, "CONCURRENTLINKEDQUEUE_OBJECT_FIELDS", true);
	public static final Attribute COPYONWRITEARRAYLIST_OBJECT_FIELDS = new Attribute(Scope.CLASS, "COPYONWRITEARRAYLIST_OBJECT_FIELDS", true);
	public static final Attribute EXECUTORSERVICE_OBJECT_FIELDS = new Attribute(Scope.CLASS, "EXECUTORSERVICE_OBJECT_FIELDS", true);
	public static final Attribute FUTURE_OBJECT_FIELDS = new Attribute(Scope.CLASS, "FUTURE_OBJECT_FIELDS", true);
	public static final Attribute THREADPOOLEXECUTOR_OBJECT_FIELDS = new Attribute(Scope.CLASS, "THREADPOOLEXECUTOR_OBJECT_FIELDS", true);
	public static final Attribute COUNTDOWNLATCH_OBJECT_FIELDS = new Attribute(Scope.CLASS, "COUNTDOWNLATCH_OBJECT_FIELDS", true);
	public static final Attribute CYCLICBARRIER_OBJECT_FIELDS = new Attribute(Scope.CLASS, "CYCLICBARRIER_OBJECT_FIELDS", true);
	public static final Attribute EXCHANGER_OBJECT_FIELDS = new Attribute(Scope.CLASS, "EXCHANGER_OBJECT_FIELDS", true);
	public static final Attribute SEMAPHORE_OBJECT_FIELDS = new Attribute(Scope.CLASS, "SEMAPHORE_OBJECT_FIELDS", true);
	public static final Attribute THREADFACTORY_OBJECT_FIELDS = new Attribute(Scope.CLASS, "THREADFACTORY_OBJECT_FIELDS", true);
	
	public static final Attribute CALLABLE_INTERFACE = new Attribute(Scope.CLASS, "CALLABLE_INTERFACE", true);
	public static final Attribute DELAYED_INTERFACE = new Attribute(Scope.CLASS, "DELAYED_INTERFACE", true);
	public static final Attribute THREADFACTORY_INTERFACE = new Attribute(Scope.CLASS, "THREADFACTORY_INTERFACE", true);
	public static final Attribute BLOCKINGQUEUE_INTERFACE = new Attribute(Scope.CLASS, "BLOCKINGQUEUE_INTERFACE", true);
	
	public static final Attribute CONCURRENTMAP_OBJECTS_EXTENDS = new Attribute(Scope.CLASS, "CONCURRENTMAP_OBJECTS_EXTENDS", true);
	public static final Attribute EXECUTORSERVICE_OBJECTS_EXTENDS = new Attribute(Scope.CLASS, "EXECUTORSERVICE_OBJECTS_EXTENDS", true);
	public static final Attribute THREADPOOLEXECUTOR_OBJECTS_EXTENDS = new Attribute(Scope.CLASS, "THREADPOOLEXECUTOR_OBJECTS_EXTENDS", true);
	public static final Attribute CYCLICBARRIER_OBJECTS_EXTENDS = new Attribute(Scope.CLASS, "CYCLICBARRIER_OBJECTS_EXTENDS", true);
	public static final Attribute EXCHANGER_OBJECTS_EXTENDS = new Attribute(Scope.CLASS, "EXCHANGER_OBJECTS_EXTENDS", true);
	public static final Attribute SEMAPHORE_OBJECTS_EXTENDS = new Attribute(Scope.CLASS, "SEMAPHORE_OBJECTS_EXTENDS", true);
	public static final Attribute LOCK_OBJECTS_EXTENDS = new Attribute(Scope.CLASS, "LOCK_OBJECTS_EXTENDS", true);
	public static final Attribute EXECUTOR_OBJECTS_EXTENDS = new Attribute(Scope.CLASS, "EXECUTOR_OBJECTS_EXTENDS", true);
	public static final Attribute ATOMICINTEGER_OBJECTS_EXTENDS = new Attribute(Scope.CLASS, "ATOMICINTEGER_OBJECTS_EXTENDS", true);
	public static final Attribute CONCURRENTLINKEDQUEUE_OBJECTS_EXTENDS = new Attribute(Scope.CLASS, "CONCURRENTLINKEDQUEUE_OBJECTS_EXTENDS", true);
	public static final Attribute COPYONWRITEARRAYLIST_OBJECTS_EXTENDS = new Attribute(Scope.CLASS, "COPYONWRITEARRAYLIST_OBJECTS_EXTENDS", true);
	public static final Attribute FUTURE_OBJECTS_EXTENDS = new Attribute(Scope.CLASS, "FUTURE_OBJECTS_EXTENDS", true);
	public static final Attribute COUNTDOWNLATCH_OBJECTS_EXTENDS = new Attribute(Scope.CLASS, "COUNTDOWNLATCH_OBJECTS_EXTENDS", true);
	
	public static String getAllSynchronizedAttributes() {
		return Attribute.combine(SYNCHRONIZED_METHODS, 
				SYNCHRONIZED_BLOCKS);
	}
	
	public static String getAllPatternAttributes() {
		return Attribute.combine(
				WHILE_WAIT, 
				CONDITIONAL_WAIT,
				DOUBLE_CHECKED_LOCK);
	}
	
	public static String getAllMethodCallAttributes() {
		return Attribute.combine(LOCK_CALLS, 
				UNLOCK_CALLS,
				WAIT_CALLS,
				NOTIFY_CALLS,
				NOTIFYALL_CALLS,
				SLEEP_CALLS,
				YIELD_CALLS,
				JOIN_CALLS,
				START_CALLS,
				RUN_CALLS);
	}
	
	public static String getAllObjectAttributes() {
		return Attribute.combine(LOCK_OBJECTS, 
				COUNT_DOWN_OBJECTS,
				CONDITION_OBJECTS,
				SEMAPHORE_OBJECTS,
				CYCLICBARRIER_OBJECTS);
	}
	
	public static String getAllNestednessAttributes() {
		return Attribute.combine(NESTEDNESS_LOCKS, 
				NESTEDNESS_SYNCHRONIZED,
				NESTEDNESS_CONDITIONALS,
				NESTEDNESS_LOOPS);
	}
	
	public static String getAllObjectFieldsAttributes() {
		return Attribute.combine(LOCK_OBJECT_FIELDS, 
				CONDITION_OBJECT_FIELDS,
				EXECUTOR_OBJECT_FIELDS,
				CONCURRENTMAP_OBJECT_FIELDS,
				ATOMICINTEGER_OBJECT_FIELDS,
				BLOCKINGQUEUE_OBJECT_FIELDS,
				CONCURRENTLINKEDQUEUE_OBJECT_FIELDS,
				COPYONWRITEARRAYLIST_OBJECT_FIELDS,
				EXECUTORSERVICE_OBJECT_FIELDS,
				FUTURE_OBJECT_FIELDS,
				THREADPOOLEXECUTOR_OBJECT_FIELDS,
				COUNTDOWNLATCH_OBJECT_FIELDS,
				CYCLICBARRIER_OBJECT_FIELDS,
				EXCHANGER_OBJECT_FIELDS,
				SEMAPHORE_OBJECT_FIELDS,
				THREADFACTORY_OBJECT_FIELDS);
	}
	
	public static String getAllInterfaceAttributes() {
		return Attribute.combine(CALLABLE_INTERFACE, 
				DELAYED_INTERFACE,
				THREADFACTORY_INTERFACE,
				BLOCKINGQUEUE_INTERFACE);
	}
	
	public static String getAllObjectExtendsAttributes() {
		return Attribute.combine(CONCURRENTMAP_OBJECTS_EXTENDS, 
				EXECUTORSERVICE_OBJECTS_EXTENDS,
				THREADPOOLEXECUTOR_OBJECTS_EXTENDS,
				CYCLICBARRIER_OBJECTS_EXTENDS,
				EXCHANGER_OBJECTS_EXTENDS,
				SEMAPHORE_OBJECTS_EXTENDS,
				LOCK_OBJECTS_EXTENDS,
				EXECUTOR_OBJECTS_EXTENDS,
				ATOMICINTEGER_OBJECTS_EXTENDS,
				CONCURRENTLINKEDQUEUE_OBJECTS_EXTENDS,
				COPYONWRITEARRAYLIST_OBJECTS_EXTENDS,
				FUTURE_OBJECTS_EXTENDS,
				COUNTDOWNLATCH_OBJECTS_EXTENDS);
	}

}
