package weka.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import attributes.Attribute;

/**
 * See how the class is extended
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
	 * simple setter
	 * 
	 * @param bonus
	 */
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	/**
	 * simple getter
	 * 
	 * @return
	 */
	public int getBonus() {
		return bonus;
	}

	/**
	 * @return standard bonusSet to give a minus point 
	 * on simple public-private stuff 
	 */
	public static Collection<Bonus> getPublicPrivateBonus() {
		return buildBonusSet(
				new PatternBonus(
						new Item(Attribute.PUBLIC_METHODS, "1"),
						new Item(Attribute.PRIVATE_METHODS, "0"), -10),
				new PatternBonus(
						new Item(Attribute.PUBLIC_METHODS, "0"),
						new Item(Attribute.PRIVATE_METHODS, "1"), -10),
				new PatternBonus(
						new Item(Attribute.PRIVATE_METHODS, "1"),
						new Item(Attribute.PUBLIC_METHODS, "0"), -10),
				new PatternBonus(
						new Item(Attribute.PRIVATE_METHODS, "0"),
						new Item(Attribute.PUBLIC_METHODS, "1"), -10));
	}
	
	/**
	 * TODO: set god bonuses
	 * @return
	 */
	public static Collection<Bonus> getSynchronizedBonus() {
		return buildBonusSet(
				new ItemBonus(Attribute.SYNCHRONIZED_BLOCKS.getName(), "[^0]", 10),
				new ItemBonus(Attribute.SYNCHRONIZED_BLOCKS.getName(), "[0]", -1),
				new PatternBonus(
						new Item(Attribute.SYNCHRONIZED_BLOCKS, "[^0]"),
						new Item(Attribute.SYNCHRONIZED_METHODS, ".*"), 10)
				);
	}

	/**
	 * @param bonusesCollections, Collection<Bonus> ...
	 * @return Collection<Bonus>
	 */
	public static Collection<Bonus> buildBonusSet(Collection<Bonus> ... bonusesCollections) {
		Collection<Bonus> bonusSet = new LinkedList<Bonus>();

		for (Collection<Bonus> bonusCollection : bonusesCollections) {
			bonusSet.addAll(bonusCollection);
		}

		return bonusSet;
	}

	/**
	 * @param bonuses, Bonus ...
	 * @return Collection<Bonus>
	 */
	public static Collection<Bonus> buildBonusSet(Bonus ... bonuses) {
		Collection<Bonus> bonusSet = new LinkedList<Bonus>();

		for (Bonus bonus : bonuses) {
			bonusSet.add(bonus);
		}

		return bonusSet;
	}
	

	
}
