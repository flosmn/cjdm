package org.ogre4j.eclipse.meshviewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.PaintObjectEvent;
import org.eclipse.swt.custom.PaintObjectListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.ogre4j.LogManager;
import org.ogre4j.LogMessageLevel;
import org.ogre4j.nativelisteners.NativeLogListener;

/**
 * An Eclipse View displaying the OGRE log.
 * 
 * <p>
 * TODO add LogListener ExtensionPoint to get OGRE initializing messages.
 * </p>
 */
public class OgreLogView extends ViewPart implements IPartListener {

	/** Stores image instance for OGRE log levels. */
	private Image[] loadedImages;
	/** Index for icon "critical" in loadedImages[] */
	public static final int IMG_INDEX_CRITICAL = 0;
	/** Index for icon "trivial" in loadedImages[] */
	public static final int IMG_INDEX_TRIVIAL = 2;
	/** Index for icon "normal" in loadedImages[] */
	public static final int IMG_INDEX_NORMAL = 1;

	/** Final size of images in log view. */
	private int scaleImagesTo = 16;

	/**
	 * Vertical offset of drawn images. The text area's line-spacing will be set
	 * to this value too to provide the needed space. Must be positive.
	 */
	private int scaledImagesYOffset = 4;

	/** References to images in log. */
	private List<Image> images = new ArrayList<Image>();

	/** Offsets of images on log. */
	private List<Integer> offsets = new ArrayList<Integer>();

	/** Widget to write log messages in. */
	private StyledText styledTextArea = null;

	/** Log listener instance. */
	private OgreLogListener logListener;

	/**
	 * Receives native callbacks.
	 * 
	 */
	class OgreLogListener extends NativeLogListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.ogre4j.ILogListener#messageLogged(java.lang.String,
		 * org.ogre4j.LogMessageLevel, boolean, java.lang.String)
		 */
		public void messageLogged(String message, LogMessageLevel lml,
				boolean maskDebug, String logName) {

			if (styledTextArea != null && !styledTextArea.isDisposed()) {

				// spaces around icon 1/2
				styledTextArea.append(" ");

				// add corresponding icon image to list
				if (lml == LogMessageLevel.LML_CRITICAL) {
					images.add(loadedImages[IMG_INDEX_CRITICAL]);
				} else if (lml == LogMessageLevel.LML_NORMAL) {
					images.add(loadedImages[IMG_INDEX_NORMAL]);
				} else {
					images.add(loadedImages[IMG_INDEX_TRIVIAL]);
				}

				// new image's offset = current end of text area
				Integer offset = new Integer(styledTextArea.getText().length());

				// add object-symbol to text
				styledTextArea.append("\uFFFC");

				offsets.add(offset);

				// set new object-symbol's style settings to fit image-size
				StyleRange style = new StyleRange();
				style.start = offset;
				style.length = 1;
				style.metrics = new GlyphMetrics(scaleImagesTo, 0,
						scaleImagesTo);
				styledTextArea.setStyleRange(style);

				// spaces around icon 2/2
				styledTextArea.append(" ");

				// add message
				styledTextArea.append(message + "\n");

				// scroll down
				int txtLength = styledTextArea.getText().length();
				styledTextArea.setSelection(txtLength, txtLength);
			}
		}
	}

	/**
	 * Default ctor. Registers at native library.
	 * <p>
	 * TODO use extension point to receive log messages before this ctor is
	 * called.
	 * </p>
	 */
	public OgreLogView() {
		this.logListener = new OgreLogListener();
		LogManager.getSingleton().getDefaultLog().addListener(logListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	public void setFocus() {
		styledTextArea.setFocus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createPartControl(Composite parent) {
		styledTextArea = new StyledText(parent, SWT.MULTI | SWT.WRAP
				| SWT.V_SCROLL | SWT.READ_ONLY);
		styledTextArea.setBackground(getSite().getShell().getDisplay()
				.getSystemColor(SWT.COLOR_WHITE));
		styledTextArea.setLineSpacing(scaledImagesYOffset);

		loadedImages = new Image[] {
				parent.getDisplay().getSystemImage(SWT.ICON_ERROR),
				parent.getDisplay().getSystemImage(SWT.ICON_INFORMATION),
				parent.getDisplay().getSystemImage(SWT.ICON_WARNING) };

		// draw the images onto the text area
		styledTextArea.addPaintObjectListener(new PaintObjectListener() {
			public void paintObject(PaintObjectEvent event) {
				GC gc = event.gc;
				StyleRange style = event.style;
				int start = style.start;
				for (int i = 0; i < offsets.size(); i++) {
					int offset = offsets.get(i).intValue();
					if (start == offset) {
						Image image = images.get(i);
						int x = event.x;
						int y = event.y + event.ascent - style.metrics.ascent;
						Rectangle rect = image.getBounds();
						gc.drawImage(image, 0, 0, rect.width, rect.height, x, y
								+ scaledImagesYOffset, scaleImagesTo,
								scaleImagesTo);
					}
				}
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partActivated(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partActivated(IWorkbenchPart part) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partBroughtToTop(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partBroughtToTop(IWorkbenchPart part) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partClosed(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partClosed(IWorkbenchPart part) {
		LogManager.getSingleton().getDefaultLog().removeListener(logListener);

		// we are not allowed to free these
		// loadedImages[0].dispose();
		// loadedImages[1].dispose();
		// loadedImages[2].dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partDeactivated(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partDeactivated(IWorkbenchPart part) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener#partOpened(org.eclipse.ui.IWorkbenchPart)
	 */
	public void partOpened(IWorkbenchPart part) {
	}
}
