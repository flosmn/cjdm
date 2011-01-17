package dstest;

/**
 * Created by IntelliJ IDEA.
 * User: Koushik Sen (ksen@cs.uiuc.edu)
 * Date: Dec 21, 2005
 * Time: 9:14:19 AM
 */

class A {

}

public class ITest {
    public static void main(String[] args) {
        Object x = (args==null) ? new A() : new Object();
    }

}
