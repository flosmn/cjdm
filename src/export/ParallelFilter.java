package export;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import attributes.Attribute;

public class ParallelFilter extends ExportFilter {
	int minParallel;
	boolean summarized;
	
	public ParallelFilter(boolean summarized, int minParallel) {
		this.minParallel = minParallel;
		this.summarized = summarized;
	}
	
	public String filter(ResultSet resultSet) throws SQLException {
		StringBuffer stringBuffer = new StringBuffer();
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		int parallelCounter = 0;
		
		int columnCount = resultSet.getMetaData().getColumnCount();
        for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
            
			String separator = (columnIndex == columnCount - 1) ? "\n" : ", ";
			
    		if (metaData.getColumnType(columnIndex + 1) == java.sql.Types.VARCHAR) {
        		String value = resultSet.getString(columnIndex + 1);
                stringBuffer.append(value + separator);
    		} else {
    			Integer value = resultSet.getInt(columnIndex + 1);
    			
    			if (summarized) {
    				stringBuffer.append(summarize(value) + separator);
    			} else {
    				stringBuffer.append(value + separator);
    			}

            	if (value == 0) continue;
            	
            	String attributeName = metaData.getColumnName(columnIndex + 1);
            	if (!Attribute.getAttribute(attributeName).isParallel()) continue;
            	
            	++parallelCounter;
    		}
        }
        
        return (parallelCounter >= minParallel) ? stringBuffer.toString() : null;
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
