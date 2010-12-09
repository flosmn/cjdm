package attributes;

import database.Scope;

public class Attribute {
	public static String combine(String first, Attribute ... attributes) {
		for (Attribute attribute : attributes) {
			String attributeName = attribute.getName();
			
			if (!first.equals("") && !attributeName.equals("")) {
				first += ", ";
			}
			
			first += attributeName;
		}
		
		return first;
	}
	
	public static String combine(Attribute ... attributes) {
		return combine("", attributes);
	}

	private Scope scope;
	private String name;
	private boolean parallel;
	
	private Attribute(Scope scope, String name, boolean parallel) {
		this.scope = scope;
		this.name = name;
		this.parallel = parallel;
	}
	
	public Scope getScope() {
		return scope;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isParallel() {
		return parallel;
	}

	public static final Attribute PROJECT_NAME = new Attribute(Scope.PROJECT, "PROJECT_NAME", true);
	public static final Attribute CLASS_NAME = new Attribute(Scope.METHOD, "CLASS_NAME", true);
	public static final Attribute METHOD_NAME = new Attribute(Scope.CLASS, "METHOD_NAME", true);
	
	public static final Attribute PUBLIC_METHODS = new Attribute(Scope.METHOD, "PUBLIC_METHODS", false);
	public static final Attribute PRIVATE_METHODS = new Attribute(Scope.METHOD, "PRIVATE_METHODS", false);
	public static final Attribute SYNCHRONIZED_METHODS = new Attribute(Scope.METHOD, "SYNCHRONIZED_METHODS", true);
	public static final Attribute METHOD_CALLS = new Attribute(Scope.METHOD, "METHOD_CALLS", false);
	public static final Attribute SYNCHRONIZED_BLOCKS = new Attribute(Scope.METHOD, "SYNCHRONIZED_BLOCKS", true);
	public static final Attribute LOCK_CALLS = new Attribute(Scope.METHOD, "LOCK_CALLS", true);
	public static final Attribute UNLOCK_CALLS= new Attribute(Scope.METHOD, "UNLOCK_CALLS", true);
	public static final Attribute WAIT_CALLS = new Attribute(Scope.METHOD, "WAIT_CALLS", true);
	public static final Attribute NOTIFY_CALLS = new Attribute(Scope.METHOD, "NOTIFY_CALLS", true);
	public static final Attribute NOTIFYALL_CALLS = new Attribute(Scope.METHOD, "NOTIFYALL_CALLS", true);
	public static final Attribute SLEEP_CALLS = new Attribute(Scope.METHOD, "SLEEP_CALLS", true);
	public static final Attribute YIELD_CALLS = new Attribute(Scope.METHOD, "YIELD_CALLS", true);
	public static final Attribute JOIN_CALLS = new Attribute(Scope.METHOD, "JOIN_CALLS", true);
	
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
}
