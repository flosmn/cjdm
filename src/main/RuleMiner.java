package main;

import java.util.List;

import com.google.gson.Gson;


public class RuleMiner {
	public static void main(String[] args) throws Exception {
		for (String arg : args) {
			System.out.println(arg);
			
		}
		
        String jsonString = 
        	"{" +
        	"	'scope' : 'class'," +
        	"	'export' : {" +
        	"		'maxRows' : 1000," +
        	"		'attributes' : [" +
        	"			'WHILE_WAIT'," +
        	"			'WAIT_CALLS'," +
        	"			'NOTIFY_CALLS'," +
        	"			'NOTIFYALL_CALLS'," +
        	"			'JOIN_CALLS'" +
        	"		]," +
        	"		'filter' : {" +
        	"			'type' : 'attributeFilter'," +
        	"			'summarized' : 'true'," +
        	"			'conditions' : [ {" +
        	"					'attribute' : 'WHILE_WAIT'," +
        	"					'minValue' : 1," +
        	"					'maxValue' : 100" +
        	"				}, {" +
        	"					'attribute' : 'JOIN_CALLS'," +
        	"					'minValue' : 0," +
        	"					'maxValue' : 2" +
        	"				}" +
        	"			]" +
        	"		}" +
        	"	}," +
        	"	'mining' : {" +
        	"		'apriori' : {" +
        	"			'lowerBoundMinSupport' : 0.1," +
        	"			'upperBoundMinSupport' : 0.9," +
        	"			'minMetric' : 0.95," +
        	"			'numRules' : 1000" +
        	"		}," +
        	"		'bonusSet' : [ {" +
        	"				'type' : 'itemBonus'," +
        	"				'attribute' : 'WAIT_CALLS'," +
        	"				'value' : '[^0]'," +
        	"				'bonus' : 1" +
        	"			}, {" +
        	"				'type' : 'patternBonus'," +
        	"				'conditions' : [ {" +
        	"						'name' : 'WAIT_CALLS'," +
        	"						'value' : '*'" +
        	"					}, {" +
        	"						'name' : '*'," +
        	"						'value' : '[^0]'" +
        	"					}" +
        	"				]," +
        	"				'consequences' : [ {" +
        	"						'name' : 'JOIN_CALLS'," +
        	"						'value' : '*'" +
        	"					}" +
        	"				]," +
        	"				'bonus' : 2" +
        	"			}" +
        	"		]," +
        	"		'numRules' : 200" +
        	"	}" +
        	"}";

        // Now do the magic.
        RuleMiningData data = new Gson().fromJson(jsonString, RuleMiningData.class);

        // Show it.
        System.out.println(data);
    }
}

class RuleMiningData {
    private String scope;
    private ExportData export;
    private MiningData mining;

    public String getScope() { return scope; }
    public ExportData getExport() { return export; }
    public MiningData getMining() { return mining; }

    public void setScope(String scope) { this.scope = scope; }
    public void setExport(ExportData export) { this.export = export; }
    public void setMining(MiningData mining) { this.mining = mining; }

    public String toString() {
        return String.format("scope:%s\nexport:%s\nmining:%s", scope, export, mining);
    }
}

class ExportData {
	private int maxRows;
	private List<String> attributes;
	private FilterData filter;
	
	public int getMaxRows() { return maxRows; }
	public List<String> getAttributes() { return attributes; }
	public FilterData getFilter() { return filter; }
	
	public void setMaxRows(int maxRows) { this.maxRows = maxRows; }
	public void setAttributes(List<String> attributes) { this.attributes = attributes; }
	public void setFilter(FilterData filter) { this.filter = filter; }
	
	public String toString() {
		return String.format("maxRows:%d, attributes:%s, filter:(%s)", maxRows, attributes, filter);
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
	private AprioriData apriori;
	private List<BonusData> bonusSet;
	private int numRules;
	
	public AprioriData getApriori() { return apriori; }
	public List<BonusData> getBonusSet() { return bonusSet; }
	public int getNumRules() { return numRules; }
	
	public void setApriori(AprioriData apriori) { this.apriori = apriori; }
	public void setBonusSet(List<BonusData> bonusSet) { this.bonusSet = bonusSet; }
	public void setNumRules(int numRules) { this.numRules = numRules; }
	
	public String toString() {
		return String.format("numRules:%d, bonusSet:(%s), apriori:(%s)", numRules, bonusSet, apriori);
	}
}

class AprioriData {
	private double lowerBoundMinSupport;
	private double upperBoundMinSupport;
	private double minMetric;
	private int numRules;
	
	public double getLowerBoundMinSupport() { return lowerBoundMinSupport; }
	public double getUpperBoundMinSupport() { return upperBoundMinSupport; }
	public double getMinMetric() { return minMetric; }
	public int getNumRules() { return numRules; }
	
	public void setLowerBoundMinSupport(double lowerBoundMinSupport) { this.lowerBoundMinSupport = lowerBoundMinSupport; }
	public void setUpperBoundMinSupport(double upperBoundMinSupport) { this.upperBoundMinSupport = upperBoundMinSupport; }
	public void setMinMetric(double minMetric) { this.minMetric = minMetric; }
	
	public String toString() {
		return String.format("lowerBoundMinSupport:%f, upperBoundMinSupport:%f, minMetric:%f, numRules:%d", lowerBoundMinSupport, upperBoundMinSupport, minMetric, numRules);
	}
}

class BonusData {
	private String type;
	private int bonus;
	
	// type itemBouns
	private String attribute;
	private String value;
	
	// type patternBonus
	private List<ItemData> conditions;
	private List<ItemData> consequences;
	
	public String toString() {
		if (type.equals("itemBonus")) {
			return String.format("(type:%s, bonus:%d, attribute:%s, value:%s)", type, bonus, attribute, value);
		} else {
			return String.format("(type:%s, bonus:%d, conditions:%s, consequences:%s)", type, bonus, conditions, consequences);
		}
	}
}

class ItemData {
	private String name;
	private String value;
	
	public String getName() { return name; }
	public String getValue() { return value; }
	
	public void setName(String name) { this.name = name; }
	public void setValue(String value) { this.value = value; }
	
	public String toString() {
		return String.format("name:%s, value:%s", name, value);
	}
}