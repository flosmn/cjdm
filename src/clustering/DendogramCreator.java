package clustering;

import java.io.File;

import org.eclipse.swt.program.Program;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

import utils.PathAndFileNames;

public class DendogramCreator {
	private final String csvSourceFile;
	private final String pdfTargetPath;
	private final String pdfName;

	public DendogramCreator(String csvSourceFile, 
							String pdfTargetPath, 
							String pdfName){
		this.csvSourceFile = csvSourceFile;
		this.pdfTargetPath = pdfTargetPath;
		this.pdfName = pdfName;
	}

	public void createClustering(){
		File dir = new File(this.pdfTargetPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		invokeRAndCreateClustering(csvSourceFile, pdfTargetPath, pdfName);
		
		Program p = Program.findProgram (".pdf");
		if (p != null) {
			// open pdf
			//p.execute (pdfTargetPath + pdfName);
		}
	}
	
	private void invokeRAndCreateClustering(String csvSourceFile, String pdfTargetPath, String pdfName){
		Rengine re = ClusteringRengine.getInstance().getRengine();
		re.eval( "sourceFile <- \""+csvSourceFile+"\"", false);
		re.eval( "data <- read.table(sourceFile,header=TRUE,sep=\",\")", false);
		REXP r = re.eval("nrow(data)", true);

		int nrows = r.asInt();
		int width = (int)(0.047*(4*nrows+1000));
		int height = 35 + (int)(nrows/10.0);
		
		re.eval( "library(squash)" , false);
		re.eval( "palette(gray((255:0)/255))", false);
		re.eval( "targetFile <- \""+pdfTargetPath + pdfName+"\"", false);
		re.eval( "rownames(data)<-data[,1]", false);
		re.eval( "data <- data[,-1]", false);
		re.eval( "centerVec <- (apply(data,2,max)+apply(data,2,min))/2", false);
		re.eval( "scaleVec <- (apply(data,2,max)-apply(data,2,min))/2", false);
		re.eval( "data <- scale(data, centerVec, scaleVec)", false);
		re.eval( "colMat <- ((data + 1)/2)*255", false);
		re.eval( "d<-dist(data, method=\"euclidean\")", false);
		re.eval( "dend <- hclust(d,method=\"ward\")", false);
		re.eval( "pdf(file=targetFile, height="+height+", width="+width+")", false);
		re.eval( "par(mar=par()$mar+c(10,20,0,0))", false);
		re.eval( "dendromat(dend, colMat, main = 'Clustering Result', ylab=\"Distance\")", false);
		re.eval( "dev.off()", false);
	}

	public static void main(String[] args) {
        Rengine re=new Rengine(args, false, null);
        re.eval("install.packages(\"lib/squash.zip\")");
        re.eval("source(\"" + PathAndFileNames.R_DATA_PATH + "classscript.R\")");
        System.out.println("done");
        System.exit(0);
	}
}
