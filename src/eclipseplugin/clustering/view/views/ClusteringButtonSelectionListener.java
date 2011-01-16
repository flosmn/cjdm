package eclipseplugin.clustering.view.views;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;

import clustering.DendogramCreator;

public class ClusteringButtonSelectionListener implements SelectionListener {

	private final Text csvSource;
	private final Text pdfTargetDir;
	private final Text pdfName;

	public ClusteringButtonSelectionListener(Text csvSource, Text pdfTargetDir, Text pdfName) {
		this.csvSource = csvSource;
		this.pdfTargetDir = pdfTargetDir;
		this.pdfName = pdfName;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		System.out.println("Clustering");
		
		String csvSourceFile = "C:/root/csv/classbla.csv";
		String pdfTargetDirPath = "C:/root/pdf/";
		String pdfNameTemp = "class.pdf";
		
		DendogramCreator dendogramCreator = new DendogramCreator(csvSourceFile, pdfTargetDirPath, pdfNameTemp);
		dendogramCreator.createClustering();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
