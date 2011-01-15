package eclipseplugin.clustering.view.views;


import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class BrowseButtonSelectionListener implements
		org.eclipse.swt.events.SelectionListener {

	private final Text textField;

	public BrowseButtonSelectionListener(Text textField) {
		this.textField = textField;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		System.out.println("Open file open dialog");

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
