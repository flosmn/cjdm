package clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Combo;

import plugin.view.views.OpenPdfAfterCreation;
import utils.FileLineCounter;
import utils.Logger;

public class DendogramCreator {
	private final String csvSourceFile;
	private final String pdfTargetPath;
	private final String pdfName;
	private final OpenPdfAfterCreation openPdf;
	private final Combo methodCombo;
	private final Combo metricCombo;
	private String method;
	private String metric;
	private int nrows;
	private int width;
	private int height;
	private String pathToScript;

	public DendogramCreator(String csvSourceFile, 
							String pdfTargetPath, 
							String pdfName, OpenPdfAfterCreation openPdf, Combo methodCombo, Combo metricCombo){
		this.csvSourceFile = csvSourceFile;
		this.pdfTargetPath = pdfTargetPath;
		this.pdfName = pdfName;
		this.openPdf = openPdf;
		this.methodCombo = methodCombo;
		this.metricCombo = metricCombo;
	}

	public void createClustering(){
		createDirs();
		createAttributes();
		writeScript(csvSourceFile, pdfTargetPath, pdfName, this.method, this.metric, this.width, this.height);
		executeScript(this.pathToScript);
		removeScript(this.pathToScript);
		openFile();
	}

	private void createAttributes() {
		this.method = methodCombo.getText();
		this.metric = metricCombo.getText();
		
		this.pathToScript = pdfTargetPath + "script.r";
		this.nrows = getNumberOfRows(csvSourceFile);
		this.width = (int)(0.047*(4*nrows+1000));
		this.height = 35 + (int)(nrows/10.0);
	}

	private void createDirs() {
		File dir = new File(this.pdfTargetPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
	}

	private void openFile() {
		Program p = Program.findProgram (".pdf");
		if (p != null && openPdf.isOpenPdfAfterCreation()) {
			p.execute (pdfTargetPath + pdfName);
		}
	}
	
	private void removeScript(String pathToScript) {
		File f = new File(pathToScript);
		if(f.exists()){
			f.delete();
		}
	}

	private void executeScript(String pathToScript) {
		try {
			if(isWindows() || isMac()){
				pathToScript = "\"" + pathToScript + "\"";
			}
			
			Runtime rt = Runtime.getRuntime();
            String[] commands = new String[]{
            		"R",
            		"-f",
            		"" + pathToScript + ""
            };
            Process proc = rt.exec(commands);
            
            // any error message?
            StreamGobbler errorGobbler = new 
                StreamGobbler(proc.getErrorStream(), "ERROR");            
            
            // any output?
            StreamGobbler outputGobbler = new 
                StreamGobbler(proc.getInputStream(), "OUTPUT");
                
            errorGobbler.start();
            outputGobbler.start();
                                    
            int exitVal = proc.waitFor();
            System.out.println("script execution finished. exit value: " + exitVal);  
		} catch (IOException e) {
			System.err.println("script could not be executed");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.err.println("script could not be executed");
			e.printStackTrace();
		}
	}

	private void writeScript(String csvSourceFile, String pdfTargetPath,
			String pdfName, String method, String metric, int width, int height) {
		Logger log = new Logger();
		logFunction(log, "dendromat.r");
		logFunction(log, "raster.r");
		log.logAndStartNewLine("palette(gray((255:0)/255))");
		log.logAndStartNewLine("targetFile <- \""+pdfTargetPath + pdfName +"\"");
		log.logAndStartNewLine( "sourceFile <- \""+csvSourceFile+"\"");
		log.logAndStartNewLine("data <- read.table(sourceFile,header=TRUE,sep=\",\")");
		log.logAndStartNewLine("rownames(data)<-data[,1]");
		log.logAndStartNewLine("data <- data[,-1]");
		log.logAndStartNewLine("centerVec <- (apply(data,2,max)+apply(data,2,min))/2");
		log.logAndStartNewLine("scaleVec <- (apply(data,2,max)-apply(data,2,min))/2");
		log.logAndStartNewLine("data <- scale(data, centerVec, scaleVec)");
		log.logAndStartNewLine("colMat <- ((data + 1)/2)*255");
		log.logAndStartNewLine("d<-dist(data, method=\""+metric+"\")");
		log.logAndStartNewLine("dend <- hclust(d,method=\""+method+"\")");
		log.logAndStartNewLine("pdf(file=targetFile, height="+ height +", width="+ width +")");
		log.logAndStartNewLine("par(mar=par()$mar+c(10,20,0,0))");
		log.logAndStartNewLine("dendromat(dend, colMat, main = 'Clustering Result', ylab=\"Distance\")");
		log.logAndStartNewLine("dev.off()");
		log.writeToFile(pdfTargetPath, "script.r");
	}

	private void logFunction(Logger log, String function) {
		String path = "resources/" + function;
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bufferedReader = new BufferedReader(isr);
			String line = null;
			while((line = bufferedReader.readLine()) != null){
				log.logAndStartNewLine(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int getNumberOfRows(String csvSourceFile) {
		int nrows = 0;
		try {
			nrows = (new FileLineCounter()).getNumberOfLines(csvSourceFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nrows;
	}
	
	private boolean isWindows(){
		String os = System.getProperty("os.name").toLowerCase();
	    return (os.indexOf( "win" ) >= 0); 
	}
 
	private boolean isMac(){
		String os = System.getProperty("os.name").toLowerCase();
	    return (os.indexOf( "mac" ) >= 0); 
	}
 
	private boolean isUnix(){
		String os = System.getProperty("os.name").toLowerCase();
	    return (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0);
	}
}

class StreamGobbler extends Thread {
	InputStream is;
	String type;

	StreamGobbler(InputStream is, String type) {
		this.is = is;
		this.type = type;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(type + ">" + line);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
