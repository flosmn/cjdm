/**
Ê* @author Christian Wellenbrock
Ê* @author JŸrgen Walter
Ê* Team 10
Ê*
Ê* This code has been developed during the winter term 2010-2011 at the
Ê* Karlsruhe Institute of Technology (KIT), Germany.
Ê* It is part of a project assignment in the course
Ê* "Multicore Programming in Practice: Tools, Models, and Languages".
Ê* Project director/instructor:
Ê* Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package mining;

import java.util.Collection;
import java.util.List;

import weka.associations.ItemSet;
import weka.core.Instances;

/**
 * A rule contains a condition, a consequence, a confidence and a rating that is
 * calculated during creation
 */
public class Rule implements Comparable<Rule> {
	private ItemSet conditionItems;
	private ItemSet consequenceItems;
	private double confidence;
	private Integer rating;
	private Instances instances;

	/**
	 * constructor
	 * @param condition
	 * @param consequence
	 * @param confidence
	 */
	public Rule(ItemSet condition, ItemSet consequence,
			double confidence, Instances instances) {
		this.setConditionItems(condition);
		this.setConsequenceItems(consequence);
		this.confidence = confidence;
		this.instances = instances;
	}
	
	/**
	 * rating : condition ==> consequence  confidence(confidence)
	 */
	public String toString() {
		return rating + " : "
				+ getConditionItems().toString(instances) + " ==> "
				+ getConsequenceItems().toString(instances)
				+ "  confidence(" + confidence + ")";
	}
	
	/**
	 * rating = 0
	 */
	public void resetRating() {
		this.rating = 0;
	}

	/**
	 * rating += rating of bonusSet
	 * @param bonusSet
	 */
	public void addRating(Collection<Bonus> bonusSet) {
		for (Bonus bonus : bonusSet) {
			this.rating += bonus.rate(this);
		}
	}

	/**
	 * simple getter
	 */
	public Integer getRating() {
		return rating;
	}

	/**
	 * simple setter
	 */
	public void setConditionItems(ItemSet conditionItems) {
		this.conditionItems = conditionItems;
	}
	
	/**
	 * simple getter
	 */
	public ItemSet getConditionItems() {
		return conditionItems;
	}
	
	/**
	 * simple setter
	 */
	public void setConsequenceItems(ItemSet consequenceItems) {
		this.consequenceItems = consequenceItems;
	}
	
	/**
	 * simple getter
	 */
	public ItemSet getConsequenceItems() {
		return consequenceItems;
	}	

	/**
	 * simple getter
	 */
	public Instances getInstances() {
		return instances;
	}
	
	@Override
	public int compareTo(Rule o) {
		return o.rating.compareTo(rating);
	}

	public static void printBestRules(List<Rule> rules, double ratio) {
		if (ratio > 1) {
			ratio = 1;
		}
		
		System.out.println("best " + (100 * ratio) + "%:");
		for (int i = 0; i < rules.size() * ratio; i++) {
			System.out.println(rules.get(i).toString());
		}
	}

	public static String getBestRules(List<Rule> rules, int maxCount) {
		if (maxCount > rules.size()) {
			maxCount = rules.size();
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < maxCount; i++) {
			sb.append(rules.get(i).toString()+"\n");
		}
		return sb.toString();
	}
	
	public static void printBestRules(List<Rule> rules, int maxCount) {
		System.out.println("best " + maxCount + ":");
		System.out.println(getBestRules(rules, maxCount));
	}
}
