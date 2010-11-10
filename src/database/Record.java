package database;

import java.util.HashMap;
import java.util.LinkedList;

public class Record {
	HashMap<String, Integer> map = new HashMap<String, Integer>();
	LinkedList<String> attributes = new LinkedList<String>();
	
	public Record(LinkedList<String> attributes) {
		this.attributes = attributes;
	}
	
	public void setValueForAttribute(Integer value, String attribute) {
		if (!attributes.contains(attribute)) {
			System.out.println("setting value for invalid attribute: " + attribute);
		}
		
		map.put(attribute, value);
	}
	
	public boolean isValid() {
		return !map.containsValue(null);
	}
	
	public LinkedList<Integer> getValues() {
		LinkedList<Integer> values = new LinkedList<Integer>();
		for (String attribute : attributes) {
			Integer value = map.get(attribute);
			if (value == null) {
				System.out.println("attribute not set: " + attribute);
			}
			values.add(value);
		}
		return values;
	}
	
	public String toString() {
		return map.toString();
	}
}
