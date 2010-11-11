package workers;

import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import utils.DirtyLittleHelper;

public class AttributeModifierCounter extends Worker {
	
	private int modifierCounter;
	private String modifierName;
	
	public AttributeModifierCounter(String modifierName) {
		this.modifierName = modifierName;
	}
	
	public void traverse(CommonTree tree){
		if(tree == null){
			System.out.println("tree == null");
			return;
		}
	
		if (tree.getText() != null && tree.getText().equals("VAR_DECLARATION")) {
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
		CommonTree modifierNode = firstChildWithType(modifierList, modifierName);
		
		if (modifierNode != null) {
			++modifierCounter;
		}
	}

	@Override
	public int doWork(CommonTreePackage treePackage) {
		modifierCounter = 0;
		
		traverse(treePackage.getTree());

		return modifierCounter;
	}

	@Override
	public String getAttributeName() {
		return "attribute_modifier_" + modifierName;
	}
	
}
