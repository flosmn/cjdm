package workers;

import java.util.List;


import main.TreePackage;

import org.antlr.runtime.tree.CommonTree;

import database.Aggregator;
import database.Scope;

import utils.DirtyLittleHelper;

/**
 * Extend this abstract class to implement a worker.
 * Every worker computes the value for one attribute using a CommonTreePackage.
 * @author welle
 */
public abstract class Worker {
	protected Scope scope;
	protected Aggregator aggregator;
	
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
	public abstract int doWork(TreePackage treePackage);
	
	public final Scope getScope() {
		return scope;
	}
	
	public final Aggregator getAggregator() {
		return aggregator;
	}
	
	/**
	 * helper method to find the first child of a given name
	 * @param parent the tree to scan the children of
	 * @param name the name of the searched child (regex)
	 * @return the first child of the given type or null if none is found
	 */
	public CommonTree firstChildMatchingName(CommonTree parent, String name) {
		if (parent == null) {
			return null;
		}
		
		List<CommonTree> children = DirtyLittleHelper.castList(CommonTree.class, parent.getChildren());
		
		for (CommonTree child : children) {
			if (child.getText().matches(name)) {
				return child;
			}
		}
				
		return null;
	}
}
