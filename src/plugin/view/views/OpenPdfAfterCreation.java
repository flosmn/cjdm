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

public class OpenPdfAfterCreation {

	private boolean openPdfAfterCreation = false;

	public void setOpenPdfAfterCreation(boolean openPdfAfterCreation) {
		this.openPdfAfterCreation = openPdfAfterCreation;
	}

	public boolean isOpenPdfAfterCreation() {
		return openPdfAfterCreation;
	}
}
