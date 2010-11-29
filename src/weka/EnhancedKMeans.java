package weka;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import utils.PathAndFileNames;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Normalize;
import weka.util.AttributeNormalizer;

public class EnhancedKMeans implements Runnable {
	SimpleKMeans clusterer;
	Instances data;
	private boolean finished = false;
	private int iterations = 100;
	
	/**
	 * Creates a new Cluster
	 * 
	 * @param number of max iterations
	 * @throws Exception if the file does not exsist oder is corrupt
	 */
	public EnhancedKMeans(int i,String file) throws Exception
	{
		this.setIterations(i);
		data = (new DataSource(file)).getDataSet();
		AttributeNormalizer an = new AttributeNormalizer(data);
		an.addAttribute("objects",1);
		an.addAttribute("fields",1);
		an.addAttribute("interface",1);
		an.addAttribute("extends",1);
		an.work();
		Normalize norm = new Normalize();
		norm.setInputFormat(data);
		//this.data = Filter.useFilter(data, norm);
		
		for (int j = 0; j < data.numAttributes(); j++)
		{
			if (data.attribute(j).type() == Attribute.STRING)
			{
				data.deleteAttributeAt(j);
				j--;
			}
		}
		
		for (int j = 0; j < data.numAttributes(); j++)
		{
			System.out.println(data.attribute(j).name() + ": "+data.attribute(j).weight());
		}
	}
	
	/**
	 * This method actually calculates the Cluster. You can either use it single threaded
	 * or you can use it to calculate the Cluster on another thread.
	 * 
	 * <code>Cluster c = new Cluster(...);
	 * Thread t = new Thread(c);
	 * t.start();</code>
	 */
	public void run()
	{
		SimpleKMeans clusterer1 = null;
		SimpleKMeans clusterer2 = null;
		this.finished = false;
		try {
			double lastError = 2000000000;
			double thisError = 1000000000;
			int clusters = 2;
			/*
			 * keep increasing the number of clusters until the number of squared error don't
			 * not change (much) anymore
			 */
			while (Math.abs(lastError / thisError) > 1.1 || clusterer1 == null)
			{
				clusterer1 = clusterer2;
				clusterer2 = new SimpleKMeans();
				String[] options = new String[4];
				options[0] = "-I";                 // max. iterations
				options[1] = this.iterations+"";
				options[2] = "-N";                 // max. iterations
				options[3] = clusters+"";
				clusterer2.setOptions(options);     // set the options
				clusterer2.buildClusterer(this.data);
				clusterer2.getNumClusters(); // to invoke calculation!
				lastError = thisError;
				thisError = clusterer2.getSquaredError();
				clusters++;
			}

			this.clusterer = clusterer1; // using the 2nd last clustering, because the error didn't change in the last one
			this.finished = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the number of clusters after the calculation.
	 * 
	 * @return	the number of clusters
	 */
	public int getNumberClusters() {
		if (finished) {
			return this.clusterer.getNumClusters();
		} else {
			return -1;
		}
	}
	
	/**
	 * Adds a new value to each data-instance, the value will indicate
	 * to what cluster the data-instance belongs
	 * 
	 * @return a cluster-flagged copy of the original data-set
	 */
	public Instances getFlaggedData() {
		if (!this.finished) return null;
		Instances data = new Instances(this.data); // copying the dataset
		Instance instance;
		Attribute att = new Attribute("Cluster");
		int[] clusters = new int[data.numInstances()];
		for (int i = 0; i < data.numInstances(); i++) {
			clusters[i] = this.getCluster(data.instance(i));
		}
		data.insertAttributeAt(att, data.numAttributes());
		for (int i = 0; i < this.data.numInstances(); i++) {
			instance = data.instance(i);
			instance.setValue(data.numAttributes()-1, clusters[i]); // when i try to give the actual attribute here it will throw an exception
		}
		return data;
	}
	
	/**
	 * Checks to what cluster this Instance belongs an returns its number
	 * 
	 * @param i
	 * @return the clusternumber
	 */
	public int getCluster(Instance i) {
		if (!this.finished) return -1;
		try {
			return this.clusterer.clusterInstance(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Returns the center of the i.-cluster
	 * 
	 * @param i
	 * @return the center of the i.-cluster
	 */
	public Instance getCluster(int i) {
		if (i >= 0 && i < this.clusterer.getNumClusters() && this.finished)
			return this.clusterer.getClusterCentroids().instance(i);
		else
			return null;
	}
	
	/**
	 * Adds a data-instance to this Cluster
	 * 
	 * @param i the instance to add
	 */
	public void addInstance(Instance i) {
		this.data.add(i);
	}
	
	/**
	 * Sets the number of iterations the cluster should run
	 * 
	 * @param i
	 */
	public void setIterations(int i) {
		if (i > 0) {
			this.iterations = i;
		}
	}
	
	public static void main(String[] args) {
		EnhancedKMeans c;
		try {
			File f = new File(PathAndFileNames.WEKA_TEST_DATA_PATH);
			File[] files = f.listFiles();
			for (File data : files) {
				if (data.getName().endsWith(".arff")) {
					c = new EnhancedKMeans(100,data.getAbsolutePath());
					c.run();
					StringBuilder output = new StringBuilder();
					output.append("Clustering for "+data.getName()+"\n");
					output.append(c.clusterer);
					System.out.println("javaprojectsources/clustered"+data.getName().substring(0, data.getName().length()-5)+"Clustered.txt");
					FileWriter fstream = new FileWriter("javaprojectsources/clustered/"+data.getName().substring(0, data.getName().length()-5)+"Clustered.txt");
					BufferedWriter out = new BufferedWriter(fstream);
					
					out.write(output.toString());
				    out.close();
					//System.out.println(c.getFlaggedData());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
