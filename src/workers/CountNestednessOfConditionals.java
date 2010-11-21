package workers;

import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import database.Aggregator;
import database.Scope;

import utils.DirtyLittleHelper;
import main.TreePackage;

public class CountNestednessOfConditionals extends Worker{
	private int maxNestedness;

	public CountNestednessOfConditionals() {
		this.scope = Scope.METHOD;
		this.aggregator = Aggregator.MAX;
	}
	
	@Override
	public String getAttributeName() {
		return "nestedness_conditionals";
	}
	
	public void traverse(CommonTree tree, int nestedness){
		if(tree == null){
			System.out.println("tree == null");
			return;
		}
	
		if (tree.getText() != null && tree.getText().equals("BLOCK_SCOPE")) {
			if(hasIf(tree)){
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

	private boolean hasIf(CommonTree tree) {
		CommonTree modifierNode = firstChildMatchingName(tree, "if");
		return (modifierNode != null);
	}

	@Override
	public int doWork(TreePackage treePackage) {
		maxNestedness = 0;
		
		traverse(treePackage.getTree(), 0);
		
		return maxNestedness;
	}

}
