package main;

import utils.PathAndFileNames;
import database.Database;
import database.Scope;
import export.Exporter;
import export.Exporter.ExportType;
import export.ParallelFilter;

public class Ignored {
	public static void main(String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);

		//Exporter.export(Scope.METHOD, ExportType.ARFF, database, "*", Integer.MAX_VALUE, new ParallelFilter(1, false));
		//Exporter.export(Scope.CLASS, ExportType.ARFF, database, "*", Integer.MAX_VALUE, new ParallelFilter(17, false));
		Exporter.export(Scope.PROJECT, ExportType.ARFF, database, "*", Integer.MAX_VALUE, new ParallelFilter(20, false));
		
		database.shutdown();
		System.out.println("Done!");
	}
}
