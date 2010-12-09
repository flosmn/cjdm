package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

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
    

	public synchronized void query(String expression) {
    	try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(expression);
	        dump(resultSet);
	        statement.close();
    	} catch (Exception exception) {
    		System.err.println(exception.getMessage());
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

	public int maxID(Scope scope) {
		int ID = 0;
	   	try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT MAX (ID) FROM " + scope.toString());
	        resultSet.next();
	        ID = resultSet.getInt(1);
	        statement.close();
    	} catch (Exception exception) {
    		System.err.println(exception.getMessage());
    		exception.printStackTrace();
    	}
		return ID;
	}

	public boolean hasRow(Scope scope, String name) {
		boolean hasRow = false;
	   	try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + scope.toString() + " WHERE name = '" + name + "'");
	        hasRow = resultSet.next();
	        statement.close();
    	} catch (Exception exception) {
    		System.err.println(exception.getMessage());
    		exception.printStackTrace();
    	}
		return hasRow;
	}

	public void requestResultSet(String query, ResultSetReceiver receiver) {
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery(query);
    		receiver.receive(resultSet);
    		statement.close();
    	} catch (Exception exception) {
    		System.err.println(exception.getMessage());
    		exception.printStackTrace();
    	}
    }
}