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
		System.out.println("Export");
		String databaseSourceDirPath = "C:/root/database/" + "cjdm";
		String csvTargetDirPath = "C:/root/csv/";
		String classCsvName = "classbla.csv";
		String methodCsvName = "methodbla.csv";
		int filterLevelClass = 0;
		int filterLevelMethod = 0;
		
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
