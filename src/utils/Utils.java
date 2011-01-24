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
package utils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

/**
 * class for some helper methods which can not be associated with just one class
 */
public class Utils {

	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) { 
        List<T> r = new LinkedList<T>(); 
         
        if(c == null) { return r; } 
         
        for(Object o: c) 
            r.add(clazz.cast(o)); 

        return r; 
	}
	
	public static void printTree(CommonTree tree) {
		printTree(tree, "");
	}
	
	private static void printTree(CommonTree tree, String s){
		System.out.println(s + tree.getText());
		
		List<CommonTree> children = Utils.castList(
				CommonTree.class, tree.getChildren());
		
		for(CommonTree child : children){
			printTree(child, s + "  ");
		}
	}

}
