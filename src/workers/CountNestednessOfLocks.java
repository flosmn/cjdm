package workers;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import main.TreePackage;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import utils.DirtyLittleHelper;
import attributes.MethodAttributes;
import database.Aggregator;
import database.Scope;

public class CountNestednessOfLocks extends Worker{
	private int maxNestedness;
	private int nestedness;
	private Collection<String> lockObjects;

	public CountNestednessOfLocks() {
		this.scope = Scope.METHOD;
		this.aggregator = Aggregator.MAX;
	}
	
	@Override
	public String getAttributeName() {
		return MethodAttributes.NESTEDNESS_LOCKS;
	}
	
	public void traverse(CommonTree tree){
		if(tree == null){
			System.out.println("tree == null");
			return;
		}
	
		if (tree.getText() != null && tree.getText().equals(".")) {
			Tree leftChild = tree.getChild(0);
			Tree rightChild = tree.getChild(1);
			
			if(rightChild.getText().equals("lock")){
				nestedness++;
				lockObjects.add(leftChild.getText());
				
				if(nestedness > maxNestedness){
					maxNestedness = nestedness;
				}
			}
			
			if(rightChild.getText().equals("unlock") && 
					lockObjects.contains(leftChild.getText())){
				nestedness--;
				lockObjects.remove(leftChild.getText());
			}
		}
		
		List<CommonTree> children = DirtyLittleHelper.castList(CommonTree.class, tree.getChildren());
		if (children == null) {
			return;
		}
		
		for (CommonTree child : children) {
			traverse(child);
		}
	}

	@Override
	public int doWork(TreePackage treePackage) {
		lockObjects = new HashSet<String>();
		maxNestedness = 0;
		nestedness = 0;
		
		traverse(treePackage.getTree());
		
		return maxNestedness;
	}

}

