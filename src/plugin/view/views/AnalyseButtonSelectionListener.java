/**
 * @author Florian Simon
 * @author Stefan Kober
 * Team 09
 *
 * This code has been developed during the winter term 2010-2011 at the
 * Karlsruhe Institute of Technology (KIT), Germany.
 * It is part of a project assignment in the course
 * "Multicore Programming in Practice: Tools, Models, and Languages".
 * Project director/instructor:
 * Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
package plugin.view.views;


import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;

import parsing.ProjectParser;
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
		String rootDirPath = rootDir.getText();
		String databaseTargetDirPath = databaseTargetDir.getText() + "/cjdm";
		
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
