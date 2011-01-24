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

public class RecursiveNestednessCounter extends Worker{
	private int maxNestedness;
	private String[] children;

	public RecursiveNestednessCounter(Attribute attribute, String ... children) {
		this.attribute = attribute;
		this.children = children;
		this.aggregator = Aggregator.MAX;
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
		
		List<CommonTree> children = Utils.castList(CommonTree.class, tree.getChildren());
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
