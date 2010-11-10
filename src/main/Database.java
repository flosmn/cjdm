package main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Database {
	private boolean attributesFinished;
	private LinkedList<String> attributes = new LinkedList<String>();
	private LinkedList<LinkedList<Integer>> list = new LinkedList<LinkedList<Integer>>();

	public void addAttribute(String attribute) {
		if (attributesFinished) {
			System.out.println("should not add attributes anymore");
		}

		attributes.add(attribute);
	}

	public Record newRecord() {
		attributesFinished = true;

		return new Record(attributes);
	}

	public void add(Record record) {
		if (!record.isValid()) {
			System.out.println("adding invalid record");
		}

		list.add(record.getValues());
	}

	public String toString() {
		return attributes.toString() + "\n" + list.toString();
	}
	
	public void export() {
		// TODO: write to file
		
		System.out.println("@relation cjdm");
		
		for (String attribute : attributes) {
			System.out.println("@attribute " + attribute + " integer");
		}
		
		System.out.println("@data");
		
		for (LinkedList<Integer> record : list) {
			Iterator<Integer> iter = record.iterator();
			while (iter.hasNext()) {
				Integer value = iter.next();
				if (iter.hasNext()) {
					System.out.print(value + ", ");
				} else {
					System.out.println(value);
				}
			}
		}
	}
}