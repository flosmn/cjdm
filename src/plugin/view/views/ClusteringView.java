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


import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class ClusteringView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "eclipseplugin.view.views.MainView";
	int numColumns = 3;
	private OpenPdfAfterCreation openPdf;

	@Override
	public void createPartControl(Composite parent) {
	    ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);

	    Composite componentParent = getComponents(sc);
	    sc.setContent(componentParent);

	    sc.setMinSize(600, 1100);

	    sc.setExpandHorizontal(true);
	    sc.setExpandVertical(true);
	}

	private Composite getComponents(Composite parent) {
		openPdf = new OpenPdfAfterCreation();
		
		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = numColumns;
		comp.setLayout(gridLayout);
		
		ClusteringViewComponents.getHeading(comp, numColumns);
		
		addAnalysePhaseComponents(comp);
		addExportPhaseComponents(comp);
		addClusteringPhaseComponents(comp);
		
		return comp;
	}

	private void addClusteringPhaseComponents(Composite comp) {
		ClusteringViewComponents.getHeadingPhase3(comp, numColumns);
		
		// csv source file
		ClusteringViewComponents.getCsvSourceDirLabel(comp, numColumns);
		ClusteringViewComponents.getDirectoryLabel(comp);
		Text csvSource = ClusteringViewComponents.getDirTextField(comp);
		ClusteringViewComponents.getButtonBrowseFile(comp, csvSource);
		
		// pdf target dir
		ClusteringViewComponents.getPdfTargetDirLabel(comp, numColumns);
		ClusteringViewComponents.getDirectoryLabel(comp);
		Text pdfTargetDir = ClusteringViewComponents.getDirTextField(comp);
		ClusteringViewComponents.getButtonBrowseDirectory(comp, pdfTargetDir);
		
		// pdf name
		ClusteringViewComponents.getPdfNameLabel(comp, numColumns);
		ClusteringViewComponents.getNameLabel(comp);
		Text pdfName = ClusteringViewComponents.getNameTextField(comp, numColumns);
		
		ClusteringViewComponents.getMethodLabel(comp, numColumns);
		Combo methodCombo = ClusteringViewComponents.getMethodCombo(comp, numColumns);
		
		ClusteringViewComponents.getMetricLabel(comp, numColumns);
		Combo metricCombo = ClusteringViewComponents.getMetricCombo(comp, numColumns);
		
		ClusteringViewComponents.getButtonPerformClustering(comp, numColumns, csvSource, pdfTargetDir, pdfName, openPdf, methodCombo, metricCombo);
		ClusteringViewComponents.getButtonOpenPdf(comp, numColumns, openPdf);
	}

	private void addExportPhaseComponents(Composite comp) {
		ClusteringViewComponents.getHeadingPhase2(comp, numColumns);
		
		// database source dir 
		ClusteringViewComponents.getDatabaseSourceDirLabel(comp, numColumns);
		ClusteringViewComponents.getDirectoryLabel(comp);
		Text databaseSourceDir = ClusteringViewComponents.getDirTextField(comp);
		ClusteringViewComponents.getButtonBrowseDirectory(comp, databaseSourceDir);
		
		// csv target dir
		ClusteringViewComponents.getCsvTargetDirLabel(comp, numColumns);
		ClusteringViewComponents.getDirectoryLabel(comp);
		Text csvTargetDir = ClusteringViewComponents.getDirTextField(comp);
		ClusteringViewComponents.getButtonBrowseDirectory(comp, csvTargetDir);
		
		// name of class csv
		ClusteringViewComponents.getClassNameLabel(comp, numColumns);
		ClusteringViewComponents.getNameLabel(comp);
		Text className = ClusteringViewComponents.getNameTextField(comp, numColumns);
		
		// name of method csv
		ClusteringViewComponents.getMethodNameLabel(comp, numColumns);
		ClusteringViewComponents.getNameLabel(comp);
		Text methodName = ClusteringViewComponents.getNameTextField(comp, numColumns);
		
		// filter level of class
		ClusteringViewComponents.getFilterLevelClassLabel(comp, numColumns);
		ClusteringViewComponents.getFilterLabel(comp);
		Text filterLevelClass = ClusteringViewComponents.getFilterLevelTextField(comp, numColumns);
		
		// filter level of method
		ClusteringViewComponents.getFilterLevelMethodLabel(comp, numColumns);
		ClusteringViewComponents.getFilterLabel(comp);
		Text filterLevelMethod = ClusteringViewComponents.getFilterLevelTextField(comp, numColumns);
		ClusteringViewComponents.getButtonExport(comp, numColumns, databaseSourceDir, csvTargetDir, className, methodName, filterLevelClass, filterLevelMethod);
	}

	private void addAnalysePhaseComponents(Composite comp) {
		ClusteringViewComponents.getHeadingPhase1(comp, numColumns);
		
		// root dir
		ClusteringViewComponents.getRootDirLabel(comp, numColumns);
		ClusteringViewComponents.getDirectoryLabel(comp);
		Text rootDir = ClusteringViewComponents.getDirTextField(comp);
		ClusteringViewComponents.getButtonBrowseDirectory(comp, rootDir);
		
		// database target dir
		ClusteringViewComponents.getDatabaseTargetDirLabel(comp, numColumns);
		ClusteringViewComponents.getDirectoryLabel(comp);
		Text databaseTargetDir = ClusteringViewComponents.getDirTextField(comp);
		ClusteringViewComponents.getButtonBrowseDirectory(comp, databaseTargetDir);
		ClusteringViewComponents.getButtonAnalyse(comp, numColumns, rootDir, databaseTargetDir);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}