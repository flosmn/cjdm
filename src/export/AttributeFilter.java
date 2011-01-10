package export;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class AttributeFilter extends ExportFilter {
	AttributeCondition[] conditions;
	boolean summarized;
	
	public AttributeFilter(boolean summarized, AttributeCondition ... conditions) {
		this.summarized = summarized;
		this.conditions = conditions;
	}
	
	public String filter(ResultSet resultSet) throws SQLException {
		StringBuffer stringBuffer = new StringBuffer();
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		int matches = 0;
		
		int columnCount = resultSet.getMetaData().getColumnCount();
        for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
            
			String separator = (columnIndex == columnCount - 1) ? "\n" : ", ";
			
    		if (metaData.getColumnType(columnIndex + 1) == java.sql.Types.VARCHAR) {
        		String value = resultSet.getString(columnIndex + 1);
                stringBuffer.append(value + separator);
    		} else {
    			Integer value = resultSet.getInt(columnIndex + 1);
            	String attributeName = metaData.getColumnName(columnIndex + 1);
            	
            	for (AttributeCondition condition : conditions) {
            		if (condition.matches(attributeName, value)) {
            			++matches;
            		}
            	}
    			
    			if (summarized) {
    				stringBuffer.append(summarize(value) + separator);
    			} else {
    				stringBuffer.append(value + separator);
    			}
    		}
        }
        
        return (matches == conditions.length) ? stringBuffer.toString() : null;
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