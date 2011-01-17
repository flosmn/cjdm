package clustering;

import org.rosuda.JRI.Rengine;

public class ClusteringRengine {

	private static ClusteringRengine clusteringRengine;
	private Rengine rengine;
	
	private ClusteringRengine(){
		rengine = new Rengine(new String[]{""}, false, null);		
	}
	
	public static ClusteringRengine getInstance(){
		if(clusteringRengine == null){
			clusteringRengine = new ClusteringRengine();
		}
		return clusteringRengine;
	}
	
	public Rengine getRengine(){
		return this.rengine;
	}
}
