/**
�* @author Christian Wellenbrock
 * @author Florian Simon
�* @author J�rgen Walter
 * @author Stefan Kober
�* Teams 09, 10
�*
�* This code has been developed during the winter term 2010-2011 at the
�* Karlsruhe Institute of Technology (KIT), Germany.
�* It is part of a project assignment in the course
�* "Multicore Programming in Practice: Tools, Models, and Languages".
�* Project director/instructor:
�* Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
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

public class CountNestednessOfLocks extends Worker{
	private int maxNestedness;
	private int nestedness;
	private Collection<String> lockObjects;

	public CountNestednessOfLocks() {
		this.attribute = MethodAttribute.NESTEDNESS_LOCKS;
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

