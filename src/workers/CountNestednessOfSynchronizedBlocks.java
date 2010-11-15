package workers;

import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import utils.DirtyLittleHelper;
import main.CommonTreePackage;

public class CountNestednessOfSynchronizedBlocks extends Worker{

	private int maxNestedNess;
	
	@Override
	public String getAttributeName() {
		return "nestedness_synchronized_blocks";
	}
	
	public void traverse(CommonTree tree, int nestedness){
		if(tree == null){
			System.out.println("tree == null");
			return;
		}
	
		if (tree.getText() != null && tree.getText().equals("BLOCK_SCOPE")) {
			if(hasSynchronized(tree)){
				nestedness++;
				if(nestedness > maxNestedNess){
					maxNestedNess = nestedness;
				}
			}
		}
		
		List<CommonTree> children = DirtyLittleHelper.castList(CommonTree.class, tree.getChildren());
		if (children == null) {
			return;
		}
		
		for (CommonTree child : children) {
			traverse(child, nestedness);
		}
	}

	private boolean hasSynchronized(CommonTree tree) {
		CommonTree modifierNode = firstChildWithType(tree, "synchronized");
		return (modifierNode != null);
	}

	@Override
	public int doWork(CommonTreePackage treePackage) {
		maxNestedNess = 0;
		
		traverse(treePackage.getTree(), 0);
		
		return maxNestedNess;
	}

}
