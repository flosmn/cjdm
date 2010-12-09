package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import utils.Logger;
import utils.PathAndFileNames;
import attributes.Attribute;
import attributes.MethodAttribute;

public class Exporter implements ResultSetReceiver {
	private Scope scope;
	private enum ExportType { ARFF, CSV };
	private ExportType exportType;
	
	public Exporter(Scope scope, ExportType exportType) {
		this.scope = scope;
		this.exportType = exportType;
	}
	
	public static void main (String[] args) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		
		export(Scope.METHOD, ExportType.CSV, database, Attribute.combine(
				MethodAttribute.COMBINED_METHOD_NAME,
				MethodAttribute.PUBLIC_METHODS,
				MethodAttribute.PRIVATE_METHODS), 100);
		
		export(Scope.METHOD, ExportType.ARFF, database, Attribute.combine(
				MethodAttribute.PUBLIC_METHODS,
				MethodAttribute.PRIVATE_METHODS), 100);
		
		database.shutdown();
		System.out.println("Done!");
	}
	
	static private void export(Scope scope, ExportType exportType, Database database, String attributes, int maxRowCount) {
		Exporter exporter = new Exporter(scope, exportType);
		exporter.export(database, attributes, maxRowCount);
	}

	public void export(Database database, String attributes, int maxRowCount) {
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
			case CSV: return PathAndFileNames.R_DATA_PATH;
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
		
		int columnCount = resultSet.getMetaData().getColumnCount();
		for (;resultSet.next();) {
			/* future use
			String line = filter.filter(resultSet);
			
			if (line != null) {
				stringBuffer.append(line);
			}
			*/
			
			// TODO: remove to filter.filter, return null to dismiss
            for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
        		String value = resultSet.getString(columnIndex + 1);
                String valueString = value.toString();
                
                stringBuffer.append(valueString);
                
				boolean isLastColumn = (columnIndex == columnCount - 1);
				stringBuffer.append(isLastColumn ? "\n" : ", ");
            }
        }
		
		return stringBuffer.toString();
    }
}
