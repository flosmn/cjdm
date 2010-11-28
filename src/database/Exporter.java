package database;

import utils.PathAndFileNames;

public class Exporter {
	public static void main (String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);

		export(database, Scope.METHOD, "*", false, Integer.MAX_VALUE);
		export(database, Scope.CLASS, "*", false, Integer.MAX_VALUE);
		export(database, Scope.PROJECT, "*", false, Integer.MAX_VALUE);
		
		export(database, Scope.METHOD, "*", true, 100);
		export(database, Scope.CLASS, "*", true, 100);
		export(database, Scope.PROJECT, "*", true, 100);
		
		database.shutdown();
		System.out.println("Done!");
	}
	
	public static void export(Database database, Scope scope, String attributes, boolean summarized, int maxRowCount) {
		String query = "SELECT " + attributes + " FROM " + scope + "_view";
		database.exportArff(query, scope.toString(), summarized, maxRowCount);
	}
}
