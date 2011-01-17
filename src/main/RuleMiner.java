package main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mining.Bonus;
import mining.Item;
import mining.ItemBonus;
import mining.Miner;
import mining.PatternBonus;
import mining.Rule;
import utils.Logger;
import utils.PathAndFileNames;
import weka.associations.Apriori;
import attributes.Attribute;

import com.google.gson.Gson;

import database.Database;
import database.Scope;
import export.AttributeCondition;
import export.AttributeFilter;
import export.ExportFilter;
import export.Exporter;
import export.Exporter.ExportType;
import export.ParallelFilter;

/**
 * This is the main class for using cjdm. Cjdm is typically used by 
 * calling {@link #main(String[])} with file names of JSON config files.
 * 
 * Then export, mining and filtering are done according to configs
 * 
 */
public class RuleMiner {
	
	/**
	 * Reads JSON file to get settings and then calls {@link #export(ExportData, String)} and 
	 * {@link #mine(MiningData, String)}
	 * 
	 * @param args fileNames of JSON setting files
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String jsonString = "";
		boolean noCjdmPassed = true;
		
		for (String fileName : args) {
			if(fileName.endsWith(".cjdm")){
				noCjdmPassed = false;
				//read settings
				jsonString = readFileAsString(fileName);
				
		        //create setting object
		        //RuleMiningData data = new Gson().fromJson(jsonString, RuleMiningData.class);

		        //extract arff file name
		        String arffFileName = fileName.split(".cjdm")[0] + ".arff";
		        String rulesFileName = fileName.split(".cjdm")[0] + ".rules";
		        
		        //create data classes from Json
		        RuleMiningData ruleMiningData = new Gson().fromJson(jsonString, RuleMiningData.class);
		        
		        ExportData exportData = ruleMiningData.getExport();		        
		        MiningData miningData = ruleMiningData.getMining();
		        boolean debug = ruleMiningData.getDebug();
		        
		        if (debug) {
					System.out.println("-----" + fileName + "-----");
		        	System.out.println(ruleMiningData);
		        }
	
		        //do export
		        if (exportData != null) {
		        	export(exportData, arffFileName, debug);
		        }

		        //do mining
		        if (miningData != null) {
		        	String rulesString = mine(miningData, arffFileName, debug);
		        	writeToFile(rulesString, rulesFileName);
		        }

			}
		}

		if (noCjdmPassed){	
			printNoCjdmPassed();
		}
    }
	
	/**
	 * writes dataString to file
	 * @param dataString text to store
	 * @param fileName path to store
	 * @param debug
	 */
	static void writeToFile(String dataString, String fileName){
    	//write to file
		Logger logger = new Logger();
		logger.log(dataString);
		logger.writeToFile("", fileName);
		System.out.println("done, rules exported to "+fileName);
	}
	
	/**
	 * Reads file 
	 * @param filePath path of the file
	 * @return text stored in the file
	 * @throws java.io.IOException
	 */
	private static String readFileAsString(String filePath) throws java.io.IOException{
	    byte[] buffer = new byte[(int) new File(filePath).length()];
	    BufferedInputStream f = null;
	    try {
	        f = new BufferedInputStream(new FileInputStream(filePath));
	        f.read(buffer);
	    } finally {
	        if (f != null) try { f.close(); } catch (IOException ignored) { }
	    }
	    return new String(buffer);
	}
	
	/**
	 * Writes data in an *.arff file 
	 * @param exportData settings for export e.g. filter
	 * @param fileName name of new *.arff file
	 * @param debug 
	 */
	public static void export(ExportData exportData, String fileName, boolean debug) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		Scope scope = exportData.buildScope();
		int maxRows = exportData.getMaxRows();
		List<String> attributes = exportData.getAttributes();
		ExportFilter filter = exportData.buildFilter();

		String combinedAttributes = "";
		for (String attribute : attributes) {
			combinedAttributes = Attribute.combine(combinedAttributes, attribute);
		}
		
		Exporter.export(scope, ExportType.ARFF, fileName, database, combinedAttributes, maxRows, filter, debug);
		
		database.shutdown();
	}
	
	/**
	 * Mines *.arff file according to mining data settings
	 * @param miningData apriori settings and bonus sets
	 * @param fileName name of *.arff file to mine
	 * @param debug 
	 * @throws Exception
	 */
	public static String mine(MiningData miningData, String fileName, boolean debug) throws Exception {
		Apriori apriori = miningData.buildApriori();
		
		Collection<Bonus> bonusSet = miningData.buildBonusSet();
		
		List<Rule> rules = Miner.getRules(fileName, apriori, bonusSet, debug);
		
		return Rule.getBestRules(rules, miningData.getNumRules());
	}
	
	/**
	 * prints a warning that no cjdm file was passed
	 */
	private static void printNoCjdmPassed() {
		System.out.println("WARNING:");
		System.out.println("There was no *.cjdm file path passed to this method. ");
		System.out.println("Correct use 1:\tPass path to your *.cjdm files");
		System.out.println("Correct use 2:\tPut some files in");
		System.out.println("\t\t"+System.getProperty("user.dir"));
		System.out.println("\t\tand pass * ");
	}
}

