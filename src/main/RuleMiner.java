package main;

import com.google.gson.Gson;


public class RuleMiner {
	public static void main(String[] args) throws Exception {
        String jsonString = 
        	"{" +
        	"	'scope' : 'class'," +
        	"	'export' : {" +
        	"		'maxRows' : 1000" +
        	"	}," +
        	"	'mining' : {" +
        	"		'apriori' : {" +
        	"			'lowerBoundMinSupport' : 0.1," +
        	"			'upperBoundMinSupport' : 0.9," +
        	"			'minMetric' : 0.95," +
        	"			'numRules' : 1000" +
        	"		}," +
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
	
	public int getMaxRows() { return maxRows; }
	
	public void setMaxRows(int maxRows) { this.maxRows = maxRows; }
	
	public String toString() {
		return String.format("maxRows:%d", maxRows);
	}
}

class MiningData {
	private AprioriData apriori;
	private int numRules;
	
	public AprioriData getApriori() { return apriori; }
	public int getNumRules() { return numRules; }
	
	public void setApriori(AprioriData apriori) { this.apriori = apriori; }
	public void setNumRules(int numRules) { this.numRules = numRules; }
	
	public String toString() {
		return String.format("numRules:%d, apriori:%s", numRules, apriori);
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
		return String.format("lowerBoundMinSupport:%f, upperBoundMinSupport:%f, minMetric:%f, numRules:&f", lowerBoundMinSupport, upperBoundMinSupport, minMetric, numRules);
	}
}