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
		String conditionString = condition.toString(instances);
		String consequenceString = consequence.toString(instances);
		
		for (Bonus bonus : bonusSet) {
			// TODO: rate items, not item sets
			if (conditionString.contains(bonus.getName())
					|| consequenceString.contains(bonus.getName()))
				rating += bonus.getValue();
		}
	}

}
