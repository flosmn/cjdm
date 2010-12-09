package export;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExportFilter {
	public String filter(ResultSet resultSet) throws SQLException {
		StringBuffer stringBuffer = new StringBuffer();
		
		int columnCount = resultSet.getMetaData().getColumnCount();
        for (int columnIndex = 0; columnIndex < columnCount; ++columnIndex) {
    		String value = resultSet.getString(columnIndex + 1);
            stringBuffer.append(value);
            
			boolean isLastColumn = (columnIndex == columnCount - 1);
			stringBuffer.append(isLastColumn ? "\n" : ", ");
        }
        return stringBuffer.toString();
	}
}
