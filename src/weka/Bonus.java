package weka;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Bonus is defined as a pair of name and its (bonus)value. 
 * Further more, negative bonuses are possible.
 */
public class Bonus {
	private String name;
	private String value;
	private int bonus;
	
	/**
	 * constructor that adds instance to list instances
	 * @param name
	 * @param value
	 */
	public Bonus(String name, String value, int bonus) {
		this.name = name;
		this.value = value;
		this.bonus = bonus;
	}
	
	/**
	 * creates default bonus system if no instances exist
	 * @return instances
	 */
	public static Collection<Bonus> getSampleBonusSet() {
		Collection<Bonus> bonusSet = new ArrayList<Bonus>();
		
		bonusSet.add(new Bonus("NOTIFY_CALLS", "1", 4));
		bonusSet.add(new Bonus("NOTIFYALL_CALLS", "1", 4));
		bonusSet.add(new Bonus("NOTIFY_CALLS", "1", 4));
		bonusSet.add(new Bonus(".*", "1", 3));
		bonusSet.add(new Bonus("YIELD_CALLS", "1", 1));
		bonusSet.add(new Bonus("SLEEP_CALLS", "1", 1));
		bonusSet.add(new Bonus("NESTEDNESS_LOCKS", "1", 1));
		bonusSet.add(new Bonus("NESTEDNESS_SYNCHRONIZED", "1", 1));
		bonusSet.add(new Bonus("NESTEDNESS_CONDITIONALS", "1", 1));
		bonusSet.add(new Bonus("NESTEDNESS_LOOPS", "1", 1));
		bonusSet.add(new Bonus("PUBLIC", "1", -2));
		
		return bonusSet;
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
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * simple getter
	 * @return name, String
	 */
	public String getValue() {
		return value;
	}

	public int rate(String name, String value) {
		boolean match = name.matches(this.name) && value.matches(this.value);

		return match ? bonus : 0;
	}
}
