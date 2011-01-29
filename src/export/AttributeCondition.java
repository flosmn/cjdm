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
package export;

import attributes.Attribute;

/**
 * This class is used in combination with the AttributeFilter. It manages a condition consisting attribute name
 * and optional minimal and maximal values.
 */
public class AttributeCondition {
	Attribute attribute;
	int minValue = 1;
	int maxValue = Integer.MAX_VALUE;
	
	public AttributeCondition(Attribute attribute, int minValue, int maxValue) {
		this.attribute = attribute;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	public AttributeCondition(Attribute attribute, int minValue) {
		this(attribute, minValue, Integer.MAX_VALUE);
	}
	
	public AttributeCondition(Attribute attribute) {
		this(attribute, 1);
	}
	
	public boolean matches(String attributeName, int value) {
		return attributeName.equals(attribute.getName()) && value >= minValue && value <= maxValue;
	}
}
