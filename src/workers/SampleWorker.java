package workers;

import database.Scope;
import main.TreePackage;

/**
 * This sample worker illustrates how a worker is meant to be implemented.
 * It counts the number of children of the root of the syntax tree.
 * @author welle
 */
public class SampleWorker extends Worker {
	protected Scope scope = Scope.METHOD;
	
	public SampleWorker() {
		this.scope = Scope.METHOD;
	}
	
	@Override
	public String getAttributeName() {
		return "number_of_childs";
	}

	@Override
	public int doWork(TreePackage treePackage) {
		return treePackage.getTree().getChildCount();
	}

}
