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
