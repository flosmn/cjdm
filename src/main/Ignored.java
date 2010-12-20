package main;

import java.util.Collection;
import java.util.List;

import mining.Bonus;
import mining.Item;
import mining.Miner;
import mining.PatternBonus;
import mining.Rule;
import utils.PathAndFileNames;
import weka.associations.Apriori;
import attributes.MethodAttribute;
import database.Database;
import database.Scope;
import export.Exporter;
import export.Exporter.ExportType;
import export.ParallelFilter;

public class Ignored {
	public static void main(String[] args) throws Exception {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);

		exportProject(database);
		exportClass(database);
		exportMethod(database);

		database.shutdown();
		
		mineProject();
		mineClass();
		mineMethod();
	}
	
	private static void exportProject(Database database) {
		Exporter.export(Scope.PROJECT, ExportType.ARFF, database, "*", Integer.MAX_VALUE, new ParallelFilter(29, false));
	}
	
	private static void exportClass(Database database) {
		Exporter.export(Scope.CLASS, ExportType.ARFF, database, "*", Integer.MAX_VALUE, new ParallelFilter(17, false));
	}
	
	private static void exportMethod(Database database) {
		Exporter.export(Scope.METHOD, ExportType.ARFF, database, "*", Integer.MAX_VALUE, new ParallelFilter(1, false));
	}
	
	private static void mineProject() throws Exception {
		Apriori projectApriori = Miner.buildApriori(0.11, 0.95, 20);

		//TODO solve this better, without SuppressWarnings
		@SuppressWarnings("unchecked")
		Collection<Bonus> projectBonusSet = Bonus.combineBonusSets(
				Bonus.buildBonusSet(
						new PatternBonus(
								new Item(MethodAttribute.PUBLIC_METHODS, "1"),
								new Item(MethodAttribute.PRIVATE_METHODS, "0"), -10)
						),
				Miner.getPublicPrivateBonus(),
				Miner.getSynchronizedBonus()
		);
		
		List<Rule> projectRules = Miner.rateAndSort("project.arff", projectApriori, projectBonusSet);

		System.out.println("project: " );
		Rule.printBestRules(projectRules, 0.3);
	}
		
	private static void mineClass() throws Exception {
		Apriori classApriori = Miner.buildApriori(0, 0, 1);

		Collection<Bonus> classBonusSet = Miner.getSynchronizedBonus();

		List<Rule> classRules = Miner.rateAndSort("class.arff", classApriori, classBonusSet);
		
		System.out.println("class: " );
		Rule.printBestRules(classRules, 0.3);
	}
	
	private static void mineMethod() throws Exception {
		Apriori methodApriori = Miner.buildApriori(0.11, 0.95, 20);
		
		Collection<Bonus> methodBonusSet = Bonus.buildBonusSet(
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
						new Item(MethodAttribute.PUBLIC_METHODS, "1"), -10));
		
		List<Rule> methodRules = Miner.rateAndSort("method.arff", methodApriori, methodBonusSet);
		
		System.out.println("method: " );
		Rule.printBestRules(methodRules, 0.3);

	}
}
