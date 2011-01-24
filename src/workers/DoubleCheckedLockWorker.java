package workers;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;


import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import parsing.TreePackage;

import utils.Utils;
import attributes.MethodAttribute;
import database.Aggregator;

public class DoubleCheckedLockWorker extends Worker{
	private int maxNestedness;
	private int nestedness;
	private Collection<String> lockObjects;

	public DoubleCheckedLockWorker() {
		this.attribute = MethodAttribute.CLASS_NAME;//MethodAttribute.NESTEDNESS_LOCKS;
		this.aggregator = Aggregator.MAX;
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
		
		List<CommonTree> children = Utils.castList(CommonTree.class, tree.getChildren());
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

