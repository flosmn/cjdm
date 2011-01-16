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

	private static GridData gridData;

	public static final String ANALYSE_BUTTON_LABEL = "Analyse";
	public static final String BROWSE_BUTTON_LABEL = "Browse";
	public static final String CLASS_NAME_LABEL = "Enter name of the csv dataset containing the class information.";
	public static final String CLUSTERING_BUTTON_LABEL = "Perform Clustering";
	public static final String CSV_SOURCE_DIR_LABEL = "Select a directory containing the csv datasets which should be used for Clustering.";
	public static final String CSV_TARGET_DIR_LABEL = "Select directory in which to save the csv datasets.";
	private static final String PDF_TARGET_DIR_LABEL = "Select directory in which to save the pdf file containing the clustering result.";;
	public static final String DATABASE_SOURCE_DIR_LABEL = "Select the directory of a database to export to csv datasets.";
	public static final String DATABASE_TARGET_DIR_LABEL = "Select the directory in which to save the database containing the result of the analyse phase.";
	public static final String DIR_LABEL = "Directory:";
	public static final String EXPORT_BUTTON_LABEL = "Export";
	public static final String FILTER_LEVEL_CLASS_LABEL = "Set a filter level for the class datavectors. The number indicates how much elements of a datavector have to be non-zero to be included in the dataset. So the higher the number the smaller will the dataset be.";
	public static final String FILTER_LEVEL_LABEL = "Enter filter Level:";
	public static final String FILTER_LEVEL_METHOD_LABEL = "Set a filter level for the method datavectors.";
	
	public static final String HEADING = "Clustering";
	public static final String HEADING_PHASE1 = "Analyse Java files and create a database containing the analyse information.";
	public static final String HEADING_PHASE2 = "Export a database to csv datasets which can be used for the clustering.";
	public static final String HEADING_PHASE3 = "Perform a clustering on csv datasets.";
	public static final String METHOD_NAME_LABEL = "Enter name of the csv dataset containing the method information.";
	private static final String PDF_NAME_LABEL = "Enter name of the pdf file containing the clustering result.";
	public static final String NAME_LABEL = "Name:";
	public static final String ROOT_DIR_LABEL = "Select root directory of Java files to analyse.";


	
	public static Button getButtonAnalyse(Composite parent, int numColumns, Text rootDir,
			Text databaseTargetDir) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(ANALYSE_BUTTON_LABEL);
		button.addSelectionListener(new AnalyseButtonSelectionListener(rootDir, databaseTargetDir));
		gridData = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
		gridData.horizontalSpan = numColumns;
		gridData.verticalIndent = 10;
		button.setLayoutData(gridData);
		return button;
	}
	
	public static Button getButtonBrowseFile(Composite parent, Text textField) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(BROWSE_BUTTON_LABEL);
		button.addSelectionListener(new BrowseFileButtonSelectionListener(textField));
		gridData = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
		button.setLayoutData(gridData);
		return button;
	}
	
	public static Button getButtonBrowseDirectory(Composite parent, Text textField) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(BROWSE_BUTTON_LABEL);
		button.addSelectionListener(new BrowseDirectoryButtonSelectionListener(textField));
		gridData = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
		button.setLayoutData(gridData);
		return button;
	}

	public static Button getButtonExport(Composite parent,
			int numColumns, Text databaseSourceDir, Text csvTargetDir,Text className, Text methodName, Text filterLevelClass, Text filterLevelMethod) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(EXPORT_BUTTON_LABEL);
		button.addSelectionListener(new ExportButtonSelectionListener(databaseSourceDir, csvTargetDir, className, methodName, filterLevelClass, filterLevelMethod));
		gridData = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
		gridData.horizontalSpan = numColumns;
		gridData.verticalSpan = 10;
		button.setLayoutData(gridData);
		return button;
	}
	
	public static Button getButtonPerformClustering(Composite parent,
			int numColumns, Text csvSource, Text pdfTargetDir, Text pdfName) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(CLUSTERING_BUTTON_LABEL);
		button.addSelectionListener(new ClusteringButtonSelectionListener(csvSource, pdfTargetDir, pdfName));
		gridData = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
		gridData.horizontalSpan = numColumns;
		gridData.verticalSpan = 10;
		button.setLayoutData(gridData);
		return button;
	}

	public static Label getClassNameLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, CLASS_NAME_LABEL, numColumns);
		return label;
	}
	
	public static Label getCsvSourceDirLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, CSV_SOURCE_DIR_LABEL, numColumns);
		return label;
	}
	
	public static Label getCsvTargetDirLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, CSV_TARGET_DIR_LABEL, numColumns);
		return label;
	}
	
	public static Label getPdfTargetDirLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, PDF_TARGET_DIR_LABEL, numColumns);
		return label;
	}

	public static Label getDatabaseSourceDirLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, DATABASE_SOURCE_DIR_LABEL, numColumns);
		return label;		
	}

	public static Label getDatabaseTargetDirLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, DATABASE_TARGET_DIR_LABEL, numColumns);
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
	
	public static Label getFilterLabel(Composite parent) {
		Label label = getLabel(parent, FILTER_LEVEL_LABEL);
		return label;
	}

	public static Label getFilterLevelClassLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, FILTER_LEVEL_CLASS_LABEL, numColumns);
		return label;
	}

	public static Label getFilterLevelMethodLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, FILTER_LEVEL_METHOD_LABEL, numColumns);
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

	private static Font getFontWithSize(Label label, int i) {
		FontData[] fontData = label.getFont().getFontData();
		Device device = label.getFont().getDevice();
		fontData[0].setHeight(i);
		return new Font(device, fontData);
	}

	public static Label getHeading(Composite parent, int numColumns){
		Label label = getLabelFillHoleWidth(parent, HEADING, numColumns);
		label.setFont(getFontWithSize(label, 14));
		return label;
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
	
	public static Label getHeadingPhase3(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, HEADING_PHASE3, numColumns);
		label.setFont(getFontWithSize(label, 12));
		return label;
	}

	private static Label getLabel(Composite parent, String text){
		Label label = new Label(parent, SWT.BOLD);
		label.setText(text);
		gridData = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
		label.setLayoutData(gridData);
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

	public static Label getMethodNameLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, METHOD_NAME_LABEL, numColumns);
		return label;
	}
	
	public static Label getPdfNameLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, PDF_NAME_LABEL, numColumns);
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

	public static Label getRootDirLabel(Composite parent, int numColumns) {
		Label label = getLabelFillHoleWidth(parent, ROOT_DIR_LABEL, numColumns);
		return label;		
	}
}
