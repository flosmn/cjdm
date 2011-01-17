/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.businesslogic.ireport.export;

import it.businesslogic.ireport.util.Misc;
import java.io.File;
import java.io.IOException;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.flex.JRSwfExporter;

/**
 *
 * @author gtoffoli
 */
public class FlashExporter extends JRAbstractExporter {

    public static void main(String[] args)
    {
        // 1. jrprint
        // 2. output file
        
        String sourceName = args[0];
        String fileName = args[1];

        try
        {
            File sourceFile = new File(sourceName);
            JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);
            JRSwfExporter exporter = new JRSwfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(
                    JRExporterParameter.OUTPUT_FILE_NAME, 
                    new File(fileName).getAbsolutePath()
                    );
            exporter.setParameter(net.sf.jasperreports.export.flex.JRSwfExporterParameter.SUPPRESS_COMPILER_MESSAGES, false);
            //exporter.setParameter(JRSwfExporterParameter.IS_EMBEDDING_IMAGES, Boolean.FALSE);
            //exporter.setParameter(JRSwfExporterParameter.IS_EMBEDDING_FONTS, Boolean.FALSE);
            //exporter.setParameter(JRSwfExporterParameter.IS_USE_NETWORK, Boolean.TRUE);
            
            System.out.println("Exporting to file " + fileName);
            System.out.flush();
            
            exporter.exportReport();
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }
        
    }

    public void exportReport() throws JRException {
        try {

            JasperPrint jasperPrint = (JasperPrint) getParameter(JRExporterParameter.JASPER_PRINT);
            String outputFileName = (String) getParameter(JRExporterParameter.OUTPUT_FILE_NAME);
            outputFileName = Misc.changeFileExtension(outputFileName, ".jrprint");
            
            String swfOutputFileName = (String) getParameter(JRExporterParameter.OUTPUT_FILE_NAME);
            File swfOutputFile = new File(swfOutputFileName);
            if (swfOutputFile.exists()) swfOutputFile.delete();
            
            JRSaver.saveObject(jasperPrint, outputFileName);
            FlashExporterExecuter executer = new FlashExporterExecuter();
            executer.setJrprintFile(outputFileName);
            executer.setOutputFile(swfOutputFileName);
            executer.execute();
            // Delete the jrprint...
            File f = new File(outputFileName);
            if (f.exists()) f.delete();
            if (!swfOutputFile.exists())
            {
                throw new JRException("Exception occurred. Unable to create the SWF file.");
            }
        
        } catch (IOException ex) {
            throw new JRException(ex);
        }
    }
    
}
