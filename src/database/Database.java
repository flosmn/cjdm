package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import utils.Logger;
import utils.PathAndFileNames;

public class Database {

    Connection connection;

    public Database(String fileName) {
    	try {
	        Class.forName("org.hsqldb.jdbcDriver");
	        connection = DriverManager.getConnection("jdbc:hsqldb:" + fileName, "sa", "");
    	} catch (Exception exception) {
    		exception.printStackTrace();
    	}
    }

    public void shutdown() {
    	try {
	        Statement statement = connection.createStatement();
	        statement.execute("SHUTDOWN");
	        connection.close();
    	} catch (Exception exception) {
    		exception.printStackTrace();
    	}
    }
    
    public synchronized void exportArff(String expression, String relationName) {
    	try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(expression); 
	        export(resultSet, relationName);
	        statement.close();
    	} catch (Exception exception) {
    		exception.printStackTrace();
    	}
    }
    
    private synchronized void export(ResultSet resultSet, String relationName) {
       	try {
	        ResultSetMetaData metaData = resultSet.getMetaData();

	        Logger logger = new Logger();
			logger.logAndStartNewLine("@relation " + relationName);

	        int columnCount = metaData.getColumnCount();
			for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
				logger.logAndStartNewLine("@attribute \"" + metaData.getColumnName(columnIndex + 1) + "\" integer");
			}
			
			logger.logAndStartNewLine("@data");

	        for (; resultSet.next(); ) {
	            for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
	                String value = resultSet.getString(columnIndex + 1);
					if (columnIndex != columnCount - 1) {
						logger.log(value + ", ");
					} else {
						logger.logAndStartNewLine(value);
					}
	            }
	        }

	        logger.writeToFile(PathAndFileNames.WEKA_TEST_DATA_PATH, relationName + ".arff");
	        
    	} catch (Exception exception) {
    		exception.printStackTrace();
    	}
    }
    
    public synchronized void query(String expression) {
    	try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(expression); 
	        dump(resultSet);
	        statement.close();
    	} catch (Exception exception) {
    		exception.printStackTrace();
    	}
    }

    public synchronized void update(String expression) throws Exception{
        Statement statement = connection.createStatement();
        
        //System.out.println(expression);
        
        if (statement.executeUpdate(expression) == -1) {
            System.out.println("db error : " + expression);
        }

        statement.close();
    }

    public static void dump(ResultSet resultSet) {
    	try {
	        ResultSetMetaData metaData = resultSet.getMetaData();
	        int columnCount = metaData.getColumnCount();
	        
	        for (int i = 0; i < columnCount; ++i) {
	        	System.out.print(metaData.getColumnName(i + 1) + " ");
	        }
	        System.out.println();
	        
	        for (; resultSet.next(); ) {
	            for (int i = 0; i < columnCount; ++i) {
	                Object o = resultSet.getObject(i + 1);
	                System.out.print(o.toString() + " ");
	            }
	            System.out.println();
	        }
    	} catch (Exception exception) {
    		exception.printStackTrace();
    	}
    }
}