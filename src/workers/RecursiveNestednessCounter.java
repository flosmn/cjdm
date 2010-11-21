package workers;

import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import database.Aggregator;
import database.Scope;

import utils.DirtyLittleHelper;
import main.TreePackage;

public class RecursiveNestednessCounter extends Worker{
	private int maxNestedness;
	private String attributeName;
	private String[] children;

	public RecursiveNestednessCounter(String attributeName, Scope scope, String ... children) {
		this.scope = Scope.METHOD;
		this.attributeName = attributeName;
		this.children = children;
		this.aggregator = Aggregator.MAX;
	}
	
	@Override
	public String getAttributeName() {
		return this.attributeName;
	}
	
	public void traverse(CommonTree tree, int nestedness){
		if(tree == null){
			System.out.println("tree == null");
			return;
		}
	
		if (tree.getText() != null && tree.getText().equals("BLOCK_SCOPE")) {
			if(hasChild(tree)){
				nestedness++;
				if(nestedness > maxNestedness){
					maxNestedness = nestedness;
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

	private boolean hasChild(CommonTree tree) {
		for(String str : children){
			if(firstChildMatchingName(tree, str) != null){
				return true;
			}
		}
		return false;
	}

	@Override
	public int doWork(TreePackage treePackage) {
		maxNestedness = 0;
		
		traverse(treePackage.getTree(), 0);
		
		return maxNestedness;
	}

}
