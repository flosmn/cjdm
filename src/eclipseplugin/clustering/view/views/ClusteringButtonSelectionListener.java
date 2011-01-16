package eclipseplugin.clustering.view.views;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;

public class ClusteringButtonSelectionListener implements SelectionListener {

	private final Text csvSourceDir;

	public ClusteringButtonSelectionListener(Text csvSourceDir) {
		this.csvSourceDir = csvSourceDir;
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
