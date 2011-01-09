package r;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

public class RBridge {

	public static void main(String[] args) {
	    Rengine re=new Rengine(args, false, null);
	    REXP x;
	    x = re.eval("read.table(\"javaprojectsources/csv/project.csv\",header=TRUE,sep=\",\")", false);
	    re.assign("data", x);
	    
	    x = re.eval("data[,1]", false);
	    re.assign("rownames(data)", x);

	    x = re.eval("data[,-1]", false);
	    re.assign("data", x);

	    x = re.eval("scale(data)", false);
	    re.assign("data", x);
	    
	    x = re.eval("dist(data, method=\"euclidean\")", false);
	    re.assign("d", x);
	    
	    x = re.eval("hclust(d, method=\"ward\")", false);
	    re.assign("fit", x);
	    
	    re.eval("pdf(file=\"javaprojectsources/r/project.pdf\", height=25, width=75)", false);
	    re.eval("plot(fit)", false);
	    re.eval("dev.off()", false);
	    
	    x = re.eval("fit");
	    System.out.println(x);
	    
	    re.end();
	}
}