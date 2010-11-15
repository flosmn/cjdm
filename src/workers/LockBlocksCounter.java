package workers;

import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import utils.DirtyLittleHelper;

/**
 *	Searches for blocks synchronized via Locks. Does not accout nested blocks.
 *	Couter is incremented if there is an lock() and unlock() call in one block scope
 *
 * TODO: check that object where lock() or unlock() is called is of type Lock.
 */
public class LockBlocksCounter extends Worker{

	private int counter;
	
	@Override
	public String getAttributeName() {
		return "lock_blocks";
	}
	
	public void traverse(CommonTree tree){
		if(tree == null){
			System.out.println("tree == null");
			return;
		}
	
		if (tree.getText() != null && tree.getText().equals("BLOCK_SCOPE")) {
			processMethod(tree);
		}
		
		List<CommonTree> children = DirtyLittleHelper.castList(CommonTree.class, tree.getChildren());
		if (children == null) {
			return;
		}
		
		for (CommonTree child : children) {
			traverse(child);
		}
	}

	private void processMethod(CommonTree tree) {
		CommonTree lockNode = firstChildWithType(tree, "lock");
		CommonTree unlockNode = firstChildWithType(tree, "unlock");
		
		if (lockNode != null && unlockNode != null) {
			++counter;
		}
	}

	@Override
	public int doWork(CommonTreePackage treePackage) {
		counter = 0;
		
		traverse(treePackage.getTree());

		return counter;
	}
}
