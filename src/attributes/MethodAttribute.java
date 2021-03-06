/**
 * @author Christian Wellenbrock
 * @author Florian Simon
 * @author J�rgen Walter
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
package attributes;

import database.Scope;

/**
 * This class stores all method {@code Scope} attribute names that can be used for mining.
 */
public class MethodAttribute {
	/** unique identifier used by R - Team09*/
	public static final Attribute COMBINED_METHOD_NAME = new Attribute(Scope.METHOD,
			"CONCAT(PROJECT_NAME,'->',CLASS_NAME,'->',METHOD_NAME," +
			"'(',CAST(RAND()*1000000 AS INT),')') AS NAME", false);
	
	public static final Attribute PROJECT_NAME = Attribute.PROJECT_NAME;
	public static final Attribute CLASS_NAME = Attribute.CLASS_NAME;
	public static final Attribute METHOD_NAME = Attribute.METHOD_NAME;
	
	// method attributes
	public static final Attribute PUBLIC_METHODS = Attribute.PUBLIC_METHODS;
	public static final Attribute PRIVATE_METHODS = Attribute.PRIVATE_METHODS;
	public static final Attribute SYNCHRONIZED_METHODS = Attribute.SYNCHRONIZED_METHODS;
	public static final Attribute METHOD_CALLS = Attribute.METHOD_CALLS;
	public static final Attribute SYNCHRONIZED_BLOCKS = Attribute.SYNCHRONIZED_BLOCKS;
	public static final Attribute LOCK_CALLS = Attribute.LOCK_CALLS;
	public static final Attribute UNLOCK_CALLS = Attribute.UNLOCK_CALLS;
	public static final Attribute WAIT_CALLS = Attribute.WAIT_CALLS;
	public static final Attribute NOTIFY_CALLS = Attribute.NOTIFY_CALLS;
	public static final Attribute NOTIFYALL_CALLS = Attribute.NOTIFYALL_CALLS;
	public static final Attribute SLEEP_CALLS = Attribute.SLEEP_CALLS;
	public static final Attribute YIELD_CALLS = Attribute.YIELD_CALLS;
	public static final Attribute JOIN_CALLS = Attribute.JOIN_CALLS;
	public static final Attribute START_CALLS = Attribute.START_CALLS;
	public static final Attribute RUN_CALLS = Attribute.RUN_CALLS;
	
	public static final Attribute LOCK_OBJECTS = Attribute.LOCK_OBJECTS;
	public static final Attribute COUNT_DOWN_OBJECTS = Attribute.COUNT_DOWN_OBJECTS;
	public static final Attribute CONDITION_OBJECTS = Attribute.CONDITION_OBJECTS;
	public static final Attribute SEMAPHORE_OBJECTS = Attribute.SEMAPHORE_OBJECTS;
	public static final Attribute CYCLICBARRIER_OBJECTS = Attribute.CYCLICBARRIER_OBJECTS;
	
	public static final Attribute NESTEDNESS_LOCKS = Attribute.NESTEDNESS_LOCKS;
	public static final Attribute NESTEDNESS_SYNCHRONIZED = Attribute.NESTEDNESS_SYNCHRONIZED;
	public static final Attribute NESTEDNESS_CONDITIONALS = Attribute.NESTEDNESS_CONDITIONALS;
	public static final Attribute NESTEDNESS_LOOPS = Attribute.NESTEDNESS_LOOPS;

	public static final Attribute WHILE_WAIT = Attribute.WHILE_WAIT;
	public static final Attribute CONDITIONAL_WAIT = Attribute.CONDITIONAL_WAIT;
	public static final Attribute DOUBLE_CHECKED_LOCK = Attribute.DOUBLE_CHECKED_LOCK;

}