/**
Ê* @author Florian Simon
Ê* @author Stefan Kober
Ê* Team 09
Ê*
Ê* This code has been developed during the winter term 2010-2011 at the
Ê* Karlsruhe Institute of Technology (KIT), Germany.
Ê* It is part of a project assignment in the course
Ê* "Multicore Programming in Practice: Tools, Models, and Languages".
Ê* Project director/instructor:
Ê* Dr. Victor Pankratius (pankratius@ipd.uka.de)
**/
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
