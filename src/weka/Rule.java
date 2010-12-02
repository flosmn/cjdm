package weka;

import weka.associations.Apriori;
import weka.associations.AprioriItemSet;

/**
 * Quadruple of condition, consequence, confidence and a rating that is
 * calculated during creation
 * 
 * @author Juergen Walter
 */
public class Rule implements Comparable<Rule> {
	private Integer rating;
	AprioriItemSet condition;
	AprioriItemSet consequence;
	double confidence;
	Apriori apriori;

	public Integer getRating() {
		return rating;
	}

	public String toString() {
		return rating + " : "
				+ condition.toString(apriori.getInstancesNoClass()) + " => "
				+ consequence.toString(apriori.getInstancesNoClass())
				+ "  conf(" + confidence + ")";
	}

	/**
	 * constructor
	 * @param condition
	 * @param consequence
	 * @param confidence
	 */
	public Rule(AprioriItemSet condition, AprioriItemSet consequence,
			double confidence, Apriori apriori) {
		this.condition = condition;
		this.consequence = consequence;
		this.confidence = confidence;
		this.apriori = apriori;
		computeRating();
	}

	@Override
	public int compareTo(Rule o) {
		return rating.compareTo(o.rating);
	}

	/**
	 * computes a rating for a rule
	 */
	private void computeRating() {
		rating = 0;
		String conditionString = condition.toString(apriori
				.getInstancesNoClass());
		String consequenceString = consequence.toString(apriori
				.getInstancesNoClass());
		
		for (Bonus bonus : Bonus.getInstances()) {
			if (conditionString.contains(bonus.getName())
					|| consequenceString.contains(bonus.getName()))
				rating = rating + bonus.getValue();
		}
	}

}
