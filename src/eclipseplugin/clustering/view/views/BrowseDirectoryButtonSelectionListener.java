package eclipseplugin.clustering.view.views;


import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Text;

public class BrowseDirectoryButtonSelectionListener implements
		org.eclipse.swt.events.SelectionListener {

	private final Text textField;

	public BrowseDirectoryButtonSelectionListener(Text textField) {
		this.textField = textField;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		 DirectoryDialog dlg = new DirectoryDialog(this.textField.getShell());
		 String selectedDirectory = dlg.open();
		 this.textField.setText(selectedDirectory);   
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
