package eclipseplugin.clustering.view.views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridLayout;
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

	@Override
	public void createPartControl(Composite parent) {
	    ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);

	    Composite componentParent = getComponents(sc);
	    sc.setContent(componentParent);

	    sc.setMinSize(600, 1000);

	    sc.setExpandHorizontal(true);
	    sc.setExpandVertical(true);
	}

	private Composite getComponents(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = numColumns;
		comp.setLayout(gridLayout);
		
		ClusteringViewComponents.getHeading(comp, numColumns);
		
		ClusteringViewComponents.getHeadingPhase1(comp, numColumns);
		ClusteringViewComponents.getRootDirLabel(comp, numColumns);
		ClusteringViewComponents.getDirectoryLabel(comp);
		Text rootDir = ClusteringViewComponents.getDirTextField(comp);
		ClusteringViewComponents.getButtonBrowse(comp, rootDir);
		ClusteringViewComponents.getDatabaseTargetDirLabel(comp, numColumns);
		ClusteringViewComponents.getDirectoryLabel(comp);
		Text databaseTargetDir = ClusteringViewComponents.getDirTextField(comp);
		ClusteringViewComponents.getButtonBrowse(comp, databaseTargetDir);
		ClusteringViewComponents.getButtonAnalyse(comp, numColumns, rootDir, databaseTargetDir);
		
		ClusteringViewComponents.getHeadingPhase2(comp, numColumns);
		ClusteringViewComponents.getDatabaseSourceDirLabel(comp, numColumns);
		ClusteringViewComponents.getDirectoryLabel(comp);
		Text databaseSourceDir = ClusteringViewComponents.getDirTextField(comp);
		ClusteringViewComponents.getButtonBrowse(comp, databaseSourceDir);
		ClusteringViewComponents.getCsvTargetDirLabel(comp, numColumns);
		ClusteringViewComponents.getDirectoryLabel(comp);
		Text csvTargetDir = ClusteringViewComponents.getDirTextField(comp);
		ClusteringViewComponents.getButtonBrowse(comp, csvTargetDir);
		ClusteringViewComponents.getClassNameLabel(comp, numColumns);
		ClusteringViewComponents.getNameLabel(comp);
		Text className = ClusteringViewComponents.getNameTextField(comp, numColumns);
		ClusteringViewComponents.getMethodNameLabel(comp, numColumns);
		ClusteringViewComponents.getNameLabel(comp);
		Text methodName = ClusteringViewComponents.getNameTextField(comp, numColumns);
		ClusteringViewComponents.getFilterLevelClassLabel(comp, numColumns);
		ClusteringViewComponents.getFilterLabel(comp);
		Text filterLevelClass = ClusteringViewComponents.getFilterLevelTextField(comp, numColumns);
		ClusteringViewComponents.getFilterLevelMethodLabel(comp, numColumns);
		ClusteringViewComponents.getFilterLabel(comp);
		Text filterLevelMethod = ClusteringViewComponents.getFilterLevelTextField(comp, numColumns);
		ClusteringViewComponents.getButtonExport(comp, numColumns, databaseSourceDir, csvTargetDir, className, methodName, filterLevelClass, filterLevelMethod);
				
		ClusteringViewComponents.getHeadingPhase3(comp, numColumns);
		ClusteringViewComponents.getCsvSourceDirLabel(comp, numColumns);
		ClusteringViewComponents.getDirectoryLabel(comp);
		Text csvSourceDir = ClusteringViewComponents.getDirTextField(comp);
		ClusteringViewComponents.getButtonBrowse(comp, csvSourceDir);
		ClusteringViewComponents.getButtonPerformClustering(comp, numColumns, csvSourceDir);
		
		return comp;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}