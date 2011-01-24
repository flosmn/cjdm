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
package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Logger {

	StringBuffer stringBuffer;
	
	public Logger() {
		this.stringBuffer = new StringBuffer();
	}
	
	public void writeToFile(String path, String filename) {
		try {
			File dir = new File(path);
			if(!dir.exists()){
				dir.mkdirs();
			}
			File f = new File(path+filename);
			if(!f.exists()){
				f.createNewFile();
			}
						
			FileWriter fstream = new FileWriter(path+filename);
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write(this.stringBuffer.toString());
		    out.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public void log(String s) {
		this.stringBuffer.append(s);
	}
	
	public void logAndStartNewLine(String line) {
		this.stringBuffer.append(line+"\n");
	}
	
}
