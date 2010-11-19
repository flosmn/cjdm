package database;

import utils.PathAndFileNames;

public class Exporter {
	public static void main (String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		for (Scope scope : Scope.getInstances()) {
			database.exportArff("SELECT * FROM " + scope + "_view", scope.toString());
		}
		
		database.shutdown();
		
		System.out.println("Done!");
	}
}
