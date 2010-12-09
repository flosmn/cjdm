package weka.util;

import java.util.ArrayList;
import java.util.Collection;


/**
 * See how the class is extended
 * @see ItemBonus
 * @see PatternBonus
 */
public abstract class Bonus {
	private int bonus;
	static String toString = "  has a bonus of ";
	
	abstract public int rate(Rule rule);

	/**
	 * returns a collection of item and pattern bonus sets
	 * @return Collection<Bonus>
	 */
	public static Collection<Bonus> getSampleBonusSet() {
		Collection<Bonus> c = new ArrayList<Bonus>();
		c.addAll(ItemBonus.getSampleBonusSet());
		c.addAll(PatternBonus.getSampleBonusSet());
		return c;
	}
		
	/**
	 * simple setter
	 * @param bonus
	 */
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	/**
	 * simple getter
	 * @return
	 */
	public int getBonus() {
		return bonus;
	}
}
