/**
Ê* @author Christian Wellenbrock
Ê* @author JŸrgen Walter
Ê* Team 10
Ê*
Ê* This code has been developed during the winter term 2010-2011 at the
Ê* Karlsruhe Institute of Technology (KIT), Germany.
Ê* It is part of a project assignment in the course
Ê* "Multicore Programming in Practice: Tools, Models, and Languages".
Ê* Project director/instructor:
Ê* Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package mining;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * This class rates rules.
 * 
 * @see ItemBonus
 * @see PatternBonus
 */
public abstract class Bonus {
	private int bonus;
	static String toString = "  has a bonus of ";

	abstract public int rate(Rule rule);

	/**
	 * returns a collection of item and pattern bonus sets
	 * 
	 * @return Collection<Bonus>
	 */
	public static Collection<Bonus> getSampleBonusSet() {
		Collection<Bonus> c = new ArrayList<Bonus>();
		c.addAll(ItemBonus.getSampleBonusSet());
		c.addAll(PatternBonus.getSampleBonusSet());
		return c;
	}

	/**
	 * sets bonus value
	 * @param bonus bonus value
	 */
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	/**
	 * gets bonus value
	 * @return bonus value
	 */
	public int getBonus() {
		return bonus;
	}
	
	/**
	 * combines collections of bonuses to one collection
	 * @param bonusesCollections collections of Bonuses to be combined
	 * @return collection of bonuses
	 */
	public static Collection<Bonus> combineBonusSets(Collection<Bonus> ... bonusesCollections) {
		Collection<Bonus> bonusSet = new LinkedList<Bonus>();

		for (Collection<Bonus> bonusCollection : bonusesCollections) {
			bonusSet.addAll(bonusCollection);
		}

		return bonusSet;
	}
	
	/**
	 * combines bonuses to one collection
	 * @param bonuses bonuses that will be combined as a collection
	 * @return collection of bonuses
	 */
	public static Collection<Bonus> buildBonusSet(Bonus ... bonuses) {
		Collection<Bonus> bonusSet = new LinkedList<Bonus>();

		for (Bonus bonus : bonuses) {
			bonusSet.add(bonus);
		}

		return bonusSet;
	}	
}
