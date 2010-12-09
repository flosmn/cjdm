package weka.util;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import weka.associations.Apriori;
import weka.associations.AprioriItemSet;

/**
 * RuleRater rates the rules according to a given bonus set 
 * and sorts them.
 * 
 * The bonus set is split into item and patter boni
 * 
 * @see Bonus
 * @see Rule
 */
public class RuleRater {
	private LinkedList<Rule> rules = new LinkedList<Rule>();
	private Collection<ItemBonus> itemBonusSet;
	private Collection<PatternBonus> patternBonusSet;
	
	/**
	 * constructor
	 * @param apriori
	 * @param bonusSet
	 */
	public RuleRater(Apriori apriori, Collection<Bonus> bonusSet) {
		for (int i = 0; i < apriori.getAllTheRules()[0].size(); i++) {
			AprioriItemSet condition = ((AprioriItemSet) apriori.getAllTheRules()[0].elementAt(i));
			AprioriItemSet consequence = ((AprioriItemSet) apriori.getAllTheRules()[1].elementAt(i));
			double confidence = ((Double)apriori.getAllTheRules()[2].elementAt(i)).doubleValue();
			
			rules.add(new Rule(condition, consequence, confidence, apriori.getInstancesNoClass()));
		}
		setBonusSet(bonusSet);
	}

	/**
	 * constructor
	 * @param apriori
	 */
	public RuleRater(Apriori apriori) {
		for (int i = 0; i < apriori.getAllTheRules()[0].size(); i++) {
			AprioriItemSet condition = ((AprioriItemSet) apriori.getAllTheRules()[0].elementAt(i));
			AprioriItemSet consequence = ((AprioriItemSet) apriori.getAllTheRules()[1].elementAt(i));
			double confidence = ((Double)apriori.getAllTheRules()[2].elementAt(i)).doubleValue();
			
			rules.add(new Rule(condition, consequence, confidence, apriori.getInstancesNoClass()));
		}
	}
	
	/**
	 * rate and sorts the rules with given bonusSet
	 * @param bonusSet
	 */
	public void rateAndSort(Collection<Bonus> bonusSet) {
		setBonusSet(bonusSet);
		rateAndSort();
	}
	
	/**
	 * rate and sorts the rules with standard bonusSet
	 */	
	public void rateAndSort(){
		System.out.println("Bonus sets used:");
		System.out.println("pattern:");
		System.out.println(patternBonusSet);
		System.out.println("item:");
		System.out.println(itemBonusSet);
		for (Rule rule : rules) {
			computeRating(rule);
		}
		Collections.sort(rules);
	}
	
	/**
	 * computes a rating for a rule
	 * uses item matching and pattern matching
	 */
	public void computeRating(Rule rule) {
		rule.setRating(0);
		ItemBonus.rate(rule, itemBonusSet);
		PatternBonus.ratePattern(rule, patternBonusSet);
	}

	/**
	 * setter that splits Bonus set into an ItemBonus set
	 * and a PatternBonus set
	 * @param bonusSet
	 */
	public void setBonusSet(Collection<Bonus> bonusSet) {
		itemBonusSet = new LinkedList<ItemBonus>();
		patternBonusSet= new LinkedList<PatternBonus>();
		for (Bonus b : bonusSet){
			if (b.getClass().equals(ItemBonus.class))
				itemBonusSet.add((ItemBonus) b);
			else if (b.getClass().equals(PatternBonus.class))
				patternBonusSet.add((PatternBonus) b);
			else
				System.err.println("Unknown Bonus type for rules");
		}
	}

	/**
	 * prints 10% best rules 
	 */
	public void printBest() {
		System.out.println("best 10%:");
		for (int i= (int) (rules.size()*0.9); i<rules.size(); i++){
			System.out.println(rules.get(i).toString());
		}
	}
	/**
	 * prints 10% worst rules 
	 */
	public void printWorst() {
		System.out.println("worst 10%:");
		for (int i= 0; i<(int) (rules.size()*0.1); i++){
			System.out.println(rules.get(i).toString());
		}
	}
	
	/**
	 * prints rules rated better than a minimum rating 
	 * 1. rate rules
	 * 2. filter rules
	 * 3. sort filtered rules
	 * 4. print rules
	 * @param minRating
	 */
	public void printRules(int minRating) {
		System.out.println("Number of all rules: \n" + rules.size());
		System.out.println("Best rated rules:");
		
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
