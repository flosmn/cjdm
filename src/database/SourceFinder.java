/**
 * @author Christian Wellenbrock
 * @author Florian Simon
 * @author JŸrgen Walter
 * @author Stefan Kober
 * Teams 09, 10
 *
 * This code has been developed during the winter term 2010-2011 at the
 * Karlsruhe Institute of Technology (KIT), Germany.
 * It is part of a project assignment in the course
 * "Multicore Programming in Practice: Tools, Models, and Languages".
 * Project director/instructor:
 * Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package database;

import utils.PathAndFileNames;
import attributes.Attribute;

public class SourceFinder {
	public static void main(String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		find(database, Scope.CLASS, Attribute.ATOMICINTEGER_OBJECT_FIELDS.getName() + " > 0");
		
		database.shutdown();
	}
	
	public static void find(Database database, Scope scope, String conditions) {
		String query = "SELECT * FROM " + scope + "_view WHERE " + conditions;
		database.query(query);
	}
}
