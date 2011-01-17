package org.ogre4j.eclipse.meshviewer;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * An Eclipse View displaying the OGRE Resource Manager.
 * 
 */
public class OgreResourceView extends ViewPart {

	/**
	 * Default ctor. Registers at native library.
	 */
	public OgreResourceView() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	public void setFocus() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createPartControl(Composite parent) {
		new OgreResourceForm(parent, 0);
	}
}
