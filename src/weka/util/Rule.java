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

	public String toString() {
		return rating + " : "
				+ getConditionItems().toString(instances) + " ==> "
				+ getConsequenceItems().toString(instances)
				+ "  conf(" + confidence + ")";
	}

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

	@Override
	public int compareTo(Rule o) {
		return rating.compareTo(o.rating);
	}
	
	public void setConditionItems(ItemSet conditionItems) {
		this.conditionItems = conditionItems;
	}
	public ItemSet getConditionItems() {
		return conditionItems;
	}
	
	public void setConsequenceItems(ItemSet consequenceItems) {
		this.consequenceItems = consequenceItems;
	}

	public ItemSet getConsequenceItems() {
		return consequenceItems;
	}

	public Integer getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	public Instances getInstances() {
		return instances;
	}

	public void resetRating() {
		this.rating = 0;
	}

	public void addRating(Collection<Bonus> bonusSet) {
		for (Bonus bonus : bonusSet) {
			this.rating += bonus.rate(this);
		}
	}
}
