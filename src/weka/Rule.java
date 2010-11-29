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

	// TODO optimize bonus system
	private static String[] bigBonus = { "NOTIFY_CALLS=1", "NOTIFYALL_CALLS=1","=1"};
	private static String[] bonus = {"SLEEP_CALLS", "YIELD_CALLS",
			"NESTEDNESS_LOCKS", "NESTEDNESS_SYNCHRONIZED",
			"NESTEDNESS_CONDITIONALS", "NESTEDNESS_LOOPS" };
	private static String[] smallBonus = {};

	/**
	 * constructor
	 * 
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
		// TODO optimize it
		rating = 0;
		String conditionString = condition.toString(apriori
				.getInstancesNoClass());
		String consequenceString = consequence.toString(apriori
				.getInstancesNoClass());
		for (int j = 0; j < bigBonus.length; j++) {
			if (conditionString.contains(bigBonus[j].toString()))
				rating = rating + 4;
			if (consequenceString.contains(bigBonus[j].toString()))
				rating = rating + 4;
		}
		for (int j = 0; j < bonus.length; j++) {
			if (conditionString.contains(bonus[j].toString()))
				rating = rating + 2;
			if (consequenceString.contains(bonus[j].toString()))
				rating = rating + 2;
		}
		for (int j = 0; j < smallBonus.length; j++) {
			if (conditionString.contains(smallBonus[j].toString()))
				rating = rating + 1;
			if (consequenceString.contains(smallBonus[j].toString()))
				rating = rating + 1;
		}
	}

}
