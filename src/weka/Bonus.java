package weka;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Bonus is defined as a pair of name and its (bonus)value. 
 * Further more, negative bonuses are possible.
 * 
 * @author Juergen Walter
 * 
 */
public class Bonus {
	static Collection<Bonus> instances= new ArrayList<Bonus>();
	private String name;
	private int value;
	
	/**
	 * default rules
	 */
	public static void createDefaultBonuses() {
		new Bonus("NOTIFY_CALLS=1", 4);
		new Bonus("NOTIFYALL_CALLS=1", 4);
		new Bonus("NOTIFY_CALLS=1", 4);
		new Bonus("=1", 3);

		new Bonus("YIELD_CALLS=1", 1);
		new Bonus("SLEEP_CALLS=1", 1);
		new Bonus("NESTEDNESS_LOCKS=1", 1);
		new Bonus("NESTEDNESS_SYNCHRONIZED=1", 1);
		new Bonus("NESTEDNESS_CONDITIONALS=1", 1);
		new Bonus("NESTEDNESS_LOOPS=1", 1);

		new Bonus("PUBLIC=1", -2);
	}

	/**
	 * constructor that adds instance to list instances
	 * @param name
	 * @param value
	 */
	public Bonus(String name, int value) {
		this.name = name;
		this.value = value;
		instances.add(this);
	}	
	
	/**
	 * creates default bonus system if no instances exist
	 * @return instances
	 */
	public static Collection<Bonus> getInstances() {
		if (instances.isEmpty()) {
			createDefaultBonuses();
		}
		return instances;
	}

	/**
	 * name + brings you + value + bonus
	 */
	public String toString() {
		return name + " brings you " + value + "bonus";
	}

	/**
	 * simple setter
	 * @param name, String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * simple getter
	 * @return name, String
	 */
	public String getName() {
		return name;
	}

	/**
	 * simple setter
	 * @param value, int
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * simple getter
	 * @return name, String
	 */
	public int getValue() {
		return value;
	}
}
