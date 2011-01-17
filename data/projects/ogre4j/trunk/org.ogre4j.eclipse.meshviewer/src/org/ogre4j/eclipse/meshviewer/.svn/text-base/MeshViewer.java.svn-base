/**
 * JTViewer.java
 *
 * Copyright &copy; 2008, netAllied GmbH, Tettnang, Germany. 
 * 
 * @author kklesats
 */
package org.ogre4j.eclipse.meshviewer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.part.EditorPart;
import org.ogre4j.ColourValue;
import org.ogre4j.IAxisAlignedBox;
import org.ogre4j.ICamera;
import org.ogre4j.IColourValue;
import org.ogre4j.IEntity;
import org.ogre4j.ILight;
import org.ogre4j.IRoot;
import org.ogre4j.ISceneManager;
import org.ogre4j.ISceneNode;
import org.ogre4j.IViewport;
import org.ogre4j.Quaternion;
import org.ogre4j.ShadowTechnique;
import org.ogre4j.Vector3;
import org.ogre4j.Light.LightTypes;
import org.ogre4j.eclipse.OgrePlugin;
import org.ogre4j.eclipse.RedrawListener;
import org.ogre4j.eclipse.RenderWindowPool.RenderWindowCanvasPair;

/**
 * A simple viewer for Ogre *.mesh files. The path of the loaded file will be
 * used as OGRE resource location.
 * 
 * TODO handle material paths separately
 * 
 * @author kklesats
 */
public class MeshViewer extends EditorPart implements IPartListener {

	/**
	 * The name of the main light.
	 */
	private static final String NAME_MAIN_LIGHT = "Main Light";

	/**
	 * The suffix for entities.
	 */
	private static final String SUFFIX_ENTITY = ".entity";

	/**
	 * The name of the main camera.
	 */
	private static final String NAME_MAIN_CAMERA = "Main Camera";

	/**
	 * The OGRE root instance.
	 */
	private IRoot root;

	/**
	 * The canvas and renderwindow of this viewer.
	 */
	private RenderWindowCanvasPair windowCanvasPair;

	/**
	 * The OGRE scene manager.
	 */
	private ISceneManager sceneManager;

	/**
	 * The OGRE camera.
	 */
	private ICamera camera;

	/**
	 * The navigator.
	 */
	private CADNavigator navigator;

	/**
	 * The name of the mesh to load. Should be null if the conversion failed.
	 */
	private String meshName;

	/**
	 * The listener for SWT events.
	 */
	private RedrawListener redrawListener;

	private IPath meshFilePath;

	private IPath resourceLocation;

	/**
	 * Default ctor. Creates the temporary directory for this instance.
	 */
	public MeshViewer() {
	}

	/**
	 * Resets the navigator of this instance.
	 */
	private void cleanUpNavigator() {
		navigator.setCamera(null);
		navigator.setCanvas(null);
		navigator.setSceneManager(null);
		navigator = null;
	}

	/**
	 * Creates the OGRE camera for.
	 */
	private void createCamera() {
		camera = sceneManager.createCamera(NAME_MAIN_CAMERA);
		camera.setPosition(0, 10, 20);
		camera.lookAt(0, 0, 0);
		camera.setNearClipDistance(1);
		camera.setFarClipDistance(0);
	}

	/**
	 * Creates the entity for the converted and loaded mesh.
	 */
	private void createEntity() {
		ISceneNode rootSceneNode = sceneManager.getRootSceneNode();
		IEntity entity = sceneManager.createEntity(this.meshName
				+ SUFFIX_ENTITY, this.meshName);
		ISceneNode sceneNode = rootSceneNode.createChildSceneNode(Vector3
				.getZERO(), Quaternion.getIDENTITY());
		sceneNode.attachObject(entity);
		setInitialCameraPosition(entity);
	}

	/**
	 * Creates a light.
	 */
	private void createLight() {
		IColourValue lightColour = new ColourValue(0.7f, 0.7f, 0.7f, 0);
		ILight light = sceneManager.createLight(NAME_MAIN_LIGHT);
		light.setDiffuseColour(lightColour);
		light.setSpecularColour(lightColour);
		lightColour.delete();
		light.setType(LightTypes.LT_DIRECTIONAL);
		light.setDirection(0, -0.5f, -1);
		light.setPosition(0, 50, 100);
		light.setVisible(true);
	}

	/**
	 * Creates all listeners.
	 */
	private void createListeners() {
		redrawListener = new RedrawListener();
		redrawListener.setCamera(camera);
		redrawListener.setCanvasAndRenderWindow(windowCanvasPair);
		windowCanvasPair.canvas.addPaintListener(redrawListener);
		windowCanvasPair.canvas.addControlListener(redrawListener);
	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		// moved into bundle activator
		// initializeOgre();
		root = OgrePlugin.getDefault().getOgreRoot();

		// create RenderWindow with SWT handle
		long handle = createRenderWindow(parent);

		// render window is created, resources can be loaded
		createResourceGroup();

		// create scene manager
		createSceneManager(handle);

		// create light
		createLight();

		// create camera
		createCamera();

		// create viewport
		createViewport();

		// connect camera with render window
		windowCanvasPair.renderWindow.setActive(true);

		// set up navigator
		setUpNavigator();

		createListeners();

		if (this.meshName == null || this.meshName.length() == 0)
			return;
		
		// check for *.mesh extension
		if(!this.meshName.toLowerCase().regionMatches(this.meshName.length()-5,".mesh", 0, 5))
			return;

		createEntity();
	}

