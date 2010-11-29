package database;

import utils.PathAndFileNames;

public class SourceFinder {
	public static void main(String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		find(database, Scope.PROJECT, "synchronized_methods between 650 AND 700");
		
		database.shutdown();
	}
	
	public static void find(Database database, Scope scope, String conditions) {
		String query = "SELECT * FROM " + scope + "_view WHERE " + conditions;
		database.query(query);
	}
}
