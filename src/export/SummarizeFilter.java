package export;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SummarizeFilter extends ExportFilter {

	public String filter(ResultSet resultSet) throws SQLException {
		StringBuffer stringBuffer = new StringBuffer();
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		int columnCount = resultSet.getMetaData().getColumnCount();
        for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
        	
        	if (metaData.getColumnTypeName(columnIndex + 1).equals("DECIMAL")) {
    			Integer value = resultSet.getInt(columnIndex + 1);
    			stringBuffer.append(summarize(value));
    		} else {
	    		String value = resultSet.getString(columnIndex + 1);
	            stringBuffer.append(value);
    		}
            
			boolean isLastColumn = (columnIndex == columnCount - 1);
			stringBuffer.append(isLastColumn ? "\n" : ", ");
        }
        
		return stringBuffer.toString();
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
}
