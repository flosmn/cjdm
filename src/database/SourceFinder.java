package database;

import utils.PathAndFileNames;

public class SourceFinder {
	public static void main(String[] args) {
		find(Scope.CLASS, "nestedness_conditionals > 0");
	}
	
	public static void find(Scope scope, String conditions) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		String query = "SELECT * FROM " + scope + "_view WHERE " + conditions;
		database.query(query);
		
		database.shutdown();
	}
}
