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

public class PatternCounter extends Worker {
	private int counter;
	private String[] nodeNames;
	
	public PatternCounter(Attribute attribute, String ... nodeNames) {
		this.attribute = attribute;
		this.aggregator = Aggregator.SUM;
		this.nodeNames = nodeNames;
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
		if (tree == null || tree.getText() == null) {
			return false;
		}
		
		if (tree.getText().matches(nodeNames[nameIndex])) {
			if(nameIndex == nodeNames.length - 1){
				return true;
			}
			++nameIndex;
		}
		
		List<CommonTree> children = Utils.castList(CommonTree.class, tree.getChildren());
		
		boolean matches = false;
		for (CommonTree child : children) {
			if (matchesPattern(child, nameIndex)) {
				matches = true;
			}
		}
		
		return matches;
	}
	
	public void traverse(CommonTree tree){
		if(tree == null){
			System.out.println("tree == null");
			return;
		}
		
		if (matchesPattern(tree)) {
			++counter;
		}
	}
	
}
