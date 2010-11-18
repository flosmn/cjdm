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
		this.parent = parent;
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
	
	public Scope getParent() {
		return parent;
	}
	
	public String toString() {
		return scope;
	}
}
