package clustering;

import org.rosuda.JRI.Rengine;

import utils.PathAndFileNames;

public class DendogramCreator {

	public static void main(String[] args) {
        Rengine re=new Rengine(args, false, null);
        re.eval("source(\"" + PathAndFileNames.R_DATA_PATH + "projectscript.R\")");
        re.eval("source(\"" + PathAndFileNames.R_DATA_PATH + "classscript.R\")");
        re.eval("source(\"" + PathAndFileNames.R_DATA_PATH + "methodscript.R\")");
        System.out.println("done");
        System.exit(0);
    }
	
}
