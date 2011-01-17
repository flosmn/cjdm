package json;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import utils.PathAndFileNames;

import com.google.gson.Gson;

class TestJsonFromObject {
	private int data1;
	private String data2;
 
	public static void main(String[] args) throws IOException {
	  //json data
	  String json = null;
	  //json = "{'data1':120,'data2':'hello'}";
	  json = readFileAsString(PathAndFileNames.PROJECT_SOURCES_PATH+"script");
	  //json = readFileAsString("javaprojectsources/script");
	  	  
	  Gson gson = new Gson();
 
	  //convert JSON into java object
	  ScriptType obj = gson.fromJson(json, ScriptType.class);
	  System.out.println(obj);
	}
 
	@Override
	public String toString() {
		return "TestJsonFromObject [data1=" + data1 + ", data2=" + data2 + "]";
	}
	private static String readFileAsString(String filePath) throws java.io.IOException{
	    byte[] buffer = new byte[(int) new File(filePath).length()];
	    BufferedInputStream f = null;
	    try {
	        f = new BufferedInputStream(new FileInputStream(filePath));
	        f.read(buffer);
	    } finally {
	        if (f != null) try { f.close(); } catch (IOException ignored) { }
	    }
	    return new String(buffer);
	}
 
}