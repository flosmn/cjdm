package weka.util;

import java.util.ArrayList;
import java.util.List;

import weka.core.Instances;

/**
 * Simple tool to set weights for attribute-groups. For example setting
 * the sum of weights for all attributes ending with "objects" to 1
 * 
 * @author Stefan Kober
 *
 */
public class AttributeNormalizer
{
	
	private Instances data;
	private List<String> attributes = new ArrayList<String>();
	
	public AttributeNormalizer(Instances data)
	{
		this.data = data;
	}
	
	/**
	 * Adds an attribute-type to Normalize. For Example add "objects" to set the sum
	 * of weights for all attributes ending with "_OBJECTS" to 1
	 * 
	 * @param att
	 */
	public void addAttribute(String att)
	{
		if (!this.attributes.contains(att.toUpperCase()))
			this.attributes.add(att.toUpperCase());
	}
	
	/**
	 * Normalizes all attribute-types set with .addAttribute(String)
	 */
	public void work()
	{
		for (String att : this.attributes)
		{
			int numOfAttributes = 0;
			// counting
			for (int i = 0; i < data.numAttributes(); i++)
			{
				if (data.attribute(i).name().endsWith("_"+att))
					numOfAttributes++;
			}
			// modifying attribute weight
			double weight = 1.0 / numOfAttributes; // 1.0 instead 1 for float-division
			for (int i = 0; i < data.numAttributes(); i++)
			{
				if (data.attribute(i).name().endsWith("_"+att))
					data.attribute(i).setWeight(weight);
			}
		}
	}
	
	
	
}
