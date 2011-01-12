package export;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import utils.Logger;
import utils.PathAndFileNames;
import attributes.Attribute;
import attributes.ClassAttribute;
import attributes.MethodAttribute;
import attributes.ProjectAttribute;
import database.Database;
import database.ResultSetReceiver;
import database.Scope;

public class Exporter implements ResultSetReceiver {
	public static void main (String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		// reference usage of export
		export(Scope.PROJECT, ExportType.CSV, database,
				Attribute.combine(
						ProjectAttribute.PROJECT_NAME,
						Attribute.getAllSynchronizedAttributes(),
						Attribute.getAllPatternAttributes(),
						Attribute.getAllMethodCallAttributes(),
						Attribute.getAllNestednessAttributes(),
						Attribute.getAllInterfaceAttributes(),
						Attribute.getAllObjectAttributes(),
						Attribute.getAllObjectFieldsAttributes()),
				Integer.MAX_VALUE);

		export(Scope.CLASS, ExportType.CSV, database,
				Attribute.combine(
						ClassAttribute.COMBINED_CLASS_NAME,
						Attribute.getAllSynchronizedAttributes(),
						Attribute.getAllPatternAttributes(),
						Attribute.getAllMethodCallAttributes(),
						Attribute.getAllNestednessAttributes(),
						Attribute.getAllInterfaceAttributes(),
						Attribute.getAllObjectAttributes(),
						Attribute.getAllObjectFieldsAttributes()),
				Integer.MAX_VALUE, new ParallelFilter(false, 2));

		export(Scope.METHOD, ExportType.CSV, database, 
				Attribute.combine(
						MethodAttribute.COMBINED_METHOD_NAME,
						MethodAttribute.WHILE_WAIT,
						Attribute.getAllSynchronizedAttributes(),
						Attribute.getAllPatternAttributes(),
						Attribute.getAllMethodCallAttributes(),
						Attribute.getAllNestednessAttributes()),
				Integer.MAX_VALUE, new ParallelFilter(false, 3));

		database.shutdown();
		System.out.println("Done!");
	}
	
	public enum ExportType { ARFF, CSV };

	private Scope scope;
	private ExportType exportType;
	private String fileName;
	private ExportFilter exportFilter;
	
	public Exporter(Scope scope, ExportType exportType, String fileName, ExportFilter exportFilter) {
		this.scope = scope;
		this.exportType = exportType;
		this.fileName = fileName;
		this.exportFilter = exportFilter;
	}

	public Exporter(Scope scope, ExportType exportType, String fileName) {
		this(scope, exportType, fileName, new ExportFilter());
	}
	
	public static void export(Scope scope, ExportType exportType, String fileName, Database database, String attributes, int maxRowCount, ExportFilter exportFilter) {
		Exporter exporter = new Exporter(scope, exportType, fileName, exportFilter);
		exporter.export(database, attributes, maxRowCount);
	}

	public static void export(Scope scope, ExportType exportType, String fileName, Database database, String attributes, int maxRowCount) {
		export(scope, exportType, fileName, database, attributes, maxRowCount, new ExportFilter());
	}
	
	public static void export(Scope scope, ExportType exportType, Database database, String attributes, int maxValue, ExportFilter exportFilter) {
		export(scope, exportType, getFileName(scope, exportType), database, attributes, maxValue, exportFilter);
	}
	
	public static void export(Scope scope, ExportType exportType, Database database, String attributes, int maxValue) {
		export(scope, exportType, getFileName(scope, exportType), database, attributes, maxValue);
	}
	
	public void export(Database database, String attributes, int maxRowCount) {
		System.out.println("exporting " + scope.toString() + " to " + exportType);
		
		String query = "SELECT " + attributes + " FROM " + scope + "_view LIMIT " + maxRowCount;
		database.requestResultSet(query, this);
	}

	@Override
	public void receive(ResultSet resultSet) {
		try {
			export(resultSet);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	
    private void export(ResultSet resultSet) throws SQLException {
        Logger logger = new Logger();
		logger.log(getHeader(resultSet.getMetaData()));
		logger.log(getBody(resultSet));
        logger.writeToFile(getFilePath(), fileName);
	}

	private String getFilePath() {
		switch (exportType) {
			case ARFF: return PathAndFileNames.WEKA_DATA_PATH;
			case CSV: return PathAndFileNames.CSV_DATA_PATH;
		}
		return null;
	}
	
	private String getHeader(ResultSetMetaData metaData) throws SQLException {
		switch (exportType) {
			case ARFF: return getArffHeader(metaData);
			case CSV: return getCsvHeader(metaData);
		}
		return null;
	}
	
	private static String getFileName(Scope scope, ExportType exportType) {
		switch(exportType) {
    		case ARFF: return scope.toString() + ".arff";
    		case CSV: return scope.toString() + ".csv";
		}
		return null;
	}
	
	private String getArffHeader(ResultSetMetaData metaData) throws SQLException {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("@relation " + scope.toString() + "\n");
		
        int columnCount = metaData.getColumnCount();
		for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
			String attributeName = metaData.getColumnName(columnIndex + 1);
			stringBuffer.append("@attribute \"" + attributeName + "\" string\n");
		}
		
		stringBuffer.append("@data\n");
		return stringBuffer.toString();
	}
	
	private String getCsvHeader(ResultSetMetaData metaData) throws SQLException {
		StringBuffer stringBuffer = new StringBuffer();
		
        int columnCount = metaData.getColumnCount();
		for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
			String attributeName = metaData.getColumnName(columnIndex + 1);
			stringBuffer.append(attributeName);

			boolean isLastColumn = (columnIndex == columnCount - 1);
			stringBuffer.append(isLastColumn ? "\n" : ", ");
		}
		
		return stringBuffer.toString();
	}

	private String getBody(ResultSet resultSet) throws SQLException {
		StringBuffer stringBuffer = new StringBuffer();
		
		int resultCounter = 0;
		int matchCounter = 0;
		
		for (;resultSet.next();) {
			++resultCounter;
			String line = exportFilter.filter(resultSet);
			
			if (line != null) {
				++matchCounter;
				stringBuffer.append(line);
			}
        }
		
		System.out.println("exported " + matchCounter + " of " + resultCounter + " results");
		
		return stringBuffer.toString();
    }
}
