package database;

public class Aggregator {
	private String name;
	
	public static Aggregator SUM = new Aggregator("SUM");
	public static Aggregator MAX = new Aggregator("MAX");
	
	private Aggregator (String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
