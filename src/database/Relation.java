package database;

import java.util.LinkedList;

public class Relation {
	private Scope scope;	
	private boolean attributesFinished;
	private LinkedList<String> attributes = new LinkedList<String>();
	
	public Relation (Scope scope) {
		this.scope = scope;
	}
	
	public void addAttribute(String attribute) {
		if (attributesFinished) {
			System.out.println("should not add attributes anymore");
			return;
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

		System.out.println(scope + ": " + record.getValues());
		// TODO: write to db
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