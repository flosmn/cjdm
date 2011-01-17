package eclipseplugin.clustering.view.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

public class BrowseFileButtonSelectionListener implements SelectionListener {

	private final Text textField;

	public BrowseFileButtonSelectionListener(Text textField) {
		this.textField = textField;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		FileDialog fd = new FileDialog(this.textField.getShell(), SWT.OPEN);
        fd.setText("Open");
        String[] filterExt = { "*.csv" };
        fd.setFilterExtensions(filterExt);
        String selected = fd.open();
        this.textField.setText(selected);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
