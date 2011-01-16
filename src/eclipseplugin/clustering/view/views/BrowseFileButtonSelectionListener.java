package eclipseplugin.clustering.view.views;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;

public class BrowseFileButtonSelectionListener implements SelectionListener {

	private final Text textField;

	public BrowseFileButtonSelectionListener(Text textField) {
		this.textField = textField;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		System.out.println("Open file choosing dialog");
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
