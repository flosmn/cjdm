package workers;

import parsing.TreePackage;
import database.Aggregator;

/**
 * This sample worker illustrates how a worker is meant to be implemented.
 * It counts the number of children of the root of the syntax tree.
 * @author welle
 */
public class SampleWorker extends Worker {
	public int counter = 0;
	
	public SampleWorker() {
		// TODO: set attribute
		this.aggregator = Aggregator.SUM;
	}
	
	@Override
	public String getAttributeName() {
		return "counter";
	}

	@Override
	public int doWork(TreePackage treePackage) {
		return ++counter;
	}
}
