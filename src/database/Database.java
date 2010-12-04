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
    
    public synchronized void exportArff(String expression, String relationName, boolean summarized) {
    	try {
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(expression); 
	        exportArff(resultSet, relationName, summarized);
	        statement.close();
    	} catch (Exception exception) {
    		System.err.println(exception.getMessage());
    		exception.printStackTrace();
    	}
    }
    
    public synchronized void exportCsv(String expression, String relationName) {
    	try {
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery(expression); 
    		exportCsv(resultSet, relationName);
    		statement.close();
    	} catch (Exception exception) {
    		System.err.println(exception.getMessage());
    		exception.printStackTrace();
    	}
    }
    private synchronized void exportArff(ResultSet resultSet, String relationName, boolean summarized) {
       	try {
	        ResultSetMetaData metaData = resultSet.getMetaData();

	        Logger logger = new Logger();
			logger.logAndStartNewLine("@relation " + relationName);
			
			String typeName = summarized ? "string" : "integer";
	        int columnCount = metaData.getColumnCount();
			for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
				String attributeName = metaData.getColumnName(columnIndex + 1);
				if (!attributeName.endsWith("_NAME")) {
					logger.logAndStartNewLine("@attribute \"" + attributeName + "\" " + typeName);
				}
			}
			
			logger.logAndStartNewLine("@data");
			
			for (;resultSet.next();) {
	            for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
	            	if (metaData.getColumnName(columnIndex + 1).endsWith("_NAME")) {
	            		continue;
	            	}
	            	
	                Integer value = resultSet.getInt(columnIndex + 1);
	                String valueString = summarized ? summarize(value) : value.toString();
					if (columnIndex != columnCount - 1) {
						logger.log(valueString + ", ");
					} else {
						logger.logAndStartNewLine(valueString);
					}
	            }
	        }

	        String fileName = summarized ? relationName + "Summarized.arff" : relationName + ".arff";
	        logger.writeToFile(PathAndFileNames.WEKA_DATA_PATH, fileName);
	        
    	} catch (Exception exception) {
    		exception.printStackTrace();
    	}
    }
    
    private synchronized void exportCsv(ResultSet resultSet, String relationName) {
       	try {
	        ResultSetMetaData metaData = resultSet.getMetaData();

	        Logger logger = new Logger();
			
			int columnCount = metaData.getColumnCount();
			for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
				String attributeName;
				if (columnIndex == 0 && relationName.equals("class")) {
					attributeName = metaData.getColumnName(columnIndex + 1) + "->"
							+ metaData.getColumnName(columnIndex + 2);
					columnIndex += 1;
				} else if (columnIndex == 0 && relationName.equals("method")) {
					attributeName = metaData.getColumnName(columnIndex + 1) + "->"
							+ metaData.getColumnName(columnIndex + 2) + "->"
							+ metaData.getColumnName(columnIndex + 3);
					columnIndex += 2;
				} else {
					attributeName = metaData.getColumnName(columnIndex + 1);
				}
				if (columnIndex != columnCount - 1) {
					logger.log(attributeName + ",");
				} else {
					logger.logAndStartNewLine(attributeName);
				}
			}
			
			for (;resultSet.next();) {
				String s = "[" + String.valueOf((int)(Math.random()*100000)) + "]";
				StringBuffer sb = new StringBuffer("");
				int nrOfNotZero = 0;
				
				for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
	            	String valueString;
	            	if(columnIndex == 0 && relationName.equals("class")){
	            		valueString = resultSet.getString(columnIndex + 1) + "->" 
	            					+ resultSet.getString(columnIndex + 2) + s;
	            		columnIndex += 1;
	                }
	            	else if(columnIndex == 0 && relationName.equals("method")){
	            		valueString = resultSet.getString(columnIndex + 1) + "->" 
	            					+ resultSet.getString(columnIndex + 2) + "->" 
	            					+ resultSet.getString(columnIndex + 3) + s;
	            		columnIndex += 2;
	                }
	            	else{
	            		valueString = resultSet.getString(columnIndex + 1);
	            		if(!valueString.equals("0")){
	            			nrOfNotZero++;
	            		}
	            	}
					if (columnIndex != columnCount - 1) {
						sb.append(valueString + ",");
					} else {
						sb.append(valueString);
					}
				}
				
				if(relationName.equals("class")){
					if(nrOfNotZero > 11) {
						logger.logAndStartNewLine(sb.toString());
					}
                }
            	else if(relationName.equals("method")){
            		if(nrOfNotZero > 6) {
						logger.logAndStartNewLine(sb.toString());
					}
                }
            	else{
            		logger.logAndStartNewLine(sb.toString());
            	}
	        }

	        String fileName = relationName + ".csv";
	        logger.writeToFile(PathAndFileNames.R_DATA_PATH, fileName);
	        
    	} catch (Exception exception) {
    		exception.printStackTrace();
    	}
    }
    
    static private String summarize(Integer value) {
    	if (value <=     0) return           "0";
    	if (value <=     1) return           "1";
    	if (value <=     4) return        "2..4";
    	if (value <=    20) return       "5..20";
    	if (value <=   100) return     "21..100";
    	if (value <=   500) return    "101..500";
    	if (value <=  3000) return   "501..3000";
    	if (value <= 20000) return "3001..20000";
    	
    	return "20001..";
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
}