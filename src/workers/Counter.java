/**
Ê* @author Christian Wellenbrock
 * @author Florian Simon
Ê* @author JŸrgen Walter
 * @author Stefan Kober
Ê* Teams 09, 10
Ê*
Ê* This code has been developed during the winter term 2010-2011 at the
Ê* Karlsruhe Institute of Technology (KIT), Germany.
Ê* It is part of a project assignment in the course
Ê* "Multicore Programming in Practice: Tools, Models, and Languages".
Ê* Project director/instructor:
Ê* Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package workers;

import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import parsing.TreePackage;
import utils.Utils;
import attributes.Attribute;
import database.Aggregator;

public class Counter extends Worker {
	private int counter;
	private String[] nodeNames;
	private boolean isMethodCallCounter;
	
	public Counter(Attribute attribute, String ... nodeNames) {
		this.attribute = attribute;
		this.aggregator = Aggregator.SUM;
		this.nodeNames = nodeNames;
		this.isMethodCallCounter = nodeNames[0].equals("METHOD_CALL");
	}
	
	@Override
	public int doWork(TreePackage treePackage) {
		counter = 0;
		
		traverse(treePackage.getTree());

		return counter;
	}
	
	@Override
	public String getAttributeName() {
		return attribute.getName();
	}

	private boolean matchesPattern(CommonTree tree) {
		return matchesPattern(tree, 0);
	}

	private boolean matchesPattern(CommonTree tree, int nameIndex) {
		if (tree == null || tree.getText() == null || !tree.getText().matches(nodeNames[nameIndex])) {
			return false;
		}
		
		if (nameIndex == nodeNames.length - 1) {
			return true;
		}
		
		List<CommonTree> children = Utils.castList(CommonTree.class, tree.getChildren());
		
		if (this.isMethodCallCounter && nameIndex == nodeNames.length - 2) {
			if (children.size() != 2) {
				System.out.println("children.length != 2");
				return false;
			}
			
			return matchesPattern(children.get(1), nameIndex + 1);
		}

		for (CommonTree child : children) {
			if (matchesPattern(child, nameIndex + 1)) {
				return true;
			}
		}
		
		return false;
	}

	public void traverse(CommonTree tree){
		if(tree == null){
			System.out.println("tree == null");
			return;
		}
		
		if (matchesPattern(tree)) {
			++counter;
		}
		
		List<CommonTree> children = Utils.castList(CommonTree.class, tree.getChildren());

		for (CommonTree child : children) {
			traverse(child);
		}
	}
	
}
