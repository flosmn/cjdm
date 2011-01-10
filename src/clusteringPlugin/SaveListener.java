package clusteringPlugin;

import java.io.File;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

public class SaveListener implements IExecutionListener{

	public SaveListener()
	{
		
	}

	public void postExecuteSuccess(String action, Object arg1) 
	{
		//MessageDialog.openInformation(null,"Event",action);
	        if (action.equals("org.eclipse.ui.file.save"))
	        {
	        	try
	        	{
		        	IResource oFile = (IResource)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput().getAdapter(IResource.class);
		        	IProject oProject = oFile.getProject();
		        	if (oProject == null)
		        	{
		        		// can't do anything :(
		        	}
		        	else
		        	{
		        		//MessageDialog.openInformation(null,"Event",oFile.getProjectRelativePath().toOSString());
		        		//MessageDialog.openInformation(null,"Event",oProject.getFullPath().toOSString());
		        		MessageDialog.openInformation(null,"Event",oProject.getLocation().toOSString());
		        		File updatedProject = new File(oProject.getLocation().toOSString());
		        		
		        	}
	        	}
	        	catch (NullPointerException e)
	        	{
	        		MessageDialog.openInformation(null,"NullPointer",e.getMessage());
	        	}
	        }
	}

	@Override
	public void notHandled(String arg0, NotHandledException arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postExecuteFailure(String arg0, ExecutionException arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preExecute(String arg0, ExecutionEvent arg1) {
		// TODO Auto-generated method stub
		
	}
}

