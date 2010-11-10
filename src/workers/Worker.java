package workers;

import java.util.List;


import org.antlr.runtime.tree.CommonTree;

import utils.DirtyLittleHelper;

public abstract class Worker {
	public abstract int doWork(CommonTreePackage treePackage);
	public abstract String getAttributeName();
	
	// util method for tree visitors
	public CommonTree firstChildWithType(CommonTree parent, String type) {
		if (parent == null) {
			return null;
		}
		
		List<CommonTree> children = DirtyLittleHelper.castList(CommonTree.class, parent.getChildren());
		
		for (CommonTree child : children) {
			if (child.getText().equals(type)) {
				return child;
			}
		}
		
		return null;
	}
}
