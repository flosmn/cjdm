package export;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import attributes.Attribute;

public class ParallelFilter extends ExportFilter {
	int minParallel;
	
	public ParallelFilter(int minParallel) {
		this.minParallel = minParallel;
	}
	
	public String filter(ResultSet resultSet) throws SQLException {
		StringBuffer stringBuffer = new StringBuffer();
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		int parallelCounter = 0;
		
		int columnCount = resultSet.getMetaData().getColumnCount();
        for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
    		String value = resultSet.getString(columnIndex + 1);
            stringBuffer.append(value);
            
			boolean isLastColumn = (columnIndex == columnCount - 1);
			stringBuffer.append(isLastColumn ? "\n" : ", ");
			
			
    		if (metaData.getColumnType(columnIndex + 1) != java.sql.Types.INTEGER) continue;

    		String attributeName = metaData.getColumnName(columnIndex + 1);
        	if (!Attribute.getAttribute(attributeName).isParallel()) continue;
	        		
        	Integer intValue = resultSet.getInt(columnIndex + 1);
        	if (intValue == 0) continue;
        	
        	++parallelCounter;
        }
        
        return (parallelCounter > minParallel) ? stringBuffer.toString() : null;
	}
}
