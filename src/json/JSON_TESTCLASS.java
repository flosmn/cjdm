package json;


import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class JSON_TESTCLASS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Gson gson = new Gson();
		int one = gson.fromJson("1", int.class);
		Integer one2 = gson.fromJson("1", Integer.class);
		Long one3 = gson.fromJson("1", Long.class);
		boolean false2 = gson.fromJson("false", Boolean.class);
		String str = gson.fromJson("\"abc\"", String.class);
		String anotherStr = gson.fromJson("[\"abc\"]", String.class);
		GsonBuilder gson2 = new GsonBuilder();
		gson2.create();
		
		Type listType = new TypeToken<List<String>>() {}.getType();
		 List<String> target = new LinkedList<String>();
		 target.add("blah");

		 Gson gson3 = new Gson();
		 String json = gson3.toJson(target, listType);
		 List<String> target2 = gson3.fromJson(json, listType);
		 System.out.println(target2);
		
	}

}
