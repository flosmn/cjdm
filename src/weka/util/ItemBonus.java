package weka.util;

import java.util.ArrayList;
import java.util.Collection;

import weka.associations.ItemSet;


/**
 * ItemBonus stores rules for giving bonus on items
 *  
 * ItemBonus is defined as a pair of name and its (bonus)value. 
 * e.g. X=1  has bonus 3 OR Y=0 has bonus -3
 * 
 * nice to know:
 * default bonusSets are available, negative bonuses are possible.
 * 
 * @see Bonus
 */
public class ItemBonus extends Bonus{
	public Item item;

	/**
	 * constructor that adds instance to list instances
	 * @param name
	 * @param value
	 * @param bonus
	 */
	public ItemBonus(String name, String value, int bonus) {
		this.item = new Item(name,value);
		this.setBonus(bonus);
	}
	
	/**
	 * name + brings you + value + bonus
	 */
	public String toString() {
		return item.getName() + Bonus.toString + item.getValue() + "\n";
	}
	
	/**
	 * creates default bonus system if no instances exist
	 * @return instances
	 */
	public static Collection<Bonus> getSampleBonusSet() {
		Collection<Bonus> bonusSet = new ArrayList<Bonus>();
		
		bonusSet.add(new ItemBonus("NOTIFY_CALLS", "1", 4));
		bonusSet.add(new ItemBonus("NOTIFYALL_CALLS", "1", 4));
		bonusSet.add(new ItemBonus("NOTIFY_CALLS", "1", 4));
		bonusSet.add(new ItemBonus(".*", "1", 3));
		bonusSet.add(new ItemBonus("YIELD_CALLS", "1", 1));
		bonusSet.add(new ItemBonus("SLEEP_CALLS", "1", 1));
		bonusSet.add(new ItemBonus("NESTEDNESS_LOCKS", "1", 1));
		bonusSet.add(new ItemBonus("NESTEDNESS_SYNCHRONIZED", "1", 1));
		bonusSet.add(new ItemBonus("NESTEDNESS_CONDITIONALS", "1", 1));
		bonusSet.add(new ItemBonus("NESTEDNESS_LOOPS", "1", 1));
		bonusSet.add(new ItemBonus("PUBLIC", "1", -2));
		bonusSet.add(new ItemBonus("LOCK_CALLS", "0", 3));
		
		return bonusSet;
	}
	
	/**
	 * simple setter
	 * @param name, String
	 */
	public void setName(String name) {
		this.item.setName(name);
	}

	/**
	 * simple getter
	 * @return name, String
	 */
	public String getName() {
		return this.item.getName();
	}

	/**
	 * simple setter
	 * @param value, int
	 */
	public void setValue(String value) {
		this.item.setValue(value);
	}

	/**
	 * simple getter
	 * @return name, String
	 */
	public String getValue() {
		return item.getValue();
	}
	
	/**
	 * give bonus for items
	 * e.g. SYNCHRONIZED=1
	 * @param itemSet
	 * @param rule
	 */
	public int rate(Rule rule) {
		int rating = 0;
		
		rating += rateCondition(rule);
		rating += rateConsequence(rule);
		
		return rating;
	}
	
	private int rateCondition(Rule rule) {
		return rateItemSet(rule, rule.getConditionItems());
	}
	
	private int rateConsequence(Rule rule) {
		return rateItemSet(rule, rule.getConsequenceItems());
	}

	private int rateItemSet(Rule rule, ItemSet itemSet) {
		int rating = 0;
		
		int[] items = itemSet.items();
		for (int i = 0; i < rule.getInstances().numAttributes(); i++) {
			if (items[i] != -1) {
				String name = rule.getInstances().attribute(i).name();
				String value = rule.getInstances().attribute(i).value(items[i]);
				rating += rateItem(name, value);
			}
		}
		
		return rating;
	}
	
	public int rateItem(String name, String value) {
		boolean match = name.matches(this.getName()) && value.matches(this.getValue());

		return match ? getBonus() : 0;
	}
}