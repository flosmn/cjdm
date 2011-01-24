/**
Ê* @author Christian Wellenbrock
 * @author Florian Simon
Ê* @author JŸrgen Walter
 * @author Stefan Kober
Ê* Teams 09, 10
Ê*
Ê* This code has been developed during the winter term 2010-2011 at the
Ê* Karlsruhe Institute of Technology (KIT), Germany.
Ê* It is part of a project assignment in the course
Ê* "Multicore Programming in Practice: Tools, Models, and Languages".
Ê* Project director/instructor:
Ê* Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package database;

public class Aggregator {
	private String name;
	
	public static Aggregator SUM = new Aggregator("SUM");
	public static Aggregator MAX = new Aggregator("MAX");
	
	private Aggregator (String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
