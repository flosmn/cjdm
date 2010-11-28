package database;

import utils.PathAndFileNames;

public class SourceFinder {
	public static void main(String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		find(database, Scope.METHOD, "nestedness_conditionals > 8");
		
		database.shutdown();
	}
	
	public static void find(Database database, Scope scope, String conditions) {
		String query = "SELECT * FROM " + scope + "_view WHERE " + conditions;
		database.query(query);
	}
}
