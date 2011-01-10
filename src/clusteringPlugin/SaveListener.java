package clusteringPlugin;

import main.ProjectParser;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

public class SaveListener implements IExecutionListener{

	public void postExecuteSuccess(String action, Object arg1) {
		if (action.equals("org.eclipse.ui.file.save")) {
			try {
				IResource oFile = (IResource) PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage()
						.getActiveEditor().getEditorInput()
						.getAdapter(IResource.class);
				final IProject oProject = oFile.getProject();
				if (oProject == null) {
					// can't do anything :(
				} else {
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {
							String projectPath = oProject.getLocation().toOSString();
							ProjectParser projectParser = new ProjectParser();
							//projectParser.parseProjects(projectPath);
						}
					});
					t.start();
				}
			} catch (NullPointerException e) {
				MessageDialog.openInformation(null, "NullPointer",
						e.getMessage());
			}
		}
	}

	@Override
	public void notHandled(String arg0, NotHandledException arg1) {
	}

	@Override
	public void postExecuteFailure(String arg0, ExecutionException arg1) {
	}

	@Override
	public void preExecute(String arg0, ExecutionEvent arg1) {
	}
}

