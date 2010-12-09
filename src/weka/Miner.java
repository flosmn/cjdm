package weka;

import java.io.File;
import java.util.Collection;


import utils.PathAndFileNames;
import weka.associations.Apriori;
import weka.associations.AprioriItemSet;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.StringToNominal;
import weka.util.Bonus;
import weka.util.RuleRater;
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
		Apriori apriori = new Apriori();
		
		apriori.setLowerBoundMinSupport(0.11);	
		apriori.setMinMetric(0.95);
		apriori.setNumRules(20);
		
		doMining("methodSummarized.arff", apriori);
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
				doMining(listOfFiles[i].getName(), apriori);
			}
		}
	}
	
	/**
	 * Association rule mining, the full service
	 *   load data
	 *   covert form numerical to nominal
	 *   does mining
	 *   
	 * @param pathAndFile, String
	 */
	public static void doMining(String fileName, Apriori apriori){
		Instances data = null;
		try {
			data = loadDataFromArff(PathAndFileNames.WEKA_DATA_PATH + fileName);
			data = strToNom(data);
			data = numToNom(data);
			apriori.buildAssociations(data);
			printRules(apriori, false);
			
			Collection<Bonus> bonusSet = Bonus.getSampleBonusSet();
			RuleRater filter = new RuleRater(apriori,bonusSet);
			filter.printRules(3);
		} catch (Exception e) {
			System.err.println("Error in doMining()");
			System.err.println("try again");
			e.printStackTrace();
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
	private static Instances loadDataFromArff(String pathAndFileName) throws Exception {
		DataSource source = new DataSource(pathAndFileName);		
		Instances data = source.getDataSet();
		return data;
	}

	/**
	 * convert attributes from numerical to nominal
	 * @param data
	 * @return data
	 * @throws Exception
	 */
	private static Instances numToNom(Instances data) throws Exception {
		NumericToNominal numericToNominal = new NumericToNominal(); 
		String[] numToNomOptions = new String[]{"-R","first-last"};
		numericToNominal.setOptions(numToNomOptions);
		numericToNominal.setInputFormat(data);
		return NumericToNominal.useFilter(data, numericToNominal);
	}

	/**
	 * convert attributes from string to nominal
	 * @param data
	 * @return data
	 * @throws Exception
	 */
	private static Instances strToNom(Instances data) throws Exception {;
		StringToNominal stringToNominal = new StringToNominal();
		String[] strToNomOptions = {"-R", "first-last"};
		stringToNominal.setOptions(strToNomOptions);
		stringToNominal.setInputFormat(data);
		return StringToNominal.useFilter(data,stringToNominal);
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