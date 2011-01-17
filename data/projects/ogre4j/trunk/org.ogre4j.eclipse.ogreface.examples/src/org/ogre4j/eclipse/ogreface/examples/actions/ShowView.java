/**
 * This file is part of ogre4j
 *  (The JNI bindings of OGRE - Object-Oriented Graphics Rendering Engine).
 *  
 * Copyright (c) 2005-2007 netAllied GmbH. All rights reserved.
 * Also see acknowledgements in README
 * 
 * ogre4j is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 * 
 * ogre4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with ogre4j; see the file COPYING.  If not, write to
 * the Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */
package org.ogre4j.eclipse.ogreface.examples.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.internal.WorkbenchMessages;

public class ShowView implements IWorkbenchWindowActionDelegate
{
    private static final String PAGE = "com.ibm.rcp.ui.perspectives.emptyperspective";
    private static final String VIEW = "org.eclipse.ogre4j.ui.example.simpleogreview";
    private IWorkbenchWindow window;

    public ShowView()
    {
    }

    public void dispose()
    {
    }

    public void init(IWorkbenchWindow window)
    {
        this.window = window;
    }

    public void run(IAction action)
    {
        try
        {
            IWorkbenchPage page = window.getActivePage();

            if (page == null)
            {
                window.openPage(PAGE, null);
                page = window.getActivePage();
            }
            page.showView(VIEW);
        } catch (PartInitException e)
        {
            ErrorDialog.openError(window.getShell(), WorkbenchMessages.ShowView_errorTitle, e.getMessage(), e.getStatus());
        } catch (WorkbenchException e)
        {
            ErrorDialog.openError(window.getShell(), WorkbenchMessages.ShowView_errorTitle, e.getMessage(), e.getStatus());
        }

    }

    public void selectionChanged(IAction action, ISelection selection)
    {
    }
}