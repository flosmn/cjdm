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
 * Miner class
 */
public class Miner {
	
	/**
	 * print best rated rules of project, class and method scope
	 */
	public static void main(String[] args) throws Exception {
		Apriori projectApriori = buildApriori(0.11, 0.95, 20);
		Apriori classApriori = buildApriori(0, 0, 1);
		Apriori methodApriori = buildApriori(0.11, 0.95, 20);
		
		//TODO solve this better, without SuppressWarnings
		@SuppressWarnings("unchecked")
		Collection<Bonus> projectBonusSet = Bonus.buildBonusSet(
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
		
		List<Rule> projectRules = rateAndSort("project.arff", projectApriori, projectBonusSet);
		List<Rule> classRules = rateAndSort("class.arff", classApriori, classBonusSet);
		List<Rule> methodRules = rateAndSort("method.arff", methodApriori, methodBonusSet);
		
		System.out.println("project: " );
		Rule.printBestRules(projectRules, 0.3);
		System.out.println("class: " );
		Rule.printBestRules(classRules, 0.3);
		System.out.println("method: " );
		Rule.printBestRules(methodRules, 0.3);
	}
	

	/**
	 * @return standard bonusSet to give a minus point 
	 * on simple public-private stuff 
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
	 * TODO: set good bonuses
	 * @return
	 */
	public static Collection<Bonus> getSynchronizedBonus() {
		return Bonus.buildBonusSet(
				new ItemBonus(Attribute.SYNCHRONIZED_BLOCKS.getName(), "[^0]", 10),
				new ItemBonus(Attribute.SYNCHRONIZED_BLOCKS.getName(), "[0]", -1),
				new PatternBonus(
						new Item(Attribute.SYNCHRONIZED_BLOCKS, "[^0]"),
						new Item(Attribute.SYNCHRONIZED_METHODS, ".*"), 10)
				);
	}

	
	/**
	 * get rated and sorted rules
	 * @param fileName
	 * @param apriori
	 * @param bonusSet
	 * @return
	 * @throws Exception
	 */
	private static List<Rule> rateAndSort(String fileName, Apriori apriori, Collection<Bonus> bonusSet) throws Exception {
		Instances instances = loadNominalInstances(fileName);
		apriori.buildAssociations(instances);
		
		return RuleRater.sortRules(apriori, bonusSet);
	}

	/**
	 * Convenient creation of a special Apriori
	 * @param lowerBoundMinSupport
	 * @param minMetric
	 * @param numRules
	 * @return
	 */
	private static Apriori buildApriori(double lowerBoundMinSupport, double minMetric, int numRules) {
		Apriori apriori = new Apriori();
		apriori.setLowerBoundMinSupport(lowerBoundMinSupport);	
		apriori.setMinMetric(minMetric);
		apriori.setNumRules(numRules);
		
		return apriori;
	}
	
	/** 
	 * @return Apriori with special settings
	 */
	public static Apriori getSampleApriori() {
		Apriori apriori = new Apriori();
		apriori.setLowerBoundMinSupport(0.11);	
		apriori.setMinMetric(0.95);
		apriori.setNumRules(10);
		return apriori;
	}

	/** 
	 * load from file
	 */
	private static Instances loadNominalInstances(String pathAndFileName) throws Exception {
		DataSource source = new DataSource(PathAndFileNames.WEKA_DATA_PATH + pathAndFileName);		
		Instances data = source.getDataSet();
		data = convertToNominal(data, new NumericToNominal());
		data = convertToNominal(data, new StringToNominal());
		return data;
	}

	/**
	 * convert attributes to nominal
	 * @param data
	 * @return data
	 * @see NumericToNominal
	 * @see StringToNominal
	 * @throws Exception
	 */
	private static Instances convertToNominal(Instances data, Filter filter) throws Exception {
		String[] options = new String[]{"-R", "first-last"};
		((OptionHandler) filter).setOptions(options);
		filter.setInputFormat(data);
		return NumericToNominal.useFilter(data, filter);
	}
}