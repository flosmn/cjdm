package main;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Logger {

	StringBuffer stringBuffer;
	
	public Logger(){
		this.stringBuffer = new StringBuffer();
	}
	
	public void writeToFile(String path, String filename){
		try{
			FileWriter fstream = new FileWriter(path+filename);
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write(this.stringBuffer.toString());
		    out.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public void log(String s){
		this.stringBuffer.append(s);
	}
	
	public void logAndStartNewLine(String line){
		this.stringBuffer.append(line+"\n");
	}
	
}
