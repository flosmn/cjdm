package workers;

import java.util.List;


import main.CommonTreePackage;

import org.antlr.runtime.tree.CommonTree;

import utils.DirtyLittleHelper;

/**
 * Extend this abstract class to implement a worker.
 * Every worker computes the value for one attribute using a CommonTreePackage.
 * @author welle
 */
public abstract class Worker {
	/**
	 * getter for attribute name
	 * @return the name of the computed attribute
	 */
	public abstract String getAttributeName();
	
	/**
	 * computes the value
	 * @param treePackage the CommonTreePackage to compute the value for
	 * @return the value of the attribute for the given treePackage.
	 */
	public abstract int doWork(CommonTreePackage treePackage);
	
	/**
	 * helper method to find the first child of a given type
	 * @param parent the tree to scan the children of
	 * @param type the type of the searched child
	 * @return the first child of the given type or null if none is found
	 */
	public CommonTree firstChildWithType(CommonTree parent, String type) {
		if (parent == null) {
			return null;
		}
		
		if(parent.getText().equals(type)){
			return parent;
		}
		
		List<CommonTree> children = DirtyLittleHelper.castList(CommonTree.class, parent.getChildren());
		
		CommonTree result = null;
		for (CommonTree child : children) {
			result = firstChildWithType(child, type);
			
			if(result != null) {
				return result;
			}
		}
				
		return null;
	}
}
