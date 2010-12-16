package weka;

import java.io.File;
import java.util.Collection;
import java.util.List;

import utils.PathAndFileNames;
import weka.associations.Apriori;
import weka.associations.AprioriItemSet;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.StringToNominal;
import weka.util.Bonus;
import weka.util.Item;
import weka.util.PatternBonus;
import weka.util.Rule;
import weka.util.RuleRater;
import attributes.MethodAttribute;
/**
 * Miner class
 * How to use: doMining()
 */
public class Miner {
	
	/**
	 * do not call from external class,
	 * better call doMining()
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Apriori projectApriori = buildApriori(0.11, 0.95, 20);
		Apriori classApriori = buildApriori(0, 0, 1);
		Apriori methodApriori = buildApriori(0.11, 0.95, 20);
		
		Collection<Bonus> projectBonusSet = Bonus.buildBonusSet(
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
		
		Collection<Bonus> classBonusSet = Bonus.buildBonusSet(
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
		
	//	printBestRules("project.arff", projectApriori, projectBonusSet);
	//	printBestRules("class.arff", classApriori, classBonusSet);
		List<Rule> methodRules = getBestRules("method.arff", methodApriori, methodBonusSet);
		
		Rule.printBestRules(methodRules, 0.1);
	}
	
	private static List<Rule> getBestRules(String fileName, Apriori apriori, Collection<Bonus> bonusSet) throws Exception {
		Instances instances = loadNominalInstances(fileName);
		apriori.buildAssociations(instances);
		
		return RuleRater.sortRules(apriori, bonusSet);
	}

	private static Apriori buildApriori(double lowerBoundMinSupport, double minMetric, int numRules) {
		Apriori apriori = new Apriori();
		apriori.setLowerBoundMinSupport(lowerBoundMinSupport);	
		apriori.setMinMetric(minMetric);
		apriori.setNumRules(numRules);
		
		return apriori;
	}

	public static void mineAll() {
		Apriori apriori = getSampleApriori();
		File folder = new File(PathAndFileNames.WEKA_DATA_PATH);
		assert (folder.isDirectory()): "given path to *.arff files is not a directory";
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith("Summarized.arff")) {
				System.out.println("____________________________________________");
				System.out.println(listOfFiles[i].getName());
				
				// TODO: sample mining
			}
		}
	}
	
	/** 
	 * build association rules
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
	 * convert attributes from numerical to nominal
	 * @param data
	 * @return data
	 * @throws Exception
	 */
	private static Instances convertToNominal(Instances data, Filter filter) throws Exception {
		String[] options = new String[]{"-R", "first-last"};
		((OptionHandler) filter).setOptions(options);
		filter.setInputFormat(data);
		return NumericToNominal.useFilter(data, filter);
	}
	
	/**
	 * print rules, detailed == false
	 * @param apriori, Apriori
	 */
	static void printRules(Apriori apriori) {
		printRules(apriori, false);
	}

	/**
	 * prints the rules
	 * @param apriori, Apriori
	 * @param detailed, boolean
	 */
	static void printRules(Apriori apriori, boolean detailed) {
		if(detailed){
				System.out.println(apriori);
		}else{
			StringBuffer text = new StringBuffer();
			text.append("\nBest rules found:\n\n");
			for (int i = 0; i < apriori.getAllTheRules()[0].size(); i++) {
				text.append(Utils
					.doubleToString((double) i + 1, (int) (Math
							.log(apriori.getNumRules())
							/ Math.log(10) + 1), 0)
							+ ". "
							+ ((AprioriItemSet) apriori.getAllTheRules()[0]	
							                                             .elementAt(i)).toString(apriori.getInstancesNoClass())//(apriori.m_instances)
                            + " ==> "
                            + ((AprioriItemSet) apriori.getAllTheRules()[1]
                                                                         .elementAt(i)).toString(apriori.getInstancesNoClass())//(apriori.m_instances)
                            + "    conf:("
			                + Utils
			                .doubleToString(
			                		((Double) apriori.getAllTheRules()[2]
			                		                                   .elementAt(i))
			                		                                   .doubleValue(), 2)
			                 + ")");
				text.append('\n');
			}
			System.out.println(text);
		}
	}
}