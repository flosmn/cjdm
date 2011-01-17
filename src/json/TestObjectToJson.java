package json;

import java.util.Collection;
import java.util.LinkedList;

import com.google.gson.Gson;

public class TestObjectToJson {
  private int data1 = 100;
  private String data2 = "hello";
  private String[] strArr = {"hello", "world"};
  private static Collection<String> strCollection;
 
  public static void main(String[] args) {
	  TestObjectToJson obj = new TestObjectToJson();
	  Gson gson = new Gson();
	  strCollection = new LinkedList<String>();
	  strCollection.add("hallo");
	  strCollection.add("welt");
 
	  //convert java object to JSON format
	  String json = gson.toJson(obj);
 
	  System.out.println(json);
  }
 
}