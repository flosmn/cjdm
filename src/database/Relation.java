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
	
	public void createTable() {
		String attributeDefs = "";
		String attributeNames = "";
		for (String attribute : attributes) {
			attributeDefs += ", " + attribute + " INTEGER";
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
			System.out.println(exception.getMessage());
		}
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
	
	/*
	public void export() {
		Logger logger = new Logger();
		logger.logAndStartNewLine("@relation "+PathAndFileNames.RELATION_NAME);
		
		for (String attribute : attributes) {
			logger.logAndStartNewLine("@attribute \"" + attribute + "\" integer");
		}
		
		logger.logAndStartNewLine("@data");
		
		for (LinkedList<Integer> record : list) {
			Iterator<Integer> iter = record.iterator();
			while (iter.hasNext()) {
				Integer value = iter.next();
				if (iter.hasNext()) {
					logger.log(value + ", ");
				} else {
					logger.logAndStartNewLine(value + "");
				}
			}
		}
		
		logger.writeToFile(
					PathAndFileNames.WEKA_TEST_DATA_PATH,
					PathAndFileNames.EXPORT_FILE_NAME);
	}
	*/
	
	public Scope getScope() {
		return scope;
	}
}