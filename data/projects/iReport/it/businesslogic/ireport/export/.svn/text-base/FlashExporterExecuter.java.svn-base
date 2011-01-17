/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.businesslogic.ireport.export;

import it.businesslogic.ireport.util.JavaProcessExecuter;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gtoffoli
 */
public class FlashExporterExecuter extends JavaProcessExecuter {

    private String jrprintFile = null;
    private String outputFile = null;
    
    
    public FlashExporterExecuter()
    {
        super();
        
        // Add all the jars in the Flex SDK lib directory...
        if (System.getenv("FLEX_SDK_HOME") != null)
        {
            File f = new File(System.getenv("FLEX_SDK_HOME") + File.separator + "lib");
            File[] jars = f.listFiles();
            for (int i=0; i<jars.length; ++i)
            {
                if (jars[i].getName().toLowerCase().endsWith(".jar"))
                {
                    getClasspathEntries().add(0, jars[i].getPath());
                }
            }
        }
        else
        {
            System.out.println("FLEX_SDK_HOME not set!");
            System.out.flush();
        }
        
        Vector cp = it.businesslogic.ireport.gui.MainFrame.getMainInstance().getClasspath();
        for (int i=0; i<cp.size(); ++i)
        {
            File f = new File( cp.elementAt(i) + "" );
            if (!f.exists()) continue;
            try {
                getClasspathEntries().add(f.getCanonicalPath());
            } catch (Exception ex)
            {
                System.out.println("Invalid path: " + f);
            }
        }
            
        // Add all the other jars...
        String irHome = it.businesslogic.ireport.gui.MainFrame.getMainInstance().IREPORT_HOME_DIR;
	if (irHome == null) irHome = System.getProperty("ireport.home",".");
        
        File f_classes = new File(irHome,"classes");
        if (f_classes.exists())
        {
            try {
                getClasspathEntries().add(f_classes.getCanonicalPath());
            } catch (IOException ex) {
                Logger.getLogger(FlashExporterExecuter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        File lib_dir = new File(irHome,"lib");     
        if (lib_dir.exists())
        {
            // Add all the jars here...
            File[] jars = lib_dir.listFiles();
            for (int i=0; i<jars.length; ++i)
            {
                if (jars[i].getName().toLowerCase().endsWith(".jar") ||
                    jars[i].getName().toLowerCase().endsWith(".zip"))
                {
                    
                    try {
                        getClasspathEntries().add(jars[i].getCanonicalPath());
                    } catch (Exception ex)
                    {
                        System.out.println("Invalid path: " + jars[i]);
                    }
                }
            }
        }
        
        setMainClass("it.businesslogic.ireport.export.FlashExporter");
            
    }

    public String getJrprintFile() {
        return jrprintFile;
    }

    public void setJrprintFile(String jrprintFile) {
        this.jrprintFile = jrprintFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public void execute() throws IOException {
    
        getParameters().clear();
        getParameters().add( getJrprintFile() );
        getParameters().add( getOutputFile() );
        
        super.execute();
    }
    
    
            
}
