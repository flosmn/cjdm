/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.businesslogic.ireport.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gtoffoli
 */
public class JavaProcessExecuter {
    
    private List<String> classpathEntries;
    private List<String> parameters;
    private String mainClass = null;
    
    private String javaExecutable = null;

    public List<String> getClasspathEntries() {
        return classpathEntries;
    }

    public void setClasspathEntries(List<String> calsspathEntries) {
        this.classpathEntries = calsspathEntries;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public String getJavaExecutable() {
        return javaExecutable;
    }

    public void setJavaExecutable(String javaExecutable) {
        this.javaExecutable = javaExecutable;
    }
    
    public JavaProcessExecuter()
    {
        parameters = new ArrayList<String>();
        classpathEntries = new ArrayList<String>();
        
        String java_home = System.getProperty("java.home");
        if (java_home != null)
        {
            javaExecutable = java_home + File.separator + "bin" + File.separator + "java";
            if (System.getProperty("os.name").toLowerCase().contains("windows"))
            {
                javaExecutable += ".exe";
            }
        }
        //else throw new Exception("Unable to locate the java home");
    }

    public void dumpOutput(final InputStream in)
    {
        Runnable r = new Runnable() {

            public void run() {
                try {
                            String s = "";
                            byte[] buffer = new byte[1024];
                            int count = 0;
                            while ((count = in.read(buffer)) >= 0) {
                                if (count > 0)
                                s += new String(buffer,0,count);
                            }
                            System.out.println(s);
                            System.out.flush();
                            in.close();
                } catch (IOException ex) {

                }
            }
        };
        
        new Thread(r).start();
        
    }
    
    public void execute() throws IOException
    {
        try {
            String classpath = "";
            for (String cpitem : getClasspathEntries()) {
                classpath += cpitem + File.pathSeparator;
            }

            List<String> cmdList = new ArrayList<String>();
            cmdList.add(javaExecutable);
            cmdList.add("-Xmx512m");
            cmdList.add("-cp");
            cmdList.add(classpath);
            cmdList.add(getMainClass());
            cmdList.addAll(getParameters());

//            System.out.println("classpath: " + classpath);
//            System.out.flush();
            
            String[] cmdArray = new String[cmdList.size()];
            Process process = Runtime.getRuntime().exec(cmdList.toArray(cmdArray));
            
            
            // Redirect the output stream...
            // Get the input stream and read from it
            dumpOutput(process.getErrorStream());
            dumpOutput(process.getInputStream());
            
            process.waitFor();

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
    }

}
