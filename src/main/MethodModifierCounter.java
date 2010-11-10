package main;

import java.util.List;

import org.antlr.runtime.tree.CommonTree;

public class MethodModifierCounter extends TreeVisitor {
	
	private int modifierCounter;
	private String modifier;
	
	public MethodModifierCounter(String modifier) {
		this.modifier = modifier;
	}
	
	public void traverse(CommonTree tree){
		if(tree == null){
			System.out.println("tree == null");
			return;
		}
	
		if (tree.getText() != null && tree.getText().endsWith("METHOD_DECL")) {
			processMethod(tree);
		}
		
		List<CommonTree> children = DirtyLittleHelper.castList(CommonTree.class, tree.getChildren());
		if (children == null) {
			return;
		}
		
		for (CommonTree child : children) {
			traverse(child);
		}
	}

	private void processMethod(CommonTree tree) {
		CommonTree modifierList = firstChildWithType(tree, "MODIFIER_LIST");
		CommonTree modifierNode = firstChildWithType(modifierList, modifier);
		
		if (modifierNode != null) {
			++modifierCounter;
		}
	}

	@Override
	public int visit(CommonTree tree) {
		traverse(tree);

		return modifierCounter;
	}
	
}
