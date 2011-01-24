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
