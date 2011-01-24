/**
Ê* @author Christian Wellenbrock
 * @author Florian Simon
Ê* @author JŸrgen Walter
 * @author Stefan Kober
Ê* Teams 09, 10
Ê*
Ê* This code has been developed during the winter term 2010-2011 at the
Ê* Karlsruhe Institute of Technology (KIT), Germany.
Ê* It is part of a project assignment in the course
Ê* "Multicore Programming in Practice: Tools, Models, and Languages".
Ê* Project director/instructor:
Ê* Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
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
