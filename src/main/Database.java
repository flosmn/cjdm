package main;

import java.util.HashMap;
import java.util.LinkedList;

public class Database {
	LinkedList<HashMap> list = new LinkedList<HashMap>();
	
	public void add (HashMap record) {
		list.add(record);
	}
	
	public String toString() {
		return list.toString();
	}
}
