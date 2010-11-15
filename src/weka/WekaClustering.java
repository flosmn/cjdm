package weka;



import utils.PathAndFileNames;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.PrincipalComponents;


public class WekaClustering implements Runnable
{
	
	SimpleKMeans clusterer = new SimpleKMeans();
	Instances data;
	private boolean finished = false;
	private int iterations = 100;
	
	/**
	 * Creates a new Cluster
	 * 
	 * @param filename relative or absolute path to the data-file
	 * @throws Exception if the file does not exsist oder is corrupt
	 */
	public WekaClustering() throws Exception
	{
		DataSource source = new DataSource(
				PathAndFileNames.WEKA_TEST_DATA_PATH + 
				PathAndFileNames.EXPORT_FILE_NAME);
		data = source.getDataSet();
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
		this.finished = false;
		try {
			double lastError = 2000000000;
			double thisError = 1000000000;
			int clusters = 1;
			while (Math.abs(lastError / thisError) > 1.5)
			{
				clusterer = new SimpleKMeans();
				String[] options = new String[4];
				 options[0] = "-I";                 // max. iterations
				 options[1] = this.iterations+"";
				 options[2] = "-N";                 // max. iterations
				 options[3] = clusters+"";
				 //System.out.println(this.data);
				clusterer.setOptions(options);     // set the options
				this.clusterer.buildClusterer(this.data);
				this.clusterer.getNumClusters(); // to invoke calculation!
				lastError = thisError;
				thisError = this.clusterer.getSquaredError();
				System.out.println("Tried "+clusters+" Cluster: "+thisError);
				clusters++;
			}
			this.finished = true;
			System.out.println(this.clusterer);
			System.out.println(this.getFlaggedData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the number of clusters after the calculation.
	 * 
	 * @return	the number of clusters
	 */
	public int getNumberClusters()
	{
		if (finished)
		{
			return this.clusterer.getNumClusters();
		}
		else
		{
			return -1;
		}
	}
	
	/**
	 * Adds a new value to each data-instance, the value will indicate
	 * to what cluster the data-instance belongs
	 * 
	 * @return a cluster-flagged copy of the original data-set
	 */
	public Instances getFlaggedData()
	{
		if (!this.finished) return null;
		Instances data = new Instances(this.data); // copying the dataset
		Instance instance;
		Attribute att = new Attribute("Cluster");
		int[] clusters = new int[data.numInstances()];
		for (int i = 0; i < data.numInstances(); i++)
		{
			clusters[i] = this.getCluster(data.instance(i));
		}
		data.insertAttributeAt(att, data.numAttributes());
		for (int i = 0; i < this.data.numInstances(); i++)
		{
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
	public int getCluster(Instance i)
	{
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
	public Instance getCluster(int i)
	{
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
	public void addInstance(Instance i)
	{
		this.data.add(i);
	}
	
	/**
	 * Sets the number of iterations the cluster should run
	 * 
	 * @param i
	 */
	public void setIterations(int i)
	{
		if (i > 0)
			this.iterations = i;
	}
	
	public static void main(String[] args)
	{
		WekaClustering c;
		try {
			c = new WekaClustering();
			c.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}