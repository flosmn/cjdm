package attributes;

public class Attributes {
	public static String combine(String ... attributes) {
		String result = "";
		for (String attribute : attributes) {
			if (!result.equals("") && !attribute.equals("")) {
				result += ", ";
			}
			
			result += attribute;
		}
		
		return result;
	}
}
