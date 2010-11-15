package workers;

import java.util.List;

import main.CommonTreePackage;

import org.antlr.runtime.tree.CommonTree;

import utils.DirtyLittleHelper;

public class Counter extends Worker {
	
	private int counter;
	private String[] nodeNames;

	
	public Counter(String ... nodeNames) {
		this.nodeNames = nodeNames;
	}
	
	public void traverse(CommonTree tree){
		if(tree == null){
			System.out.println("tree == null");
			return;
		}
		
		if (matchesPattern(tree)) {
			++counter;
		}
				
		List<CommonTree> children = DirtyLittleHelper.castList(CommonTree.class, tree.getChildren());

		for (CommonTree child : children) {
			traverse(child);
		}
	}


	private boolean matchesPattern(CommonTree tree) {
		return matchesPattern(tree, 0);
	}

	private boolean matchesPattern(CommonTree tree, int nameIndex) {
		if (tree.getText() == null || !tree.getText().matches(nodeNames[nameIndex])) {
			return false;
		}

		if (nameIndex == nodeNames.length - 1) {
			return true;
		}
		
		List<CommonTree> children = DirtyLittleHelper.castList(CommonTree.class, tree.getChildren());
		for (CommonTree child : children) {
			if (matchesPattern(child, nameIndex + 1)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int doWork(CommonTreePackage treePackage) {
		counter = 0;
		
		traverse(treePackage.getTree());

		return counter;
	}

	@Override
	public String getAttributeName() {
		String names = nodeNames[0];
		for (int i = 1; i < nodeNames.length; ++i) {
			names += "," + nodeNames[i];
		}
		return "Counter(" + names + ")";
	}
	
}
