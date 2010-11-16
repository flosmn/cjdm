package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

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
        
        if (statement.executeUpdate(expression) == -1) {
            System.out.println("db error : " + expression);
        }

        statement.close();
    }

    public static void dump(ResultSet resultSet) {
    	try {
	        ResultSetMetaData metaData   = resultSet.getMetaData();
	        int columnCount = metaData.getColumnCount();
	
	        for (; resultSet.next(); ) {
	            for (int i = 0; i < columnCount; ++i) {
	                Object o = resultSet.getObject(i + 1);
	                System.out.print(o.toString() + " ");
	            }
	            System.out.println(" ");
	        }
    	} catch (Exception exception) {
    		exception.printStackTrace();
    	}
    }

    
    public static void main(String[] args) {

        Database db = null;

        db = new Database(PathAndFileNames.DATA_BASE_PATH + "test.odb");

        try {
			db.update(
			    "CREATE TABLE sample_table ( id INTEGER IDENTITY, str_col VARCHAR(256), num_col INTEGER)");
		} catch (Exception e) {
			
		}

		try {
	        db.update(
	            "INSERT INTO sample_table(str_col,num_col) VALUES('Ford', 100)");
	        db.update(
	            "INSERT INTO sample_table(str_col,num_col) VALUES('Toyota', 200)");
	        db.update(
	            "INSERT INTO sample_table(str_col,num_col) VALUES('Honda', 300)");
	        db.update(
	            "INSERT INTO sample_table(str_col,num_col) VALUES('GM', 400)");
	
	        db.query("SELECT * FROM sample_table");
	
	        db.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}