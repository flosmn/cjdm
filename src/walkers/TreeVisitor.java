package walkers;

import java.util.List;


import org.antlr.runtime.tree.CommonTree;

import utils.DirtyLittleHelper;

public abstract class TreeVisitor {
	public abstract int visit(CommonTree tree);
	
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
