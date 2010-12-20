package mining;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import weka.associations.Apriori;
import weka.associations.AprioriItemSet;

/**
 * RuleRater rates the rules according to a given bonus set 
 * and sorts them.
 * 
 * @see Bonus
 * @see Rule
 */
public class RuleRater {
	
	/**
	 * extract rules, add rating and sort
	 * @param apriori
	 * @param bonusSet
	 * @return
	 */
	public static List<Rule> sortRules(Apriori apriori, Collection<Bonus> bonusSet) {
		List<Rule> rules = extractRules(apriori);
		
		for (Rule rule : rules) {
			rule.resetRating();
			rule.addRating(bonusSet);
		}
		
		Collections.sort(rules);
		
		return rules;
	}

	/**
	 * convert rules to weka.util.Rule
	 * @param apriori
	 * @return
	 */
	private static List<Rule> extractRules(Apriori apriori) {
		List<Rule> rules = new LinkedList<Rule>();
		
		for (int i = 0; i < apriori.getAllTheRules()[0].size(); i++) {
			AprioriItemSet condition = ((AprioriItemSet) apriori.getAllTheRules()[0].elementAt(i));
			AprioriItemSet consequence = ((AprioriItemSet) apriori.getAllTheRules()[1].elementAt(i));
			double confidence = ((Double)apriori.getAllTheRules()[2].elementAt(i)).doubleValue();
			
			rules.add(new Rule(condition, consequence, confidence, apriori.getInstancesNoClass()));
		}
		
		return rules;
	}
}