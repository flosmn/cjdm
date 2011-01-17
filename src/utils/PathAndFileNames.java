package utils;

/**
 * This class stores all data paths
 */
public class PathAndFileNames {
	
	public static String PROJECT_SOURCES_PATH;
	public static String WEKA_DATA_PATH;
	public static String R_DATA_PATH;
	public static String CSV_DATA_PATH;
	public static String DATA_BASE_PATH;
	public static String CLASS_CSV_NAME;
	public static String METHOD_CSV_NAME;
	
	static {
		setPaths("");
	}

	public static void setPaths(String basePath) {
		if(basePath.length()>0){
			basePath += "/";
		}
		PROJECT_SOURCES_PATH = basePath + "data/TestData";
		WEKA_DATA_PATH = basePath + "data/arff/";
		R_DATA_PATH = basePath + "data/r/";
		CSV_DATA_PATH = basePath + "data/csv/";
		DATA_BASE_PATH = basePath + "database/cjdm";
		CLASS_CSV_NAME = "class.csv";
		METHOD_CSV_NAME = "method.csv";
		
	}
	
	public static void setProjectSourcesPath(String path){
		PROJECT_SOURCES_PATH = path;
	}

	public static void setCsvDataPath(String path){
		CSV_DATA_PATH = path;
	}

	public static void setDataBasePath(String path) {
		DATA_BASE_PATH = path;
	}

	public static void setClassCsvName(String classCsvName) {
		PathAndFileNames.CLASS_CSV_NAME = classCsvName;
	}

	public static void setMethodCsvName(String methodCsvName) {
		PathAndFileNames.METHOD_CSV_NAME = methodCsvName;
	}
	
	
}