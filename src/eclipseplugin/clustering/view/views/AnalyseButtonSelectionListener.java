package eclipseplugin.clustering.view.views;

import java.io.File;

import main.ProjectParser;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;

import utils.PathAndFileNames;
import database.Database;
import export.Exporter;

public class AnalyseButtonSelectionListener implements SelectionListener {

	private final Text rootDir;
	private final Text targetDir;
	private final Text className;
	private final Text methodName;
	private final Text filterLevelClass;
	private final Text filterLevelMethod;

	public AnalyseButtonSelectionListener(Text rootDir, Text targetDir,
			Text className, Text methodName, Text filterLevelClass, Text filterLevelMethod) {
				this.rootDir = rootDir;
				this.targetDir = targetDir;
				this.className = className;
				this.methodName = methodName;
				this.filterLevelClass = filterLevelClass;
				this.filterLevelMethod = filterLevelMethod;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		System.out.println("Analyse");
		String rootDirPath = "C:/root/";
		String targetDirPath = "C:/root/csv/";
		String classCsvName = "classbla.csv";
		String methodCsvName = "methodbla.csv";
		int filterLevelClass = 0;
		int filterLevelMethod = 0;
		
		PathAndFileNames.setProjectSourcesPath(rootDirPath);
		PathAndFileNames.setCsvDataPath(targetDirPath);
		PathAndFileNames.setDataBasePath(targetDirPath + "temp/cjdm");
		PathAndFileNames.setClassCsvName(classCsvName);
		PathAndFileNames.setMethodCsvName(methodCsvName);
		
		parseProjects();
		exportToCsv(filterLevelClass, filterLevelMethod);
		cleanUp(targetDirPath);
	}

	private void cleanUp(String targetDirPath) {
		File file = new File(targetDirPath + "temp");
		deleteDirectory(file);
	}
	
	private void deleteDirectory(File path) {
	    if( path.exists() ) {
	      File[] files = path.listFiles();
	      for(int i=0; i<files.length; i++) {
	         if(files[i].isDirectory()) {
	           deleteDirectory(files[i]);
	         }
	         else {
	           files[i].delete();
	         }
	      }
	    }
	    path.delete();
	  }	

	private void exportToCsv(int filterLevelClass, int filterLevelMethod) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		Exporter.exportClassCsv(filterLevelClass, database);
		Exporter.exportMethodCsv(filterLevelMethod, database);
		database.shutdown();
	}

	private void parseProjects() {
		ProjectParser projectParser = new ProjectParser();
		projectParser.parseProjects();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
