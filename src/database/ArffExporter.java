package database;

import utils.PathAndFileNames;

public class ArffExporter {
	public static void main (String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		/*
		export(database, Scope.METHOD, "*", false, Integer.MAX_VALUE);
		export(database, Scope.CLASS, "*", false, Integer.MAX_VALUE);
		export(database, Scope.PROJECT, "*", false, Integer.MAX_VALUE);
		*/
		export(database, Scope.METHOD, "SYNCHRONIZED_METHODS, METHOD_CALLS, SYNCHRONIZED_BLOCKS, LOCK_CALLS", true, 100);
	//	export(database, Scope.CLASS, "*", true, Integer.MAX_VALUE);
	//	export(database, Scope.PROJECT, "*", true, Integer.MAX_VALUE);
		
		database.shutdown();
		System.out.println("Done!");
	}
	
	public static void export(Database database, Scope scope, String attributes, boolean summarized, int maxRowCount) {
		String query = "SELECT " + attributes + " FROM " + scope + "_view LIMIT " + maxRowCount;
		database.exportArff(query, scope.toString(), summarized);
	}
}
