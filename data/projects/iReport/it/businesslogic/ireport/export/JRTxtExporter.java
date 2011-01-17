/* ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * Copyright (C) 2001-2007 JasperSoft Corporation http://www.jaspersoft.com
 *
 * This class is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This class is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this class; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 *y
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 *
 *
 *
 * JRTxtExporter.java
 *
 * Created on 16 aprile 2004, 10.27
 *
 */
package it.businesslogic.ireport.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JRPropertiesMap;

/**
 *
 * @author  giulio toffoli
 *
 *  This is a really simple exporter with many assumptions on report.
 *  The report page is divided in a grid of 10 pixel x 10 pixel. In each of this
 *  pixels there is a char.
 *  Elements must be inserted into this grid (so elements must have coords and dims
 *  that are multiple of 10px).
 *
 */
public class JRTxtExporter extends JRAbstractExporter {

    /**
     * The display width provider
     */
    private IDisplayWidthProvider dwProvider = null;

    /** Creates a new instance of JRPdfExporter */
    public JRTxtExporter() {

        // Get the value of BIDI Markers ( implicit directional marker ) and  is set to System property
        bidi_prefix = System.getProperty("BIDI_PREFIX");

        // Set the flag if bidi markers is not null and not blank       
        if (bidi_prefix != null && bidi_prefix.trim().length() > 0) {
            //System.out.println("iReport TXT exporter : BIDI Prefix being used...");
            useBIDIMarker = true;
        } 
		/*else {
            System.out.println("iReport TXT exporter : NO BIDI Prefix!");
        }*/

    }
    int PAGE_ROWS = 61;
    int PAGE_COLUMNS = 255;
    int CHAR_UNIT_H = 10;
    int CHAR_UNIT_V = 20;
    boolean ADD_FORM_FEED = true;
    /**
     *
     */
    protected String delimiter = null;
    /**
     *
     */
    protected Writer writer = null;
    protected JRExportProgressMonitor progressMonitor = null;
    
	/**
     * This variable will hold the difference between the length of the line and
     * display width of line
     */
    private int currentLineDiff = 0;

    /**
     * This flags tells whether BIDI Markers are to be used or not
     */
    private boolean useBIDIMarker = false;

	/**
	 * Holds the Bidi marker value 
	 */
    private String bidi_prefix = "";

