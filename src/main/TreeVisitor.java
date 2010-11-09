package main;

import java.util.List;

import org.antlr.runtime.tree.CommonTree;

public abstract class TreeVisitor {
	public abstract int visit(CommonTree tree);
	
	public CommonTree firstChildWithType(CommonTree parent, String type) {
		if (parent == null) {
			return null;
		}
		
		List<CommonTree> children = parent.getChildren();
		
		for (CommonTree child : children) {
			if (child.getText().equals(type)) {
				return child;
			}
		}
		
		return null;
	}
}
