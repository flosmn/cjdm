package r;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

public class RBridge {

	public static void main(String[] args) {
	    Rengine re=new Rengine(args, false, null);
	    REXP x;
	    x = re.eval("read.table(\"javaprojectsources/csv/class.csv\",header=TRUE,sep=\",\")", false);
	    re.assign("data", x);
	    
	    x = re.eval("data[,1]", false);
	    re.assign("rownames(data)", x);
	    	
	    x = re.eval("data[,1]", true);
	    System.out.println(x);

	    x = re.eval("data[,-1]", false);
	    re.assign("data", x);

	    x = re.eval("data");
	    System.out.println(x);
	    
	    re.end();
	}
}