package mining;

import java.util.Collection;
import java.util.List;

import utils.PathAndFileNames;
import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.StringToNominal;
import attributes.Attribute;
import attributes.MethodAttribute;

/**
 * This class creates association rules out of arff files 
 * 
 * @see Bonus
 * @see Rule
 * @see Apriori
 * 
 */
public class Miner {
	
	/**
	 * prints the best rated rules of project, class and method scope
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		// reference usage of mining
		Apriori projectApriori = buildApriori(0.11, 1.0, 0.95, 20);
		Apriori classApriori = buildApriori(0, 1.0, 0, 1);
		Apriori methodApriori = buildApriori(0.11, 1.0, 0.95, 20);
		
		//TODO solve this better, without SuppressWarnings
		@SuppressWarnings("unchecked")
		Collection<Bonus> projectBonusSet = Bonus.combineBonusSets(
				Bonus.buildBonusSet(
						new PatternBonus(
								new Item(MethodAttribute.PUBLIC_METHODS, "1"),
								new Item(MethodAttribute.PRIVATE_METHODS, "0"), -10)
						),
				getPublicPrivateBonus(),
				getSynchronizedBonus()
		);
		
		Collection<Bonus> classBonusSet = getSynchronizedBonus();
		
		Collection<Bonus> methodBonusSet = Bonus.buildBonusSet(
				new PatternBonus(
						new Item(MethodAttribute.PUBLIC_METHODS, "1"),
						new Item(MethodAttribute.PRIVATE_METHODS, "0"), -10),
				new PatternBonus(
						new Item(MethodAttribute.PUBLIC_METHODS, "0"),
						new Item(MethodAttribute.PRIVATE_METHODS, "1"), -10),
				new PatternBonus(
						new Item(MethodAttribute.PRIVATE_METHODS, "1"),
						new Item(MethodAttribute.PUBLIC_METHODS, "0"), -10),
				new PatternBonus(
						new Item(MethodAttribute.PRIVATE_METHODS, "0"),
						new Item(MethodAttribute.PUBLIC_METHODS, "1"), -10));
		
		List<Rule> projectRules = getRules("project.arff", projectApriori, projectBonusSet);
		List<Rule> classRules = getRules("class.arff", classApriori, classBonusSet);
		List<Rule> methodRules = getRules("method.arff", methodApriori, methodBonusSet);
		
		System.out.println("project: " );
		Rule.printBestRules(projectRules, 0.3);
		System.out.println("class: " );
		Rule.printBestRules(classRules, 0.3);
		System.out.println("method: " );
		Rule.printBestRules(methodRules, 0.3);
	}
	

	/**
	 * gets a standard {@link Bonus} set 
	 * to give malus on trivial public private stuff  
	 * @return fix collection of <{@link Bonus}> 
	 * @see ItemBonus
	 * @see PatternBonus
	 */
	public static Collection<Bonus> getPublicPrivateBonus() {
		return Bonus.buildBonusSet(
				new PatternBonus(
						new Item(Attribute.PUBLIC_METHODS, "[^0]"),
						new Item(Attribute.PRIVATE_METHODS, "0"), -10),
				new PatternBonus(
						new Item(Attribute.PUBLIC_METHODS, "0"),
						new Item(Attribute.PRIVATE_METHODS, "[^0]"), -10),
				new PatternBonus(
						new Item(Attribute.PRIVATE_METHODS, "[^0]"),
						new Item(Attribute.PUBLIC_METHODS, "0"), -10),
				new PatternBonus(
						new Item(Attribute.PRIVATE_METHODS, "0"),
						new Item(Attribute.PUBLIC_METHODS, "[^0]"), -10));
	}
	
	/**
	 * gets a standard {@link Bonus} set
	 * for synchronized stuff
	 * @return fix collection of <{@link Bonus}> 
	 * @see ItemBonus
	 * @see PatternBonus
	 */
	public static Collection<Bonus> getSynchronizedBonus() {
		 // TODO: set god bonuses
		return Bonus.buildBonusSet(
				new ItemBonus(Attribute.SYNCHRONIZED_BLOCKS.getName(), "[^0]", 10),
				new ItemBonus(Attribute.SYNCHRONIZED_BLOCKS.getName(), "[0]", -1),
				new PatternBonus(
						new Item(Attribute.SYNCHRONIZED_BLOCKS, "[^0]"),
						new Item(Attribute.SYNCHRONIZED_METHODS, ".*"), 10)
				);
	}

	
	/**
	 * gets rated and sorted rules
	 * @param fileName name of the *.arff file
	 * @param apriori Apriori 
	 * @param bonusSet collection of bonuses
	 * @return List of {@link Rule}
	 * @throws Exception
	 */
	public static List<Rule> getRules(String fileName, Apriori apriori, Collection<Bonus> bonusSet, boolean debug) throws Exception {
		Instances instances = loadNominalInstances(fileName);
		
		if (debug) {
			System.out.println("building associatons for " + fileName);
		}
		
		apriori.buildAssociations(instances);
		
		return RuleRater.sortRules(apriori, bonusSet, debug);
	}
	
	public static List<Rule> getRules(String fileName, Apriori apriori, Collection<Bonus> bonusSet) throws Exception {
		return getRules(fileName, apriori, bonusSet, true);
	}
	
	/**
	 * creates an {@link Apriori} with parameterized features
	 * @param lowerBoundMinSupport
	 * @param upperBoundMinSupport 
	 * @param minMetric 
	 * @param numRules maximum number of rules to read
	 * @return Apriori 
	 * @see #getSampleApriori()
	 */
	public static Apriori buildApriori(double lowerBoundMinSupport, double upperBoundMinSupport, double minMetric, int numRules) {
		Apriori apriori = new Apriori();
		apriori.setLowerBoundMinSupport(lowerBoundMinSupport);
		apriori.setUpperBoundMinSupport(upperBoundMinSupport);
		apriori.setMinMetric(minMetric);
		apriori.setNumRules(numRules);
		
		return apriori;
	}
	
	/** 
	 * creates an {@link Apriori} convenient, features are set
	 * @return Apriori with special settings
	 * @see #buildApriori(double lowerBoundMinSupport, 
	 * 		double upperBoundMinSupport, double minMetric, int numRules)
	 */
	public static Apriori getSampleApriori() {
		Apriori apriori = new Apriori();
		apriori.setLowerBoundMinSupport(0.11);	
		apriori.setMinMetric(0.95);
		apriori.setNumRules(10);
		return apriori;
	}

	/** 
	 * loads Instances from *.arff file
	 * @param fileName name of the *.arff file
	 * @return Instances converted to nominal
	 */
	private static Instances loadNominalInstances(String fileName) throws Exception {
		DataSource source = new DataSource(PathAndFileNames.WEKA_DATA_PATH + fileName);		
		Instances data = source.getDataSet();
		data = convertToNominal(data, new NumericToNominal());
		data = convertToNominal(data, new StringToNominal());
		return data;
	}

	/**
	 * converts attributes to nominal
	 * @param data Instances to filter
	 * @param filter filters to nominal e.g. {@link StringToNominal} or {@link NumericToNominal}
	 * @return filtered Instances
	 * @throws Exception
	 */
	private static Instances convertToNominal(Instances data, Filter filter) throws Exception {
		String[] options = new String[]{"-R", "first-last"};
		((OptionHandler) filter).setOptions(options);
		filter.setInputFormat(data);
		return NumericToNominal.useFilter(data, filter);
	}
}