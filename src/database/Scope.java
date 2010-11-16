package database;

import java.util.ArrayList;
import java.util.Collection;

public class Scope {
	private String scope;
	private static Collection<Scope> instances;

	public static Scope METHOD = new Scope("method");
	public static Scope CLASS = new Scope("class");
	public static Scope PROJECT = new Scope("project");
	
	private Scope(String scope) {
		this.scope = scope;
	}
	
	public static Collection<Scope> getInstances() {
		if (instances == null) {
			instances = new ArrayList<Scope>();
			
			instances.add(METHOD);
			instances.add(CLASS);
			instances.add(PROJECT);
		}
		
		return instances;
	}

	public String toString() {
		return scope;
	}
}
