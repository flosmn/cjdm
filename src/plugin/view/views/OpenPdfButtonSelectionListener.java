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
