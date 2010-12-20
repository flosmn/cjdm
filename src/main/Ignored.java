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
import attributes.MethodAttribute;
import attributes.ProjectAttribute;
import database.Database;
import database.Scope;
import export.Exporter;
import export.Exporter.ExportType;
import export.ParallelFilter;

public class Ignored {
	public static void main(String[] args) throws Exception {
		//export();
		
		//mineProject();
		//mineClass();
		mineMethod();
	}
	
	public static void export() {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		//exportProject(database);
		//exportClass(database);
		exportMethod(database);
		
		database.shutdown();
	}

	public static void exportProject(Database database) {
		Exporter.export(Scope.PROJECT, ExportType.ARFF, database, Attribute.combine(
				ProjectAttribute.WAIT_CALLS,
				ProjectAttribute.NOTIFY_CALLS,
				ProjectAttribute.NOTIFYALL_CALLS,
				ProjectAttribute.JOIN_CALLS
				), Integer.MAX_VALUE, new ParallelFilter(1, false));
	}
	
	public static void exportClass(Database database) {
		Exporter.export(Scope.CLASS, ExportType.ARFF, database, "*", Integer.MAX_VALUE, new ParallelFilter(17, false));
	}
	
	private static void exportMethod(Database database) {
		Exporter.export(Scope.METHOD, ExportType.ARFF, database, Attribute.combine(
				MethodAttribute.WAIT_CALLS,
				MethodAttribute.NOTIFY_CALLS,
				MethodAttribute.NOTIFYALL_CALLS,
				MethodAttribute.JOIN_CALLS
				), Integer.MAX_VALUE, new ParallelFilter(1, false));
	}
	
	private static void mineProject() throws Exception {
		Apriori apriori = Miner.buildApriori(0.11, 0.95, 1000);
		
		Collection<Bonus> bonusSet = Bonus.buildBonusSet(
				new ItemBonus(".*", "0", -1),
				new ItemBonus(ProjectAttribute.WAIT_CALLS, "[^0]", 1),
				new ItemBonus(ProjectAttribute.NOTIFY_CALLS, "[^0]", 2),
				new ItemBonus(ProjectAttribute.NOTIFYALL_CALLS, "[^0]", 3));
		
		List<Rule> rules = Miner.getRules("project.arff", apriori, bonusSet);

		Rule.printBestRules(rules, 100);
	}
		
	private static void mineClass() throws Exception {
		Apriori apriori = Miner.buildApriori(0, 0, 1);

		Collection<Bonus> bonusSet = Miner.getSynchronizedBonus();

		List<Rule> rules = Miner.getRules("class.arff", apriori, bonusSet);
		
		Rule.printBestRules(rules, 0.3);
	}
	
	private static void mineMethod() throws Exception {
		Apriori apriori = Miner.buildApriori(0.11, 0.95, 20);
		
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
		
		List<Rule> rules = Miner.getRules("method.arff", apriori, bonusSet);
		
		Rule.printBestRules(rules, 0.3);

	}
}