	/**
	 * Creates the OGRE render window.
	 * 
	 * @param parent
	 *            The parent composite to use for the render window.
	 * @return The handle of the new created render window.
	 */
	private long createRenderWindow(Composite parent) {
		windowCanvasPair = OgrePlugin.getDefault().getRenderWindowPool()
				.accquireRenderWindow();
		windowCanvasPair.canvas.setSize(1, 1);
		windowCanvasPair.canvas.setParent(parent);
		windowCanvasPair.canvas.setEnabled(true);
		windowCanvasPair.canvas.setVisible(true);
		windowCanvasPair.renderWindow.windowMovedOrResized();
		windowCanvasPair.renderWindow.setVisible(true);
		long handle = windowCanvasPair.canvas.handle;
		return handle;
	}

	/**
	 * Adds the resource location of the viewer to OGRE and loads all resources.
	 */
	private void createResourceGroup() {
		OgrePlugin.getDefault().addResourceLocation(
				resourceLocation.toOSString(),
				OgrePlugin.RESOURCE_LOCATION_TYPE_FILE_SYSTEM, this.toString());
		OgrePlugin.getDefault().initializeResourceGroup(this.toString());
	}

	/**
	 * Creates the OGRE scene manager.
	 * 
	 * @param handle
	 *            The handle of this viewers render window. Will be used as
	 *            suffix of the manager name.
	 */
	private void createSceneManager(long handle) {
		sceneManager = root.createSceneManager(0, this.toString());
		sceneManager.setShadowTechnique(ShadowTechnique.SHADOWTYPE_NONE);
		IColourValue ambientColour = new ColourValue(0.5f, 0.5f, 0.5f, 0);
		sceneManager.setAmbientLight(ambientColour);
		ambientColour.delete();
	}

	/**
	 * Creates the OGRE viewport.
	 */
	private void createViewport() {
		IViewport viewport = windowCanvasPair.renderWindow.addViewport(camera,
				0, 0.0f, 0.0f, 1.0f, 1.0f);
		IColourValue backgroundColour = new ColourValue(0f, 0f, 0f, 1f);
		viewport.setBackgroundColour(backgroundColour);
		backgroundColour.delete();
		viewport.setOverlaysEnabled(false);
		viewport.setCamera(camera);
		camera.setAspectRatio((float) windowCanvasPair.canvas.getBounds().width
				/ (float) windowCanvasPair.canvas.getBounds().height);
	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		// nothing to do

	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		// nothing to do

	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.ui.part.EditorPart#init(org.eclipse.ui.IEditorSite,
	 *      org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		site.getWorkbenchWindow().getPartService().addPartListener(this);
	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.ui.part.EditorPart#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return false;
	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.ui.IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partActivated(IWorkbenchPart part) {
		// nothing to do

	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.ui.IPartListener#partBroughtToTop(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partBroughtToTop(IWorkbenchPart part) {
		// nothing to do

	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.ui.IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partClosed(IWorkbenchPart part) {
		if (part != this)
			return;

		if (windowCanvasPair != null) {
			removeListeners();
			cleanUpNavigator();

			root.destroySceneManager(sceneManager);
			sceneManager = null;
			camera = null;

			OgrePlugin.getDefault().removeResourceLocation(
					resourceLocation.toOSString(), this.toString());
			OgrePlugin.getDefault().destroyResourceGroup(this.toString());

			OgrePlugin.getDefault().getRenderWindowPool().releaseRenderWindow(
					windowCanvasPair);
			windowCanvasPair = null;
		}

		getSite().getWorkbenchWindow().getPartService()
				.removePartListener(this);

		resourceLocation = null;
		meshFilePath = null;
		meshName = null;
	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.ui.IPartListener#partDeactivated(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partDeactivated(IWorkbenchPart part) {
		// nothing to do

	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.ui.IPartListener#partOpened(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partOpened(IWorkbenchPart part) {
		// nothing to do

	}

	/**
	 * Removes all listeners.
	 */
	private void removeListeners() {
		windowCanvasPair.canvas.removePaintListener(redrawListener);
		windowCanvasPair.canvas.removeControlListener(redrawListener);
		redrawListener.setCamera(null);
		redrawListener.setCanvasAndRenderWindow(null);
	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// nothing to do.
	}

	/**
	 * Sets the initial camera position for the viewer to the center of the
	 * given entities bounding box.
	 * 
	 * @param entity
	 *            The entity to focus on.
	 */
	private void setInitialCameraPosition(IEntity entity) {
		IAxisAlignedBox bb = entity.getBoundingBox();
		this.navigator.setInitialCameraPosition(bb);
	}

	/**
	 * @{inheritdoc
	 * @see org.eclipse.ui.part.EditorPart#setInput(org.eclipse.ui.IEditorInput)
	 */
	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);

		meshFilePath = null;
		if (input instanceof IFileEditorInput) {
			IFileEditorInput fileInput = (IFileEditorInput) input;
			IFile meshFile = fileInput.getFile();
			meshFilePath = new Path(meshFile.getLocationURI().getPath());
		} else if (input instanceof FileStoreEditorInput) {
			FileStoreEditorInput fileStoreInput = (FileStoreEditorInput) input;
			meshFilePath = new Path(fileStoreInput.getURI().getPath());
		}

		if (meshFilePath == null)
			return;

		meshFilePath = meshFilePath.makeAbsolute();

		if (!meshFilePath.toFile().exists()) {
			this.meshFilePath = null;
			return;
		}

		this.meshName = meshFilePath.lastSegment();
		this.resourceLocation = meshFilePath.removeLastSegments(1);
		setPartName(this.meshName);
	}

	/**
	 * Sets up the navigator.
	 */
	private void setUpNavigator() {
		navigator = new CADNavigator();
		navigator.setCamera(camera);
		navigator.setCanvas(windowCanvasPair.canvas);
		navigator.setSceneManager(sceneManager);
	}
}
