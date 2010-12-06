package weka.util;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import weka.Bonus;
import weka.associations.Apriori;
import weka.associations.AprioriItemSet;

/**
 * print and get best rated rules
 */
public class RuleRater {
	private LinkedList<Rule> rules = new LinkedList<Rule>();
	
	public RuleRater(Apriori apriori) {
		for (int i = 0; i < apriori.getAllTheRules()[0].size(); i++) {
			AprioriItemSet condition = ((AprioriItemSet) apriori.getAllTheRules()[0].elementAt(i));
			AprioriItemSet consequence = ((AprioriItemSet) apriori.getAllTheRules()[1].elementAt(i));
			double confidence = ((Double)apriori.getAllTheRules()[2].elementAt(i)).doubleValue();
			
			rules.add(new Rule(condition, consequence, confidence, apriori.getInstancesNoClass()));
		}
	}
	
	public void rateAndSort(Collection<Bonus> bonusSet) {
		for (Rule rule : rules) {
			rule.computeRating(bonusSet);
		}
		
		Collections.sort(rules);
	}
	
	/**
	 * prints rules rated better than MIN_RANKING 
	 * 1. rate rules
	 * 2. filter rules
	 * 3. sort filtered rules
	 * 4. print rules
	 * @param apriori
	 */
	public void printRules(int minRating) {
		System.out.println("number of all rules: " + rules.size());
		System.out.println("best rated rules:");
		
		boolean output = false;
		
		for (Rule rule : rules) {
			if (rule.getRating() >= minRating) {
				System.out.println(rule.toString());
				output = true;
			}
		}

		if (!output) {
			System.out.println("nothing found, set lower min rating");
		}
	}
}
