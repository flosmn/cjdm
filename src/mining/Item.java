package mining;

import attributes.Attribute;


/**
 * pair of String and int
 * 
 */
public class Item {
	private String name;
	private String value;
	
	public String toString(){
		return name +"="+value;
	}
	/**
	 * constructor
	 * @param name
	 * @param value
	 */
	public Item(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public Item(Attribute attribute, String value) {
		this(attribute.getName(), value);
	}

	/**
	 * simple setter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * simple getter
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * simple setter
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * simple getter
	 * @return
	 */
	public String getValue() {
		return value;
	}
}
