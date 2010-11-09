package main;

import java.util.Collection;
import java.util.HashMap;

/**
 * Parse a java file or directory of java files using the generated parser
 * ANTclassOrInterfaceDeclarationLR builds from java.g
 */
class Main {

	private static String[] modifiers = { "public", "private", "synchronized" };
	private static Database database = new Database();

	public static void main(String[] args) {
		Collection<CommonTreePackage> treePackages = (new GenerateTreePackages()).generate();

		for(CommonTreePackage treePackage : treePackages){
			analyseTree(treePackage);
		}		

		System.out.println(database);
	}

	private static void analyseTree(CommonTreePackage treePackage) {
		for (String modifier : modifiers) {
			TreeVisitor visitor = new MethodModifierCounter(modifier);
			int numberOfModifiers = visitor.visit(treePackage.getTree());
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put(	treePackage.getProjectName() + "." + 
						treePackage.getFileName() +  "_method_modifier_" + modifier,
						numberOfModifiers);
			database.add(map);
		}		
		System.out.println(database);
	}
}
