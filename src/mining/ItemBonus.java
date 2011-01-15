package mining;

import java.util.ArrayList;
import java.util.Collection;

import weka.associations.ItemSet;
import attributes.Attribute;


/**
 * This class stores items for rule rating.
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
	 * Constructor
	 * @param name name of the item
	 * @param value value of the item
	 * @param bonus bonus value of item
	 */
	public ItemBonus(String name, String value, int bonus) {
		this.item = new Item(name,value);
		this.setBonus(bonus);
	}
	
	/**
	 * Convenient Constructor
	 * @param attribute
	 * @param value
	 * @param bonus
	 */
	public ItemBonus(Attribute attribute, String value, int bonus) {
		this(attribute.getName(), value, bonus);
	}

	/**
	 * prints item name + bonus
	 */
	public String toString() {
		return item.getName() + Bonus.toString + item.getValue() + "\n";
	}
	
	/**
	 * creates default bonus system if no instances exist
	 * @return a collection of {@code Bonus}
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
	 * sets name of the item
	 * @param name name of the item
	 */
	public void setName(String name) {
		this.item.setName(name);
	}

	/**
	 * gets name of the item
	 * @return name of the item
	 */
	public String getName() {
		return this.item.getName();
	}

	/**
	 * sets the value of the item
	 * @param value new name of the ItemBonus Item
	 */
	public void setValue(String value) {
		this.item.setValue(value);
	}

	/**
	 * gets value of the item
	 * @return the name of the item 
	 */
	public String getValue() {
		return item.getValue();
	}
	
	/**
	 * gives bonus for items
	 * @param rule the rule to be rated
	 */
	public int rate(Rule rule) {
		int rating = 0;
		
		rating += rateCondition(rule);
		rating += rateConsequence(rule);
		
		return rating;
	}
	
	/**
	 * gives bonus on condition
	 * @param rule the rule to be rated
	 * @return given bonus
	 */
	private int rateCondition(Rule rule) {
		return rateItemSet(rule, rule.getConditionItems());
	}
	
	/**
	 * gives bonus on consequence
	 * @param rule the rule to be rated
	 * @return given bonus
	 */
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
	
	/**
	 * calculates rating for an item
	 * @param name name of item
	 * @param value value of item 
	 * @return rating of item
	 */
	public int rateItem(String name, String value) {
		boolean match = name.matches(this.getName()) && value.matches(this.getValue());

		return match ? getBonus() : 0;
	}
}