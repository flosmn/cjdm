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
		 
		 if(selectedDirectory != null){
			 this.textField.setText(selectedDirectory);
		 }
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
