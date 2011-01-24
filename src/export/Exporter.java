/**
 * @author Christian Wellenbrock
 * @author Florian Simon
 * @author JŸrgen Walter
 * @author Stefan Kober
 * Teams 09, 10
 *
 * This code has been developed during the winter term 2010-2011 at the
 * Karlsruhe Institute of Technology (KIT), Germany.
 * It is part of a project assignment in the course
 * "Multicore Programming in Practice: Tools, Models, and Languages".
 * Project director/instructor:
 * Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package export;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import utils.Logger;
import utils.PathAndFileNames;
import attributes.Attribute;
import attributes.ClassAttribute;
import attributes.MethodAttribute;
import database.Database;
import database.ResultSetReceiver;
import database.Scope;

public class Exporter implements ResultSetReceiver {
	public static void main (String[] args) {
	}
	
	public enum ExportType { ARFF, CSV };

	private Scope scope;
	private ExportType exportType;
	private String fileName;
	private ExportFilter exportFilter;
	private boolean debug = true;
	private boolean noPath = false;
	
	public Exporter(Scope scope, ExportType exportType, String fileName, ExportFilter exportFilter, boolean debug, boolean noPath) {
		this.scope = scope;
		this.exportType = exportType;
		this.fileName = fileName;
		this.exportFilter = exportFilter;
		this.debug = debug;
		this.noPath = noPath;
	}
	
	public Exporter(Scope scope, ExportType exportType, String fileName, ExportFilter exportFilter) {
		this(scope, exportType, fileName, exportFilter, false, false);
	}

	public Exporter(Scope scope, ExportType exportType, String fileName) {
		this(scope, exportType, fileName, new ExportFilter());
	}
	
	public static void export(Scope scope, ExportType exportType, String fileName, Database database, String attributes, int maxRowCount, ExportFilter exportFilter, boolean debug, boolean noPath) {
		Exporter exporter = new Exporter(scope, exportType, fileName, exportFilter, debug, noPath);
		exporter.export(database, attributes, maxRowCount);
	}
	public static void export(Scope scope, ExportType exportType, String fileName, Database database, String attributes, int maxRowCount, ExportFilter exportFilter) {
		export(scope, exportType, fileName, database, attributes, maxRowCount, exportFilter, true, false);
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
		if (debug) {
			System.out.println("exporting " + scope.toString() + " to " + exportType);
		}
		String query = "SELECT " + attributes + " FROM " + scope + "_view LIMIT " + maxRowCount;
		database.requestResultSet(query, this);
	}
	
	public static void exportClassCsv(int filterLevel, Database database){
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
				Integer.MAX_VALUE, new ParallelFilter(false, filterLevel));
	}
	
	public static void exportMethodCsv(int filterLevel, Database database){
		export(Scope.METHOD, ExportType.CSV, database, 
				Attribute.combine(
						MethodAttribute.COMBINED_METHOD_NAME,
						MethodAttribute.WHILE_WAIT,
						Attribute.getAllSynchronizedAttributes(),
						Attribute.getAllPatternAttributes(),
						Attribute.getAllMethodCallAttributes(),
						Attribute.getAllNestednessAttributes()),
				Integer.MAX_VALUE, new ParallelFilter(false, filterLevel));
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
		if (noPath ) {
			return "";
		}
		
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
    		case CSV: return getCsvName(scope);
		}
		return null;
	}
	
	private static String getCsvName(Scope scope) {
		String name = "";
		if(scope == Scope.CLASS){
			name = PathAndFileNames.CLASS_CSV_NAME;
		}
		else if(scope == Scope.METHOD){
			name = PathAndFileNames.METHOD_CSV_NAME;
		}
		else{
			name = "project";
		}
		if(!name.endsWith(".csv")){
			name += ".csv";
		}
		return name;
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
		
		if (debug) {
			System.out.println("exported " + matchCounter + " of " + resultCounter + " results");
		}
		
		return stringBuffer.toString();
    }
}
