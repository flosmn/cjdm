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
       
        if(selected != null){
        	this.textField.setText(selected);
        }
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
