package eclipseplugin.clustering.view.views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ClusteringViewComponents {

	public static final String HEADING = "Clustering";
	public static final String HEADING_PHASE1 = "Analyse Java files and create Csv files which can be used by the Clustering.";
	public static final String HEADING_PHASE2 = "Perform a Clustering on a dataset.";
	public static final String ROOT_DIR_LABEL = "Select root directory of Java files to analyse.";
	public static final String TARGET_DIR_LABEL_CSV_LABEL = "Select directory in which to save the Csv files containing the result of the analyse phase.";
	public static final String CHOOSE_TEXT_LABEL_CLASS = "Enter name of the Csv file containing the class information.";
	public static final String CHOOSE_TEXT_LABEL_METHOD = "Enter name of the Csv file containing the method information.";
	public static final String CHOOSE_FILTER_LEVEL_CLASS_LABEL = "Set a filter level for the class datavectors. The number indicates how much elements of a datavector have to be non-zero to be included in the dataset. So the higher the number the smaller will the dataset be.";
	public static final String CHOOSE_FILTER_LEVEL_METHOD_LABEL = "Set a filter level for the method datavectors.";
	public static final String CHOOSE_DATASET_DIR_LABEL = "Select a directory containing the Csv files which should be used for Clustering.";
	
	public static final String DIR_LABEL = "Directory:";
	public static final String NAME_LABEL = "Name:";
	public static final String FILTER_LEVEL_LABEL = "Enter filter Level:";
	public static final String BROWSE_BUTTON_LABEL = "Browse";
	public static final String ANALYSE_BUTTON_LABEL = "Analyse";
	public static final String CLUSTERING_BUTTON_LABEL = "Perform Clustering";
	private static GridData gridData;
	
	public static Label getHeading(Composite parent, int numColumns){
		Label label = getLabelFillHoleWidth(parent, HEADING, numColumns);
		label.setFont(getFontWithSize(label, 14));
		return label;
	}
	
	private static Font getFontWithSize(Label label, int i) {
		FontData[] fontData = label.getFont().getFontData();
		Device device = label.getFont().getDevice();
		fontData[0].setHeight(i);
		return new Font(device, fontData);
	}

	public static Label getHeadingPhase1(Composite parent, int numColumns){
		Label label = getLabelFillHoleWidth(parent, HEADING_PHASE1, numColumns);
		label.setFont(getFontWithSize(label, 12));
		return label;
	}

	public static Label getHeadingPhase2(Composite parent, int numColumns){
		Label label = getLabelFillHoleWidth(parent, HEADING_PHASE2, numColumns);
		label.setFont(getFontWithSize(label, 12));
		return label;
	}
	
	private static Label getLabelFillHoleWidth(final Composite parent, String text, int numColumns){
		final Label label = getLabel(parent, text);
		final GridData layoutData = (GridData)label.getLayoutData();
		layoutData.horizontalSpan = numColumns;
		layoutData.grabExcessHorizontalSpace = true;
		layoutData.horizontalAlignment = GridData.FILL;
		layoutData.verticalIndent = 10;
		label.setLayoutData(layoutData);
		return label;
	}
	
	private static Label getLabel(Composite parent, String text){
		Label label = new Label(parent, SWT.BOLD);
		label.setText(text);
		gridData = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
		label.setLayoutData(gridData);
		return label;
	}

	public static Label getRootDirLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, ROOT_DIR_LABEL, numColumns);
		return label;		
	}

	public static Label getDirectoryLabel(Composite parent) {
		Label label = getLabel(parent, DIR_LABEL);
		return label;
	}

	public static Text getDirTextField(Composite parent) {
		Text textField = new Text(parent, SWT.BOLD);
		gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData.heightHint = 25;
		textField.setLayoutData(gridData);
		return textField;
	}

	public static Button getBrowseButton(Composite parent, Text textField) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(BROWSE_BUTTON_LABEL);
		button.addSelectionListener(new BrowseButtonSelectionListener(textField));
		gridData = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
		button.setLayoutData(gridData);
		return button;
		
	}

	public static Label getTargetDirLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, TARGET_DIR_LABEL_CSV_LABEL, numColumns);
		return label;
	}

	public static Label getTargetNameMethodLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, CHOOSE_TEXT_LABEL_METHOD, numColumns);
		return label;
	}

	public static Label getTargetNameClassLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, CHOOSE_TEXT_LABEL_CLASS, numColumns);
		return label;
	}

	public static Label getNameLabel(Composite parent) {
		Label label = getLabel(parent, NAME_LABEL);
		return label;
	}

	public static Text getNameTextField(Composite parent, int numColumns) {
		Text textField = new Text(parent, SWT.BOLD);
		gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData.horizontalSpan = numColumns - 1;
		gridData.heightHint = 25;
		textField.setLayoutData(gridData);
		return textField;
	}

	public static Label getFilterLevelClassLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, CHOOSE_FILTER_LEVEL_CLASS_LABEL, numColumns);
		return label;
	}
	
	public static Label getFilterLevelMethodLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, CHOOSE_FILTER_LEVEL_METHOD_LABEL, numColumns);
		return label;		
	}

	public static Label getFilterLabel(Composite parent) {
		Label label = getLabel(parent, FILTER_LEVEL_LABEL);
		return label;
	}

	public static Text getFilterLevelTextField(Composite parent,
			int numColumns) {
		Text textField = new Text(parent, SWT.BOLD);
		gridData = new GridData(GridData.BEGINNING, GridData.CENTER, true, false);
		gridData.heightHint = 25;
		textField.setLayoutData(gridData);
		return textField;
	}

	public static Button getAnalyseButton(Composite parent, int numColumns, Text rootDir,
			Text targetDir, Text className, Text methodName, Text filterLevelClass, Text filterLevelMethod) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(ANALYSE_BUTTON_LABEL);
		button.addSelectionListener(new AnalyseButtonSelectionListener(rootDir, targetDir, className, methodName, filterLevelClass, filterLevelMethod));
		gridData = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
		gridData.horizontalSpan = numColumns;
		gridData.verticalIndent = 10;
		button.setLayoutData(gridData);
		return button;
	}

	public static Label getDatasetDirLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, CHOOSE_DATASET_DIR_LABEL, numColumns);
		return label;
	}

	public static Button getPerformClusteringButton(Composite parent,
			int numColumns, Text datasetDir) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(CLUSTERING_BUTTON_LABEL);
		button.addSelectionListener(new ClusteringButtonSelectionListener(datasetDir));
		gridData = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
		gridData.horizontalSpan = numColumns;
		gridData.verticalSpan = 10;
		button.setLayoutData(gridData);
		return button;
	}
	
	
}
