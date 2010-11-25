package database;

import utils.PathAndFileNames;

public class Exporter {
	public static void main (String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);

		export(database, Scope.METHOD, "*", false);
		export(database, Scope.CLASS, "*", false);
		export(database, Scope.PROJECT, "*", false);
		
		export(database, Scope.METHOD, "*", true);
		export(database, Scope.CLASS, "*", true);
		export(database, Scope.PROJECT, "*", true);
		
		database.shutdown();
		System.out.println("Done!");
	}
	
	public static void export(Database database, Scope scope, String attributes, boolean summarized) {
		String query = "SELECT " + attributes + " FROM " + scope + "_view";
		database.exportArff(query, scope.toString(), summarized);
	}
}
