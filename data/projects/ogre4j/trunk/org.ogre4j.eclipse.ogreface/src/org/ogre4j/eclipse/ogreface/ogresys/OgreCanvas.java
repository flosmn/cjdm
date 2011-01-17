package org.ogre4j.eclipse.ogreface.ogresys;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TypedListener;
import org.ogre4j.ICamera;
import org.ogre4j.INameValuePairList;
import org.ogre4j.IRenderWindow;
import org.ogre4j.IRoot;
import org.ogre4j.IViewport;
import org.ogre4j.NameValuePairList;
import org.ogre4j.Root;
import org.xbig.base.StringPointer;

public class OgreCanvas extends Canvas implements DisposeListener {

	private IRenderWindow renderWindow = null;

	private OgreScene scene = null;
	
	private String name = null;
	
    private String selectionName = null;
    
    private IViewport viewport = null;

	private String camName;

	public OgreCanvas(Composite parent, String name, OgreScene scene)
			throws Exception {
		super(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);

		this.scene = scene;
		
		// remember name
		this.name = name;

		// check if Plugin is running
		if (OgreSystem.getDefault() == null) {
			throw new RuntimeException(
					"Cannot create canvas because plugin is not running !!!");
		}
		// necessary that OGRE can create it's viewport in SWT applications
		getShell().open();

		// create render window
		IRoot root = OgreSystem.getDefault().getRoot();
		// System.out.println("Canvas parent size: " + parent.getSize());
		// System.out.println("Canvas parent ClientArea: " +
		// parent.getClientArea());
		// System.out.println("Canvas size: " + getSize());
		// System.out.println("Canvas ClientArea: " + getClientArea());
		if (getSize().x == 0 && getSize().y == 0) {
			setSize(1, 1);
		}

		// create RenderWindow with SWT handle
		INameValuePairList params = new NameValuePairList();
		StringPointer windowHandlePointer = new StringPointer(Integer
				.toString(this.handle));
		params.insert("externalWindowHandle", windowHandlePointer);
		renderWindow = root.createRenderWindow(name, 1, 1, false, params);

		// set render window inactive
		renderWindow.setActive(false);

		// register
		// OgrePlugin.getDefault().registerCanvas(this);

		// take care of paint events
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				renderWindow.windowMovedOrResized();
				OgreSystem.getDefault().redraw();
			}
		});

		// clean up on disposal
		parent.addDisposeListener(this);
	}

	public String getOgreCanvasName() {
		return name;
	}
	
	public void removeSelectionListener(SelectionListener listener) {
		removeListener(SWT.Selection, listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Control#setBounds(int, int, int, int)
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		float aspectRatio = 1;
		if (height != 0) {
			aspectRatio = (float) width / (float) height;
		}
		scene.setAspectRatio(camName, aspectRatio);
		super.setBounds(x, y, width, height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Control#setBounds(org.eclipse.swt.graphics.Rectangle)
	 */
	@Override
	public void setBounds(Rectangle rect) {
		float aspectRatio = 1;
		if (rect.height != 0) {
			aspectRatio = (float) rect.width / (float) rect.height;
		}
		scene.setAspectRatio(camName, aspectRatio);
		super.setBounds(rect);
	}
	
	
	/*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.widgets.Control#setSize(int, int)
     */
    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        if (renderWindow == null) {
            return;
        }
        renderWindow.windowMovedOrResized();
        Point size = getSize();
        float ratio = (float) size.x / (float) size.y;
        // TODO set aspect ratio for all cameras in the ogre system!
        scene.setAspectRatio(camName, ratio);
    }

    /**
     * 
     * @param objectName
     * @return void
     */
    public void setSelection(String objectName) {
        // get node from map
        if (!scene.hasNode(objectName)) {
            throw new RuntimeException("Invalid node name: " + objectName + " !!!");
        }

        // remember selected
        selectionName = objectName;

        // show bounding box
        // node.setShowBoundingBox(true);

        // tell renderloop about change
        OgreSystem.getDefault().redraw();

        // tell listeners
        notifyListeners(SWT.Selection, new Event());
    }
    
    /**
     * 
     * @return String
     */
    public String getSelection() {
        return selectionName;
    }
    
    public void addSelectionListener(SelectionListener listener) {
        addListener(SWT.Selection, new TypedListener(listener));
    }

    public void widgetDisposed(DisposeEvent e) {
    	Root.getSingleton().detachRenderTarget(renderWindow);
        renderWindow.destroy();
    }

	public OgreScene getScene() {
		return scene;
	}

	public void setActiveCamera(String name) throws Exception {       
        //ICamera ogreCam = scene.activateCam(name);
		
		ICamera ogreCam = scene.getOgreCam(name);
		
        this.camName = name;
        
        // create new vp
        this.viewport = renderWindow.addViewport(ogreCam, 0, 0, 0, 1.0f, 1.0f);
        
//        IColourValue bgColor = new ColourValue(camData.getBgCol().x, camData.getBgCol().y, camData.getBgCol().z,
//                camData.getBgCol().w);
        
        
        //viewport.setBackgroundColour(bgColor);
        
        
//        bgColor.delete();
        

        if (viewport.getActualHeight() != 0) {
            ogreCam.setAspectRatio(viewport.getActualWidth() / viewport.getActualHeight());
        } else {
            ogreCam.setAspectRatio(1);
        }
        viewport.setCamera(ogreCam);


        // set render window active
        renderWindow.setActive(true);

        if (!scene.sceneIsInCreation) {
            // tell renderloop
            OgreSystem.getDefault().redraw();
        }
    }
}
