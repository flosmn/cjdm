/**
�* @author Christian Wellenbrock
 * @author Florian Simon
�* @author J�rgen Walter
 * @author Stefan Kober
�* Teams 09, 10
�*
�* This code has been developed during the winter term 2010-2011 at the
�* Karlsruhe Institute of Technology (KIT), Germany.
�* It is part of a project assignment in the course
�* "Multicore Programming in Practice: Tools, Models, and Languages".
�* Project director/instructor:
�* Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package database;

import java.sql.ResultSet;

public interface ResultSetReceiver {
	public void receive(ResultSet resultSet);
}
