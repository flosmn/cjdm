/**
Ê* @author Christian Wellenbrock
 * @author Florian Simon
Ê* @author JŸrgen Walter
 * @author Stefan Kober
Ê* Teams 09, 10
Ê*
Ê* This code has been developed during the winter term 2010-2011 at the
Ê* Karlsruhe Institute of Technology (KIT), Germany.
Ê* It is part of a project assignment in the course
Ê* "Multicore Programming in Practice: Tools, Models, and Languages".
Ê* Project director/instructor:
Ê* Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package export;

import attributes.Attribute;

public class AttributeCondition {
	Attribute attribute;
	int minValue;
	int maxValue;
	
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
