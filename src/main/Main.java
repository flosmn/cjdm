package main;

import java.util.Collection;

import database.Database;
import database.Record;

import workers.AbstractWorker;
import workers.CommonTreePackage;
import workers.MethodModifierCounter;

/**
 * Parse a java file or directory of java files using the generated parser
 * ANTclassOrInterfaceDeclarationLR builds from java.g
 */
class Main {

	private static String[] methodModifiers = { "public", "private", "synchronized" };
	private static Database database = new Database();

	public static void main(String[] args) {
		for (String modifier : methodModifiers) {
			database.addAttribute("method_modifier_" + modifier);
		}
		
		Collection<CommonTreePackage> treePackages = (new GenerateTreePackages()).generate();

		for(CommonTreePackage treePackage : treePackages){
			analyseTree(treePackage);
		}		

		database.export();
	}

	private static void analyseTree(CommonTreePackage treePackage) {
		Record record = database.newRecord();

		for (String modifier : methodModifiers) {
			AbstractWorker worker = new MethodModifierCounter(modifier);
			int numberOfModifiers = worker.doWork(treePackage);
			record.setValueForAttribute(numberOfModifiers, "method_modifier_" + modifier);
		}

		database.add(record);
	}
}
