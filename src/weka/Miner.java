package weka;

import java.io.File;

import utils.PathAndFileNames;
import weka.associations.Apriori;
import weka.associations.AprioriItemSet;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.NumericToNominal;

/**
 * Miner class
 * How to use: doMining()
 * @author Juergen Walter
 */
public class Miner {
	
	/**
	 * do not call from external class,
	 * better call doMining()
	 * @param args
	 * @throws Exception
	 */
	@Deprecated
	public static void main(String[] args) throws Exception {
		File folder = new File(PathAndFileNames.WEKA_TEST_DATA_PATH);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".arff")) {
				System.out.println("____________________________________________");
				System.out.println(listOfFiles[i].getName());
				doMining(PathAndFileNames.WEKA_TEST_DATA_PATH + listOfFiles[i].getName());
			}
		}
	}
	
	/**
	 * calls doMining(../../cjdm.arf)
	 */
	public static void doMining(){
		doMining(PathAndFileNames.WEKA_TEST_DATA_PATH + PathAndFileNames.EXPORT_FILE_NAME);
	}
	
	/**
	 * Association rule mining, the full service
	 *   load data
	 *   covert form numerical to nominal
	 *   does mining
	 *   
	 * @param pathAndFile, String
	 */
	public static void doMining(String pathAndFile){
		Instances data = null;
		Apriori apriori = null;
		try {
			data = loadDataFromArff(pathAndFile);
			data = numToNom(data); 		
			apriori = createAndSetApriori();
			//mining, returns void, changes apriori
			apriori.buildAssociations(data);
			printRules(apriori, false);
		} catch (Exception e) {
			System.err.println("Error in doMining()");
			System.err.println("try again");
			e.printStackTrace();
		}
	}

	/** 
	 * build association rules
	 */
	private static Apriori createAndSetApriori() {
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
	 * convert from numerical to nominal
	 * @param data
	 * @return data
	 * @throws Exception
	 */
	private static Instances numToNom(Instances data) throws Exception {
		NumericToNominal numToNom = new NumericToNominal(); 
		String[] numToNomOptions = new String[3];
		numToNomOptions[0] = "-R ";
		numToNomOptions[1] = "1,";
		numToNomOptions[2] = "2";
		numToNom.setOptions(numToNomOptions);
		numToNom.setInputFormat(data);
		data = NumericToNominal.useFilter(data, numToNom);
		return data;
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