class RuleMiningData {
    private ExportData export;
    private MiningData mining;
    private boolean debug = false;
    
    public boolean getDebug() { return debug; }
    public ExportData getExport() { return export; }
	public MiningData getMining() {
		mining.setDebug(debug);
		return mining;
	}

    public void setExport(ExportData export) { this.export = export; }
    public void setMining(MiningData mining) { this.mining = mining; }

    public String toString() {
        return String.format("debug:%b\nexport:%s\nmining:%s", debug, export, mining);
    }
}

class ExportData {
    private String scope = "project";
	private int maxRows;
	private List<String> attributes;
	private FilterData filter = null;	// optional
	
	/**
	 * Transforms a {@link String} to {@link Scope}
	 * @return scope instance according to String
	 */
    public Scope buildScope() {
    	if (scope.equals("method")) return Scope.METHOD;
    	if (scope.equals("class")) return Scope.CLASS;
    	if (scope.equals("project")) return Scope.PROJECT;
    	
    	System.out.println("invalid scope type, using 'project'");
    	return Scope.PROJECT;
    }
    
    /**
     * Builds a filter with settings of {@link #filter}
     * @return filter
     * @see FilterData
     */
    public ExportFilter buildFilter() {
		if (filter == null) {
			return new ExportFilter();
		}
		
		String filterType = filter.getType();
		if (filterType.equals("parallelFilter")) {
			return new ParallelFilter(filter.getSummarized(), filter.getMinParallel());
		}
		
		if (filterType.equals("attributeFilter")) {
			List<ConditionData> conditionDatas = filter.getConditions();
			
			List<AttributeCondition> conditions = new ArrayList<AttributeCondition>();
			for (ConditionData conditionData : conditionDatas) {
				conditions.add(conditionData.build());
			}
			
			return new AttributeFilter(filter.getSummarized(), conditions);
		}
		
		System.out.println("invalid filter type, using none");
		return new ExportFilter();
	}
	
    public String getScope() { return scope; }
	public int getMaxRows() { return maxRows; }
	public List<String> getAttributes() { return attributes; }
	public FilterData getFilter() { return filter; }
	
    public void setScope(String scope) { this.scope = scope; }
	public void setMaxRows(int maxRows) { this.maxRows = maxRows; }
	public void setAttributes(List<String> attributes) { this.attributes = attributes; }
	public void setFilter(FilterData filter) { this.filter = filter; }
	
	public String toString() {
		return String.format("scope:%s, maxRows:%d, attributes:%s, filter:(%s)", scope, maxRows, attributes, filter);
	}
}

class FilterData {
	private String type;
	private boolean summarized;
	
	// type attributeFilter
	private List<ConditionData> conditions;
	
	// type parallelFilter
	private int minParallel;
	
	public String getType() { return type; }
	public boolean getSummarized() { return summarized; }
	public List<ConditionData> getConditions() { return conditions; }
	public int getMinParallel() { return minParallel; }
	
	public void setType(String type) { this.type = type; }
	public void setSummarized(boolean summarized) { this.summarized = summarized; }
	public void setConditions(List<ConditionData> conditions) { this.conditions = conditions; }
	public void setMinParallel(int minParallel) { this.minParallel = minParallel; }
	
	public String toString() {
		if (type.equals("attributeFilter")) {
			return String.format("type:%s, summarized:%b, conditions:%s", type, summarized, conditions);
		} else {
			return String.format("type:%s, summarized:%b, minParallel:%d", type, summarized, minParallel);
		}
	}
}

class ConditionData {
	private String attribute;
	private int minValue;
	private int maxValue = Integer.MAX_VALUE;
	
	public AttributeCondition build() {
		return new AttributeCondition(Attribute.getAttribute(attribute), minValue, maxValue);
	}
	
	public String getAttribute() { return attribute; }
	public int getMinValue() { return minValue; }
	public int getMaxValue() { return maxValue; }
	
	public void setAttribute(String attribute) { this.attribute = attribute; }
	public void setMinValue(int minValue) { this.minValue = minValue; }
	public void setMaxValue(int maxValue) { this.maxValue = maxValue; }
	
	public String toString() {
		return String.format("(attribute:%s, minValue:%d, maxValue:%d)", attribute, minValue, maxValue);
	}
}

class MiningData {
	private AprioriData aprioriData = new AprioriData();
	private List<BonusData> bonusSet;
	private int numRules;
	private boolean debug;
	
