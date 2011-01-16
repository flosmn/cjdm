package eclipseplugin.clustering.view.views;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;

public class AnalyseButtonSelectionListener implements SelectionListener {

	private final Text rootDir;
	private final Text targetDir;
	private final Text className;
	private final Text methodName;
	private final Text filterLevel;

	public AnalyseButtonSelectionListener(Text rootDir, Text targetDir,
			Text className, Text methodName, Text filterLevel) {
				this.rootDir = rootDir;
				this.targetDir = targetDir;
				this.className = className;
				this.methodName = methodName;
				this.filterLevel = filterLevel;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
