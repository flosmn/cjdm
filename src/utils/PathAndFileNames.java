package utils;

public class PathAndFileNames {
	
	public static String PROJECT_SOURCES_PATH;
	public static String WEKA_DATA_PATH;
	public static String R_DATA_PATH;
	public static String CSV_DATA_PATH;
	public static String DATA_BASE_PATH;
	
	static {
		setPaths("");
	}

	public static void setPaths(String basePath) {
		if(basePath.length()>0){
			basePath += "/";
		}
		PROJECT_SOURCES_PATH = basePath + "javaprojectsources/TestData";
		WEKA_DATA_PATH = basePath + "javaprojectsources/arff/";
		R_DATA_PATH = basePath + "javaprojectsources/r/";
		CSV_DATA_PATH = basePath + "javaprojectsources/csv/";
		DATA_BASE_PATH = basePath + "javaprojectsources/database/cjdm";
	}	
}