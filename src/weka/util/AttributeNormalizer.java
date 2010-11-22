package weka.util;

import java.util.ArrayList;
import java.util.List;

import weka.core.Instances;

public class AttributeNormalizer
{
	
	private Instances data;
	private List<String> attributes = new ArrayList<String>();
	
	public AttributeNormalizer(Instances data)
	{
		this.data = data;
	}
	
	public void addAttribute(String att)
	{
		if (!this.attributes.contains(att.toUpperCase()))
			this.attributes.add(att.toUpperCase());
	}
	
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
