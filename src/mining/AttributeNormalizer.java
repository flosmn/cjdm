/**
Ê* @author Christian Wellenbrock
Ê* @author JŸrgen Walter
Ê* Team 10
Ê*
Ê* This code has been developed during the winter term 2010-2011 at the
Ê* Karlsruhe Institute of Technology (KIT), Germany.
Ê* It is part of a project assignment in the course
Ê* "Multicore Programming in Practice: Tools, Models, and Languages".
Ê* Project director/instructor:
Ê* Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package mining;

import java.util.HashMap;
import java.util.Map;

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
	private Map<String,Double> attributes = new HashMap<String,Double>();
	
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
	public void addAttribute(String att,double i)
	{
		if (!this.attributes.containsKey(att.toUpperCase()))
			this.attributes.put(att.toUpperCase(),i);
	}
	
	/**
	 * Normalizes all attribute-types set with .addAttribute(String)
	 */
	public void work()
	{
		synchronized(this.attributes)
		{
			for (String att : this.attributes.keySet())
			{
				int numOfAttributes = 0;
				// counting
				for (int i = 0; i < data.numAttributes(); i++)
				{
					if (data.attribute(i).name().endsWith("_"+att))
					{
						numOfAttributes++;
					}
				}
				// modifying attribute weight
				System.out.println(this.attributes.get(att));
				double weight = this.attributes.get(att) / numOfAttributes; // 1.0 instead 1 for float-division
				for (int i = 0; i < data.numAttributes(); i++)
				{
					if (data.attribute(i).name().endsWith("_"+att))
					{
						data.attribute(i).setWeight(weight);
						System.out.println("Setting weight to "+weight+" for "+data.attribute(i).name());
					}
				}
			}
		}
	}
	
	
	
}
