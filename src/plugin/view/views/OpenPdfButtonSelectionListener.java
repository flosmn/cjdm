package plugin.view.views;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

public class OpenPdfButtonSelectionListener implements SelectionListener {

	private final OpenPdfAfterCreation openPdf;

	public OpenPdfButtonSelectionListener(OpenPdfAfterCreation openPdf) {
		this.openPdf = openPdf;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		this.openPdf.setOpenPdfAfterCreation(((Button)e.getSource()).getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
	}

}
