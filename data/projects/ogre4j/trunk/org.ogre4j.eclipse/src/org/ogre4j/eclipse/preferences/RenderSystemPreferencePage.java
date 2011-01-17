package org.ogre4j.eclipse.preferences;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;
import org.eclipse.ui.progress.UIJob;
import org.ogre4j.eclipse.OgrePlugin;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class RenderSystemPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	public RenderSystemPreferencePage() {
		super(GRID);
		setPreferenceStore(OgrePlugin.getDefault().getPreferenceStore());
		setDescription("Preferences for the 3D rendering engine OGRE");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		addField(new ComboFieldEditor(
				PreferenceConstants.PREFERENCE_RENDERSYSTEM,
				"Choose Render System (Restart needed)", new String[][] {
						new String[] {
								OgrePlugin.RENDERSYSTEM_DISPLAY_NAME_OPENGL,
								OgrePlugin.RENDERSYSTEM_NAME_OPENGL },
						new String[] {
								OgrePlugin.RENDERSYSTEM_DISPLAY_NAME_DIRECT3D9,
								OgrePlugin.RENDERSYSTEM_NAME_DIRECT3D9 } },
				getFieldEditorParent()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
	public boolean performOk() {
		String oldValue = getPreferenceStore().getString(
				PreferenceConstants.PREFERENCE_RENDERSYSTEM);

		boolean doContinue = super.performOk();

		if (doContinue) {
			String newValue = getPreferenceStore().getString(
					PreferenceConstants.PREFERENCE_RENDERSYSTEM);
			if (!oldValue.equals(newValue)) {

				UIJob job = new UIJob("Restart workspace") {

					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						
						boolean restart = MessageDialog
								.openQuestion(
										this.getDisplay().getActiveShell(),
										"Change Of Render System",
										"The change of the render system needs a restart of the system to take effect. Do you want to restart the system now?");
						if (restart) {
							PlatformUI.getWorkbench().restart();
						}
						return Status.OK_STATUS;
					}
				};
				job.setSystem(true);
				IWorkbenchPreferenceContainer container = (IWorkbenchPreferenceContainer) getContainer();
				container.registerUpdateJob(job);
			}
		}
		return doContinue;
	}
}