	/**
	 * creates new apriori and sets values
	 * @return apriori instance with values of aprioriData
	 * @throws Exception
	 */
	public Apriori buildApriori() throws Exception {
		Apriori apriori = new Apriori();
		String[] options = {
				"-N",Integer.toString(aprioriData.minRules),
				"-T",Integer.toString(aprioriData.metricType),
				"-C",Double.toString(aprioriData.minMetricScore),
				"-D",Double.toString(aprioriData.deltaMinSupport),
				"-U",Double.toString(aprioriData.upperBoundMinSupport),
				"-M",Double.toString(aprioriData.lowerBoundMinSupport),
				"-S",Double.toString(aprioriData.significanceLevel),
				"-I",Boolean.toString(aprioriData.outputItemSets),
				"-R",Boolean.toString(aprioriData.removeMissingColumns),
				};
		apriori.setOptions(options);
		
		if (debug) {
			apriori.setVerbose(true);
		}
		
		return apriori;
	}
	
	public Collection<Bonus> buildBonusSet() {
		List<Bonus> bonuses = new ArrayList<Bonus>();
		for (BonusData bonusData : bonusSet) {
			bonuses.add(bonusData.build());
		}
		
		return bonuses;
	}
	
	public AprioriData getApriori() { return aprioriData; }
	public List<BonusData> getBonusSet() { return bonusSet; }
	public int getNumRules() { return numRules; }
	public boolean getDebug() { return debug; }
	
	public void setApriori(AprioriData apriori) { this.aprioriData = apriori; }
	public void setBonusSet(List<BonusData> bonusSet) { this.bonusSet = bonusSet; }
	public void setNumRules(int numRules) { this.numRules = numRules; }
	public void setDebug(boolean debug) { this.debug = debug; }
	
	public String toString() {
		return String.format("numRules:%d, bonusSet:(%s), apriori:(%s)", numRules, bonusSet, aprioriData);
	}
}

/**
 * This class stores data for the use of weka apriori algorithm
 * @see Apriori
 */
class AprioriData {
	
	// required number of rules (default: 10).
	int minRules = 200 ;
	
	// type of metric by which to sort rules
	// 0 = confidence | 1 = lift | 2 = leverage | 3 = conviction.
	int metricType = 0; 
	
	// minimum metric score of a rule (default: 0.9).
	double minMetricScore = 0.9; 
	
	// delta by which the minimum support is decreased in each iteration (default: 0.05).
	double deltaMinSupport = 0.05;
	
	// upper bound for minimum support. Don't explicitly look for rules with more than this level of support.
	double upperBoundMinSupport = 1.0;
	
	// lower bound for the minimum support (default = 0.1).
	double lowerBoundMinSupport = 0.1;
	
	// If used, rules are tested for significance at the given level. Slower (default = no significance testing).
	double significanceLevel = -1;
	
	// If set the itemsets found are also output (default = no).
	boolean outputItemSets = false;
	
	// If set then progress is reported iteratively during execution.
	boolean reportProgress = false;
	
	// If set then columns that contain all missing values are removed from the data. 
	boolean removeMissingColumns = false;
}

class BonusData {
	private String type = "itemBonus";
	private int bonus = 0;
	
	// type itemBouns
	private String attribute = "";
	private String value = "";
	
	// type patternBonus
	private List<ItemData> conditions;
	private List<ItemData> consequences;
	
	public Bonus build() {
		if (type.equals("itemBonus")) {
			return new ItemBonus(attribute, value, bonus);
		}
		
		if (type.equals("patternBonus")) {
			Collection<Item> cond = new ArrayList<Item>();
			for (ItemData itemData : conditions) {
				cond.add(itemData.build());
			}
			
			Collection<Item> cons = new ArrayList<Item>();
			for (ItemData itemData : consequences) {
				cond.add(itemData.build());
			}
			
			return new PatternBonus(cond, cons, bonus);
		}
		
		System.out.println("invalid bonus type, using no bonus");
		return new ItemBonus("", "", 0);
	}
	
	public String getType() { return type; }
	public int getBonus() { return bonus; }
	public String getAttribute() { return attribute; }
	public String value() { return value; }
	public List<ItemData> getConditions() { return conditions; }
	public List<ItemData> getConsequences() { return consequences; }
	
	public void setType(String type) { this.type = type; }
	public void setBonus(int bonus) { this.bonus = bonus; }
	public void setAttribute(String attribute) { this.attribute = attribute; }
	public void setValue(String value) { this.value = value; }
	public void setConditions(List<ItemData> conditions) { this.conditions = conditions; }
	public void setConsequences(List<ItemData> consequences) { this.consequences = consequences; }
	
	public String toString() {
		if (type.equals("itemBonus")) {
			return String.format("(type:%s, bonus:%d, attribute:%s, value:%s)", type, bonus, attribute, value);
		} else {
			return String.format("(type:%s, bonus:%d, conditions:%s, consequences:%s)", type, bonus, conditions, consequences);
		}
	}
}

class ItemData {
	private String name = "";
	private String value = "";
	
	public Item build() {
		return new Item(name, value);
	}
	
	public String getName() { return name; }
	public String getValue() { return value; }
	
	public void setName(String name) { this.name = name; }
	public void setValue(String value) { this.value = value; }
	
	public String toString() {
		return String.format("name:%s, value:%s", name, value);
	}
}