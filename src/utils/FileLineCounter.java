/**
 * @author Christian Wellenbrock
 * @author Florian Simon
 * @author JŸrgen Walter
 * @author Stefan Kober
 * Teams 09, 10
 *
 * This code has been developed during the winter term 2010-2011 at the
 * Karlsruhe Institute of Technology (KIT), Germany.
 * It is part of a project assignment in the course
 * "Multicore Programming in Practice: Tools, Models, and Languages".
 * Project director/instructor:
 * Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileLineCounter {

	public int getNumberOfLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    byte[] c = new byte[1024];
	    int count = 0;
	    int readChars = 0;
	    while ((readChars = is.read(c)) != -1) {
	        for (int i = 0; i < readChars; ++i) {
	            if (c[i] == '\n')
	                ++count;
	        }
	    }
	    return count;
	}
}
