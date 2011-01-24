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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import clustering.DendogramCreator;

public class ClusteringButtonSelectionListener implements SelectionListener {

	private final Text csvSource;
	private final Text pdfTargetDir;
	private final Text pdfName;
	private final OpenPdfAfterCreation openPdf;
	private final Combo methodCombo;
	private final Combo metricCombo;

	public ClusteringButtonSelectionListener(Text csvSource, Text pdfTargetDir, Text pdfName, OpenPdfAfterCreation openPdf, Combo methodCombo, Combo metricCombo) {
		this.csvSource = csvSource;
		this.pdfTargetDir = pdfTargetDir;
		this.pdfName = pdfName;
		this.openPdf = openPdf;
		this.methodCombo = methodCombo;
		this.metricCombo = metricCombo;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		String csvSourceFile = csvSource.getText();
		String pdfTargetDirPath = pdfTargetDir.getText() + "/";
		String pdfNameTemp = pdfName.getText();

		csvSourceFile = csvSourceFile.replaceAll("\\\\", "/");
		pdfTargetDirPath = pdfTargetDirPath.replaceAll("\\\\", "/");
		
		if(!pdfNameTemp.endsWith(".pdf")) {
			pdfNameTemp += ".pdf";
		}
		
		DendogramCreator dendogramCreator = new DendogramCreator(csvSourceFile, pdfTargetDirPath, pdfNameTemp, openPdf, methodCombo, metricCombo);
		dendogramCreator.createClustering();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
	}

}
