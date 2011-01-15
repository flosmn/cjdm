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

	    sc.setMinSize(600, 600);

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
		ClusteringViewComponents.getBrowseButton(comp, rootDir);
		ClusteringViewComponents.getTargetDirLabel(comp, numColumns);
		ClusteringViewComponents.getDirectoryLabel(comp);
		Text targetDir = ClusteringViewComponents.getDirTextField(comp);
		ClusteringViewComponents.getBrowseButton(comp, targetDir);
		ClusteringViewComponents.getTargetNameClassLabel(comp, numColumns);
		ClusteringViewComponents.getNameLabel(comp);
		Text className = ClusteringViewComponents.getNameTextField(comp, numColumns);
		ClusteringViewComponents.getTargetNameMethodLabel(comp, numColumns);
		ClusteringViewComponents.getNameLabel(comp);
		Text methodName = ClusteringViewComponents.getNameTextField(comp, numColumns);
		ClusteringViewComponents.getFilterLevelLabel(comp, numColumns);
		ClusteringViewComponents.getFilterLabel(comp);
		Text filterLevel = ClusteringViewComponents.getFilterLevelTextField(comp, numColumns);
		ClusteringViewComponents.getAnalyseButton(comp, numColumns, rootDir, targetDir, className, methodName, filterLevel);
				
		ClusteringViewComponents.getHeadingPhase2(comp, numColumns);
		ClusteringViewComponents.getDatasetDirLabel(comp, numColumns);
		ClusteringViewComponents.getDirectoryLabel(comp);
		Text datasetDir = ClusteringViewComponents.getDirTextField(comp);
		ClusteringViewComponents.getBrowseButton(comp, datasetDir);
		ClusteringViewComponents.getPerformClusteringButton(comp, numColumns, datasetDir);
		
		return comp;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}