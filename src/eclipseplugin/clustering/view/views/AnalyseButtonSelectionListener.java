package eclipseplugin.clustering.view.views;

import main.ProjectParser;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;

import utils.PathAndFileNames;

public class AnalyseButtonSelectionListener implements SelectionListener {

	private final Text rootDir;
	private final Text databaseTargetDir;

	public AnalyseButtonSelectionListener(Text rootDir, Text databaseTargetDir) {
				this.rootDir = rootDir;
				this.databaseTargetDir = databaseTargetDir;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		System.out.println("Analyse");
		String rootDirPath = "C:/root/projects";
		String databaseTargetDirPath = "C:/root/database/" + "cjdm";
		
		PathAndFileNames.setProjectSourcesPath(rootDirPath);
		PathAndFileNames.setDataBasePath(databaseTargetDirPath);
		
		parseProjects();
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
