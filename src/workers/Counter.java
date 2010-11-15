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
		
		processNode(tree);
				
		List<CommonTree> children = DirtyLittleHelper.castList(CommonTree.class, tree.getChildren());

		for (CommonTree child : children) {
			traverse(child);
		}
	}


	private void processNode(CommonTree tree) {
		processTree(tree, 0);
	}

	private void processTree(CommonTree tree, int nameIndex) {
		if (tree.getText() == null || !tree.getText().matches(nodeNames[nameIndex])) {
			return;
		}
		
		if (nameIndex == nodeNames.length - 1) {
			++counter;
			return;
		}
		
		List<CommonTree> children = DirtyLittleHelper.castList(CommonTree.class, tree.getChildren());
		for (CommonTree child : children) {
			processTree(child, nameIndex + 1);
		}
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