    /**
     *
     */
    public void exportReport() throws JRException {
        progressMonitor = (JRExportProgressMonitor) parameters.get(JRExporterParameter.PROGRESS_MONITOR);

        /*   */
        setInput();

        /*   */
        setPageRange();


        String encoding = (String) parameters.get(JRExporterParameter.CHARACTER_ENCODING);
        if (encoding == null) {
            encoding = "ISO-8859-1";
        }
        //System.out.println("Using encoding: " + encoding);

        Object obj = parameters.get(JRTxtExporterParameter.DISPLAY_WIDTH_PROVIDER_FACTORY);

        if (obj != null && obj instanceof String) // try to instance the class
        {
            String stringDisplaywidthProviderFactoryClass = (String) obj;

            try {

                obj = Class.forName(stringDisplaywidthProviderFactoryClass, true,
                        classLoader == null ? Thread.currentThread().getContextClassLoader() : classLoader).newInstance();

            } catch (Throwable t) {
                t.printStackTrace();
            }
        }


        if (obj != null && obj instanceof IDisplayWidthProviderFactory) {
            try {

                dwProvider = ((IDisplayWidthProviderFactory) obj).createDisplayWidthProvider();

            } catch (Throwable t) {
                t.printStackTrace();
            }

        }

        String page_rows = (String) parameters.get(JRTxtExporterParameter.PAGE_ROWS);
        if (page_rows != null) {
            PAGE_ROWS = Integer.parseInt(page_rows);
            ;
        } else {
            PAGE_ROWS = this.jasperPrint.getPageHeight() / 20;
        }

        String page_cols = (String) parameters.get(JRTxtExporterParameter.PAGE_COLUMNS);
        if (page_cols != null) {
            PAGE_COLUMNS = Integer.parseInt(page_cols);
        } else {
            PAGE_COLUMNS = this.jasperPrint.getPageWidth() / this.CHAR_UNIT_H;
        }

        Object add_form_feed = parameters.get(JRTxtExporterParameter.ADD_FORM_FEED);
        if (add_form_feed != null) {

            ADD_FORM_FEED = (add_form_feed + "").equals("true");
        } else {
            ADD_FORM_FEED = true;
        }



        delimiter = ",";

        StringBuffer sb = (StringBuffer) parameters.get(JRXmlExporterParameter.OUTPUT_STRING_BUFFER);
        if (sb != null) {
            try {
                writer = new StringWriter();
                exportReportToWriter();
                sb.append(writer.toString());
            } catch (IOException e) {
                throw new JRException("Error writing to StringBuffer writer : " + jasperPrint.getName(), e);
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                    }
                }
            }
        } else {
            writer = (Writer) parameters.get(JRExporterParameter.OUTPUT_WRITER);
            if (writer != null) {
                try {
                    exportReportToWriter();
                } catch (IOException e) {
                    throw new JRException("Error writing to writer : " + jasperPrint.getName(), e);
                }
            } else {
                OutputStream os = (OutputStream) parameters.get(JRExporterParameter.OUTPUT_STREAM);
                if (os != null) {
                    try {
                        writer = new OutputStreamWriter(os, encoding);
                        exportReportToWriter();
                    } catch (IOException e) {
                        throw new JRException("Error writing to OutputStream writer : " + jasperPrint.getName(), e);
                    }
                } else {
                    File destFile = (File) parameters.get(JRExporterParameter.OUTPUT_FILE);
                    if (destFile == null) {
                        String fileName = (String) parameters.get(JRExporterParameter.OUTPUT_FILE_NAME);
                        if (fileName != null) {
                            destFile = new File(fileName);
                        } else {
                            throw new JRException("No output specified for the exporter.");
                        }
                    }

                    try {
                        os = new FileOutputStream(destFile);
                        writer = new OutputStreamWriter(os, encoding);
                        exportReportToWriter();
                    } catch (IOException e) {
                        throw new JRException("Error writing to file writer : " + jasperPrint.getName(), e);
                    } finally {
                        if (writer != null) {
                            try {
                                writer.close();
                            } catch (IOException e) {
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     *
     */
    protected void exportReportToWriter() throws JRException, IOException {
        List pages = jasperPrint.getPages();
        if (pages != null && pages.size() > 0) {
            JRPrintPage page = null;

            for (int i = startPageIndex; i <= endPageIndex; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    throw new JRException("Current thread interrupted.");
                }

                page = (JRPrintPage) pages.get(i);

                /*   */
                exportPage(page);
            }
        }

        writer.flush();
    }

    /**
     *
     */
    protected void exportPage(JRPrintPage page) throws JRException, IOException {
        Vector lines = layoutGrid(page);

        int y = 0;
        for (y = 0; y < lines.size(); y++) {
            String s = ("" + lines.elementAt(y));
            while (s.endsWith(" ")) {
                s = s.substring(0, s.length() - 1);
            }
            writer.write(s);
            writer.write("\n");
        }

        while (y < PAGE_ROWS) {
            writer.write("\n");
            y++;
        }
        if (ADD_FORM_FEED) {
            writer.write("\f");
        }

        if (progressMonitor != null) {
            progressMonitor.afterPageExport();
        }
    }

    // modified layout grid
    protected Vector layoutGrid(JRPrintPage page) {
        Vector v_lines = new Vector();

        String void_line = "";
        for (int i = 0; i < PAGE_COLUMNS; ++i) {
            void_line += " ";
        }

        for (int i = 0; i < PAGE_ROWS; ++i) {
            v_lines.add(void_line += " ");
        }

        List yCuts = yCuts = new ArrayList();
        yCuts.add(new Integer(0));
        yCuts.add(new Integer(jasperPrint.getPageHeight()));

        Integer y = null;

        Collection elems = page.getElements();
        for (Iterator it = elems.iterator(); it.hasNext();) {
            JRPrintElement element = ((JRPrintElement) it.next());

            if (element instanceof JRPrintText) {
                y = new Integer(element.getY());
                if (!yCuts.contains(y)) {
                    yCuts.add(y);
                }
                y = new Integer(element.getY() + element.getHeight());
                if (!yCuts.contains(y)) {
                    yCuts.add(y);
                }
            }
        }

        Collections.sort(yCuts);
        int yCellCount = yCuts.size() - 1;

        int real_line_num = 0;

        // Get all the JRPrintElements in an array
        JRPrintElement elements[] = (JRPrintElement[]) elems.toArray(new JRPrintElement[0]);

        // Call quick sort method
        JRPrintElement sortedElements[] = doQuickSort(elements, 0, elements.length - 1);

        // Separate all elements in the page according to their yCut
        HashMap pageElements = separateElementsByLine(sortedElements);

        for (int j = 0; j < yCellCount; j++) {
            if (v_lines.size() <= real_line_num) {
                v_lines.add(new String(void_line));
            }

            Integer actual_cut = ((Integer) (yCuts.get(j)));

            int lines_used = 1;

            // Resetting currentLineDiff to zero
            currentLineDiff = 0;

            HashMap lineDiffs = new HashMap();

            // Get list of all elements in the line i.e. the elements which have Y = actual cut
            ArrayList lineElements = (ArrayList) pageElements.get(actual_cut);

            if (lineElements != null) {

                for (Iterator it = lineElements.iterator(); it.hasNext();) {

                    JRPrintText pt = ((JRPrintText) it.next());

                    // 1. take starting char...
                    int start_at = pt.getX() / CHAR_UNIT_H;
                    int len = pt.getWidth() / CHAR_UNIT_H;

                    String str = pt.getText();

                    int line_height = 0;
                    if (pt.getHeight() <= CHAR_UNIT_V) // Single line object
                    {

                        if (str.length() > len) {
                            str = str.substring(0, len);
                        }

                        if (pt.getY() / CHAR_UNIT_V > real_line_num) {
                            real_line_num = pt.getY() / CHAR_UNIT_V;
                        }
                        if (v_lines.size() <= real_line_num) {
                            v_lines.add(new String(void_line));
                        }
                        String line = (String) v_lines.elementAt(real_line_num);

                        line = replaceLineRegion(line, start_at, len, str, pt.getTextAlignment());

                        // Maintain the diff for every line
                        lineDiffs.put(real_line_num, currentLineDiff);

                        v_lines.setElementAt(line, real_line_num);
                    } else // is a multiline...
                    {
                        int max_field_lines = 1;

                        //Vector field_lines = getFieldLines(line_height + " " + pt.getHeight() + " " + max_field_lines + str,len);
                        Vector field_lines = getFieldLines(str, len);

                        // For each line, write the right text...
                        int nl = 0;
                        for (nl = 0; nl < field_lines.size(); nl++) {
                            String text = (String) field_lines.elementAt(nl);

                            //text = text.replace(' ','*');

                            //text = field_lines.size()+  " " +text;
                            while (v_lines.size() <= (real_line_num + nl)) {
                                v_lines.add(new String(void_line));
                            }

                            String line = (String) v_lines.elementAt(real_line_num + nl);
							
							/**
							 * Check for Linediff for the current line number. If the lineDiff already exists 
							 * then update the same to currentLineDiff
							 */

                            if (lineDiffs.containsKey(real_line_num + nl)) {
                                // Setting the line diff for every field line
                                currentLineDiff = (Integer) lineDiffs.get(real_line_num + nl);
                            } else {
                                currentLineDiff = 0;
                            }

                            line = replaceLineRegion(line, start_at, len, text, pt.getTextAlignment());

                            // Maintain the diff for every field line
                            lineDiffs.put(real_line_num + nl, currentLineDiff);

                            v_lines.setElementAt(line, real_line_num + nl);

                        }
						// lines_used to be updated after all the wrapped lines gets printed
                        if (nl > lines_used) {
                            lines_used = nl;
                        }
                    }


                //isRowUsed[y1] = true;
                //isColUsed[x1] = true;
                }
            }
            real_line_num += lines_used;
        }

        return v_lines;
    }

    /**
     * Separates all the elements based on their Y cuts <br>
     * and returns a hashmap having key = Y cut, value = list of all JRPrintText elements
     * @param elems Collection of print elements
     * @return Hashmap of print text elements
     */
    private HashMap separateElementsByLine(JRPrintElement elems[]) {
        // This hashmap will contain all the elements of page line wise
        HashMap pageElements = new HashMap();

        for (int i = 0; i < elems.length; i++) {
            JRPrintElement element = elems[i];

            if (element instanceof JRPrintText) {
                JRPrintText pt = (JRPrintText) element;

                // Get the Y cut of the element
                Integer yCut = Integer.valueOf(pt.getY());

                // Get the list of elements for this yCut
                ArrayList lineElements = (ArrayList) pageElements.get(yCut);
                if (lineElements == null) {
                    //  If list for this line not yet created. create it.
                    lineElements = new ArrayList();
                    // Add it to the hashmap
                    pageElements.put(yCut, lineElements);
                }

                // Add the PrintText object in the line elements list
                lineElements.add(pt);
            }
        }

        return pageElements;
    }

    /*
    protected String replaceLineRegion(String line,int start_at, int len, String str, byte alignment)
    {
    int side = 0;
    if (alignment == net.sf.jasperreports.engine.JRAlignment.HORIZONTAL_ALIGN_CENTER)
    {
    side = len - str.length();
    if (side > 0)
    {
    side = side/2;
    }
    }

    for (int k=0; k < side; ++k)
    {
    str = " "+str;
    }

    if (alignment == net.sf.jasperreports.engine.JRAlignment.HORIZONTAL_ALIGN_RIGHT)
    {
    while (str.length() < len) str = " "+str;
    }

    while (str.length() < len) str += " ";

    return  line.substring(0,start_at) + str + line.substring(start_at+len);
    }
     */
    protected String replaceLineRegion(String line, int start_at, int len, String str, byte alignment) {
        int side = 0;

        // Check whether bidi markers are to be appended to string or not
        if (useBIDIMarker) {
            str = bidi_prefix + str;
        }

        int displayWidthOfString = getDisplayWidth(str);
        if (alignment == net.sf.jasperreports.engine.JRAlignment.HORIZONTAL_ALIGN_CENTER) {
            // Get difference between element length and string display width
            //side = len - str.length();
            side = len - displayWidthOfString;

            if (side > 0) {
                side = side / 2;
                for (int k = 0; k < side; ++k) {
                    str = " " + str;
                }
                // Since spaces have been added to the string,
                // increment display width accordingly
                displayWidthOfString = displayWidthOfString + side;
            }
        }


        if (alignment == net.sf.jasperreports.engine.JRAlignment.HORIZONTAL_ALIGN_RIGHT) {
            // Get difference between element length and string display width
            side = len - displayWidthOfString;

            //while (str.length() < len) str = " "+str;
            for (int i = 1; i <= side; i++) {
                str = " " + str;
            }
            // Since spaces have been added to the string,
            // increment display width accordingly
            displayWidthOfString = displayWidthOfString + side;
        }

        // Get string length of the string
        int lengthOfString = str.length();
        // Get difference between display width of string and length of the element
        side = len - displayWidthOfString;

        //while (str.length() < len) str += " ";
        for (int i = 1; i <= side; i++) {
            str += " ";
        }

        // Create the replaced line by inserting the string at appropriate place
        String replacedLine = line.substring(0, start_at + currentLineDiff) + str + line.substring(start_at + len + currentLineDiff);

        // Add the difference between length and display width of the string to line diff
        currentLineDiff = currentLineDiff + lengthOfString - displayWidthOfString;
        return replacedLine;
    }

    public Vector getFieldLines(String str, int row_len) {
        Vector lines = new Vector();

        StringTokenizer st = new StringTokenizer(str, "\n", false);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            while (token.length() > row_len) {
                // Find the last space before character row_len....
                String tmp_line = token.substring(0, row_len);
                int li = tmp_line.lastIndexOf(' ');
                if (token.charAt(row_len) == ' ') {
                    lines.addElement(tmp_line);
                    token = token.substring(row_len).trim();
                } else if (li == -1) {
                    lines.addElement(tmp_line);
                    token = token.substring(row_len).trim();
                } else {
                    tmp_line = token.substring(0, li);
                    lines.addElement(tmp_line);
                    token = token.substring(li + 1).trim();
                }

            }
            if (token.trim().length() > 0) {
                if (lines.size() == 0) {
                    lines.addElement(token);
                } else {
                    lines.addElement(token.trim());
                }
            }
        }
        //lines.addElement("" +row_len);

        return lines;
    }

    /**
     * Sorts given array of JRPrintElement objects and returns the sorted array
     * @param elems Array of JRPrintElement objects to be sorted
     * @param start
     * @param end
     * @return Sorted array of JRPrintElement objects
     */
    private JRPrintElement[] doQuickSort(JRPrintElement[] elems, int start, int end) {
        //  Start is the lower index, end is the upper index
        //  of the region of array that is to be sorted
        int i = start, j = end;
        JRPrintElement temp;

        //Return empty elements array if the array is empty
        if (elems.length <= 0) {
            return elems;
        }

        int pivot = elems[(start + end) / 2].getX();

        //Partitioning the elements
        do {
            while (elems[i].getX() < pivot) {
                i++;
            }
            while (elems[j].getX() > pivot) {
                j--;
            }
            if (i <= j) {
                // swap the elements
                temp = elems[i];
                elems[i] = elems[j];
                elems[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);

        //Calling quick sort recursively
        if (start < j) {
            doQuickSort(elems, start, j);
        }
        if (i < end) {
            doQuickSort(elems, i, end);
        }

        return elems;
    }

    /**
     * Returns display width of given string. Display width provider will be
     * used if available. Otherwise simple string length will be returned.
     * @param str String whose display width is to be returned
     * @return Display width of the string.
     */
    private int getDisplayWidth(String str) {
        if (dwProvider != null) {
            try {
                return dwProvider.getDisplayWidth(str);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        // By default return string length
        return str.length();
    }
}
