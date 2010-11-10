package workers;

import java.util.List;


import org.antlr.runtime.tree.CommonTree;

import utils.DirtyLittleHelper;

public abstract class AbstractWorker {
	public abstract int doWork(CommonTreePackage treePackage);
	
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
