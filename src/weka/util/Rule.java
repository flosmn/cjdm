package weka.util;

import weka.associations.ItemSet;
import weka.core.Instances;

/**
 * A rule contains a condition, a consequence, a confidence and a rating that is
 * calculated during creation
 */
public class Rule implements Comparable<Rule> {
	private ItemSet condition;
	private ItemSet consequence;
	private double confidence;
	private Integer rating;
	private Instances instances;

	public String toString() {
		return rating + " : "
				+ getCondition().toString(instances) + " ==> "
				+ getConsequence().toString(instances)
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
		this.setCondition(condition);
		this.setConsequence(consequence);
		this.confidence = confidence;
		this.instances = instances;
	}

	@Override
	public int compareTo(Rule o) {
		return rating.compareTo(o.rating);
	}
	
	public void setCondition(ItemSet condition) {
		this.condition = condition;
	}
	public ItemSet getCondition() {
		return condition;
	}
	
	public Integer getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setConsequence(ItemSet consequence) {
		this.consequence = consequence;
	}

	public ItemSet getConsequence() {
		return consequence;
	}

	public Instances getInstances() {
		return instances;
	}
}
