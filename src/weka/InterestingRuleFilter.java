package weka;

import java.util.Collections;
import java.util.LinkedList;

import weka.associations.Apriori;
import weka.associations.AprioriItemSet;

/**
 * print and get best rated rules
 * 
 * @author Juergen Walter
 */
public class InterestingRuleFilter {
	private static int MIN_RATING = 3;

	/**
	 * prints rules rated better than MIN_RANKING 
	 * 1. rate rules
	 * 2. filter rules
	 * 3. sort filtered rules
	 * 4. print rules
	 * @param apriori
	 */
	public static void printBestRules(Apriori apriori) {
		LinkedList<Rule> bestRules = filterBestRules(apriori);
		System.out.println("number of all rules:");
		System.out.println(apriori.getNumRules());
		System.out.println("best rated rules:");
		if (bestRules.size() == 0)
			System.out.println("none, set lower quality standards");
		for (int i=0; i<bestRules.size(); i++){
			System.out.println(bestRules.get(i));			
		}
	}

	/**
	 * filterBestRules
	 * 1. rate rules
	 * 2. filter rules
	 * 3. sort filtered rules
	 * @param apriori
	 * @return rules ranked better than MIN_RANKING 
	 */
	private static LinkedList<Rule> filterBestRules(Apriori apriori) {
		LinkedList<Rule> bestRules = new LinkedList<Rule>();
		for (int i = 0; i < apriori.getAllTheRules()[0].size(); i++) {
			AprioriItemSet condition = ((AprioriItemSet) apriori
					.getAllTheRules()[0].elementAt(i));
			AprioriItemSet consequence = ((AprioriItemSet) apriori
					.getAllTheRules()[1].elementAt(i));
			double confidence = ((Double)apriori.getAllTheRules()[2].elementAt(i)).doubleValue();
			Rule rule = new Rule(condition, consequence, confidence, apriori);
			if (rule.getRating() > MIN_RATING) {
				bestRules.add(rule);
			}
		}
		Collections.sort(bestRules);
		return bestRules;
	}
}
