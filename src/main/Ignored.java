package main;

import java.util.Collection;
import java.util.List;

import mining.Bonus;
import mining.Item;
import mining.ItemBonus;
import mining.Miner;
import mining.PatternBonus;
import mining.Rule;
import utils.PathAndFileNames;
import weka.associations.Apriori;
import attributes.Attribute;
import attributes.ClassAttribute;
import attributes.MethodAttribute;
import attributes.ProjectAttribute;
import database.Database;
import database.Scope;
import export.AttributeCondition;
import export.AttributeFilter;
import export.Exporter;
import export.Exporter.ExportType;
import export.ParallelFilter;

public class Ignored {
	public static void main(String[] args) throws Exception {
		export();
		
		//mineProject("project.arff");
		//mineClass("class.arff");
		//mineMethod("method.arff");
	}
	
	public static void export() {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		exportProject(database, "project.arff");
		//exportClass(database, "class.arff");
		//exportMethod(database, "method.arff");
		
		database.shutdown();
	}

	public static void exportProject(Database database, String fileName) {
		Exporter.export(Scope.PROJECT, ExportType.ARFF, fileName, database,
				Attribute.combine(
						ProjectAttribute.WAIT_CALLS,
						ProjectAttribute.NOTIFY_CALLS,
						ProjectAttribute.NOTIFYALL_CALLS,
						ProjectAttribute.JOIN_CALLS
				), Integer.MAX_VALUE,
				new AttributeFilter(true,
						new AttributeCondition(ProjectAttribute.JOIN_CALLS)
				)
		);
	}
	
	public static void exportClass(Database database, String fileName) {
		Exporter.export(Scope.CLASS, ExportType.ARFF, fileName, database, Attribute.combine(
				ClassAttribute.WAIT_CALLS,
				ClassAttribute.NOTIFY_CALLS,
				ClassAttribute.NOTIFYALL_CALLS,
				ClassAttribute.JOIN_CALLS
				), Integer.MAX_VALUE, new ParallelFilter(1, true));
	}
	
	private static void exportMethod(Database database, String fileName) {
		Exporter.export(Scope.METHOD, ExportType.ARFF, fileName, database, Attribute.combine(
				MethodAttribute.WAIT_CALLS,
				MethodAttribute.NOTIFY_CALLS,
				MethodAttribute.NOTIFYALL_CALLS,
				MethodAttribute.JOIN_CALLS
				), Integer.MAX_VALUE, new ParallelFilter(1, true));
	}
	
	private static void mineProject(String fileName) throws Exception {
		Apriori apriori = Miner.buildApriori(0.11, 0.95, 1000);
		
		Collection<Bonus> bonusSet = Bonus.buildBonusSet(
				new ItemBonus(".*", "0", -1),
				new ItemBonus(ProjectAttribute.WAIT_CALLS, "[^0]", 1),
				new ItemBonus(ProjectAttribute.NOTIFY_CALLS, "[^0]", 2),
				new ItemBonus(ProjectAttribute.NOTIFYALL_CALLS, "[^0]", 3));
		
		List<Rule> rules = Miner.getRules(fileName, apriori, bonusSet);

		Rule.printBestRules(rules, 100);
	}
		
	private static void mineClass(String fileName) throws Exception {
		Apriori apriori = Miner.buildApriori(0, 0, 1);

		Collection<Bonus> bonusSet = Miner.getSynchronizedBonus();

		List<Rule> rules = Miner.getRules(fileName, apriori, bonusSet);
		
		Rule.printBestRules(rules, 0.3);
	}
	
	private static void mineMethod(String fileName) throws Exception {
		Apriori apriori = Miner.buildApriori(0.05, 0.8, 1000);
		
		Collection<Bonus> bonusSet = Bonus.combineBonusSets(
				Bonus.buildBonusSet(
						new PatternBonus(
								new Item(MethodAttribute.PUBLIC_METHODS, "1"),
								new Item(MethodAttribute.PRIVATE_METHODS, "0"), -10),
						new PatternBonus(
								new Item(MethodAttribute.PUBLIC_METHODS, "0"),
								new Item(MethodAttribute.PRIVATE_METHODS, "1"), -10),
						new PatternBonus(
								new Item(MethodAttribute.PRIVATE_METHODS, "1"),
								new Item(MethodAttribute.PUBLIC_METHODS, "0"), -10),
						new PatternBonus(
								new Item(MethodAttribute.PRIVATE_METHODS, "0"),
								new Item(MethodAttribute.PUBLIC_METHODS, "1"), -10)),
				Bonus.getSampleBonusSet()
				);
		
		List<Rule> rules = Miner.getRules(fileName, apriori, bonusSet);
		
		Rule.printBestRules(rules, 1.0);

	}
}
