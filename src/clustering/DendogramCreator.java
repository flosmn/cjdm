package clustering;

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
		Rengine re=new Rengine(new String[]{""}, false, null);
		
		REXP p;
		p = re.eval("5+5");
		System.out.println(p);
		
		
		//re.eval("install.packages(\"lib/squash.zip\")");
		//re.eval("source(\"" + PathAndFileNames.R_DATA_PATH + "classscript2.R\")");
		//re.eval(getClassScript(csvSourceFile, pdfTargetPath, pdfName));
		
		System.out.println("done");
	}
	
	private String getClassScript(String csvSourceFile, String pdfTargetPath, String pdfName){
		String str = "";
		str += "library(squash)\n";
		str += "palette(gray((255:0)/255))\n";
		str += "sourceFile <- \""+csvSourceFile+"\"\n";
		str += "targetFile <- \""+pdfTargetPath + pdfName+"\"\n";
		str += "data <- read.table(sourceFile,header=TRUE,sep=\",\")\n";
		str += "rownames(data)<-data[,1]\n";
		str += "data <- data[,-1]\n";
		str += "centerVec <- (apply(data,2,max)+apply(data,2,min))/2\n";
		str += "scaleVec <- (apply(data,2,max)-apply(data,2,min))/2\n";
		str += "data <- scale(data, centerVec, scaleVec)\n";
		str += "colMat <- ((data + 1)/2)*255\n";
		str += "d<-dist(data, method=\"euclidean\")\n";
		str += "dend <- hclust(d,method=\"ward\")\n";
		str += "pdf(file=targetFile, height=75, width=125)\n";
		str += "par(mar=par()$mar+c(10,20,0,0))\n";
		str += "dendromat(dend, colMat, main = 'Class Clustering')\n";
		str += "dev.off()\n";
		return str;		
	}

	public static void main(String[] args) {
        Rengine re=new Rengine(args, false, null);
        re.eval("install.packages(\"lib/squash.zip\")");
        re.eval("source(\"" + PathAndFileNames.R_DATA_PATH + "classscript.R\")");
        System.out.println("done");
        System.exit(0);
	}
}
