package database;

import java.util.LinkedList;

public class Relation {
	private Database database;
	private Scope scope;
	private boolean tableCreated;
	private LinkedList<String> attributes = new LinkedList<String>();
	private String insertPrefix;
	
	public Relation (Database database, Scope scope) {
		this.database = database;
		this.scope = scope;
	}
	
	public void addAttribute(String attribute) {
		if (tableCreated) {
			System.out.println("should not add attributes anymore");
			return;
		}

		attributes.add(attribute);
	}

	public Record newRecord() {
		tableCreated = true;

		return new Record(attributes);
	}

	public void dropView() {
		try {
			database.update("DROP VIEW " + scope + "_view");
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	public void dropTable() {
		try {
			database.update("DROP TABLE " + scope);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	/**
	 * @return next free index
	 */
	public int createTable() {
		int nextIndex = 0;
		
		String attributeDefs = "";
		String attributeNames = "";
		for (String attribute : attributes) {
			if (attribute.endsWith("name")) {
				attributeDefs += ", " + attribute + " STRING";
			} else {
				attributeDefs += ", " + attribute + " INTEGER";
			}
			attributeNames += ", " + attribute;
		}
		
		String query = "CREATE TABLE " + scope + " ( ID INTEGER PRIMARY KEY, parentID INTEGER";
		if (scope != Scope.PROJECT) {
			query += " FOREIGN KEY REFERENCES " + scope.getParent() + "(ID)";
		}
		query += ", name VARCHAR(256)" + attributeDefs + " )";
		insertPrefix = "INSERT INTO " + scope + " ( ID, parentID, name" + attributeNames + " ) VALUES ";

		try {
			database.update(query);
		} catch (Exception exception) {
			nextIndex = database.maxID(scope);
			System.out.println(exception.getMessage());
		}
		
		return nextIndex;
	}
	
	public void add(Record record) {
		if (!record.isValid()) {
			System.out.println("adding invalid record");
		}

		String query = insertPrefix + record.insertSuffix();

		try {
			database.update(query);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public String toString() {
		return attributes.toString();
	}
	
	public Scope getScope() {
		return scope;
	}

	public boolean hasRowNamed(String name) {
		return database.hasRow(scope, name);
	}
}