package weka.util;

import java.util.Collection;
import java.util.LinkedList;

import weka.associations.ItemSet;


/**
 * PatternBonus stores rules for giving bonus on patterns
 * 
 * e.g.
 * A=X and B=Y ==> C=Y and D=""  has bonus 10
 * A=X and B=Y ==> ""=""         has bonus 2
 * 
 * PatternBonus consists of a condition collection, a consequence 
 * collection and its bonus value 
 * 
 * @see Bonus
 */
public class PatternBonus extends Bonus {
	public Collection<Item> conditions;
	public Collection<Item> consequences;

	/**
	 * constructor
	 * @param cond
	 * @param cons
	 * @param bonus
	 */
	public PatternBonus(Collection<Item> cond, Collection<Item> cons, int bonus) {
		this.conditions = cond;
		this.consequences = cons;
		this.setBonus(bonus);
	}

	public PatternBonus(Item cond, Item cons, int bonus) {
		this(packageItem(cond), packageItem(cons), bonus);
	}


	/**
	 * toString
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Item i : conditions) {
			sb.append(i.getName() + " " + i.getValue());
		}
		sb.append("==>");
		for (Item i : consequences) {
			sb.append(i.getName() + " " + i.getValue());
		}
		sb.append(Bonus.toString + getBonus() + "\n");
		return sb.toString();
	}

	/**
	 * @return a bonus set for patterns
	 */
	public static Collection<Bonus> getSampleBonusSet() {
		return getSampleMethodBonusSet();
	}

	/**
	 * @return a bonus set for patterns
	 */
	public static Collection<Bonus> getSampleMethodBonusSet() {
		Collection<Bonus> bonusSet = new LinkedList<Bonus>();
		//public vs private filter 
		bonusSet.add(new PatternBonus(
				new Item("PUBLIC_METHODS", "1"),
				new Item("PRIVATE_METHODS", "0"), -10));
		bonusSet.add(new PatternBonus(
				new Item("PUBLIC_METHODS", "0"),
				new Item("PRIVATE_METHODS", "1"), -10));
		bonusSet.add(new PatternBonus(
				new Item("PRIVATE_METHODS", "1"),
				new Item("PUBLIC_METHODS", "0"), -10));
		bonusSet.add(new PatternBonus(
				new Item("PRIVATE_METHODS", "0"),
				new Item("PUBLIC_METHODS", "1"), -10));

		/**
		 * A=Z and B=X ==> "" is possible
		 * A=X and B=Y ==> C="" is possible
		 */
		Collection<Item> temp = new LinkedList<Item>();
		temp.add(new Item(".*", ".*"));
		bonusSet.add(new PatternBonus(temp, packageItem(new Item("LOCK_CALLS", ".*")), 30));
		bonusSet.add(new PatternBonus(twoItems("SYNCHRONIZED_METHODS","1","","1"), packageItem(new Item(".*",".*")), 11));
		return bonusSet;
	}
	
	/**
	 * give a bonus on special patterns
	 * e.g. @see PatternBonus
	 * @param rule
	 */
	public int rate(Rule rule) {
		String conditionString = rule.getConditionItems().toString(rule.getInstances());
		String consequenceString = rule.getConsequenceItems().toString(rule.getInstances());

		if (!matches(conditionString, conditions)) return 0;
		if (!matches(consequenceString, consequences)) return 0;
		
		return getBonus();
	}
	
	private boolean matches(String itemSetString, Collection<Item> patternItems) {
		String string = itemSetString;
		for (Item item : patternItems){
			if (!string.matches(item.getName()+"="+item.getValue())){
				return false;
			}	
		}
		return true;
	}
	
	/**
	 * little helper method
	 * @param name
	 * @param value
	 * @return
	 */
	private static Collection<Item> packageItem(Item item) {
		Collection<Item> collection = new LinkedList<Item>();
		collection.add(item);
		return collection;
	}
	
	/**
	 * little helper method
	 * @param name
	 * @param value
	 * @return
	 */
	private static Collection<Item> twoItems(String name, String value,String name2, String value2) {
		Collection<Item> collection = new LinkedList<Item>();
		collection.add(new Item(name, value));
		collection.add(new Item(name2, value2));
		return collection;
	}



}