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
package workers;

import parsing.TreePackage;
import database.Aggregator;

/**
 * This sample worker illustrates how a worker is meant to be implemented.
 * It counts the number of children of the root of the syntax tree.
 * @author welle
 */
public class SampleWorker extends Worker {
	public int counter = 0;
	
	public SampleWorker() {
		// TODO: set attribute
		this.aggregator = Aggregator.SUM;
	}
	
	@Override
	public String getAttributeName() {
		return "counter";
	}

	@Override
	public int doWork(TreePackage treePackage) {
		return ++counter;
	}
}
