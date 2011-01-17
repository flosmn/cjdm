/*******************************************************************************
 * Copyright (c) 2004, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.ogre4j.eclipse.meshviewer.rcp;

import java.io.File;
import java.text.MessageFormat;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.ide.FileStoreEditorInput;

public class OpenFileAction extends Action implements IWorkbenchWindowActionDelegate {

    private IWorkbenchWindow fWindow;

    public OpenFileAction() {
        setEnabled(true);
    }

    public void dispose() {
        fWindow = null;
    }

    public void init(IWorkbenchWindow window) {
        fWindow = window;
    }

    private File queryFile() {
        FileDialog dialog = new FileDialog(fWindow.getShell(), SWT.OPEN);
        dialog.setText("Open File"); //$NON-NLS-1$
        String path = dialog.open();
        if (path != null && path.length() > 0)
            return new File(path);
        return null;
    }

    /*
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        File file = queryFile();
        if (file != null) {
            IWorkbenchPage page = fWindow.getActivePage();
            try {
                IFileStore fileStore = EFS.getStore(file.toURI());
                page.openEditor(new FileStoreEditorInput(fileStore), "org.ogre4j.eclipse.meshviewer.MeshViewer");
            } catch (CoreException e) {
                e.printStackTrace();
            }
        } else if (file != null) {
            String msg = MessageFormat.format("File is null: {0}", (Object[]) new String[] { file.getName() }); //$NON-NLS-1$
            MessageDialog.openWarning(fWindow.getShell(), "Problem", msg); //$NON-NLS-1$
        }
    }

    public void run(IAction action) {
        run();
    }

    public void selectionChanged(IAction action, ISelection selection) {
    }
}