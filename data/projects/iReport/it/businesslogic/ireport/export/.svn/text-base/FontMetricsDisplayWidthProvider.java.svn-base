/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.businesslogic.ireport.export;

import java.awt.Font;
import java.io.InputStream;
import javax.swing.JLabel;

/**
 * Display width provider that uses font metrics to arrive at
 * display width of a given string. This provider should be used
 * when the report being generated may contain combined characters
 * (where the value returned by String.length() may not match the
 * actual display width).
 *
 * This provider relies on availability of a fixed width (monospaced)
 * unicode font to arrive at the display width.
 *
 * @author nandini_duggal
 */
public class FontMetricsDisplayWidthProvider implements IDisplayWidthProvider {

    /*
     * Font Metrics object used to calculate display width
     */
    private java.awt.FontMetrics fm = null;

    /*
     * Display width of one character
     */
    private static int oneCharWidth = 0;

    /**
     * Default character used for calculating width of single display character
     */
    private static String defaultChar = "N";

    /**
     * 
     * @param ttfFontPath The name of the font to use to calculate the width
     */
    public FontMetricsDisplayWidthProvider(String ttfFontPath) {
        init(ttfFontPath);
    }

    /**
     * As font name will be used the value of the system property: TXTRPT_ALIGNMENT_FONT
     */
    public FontMetricsDisplayWidthProvider() {
        init(null);
    }

    /**
     */
    public void init(String fontName)
    {

        try{

            Font font = null;

            if (fontName == null)
            {
                fontName = System.getProperty("TXTRPT_ALIGNMENT_FONT");
            }

            if (fontName != null)
            {

                //System.out.println("Loading font " + fontName);
                //System.out.flush();
                // Load the font
                InputStream fontInputStream = FontMetricsDisplayWidthProvider.class.getClassLoader().getResourceAsStream(fontName);

                font = Font.createFont(Font.TRUETYPE_FONT, fontInputStream);
            }
            
            if (font == null)
            {
                font = new Font("Courier New",0,12);
            }
            // Create font metrics object
            this.fm =  new JLabel("").getFontMetrics(font);

            // Calculate display width of one character
            this.oneCharWidth = fm.stringWidth(defaultChar);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will give the diplay width of given string
     * using font metrics
     */
    public int getDisplayWidth(String str) {
        if(fm != null && oneCharWidth > 0) {
            // Divide the string width of string by width of one character and  return
            return fm.stringWidth(str)/oneCharWidth;
        } else {
            return str.length();
        }
    }



}
