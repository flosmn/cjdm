package weka;

import java.util.Collection;

import weka.associations.ItemSet;
import weka.core.Instances;

/**
 * Quadruple of condition, consequence, confidence and a rating that is
 * calculated during creation
 * 
 */
public class Rule implements Comparable<Rule> {
	private Integer rating;
	ItemSet condition;
	ItemSet consequence;
	double confidence;
	Instances instances;

	public Integer getRating() {
		return rating;
	}

	public String toString() {
		return rating + " : "
				+ condition.toString(instances) + " => "
				+ consequence.toString(instances)
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
		this.condition = condition;
		this.consequence = consequence;
		this.confidence = confidence;
		this.instances = instances;
	}

	@Override
	public int compareTo(Rule o) {
		return rating.compareTo(o.rating);
	}

	/**
	 * computes a rating for a rule
	 */
	public void computeRating(Collection<Bonus> bonusSet) {
		rating = 0;
		
		rate(condition, bonusSet);
		rate(consequence, bonusSet);
	}
	
	private void rate(ItemSet itemSet, Collection<Bonus> bonusSet) {
		int[] items = itemSet.items();
		for (int i = 0; i < instances.numAttributes(); i++) {
			if (items[i] != -1) {
				String name = instances.attribute(i).name();
				String value = instances.attribute(i).value(items[i]);
				
				rate(name, value, bonusSet);
			}
		}
	}
	
	private void rate(String name, String value, Collection<Bonus> bonusSet) {
		for (Bonus bonus : bonusSet) {
			rating += bonus.rate(name, value);
		}
	}
}
