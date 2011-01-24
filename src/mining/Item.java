/**
�* @author Christian Wellenbrock
�* @author J�rgen Walter
�* Team 10
�*
�* This code has been developed during the winter term 2010-2011 at the
�* Karlsruhe Institute of Technology (KIT), Germany.
�* It is part of a project assignment in the course
�* "Multicore Programming in Practice: Tools, Models, and Languages".
�* Project director/instructor:
�* Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package mining;

import attributes.Attribute;


/**
 * This class stores a pair of item name and value
 * 
 */
public class Item {
	private String name;
	private String value;
	
	public String toString(){
		return name +"="+value;
	}
	/**
	 * Constructor
	 * @param name
	 * @param value
	 */
	public Item(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Convenient constructor that transforms {@code Attribute} to a {@code String}
	 * @param attribute
	 * @param value
	 */
	public Item(Attribute attribute, String value) {
		this(attribute.getName(), value);
	}

	/**
	 * sets name of item
	 * @param name of item
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gets name of item
	 * @return the name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets value of item
	 * @param value of item
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * gets value of item
	 * @return value of item
	 */
	public String getValue() {
		return value;
	}
}
