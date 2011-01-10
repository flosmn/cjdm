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
	
	public AttributeCondition(Attribute attribute) {
		this(attribute, 1, Integer.MAX_VALUE);
	}
	
	public boolean matches(String attributeName, int value) {
		return attributeName.equals(attribute.getName()) && value >= minValue && value <= maxValue;
	}
}
