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
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 *
 *
 *
 * ChartFactory.java
 * 
 * Created on 28 settembre 2004, 23.55
 *
 */

package it.businesslogic.ireport.chart;
import java.util.*;
import java.awt.*;
/**
 *
 * @author  Administrator
 */
public abstract class ChartFactory {
    
   static public java.awt.Image drawChart(String[] parameters, it.businesslogic.ireport.IReportScriptlet scriptlet)
   {
       /* redefine it!! */
       return null;
   }
   
   
    protected static int getParameterAsInteger(String name, java.util.Properties props, int defaultValue)
    {
        String val =  props.getProperty(name, ""+defaultValue);
        try {
            return Integer.parseInt(val);
        } catch (Exception ex) { }
        return defaultValue; 
    }
    
    protected static double getParameterAsDouble(String name, java.util.Properties props, double defaultValue)
    {
        String val =  props.getProperty(name, ""+defaultValue);
        try {
            return Double.parseDouble(val);
        } catch (Exception ex) { }
        return defaultValue; 
    }
    
    
    protected static boolean getParameterAsBoolean(String name, java.util.Properties props, boolean defaultValue)
    {
        String val =  props.getProperty(name, ""+defaultValue);
        try {
            return val.toUpperCase().equals("TRUE");   // from java 1.5.0 only... = Boolean.parseBoolean(val);
        } catch (Exception ex) { }
        return defaultValue; 
    }
    
    protected static java.awt.Color getParameterAsColor(String name, java.util.Properties props, java.awt.Color defaultValue)
    {
        String val =  props.getProperty(name,"");
        try {
            
            java.awt.Color c = parseColorString(val);
            if (c != null) return c;
            
        } catch (Exception ex) { }
        return defaultValue; 
    }
    
    protected static java.util.Vector getSeries(String name, java.util.Properties props, it.businesslogic.ireport.IReportScriptlet scriptlet)
    {

        if (scriptlet == null)
        {
            Vector v =  new Vector();
            v.add(new Double(1));
            v.add(new Double(5));
            v.add(new Double(3));
            v.add(new Double(8));
            v.add(new Double(3));
            v.add(new Double(5));
            v.add(new Double(1));
            v.add(new Double(7));
            v.add(new Double(6));
            v.add(new Double(9));
            
            return v;
        }
        if (!props.containsKey( name ) ) return new Vector();
        String varName = (String)props.getProperty(name);
        
        
        return scriptlet.getSerie( varName );
    }
    
    public static java.awt.Color parseColorString(String newValue)
    {
        if (newValue == null) return null;
        
        newValue = newValue.trim();
        if (!newValue.startsWith("[") || !newValue.endsWith("]"))
        {
            return null;
        }
        
        int r = 0;
        int g = 0;
        int b = 0;
        String rgbValues = newValue.substring(1,newValue.length()-1);  
        try {
        
            StringTokenizer st = new StringTokenizer(rgbValues, ",",false);
            r = Integer.parseInt( st.nextToken() );
            g = Integer.parseInt( st.nextToken() );
            b = Integer.parseInt( st.nextToken() );
        } catch (Exception ex) { return null; }
        
        Color c = new Color(r,g,b);
        return c;
        
    }
}
