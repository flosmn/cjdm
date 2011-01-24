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

import java.util.ArrayList;
import java.util.Collection;

public class Scope {
	private String scope;
	private Scope parent;
	private static Collection<Scope> instances;

	public static Scope PROJECT = new Scope("project", null);
	public static Scope CLASS = new Scope("class", PROJECT);
	public static Scope METHOD = new Scope("method", CLASS);
	
	private Scope(String scope, Scope parent) {
		this.scope = scope;
		this.parent = parent == null ? this : parent;
	}
	
	public static Collection<Scope> getInstances() {
		if (instances == null) {
			instances = new ArrayList<Scope>();
			
			instances.add(PROJECT);
			instances.add(CLASS);
			instances.add(METHOD);
		}
		
		return instances;
	}
	
	public Scope getParent() {
		return parent;
	}
	
	public String toString() {
		return scope;
	}
}
