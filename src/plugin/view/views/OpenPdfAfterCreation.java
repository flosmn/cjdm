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

public class OpenPdfAfterCreation {

	private boolean openPdfAfterCreation = false;

	public void setOpenPdfAfterCreation(boolean openPdfAfterCreation) {
		this.openPdfAfterCreation = openPdfAfterCreation;
	}

	public boolean isOpenPdfAfterCreation() {
		return openPdfAfterCreation;
	}
}
