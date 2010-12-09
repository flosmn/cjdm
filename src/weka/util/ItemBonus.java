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
	public static void rate(Rule rule, Collection<ItemBonus> itemBonusSet) {
		rateCondition(rule, itemBonusSet);
		rateConsequence(rule, itemBonusSet);
	}
	
	private static void rateCondition(Rule rule, Collection<ItemBonus> itemBonusSet) {
		rateItemSet(rule, rule.getCondition(), itemBonusSet);
	}
	
	private static void rateConsequence(Rule rule, Collection<ItemBonus> itemBonusSet) {
		rateItemSet(rule, rule.getConsequence(), itemBonusSet);
	}

	private static void rateItemSet(Rule rule, ItemSet itemSet, Collection<ItemBonus> itemBonusSet) {
		int[] items = itemSet.items();
		for (int i = 0; i < rule.getInstances().numAttributes(); i++) {
			if (items[i] != -1) {
				String name = rule.getInstances().attribute(i).name();
				String value = rule.getInstances().attribute(i).value(items[i]);
				rateItem(name, value, rule, itemBonusSet);
			}
		}
	}
	/**
	 * add one item bonus to a rule
	 * @see ItemBonus
	 * @param name
	 * @param value
	 * @param bonusSet
	 * @param rule
	 */
	private static void rateItem(String name, String value, Rule rule, Collection<ItemBonus> itemBonusSet) {
		for (ItemBonus bonus : itemBonusSet) {
			rule.setRating(rule.getRating()+ bonus.rate(name, value));
		}
	}

	public int rate(String name, String value) {
		boolean match = name.matches(this.item.getName()) && value.matches(this.item.getValue());

		return match ? getBonus() : 0;
	}
}