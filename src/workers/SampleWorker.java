package workers;

/**
 * This sample worker illustrates how a worker is meant to be implemented.
 * It counts the number of children of the root of the syntax tree.
 * @author welle
 */
public class SampleWorker extends Worker {

	@Override
	public String getAttributeName() {
		return "number_of_childs";
	}

	@Override
	public int doWork(CommonTreePackage treePackage) {
		return treePackage.getTree().getChildCount();
	}
	
}
