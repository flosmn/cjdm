package eclipseplugin.clustering.view.views;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;

import utils.PathAndFileNames;
import database.Database;
import export.Exporter;

public class ExportButtonSelectionListener implements SelectionListener {

	private final Text databaseSourceDir;
	private final Text className;
	private final Text methodName;
	private final Text filterLevelClass;
	private final Text filterLevelMethod;
	private final Text csvTargetDir;

	public ExportButtonSelectionListener(Text databaseSourceDir, Text csvTargetDir, Text className,
			Text methodName, Text filterLevelClass, Text filterLevelMethod) {
				this.databaseSourceDir = databaseSourceDir;
				this.csvTargetDir = csvTargetDir;
				this.className = className;
				this.methodName = methodName;
				this.filterLevelClass = filterLevelClass;
				this.filterLevelMethod = filterLevelMethod;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		String databaseSourceDirPath = databaseSourceDir.getText() + "/cjdm";
		String csvTargetDirPath = csvTargetDir.getText() + "/";
		String classCsvName = className.getText();
		String methodCsvName = methodName.getText();
		int filterLevelClass = Integer.parseInt(this.filterLevelClass.getText());
		int filterLevelMethod = Integer.parseInt(this.filterLevelMethod.getText());
		
		if(!classCsvName.endsWith(".csv")) {
			classCsvName += ".csv";
		}
		
		if(!methodCsvName.endsWith(".csv")) {
			methodCsvName += ".csv";
		}
		
		if(filterLevelClass < 0){
			filterLevelClass = 0;
		}
		
		if(filterLevelMethod < 0){
			filterLevelMethod = 0;
		}
		
		PathAndFileNames.setDataBasePath(databaseSourceDirPath);
		PathAndFileNames.setCsvDataPath(csvTargetDirPath);
		PathAndFileNames.setClassCsvName(classCsvName);
		PathAndFileNames.setMethodCsvName(methodCsvName);
		exportToCsv(filterLevelClass, filterLevelMethod);
	}
	
	private void exportToCsv(int filterLevelClass, int filterLevelMethod) {
		Database database = new Database(PathAndFileNames.DATA_BASE_PATH);
		Exporter.exportClassCsv(filterLevelClass, database);
		Exporter.exportMethodCsv(filterLevelMethod, database);
		database.shutdown();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
