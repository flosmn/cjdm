package export;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import utils.Logger;
import utils.PathAndFileNames;
import database.Database;
import database.ResultSetReceiver;
import database.Scope;

public class Exporter implements ResultSetReceiver {
	public static void main (String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		/*
		export(Scope.PROJECT, ExportType.CSV, database,
				Attribute.combine(ProjectAttribute.PROJECT_NAME) + ","
				+ Attribute.getAllSynchronizedAttributes() + ","
				+ Attribute.getAllPatternAttributes() + ","
				+ Attribute.getAllMethodCallAttributes() + ","
				+ Attribute.getAllNestednessAttributes() + ","
				+ Attribute.getAllInterfaceAttributes() + ","
				+ Attribute.getAllObjectAttributes() + ","
				+ Attribute.getAllObjectFieldsAttributes(),
				Integer.MAX_VALUE, new ExportFilter());

		export(Scope.CLASS, ExportType.CSV, database,
				Attribute.combine(ClassAttribute.COMBINED_CLASS_NAME) + ","
						+ Attribute.getAllSynchronizedAttributes() + ","
						+ Attribute.getAllPatternAttributes() + ","
						+ Attribute.getAllMethodCallAttributes() + ","
						+ Attribute.getAllNestednessAttributes() + ","
						+ Attribute.getAllInterfaceAttributes() + ","
						+ Attribute.getAllObjectAttributes() + ","
						+ Attribute.getAllObjectFieldsAttributes(),
				Integer.MAX_VALUE, new ParallelFilter(2, false));

		export(Scope.METHOD, ExportType.CSV, database, 
				Attribute.combine(MethodAttribute.COMBINED_METHOD_NAME, Attribute.WHILE_WAIT) + ","
					+ Attribute.getAllSynchronizedAttributes() + ","
					+ Attribute.getAllPatternAttributes() + ","
					+ Attribute.getAllMethodCallAttributes() + ","
					+ Attribute.getAllNestednessAttributes(),
				Integer.MAX_VALUE, new ParallelFilter(3, false));
		
		*/
		/*
		export(Scope.METHOD, ExportType.ARFF, database, Attribute.combine(
				MethodAttribute.PUBLIC_METHODS,
				MethodAttribute.PRIVATE_METHODS), 100);
		*/
		export(Scope.METHOD, ExportType.ARFF, database, "*", Integer.MAX_VALUE, new ParallelFilter(1, false));
		export(Scope.CLASS, ExportType.ARFF, database, "*", Integer.MAX_VALUE, new ParallelFilter(1, false));
		export(Scope.PROJECT, ExportType.ARFF, database, "*", Integer.MAX_VALUE, new ParallelFilter(1, false));
		/*
		export(Scope.PROJECT, ExportType.ARFF, database, Attribute.combine(
				ProjectAttribute.PROJECT_NAME,
				ProjectAttribute.NOTIFY_CALLS,
				ProjectAttribute.NOTIFYALL_CALLS), Integer.MAX_VALUE);
		*/
		database.shutdown();
		System.out.println("Done!");
	}
	
	private Scope scope;
	private enum ExportType { ARFF, CSV };
	private ExportType exportType;
	private ExportFilter exportFilter;
	
	public Exporter(Scope scope, ExportType exportType, ExportFilter exportFilter) {
		this.scope = scope;
		this.exportType = exportType;
		this.exportFilter = exportFilter;
	}

	public Exporter(Scope scope, ExportType exportType) {
		this(scope, exportType, new ExportFilter());
	}
	
	static public void export(Scope scope, ExportType exportType, Database database, String attributes, int maxRowCount, ExportFilter exportFilter) {
		Exporter exporter = new Exporter(scope, exportType, exportFilter);
		exporter.export(database, attributes, maxRowCount);
	}

	static public void export(Scope scope, ExportType exportType, Database database, String attributes, int maxRowCount) {
		export(scope, exportType, database, attributes, maxRowCount, new ExportFilter());
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
        logger.writeToFile(getFilePath(), getFileName());		
	}

	private String getFilePath() {
		switch (exportType) {
			case ARFF: return PathAndFileNames.WEKA_DATA_PATH;
			case CSV: return PathAndFileNames.CSV_DATA_PATH;
		}
		return null;
	}

	private String getFileName() {
    	switch(exportType) {
	    	case ARFF: return scope.toString() + ".arff";
	    	case CSV: return scope.toString() + ".csv";
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
