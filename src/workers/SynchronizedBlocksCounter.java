package workers;

import java.util.List;

import main.CommonTreePackage;

import org.antlr.runtime.tree.CommonTree;

import utils.DirtyLittleHelper;

public class SynchronizedBlocksCounter extends Worker{

	private int counter;
	
	@Override
	public String getAttributeName() {
		return "synchronizedBlocks";
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
		CommonTree modifierNode = firstChildMatchingName(tree, "synchronized");
		
		if (modifierNode != null) {
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
