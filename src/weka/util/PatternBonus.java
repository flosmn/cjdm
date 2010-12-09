package weka.util;

import java.util.Collection;
import java.util.LinkedList;


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
	public Collection<Item> cond;
	public Collection<Item> cons;

	/**
	 * constructor
	 * @param cond
	 * @param cons
	 * @param bonus
	 */
	public PatternBonus(Collection<Item> cond, Collection<Item> cons, int bonus) {
		this.cond = cond;
		this.cons = cons;
		this.setBonus(bonus);
	}

	/**
	 * toString
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Item i : cond) {
			sb.append(i.getName() + " " + i.getValue());
		}
		sb.append("==>");
		for (Item i : cons) {
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
		bonusSet.add(new PatternBonus(newItem("PUBLIC_METHODS", "1"), newItem(
				"PRIVATE_METHODS", "0"), -10));
		bonusSet.add(new PatternBonus(newItem("PUBLIC_METHODS", "0"), newItem(
				"PRIVATE_METHODS", "1"), -10));
		bonusSet.add(new PatternBonus(newItem("PRIVATE_METHODS", "1"), newItem(
				"PUBLIC_METHODS", "0"), -10));
		bonusSet.add(new PatternBonus(newItem("PRIVATE_METHODS", "0"), newItem(
				"PUBLIC_METHODS", "1"), -10));

		/**
		 * A=Z and B=X ==> "" is possible
		 * A=X and B=Y ==> C="" is possible
		 */
		Collection<Item> temp = new LinkedList<Item>();
		temp.add(new Item("PUBLIC_METHODS", "1"));
		temp.add(new Item("METHOD_CALLS", "0"));
		bonusSet.add(new PatternBonus(temp, newItem("SYNCHRONIZED_METHODS", ""), 30));
		bonusSet.add(new PatternBonus(twoItems("SYNCHRONIZED_METHODS","1","","1"), newItem("",""), 11));
		return bonusSet;
	}
	
	/**
	 * give a bonus on special patterns
	 * e.g. @see PatternBonus
	 * @param rule
	 */
	public static void ratePattern(Rule rule, Collection<PatternBonus> bonusSet) {
		for (PatternBonus b : bonusSet){
			boolean flag1 = true;
			boolean flag2 = true;
			String condString = rule.getCondition().toString(rule.getInstances());
			String consString = rule.getConsequence().toString(rule.getInstances());
			for (Item c : b.cond){
				if (c.getValue()==""){
					if (c.getName()==""){
						continue;
					}else{
						if (!consString.contains(c.getName()) ){
							flag2 = false;
							continue;
						}						
					}
				}
				if (!condString.contains(c.getName()+"="+c.getValue())){
					flag1 = false;
					continue;
				}	
			}
			for (Item c : b.cons){
				if (c.getValue()==""){
					if (c.getName()==""){
						continue;
					}else{
						if (!consString.contains(c.getName()) ){
							flag2 = false;
							continue;
						}						
					}
				}
				if (!consString.contains(c.getName()+"="+c.getValue()) ){
					flag2 = false;
					continue;
				}
			}
			if (flag1 && flag2){
				rule.setRating(rule.getRating()+b.getBonus());
			}
		}
	}
	
	/**
	 * little helper method
	 * @param name
	 * @param value
	 * @return
	 */
	private static Collection<Item> newItem(String name, String value) {
		Collection<Item> collection = new LinkedList<Item>();
		collection.add(new Item(name, value));
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