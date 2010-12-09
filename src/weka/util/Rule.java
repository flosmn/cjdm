package weka.util;

import java.util.Collection;

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
		return rating.compareTo(o.rating);
	}
}
