package main;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * class for some helper methods which can not be associated with just one class
 */
public class DirtyLittleHelper {

	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new LinkedList<T>();
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
}
